package com.google.android.datatransport;

import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class a<T> extends Event<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Integer f8052a;
    public final T b;
    public final Priority c;

    public a(@Nullable Integer num, T t, Priority priority) {
        this.f8052a = num;
        Objects.requireNonNull(t, "Null payload");
        this.b = t;
        Objects.requireNonNull(priority, "Null priority");
        this.c = priority;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Event) {
            Event event = (Event) obj;
            Integer num = this.f8052a;
            if (num != null ? num.equals(event.getCode()) : event.getCode() == null) {
                if (this.b.equals(event.getPayload()) && this.c.equals(event.getPriority())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.datatransport.Event
    @Nullable
    public Integer getCode() {
        return this.f8052a;
    }

    @Override // com.google.android.datatransport.Event
    public T getPayload() {
        return this.b;
    }

    @Override // com.google.android.datatransport.Event
    public Priority getPriority() {
        return this.c;
    }

    public int hashCode() {
        Integer num = this.f8052a;
        return (((((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public String toString() {
        return "Event{code=" + this.f8052a + ", payload=" + this.b + ", priority=" + this.c + "}";
    }
}
