package com.google.firebase.messaging;

import com.google.android.datatransport.Transformer;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
/* loaded from: classes10.dex */
public final /* synthetic */ class g0 implements Transformer {

    /* renamed from: a  reason: collision with root package name */
    public static final Transformer f11342a = new g0();

    @Override // com.google.android.datatransport.Transformer
    public Object apply(Object obj) {
        return ((MessagingClientEventExtension) obj).toByteArray();
    }
}
