package com.google.android.gms.internal.measurement;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;
/* loaded from: classes8.dex */
public final class zzat implements Iterable<zzap>, zzap {
    public final String h;

    public zzat(String str) {
        if (str != null) {
            this.h = str;
            return;
        }
        throw new IllegalArgumentException("StringValue cannot be null.");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzat) {
            return this.h.equals(((zzat) obj).h);
        }
        return false;
    }

    public final int hashCode() {
        return this.h.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator<zzap> iterator() {
        return new e(this);
    }

    public final String toString() {
        String str = this.h;
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append(Typography.quote);
        sb.append(str);
        sb.append(Typography.quote);
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbK(String str, zzg zzgVar, List<zzap> list) {
        String str2;
        Object obj;
        String str3;
        String str4;
        char c;
        zzat zzatVar;
        int i;
        zzap zzahVar;
        double doubleValue;
        String str5;
        Matcher matcher;
        double min;
        double min2;
        zzat zzatVar2;
        int i2;
        int i3;
        zzg zzgVar2;
        int i4;
        int length;
        if ("charAt".equals(str) || "concat".equals(str) || "hasOwnProperty".equals(str) || "indexOf".equals(str) || "lastIndexOf".equals(str) || "match".equals(str) || "replace".equals(str) || FirebaseAnalytics.Event.SEARCH.equals(str) || "slice".equals(str) || "split".equals(str) || "substring".equals(str) || "toLowerCase".equals(str) || "toLocaleLowerCase".equals(str) || "toString".equals(str) || "toUpperCase".equals(str)) {
            str2 = "toLocaleUpperCase";
        } else {
            str2 = "toLocaleUpperCase";
            if (!str2.equals(str) && !"trim".equals(str)) {
                throw new IllegalArgumentException(String.format("%s is not a String function", str));
            }
        }
        switch (str.hashCode()) {
            case -1789698943:
                obj = "charAt";
                str3 = "hasOwnProperty";
                if (str.equals(str3)) {
                    str4 = "toString";
                    c = 2;
                    break;
                }
                c = 65535;
                str4 = "toString";
                break;
            case -1776922004:
                obj = "charAt";
                if (str.equals("toString")) {
                    c = 14;
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case -1464939364:
                obj = "charAt";
                if (str.equals("toLocaleLowerCase")) {
                    c = '\f';
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case -1361633751:
                obj = "charAt";
                if (str.equals(obj)) {
                    str3 = "hasOwnProperty";
                    c = 0;
                    str4 = "toString";
                    break;
                }
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case -1354795244:
                if (str.equals("concat")) {
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    c = 1;
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case -1137582698:
                if (str.equals("toLowerCase")) {
                    c = '\r';
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case -906336856:
                if (str.equals(FirebaseAnalytics.Event.SEARCH)) {
                    c = 7;
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case -726908483:
                if (str.equals(str2)) {
                    c = 11;
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case -467511597:
                if (str.equals("lastIndexOf")) {
                    c = 4;
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case -399551817:
                if (str.equals("toUpperCase")) {
                    c = 15;
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case 3568674:
                if (str.equals("trim")) {
                    c = 16;
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case 103668165:
                if (str.equals("match")) {
                    c = 5;
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case 109526418:
                if (str.equals("slice")) {
                    c = '\b';
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case 109648666:
                if (str.equals("split")) {
                    c = '\t';
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case 530542161:
                if (str.equals("substring")) {
                    c = '\n';
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case 1094496948:
                if (str.equals("replace")) {
                    c = 6;
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            case 1943291465:
                if (str.equals("indexOf")) {
                    c = 3;
                    obj = "charAt";
                    str3 = "hasOwnProperty";
                    str4 = "toString";
                    break;
                }
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
            default:
                obj = "charAt";
                str3 = "hasOwnProperty";
                c = 65535;
                str4 = "toString";
                break;
        }
        String str6 = obj;
        switch (c) {
            case 0:
                zzh.zzj(str6, 1, list);
                int zza = list.size() > 0 ? (int) zzh.zza(zzgVar.zzb(list.get(0)).zzh().doubleValue()) : 0;
                String str7 = this.h;
                if (zza >= 0 && zza < str7.length()) {
                    return new zzat(String.valueOf(str7.charAt(zza)));
                }
                return zzap.zzm;
            case 1:
                zzatVar = this;
                if (list.size() != 0) {
                    StringBuilder sb = new StringBuilder(zzatVar.h);
                    for (int i5 = 0; i5 < list.size(); i5++) {
                        sb.append(zzgVar.zzb(list.get(i5)).zzi());
                    }
                    return new zzat(sb.toString());
                }
                return zzatVar;
            case 2:
                zzh.zzh(str3, 1, list);
                String str8 = this.h;
                zzap zzb = zzgVar.zzb(list.get(0));
                if ("length".equals(zzb.zzi())) {
                    return zzap.zzk;
                }
                double doubleValue2 = zzb.zzh().doubleValue();
                return (doubleValue2 != Math.floor(doubleValue2) || (i = (int) doubleValue2) < 0 || i >= str8.length()) ? zzap.zzl : zzap.zzk;
            case 3:
                zzh.zzj("indexOf", 2, list);
                zzahVar = new zzah(Double.valueOf(this.h.indexOf(list.size() > 0 ? zzgVar.zzb(list.get(0)).zzi() : "undefined", (int) zzh.zza(list.size() < 2 ? 0.0d : zzgVar.zzb(list.get(1)).zzh().doubleValue()))));
                return zzahVar;
            case 4:
                zzh.zzj("lastIndexOf", 2, list);
                String str9 = this.h;
                String zzi = list.size() > 0 ? zzgVar.zzb(list.get(0)).zzi() : "undefined";
                return new zzah(Double.valueOf(str9.lastIndexOf(zzi, (int) (Double.isNaN(list.size() < 2 ? Double.NaN : zzgVar.zzb(list.get(1)).zzh().doubleValue()) ? Double.POSITIVE_INFINITY : zzh.zza(doubleValue)))));
            case 5:
                zzh.zzj("match", 1, list);
                Matcher matcher2 = Pattern.compile(list.size() > 0 ? zzgVar.zzb(list.get(0)).zzi() : "").matcher(this.h);
                return matcher2.find() ? new zzae(Arrays.asList(new zzat(matcher2.group()))) : zzap.zzg;
            case 6:
                zzatVar = this;
                zzh.zzj("replace", 2, list);
                zzap zzapVar = zzap.zzf;
                if (list.size() > 0) {
                    str5 = zzgVar.zzb(list.get(0)).zzi();
                    if (list.size() > 1) {
                        zzapVar = zzgVar.zzb(list.get(1));
                    }
                }
                String str10 = str5;
                String str11 = zzatVar.h;
                int indexOf = str11.indexOf(str10);
                if (indexOf >= 0) {
                    if (zzapVar instanceof zzai) {
                        zzapVar = ((zzai) zzapVar).zza(zzgVar, Arrays.asList(new zzat(str10), new zzah(Double.valueOf(indexOf)), zzatVar));
                    }
                    String substring = str11.substring(0, indexOf);
                    String zzi2 = zzapVar.zzi();
                    String substring2 = str11.substring(indexOf + str10.length());
                    StringBuilder sb2 = new StringBuilder(String.valueOf(substring).length() + String.valueOf(zzi2).length() + String.valueOf(substring2).length());
                    sb2.append(substring);
                    sb2.append(zzi2);
                    sb2.append(substring2);
                    zzahVar = new zzat(sb2.toString());
                    return zzahVar;
                }
                return zzatVar;
            case 7:
                zzh.zzj(FirebaseAnalytics.Event.SEARCH, 1, list);
                if (Pattern.compile(list.size() > 0 ? zzgVar.zzb(list.get(0)).zzi() : "undefined").matcher(this.h).find()) {
                    return new zzah(Double.valueOf(matcher.start()));
                }
                return new zzah(Double.valueOf(-1.0d));
            case '\b':
                zzh.zzj("slice", 2, list);
                String str12 = this.h;
                double zza2 = zzh.zza(list.size() > 0 ? zzgVar.zzb(list.get(0)).zzh().doubleValue() : 0.0d);
                if (zza2 < 0.0d) {
                    min = Math.max(str12.length() + zza2, 0.0d);
                } else {
                    min = Math.min(zza2, str12.length());
                }
                int i6 = (int) min;
                double zza3 = zzh.zza(list.size() > 1 ? zzgVar.zzb(list.get(1)).zzh().doubleValue() : str12.length());
                if (zza3 < 0.0d) {
                    min2 = Math.max(str12.length() + zza3, 0.0d);
                } else {
                    min2 = Math.min(zza3, str12.length());
                }
                zzatVar2 = new zzat(str12.substring(i6, Math.max(0, ((int) min2) - i6) + i6));
                return zzatVar2;
            case '\t':
                zzh.zzj("split", 2, list);
                String str13 = this.h;
                if (str13.length() == 0) {
                    return new zzae(Arrays.asList(this));
                }
                ArrayList arrayList = new ArrayList();
                if (list.size() == 0) {
                    arrayList.add(this);
                } else {
                    String zzi3 = zzgVar.zzb(list.get(0)).zzi();
                    long zzd = list.size() > 1 ? zzh.zzd(zzgVar.zzb(list.get(1)).zzh().doubleValue()) : 2147483647L;
                    if (zzd == 0) {
                        return new zzae();
                    }
                    String[] split = str13.split(Pattern.quote(zzi3), ((int) zzd) + 1);
                    int length2 = split.length;
                    if (!zzi3.equals("") || length2 <= 0) {
                        i2 = length2;
                        i3 = 0;
                    } else {
                        boolean equals = split[0].equals("");
                        i2 = length2 - 1;
                        if (!split[i2].equals("")) {
                            i2 = length2;
                        }
                        i3 = equals;
                    }
                    if (length2 > zzd) {
                        i2--;
                    }
                    for (int i7 = i3; i7 < i2; i7++) {
                        arrayList.add(new zzat(split[i7]));
                    }
                }
                return new zzae(arrayList);
            case '\n':
                zzh.zzj("substring", 2, list);
                String str14 = this.h;
                if (list.size() > 0) {
                    zzgVar2 = zzgVar;
                    i4 = (int) zzh.zza(zzgVar2.zzb(list.get(0)).zzh().doubleValue());
                } else {
                    zzgVar2 = zzgVar;
                    i4 = 0;
                }
                if (list.size() > 1) {
                    length = (int) zzh.zza(zzgVar2.zzb(list.get(1)).zzh().doubleValue());
                } else {
                    length = str14.length();
                }
                int min3 = Math.min(Math.max(i4, 0), str14.length());
                int min4 = Math.min(Math.max(length, 0), str14.length());
                zzatVar2 = new zzat(str14.substring(Math.min(min3, min4), Math.max(min3, min4)));
                return zzatVar2;
            case 11:
                zzh.zzh(str2, 0, list);
                return new zzat(this.h.toUpperCase());
            case '\f':
                zzh.zzh("toLocaleLowerCase", 0, list);
                return new zzat(this.h.toLowerCase());
            case '\r':
                zzh.zzh("toLowerCase", 0, list);
                return new zzat(this.h.toLowerCase(Locale.ENGLISH));
            case 14:
                zzatVar = this;
                zzh.zzh(str4, 0, list);
                return zzatVar;
            case 15:
                zzh.zzh("toUpperCase", 0, list);
                return new zzat(this.h.toUpperCase(Locale.ENGLISH));
            case 16:
                zzh.zzh("toUpperCase", 0, list);
                return new zzat(this.h.trim());
            default:
                throw new IllegalArgumentException("Command not supported");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzat(this.h);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.valueOf(!this.h.isEmpty());
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        if (this.h.isEmpty()) {
            return Double.valueOf(0.0d);
        }
        try {
            return Double.valueOf(this.h);
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return this.h;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator<zzap> zzl() {
        return new d(this);
    }
}
