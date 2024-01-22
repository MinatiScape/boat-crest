package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes8.dex */
public final class zzei extends zzcm {

    /* renamed from: a  reason: collision with root package name */
    public final BaseImplementation.ResultHolder<Status> f8860a;

    public zzei(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.f8860a = resultHolder;
    }

    public static zzei zza(TaskCompletionSource<Void> taskCompletionSource) {
        return new zzei(new l1(taskCompletionSource));
    }

    public static zzei zzb(TaskCompletionSource<Boolean> taskCompletionSource) {
        return new zzei(new k1(taskCompletionSource));
    }

    @Override // com.google.android.gms.internal.fitness.zzcn
    public final void onResult(Status status) {
        this.f8860a.setResult(status);
    }
}
