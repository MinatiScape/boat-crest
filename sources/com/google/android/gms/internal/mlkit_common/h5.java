package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class h5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final h5 f9210a = new h5();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzbk zzbkVar = new zzbk();
        zzbkVar.zza(3);
        builder.withProperty(zzbkVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzbk zzbkVar2 = new zzbk();
        zzbkVar2.zza(4);
        builder2.withProperty(zzbkVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        zzbk zzbkVar3 = new zzbk();
        zzbkVar3.zza(5);
        builder3.withProperty(zzbkVar3.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzod zzodVar = (zzod) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
