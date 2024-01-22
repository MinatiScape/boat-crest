package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CreationContextFactory_Factory implements Factory<d> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f8085a;
    public final Provider<Clock> b;
    public final Provider<Clock> c;

    public CreationContextFactory_Factory(Provider<Context> provider, Provider<Clock> provider2, Provider<Clock> provider3) {
        this.f8085a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static CreationContextFactory_Factory create(Provider<Context> provider, Provider<Clock> provider2, Provider<Clock> provider3) {
        return new CreationContextFactory_Factory(provider, provider2, provider3);
    }

    public static d newInstance(Context context, Clock clock, Clock clock2) {
        return new d(context, clock, clock2);
    }

    @Override // javax.inject.Provider
    public d get() {
        return newInstance(this.f8085a.get(), this.b.get(), this.c.get());
    }
}
