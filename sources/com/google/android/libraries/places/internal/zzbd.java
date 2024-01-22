package com.google.android.libraries.places.internal;

import android.graphics.Bitmap;
/* loaded from: classes10.dex */
public final class zzbd implements zzan<zzbb> {
    private Bitmap zza;

    @Override // com.google.android.libraries.places.internal.zzan
    public final zzan<zzbb> zza(Bitmap bitmap) {
        this.zza = bitmap;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzan
    public final /* synthetic */ zzbb zza() {
        zzft.zzb(this.zza != null, "Photo must be set to non-null value.");
        return new zzbb(this.zza);
    }
}
