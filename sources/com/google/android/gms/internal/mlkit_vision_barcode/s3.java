package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class s3 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final s3 f9493a = new s3();
    public static final FieldDescriptor b;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("format");
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(1);
        b = builder.withProperty(zzfcVar.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        ((ObjectEncoderContext) obj2).add(b, ((zzth) obj).zza());
    }
}
