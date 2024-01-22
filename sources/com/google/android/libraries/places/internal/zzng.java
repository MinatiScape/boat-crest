package com.google.android.libraries.places.internal;

import kotlin.text.Typography;
/* loaded from: classes10.dex */
public enum zzng implements zzsj {
    OTHERS(0),
    NEARBY_ALERT(1),
    GET_CURRENT_PLACE(2),
    GEO_DATA_API(3);
    
    private static final zzsi<zzng> zze = new zzsi<zzng>() { // from class: com.google.android.libraries.places.internal.zznf
    };
    private final int zzf;

    zzng(int i) {
        this.zzf = i;
    }

    public static zzsl zzb() {
        return zznh.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzng.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzf + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.libraries.places.internal.zzsj
    public final int zza() {
        return this.zzf;
    }
}
