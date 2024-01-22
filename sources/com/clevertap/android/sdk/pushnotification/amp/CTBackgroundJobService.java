package com.clevertap.android.sdk.pushnotification.amp;

import android.app.job.JobParameters;
import android.app.job.JobService;
import androidx.annotation.RequiresApi;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Logger;
@RequiresApi(api = 21)
/* loaded from: classes2.dex */
public class CTBackgroundJobService extends JobService {

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public final /* synthetic */ JobParameters h;

        public a(JobParameters jobParameters) {
            this.h = jobParameters;
        }

        @Override // java.lang.Runnable
        public void run() {
            CleverTapAPI.runJobWork(CTBackgroundJobService.this.getApplicationContext(), this.h);
            CTBackgroundJobService.this.jobFinished(this.h, true);
        }
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        Logger.v("Job Service is starting");
        new Thread(new a(jobParameters)).start();
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
