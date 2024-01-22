package com.google.android.libraries.places.internal;

import kotlin.text.Typography;
/* loaded from: classes10.dex */
public enum zzpo implements zzsj {
    UNDEFINED(0),
    INFERRED_HOME(1),
    INFERRED_WORK(2);
    
    private static final zzsi<zzpo> zzd = new zzsi<zzpo>() { // from class: com.google.android.libraries.places.internal.zzpn
    };
    private final int zze;

    zzpo(int i) {
        this.zze = i;
    }

    public static zzsl zzb() {
        return zzpq.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzpo.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zze + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.libraries.places.internal.zzsj
    public final int zza() {
        return this.zze;
    }
}
