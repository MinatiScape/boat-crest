package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class y7 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final y7 f9537a = new y7();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("deviceInfo");
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(1);
        builder.withProperty(zzfcVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("nnapiInfo");
        zzfc zzfcVar2 = new zzfc();
        zzfcVar2.zza(2);
        builder2.withProperty(zzfcVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("gpuInfo");
        zzfc zzfcVar3 = new zzfc();
        zzfcVar3.zza(3);
        builder3.withProperty(zzfcVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("pipelineIdentifier");
        zzfc zzfcVar4 = new zzfc();
        zzfcVar4.zza(4);
        builder4.withProperty(zzfcVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("acceptedConfigurations");
        zzfc zzfcVar5 = new zzfc();
        zzfcVar5.zza(5);
        builder5.withProperty(zzfcVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder(Constants.KEY_ACTION);
        zzfc zzfcVar6 = new zzfc();
        zzfcVar6.zza(6);
        builder6.withProperty(zzfcVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(NotificationCompat.CATEGORY_STATUS);
        zzfc zzfcVar7 = new zzfc();
        zzfcVar7.zza(7);
        builder7.withProperty(zzfcVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("customErrors");
        zzfc zzfcVar8 = new zzfc();
        zzfcVar8.zza(8);
        builder8.withProperty(zzfcVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("benchmarkStatus");
        zzfc zzfcVar9 = new zzfc();
        zzfcVar9.zza(9);
        builder9.withProperty(zzfcVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("validationTestResult");
        zzfc zzfcVar10 = new zzfc();
        zzfcVar10.zza(10);
        builder10.withProperty(zzfcVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("timestampUs");
        zzfc zzfcVar11 = new zzfc();
        zzfcVar11.zza(11);
        builder11.withProperty(zzfcVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("elapsedUs");
        zzfc zzfcVar12 = new zzfc();
        zzfcVar12.zza(12);
        builder12.withProperty(zzfcVar12.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzsz zzszVar = (zzsz) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
