package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
/* loaded from: classes10.dex */
final class zzsd implements zztp {
    private static final zzsd zza = new zzsd();

    private zzsd() {
    }

    public static zzsd zza() {
        return zza;
    }

    @Override // com.google.android.libraries.places.internal.zztp
    public final zztm zzb(Class<?> cls) {
        if (!zzsf.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (zztm) zzsf.zza((Class<zzsf>) cls.asSubclass(zzsf.class)).zza(zzsf.zzd.zzc, (Object) null, (Object) null);
        } catch (Exception e) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e);
        }
    }

    @Override // com.google.android.libraries.places.internal.zztp
    public final boolean zza(Class<?> cls) {
        return zzsf.class.isAssignableFrom(cls);
    }
}
