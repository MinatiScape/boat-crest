package com.google.android.gms.internal.p001authapiphone;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* renamed from: com.google.android.gms.internal.auth-api-phone.d  reason: invalid package */
/* loaded from: classes6.dex */
public final class d extends zzd {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8500a;

    public d(zzr zzrVar, TaskCompletionSource taskCompletionSource) {
        this.f8500a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p001authapiphone.zze
    public final void zzb(Status status, int i) {
        TaskUtil.setResultOrApiException(status, Integer.valueOf(i), this.f8500a);
    }
}
