package com.google.android.gms.internal.p001authapiphone;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* renamed from: com.google.android.gms.internal.auth-api-phone.a  reason: invalid package */
/* loaded from: classes6.dex */
public final class a extends zzi {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8498a;

    public a(zzab zzabVar, TaskCompletionSource taskCompletionSource) {
        this.f8498a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p001authapiphone.zzj
    public final void zzb(Status status) {
        TaskUtil.setResultOrApiException(status, this.f8498a);
    }
}