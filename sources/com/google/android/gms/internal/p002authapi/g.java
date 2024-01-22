package com.google.android.gms.internal.p002authapi;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* renamed from: com.google.android.gms.internal.auth-api.g  reason: invalid package */
/* loaded from: classes6.dex */
public final class g extends zbac {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8509a;

    public g(zbay zbayVar, TaskCompletionSource taskCompletionSource) {
        this.f8509a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p002authapi.zbad
    public final void zbb(Status status, PendingIntent pendingIntent) throws RemoteException {
        TaskUtil.setResultOrApiException(status, pendingIntent, this.f8509a);
    }
}
