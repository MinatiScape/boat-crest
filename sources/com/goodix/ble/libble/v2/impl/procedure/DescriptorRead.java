package com.goodix.ble.libble.v2.impl.procedure;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Build;
import com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureRead;
import com.goodix.ble.libble.v2.impl.BleDescriptorX;
import com.goodix.ble.libble.v2.impl.BleGattX;
import com.goodix.ble.libble.v2.impl.data.BleIntState;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.IEventListener;
/* loaded from: classes5.dex */
public class DescriptorRead extends BleBaseProcedure implements GBGattProcedureRead, IEventListener<BleIntState> {
    public a A;
    public byte[] B;
    public BleDescriptorX z;

    /* loaded from: classes5.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            if (i == 0) {
                DescriptorRead.this.B = bluetoothGattDescriptor.getValue();
                DescriptorRead.this.finishedWithDone();
            } else if (i == 5 || i == 8 || i == 137) {
                DescriptorRead.this.finishedWithError("Insufficient Authentication");
            } else {
                String str = "Error on reading descriptor <" + bluetoothGattDescriptor.getUuid() + ">: " + BleGattX.gattStatusToString(i);
                ILogger iLogger = DescriptorRead.this.logger;
                if (iLogger != null) {
                    iLogger.e("DescriptorRead", str);
                }
                DescriptorRead.this.finishedWithError(str);
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public int doWork2() {
        String str;
        BleDescriptorX bleDescriptorX = this.z;
        if (bleDescriptorX == null) {
            str = "Target descriptor is null.";
        } else {
            BluetoothGattDescriptor gattDescriptor = bleDescriptorX.getGattDescriptor();
            if (gattDescriptor == null) {
                str = "Target descriptor is not discovered.";
            } else if (this.gattX.isConnected()) {
                a aVar = new a();
                this.A = aVar;
                this.gattX.register(aVar);
                if (this.gattX.tryReadDescriptor(gattDescriptor)) {
                    if (Build.VERSION.SDK_INT < 26) {
                        this.gattX.evtBondStateChanged().subEvent(this).setExecutor(getExecutor()).register2(this);
                        return BleBaseProcedure.COMMUNICATION_TIMEOUT;
                    }
                    return BleBaseProcedure.COMMUNICATION_TIMEOUT;
                }
                str = "Failed to read descriptor.";
            } else {
                str = "Failed to read descriptor. The connection is not established.";
            }
        }
        finishedWithError(str);
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureRead
    public byte[] getValue() {
        return this.B;
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure, com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        BleGattX bleGattX = this.gattX;
        if (bleGattX != null) {
            bleGattX.evtBondStateChanged().clear(this);
            a aVar = this.A;
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
                iLogger.v("DescriptorRead", "Retry to read descriptor after bonded");
            }
            if (this.gattX.tryReadDescriptor(this.z.getGattDescriptor())) {
                return;
            }
            finishedWithError("Failed to read descriptor after bonded.");
        }
    }

    public void setTargetDescriptor(BleDescriptorX bleDescriptorX) {
        this.z = bleDescriptorX;
    }
}
