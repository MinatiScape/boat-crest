package com.google.android.gms.internal.clearcut;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.util.VisibleForTesting;
/* loaded from: classes7.dex */
public final class zze extends GoogleApi<Api.ApiOptions.NoOptions> implements com.google.android.gms.clearcut.zzb {
    @VisibleForTesting
    public zze(@NonNull Context context) {
        super(context, ClearcutLogger.API, (Api.ApiOptions) null, new ApiExceptionMapper());
    }

    public static com.google.android.gms.clearcut.zzb zzb(@NonNull Context context) {
        return new zze(context);
    }

    @Override // com.google.android.gms.clearcut.zzb
    public final PendingResult<Status> zzb(com.google.android.gms.clearcut.zze zzeVar) {
        return doBestEffortWrite((zze) new u3(zzeVar, asGoogleApiClient()));
    }
}
