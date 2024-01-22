package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes7.dex */
public final class q7<T> implements c8<T> {

    /* renamed from: a  reason: collision with root package name */
    public final zzyk f8721a;
    public final u8<?, ?> b;
    public final boolean c;
    public final o6<?> d;

    public q7(u8<?, ?> u8Var, o6<?> o6Var, zzyk zzykVar) {
        this.b = u8Var;
        this.c = o6Var.d(zzykVar);
        this.d = o6Var;
        this.f8721a = zzykVar;
    }

    public static <T> q7<T> c(u8<?, ?> u8Var, o6<?> o6Var, zzyk zzykVar) {
        return new q7<>(u8Var, o6Var, zzykVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final int a(T t) {
        int hashCode = this.b.e(t).hashCode();
        return this.c ? (hashCode * 53) + this.d.e(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final boolean b(T t, T t2) {
        if (this.b.e(t).equals(this.b.e(t2))) {
            if (this.c) {
                return this.d.e(t).equals(this.d.e(t2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final void e(T t) {
        this.b.k(t);
        this.d.g(t);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0099 A[EDGE_INSN: B:57:0x0099->B:34:0x0099 ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.gms.internal.firebase_ml.c8
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(T r10, byte[] r11, int r12, int r13, com.google.android.gms.internal.firebase_ml.v5 r14) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r10
            com.google.android.gms.internal.firebase_ml.zzwz r0 = (com.google.android.gms.internal.firebase_ml.zzwz) r0
            com.google.android.gms.internal.firebase_ml.zzzz r1 = r0.zzclj
            com.google.android.gms.internal.firebase_ml.zzzz r2 = com.google.android.gms.internal.firebase_ml.zzzz.zzwz()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.firebase_ml.zzzz r1 = com.google.android.gms.internal.firebase_ml.zzzz.f()
            r0.zzclj = r1
        L11:
            com.google.android.gms.internal.firebase_ml.zzwz$zzc r10 = (com.google.android.gms.internal.firebase_ml.zzwz.zzc) r10
            r10.f()
            r10 = 0
            r0 = r10
        L18:
            if (r12 >= r13) goto La4
            int r4 = com.google.android.gms.internal.firebase_ml.w5.i(r11, r12, r14)
            int r2 = r14.f8744a
            r12 = 11
            r3 = 2
            if (r2 == r12) goto L51
            r12 = r2 & 7
            if (r12 != r3) goto L4c
            com.google.android.gms.internal.firebase_ml.o6<?> r12 = r9.d
            com.google.android.gms.internal.firebase_ml.zzwo r0 = r14.d
            com.google.android.gms.internal.firebase_ml.zzyk r3 = r9.f8721a
            int r5 = r2 >>> 3
            java.lang.Object r12 = r12.b(r0, r3, r5)
            r0 = r12
            com.google.android.gms.internal.firebase_ml.zzwz$zze r0 = (com.google.android.gms.internal.firebase_ml.zzwz.zze) r0
            if (r0 != 0) goto L43
            r3 = r11
            r5 = r13
            r6 = r1
            r7 = r14
            int r12 = com.google.android.gms.internal.firebase_ml.w5.c(r2, r3, r4, r5, r6, r7)
            goto L18
        L43:
            com.google.android.gms.internal.firebase_ml.x7.c()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L4c:
            int r12 = com.google.android.gms.internal.firebase_ml.w5.a(r2, r11, r4, r13, r14)
            goto L18
        L51:
            r12 = 0
            r2 = r10
        L53:
            if (r4 >= r13) goto L99
            int r4 = com.google.android.gms.internal.firebase_ml.w5.i(r11, r4, r14)
            int r5 = r14.f8744a
            int r6 = r5 >>> 3
            r7 = r5 & 7
            if (r6 == r3) goto L7b
            r8 = 3
            if (r6 == r8) goto L65
            goto L90
        L65:
            if (r0 != 0) goto L72
            if (r7 != r3) goto L90
            int r4 = com.google.android.gms.internal.firebase_ml.w5.q(r11, r4, r14)
            java.lang.Object r2 = r14.c
            com.google.android.gms.internal.firebase_ml.zzvv r2 = (com.google.android.gms.internal.firebase_ml.zzvv) r2
            goto L53
        L72:
            com.google.android.gms.internal.firebase_ml.x7.c()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L7b:
            if (r7 != 0) goto L90
            int r4 = com.google.android.gms.internal.firebase_ml.w5.i(r11, r4, r14)
            int r12 = r14.f8744a
            com.google.android.gms.internal.firebase_ml.o6<?> r0 = r9.d
            com.google.android.gms.internal.firebase_ml.zzwo r5 = r14.d
            com.google.android.gms.internal.firebase_ml.zzyk r6 = r9.f8721a
            java.lang.Object r0 = r0.b(r5, r6, r12)
            com.google.android.gms.internal.firebase_ml.zzwz$zze r0 = (com.google.android.gms.internal.firebase_ml.zzwz.zze) r0
            goto L53
        L90:
            r6 = 12
            if (r5 == r6) goto L99
            int r4 = com.google.android.gms.internal.firebase_ml.w5.a(r5, r11, r4, r13, r14)
            goto L53
        L99:
            if (r2 == 0) goto La1
            int r12 = r12 << 3
            r12 = r12 | r3
            r1.c(r12, r2)
        La1:
            r12 = r4
            goto L18
        La4:
            if (r12 != r13) goto La7
            return
        La7:
            com.google.android.gms.internal.firebase_ml.zzxk r10 = com.google.android.gms.internal.firebase_ml.zzxk.zzvi()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.q7.f(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase_ml.v5):void");
    }

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final void g(T t, p pVar) throws IOException {
        Iterator<Map.Entry<?, Object>> d = this.d.e(t).d();
        while (d.hasNext()) {
            Map.Entry<?, Object> next = d.next();
            zzwt zzwtVar = (zzwt) next.getKey();
            if (zzwtVar.zzuj() == zzaaq.MESSAGE && !zzwtVar.zzuk() && !zzwtVar.zzul()) {
                if (next instanceof z6) {
                    pVar.zza(zzwtVar.zzd(), ((z6) next).a().zztg());
                } else {
                    pVar.zza(zzwtVar.zzd(), next.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        u8<?, ?> u8Var = this.b;
        u8Var.g(u8Var.e(t), pVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final void h(T t, T t2) {
        e8.h(this.b, t, t2);
        if (this.c) {
            e8.f(this.d, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final int i(T t) {
        u8<?, ?> u8Var = this.b;
        int f = u8Var.f(u8Var.e(t)) + 0;
        return this.c ? f + this.d.e(t).s() : f;
    }

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final boolean j(T t) {
        return this.d.e(t).c();
    }

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final T newInstance() {
        return (T) this.f8721a.zzuu().zzva();
    }
}
