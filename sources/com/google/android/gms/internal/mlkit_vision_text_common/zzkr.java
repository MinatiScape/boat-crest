package com.google.android.gms.internal.mlkit_vision_text_common;
/* loaded from: classes6.dex */
public enum zzkr implements zzcx {
    TYPE_UNKNOWN(0),
    TYPE_THIN(1),
    TYPE_THICK(2),
    TYPE_GMV(3);
    
    private final int zzf;

    zzkr(int i) {
        this.zzf = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcx
    public final int zza() {
        return this.zzf;
    }
}
