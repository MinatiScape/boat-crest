package com.goodix.ble.libcomx.util;

import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskError;
/* loaded from: classes6.dex */
public class SimpleTask extends Task {
    public int A;
    public Work B;
    public Object z;

    /* loaded from: classes6.dex */
    public interface Work {
        void onWork(SimpleTask simpleTask, Object obj) throws Throwable;
    }

    public SimpleTask() {
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        TaskError taskError;
        Work work = this.B;
        if (work != null) {
            try {
                work.onWork(this, this.z);
                return 0;
            } catch (Throwable th) {
                if (th instanceof TaskError) {
                    taskError = th;
                } else {
                    taskError = new TaskError(this, th.getMessage(), th);
                }
                finished(-1, taskError);
                return 0;
            }
        }
        return 0;
    }

    public int getTaskId() {
        return this.A;
    }

    public SimpleTask setTag(Object obj) {
        this.z = obj;
        return this;
    }

    public SimpleTask setTaskId(int i) {
        this.A = i;
        return this;
    }

    public SimpleTask setWork(Work work) {
        this.B = work;
        return this;
    }

    public SimpleTask(Work work) {
        this.B = work;
    }

    public SimpleTask(String str, Object obj, Work work) {
        this.B = work;
        this.z = obj;
        setName(str);
        if (obj instanceof Integer) {
            this.A = ((Integer) obj).intValue();
        }
    }
}
