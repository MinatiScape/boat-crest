package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class c5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final c5 f9377a = new c5();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;
    public static final FieldDescriptor f;
    public static final FieldDescriptor g;
    public static final FieldDescriptor h;
    public static final FieldDescriptor i;
    public static final FieldDescriptor j;
    public static final FieldDescriptor k;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("durationMs");
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(1);
        b = builder.withProperty(zzfcVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("errorCode");
        zzfc zzfcVar2 = new zzfc();
        zzfcVar2.zza(2);
        c = builder2.withProperty(zzfcVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isColdCall");
        zzfc zzfcVar3 = new zzfc();
        zzfcVar3.zza(3);
        d = builder3.withProperty(zzfcVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("autoManageModelOnBackground");
        zzfc zzfcVar4 = new zzfc();
        zzfcVar4.zza(4);
        e = builder4.withProperty(zzfcVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("autoManageModelOnLowMemory");
        zzfc zzfcVar5 = new zzfc();
        zzfcVar5.zza(5);
        f = builder5.withProperty(zzfcVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("isNnApiEnabled");
        zzfc zzfcVar6 = new zzfc();
        zzfcVar6.zza(6);
        g = builder6.withProperty(zzfcVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("eventsCount");
        zzfc zzfcVar7 = new zzfc();
        zzfcVar7.zza(7);
        h = builder7.withProperty(zzfcVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("otherErrors");
        zzfc zzfcVar8 = new zzfc();
        zzfcVar8.zza(8);
        i = builder8.withProperty(zzfcVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("remoteConfigValueForAcceleration");
        zzfc zzfcVar9 = new zzfc();
        zzfcVar9.zza(9);
        j = builder9.withProperty(zzfcVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isAccelerated");
        zzfc zzfcVar10 = new zzfc();
        zzfcVar10.zza(10);
        k = builder10.withProperty(zzfcVar10.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzoy zzoyVar = (zzoy) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzoyVar.zze());
        objectEncoderContext.add(c, zzoyVar.zza());
        objectEncoderContext.add(d, zzoyVar.zzd());
        objectEncoderContext.add(e, zzoyVar.zzb());
        objectEncoderContext.add(f, zzoyVar.zzc());
        objectEncoderContext.add(g, (Object) null);
        objectEncoderContext.add(h, (Object) null);
        objectEncoderContext.add(i, (Object) null);
        objectEncoderContext.add(j, (Object) null);
        objectEncoderContext.add(k, (Object) null);
    }
}
