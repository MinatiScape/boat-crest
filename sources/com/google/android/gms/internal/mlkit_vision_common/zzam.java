package com.google.android.gms.internal.mlkit_vision_common;

import androidx.annotation.NonNull;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.config.EncoderConfig;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzam implements EncoderConfig {
    public static final ObjectEncoder d = new ObjectEncoder() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzal
        @Override // com.google.firebase.encoders.ObjectEncoder
        public final void encode(Object obj, Object obj2) {
            ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
            int i = zzam.zza;
            throw new EncodingException("Couldn't find encoder for type ".concat(String.valueOf(obj.getClass().getCanonicalName())));
        }
    };
    public static final /* synthetic */ int zza = 0;

    /* renamed from: a  reason: collision with root package name */
    public final Map f9783a = new HashMap();
    public final Map b = new HashMap();
    public final ObjectEncoder c = d;

    @Override // com.google.firebase.encoders.config.EncoderConfig
    @NonNull
    public final /* bridge */ /* synthetic */ EncoderConfig registerEncoder(@NonNull Class cls, @NonNull ObjectEncoder objectEncoder) {
        this.f9783a.put(cls, objectEncoder);
        this.b.remove(cls);
        return this;
    }

    public final zzan zza() {
        return new zzan(new HashMap(this.f9783a), new HashMap(this.b), this.c);
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    @NonNull
    public final /* bridge */ /* synthetic */ EncoderConfig registerEncoder(@NonNull Class cls, @NonNull ValueEncoder valueEncoder) {
        this.b.put(cls, valueEncoder);
        this.f9783a.remove(cls);
        return this;
    }
}
