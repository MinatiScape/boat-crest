package bleshadow.dagger.internal;

import bleshadow.dagger.Lazy;
import bleshadow.javax.inject.Provider;
/* loaded from: classes.dex */
public final class ProviderOfLazy<T> implements Provider<Lazy<T>> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<T> f1854a;

    public ProviderOfLazy(Provider<T> provider) {
        this.f1854a = provider;
    }

    public static <T> Provider<Lazy<T>> create(Provider<T> provider) {
        return new ProviderOfLazy((Provider) Preconditions.checkNotNull(provider));
    }

    @Override // bleshadow.javax.inject.Provider
    public Lazy<T> get() {
        return DoubleCheck.lazy(this.f1854a);
    }
}
