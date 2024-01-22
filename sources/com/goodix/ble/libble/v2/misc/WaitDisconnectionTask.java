package com.goodix.ble.libble.v2.misc;

import com.goodix.ble.libble.v2.gb.GBRemoteDevice;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskParameter;
/* loaded from: classes5.dex */
public class WaitDisconnectionTask extends Task implements IEventListener<Integer> {
    public int A = 30000;
    @TaskParameter
    public GBRemoteDevice z;

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        GBRemoteDevice gBRemoteDevice = this.z;
        if (gBRemoteDevice == null) {
            finishedWithError("Device is null.");
            return 0;
        }
        gBRemoteDevice.evtStateChanged().subEvent(this).setExecutor(getExecutor()).register2(this);
        if (this.z.isDisconnected()) {
            finishedWithDone();
        }
        return this.A;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        super.onCleanup();
        GBRemoteDevice gBRemoteDevice = this.z;
        if (gBRemoteDevice != null) {
            gBRemoteDevice.evtStateChanged().clear(this);
        }
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, Integer num) {
        if (num.intValue() == 0) {
            finishedWithDone();
        }
    }

    public WaitDisconnectionTask setDevice(GBRemoteDevice gBRemoteDevice) {
        this.z = gBRemoteDevice;
        return this;
    }

    public WaitDisconnectionTask setTimeout(int i) {
        this.A = i;
        return this;
    }
}
