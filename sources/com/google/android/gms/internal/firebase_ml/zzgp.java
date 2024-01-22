package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes7.dex */
public final class zzgp implements zzgt {
    @Override // com.google.android.gms.internal.firebase_ml.zzgt
    public final long getLength() throws IOException {
        return 0L;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzgt
    public final String getType() {
        return null;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzjq
    public final void writeTo(OutputStream outputStream) throws IOException {
        outputStream.flush();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzgt
    public final boolean zzfr() {
        return true;
    }
}
