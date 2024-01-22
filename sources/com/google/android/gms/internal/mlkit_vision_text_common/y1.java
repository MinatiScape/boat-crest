package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class y1 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final y1 f9917a = new y1();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("logEventKey");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        b = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("eventCount");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        c = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("inferenceDurationStats");
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(3);
        d = builder3.withProperty(zzcvVar3.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzeg zzegVar = (zzeg) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzegVar.zza());
        objectEncoderContext.add(c, zzegVar.zzc());
        objectEncoderContext.add(d, zzegVar.zzb());
    }
}
