package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class q5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final q5 f9269a = new q5();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("xMin");
        zzbk zzbkVar = new zzbk();
        zzbkVar.zza(1);
        builder.withProperty(zzbkVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("yMin");
        zzbk zzbkVar2 = new zzbk();
        zzbkVar2.zza(2);
        builder2.withProperty(zzbkVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("xMax");
        zzbk zzbkVar3 = new zzbk();
        zzbkVar3.zza(3);
        builder3.withProperty(zzbkVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("yMax");
        zzbk zzbkVar4 = new zzbk();
        zzbkVar4.zza(4);
        builder4.withProperty(zzbkVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("confidenceScore");
        zzbk zzbkVar5 = new zzbk();
        zzbkVar5.zza(5);
        builder5.withProperty(zzbkVar5.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzoh zzohVar = (zzoh) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}