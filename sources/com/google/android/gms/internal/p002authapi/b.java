package com.google.android.gms.internal.p002authapi;

import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* renamed from: com.google.android.gms.internal.auth-api.b  reason: invalid package */
/* loaded from: classes6.dex */
public final class b extends zbae {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8505a;

    public b(zbao zbaoVar, TaskCompletionSource taskCompletionSource) {
        this.f8505a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p002authapi.zbaf
    public final void zbb(Status status, SaveAccountLinkingTokenResult saveAccountLinkingTokenResult) throws RemoteException {
        if (status.isSuccess()) {
            this.f8505a.setResult(saveAccountLinkingTokenResult);
        } else {
            this.f8505a.setException(ApiExceptionUtil.fromStatus(status));
        }
    }
}
