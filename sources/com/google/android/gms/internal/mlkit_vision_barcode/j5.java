package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class j5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final j5 f9428a = new j5();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("options");
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(1);
        builder.withProperty(zzfcVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("latencyMs");
        zzfc zzfcVar2 = new zzfc();
        zzfcVar2.zza(2);
        builder2.withProperty(zzfcVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("burstCount");
        zzfc zzfcVar3 = new zzfc();
        zzfcVar3.zza(3);
        builder3.withProperty(zzfcVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("estimatedCaptureLatencyMs");
        zzfc zzfcVar4 = new zzfc();
        zzfcVar4.zza(4);
        builder4.withProperty(zzfcVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("errorCode");
        zzfc zzfcVar5 = new zzfc();
        zzfcVar5.zza(5);
        builder5.withProperty(zzfcVar5.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzpe zzpeVar = (zzpe) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}