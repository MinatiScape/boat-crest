package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class i2 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final i2 f9684a = new i2();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("eventType");
        zzae zzaeVar = new zzae();
        zzaeVar.zza(1);
        builder.withProperty(zzaeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("errorCode");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.zza(2);
        builder2.withProperty(zzaeVar2.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzjd zzjdVar = (zzjd) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}