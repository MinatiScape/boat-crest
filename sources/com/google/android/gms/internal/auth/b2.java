package com.google.android.gms.internal.auth;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes6.dex */
public final class b2 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class f8518a;
    public static final m2 b;
    public static final m2 c;
    public static final m2 d;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        f8518a = cls;
        b = j(false);
        c = j(true);
        d = new n2();
    }

    public static m2 a() {
        return b;
    }

    public static m2 b() {
        return c;
    }

    public static m2 c() {
        return d;
    }

    public static Object d(int i, List list, zzex zzexVar, Object obj, m2 m2Var) {
        if (zzexVar == null) {
            return obj;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zzexVar.zza()) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    obj = e(i, intValue, obj, m2Var);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzexVar.zza()) {
                    obj = e(i, intValue2, obj, m2Var);
                    it.remove();
                }
            }
        }
        return obj;
    }

    public static Object e(int i, int i2, Object obj, m2 m2Var) {
        if (obj == null) {
            obj = m2Var.c();
        }
        m2Var.d(obj, i, i2);
        return obj;
    }

    public static void f(m2 m2Var, Object obj, Object obj2) {
        m2Var.f(obj, m2Var.b(m2Var.a(obj), m2Var.a(obj2)));
    }

    public static void g(Class cls) {
        Class cls2;
        if (!zzeu.class.isAssignableFrom(cls) && (cls2 = f8518a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static boolean h(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static void i(m1 m1Var, Object obj, Object obj2, long j) {
        u2.p(obj, j, m1.a(u2.f(obj, j), u2.f(obj2, j)));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:5:0x0004
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static com.google.android.gms.internal.auth.m2 j(boolean r6) {
        /*
            r0 = 0
            java.lang.Class<com.google.protobuf.z0> r1 = com.google.protobuf.z0.class
            goto L5
        L4:
            r1 = r0
        L5:
            if (r1 != 0) goto L8
            return r0
        L8:
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L23
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch: java.lang.Throwable -> L23
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L23
            java.lang.reflect.Constructor r1 = r1.getConstructor(r3)     // Catch: java.lang.Throwable -> L23
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L23
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch: java.lang.Throwable -> L23
            r2[r5] = r6     // Catch: java.lang.Throwable -> L23
            java.lang.Object r6 = r1.newInstance(r2)     // Catch: java.lang.Throwable -> L23
            com.google.android.gms.internal.auth.m2 r6 = (com.google.android.gms.internal.auth.m2) r6     // Catch: java.lang.Throwable -> L23
            return r6
        L23:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.b2.j(boolean):com.google.android.gms.internal.auth.m2");
    }
}
