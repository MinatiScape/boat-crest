package com.google.android.gms.internal.mlkit_common;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class d3 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final d3 f9179a = new d3();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;
    public static final FieldDescriptor f;
    public static final FieldDescriptor g;
    public static final FieldDescriptor h;
    public static final FieldDescriptor i;
    public static final FieldDescriptor j;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(AppMeasurementSdk.ConditionalUserProperty.NAME);
        zzbk zzbkVar = new zzbk();
        zzbkVar.zza(1);
        b = builder.withProperty(zzbkVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("version");
        zzbk zzbkVar2 = new zzbk();
        zzbkVar2.zza(2);
        c = builder2.withProperty(zzbkVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("source");
        zzbk zzbkVar3 = new zzbk();
        zzbkVar3.zza(3);
        d = builder3.withProperty(zzbkVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder(NotificationCompat.MessagingStyle.Message.KEY_DATA_URI);
        zzbk zzbkVar4 = new zzbk();
        zzbkVar4.zza(4);
        e = builder4.withProperty(zzbkVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("hash");
        zzbk zzbkVar5 = new zzbk();
        zzbkVar5.zza(5);
        f = builder5.withProperty(zzbkVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("modelType");
        zzbk zzbkVar6 = new zzbk();
        zzbkVar6.zza(6);
        g = builder6.withProperty(zzbkVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("size");
        zzbk zzbkVar7 = new zzbk();
        zzbkVar7.zza(7);
        h = builder7.withProperty(zzbkVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("hasLabelMap");
        zzbk zzbkVar8 = new zzbk();
        zzbkVar8.zza(8);
        i = builder8.withProperty(zzbkVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("isManifestModel");
        zzbk zzbkVar9 = new zzbk();
        zzbkVar9.zza(9);
        j = builder9.withProperty(zzbkVar9.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlz zzlzVar = (zzlz) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzlzVar.zzd());
        objectEncoderContext.add(c, (Object) null);
        objectEncoderContext.add(d, zzlzVar.zzb());
        objectEncoderContext.add(e, (Object) null);
        objectEncoderContext.add(f, zzlzVar.zzc());
        objectEncoderContext.add(g, zzlzVar.zza());
        objectEncoderContext.add(h, (Object) null);
        objectEncoderContext.add(i, (Object) null);
        objectEncoderContext.add(j, (Object) null);
    }
}
