package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes8.dex */
public final class z0 implements l1 {

    /* renamed from: a  reason: collision with root package name */
    public final zzfo f9626a;
    public final y1 b;
    public final boolean c;
    public final c0 d;

    public z0(y1 y1Var, c0 c0Var, zzfo zzfoVar) {
        this.b = y1Var;
        this.c = c0Var.g(zzfoVar);
        this.d = c0Var;
        this.f9626a = zzfoVar;
    }

    public static z0 c(y1 y1Var, c0 c0Var, zzfo zzfoVar) {
        return new z0(y1Var, c0Var, zzfoVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00bf A[EDGE_INSN: B:56:0x00bf->B:33:0x00bf ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.Object r11, byte[] r12, int r13, int r14, com.google.android.gms.internal.mlkit_vision_barcode_bundled.m r15) throws java.io.IOException {
        /*
            r10 = this;
            r0 = r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed r0 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed) r0
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz r1 = r0.zzc
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz.zzc()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz.c()
            r0.zzc = r1
        L11:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdz r11 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdz) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.f0 r11 = r11.k()
            r0 = 0
            r2 = r0
        L19:
            if (r13 >= r14) goto Lca
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.n.j(r12, r13, r15)
            int r13 = r15.f9604a
            r3 = 11
            r5 = 2
            if (r13 == r3) goto L65
            r3 = r13 & 7
            if (r3 != r5) goto L60
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.c0 r2 = r10.d
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo r3 = r15.d
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r5 = r10.f9626a
            int r6 = r13 >>> 3
            java.lang.Object r8 = r2.d(r3, r5, r6)
            if (r8 == 0) goto L55
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.d1 r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.d1.a()
            r2 = r8
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeb r2 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeb) r2
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r3 = r2.c
            java.lang.Class r3 = r3.getClass()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1 r13 = r13.b(r3)
            int r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.n.d(r13, r12, r4, r14, r15)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.i0 r2 = r2.d
            java.lang.Object r3 = r15.c
            r11.i(r2, r3)
            goto L5e
        L55:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.n.i(r2, r3, r4, r5, r6, r7)
        L5e:
            r2 = r8
            goto L19
        L60:
            int r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.n.p(r13, r12, r4, r14, r15)
            goto L19
        L65:
            r13 = 0
            r3 = r0
        L67:
            if (r4 >= r14) goto Lbf
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.n.j(r12, r4, r15)
            int r6 = r15.f9604a
            int r7 = r6 >>> 3
            r8 = r6 & 7
            if (r7 == r5) goto La3
            r9 = 3
            if (r7 == r9) goto L79
            goto Lb6
        L79:
            if (r2 == 0) goto L98
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.d1 r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.d1.a()
            r7 = r2
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeb r7 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeb) r7
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r8 = r7.c
            java.lang.Class r8 = r8.getClass()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1 r6 = r6.b(r8)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.n.d(r6, r12, r4, r14, r15)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.i0 r6 = r7.d
            java.lang.Object r7 = r15.c
            r11.i(r6, r7)
            goto L67
        L98:
            if (r8 != r5) goto Lb6
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.n.a(r12, r4, r15)
            java.lang.Object r3 = r15.c
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r3 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r3
            goto L67
        La3:
            if (r8 != 0) goto Lb6
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.n.j(r12, r4, r15)
            int r13 = r15.f9604a
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.c0 r2 = r10.d
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo r6 = r15.d
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r7 = r10.f9626a
            java.lang.Object r2 = r2.d(r6, r7, r13)
            goto L67
        Lb6:
            r7 = 12
            if (r6 == r7) goto Lbf
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.n.p(r6, r12, r4, r14, r15)
            goto L67
        Lbf:
            if (r3 == 0) goto Lc7
            int r13 = r13 << 3
            r13 = r13 | r5
            r1.f(r13, r3)
        Lc7:
            r13 = r4
            goto L19
        Lca:
            if (r13 != r14) goto Lcd
            return
        Lcd:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zze()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.z0.a(java.lang.Object, byte[], int, int, com.google.android.gms.internal.mlkit_vision_barcode_bundled.m):void");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final void b(Object obj, m2 m2Var) throws IOException {
        Iterator f = this.d.b(obj).f();
        while (f.hasNext()) {
            Map.Entry entry = (Map.Entry) f.next();
            zzds zzdsVar = (zzds) entry.getKey();
            if (zzdsVar.zze() == zzhp.MESSAGE) {
                zzdsVar.zzg();
                zzdsVar.zzf();
                if (entry instanceof k0) {
                    m2Var.zzw(zzdsVar.zza(), ((k0) entry).a().zzb());
                } else {
                    m2Var.zzw(zzdsVar.zza(), entry.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        y1 y1Var = this.b;
        y1Var.i(y1Var.d(obj), m2Var);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final int zza(Object obj) {
        y1 y1Var = this.b;
        int b = y1Var.b(y1Var.d(obj));
        return this.c ? b + this.d.b(obj).b() : b;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final int zzb(Object obj) {
        int hashCode = this.b.d(obj).hashCode();
        return this.c ? (hashCode * 53) + this.d.b(obj).f9593a.hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final Object zze() {
        zzfo zzfoVar = this.f9626a;
        if (zzfoVar instanceof zzed) {
            return ((zzed) zzfoVar).d();
        }
        return zzfoVar.zzY().zzk();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final void zzf(Object obj) {
        this.b.g(obj);
        this.d.e(obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final void zzg(Object obj, Object obj2) {
        n1.d(this.b, obj, obj2);
        if (this.c) {
            n1.c(this.d, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final boolean zzj(Object obj, Object obj2) {
        if (this.b.d(obj).equals(this.b.d(obj2))) {
            if (this.c) {
                return this.d.b(obj).equals(this.d.b(obj2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final boolean zzk(Object obj) {
        return this.d.b(obj).k();
    }
}
