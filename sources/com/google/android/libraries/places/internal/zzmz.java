package com.google.android.libraries.places.internal;

import kotlin.text.Typography;
/* loaded from: classes10.dex */
public enum zzmz implements zzsj {
    SUCCESS(0),
    GEOFENCE_NOT_AVAILABLE(1000),
    GEOFENCE_TOO_MANY_GEOFENCES(1001),
    GEOFENCE_TOO_MANY_PENDING_INTENTS(1002),
    GEOFENCE_CURRENT_LOCATION_UNKNOWN(1003);
    
    private static final zzsi<zzmz> zzf = new zzsi<zzmz>() { // from class: com.google.android.libraries.places.internal.zznb
    };
    private final int zzg;

    zzmz(int i) {
        this.zzg = i;
    }

    public static zzsl zzb() {
        return zzna.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzmz.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.libraries.places.internal.zzsj
    public final int zza() {
        return this.zzg;
    }
}
