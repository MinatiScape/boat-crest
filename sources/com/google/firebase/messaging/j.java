package com.google.firebase.messaging;

import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public final /* synthetic */ class j implements Executor {
    public static final Executor h = new j();

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        runnable.run();
    }
}