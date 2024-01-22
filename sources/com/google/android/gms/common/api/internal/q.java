package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import java.util.concurrent.locks.Lock;
/* loaded from: classes6.dex */
public abstract class q implements Runnable {
    public final /* synthetic */ zaaw h;

    @WorkerThread
    public abstract void a();

    @Override // java.lang.Runnable
    @WorkerThread
    public final void run() {
        Lock lock;
        Lock lock2;
        zabi zabiVar;
        Lock lock3;
        lock = this.h.b;
        lock.lock();
        try {
            try {
                if (Thread.interrupted()) {
                    lock3 = this.h.b;
                } else {
                    a();
                    lock3 = this.h.b;
                }
            } catch (RuntimeException e) {
                zabiVar = this.h.f8298a;
                zabiVar.g(e);
                lock3 = this.h.b;
            }
            lock3.unlock();
        } catch (Throwable th) {
            lock2 = this.h.b;
            lock2.unlock();
            throw th;
        }
    }
}
