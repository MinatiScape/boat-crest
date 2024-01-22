package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes8.dex */
public final class zzwz {
    public static final Class<?> zza;
    public static final zzxo<?, ?> zzb;
    public static final zzxo<?, ?> zzc;
    public static final zzxo<?, ?> zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzab(false);
        zzc = zzab(true);
        zzd = new zzxq();
    }

    public static zzxo<?, ?> zzA() {
        return zzc;
    }

    public static zzxo<?, ?> zzB() {
        return zzd;
    }

    public static <UT, UB> UB zzC(int i, List<Integer> list, zzvd zzvdVar, UB ub, zzxo<UT, UB> zzxoVar) {
        if (zzvdVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzvdVar.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = (UB) zzD(i, intValue, ub, zzxoVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return ub;
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzvdVar.zza(intValue2)) {
                    ub = (UB) zzD(i, intValue2, ub, zzxoVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static <UT, UB> UB zzD(int i, int i2, UB ub, zzxo<UT, UB> zzxoVar) {
        if (ub == null) {
            ub = zzxoVar.zzf();
        }
        zzxoVar.zzl(ub, i, i2);
        return ub;
    }

    public static <T, FT extends zzun<FT>> void zzE(zzuk<FT> zzukVar, T t, T t2) {
        zzuo<FT> zzb2 = zzukVar.zzb(t2);
        if (zzb2.zza.isEmpty()) {
            return;
        }
        zzukVar.zzc(t).zzh(zzb2);
    }

    public static <T, UT, UB> void zzF(zzxo<UT, UB> zzxoVar, T t, T t2) {
        zzxoVar.zzo(t, zzxoVar.zze(zzxoVar.zzd(t), zzxoVar.zzd(t2)));
    }

    public static void zzG(Class<?> cls) {
        Class<?> cls2;
        if (!zzuz.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static boolean zzH(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static <T> void zzI(zzwf zzwfVar, T t, T t2, long j) {
        zzxy.zzs(t, j, zzwf.zzc(zzxy.zzf(t, j), zzxy.zzf(t2, j)));
    }

    public static void zzJ(int i, List<Boolean> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzc(i, list, z);
    }

    public static void zzK(int i, List<zztd> list, zztp zztpVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zze(i, list);
    }

    public static void zzL(int i, List<Double> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzg(i, list, z);
    }

    public static void zzM(int i, List<Integer> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzj(i, list, z);
    }

    public static void zzN(int i, List<Integer> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzl(i, list, z);
    }

    public static void zzO(int i, List<Long> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzn(i, list, z);
    }

    public static void zzP(int i, List<Float> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzp(i, list, z);
    }

    public static void zzQ(int i, List<?> list, zztp zztpVar, zzwx zzwxVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            zztpVar.zzq(i, list.get(i2), zzwxVar);
        }
    }

    public static void zzR(int i, List<Integer> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzs(i, list, z);
    }

    public static void zzS(int i, List<Long> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzu(i, list, z);
    }

    public static void zzT(int i, List<?> list, zztp zztpVar, zzwx zzwxVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            zztpVar.zzv(i, list.get(i2), zzwxVar);
        }
    }

    public static void zzU(int i, List<Integer> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzy(i, list, z);
    }

    public static void zzV(int i, List<Long> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzA(i, list, z);
    }

    public static void zzW(int i, List<Integer> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzC(i, list, z);
    }

    public static void zzX(int i, List<Long> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzE(i, list, z);
    }

    public static void zzY(int i, List<String> list, zztp zztpVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzH(i, list);
    }

    public static void zzZ(int i, List<Integer> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzJ(i, list, z);
    }

    public static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzto.zzD(i << 3) + 1);
    }

    public static void zzaa(int i, List<Long> list, zztp zztpVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zztpVar.zzL(i, list, z);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:5:0x0004
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static com.google.android.gms.internal.gtm.zzxo<?, ?> zzab(boolean r6) {
        /*
            r0 = 0
            java.lang.Class<com.google.protobuf.z0> r1 = com.google.protobuf.z0.class
            goto L5
        L4:
            r1 = r0
        L5:
            if (r1 != 0) goto L8
            return r0
        L8:
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L23
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch: java.lang.Throwable -> L23
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L23
            java.lang.reflect.Constructor r1 = r1.getConstructor(r3)     // Catch: java.lang.Throwable -> L23
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L23
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch: java.lang.Throwable -> L23
            r2[r5] = r6     // Catch: java.lang.Throwable -> L23
            java.lang.Object r6 = r1.newInstance(r2)     // Catch: java.lang.Throwable -> L23
            com.google.android.gms.internal.gtm.zzxo r6 = (com.google.android.gms.internal.gtm.zzxo) r6     // Catch: java.lang.Throwable -> L23
            return r6
        L23:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzwz.zzab(boolean):com.google.android.gms.internal.gtm.zzxo");
    }

    public static int zzb(List<?> list) {
        return list.size();
    }

    public static int zzc(int i, List<zztd> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzC = size * zzto.zzC(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzC += zzto.zzu(list.get(i2));
        }
        return zzC;
    }

    public static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzto.zzC(i));
    }

    public static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzva) {
            zzva zzvaVar = (zzva) list;
            i = 0;
            while (i2 < size) {
                i += zzto.zzx(zzvaVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzto.zzx(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int zzf(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzto.zzD(i << 3) + 4);
    }

    public static int zzg(List<?> list) {
        return list.size() * 4;
    }

    public static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzto.zzD(i << 3) + 8);
    }

    public static int zzi(List<?> list) {
        return list.size() * 8;
    }

    public static int zzj(int i, List<zzwk> list, zzwx zzwxVar) {
        int size = list.size();
        if (size != 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                i2 += zzto.zzv(i, list.get(i3), zzwxVar);
            }
            return i2;
        }
        return 0;
    }

    public static int zzk(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzto.zzC(i));
    }

    public static int zzl(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzva) {
            zzva zzvaVar = (zzva) list;
            i = 0;
            while (i2 < size) {
                i += zzto.zzx(zzvaVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzto.zzx(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int zzm(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzto.zzC(i));
    }

    public static int zzn(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvz) {
            zzvz zzvzVar = (zzvz) list;
            i = 0;
            while (i2 < size) {
                i += zzto.zzE(zzvzVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzto.zzE(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int zzo(int i, Object obj, zzwx zzwxVar) {
        if (obj instanceof zzvq) {
            int zzD = zzto.zzD(i << 3);
            int zza2 = ((zzvq) obj).zza();
            return zzD + zzto.zzD(zza2) + zza2;
        }
        return zzto.zzD(i << 3) + zzto.zzA((zzwk) obj, zzwxVar);
    }

    public static int zzp(int i, List<?> list, zzwx zzwxVar) {
        int zzA;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzC = zzto.zzC(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzvq) {
                zzA = zzto.zzy((zzvq) obj);
            } else {
                zzA = zzto.zzA((zzwk) obj, zzwxVar);
            }
            zzC += zzA;
        }
        return zzC;
    }

    public static int zzq(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzto.zzC(i));
    }

    public static int zzr(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzva) {
            zzva zzvaVar = (zzva) list;
            i = 0;
            while (i2 < size) {
                int zze = zzvaVar.zze(i2);
                i += zzto.zzD((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                int intValue = list.get(i2).intValue();
                i += zzto.zzD((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    public static int zzs(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzto.zzC(i));
    }

    public static int zzt(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvz) {
            zzvz zzvzVar = (zzvz) list;
            i = 0;
            while (i2 < size) {
                long zze = zzvzVar.zze(i2);
                i += zzto.zzE((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                long longValue = list.get(i2).longValue();
                i += zzto.zzE((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    public static int zzu(int i, List<?> list) {
        int zzB;
        int zzB2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzC = zzto.zzC(i) * size;
        if (list instanceof zzvs) {
            zzvs zzvsVar = (zzvs) list;
            while (i2 < size) {
                Object zzf = zzvsVar.zzf(i2);
                if (zzf instanceof zztd) {
                    zzB2 = zzto.zzu((zztd) zzf);
                } else {
                    zzB2 = zzto.zzB((String) zzf);
                }
                zzC += zzB2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zztd) {
                    zzB = zzto.zzu((zztd) obj);
                } else {
                    zzB = zzto.zzB((String) obj);
                }
                zzC += zzB;
                i2++;
            }
        }
        return zzC;
    }

    public static int zzv(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzto.zzC(i));
    }

    public static int zzw(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzva) {
            zzva zzvaVar = (zzva) list;
            i = 0;
            while (i2 < size) {
                i += zzto.zzD(zzvaVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzto.zzD(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int zzx(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzto.zzC(i));
    }

    public static int zzy(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvz) {
            zzvz zzvzVar = (zzvz) list;
            i = 0;
            while (i2 < size) {
                i += zzto.zzE(zzvzVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzto.zzE(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzxo<?, ?> zzz() {
        return zzb;
    }
}
