package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzgs extends zzgr {
    @Override // com.google.android.recaptcha.internal.zzgr
    public final int zza(Map.Entry entry) {
        return ((zzhc) entry.getKey()).zza;
    }

    @Override // com.google.android.recaptcha.internal.zzgr
    public final zzgv zzb(Object obj) {
        return ((zzhb) obj).zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzgr
    public final zzgv zzc(Object obj) {
        return ((zzhb) obj).zzi();
    }

    @Override // com.google.android.recaptcha.internal.zzgr
    public final Object zzd(zzgq zzgqVar, zzip zzipVar, int i) {
        return zzgqVar.zza(zzipVar, i);
    }

    @Override // com.google.android.recaptcha.internal.zzgr
    public final Object zze(Object obj, zzjb zzjbVar, Object obj2, zzgq zzgqVar, zzgv zzgvVar, Object obj3, zzjw zzjwVar) throws IOException {
        Object zze;
        zzhd zzhdVar = (zzhd) obj2;
        zzkm zzkmVar = zzhdVar.zzb.zzb;
        zzip zzipVar = null;
        if (zzkmVar != zzkm.zzn) {
            switch (zzkmVar.ordinal()) {
                case 0:
                    zzipVar = Double.valueOf(zzjbVar.zza());
                    break;
                case 1:
                    zzipVar = Float.valueOf(zzjbVar.zzb());
                    break;
                case 2:
                    zzipVar = Long.valueOf(zzjbVar.zzl());
                    break;
                case 3:
                    zzipVar = Long.valueOf(zzjbVar.zzo());
                    break;
                case 4:
                    zzipVar = Integer.valueOf(zzjbVar.zzg());
                    break;
                case 5:
                    zzipVar = Long.valueOf(zzjbVar.zzk());
                    break;
                case 6:
                    zzipVar = Integer.valueOf(zzjbVar.zzf());
                    break;
                case 7:
                    zzipVar = Boolean.valueOf(zzjbVar.zzN());
                    break;
                case 8:
                    zzipVar = zzjbVar.zzr();
                    break;
                case 9:
                    Object zze2 = zzgvVar.zze(zzhdVar.zzb);
                    if (zze2 instanceof zzhf) {
                        zzjc zzb = zziy.zza().zzb(zze2.getClass());
                        if (!((zzhf) zze2).zzF()) {
                            Object zze3 = zzb.zze();
                            zzb.zzg(zze3, zze2);
                            zzgvVar.zzi(zzhdVar.zzb, zze3);
                            zze2 = zze3;
                        }
                        zzjbVar.zzt(zze2, zzb, zzgqVar);
                        return obj3;
                    }
                    throw null;
                case 10:
                    Object zze4 = zzgvVar.zze(zzhdVar.zzb);
                    if (zze4 instanceof zzhf) {
                        zzjc zzb2 = zziy.zza().zzb(zze4.getClass());
                        if (!((zzhf) zze4).zzF()) {
                            Object zze5 = zzb2.zze();
                            zzb2.zzg(zze5, zze4);
                            zzgvVar.zzi(zzhdVar.zzb, zze5);
                            zze4 = zze5;
                        }
                        zzjbVar.zzu(zze4, zzb2, zzgqVar);
                        return obj3;
                    }
                    throw null;
                case 11:
                    zzipVar = zzjbVar.zzp();
                    break;
                case 12:
                    zzipVar = Integer.valueOf(zzjbVar.zzj());
                    break;
                case 13:
                    throw new IllegalStateException("Shouldn't reach here.");
                case 14:
                    zzipVar = Integer.valueOf(zzjbVar.zzh());
                    break;
                case 15:
                    zzipVar = Long.valueOf(zzjbVar.zzm());
                    break;
                case 16:
                    zzipVar = Integer.valueOf(zzjbVar.zzi());
                    break;
                case 17:
                    zzipVar = Long.valueOf(zzjbVar.zzn());
                    break;
            }
            int ordinal = zzhdVar.zzb.zzb.ordinal();
            if ((ordinal == 9 || ordinal == 10) && (zze = zzgvVar.zze(zzhdVar.zzb)) != null) {
                byte[] bArr = zzhn.zzd;
                zzipVar = ((zzip) zze).zzW().zzc((zzip) zzipVar).zzk();
            }
            zzgvVar.zzi(zzhdVar.zzb, zzipVar);
            return obj3;
        }
        zzjbVar.zzg();
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzgr
    public final void zzf(Object obj) {
        ((zzhb) obj).zzb.zzg();
    }

    @Override // com.google.android.recaptcha.internal.zzgr
    public final void zzg(zzjb zzjbVar, Object obj, zzgq zzgqVar, zzgv zzgvVar) throws IOException {
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzgr
    public final void zzh(zzfi zzfiVar, Object obj, zzgq zzgqVar, zzgv zzgvVar) throws IOException {
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzgr
    public final void zzi(zzko zzkoVar, Map.Entry entry) throws IOException {
        zzhc zzhcVar = (zzhc) entry.getKey();
        zzkm zzkmVar = zzkm.zza;
        switch (zzhcVar.zzb.ordinal()) {
            case 0:
                zzkoVar.zzf(zzhcVar.zza, ((Double) entry.getValue()).doubleValue());
                return;
            case 1:
                zzkoVar.zzo(zzhcVar.zza, ((Float) entry.getValue()).floatValue());
                return;
            case 2:
                zzkoVar.zzt(zzhcVar.zza, ((Long) entry.getValue()).longValue());
                return;
            case 3:
                zzkoVar.zzK(zzhcVar.zza, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                zzkoVar.zzr(zzhcVar.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 5:
                zzkoVar.zzm(zzhcVar.zza, ((Long) entry.getValue()).longValue());
                return;
            case 6:
                zzkoVar.zzk(zzhcVar.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 7:
                zzkoVar.zzb(zzhcVar.zza, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 8:
                zzkoVar.zzG(zzhcVar.zza, (String) entry.getValue());
                return;
            case 9:
                zzkoVar.zzq(zzhcVar.zza, entry.getValue(), zziy.zza().zzb(entry.getValue().getClass()));
                return;
            case 10:
                zzkoVar.zzv(zzhcVar.zza, entry.getValue(), zziy.zza().zzb(entry.getValue().getClass()));
                return;
            case 11:
                zzkoVar.zzd(zzhcVar.zza, (zzfi) entry.getValue());
                return;
            case 12:
                zzkoVar.zzI(zzhcVar.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                zzkoVar.zzr(zzhcVar.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 14:
                zzkoVar.zzx(zzhcVar.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                zzkoVar.zzz(zzhcVar.zza, ((Long) entry.getValue()).longValue());
                return;
            case 16:
                zzkoVar.zzB(zzhcVar.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 17:
                zzkoVar.zzD(zzhcVar.zza, ((Long) entry.getValue()).longValue());
                return;
            default:
                return;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzgr
    public final boolean zzj(zzip zzipVar) {
        return zzipVar instanceof zzhb;
    }
}
