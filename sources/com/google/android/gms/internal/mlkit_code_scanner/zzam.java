package com.google.android.gms.internal.mlkit_code_scanner;

import androidx.annotation.NonNull;
import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzam {

    /* renamed from: a  reason: collision with root package name */
    public final Map f9140a;
    public final Map b;
    public final ObjectEncoder c;

    public zzam(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f9140a = map;
        this.b = map2;
        this.c = objectEncoder;
    }

    @NonNull
    public final byte[] zza(@NonNull Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new d(byteArrayOutputStream, this.f9140a, this.b, this.c).f(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
