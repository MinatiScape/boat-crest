package com.google.android.gms.internal.gtm;

import java.lang.reflect.Field;
import sun.misc.Unsafe;
/* loaded from: classes8.dex */
public abstract class zzxx {
    public final Unsafe zza;

    public zzxx(Unsafe unsafe) {
        this.zza = unsafe;
    }

    public abstract double zza(Object obj, long j);

    public abstract float zzb(Object obj, long j);

    public abstract void zzc(Object obj, long j, boolean z);

    public abstract void zzd(Object obj, long j, byte b);

    public abstract void zze(Object obj, long j, double d);

    public abstract void zzf(Object obj, long j, float f);

    public abstract boolean zzg(Object obj, long j);

    public final int zzh(Class<?> cls) {
        return this.zza.arrayBaseOffset(cls);
    }

    public final int zzi(Class<?> cls) {
        return this.zza.arrayIndexScale(cls);
    }

    public final int zzj(Object obj, long j) {
        return this.zza.getInt(obj, j);
    }

    public final long zzk(Object obj, long j) {
        return this.zza.getLong(obj, j);
    }

    public final long zzl(Field field) {
        return this.zza.objectFieldOffset(field);
    }

    public final Object zzm(Object obj, long j) {
        return this.zza.getObject(obj, j);
    }

    public final void zzn(Object obj, long j, int i) {
        this.zza.putInt(obj, j, i);
    }

    public final void zzo(Object obj, long j, long j2) {
        this.zza.putLong(obj, j, j2);
    }

    public final void zzp(Object obj, long j, Object obj2) {
        this.zza.putObject(obj, j, obj2);
    }
}
