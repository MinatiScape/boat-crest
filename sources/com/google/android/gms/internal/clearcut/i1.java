package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes7.dex */
public final class i1<T> implements r1<T> {

    /* renamed from: a  reason: collision with root package name */
    public final zzdo f8582a;
    public final i2<?, ?> b;
    public final boolean c;
    public final e0<?> d;

    public i1(i2<?, ?> i2Var, e0<?> e0Var, zzdo zzdoVar) {
        this.b = i2Var;
        this.c = e0Var.g(zzdoVar);
        this.d = e0Var;
        this.f8582a = zzdoVar;
    }

    public static <T> i1<T> e(i2<?, ?> i2Var, e0<?> e0Var, zzdo zzdoVar) {
        return new i1<>(i2Var, e0Var, zzdoVar);
    }

    @Override // com.google.android.gms.internal.clearcut.r1
    public final int a(T t) {
        int hashCode = this.b.k(t).hashCode();
        return this.c ? (hashCode * 53) + this.d.b(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.clearcut.r1
    public final boolean b(T t, T t2) {
        if (this.b.k(t).equals(this.b.k(t2))) {
            if (this.c) {
                return this.d.b(t).equals(this.d.b(t2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.clearcut.r1
    public final void c(T t, T t2) {
        t1.i(this.b, t, t2);
        if (this.c) {
            t1.g(this.d, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.clearcut.r1
    public final int d(T t) {
        i2<?, ?> i2Var = this.b;
        int l = i2Var.l(i2Var.k(t)) + 0;
        return this.c ? l + this.d.b(t).m() : l;
    }

    @Override // com.google.android.gms.internal.clearcut.r1
    public final boolean f(T t) {
        return this.d.b(t).d();
    }

    @Override // com.google.android.gms.internal.clearcut.r1
    public final void g(T t, z2 z2Var) throws IOException {
        Iterator<Map.Entry<?, Object>> e = this.d.b(t).e();
        while (e.hasNext()) {
            Map.Entry<?, Object> next = e.next();
            zzca zzcaVar = (zzca) next.getKey();
            if (zzcaVar.zzav() != zzfq.MESSAGE || zzcaVar.zzaw() || zzcaVar.zzax()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            z2Var.zza(zzcaVar.zzc(), next instanceof q0 ? ((q0) next).a().zzr() : next.getValue());
        }
        i2<?, ?> i2Var = this.b;
        i2Var.e(i2Var.k(t), z2Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0061 A[EDGE_INSN: B:49:0x0061->B:27:0x0061 ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.gms.internal.clearcut.r1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h(T r8, byte[] r9, int r10, int r11, com.google.android.gms.internal.clearcut.o r12) throws java.io.IOException {
        /*
            r7 = this;
            com.google.android.gms.internal.clearcut.zzcg r8 = (com.google.android.gms.internal.clearcut.zzcg) r8
            com.google.android.gms.internal.clearcut.zzey r0 = r8.zzjp
            com.google.android.gms.internal.clearcut.zzey r1 = com.google.android.gms.internal.clearcut.zzey.zzea()
            if (r0 != r1) goto L10
            com.google.android.gms.internal.clearcut.zzey r0 = com.google.android.gms.internal.clearcut.zzey.f()
            r8.zzjp = r0
        L10:
            r8 = r0
        L11:
            if (r10 >= r11) goto L6b
            int r2 = com.google.android.gms.internal.clearcut.n.e(r9, r10, r12)
            int r0 = r12.f8592a
            r10 = 11
            r1 = 2
            if (r0 == r10) goto L30
            r10 = r0 & 7
            if (r10 != r1) goto L2b
            r1 = r9
            r3 = r11
            r4 = r8
            r5 = r12
            int r10 = com.google.android.gms.internal.clearcut.n.c(r0, r1, r2, r3, r4, r5)
            goto L11
        L2b:
            int r10 = com.google.android.gms.internal.clearcut.n.a(r0, r9, r2, r11, r12)
            goto L11
        L30:
            r10 = 0
            r0 = 0
        L32:
            if (r2 >= r11) goto L61
            int r2 = com.google.android.gms.internal.clearcut.n.e(r9, r2, r12)
            int r3 = r12.f8592a
            int r4 = r3 >>> 3
            r5 = r3 & 7
            if (r4 == r1) goto L4f
            r6 = 3
            if (r4 == r6) goto L44
            goto L58
        L44:
            if (r5 != r1) goto L58
            int r2 = com.google.android.gms.internal.clearcut.n.m(r9, r2, r12)
            java.lang.Object r0 = r12.c
            com.google.android.gms.internal.clearcut.zzbb r0 = (com.google.android.gms.internal.clearcut.zzbb) r0
            goto L32
        L4f:
            if (r5 != 0) goto L58
            int r2 = com.google.android.gms.internal.clearcut.n.e(r9, r2, r12)
            int r10 = r12.f8592a
            goto L32
        L58:
            r4 = 12
            if (r3 == r4) goto L61
            int r2 = com.google.android.gms.internal.clearcut.n.a(r3, r9, r2, r11, r12)
            goto L32
        L61:
            if (r0 == 0) goto L69
            int r10 = r10 << 3
            r10 = r10 | r1
            r8.d(r10, r0)
        L69:
            r10 = r2
            goto L11
        L6b:
            if (r10 != r11) goto L6e
            return
        L6e:
            com.google.android.gms.internal.clearcut.zzco r8 = com.google.android.gms.internal.clearcut.zzco.zzbo()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.i1.h(java.lang.Object, byte[], int, int, com.google.android.gms.internal.clearcut.o):void");
    }

    @Override // com.google.android.gms.internal.clearcut.r1
    public final T newInstance() {
        return (T) this.f8582a.zzbd().zzbi();
    }

    @Override // com.google.android.gms.internal.clearcut.r1
    public final void zzc(T t) {
        this.b.d(t);
        this.d.f(t);
    }
}
