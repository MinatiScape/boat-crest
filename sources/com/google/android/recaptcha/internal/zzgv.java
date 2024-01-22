package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzgv {
    private static final zzgv zzb = new zzgv(true);
    public final zzjp zza = new zzjf(16);
    private boolean zzc;
    private boolean zzd;

    private zzgv() {
    }

    public static int zza(zzgu zzguVar, Object obj) {
        int zzd;
        int zzy;
        zzkm zzd2 = zzguVar.zzd();
        int zza = zzguVar.zza();
        zzguVar.zzg();
        int i = zzft.zzb;
        int zzy2 = zzft.zzy(zza << 3);
        if (zzd2 == zzkm.zzj) {
            zzip zzipVar = (zzip) obj;
            byte[] bArr = zzhn.zzd;
            if (zzipVar instanceof zzes) {
                zzes zzesVar = (zzes) zzipVar;
                throw null;
            }
            zzy2 += zzy2;
        }
        zzkn zzknVar = zzkn.INT;
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
                i2 = zzft.zzz(((Long) obj).longValue());
                break;
            case 3:
                i2 = zzft.zzz(((Long) obj).longValue());
                break;
            case 4:
                i2 = zzft.zzu(((Integer) obj).intValue());
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
                if (obj instanceof zzfi) {
                    zzd = ((zzfi) obj).zzd();
                    zzy = zzft.zzy(zzd);
                    i2 = zzy + zzd;
                    break;
                } else {
                    i2 = zzft.zzx((String) obj);
                    break;
                }
            case 9:
                i2 = ((zzip) obj).zzn();
                break;
            case 10:
                if (obj instanceof zzhu) {
                    zzd = ((zzhu) obj).zza();
                    zzy = zzft.zzy(zzd);
                    i2 = zzy + zzd;
                    break;
                } else {
                    i2 = zzft.zzv((zzip) obj);
                    break;
                }
            case 11:
                if (obj instanceof zzfi) {
                    zzd = ((zzfi) obj).zzd();
                    zzy = zzft.zzy(zzd);
                } else {
                    zzd = ((byte[]) obj).length;
                    zzy = zzft.zzy(zzd);
                }
                i2 = zzy + zzd;
                break;
            case 12:
                i2 = zzft.zzy(((Integer) obj).intValue());
                break;
            case 13:
                if (obj instanceof zzhh) {
                    i2 = zzft.zzu(((zzhh) obj).zza());
                    break;
                } else {
                    i2 = zzft.zzu(((Integer) obj).intValue());
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
                i2 = zzft.zzy((intValue >> 31) ^ (intValue + intValue));
                break;
            case 17:
                long longValue = ((Long) obj).longValue();
                i2 = zzft.zzz((longValue >> 63) ^ (longValue + longValue));
                break;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return zzy2 + i2;
    }

    public static zzgv zzd() {
        return zzb;
    }

    private static Object zzl(Object obj) {
        if (obj instanceof zziu) {
            return ((zziu) obj).zzd();
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

    private final void zzm(Map.Entry entry) {
        zzip zzj;
        zzgu zzguVar = (zzgu) entry.getKey();
        Object value = entry.getValue();
        if (!(value instanceof zzhu)) {
            zzguVar.zzg();
            if (zzguVar.zze() == zzkn.MESSAGE) {
                Object zze = zze(zzguVar);
                if (zze == null) {
                    this.zza.put(zzguVar, zzl(value));
                    return;
                }
                if (zze instanceof zziu) {
                    zzj = zzguVar.zzc((zziu) zze, (zziu) value);
                } else {
                    zzio zzW = ((zzip) zze).zzW();
                    zzguVar.zzb(zzW, (zzip) value);
                    zzj = zzW.zzj();
                }
                this.zza.put(zzguVar, zzj);
                return;
            }
            this.zza.put(zzguVar, zzl(value));
            return;
        }
        zzhu zzhuVar = (zzhu) value;
        throw null;
    }

    private static boolean zzn(Map.Entry entry) {
        zzgu zzguVar = (zzgu) entry.getKey();
        if (zzguVar.zze() == zzkn.MESSAGE) {
            zzguVar.zzg();
            Object value = entry.getValue();
            if (value instanceof zziq) {
                return ((zziq) value).zzo();
            }
            if (value instanceof zzhu) {
                return true;
            }
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        return true;
    }

    private static final int zzo(Map.Entry entry) {
        zzgu zzguVar = (zzgu) entry.getKey();
        Object value = entry.getValue();
        if (zzguVar.zze() == zzkn.MESSAGE) {
            zzguVar.zzg();
            zzguVar.zzf();
            if (value instanceof zzhu) {
                int zzy = zzft.zzy(((zzgu) entry.getKey()).zza());
                int zza = ((zzhu) value).zza();
                int zzy2 = zzft.zzy(zza) + zza;
                int zzy3 = zzft.zzy(24);
                int zzy4 = zzft.zzy(16);
                int zzy5 = zzft.zzy(8);
                return zzy5 + zzy5 + zzy4 + zzy + zzy3 + zzy2;
            }
            int zzy6 = zzft.zzy(((zzgu) entry.getKey()).zza());
            int zzy7 = zzft.zzy(24) + zzft.zzv((zzip) value);
            int zzy8 = zzft.zzy(16);
            int zzy9 = zzft.zzy(8);
            return zzy9 + zzy9 + zzy8 + zzy6 + zzy7;
        }
        return zza(zzguVar, value);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzgv) {
            return this.zza.equals(((zzgv) obj).zza);
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
        for (Map.Entry entry : this.zza.zzc()) {
            i += zzo(entry);
        }
        return i;
    }

    /* renamed from: zzc */
    public final zzgv clone() {
        zzgv zzgvVar = new zzgv();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzgvVar.zzi((zzgu) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzgvVar.zzi((zzgu) entry.getKey(), entry.getValue());
        }
        zzgvVar.zzd = this.zzd;
        return zzgvVar;
    }

    public final Object zze(zzgu zzguVar) {
        Object obj = this.zza.get(zzguVar);
        if (obj instanceof zzhu) {
            zzhu zzhuVar = (zzhu) obj;
            throw null;
        }
        return obj;
    }

    public final Iterator zzf() {
        return this.zzd ? new zzht(this.zza.entrySet().iterator()) : this.zza.entrySet().iterator();
    }

    public final void zzg() {
        if (this.zzc) {
            return;
        }
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            if (zzg.getValue() instanceof zzhf) {
                ((zzhf) zzg.getValue()).zzA();
            }
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzh(zzgv zzgvVar) {
        for (int i = 0; i < zzgvVar.zza.zzb(); i++) {
            zzm(zzgvVar.zza.zzg(i));
        }
        for (Map.Entry entry : zzgvVar.zza.zzc()) {
            zzm(entry);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
        if ((r7 instanceof com.google.android.recaptcha.internal.zzhh) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0035, code lost:
        if ((r7 instanceof byte[]) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r0 == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0023, code lost:
        if ((r7 instanceof com.google.android.recaptcha.internal.zzhu) == false) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzi(com.google.android.recaptcha.internal.zzgu r6, java.lang.Object r7) {
        /*
            r5 = this;
            r6.zzg()
            com.google.android.recaptcha.internal.zzkm r0 = r6.zzd()
            byte[] r1 = com.google.android.recaptcha.internal.zzhn.zzd
            java.util.Objects.requireNonNull(r7)
            com.google.android.recaptcha.internal.zzkm r1 = com.google.android.recaptcha.internal.zzkm.zza
            com.google.android.recaptcha.internal.zzkn r1 = com.google.android.recaptcha.internal.zzkn.INT
            com.google.android.recaptcha.internal.zzkn r0 = r0.zza()
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
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzip
            if (r0 != 0) goto L4b
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzhu
            if (r0 == 0) goto L57
            goto L4b
        L26:
            boolean r0 = r7 instanceof java.lang.Integer
            if (r0 != 0) goto L4b
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzhh
            if (r0 == 0) goto L57
            goto L4b
        L2f:
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzfi
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
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzhu
            if (r0 == 0) goto L51
            r5.zzd = r1
        L51:
            com.google.android.recaptcha.internal.zzjp r0 = r5.zza
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
            com.google.android.recaptcha.internal.zzkm r6 = r6.zzd()
            com.google.android.recaptcha.internal.zzkn r6 = r6.zza()
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzgv.zzi(com.google.android.recaptcha.internal.zzgu, java.lang.Object):void");
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
        for (Map.Entry entry : this.zza.zzc()) {
            if (!zzn(entry)) {
                return false;
            }
        }
        return true;
    }

    private zzgv(boolean z) {
        zzg();
        zzg();
    }
}
