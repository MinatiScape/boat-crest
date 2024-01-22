package com.google.android.libraries.places.internal;

import com.google.protobuf.z0;
import java.io.IOException;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzug {
    private static final Class<?> zza = zzd();
    private static final zzuw<?, ?> zzb = zza(false);
    private static final zzuw<?, ?> zzc = zza(true);
    private static final zzuw<?, ?> zzd = new zzuy();

    public static void zza(Class<?> cls) {
        Class<?> cls2;
        if (!zzsf.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzb(int i, List<Float> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzf(i, list, z);
    }

    public static void zzc(int i, List<Long> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzc(i, list, z);
    }

    public static void zzd(int i, List<Long> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzd(i, list, z);
    }

    public static void zze(int i, List<Long> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzn(i, list, z);
    }

    public static void zzf(int i, List<Long> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zze(i, list, z);
    }

    public static void zzg(int i, List<Long> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzl(i, list, z);
    }

    public static void zzh(int i, List<Integer> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zza(i, list, z);
    }

    public static void zzi(int i, List<Integer> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzj(i, list, z);
    }

    public static void zzj(int i, List<Integer> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzm(i, list, z);
    }

    public static void zzk(int i, List<Integer> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzb(i, list, z);
    }

    public static void zzl(int i, List<Integer> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzk(i, list, z);
    }

    public static void zzm(int i, List<Integer> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzh(i, list, z);
    }

    public static void zzn(int i, List<Boolean> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzi(i, list, z);
    }

    public static void zzb(int i, List<zzrb> list, zzvq zzvqVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzb(i, list);
    }

    public static int zzc(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zztc) {
            zztc zztcVar = (zztc) list;
            i = 0;
            while (i2 < size) {
                i += zzrs.zzf(zztcVar.zza(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzrs.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int zzd(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzsh) {
            zzsh zzshVar = (zzsh) list;
            i = 0;
            while (i2 < size) {
                i += zzrs.zzk(zzshVar.zza(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzrs.zzk(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzsh) {
            zzsh zzshVar = (zzsh) list;
            i = 0;
            while (i2 < size) {
                i += zzrs.zzf(zzshVar.zza(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzrs.zzf(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int zzf(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzsh) {
            zzsh zzshVar = (zzsh) list;
            i = 0;
            while (i2 < size) {
                i += zzrs.zzg(zzshVar.zza(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzrs.zzg(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int zzg(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzsh) {
            zzsh zzshVar = (zzsh) list;
            i = 0;
            while (i2 < size) {
                i += zzrs.zzh(zzshVar.zza(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzrs.zzh(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int zzh(List<?> list) {
        return list.size() << 2;
    }

    public static int zzi(List<?> list) {
        return list.size() << 3;
    }

    public static int zzj(List<?> list) {
        return list.size();
    }

    public static void zza(int i, List<Double> list, zzvq zzvqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzg(i, list, z);
    }

    public static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzrs.zzi(i, 0);
    }

    public static int zzi(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzrs.zzg(i, 0L);
    }

    public static int zzj(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzrs.zzb(i, true);
    }

    public static void zzb(int i, List<?> list, zzvq zzvqVar, zzue zzueVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zzb(i, list, zzueVar);
    }

    public static void zza(int i, List<String> list, zzvq zzvqVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zza(i, list);
    }

    public static int zzb(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zztc) {
            zztc zztcVar = (zztc) list;
            i = 0;
            while (i2 < size) {
                i += zzrs.zze(zztcVar.zza(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzrs.zze(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void zza(int i, List<?> list, zzvq zzvqVar, zzue zzueVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzvqVar.zza(i, list, zzueVar);
    }

    public static int zzc(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzc(list) + (size * zzrs.zze(i));
    }

    public static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzrs.zze(i));
    }

    public static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzrs.zze(i));
    }

    public static int zzf(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzrs.zze(i));
    }

    public static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzrs.zze(i));
    }

    public static int zza(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zztc) {
            zztc zztcVar = (zztc) list;
            i = 0;
            while (i2 < size) {
                i += zzrs.zzd(zztcVar.zza(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzrs.zzd(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzuw<?, ?> zzc() {
        return zzd;
    }

    private static Class<?> zzd() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zze() {
        return z0.class;
    }

    public static int zzb(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzrs.zze(i));
    }

    public static int zza(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzrs.zze(i));
    }

    public static int zzb(int i, List<zzrb> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = size * zzrs.zze(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zze += zzrs.zzb(list.get(i2));
        }
        return zze;
    }

    public static int zza(int i, List<?> list) {
        int zzb2;
        int zzb3;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zze = zzrs.zze(i) * size;
        if (list instanceof zzsz) {
            zzsz zzszVar = (zzsz) list;
            while (i2 < size) {
                Object zza2 = zzszVar.zza(i2);
                if (zza2 instanceof zzrb) {
                    zzb3 = zzrs.zzb((zzrb) zza2);
                } else {
                    zzb3 = zzrs.zzb((String) zza2);
                }
                zze += zzb3;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzrb) {
                    zzb2 = zzrs.zzb((zzrb) obj);
                } else {
                    zzb2 = zzrs.zzb((String) obj);
                }
                zze += zzb2;
                i2++;
            }
        }
        return zze;
    }

    public static int zzb(int i, List<zzto> list, zzue zzueVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzrs.zzc(i, list.get(i3), zzueVar);
        }
        return i2;
    }

    public static zzuw<?, ?> zzb() {
        return zzc;
    }

    public static int zza(int i, Object obj, zzue zzueVar) {
        if (obj instanceof zzsx) {
            return zzrs.zza(i, (zzsx) obj);
        }
        return zzrs.zzb(i, (zzto) obj, zzueVar);
    }

    public static int zza(int i, List<?> list, zzue zzueVar) {
        int zza2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = zzrs.zze(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzsx) {
                zza2 = zzrs.zza((zzsx) obj);
            } else {
                zza2 = zzrs.zza((zzto) obj, zzueVar);
            }
            zze += zza2;
        }
        return zze;
    }

    public static zzuw<?, ?> zza() {
        return zzb;
    }

    private static zzuw<?, ?> zza(boolean z) {
        try {
            Class<?> zze = zze();
            if (zze == null) {
                return null;
            }
            return (zzuw) zze.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static <T> void zza(zztl zztlVar, T t, T t2, long j) {
        zzvc.zza(t, j, zztlVar.zza(zzvc.zzf(t, j), zzvc.zzf(t2, j)));
    }

    public static <T, FT extends zzrz<FT>> void zza(zzrw<FT> zzrwVar, T t, T t2) {
        zzrx<FT> zza2 = zzrwVar.zza(t2);
        if (zza2.zza.isEmpty()) {
            return;
        }
        zzrwVar.zzb(t).zza(zza2);
    }

    public static <T, UT, UB> void zza(zzuw<UT, UB> zzuwVar, T t, T t2) {
        zzuwVar.zza(t, zzuwVar.zzb(zzuwVar.zza(t), zzuwVar.zza(t2)));
    }
}
