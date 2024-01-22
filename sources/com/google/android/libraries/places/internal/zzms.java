package com.google.android.libraries.places.internal;

import kotlin.text.Typography;
/* loaded from: classes10.dex */
public enum zzms implements zzsj {
    UNKNOWN(0),
    TYPE_ADD(1),
    TYPE_REMOVE(2);
    
    private static final zzsi<zzms> zzd = new zzsi<zzms>() { // from class: com.google.android.libraries.places.internal.zzmr
    };
    private final int zze;

    zzms(int i) {
        this.zze = i;
    }

    public static zzsl zzb() {
        return zzmu.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzms.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zze + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.libraries.places.internal.zzsj
    public final int zza() {
        return this.zze;
    }
}
