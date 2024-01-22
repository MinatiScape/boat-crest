package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class DefaultScheduler implements Scheduler {
    public static final Logger f = Logger.getLogger(TransportRuntime.class.getName());

    /* renamed from: a  reason: collision with root package name */
    public final WorkScheduler f8111a;
    public final Executor b;
    public final BackendRegistry c;
    public final EventStore d;
    public final SynchronizationGuard e;

    @Inject
    public DefaultScheduler(Executor executor, BackendRegistry backendRegistry, WorkScheduler workScheduler, EventStore eventStore, SynchronizationGuard synchronizationGuard) {
        this.b = executor;
        this.c = backendRegistry;
        this.f8111a = workScheduler;
        this.d = eventStore;
        this.e = synchronizationGuard;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object c(TransportContext transportContext, EventInternal eventInternal) {
        this.d.persist(transportContext, eventInternal);
        this.f8111a.schedule(transportContext, 1);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(final TransportContext transportContext, TransportScheduleCallback transportScheduleCallback, EventInternal eventInternal) {
        try {
            TransportBackend transportBackend = this.c.get(transportContext.getBackendName());
            if (transportBackend == null) {
                String format = String.format("Transport backend '%s' is not registered", transportContext.getBackendName());
                f.warning(format);
                transportScheduleCallback.onSchedule(new IllegalArgumentException(format));
                return;
            }
            final EventInternal decorate = transportBackend.decorate(eventInternal);
            this.e.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.a
                @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                public final Object execute() {
                    Object c;
                    c = DefaultScheduler.this.c(transportContext, decorate);
                    return c;
                }
            });
            transportScheduleCallback.onSchedule(null);
        } catch (Exception e) {
            Logger logger = f;
            logger.warning("Error scheduling event " + e.getMessage());
            transportScheduleCallback.onSchedule(e);
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.Scheduler
    public void schedule(final TransportContext transportContext, final EventInternal eventInternal, final TransportScheduleCallback transportScheduleCallback) {
        this.b.execute(new Runnable() { // from class: com.google.android.datatransport.runtime.scheduling.b
            @Override // java.lang.Runnable
            public final void run() {
                DefaultScheduler.this.d(transportContext, transportScheduleCallback, eventInternal);
            }
        });
    }
}
