package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class MetadataBackendRegistry_Factory implements Factory<e> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f8086a;
    public final Provider<d> b;

    public MetadataBackendRegistry_Factory(Provider<Context> provider, Provider<d> provider2) {
        this.f8086a = provider;
        this.b = provider2;
    }

    public static MetadataBackendRegistry_Factory create(Provider<Context> provider, Provider<d> provider2) {
        return new MetadataBackendRegistry_Factory(provider, provider2);
    }

    public static e newInstance(Context context, Object obj) {
        return new e(context, (d) obj);
    }

    @Override // javax.inject.Provider
    public e get() {
        return newInstance(this.f8086a.get(), this.b.get());
    }
}
