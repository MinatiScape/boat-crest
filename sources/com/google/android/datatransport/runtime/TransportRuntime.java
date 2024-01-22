package com.google.android.datatransport.runtime;

import android.content.Context;
import androidx.annotation.RestrictTo;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.Monotonic;
import com.google.android.datatransport.runtime.time.WallTime;
import java.util.Collections;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class TransportRuntime implements j {
    public static volatile k e;

    /* renamed from: a  reason: collision with root package name */
    public final Clock f8079a;
    public final Clock b;
    public final Scheduler c;
    public final Uploader d;

    @Inject
    public TransportRuntime(@WallTime Clock clock, @Monotonic Clock clock2, Scheduler scheduler, Uploader uploader, WorkInitializer workInitializer) {
        this.f8079a = clock;
        this.b = clock2;
        this.c = scheduler;
        this.d = uploader;
        workInitializer.ensureContextsScheduled();
    }

    public static Set<Encoding> b(Destination destination) {
        if (destination instanceof EncodedDestination) {
            return Collections.unmodifiableSet(((EncodedDestination) destination).getSupportedEncodings());
        }
        return Collections.singleton(Encoding.of("proto"));
    }

    public static TransportRuntime getInstance() {
        k kVar = e;
        if (kVar != null) {
            return kVar.b();
        }
        throw new IllegalStateException("Not initialized!");
    }

    public static void initialize(Context context) {
        if (e == null) {
            synchronized (TransportRuntime.class) {
                if (e == null) {
                    e = d.c().a(context).build();
                }
            }
        }
    }

    public final EventInternal a(SendRequest sendRequest) {
        return EventInternal.builder().setEventMillis(this.f8079a.getTime()).setUptimeMillis(this.b.getTime()).setTransportName(sendRequest.g()).setEncodedPayload(new EncodedPayload(sendRequest.b(), sendRequest.d())).setCode(sendRequest.c().getCode()).build();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Uploader getUploader() {
        return this.d;
    }

    @Deprecated
    public TransportFactory newFactory(String str) {
        return new g(b(null), TransportContext.builder().setBackendName(str).build(), this);
    }

    @Override // com.google.android.datatransport.runtime.j
    public void send(SendRequest sendRequest, TransportScheduleCallback transportScheduleCallback) {
        this.c.schedule(sendRequest.f().withPriority(sendRequest.c().getPriority()), a(sendRequest), transportScheduleCallback);
    }

    public TransportFactory newFactory(Destination destination) {
        return new g(b(destination), TransportContext.builder().setBackendName(destination.getName()).setExtras(destination.getExtras()).build(), this);
    }
}
