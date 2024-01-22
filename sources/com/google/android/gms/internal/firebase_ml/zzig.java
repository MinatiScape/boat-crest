package com.google.android.gms.internal.firebase_ml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
/* loaded from: classes7.dex */
public final class zzig extends zzhx {
    public static zzig zzht() {
        return u0.f8736a;
    }

    public final zzib b(Reader reader) {
        return new w0(this, new zzsz(reader));
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhx
    public final zzib zza(InputStream inputStream) {
        return b(new InputStreamReader(inputStream, zziw.UTF_8));
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhx
    public final zzib zzam(String str) {
        return b(new StringReader(str));
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhx
    public final zzib zza(InputStream inputStream, Charset charset) {
        if (charset == null) {
            return zza(inputStream);
        }
        return b(new InputStreamReader(inputStream, charset));
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhx
    public final zzia zza(OutputStream outputStream, Charset charset) {
        return new t0(this, new zzte(new OutputStreamWriter(outputStream, charset)));
    }
}
