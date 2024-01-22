package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class g5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final g5 f9013a = new g5();
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
        zzad zzadVar = new zzad();
        zzadVar.zza(1);
        b = builder.withProperty(zzadVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        zzad zzadVar2 = new zzad();
        zzadVar2.zza(2);
        c = builder2.withProperty(zzadVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzad zzadVar3 = new zzad();
        zzadVar3.zza(3);
        d = builder3.withProperty(zzadVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzad zzadVar4 = new zzad();
        zzadVar4.zza(4);
        e = builder4.withProperty(zzadVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzad zzadVar5 = new zzad();
        zzadVar5.zza(5);
        f = builder5.withProperty(zzadVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzad zzadVar6 = new zzad();
        zzadVar6.zza(6);
        g = builder6.withProperty(zzadVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("apiKey");
        zzad zzadVar7 = new zzad();
        zzadVar7.zza(7);
        h = builder7.withProperty(zzadVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzad zzadVar8 = new zzad();
        zzadVar8.zza(8);
        i = builder8.withProperty(zzadVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzad zzadVar9 = new zzad();
        zzadVar9.zza(9);
        j = builder9.withProperty(zzadVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzad zzadVar10 = new zzad();
        zzadVar10.zza(10);
        k = builder10.withProperty(zzadVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzad zzadVar11 = new zzad();
        zzadVar11.zza(11);
        l = builder11.withProperty(zzadVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzad zzadVar12 = new zzad();
        zzadVar12.zza(12);
        m = builder12.withProperty(zzadVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzad zzadVar13 = new zzad();
        zzadVar13.zza(13);
        n = builder13.withProperty(zzadVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzad zzadVar14 = new zzad();
        zzadVar14.zza(14);
        o = builder14.withProperty(zzadVar14.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzms zzmsVar = (zzms) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzmsVar.zzg());
        objectEncoderContext.add(c, zzmsVar.zzh());
        objectEncoderContext.add(d, (Object) null);
        objectEncoderContext.add(e, zzmsVar.zzj());
        objectEncoderContext.add(f, zzmsVar.zzk());
        objectEncoderContext.add(g, (Object) null);
        objectEncoderContext.add(h, (Object) null);
        objectEncoderContext.add(i, zzmsVar.zza());
        objectEncoderContext.add(j, zzmsVar.zzi());
        objectEncoderContext.add(k, zzmsVar.zzb());
        objectEncoderContext.add(l, zzmsVar.zzd());
        objectEncoderContext.add(m, zzmsVar.zzc());
        objectEncoderContext.add(n, zzmsVar.zze());
        objectEncoderContext.add(o, zzmsVar.zzf());
    }
}
