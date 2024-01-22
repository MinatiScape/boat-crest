package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class f8 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final f8 f9401a = new f8();
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
    public static final FieldDescriptor l;
    public static final FieldDescriptor m;
    public static final FieldDescriptor n;
    public static final FieldDescriptor o;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_ID);
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(1);
        b = builder.withProperty(zzfcVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        zzfc zzfcVar2 = new zzfc();
        zzfcVar2.zza(2);
        c = builder2.withProperty(zzfcVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzfc zzfcVar3 = new zzfc();
        zzfcVar3.zza(3);
        d = builder3.withProperty(zzfcVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzfc zzfcVar4 = new zzfc();
        zzfcVar4.zza(4);
        e = builder4.withProperty(zzfcVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzfc zzfcVar5 = new zzfc();
        zzfcVar5.zza(5);
        f = builder5.withProperty(zzfcVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzfc zzfcVar6 = new zzfc();
        zzfcVar6.zza(6);
        g = builder6.withProperty(zzfcVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("apiKey");
        zzfc zzfcVar7 = new zzfc();
        zzfcVar7.zza(7);
        h = builder7.withProperty(zzfcVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzfc zzfcVar8 = new zzfc();
        zzfcVar8.zza(8);
        i = builder8.withProperty(zzfcVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzfc zzfcVar9 = new zzfc();
        zzfcVar9.zza(9);
        j = builder9.withProperty(zzfcVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzfc zzfcVar10 = new zzfc();
        zzfcVar10.zza(10);
        k = builder10.withProperty(zzfcVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzfc zzfcVar11 = new zzfc();
        zzfcVar11.zza(11);
        l = builder11.withProperty(zzfcVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzfc zzfcVar12 = new zzfc();
        zzfcVar12.zza(12);
        m = builder12.withProperty(zzfcVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzfc zzfcVar13 = new zzfc();
        zzfcVar13.zza(13);
        n = builder13.withProperty(zzfcVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzfc zzfcVar14 = new zzfc();
        zzfcVar14.zza(14);
        o = builder14.withProperty(zzfcVar14.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzsl zzslVar = (zzsl) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzslVar.zzg());
        objectEncoderContext.add(c, zzslVar.zzh());
        objectEncoderContext.add(d, (Object) null);
        objectEncoderContext.add(e, zzslVar.zzj());
        objectEncoderContext.add(f, zzslVar.zzk());
        objectEncoderContext.add(g, (Object) null);
        objectEncoderContext.add(h, (Object) null);
        objectEncoderContext.add(i, zzslVar.zza());
        objectEncoderContext.add(j, zzslVar.zzi());
        objectEncoderContext.add(k, zzslVar.zzb());
        objectEncoderContext.add(l, zzslVar.zzd());
        objectEncoderContext.add(m, zzslVar.zzc());
        objectEncoderContext.add(n, zzslVar.zze());
        objectEncoderContext.add(o, zzslVar.zzf());
    }
}
