package com.goodix.ble.libble.v2.impl.procedure;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Build;
import com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureWrite;
import com.goodix.ble.libble.v2.impl.BleDescriptorX;
import com.goodix.ble.libble.v2.impl.BleGattX;
import com.goodix.ble.libble.v2.impl.data.BleIntState;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.IEventListener;
/* loaded from: classes5.dex */
public class DescriptorWrite extends BleBaseProcedure implements GBGattProcedureWrite, IEventListener<BleIntState> {
    public byte[] A;
    public a B;
    public BleDescriptorX z;

    /* loaded from: classes5.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            if (i == 0) {
                DescriptorWrite.this.finishedWithDone();
            } else if (i == 5 || i == 8 || i == 137) {
                DescriptorWrite.this.finishedWithError("Insufficient Authentication");
            } else {
                String str = "Error on writing descriptor <" + bluetoothGattDescriptor.getUuid() + ">: " + BleGattX.gattStatusToString(i);
                ILogger iLogger = DescriptorWrite.this.logger;
                if (iLogger != null) {
                    iLogger.e("DescriptorWrite", str);
                }
                DescriptorWrite.this.finishedWithError(str);
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public int doWork2() {
        String str;
        if (this.z == null) {
            str = "Target descriptor is null.";
        } else if (!this.gattX.isConnected()) {
            finishedWithError("Failed to write descriptor. The connection is not established.");
            return 0;
        } else {
            BluetoothGattDescriptor gattDescriptor = this.z.getGattDescriptor();
            if (gattDescriptor == null) {
                str = "Target descriptor is not discovered.";
            } else if (this.A == null) {
                str = "Value is null.";
            } else if (!this.gattX.isConnected()) {
                finishedWithError("Failed to write descriptor. The connection is not established.");
                return 0;
            } else {
                a aVar = new a();
                this.B = aVar;
                this.gattX.register(aVar);
                gattDescriptor.setValue(this.A);
                if (this.gattX.tryWriteDescriptor(gattDescriptor)) {
                    if (Build.VERSION.SDK_INT < 26) {
                        this.gattX.evtBondStateChanged().subEvent(this).setExecutor(getExecutor()).register2(this);
                        return BleBaseProcedure.COMMUNICATION_TIMEOUT;
                    }
                    return BleBaseProcedure.COMMUNICATION_TIMEOUT;
                }
                str = "Failed to write descriptor.";
            }
        }
        finishedWithError(str);
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure, com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        BleGattX bleGattX = this.gattX;
        if (bleGattX != null) {
            bleGattX.evtBondStateChanged().clear(this);
            a aVar = this.B;
            if (aVar != null) {
                this.gattX.remove(aVar);
            }
        }
        super.onCleanup();
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, BleIntState bleIntState) {
        if (bleIntState.state == 12) {
            ILogger iLogger = this.logger;
            if (iLogger != null) {
                iLogger.v("DescriptorWrite", "Retry to write descriptor after bonded");
            }
            if (this.gattX.tryWriteDescriptor(this.z.getGattDescriptor())) {
                return;
            }
            finishedWithError("Failed to write descriptor after bonded.");
        }
    }

    public void setTargetDescriptor(BleDescriptorX bleDescriptorX) {
        this.z = bleDescriptorX;
    }

    @Override // com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureWrite
    public void setValue(byte[] bArr) {
        this.A = bArr;
    }
}
