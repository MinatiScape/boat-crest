package com.google.android.libraries.places.internal;

import kotlin.text.Typography;
/* loaded from: classes10.dex */
public enum zzkh implements zzsj {
    INVALID_STATE(0),
    NOT_INITIALIZED(1),
    NO_GEOFENCE(2),
    DISABLED(3),
    HAS_GEOFENCE(4),
    UNAVAILABLE_ACTIVITY(5),
    UNKNOWN_ACTIVITY(6),
    STILL_ACTIVITY(7),
    WALKING_ACTIVITY(8),
    FAST_MOVING_ACTIVITY(9),
    BACKGROUND_USER(10),
    CHRE_GEOFENCING(11);
    
    private static final zzsi<zzkh> zzm = new zzsi<zzkh>() { // from class: com.google.android.libraries.places.internal.zzkg
    };
    private final int zzn;

    zzkh(int i) {
        this.zzn = i;
    }

    public static zzsl zzb() {
        return zzki.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzkh.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzn + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.libraries.places.internal.zzsj
    public final int zza() {
        return this.zzn;
    }
}
