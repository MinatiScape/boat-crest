package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
@Deprecated
/* loaded from: classes6.dex */
public class CampaignTrackingService extends Service {
    @Override // android.app.Service
    @RecentlyNullable
    public IBinder onBind(@RecentlyNonNull Intent intent) {
        return null;
    }
}
