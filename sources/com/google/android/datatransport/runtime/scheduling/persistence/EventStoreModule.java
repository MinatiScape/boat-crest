package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.Module;
import com.google.android.datatransport.runtime.dagger.Provides;
import javax.inject.Named;
@Module
/* loaded from: classes6.dex */
public abstract class EventStoreModule {
    @Provides
    @Named("SQLITE_DB_NAME")
    public static String a() {
        return "com.google.android.datatransport.events";
    }

    @Provides
    @Named("SCHEMA_VERSION")
    public static int b() {
        return SchemaManager.j;
    }

    @Provides
    public static c c() {
        return c.f8139a;
    }
}
