package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jose4j.jwk.JsonWebKeySet;
/* loaded from: classes8.dex */
public final class zzsa {
    public static zzak zza(zzak zzakVar) {
        zzal zzg = zzak.zzg();
        zzg.zzt(1);
        zzg.zzt(zzakVar.zzO());
        zzg.zzi();
        zzg.zza(zzakVar.zzq());
        zzg.zzo(zzakVar.zzN());
        return (zzak) zzg.zzC();
    }

    public static zzrs zzb(zzaa zzaaVar) throws zzrz {
        zzak[] zzakVarArr = new zzak[zzaaVar.zzf()];
        for (int i = 0; i < zzaaVar.zzf(); i++) {
            zze(i, zzaaVar, zzakVarArr, new HashSet(0));
        }
        zzru zzruVar = new zzru(null);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < zzaaVar.zze(); i2++) {
            arrayList.add(zzf(zzaaVar.zzi(i2), zzaaVar, zzakVarArr, i2));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < zzaaVar.zzc(); i3++) {
            arrayList2.add(zzf(zzaaVar.zzh(i3), zzaaVar, zzakVarArr, i3));
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < zzaaVar.zza(); i4++) {
            zzro zzf = zzf(zzaaVar.zzg(i4), zzaaVar, zzakVarArr, i4);
            zzruVar.zzb(zzf);
            arrayList3.add(zzf);
        }
        for (zzac zzacVar : zzaaVar.zzr()) {
            zzry zzryVar = new zzry(null);
            for (Integer num : zzacVar.zzh()) {
                zzryVar.zzg((zzro) arrayList2.get(num.intValue()));
            }
            for (Integer num2 : zzacVar.zzg()) {
                zzryVar.zzf((zzro) arrayList2.get(num2.intValue()));
            }
            for (Integer num3 : zzacVar.zze()) {
                zzryVar.zzd((zzro) arrayList.get(num3.intValue()));
            }
            for (Integer num4 : zzacVar.zzf()) {
                zzryVar.zze(zzaaVar.zzn(num4.intValue()).zzp());
            }
            for (Integer num5 : zzacVar.zzk()) {
                zzryVar.zzj((zzro) arrayList.get(num5.intValue()));
            }
            for (Integer num6 : zzacVar.zzl()) {
                zzryVar.zzk(zzaaVar.zzn(num6.intValue()).zzp());
            }
            for (Integer num7 : zzacVar.zzc()) {
                zzryVar.zzb((zzro) arrayList3.get(num7.intValue()));
            }
            for (Integer num8 : zzacVar.zzd()) {
                zzryVar.zzc(zzaaVar.zzn(num8.intValue()).zzp());
            }
            for (Integer num9 : zzacVar.zzi()) {
                zzryVar.zzh((zzro) arrayList3.get(num9.intValue()));
            }
            for (Integer num10 : zzacVar.zzj()) {
                zzryVar.zzi(zzaaVar.zzn(num10.intValue()).zzp());
            }
            zzruVar.zzc(zzryVar.zza());
        }
        zzruVar.zze(zzaaVar.zzo());
        zzruVar.zzd(zzaaVar.zzd());
        return zzruVar.zza();
    }

    public static void zzc(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    public static zzae zzd(zzak zzakVar) throws zzrz {
        zzux<zzak, zzae> zzuxVar = zzae.zza;
        if (!zzakVar.zzW(zzuxVar)) {
            String valueOf = String.valueOf(zzakVar);
            StringBuilder sb = new StringBuilder(valueOf.length() + 54);
            sb.append("Expected a ServingValue and didn't get one. Value is: ");
            sb.append(valueOf);
            zzh(sb.toString());
        }
        return (zzae) zzakVar.zzV(zzuxVar);
    }

    public static zzak zze(int i, zzaa zzaaVar, zzak[] zzakVarArr, Set<Integer> set) throws zzrz {
        zzal zzalVar;
        Integer valueOf = Integer.valueOf(i);
        if (set.contains(valueOf)) {
            String valueOf2 = String.valueOf(set);
            StringBuilder sb = new StringBuilder(valueOf2.length() + 90);
            sb.append("Value cycle detected.  Current value reference: ");
            sb.append(i);
            sb.append(".  Previous value references: ");
            sb.append(valueOf2);
            sb.append(".");
            zzh(sb.toString());
        }
        zzal zzalVar2 = (zzal) ((zzak) zzg(zzaaVar.zzs(), i, "values")).zzZ();
        zzak zzakVar = zzakVarArr[i];
        if (zzakVar != null) {
            return zzakVar;
        }
        set.add(valueOf);
        int zzu = zzalVar2.zzu();
        if (zzu != 2) {
            if (zzu == 3) {
                zzalVar = (zzal) zza((zzak) zzalVar2.zzC()).zzZ();
                zzae zzd = zzd((zzak) zzalVar2.zzC());
                if (zzd.zzc() != zzd.zzd()) {
                    int zzc = zzd.zzc();
                    int zzd2 = zzd.zzd();
                    StringBuilder sb2 = new StringBuilder(58);
                    sb2.append("Uneven map keys (");
                    sb2.append(zzc);
                    sb2.append(") and map values (");
                    sb2.append(zzd2);
                    sb2.append(")");
                    zzh(sb2.toString());
                }
                zzalVar.zzk();
                zzalVar.zzl();
                for (Integer num : zzd.zzg()) {
                    zzalVar.zzf(zze(num.intValue(), zzaaVar, zzakVarArr, set));
                }
                for (Integer num2 : zzd.zzh()) {
                    zzalVar.zzg(zze(num2.intValue(), zzaaVar, zzakVarArr, set));
                }
            } else if (zzu == 4) {
                zzalVar = (zzal) zza((zzak) zzalVar2.zzC()).zzZ();
                zzalVar.zzr(com.google.android.gms.tagmanager.zzfv.zzn(com.google.android.gms.tagmanager.zzfv.zzl(zze(zzd((zzak) zzalVar2.zzC()).zza(), zzaaVar, zzakVarArr, set))));
            } else if (zzu == 7) {
                zzalVar = (zzal) zza((zzak) zzalVar2.zzC()).zzZ();
                zzae zzd3 = zzd((zzak) zzalVar2.zzC());
                zzalVar.zzm();
                for (Integer num3 : zzd3.zzi()) {
                    zzalVar.zzh(zze(num3.intValue(), zzaaVar, zzakVarArr, set));
                }
            }
            zzalVar2 = zzalVar;
        } else {
            zzae zzd4 = zzd((zzak) zzalVar2.zzC());
            zzalVar2 = (zzal) zza((zzak) zzalVar2.zzC()).zzZ();
            zzalVar2.zzj();
            for (Integer num4 : zzd4.zzf()) {
                zzalVar2.zze(zze(num4.intValue(), zzaaVar, zzakVarArr, set));
            }
        }
        zzakVarArr[i] = (zzak) zzalVar2.zzC();
        set.remove(Integer.valueOf(i));
        return (zzak) zzalVar2.zzC();
    }

    public static zzro zzf(zzs zzsVar, zzaa zzaaVar, zzak[] zzakVarArr, int i) throws zzrz {
        zzrq zzrqVar = new zzrq(null);
        for (Integer num : zzsVar.zzc()) {
            zzy zzyVar = (zzy) zzg(zzaaVar.zzq(), num.intValue(), "properties");
            String str = (String) zzg(zzaaVar.zzp(), zzyVar.zza(), JsonWebKeySet.JWK_SET_MEMBER_NAME);
            int zzc = zzyVar.zzc();
            if (zzc < 0 || zzc >= zzakVarArr.length) {
                StringBuilder sb = new StringBuilder(51);
                sb.append("Index out of bounds detected: ");
                sb.append(zzc);
                sb.append(" in values");
                zzh(sb.toString());
            }
            zzak zzakVar = zzakVarArr[zzc];
            if (zzb.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzrqVar.zzc(zzakVar);
            } else {
                zzrqVar.zzb(str, zzakVar);
            }
        }
        return zzrqVar.zza();
    }

    public static <T> T zzg(List<T> list, int i, String str) throws zzrz {
        if (i < 0 || i >= list.size()) {
            StringBuilder sb = new StringBuilder(str.length() + 45);
            sb.append("Index out of bounds detected: ");
            sb.append(i);
            sb.append(" in ");
            sb.append(str);
            zzh(sb.toString());
        }
        return list.get(i);
    }

    public static void zzh(String str) throws zzrz {
        com.google.android.gms.tagmanager.zzdh.zza(str);
        throw new zzrz(str);
    }
}
