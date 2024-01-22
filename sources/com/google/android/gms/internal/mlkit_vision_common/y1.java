package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class y1 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final y1 f9773a = new y1();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;
    public static final FieldDescriptor f;
    public static final FieldDescriptor g;
    public static final FieldDescriptor h;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("durationMs");
        zzae zzaeVar = new zzae();
        zzaeVar.zza(1);
        b = builder.withProperty(zzaeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("imageSource");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.zza(2);
        c = builder2.withProperty(zzaeVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("imageFormat");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.zza(3);
        d = builder3.withProperty(zzaeVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("imageByteSize");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.zza(4);
        e = builder4.withProperty(zzaeVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("imageWidth");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.zza(5);
        f = builder5.withProperty(zzaeVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("imageHeight");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.zza(6);
        g = builder6.withProperty(zzaeVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("rotationDegrees");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.zza(7);
        h = builder7.withProperty(zzaeVar7.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zziq zziqVar = (zziq) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zziqVar.zzg());
        objectEncoderContext.add(c, zziqVar.zzb());
        objectEncoderContext.add(d, zziqVar.zza());
        objectEncoderContext.add(e, zziqVar.zzc());
        objectEncoderContext.add(f, zziqVar.zze());
        objectEncoderContext.add(g, zziqVar.zzd());
        objectEncoderContext.add(h, zziqVar.zzf());
    }
}
