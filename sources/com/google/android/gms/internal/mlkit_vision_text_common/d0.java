package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.Serializable;
/* loaded from: classes6.dex */
public final class d0 extends n implements Serializable {
    public final Object zza;
    public final Object zzb;

    public d0(Object obj, Object obj2) {
        this.zza = obj;
        this.zzb = obj2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.n, java.util.Map.Entry
    public final Object getKey() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.n, java.util.Map.Entry
    public final Object getValue() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.n, java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
