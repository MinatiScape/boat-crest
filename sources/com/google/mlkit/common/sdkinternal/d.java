package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.Closeable;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class d implements Closeable {
    public final /* synthetic */ TaskQueue h;

    public /* synthetic */ d(TaskQueue taskQueue, zzw zzwVar) {
        AtomicReference atomicReference;
        this.h = taskQueue;
        atomicReference = taskQueue.d;
        Preconditions.checkState(((Thread) atomicReference.getAndSet(Thread.currentThread())) == null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        AtomicReference atomicReference;
        atomicReference = this.h.d;
        atomicReference.set(null);
        this.h.c();
    }
}
