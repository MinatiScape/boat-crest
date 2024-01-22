package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class e1 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final e1 f9184a = new e1();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("remoteModelOptions");
        zzbk zzbkVar = new zzbk();
        zzbkVar.zza(1);
        builder.withProperty(zzbkVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("localModelOptions");
        zzbk zzbkVar2 = new zzbk();
        zzbkVar2.zza(2);
        builder2.withProperty(zzbkVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("errorCodes");
        zzbk zzbkVar3 = new zzbk();
        zzbkVar3.zza(3);
        builder3.withProperty(zzbkVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("modelInitializationMs");
        zzbk zzbkVar4 = new zzbk();
        zzbkVar4.zza(4);
        builder4.withProperty(zzbkVar4.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzde zzdeVar = (zzde) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}