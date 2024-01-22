package com.google.android.gms.internal.mlkit_common;

import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
/* loaded from: classes8.dex */
public final class d6 {

    /* renamed from: a  reason: collision with root package name */
    public static final Method f9182a;
    public static final Method b;
    public static final Method c;
    public static final Field d;
    public static final Field e;
    public static final Field f;
    public static final Object g;
    public static final Throwable h;

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Throwable th;
        Object obj;
        Field field;
        Method method;
        Method method2;
        Method method3;
        Field field2;
        Field field3;
        try {
            Class<?> cls = Class.forName("libcore.io.Libcore");
            Class<?> cls2 = Class.forName("libcore.io.StructStat");
            Class<?> cls3 = Class.forName("libcore.io.OsConstants");
            Class<?> cls4 = Class.forName("libcore.io.ForwardingOs");
            method = cls3.getDeclaredMethod("S_ISLNK", Integer.TYPE);
            try {
                method.setAccessible(true);
                method3 = cls4.getDeclaredMethod("lstat", String.class);
                try {
                    method2 = cls4.getDeclaredMethod("fstat", FileDescriptor.class);
                    try {
                        Field declaredField = cls.getDeclaredField("os");
                        declaredField.setAccessible(true);
                        obj = declaredField.get(cls);
                        try {
                            field2 = cls2.getField("st_dev");
                            try {
                                field3 = cls2.getField("st_ino");
                                try {
                                    field = cls2.getField("st_mode");
                                    try {
                                        field2.setAccessible(true);
                                        field3.setAccessible(true);
                                        field.setAccessible(true);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        try {
                                            Log.d("StructStatHelper", "Reflection failed", th);
                                            f9182a = method;
                                            b = method3;
                                            c = method2;
                                            d = field2;
                                            e = field3;
                                            f = field;
                                            g = obj;
                                            h = th;
                                        } finally {
                                            f9182a = method;
                                            b = method3;
                                            c = method2;
                                            d = field2;
                                            e = field3;
                                            f = field;
                                            g = obj;
                                            h = null;
                                        }
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    field = null;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                field = null;
                                field3 = null;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            field = null;
                            field2 = field;
                            field3 = field2;
                            Log.d("StructStatHelper", "Reflection failed", th);
                            f9182a = method;
                            b = method3;
                            c = method2;
                            d = field2;
                            e = field3;
                            f = field;
                            g = obj;
                            h = th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        obj = null;
                        field = null;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    obj = null;
                    field = null;
                    method2 = null;
                    field2 = 0;
                }
            } catch (Throwable th8) {
                th = th8;
                obj = null;
                field = null;
                method2 = null;
                method3 = method2;
                field2 = method3;
                field3 = field2;
                Log.d("StructStatHelper", "Reflection failed", th);
                f9182a = method;
                b = method3;
                c = method2;
                d = field2;
                e = field3;
                f = field;
                g = obj;
                h = th;
            }
        } catch (Throwable th9) {
            th = th9;
            obj = null;
            field = null;
            method = null;
            method2 = null;
        }
    }

    public static i6 a(final FileDescriptor fileDescriptor) throws IOException {
        return (i6) f(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzn
            @Override // java.util.concurrent.Callable
            public final Object call() {
                i6 e2;
                e2 = d6.e(d6.c.invoke(d6.g, fileDescriptor));
                return e2;
            }
        });
    }

    public static i6 d(final String str) throws IOException {
        return (i6) f(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzm
            @Override // java.util.concurrent.Callable
            public final Object call() {
                i6 e2;
                e2 = d6.e(d6.b.invoke(d6.g, str));
                return e2;
            }
        });
    }

    public static i6 e(Object obj) throws Exception {
        return new i6(((Long) d.get(obj)).longValue(), ((Long) e.get(obj)).longValue(), ((Boolean) f9182a.invoke(null, Integer.valueOf(((Integer) f.get(obj)).intValue()))).booleanValue());
    }

    public static Object f(Callable callable) throws IOException {
        try {
            Throwable th = h;
            if (th == null) {
                return callable.call();
            }
            throw new IOException(th);
        } catch (Throwable th2) {
            throw new IOException(th2);
        }
    }
}
