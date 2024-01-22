package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
final class zzgy implements zzin {
    private static final zzgy zza = new zzgy();

    private zzgy() {
    }

    public static zzgy zza() {
        return zza;
    }

    @Override // com.google.android.recaptcha.internal.zzin
    public final zzim zzb(Class cls) {
        if (zzhf.class.isAssignableFrom(cls)) {
            try {
                return (zzim) zzhf.zzr(cls.asSubclass(zzhf.class)).zzh(3, null, null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e);
            }
        }
        throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.recaptcha.internal.zzin
    public final boolean zzc(Class cls) {
        return zzhf.class.isAssignableFrom(cls);
    }
}
