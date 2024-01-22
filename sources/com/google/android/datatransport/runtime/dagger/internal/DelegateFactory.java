package com.google.android.datatransport.runtime.dagger.internal;

import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DelegateFactory<T> implements Factory<T> {

    /* renamed from: a  reason: collision with root package name */
    public Provider<T> f8099a;

    public static <T> void setDelegate(Provider<T> provider, Provider<T> provider2) {
        Preconditions.checkNotNull(provider2);
        DelegateFactory delegateFactory = (DelegateFactory) provider;
        if (delegateFactory.f8099a == null) {
            delegateFactory.f8099a = provider2;
            return;
        }
        throw new IllegalStateException();
    }

    public Provider<T> a() {
        return (Provider) Preconditions.checkNotNull(this.f8099a);
    }

    @Override // javax.inject.Provider
    public T get() {
        Provider<T> provider = this.f8099a;
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
