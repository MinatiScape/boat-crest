package com.goodix.ble.libble.v2.impl.procedure;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.os.Build;
import com.goodix.ble.libble.v2.impl.BleGattX;
/* loaded from: classes5.dex */
public class MtuExchange extends BleBaseProcedure {
    public int A;
    public a z;

    /* loaded from: classes5.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            MtuExchange mtuExchange = MtuExchange.this;
            if (i2 == 0) {
                if (mtuExchange.A == i) {
                    MtuExchange.this.finishedWithDone();
                    return;
                }
                MtuExchange mtuExchange2 = MtuExchange.this;
                mtuExchange2.finishedWithError("Failed to change MTU to " + MtuExchange.this.A + " , now it's " + i);
            } else if (mtuExchange.gattX != null && mtuExchange.A == i) {
                MtuExchange.this.finishedWithDone();
            } else {
                MtuExchange mtuExchange3 = MtuExchange.this;
                mtuExchange3.finishedWithError("Failed to request MTU " + MtuExchange.this.A + " : " + BleGattX.gattStatusToString(i2));
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public int doWork2() {
        String str;
        if (this.A < 23) {
            str = "MTU is less than 23: " + this.A;
        } else if (this.gattX.isConnected()) {
            BluetoothGatt gatt = this.gattX.getGatt();
            if (gatt == null) {
                str = "Abort requesting MTU for null gatt.";
            } else if (Build.VERSION.SDK_INT < 21) {
                str = "Requesting MTU requires API level 21.";
            } else {
                a aVar = new a();
                this.z = aVar;
                this.gattX.register(aVar);
                if (gatt.requestMtu(this.A)) {
                    return BleBaseProcedure.GATT_TIMEOUT;
                }
                str = "Failed to request MTU.";
            }
        } else {
            str = "Failed to exchange MTU. The connection is not established.";
        }
        finishedWithError(str);
        return 0;
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

    public void setMtu(int i) {
        this.A = i;
    }
}
