package com.clevertap.android.sdk.task;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class MainThreadExecutor implements Executor {
    public Handler h = new Handler(Looper.getMainLooper());

    @Override // java.util.concurrent.Executor
    public void execute(@NonNull Runnable runnable) {
        this.h.post(runnable);
    }
}
