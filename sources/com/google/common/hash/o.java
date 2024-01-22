package com.google.common.hash;

import com.google.common.base.Supplier;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes10.dex */
public final class o {

    /* renamed from: a  reason: collision with root package name */
    public static final Supplier<n> f10657a;

    /* loaded from: classes10.dex */
    public class a implements Supplier<n> {
        @Override // com.google.common.base.Supplier
        /* renamed from: a */
        public n get() {
            return new p();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Supplier<n> {
        @Override // com.google.common.base.Supplier
        /* renamed from: a */
        public n get() {
            return new c(null);
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends AtomicLong implements n {
        private c() {
        }

        @Override // com.google.common.hash.n
        public void add(long j) {
            getAndAdd(j);
        }

        @Override // com.google.common.hash.n
        public void increment() {
            getAndIncrement();
        }

        @Override // com.google.common.hash.n
        public long sum() {
            return get();
        }

        public /* synthetic */ c(a aVar) {
            this();
        }
    }

    static {
        Supplier<n> bVar;
        try {
            new p();
            bVar = new a();
        } catch (Throwable unused) {
            bVar = new b();
        }
        f10657a = bVar;
    }

    public static n a() {
        return f10657a.get();
    }
}
