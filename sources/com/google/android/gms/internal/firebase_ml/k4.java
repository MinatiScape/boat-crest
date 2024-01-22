package com.google.android.gms.internal.firebase_ml;

import android.os.Handler;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
/* loaded from: classes7.dex */
public enum k4 implements Executor {
    INSTANCE;

    @Override // java.util.concurrent.Executor
    public final void execute(@NonNull Runnable runnable) {
        Handler handler;
        handler = zzpx.zzof().h;
        handler.post(runnable);
    }
}
