package com.google.android.gms.internal.firebase_messaging;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes7.dex */
public final class g implements ObjectEncoder<zze> {

    /* renamed from: a  reason: collision with root package name */
    public static final g f8647a = new g();
    public static final FieldDescriptor b = FieldDescriptor.of("messagingClientEventExtension");

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        ((ObjectEncoderContext) obj2).add(b, ((zze) obj).zzc());
    }
}
