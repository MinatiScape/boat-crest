package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class x2 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final x2 f9314a = new x2();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("options");
        zzbk zzbkVar = new zzbk();
        zzbkVar.zza(1);
        builder.withProperty(zzbkVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("latencyMs");
        zzbk zzbkVar2 = new zzbk();
        zzbkVar2.zza(2);
        builder2.withProperty(zzbkVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("burstCount");
        zzbk zzbkVar3 = new zzbk();
        zzbkVar3.zza(3);
        builder3.withProperty(zzbkVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("estimatedCaptureLatencyMs");
        zzbk zzbkVar4 = new zzbk();
        zzbkVar4.zza(4);
        builder4.withProperty(zzbkVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("errorCode");
        zzbk zzbkVar5 = new zzbk();
        zzbkVar5.zza(5);
        builder5.withProperty(zzbkVar5.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzli zzliVar = (zzli) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
