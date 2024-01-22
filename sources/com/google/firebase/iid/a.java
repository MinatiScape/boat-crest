package com.google.firebase.iid;

import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public final /* synthetic */ class a implements Executor {
    public static final Executor h = new a();

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
