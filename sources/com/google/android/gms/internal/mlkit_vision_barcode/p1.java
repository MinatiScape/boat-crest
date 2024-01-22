package com.google.android.gms.internal.mlkit_vision_barcode;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import javax.annotation.CheckForNull;
import sun.misc.Unsafe;
/* loaded from: classes9.dex */
public final class p1 extends h1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Unsafe f9470a;
    public static final long b;
    public static final long c;
    public static final long d;
    public static final long e;
    public static final long f;

    /* loaded from: classes9.dex */
    public class a implements PrivilegedExceptionAction<Unsafe> {
        @Override // java.security.PrivilegedExceptionAction
        public final /* bridge */ /* synthetic */ Unsafe run() throws Exception {
            Field[] declaredFields;
            for (Field field : Unsafe.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (Unsafe.class.isInstance(obj)) {
                    return (Unsafe) Unsafe.class.cast(obj);
                }
            }
            throw new NoSuchFieldError("the Unsafe");
        }
    }

    static {
        Unsafe unsafe;
        try {
            try {
                unsafe = Unsafe.getUnsafe();
            } catch (PrivilegedActionException e2) {
                throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
            }
        } catch (SecurityException unused) {
            unsafe = (Unsafe) AccessController.doPrivileged(new a());
        }
        try {
            c = unsafe.objectFieldOffset(zzec.class.getDeclaredField("j"));
            b = unsafe.objectFieldOffset(zzec.class.getDeclaredField("i"));
            d = unsafe.objectFieldOffset(zzec.class.getDeclaredField("h"));
            e = unsafe.objectFieldOffset(q1.class.getDeclaredField("a"));
            f = unsafe.objectFieldOffset(q1.class.getDeclaredField("b"));
            f9470a = unsafe;
        } catch (NoSuchFieldException e3) {
            throw new RuntimeException(e3);
        } catch (RuntimeException e4) {
            throw e4;
        }
    }

    public /* synthetic */ p1(zzeg zzegVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final k1 a(zzec zzecVar, k1 k1Var) {
        k1 k1Var2;
        do {
            k1Var2 = zzecVar.i;
            if (k1Var == k1Var2) {
                return k1Var2;
            }
        } while (!e(zzecVar, k1Var2, k1Var));
        return k1Var2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final q1 b(zzec zzecVar, q1 q1Var) {
        q1 q1Var2;
        do {
            q1Var2 = zzecVar.j;
            if (q1Var == q1Var2) {
                return q1Var2;
            }
        } while (!g(zzecVar, q1Var2, q1Var));
        return q1Var2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final void c(q1 q1Var, @CheckForNull q1 q1Var2) {
        f9470a.putObject(q1Var, f, q1Var2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final void d(q1 q1Var, Thread thread) {
        f9470a.putObject(q1Var, e, thread);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final boolean e(zzec zzecVar, @CheckForNull k1 k1Var, k1 k1Var2) {
        return zzef.zza(f9470a, zzecVar, b, k1Var, k1Var2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final boolean f(zzec zzecVar, @CheckForNull Object obj, Object obj2) {
        return zzef.zza(f9470a, zzecVar, d, obj, obj2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final boolean g(zzec zzecVar, @CheckForNull q1 q1Var, @CheckForNull q1 q1Var2) {
        return zzef.zza(f9470a, zzecVar, c, q1Var, q1Var2);
    }
}
