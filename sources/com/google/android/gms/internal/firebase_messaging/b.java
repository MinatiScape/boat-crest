package com.google.android.gms.internal.firebase_messaging;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.util.Map;
/* loaded from: classes7.dex */
public final /* synthetic */ class b implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final ObjectEncoder f8642a = new b();

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final void encode(Object obj, Object obj2) {
        c.g((Map.Entry) obj, (ObjectEncoderContext) obj2);
    }
}
