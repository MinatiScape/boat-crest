package com.google.android.gms.common.util.concurrent;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.common.zzi;
import java.util.concurrent.Executor;
@KeepForSdk
/* loaded from: classes6.dex */
public class HandlerExecutor implements Executor {
    public final Handler h;

    @KeepForSdk
    public HandlerExecutor(@NonNull Looper looper) {
        this.h = new zzi(looper);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(@NonNull Runnable runnable) {
        this.h.post(runnable);
    }
}
