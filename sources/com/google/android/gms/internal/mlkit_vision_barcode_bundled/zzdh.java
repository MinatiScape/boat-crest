package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
/* loaded from: classes8.dex */
public final class zzdh extends IOException {
    public zzdh() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    public zzdh(String str, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.valueOf(str)), th);
    }

    public zzdh(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
