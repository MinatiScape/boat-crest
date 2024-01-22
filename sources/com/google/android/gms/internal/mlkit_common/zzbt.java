package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.NonNull;
import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzbt {

    /* renamed from: a  reason: collision with root package name */
    public final Map f9335a;
    public final Map b;
    public final ObjectEncoder c;

    public zzbt(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f9335a = map;
        this.b = map2;
        this.c = objectEncoder;
    }

    @NonNull
    public final byte[] zza(@NonNull Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new s(byteArrayOutputStream, this.f9335a, this.b, this.c).f(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
