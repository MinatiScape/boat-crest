package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class c3 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final c3 f9172a = new c3();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;
    public static final FieldDescriptor f;
    public static final FieldDescriptor g;
    public static final FieldDescriptor h;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("options");
        zzbk zzbkVar = new zzbk();
        zzbkVar.zza(1);
        b = builder.withProperty(zzbkVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("roughDownloadDurationMs");
        zzbk zzbkVar2 = new zzbk();
        zzbkVar2.zza(2);
        c = builder2.withProperty(zzbkVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("errorCode");
        zzbk zzbkVar3 = new zzbk();
        zzbkVar3.zza(3);
        d = builder3.withProperty(zzbkVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("exactDownloadDurationMs");
        zzbk zzbkVar4 = new zzbk();
        zzbkVar4.zza(4);
        e = builder4.withProperty(zzbkVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("downloadStatus");
        zzbk zzbkVar5 = new zzbk();
        zzbkVar5.zza(5);
        f = builder5.withProperty(zzbkVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("downloadFailureStatus");
        zzbk zzbkVar6 = new zzbk();
        zzbkVar6.zza(6);
        g = builder6.withProperty(zzbkVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("mddDownloadErrorCodes");
        zzbk zzbkVar7 = new zzbk();
        zzbkVar7.zza(7);
        h = builder7.withProperty(zzbkVar7.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlu zzluVar = (zzlu) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzluVar.zzc());
        objectEncoderContext.add(c, zzluVar.zzf());
        objectEncoderContext.add(d, zzluVar.zza());
        objectEncoderContext.add(e, zzluVar.zze());
        objectEncoderContext.add(f, zzluVar.zzb());
        objectEncoderContext.add(g, zzluVar.zzd());
        objectEncoderContext.add(h, (Object) null);
    }
}
