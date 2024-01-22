package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
public final class g3<T> implements o3<T> {

    /* renamed from: a  reason: collision with root package name */
    public final zzic f9978a;
    public final g4<?, ?> b;
    public final boolean c;
    public final b2<?> d;

    public g3(g4<?, ?> g4Var, b2<?> b2Var, zzic zzicVar) {
        this.b = g4Var;
        this.c = b2Var.g(zzicVar);
        this.d = b2Var;
        this.f9978a = zzicVar;
    }

    public static <T> g3<T> j(g4<?, ?> g4Var, b2<?> b2Var, zzic zzicVar) {
        return new g3<>(g4Var, b2Var, zzicVar);
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final int a(T t) {
        int hashCode = this.b.q(t).hashCode();
        return this.c ? (hashCode * 53) + this.d.h(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final boolean b(T t, T t2) {
        if (this.b.q(t).equals(this.b.q(t2))) {
            if (this.c) {
                return this.d.h(t).equals(this.d.h(t2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final void c(T t, T t2) {
        q3.i(this.b, t, t2);
        if (this.c) {
            q3.g(this.d, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final boolean d(T t) {
        return this.d.h(t).c();
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final void e(T t) {
        this.b.m(t);
        this.d.j(t);
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final int f(T t) {
        g4<?, ?> g4Var = this.b;
        int s = g4Var.s(g4Var.q(t)) + 0;
        return this.c ? s + this.d.h(t).r() : s;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00be A[EDGE_INSN: B:57:0x00be->B:33:0x00be ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.gms.internal.vision.o3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(T r11, byte[] r12, int r13, int r14, com.google.android.gms.internal.vision.f1 r15) throws java.io.IOException {
        /*
            r10 = this;
            r0 = r11
            com.google.android.gms.internal.vision.zzgs r0 = (com.google.android.gms.internal.vision.zzgs) r0
            com.google.android.gms.internal.vision.zzjm r1 = r0.zzwj
            com.google.android.gms.internal.vision.zzjm r2 = com.google.android.gms.internal.vision.zzjm.zzig()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.vision.zzjm r1 = com.google.android.gms.internal.vision.zzjm.f()
            r0.zzwj = r1
        L11:
            com.google.android.gms.internal.vision.zzgs$zze r11 = (com.google.android.gms.internal.vision.zzgs.zze) r11
            com.google.android.gms.internal.vision.e2 r11 = r11.i()
            r0 = 0
            r2 = r0
        L19:
            if (r13 >= r14) goto Lc9
            int r4 = com.google.android.gms.internal.vision.d1.i(r12, r13, r15)
            int r13 = r15.f9971a
            r3 = 11
            r5 = 2
            if (r13 == r3) goto L65
            r3 = r13 & 7
            if (r3 != r5) goto L60
            com.google.android.gms.internal.vision.b2<?> r2 = r10.d
            com.google.android.gms.internal.vision.zzgd r3 = r15.d
            com.google.android.gms.internal.vision.zzic r5 = r10.f9978a
            int r6 = r13 >>> 3
            java.lang.Object r2 = r2.b(r3, r5, r6)
            r8 = r2
            com.google.android.gms.internal.vision.zzgs$zzg r8 = (com.google.android.gms.internal.vision.zzgs.zzg) r8
            if (r8 == 0) goto L55
            com.google.android.gms.internal.vision.k3 r13 = com.google.android.gms.internal.vision.k3.b()
            com.google.android.gms.internal.vision.zzic r2 = r8.c
            java.lang.Class r2 = r2.getClass()
            com.google.android.gms.internal.vision.o3 r13 = r13.a(r2)
            int r13 = com.google.android.gms.internal.vision.d1.g(r13, r12, r4, r14, r15)
            com.google.android.gms.internal.vision.zzgs$a r2 = r8.d
            java.lang.Object r3 = r15.c
            r11.h(r2, r3)
            goto L5e
        L55:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.android.gms.internal.vision.d1.c(r2, r3, r4, r5, r6, r7)
        L5e:
            r2 = r8
            goto L19
        L60:
            int r13 = com.google.android.gms.internal.vision.d1.a(r13, r12, r4, r14, r15)
            goto L19
        L65:
            r13 = 0
            r3 = r0
        L67:
            if (r4 >= r14) goto Lbe
            int r4 = com.google.android.gms.internal.vision.d1.i(r12, r4, r15)
            int r6 = r15.f9971a
            int r7 = r6 >>> 3
            r8 = r6 & 7
            if (r7 == r5) goto La0
            r9 = 3
            if (r7 == r9) goto L79
            goto Lb5
        L79:
            if (r2 == 0) goto L95
            com.google.android.gms.internal.vision.k3 r6 = com.google.android.gms.internal.vision.k3.b()
            com.google.android.gms.internal.vision.zzic r7 = r2.c
            java.lang.Class r7 = r7.getClass()
            com.google.android.gms.internal.vision.o3 r6 = r6.a(r7)
            int r4 = com.google.android.gms.internal.vision.d1.g(r6, r12, r4, r14, r15)
            com.google.android.gms.internal.vision.zzgs$a r6 = r2.d
            java.lang.Object r7 = r15.c
            r11.h(r6, r7)
            goto L67
        L95:
            if (r8 != r5) goto Lb5
            int r4 = com.google.android.gms.internal.vision.d1.t(r12, r4, r15)
            java.lang.Object r3 = r15.c
            com.google.android.gms.internal.vision.zzfh r3 = (com.google.android.gms.internal.vision.zzfh) r3
            goto L67
        La0:
            if (r8 != 0) goto Lb5
            int r4 = com.google.android.gms.internal.vision.d1.i(r12, r4, r15)
            int r13 = r15.f9971a
            com.google.android.gms.internal.vision.b2<?> r2 = r10.d
            com.google.android.gms.internal.vision.zzgd r6 = r15.d
            com.google.android.gms.internal.vision.zzic r7 = r10.f9978a
            java.lang.Object r2 = r2.b(r6, r7, r13)
            com.google.android.gms.internal.vision.zzgs$zzg r2 = (com.google.android.gms.internal.vision.zzgs.zzg) r2
            goto L67
        Lb5:
            r7 = 12
            if (r6 == r7) goto Lbe
            int r4 = com.google.android.gms.internal.vision.d1.a(r6, r12, r4, r14, r15)
            goto L67
        Lbe:
            if (r3 == 0) goto Lc6
            int r13 = r13 << 3
            r13 = r13 | r5
            r1.d(r13, r3)
        Lc6:
            r13 = r4
            goto L19
        Lc9:
            if (r13 != r14) goto Lcc
            return
        Lcc:
            com.google.android.gms.internal.vision.zzhc r11 = com.google.android.gms.internal.vision.zzhc.zzgs()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.g3.g(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.f1):void");
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final void h(T t, x4 x4Var) throws IOException {
        Iterator<Map.Entry<?, Object>> d = this.d.h(t).d();
        while (d.hasNext()) {
            Map.Entry<?, Object> next = d.next();
            zzgk zzgkVar = (zzgk) next.getKey();
            if (zzgkVar.zzft() == zzkd.MESSAGE && !zzgkVar.zzfu() && !zzgkVar.zzfv()) {
                if (next instanceof n2) {
                    x4Var.zza(zzgkVar.zzag(), ((n2) next).a().zzdk());
                } else {
                    x4Var.zza(zzgkVar.zzag(), next.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        g4<?, ?> g4Var = this.b;
        g4Var.i(g4Var.q(t), x4Var);
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final void i(T t, p3 p3Var, zzgd zzgdVar) throws IOException {
        boolean z;
        g4<?, ?> g4Var = this.b;
        b2<?> b2Var = this.d;
        Object r = g4Var.r(t);
        e2<?> i = b2Var.i(t);
        do {
            try {
                if (p3Var.m() == Integer.MAX_VALUE) {
                    return;
                }
                int tag = p3Var.getTag();
                if (tag == 11) {
                    int i2 = 0;
                    Object obj = null;
                    zzfh zzfhVar = null;
                    while (p3Var.m() != Integer.MAX_VALUE) {
                        int tag2 = p3Var.getTag();
                        if (tag2 == 16) {
                            i2 = p3Var.r();
                            obj = b2Var.b(zzgdVar, this.f9978a, i2);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                b2Var.e(p3Var, obj, zzgdVar, i);
                            } else {
                                zzfhVar = p3Var.g();
                            }
                        } else if (!p3Var.p()) {
                            break;
                        }
                    }
                    if (p3Var.getTag() != 12) {
                        throw zzhc.zzgq();
                    } else if (zzfhVar != null) {
                        if (obj != null) {
                            b2Var.d(zzfhVar, obj, zzgdVar, i);
                        } else {
                            g4Var.b(r, i2, zzfhVar);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object b = b2Var.b(zzgdVar, this.f9978a, tag >>> 3);
                    if (b != null) {
                        b2Var.e(p3Var, b, zzgdVar, i);
                    } else {
                        z = g4Var.f(r, p3Var);
                        continue;
                    }
                } else {
                    z = p3Var.p();
                    continue;
                }
                z = true;
                continue;
            } finally {
                g4Var.k(t, r);
            }
        } while (z);
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final T newInstance() {
        return (T) this.f9978a.zzgj().zzgb();
    }
}
