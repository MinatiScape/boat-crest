package com.abupdate.iot_libs.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.SystemClock;
import androidx.annotation.RequiresApi;
import com.abupdate.iot_libs.MqttAgentPolicy;
import com.abupdate.iot_libs.constant.OtaConstants;
import com.abupdate.iot_libs.engine.f;
import com.abupdate.iot_libs.report.ReportManager;
import com.abupdate.iot_libs.utils.SPFTool;
import com.abupdate.trace.Trace;
@RequiresApi(api = 21)
/* loaded from: classes.dex */
public class JobSchedulerService extends JobService {

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ JobParameters h;

        public a(JobParameters jobParameters) {
            this.h = jobParameters;
        }

        @Override // java.lang.Runnable
        public void run() {
            SystemClock.sleep(30000L);
            if (SPFTool.getBoolean(MqttAgentPolicy.CONFIG_MQTT_CONNECT, false) && !MqttAgentPolicy.isConnected()) {
                JobSchedulerService.this.jobFinished(this.h, true);
            }
            if (JobSchedulerService.this.a() == 0) {
                JobSchedulerService.this.jobFinished(this.h, false);
            } else {
                JobSchedulerService.this.jobFinished(this.h, true);
            }
        }
    }

    public int a() {
        return ReportManager.getInstance(this).queryReport();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Trace.d("JobSchedulerService", "onCreate() ");
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        Trace.d("JobSchedulerService", "onStartJob() ");
        if (SPFTool.getBoolean(MqttAgentPolicy.CONFIG_MQTT_CONNECT, false) && !MqttAgentPolicy.isConnected()) {
            MqttAgentPolicy.connect();
        }
        if (a() != 0) {
            OtaService.startByAction(OtaService.ACTION_REPORT);
        }
        f.a().a(new a(jobParameters));
        if (System.currentTimeMillis() - SPFTool.getLong(OtaConstants.SPF_STATIC_CHECK_VERSION_CYCLE, -1L) >= OtaConstants.STATIC_CHECK_VERSION_CYCLE) {
            SPFTool.putLong(OtaConstants.SPF_STATIC_CHECK_VERSION_CYCLE, System.currentTimeMillis());
            OtaService.startByAction(OtaService.ACTION_STATIC_CHECK_VERSION);
            return true;
        }
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        Trace.d("JobSchedulerService", "onStopJob() ");
        return false;
    }
}
