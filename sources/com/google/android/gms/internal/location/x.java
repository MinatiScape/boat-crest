package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes8.dex */
public final class x extends zzl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8895a;

    public x(TaskCompletionSource taskCompletionSource) {
        this.f8895a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.location.zzm
    public final void zzb(int i, String[] strArr) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i)), this.f8895a);
    }

    @Override // com.google.android.gms.internal.location.zzm
    public final void zzc(int i, PendingIntent pendingIntent) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i)), this.f8895a);
    }

    @Override // com.google.android.gms.internal.location.zzm
    public final void zzd(int i, String[] strArr) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i)), this.f8895a);
    }
}
