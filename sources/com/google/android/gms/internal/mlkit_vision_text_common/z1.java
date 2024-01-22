package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class z1 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final z1 f9922a = new z1();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;
    public static final FieldDescriptor f;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("errorCode");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        b = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("hasResult");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        c = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isColdCall");
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(3);
        d = builder3.withProperty(zzcvVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("imageInfo");
        zzcv zzcvVar4 = new zzcv();
        zzcvVar4.zza(4);
        e = builder4.withProperty(zzcvVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("recognizerOptions");
        zzcv zzcvVar5 = new zzcv();
        zzcvVar5.zza(5);
        f = builder5.withProperty(zzcvVar5.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzee zzeeVar = (zzee) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzeeVar.zza());
        objectEncoderContext.add(c, (Object) null);
        objectEncoderContext.add(d, zzeeVar.zzc());
        objectEncoderContext.add(e, (Object) null);
        objectEncoderContext.add(f, zzeeVar.zzb());
    }
}
