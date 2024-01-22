package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzul extends zzuk<zzuw> {
    @Override // com.google.android.gms.internal.gtm.zzuk
    public final int zza(Map.Entry<?, ?> entry) {
        return ((zzuw) entry.getKey()).zzb;
    }

    @Override // com.google.android.gms.internal.gtm.zzuk
    public final zzuo<zzuw> zzb(Object obj) {
        return ((zzuv) obj).zza;
    }

    @Override // com.google.android.gms.internal.gtm.zzuk
    public final zzuo<zzuw> zzc(Object obj) {
        return ((zzuv) obj).zzU();
    }

    @Override // com.google.android.gms.internal.gtm.zzuk
    public final Object zzd(zzuj zzujVar, zzwk zzwkVar, int i) {
        return zzujVar.zzc(zzwkVar, i);
    }

    @Override // com.google.android.gms.internal.gtm.zzuk
    public final <UT, UB> UB zze(zzww zzwwVar, Object obj, zzuj zzujVar, zzuo<zzuw> zzuoVar, UB ub, zzxo<UT, UB> zzxoVar) throws IOException {
        Object valueOf;
        Object zze;
        zzux zzuxVar = (zzux) obj;
        zzuw zzuwVar = zzuxVar.zzd;
        int i = zzuwVar.zzb;
        zzye zzyeVar = zzuwVar.zzc;
        if (zzyeVar == zzye.zzn) {
            int zzg = zzwwVar.zzg();
            zzvc<?> zzvcVar = zzuxVar.zzd.zza;
            if (zzyl.zzc(zzg) == null) {
                return (UB) zzwz.zzD(i, zzg, ub, zzxoVar);
            }
            valueOf = Integer.valueOf(zzg);
        } else {
            switch (zzyeVar.ordinal()) {
                case 0:
                    valueOf = Double.valueOf(zzwwVar.zza());
                    break;
                case 1:
                    valueOf = Float.valueOf(zzwwVar.zzb());
                    break;
                case 2:
                    valueOf = Long.valueOf(zzwwVar.zzl());
                    break;
                case 3:
                    valueOf = Long.valueOf(zzwwVar.zzo());
                    break;
                case 4:
                    valueOf = Integer.valueOf(zzwwVar.zzg());
                    break;
                case 5:
                    valueOf = Long.valueOf(zzwwVar.zzk());
                    break;
                case 6:
                    valueOf = Integer.valueOf(zzwwVar.zzf());
                    break;
                case 7:
                    valueOf = Boolean.valueOf(zzwwVar.zzS());
                    break;
                case 8:
                    valueOf = zzwwVar.zzv();
                    break;
                case 9:
                    valueOf = zzwwVar.zzr(zzuxVar.zzc.getClass(), zzujVar);
                    break;
                case 10:
                    valueOf = zzwwVar.zzt(zzuxVar.zzc.getClass(), zzujVar);
                    break;
                case 11:
                    valueOf = zzwwVar.zzq();
                    break;
                case 12:
                    valueOf = Integer.valueOf(zzwwVar.zzj());
                    break;
                case 13:
                    throw new IllegalStateException("Shouldn't reach here.");
                case 14:
                    valueOf = Integer.valueOf(zzwwVar.zzh());
                    break;
                case 15:
                    valueOf = Long.valueOf(zzwwVar.zzm());
                    break;
                case 16:
                    valueOf = Integer.valueOf(zzwwVar.zzi());
                    break;
                case 17:
                    valueOf = Long.valueOf(zzwwVar.zzn());
                    break;
                default:
                    valueOf = null;
                    break;
            }
        }
        zzuxVar.zza();
        int ordinal = zzuxVar.zzd.zzc.ordinal();
        if ((ordinal == 9 || ordinal == 10) && (zze = zzuoVar.zze(zzuxVar.zzd)) != null) {
            valueOf = zzvi.zzg(zze, valueOf);
        }
        zzuoVar.zzi(zzuxVar.zzd, valueOf);
        return ub;
    }

    @Override // com.google.android.gms.internal.gtm.zzuk
    public final void zzf(Object obj) {
        ((zzuv) obj).zza.zzg();
    }

    @Override // com.google.android.gms.internal.gtm.zzuk
    public final void zzg(zzww zzwwVar, Object obj, zzuj zzujVar, zzuo<zzuw> zzuoVar) throws IOException {
        zzux zzuxVar = (zzux) obj;
        zzuoVar.zzi(zzuxVar.zzd, zzwwVar.zzt(zzuxVar.zzc.getClass(), zzujVar));
    }

    @Override // com.google.android.gms.internal.gtm.zzuk
    public final void zzh(zztd zztdVar, Object obj, zzuj zzujVar, zzuo<zzuw> zzuoVar) throws IOException {
        byte[] bArr;
        zzux zzuxVar = (zzux) obj;
        zzwk zzD = zzuxVar.zzc.zzao().zzD();
        int zzd = zztdVar.zzd();
        if (zzd == 0) {
            bArr = zzvi.zzc;
        } else {
            byte[] bArr2 = new byte[zzd];
            zztdVar.zze(bArr2, 0, 0, zzd);
            bArr = bArr2;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (wrap.hasArray()) {
            zzsn zzsnVar = new zzsn(wrap, true);
            zzwt.zza().zzb(zzD.getClass()).zzh(zzD, zzsnVar, zzujVar);
            zzuoVar.zzi(zzuxVar.zzd, zzD);
            if (zzsnVar.zzc() != Integer.MAX_VALUE) {
                throw zzvk.zzb();
            }
            return;
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }

    @Override // com.google.android.gms.internal.gtm.zzuk
    public final boolean zzi(zzwk zzwkVar) {
        return zzwkVar instanceof zzuv;
    }

    @Override // com.google.android.gms.internal.gtm.zzuk
    public final void zzj(zztp zztpVar, Map.Entry<?, ?> entry) throws IOException {
        zzuw zzuwVar = (zzuw) entry.getKey();
        zzye zzyeVar = zzye.zza;
        switch (zzuwVar.zzc.ordinal()) {
            case 0:
                zztpVar.zzf(zzuwVar.zzb, ((Double) entry.getValue()).doubleValue());
                return;
            case 1:
                zztpVar.zzo(zzuwVar.zzb, ((Float) entry.getValue()).floatValue());
                return;
            case 2:
                zztpVar.zzt(zzuwVar.zzb, ((Long) entry.getValue()).longValue());
                return;
            case 3:
                zztpVar.zzK(zzuwVar.zzb, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                zztpVar.zzr(zzuwVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 5:
                zztpVar.zzm(zzuwVar.zzb, ((Long) entry.getValue()).longValue());
                return;
            case 6:
                zztpVar.zzk(zzuwVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 7:
                zztpVar.zzb(zzuwVar.zzb, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 8:
                zztpVar.zzG(zzuwVar.zzb, (String) entry.getValue());
                return;
            case 9:
                zztpVar.zzq(zzuwVar.zzb, entry.getValue(), zzwt.zza().zzb(entry.getValue().getClass()));
                return;
            case 10:
                zztpVar.zzv(zzuwVar.zzb, entry.getValue(), zzwt.zza().zzb(entry.getValue().getClass()));
                return;
            case 11:
                zztpVar.zzd(zzuwVar.zzb, (zztd) entry.getValue());
                return;
            case 12:
                zztpVar.zzI(zzuwVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                zztpVar.zzr(zzuwVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 14:
                zztpVar.zzx(zzuwVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                zztpVar.zzz(zzuwVar.zzb, ((Long) entry.getValue()).longValue());
                return;
            case 16:
                zztpVar.zzB(zzuwVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 17:
                zztpVar.zzD(zzuwVar.zzb, ((Long) entry.getValue()).longValue());
                return;
            default:
                return;
        }
    }
}
