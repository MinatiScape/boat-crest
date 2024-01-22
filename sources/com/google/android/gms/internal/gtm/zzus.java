package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzus implements zzwi {
    public static final zzus zza = new zzus();

    public static zzus zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.gtm.zzwi
    public final zzwh zzb(Class<?> cls) {
        if (!zzuz.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (zzwh) zzuz.zzab(cls.asSubclass(zzuz.class)).zzb(3, null, null);
        } catch (Exception e) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e);
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzwi
    public final boolean zzc(Class<?> cls) {
        return zzuz.class.isAssignableFrom(cls);
    }
}
