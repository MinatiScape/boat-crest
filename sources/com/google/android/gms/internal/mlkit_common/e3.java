package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class e3 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final e3 f9186a = new e3();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("modelInfo");
        zzbk zzbkVar = new zzbk();
        zzbkVar.zza(1);
        b = builder.withProperty(zzbkVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("initialDownloadConditions");
        zzbk zzbkVar2 = new zzbk();
        zzbkVar2.zza(2);
        c = builder2.withProperty(zzbkVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("updateDownloadConditions");
        zzbk zzbkVar3 = new zzbk();
        zzbkVar3.zza(3);
        d = builder3.withProperty(zzbkVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("isModelUpdateEnabled");
        zzbk zzbkVar4 = new zzbk();
        zzbkVar4.zza(4);
        e = builder4.withProperty(zzbkVar4.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, ((zzmd) obj).zza());
        objectEncoderContext.add(c, (Object) null);
        objectEncoderContext.add(d, (Object) null);
        objectEncoderContext.add(e, (Object) null);
    }
}
