package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.WallTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class Uploader {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8119a;
    public final BackendRegistry b;
    public final EventStore c;
    public final WorkScheduler d;
    public final Executor e;
    public final SynchronizationGuard f;
    public final Clock g;

    @Inject
    public Uploader(Context context, BackendRegistry backendRegistry, EventStore eventStore, WorkScheduler workScheduler, Executor executor, SynchronizationGuard synchronizationGuard, @WallTime Clock clock) {
        this.f8119a = context;
        this.b = backendRegistry;
        this.c = eventStore;
        this.d = workScheduler;
        this.e = executor;
        this.f = synchronizationGuard;
        this.g = clock;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Iterable f(TransportContext transportContext) {
        return this.c.loadBatch(transportContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object g(BackendResponse backendResponse, Iterable iterable, TransportContext transportContext, int i) {
        if (backendResponse.getStatus() == BackendResponse.Status.TRANSIENT_ERROR) {
            this.c.recordFailure(iterable);
            this.d.schedule(transportContext, i + 1);
            return null;
        }
        this.c.recordSuccess(iterable);
        if (backendResponse.getStatus() == BackendResponse.Status.OK) {
            this.c.recordNextCallTime(transportContext, this.g.getTime() + backendResponse.getNextRequestWaitMillis());
        }
        if (this.c.hasPendingEventsFor(transportContext)) {
            this.d.schedule(transportContext, 1, true);
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object h(TransportContext transportContext, int i) {
        this.d.schedule(transportContext, i + 1);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(final TransportContext transportContext, final int i, Runnable runnable) {
        try {
            try {
                SynchronizationGuard synchronizationGuard = this.f;
                final EventStore eventStore = this.c;
                Objects.requireNonNull(eventStore);
                synchronizationGuard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.h
                    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                    public final Object execute() {
                        return Integer.valueOf(EventStore.this.cleanUp());
                    }
                });
                if (!e()) {
                    this.f.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.f
                        @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                        public final Object execute() {
                            Object h;
                            h = Uploader.this.h(transportContext, i);
                            return h;
                        }
                    });
                } else {
                    j(transportContext, i);
                }
            } catch (SynchronizationException unused) {
                this.d.schedule(transportContext, i + 1);
            }
        } finally {
            runnable.run();
        }
    }

    public boolean e() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f8119a.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void j(final TransportContext transportContext, final int i) {
        BackendResponse send;
        TransportBackend transportBackend = this.b.get(transportContext.getBackendName());
        final Iterable<PersistedEvent> iterable = (Iterable) this.f.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.e
            @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
            public final Object execute() {
                Iterable f;
                f = Uploader.this.f(transportContext);
                return f;
            }
        });
        if (iterable.iterator().hasNext()) {
            if (transportBackend == null) {
                Logging.d("Uploader", "Unknown backend for %s, deleting event batch for it...", transportContext);
                send = BackendResponse.fatalError();
            } else {
                ArrayList arrayList = new ArrayList();
                for (PersistedEvent persistedEvent : iterable) {
                    arrayList.add(persistedEvent.getEvent());
                }
                send = transportBackend.send(BackendRequest.builder().setEvents(arrayList).setExtras(transportContext.getExtras()).build());
            }
            final BackendResponse backendResponse = send;
            this.f.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.g
                @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                public final Object execute() {
                    Object g;
                    g = Uploader.this.g(backendResponse, iterable, transportContext, i);
                    return g;
                }
            });
        }
    }

    public void upload(final TransportContext transportContext, final int i, final Runnable runnable) {
        this.e.execute(new Runnable() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.i
            @Override // java.lang.Runnable
            public final void run() {
                Uploader.this.i(transportContext, i, runnable);
            }
        });
    }
}
