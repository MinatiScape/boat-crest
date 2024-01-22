package com.google.android.gms.internal.firebase_ml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
/* loaded from: classes7.dex */
public abstract class zzhx {
    public final String a(Object obj, boolean z) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zzia zza = zza(byteArrayOutputStream, zziw.UTF_8);
        if (z) {
            zza.zzha();
        }
        zza.zzd(obj);
        zza.flush();
        return byteArrayOutputStream.toString("UTF-8");
    }

    public final String toString(Object obj) throws IOException {
        return a(obj, false);
    }

    public abstract zzia zza(OutputStream outputStream, Charset charset) throws IOException;

    public abstract zzib zza(InputStream inputStream) throws IOException;

    public abstract zzib zza(InputStream inputStream, Charset charset) throws IOException;

    public abstract zzib zzam(String str) throws IOException;

    public final String zzc(Object obj) throws IOException {
        return a(obj, true);
    }
}
