package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class c8 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final c8 f9380a = new c8();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;
    public static final FieldDescriptor f;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("xMin");
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(1);
        b = builder.withProperty(zzfcVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("yMin");
        zzfc zzfcVar2 = new zzfc();
        zzfcVar2.zza(2);
        c = builder2.withProperty(zzfcVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("xMax");
        zzfc zzfcVar3 = new zzfc();
        zzfcVar3.zza(3);
        d = builder3.withProperty(zzfcVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("yMax");
        zzfc zzfcVar4 = new zzfc();
        zzfcVar4.zza(4);
        e = builder4.withProperty(zzfcVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("confidenceScore");
        zzfc zzfcVar5 = new zzfc();
        zzfcVar5.zza(5);
        f = builder5.withProperty(zzfcVar5.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzse zzseVar = (zzse) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzseVar.zzc());
        objectEncoderContext.add(c, zzseVar.zze());
        objectEncoderContext.add(d, zzseVar.zzb());
        objectEncoderContext.add(e, zzseVar.zzd());
        objectEncoderContext.add(f, zzseVar.zza());
    }
}
