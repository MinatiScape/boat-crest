package com.google.android.gms.internal.firebase_messaging;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
import java.io.IOException;
/* loaded from: classes7.dex */
public final class f implements ObjectEncoder<MessagingClientEventExtension> {

    /* renamed from: a  reason: collision with root package name */
    public static final f f8646a = new f();
    public static final FieldDescriptor b;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("messagingClientEvent");
        zzv zzvVar = new zzv();
        zzvVar.zza(1);
        b = builder.withProperty(zzvVar.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        ((ObjectEncoderContext) obj2).add(b, ((MessagingClientEventExtension) obj).getMessagingClientEventInternal());
    }
}
