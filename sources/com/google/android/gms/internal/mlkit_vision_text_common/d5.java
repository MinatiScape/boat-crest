package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class d5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final d5 f9816a = new d5();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(3);
        b = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(4);
        c = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(5);
        d = builder3.withProperty(zzcvVar3.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, ((zzmq) obj).zza());
        objectEncoderContext.add(c, (Object) null);
        objectEncoderContext.add(d, (Object) null);
    }
}
