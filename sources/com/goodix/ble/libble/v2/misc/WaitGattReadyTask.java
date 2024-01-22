package com.goodix.ble.libble.v2.misc;

import com.goodix.ble.libble.v2.gb.GBRemoteDevice;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskParameter;
/* loaded from: classes5.dex */
public class WaitGattReadyTask extends Task implements IEventListener<Boolean> {
    public int A = 30000;
    @TaskParameter
    public GBRemoteDevice z;

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        this.z.evtReady().subEvent(this).setExecutor(getExecutor()).register2(this);
        if (this.z.isReady()) {
            finishedWithDone();
            return 0;
        }
        return this.A;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        this.z.evtReady().clear(this);
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, Boolean bool) {
        if (obj == this.z && i == 108) {
            if (bool.booleanValue()) {
                finishedWithDone();
            } else {
                finishedWithError("Failed to setup GATT.");
            }
        }
    }

    public WaitGattReadyTask setGatt(GBRemoteDevice gBRemoteDevice) {
        this.z = gBRemoteDevice;
        return this;
    }

    public WaitGattReadyTask setTimeout(int i) {
        this.A = i;
        return this;
    }
}
