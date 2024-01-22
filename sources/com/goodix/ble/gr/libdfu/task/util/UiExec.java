package com.goodix.ble.gr.libdfu.task.util;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
/* loaded from: classes5.dex */
public class UiExec implements Executor {
    public Handler h;

    public UiExec() {
        this(null);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.h.post(runnable);
    }

    public Handler getHandler() {
        return this.h;
    }

    public UiExec(Handler handler) {
        this.h = handler == null ? new Handler(Looper.getMainLooper()) : handler;
    }
}
