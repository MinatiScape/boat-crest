package com.goodix.ble.libble.v2.impl.procedure;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Build;
import com.goodix.ble.libble.v2.gb.procedure.GBGattProcedureRead;
import com.goodix.ble.libble.v2.impl.BleCharacteristicX;
import com.goodix.ble.libble.v2.impl.BleGattX;
import com.goodix.ble.libble.v2.impl.data.BleIntState;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.IEventListener;
/* loaded from: classes5.dex */
public class CharacteristicRead extends BleBaseProcedure implements GBGattProcedureRead, IEventListener<BleIntState> {
    public a A;
    public byte[] B;
    public BleCharacteristicX z;

    /* loaded from: classes5.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (i == 0) {
                CharacteristicRead.this.B = bluetoothGattCharacteristic.getValue();
                CharacteristicRead.this.finishedWithDone();
            } else if (i == 5 || i == 8 || i == 137) {
                CharacteristicRead.this.finishedWithError("Insufficient Authentication");
            } else {
                String str = "Error on reading characteristic <" + bluetoothGattCharacteristic.getUuid() + ">: " + BleGattX.gattStatusToString(i);
                ILogger iLogger = CharacteristicRead.this.logger;
                if (iLogger != null) {
                    iLogger.e("CharacteristicRead", str);
                }
                CharacteristicRead.this.finishedWithError(str);
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public int doWork2() {
        String str;
        BleCharacteristicX bleCharacteristicX = this.z;
        if (bleCharacteristicX == null) {
            str = "Target characteristic is null.";
        } else {
            BluetoothGattCharacteristic gattCharacteristic = bleCharacteristicX.getGattCharacteristic();
            if (gattCharacteristic == null) {
                str = "Target characteristic is not discovered.";
            } else if (this.gattX.isConnected()) {
                a aVar = new a();
                this.A = aVar;
                this.gattX.register(aVar);
                if (this.gattX.tryReadCharacteristic(gattCharacteristic)) {
                    if (Build.VERSION.SDK_INT < 26) {
                        this.gattX.evtBondStateChanged().subEvent(this).setExecutor(getExecutor()).register2(this);
                        return BleBaseProcedure.COMMUNICATION_TIMEOUT;
                    }
                    return BleBaseProcedure.COMMUNICATION_TIMEOUT;
                }
                str = "Failed to read characteristic.";
            } else {
                str = "Failed to read characteristic. The connection is not established.";
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
                iLogger.v("CharacteristicRead", "Retry to read characteristic after bonded");
            }
            if (this.gattX.tryReadCharacteristic(this.z.getGattCharacteristic())) {
                return;
            }
            finishedWithError("Failed to read characteristic after bonded.");
        }
    }

    public void setTargetCharacteristic(BleCharacteristicX bleCharacteristicX) {
        this.z = bleCharacteristicX;
    }
}
