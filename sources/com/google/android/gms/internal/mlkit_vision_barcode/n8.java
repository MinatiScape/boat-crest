package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class n8 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final n8 f9462a = new n8();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("result");
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(1);
        builder.withProperty(zzfcVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("ok");
        zzfc zzfcVar2 = new zzfc();
        zzfcVar2.zza(2);
        builder2.withProperty(zzfcVar2.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zztd zztdVar = (zztd) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
