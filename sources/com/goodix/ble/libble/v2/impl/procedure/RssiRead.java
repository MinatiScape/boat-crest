package com.goodix.ble.libble.v2.impl.procedure;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import com.goodix.ble.libble.v2.gb.procedure.GBProcedureRssiRead;
import com.goodix.ble.libble.v2.impl.BleGattX;
/* loaded from: classes5.dex */
public class RssiRead extends BleBaseProcedure implements GBProcedureRssiRead {
    public int A;
    public a z;

    /* loaded from: classes5.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
            RssiRead rssiRead = RssiRead.this;
            if (i2 == 0) {
                rssiRead.A = i;
                RssiRead.this.finishedWithDone();
                return;
            }
            rssiRead.finishedWithError("Failed to read RSSI: " + BleGattX.gattStatusToString(i2));
        }
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public int doWork2() {
        String str;
        if (this.gattX.isConnected()) {
            BluetoothGatt gatt = this.gattX.getGatt();
            if (gatt == null) {
                str = "Abort reading RSSI for null gatt.";
            } else {
                a aVar = new a();
                this.z = aVar;
                this.gattX.register(aVar);
                if (gatt.readRemoteRssi()) {
                    return BleBaseProcedure.COMMUNICATION_TIMEOUT;
                }
                str = "Failed to read RSSI.";
            }
        } else {
            str = "Failed to read RSSI. The connection is not established.";
        }
        finishedWithError(str);
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.gb.procedure.GBProcedureRssiRead
    public int getRssi() {
        return this.A;
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure, com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        BleGattX bleGattX;
        a aVar = this.z;
        if (aVar != null && (bleGattX = this.gattX) != null) {
            bleGattX.remove(aVar);
        }
        super.onCleanup();
    }
}
