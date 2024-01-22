package com.google.android.gms.internal.mlkit_code_scanner;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class p2 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final p2 f9070a = new p2();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(AppMeasurementSdk.ConditionalUserProperty.NAME);
        zzad zzadVar = new zzad();
        zzadVar.zza(1);
        builder.withProperty(zzadVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("version");
        zzad zzadVar2 = new zzad();
        zzadVar2.zza(2);
        builder2.withProperty(zzadVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("source");
        zzad zzadVar3 = new zzad();
        zzadVar3.zza(3);
        builder3.withProperty(zzadVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder(NotificationCompat.MessagingStyle.Message.KEY_DATA_URI);
        zzad zzadVar4 = new zzad();
        zzadVar4.zza(4);
        builder4.withProperty(zzadVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("hash");
        zzad zzadVar5 = new zzad();
        zzadVar5.zza(5);
        builder5.withProperty(zzadVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("modelType");
        zzad zzadVar6 = new zzad();
        zzadVar6.zza(6);
        builder6.withProperty(zzadVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("size");
        zzad zzadVar7 = new zzad();
        zzadVar7.zza(7);
        builder7.withProperty(zzadVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("hasLabelMap");
        zzad zzadVar8 = new zzad();
        zzadVar8.zza(8);
        builder8.withProperty(zzadVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("isManifestModel");
        zzad zzadVar9 = new zzad();
        zzadVar9.zza(9);
        builder9.withProperty(zzadVar9.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzkg zzkgVar = (zzkg) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
