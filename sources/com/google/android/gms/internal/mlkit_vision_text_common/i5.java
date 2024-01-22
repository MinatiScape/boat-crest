package com.google.android.gms.internal.mlkit_vision_text_common;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class i5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final i5 f9841a = new i5();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("deviceInfo");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("nnapiInfo");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("gpuInfo");
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(3);
        builder3.withProperty(zzcvVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("pipelineIdentifier");
        zzcv zzcvVar4 = new zzcv();
        zzcvVar4.zza(4);
        builder4.withProperty(zzcvVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("acceptedConfigurations");
        zzcv zzcvVar5 = new zzcv();
        zzcvVar5.zza(5);
        builder5.withProperty(zzcvVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder(Constants.KEY_ACTION);
        zzcv zzcvVar6 = new zzcv();
        zzcvVar6.zza(6);
        builder6.withProperty(zzcvVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(NotificationCompat.CATEGORY_STATUS);
        zzcv zzcvVar7 = new zzcv();
        zzcvVar7.zza(7);
        builder7.withProperty(zzcvVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("customErrors");
        zzcv zzcvVar8 = new zzcv();
        zzcvVar8.zza(8);
        builder8.withProperty(zzcvVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("benchmarkStatus");
        zzcv zzcvVar9 = new zzcv();
        zzcvVar9.zza(9);
        builder9.withProperty(zzcvVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("validationTestResult");
        zzcv zzcvVar10 = new zzcv();
        zzcvVar10.zza(10);
        builder10.withProperty(zzcvVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("timestampUs");
        zzcv zzcvVar11 = new zzcv();
        zzcvVar11.zza(11);
        builder11.withProperty(zzcvVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("elapsedUs");
        zzcv zzcvVar12 = new zzcv();
        zzcvVar12.zza(12);
        builder12.withProperty(zzcvVar12.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zznl zznlVar = (zznl) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
