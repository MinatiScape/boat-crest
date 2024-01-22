package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzun;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzuo<T extends zzun<T>> {
    public static final zzuo zzb = new zzuo(true);
    public final zzxk<T, Object> zza = new zzxa(16);
    public boolean zzc;
    public boolean zzd;

    public zzuo() {
    }

    public static int zza(zzun<?> zzunVar, Object obj) {
        zzye zzd = zzunVar.zzd();
        int zza = zzunVar.zza();
        zzunVar.zzg();
        int zzC = zzto.zzC(zza);
        if (zzd == zzye.zzj) {
            zzvi.zzi((zzwk) obj);
            zzC += zzC;
        }
        zzyf zzyfVar = zzyf.INT;
        int i = 4;
        switch (zzd.ordinal()) {
            case 0:
                ((Double) obj).doubleValue();
                i = 8;
                break;
            case 1:
                ((Float) obj).floatValue();
                break;
            case 2:
                i = zzto.zzE(((Long) obj).longValue());
                break;
            case 3:
                i = zzto.zzE(((Long) obj).longValue());
                break;
            case 4:
                i = zzto.zzx(((Integer) obj).intValue());
                break;
            case 5:
                ((Long) obj).longValue();
                i = 8;
                break;
            case 6:
                ((Integer) obj).intValue();
                break;
            case 7:
                ((Boolean) obj).booleanValue();
                i = 1;
                break;
            case 8:
                if (obj instanceof zztd) {
                    i = zzto.zzu((zztd) obj);
                    break;
                } else {
                    i = zzto.zzB((String) obj);
                    break;
                }
            case 9:
                i = zzto.zzw((zzwk) obj);
                break;
            case 10:
                if (obj instanceof zzvp) {
                    i = zzto.zzy((zzvp) obj);
                    break;
                } else {
                    i = zzto.zzz((zzwk) obj);
                    break;
                }
            case 11:
                if (obj instanceof zztd) {
                    i = zzto.zzu((zztd) obj);
                    break;
                } else {
                    i = zzto.zzt((byte[]) obj);
                    break;
                }
            case 12:
                i = zzto.zzD(((Integer) obj).intValue());
                break;
            case 13:
                if (obj instanceof zzvb) {
                    i = zzto.zzx(((zzvb) obj).zza());
                    break;
                } else {
                    i = zzto.zzx(((Integer) obj).intValue());
                    break;
                }
            case 14:
                ((Integer) obj).intValue();
                break;
            case 15:
                ((Long) obj).longValue();
                i = 8;
                break;
            case 16:
                int intValue = ((Integer) obj).intValue();
                i = zzto.zzD((intValue >> 31) ^ (intValue + intValue));
                break;
            case 17:
                long longValue = ((Long) obj).longValue();
                i = zzto.zzE((longValue >> 63) ^ (longValue + longValue));
                break;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return zzC + i;
    }

    public static <T extends zzun<T>> zzuo<T> zzd() {
        return zzb;
    }

    public static Object zzl(Object obj) {
        if (obj instanceof zzwp) {
            return ((zzwp) obj).zzc();
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

    public static <T extends zzun<T>> boolean zzn(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zze() == zzyf.MESSAGE) {
            key.zzg();
            Object value = entry.getValue();
            if (value instanceof zzwk) {
                if (!((zzwk) value).zzas()) {
                    return false;
                }
            } else if (value instanceof zzvp) {
                return true;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        }
        return true;
    }

    public static final int zzo(Map.Entry<T, Object> entry) {
        int zzD;
        int zzD2;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.zze() == zzyf.MESSAGE) {
            key.zzg();
            key.zzf();
            if (value instanceof zzvp) {
                int zza = entry.getKey().zza();
                int zzD3 = zzto.zzD(8);
                int zza2 = ((zzvp) value).zza();
                zzD = zzD3 + zzD3 + zzto.zzD(16) + zzto.zzD(zza);
                zzD2 = zzto.zzD(24) + zzto.zzD(zza2) + zza2;
            } else {
                int zza3 = entry.getKey().zza();
                int zzD4 = zzto.zzD(8);
                zzD = zzD4 + zzD4 + zzto.zzD(16) + zzto.zzD(zza3);
                zzD2 = zzto.zzD(24) + zzto.zzz((zzwk) value);
            }
            return zzD + zzD2;
        }
        return zza(key, value);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzuo) {
            return this.zza.equals(((zzuo) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzb() {
        int i = 0;
        for (int i2 = 0; i2 < this.zza.zzb(); i2++) {
            i += zzo(this.zza.zzg(i2));
        }
        for (Map.Entry<T, Object> entry : this.zza.zzc()) {
            i += zzo(entry);
        }
        return i;
    }

    /* renamed from: zzc */
    public final zzuo<T> clone() {
        zzuo<T> zzuoVar = new zzuo<>();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry<T, Object> zzg = this.zza.zzg(i);
            zzuoVar.zzi(zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry<T, Object> entry : this.zza.zzc()) {
            zzuoVar.zzi(entry.getKey(), entry.getValue());
        }
        zzuoVar.zzd = this.zzd;
        return zzuoVar;
    }

    public final Object zze(T t) {
        Object obj = this.zza.get(t);
        if (obj instanceof zzvp) {
            zzvp zzvpVar = (zzvp) obj;
            throw null;
        }
        return obj;
    }

    public final Iterator<Map.Entry<T, Object>> zzf() {
        if (this.zzd) {
            return new zzvo(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    public final void zzg() {
        if (this.zzc) {
            return;
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzh(zzuo<T> zzuoVar) {
        for (int i = 0; i < zzuoVar.zza.zzb(); i++) {
            zzm(zzuoVar.zza.zzg(i));
        }
        for (Map.Entry<T, Object> entry : zzuoVar.zza.zzc()) {
            zzm(entry);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        if ((r7 instanceof com.google.android.gms.internal.gtm.zzvb) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0033, code lost:
        if ((r7 instanceof byte[]) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0047, code lost:
        if (r0 == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
        if ((r7 instanceof com.google.android.gms.internal.gtm.zzvp) == false) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzi(T r6, java.lang.Object r7) {
        /*
            r5 = this;
            r6.zzg()
            com.google.android.gms.internal.gtm.zzye r0 = r6.zzd()
            com.google.android.gms.internal.gtm.zzvi.zze(r7)
            com.google.android.gms.internal.gtm.zzye r1 = com.google.android.gms.internal.gtm.zzye.zza
            com.google.android.gms.internal.gtm.zzyf r1 = com.google.android.gms.internal.gtm.zzyf.INT
            com.google.android.gms.internal.gtm.zzyf r0 = r0.zza()
            int r0 = r0.ordinal()
            r1 = 1
            switch(r0) {
                case 0: goto L45;
                case 1: goto L42;
                case 2: goto L3f;
                case 3: goto L3c;
                case 4: goto L39;
                case 5: goto L36;
                case 6: goto L2d;
                case 7: goto L24;
                case 8: goto L1b;
                default: goto L1a;
            }
        L1a:
            goto L55
        L1b:
            boolean r0 = r7 instanceof com.google.android.gms.internal.gtm.zzwk
            if (r0 != 0) goto L49
            boolean r0 = r7 instanceof com.google.android.gms.internal.gtm.zzvp
            if (r0 == 0) goto L55
            goto L49
        L24:
            boolean r0 = r7 instanceof java.lang.Integer
            if (r0 != 0) goto L49
            boolean r0 = r7 instanceof com.google.android.gms.internal.gtm.zzvb
            if (r0 == 0) goto L55
            goto L49
        L2d:
            boolean r0 = r7 instanceof com.google.android.gms.internal.gtm.zztd
            if (r0 != 0) goto L49
            boolean r0 = r7 instanceof byte[]
            if (r0 == 0) goto L55
            goto L49
        L36:
            boolean r0 = r7 instanceof java.lang.String
            goto L47
        L39:
            boolean r0 = r7 instanceof java.lang.Boolean
            goto L47
        L3c:
            boolean r0 = r7 instanceof java.lang.Double
            goto L47
        L3f:
            boolean r0 = r7 instanceof java.lang.Float
            goto L47
        L42:
            boolean r0 = r7 instanceof java.lang.Long
            goto L47
        L45:
            boolean r0 = r7 instanceof java.lang.Integer
        L47:
            if (r0 == 0) goto L55
        L49:
            boolean r0 = r7 instanceof com.google.android.gms.internal.gtm.zzvp
            if (r0 == 0) goto L4f
            r5.zzd = r1
        L4f:
            com.google.android.gms.internal.gtm.zzxk<T extends com.google.android.gms.internal.gtm.zzun<T>, java.lang.Object> r0 = r5.zza
            r0.put(r6, r7)
            return
        L55:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            int r4 = r6.zza()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2[r3] = r4
            com.google.android.gms.internal.gtm.zzye r6 = r6.zzd()
            com.google.android.gms.internal.gtm.zzyf r6 = r6.zza()
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzuo.zzi(com.google.android.gms.internal.gtm.zzun, java.lang.Object):void");
    }

    public final boolean zzj() {
        return this.zzc;
    }

    public final boolean zzk() {
        for (int i = 0; i < this.zza.zzb(); i++) {
            if (!zzn(this.zza.zzg(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> entry : this.zza.zzc()) {
            if (!zzn(entry)) {
                return false;
            }
        }
        return true;
    }

    public final void zzm(Map.Entry<T, Object> entry) {
        zzwk zzC;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (!(value instanceof zzvp)) {
            key.zzg();
            if (key.zze() == zzyf.MESSAGE) {
                Object zze = zze(key);
                if (zze == null) {
                    this.zza.put(key, zzl(value));
                    return;
                }
                if (zze instanceof zzwp) {
                    zzC = key.zzc((zzwp) zze, (zzwp) value);
                } else {
                    zzwj zzap = ((zzwk) zze).zzap();
                    key.zzb(zzap, (zzwk) value);
                    zzC = zzap.zzC();
                }
                this.zza.put(key, zzC);
                return;
            }
            this.zza.put(key, zzl(value));
            return;
        }
        zzvp zzvpVar = (zzvp) value;
        throw null;
    }

    public zzuo(boolean z) {
        zzg();
        zzg();
    }
}
