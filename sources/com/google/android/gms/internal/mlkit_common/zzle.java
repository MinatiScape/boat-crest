package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes8.dex */
public enum zzle implements zzbm {
    UNKNOWN(0),
    TRANSLATE(1);
    
    private final int zzd;

    zzle(int i) {
        this.zzd = i;
    }

    public static zzle zzb(int i) {
        zzle[] values;
        for (zzle zzleVar : values()) {
            if (zzleVar.zzd == i) {
                return zzleVar;
            }
        }
        return UNKNOWN;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbm
    public final int zza() {
        return this.zzd;
    }
}
