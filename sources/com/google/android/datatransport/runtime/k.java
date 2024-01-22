package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.BackendRegistryModule;
import com.google.android.datatransport.runtime.dagger.BindsInstance;
import com.google.android.datatransport.runtime.dagger.Component;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule;
import com.google.android.datatransport.runtime.time.TimeModule;
import java.io.Closeable;
import java.io.IOException;
import javax.inject.Singleton;
@Singleton
@Component(modules = {BackendRegistryModule.class, EventStoreModule.class, e.class, SchedulingModule.class, SchedulingConfigModule.class, TimeModule.class})
/* loaded from: classes6.dex */
public abstract class k implements Closeable {

    @Component.Builder
    /* loaded from: classes6.dex */
    public interface a {
        @BindsInstance
        a a(Context context);

        k build();
    }

    public abstract EventStore a();

    public abstract TransportRuntime b();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        a().close();
    }
}
