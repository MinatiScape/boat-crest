package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jose4j.jwk.RsaJsonWebKey;
@ReflectionSupport(ReflectionSupport.Level.FULL)
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public abstract class d<OutputT> extends AbstractFuture.j<OutputT> {
    public static final b q;
    public static final Logger r = Logger.getLogger(d.class.getName());
    public volatile Set<Throwable> o = null;
    public volatile int p;

    /* loaded from: classes10.dex */
    public static abstract class b {
        public b() {
        }

        public abstract void a(d dVar, Set<Throwable> set, Set<Throwable> set2);

        public abstract int b(d dVar);
    }

    /* loaded from: classes10.dex */
    public static final class c extends b {

        /* renamed from: a  reason: collision with root package name */
        public final AtomicReferenceFieldUpdater<d, Set<Throwable>> f10807a;
        public final AtomicIntegerFieldUpdater<d> b;

        public c(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            super();
            this.f10807a = atomicReferenceFieldUpdater;
            this.b = atomicIntegerFieldUpdater;
        }

        @Override // com.google.common.util.concurrent.d.b
        public void a(d dVar, Set<Throwable> set, Set<Throwable> set2) {
            this.f10807a.compareAndSet(dVar, set, set2);
        }

        @Override // com.google.common.util.concurrent.d.b
        public int b(d dVar) {
            return this.b.decrementAndGet(dVar);
        }
    }

    /* renamed from: com.google.common.util.concurrent.d$d  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static final class C0530d extends b {
        public C0530d() {
            super();
        }

        @Override // com.google.common.util.concurrent.d.b
        public void a(d dVar, Set<Throwable> set, Set<Throwable> set2) {
            synchronized (dVar) {
                if (dVar.o == set) {
                    dVar.o = set2;
                }
            }
        }

        @Override // com.google.common.util.concurrent.d.b
        public int b(d dVar) {
            int A;
            synchronized (dVar) {
                A = d.A(dVar);
            }
            return A;
        }
    }

    static {
        b c0530d;
        Throwable th = null;
        try {
            c0530d = new c(AtomicReferenceFieldUpdater.newUpdater(d.class, Set.class, "o"), AtomicIntegerFieldUpdater.newUpdater(d.class, RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME));
        } catch (Throwable th2) {
            th = th2;
            c0530d = new C0530d();
        }
        q = c0530d;
        if (th != null) {
            r.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
    }

    public d(int i) {
        this.p = i;
    }

    public static /* synthetic */ int A(d dVar) {
        int i = dVar.p - 1;
        dVar.p = i;
        return i;
    }

    public abstract void B(Set<Throwable> set);

    public final void C() {
        this.o = null;
    }

    public final int D() {
        return q.b(this);
    }

    public final Set<Throwable> E() {
        Set<Throwable> set = this.o;
        if (set == null) {
            Set<Throwable> newConcurrentHashSet = Sets.newConcurrentHashSet();
            B(newConcurrentHashSet);
            q.a(this, null, newConcurrentHashSet);
            return this.o;
        }
        return set;
    }
}
