package com.google.android.gms.common.api.internal;

import java.util.concurrent.locks.Lock;
/* loaded from: classes6.dex */
public abstract class x {

    /* renamed from: a  reason: collision with root package name */
    public final zabf f8292a;

    public x(zabf zabfVar) {
        this.f8292a = zabfVar;
    }

    public abstract void a();

    public final void b(zabi zabiVar) {
        Lock lock;
        Lock lock2;
        zabf zabfVar;
        Lock lock3;
        lock = zabiVar.f8301a;
        lock.lock();
        try {
            zabfVar = zabiVar.k;
            if (zabfVar != this.f8292a) {
                lock3 = zabiVar.f8301a;
            } else {
                a();
                lock3 = zabiVar.f8301a;
            }
            lock3.unlock();
        } catch (Throwable th) {
            lock2 = zabiVar.f8301a;
            lock2.unlock();
            throw th;
        }
    }
}
