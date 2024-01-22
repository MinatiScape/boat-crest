package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes6.dex */
public final class q0 extends com.google.android.gms.internal.base.zau {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zada f8282a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q0(zada zadaVar, Looper looper) {
        super(looper);
        this.f8282a = zadaVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        Object obj;
        zada zadaVar;
        int i = message.what;
        if (i != 0) {
            if (i != 1) {
                Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + i);
                return;
            }
            RuntimeException runtimeException = (RuntimeException) message.obj;
            Log.e("TransformedResultImpl", "Runtime exception on the transformation worker thread: ".concat(String.valueOf(runtimeException.getMessage())));
            throw runtimeException;
        }
        PendingResult pendingResult = (PendingResult) message.obj;
        obj = this.f8282a.e;
        synchronized (obj) {
            zadaVar = this.f8282a.b;
            zada zadaVar2 = (zada) Preconditions.checkNotNull(zadaVar);
            if (pendingResult == null) {
                zadaVar2.i(new Status(13, "Transform returned null"));
            } else if (pendingResult instanceof zacp) {
                zadaVar2.i(((zacp) pendingResult).a());
            } else {
                zadaVar2.zai(pendingResult);
            }
        }
    }
}
