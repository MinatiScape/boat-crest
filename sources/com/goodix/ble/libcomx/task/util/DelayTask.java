package com.goodix.ble.libcomx.task.util;

import com.goodix.ble.libcomx.task.Task;
/* loaded from: classes5.dex */
public class DelayTask extends Task {
    public float A;
    public float B = 0.0f;
    public int z;

    public DelayTask(int i) {
        this.z = i;
    }

    @Override // com.goodix.ble.libcomx.task.Task, com.goodix.ble.libcomx.task.ITask
    public void abort() {
        stopTimer();
        super.abort();
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        if (this.z < 1) {
            this.z = 1;
        }
        if (this.z > 864000000) {
            finishedWithError("Delay is too long: " + this.z);
        }
        startTimer(1, this.z);
        this.A = 0.0f;
        int i = this.z;
        float f = i / 100.0f;
        this.B = f;
        if (f < 1000.0f) {
            this.B = 1000.0f;
        }
        float f2 = this.B;
        if (i > f2) {
            startTimer(2, f2, f2);
        }
        return this.z + 1000;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onTimeout(int i) {
        if (i == 1) {
            finishedWithDone();
        } else if (i == 2) {
            float f = this.A + this.B;
            this.A = f;
            publishProgress((int) ((f * 100.0f) / this.z));
        }
    }

    public DelayTask setDelay(int i) {
        this.z = i;
        return this;
    }
}
