package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Supplier;
import java.util.concurrent.atomic.AtomicLong;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public static final Supplier<com.google.common.cache.b> f10545a;

    /* loaded from: classes10.dex */
    public class a implements Supplier<com.google.common.cache.b> {
        @Override // com.google.common.base.Supplier
        /* renamed from: a */
        public com.google.common.cache.b get() {
            return new d();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Supplier<com.google.common.cache.b> {
        @Override // com.google.common.base.Supplier
        /* renamed from: a */
        public com.google.common.cache.b get() {
            return new C0467c(null);
        }
    }

    /* renamed from: com.google.common.cache.c$c  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static final class C0467c extends AtomicLong implements com.google.common.cache.b {
        private C0467c() {
        }

        @Override // com.google.common.cache.b
        public void add(long j) {
            getAndAdd(j);
        }

        @Override // com.google.common.cache.b
        public void increment() {
            getAndIncrement();
        }

        @Override // com.google.common.cache.b
        public long sum() {
            return get();
        }

        public /* synthetic */ C0467c(a aVar) {
            this();
        }
    }

    static {
        Supplier<com.google.common.cache.b> bVar;
        try {
            new d();
            bVar = new a();
        } catch (Throwable unused) {
            bVar = new b();
        }
        f10545a = bVar;
    }

    public static com.google.common.cache.b a() {
        return f10545a.get();
    }
}
