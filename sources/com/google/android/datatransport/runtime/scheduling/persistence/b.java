package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class b extends PersistedEvent {

    /* renamed from: a  reason: collision with root package name */
    public final long f8138a;
    public final TransportContext b;
    public final EventInternal c;

    public b(long j, TransportContext transportContext, EventInternal eventInternal) {
        this.f8138a = j;
        Objects.requireNonNull(transportContext, "Null transportContext");
        this.b = transportContext;
        Objects.requireNonNull(eventInternal, "Null event");
        this.c = eventInternal;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PersistedEvent) {
            PersistedEvent persistedEvent = (PersistedEvent) obj;
            return this.f8138a == persistedEvent.getId() && this.b.equals(persistedEvent.getTransportContext()) && this.c.equals(persistedEvent.getEvent());
        }
        return false;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public EventInternal getEvent() {
        return this.c;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public long getId() {
        return this.f8138a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public TransportContext getTransportContext() {
        return this.b;
    }

    public int hashCode() {
        long j = this.f8138a;
        return ((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public String toString() {
        return "PersistedEvent{id=" + this.f8138a + ", transportContext=" + this.b + ", event=" + this.c + "}";
    }
}
