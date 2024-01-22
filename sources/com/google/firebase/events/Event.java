package com.google.firebase.events;

import com.google.firebase.components.Preconditions;
/* loaded from: classes10.dex */
public class Event<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Class<T> f11277a;
    public final T b;

    public Event(Class<T> cls, T t) {
        this.f11277a = (Class) Preconditions.checkNotNull(cls);
        this.b = (T) Preconditions.checkNotNull(t);
    }

    public T getPayload() {
        return this.b;
    }

    public Class<T> getType() {
        return this.f11277a;
    }

    public String toString() {
        return String.format("Event{type: %s, payload: %s}", this.f11277a, this.b);
    }
}
