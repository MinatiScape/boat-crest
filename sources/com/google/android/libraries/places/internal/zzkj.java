package com.google.android.libraries.places.internal;

import kotlin.text.Typography;
/* loaded from: classes10.dex */
public enum zzkj implements zzsj {
    INVALID_MOVEMENT(0),
    UNKNOWN_MOVEMENT(1),
    STILL_MOVEMENT(2),
    WALKING_MOVEMENT(3),
    FAST_MOVING_MOVEMENT(4);
    
    private static final zzsi<zzkj> zzf = new zzsi<zzkj>() { // from class: com.google.android.libraries.places.internal.zzkl
    };
    private final int zzg;

    zzkj(int i) {
        this.zzg = i;
    }

    public static zzsl zzb() {
        return zzkk.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzkj.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.libraries.places.internal.zzsj
    public final int zza() {
        return this.zzg;
    }
}
