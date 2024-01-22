package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class zzgx extends RuntimeException {
    public zzgx(zzfo zzfoVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzeo zza() {
        return new zzeo(getMessage());
    }
}
