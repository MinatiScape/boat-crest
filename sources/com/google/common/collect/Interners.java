package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class Interners {

    /* loaded from: classes10.dex */
    public static class InternerBuilder {

        /* renamed from: a  reason: collision with root package name */
        public final MapMaker f10560a;
        public boolean b;

        public <E> Interner<E> build() {
            if (!this.b) {
                this.f10560a.weakKeys();
            }
            return new c(this.f10560a);
        }

        public InternerBuilder concurrencyLevel(int i) {
            this.f10560a.concurrencyLevel(i);
            return this;
        }

        public InternerBuilder strong() {
            this.b = true;
            return this;
        }

        @GwtIncompatible("java.lang.ref.WeakReference")
        public InternerBuilder weak() {
            this.b = false;
            return this;
        }

        public InternerBuilder() {
            this.f10560a = new MapMaker();
            this.b = true;
        }
    }

    /* loaded from: classes10.dex */
    public static class b<E> implements Function<E, E> {
        public final Interner<E> h;

        public b(Interner<E> interner) {
            this.h = interner;
        }

        @Override // com.google.common.base.Function
        public E apply(E e) {
            return this.h.intern(e);
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof b) {
                return this.h.equals(((b) obj).h);
            }
            return false;
        }

        public int hashCode() {
            return this.h.hashCode();
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static final class c<E> implements Interner<E> {
        @VisibleForTesting

        /* renamed from: a  reason: collision with root package name */
        public final l1<E, MapMaker.a, ?, ?> f10561a;

        /* JADX WARN: Type inference failed for: r0v1, types: [com.google.common.collect.l1$i] */
        @Override // com.google.common.collect.Interner
        public E intern(E e) {
            E e2;
            do {
                ?? entry = this.f10561a.getEntry(e);
                if (entry != 0 && (e2 = (E) entry.getKey()) != null) {
                    return e2;
                }
            } while (this.f10561a.putIfAbsent(e, MapMaker.a.VALUE) != null);
            return e;
        }

        public c(MapMaker mapMaker) {
            this.f10561a = l1.createWithDummyValues(mapMaker.f(Equivalence.equals()));
        }
    }

    public static <E> Function<E, E> asFunction(Interner<E> interner) {
        return new b((Interner) Preconditions.checkNotNull(interner));
    }

    public static InternerBuilder newBuilder() {
        return new InternerBuilder();
    }

    public static <E> Interner<E> newStrongInterner() {
        return newBuilder().strong().build();
    }

    @GwtIncompatible("java.lang.ref.WeakReference")
    public static <E> Interner<E> newWeakInterner() {
        return newBuilder().weak().build();
    }
}
