package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class c5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final c5 f8985a = new c5();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("appName");
        zzad zzadVar = new zzad();
        zzadVar.zza(1);
        builder.withProperty(zzadVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("sessionId");
        zzad zzadVar2 = new zzad();
        zzadVar2.zza(2);
        builder2.withProperty(zzadVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("startZoomLevel");
        zzad zzadVar3 = new zzad();
        zzadVar3.zza(3);
        builder3.withProperty(zzadVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("endZoomLevel");
        zzad zzadVar4 = new zzad();
        zzadVar4.zza(4);
        builder4.withProperty(zzadVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("durationMs");
        zzad zzadVar5 = new zzad();
        zzadVar5.zza(5);
        builder5.withProperty(zzadVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("predictedArea");
        zzad zzadVar6 = new zzad();
        zzadVar6.zza(6);
        builder6.withProperty(zzadVar6.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzmn zzmnVar = (zzmn) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
