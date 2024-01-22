package com.google.common.eventbus;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import com.squareup.otto.Bus;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
@Beta
/* loaded from: classes10.dex */
public class EventBus {
    public static final Logger f = Logger.getLogger(EventBus.class.getName());

    /* renamed from: a  reason: collision with root package name */
    public final String f10607a;
    public final Executor b;
    public final SubscriberExceptionHandler c;
    public final c d;
    public final com.google.common.eventbus.a e;

    /* loaded from: classes10.dex */
    public static final class a implements SubscriberExceptionHandler {

        /* renamed from: a  reason: collision with root package name */
        public static final a f10608a = new a();

        public static Logger a(SubscriberExceptionContext subscriberExceptionContext) {
            String name = EventBus.class.getName();
            String identifier = subscriberExceptionContext.getEventBus().identifier();
            StringBuilder sb = new StringBuilder(name.length() + 1 + String.valueOf(identifier).length());
            sb.append(name);
            sb.append(".");
            sb.append(identifier);
            return Logger.getLogger(sb.toString());
        }

        public static String b(SubscriberExceptionContext subscriberExceptionContext) {
            Method subscriberMethod = subscriberExceptionContext.getSubscriberMethod();
            String name = subscriberMethod.getName();
            String name2 = subscriberMethod.getParameterTypes()[0].getName();
            String valueOf = String.valueOf(subscriberExceptionContext.getSubscriber());
            String valueOf2 = String.valueOf(subscriberExceptionContext.getEvent());
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 80 + name2.length() + valueOf.length() + valueOf2.length());
            sb.append("Exception thrown by subscriber method ");
            sb.append(name);
            sb.append(HexStringBuilder.COMMENT_BEGIN_CHAR);
            sb.append(name2);
            sb.append(HexStringBuilder.COMMENT_END_CHAR);
            sb.append(" on subscriber ");
            sb.append(valueOf);
            sb.append(" when dispatching event: ");
            sb.append(valueOf2);
            return sb.toString();
        }

        @Override // com.google.common.eventbus.SubscriberExceptionHandler
        public void handleException(Throwable th, SubscriberExceptionContext subscriberExceptionContext) {
            Logger a2 = a(subscriberExceptionContext);
            Level level = Level.SEVERE;
            if (a2.isLoggable(level)) {
                a2.log(level, b(subscriberExceptionContext), th);
            }
        }
    }

    public EventBus() {
        this(Bus.DEFAULT_IDENTIFIER);
    }

    public final Executor a() {
        return this.b;
    }

    public void b(Throwable th, SubscriberExceptionContext subscriberExceptionContext) {
        Preconditions.checkNotNull(th);
        Preconditions.checkNotNull(subscriberExceptionContext);
        try {
            this.c.handleException(th, subscriberExceptionContext);
        } catch (Throwable th2) {
            f.log(Level.SEVERE, String.format(Locale.ROOT, "Exception %s thrown while handling exception: %s", th2, th), th2);
        }
    }

    public final String identifier() {
        return this.f10607a;
    }

    public void post(Object obj) {
        Iterator<b> f2 = this.d.f(obj);
        if (f2.hasNext()) {
            this.e.a(obj, f2);
        } else if (obj instanceof DeadEvent) {
        } else {
            post(new DeadEvent(this, obj));
        }
    }

    public void register(Object obj) {
        this.d.g(obj);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(this.f10607a).toString();
    }

    public void unregister(Object obj) {
        this.d.h(obj);
    }

    public EventBus(String str) {
        this(str, MoreExecutors.directExecutor(), com.google.common.eventbus.a.c(), a.f10608a);
    }

    public EventBus(SubscriberExceptionHandler subscriberExceptionHandler) {
        this(Bus.DEFAULT_IDENTIFIER, MoreExecutors.directExecutor(), com.google.common.eventbus.a.c(), subscriberExceptionHandler);
    }

    public EventBus(String str, Executor executor, com.google.common.eventbus.a aVar, SubscriberExceptionHandler subscriberExceptionHandler) {
        this.d = new c(this);
        this.f10607a = (String) Preconditions.checkNotNull(str);
        this.b = (Executor) Preconditions.checkNotNull(executor);
        this.e = (com.google.common.eventbus.a) Preconditions.checkNotNull(aVar);
        this.c = (SubscriberExceptionHandler) Preconditions.checkNotNull(subscriberExceptionHandler);
    }
}
