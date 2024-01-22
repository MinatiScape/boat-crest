package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzje {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzjw zzc;
    private static final zzjw zzd;

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:9:0x000e
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    static {
        /*
            r0 = 0
            java.lang.String r1 = "com.google.protobuf.GeneratedMessage"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L8
            goto L9
        L8:
            r1 = r0
        L9:
            com.google.android.recaptcha.internal.zzje.zzb = r1
            java.lang.Class<com.google.protobuf.z0> r1 = com.google.protobuf.z0.class
            goto Lf
        Le:
            r1 = r0
        Lf:
            if (r1 != 0) goto L12
            goto L22
        L12:
            r2 = 0
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L22
            java.lang.reflect.Constructor r1 = r1.getConstructor(r3)     // Catch: java.lang.Throwable -> L22
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L22
            java.lang.Object r1 = r1.newInstance(r2)     // Catch: java.lang.Throwable -> L22
            com.google.android.recaptcha.internal.zzjw r1 = (com.google.android.recaptcha.internal.zzjw) r1     // Catch: java.lang.Throwable -> L22
            r0 = r1
        L22:
            com.google.android.recaptcha.internal.zzje.zzc = r0
            com.google.android.recaptcha.internal.zzjy r0 = new com.google.android.recaptcha.internal.zzjy
            r0.<init>()
            com.google.android.recaptcha.internal.zzje.zzd = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzje.<clinit>():void");
    }

    public static Object zzA(Object obj, int i, List list, zzhj zzhjVar, Object obj2, zzjw zzjwVar) {
        if (zzhjVar == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zzhjVar.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    obj2 = zzB(obj, i, intValue, obj2, zzjwVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzhjVar.zza(intValue2)) {
                    obj2 = zzB(obj, i, intValue2, obj2, zzjwVar);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    public static Object zzB(Object obj, int i, int i2, Object obj2, zzjw zzjwVar) {
        if (obj2 == null) {
            obj2 = zzjwVar.zzc(obj);
        }
        zzjwVar.zzl(obj2, i, i2);
        return obj2;
    }

    public static void zzC(zzgr zzgrVar, Object obj, Object obj2) {
        zzgv zzb2 = zzgrVar.zzb(obj2);
        if (zzb2.zza.isEmpty()) {
            return;
        }
        zzgrVar.zzc(obj).zzh(zzb2);
    }

    public static void zzD(zzjw zzjwVar, Object obj, Object obj2) {
        zzjwVar.zzo(obj, zzjwVar.zze(zzjwVar.zzd(obj), zzjwVar.zzd(obj2)));
    }

    public static void zzE(Class cls) {
        Class cls2;
        if (!zzhf.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzF(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzc(i, list, z);
    }

    public static void zzG(int i, List list, zzko zzkoVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zze(i, list);
    }

    public static void zzH(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzg(i, list, z);
    }

    public static void zzI(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzj(i, list, z);
    }

    public static void zzJ(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzl(i, list, z);
    }

    public static void zzK(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzn(i, list, z);
    }

    public static void zzL(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzp(i, list, z);
    }

    public static void zzM(int i, List list, zzko zzkoVar, zzjc zzjcVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzfu) zzkoVar).zzq(i, list.get(i2), zzjcVar);
        }
    }

    public static void zzN(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzs(i, list, z);
    }

    public static void zzO(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzu(i, list, z);
    }

    public static void zzP(int i, List list, zzko zzkoVar, zzjc zzjcVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzfu) zzkoVar).zzv(i, list.get(i2), zzjcVar);
        }
    }

    public static void zzQ(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzy(i, list, z);
    }

    public static void zzR(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzA(i, list, z);
    }

    public static void zzS(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzC(i, list, z);
    }

    public static void zzT(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzE(i, list, z);
    }

    public static void zzU(int i, List list, zzko zzkoVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzH(i, list);
    }

    public static void zzV(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzJ(i, list, z);
    }

    public static void zzW(int i, List list, zzko zzkoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkoVar.zzL(i, list, z);
    }

    public static boolean zzX(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzft.zzy(i << 3) + 1);
    }

    public static int zzb(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzy = size * zzft.zzy(i << 3);
        for (int i2 = 0; i2 < list.size(); i2++) {
            int zzd2 = ((zzfi) list.get(i2)).zzd();
            zzy += zzft.zzy(zzd2) + zzd2;
        }
        return zzy;
    }

    public static int zzc(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzft.zzy(i << 3));
    }

    public static int zzd(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhg) {
            zzhg zzhgVar = (zzhg) list;
            i = 0;
            while (i2 < size) {
                i += zzft.zzu(zzhgVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzft.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int zze(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzft.zzy(i << 3) + 4);
    }

    public static int zzf(List list) {
        return list.size() * 4;
    }

    public static int zzg(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzft.zzy(i << 3) + 8);
    }

    public static int zzh(List list) {
        return list.size() * 8;
    }

    public static int zzi(int i, List list, zzjc zzjcVar) {
        int size = list.size();
        if (size != 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                i2 += zzft.zzt(i, (zzip) list.get(i3), zzjcVar);
            }
            return i2;
        }
        return 0;
    }

    public static int zzj(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzk(list) + (size * zzft.zzy(i << 3));
    }

    public static int zzk(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhg) {
            zzhg zzhgVar = (zzhg) list;
            i = 0;
            while (i2 < size) {
                i += zzft.zzu(zzhgVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzft.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int zzl(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzm(list) + (list.size() * zzft.zzy(i << 3));
    }

    public static int zzm(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzie) {
            zzie zzieVar = (zzie) list;
            i = 0;
            while (i2 < size) {
                i += zzft.zzz(zzieVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzft.zzz(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int zzn(int i, Object obj, zzjc zzjcVar) {
        if (obj instanceof zzhv) {
            int i2 = zzft.zzb;
            int zza2 = ((zzhv) obj).zza();
            return zzft.zzy(i << 3) + zzft.zzy(zza2) + zza2;
        }
        return zzft.zzy(i << 3) + zzft.zzw((zzip) obj, zzjcVar);
    }

    public static int zzo(int i, List list, zzjc zzjcVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzy = zzft.zzy(i << 3) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzhv) {
                int zza2 = ((zzhv) obj).zza();
                zzy += zzft.zzy(zza2) + zza2;
            } else {
                zzy += zzft.zzw((zzip) obj, zzjcVar);
            }
        }
        return zzy;
    }

    public static int zzp(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzq(list) + (size * zzft.zzy(i << 3));
    }

    public static int zzq(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhg) {
            zzhg zzhgVar = (zzhg) list;
            i = 0;
            while (i2 < size) {
                int zze = zzhgVar.zze(i2);
                i += zzft.zzy((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i += zzft.zzy((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    public static int zzr(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzs(list) + (size * zzft.zzy(i << 3));
    }

    public static int zzs(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzie) {
            zzie zzieVar = (zzie) list;
            i = 0;
            while (i2 < size) {
                long zze = zzieVar.zze(i2);
                i += zzft.zzz((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i += zzft.zzz((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    public static int zzt(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        boolean z = list instanceof zzhx;
        int zzy = zzft.zzy(i << 3) * size;
        if (z) {
            zzhx zzhxVar = (zzhx) list;
            while (i2 < size) {
                Object zzf = zzhxVar.zzf(i2);
                if (zzf instanceof zzfi) {
                    int zzd2 = ((zzfi) zzf).zzd();
                    zzy += zzft.zzy(zzd2) + zzd2;
                } else {
                    zzy += zzft.zzx((String) zzf);
                }
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzfi) {
                    int zzd3 = ((zzfi) obj).zzd();
                    zzy += zzft.zzy(zzd3) + zzd3;
                } else {
                    zzy += zzft.zzx((String) obj);
                }
                i2++;
            }
        }
        return zzy;
    }

    public static int zzu(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzv(list) + (size * zzft.zzy(i << 3));
    }

    public static int zzv(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhg) {
            zzhg zzhgVar = (zzhg) list;
            i = 0;
            while (i2 < size) {
                i += zzft.zzy(zzhgVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzft.zzy(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int zzw(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzx(list) + (size * zzft.zzy(i << 3));
    }

    public static int zzx(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzie) {
            zzie zzieVar = (zzie) list;
            i = 0;
            while (i2 < size) {
                i += zzft.zzz(zzieVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzft.zzz(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzjw zzy() {
        return zzc;
    }

    public static zzjw zzz() {
        return zzd;
    }
}
