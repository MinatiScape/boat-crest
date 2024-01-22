package bleshadow.dagger.internal;

import bleshadow.javax.inject.Provider;
/* loaded from: classes.dex */
public final class DelegateFactory<T> implements Factory<T> {

    /* renamed from: a  reason: collision with root package name */
    public Provider<T> f1850a;

    public static <T> void setDelegate(Provider<T> provider, Provider<T> provider2) {
        Preconditions.checkNotNull(provider2);
        DelegateFactory delegateFactory = (DelegateFactory) provider;
        if (delegateFactory.f1850a == null) {
            delegateFactory.f1850a = provider2;
            return;
        }
        throw new IllegalStateException();
    }

    public Provider<T> a() {
        return (Provider) Preconditions.checkNotNull(this.f1850a);
    }

    @Override // bleshadow.javax.inject.Provider
    public T get() {
        Provider<T> provider = this.f1850a;
        if (provider != null) {
            return provider.get();
        }
        throw new IllegalStateException();
    }

    @Deprecated
    public void setDelegatedProvider(Provider<T> provider) {
        setDelegate(this, provider);
    }
}
