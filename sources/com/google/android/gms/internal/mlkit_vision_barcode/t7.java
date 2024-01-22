package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class t7 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final t7 f9504a = new t7();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzfc zzfcVar = new zzfc();
        zzfcVar.zza(3);
        builder.withProperty(zzfcVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzfc zzfcVar2 = new zzfc();
        zzfcVar2.zza(4);
        builder2.withProperty(zzfcVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        zzfc zzfcVar3 = new zzfc();
        zzfcVar3.zza(5);
        builder3.withProperty(zzfcVar3.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzrx zzrxVar = (zzrx) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
