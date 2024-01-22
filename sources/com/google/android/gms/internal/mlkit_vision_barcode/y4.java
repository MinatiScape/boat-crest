package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class y4 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final y4 f9534a = new y4();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("imageFormat");
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(1);
        b = builder.withProperty(zzfcVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("originalImageSize");
        zzfc zzfcVar2 = new zzfc();
        zzfcVar2.zza(2);
        c = builder2.withProperty(zzfcVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("compressedImageSize");
        zzfc zzfcVar3 = new zzfc();
        zzfcVar3.zza(3);
        d = builder3.withProperty(zzfcVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("isOdmlImage");
        zzfc zzfcVar4 = new zzfc();
        zzfcVar4.zza(4);
        e = builder4.withProperty(zzfcVar4.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzos zzosVar = (zzos) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzosVar.zza());
        objectEncoderContext.add(c, zzosVar.zzb());
        objectEncoderContext.add(d, (Object) null);
        objectEncoderContext.add(e, (Object) null);
    }
}
