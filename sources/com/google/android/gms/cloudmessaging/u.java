package com.google.android.gms.cloudmessaging;

import android.os.Looper;
import android.os.Message;
/* loaded from: classes6.dex */
public final class u extends com.google.android.gms.internal.cloudmessaging.zze {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Rpc f8231a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(Rpc rpc, Looper looper) {
        super(looper);
        this.f8231a = rpc;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        this.f8231a.f(message);
    }
}
