package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class b5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final b5 f9371a = new b5();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("scoreType");
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(1);
        builder.withProperty(zzfcVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(FirebaseAnalytics.Param.SCORE);
        zzfc zzfcVar2 = new zzfc();
        zzfcVar2.zza(2);
        builder2.withProperty(zzfcVar2.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzov zzovVar = (zzov) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}