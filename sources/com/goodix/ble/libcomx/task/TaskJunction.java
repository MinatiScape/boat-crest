package com.goodix.ble.libcomx.task;

import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.event.IEventListener;
/* loaded from: classes5.dex */
public class TaskJunction implements IEventListener {
    public Event h;
    public ITask i;
    public boolean j = false;
    public boolean k = false;
    public int l = 0;
    public boolean m;

    public static TaskJunction link(Event event, ITask iTask) {
        TaskJunction taskJunction = new TaskJunction();
        taskJunction.h = event;
        event.register(taskJunction);
        taskJunction.i = iTask;
        return taskJunction;
    }

    public void destroy() {
        this.h.remove(this);
        this.h = null;
        this.i = null;
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    @Deprecated
    public void onEvent(Object obj, int i, Object obj2) {
        ITaskContext iTaskContext;
        ITask iTask = null;
        if (obj instanceof ITask) {
            iTask = (ITask) obj;
            iTaskContext = null;
        } else {
            iTaskContext = obj instanceof ITaskContext ? (ITaskContext) obj : null;
        }
        boolean z = true;
        if (i == 342 && (obj2 instanceof ITaskResult)) {
            ITaskResult iTaskResult = (ITaskResult) obj2;
            boolean z2 = (this.j && iTaskResult.getError() == null) ? false : true;
            if (!this.k) {
                z = z2;
            } else if (iTaskResult.getCode() != this.l) {
                z = false;
            }
        }
        if (z) {
            if (this.m) {
                destroy();
            }
            this.i.start(iTaskContext, iTask);
        }
    }

    public TaskJunction setOneshot(boolean z) {
        this.m = z;
        return this;
    }

    public TaskJunction setStartIfCode(int i) {
        this.k = true;
        this.l = i;
        return this;
    }

    public TaskJunction setStartIfError() {
        this.j = true;
        return this;
    }

    public static TaskJunction link(ITask iTask, ITask iTask2) {
        TaskJunction taskJunction = new TaskJunction();
        Event<ITaskResult> evtFinished = iTask.evtFinished();
        taskJunction.h = evtFinished;
        evtFinished.register(taskJunction);
        taskJunction.i = iTask2;
        return taskJunction;
    }
}
