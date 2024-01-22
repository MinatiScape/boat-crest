package com.coveiot.android.sportsnotification;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper;
import com.coveiot.utils.utility.LogHelper;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class PeriodicSportsJobService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStartJob(@Nullable JobParameters jobParameters) {
        LogHelper.d("SoccerSportsServiceNew", "PeriodicSportsJobService Started");
        SoccerApiHelper.Companion.getInstance(this).checkConditionsAndCallApi();
        Intent intent = new Intent("ACTION_MATCH_UPDATE");
        intent.putExtra(com.clevertap.android.sdk.Constants.KEY_ACTION, "schedule_periodic_job_again");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(@Nullable JobParameters jobParameters) {
        return true;
    }
}
