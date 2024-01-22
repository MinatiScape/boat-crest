package com.coveiot.android.sportsnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.format.DateUtils;
import androidx.core.content.ContextCompat;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.VibrationModel;
import com.coveiot.android.bleabstract.request.MotorVibrationRequest;
import com.coveiot.android.bleabstract.request.SportsNotificationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
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
import com.coveiot.android.sportsnotification.model.AppDynamicSportsField;
import com.coveiot.android.sportsnotification.model.Filters;
import com.coveiot.android.sportsnotification.model.MQTTPayLoad;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.model.SelectedMatchLastStateData;
import com.coveiot.android.sportsnotification.model.SoccerFilterConfig;
import com.coveiot.android.sportsnotification.model.SpSyncComplete;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.sportsnotification.utils.SoccerData;
import com.coveiot.android.sportsnotification.utils.SoccerHelperFactory;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSports;
import com.coveiot.coveaccess.device.rcf.mqtt.DeviceMqttInfoManager;
import com.coveiot.coveaccess.device.rcf.mqtt.model.GetIotMqttInfoResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.sports.Data;
import com.coveiot.coveaccess.sports.Response;
import com.coveiot.coveaccess.sports.SportsAuthTokenRequest;
import com.coveiot.coveaccess.sports.SportsTokenRes;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SoccerSportsService extends Service implements IMessageObserver, IConnectionObserver, IResponseObserver, IPubSubAction, ISubscriptionObserver {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static boolean x;
    public Context h;
    @Nullable
    public Integer i;
    @Nullable
    public Integer j;
    @NotNull
    public final String k;
    public int l;
    public long m;
    public long n;
    @Nullable
    public final Handler o;
    @Nullable
    public final Handler p;
    @Nullable
    public final Handler q;
    public IBinder r;
    @NotNull
    public final String s;
    @Nullable
    public MQTTClient t;
    @Nullable
    public String u;
    public int v;
    public int w;

    /* loaded from: classes7.dex */
    public final class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        @NotNull
        public final SoccerSportsService getService() {
            return SoccerSportsService.this;
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
            return SoccerSportsService.x;
        }

        public final void setWasWatchReconnected(boolean z) {
            SoccerSportsService.x = z;
        }
    }

    /* loaded from: classes7.dex */
    public interface SportsSettingsResultListener {
        void onSettingsResult(boolean z);
    }

    @DebugMetadata(c = "com.coveiot.android.sportsnotification.SoccerSportsService$startUpdates$1$1", f = "SoccerSportsService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Calendar $matchStartTime;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Calendar calendar, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$matchStartTime = calendar;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$matchStartTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                LogHelper.d(SoccerSportsService.this.k, "Log at match start time");
                SoccerSportsService soccerSportsService = SoccerSportsService.this;
                MatchListModel selectedSoccerMatch = SportsPreference.Companion.getSelectedSoccerMatch(soccerSportsService);
                soccerSportsService.y(selectedSoccerMatch != null ? selectedSoccerMatch.getMatchId() : null);
                SoccerSportsService.this.e();
                SoccerSportsService.this.h();
                if (System.currentTimeMillis() - this.$matchStartTime.getTimeInMillis() > 30000) {
                    SoccerSportsService.this.k(System.currentTimeMillis() - this.$matchStartTime.getTimeInMillis());
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public SoccerSportsService() {
        String simpleName = SoccerSportsService.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SoccerSportsService::class.java.simpleName");
        this.k = simpleName;
        this.l = 900000;
        this.m = -1L;
        this.n = -1L;
        this.o = new Handler(Looper.getMainLooper());
        this.p = new Handler(Looper.getMainLooper());
        this.q = new Handler(Looper.getMainLooper());
        this.s = "341";
        this.r = new BtServiceBinder();
    }

    public static final void i(SoccerSportsService this$0, Ref.LongRef interval) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(interval, "$interval");
        LogHelper.d(this$0.k, "Log at match regular interval time");
        if (this$0.n != -1 && System.currentTimeMillis() - this$0.n > interval.element + 30000) {
            this$0.k(System.currentTimeMillis() - this$0.n);
        }
        this$0.e();
        this$0.h();
    }

    public static final void w(SoccerSportsService this$0, Calendar matchStartTime) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(matchStartTime, "$matchStartTime");
        kotlinx.coroutines.e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new a(matchStartTime, null), 3, null);
    }

    public final void c() {
        kotlinx.coroutines.e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new SoccerSportsService$callMatchScoreCardAPI$1(this, null), 3, null);
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void connect(@NotNull Context context, @NotNull MQTTConnectionParams params, @Nullable SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    public final void d() {
        LogHelper.d(this.k, "callMatchSummaryApi called");
        kotlinx.coroutines.e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new SoccerSportsService$callMatchSummaryApi$1(this, null), 3, null);
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void disconnect(@Nullable SuccessListener successListener) {
    }

    public final void e() {
        String str = this.k;
        LogHelper.d(str, "checkConditionsAndCallApi called as getScoreCardData Running --- " + System.currentTimeMillis());
        if (BleApiManager.getInstance(this).getBleApi() == null || BleApiManager.getInstance(this).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        if (sportsUtils.isCZDevice(this) || sportsUtils.isCADevice(this)) {
            SportsPreference.Companion companion = SportsPreference.Companion;
            if (companion.getSportsNotification(this) != null) {
                SportsPreferenceModel sportsNotification = companion.getSportsNotification(this);
                Intrinsics.checkNotNull(sportsNotification);
                if (sportsNotification.isEnable() != null) {
                    SportsPreferenceModel sportsNotification2 = companion.getSportsNotification(this);
                    Intrinsics.checkNotNull(sportsNotification2);
                    Boolean isEnable = sportsNotification2.isEnable();
                    Intrinsics.checkNotNull(isEnable);
                    if (isEnable.booleanValue()) {
                        MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(this);
                        SelectedMatchLastStateData selectedSoccerMatchLastState = companion.getSelectedSoccerMatchLastState(this);
                        if (selectedSoccerMatch != null) {
                            if (selectedSoccerMatchLastState != null && selectedSoccerMatch.getDate() != null) {
                                String date = selectedSoccerMatch.getDate();
                                Intrinsics.checkNotNull(date);
                                if (System.currentTimeMillis() > sportsUtils.minusMinutesFromCalendar(60, sportsUtils.getTimeInCurrentZone(date)).getTimeInMillis()) {
                                    if (selectedSoccerMatchLastState.getMatchStatus() != 2 && selectedSoccerMatchLastState.getMatchStatus() != 4) {
                                        c();
                                        return;
                                    } else if (!DateUtils.isToday(selectedSoccerMatchLastState.getLastStateTimestamp())) {
                                        q();
                                        return;
                                    } else if (selectedSoccerMatchLastState.getMatchStatus() == 2) {
                                        d();
                                        return;
                                    } else {
                                        return;
                                    }
                                }
                                return;
                            }
                            c();
                        }
                    }
                }
            }
        }
    }

    public final void f(final SuccessListener successListener) {
        MQTTClient mQTTClient;
        MQTTClient mQTTClient2 = this.t;
        if (mQTTClient2 != null) {
            Boolean valueOf = mQTTClient2 != null ? Boolean.valueOf(mQTTClient2.isConnected()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue()) {
                if (this.u != null) {
                    try {
                        try {
                            MQTTClient mQTTClient3 = this.t;
                            if (mQTTClient3 != null) {
                                Boolean valueOf2 = mQTTClient3 != null ? Boolean.valueOf(mQTTClient3.isConnected()) : null;
                                Intrinsics.checkNotNull(valueOf2);
                                if (valueOf2.booleanValue() && (mQTTClient = this.t) != null) {
                                    String str = this.u;
                                    Intrinsics.checkNotNull(str);
                                    mQTTClient.unsubscribe(str, new SuccessListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$closeMqttConnection$1
                                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                        public void onFailure(@Nullable String str2) {
                                            SuccessListener successListener2 = SuccessListener.this;
                                            if (successListener2 != null) {
                                                successListener2.onFailure(str2);
                                            }
                                        }

                                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                        public void onSuccess() {
                                            MQTTClient mqttClient = this.getMqttClient();
                                            if (mqttClient != null) {
                                                final SuccessListener successListener2 = SuccessListener.this;
                                                mqttClient.disconnect(new SuccessListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$closeMqttConnection$1$onSuccess$1
                                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                                    public void onFailure(@Nullable String str2) {
                                                        SuccessListener successListener3 = SuccessListener.this;
                                                        if (successListener3 != null) {
                                                            successListener3.onFailure(str2);
                                                        }
                                                    }

                                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                                    public void onSuccess() {
                                                        SuccessListener successListener3 = SuccessListener.this;
                                                        if (successListener3 != null) {
                                                            successListener3.onSuccess();
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            MQTTClient mQTTClient4 = this.t;
                            if (mQTTClient4 != null) {
                                mQTTClient4.disconnect(new SuccessListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$closeMqttConnection$2
                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                    public void onFailure(@Nullable String str2) {
                                        SuccessListener successListener2 = SuccessListener.this;
                                        if (successListener2 != null) {
                                            successListener2.onFailure(str2);
                                        }
                                    }

                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                    public void onSuccess() {
                                        SuccessListener successListener2 = SuccessListener.this;
                                        if (successListener2 != null) {
                                            successListener2.onSuccess();
                                        }
                                    }
                                });
                            }
                        }
                        return;
                    } finally {
                        this.u = null;
                    }
                }
                MQTTClient mQTTClient5 = this.t;
                if (mQTTClient5 != null) {
                    mQTTClient5.disconnect(new SuccessListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$closeMqttConnection$3
                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                        public void onFailure(@Nullable String str2) {
                            SuccessListener successListener2 = SuccessListener.this;
                            if (successListener2 != null) {
                                successListener2.onFailure(str2);
                            }
                        }

                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                        public void onSuccess() {
                            SuccessListener successListener2 = SuccessListener.this;
                            if (successListener2 != null) {
                                successListener2.onSuccess();
                            }
                        }
                    });
                    return;
                }
                return;
            }
        }
        if (successListener != null) {
            successListener.onSuccess();
        }
    }

    public final void g() {
        Notification build;
        NotificationInfo data = BleApiUtils.getData();
        if (data.getAppColor() == 0) {
            BleApiManager.getInstance(this);
            data = BleApiUtils.getData();
        }
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(data.getAppLauncherActivity()), 67108864);
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            Object systemService = getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            NotificationChannel notificationChannel = new NotificationChannel(this.s, "Soccer Score updates", 2);
            notificationChannel.enableLights(false);
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
            build = new Notification.Builder(this, this.s).setContentTitle("Soccer Score updates").setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this, CRICKET_NO…\n                .build()");
        } else if (i >= 21) {
            build = new Notification.Builder(this).setContentTitle("Soccer Score updates").setSmallIcon(data.getAppIcon()).setColor(ContextCompat.getColor(this, data.getAppColor())).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        } else {
            build = new Notification.Builder(this).setContentTitle("Soccer Score updates").setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        }
        startForeground(Integer.parseInt(this.s), build);
    }

    public final void getAccessToken() {
        SportsAuthTokenRequest sportsAuthTokenRequest = new SportsAuthTokenRequest();
        sportsAuthTokenRequest.setExtend(4);
        sportsAuthTokenRequest.setCtx("entitysport");
        sportsAuthTokenRequest.setSport("SOCCER");
        CoveSports.getSportsToken(sportsAuthTokenRequest, new CoveApiListener<SportsTokenRes, CoveApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$getAccessToken$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SportsTokenRes sportsTokenRes) {
                if (sportsTokenRes != null) {
                    AccessTokenPreference accessTokenPreference = new AccessTokenPreference(SoccerSportsService.this);
                    Data data = sportsTokenRes.getData();
                    Intrinsics.checkNotNull(data);
                    Response response = data.getResponse();
                    Intrinsics.checkNotNull(response);
                    String token = response.getToken();
                    Intrinsics.checkNotNull(token);
                    accessTokenPreference.saveAccessToken(token);
                    SoccerSportsService.this.c();
                }
            }
        });
    }

    @Nullable
    public final String getMTopic() {
        return this.u;
    }

    @Nullable
    public final MQTTClient getMqttClient() {
        return this.t;
    }

    public final int getRetryCount() {
        return this.v;
    }

    public final int getSubscriptionRetryCount() {
        return this.w;
    }

    public final void h() {
        Integer apiHitInterval;
        Handler handler = this.o;
        if (handler != null) {
            Context context = null;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            final Ref.LongRef longRef = new Ref.LongRef();
            longRef.element = this.l;
            SportsPreference.Companion companion = SportsPreference.Companion;
            Context context2 = this.h;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context2 = null;
            }
            if (companion.getSoccerConfig(context2) != null) {
                Context context3 = this.h;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context3 = null;
                }
                SoccerFilterConfig soccerConfig = companion.getSoccerConfig(context3);
                Intrinsics.checkNotNull(soccerConfig);
                Filters filters = soccerConfig.getFilters().get(0);
                Intrinsics.checkNotNull(filters);
                if (filters.getApiHitInterval() != null) {
                    Context context4 = this.h;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    } else {
                        context = context4;
                    }
                    SoccerFilterConfig soccerConfig2 = companion.getSoccerConfig(context);
                    Intrinsics.checkNotNull(soccerConfig2);
                    Filters filters2 = soccerConfig2.getFilters().get(0);
                    Intrinsics.checkNotNull(filters2);
                    Intrinsics.checkNotNull(filters2.getApiHitInterval());
                    longRef.element = apiHitInterval.intValue() * 60000;
                }
            }
            this.o.postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.b
                @Override // java.lang.Runnable
                public final void run() {
                    SoccerSportsService.i(SoccerSportsService.this, longRef);
                }
            }, longRef.element);
        }
    }

    public final void j(String str, String str2) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.ENTITY_SPORTS_API_FAILURE.getValue());
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
        hashMap.put("api", str);
        if (str2 != null) {
            hashMap.put("error_message", str2);
        }
        analyticsLog.setMapData(hashMap);
    }

    public final void k(long j) {
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0593  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x05a4  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x05d2  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x05d6  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x05e3  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x05f5  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0603  */
    /* JADX WARN: Type inference failed for: r19v3 */
    /* JADX WARN: Type inference failed for: r19v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l(retrofit2.Response<com.coveiot.android.sportsnotificationsdk.soccer.models.matchinfo.SMatchInfoResponse> r18, int r19) {
        /*
            Method dump skipped, instructions count: 1553
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.SoccerSportsService.l(retrofit2.Response, int):void");
    }

    public final void m(Integer num, GetIotMqttInfoResponse getIotMqttInfoResponse) {
        SportsPreference.Companion companion = SportsPreference.Companion;
        Context context = this.h;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        String str = getIotMqttInfoResponse.clientId;
        Intrinsics.checkNotNullExpressionValue(str, "p0.clientId");
        companion.saveClientId(context, str);
        Context context3 = this.h;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context3 = null;
        }
        companion.saveHost(context3, "ssl://" + getIotMqttInfoResponse.host + ':' + getIotMqttInfoResponse.port);
        Context context4 = this.h;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context4 = null;
        }
        Integer num2 = getIotMqttInfoResponse.getEntitySports().getSoccer().qos;
        Intrinsics.checkNotNullExpressionValue(num2, "p0.entitySports.soccer.qos");
        companion.saveRequestQOS(context4, num2.intValue());
        Context context5 = this.h;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context5 = null;
        }
        String str2 = getIotMqttInfoResponse.getEntitySports().getSoccer().contentType;
        Intrinsics.checkNotNullExpressionValue(str2, "p0.entitySports.soccer.contentType");
        companion.saveRequestContentType(context5, str2);
        Context context6 = this.h;
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
        Context context7 = this.h;
        if (context7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context7;
        }
        Integer num3 = getIotMqttInfoResponse.keepAlive;
        Intrinsics.checkNotNullExpressionValue(num3, "p0.keepAlive");
        companion.saveKeepAlive(context2, num3.intValue());
    }

    public final void n(SoccerData soccerData) {
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(this).deviceType");
        t(companion.getSoccerHelper(deviceType).getMatchCanceledRequest(soccerData), new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$sendMatchCanceledResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void o(SoccerData soccerData) {
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(this).deviceType");
        t(companion.getSoccerHelper(deviceType).getSoccerEventRequest(soccerData), new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$sendMatchOngoingResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    @Override // android.app.Service
    @NotNull
    public IBinder onBind(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        IBinder iBinder = this.r;
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
        g();
        try {
            CoveEventBusManager.getInstance().getEventBus().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MQTTClientFactory mQTTClientFactory = MQTTClientFactory.INSTANCE;
        MQTTClientType mQTTClientType = MQTTClientType.ECLIPSE_PAHO_CLIENT;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        MQTTClient mQTTClient = mQTTClientFactory.getMQTTClient(mQTTClientType, applicationContext);
        this.t = mQTTClient;
        if (mQTTClient != null) {
            mQTTClient.addObserver(this);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        try {
            LogHelper.d(this.k, "Service onDestroy");
            x();
            stopForeground(true);
            z();
            stopSelf();
            CoveEventBusManager.getInstance().getEventBus().unregister(this);
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
        String str2 = this.u;
        if (str2 == null || !kotlin.text.m.equals(topic, str2, true)) {
            return;
        }
        try {
            MQTTPayLoad mQTTPayLoad = (MQTTPayLoad) new Gson().fromJson(message, (Class<Object>) MQTTPayLoad.class);
            if (mQTTPayLoad != null) {
                LogHelper.d(this.k, mQTTPayLoad.toString());
                if (mQTTPayLoad.getEvt() == null || !kotlin.text.m.equals(mQTTPayLoad.getEvt().getTy(), "update", true)) {
                    return;
                }
                c();
            }
        } catch (Exception unused) {
            c();
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
                Intent intent2 = new Intent(this, SoccerSportsService.class);
                if (Build.VERSION.SDK_INT >= 26) {
                    startForegroundService(intent2);
                } else {
                    startService(intent2);
                }
                return 3;
            } catch (Exception e) {
                e.printStackTrace();
                Context context = this.h;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context = null;
                }
                BleApiUtils.checkExceptionAndShowNotification(e, context);
            }
        }
        this.h = this;
        g();
        v();
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
        if (companion.getSelectedSoccerMatchLastState(this) == null || companion.getSportsNotification(this) == null || !x) {
            return;
        }
        x = false;
        e();
    }

    public final void p(SoccerData soccerData) {
        z();
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(this).deviceType");
        t(companion.getSoccerHelper(deviceType).getSoccerSummaryRequest(soccerData), new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$sendMatchSummaryResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
                if (z) {
                    SoccerSportsService.this.x();
                }
            }
        });
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void publish(@NotNull String message, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    public final void q() {
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(this).deviceType");
        t(companion.getSoccerHelper(deviceType).getNoMatchSelectedRequest(), new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$sendNoMatchSelectedToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
                if (z) {
                    SoccerSportsService.this.x();
                }
            }
        });
    }

    public final void r() {
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(this).deviceType");
        t(companion.getSoccerHelper(deviceType).getOfflineRequest(), new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$sendOfflineStateToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
                SportsPreference.Companion.setOfflineStatus(SoccerSportsService.this, true);
            }
        });
    }

    public final void resetUpdates() {
        x();
        LogHelper.d(this.k, "resetUpdates");
        try {
            f(new SuccessListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$resetUpdates$1
                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                public void onFailure(@Nullable String str) {
                }

                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                public void onSuccess() {
                    SoccerSportsService.this.v();
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

    public final void s(SportsNotificationRequest sportsNotificationRequest, final SportsSettingsResultListener sportsSettingsResultListener, final ArrayList<DynamicSportsField> arrayList) {
        BleApiManager.getInstance(this).getBleApi().getData(sportsNotificationRequest, new DataResultListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$sendSportCommandAfterVibration$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                sportsSettingsResultListener.onSettingsResult(false);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Integer num;
                Integer num2;
                Integer num3;
                Integer num4;
                Intrinsics.checkNotNullParameter(response, "response");
                SportsPreference.Companion companion = SportsPreference.Companion;
                if (companion.getSelectedSoccerMatch(SoccerSportsService.this) != null) {
                    MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(SoccerSportsService.this);
                    Intrinsics.checkNotNull(selectedSoccerMatch);
                    if (selectedSoccerMatch.getMatchId() != null) {
                        LogHelper.d(SoccerSportsService.this.k, "sendSportDataToTheWatch");
                        sportsSettingsResultListener.onSettingsResult(true);
                        AppDynamicSportsField.INSTANCE.setMDynamicSportsFieldList(arrayList);
                        SelectedMatchLastStateData selectedMatchLastStateData = new SelectedMatchLastStateData();
                        MatchListModel selectedSoccerMatch2 = companion.getSelectedSoccerMatch(SoccerSportsService.this);
                        Integer matchId = selectedSoccerMatch2 != null ? selectedSoccerMatch2.getMatchId() : null;
                        Intrinsics.checkNotNull(matchId);
                        selectedMatchLastStateData.setMatchId(matchId.intValue());
                        num = SoccerSportsService.this.j;
                        if (num != null) {
                            num4 = SoccerSportsService.this.j;
                            Intrinsics.checkNotNull(num4);
                            selectedMatchLastStateData.setGameStatus(num4.intValue());
                        }
                        num2 = SoccerSportsService.this.i;
                        if (num2 != null) {
                            num3 = SoccerSportsService.this.i;
                            Intrinsics.checkNotNull(num3);
                            selectedMatchLastStateData.setMatchStatus(num3.intValue());
                        }
                        selectedMatchLastStateData.setLastStateTimestamp(System.currentTimeMillis());
                        companion.saveSelectedSoccerMatchLastState(SoccerSportsService.this, selectedMatchLastStateData);
                    }
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void setMTopic(@Nullable String str) {
        this.u = str;
    }

    public final void setMqttClient(@Nullable MQTTClient mQTTClient) {
        this.t = mQTTClient;
    }

    public final void setRetryCount(int i) {
        this.v = i;
    }

    public final void setSubscriptionRetryCount(int i) {
        this.w = i;
    }

    public final void stopService() {
        x();
        stopForeground(true);
        stopSelf();
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void subscribe(@NotNull String topic, int i, @NotNull String contentType, @Nullable SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
    }

    public final void t(ArrayList<DynamicSportsField> arrayList, SportsSettingsResultListener sportsSettingsResultListener) {
        if (BleApiManager.getInstance(this).getBleApi() == null || BleApiManager.getInstance(this).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        SportsNotificationRequest sportsNotificationRequest = new SportsNotificationRequest(arrayList);
        MotorVibrationRequest build = new MotorVibrationRequest.Builder().setVibrationModelList(kotlin.collections.e.listOf(new VibrationModel(1, 80, 16))).build();
        if (SportsPreference.Companion.isVibrationEnabled(this)) {
            if (System.currentTimeMillis() - this.m >= 15000) {
                this.m = System.currentTimeMillis();
                BleApiManager.getInstance(this).getBleApi().setUserSettings(build, new SoccerSportsService$sendSportDataToTheWatch$1(this, sportsNotificationRequest, sportsSettingsResultListener, arrayList));
                return;
            }
            s(sportsNotificationRequest, sportsSettingsResultListener, arrayList);
            return;
        }
        s(sportsNotificationRequest, sportsSettingsResultListener, arrayList);
    }

    public final void u(SoccerData soccerData) {
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(this).deviceType");
        t(companion.getSoccerHelper(deviceType).getMatchStartAtRequest(soccerData), new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$sendYetToStartResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void unsubscribe(@NotNull String topic, @Nullable SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(topic, "topic");
    }

    public final void v() {
        SportsPreference.Companion companion = SportsPreference.Companion;
        if (companion.getSelectedSoccerMatch(this) != null) {
            this.u = null;
            c();
            Handler handler = this.q;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(this);
            Intrinsics.checkNotNull(selectedSoccerMatch);
            if (selectedSoccerMatch.getDate() != null) {
                SportsUtils sportsUtils = SportsUtils.INSTANCE;
                MatchListModel selectedSoccerMatch2 = companion.getSelectedSoccerMatch(this);
                Intrinsics.checkNotNull(selectedSoccerMatch2);
                String date = selectedSoccerMatch2.getDate();
                Intrinsics.checkNotNull(date);
                final Calendar startTime = sportsUtils.getStartTime(date);
                startTime.add(12, 1);
                if (startTime.getTimeInMillis() > Calendar.getInstance().getTimeInMillis()) {
                    long timeInMillis = startTime.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
                    Handler handler2 = this.q;
                    if (handler2 != null) {
                        handler2.postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.a
                            @Override // java.lang.Runnable
                            public final void run() {
                                SoccerSportsService.w(SoccerSportsService.this, startTime);
                            }
                        }, timeInMillis);
                        return;
                    }
                    return;
                }
                MatchListModel selectedSoccerMatch3 = companion.getSelectedSoccerMatch(this);
                y(selectedSoccerMatch3 != null ? selectedSoccerMatch3.getMatchId() : null);
                h();
                return;
            }
            q();
            return;
        }
        q();
    }

    public final void x() {
        Handler handler = this.o;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public final void y(final Integer num) {
        if (num != null) {
            DeviceMqttInfoManager.getIotMqttInfo(PreferenceManager.getInstance().getUserDeviceID(), new CoveApiListener<GetIotMqttInfoResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$subscribeForMatchEvents$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetIotMqttInfoResponse getIotMqttInfoResponse) {
                    Integer num2;
                    if (getIotMqttInfoResponse == null || (num2 = getIotMqttInfoResponse.code) == null || num2.intValue() != 200 || getIotMqttInfoResponse.getEntitySports() == null || getIotMqttInfoResponse.getEntitySports().getSoccer() == null) {
                        return;
                    }
                    SoccerSportsService soccerSportsService = SoccerSportsService.this;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = getIotMqttInfoResponse.getEntitySports().getSoccer().topic;
                    Intrinsics.checkNotNullExpressionValue(str, "p0.entitySports.soccer.topic");
                    String format = String.format(str, Arrays.copyOf(new Object[]{num.toString()}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    soccerSportsService.setMTopic(format);
                    String str2 = getIotMqttInfoResponse.clientId;
                    Intrinsics.checkNotNullExpressionValue(str2, "p0.clientId");
                    String valueOf = String.valueOf(getIotMqttInfoResponse.port);
                    String mTopic = SoccerSportsService.this.getMTopic();
                    Intrinsics.checkNotNull(mTopic);
                    Integer num3 = getIotMqttInfoResponse.getEntitySports().getSoccer().qos;
                    Intrinsics.checkNotNullExpressionValue(num3, "p0.entitySports.soccer.qos");
                    int intValue = num3.intValue();
                    Integer num4 = getIotMqttInfoResponse.keepAlive;
                    Intrinsics.checkNotNullExpressionValue(num4, "p0.keepAlive");
                    MQTTConnectionParams mQTTConnectionParams = new MQTTConnectionParams(str2, "ssl://" + getIotMqttInfoResponse.host + ':' + getIotMqttInfoResponse.port, valueOf, null, null, mTopic, false, false, intValue, null, null, null, 0, num4.intValue(), null, null, null, 122584, null);
                    SoccerSportsService.this.m(num, getIotMqttInfoResponse);
                    MQTTClient mqttClient = SoccerSportsService.this.getMqttClient();
                    Boolean valueOf2 = mqttClient != null ? Boolean.valueOf(mqttClient.isConnected()) : null;
                    Intrinsics.checkNotNull(valueOf2);
                    if (valueOf2.booleanValue()) {
                        MQTTClient mqttClient2 = SoccerSportsService.this.getMqttClient();
                        if (mqttClient2 != null) {
                            String mTopic2 = SoccerSportsService.this.getMTopic();
                            Intrinsics.checkNotNull(mTopic2);
                            Integer num5 = getIotMqttInfoResponse.getEntitySports().getSoccer().qos;
                            Intrinsics.checkNotNullExpressionValue(num5, "p0.entitySports.soccer.qos");
                            int intValue2 = num5.intValue();
                            String str3 = getIotMqttInfoResponse.getEntitySports().getSoccer().contentType;
                            Intrinsics.checkNotNullExpressionValue(str3, "p0.entitySports.soccer.contentType");
                            mqttClient2.subscribe(mTopic2, intValue2, str3, new SuccessListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$subscribeForMatchEvents$1$onSuccess$1
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
                    MQTTClient mqttClient3 = SoccerSportsService.this.getMqttClient();
                    if (mqttClient3 != null) {
                        Context applicationContext = SoccerSportsService.this.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                        final SoccerSportsService soccerSportsService2 = SoccerSportsService.this;
                        final Integer num6 = num;
                        mqttClient3.connect(applicationContext, mQTTConnectionParams, new SuccessListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$subscribeForMatchEvents$1$onSuccess$2
                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onFailure(@Nullable String str4) {
                                String str5 = SoccerSportsService.this.k;
                                LogHelper.e(str5, " MQTT connection failed : " + str4);
                                if (SoccerSportsService.this.getSubscriptionRetryCount() < 3) {
                                    SoccerSportsService soccerSportsService3 = SoccerSportsService.this;
                                    soccerSportsService3.setSubscriptionRetryCount(soccerSportsService3.getSubscriptionRetryCount() + 1);
                                    SoccerSportsService.this.y(num6);
                                }
                            }

                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onSuccess() {
                                LogHelper.d(SoccerSportsService.this.k, " MQTT connection success");
                                SoccerSportsService.this.setSubscriptionRetryCount(0);
                            }
                        });
                    }
                }
            });
        }
    }

    public final void z() {
        MQTTClient mQTTClient;
        String topicSub = SportsPreference.Companion.getTopicSub(this);
        if (topicSub == null || (mQTTClient = this.t) == null) {
            return;
        }
        mQTTClient.unsubscribe(topicSub, new SuccessListener() { // from class: com.coveiot.android.sportsnotification.SoccerSportsService$unsubscribeMqttTopic$1$1
            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
            public void onFailure(@Nullable String str) {
            }

            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
            public void onSuccess() {
            }
        });
    }
}
