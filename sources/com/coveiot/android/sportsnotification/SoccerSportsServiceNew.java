package com.coveiot.android.sportsnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.remotecommandframeworksdk.IConnectionObserver;
import com.coveiot.android.remotecommandframeworksdk.IMessageObserver;
import com.coveiot.android.remotecommandframeworksdk.IPubSubAction;
import com.coveiot.android.remotecommandframeworksdk.IResponseObserver;
import com.coveiot.android.remotecommandframeworksdk.ISubscriptionObserver;
import com.coveiot.android.remotecommandframeworksdk.MQTTClient;
import com.coveiot.android.remotecommandframeworksdk.MQTTClientFactory;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.android.remotecommandframeworksdk.utils.MQTTClientType;
import com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.model.Filters;
import com.coveiot.android.sportsnotification.model.MQTTPayLoad;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.model.SoccerFilterConfig;
import com.coveiot.android.sportsnotification.model.SpSyncComplete;
import com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.rcf.mqtt.DeviceMqttInfoManager;
import com.coveiot.coveaccess.device.rcf.mqtt.model.GetIotMqttInfoResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SoccerSportsServiceNew extends Service implements IMessageObserver, IConnectionObserver, IResponseObserver, IPubSubAction, ISubscriptionObserver {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static boolean u;
    public final int h = 2147483643;
    public final int i = 2147483643;
    public Context j;
    @NotNull
    public final String k;
    public int l;
    public long m;
    public long n;
    public IBinder o;
    @NotNull
    public final String p;
    public int q;
    @Nullable
    public MQTTClient r;
    @Nullable
    public String s;
    @NotNull
    public final MatchBroadCAstReceiver t;

    /* loaded from: classes7.dex */
    public final class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        @NotNull
        public final SoccerSportsServiceNew getService() {
            return SoccerSportsServiceNew.this;
        }
    }

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean getWasWatchReconnected() {
            return SoccerSportsServiceNew.u;
        }

        public final void setWasWatchReconnected(boolean z) {
            SoccerSportsServiceNew.u = z;
        }
    }

    /* loaded from: classes7.dex */
    public final class MatchBroadCAstReceiver extends BroadcastReceiver {
        public MatchBroadCAstReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(@Nullable Context context, @Nullable Intent intent) {
            String stringExtra;
            if (intent != null) {
                if (intent.hasExtra(com.clevertap.android.sdk.Constants.KEY_ACTION) && (stringExtra = intent.getStringExtra(com.clevertap.android.sdk.Constants.KEY_ACTION)) != null) {
                    int hashCode = stringExtra.hashCode();
                    if (hashCode != 58288195) {
                        if (hashCode == 138240610 && stringExtra.equals("schedule_periodic_job_again")) {
                            SoccerSportsServiceNew.this.f();
                        }
                    } else if (stringExtra.equals("stop_periodic_handler")) {
                        SoccerSportsServiceNew.this.a();
                    }
                }
                if (intent.hasExtra(MqttServiceConstants.SUBSCRIBE_ACTION)) {
                    if (intent.getBooleanExtra(MqttServiceConstants.SUBSCRIBE_ACTION, false)) {
                        SoccerSportsServiceNew soccerSportsServiceNew = SoccerSportsServiceNew.this;
                        MatchListModel selectedSoccerMatch = SportsPreference.Companion.getSelectedSoccerMatch(soccerSportsServiceNew);
                        soccerSportsServiceNew.m(selectedSoccerMatch != null ? selectedSoccerMatch.getMatchId() : null);
                        return;
                    }
                    SoccerSportsServiceNew.c(SoccerSportsServiceNew.this, null, 1, null);
                }
            }
        }

        @Override // android.content.BroadcastReceiver
        @NotNull
        public IBinder peekService(@Nullable Context context, @Nullable Intent intent) {
            IBinder peekService = super.peekService(context, intent);
            Intrinsics.checkNotNullExpressionValue(peekService, "super.peekService(myContext, service)");
            return peekService;
        }
    }

    /* loaded from: classes7.dex */
    public interface SportsSettingsResultListener {
        void onSettingsResult(boolean z);
    }

    public SoccerSportsServiceNew() {
        String simpleName = SoccerSportsServiceNew.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SoccerSportsServiceNew::class.java.simpleName");
        this.k = simpleName;
        this.l = 900000;
        this.m = -1L;
        this.n = -1L;
        this.p = "341";
        this.t = new MatchBroadCAstReceiver();
        this.o = new BtServiceBinder();
    }

    public static /* synthetic */ void c(SoccerSportsServiceNew soccerSportsServiceNew, SuccessListener successListener, int i, Object obj) {
        if ((i & 1) != 0) {
            successListener = null;
        }
        soccerSportsServiceNew.b(successListener);
    }

    public final void a() {
        LogHelper.d(this.k, "cancelPeriodicJob");
        JobScheduler e = e();
        if (e != null) {
            try {
                e.cancel(this.h);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x007a, code lost:
        if (r2 != false) goto L66;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(final com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener r7) {
        /*
            r6 = this;
            com.coveiot.android.remotecommandframeworksdk.MQTTClient r0 = r6.r     // Catch: java.lang.Exception -> Lcc
            if (r0 == 0) goto Lc6
            r1 = 0
            if (r0 == 0) goto L10
            boolean r0 = r0.isConnected()     // Catch: java.lang.Exception -> Lcc
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch: java.lang.Exception -> Lcc
            goto L11
        L10:
            r0 = r1
        L11:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Exception -> Lcc
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> Lcc
            if (r0 == 0) goto Lc6
            java.lang.String r0 = r6.s     // Catch: java.lang.Exception -> Lcc
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L8c
            com.coveiot.android.remotecommandframeworksdk.MQTTClient r0 = r6.r     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            if (r0 == 0) goto L4a
            if (r0 == 0) goto L2f
            boolean r0 = r0.isConnected()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            goto L30
        L2f:
            r0 = r1
        L30:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            if (r0 == 0) goto L4a
            com.coveiot.android.remotecommandframeworksdk.MQTTClient r0 = r6.r     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            if (r0 == 0) goto L4a
            java.lang.String r4 = r6.s     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            com.coveiot.android.sportsnotification.SoccerSportsServiceNew$closeMqttConnection$1 r5 = new com.coveiot.android.sportsnotification.SoccerSportsServiceNew$closeMqttConnection$1     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            r5.<init>()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            r0.unsubscribe(r4, r5)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
        L4a:
            r6.s = r1     // Catch: java.lang.Exception -> Lcc
            goto Ld0
        L4e:
            r7 = move-exception
            goto L89
        L50:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L4e
            com.coveiot.android.remotecommandframeworksdk.MQTTClient r0 = r6.r     // Catch: java.lang.Throwable -> L4e
            if (r0 == 0) goto L5d
            kotlin.Pair r0 = r0.getAllSubscription()     // Catch: java.lang.Throwable -> L4e
            goto L5e
        L5d:
            r0 = r1
        L5e:
            if (r0 == 0) goto L7c
            com.coveiot.android.remotecommandframeworksdk.MQTTClient r0 = r6.r     // Catch: java.lang.Throwable -> L4e
            if (r0 == 0) goto L69
            kotlin.Pair r0 = r0.getAllSubscription()     // Catch: java.lang.Throwable -> L4e
            goto L6a
        L69:
            r0 = r1
        L6a:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Throwable -> L4e
            java.util.List r0 = kotlin.TuplesKt.toList(r0)     // Catch: java.lang.Throwable -> L4e
            if (r0 == 0) goto L79
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L4e
            if (r0 == 0) goto L7a
        L79:
            r2 = r3
        L7a:
            if (r2 == 0) goto L4a
        L7c:
            com.coveiot.android.remotecommandframeworksdk.MQTTClient r0 = r6.r     // Catch: java.lang.Throwable -> L4e
            if (r0 == 0) goto L4a
            com.coveiot.android.sportsnotification.SoccerSportsServiceNew$closeMqttConnection$2 r2 = new com.coveiot.android.sportsnotification.SoccerSportsServiceNew$closeMqttConnection$2     // Catch: java.lang.Throwable -> L4e
            r2.<init>()     // Catch: java.lang.Throwable -> L4e
            r0.disconnect(r2)     // Catch: java.lang.Throwable -> L4e
            goto L4a
        L89:
            r6.s = r1     // Catch: java.lang.Exception -> Lcc
            throw r7     // Catch: java.lang.Exception -> Lcc
        L8c:
            com.coveiot.android.remotecommandframeworksdk.MQTTClient r0 = r6.r     // Catch: java.lang.Exception -> Lcc
            if (r0 == 0) goto L95
            kotlin.Pair r0 = r0.getAllSubscription()     // Catch: java.lang.Exception -> Lcc
            goto L96
        L95:
            r0 = r1
        L96:
            if (r0 == 0) goto Lb9
            com.coveiot.android.remotecommandframeworksdk.MQTTClient r0 = r6.r     // Catch: java.lang.Exception -> Lcc
            if (r0 == 0) goto La0
            kotlin.Pair r1 = r0.getAllSubscription()     // Catch: java.lang.Exception -> Lcc
        La0:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> Lcc
            java.util.List r0 = kotlin.TuplesKt.toList(r1)     // Catch: java.lang.Exception -> Lcc
            if (r0 == 0) goto Laf
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Exception -> Lcc
            if (r0 == 0) goto Lb0
        Laf:
            r2 = r3
        Lb0:
            if (r2 == 0) goto Lb3
            goto Lb9
        Lb3:
            if (r7 == 0) goto Ld0
            r7.onSuccess()     // Catch: java.lang.Exception -> Lcc
            goto Ld0
        Lb9:
            com.coveiot.android.remotecommandframeworksdk.MQTTClient r0 = r6.r     // Catch: java.lang.Exception -> Lcc
            if (r0 == 0) goto Ld0
            com.coveiot.android.sportsnotification.SoccerSportsServiceNew$closeMqttConnection$3 r1 = new com.coveiot.android.sportsnotification.SoccerSportsServiceNew$closeMqttConnection$3     // Catch: java.lang.Exception -> Lcc
            r1.<init>()     // Catch: java.lang.Exception -> Lcc
            r0.disconnect(r1)     // Catch: java.lang.Exception -> Lcc
            goto Ld0
        Lc6:
            if (r7 == 0) goto Ld0
            r7.onSuccess()     // Catch: java.lang.Exception -> Lcc
            goto Ld0
        Lcc:
            r7 = move-exception
            r7.printStackTrace()
        Ld0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.SoccerSportsServiceNew.b(com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener):void");
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void connect(@NotNull Context context, @NotNull MQTTConnectionParams params, @Nullable SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    public final void d() {
        Notification build;
        NotificationInfo data = BleApiUtils.getData();
        if (data.getAppColor() == 0) {
            BleApiManager.getInstance(this);
            data = BleApiUtils.getData();
            if (data.getAppColor() == 0) {
                BleApiUtils.getMetadata(this);
                data = BleApiUtils.getData();
            }
        }
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, SportsNotificationActivity.class), 67108864);
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            Object systemService = getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            NotificationChannel notificationChannel = new NotificationChannel(this.p, "Soccer Score updates", 2);
            notificationChannel.enableLights(false);
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
            build = new Notification.Builder(this, this.p).setContentTitle("Soccer Score updates").setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this, CRICKET_NO…\n                .build()");
        } else if (i >= 21) {
            build = new Notification.Builder(this).setContentTitle("Soccer Score updates").setSmallIcon(data.getAppIcon()).setColor(ContextCompat.getColor(this, data.getAppColor())).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        } else {
            build = new Notification.Builder(this).setContentTitle("Soccer Score updates").setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        }
        startForeground(Integer.parseInt(this.p), build);
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void disconnect(@Nullable SuccessListener successListener) {
    }

    public final JobScheduler e() {
        return (JobScheduler) getSystemService(JobScheduler.class);
    }

    public final void f() {
        long j = this.l;
        SportsPreference.Companion companion = SportsPreference.Companion;
        Context context = this.j;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        if (companion.getSoccerConfig(context) != null) {
            Context context3 = this.j;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context3 = null;
            }
            SoccerFilterConfig soccerConfig = companion.getSoccerConfig(context3);
            Intrinsics.checkNotNull(soccerConfig);
            Filters filters = soccerConfig.getFilters().get(0);
            Intrinsics.checkNotNull(filters);
            if (filters.getApiHitInterval() != null) {
                Context context4 = this.j;
                if (context4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                } else {
                    context2 = context4;
                }
                SoccerFilterConfig soccerConfig2 = companion.getSoccerConfig(context2);
                Intrinsics.checkNotNull(soccerConfig2);
                Filters filters2 = soccerConfig2.getFilters().get(0);
                Intrinsics.checkNotNull(filters2);
                Integer apiHitInterval = filters2.getApiHitInterval();
                Intrinsics.checkNotNull(apiHitInterval);
                j = apiHitInterval.intValue() * 60000;
            }
        }
        if (this.m != -1) {
            if (System.currentTimeMillis() - this.m > 30000 + j) {
                g(System.currentTimeMillis() - this.m);
            }
            this.m = System.currentTimeMillis();
        }
        j(j);
    }

    public final void g(long j) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.SYSTEM_INTERRUPTS_TIMER.getValue());
        analyticsLog.setCTX("entitysport");
        MatchListModel selectedSoccerMatch = SportsPreference.Companion.getSelectedSoccerMatch(this);
        analyticsLog.setMatchID(String.valueOf(selectedSoccerMatch != null ? selectedSoccerMatch.getMatchId() : null));
        String lowerCase = String.valueOf(selectedSoccerMatch != null ? selectedSoccerMatch.getDate() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        analyticsLog.setMatchDate((String) StringsKt__StringsKt.split$default((CharSequence) StringsKt__StringsKt.trim(lowerCase).toString(), new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0));
        String lowerCase2 = String.valueOf(selectedSoccerMatch != null ? selectedSoccerMatch.getTeamA() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
        analyticsLog.setCricketTeam1(lowerCase2);
        String lowerCase3 = String.valueOf(selectedSoccerMatch != null ? selectedSoccerMatch.getTeamB() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
        analyticsLog.setCricketTeam2(lowerCase3);
        StringBuilder sb = new StringBuilder();
        String lowerCase4 = String.valueOf(selectedSoccerMatch != null ? selectedSoccerMatch.getFormattedTime() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
        sb.append(StringsKt__StringsKt.trim((String) StringsKt__StringsKt.split$default((CharSequence) lowerCase4, new String[]{com.clevertap.android.sdk.Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).get(0)).toString());
        sb.append(":00");
        analyticsLog.setMatchStartTime(sb.toString());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("delay_in_milliseconds", String.valueOf(j));
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    @Nullable
    public final String getMTopic() {
        return this.s;
    }

    @NotNull
    public final MatchBroadCAstReceiver getMatchBroadCAstReceiver() {
        return this.t;
    }

    @Nullable
    public final MQTTClient getMqttClient() {
        return this.r;
    }

    public final int getSubscriptionRetryCount() {
        return this.q;
    }

    public final void h(Integer num, GetIotMqttInfoResponse getIotMqttInfoResponse) {
        SportsPreference.Companion companion = SportsPreference.Companion;
        Context context = this.j;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        String str = getIotMqttInfoResponse.clientId;
        Intrinsics.checkNotNullExpressionValue(str, "p0.clientId");
        companion.saveClientId(context, str);
        Context context3 = this.j;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context3 = null;
        }
        companion.saveHost(context3, "ssl://" + getIotMqttInfoResponse.host + ':' + getIotMqttInfoResponse.port);
        Context context4 = this.j;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context4 = null;
        }
        Integer num2 = getIotMqttInfoResponse.getEntitySports().getSoccer().qos;
        Intrinsics.checkNotNullExpressionValue(num2, "p0.entitySports.soccer.qos");
        companion.saveRequestQOS(context4, num2.intValue());
        Context context5 = this.j;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context5 = null;
        }
        String str2 = getIotMqttInfoResponse.getEntitySports().getSoccer().contentType;
        Intrinsics.checkNotNullExpressionValue(str2, "p0.entitySports.soccer.contentType");
        companion.saveRequestContentType(context5, str2);
        Context context6 = this.j;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context6 = null;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str3 = getIotMqttInfoResponse.getEntitySports().getSoccer().topic;
        Intrinsics.checkNotNullExpressionValue(str3, "p0.entitySports.soccer.topic");
        String format = String.format(str3, Arrays.copyOf(new Object[]{String.valueOf(num)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        companion.saveTopicSub(context6, format);
        Context context7 = this.j;
        if (context7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context7;
        }
        Integer num3 = getIotMqttInfoResponse.keepAlive;
        Intrinsics.checkNotNullExpressionValue(num3, "p0.keepAlive");
        companion.saveKeepAlive(context2, num3.intValue());
    }

    public final void i(long j) {
        LogHelper.d(this.k, "SCHEDULED MATCH START JOB");
        Context context = this.j;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        JobInfo.Builder builder = new JobInfo.Builder(this.i, new ComponentName(context, MatchStartJobService.class));
        builder.setMinimumLatency(j);
        builder.setOverrideDeadline(j + 500);
        builder.setRequiredNetworkType(1);
        builder.setRequiresCharging(false);
        JobScheduler e = e();
        if (e != null) {
            try {
                e.cancel(this.i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (e != null) {
            e.schedule(builder.build());
        }
    }

    public final void j(long j) {
        LogHelper.d(this.k, "SCHEDULED MATCH PERIODIC JOB");
        Context context = this.j;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        new PeriodicSportsJobService();
        JobInfo.Builder builder = new JobInfo.Builder(this.h, new ComponentName(context, PeriodicSportsJobService.class));
        builder.setMinimumLatency(j);
        builder.setOverrideDeadline(j + 500);
        builder.setRequiredNetworkType(1);
        builder.setRequiresCharging(false);
        JobScheduler e = e();
        if (e != null) {
            try {
                e.cancel(this.h);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (e != null) {
            e.schedule(builder.build());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.Context] */
    public final void k() {
        SportsPreference.Companion companion = SportsPreference.Companion;
        if (companion.getSelectedSoccerMatch(this) != null) {
            this.s = null;
            SoccerApiHelper.Companion companion2 = SoccerApiHelper.Companion;
            Context context = this.j;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context = null;
            }
            companion2.getInstance(context).callMatchScoreCardAPI();
            Context context2 = this.j;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context2 = null;
            }
            companion2.getInstance(context2).resetStates();
            MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(this);
            Intrinsics.checkNotNull(selectedSoccerMatch);
            if (selectedSoccerMatch.getDate() != null) {
                SportsUtils sportsUtils = SportsUtils.INSTANCE;
                MatchListModel selectedSoccerMatch2 = companion.getSelectedSoccerMatch(this);
                Intrinsics.checkNotNull(selectedSoccerMatch2);
                String date = selectedSoccerMatch2.getDate();
                Intrinsics.checkNotNull(date);
                Calendar startTime = sportsUtils.getStartTime(date);
                startTime.add(12, 1);
                if (startTime.getTimeInMillis() > Calendar.getInstance().getTimeInMillis()) {
                    i(startTime.getTimeInMillis() - Calendar.getInstance().getTimeInMillis());
                    return;
                }
                MatchListModel selectedSoccerMatch3 = companion.getSelectedSoccerMatch(this);
                m(selectedSoccerMatch3 != null ? selectedSoccerMatch3.getMatchId() : null);
                f();
                return;
            }
            ?? r0 = this.j;
            if (r0 == 0) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            } else {
                r3 = r0;
            }
            companion2.getInstance(r3).sendNoMatchSelectedToWatch();
            return;
        }
        SoccerApiHelper.Companion companion3 = SoccerApiHelper.Companion;
        ?? r1 = this.j;
        if (r1 == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            r3 = r1;
        }
        companion3.getInstance(r3).sendNoMatchSelectedToWatch();
    }

    public final void l() {
        LogHelper.d(this.k, "stopPeriodic Job");
        JobScheduler e = e();
        if (e != null) {
            try {
                e.cancel(this.h);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public final void m(final Integer num) {
        if (num != null) {
            LogHelper.d(this.k, "subscribeForMatchEvents");
            DeviceMqttInfoManager.getIotMqttInfo(PreferenceManager.getInstance().getUserDeviceID(), new CoveApiListener<GetIotMqttInfoResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.SoccerSportsServiceNew$subscribeForMatchEvents$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetIotMqttInfoResponse getIotMqttInfoResponse) {
                    Integer num2;
                    if (getIotMqttInfoResponse == null || (num2 = getIotMqttInfoResponse.code) == null || num2.intValue() != 200 || getIotMqttInfoResponse.getEntitySports() == null || getIotMqttInfoResponse.getEntitySports().getSoccer() == null) {
                        return;
                    }
                    SoccerSportsServiceNew soccerSportsServiceNew = SoccerSportsServiceNew.this;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = getIotMqttInfoResponse.getEntitySports().getSoccer().topic;
                    Intrinsics.checkNotNullExpressionValue(str, "p0.entitySports.soccer.topic");
                    String format = String.format(str, Arrays.copyOf(new Object[]{num.toString()}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    soccerSportsServiceNew.setMTopic(format);
                    String str2 = getIotMqttInfoResponse.clientId;
                    Intrinsics.checkNotNullExpressionValue(str2, "p0.clientId");
                    String valueOf = String.valueOf(getIotMqttInfoResponse.port);
                    String mTopic = SoccerSportsServiceNew.this.getMTopic();
                    Intrinsics.checkNotNull(mTopic);
                    Integer num3 = getIotMqttInfoResponse.getEntitySports().getSoccer().qos;
                    Intrinsics.checkNotNullExpressionValue(num3, "p0.entitySports.soccer.qos");
                    int intValue = num3.intValue();
                    Integer num4 = getIotMqttInfoResponse.keepAlive;
                    Intrinsics.checkNotNullExpressionValue(num4, "p0.keepAlive");
                    MQTTConnectionParams mQTTConnectionParams = new MQTTConnectionParams(str2, "ssl://" + getIotMqttInfoResponse.host + ':' + getIotMqttInfoResponse.port, valueOf, null, null, mTopic, false, false, intValue, null, null, null, 0, num4.intValue(), null, null, null, 122584, null);
                    SoccerSportsServiceNew.this.h(num, getIotMqttInfoResponse);
                    try {
                        MQTTClient mqttClient = SoccerSportsServiceNew.this.getMqttClient();
                        Boolean valueOf2 = mqttClient != null ? Boolean.valueOf(mqttClient.isConnected()) : null;
                        Intrinsics.checkNotNull(valueOf2);
                        if (valueOf2.booleanValue()) {
                            MQTTClient mqttClient2 = SoccerSportsServiceNew.this.getMqttClient();
                            if (mqttClient2 != null) {
                                String mTopic2 = SoccerSportsServiceNew.this.getMTopic();
                                Intrinsics.checkNotNull(mTopic2);
                                Integer num5 = getIotMqttInfoResponse.getEntitySports().getSoccer().qos;
                                Intrinsics.checkNotNullExpressionValue(num5, "p0.entitySports.soccer.qos");
                                int intValue2 = num5.intValue();
                                String str3 = getIotMqttInfoResponse.getEntitySports().getSoccer().contentType;
                                Intrinsics.checkNotNullExpressionValue(str3, "p0.entitySports.soccer.contentType");
                                mqttClient2.subscribe(mTopic2, intValue2, str3, new SuccessListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsServiceNew$subscribeForMatchEvents$1$onSuccess$1
                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                    public void onFailure(@Nullable String str4) {
                                    }

                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                    public void onSuccess() {
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        MQTTClient mqttClient3 = SoccerSportsServiceNew.this.getMqttClient();
                        if (mqttClient3 != null) {
                            Context applicationContext = SoccerSportsServiceNew.this.getApplicationContext();
                            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                            final SoccerSportsServiceNew soccerSportsServiceNew2 = SoccerSportsServiceNew.this;
                            final Integer num6 = num;
                            mqttClient3.connect(applicationContext, mQTTConnectionParams, new SuccessListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsServiceNew$subscribeForMatchEvents$1$onSuccess$2
                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onFailure(@Nullable String str4) {
                                    String str5;
                                    str5 = SoccerSportsServiceNew.this.k;
                                    LogHelper.e(str5, " MQTT connection failed : " + str4);
                                    if (SoccerSportsServiceNew.this.getSubscriptionRetryCount() < 3) {
                                        SoccerSportsServiceNew soccerSportsServiceNew3 = SoccerSportsServiceNew.this;
                                        soccerSportsServiceNew3.setSubscriptionRetryCount(soccerSportsServiceNew3.getSubscriptionRetryCount() + 1);
                                        SoccerSportsServiceNew.this.m(num6);
                                    }
                                }

                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onSuccess() {
                                    String str4;
                                    str4 = SoccerSportsServiceNew.this.k;
                                    LogHelper.d(str4, " MQTT connection success");
                                    SoccerSportsServiceNew.this.setSubscriptionRetryCount(0);
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override // android.app.Service
    @NotNull
    public IBinder onBind(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        IBinder iBinder = this.o;
        if (iBinder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("serviceBinder");
            return null;
        }
        return iBinder;
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IConnectionObserver
    public void onConnectionUpdate(boolean z) {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.t, new IntentFilter("ACTION_MATCH_UPDATE"));
        d();
        MQTTClientFactory mQTTClientFactory = MQTTClientFactory.INSTANCE;
        MQTTClientType mQTTClientType = MQTTClientType.ECLIPSE_PAHO_CLIENT;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        MQTTClient mQTTClient = mQTTClientFactory.getMQTTClient(mQTTClientType, applicationContext);
        this.r = mQTTClient;
        if (mQTTClient != null) {
            mQTTClient.addObserver(this);
        }
        try {
            CoveEventBusManager.getInstance().getEventBus().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        try {
            LogHelper.d(this.k, "Service onDestroy");
            l();
            stopForeground(true);
            MQTTClient mQTTClient = this.r;
            if (mQTTClient != null && mQTTClient != null) {
                mQTTClient.removeObserver(this);
            }
            c(this, null, 1, null);
            stopSelf();
            CoveEventBusManager.getInstance().getEventBus().unregister(this);
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IMessageObserver
    public void onMessageUpdate(@NotNull String topic, @NotNull String message) {
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(message, "message");
        String str = this.k;
        LogHelper.d(str, " Message received from MQTT : " + message);
        String str2 = this.s;
        if (str2 == null || !kotlin.text.m.equals(topic, str2, true)) {
            return;
        }
        try {
            MQTTPayLoad mQTTPayLoad = (MQTTPayLoad) new Gson().fromJson(message, (Class<Object>) MQTTPayLoad.class);
            if (mQTTPayLoad != null) {
                LogHelper.d(this.k, mQTTPayLoad.toString());
                if (mQTTPayLoad.getEvt() != null && kotlin.text.m.equals(mQTTPayLoad.getEvt().getTy(), "update", true)) {
                    long j = this.n;
                    Long ts = mQTTPayLoad.getTs();
                    if (ts != null && j == ts.longValue()) {
                        LogHelper.d(this.k, "Received duplicate message from observer, so ignoring it");
                    }
                    Long ts2 = mQTTPayLoad.getTs();
                    Intrinsics.checkNotNullExpressionValue(ts2, "mqttPayLoad.ts");
                    this.n = ts2.longValue();
                    SoccerApiHelper.Companion.getInstance(this).callMatchScoreCardAPI();
                }
            }
        } catch (Exception unused) {
            SoccerApiHelper.Companion.getInstance(this).callMatchScoreCardAPI();
        }
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IMessageObserver
    public void onMessageUpdate(@NotNull String topic, @NotNull byte[] byteArray) {
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IResponseObserver
    public void onResetRetainFlag(@NotNull String msg, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IResponseObserver
    public void onSendResponse(@NotNull String msg, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IResponseObserver
    public void onSendResponseAck(@NotNull String msg, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        if (intent == null) {
            try {
                Intent intent2 = new Intent(this, SoccerSportsServiceNew.class);
                if (Build.VERSION.SDK_INT >= 26) {
                    startForegroundService(intent2);
                } else {
                    startService(intent2);
                }
                return 3;
            } catch (Exception e) {
                e.printStackTrace();
                BleApiUtils.checkExceptionAndShowNotification(e, this);
            }
        }
        this.j = this;
        d();
        k();
        return 3;
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.ISubscriptionObserver
    public void onStateUpdate(@NotNull String status) {
        Intrinsics.checkNotNullParameter(status, "status");
    }

    @Subscribe
    public final void onSyncCompleteReceived(@NotNull SpSyncComplete spSyncComplete) {
        Intrinsics.checkNotNullParameter(spSyncComplete, "spSyncComplete");
        SportsPreference.Companion companion = SportsPreference.Companion;
        if (companion.getSelectedSoccerMatchLastState(this) == null || companion.getSportsNotification(this) == null || !u) {
            return;
        }
        u = false;
        LogHelper.d(this.k, "onSyncCompleteReceived");
        SoccerApiHelper.Companion.getInstance(this).checkConditionsAndCallApi();
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void publish(@NotNull String message, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    public final void resetUpdates() {
        l();
        LogHelper.d(this.k, "resetUpdates");
        try {
            b(new SuccessListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsServiceNew$resetUpdates$1
                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                public void onFailure(@Nullable String str) {
                }

                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                public void onSuccess() {
                    SoccerSportsServiceNew.this.k();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void restartService() {
        stopForeground(true);
        stopSelf();
    }

    public final void setMTopic(@Nullable String str) {
        this.s = str;
    }

    public final void setMqttClient(@Nullable MQTTClient mQTTClient) {
        this.r = mQTTClient;
    }

    public final void setSubscriptionRetryCount(int i) {
        this.q = i;
    }

    public final void stopService() {
        l();
        stopForeground(true);
        stopSelf();
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void subscribe(@NotNull String topic, int i, @NotNull String contentType, @Nullable SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void unsubscribe(@NotNull String topic, @Nullable SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(topic, "topic");
    }
}
