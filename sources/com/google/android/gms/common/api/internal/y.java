package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
/* loaded from: classes6.dex */
public final class y extends com.google.android.gms.internal.base.zau {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zabi f8293a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(zabi zabiVar, Looper looper) {
        super(looper);
        this.f8293a = zabiVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            ((x) message.obj).b(this.f8293a);
        } else if (i != 2) {
            Log.w("GACStateManager", "Unknown message id: " + i);
        } else {
            throw ((RuntimeException) message.obj);
        }
    }
}
