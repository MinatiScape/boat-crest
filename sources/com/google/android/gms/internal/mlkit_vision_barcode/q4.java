package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class q4 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final q4 f9481a = new q4();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("useCases");
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(1);
        builder.withProperty(zzfcVar.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzoh zzohVar = (zzoh) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
