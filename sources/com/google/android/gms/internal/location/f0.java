package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes8.dex */
public final class f0 extends zzr {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8884a;

    public f0(TaskCompletionSource taskCompletionSource) {
        this.f8884a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.location.zzs
    public final void zzb(LocationSettingsResult locationSettingsResult) {
        TaskUtil.setResultOrApiException(locationSettingsResult.getStatus(), new LocationSettingsResponse(locationSettingsResult), this.f8884a);
    }
}
