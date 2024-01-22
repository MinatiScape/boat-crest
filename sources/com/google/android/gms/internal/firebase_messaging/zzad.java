package com.google.android.gms.internal.firebase_messaging;

import androidx.annotation.NonNull;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.config.EncoderConfig;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public final class zzad implements EncoderConfig<zzad> {
    public static final ObjectEncoder<Object> d = d.f8644a;
    public static final /* synthetic */ int zza = 0;

    /* renamed from: a  reason: collision with root package name */
    public final Map<Class<?>, ObjectEncoder<?>> f8652a = new HashMap();
    public final Map<Class<?>, ValueEncoder<?>> b = new HashMap();
    public final ObjectEncoder<Object> c = d;

    @Override // com.google.firebase.encoders.config.EncoderConfig
    @NonNull
    public final /* bridge */ /* synthetic */ zzad registerEncoder(@NonNull Class cls, @NonNull ObjectEncoder objectEncoder) {
        this.f8652a.put(cls, objectEncoder);
        this.b.remove(cls);
        return this;
    }

    public final zzae zza() {
        return new zzae(new HashMap(this.f8652a), new HashMap(this.b), this.c);
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    @NonNull
    public final /* bridge */ /* synthetic */ zzad registerEncoder(@NonNull Class cls, @NonNull ValueEncoder valueEncoder) {
        this.b.put(cls, valueEncoder);
        this.f8652a.remove(cls);
        return this;
    }
}
