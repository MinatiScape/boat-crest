package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class n5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final n5 f9250a = new n5();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("stageId");
        zzbk zzbkVar = new zzbk();
        zzbkVar.zza(1);
        builder.withProperty(zzbkVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("device");
        zzbk zzbkVar2 = new zzbk();
        zzbkVar2.zza(2);
        builder2.withProperty(zzbkVar2.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzpc zzpcVar = (zzpc) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
