package com.goodix.ble.libble.v2.impl.procedure;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import com.goodix.ble.libble.v2.gb.procedure.GBProcedureConnect;
import com.goodix.ble.libble.v2.impl.BleGattX;
/* loaded from: classes5.dex */
public class GattDisconnect extends BleBaseProcedure {
    public boolean A = false;
    public a z;

    /* loaded from: classes5.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 == 0) {
                GattDisconnect.this.d();
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public boolean acquireLock() {
        Object owner = this.remoteDevice.getLocker().getOwner();
        if (owner instanceof GBProcedureConnect) {
            ((GBProcedureConnect) owner).abort();
        }
        return super.acquireLock();
    }

    public final void d() {
        if (this.A) {
            this.gattX.tryRefreshDeviceCache();
        }
        this.gattX.tryCloseGatt();
        startTimer(1, 300L);
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public int doWork2() {
        this.remoteDevice.expectConnection = false;
        if (this.gattX.getConnectionState() == 0) {
            d();
            finishedWithDone();
            return 0;
        }
        a aVar = new a();
        this.z = aVar;
        this.gattX.register(aVar);
        boolean isConnected = this.gattX.isConnected();
        if (!this.gattX.tryDisconnect()) {
            finishedWithError("Failed to disconnect.");
            return 0;
        } else if (isConnected) {
            return BleBaseProcedure.GATT_TIMEOUT;
        } else {
            d();
            return BleBaseProcedure.GATT_TIMEOUT;
        }
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

    @Override // com.goodix.ble.libcomx.task.Task
    public void onTimeout(int i) {
        super.onTimeout(i);
        if (i == 1) {
            finishedWithDone();
        }
    }

    public void setClearCache(boolean z) {
        this.A = z;
    }
}
