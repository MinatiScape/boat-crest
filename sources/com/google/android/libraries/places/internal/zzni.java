package com.google.android.libraries.places.internal;

import kotlin.text.Typography;
/* loaded from: classes10.dex */
public enum zzni implements zzsj {
    TYPE_UNSPECIFIED(0),
    ENTER(1),
    EXIT(2),
    DWELL_DEPRECATED(3),
    DWELL(4);
    
    private static final zzsi<zzni> zzf = new zzsi<zzni>() { // from class: com.google.android.libraries.places.internal.zznk
    };
    private final int zzg;

    zzni(int i) {
        this.zzg = i;
    }

    public static zzsl zzb() {
        return zznj.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzni.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.libraries.places.internal.zzsj
    public final int zza() {
        return this.zzg;
    }
}
