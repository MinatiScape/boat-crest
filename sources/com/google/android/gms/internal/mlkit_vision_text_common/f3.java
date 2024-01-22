package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class f3 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final f3 f9824a = new f3();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("imageFormat");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        b = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("originalImageSize");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        c = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("compressedImageSize");
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(3);
        d = builder3.withProperty(zzcvVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("isOdmlImage");
        zzcv zzcvVar4 = new zzcv();
        zzcvVar4.zza(4);
        e = builder4.withProperty(zzcvVar4.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzkh zzkhVar = (zzkh) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzkhVar.zza());
        objectEncoderContext.add(c, zzkhVar.zzb());
        objectEncoderContext.add(d, (Object) null);
        objectEncoderContext.add(e, (Object) null);
    }
}
