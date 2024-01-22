package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class a2 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final a2 f9156a = new a2();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("modelType");
        zzbk zzbkVar = new zzbk();
        zzbkVar.zza(1);
        b = builder.withProperty(zzbkVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isSuccessful");
        zzbk zzbkVar2 = new zzbk();
        zzbkVar2.zza(2);
        c = builder2.withProperty(zzbkVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("modelName");
        zzbk zzbkVar3 = new zzbk();
        zzbkVar3.zza(3);
        d = builder3.withProperty(zzbkVar3.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzkk zzkkVar = (zzkk) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzkkVar.zza());
        objectEncoderContext.add(c, zzkkVar.zzb());
        objectEncoderContext.add(d, (Object) null);
    }
}
