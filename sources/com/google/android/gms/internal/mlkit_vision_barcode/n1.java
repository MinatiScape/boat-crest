package com.google.android.gms.internal.mlkit_vision_barcode;

import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class n1 extends h1 {
    public /* synthetic */ n1(zzee zzeeVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final k1 a(zzec zzecVar, k1 k1Var) {
        k1 k1Var2;
        synchronized (zzecVar) {
            k1Var2 = zzecVar.i;
            if (k1Var2 != k1Var) {
                zzecVar.i = k1Var;
            }
        }
        return k1Var2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final q1 b(zzec zzecVar, q1 q1Var) {
        q1 q1Var2;
        synchronized (zzecVar) {
            q1Var2 = zzecVar.j;
            if (q1Var2 != q1Var) {
                zzecVar.j = q1Var;
            }
        }
        return q1Var2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final void c(q1 q1Var, @CheckForNull q1 q1Var2) {
        q1Var.b = q1Var2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final void d(q1 q1Var, Thread thread) {
        q1Var.f9478a = thread;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final boolean e(zzec zzecVar, @CheckForNull k1 k1Var, k1 k1Var2) {
        k1 k1Var3;
        synchronized (zzecVar) {
            k1Var3 = zzecVar.i;
            if (k1Var3 == k1Var) {
                zzecVar.i = k1Var2;
                return true;
            }
            return false;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final boolean f(zzec zzecVar, @CheckForNull Object obj, Object obj2) {
        Object obj3;
        synchronized (zzecVar) {
            obj3 = zzecVar.h;
            if (obj3 == obj) {
                zzecVar.h = obj2;
                return true;
            }
            return false;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.h1
    public final boolean g(zzec zzecVar, @CheckForNull q1 q1Var, @CheckForNull q1 q1Var2) {
        q1 q1Var3;
        synchronized (zzecVar) {
            q1Var3 = zzecVar.j;
            if (q1Var3 == q1Var) {
                zzecVar.j = q1Var2;
                return true;
            }
            return false;
        }
    }
}
