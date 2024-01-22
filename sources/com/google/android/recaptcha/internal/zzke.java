package com.google.android.recaptcha.internal;

import sun.misc.Unsafe;
/* loaded from: classes10.dex */
final class zzke extends zzkf {
    public zzke(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.recaptcha.internal.zzkf
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    @Override // com.google.android.recaptcha.internal.zzkf
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    @Override // com.google.android.recaptcha.internal.zzkf
    public final void zzc(Object obj, long j, boolean z) {
        if (zzkg.zzb) {
            zzkg.zzD(obj, j, r3 ? (byte) 1 : (byte) 0);
        } else {
            zzkg.zzE(obj, j, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzkf
    public final void zzd(Object obj, long j, byte b) {
        if (zzkg.zzb) {
            zzkg.zzD(obj, j, b);
        } else {
            zzkg.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzkf
    public final void zze(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.recaptcha.internal.zzkf
    public final void zzf(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.recaptcha.internal.zzkf
    public final boolean zzg(Object obj, long j) {
        if (zzkg.zzb) {
            return zzkg.zzt(obj, j);
        }
        return zzkg.zzu(obj, j);
    }
}
