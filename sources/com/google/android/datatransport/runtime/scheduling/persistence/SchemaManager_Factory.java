package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class SchemaManager_Factory implements Factory<SchemaManager> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f8136a;
    public final Provider<String> b;
    public final Provider<Integer> c;

    public SchemaManager_Factory(Provider<Context> provider, Provider<String> provider2, Provider<Integer> provider3) {
        this.f8136a = provider;
        this.b = provider2;
        this.c = provider3;
    }

    public static SchemaManager_Factory create(Provider<Context> provider, Provider<String> provider2, Provider<Integer> provider3) {
        return new SchemaManager_Factory(provider, provider2, provider3);
    }

    public static SchemaManager newInstance(Context context, String str, int i) {
        return new SchemaManager(context, str, i);
    }

    @Override // javax.inject.Provider
    public SchemaManager get() {
        return newInstance(this.f8136a.get(), this.b.get(), this.c.get().intValue());
    }
}
