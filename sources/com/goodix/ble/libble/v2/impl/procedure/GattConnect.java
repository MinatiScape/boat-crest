package com.goodix.ble.libble.v2.impl.procedure;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.os.SystemClock;
import com.goodix.ble.libble.v2.gb.procedure.GBProcedureConnect;
import com.goodix.ble.libble.v2.impl.BleGattX;
import com.goodix.ble.libcomx.ILogger;
/* loaded from: classes5.dex */
public class GattConnect extends BleBaseProcedure implements GBProcedureConnect {
    public a D;
    public long E;
    public int F;
    public int z = 0;
    public int A = 0;
    public int B = 0;
    public boolean C = false;

    /* loaded from: classes5.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            BleGattX bleGattX;
            if (i == 0) {
                if (i2 != 2) {
                    GattConnect.this.finishedWithError("Disconnect successfully?");
                    return;
                }
                GattConnect.this.E = 0L;
                GattConnect.this.finishedWithDone();
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime() - GattConnect.this.E;
            if (GattConnect.this.E > 0 && elapsedRealtime > 20000) {
                GattConnect.this.finishedWithError("GATT Timeout.");
            } else if (GattConnect.this.F >= GattConnect.this.A) {
                if (!GattConnect.this.C && (bleGattX = GattConnect.this.gattX) != null && i2 != 2) {
                    bleGattX.tryCloseGatt();
                }
                GattConnect gattConnect = GattConnect.this;
                gattConnect.finishedWithError("Failed to connect device after " + GattConnect.this.F + " retry(s). Last status: " + i);
            } else if (GattConnect.this.B <= 0) {
                GattConnect.this.onTimeout(1);
            } else {
                ILogger iLogger = GattConnect.this.logger;
                if (iLogger != null) {
                    String name = GattConnect.this.getName();
                    iLogger.d(name, "wait " + GattConnect.this.B + "ms to retry.");
                }
                GattConnect gattConnect2 = GattConnect.this;
                gattConnect2.startTimer(1, gattConnect2.B);
                BleGattX bleGattX2 = GattConnect.this.gattX;
                if (bleGattX2 != null) {
                    bleGattX2.tryCloseGatt();
                }
            }
        }
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure
    public int doWork2() {
        this.remoteDevice.expectConnection = true;
        if (this.gattX.isConnected()) {
            finishedWithDone();
            return 0;
        }
        a aVar = new a();
        this.D = aVar;
        this.gattX.register(aVar);
        this.F = 0;
        BleGattX bleGattX = this.gattX;
        int i = this.z;
        if (bleGattX.tryConnect(i != 0 ? i : 1, this.C)) {
            this.E = SystemClock.elapsedRealtime();
            return BleBaseProcedure.GATT_TIMEOUT;
        }
        finishedWithError("Failed to start connecting.");
        return 0;
    }

    @Override // com.goodix.ble.libble.v2.impl.procedure.BleBaseProcedure, com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        BleGattX bleGattX;
        BleGattX bleGattX2;
        if (getResult().getCode() == -2 && (bleGattX2 = this.gattX) != null) {
            bleGattX2.tryDisconnect();
            this.gattX.tryCloseGatt();
        }
        a aVar = this.D;
        if (aVar != null && (bleGattX = this.gattX) != null) {
            bleGattX.remove(aVar);
        }
        super.onCleanup();
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onTimeout(int i) {
        super.onTimeout(i);
        if (i == 1) {
            this.F++;
            ILogger iLogger = this.logger;
            if (iLogger != null) {
                iLogger.d(getName(), "Retry connecting... #" + this.F);
            }
            BleGattX gatt = this.remoteDevice.getGatt();
            int i2 = this.z;
            if (!gatt.tryConnect(i2 != 0 ? i2 : 1, this.C)) {
                finishedWithError("Failed to retry connecting.");
                return;
            }
            this.E = SystemClock.elapsedRealtime();
            refreshTaskTimeout();
        }
    }

    @Override // com.goodix.ble.libble.v2.gb.procedure.GBProcedureConnect
    public GBProcedureConnect setBackgroundMode(boolean z) {
        this.C = z;
        return this;
    }

    public void setPreferredPhy(int i) {
        this.z = i;
    }

    @Override // com.goodix.ble.libble.v2.gb.procedure.GBProcedureConnect
    public GBProcedureConnect setRetry(int i, int i2) {
        this.A = i;
        this.B = i2;
        return this;
    }
}
