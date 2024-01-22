package com.goodix.ble.libcomx.ptmodel;

import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.task.ITask;
import com.goodix.ble.libcomx.task.ITaskResult;
import com.goodix.ble.libcomx.task.TaskQueue;
/* loaded from: classes5.dex */
public final class PtStep extends TaskQueue {
    public static final int EVT_JUDGE_UPDATED = 728;
    public PtJudge I;
    public Event<Void> J = new Event<>(this, EVT_JUDGE_UPDATED);

    public PtStep(PtJudge ptJudge) {
        this.I = ptJudge;
        setAbortOnException(true);
    }

    public <T extends ITask> T addAction(T t) {
        addTask(t);
        return t;
    }

    public Event<Void> evtJudgeUpdated() {
        return this.J;
    }

    public PtJudge getJudge() {
        return this.I;
    }

    @Override // com.goodix.ble.libcomx.task.TaskQueue, com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        PtJudge ptJudge;
        super.onCleanup();
        ITaskResult result = getResult();
        if (result.getError() == null || (ptJudge = this.I) == null) {
            return;
        }
        ptJudge.exception = result.getError().getMessage();
        evtJudgeUpdated().postEvent(null);
    }
}
