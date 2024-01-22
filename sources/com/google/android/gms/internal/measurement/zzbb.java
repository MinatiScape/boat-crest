package com.google.android.gms.internal.measurement;

import com.clevertap.android.sdk.Constants;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzbb {
    public static zzae a(zzae zzaeVar, zzg zzgVar, zzai zzaiVar, Boolean bool, Boolean bool2) {
        zzae zzaeVar2 = new zzae();
        Iterator<Integer> zzk = zzaeVar.zzk();
        while (zzk.hasNext()) {
            int intValue = zzk.next().intValue();
            if (zzaeVar.zzs(intValue)) {
                zzap zza = zzaiVar.zza(zzgVar, Arrays.asList(zzaeVar.zze(intValue), new zzah(Double.valueOf(intValue)), zzaeVar));
                if (zza.zzg().equals(bool)) {
                    return zzaeVar2;
                }
                if (bool2 == null || zza.zzg().equals(bool2)) {
                    zzaeVar2.zzq(intValue, zza);
                }
            }
        }
        return zzaeVar2;
    }

    public static zzap b(zzae zzaeVar, zzg zzgVar, List<zzap> list, boolean z) {
        zzap zzapVar;
        zzh.zzi("reduce", 1, list);
        zzh.zzj("reduce", 2, list);
        zzap zzb = zzgVar.zzb(list.get(0));
        if (zzb instanceof zzai) {
            if (list.size() == 2) {
                zzapVar = zzgVar.zzb(list.get(1));
                if (zzapVar instanceof zzag) {
                    throw new IllegalArgumentException("Failed to parse initial value");
                }
            } else if (zzaeVar.zzc() == 0) {
                throw new IllegalStateException("Empty array with no initial value error");
            } else {
                zzapVar = null;
            }
            zzai zzaiVar = (zzai) zzb;
            int zzc = zzaeVar.zzc();
            int i = z ? 0 : zzc - 1;
            int i2 = z ? zzc - 1 : 0;
            int i3 = true == z ? 1 : -1;
            if (zzapVar == null) {
                zzapVar = zzaeVar.zze(i);
                i += i3;
            }
            while ((i2 - i) * i3 >= 0) {
                if (zzaeVar.zzs(i)) {
                    zzapVar = zzaiVar.zza(zzgVar, Arrays.asList(zzapVar, zzaeVar.zze(i), new zzah(Double.valueOf(i)), zzaeVar));
                    if (zzapVar instanceof zzag) {
                        throw new IllegalStateException("Reduce operation failed");
                    }
                    i += i3;
                } else {
                    i += i3;
                }
            }
            return zzapVar;
        }
        throw new IllegalArgumentException("Callback should be a method");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static zzap zza(String str, zzae zzaeVar, zzg zzgVar, List<zzap> list) {
        String str2;
        char c;
        String str3;
        double min;
        zzai zzaiVar;
        switch (str.hashCode()) {
            case -1776922004:
                str2 = "toString";
                if (str.equals(str2)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -1354795244:
                if (str.equals("concat")) {
                    str2 = "toString";
                    c = 0;
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case -1274492040:
                if (str.equals("filter")) {
                    str2 = "toString";
                    c = 2;
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case -934873754:
                if (str.equals("reduce")) {
                    c = '\n';
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case -895859076:
                if (str.equals("splice")) {
                    c = 17;
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case -678635926:
                if (str.equals("forEach")) {
                    c = 3;
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case -467511597:
                if (str.equals("lastIndexOf")) {
                    c = 6;
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case -277637751:
                if (str.equals("unshift")) {
                    c = 19;
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 107868:
                if (str.equals("map")) {
                    c = 7;
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 111185:
                if (str.equals("pop")) {
                    c = '\b';
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 3267882:
                if (str.equals("join")) {
                    c = 5;
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 3452698:
                if (str.equals("push")) {
                    c = '\t';
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 3536116:
                if (str.equals("some")) {
                    c = 15;
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 3536286:
                if (str.equals("sort")) {
                    c = 16;
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 96891675:
                if (str.equals("every")) {
                    str2 = "toString";
                    c = 1;
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 109407362:
                if (str.equals("shift")) {
                    c = '\r';
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 109526418:
                if (str.equals("slice")) {
                    c = 14;
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 965561430:
                if (str.equals("reduceRight")) {
                    c = 11;
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 1099846370:
                if (str.equals("reverse")) {
                    c = '\f';
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            case 1943291465:
                if (str.equals("indexOf")) {
                    c = 4;
                    str2 = "toString";
                    break;
                }
                str2 = "toString";
                c = 65535;
                break;
            default:
                str2 = "toString";
                c = 65535;
                break;
        }
        double d = 0.0d;
        switch (c) {
            case 0:
                zzap zzd = zzaeVar.zzd();
                if (!list.isEmpty()) {
                    for (zzap zzapVar : list) {
                        zzap zzb = zzgVar.zzb(zzapVar);
                        if (!(zzb instanceof zzag)) {
                            zzae zzaeVar2 = (zzae) zzd;
                            int zzc = zzaeVar2.zzc();
                            if (zzb instanceof zzae) {
                                zzae zzaeVar3 = (zzae) zzb;
                                Iterator<Integer> zzk = zzaeVar3.zzk();
                                while (zzk.hasNext()) {
                                    Integer next = zzk.next();
                                    zzaeVar2.zzq(next.intValue() + zzc, zzaeVar3.zze(next.intValue()));
                                }
                            } else {
                                zzaeVar2.zzq(zzc, zzb);
                            }
                        } else {
                            throw new IllegalStateException("Failed evaluation of arguments");
                        }
                    }
                }
                return zzd;
            case 1:
                zzh.zzh("every", 1, list);
                zzap zzb2 = zzgVar.zzb(list.get(0));
                if (zzb2 instanceof zzao) {
                    if (zzaeVar.zzc() != 0 && a(zzaeVar, zzgVar, (zzao) zzb2, Boolean.FALSE, Boolean.TRUE).zzc() != zzaeVar.zzc()) {
                        return zzap.zzl;
                    }
                    return zzap.zzk;
                }
                throw new IllegalArgumentException("Callback should be a method");
            case 2:
                zzh.zzh("filter", 1, list);
                zzap zzb3 = zzgVar.zzb(list.get(0));
                if (zzb3 instanceof zzao) {
                    if (zzaeVar.zzb() == 0) {
                        return new zzae();
                    }
                    zzap zzd2 = zzaeVar.zzd();
                    zzae a2 = a(zzaeVar, zzgVar, (zzao) zzb3, null, Boolean.TRUE);
                    zzae zzaeVar4 = new zzae();
                    Iterator<Integer> zzk2 = a2.zzk();
                    while (zzk2.hasNext()) {
                        zzaeVar4.zzq(zzaeVar4.zzc(), ((zzae) zzd2).zze(zzk2.next().intValue()));
                    }
                    return zzaeVar4;
                }
                throw new IllegalArgumentException("Callback should be a method");
            case 3:
                zzh.zzh("forEach", 1, list);
                zzap zzb4 = zzgVar.zzb(list.get(0));
                if (zzb4 instanceof zzao) {
                    if (zzaeVar.zzb() == 0) {
                        return zzap.zzf;
                    }
                    a(zzaeVar, zzgVar, (zzao) zzb4, null, null);
                    return zzap.zzf;
                }
                throw new IllegalArgumentException("Callback should be a method");
            case 4:
                zzh.zzj("indexOf", 2, list);
                zzap zzapVar2 = zzap.zzf;
                if (!list.isEmpty()) {
                    zzapVar2 = zzgVar.zzb(list.get(0));
                }
                if (list.size() > 1) {
                    double zza = zzh.zza(zzgVar.zzb(list.get(1)).zzh().doubleValue());
                    if (zza >= zzaeVar.zzc()) {
                        return new zzah(Double.valueOf(-1.0d));
                    }
                    d = zza < 0.0d ? zzaeVar.zzc() + zza : zza;
                }
                Iterator<Integer> zzk3 = zzaeVar.zzk();
                while (zzk3.hasNext()) {
                    int intValue = zzk3.next().intValue();
                    double d2 = intValue;
                    if (d2 >= d && zzh.zzl(zzaeVar.zze(intValue), zzapVar2)) {
                        return new zzah(Double.valueOf(d2));
                    }
                }
                return new zzah(Double.valueOf(-1.0d));
            case 5:
                zzh.zzj("join", 1, list);
                if (zzaeVar.zzc() == 0) {
                    return zzap.zzm;
                }
                if (list.size() > 0) {
                    zzap zzb5 = zzgVar.zzb(list.get(0));
                    str3 = ((zzb5 instanceof zzan) || (zzb5 instanceof zzau)) ? "" : zzb5.zzi();
                } else {
                    str3 = Constants.SEPARATOR_COMMA;
                }
                return new zzat(zzaeVar.zzj(str3));
            case 6:
                zzh.zzj("lastIndexOf", 2, list);
                zzap zzapVar3 = zzap.zzf;
                if (!list.isEmpty()) {
                    zzapVar3 = zzgVar.zzb(list.get(0));
                }
                double zzc2 = zzaeVar.zzc() - 1;
                if (list.size() > 1) {
                    zzap zzb6 = zzgVar.zzb(list.get(1));
                    zzc2 = Double.isNaN(zzb6.zzh().doubleValue()) ? zzaeVar.zzc() - 1 : zzh.zza(zzb6.zzh().doubleValue());
                    if (zzc2 < 0.0d) {
                        zzc2 += zzaeVar.zzc();
                    }
                }
                if (zzc2 < 0.0d) {
                    return new zzah(Double.valueOf(-1.0d));
                }
                for (int min2 = (int) Math.min(zzaeVar.zzc(), zzc2); min2 >= 0; min2--) {
                    if (zzaeVar.zzs(min2) && zzh.zzl(zzaeVar.zze(min2), zzapVar3)) {
                        return new zzah(Double.valueOf(min2));
                    }
                }
                return new zzah(Double.valueOf(-1.0d));
            case 7:
                zzh.zzh("map", 1, list);
                zzap zzb7 = zzgVar.zzb(list.get(0));
                if (zzb7 instanceof zzao) {
                    if (zzaeVar.zzc() == 0) {
                        return new zzae();
                    }
                    return a(zzaeVar, zzgVar, (zzao) zzb7, null, null);
                }
                throw new IllegalArgumentException("Callback should be a method");
            case '\b':
                zzh.zzh("pop", 0, list);
                int zzc3 = zzaeVar.zzc();
                if (zzc3 == 0) {
                    return zzap.zzf;
                }
                int i = zzc3 - 1;
                zzap zze = zzaeVar.zze(i);
                zzaeVar.zzp(i);
                return zze;
            case '\t':
                if (!list.isEmpty()) {
                    for (zzap zzapVar4 : list) {
                        zzaeVar.zzq(zzaeVar.zzc(), zzgVar.zzb(zzapVar4));
                    }
                }
                return new zzah(Double.valueOf(zzaeVar.zzc()));
            case '\n':
                return b(zzaeVar, zzgVar, list, true);
            case 11:
                return b(zzaeVar, zzgVar, list, false);
            case '\f':
                zzh.zzh("reverse", 0, list);
                int zzc4 = zzaeVar.zzc();
                if (zzc4 != 0) {
                    for (int i2 = 0; i2 < zzc4 / 2; i2++) {
                        if (zzaeVar.zzs(i2)) {
                            zzap zze2 = zzaeVar.zze(i2);
                            zzaeVar.zzq(i2, null);
                            int i3 = (zzc4 - 1) - i2;
                            if (zzaeVar.zzs(i3)) {
                                zzaeVar.zzq(i2, zzaeVar.zze(i3));
                            }
                            zzaeVar.zzq(i3, zze2);
                        }
                    }
                }
                return zzaeVar;
            case '\r':
                zzh.zzh("shift", 0, list);
                if (zzaeVar.zzc() == 0) {
                    return zzap.zzf;
                }
                zzap zze3 = zzaeVar.zze(0);
                zzaeVar.zzp(0);
                return zze3;
            case 14:
                zzh.zzj("slice", 2, list);
                if (list.isEmpty()) {
                    return zzaeVar.zzd();
                }
                double zzc5 = zzaeVar.zzc();
                double zza2 = zzh.zza(zzgVar.zzb(list.get(0)).zzh().doubleValue());
                if (zza2 < 0.0d) {
                    min = Math.max(zza2 + zzc5, 0.0d);
                } else {
                    min = Math.min(zza2, zzc5);
                }
                if (list.size() == 2) {
                    double zza3 = zzh.zza(zzgVar.zzb(list.get(1)).zzh().doubleValue());
                    if (zza3 < 0.0d) {
                        zzc5 = Math.max(zzc5 + zza3, 0.0d);
                    } else {
                        zzc5 = Math.min(zzc5, zza3);
                    }
                }
                zzae zzaeVar5 = new zzae();
                for (int i4 = (int) min; i4 < zzc5; i4++) {
                    zzaeVar5.zzq(zzaeVar5.zzc(), zzaeVar.zze(i4));
                }
                return zzaeVar5;
            case 15:
                zzh.zzh("some", 1, list);
                zzap zzb8 = zzgVar.zzb(list.get(0));
                if (zzb8 instanceof zzai) {
                    if (zzaeVar.zzc() == 0) {
                        return zzap.zzl;
                    }
                    zzai zzaiVar2 = (zzai) zzb8;
                    Iterator<Integer> zzk4 = zzaeVar.zzk();
                    while (zzk4.hasNext()) {
                        int intValue2 = zzk4.next().intValue();
                        if (zzaeVar.zzs(intValue2) && zzaiVar2.zza(zzgVar, Arrays.asList(zzaeVar.zze(intValue2), new zzah(Double.valueOf(intValue2)), zzaeVar)).zzg().booleanValue()) {
                            return zzap.zzk;
                        }
                    }
                    return zzap.zzl;
                }
                throw new IllegalArgumentException("Callback should be a method");
            case 16:
                zzh.zzj("sort", 1, list);
                if (zzaeVar.zzc() >= 2) {
                    List<zzap> zzm = zzaeVar.zzm();
                    if (list.isEmpty()) {
                        zzaiVar = null;
                    } else {
                        zzap zzb9 = zzgVar.zzb(list.get(0));
                        if (zzb9 instanceof zzai) {
                            zzaiVar = (zzai) zzb9;
                        } else {
                            throw new IllegalArgumentException("Comparator should be a method");
                        }
                    }
                    Collections.sort(zzm, new f(zzaiVar, zzgVar));
                    zzaeVar.zzn();
                    int i5 = 0;
                    for (zzap zzapVar5 : zzm) {
                        zzaeVar.zzq(i5, zzapVar5);
                        i5++;
                    }
                }
                return zzaeVar;
            case 17:
                if (list.isEmpty()) {
                    return new zzae();
                }
                int zza4 = (int) zzh.zza(zzgVar.zzb(list.get(0)).zzh().doubleValue());
                if (zza4 < 0) {
                    zza4 = Math.max(0, zza4 + zzaeVar.zzc());
                } else if (zza4 > zzaeVar.zzc()) {
                    zza4 = zzaeVar.zzc();
                }
                int zzc6 = zzaeVar.zzc();
                zzae zzaeVar6 = new zzae();
                if (list.size() > 1) {
                    int max = Math.max(0, (int) zzh.zza(zzgVar.zzb(list.get(1)).zzh().doubleValue()));
                    if (max > 0) {
                        for (int i6 = zza4; i6 < Math.min(zzc6, zza4 + max); i6++) {
                            zzaeVar6.zzq(zzaeVar6.zzc(), zzaeVar.zze(zza4));
                            zzaeVar.zzp(zza4);
                        }
                    }
                    if (list.size() > 2) {
                        for (int i7 = 2; i7 < list.size(); i7++) {
                            zzap zzb10 = zzgVar.zzb(list.get(i7));
                            if (!(zzb10 instanceof zzag)) {
                                zzaeVar.zzo((zza4 + i7) - 2, zzb10);
                            } else {
                                throw new IllegalArgumentException("Failed to parse elements to add");
                            }
                        }
                    }
                } else {
                    while (zza4 < zzc6) {
                        zzaeVar6.zzq(zzaeVar6.zzc(), zzaeVar.zze(zza4));
                        zzaeVar.zzq(zza4, null);
                        zza4++;
                    }
                }
                return zzaeVar6;
            case 18:
                zzh.zzh(str2, 0, list);
                return new zzat(zzaeVar.zzj(Constants.SEPARATOR_COMMA));
            case 19:
                if (!list.isEmpty()) {
                    zzae zzaeVar7 = new zzae();
                    for (zzap zzapVar6 : list) {
                        zzap zzb11 = zzgVar.zzb(zzapVar6);
                        if (!(zzb11 instanceof zzag)) {
                            zzaeVar7.zzq(zzaeVar7.zzc(), zzb11);
                        } else {
                            throw new IllegalStateException("Argument evaluation failed");
                        }
                    }
                    int zzc7 = zzaeVar7.zzc();
                    Iterator<Integer> zzk5 = zzaeVar.zzk();
                    while (zzk5.hasNext()) {
                        Integer next2 = zzk5.next();
                        zzaeVar7.zzq(next2.intValue() + zzc7, zzaeVar.zze(next2.intValue()));
                    }
                    zzaeVar.zzn();
                    Iterator<Integer> zzk6 = zzaeVar7.zzk();
                    while (zzk6.hasNext()) {
                        Integer next3 = zzk6.next();
                        zzaeVar.zzq(next3.intValue(), zzaeVar7.zze(next3.intValue()));
                    }
                }
                return new zzah(Double.valueOf(zzaeVar.zzc()));
            default:
                throw new IllegalArgumentException("Command not supported");
        }
    }
}
