package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class z2 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final z2 f9780a = new z2();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("inferenceCommonLogEvent");
        zzae zzaeVar = new zzae();
        zzaeVar.zza(1);
        builder.withProperty(zzaeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("imageInfo");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.zza(2);
        builder2.withProperty(zzaeVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("captionCount");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.zza(4);
        builder3.withProperty(zzaeVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("highestScore");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.zza(5);
        builder4.withProperty(zzaeVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("imageType");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.zza(6);
        builder5.withProperty(zzaeVar5.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzjs zzjsVar = (zzjs) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
