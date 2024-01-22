package com.google.android.gms.internal.gtm;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* loaded from: classes8.dex */
public final class zzxy {
    public static final long zza;
    public static final boolean zzb;
    public static final Unsafe zzc;
    public static final Class<?> zzd;
    public static final boolean zze;
    public static final boolean zzf;
    public static final zzxx zzg;
    public static final boolean zzh;
    public static final boolean zzi;

    /* JADX WARN: Removed duplicated region for block: B:33:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x013b  */
    static {
        /*
            Method dump skipped, instructions count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzxy.<clinit>():void");
    }

    public static int zzA(Class<?> cls) {
        if (zzi) {
            return zzg.zzi(cls);
        }
        return -1;
    }

    public static Field zzB() {
        int i = zzsk.zza;
        Field zzC = zzC(Buffer.class, "effectiveDirectAddress");
        if (zzC == null) {
            Field zzC2 = zzC(Buffer.class, "address");
            if (zzC2 == null || zzC2.getType() != Long.TYPE) {
                return null;
            }
            return zzC2;
        }
        return zzC;
    }

    public static Field zzC(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzD(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        zzxx zzxxVar = zzg;
        int zzj = zzxxVar.zzj(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zzxxVar.zzn(obj, j2, ((255 & b) << i) | (zzj & (~(255 << i))));
    }

    public static void zzE(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        zzxx zzxxVar = zzg;
        int i = (((int) j) & 3) << 3;
        zzxxVar.zzn(obj, j2, ((255 & b) << i) | (zzxxVar.zzj(obj, j2) & (~(255 << i))));
    }

    public static double zza(Object obj, long j) {
        return zzg.zza(obj, j);
    }

    public static float zzb(Object obj, long j) {
        return zzg.zzb(obj, j);
    }

    public static int zzc(Object obj, long j) {
        return zzg.zzj(obj, j);
    }

    public static long zzd(Object obj, long j) {
        return zzg.zzk(obj, j);
    }

    public static <T> T zze(Class<T> cls) {
        try {
            return (T) zzc.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Object zzf(Object obj, long j) {
        return zzg.zzm(obj, j);
    }

    public static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzxu());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* bridge */ /* synthetic */ void zzh(Throwable th) {
        Logger logger = Logger.getLogger(zzxy.class.getName());
        Level level = Level.WARNING;
        String valueOf = String.valueOf(th);
        StringBuilder sb = new StringBuilder(valueOf.length() + 71);
        sb.append("platform method missing - proto runtime falling back to safer methods: ");
        sb.append(valueOf);
        logger.logp(level, "com.google.protobuf.UnsafeUtil", "logMissingMethod", sb.toString());
    }

    public static void zzm(Object obj, long j, boolean z) {
        zzg.zzc(obj, j, z);
    }

    public static void zzn(byte[] bArr, long j, byte b) {
        zzg.zzd(bArr, zza + j, b);
    }

    public static void zzo(Object obj, long j, double d) {
        zzg.zze(obj, j, d);
    }

    public static void zzp(Object obj, long j, float f) {
        zzg.zzf(obj, j, f);
    }

    public static void zzq(Object obj, long j, int i) {
        zzg.zzn(obj, j, i);
    }

    public static void zzr(Object obj, long j, long j2) {
        zzg.zzo(obj, j, j2);
    }

    public static void zzs(Object obj, long j, Object obj2) {
        zzg.zzp(obj, j, obj2);
    }

    public static /* bridge */ /* synthetic */ boolean zzt(Object obj, long j) {
        return ((byte) ((zzg.zzj(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    public static /* bridge */ /* synthetic */ boolean zzu(Object obj, long j) {
        return ((byte) ((zzg.zzj(obj, (-4) & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    public static boolean zzv(Class<?> cls) {
        int i = zzsk.zza;
        try {
            Class<?> cls2 = zzd;
            Class<?> cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class<?> cls4 = Integer.TYPE;
            cls2.getMethod("pokeInt", cls, cls4, cls3);
            cls2.getMethod("peekInt", cls, cls3);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls4, cls4);
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean zzw(Object obj, long j) {
        return zzg.zzg(obj, j);
    }

    public static boolean zzx() {
        return zzi;
    }

    public static boolean zzy() {
        return zzh;
    }

    public static int zzz(Class<?> cls) {
        if (zzi) {
            return zzg.zzh(cls);
        }
        return -1;
    }
}
