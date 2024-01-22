package com.google.android.gms.common.internal;

import android.os.Bundle;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
/* loaded from: classes6.dex */
public final class zzg extends j {
    public final /* synthetic */ BaseGmsClient e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @BinderThread
    public zzg(BaseGmsClient baseGmsClient, @Nullable int i, Bundle bundle) {
        super(baseGmsClient, i, null);
        this.e = baseGmsClient;
    }

    @Override // com.google.android.gms.common.internal.j
    public final void zzb(ConnectionResult connectionResult) {
        if (this.e.enableLocalFallback() && BaseGmsClient.m(this.e)) {
            BaseGmsClient.j(this.e, 16);
            return;
        }
        this.e.zzc.onReportServiceBinding(connectionResult);
        this.e.onConnectionFailed(connectionResult);
    }

    @Override // com.google.android.gms.common.internal.j
    public final boolean zzd() {
        this.e.zzc.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
        return true;
    }
}
