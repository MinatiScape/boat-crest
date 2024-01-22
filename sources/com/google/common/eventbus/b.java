package com.google.common.eventbus;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public class b {
    @Weak

    /* renamed from: a  reason: collision with root package name */
    public EventBus f10614a;
    @VisibleForTesting
    public final Object b;
    public final Method c;
    public final Executor d;

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public final /* synthetic */ Object h;

        public a(Object obj) {
            this.h = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                b.this.f(this.h);
            } catch (InvocationTargetException e) {
                b.this.f10614a.b(e.getCause(), b.this.c(this.h));
            }
        }
    }

    @VisibleForTesting
    /* renamed from: com.google.common.eventbus.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static final class C0500b extends b {
        public /* synthetic */ C0500b(EventBus eventBus, Object obj, Method method, a aVar) {
            this(eventBus, obj, method);
        }

        @Override // com.google.common.eventbus.b
        public void f(Object obj) throws InvocationTargetException {
            synchronized (this) {
                super.f(obj);
            }
        }

        public C0500b(EventBus eventBus, Object obj, Method method) {
            super(eventBus, obj, method, null);
        }
    }

    public /* synthetic */ b(EventBus eventBus, Object obj, Method method, a aVar) {
        this(eventBus, obj, method);
    }

    public static b d(EventBus eventBus, Object obj, Method method) {
        if (g(method)) {
            return new b(eventBus, obj, method);
        }
        return new C0500b(eventBus, obj, method, null);
    }

    public static boolean g(Method method) {
        return method.getAnnotation(AllowConcurrentEvents.class) != null;
    }

    public final SubscriberExceptionContext c(Object obj) {
        return new SubscriberExceptionContext(this.f10614a, obj, this.b, this.c);
    }

    public final void e(Object obj) {
        this.d.execute(new a(obj));
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof b) {
            b bVar = (b) obj;
            return this.b == bVar.b && this.c.equals(bVar.c);
        }
        return false;
    }

    @VisibleForTesting
    public void f(Object obj) throws InvocationTargetException {
        try {
            this.c.invoke(this.b, Preconditions.checkNotNull(obj));
        } catch (IllegalAccessException e) {
            String valueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(valueOf.length() + 28);
            sb.append("Method became inaccessible: ");
            sb.append(valueOf);
            throw new Error(sb.toString(), e);
        } catch (IllegalArgumentException e2) {
            String valueOf2 = String.valueOf(obj);
            StringBuilder sb2 = new StringBuilder(valueOf2.length() + 33);
            sb2.append("Method rejected target/argument: ");
            sb2.append(valueOf2);
            throw new Error(sb2.toString(), e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            }
            throw e3;
        }
    }

    public final int hashCode() {
        return ((this.c.hashCode() + 31) * 31) + System.identityHashCode(this.b);
    }

    public b(EventBus eventBus, Object obj, Method method) {
        this.f10614a = eventBus;
        this.b = Preconditions.checkNotNull(obj);
        this.c = method;
        method.setAccessible(true);
        this.d = eventBus.a();
    }
}
