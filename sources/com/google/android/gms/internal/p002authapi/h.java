package com.google.android.gms.internal.p002authapi;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* renamed from: com.google.android.gms.internal.auth-api.h  reason: invalid package */
/* loaded from: classes6.dex */
public final class h extends zbaa {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8510a;

    public h(zbay zbayVar, TaskCompletionSource taskCompletionSource) {
        this.f8510a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p002authapi.zbab
    public final void zbb(Status status, PendingIntent pendingIntent) {
        TaskUtil.setResultOrApiException(status, pendingIntent, this.f8510a);
    }
}
