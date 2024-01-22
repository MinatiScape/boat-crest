package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzfv {
    public static final Long zza = new Long(0);
    public static final Double zzb = new Double(0.0d);
    public static final zzfu zzc = zzfu.zzd(0);
    public static final String zzd;
    public static final Boolean zze;
    public static final List<Object> zzf;
    public static final Map<Object, Object> zzg;
    public static final com.google.android.gms.internal.gtm.zzak zzh;

    static {
        String str = new String("");
        zzd = str;
        zze = new Boolean(false);
        zzf = new ArrayList(0);
        zzg = new HashMap();
        zzh = zzc(str);
    }

    public static com.google.android.gms.internal.gtm.zzak zza(String str) {
        com.google.android.gms.internal.gtm.zzal zzg2 = com.google.android.gms.internal.gtm.zzak.zzg();
        zzg2.zzt(1);
        zzg2.zzt(5);
        zzg2.zzp(str);
        zzg2.zzo(false);
        return (com.google.android.gms.internal.gtm.zzak) zzg2.zzC();
    }

    public static com.google.android.gms.internal.gtm.zzak zzb() {
        return zzh;
    }

    public static com.google.android.gms.internal.gtm.zzak zzc(Object obj) {
        com.google.android.gms.internal.gtm.zzal zzg2 = com.google.android.gms.internal.gtm.zzak.zzg();
        zzg2.zzt(1);
        if (obj instanceof com.google.android.gms.internal.gtm.zzak) {
            return (com.google.android.gms.internal.gtm.zzak) obj;
        }
        boolean z = false;
        if (obj instanceof String) {
            zzg2.zzt(1);
            zzg2.zzs((String) obj);
        } else if (obj instanceof List) {
            zzg2.zzt(2);
            List<Object> list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            boolean z2 = false;
            for (Object obj2 : list) {
                com.google.android.gms.internal.gtm.zzak zzc2 = zzc(obj2);
                com.google.android.gms.internal.gtm.zzak zzakVar = zzh;
                if (zzc2 == zzakVar) {
                    return zzakVar;
                }
                z2 = z2 || zzc2.zzN();
                arrayList.add(zzc2);
            }
            zzg2.zzj();
            zzg2.zzb(arrayList);
            z = z2;
        } else if (obj instanceof Map) {
            zzg2.zzt(3);
            Set<Map.Entry> entrySet = ((Map) obj).entrySet();
            ArrayList arrayList2 = new ArrayList(entrySet.size());
            ArrayList arrayList3 = new ArrayList(entrySet.size());
            boolean z3 = false;
            for (Map.Entry entry : entrySet) {
                com.google.android.gms.internal.gtm.zzak zzc3 = zzc(entry.getKey());
                com.google.android.gms.internal.gtm.zzak zzc4 = zzc(entry.getValue());
                com.google.android.gms.internal.gtm.zzak zzakVar2 = zzh;
                if (zzc3 == zzakVar2 || zzc4 == zzakVar2) {
                    return zzakVar2;
                }
                z3 = z3 || zzc3.zzN() || zzc4.zzN();
                arrayList2.add(zzc3);
                arrayList3.add(zzc4);
            }
            zzg2.zzk();
            zzg2.zzc(arrayList2);
            zzg2.zzl();
            zzg2.zzd(arrayList3);
            z = z3;
        } else if (zzr(obj)) {
            zzg2.zzt(1);
            zzg2.zzs(obj.toString());
        } else if (zzs(obj)) {
            zzg2.zzt(6);
            zzg2.zzq(zzp(obj));
        } else if (obj instanceof Boolean) {
            zzg2.zzt(8);
            zzg2.zzn(((Boolean) obj).booleanValue());
        } else {
            String valueOf = String.valueOf(obj == null ? "null" : obj.getClass().toString());
            zzdh.zza(valueOf.length() != 0 ? "Converting to Value from unknown object type: ".concat(valueOf) : new String("Converting to Value from unknown object type: "));
            return zzh;
        }
        zzg2.zzo(z);
        return (com.google.android.gms.internal.gtm.zzak) zzg2.zzC();
    }

    public static zzfu zzd() {
        return zzc;
    }

    public static zzfu zze(Object obj) {
        if (obj instanceof zzfu) {
            return (zzfu) obj;
        }
        if (zzs(obj)) {
            return zzfu.zzd(zzp(obj));
        }
        if (zzr(obj)) {
            return zzfu.zzc(Double.valueOf(zzo(obj)));
        }
        return zzq(zzn(obj));
    }

    public static Boolean zzf() {
        return zze;
    }

    public static Boolean zzg(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        String zzn = zzn(obj);
        return "true".equalsIgnoreCase(zzn) ? Boolean.TRUE : "false".equalsIgnoreCase(zzn) ? Boolean.FALSE : zze;
    }

    public static Double zzh() {
        return zzb;
    }

    public static Double zzi(Object obj) {
        if (zzr(obj)) {
            return Double.valueOf(zzo(obj));
        }
        zzfu zzq = zzq(zzn(obj));
        return zzq == zzc ? zzb : Double.valueOf(zzq.doubleValue());
    }

    public static Long zzj() {
        return zza;
    }

    public static Long zzk(Object obj) {
        if (zzs(obj)) {
            return Long.valueOf(zzp(obj));
        }
        zzfu zzq = zzq(zzn(obj));
        return zzq == zzc ? zza : Long.valueOf(zzq.zzb());
    }

    public static Object zzl(com.google.android.gms.internal.gtm.zzak zzakVar) {
        if (zzakVar == null) {
            return null;
        }
        switch (zzakVar.zzO()) {
            case 1:
                return zzakVar.zzp();
            case 2:
                ArrayList arrayList = new ArrayList(zzakVar.zza());
                for (com.google.android.gms.internal.gtm.zzak zzakVar2 : zzakVar.zzr()) {
                    Object zzl = zzl(zzakVar2);
                    if (zzl == null) {
                        return null;
                    }
                    arrayList.add(zzl);
                }
                return arrayList;
            case 3:
                if (zzakVar.zzc() != zzakVar.zzd()) {
                    String valueOf = String.valueOf(zzakVar.toString());
                    zzdh.zza(valueOf.length() != 0 ? "Converting an invalid value to object: ".concat(valueOf) : new String("Converting an invalid value to object: "));
                    return null;
                }
                HashMap hashMap = new HashMap(zzakVar.zzd());
                for (int i = 0; i < zzakVar.zzc(); i++) {
                    Object zzl2 = zzl(zzakVar.zzk(i));
                    Object zzl3 = zzl(zzakVar.zzl(i));
                    if (zzl2 == null || zzl3 == null) {
                        return null;
                    }
                    hashMap.put(zzl2, zzl3);
                }
                return hashMap;
            case 4:
                zzdh.zza("Trying to convert a macro reference to object");
                return null;
            case 5:
                zzdh.zza("Trying to convert a function id to object");
                return null;
            case 6:
                return Long.valueOf(zzakVar.zzf());
            case 7:
                StringBuilder sb = new StringBuilder();
                for (com.google.android.gms.internal.gtm.zzak zzakVar3 : zzakVar.zzs()) {
                    String zzn = zzn(zzl(zzakVar3));
                    if (zzn == zzd) {
                        return null;
                    }
                    sb.append(zzn);
                }
                return sb.toString();
            default:
                return Boolean.valueOf(zzakVar.zzM());
        }
    }

    public static String zzm() {
        return zzd;
    }

    public static String zzn(Object obj) {
        return obj == null ? zzd : obj.toString();
    }

    public static double zzo(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        zzdh.zza("getDouble received non-Number");
        return 0.0d;
    }

    public static long zzp(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        zzdh.zza("getInt64 received non-Number");
        return 0L;
    }

    public static zzfu zzq(String str) {
        try {
            return zzfu.zze(str);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 33);
            sb.append("Failed to convert '");
            sb.append(str);
            sb.append("' to a number.");
            zzdh.zza(sb.toString());
            return zzc;
        }
    }

    public static boolean zzr(Object obj) {
        return (obj instanceof Double) || (obj instanceof Float) || ((obj instanceof zzfu) && ((zzfu) obj).zzf());
    }

    public static boolean zzs(Object obj) {
        return (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || ((obj instanceof zzfu) && ((zzfu) obj).zzg());
    }
}
