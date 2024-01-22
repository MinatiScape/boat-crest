package com.google.android.gms.internal.firebase_messaging;

import com.google.firebase.encoders.annotations.Encodable;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
@Encodable
/* loaded from: classes7.dex */
public abstract class zze {

    /* renamed from: a  reason: collision with root package name */
    public static final zzae f8654a;

    static {
        zzad zzadVar = new zzad();
        zzd.zza.configure(zzadVar);
        f8654a = zzadVar.zza();
    }

    public static byte[] zza(Object obj) {
        zzae zzaeVar = f8654a;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            zzaeVar.zza(obj, byteArrayOutputStream);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static void zzb(Object obj, OutputStream outputStream) throws IOException {
        f8654a.zza(obj, outputStream);
    }

    public abstract MessagingClientEventExtension zzc();
}
