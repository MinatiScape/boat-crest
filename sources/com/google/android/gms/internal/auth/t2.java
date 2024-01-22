package com.google.android.gms.internal.auth;

import java.lang.reflect.Field;
import sun.misc.Unsafe;
/* loaded from: classes6.dex */
public abstract class t2 {

    /* renamed from: a  reason: collision with root package name */
    public final Unsafe f8539a;

    public t2(Unsafe unsafe) {
        this.f8539a = unsafe;
    }

    public abstract double a(Object obj, long j);

    public abstract float b(Object obj, long j);

    public abstract void c(Object obj, long j, boolean z);

    public abstract void d(Object obj, long j, double d);

    public abstract void e(Object obj, long j, float f);

    public abstract boolean f(Object obj, long j);

    public final int g(Class cls) {
        return this.f8539a.arrayBaseOffset(cls);
    }

    public final int h(Class cls) {
        return this.f8539a.arrayIndexScale(cls);
    }

    public final int i(Object obj, long j) {
        return this.f8539a.getInt(obj, j);
    }

    public final long j(Object obj, long j) {
        return this.f8539a.getLong(obj, j);
    }

    public final long k(Field field) {
        return this.f8539a.objectFieldOffset(field);
    }

    public final Object l(Object obj, long j) {
        return this.f8539a.getObject(obj, j);
    }

    public final void m(Object obj, long j, int i) {
        this.f8539a.putInt(obj, j, i);
    }

    public final void n(Object obj, long j, long j2) {
        this.f8539a.putLong(obj, j, j2);
    }

    public final void o(Object obj, long j, Object obj2) {
        this.f8539a.putObject(obj, j, obj2);
    }
}
