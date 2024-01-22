package com.google.android.gms.internal.firebase_messaging;

import androidx.annotation.NonNull;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ValueEncoder;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
/* loaded from: classes7.dex */
public final class zzae {

    /* renamed from: a  reason: collision with root package name */
    public final Map<Class<?>, ObjectEncoder<?>> f8653a;
    public final Map<Class<?>, ValueEncoder<?>> b;
    public final ObjectEncoder<Object> c;

    public zzae(Map<Class<?>, ObjectEncoder<?>> map, Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder) {
        this.f8653a = map;
        this.b = map2;
        this.c = objectEncoder;
    }

    public final void zza(@NonNull Object obj, @NonNull OutputStream outputStream) throws IOException {
        new c(outputStream, this.f8653a, this.b, this.c).f(obj);
    }
}
