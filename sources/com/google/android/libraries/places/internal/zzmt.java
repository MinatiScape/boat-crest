package com.google.android.libraries.places.internal;

import kotlin.text.Typography;
/* loaded from: classes10.dex */
public enum zzmt implements zzsj {
    HOME(0),
    WORK(1),
    NICKNAME(2);
    
    private static final zzsi<zzmt> zzd = new zzsi<zzmt>() { // from class: com.google.android.libraries.places.internal.zzmw
    };
    private final int zze;

    zzmt(int i) {
        this.zze = i;
    }

    public static zzsl zzb() {
        return zzmv.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzmt.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zze + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.libraries.places.internal.zzsj
    public final int zza() {
        return this.zze;
    }
}
