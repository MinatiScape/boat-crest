package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class e0 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final e0 f9183a = new e0();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("errorCode");
        zzbk zzbkVar = new zzbk();
        zzbkVar.zza(1);
        builder.withProperty(zzbkVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("imageInfo");
        zzbk zzbkVar2 = new zzbk();
        zzbkVar2.zza(2);
        builder2.withProperty(zzbkVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isColdCall");
        zzbk zzbkVar3 = new zzbk();
        zzbkVar3.zza(3);
        builder3.withProperty(zzbkVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("options");
        zzbk zzbkVar4 = new zzbk();
        zzbkVar4.zza(4);
        builder4.withProperty(zzbkVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("documentPresenceConfidence");
        zzbk zzbkVar5 = new zzbk();
        zzbkVar5.zza(5);
        builder5.withProperty(zzbkVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("documentCornerConfidence");
        zzbk zzbkVar6 = new zzbk();
        zzbkVar6.zza(6);
        builder6.withProperty(zzbkVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("documentRotationSuggestionDegrees");
        zzbk zzbkVar7 = new zzbk();
        zzbkVar7.zza(7);
        builder7.withProperty(zzbkVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("documentRotationSuggestionConfidence");
        zzbk zzbkVar8 = new zzbk();
        zzbkVar8.zza(8);
        builder8.withProperty(zzbkVar8.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzce zzceVar = (zzce) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}