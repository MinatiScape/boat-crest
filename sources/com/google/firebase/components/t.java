package com.google.firebase.components;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
/* loaded from: classes10.dex */
public class t<T> implements Provider<T>, Deferred<T> {
    public static final Deferred.DeferredHandler<Object> c = new Deferred.DeferredHandler() { // from class: com.google.firebase.components.q
        @Override // com.google.firebase.inject.Deferred.DeferredHandler
        public final void handle(Provider provider) {
            t.e(provider);
        }
    };
    public static final Provider<Object> d = new Provider() { // from class: com.google.firebase.components.s
        @Override // com.google.firebase.inject.Provider
        public final Object get() {
            Object f;
            f = t.f();
            return f;
        }
    };
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public Deferred.DeferredHandler<T> f11112a;
    public volatile Provider<T> b;

    public t(Deferred.DeferredHandler<T> deferredHandler, Provider<T> provider) {
        this.f11112a = deferredHandler;
        this.b = provider;
    }

    public static <T> t<T> d() {
        return new t<>(c, d);
    }

    public static /* synthetic */ void e(Provider provider) {
    }

    public static /* synthetic */ Object f() {
        return null;
    }

    public static /* synthetic */ void g(Deferred.DeferredHandler deferredHandler, Deferred.DeferredHandler deferredHandler2, Provider provider) {
        deferredHandler.handle(provider);
        deferredHandler2.handle(provider);
    }

    public static <T> t<T> h(Provider<T> provider) {
        return new t<>(null, provider);
    }

    @Override // com.google.firebase.inject.Provider
    public T get() {
        return this.b.get();
    }

    public void i(Provider<T> provider) {
        Deferred.DeferredHandler<T> deferredHandler;
        if (this.b == d) {
            synchronized (this) {
                deferredHandler = this.f11112a;
                this.f11112a = null;
                this.b = provider;
            }
            deferredHandler.handle(provider);
            return;
        }
        throw new IllegalStateException("provide() can be called only once.");
    }

    @Override // com.google.firebase.inject.Deferred
    public void whenAvailable(@NonNull final Deferred.DeferredHandler<T> deferredHandler) {
        Provider<T> provider;
        Provider<T> provider2 = this.b;
        Provider<Object> provider3 = d;
        if (provider2 != provider3) {
            deferredHandler.handle(provider2);
            return;
        }
        Provider<T> provider4 = null;
        synchronized (this) {
            provider = this.b;
            if (provider != provider3) {
                provider4 = provider;
            } else {
                final Deferred.DeferredHandler<T> deferredHandler2 = this.f11112a;
                this.f11112a = new Deferred.DeferredHandler() { // from class: com.google.firebase.components.p
                    @Override // com.google.firebase.inject.Deferred.DeferredHandler
                    public final void handle(Provider provider5) {
                        t.g(Deferred.DeferredHandler.this, deferredHandler, provider5);
                    }
                };
            }
        }
        if (provider4 != null) {
            deferredHandler.handle(provider);
        }
    }
}
