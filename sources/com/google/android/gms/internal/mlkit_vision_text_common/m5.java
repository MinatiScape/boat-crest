package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class m5 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final m5 f9861a = new m5();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("options");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("errorCode");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        builder2.withProperty(zzcvVar2.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzmv zzmvVar = (zzmv) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}