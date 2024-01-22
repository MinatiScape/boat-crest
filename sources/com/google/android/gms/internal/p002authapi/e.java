package com.google.android.gms.internal.p002authapi;

import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* renamed from: com.google.android.gms.internal.auth-api.e  reason: invalid package */
/* loaded from: classes6.dex */
public final class e extends zbx {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8507a;

    public e(zbay zbayVar, TaskCompletionSource taskCompletionSource) {
        this.f8507a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p002authapi.zby
    public final void zbb(Status status, BeginSignInResult beginSignInResult) throws RemoteException {
        TaskUtil.setResultOrApiException(status, beginSignInResult, this.f8507a);
    }
}
