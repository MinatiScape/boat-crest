package com.goodix.ble.libble.v2.impl.procedure;

import com.goodix.ble.libble.v2.gb.procedure.GBProcedure;
import com.goodix.ble.libble.v2.impl.BleGattX;
import com.goodix.ble.libble.v2.impl.BleRemoteDevice;
import com.goodix.ble.libcomx.logger.Logger;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.util.AccessLock;
/* loaded from: classes5.dex */
public abstract class BleBaseProcedure extends Task implements GBProcedure, AccessLock.CB {
    public static final int COMMUNICATION_TIMEOUT = 12000;
    public static final int GATT_TIMEOUT = 31000;
    public BleGattX gattX;
    public BleRemoteDevice remoteDevice;
    public int timeout = GATT_TIMEOUT;

    public boolean acquireLock() {
        if (this.remoteDevice.getLocker().acquireLock(this) || !this.printVerboseLog) {
            return true;
        }
        Logger.v(this.logger, getName(), "Wait lock.");
        return true;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public final int doWork() {
        BleRemoteDevice bleRemoteDevice = this.remoteDevice;
        if (bleRemoteDevice == null) {
            finishedWithError("Target remote device is null.");
            return 0;
        }
        this.gattX = bleRemoteDevice.getGatt();
        if (!acquireLock()) {
            doWork2();
        }
        return this.timeout;
    }

    public abstract int doWork2();

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        if (this.printVerboseLog) {
            Logger.v(this.logger, getName(), "Release lock.");
        }
        this.remoteDevice.getLocker().releaseLock(this);
    }

    @Override // com.goodix.ble.libcomx.util.AccessLock.CB
    public void onLockAcquired(AccessLock accessLock) {
        if (this.printVerboseLog) {
            Logger.v(this.logger, getName(), "Acquired lock.");
        }
        refreshTaskTimeout();
        getExecutor().execute(new Runnable() { // from class: com.goodix.ble.libble.v2.impl.procedure.a
            @Override // java.lang.Runnable
            public final void run() {
                BleBaseProcedure.this.doWork2();
            }
        });
    }

    public void setRemoteDevice(BleRemoteDevice bleRemoteDevice) {
        this.remoteDevice = bleRemoteDevice;
    }

    @Override // com.goodix.ble.libble.v2.gb.procedure.GBProcedure
    public GBProcedure setTimeout(int i) {
        this.timeout = i;
        return this;
    }

    @Override // com.goodix.ble.libble.v2.gb.procedure.GBProcedure
    public void startProcedure() {
        start(null, null);
    }
}
