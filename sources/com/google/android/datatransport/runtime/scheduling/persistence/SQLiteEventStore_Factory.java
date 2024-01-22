package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class SQLiteEventStore_Factory implements Factory<SQLiteEventStore> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Clock> f8135a;
    public final Provider<Clock> b;
    public final Provider<c> c;
    public final Provider<SchemaManager> d;

    public SQLiteEventStore_Factory(Provider<Clock> provider, Provider<Clock> provider2, Provider<c> provider3, Provider<SchemaManager> provider4) {
        this.f8135a = provider;
        this.b = provider2;
        this.c = provider3;
        this.d = provider4;
    }

    public static SQLiteEventStore_Factory create(Provider<Clock> provider, Provider<Clock> provider2, Provider<c> provider3, Provider<SchemaManager> provider4) {
        return new SQLiteEventStore_Factory(provider, provider2, provider3, provider4);
    }

    public static SQLiteEventStore newInstance(Clock clock, Clock clock2, Object obj, Object obj2) {
        return new SQLiteEventStore(clock, clock2, (c) obj, (SchemaManager) obj2);
    }

    @Override // javax.inject.Provider
    public SQLiteEventStore get() {
        return newInstance(this.f8135a.get(), this.b.get(), this.c.get(), this.d.get());
    }
}
