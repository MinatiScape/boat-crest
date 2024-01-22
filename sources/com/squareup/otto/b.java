package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
/* loaded from: classes12.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final Object f13711a;
    public final Method b;
    public final int c;
    public boolean d = true;

    public b(Object obj, Method method) {
        Objects.requireNonNull(obj, "EventHandler target cannot be null.");
        Objects.requireNonNull(method, "EventHandler method cannot be null.");
        this.f13711a = obj;
        this.b = method;
        method.setAccessible(true);
        this.c = ((method.hashCode() + 31) * 31) + obj.hashCode();
    }

    public void a(Object obj) throws InvocationTargetException {
        if (this.d) {
            try {
                this.b.invoke(this.f13711a, obj);
                return;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                if (e2.getCause() instanceof Error) {
                    throw ((Error) e2.getCause());
                }
                throw e2;
            }
        }
        throw new IllegalStateException(toString() + " has been invalidated and can no longer handle events.");
    }

    public void b() {
        this.d = false;
    }

    public boolean c() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && b.class == obj.getClass()) {
            b bVar = (b) obj;
            return this.b.equals(bVar.b) && this.f13711a == bVar.f13711a;
        }
        return false;
    }

    public int hashCode() {
        return this.c;
    }

    public String toString() {
        return "[EventHandler " + this.b + "]";
    }
}
