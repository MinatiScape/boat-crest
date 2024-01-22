package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes8.dex */
public final class f0 {
    public static final f0 d = new f0(true);

    /* renamed from: a  reason: collision with root package name */
    public final v1 f9593a = new o1(16);
    public boolean b;
    public boolean c;

    public f0() {
    }

    public static int a(zzds zzdsVar, Object obj) {
        int zzd;
        int zzy;
        zzho zzd2 = zzdsVar.zzd();
        int zza = zzdsVar.zza();
        zzdsVar.zzg();
        int i = zzdj.zzb;
        int zzy2 = zzdj.zzy(zza << 3);
        if (zzd2 == zzho.zzj) {
            zzfo zzfoVar = (zzfo) obj;
            byte[] bArr = zzem.zzd;
            if (zzfoVar instanceof zzcl) {
                zzcl zzclVar = (zzcl) zzfoVar;
                throw null;
            }
            zzy2 += zzy2;
        }
        zzhp zzhpVar = zzhp.INT;
        int i2 = 4;
        switch (zzd2.ordinal()) {
            case 0:
                ((Double) obj).doubleValue();
                i2 = 8;
                break;
            case 1:
                ((Float) obj).floatValue();
                break;
            case 2:
                i2 = zzdj.zzz(((Long) obj).longValue());
                break;
            case 3:
                i2 = zzdj.zzz(((Long) obj).longValue());
                break;
            case 4:
                i2 = zzdj.zzu(((Integer) obj).intValue());
                break;
            case 5:
                ((Long) obj).longValue();
                i2 = 8;
                break;
            case 6:
                ((Integer) obj).intValue();
                break;
            case 7:
                ((Boolean) obj).booleanValue();
                i2 = 1;
                break;
            case 8:
                if (obj instanceof zzdb) {
                    zzd = ((zzdb) obj).zzd();
                    zzy = zzdj.zzy(zzd);
                    i2 = zzy + zzd;
                    break;
                } else {
                    i2 = zzdj.zzx((String) obj);
                    break;
                }
            case 9:
                i2 = ((zzfo) obj).zzE();
                break;
            case 10:
                if (obj instanceof zzet) {
                    zzd = ((zzet) obj).zza();
                    zzy = zzdj.zzy(zzd);
                    i2 = zzy + zzd;
                    break;
                } else {
                    i2 = zzdj.zzv((zzfo) obj);
                    break;
                }
            case 11:
                if (obj instanceof zzdb) {
                    zzd = ((zzdb) obj).zzd();
                    zzy = zzdj.zzy(zzd);
                } else {
                    zzd = ((byte[]) obj).length;
                    zzy = zzdj.zzy(zzd);
                }
                i2 = zzy + zzd;
                break;
            case 12:
                i2 = zzdj.zzy(((Integer) obj).intValue());
                break;
            case 13:
                if (obj instanceof zzef) {
                    i2 = zzdj.zzu(((zzef) obj).zza());
                    break;
                } else {
                    i2 = zzdj.zzu(((Integer) obj).intValue());
                    break;
                }
            case 14:
                ((Integer) obj).intValue();
                break;
            case 15:
                ((Long) obj).longValue();
                i2 = 8;
                break;
            case 16:
                int intValue = ((Integer) obj).intValue();
                i2 = zzdj.zzy((intValue >> 31) ^ (intValue + intValue));
                break;
            case 17:
                long longValue = ((Long) obj).longValue();
                i2 = zzdj.zzz((longValue >> 63) ^ (longValue + longValue));
                break;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return zzy2 + i2;
    }

    public static f0 d() {
        return d;
    }

    public static Object l(Object obj) {
        if (obj instanceof zzft) {
            return ((zzft) obj).zzc();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            return bArr2;
        }
        return obj;
    }

    public static boolean n(Map.Entry entry) {
        zzds zzdsVar = (zzds) entry.getKey();
        if (zzdsVar.zze() == zzhp.MESSAGE) {
            zzdsVar.zzg();
            Object value = entry.getValue();
            if (value instanceof zzfp) {
                return ((zzfp) value).zzac();
            }
            if (value instanceof zzet) {
                return true;
            }
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        return true;
    }

    public static final int o(Map.Entry entry) {
        zzds zzdsVar = (zzds) entry.getKey();
        Object value = entry.getValue();
        if (zzdsVar.zze() == zzhp.MESSAGE) {
            zzdsVar.zzg();
            zzdsVar.zzf();
            if (value instanceof zzet) {
                int zzy = zzdj.zzy(((zzds) entry.getKey()).zza());
                int zza = ((zzet) value).zza();
                int zzy2 = zzdj.zzy(zza) + zza;
                int zzy3 = zzdj.zzy(24);
                int zzy4 = zzdj.zzy(16);
                int zzy5 = zzdj.zzy(8);
                return zzy5 + zzy5 + zzy4 + zzy + zzy3 + zzy2;
            }
            int zzy6 = zzdj.zzy(((zzds) entry.getKey()).zza());
            int zzy7 = zzdj.zzy(24) + zzdj.zzv((zzfo) value);
            int zzy8 = zzdj.zzy(16);
            int zzy9 = zzdj.zzy(8);
            return zzy9 + zzy9 + zzy8 + zzy6 + zzy7;
        }
        return a(zzdsVar, value);
    }

    public final int b() {
        int i = 0;
        for (int i2 = 0; i2 < this.f9593a.b(); i2++) {
            i += o(this.f9593a.h(i2));
        }
        for (Map.Entry entry : this.f9593a.d()) {
            i += o(entry);
        }
        return i;
    }

    /* renamed from: c */
    public final f0 clone() {
        f0 f0Var = new f0();
        for (int i = 0; i < this.f9593a.b(); i++) {
            Map.Entry h = this.f9593a.h(i);
            f0Var.i((zzds) h.getKey(), h.getValue());
        }
        for (Map.Entry entry : this.f9593a.d()) {
            f0Var.i((zzds) entry.getKey(), entry.getValue());
        }
        f0Var.c = this.c;
        return f0Var;
    }

    public final Object e(zzds zzdsVar) {
        Object obj = this.f9593a.get(zzdsVar);
        if (obj instanceof zzet) {
            zzet zzetVar = (zzet) obj;
            throw null;
        }
        return obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof f0) {
            return this.f9593a.equals(((f0) obj).f9593a);
        }
        return false;
    }

    public final Iterator f() {
        return this.c ? new l0(this.f9593a.entrySet().iterator()) : this.f9593a.entrySet().iterator();
    }

    public final void g() {
        if (this.b) {
            return;
        }
        for (int i = 0; i < this.f9593a.b(); i++) {
            Map.Entry h = this.f9593a.h(i);
            if (h.getValue() instanceof zzed) {
                ((zzed) h.getValue()).zzS();
            }
        }
        this.f9593a.a();
        this.b = true;
    }

    public final void h(f0 f0Var) {
        for (int i = 0; i < f0Var.f9593a.b(); i++) {
            m(f0Var.f9593a.h(i));
        }
        for (Map.Entry entry : f0Var.f9593a.d()) {
            m(entry);
        }
    }

    public final int hashCode() {
        return this.f9593a.hashCode();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
        if ((r7 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzef) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0035, code lost:
        if ((r7 instanceof byte[]) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r0 == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0023, code lost:
        if ((r7 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzet) == false) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void i(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds r6, java.lang.Object r7) {
        /*
            r5 = this;
            r6.zzg()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r0 = r6.zzd()
            byte[] r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            java.util.Objects.requireNonNull(r7)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho.zza
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhp r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhp.INT
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhp r0 = r0.zza()
            int r0 = r0.ordinal()
            r1 = 1
            switch(r0) {
                case 0: goto L47;
                case 1: goto L44;
                case 2: goto L41;
                case 3: goto L3e;
                case 4: goto L3b;
                case 5: goto L38;
                case 6: goto L2f;
                case 7: goto L26;
                case 8: goto L1d;
                default: goto L1c;
            }
        L1c:
            goto L57
        L1d:
            boolean r0 = r7 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo
            if (r0 != 0) goto L4b
            boolean r0 = r7 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzet
            if (r0 == 0) goto L57
            goto L4b
        L26:
            boolean r0 = r7 instanceof java.lang.Integer
            if (r0 != 0) goto L4b
            boolean r0 = r7 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzef
            if (r0 == 0) goto L57
            goto L4b
        L2f:
            boolean r0 = r7 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
            if (r0 != 0) goto L4b
            boolean r0 = r7 instanceof byte[]
            if (r0 == 0) goto L57
            goto L4b
        L38:
            boolean r0 = r7 instanceof java.lang.String
            goto L49
        L3b:
            boolean r0 = r7 instanceof java.lang.Boolean
            goto L49
        L3e:
            boolean r0 = r7 instanceof java.lang.Double
            goto L49
        L41:
            boolean r0 = r7 instanceof java.lang.Float
            goto L49
        L44:
            boolean r0 = r7 instanceof java.lang.Long
            goto L49
        L47:
            boolean r0 = r7 instanceof java.lang.Integer
        L49:
            if (r0 == 0) goto L57
        L4b:
            boolean r0 = r7 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzet
            if (r0 == 0) goto L51
            r5.c = r1
        L51:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.v1 r0 = r5.f9593a
            r0.put(r6, r7)
            return
        L57:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            int r4 = r6.zza()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2[r3] = r4
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r6 = r6.zzd()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhp r6 = r6.zza()
            r2[r1] = r6
            r6 = 2
            java.lang.Class r7 = r7.getClass()
            java.lang.String r7 = r7.getName()
            r2[r6] = r7
            java.lang.String r6 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r6 = java.lang.String.format(r6, r2)
            r0.<init>(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.f0.i(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds, java.lang.Object):void");
    }

    public final boolean j() {
        return this.b;
    }

    public final boolean k() {
        for (int i = 0; i < this.f9593a.b(); i++) {
            if (!n(this.f9593a.h(i))) {
                return false;
            }
        }
        for (Map.Entry entry : this.f9593a.d()) {
            if (!n(entry)) {
                return false;
            }
        }
        return true;
    }

    public final void m(Map.Entry entry) {
        zzfo zzj;
        zzds zzdsVar = (zzds) entry.getKey();
        Object value = entry.getValue();
        if (!(value instanceof zzet)) {
            zzdsVar.zzg();
            if (zzdsVar.zze() == zzhp.MESSAGE) {
                Object e = e(zzdsVar);
                if (e == null) {
                    this.f9593a.put(zzdsVar, l(value));
                    return;
                }
                if (e instanceof zzft) {
                    zzj = zzdsVar.zzc((zzft) e, (zzft) value);
                } else {
                    zzfn zzZ = ((zzfo) e).zzZ();
                    zzdsVar.zzb(zzZ, (zzfo) value);
                    zzj = zzZ.zzj();
                }
                this.f9593a.put(zzdsVar, zzj);
                return;
            }
            this.f9593a.put(zzdsVar, l(value));
            return;
        }
        zzet zzetVar = (zzet) value;
        throw null;
    }

    public f0(boolean z) {
        g();
        g();
    }
}
