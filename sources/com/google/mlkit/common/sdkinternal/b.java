package com.google.mlkit.common.sdkinternal;

import android.os.Handler;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public enum b implements Executor {
    INSTANCE;

    @Override // java.util.concurrent.Executor
    public final void execute(@NonNull Runnable runnable) {
        Handler handler;
        handler = MLTaskExecutor.getInstance().f11588a;
        handler.post(runnable);
    }
}
