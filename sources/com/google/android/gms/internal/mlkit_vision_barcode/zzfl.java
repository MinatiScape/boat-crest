package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.NonNull;
import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes9.dex */
public final class zzfl {

    /* renamed from: a  reason: collision with root package name */
    public final Map f9549a;
    public final Map b;
    public final ObjectEncoder c;

    public zzfl(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f9549a = map;
        this.b = map2;
        this.c = objectEncoder;
    }

    @NonNull
    public final byte[] zza(@NonNull Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new f2(byteArrayOutputStream, this.f9549a, this.b, this.c).f(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
