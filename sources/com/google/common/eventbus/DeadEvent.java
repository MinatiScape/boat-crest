package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
@Beta
/* loaded from: classes10.dex */
public class DeadEvent {

    /* renamed from: a  reason: collision with root package name */
    public final Object f10606a;
    public final Object b;

    public DeadEvent(Object obj, Object obj2) {
        this.f10606a = Preconditions.checkNotNull(obj);
        this.b = Preconditions.checkNotNull(obj2);
    }

    public Object getEvent() {
        return this.b;
    }

    public Object getSource() {
        return this.f10606a;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("source", this.f10606a).add("event", this.b).toString();
    }
}
