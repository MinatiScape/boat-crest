package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
/* loaded from: classes6.dex */
public class AlarmManagerScheduler implements WorkScheduler {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8116a;
    public final EventStore b;
    public AlarmManager c;
    public final SchedulerConfig d;
    public final Clock e;

    public AlarmManagerScheduler(Context context, EventStore eventStore, Clock clock, SchedulerConfig schedulerConfig) {
        this(context, eventStore, (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM), clock, schedulerConfig);
    }

    @VisibleForTesting
    public boolean a(Intent intent) {
        return PendingIntent.getBroadcast(this.f8116a, 0, intent, PKIFailureInfo.duplicateCertReq) != null;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void schedule(TransportContext transportContext, int i) {
        schedule(transportContext, i, false);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void schedule(TransportContext transportContext, int i, boolean z) {
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter("backendName", transportContext.getBackendName());
        builder.appendQueryParameter("priority", String.valueOf(PriorityMapping.toInt(transportContext.getPriority())));
        if (transportContext.getExtras() != null) {
            builder.appendQueryParameter(NotificationCompat.MessagingStyle.Message.KEY_EXTRAS_BUNDLE, Base64.encodeToString(transportContext.getExtras(), 0));
        }
        Intent intent = new Intent(this.f8116a, AlarmManagerSchedulerBroadcastReceiver.class);
        intent.setData(builder.build());
        intent.putExtra("attemptNumber", i);
        if (!z && a(intent)) {
            Logging.d("AlarmManagerScheduler", "Upload for context %s is already scheduled. Returning...", transportContext);
            return;
        }
        long nextCallTime = this.b.getNextCallTime(transportContext);
        long scheduleDelay = this.d.getScheduleDelay(transportContext.getPriority(), nextCallTime, i);
        Logging.d("AlarmManagerScheduler", "Scheduling upload for context %s in %dms(Backend next call timestamp %d). Attempt %d", transportContext, Long.valueOf(scheduleDelay), Long.valueOf(nextCallTime), Integer.valueOf(i));
        this.c.set(3, this.e.getTime() + scheduleDelay, PendingIntent.getBroadcast(this.f8116a, 0, intent, 0));
    }

    @VisibleForTesting
    public AlarmManagerScheduler(Context context, EventStore eventStore, AlarmManager alarmManager, Clock clock, SchedulerConfig schedulerConfig) {
        this.f8116a = context;
        this.b = eventStore;
        this.c = alarmManager;
        this.e = clock;
        this.d = schedulerConfig;
    }
}
