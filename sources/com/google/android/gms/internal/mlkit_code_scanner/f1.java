package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class f1 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final f1 f9002a = new f1();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("supportedFormats");
        zzad zzadVar = new zzad();
        zzadVar.zza(1);
        b = builder.withProperty(zzadVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("durationMs");
        zzad zzadVar2 = new zzad();
        zzadVar2.zza(2);
        c = builder2.withProperty(zzadVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("errorCode");
        zzad zzadVar3 = new zzad();
        zzadVar3.zza(3);
        d = builder3.withProperty(zzadVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("allowManualInput");
        zzad zzadVar4 = new zzad();
        zzadVar4.zza(4);
        e = builder4.withProperty(zzadVar4.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzix zzixVar = (zzix) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzixVar.zzc());
        objectEncoderContext.add(c, zzixVar.zzd());
        objectEncoderContext.add(d, zzixVar.zza());
        objectEncoderContext.add(e, zzixVar.zzb());
    }
}
