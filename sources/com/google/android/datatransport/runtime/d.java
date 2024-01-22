package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.CreationContextFactory_Factory;
import com.google.android.datatransport.runtime.backends.MetadataBackendRegistry_Factory;
import com.google.android.datatransport.runtime.dagger.internal.DoubleCheck;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.InstanceFactory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.k;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler_Factory;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule_ConfigFactory;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule_WorkSchedulerFactory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_DbNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_SchemaVersionFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_StoreConfigFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore_Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager_Factory;
import com.google.android.datatransport.runtime.time.TimeModule_EventClockFactory;
import com.google.android.datatransport.runtime.time.TimeModule_UptimeClockFactory;
import java.util.concurrent.Executor;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class d extends k {
    public Provider<Executor> h;
    public Provider<Context> i;
    public Provider j;
    public Provider k;
    public Provider l;
    public Provider<SQLiteEventStore> m;
    public Provider<SchedulerConfig> n;
    public Provider<WorkScheduler> o;
    public Provider<DefaultScheduler> p;
    public Provider<Uploader> q;
    public Provider<WorkInitializer> r;
    public Provider<TransportRuntime> s;

    /* loaded from: classes6.dex */
    public static final class b implements k.a {

        /* renamed from: a  reason: collision with root package name */
        public Context f8096a;

        public b() {
        }

        @Override // com.google.android.datatransport.runtime.k.a
        /* renamed from: b */
        public b a(Context context) {
            this.f8096a = (Context) Preconditions.checkNotNull(context);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.k.a
        public k build() {
            Preconditions.checkBuilderRequirement(this.f8096a, Context.class);
            return new d(this.f8096a);
        }
    }

    public static k.a c() {
        return new b();
    }

    @Override // com.google.android.datatransport.runtime.k
    public EventStore a() {
        return this.m.get();
    }

    @Override // com.google.android.datatransport.runtime.k
    public TransportRuntime b() {
        return this.s.get();
    }

    public final void d(Context context) {
        this.h = DoubleCheck.provider(ExecutionModule_ExecutorFactory.create());
        Factory create = InstanceFactory.create(context);
        this.i = create;
        CreationContextFactory_Factory create2 = CreationContextFactory_Factory.create(create, TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create());
        this.j = create2;
        this.k = DoubleCheck.provider(MetadataBackendRegistry_Factory.create(this.i, create2));
        this.l = SchemaManager_Factory.create(this.i, EventStoreModule_DbNameFactory.create(), EventStoreModule_SchemaVersionFactory.create());
        this.m = DoubleCheck.provider(SQLiteEventStore_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), EventStoreModule_StoreConfigFactory.create(), this.l));
        SchedulingConfigModule_ConfigFactory create3 = SchedulingConfigModule_ConfigFactory.create(TimeModule_EventClockFactory.create());
        this.n = create3;
        SchedulingModule_WorkSchedulerFactory create4 = SchedulingModule_WorkSchedulerFactory.create(this.i, this.m, create3, TimeModule_UptimeClockFactory.create());
        this.o = create4;
        Provider<Executor> provider = this.h;
        Provider provider2 = this.k;
        Provider<SQLiteEventStore> provider3 = this.m;
        this.p = DefaultScheduler_Factory.create(provider, provider2, create4, provider3, provider3);
        Provider<Context> provider4 = this.i;
        Provider provider5 = this.k;
        Provider<SQLiteEventStore> provider6 = this.m;
        this.q = Uploader_Factory.create(provider4, provider5, provider6, this.o, this.h, provider6, TimeModule_EventClockFactory.create());
        Provider<Executor> provider7 = this.h;
        Provider<SQLiteEventStore> provider8 = this.m;
        this.r = WorkInitializer_Factory.create(provider7, provider8, this.o, provider8);
        this.s = DoubleCheck.provider(TransportRuntime_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), this.p, this.q, this.r));
    }

    public d(Context context) {
        d(context);
    }
}
