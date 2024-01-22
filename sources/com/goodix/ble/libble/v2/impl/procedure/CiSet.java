package com.goodix.ble.libble.v2.impl.procedure;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.os.Build;
import androidx.annotation.Keep;
import com.goodix.ble.libble.v2.impl.BleGattX;
/* loaded from: classes5.dex */
public class CiSet extends BleBaseProcedure {
    public int A;
    public CB z;

    /* loaded from: classes5.dex */
    public class CB extends BluetoothGattCallback {
        public CB() {
        }

        @Keep
        public void onConnectionUpdated(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4) {
            if (i4 == 0) {
                CiSet.this.finishedWithDone();
            } else if (i4 == 59) {
                CiSet ciSet = CiSet.this;
                ciSet.finishedWithError("Connection parameters update failed with status: UNACCEPT CONN INTERVAL (0x3b) (interval: " + (i * 1.25d) + "ms, latency: " + i2 + ", timeout: " + (i3 * 10) + "ms)");
            } else {
                CiSet ciSet2 = CiSet.this;
                ciSet2.finishedWithError("Connection parameters update failed with status " + BleGattX.gattStatusToString(i4) + " (interval: " + (i * 1.25d) + "ms, latency: " + i2 + ", timeout: " + (i3 * 10) + "ms)");
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public int doWork2() {
        String str;
        if (this.A > 2) {
            str = "Invalid connection priority: " + this.A;
        } else if (this.gattX.isConnected()) {
            BluetoothGatt gatt = this.gattX.getGatt();
            if (gatt == null) {
                str = "Abort setting connection priority for null gatt.";
            } else if (Build.VERSION.SDK_INT < 21) {
                str = "Setting connection priority requires API level 21.";
            } else {
                CB cb = new CB();
                this.z = cb;
                this.gattX.register(cb);
                int i = this.A;
                if (i == 0) {
                    this.A = 0;
                } else if (i == 1) {
                    this.A = 1;
                } else if (i == 2) {
                    this.A = 2;
                }
                if (gatt.requestConnectionPriority(this.A)) {
                    return BleBaseProcedure.GATT_TIMEOUT;
                }
                str = "Failed to set connection priority.";
            }
        } else {
            str = "Failed to set CI. The connection is not established.";
        }
        finishedWithError(str);
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure, com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        BleGattX bleGattX;
        CB cb = this.z;
        if (cb != null && (bleGattX = this.gattX) != null) {
            bleGattX.remove(cb);
        }
        super.onCleanup();
    }

    public void setPriority(int i) {
        this.A = i;
    }
}
