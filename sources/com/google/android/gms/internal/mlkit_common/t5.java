package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class t5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final t5 f9289a = new t5();
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
        zzbk zzbkVar = new zzbk();
        zzbkVar.zza(1);
        b = builder.withProperty(zzbkVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        zzbk zzbkVar2 = new zzbk();
        zzbkVar2.zza(2);
        c = builder2.withProperty(zzbkVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzbk zzbkVar3 = new zzbk();
        zzbkVar3.zza(3);
        d = builder3.withProperty(zzbkVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzbk zzbkVar4 = new zzbk();
        zzbkVar4.zza(4);
        e = builder4.withProperty(zzbkVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzbk zzbkVar5 = new zzbk();
        zzbkVar5.zza(5);
        f = builder5.withProperty(zzbkVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzbk zzbkVar6 = new zzbk();
        zzbkVar6.zza(6);
        g = builder6.withProperty(zzbkVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("apiKey");
        zzbk zzbkVar7 = new zzbk();
        zzbkVar7.zza(7);
        h = builder7.withProperty(zzbkVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzbk zzbkVar8 = new zzbk();
        zzbkVar8.zza(8);
        i = builder8.withProperty(zzbkVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzbk zzbkVar9 = new zzbk();
        zzbkVar9.zza(9);
        j = builder9.withProperty(zzbkVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzbk zzbkVar10 = new zzbk();
        zzbkVar10.zza(10);
        k = builder10.withProperty(zzbkVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzbk zzbkVar11 = new zzbk();
        zzbkVar11.zza(11);
        l = builder11.withProperty(zzbkVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzbk zzbkVar12 = new zzbk();
        zzbkVar12.zza(12);
        m = builder12.withProperty(zzbkVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzbk zzbkVar13 = new zzbk();
        zzbkVar13.zza(13);
        n = builder13.withProperty(zzbkVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzbk zzbkVar14 = new zzbk();
        zzbkVar14.zza(14);
        o = builder14.withProperty(zzbkVar14.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzon zzonVar = (zzon) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzonVar.zzg());
        objectEncoderContext.add(c, zzonVar.zzh());
        objectEncoderContext.add(d, (Object) null);
        objectEncoderContext.add(e, zzonVar.zzj());
        objectEncoderContext.add(f, zzonVar.zzk());
        objectEncoderContext.add(g, (Object) null);
        objectEncoderContext.add(h, (Object) null);
        objectEncoderContext.add(i, zzonVar.zza());
        objectEncoderContext.add(j, zzonVar.zzi());
        objectEncoderContext.add(k, zzonVar.zzb());
        objectEncoderContext.add(l, zzonVar.zzd());
        objectEncoderContext.add(m, zzonVar.zzc());
        objectEncoderContext.add(n, zzonVar.zze());
        objectEncoderContext.add(o, zzonVar.zzf());
    }
}
