package com.google.android.libraries.places.internal;

import kotlin.text.Typography;
/* loaded from: classes10.dex */
public enum zzln implements zzsj {
    UNKNOWN_MODEL_VERSION(0),
    SIMPLE_TENSOR_FLOW_TWO_PASS_V1(1),
    DEFAULT_MODEL_V1(2),
    SIMPLE_TENSOR_FLOW_TWO_PASS_V2(3);
    
    private static final zzsi<zzln> zze = new zzsi<zzln>() { // from class: com.google.android.libraries.places.internal.zzlm
    };
    private final int zzf;

    zzln(int i) {
        this.zzf = i;
    }

    public static zzsl zzb() {
        return zzlo.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzln.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzf + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.libraries.places.internal.zzsj
    public final int zza() {
        return this.zzf;
    }
}
