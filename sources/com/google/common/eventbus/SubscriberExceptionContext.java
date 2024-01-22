package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import java.lang.reflect.Method;
/* loaded from: classes10.dex */
public class SubscriberExceptionContext {

    /* renamed from: a  reason: collision with root package name */
    public final EventBus f10609a;
    public final Object b;
    public final Object c;
    public final Method d;

    public SubscriberExceptionContext(EventBus eventBus, Object obj, Object obj2, Method method) {
        this.f10609a = (EventBus) Preconditions.checkNotNull(eventBus);
        this.b = Preconditions.checkNotNull(obj);
        this.c = Preconditions.checkNotNull(obj2);
        this.d = (Method) Preconditions.checkNotNull(method);
    }

    public Object getEvent() {
        return this.b;
    }

    public EventBus getEventBus() {
        return this.f10609a;
    }

    public Object getSubscriber() {
        return this.c;
    }

    public Method getSubscriberMethod() {
        return this.d;
    }
}
