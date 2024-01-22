package com.google.android.gms.common.api.internal;

import java.util.concurrent.locks.Lock;
/* loaded from: classes6.dex */
public final class z0 implements Runnable {
    public final /* synthetic */ b h;

    public z0(b bVar) {
        this.h = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Lock lock;
        Lock lock2;
        lock = this.h.m;
        lock.lock();
        try {
            b.p(this.h);
        } finally {
            lock2 = this.h.m;
            lock2.unlock();
        }
    }
}
