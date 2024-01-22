package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportScheduleCallback;
/* loaded from: classes6.dex */
public final class i<T> implements Transport<T> {

    /* renamed from: a  reason: collision with root package name */
    public final TransportContext f8110a;
    public final String b;
    public final Encoding c;
    public final Transformer<T, byte[]> d;
    public final j e;

    public i(TransportContext transportContext, String str, Encoding encoding, Transformer<T, byte[]> transformer, j jVar) {
        this.f8110a = transportContext;
        this.b = str;
        this.c = encoding;
        this.d = transformer;
        this.e = jVar;
    }

    public static /* synthetic */ void b(Exception exc) {
    }

    @Override // com.google.android.datatransport.Transport
    public void schedule(Event<T> event, TransportScheduleCallback transportScheduleCallback) {
        this.e.send(SendRequest.a().setTransportContext(this.f8110a).b(event).setTransportName(this.b).c(this.d).a(this.c).build(), transportScheduleCallback);
    }

    @Override // com.google.android.datatransport.Transport
    public void send(Event<T> event) {
        schedule(event, new TransportScheduleCallback() { // from class: com.google.android.datatransport.runtime.h
            @Override // com.google.android.datatransport.TransportScheduleCallback
            public final void onSchedule(Exception exc) {
                i.b(exc);
            }
        });
    }
}
