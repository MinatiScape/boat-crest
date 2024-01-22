package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class z3 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final z3 f9781a = new z3();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzae zzaeVar = new zzae();
        zzaeVar.zza(3);
        builder.withProperty(zzaeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.zza(4);
        builder2.withProperty(zzaeVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        zzae zzaeVar3 = new zzae();
        zzaeVar3.zza(5);
        builder3.withProperty(zzaeVar3.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzks zzksVar = (zzks) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
