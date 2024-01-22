package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class WorkInitializer {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f8121a;
    public final EventStore b;
    public final WorkScheduler c;
    public final SynchronizationGuard d;

    @Inject
    public WorkInitializer(Executor executor, EventStore eventStore, WorkScheduler workScheduler, SynchronizationGuard synchronizationGuard) {
        this.f8121a = executor;
        this.b = eventStore;
        this.c = workScheduler;
        this.d = synchronizationGuard;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object c() {
        for (TransportContext transportContext : this.b.loadActiveContexts()) {
            this.c.schedule(transportContext, 1);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        this.d.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.j
            @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
            public final Object execute() {
                Object c;
                c = WorkInitializer.this.c();
                return c;
            }
        });
    }

    public void ensureContextsScheduled() {
        this.f8121a.execute(new Runnable() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.k
            @Override // java.lang.Runnable
            public final void run() {
                WorkInitializer.this.d();
            }
        });
    }
}
