package bleshadow.dagger.internal;

import bleshadow.javax.inject.Provider;
/* loaded from: classes.dex */
public final class SingleCheck<T> implements Provider<T> {
    public static final Object c = new Object();

    /* renamed from: a  reason: collision with root package name */
    public volatile Provider<T> f1858a;
    public volatile Object b = c;

    public SingleCheck(Provider<T> provider) {
        this.f1858a = provider;
    }

    public static <P extends Provider<T>, T> Provider<T> provider(P p) {
        return ((p instanceof SingleCheck) || (p instanceof DoubleCheck)) ? p : new SingleCheck((Provider) Preconditions.checkNotNull(p));
    }

    @Override // bleshadow.javax.inject.Provider
    public T get() {
        T t = (T) this.b;
        if (t == c) {
            Provider<T> provider = this.f1858a;
            if (provider == null) {
                return (T) this.b;
            }
            T t2 = provider.get();
            this.b = t2;
            this.f1858a = null;
            return t2;
        }
        return t;
    }
}