package com.coveiot.android.sportsnotification.utils.apihelper;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.SportsNotificationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframeworksdk.utils.SingletonHolder;
import com.coveiot.android.sportsnotification.AccessTokenPreference;
import com.coveiot.android.sportsnotification.SoccerSportsServiceNew;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsUtils;
import com.coveiot.android.sportsnotification.model.AppDynamicSportsField;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.model.SelectedMatchLastStateData;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.sportsnotification.utils.SoccerData;
import com.coveiot.android.sportsnotification.utils.SoccerHelperFactory;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSports;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sports.Data;
import com.coveiot.coveaccess.sports.Response;
import com.coveiot.coveaccess.sports.SportsAuthTokenRequest;
import com.coveiot.coveaccess.sports.SportsTokenRes;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SoccerApiHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5892a;
    public long b;
    public int c;
    @Nullable
    public Integer d;
    @Nullable
    public Integer e;
    @NotNull
    public final String f;
    @Nullable
    public final Handler g;

    /* loaded from: classes7.dex */
    public static final class Companion extends SingletonHolder<SoccerApiHelper, Context> {

        /* loaded from: classes7.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, SoccerApiHelper> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, SoccerApiHelper.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final SoccerApiHelper invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SoccerApiHelper(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes7.dex */
    public interface SportsSettingsResultListener {
        void onSettingsResult(boolean z);
    }

    public SoccerApiHelper(Context context) {
        this.f5892a = context;
        this.b = -1L;
        this.f = "SoccerSportsServiceNew";
        this.g = new Handler(Looper.getMainLooper());
    }

    public /* synthetic */ SoccerApiHelper(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void a() {
        LogHelper.d(this.f, "callMatchSummaryApi called");
        e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new SoccerApiHelper$callMatchSummaryApi$1(this, null), 3, null);
    }

    public final void b() {
        Intent intent = new Intent("ACTION_MATCH_UPDATE");
        intent.putExtra(MqttServiceConstants.SUBSCRIBE_ACTION, false);
        LocalBroadcastManager.getInstance(this.f5892a).sendBroadcast(intent);
    }

    public final void c(String str, String str2) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.ENTITY_SPORTS_API_FAILURE.getValue());
        analyticsLog.setCTX("entitysport");
        MatchListModel selectedSoccerMatch = SportsPreference.Companion.getSelectedSoccerMatch(this.f5892a);
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
        sb.append(StringsKt__StringsKt.trim((String) StringsKt__StringsKt.split$default((CharSequence) lowerCase4, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).get(0)).toString());
        sb.append(":00");
        analyticsLog.setMatchStartTime(sb.toString());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("api", str);
        if (str2 != null) {
            hashMap.put("error_message", str2);
        }
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public final void callMatchScoreCardAPI() {
        LogHelper.d(this.f, "callMatchScoreCardAPI called");
        e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new SoccerApiHelper$callMatchScoreCardAPI$1(this, null), 3, null);
    }

    public final void checkConditionsAndCallApi() {
        String str = this.f;
        LogHelper.d(str, "checkConditionsAndCallApi called --- " + System.currentTimeMillis());
        if (BleApiManager.getInstance(this.f5892a).getBleApi() == null || BleApiManager.getInstance(this.f5892a).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        if (sportsUtils.isDeviceSupportsSoccerSportsSettings(this.f5892a)) {
            SportsPreference.Companion companion = SportsPreference.Companion;
            if (companion.getSportsNotification(this.f5892a) != null) {
                SportsPreferenceModel sportsNotification = companion.getSportsNotification(this.f5892a);
                Intrinsics.checkNotNull(sportsNotification);
                if (sportsNotification.isEnable() != null) {
                    SportsPreferenceModel sportsNotification2 = companion.getSportsNotification(this.f5892a);
                    Intrinsics.checkNotNull(sportsNotification2);
                    Boolean isEnable = sportsNotification2.isEnable();
                    Intrinsics.checkNotNull(isEnable);
                    if (isEnable.booleanValue()) {
                        MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(this.f5892a);
                        SelectedMatchLastStateData selectedSoccerMatchLastState = companion.getSelectedSoccerMatchLastState(this.f5892a);
                        if (selectedSoccerMatch != null) {
                            if (selectedSoccerMatchLastState != null && selectedSoccerMatch.getDate() != null) {
                                String date = selectedSoccerMatch.getDate();
                                Intrinsics.checkNotNull(date);
                                if (System.currentTimeMillis() > sportsUtils.minusMinutesFromCalendar(60, sportsUtils.getTimeInCurrentZone(date)).getTimeInMillis()) {
                                    if (selectedSoccerMatchLastState.getMatchStatus() != 2 && selectedSoccerMatchLastState.getMatchStatus() != 4) {
                                        callMatchScoreCardAPI();
                                        return;
                                    } else if (!DateUtils.isToday(selectedSoccerMatchLastState.getLastStateTimestamp())) {
                                        sendNoMatchSelectedToWatch();
                                        return;
                                    } else if (selectedSoccerMatchLastState.getMatchStatus() == 2) {
                                        a();
                                        return;
                                    } else {
                                        return;
                                    }
                                }
                                return;
                            }
                            callMatchScoreCardAPI();
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x05bb  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x05cc  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x05fa  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x05fe  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x060b  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x061d  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x062b  */
    /* JADX WARN: Type inference failed for: r19v3 */
    /* JADX WARN: Type inference failed for: r19v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d(retrofit2.Response<com.coveiot.android.sportsnotificationsdk.soccer.models.matchinfo.SMatchInfoResponse> r18, int r19) {
        /*
            Method dump skipped, instructions count: 1612
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper.d(retrofit2.Response, int):void");
    }

    public final void e(SoccerData soccerData) {
        LogHelper.d(this.f, "sendMatchCanceledResponseToWatch called");
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this.f5892a).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(context).deviceType");
        j(companion.getSoccerHelper(deviceType).getMatchCanceledRequest(soccerData), new SoccerSportsServiceNew.SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$sendMatchCanceledResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsServiceNew.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void f(SoccerData soccerData) {
        LogHelper.d(this.f, "sendMatchOngoingResponseToWatch called");
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this.f5892a).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(context).deviceType");
        j(companion.getSoccerHelper(deviceType).getSoccerEventRequest(soccerData), new SoccerSportsServiceNew.SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$sendMatchOngoingResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsServiceNew.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void g(SoccerData soccerData) {
        LogHelper.d(this.f, "sendMatchSummaryResponseToWatch called");
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this.f5892a).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(context).deviceType");
        j(companion.getSoccerHelper(deviceType).getSoccerSummaryRequest(soccerData), new SoccerSportsServiceNew.SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$sendMatchSummaryResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsServiceNew.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
                if (z) {
                    SoccerApiHelper.this.l();
                    SoccerApiHelper.this.b();
                }
            }
        });
    }

    public final void getAccessToken() {
        SportsAuthTokenRequest sportsAuthTokenRequest = new SportsAuthTokenRequest();
        sportsAuthTokenRequest.setExtend(4);
        sportsAuthTokenRequest.setCtx("entitysport");
        sportsAuthTokenRequest.setSport("SOCCER");
        CoveSports.getSportsToken(sportsAuthTokenRequest, new CoveApiListener<SportsTokenRes, CoveApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$getAccessToken$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SportsTokenRes sportsTokenRes) {
                if (sportsTokenRes != null) {
                    AccessTokenPreference accessTokenPreference = new AccessTokenPreference(SoccerApiHelper.this.getContext());
                    Data data = sportsTokenRes.getData();
                    Intrinsics.checkNotNull(data);
                    Response response = data.getResponse();
                    Intrinsics.checkNotNull(response);
                    String token = response.getToken();
                    Intrinsics.checkNotNull(token);
                    accessTokenPreference.saveAccessToken(token);
                    SoccerApiHelper.this.callMatchScoreCardAPI();
                }
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5892a;
    }

    @NotNull
    public final String getTAG() {
        return this.f;
    }

    public final void h() {
        LogHelper.d(this.f, "sendOfflineStateToWatch called");
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this.f5892a).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(context).deviceType");
        j(companion.getSoccerHelper(deviceType).getOfflineRequest(), new SoccerSportsServiceNew.SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$sendOfflineStateToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsServiceNew.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
                SportsPreference.Companion.setOfflineStatus(SoccerApiHelper.this.getContext(), true);
            }
        });
    }

    public final void i(SportsNotificationRequest sportsNotificationRequest, final SoccerSportsServiceNew.SportsSettingsResultListener sportsSettingsResultListener, final ArrayList<DynamicSportsField> arrayList) {
        BleApiManager.getInstance(this.f5892a).getBleApi().getData(sportsNotificationRequest, new DataResultListener() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$sendSportCommandAfterVibration$1
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
                if (companion.getSelectedSoccerMatch(SoccerApiHelper.this.getContext()) != null) {
                    MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(SoccerApiHelper.this.getContext());
                    Intrinsics.checkNotNull(selectedSoccerMatch);
                    if (selectedSoccerMatch.getMatchId() != null) {
                        LogHelper.d(SoccerApiHelper.this.getTAG(), "sendSportDataToTheWatch");
                        sportsSettingsResultListener.onSettingsResult(true);
                        AppDynamicSportsField.INSTANCE.setMDynamicSportsFieldList(arrayList);
                        SelectedMatchLastStateData selectedMatchLastStateData = new SelectedMatchLastStateData();
                        MatchListModel selectedSoccerMatch2 = companion.getSelectedSoccerMatch(SoccerApiHelper.this.getContext());
                        Integer matchId = selectedSoccerMatch2 != null ? selectedSoccerMatch2.getMatchId() : null;
                        Intrinsics.checkNotNull(matchId);
                        selectedMatchLastStateData.setMatchId(matchId.intValue());
                        num = SoccerApiHelper.this.e;
                        if (num != null) {
                            num4 = SoccerApiHelper.this.e;
                            Intrinsics.checkNotNull(num4);
                            selectedMatchLastStateData.setGameStatus(num4.intValue());
                        }
                        num2 = SoccerApiHelper.this.d;
                        if (num2 != null) {
                            num3 = SoccerApiHelper.this.d;
                            Intrinsics.checkNotNull(num3);
                            selectedMatchLastStateData.setMatchStatus(num3.intValue());
                        }
                        selectedMatchLastStateData.setLastStateTimestamp(System.currentTimeMillis());
                        companion.saveSelectedSoccerMatchLastState(SoccerApiHelper.this.getContext(), selectedMatchLastStateData);
                    }
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void j(ArrayList<DynamicSportsField> arrayList, SoccerSportsServiceNew.SportsSettingsResultListener sportsSettingsResultListener) {
        e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SoccerApiHelper$sendSportDataToTheWatch$1(this, arrayList, sportsSettingsResultListener, null), 2, null);
    }

    public final void k(SoccerData soccerData) {
        LogHelper.d(this.f, "sendYetToStartResponseToWatch called");
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this.f5892a).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(context).deviceType");
        j(companion.getSoccerHelper(deviceType).getMatchStartAtRequest(soccerData), new SoccerSportsServiceNew.SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$sendYetToStartResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsServiceNew.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void l() {
        Intent intent = new Intent("ACTION_MATCH_UPDATE");
        intent.putExtra(Constants.KEY_ACTION, "stop_periodic_handler");
        LocalBroadcastManager.getInstance(this.f5892a).sendBroadcast(intent);
    }

    public final void resetStates() {
        this.b = -1L;
        this.c = 0;
    }

    public final void sendNoMatchSelectedToWatch() {
        LogHelper.d(this.f, "sendNoMatchSelectedToWatch called");
        SoccerHelperFactory.Companion companion = SoccerHelperFactory.Companion;
        DeviceType deviceType = BleApiManager.getInstance(this.f5892a).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(context).deviceType");
        j(companion.getSoccerHelper(deviceType).getNoMatchSelectedRequest(), new SoccerSportsServiceNew.SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper$sendNoMatchSelectedToWatch$1
            @Override // com.coveiot.android.sportsnotification.SoccerSportsServiceNew.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
                if (z) {
                    SoccerApiHelper.this.l();
                }
            }
        });
    }
}
