package com.google.android.gms.internal.p002authapi;

import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SavePasswordResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* renamed from: com.google.android.gms.internal.auth-api.c  reason: invalid package */
/* loaded from: classes6.dex */
public final class c extends zbag {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8506a;

    public c(zbao zbaoVar, TaskCompletionSource taskCompletionSource) {
        this.f8506a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p002authapi.zbah
    public final void zbb(Status status, SavePasswordResult savePasswordResult) throws RemoteException {
        TaskUtil.setResultOrApiException(status, savePasswordResult, this.f8506a);
    }
}
