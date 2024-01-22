package com.google.android.gms.internal.mlkit_vision_text_common;

import androidx.annotation.NonNull;
import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes6.dex */
public final class zzde {

    /* renamed from: a  reason: collision with root package name */
    public final Map f9929a;
    public final Map b;
    public final ObjectEncoder c;

    public zzde(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f9929a = map;
        this.b = map2;
        this.c = objectEncoder;
    }

    @NonNull
    public final byte[] zza(@NonNull Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new b1(byteArrayOutputStream, this.f9929a, this.b, this.c).f(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
