package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes8.dex */
public final class b0 extends zzp {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8879a;

    public b0(zzda zzdaVar, TaskCompletionSource taskCompletionSource) {
        this.f8879a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.location.zzq
    public final void zzb(Status status, Location location) {
        TaskUtil.setResultOrApiException(status, location, this.f8879a);
    }
}
