package com.google.android.gms.cloudmessaging;

import java.util.concurrent.Executor;
/* loaded from: classes6.dex */
public final /* synthetic */ class v implements Executor {
    public static final Executor h = new v();

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
