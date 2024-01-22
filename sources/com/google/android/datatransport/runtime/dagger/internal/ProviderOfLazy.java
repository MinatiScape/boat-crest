package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ProviderOfLazy<T> implements Provider<Lazy<T>> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<T> f8103a;

    public ProviderOfLazy(Provider<T> provider) {
        this.f8103a = provider;
    }

    public static <T> Provider<Lazy<T>> create(Provider<T> provider) {
        return new ProviderOfLazy((Provider) Preconditions.checkNotNull(provider));
    }

    @Override // javax.inject.Provider
    public Lazy<T> get() {
        return DoubleCheck.lazy(this.f8103a);
    }
}
