package com.coveiot.android.leonardo.probe;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.performance.Constants;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ReminderJobServiceJobService extends JobService {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int i = Constants.ACTIVITY_INSIGHT_NOTIFICATION_ID;
    public final String h = ReminderJobServiceJobService.class.getSimpleName();

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getSPO2_REMINDER_NOTIFICATION_ID() {
            return ReminderJobServiceJobService.i;
        }
    }

    public final String getTAG() {
        return this.h;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(@Nullable JobParameters jobParameters) {
        LogHelper.d(this.h, "Spo2 Reminder job triggered");
        NotificationManager.getInstance().notifySpo2MeasureReminder(this, i);
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(@Nullable JobParameters jobParameters) {
        return true;
    }
}
