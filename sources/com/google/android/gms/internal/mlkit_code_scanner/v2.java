package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class v2 implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final v2 f9111a = new v2();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("deviceInfos");
        zzad zzadVar = new zzad();
        zzadVar.zza(1);
        builder.withProperty(zzadVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("errorInfo");
        zzad zzadVar2 = new zzad();
        zzadVar2.zza(2);
        builder2.withProperty(zzadVar2.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zznd zzndVar = (zznd) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
