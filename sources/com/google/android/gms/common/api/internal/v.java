package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
/* loaded from: classes6.dex */
public final class v extends com.google.android.gms.internal.base.zau {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zabe f8289a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(zabe zabeVar, Looper looper) {
        super(looper);
        this.f8289a = zabeVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            zabe.g(this.f8289a);
        } else if (i != 2) {
            Log.w("GoogleApiClientImpl", "Unknown message id: " + i);
        } else {
            zabe.f(this.f8289a);
        }
    }
}
