package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class s4 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final s4 f9494a = new s4();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;
    public static final FieldDescriptor f;
    public static final FieldDescriptor g;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("maxMs");
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(1);
        b = builder.withProperty(zzfcVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("minMs");
        zzfc zzfcVar2 = new zzfc();
        zzfcVar2.zza(2);
        c = builder2.withProperty(zzfcVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("avgMs");
        zzfc zzfcVar3 = new zzfc();
        zzfcVar3.zza(3);
        d = builder3.withProperty(zzfcVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("firstQuartileMs");
        zzfc zzfcVar4 = new zzfc();
        zzfcVar4.zza(4);
        e = builder4.withProperty(zzfcVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("medianMs");
        zzfc zzfcVar5 = new zzfc();
        zzfcVar5.zza(5);
        f = builder5.withProperty(zzfcVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("thirdQuartileMs");
        zzfc zzfcVar6 = new zzfc();
        zzfcVar6.zza(6);
        g = builder6.withProperty(zzfcVar6.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzol zzolVar = (zzol) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzolVar.zzc());
        objectEncoderContext.add(c, zzolVar.zze());
        objectEncoderContext.add(d, zzolVar.zza());
        objectEncoderContext.add(e, zzolVar.zzb());
        objectEncoderContext.add(f, zzolVar.zzd());
        objectEncoderContext.add(g, zzolVar.zzf());
    }
}
