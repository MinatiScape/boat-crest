package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class j3 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final j3 f9031a = new j3();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("errorCode");
        zzad zzadVar = new zzad();
        zzadVar.zza(1);
        builder.withProperty(zzadVar.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzky zzkyVar = (zzky) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}