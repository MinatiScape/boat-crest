package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class n5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final n5 f9866a = new n5();
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
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        b = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        c = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(3);
        d = builder3.withProperty(zzcvVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzcv zzcvVar4 = new zzcv();
        zzcvVar4.zza(4);
        e = builder4.withProperty(zzcvVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzcv zzcvVar5 = new zzcv();
        zzcvVar5.zza(5);
        f = builder5.withProperty(zzcvVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzcv zzcvVar6 = new zzcv();
        zzcvVar6.zza(6);
        g = builder6.withProperty(zzcvVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("apiKey");
        zzcv zzcvVar7 = new zzcv();
        zzcvVar7.zza(7);
        h = builder7.withProperty(zzcvVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzcv zzcvVar8 = new zzcv();
        zzcvVar8.zza(8);
        i = builder8.withProperty(zzcvVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzcv zzcvVar9 = new zzcv();
        zzcvVar9.zza(9);
        j = builder9.withProperty(zzcvVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzcv zzcvVar10 = new zzcv();
        zzcvVar10.zza(10);
        k = builder10.withProperty(zzcvVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzcv zzcvVar11 = new zzcv();
        zzcvVar11.zza(11);
        l = builder11.withProperty(zzcvVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzcv zzcvVar12 = new zzcv();
        zzcvVar12.zza(12);
        m = builder12.withProperty(zzcvVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzcv zzcvVar13 = new zzcv();
        zzcvVar13.zza(13);
        n = builder13.withProperty(zzcvVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzcv zzcvVar14 = new zzcv();
        zzcvVar14.zza(14);
        o = builder14.withProperty(zzcvVar14.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzmy zzmyVar = (zzmy) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzmyVar.zzg());
        objectEncoderContext.add(c, zzmyVar.zzh());
        objectEncoderContext.add(d, (Object) null);
        objectEncoderContext.add(e, zzmyVar.zzj());
        objectEncoderContext.add(f, zzmyVar.zzk());
        objectEncoderContext.add(g, (Object) null);
        objectEncoderContext.add(h, (Object) null);
        objectEncoderContext.add(i, zzmyVar.zza());
        objectEncoderContext.add(j, zzmyVar.zzi());
        objectEncoderContext.add(k, zzmyVar.zzb());
        objectEncoderContext.add(l, zzmyVar.zzd());
        objectEncoderContext.add(m, zzmyVar.zzc());
        objectEncoderContext.add(n, zzmyVar.zze());
        objectEncoderContext.add(o, zzmyVar.zzf());
    }
}
