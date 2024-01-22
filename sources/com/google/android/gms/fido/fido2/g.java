package com.google.android.gms.fido.fido2;

import android.app.PendingIntent;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public final class g extends com.google.android.gms.internal.fido.zzl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8410a;

    public g(Fido2PrivilegedApiClient fido2PrivilegedApiClient, TaskCompletionSource taskCompletionSource) {
        this.f8410a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.fido.zzm
    public final void zzb(Status status, @Nullable PendingIntent pendingIntent) {
        TaskUtil.setResultOrApiException(status, pendingIntent, this.f8410a);
    }
}