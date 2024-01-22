package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class o5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final o5 f9066a = new o5();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(DirectionsCriteria.METRIC);
        zzad zzadVar = new zzad();
        zzadVar.zza(1);
        builder.withProperty(zzadVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("result");
        zzad zzadVar2 = new zzad();
        zzadVar2.zza(2);
        builder2.withProperty(zzadVar2.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzni zzniVar = (zzni) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
