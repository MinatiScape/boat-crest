package com.google.android.gms.internal.mlkit_vision_common;

import androidx.annotation.NonNull;
import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzan {

    /* renamed from: a  reason: collision with root package name */
    public final Map f9784a;
    public final Map b;
    public final ObjectEncoder c;

    public zzan(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f9784a = map;
        this.b = map2;
        this.c = objectEncoder;
    }

    @NonNull
    public final byte[] zza(@NonNull Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new d(byteArrayOutputStream, this.f9784a, this.b, this.c).f(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
