package com.google.android.gms.tagmanager;

import android.content.Context;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class InstallReferrerReceiver extends CampaignTrackingReceiver {
    @Override // com.google.android.gms.analytics.CampaignTrackingReceiver
    public final void zza(@RecentlyNonNull Context context, @RecentlyNonNull String str) {
        zzcx.zzd(str);
        zzcx.zze(context, str);
    }
}
