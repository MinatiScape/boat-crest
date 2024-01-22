package com.google.android.gms.internal.firebase_ml;

import com.facebook.stetho.inspector.network.DecompressionHelper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
/* loaded from: classes7.dex */
public final class zzgs implements zzgw {
    @Override // com.google.android.gms.internal.firebase_ml.zzgw
    public final String getName() {
        return DecompressionHelper.GZIP_ENCODING;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzgw
    public final void zza(zzjq zzjqVar, OutputStream outputStream) throws IOException {
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(new m0(this, outputStream));
        zzjqVar.writeTo(gZIPOutputStream);
        gZIPOutputStream.close();
    }
}
