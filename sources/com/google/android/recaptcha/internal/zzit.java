package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzit implements zzjc {
    private final zzip zza;
    private final zzjw zzb;
    private final boolean zzc;
    private final zzgr zzd;

    private zzit(zzjw zzjwVar, zzgr zzgrVar, zzip zzipVar) {
        this.zzb = zzjwVar;
        this.zzc = zzgrVar.zzj(zzipVar);
        this.zzd = zzgrVar;
        this.zza = zzipVar;
    }

    public static zzit zzc(zzjw zzjwVar, zzgr zzgrVar, zzip zzipVar) {
        return new zzit(zzjwVar, zzgrVar, zzipVar);
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final int zza(Object obj) {
        zzjw zzjwVar = this.zzb;
        int zzb = zzjwVar.zzb(zzjwVar.zzd(obj));
        return this.zzc ? zzb + this.zzd.zzb(obj).zzb() : zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzd(obj).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zzb(obj).zza.hashCode() : hashCode;
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final Object zze() {
        zzip zzipVar = this.zza;
        if (zzipVar instanceof zzhf) {
            return ((zzhf) zzipVar).zzs();
        }
        return zzipVar.zzV().zzk();
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final void zzf(Object obj) {
        this.zzb.zzm(obj);
        this.zzd.zzf(obj);
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final void zzg(Object obj, Object obj2) {
        zzje.zzD(this.zzb, obj, obj2);
        if (this.zzc) {
            zzje.zzC(this.zzd, obj, obj2);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final void zzh(Object obj, zzjb zzjbVar, zzgq zzgqVar) throws IOException {
        boolean zzO;
        zzjw zzjwVar = this.zzb;
        zzgr zzgrVar = this.zzd;
        Object zzc = zzjwVar.zzc(obj);
        zzgv zzc2 = zzgrVar.zzc(obj);
        while (zzjbVar.zzc() != Integer.MAX_VALUE) {
            try {
                int zzd = zzjbVar.zzd();
                if (zzd != 11) {
                    if ((zzd & 7) == 2) {
                        Object zzd2 = zzgrVar.zzd(zzgqVar, this.zza, zzd >>> 3);
                        if (zzd2 != null) {
                            zzgrVar.zzg(zzjbVar, zzd2, zzgqVar, zzc2);
                        } else {
                            zzO = zzjwVar.zzr(zzc, zzjbVar);
                        }
                    } else {
                        zzO = zzjbVar.zzO();
                    }
                    if (!zzO) {
                        return;
                    }
                } else {
                    int i = 0;
                    Object obj2 = null;
                    zzfi zzfiVar = null;
                    while (zzjbVar.zzc() != Integer.MAX_VALUE) {
                        int zzd3 = zzjbVar.zzd();
                        if (zzd3 == 16) {
                            i = zzjbVar.zzj();
                            obj2 = zzgrVar.zzd(zzgqVar, this.zza, i);
                        } else if (zzd3 == 26) {
                            if (obj2 != null) {
                                zzgrVar.zzg(zzjbVar, obj2, zzgqVar, zzc2);
                            } else {
                                zzfiVar = zzjbVar.zzp();
                            }
                        } else if (!zzjbVar.zzO()) {
                            break;
                        }
                    }
                    if (zzjbVar.zzd() != 12) {
                        throw zzhp.zzb();
                    } else if (zzfiVar != null) {
                        if (obj2 != null) {
                            zzgrVar.zzh(zzfiVar, obj2, zzgqVar, zzc2);
                        } else {
                            zzjwVar.zzk(zzc, i, zzfiVar);
                        }
                    }
                }
            } finally {
                zzjwVar.zzn(obj, zzc);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0088 A[EDGE_INSN: B:56:0x0088->B:34:0x0088 ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.recaptcha.internal.zzjc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzi(java.lang.Object r10, byte[] r11, int r12, int r13, com.google.android.recaptcha.internal.zzev r14) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r10
            com.google.android.recaptcha.internal.zzhf r0 = (com.google.android.recaptcha.internal.zzhf) r0
            com.google.android.recaptcha.internal.zzjx r1 = r0.zzc
            com.google.android.recaptcha.internal.zzjx r2 = com.google.android.recaptcha.internal.zzjx.zzc()
            if (r1 != r2) goto L11
            com.google.android.recaptcha.internal.zzjx r1 = com.google.android.recaptcha.internal.zzjx.zzf()
            r0.zzc = r1
        L11:
            com.google.android.recaptcha.internal.zzhb r10 = (com.google.android.recaptcha.internal.zzhb) r10
            r10.zzi()
            r10 = 0
            r0 = r10
        L18:
            if (r12 >= r13) goto L92
            int r4 = com.google.android.recaptcha.internal.zzew.zzj(r11, r12, r14)
            int r2 = r14.zza
            r12 = 11
            r3 = 2
            if (r2 == r12) goto L48
            r12 = r2 & 7
            if (r12 != r3) goto L43
            com.google.android.recaptcha.internal.zzgr r12 = r9.zzd
            com.google.android.recaptcha.internal.zzgq r0 = r14.zzd
            com.google.android.recaptcha.internal.zzip r3 = r9.zza
            int r5 = r2 >>> 3
            java.lang.Object r0 = r12.zzd(r0, r3, r5)
            if (r0 != 0) goto L40
            r3 = r11
            r5 = r13
            r6 = r1
            r7 = r14
            int r12 = com.google.android.recaptcha.internal.zzew.zzi(r2, r3, r4, r5, r6, r7)
            goto L18
        L40:
            int r11 = com.google.android.recaptcha.internal.zziy.zza
            throw r10
        L43:
            int r12 = com.google.android.recaptcha.internal.zzew.zzp(r2, r11, r4, r13, r14)
            goto L18
        L48:
            r12 = 0
            r2 = r10
        L4a:
            if (r4 >= r13) goto L88
            int r4 = com.google.android.recaptcha.internal.zzew.zzj(r11, r4, r14)
            int r5 = r14.zza
            int r6 = r5 >>> 3
            r7 = r5 & 7
            if (r6 == r3) goto L6c
            r8 = 3
            if (r6 == r8) goto L5c
            goto L7f
        L5c:
            if (r0 != 0) goto L69
            if (r7 != r3) goto L7f
            int r4 = com.google.android.recaptcha.internal.zzew.zza(r11, r4, r14)
            java.lang.Object r2 = r14.zzc
            com.google.android.recaptcha.internal.zzfi r2 = (com.google.android.recaptcha.internal.zzfi) r2
            goto L4a
        L69:
            int r11 = com.google.android.recaptcha.internal.zziy.zza
            throw r10
        L6c:
            if (r7 != 0) goto L7f
            int r4 = com.google.android.recaptcha.internal.zzew.zzj(r11, r4, r14)
            int r12 = r14.zza
            com.google.android.recaptcha.internal.zzgr r0 = r9.zzd
            com.google.android.recaptcha.internal.zzgq r5 = r14.zzd
            com.google.android.recaptcha.internal.zzip r6 = r9.zza
            java.lang.Object r0 = r0.zzd(r5, r6, r12)
            goto L4a
        L7f:
            r6 = 12
            if (r5 == r6) goto L88
            int r4 = com.google.android.recaptcha.internal.zzew.zzp(r5, r11, r4, r13, r14)
            goto L4a
        L88:
            if (r2 == 0) goto L90
            int r12 = r12 << 3
            r12 = r12 | r3
            r1.zzj(r12, r2)
        L90:
            r12 = r4
            goto L18
        L92:
            if (r12 != r13) goto L95
            return
        L95:
            com.google.android.recaptcha.internal.zzhp r10 = com.google.android.recaptcha.internal.zzhp.zzg()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzit.zzi(java.lang.Object, byte[], int, int, com.google.android.recaptcha.internal.zzev):void");
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final void zzj(Object obj, zzko zzkoVar) throws IOException {
        Iterator zzf = this.zzd.zzb(obj).zzf();
        while (zzf.hasNext()) {
            Map.Entry entry = (Map.Entry) zzf.next();
            zzgu zzguVar = (zzgu) entry.getKey();
            if (zzguVar.zze() != zzkn.MESSAGE) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            zzguVar.zzg();
            zzguVar.zzf();
            if (entry instanceof zzhs) {
                zzkoVar.zzw(zzguVar.zza(), ((zzhs) entry).zza().zzb());
            } else {
                zzkoVar.zzw(zzguVar.zza(), entry.getValue());
            }
        }
        zzjw zzjwVar = this.zzb;
        zzjwVar.zzp(zzjwVar.zzd(obj), zzkoVar);
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final boolean zzk(Object obj, Object obj2) {
        if (this.zzb.zzd(obj).equals(this.zzb.zzd(obj2))) {
            if (this.zzc) {
                return this.zzd.zzb(obj).equals(this.zzd.zzb(obj2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzjc
    public final boolean zzl(Object obj) {
        return this.zzd.zzb(obj).zzk();
    }
}
