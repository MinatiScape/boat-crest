package com.google.android.datatransport.runtime.dagger.internal;

import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class SingleCheck<T> implements Provider<T> {
    public static final Object c = new Object();

    /* renamed from: a  reason: collision with root package name */
    public volatile Provider<T> f8107a;
    public volatile Object b = c;

    public SingleCheck(Provider<T> provider) {
        this.f8107a = provider;
    }

    public static <P extends Provider<T>, T> Provider<T> provider(P p) {
        return ((p instanceof SingleCheck) || (p instanceof DoubleCheck)) ? p : new SingleCheck((Provider) Preconditions.checkNotNull(p));
    }

    @Override // javax.inject.Provider
    public T get() {
        T t = (T) this.b;
        if (t == c) {
            Provider<T> provider = this.f8107a;
            if (provider == null) {
                return (T) this.b;
            }
            T t2 = provider.get();
            this.b = t2;
            this.f8107a = null;
            return t2;
        }
        return t;
    }
}
