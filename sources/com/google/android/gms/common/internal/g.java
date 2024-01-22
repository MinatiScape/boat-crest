package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public final class g implements PendingResult.StatusListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PendingResult f8334a;
    public final /* synthetic */ TaskCompletionSource b;
    public final /* synthetic */ PendingResultUtil.ResultConverter c;
    public final /* synthetic */ zas d;

    public g(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, PendingResultUtil.ResultConverter resultConverter, zas zasVar) {
        this.f8334a = pendingResult;
        this.b = taskCompletionSource;
        this.c = resultConverter;
        this.d = zasVar;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        if (status.isSuccess()) {
            this.b.setResult(this.c.convert(this.f8334a.await(0L, TimeUnit.MILLISECONDS)));
            return;
        }
        this.b.setException(ApiExceptionUtil.fromStatus(status));
    }
}
