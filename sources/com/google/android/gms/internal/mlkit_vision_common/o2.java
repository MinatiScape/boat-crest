package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class o2 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final o2 f9717a = new o2();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("errorCode");
        zzae zzaeVar = new zzae();
        zzaeVar.zza(1);
        builder.withProperty(zzaeVar.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzjh zzjhVar = (zzjh) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
