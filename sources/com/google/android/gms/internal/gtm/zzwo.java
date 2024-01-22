package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzwo<T> implements zzwx<T> {
    public final zzwk zza;
    public final zzxo<?, ?> zzb;
    public final boolean zzc;
    public final zzuk<?> zzd;

    public zzwo(zzxo<?, ?> zzxoVar, zzuk<?> zzukVar, zzwk zzwkVar) {
        this.zzb = zzxoVar;
        this.zzc = zzukVar.zzi(zzwkVar);
        this.zzd = zzukVar;
        this.zza = zzwkVar;
    }

    public static <T> zzwo<T> zzc(zzxo<?, ?> zzxoVar, zzuk<?> zzukVar, zzwk zzwkVar) {
        return new zzwo<>(zzxoVar, zzukVar, zzwkVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzwx
    public final int zza(T t) {
        zzxo<?, ?> zzxoVar = this.zzb;
        int zzb = zzxoVar.zzb(zzxoVar.zzd(t));
        return this.zzc ? zzb + this.zzd.zzb(t).zzb() : zzb;
    }

    @Override // com.google.android.gms.internal.gtm.zzwx
    public final int zzb(T t) {
        int hashCode = this.zzb.zzd(t).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zzb(t).zza.hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.gtm.zzwx
    public final T zze() {
        return (T) this.zza.zzao().zzD();
    }

    @Override // com.google.android.gms.internal.gtm.zzwx
    public final void zzf(T t) {
        this.zzb.zzm(t);
        this.zzd.zzf(t);
    }

    @Override // com.google.android.gms.internal.gtm.zzwx
    public final void zzg(T t, T t2) {
        zzwz.zzF(this.zzb, t, t2);
        if (this.zzc) {
            zzwz.zzE(this.zzd, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzwx
    public final void zzh(T t, zzww zzwwVar, zzuj zzujVar) throws IOException {
        boolean zzT;
        zzxo<?, ?> zzxoVar = this.zzb;
        zzuk<?> zzukVar = this.zzd;
        Object zzc = zzxoVar.zzc(t);
        zzuo<?> zzc2 = zzukVar.zzc(t);
        while (zzwwVar.zzc() != Integer.MAX_VALUE) {
            try {
                int zzd = zzwwVar.zzd();
                if (zzd != 11) {
                    if ((zzd & 7) == 2) {
                        Object zzd2 = zzukVar.zzd(zzujVar, this.zza, zzd >>> 3);
                        if (zzd2 != null) {
                            zzukVar.zzg(zzwwVar, zzd2, zzujVar, zzc2);
                        } else {
                            zzT = zzxoVar.zzp(zzc, zzwwVar);
                        }
                    } else {
                        zzT = zzwwVar.zzT();
                    }
                    if (!zzT) {
                        return;
                    }
                } else {
                    int i = 0;
                    Object obj = null;
                    zztd zztdVar = null;
                    while (zzwwVar.zzc() != Integer.MAX_VALUE) {
                        int zzd3 = zzwwVar.zzd();
                        if (zzd3 == 16) {
                            i = zzwwVar.zzj();
                            obj = zzukVar.zzd(zzujVar, this.zza, i);
                        } else if (zzd3 == 26) {
                            if (obj != null) {
                                zzukVar.zzg(zzwwVar, obj, zzujVar, zzc2);
                            } else {
                                zztdVar = zzwwVar.zzq();
                            }
                        } else if (!zzwwVar.zzT()) {
                            break;
                        }
                    }
                    if (zzwwVar.zzd() != 12) {
                        throw zzvk.zzb();
                    } else if (zztdVar != null) {
                        if (obj != null) {
                            zzukVar.zzh(zztdVar, obj, zzujVar, zzc2);
                        } else {
                            zzxoVar.zzk(zzc, i, zztdVar);
                        }
                    }
                }
            } finally {
                zzxoVar.zzn(t, zzc);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00bf A[EDGE_INSN: B:56:0x00bf->B:33:0x00bf ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.gms.internal.gtm.zzwx
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzi(T r11, byte[] r12, int r13, int r14, com.google.android.gms.internal.gtm.zzsl r15) throws java.io.IOException {
        /*
            r10 = this;
            r0 = r11
            com.google.android.gms.internal.gtm.zzuz r0 = (com.google.android.gms.internal.gtm.zzuz) r0
            com.google.android.gms.internal.gtm.zzxp r1 = r0.zzc
            com.google.android.gms.internal.gtm.zzxp r2 = com.google.android.gms.internal.gtm.zzxp.zzc()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.gtm.zzxp r1 = com.google.android.gms.internal.gtm.zzxp.zze()
            r0.zzc = r1
        L11:
            com.google.android.gms.internal.gtm.zzuv r11 = (com.google.android.gms.internal.gtm.zzuv) r11
            com.google.android.gms.internal.gtm.zzuo r11 = r11.zzU()
            r0 = 0
            r2 = r0
        L19:
            if (r13 >= r14) goto Lca
            int r4 = com.google.android.gms.internal.gtm.zzsm.zzj(r12, r13, r15)
            int r13 = r15.zza
            r3 = 11
            r5 = 2
            if (r13 == r3) goto L65
            r3 = r13 & 7
            if (r3 != r5) goto L60
            com.google.android.gms.internal.gtm.zzuk<?> r2 = r10.zzd
            com.google.android.gms.internal.gtm.zzuj r3 = r15.zzd
            com.google.android.gms.internal.gtm.zzwk r5 = r10.zza
            int r6 = r13 >>> 3
            java.lang.Object r8 = r2.zzd(r3, r5, r6)
            if (r8 == 0) goto L55
            com.google.android.gms.internal.gtm.zzwt r13 = com.google.android.gms.internal.gtm.zzwt.zza()
            r2 = r8
            com.google.android.gms.internal.gtm.zzux r2 = (com.google.android.gms.internal.gtm.zzux) r2
            com.google.android.gms.internal.gtm.zzwk r3 = r2.zzc
            java.lang.Class r3 = r3.getClass()
            com.google.android.gms.internal.gtm.zzwx r13 = r13.zzb(r3)
            int r13 = com.google.android.gms.internal.gtm.zzsm.zzd(r13, r12, r4, r14, r15)
            com.google.android.gms.internal.gtm.zzuw r2 = r2.zzd
            java.lang.Object r3 = r15.zzc
            r11.zzi(r2, r3)
            goto L5e
        L55:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.android.gms.internal.gtm.zzsm.zzi(r2, r3, r4, r5, r6, r7)
        L5e:
            r2 = r8
            goto L19
        L60:
            int r13 = com.google.android.gms.internal.gtm.zzsm.zzn(r13, r12, r4, r14, r15)
            goto L19
        L65:
            r13 = 0
            r3 = r0
        L67:
            if (r4 >= r14) goto Lbf
            int r4 = com.google.android.gms.internal.gtm.zzsm.zzj(r12, r4, r15)
            int r6 = r15.zza
            r7 = r6 & 7
            int r8 = r6 >>> 3
            if (r8 == r5) goto La3
            r9 = 3
            if (r8 == r9) goto L79
            goto Lb6
        L79:
            if (r2 == 0) goto L98
            com.google.android.gms.internal.gtm.zzwt r6 = com.google.android.gms.internal.gtm.zzwt.zza()
            r7 = r2
            com.google.android.gms.internal.gtm.zzux r7 = (com.google.android.gms.internal.gtm.zzux) r7
            com.google.android.gms.internal.gtm.zzwk r8 = r7.zzc
            java.lang.Class r8 = r8.getClass()
            com.google.android.gms.internal.gtm.zzwx r6 = r6.zzb(r8)
            int r4 = com.google.android.gms.internal.gtm.zzsm.zzd(r6, r12, r4, r14, r15)
            com.google.android.gms.internal.gtm.zzuw r6 = r7.zzd
            java.lang.Object r7 = r15.zzc
            r11.zzi(r6, r7)
            goto L67
        L98:
            if (r7 != r5) goto Lb6
            int r4 = com.google.android.gms.internal.gtm.zzsm.zza(r12, r4, r15)
            java.lang.Object r3 = r15.zzc
            com.google.android.gms.internal.gtm.zztd r3 = (com.google.android.gms.internal.gtm.zztd) r3
            goto L67
        La3:
            if (r7 != 0) goto Lb6
            int r4 = com.google.android.gms.internal.gtm.zzsm.zzj(r12, r4, r15)
            int r13 = r15.zza
            com.google.android.gms.internal.gtm.zzuk<?> r2 = r10.zzd
            com.google.android.gms.internal.gtm.zzuj r6 = r15.zzd
            com.google.android.gms.internal.gtm.zzwk r7 = r10.zza
            java.lang.Object r2 = r2.zzd(r6, r7, r13)
            goto L67
        Lb6:
            r7 = 12
            if (r6 == r7) goto Lbf
            int r4 = com.google.android.gms.internal.gtm.zzsm.zzn(r6, r12, r4, r14, r15)
            goto L67
        Lbf:
            if (r3 == 0) goto Lc7
            int r13 = r13 << 3
            r13 = r13 | r5
            r1.zzh(r13, r3)
        Lc7:
            r13 = r4
            goto L19
        Lca:
            if (r13 != r14) goto Lcd
            return
        Lcd:
            com.google.android.gms.internal.gtm.zzvk r11 = com.google.android.gms.internal.gtm.zzvk.zzg()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzwo.zzi(java.lang.Object, byte[], int, int, com.google.android.gms.internal.gtm.zzsl):void");
    }

    @Override // com.google.android.gms.internal.gtm.zzwx
    public final boolean zzj(T t, T t2) {
        if (this.zzb.zzd(t).equals(this.zzb.zzd(t2))) {
            if (this.zzc) {
                return this.zzd.zzb(t).equals(this.zzd.zzb(t2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.gtm.zzwx
    public final boolean zzk(T t) {
        return this.zzd.zzb(t).zzk();
    }

    @Override // com.google.android.gms.internal.gtm.zzwx
    public final void zzn(T t, zztp zztpVar) throws IOException {
        Iterator<Map.Entry<?, Object>> zzf = this.zzd.zzb(t).zzf();
        while (zzf.hasNext()) {
            Map.Entry<?, Object> next = zzf.next();
            zzun zzunVar = (zzun) next.getKey();
            if (zzunVar.zze() == zzyf.MESSAGE) {
                zzunVar.zzg();
                zzunVar.zzf();
                if (next instanceof zzvn) {
                    zztpVar.zzw(zzunVar.zza(), ((zzvn) next).zza().zzb());
                } else {
                    zztpVar.zzw(zzunVar.zza(), next.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        zzxo<?, ?> zzxoVar = this.zzb;
        zzxoVar.zzr(zzxoVar.zzd(t), zztpVar);
    }
}
