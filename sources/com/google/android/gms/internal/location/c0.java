package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes8.dex */
public final class c0 extends zzj {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Object f8881a;
    public final /* synthetic */ TaskCompletionSource b;

    public c0(Object obj, TaskCompletionSource taskCompletionSource) {
        this.f8881a = obj;
        this.b = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.location.zzk
    public final void zzd(zzg zzgVar) {
        TaskUtil.setResultOrApiException(zzgVar.getStatus(), this.f8881a, this.b);
    }

    @Override // com.google.android.gms.internal.location.zzk
    public final void zze() {
    }
}
