package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class u4 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final u4 f9106a = new u4();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzad zzadVar = new zzad();
        zzadVar.zza(3);
        builder.withProperty(zzadVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzad zzadVar2 = new zzad();
        zzadVar2.zza(4);
        builder2.withProperty(zzadVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        zzad zzadVar3 = new zzad();
        zzadVar3.zza(5);
        builder3.withProperty(zzadVar3.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzmi zzmiVar = (zzmi) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
