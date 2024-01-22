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
import android.text.format.DateUtils;
import androidx.core.content.ContextCompat;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.SportsNotificationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.idoSdk.IDOConstants;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.model.AppDynamicSportsField;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.model.SelectedMatchLastStateData;
import com.coveiot.android.sportsnotification.model.SpSyncComplete;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.sportsnotification.model.WatchCricketUIModel;
import com.coveiot.android.sportsnotification.utils.FontLengthUtility;
import com.coveiot.android.sportsnotification.utils.FontLengthUtilityOPP1;
import com.coveiot.android.sportsnotification.utils.FontLengthUtilityOPP2;
import com.coveiot.android.sportsnotification.utils.FontLengthUtilityOPP3;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSports;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sports.Data;
import com.coveiot.coveaccess.sports.Response;
import com.coveiot.coveaccess.sports.SportsAuthTokenRequest;
import com.coveiot.coveaccess.sports.SportsTokenRes;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SportsService extends Service {
    @Nullable
    public Integer h;
    @Nullable
    public Integer i;
    @NotNull
    public final String j;
    public int k;
    @Nullable
    public final Handler l;
    @Nullable
    public final Handler m;
    @Nullable
    public final Handler n;
    public IBinder o;
    @NotNull
    public final String p;
    public int q;

    /* loaded from: classes7.dex */
    public final class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        @NotNull
        public final SportsService getService() {
            return SportsService.this;
        }
    }

    /* loaded from: classes7.dex */
    public interface SportsSettingsResultListener {
        void onSettingsResult(boolean z);
    }

    @DebugMetadata(c = "com.coveiot.android.sportsnotification.SportsService$onStartCommand$1$1", f = "SportsService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
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
                SportsService.this.d();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public SportsService() {
        String simpleName = SportsService.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SportsService::class.java.simpleName");
        this.j = simpleName;
        this.k = IDOConstants.MULTI_PACKET_CMD_MS_TIMER;
        this.l = new Handler();
        this.m = new Handler();
        this.n = new Handler();
        this.p = "341";
        this.q = com.veryfit.multi.nativeprotocol.b.C1;
        this.o = new BtServiceBinder();
    }

    public static final void o(SportsService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d();
        this$0.n();
    }

    public static final void q(SportsService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        kotlinx.coroutines.e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new a(null), 3, null);
    }

    public final void A() {
        int stringWidth$default;
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WatchCricketUIModel watchCricketUIPreference = sportsUtils.getWatchCricketUIPreference(applicationContext);
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        if (!sportsUtils.isOPP3Device(applicationContext2)) {
            Context applicationContext3 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
            if (!sportsUtils.isOPP4Device(applicationContext3)) {
                Context applicationContext4 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
                if (!sportsUtils.isOPP1Device(applicationContext4)) {
                    Context applicationContext5 = getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext5, "applicationContext");
                    if (!sportsUtils.isOPP5Device(applicationContext5)) {
                        Context applicationContext6 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext6, "applicationContext");
                        if (sportsUtils.isOPP2Device(applicationContext6)) {
                            stringWidth$default = (this.q - FontLengthUtilityOPP2.getStringWidth$default(FontLengthUtilityOPP2.INSTANCE, "Internet offline!", 0, 2, null)) / 2;
                        } else {
                            stringWidth$default = watchCricketUIPreference.getGameStatusXPosition() - 64;
                        }
                        int i = stringWidth$default;
                        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
                        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, sportsUtils.getSportsCricketImageID(this)));
                        int gameStatusRGBColor = sportsUtils.getGameStatusRGBColor(this);
                        int gameStatusYPosition = watchCricketUIPreference.getGameStatusYPosition();
                        Context applicationContext7 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext7, "applicationContext");
                        int gameStatusLength = sportsUtils.getGameStatusLength(applicationContext7);
                        Context applicationContext8 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext8, "applicationContext");
                        int gameStatusWidth = sportsUtils.getGameStatusWidth(applicationContext8);
                        Context applicationContext9 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext9, "applicationContext");
                        arrayList.add(new DynamicSportFieldText(2, gameStatusRGBColor, i, gameStatusYPosition, gameStatusLength, gameStatusWidth, sportsUtils.getGameStatusFont(applicationContext9), 0, "Internet offline!"));
                        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendOfflineStateToWatch$1
                            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
                            public void onSettingsResult(boolean z) {
                                SportsPreference.Companion.setOfflineStatus(SportsService.this, true);
                            }
                        });
                    }
                }
                stringWidth$default = (this.q - FontLengthUtilityOPP1.getStringWidth$default(FontLengthUtilityOPP1.INSTANCE, "Internet offline!", 0, 2, null)) / 2;
                int i2 = stringWidth$default;
                ArrayList<DynamicSportsField> arrayList2 = new ArrayList<>();
                arrayList2.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, sportsUtils.getSportsCricketImageID(this)));
                int gameStatusRGBColor2 = sportsUtils.getGameStatusRGBColor(this);
                int gameStatusYPosition2 = watchCricketUIPreference.getGameStatusYPosition();
                Context applicationContext72 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext72, "applicationContext");
                int gameStatusLength2 = sportsUtils.getGameStatusLength(applicationContext72);
                Context applicationContext82 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext82, "applicationContext");
                int gameStatusWidth2 = sportsUtils.getGameStatusWidth(applicationContext82);
                Context applicationContext92 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext92, "applicationContext");
                arrayList2.add(new DynamicSportFieldText(2, gameStatusRGBColor2, i2, gameStatusYPosition2, gameStatusLength2, gameStatusWidth2, sportsUtils.getGameStatusFont(applicationContext92), 0, "Internet offline!"));
                C(arrayList2, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendOfflineStateToWatch$1
                    @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
                    public void onSettingsResult(boolean z) {
                        SportsPreference.Companion.setOfflineStatus(SportsService.this, true);
                    }
                });
            }
        }
        stringWidth$default = (this.q - FontLengthUtilityOPP3.getStringWidth$default(FontLengthUtilityOPP3.INSTANCE, "Internet offline!", 0, 2, null)) / 2;
        int i22 = stringWidth$default;
        ArrayList<DynamicSportsField> arrayList22 = new ArrayList<>();
        arrayList22.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, sportsUtils.getSportsCricketImageID(this)));
        int gameStatusRGBColor22 = sportsUtils.getGameStatusRGBColor(this);
        int gameStatusYPosition22 = watchCricketUIPreference.getGameStatusYPosition();
        Context applicationContext722 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext722, "applicationContext");
        int gameStatusLength22 = sportsUtils.getGameStatusLength(applicationContext722);
        Context applicationContext822 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext822, "applicationContext");
        int gameStatusWidth22 = sportsUtils.getGameStatusWidth(applicationContext822);
        Context applicationContext922 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext922, "applicationContext");
        arrayList22.add(new DynamicSportFieldText(2, gameStatusRGBColor22, i22, gameStatusYPosition22, gameStatusLength22, gameStatusWidth22, sportsUtils.getGameStatusFont(applicationContext922), 0, "Internet offline!"));
        C(arrayList22, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendOfflineStateToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
                SportsPreference.Companion.setOfflineStatus(SportsService.this, true);
            }
        });
    }

    public final void B(SportsNotificationRequest sportsNotificationRequest, final SportsSettingsResultListener sportsSettingsResultListener, final ArrayList<DynamicSportsField> arrayList) {
        BleApiManager.getInstance(this).getBleApi().getData(sportsNotificationRequest, new DataResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendSportCommandAfterVibration$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                sportsSettingsResultListener.onSettingsResult(false);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String str;
                Integer num;
                Integer num2;
                Integer num3;
                Integer num4;
                Intrinsics.checkNotNullParameter(response, "response");
                SportsPreference.Companion companion = SportsPreference.Companion;
                if (companion.getSelectedMatch(SportsService.this) != null) {
                    MatchListModel selectedMatch = companion.getSelectedMatch(SportsService.this);
                    Intrinsics.checkNotNull(selectedMatch);
                    if (selectedMatch.getMatchId() != null) {
                        str = SportsService.this.j;
                        LogHelper.d(str, "sendSportDataToTheWatch");
                        sportsSettingsResultListener.onSettingsResult(true);
                        AppDynamicSportsField.INSTANCE.setMDynamicSportsFieldList(arrayList);
                        SelectedMatchLastStateData selectedMatchLastStateData = new SelectedMatchLastStateData();
                        MatchListModel selectedMatch2 = companion.getSelectedMatch(SportsService.this);
                        Integer matchId = selectedMatch2 != null ? selectedMatch2.getMatchId() : null;
                        Intrinsics.checkNotNull(matchId);
                        selectedMatchLastStateData.setMatchId(matchId.intValue());
                        num = SportsService.this.i;
                        if (num != null) {
                            num4 = SportsService.this.i;
                            Intrinsics.checkNotNull(num4);
                            selectedMatchLastStateData.setGameStatus(num4.intValue());
                        }
                        num2 = SportsService.this.h;
                        if (num2 != null) {
                            num3 = SportsService.this.h;
                            Intrinsics.checkNotNull(num3);
                            selectedMatchLastStateData.setMatchStatus(num3.intValue());
                        }
                        selectedMatchLastStateData.setLastStateTimestamp(System.currentTimeMillis());
                        companion.saveSelectedMatchLastState(SportsService.this, selectedMatchLastStateData);
                    }
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void C(ArrayList<DynamicSportsField> arrayList, SportsSettingsResultListener sportsSettingsResultListener) {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SportsService$sendSportDataToTheWatch$1(this, arrayList, sportsSettingsResultListener, null), 2, null);
    }

    public final void D(String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(i());
        arrayList.add(f(2, str));
        arrayList.add(j(3, str2));
        arrayList.add(l(4, str3));
        arrayList.add(k(5, str4, str5));
        arrayList.add(m(6, str4, str5));
        arrayList.add(g(7, str6));
        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendStumpsResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void E(String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(i());
        arrayList.add(f(2, str));
        arrayList.add(j(3, str2));
        arrayList.add(l(4, str3));
        arrayList.add(k(5, str4, str5));
        arrayList.add(m(6, str4, str5));
        arrayList.add(g(7, str6));
        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendTeaBreakResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void F(String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(i());
        arrayList.add(f(2, str));
        arrayList.add(j(3, str2));
        arrayList.add(l(4, str3));
        arrayList.add(k(5, str4, str5));
        arrayList.add(m(6, str4, str5));
        arrayList.add(g(7, str6));
        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendTossResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void G(String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(i());
        arrayList.add(f(2, str));
        arrayList.add(j(3, str2));
        arrayList.add(l(4, str3));
        arrayList.add(k(5, str4, str5));
        arrayList.add(m(6, str4, str5));
        arrayList.add(g(7, str6));
        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendUnknownResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void H(String str, String str2, String str3, String str4, String str5, String str6) {
        int stringWidth$default;
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WatchCricketUIModel watchCricketUIPreference = sportsUtils.getWatchCricketUIPreference(applicationContext);
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        if (!sportsUtils.isOPP3Device(applicationContext2)) {
            Context applicationContext3 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
            if (!sportsUtils.isOPP4Device(applicationContext3)) {
                Context applicationContext4 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
                if (!sportsUtils.isOPP1Device(applicationContext4)) {
                    Context applicationContext5 = getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext5, "applicationContext");
                    if (!sportsUtils.isOPP5Device(applicationContext5)) {
                        Context applicationContext6 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext6, "applicationContext");
                        if (sportsUtils.isOPP2Device(applicationContext6)) {
                            stringWidth$default = (this.q - FontLengthUtilityOPP2.getStringWidth$default(FontLengthUtilityOPP2.INSTANCE, str6, 0, 2, null)) / 2;
                        } else {
                            stringWidth$default = (watchCricketUIPreference.getGameStatusXPosition() - ((str6.length() / 2) * 8)) + 7;
                        }
                        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
                        arrayList.add(i());
                        arrayList.add(f(2, str));
                        arrayList.add(j(3, str2));
                        arrayList.add(l(4, str3));
                        arrayList.add(k(5, str4, str5));
                        arrayList.add(m(6, str4, str5));
                        arrayList.add(h(7, str6, stringWidth$default));
                        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendYetToStartResponseToWatch$1
                            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
                            public void onSettingsResult(boolean z) {
                            }
                        });
                    }
                }
                stringWidth$default = (this.q - FontLengthUtilityOPP1.getStringWidth$default(FontLengthUtilityOPP1.INSTANCE, str6, 0, 2, null)) / 2;
                ArrayList<DynamicSportsField> arrayList2 = new ArrayList<>();
                arrayList2.add(i());
                arrayList2.add(f(2, str));
                arrayList2.add(j(3, str2));
                arrayList2.add(l(4, str3));
                arrayList2.add(k(5, str4, str5));
                arrayList2.add(m(6, str4, str5));
                arrayList2.add(h(7, str6, stringWidth$default));
                C(arrayList2, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendYetToStartResponseToWatch$1
                    @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
                    public void onSettingsResult(boolean z) {
                    }
                });
            }
        }
        stringWidth$default = (this.q - FontLengthUtilityOPP3.getStringWidth$default(FontLengthUtilityOPP3.INSTANCE, str6, 0, 2, null)) / 2;
        ArrayList<DynamicSportsField> arrayList22 = new ArrayList<>();
        arrayList22.add(i());
        arrayList22.add(f(2, str));
        arrayList22.add(j(3, str2));
        arrayList22.add(l(4, str3));
        arrayList22.add(k(5, str4, str5));
        arrayList22.add(m(6, str4, str5));
        arrayList22.add(h(7, str6, stringWidth$default));
        C(arrayList22, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendYetToStartResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void I() {
        Handler handler = this.l;
        if (handler != null) {
            Intrinsics.checkNotNull(handler);
            handler.removeCallbacksAndMessages(null);
        }
    }

    public final void c() {
        kotlinx.coroutines.e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new SportsService$callMatchScoreCardAPI$1(this, null), 3, null);
    }

    public final void d() {
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        this.q = sportsUtils.getWatchCricketUIPreference(this).getBackgroundFileWidth();
        String str = this.j;
        LogHelper.d(str, "getScoreCardData Running --- " + System.currentTimeMillis());
        if (BleApiManager.getInstance(this).getBleApi() == null || BleApiManager.getInstance(this).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        if (sportsUtils.isCZDevice(this) || sportsUtils.isCADevice(this) || SportsUtils.isCYDevice(this) || sportsUtils.isOPPDevice(this)) {
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
                        MatchListModel selectedMatch = companion.getSelectedMatch(this);
                        SelectedMatchLastStateData selectedMatchLastState = companion.getSelectedMatchLastState(this);
                        if (selectedMatch != null) {
                            SportsPreferenceModel sportsNotification3 = companion.getSportsNotification(this);
                            Intrinsics.checkNotNull(sportsNotification3);
                            Integer interval = sportsNotification3.getInterval();
                            if (interval != null) {
                                this.k = interval.intValue() * 60000;
                            }
                            if (selectedMatchLastState != null && selectedMatch.getDate() != null) {
                                String date = selectedMatch.getDate();
                                Intrinsics.checkNotNull(date);
                                try {
                                    if (System.currentTimeMillis() > sportsUtils.minusMinutesFromCalendar(60, sportsUtils.getTimeInCurrentZone(date)).getTimeInMillis()) {
                                        if (selectedMatchLastState.getMatchStatus() != 2 && selectedMatchLastState.getMatchStatus() != 4) {
                                            c();
                                        }
                                        if (selectedMatchLastState.getLastStateTimestamp() != 0 && !DateUtils.isToday(selectedMatchLastState.getLastStateTimestamp())) {
                                            z();
                                        } else if (selectedMatchLastState.getLastStateTimestamp() == 0) {
                                            String date2 = selectedMatch.getDate();
                                            Intrinsics.checkNotNull(date2);
                                            if (!DateUtils.isToday(sportsUtils.getStartTime(date2).getTimeInMillis())) {
                                                z();
                                            }
                                        }
                                    }
                                    return;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            c();
                        }
                    }
                }
            }
        }
    }

    public final void e() {
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
            NotificationChannel notificationChannel = new NotificationChannel(this.p, "Cricket Score updates", 2);
            notificationChannel.enableLights(false);
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
            build = new Notification.Builder(this, this.p).setContentTitle("Cricket Score updates").setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this, CRICKET_NO…\n                .build()");
        } else if (i >= 21) {
            build = new Notification.Builder(this).setContentTitle("Cricket Score updates").setSmallIcon(data.getAppIcon()).setColor(ContextCompat.getColor(this, data.getAppColor())).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        } else {
            build = new Notification.Builder(this).setContentTitle("Cricket Score updates").setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        }
        startForeground(Integer.parseInt(this.p), build);
    }

    public final DynamicSportFieldText f(int i, String str) {
        int stringWidth$default;
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WatchCricketUIModel watchCricketUIPreference = sportsUtils.getWatchCricketUIPreference(applicationContext);
        int matchFormatXPosition = watchCricketUIPreference.getMatchFormatXPosition();
        if (str.length() % 2 != 0) {
            matchFormatXPosition = watchCricketUIPreference.getMatchFormatXPosition2() - 10;
        }
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        if (!sportsUtils.isOPP3Device(applicationContext2)) {
            Context applicationContext3 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
            if (!sportsUtils.isOPP4Device(applicationContext3)) {
                Context applicationContext4 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
                if (sportsUtils.isPS1Device(applicationContext4)) {
                    stringWidth$default = (this.q - FontLengthUtility.INSTANCE.getStringWidth(str, 28)) / 2;
                } else {
                    stringWidth$default = matchFormatXPosition - ((str.length() / 2) * 10);
                }
                int matchFormatRGBColor = sportsUtils.getMatchFormatRGBColor();
                int matchFormatYPosition = watchCricketUIPreference.getMatchFormatYPosition();
                Context applicationContext5 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext5, "applicationContext");
                int matchFormatLWF = sportsUtils.getMatchFormatLWF(applicationContext5);
                Context applicationContext6 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext6, "applicationContext");
                int matchFormatLWF2 = sportsUtils.getMatchFormatLWF(applicationContext6);
                Context applicationContext7 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext7, "applicationContext");
                return new DynamicSportFieldText(i, matchFormatRGBColor, stringWidth$default, matchFormatYPosition, matchFormatLWF, matchFormatLWF2, sportsUtils.getMatchFormatLWF(applicationContext7), 0, str);
            }
        }
        stringWidth$default = (this.q - FontLengthUtilityOPP3.getStringWidth$default(FontLengthUtilityOPP3.INSTANCE, str, 0, 2, null)) / 2;
        int matchFormatRGBColor2 = sportsUtils.getMatchFormatRGBColor();
        int matchFormatYPosition2 = watchCricketUIPreference.getMatchFormatYPosition();
        Context applicationContext52 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext52, "applicationContext");
        int matchFormatLWF3 = sportsUtils.getMatchFormatLWF(applicationContext52);
        Context applicationContext62 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext62, "applicationContext");
        int matchFormatLWF22 = sportsUtils.getMatchFormatLWF(applicationContext62);
        Context applicationContext72 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext72, "applicationContext");
        return new DynamicSportFieldText(i, matchFormatRGBColor2, stringWidth$default, matchFormatYPosition2, matchFormatLWF3, matchFormatLWF22, sportsUtils.getMatchFormatLWF(applicationContext72), 0, str);
    }

    public final DynamicSportFieldText g(int i, String str) {
        int stringWidth$default;
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WatchCricketUIModel watchCricketUIPreference = sportsUtils.getWatchCricketUIPreference(applicationContext);
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        if (!sportsUtils.isOPP3Device(applicationContext2)) {
            Context applicationContext3 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
            if (!sportsUtils.isOPP4Device(applicationContext3)) {
                Context applicationContext4 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
                if (!sportsUtils.isOPP1Device(applicationContext4)) {
                    Context applicationContext5 = getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext5, "applicationContext");
                    if (!sportsUtils.isOPP5Device(applicationContext5)) {
                        Context applicationContext6 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext6, "applicationContext");
                        if (sportsUtils.isOPP2Device(applicationContext6)) {
                            stringWidth$default = (this.q - FontLengthUtilityOPP2.getStringWidth$default(FontLengthUtilityOPP2.INSTANCE, str, 0, 2, null)) / 2;
                        } else {
                            stringWidth$default = watchCricketUIPreference.getGameStatusXPosition2() - ((str.length() / 2) * 8);
                        }
                        int gameStatusRGBColor = sportsUtils.getGameStatusRGBColor(this);
                        int gameStatusYPosition = watchCricketUIPreference.getGameStatusYPosition();
                        Context applicationContext7 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext7, "applicationContext");
                        int gameStatusLength = sportsUtils.getGameStatusLength(applicationContext7);
                        Context applicationContext8 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext8, "applicationContext");
                        int gameStatusWidth = sportsUtils.getGameStatusWidth(applicationContext8);
                        Context applicationContext9 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext9, "applicationContext");
                        return new DynamicSportFieldText(i, gameStatusRGBColor, stringWidth$default, gameStatusYPosition, gameStatusLength, gameStatusWidth, sportsUtils.getGameStatusFont(applicationContext9), 0, str);
                    }
                }
                stringWidth$default = (this.q - FontLengthUtilityOPP1.getStringWidth$default(FontLengthUtilityOPP1.INSTANCE, str, 0, 2, null)) / 2;
                int gameStatusRGBColor2 = sportsUtils.getGameStatusRGBColor(this);
                int gameStatusYPosition2 = watchCricketUIPreference.getGameStatusYPosition();
                Context applicationContext72 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext72, "applicationContext");
                int gameStatusLength2 = sportsUtils.getGameStatusLength(applicationContext72);
                Context applicationContext82 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext82, "applicationContext");
                int gameStatusWidth2 = sportsUtils.getGameStatusWidth(applicationContext82);
                Context applicationContext92 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext92, "applicationContext");
                return new DynamicSportFieldText(i, gameStatusRGBColor2, stringWidth$default, gameStatusYPosition2, gameStatusLength2, gameStatusWidth2, sportsUtils.getGameStatusFont(applicationContext92), 0, str);
            }
        }
        stringWidth$default = (this.q - FontLengthUtilityOPP3.getStringWidth$default(FontLengthUtilityOPP3.INSTANCE, str, 0, 2, null)) / 2;
        int gameStatusRGBColor22 = sportsUtils.getGameStatusRGBColor(this);
        int gameStatusYPosition22 = watchCricketUIPreference.getGameStatusYPosition();
        Context applicationContext722 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext722, "applicationContext");
        int gameStatusLength22 = sportsUtils.getGameStatusLength(applicationContext722);
        Context applicationContext822 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext822, "applicationContext");
        int gameStatusWidth22 = sportsUtils.getGameStatusWidth(applicationContext822);
        Context applicationContext922 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext922, "applicationContext");
        return new DynamicSportFieldText(i, gameStatusRGBColor22, stringWidth$default, gameStatusYPosition22, gameStatusLength22, gameStatusWidth22, sportsUtils.getGameStatusFont(applicationContext922), 0, str);
    }

    public final void getAccessToken() {
        SportsAuthTokenRequest sportsAuthTokenRequest = new SportsAuthTokenRequest();
        sportsAuthTokenRequest.setExtend(4);
        sportsAuthTokenRequest.setCtx("entitysport");
        sportsAuthTokenRequest.setSport("CRICKET");
        CoveSports.getSportsToken(sportsAuthTokenRequest, new CoveApiListener<SportsTokenRes, CoveApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.SportsService$getAccessToken$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SportsTokenRes sportsTokenRes) {
                if (sportsTokenRes != null) {
                    AccessTokenPreference accessTokenPreference = new AccessTokenPreference(SportsService.this);
                    Data data = sportsTokenRes.getData();
                    Intrinsics.checkNotNull(data);
                    Response response = data.getResponse();
                    Intrinsics.checkNotNull(response);
                    String token = response.getToken();
                    Intrinsics.checkNotNull(token);
                    accessTokenPreference.saveAccessToken(token);
                    SportsService.this.c();
                }
            }
        });
    }

    public final int getScreenWidth() {
        return this.q;
    }

    public final DynamicSportFieldText h(int i, String str, int i2) {
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WatchCricketUIModel watchCricketUIPreference = sportsUtils.getWatchCricketUIPreference(applicationContext);
        int gameStatusRGBColor = sportsUtils.getGameStatusRGBColor(this);
        int gameStatusYPosition = watchCricketUIPreference.getGameStatusYPosition();
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        int gameStatusLength = sportsUtils.getGameStatusLength(applicationContext2);
        Context applicationContext3 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
        int gameStatusWidth = sportsUtils.getGameStatusWidth(applicationContext3);
        Context applicationContext4 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
        return new DynamicSportFieldText(i, gameStatusRGBColor, i2, gameStatusYPosition, gameStatusLength, gameStatusWidth, sportsUtils.getGameStatusFont(applicationContext4), 0, str);
    }

    public final DynamicSportFieldImage i() {
        return new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, SportsUtils.INSTANCE.getSportsCricketImageID(this));
    }

    public final DynamicSportFieldText j(int i, String str) {
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WatchCricketUIModel watchCricketUIPreference = sportsUtils.getWatchCricketUIPreference(applicationContext);
        int teamANameRGBColor = sportsUtils.getTeamANameRGBColor();
        int teamANameXPosition = watchCricketUIPreference.getTeamANameXPosition();
        int teamANameYPosition = watchCricketUIPreference.getTeamANameYPosition();
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        int teamANameLWF = sportsUtils.getTeamANameLWF(applicationContext2);
        Context applicationContext3 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
        int teamANameLWF2 = sportsUtils.getTeamANameLWF(applicationContext3);
        Context applicationContext4 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
        return new DynamicSportFieldText(i, teamANameRGBColor, teamANameXPosition, teamANameYPosition, teamANameLWF, teamANameLWF2, sportsUtils.getTeamANameLWF(applicationContext4), 0, str);
    }

    public final DynamicSportFieldText k(int i, String str, String str2) {
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WatchCricketUIModel watchCricketUIPreference = sportsUtils.getWatchCricketUIPreference(applicationContext);
        if (str.length() <= 10 && str2.length() <= 10) {
            int teamAScoreRGBColor = sportsUtils.getTeamAScoreRGBColor();
            int teamAScoreXPosition = watchCricketUIPreference.getTeamAScoreXPosition();
            int teamAScoreYPosition = watchCricketUIPreference.getTeamAScoreYPosition();
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
            int teamAScoreLWF = sportsUtils.getTeamAScoreLWF(applicationContext2);
            Context applicationContext3 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
            int teamAScoreLWF2 = sportsUtils.getTeamAScoreLWF(applicationContext3);
            Context applicationContext4 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
            return new DynamicSportFieldText(i, teamAScoreRGBColor, teamAScoreXPosition, teamAScoreYPosition, teamAScoreLWF, teamAScoreLWF2, sportsUtils.getTeamAScoreLWF(applicationContext4), 0, AppUtils.isEmpty(str) ? "Yet to bat" : str);
        }
        int teamAScoreRGBColor2 = sportsUtils.getTeamAScoreRGBColor();
        int teamAScoreXPosition2 = watchCricketUIPreference.getTeamAScoreXPosition2();
        int teamAScoreYPosition2 = watchCricketUIPreference.getTeamAScoreYPosition();
        Context applicationContext5 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext5, "applicationContext");
        int teamAScoreLWF3 = sportsUtils.getTeamAScoreLWF(applicationContext5);
        Context applicationContext6 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext6, "applicationContext");
        int teamAScoreLWF4 = sportsUtils.getTeamAScoreLWF(applicationContext6);
        Context applicationContext7 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext7, "applicationContext");
        return new DynamicSportFieldText(i, teamAScoreRGBColor2, teamAScoreXPosition2, teamAScoreYPosition2, teamAScoreLWF3, teamAScoreLWF4, sportsUtils.getTeamAScoreLWF(applicationContext7), 0, AppUtils.isEmpty(str) ? "Yet to bat" : str);
    }

    public final DynamicSportFieldText l(int i, String str) {
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WatchCricketUIModel watchCricketUIPreference = sportsUtils.getWatchCricketUIPreference(applicationContext);
        int teamBNameRGBColor = sportsUtils.getTeamBNameRGBColor();
        int teamBNameXPosition = watchCricketUIPreference.getTeamBNameXPosition();
        int teamBNameYPosition = watchCricketUIPreference.getTeamBNameYPosition();
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        int teamBNameLWF = sportsUtils.getTeamBNameLWF(applicationContext2);
        Context applicationContext3 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
        int teamBNameLWF2 = sportsUtils.getTeamBNameLWF(applicationContext3);
        Context applicationContext4 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
        return new DynamicSportFieldText(i, teamBNameRGBColor, teamBNameXPosition, teamBNameYPosition, teamBNameLWF, teamBNameLWF2, sportsUtils.getTeamBNameLWF(applicationContext4), 0, str);
    }

    public final DynamicSportFieldText m(int i, String str, String str2) {
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WatchCricketUIModel watchCricketUIPreference = sportsUtils.getWatchCricketUIPreference(applicationContext);
        if (str.length() <= 10 && str2.length() <= 10) {
            int teamBScoreRGBColor = sportsUtils.getTeamBScoreRGBColor();
            int teamBScoreXPosition = watchCricketUIPreference.getTeamBScoreXPosition();
            int teamBScoreYPosition = watchCricketUIPreference.getTeamBScoreYPosition();
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
            int teamBScoreLWF = sportsUtils.getTeamBScoreLWF(applicationContext2);
            Context applicationContext3 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
            int teamBScoreLWF2 = sportsUtils.getTeamBScoreLWF(applicationContext3);
            Context applicationContext4 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
            return new DynamicSportFieldText(i, teamBScoreRGBColor, teamBScoreXPosition, teamBScoreYPosition, teamBScoreLWF, teamBScoreLWF2, sportsUtils.getTeamBScoreLWF(applicationContext4), 0, AppUtils.isEmpty(str2) ? "Yet to bat" : str2);
        }
        int teamBScoreRGBColor2 = sportsUtils.getTeamBScoreRGBColor();
        int teamBScoreXPosition2 = watchCricketUIPreference.getTeamBScoreXPosition2();
        int teamBScoreYPosition2 = watchCricketUIPreference.getTeamBScoreYPosition();
        Context applicationContext5 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext5, "applicationContext");
        int teamBScoreLWF3 = sportsUtils.getTeamBScoreLWF(applicationContext5);
        Context applicationContext6 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext6, "applicationContext");
        int teamBScoreLWF4 = sportsUtils.getTeamBScoreLWF(applicationContext6);
        Context applicationContext7 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext7, "applicationContext");
        return new DynamicSportFieldText(i, teamBScoreRGBColor2, teamBScoreXPosition2, teamBScoreYPosition2, teamBScoreLWF3, teamBScoreLWF4, sportsUtils.getTeamBScoreLWF(applicationContext7), 0, AppUtils.isEmpty(str2) ? "Yet to bat" : str2);
    }

    public final void n() {
        Handler handler = this.l;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.l
                @Override // java.lang.Runnable
                public final void run() {
                    SportsService.o(SportsService.this);
                }
            }, this.k);
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

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        e();
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
            LogHelper.d(this.j, "Service onDestroy");
            Handler handler = this.l;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            stopForeground(true);
            stopSelf();
            CoveEventBusManager.getInstance().getEventBus().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        if (intent == null) {
            try {
                Intent intent2 = new Intent(this, SportsService.class);
                if (Build.VERSION.SDK_INT >= 26) {
                    startForegroundService(intent2);
                } else {
                    startService(intent2);
                }
            } catch (Exception e) {
                e.printStackTrace();
                BleApiUtils.checkExceptionAndShowNotification(e, this);
            }
            return 3;
        }
        e();
        this.q = SportsUtils.INSTANCE.getWatchCricketUIPreference(this).getBackgroundFileWidth();
        c();
        Handler handler = this.n;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        SportsPreference.Companion companion = SportsPreference.Companion;
        if (companion.getSelectedMatch(this) != null) {
            MatchListModel selectedMatch = companion.getSelectedMatch(this);
            Intrinsics.checkNotNull(selectedMatch);
            if (selectedMatch.getDate() != null) {
                MatchListModel selectedMatch2 = companion.getSelectedMatch(this);
                Intrinsics.checkNotNull(selectedMatch2);
                String date = selectedMatch2.getDate();
                Intrinsics.checkNotNull(date);
                Calendar p = p(date);
                Handler handler2 = this.n;
                if (handler2 != null) {
                    handler2.postAtTime(new Runnable() { // from class: com.coveiot.android.sportsnotification.m
                        @Override // java.lang.Runnable
                        public final void run() {
                            SportsService.q(SportsService.this);
                        }
                    }, p.getTimeInMillis());
                }
            } else {
                z();
            }
        } else {
            z();
        }
        n();
        return 3;
    }

    @Subscribe
    public final void onSyncCompleteReceived(@NotNull SpSyncComplete spSyncComplete) {
        Intrinsics.checkNotNullParameter(spSyncComplete, "spSyncComplete");
        SportsPreference.Companion companion = SportsPreference.Companion;
        if (companion.getSelectedMatchLastState(this) == null || companion.getSportsNotification(this) == null) {
            return;
        }
        SportsPreferenceModel sportsNotification = companion.getSportsNotification(this);
        if ((sportsNotification != null ? sportsNotification.getInterval() : null) != null) {
            long currentTimeMillis = System.currentTimeMillis();
            SelectedMatchLastStateData selectedMatchLastState = companion.getSelectedMatchLastState(this);
            Long valueOf = selectedMatchLastState != null ? Long.valueOf(selectedMatchLastState.getLastStateTimestamp()) : null;
            Intrinsics.checkNotNull(valueOf);
            long longValue = currentTimeMillis - valueOf.longValue();
            SportsPreferenceModel sportsNotification2 = companion.getSportsNotification(this);
            Integer interval = sportsNotification2 != null ? sportsNotification2.getInterval() : null;
            Intrinsics.checkNotNull(interval);
            if (longValue > interval.intValue() * 60 * 1000) {
                d();
            }
        }
    }

    public final Calendar p(String str) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Intrinsics.checkNotNull(str);
        calendar.setTime(simpleDateFormat.parse(str));
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0469  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x04a4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04df  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0311  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void r(retrofit2.Response<com.coveiot.android.sportsnotificationsdk.models.scorecard.GetScorecardRes> r17, int r18) {
        /*
            Method dump skipped, instructions count: 1296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.SportsService.r(retrofit2.Response, int):void");
    }

    public final void resetUpdates() {
        Handler handler = this.l;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        LogHelper.d(this.j, "resetUpdates");
        c();
        n();
    }

    public final void restartService() {
        stopForeground(true);
        stopSelf();
    }

    public final void s(String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(i());
        arrayList.add(f(2, str));
        arrayList.add(j(3, str2));
        arrayList.add(l(4, str3));
        arrayList.add(k(5, str4, str5));
        arrayList.add(m(6, str4, str5));
        arrayList.add(g(7, str6));
        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendDrinkBreakResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void setScreenWidth(int i) {
        this.q = i;
    }

    public final void stopService() {
        Handler handler = this.l;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        stopForeground(true);
        stopSelf();
    }

    public final void t(String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(i());
        arrayList.add(f(2, str));
        arrayList.add(j(3, str2));
        arrayList.add(l(4, str3));
        arrayList.add(k(5, str4, str5));
        arrayList.add(m(6, str4, str5));
        arrayList.add(g(7, str6));
        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendInningsBreakResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void u(String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(i());
        arrayList.add(f(2, str));
        arrayList.add(j(3, str2));
        arrayList.add(l(4, str3));
        arrayList.add(k(5, str4, str5));
        arrayList.add(m(6, str4, str5));
        arrayList.add(g(7, str6));
        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendLunchBreakResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void v(String str, String str2, String str3, String str4) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(i());
        arrayList.add(f(2, str));
        arrayList.add(j(3, str2));
        arrayList.add(l(4, str3));
        arrayList.add(g(5, str4));
        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendMatchAbandonedResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
                if (z) {
                    SportsService.this.I();
                }
            }
        });
    }

    public final void w(String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(i());
        arrayList.add(f(2, str));
        arrayList.add(j(3, str2));
        arrayList.add(l(4, str3));
        arrayList.add(k(5, str4, str5));
        arrayList.add(m(6, str4, str5));
        arrayList.add(g(7, str6));
        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendMatchCompletedResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
                if (z) {
                    SportsService.this.I();
                }
            }
        });
    }

    public final void x(String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(i());
        arrayList.add(f(2, str));
        arrayList.add(j(3, str2));
        arrayList.add(l(4, str3));
        arrayList.add(k(5, str4, str5));
        arrayList.add(m(6, str4, str5));
        arrayList.add(g(7, str6));
        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendMatchDelayedResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void y(String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(i());
        arrayList.add(f(2, str));
        arrayList.add(j(3, str2));
        arrayList.add(l(4, str3));
        arrayList.add(k(5, str4, str5));
        arrayList.add(m(6, str4, str5));
        arrayList.add(g(7, str6));
        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendMatchOngoingResponseToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
            }
        });
    }

    public final void z() {
        int stringWidth$default;
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        this.q = sportsUtils.getWatchCricketUIPreference(this).getBackgroundFileWidth();
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WatchCricketUIModel watchCricketUIPreference = sportsUtils.getWatchCricketUIPreference(applicationContext);
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        if (!sportsUtils.isOPP3Device(applicationContext2)) {
            Context applicationContext3 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
            if (!sportsUtils.isOPP4Device(applicationContext3)) {
                Context applicationContext4 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
                if (!sportsUtils.isOPP1Device(applicationContext4)) {
                    Context applicationContext5 = getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext5, "applicationContext");
                    if (!sportsUtils.isOPP5Device(applicationContext5)) {
                        Context applicationContext6 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext6, "applicationContext");
                        if (sportsUtils.isOPP2Device(applicationContext6)) {
                            stringWidth$default = (this.q - FontLengthUtilityOPP2.getStringWidth$default(FontLengthUtilityOPP2.INSTANCE, "No match selected", 0, 2, null)) / 2;
                        } else {
                            stringWidth$default = watchCricketUIPreference.getGameStatusXPosition() - 64;
                        }
                        int i = stringWidth$default;
                        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
                        arrayList.add(i());
                        int gameStatusRGBColor = sportsUtils.getGameStatusRGBColor(this);
                        int gameStatusYPosition = watchCricketUIPreference.getGameStatusYPosition();
                        Context applicationContext7 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext7, "applicationContext");
                        int gameStatusLength = sportsUtils.getGameStatusLength(applicationContext7);
                        Context applicationContext8 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext8, "applicationContext");
                        int gameStatusWidth = sportsUtils.getGameStatusWidth(applicationContext8);
                        Context applicationContext9 = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext9, "applicationContext");
                        arrayList.add(new DynamicSportFieldText(2, gameStatusRGBColor, i, gameStatusYPosition, gameStatusLength, gameStatusWidth, sportsUtils.getGameStatusFont(applicationContext9), 0, "No match selected"));
                        C(arrayList, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendNoMatchSelectedToWatch$1
                            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
                            public void onSettingsResult(boolean z) {
                                if (z) {
                                    SportsService.this.I();
                                }
                            }
                        });
                    }
                }
                stringWidth$default = (this.q - FontLengthUtilityOPP1.getStringWidth$default(FontLengthUtilityOPP1.INSTANCE, "No match selected", 0, 2, null)) / 2;
                int i2 = stringWidth$default;
                ArrayList<DynamicSportsField> arrayList2 = new ArrayList<>();
                arrayList2.add(i());
                int gameStatusRGBColor2 = sportsUtils.getGameStatusRGBColor(this);
                int gameStatusYPosition2 = watchCricketUIPreference.getGameStatusYPosition();
                Context applicationContext72 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext72, "applicationContext");
                int gameStatusLength2 = sportsUtils.getGameStatusLength(applicationContext72);
                Context applicationContext82 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext82, "applicationContext");
                int gameStatusWidth2 = sportsUtils.getGameStatusWidth(applicationContext82);
                Context applicationContext92 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext92, "applicationContext");
                arrayList2.add(new DynamicSportFieldText(2, gameStatusRGBColor2, i2, gameStatusYPosition2, gameStatusLength2, gameStatusWidth2, sportsUtils.getGameStatusFont(applicationContext92), 0, "No match selected"));
                C(arrayList2, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendNoMatchSelectedToWatch$1
                    @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
                    public void onSettingsResult(boolean z) {
                        if (z) {
                            SportsService.this.I();
                        }
                    }
                });
            }
        }
        stringWidth$default = (this.q - FontLengthUtilityOPP3.getStringWidth$default(FontLengthUtilityOPP3.INSTANCE, "No match selected", 0, 2, null)) / 2;
        int i22 = stringWidth$default;
        ArrayList<DynamicSportsField> arrayList22 = new ArrayList<>();
        arrayList22.add(i());
        int gameStatusRGBColor22 = sportsUtils.getGameStatusRGBColor(this);
        int gameStatusYPosition22 = watchCricketUIPreference.getGameStatusYPosition();
        Context applicationContext722 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext722, "applicationContext");
        int gameStatusLength22 = sportsUtils.getGameStatusLength(applicationContext722);
        Context applicationContext822 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext822, "applicationContext");
        int gameStatusWidth22 = sportsUtils.getGameStatusWidth(applicationContext822);
        Context applicationContext922 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext922, "applicationContext");
        arrayList22.add(new DynamicSportFieldText(2, gameStatusRGBColor22, i22, gameStatusYPosition22, gameStatusLength22, gameStatusWidth22, sportsUtils.getGameStatusFont(applicationContext922), 0, "No match selected"));
        C(arrayList22, new SportsSettingsResultListener() { // from class: com.coveiot.android.sportsnotification.SportsService$sendNoMatchSelectedToWatch$1
            @Override // com.coveiot.android.sportsnotification.SportsService.SportsSettingsResultListener
            public void onSettingsResult(boolean z) {
                if (z) {
                    SportsService.this.I();
                }
            }
        });
    }
}
