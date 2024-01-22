package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
public enum r1 implements Executor {
    INSTANCE;

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "MoreExecutors.directExecutor()";
    }
}
