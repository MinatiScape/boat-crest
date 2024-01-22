package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
/* loaded from: classes12.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public final Object f13712a;
    public final Method b;
    public final int c;
    public boolean d = true;

    public c(Object obj, Method method) {
        Objects.requireNonNull(obj, "EventProducer target cannot be null.");
        Objects.requireNonNull(method, "EventProducer method cannot be null.");
        this.f13712a = obj;
        this.b = method;
        method.setAccessible(true);
        this.c = ((method.hashCode() + 31) * 31) + obj.hashCode();
    }

    public void a() {
        this.d = false;
    }

    public boolean b() {
        return this.d;
    }

    public Object c() throws InvocationTargetException {
        if (this.d) {
            try {
                return this.b.invoke(this.f13712a, new Object[0]);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                if (e2.getCause() instanceof Error) {
                    throw ((Error) e2.getCause());
                }
                throw e2;
            }
        }
        throw new IllegalStateException(toString() + " has been invalidated and can no longer produce events.");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && c.class == obj.getClass()) {
            c cVar = (c) obj;
            return this.b.equals(cVar.b) && this.f13712a == cVar.f13712a;
        }
        return false;
    }

    public int hashCode() {
        return this.c;
    }

    public String toString() {
        return "[EventProducer " + this.b + "]";
    }
}
