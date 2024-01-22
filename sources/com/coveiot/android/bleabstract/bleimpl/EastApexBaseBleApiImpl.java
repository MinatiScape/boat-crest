package com.coveiot.android.bleabstract.bleimpl;

import android.app.ActivityManager;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.apex.bluetooth.enumeration.PersonHand;
import com.apex.bluetooth.enumeration.TimeZone;
import com.apex.bluetooth.enumeration.UnitFormat;
import com.apex.bluetooth.model.EABleBatInfo;
import com.apex.bluetooth.model.EABleDailyGoal;
import com.apex.bluetooth.model.EABleDevUnit;
import com.apex.bluetooth.model.EABleFeatures;
import com.apex.bluetooth.model.EABleGesturesBrightScreen;
import com.apex.bluetooth.model.EABleNotDisturb;
import com.apex.bluetooth.model.EABlePersonInfo;
import com.apex.bluetooth.model.EABleReminder;
import com.apex.bluetooth.model.EABleSyncTime;
import com.apex.bluetooth.model.EABleWatchFace;
import com.apex.bluetooth.model.EABleWatchInfo;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.Alarm;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DNDData;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.CallStatusType;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.bleabstract.response.DeviceSettingsInfoResponse;
import com.coveiot.android.bleabstract.response.GetCalorieDistanceGoalResponse;
import com.coveiot.android.bleabstract.response.GetFitnessInfoResponse;
import com.coveiot.android.bleabstract.response.GetLiftWristResponse;
import com.coveiot.android.bleabstract.response.GetWatchtimeInfoResponse;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.LiveAGPSUploadPercentage;
import com.coveiot.android.bleabstract.response.LiveECGDataResponse;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveSportData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.Spo2Response;
import com.coveiot.android.bleabstract.response.Spo2WaveResponse;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.response.WatchFacePositionResponse;
import com.coveiot.android.bleabstract.services.EastapexBleService;
import com.coveiot.android.eastapexsdk.EastApexResponseListener;
import com.coveiot.android.eastapexsdk.api.EastApexActivityDataReq;
import com.coveiot.android.eastapexsdk.api.EastApexBaseReq;
import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.android.eastapexsdk.api.EastApexBatteryLevelReq;
import com.coveiot.android.eastapexsdk.api.EastApexCloudWatchFaceReq;
import com.coveiot.android.eastapexsdk.api.EastApexCustomWatchFaceReq;
import com.coveiot.android.eastapexsdk.api.EastApexDefaultWatchFaceReq;
import com.coveiot.android.eastapexsdk.api.EastApexDeviceInfoReq;
import com.coveiot.android.eastapexsdk.api.EastApexDrinkReminderReq;
import com.coveiot.android.eastapexsdk.api.EastApexFemaleWellnessConfigReq;
import com.coveiot.android.eastapexsdk.api.EastApexGetAlarmReq;
import com.coveiot.android.eastapexsdk.api.EastApexGetDNDReq;
import com.coveiot.android.eastapexsdk.api.EastApexGetDialyGoalsReq;
import com.coveiot.android.eastapexsdk.api.EastApexGetDistanceUnitReq;
import com.coveiot.android.eastapexsdk.api.EastApexGetLiftWristReq;
import com.coveiot.android.eastapexsdk.api.EastApexGetUserInfoReq;
import com.coveiot.android.eastapexsdk.api.EastApexGetWatchTimeReq;
import com.coveiot.android.eastapexsdk.api.EastApexHeartRateReq;
import com.coveiot.android.eastapexsdk.api.EastApexMusicMetaDataReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetAlarmReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetCallStatusReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetDNDReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetDialyGoalsReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetDistanceUnitReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetHeartRateMinitoring;
import com.coveiot.android.eastapexsdk.api.EastApexSetHourSystemReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetLiftWristReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetMsgNotificationReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetSedentaryReminderReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetUserInfoReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetWeatherReq;
import com.coveiot.android.eastapexsdk.api.EastApexSleepReq;
import com.coveiot.android.eastapexsdk.api.EastApexSpo2Req;
import com.coveiot.android.eastapexsdk.api.EastApexStepsReq;
import com.coveiot.android.eastapexsdk.api.EastApexStressReq;
import com.coveiot.android.eastapexsdk.api.EastApexSyncContactsReq;
import com.coveiot.android.eastapexsdk.api.EastApexWatchFacePositionRequest;
import com.coveiot.android.eastapexsdk.error.EastApexError;
import com.coveiot.android.eastapexsdk.error.EastApexErrorType;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.scan.AssociationResult;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class EastApexBaseBleApiImpl implements BleApi, EastApexResponseListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2935a;
    @NotNull
    public final Handler b;
    @NotNull
    public final Handler c;
    @Nullable
    public ConnectionResultListener d;
    @NotNull
    public final Handler e;
    @Nullable
    public BleBaseRequest f;
    public final int g;
    public final int h;
    public final int i;
    @NotNull
    public final Handler j;
    @Nullable
    public MutableLiveData<ConnectionStatus> k;
    @NotNull
    public final LinkedList<QueueObject> l;
    @Nullable
    public DeviceSupportedFeatures m;
    @Nullable
    public EastapexBleService n;
    public final String o;
    @Nullable
    public ServiceConnection p;
    public final int q;
    @NotNull
    public final Runnable r;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<EastApexBaseBleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, EastApexBaseBleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f2936a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, EastApexBaseBleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public EastApexBaseBleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new EastApexBaseBleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f2936a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public static final class QueueObject {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f2937a;

        public QueueObject(@NotNull BleBaseRequest baseRequest) {
            Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
            this.f2937a = baseRequest;
        }

        @NotNull
        public final BleBaseRequest getBaseRequest() {
            return this.f2937a;
        }

        public final void setBaseRequest(@NotNull BleBaseRequest bleBaseRequest) {
            Intrinsics.checkNotNullParameter(bleBaseRequest, "<set-?>");
            this.f2937a = bleBaseRequest;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            int[] iArr = new int[CloveBleState.BleState.values().length];
            try {
                iArr[CloveBleState.BleState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CloveBleState.BleState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CloveBleState.BleState.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[EastApexErrorType.values().length];
            try {
                iArr2[EastApexErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[EastApexErrorType.COMMAND_TIME_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[EastApexErrorType.COMMAND_RESPONSE_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
            NotificationType.values();
            int[] iArr3 = new int[65];
            try {
                NotificationType notificationType = NotificationType.SMS;
                iArr3[2] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                NotificationType notificationType2 = NotificationType.WHATSAPP;
                iArr3[4] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                NotificationType notificationType3 = NotificationType.WECHAT;
                iArr3[5] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                NotificationType notificationType4 = NotificationType.FACEBOOK;
                iArr3[6] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                NotificationType notificationType5 = NotificationType.INSTAGRAM;
                iArr3[7] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                NotificationType notificationType6 = NotificationType.TWITTER;
                iArr3[8] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                NotificationType notificationType7 = NotificationType.MISSED_CALL;
                iArr3[20] = 7;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                NotificationType notificationType8 = NotificationType.LINKEDIN;
                iArr3[15] = 8;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                NotificationType notificationType9 = NotificationType.SKYPE;
                iArr3[14] = 9;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                NotificationType notificationType10 = NotificationType.TELEGRAM;
                iArr3[17] = 10;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                NotificationType notificationType11 = NotificationType.OTHER_APPS;
                iArr3[18] = 11;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                NotificationType notificationType12 = NotificationType.MESSENGER;
                iArr3[9] = 12;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                NotificationType notificationType13 = NotificationType.YOUTUBE;
                iArr3[27] = 13;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                NotificationType notificationType14 = NotificationType.YAHOO_EMAIL;
                iArr3[25] = 14;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                NotificationType notificationType15 = NotificationType.OUTLOOK;
                iArr3[24] = 15;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                NotificationType notificationType16 = NotificationType.GMAIL;
                iArr3[26] = 16;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                NotificationType notificationType17 = NotificationType.SNAPCHAT;
                iArr3[12] = 17;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                NotificationType notificationType18 = NotificationType.CALENDAR;
                iArr3[1] = 18;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                NotificationType notificationType19 = NotificationType.EMAIL;
                iArr3[3] = 19;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                NotificationType notificationType20 = NotificationType.QQ;
                iArr3[10] = 20;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                NotificationType notificationType21 = NotificationType.QZONE;
                iArr3[11] = 21;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                NotificationType notificationType22 = NotificationType.LINE;
                iArr3[13] = 22;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                NotificationType notificationType23 = NotificationType.VKCLIENT;
                iArr3[16] = 23;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                NotificationType notificationType24 = NotificationType.CUSTOM_EVENT;
                iArr3[19] = 24;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                NotificationType notificationType25 = NotificationType.FACEBOOK_LITE;
                iArr3[21] = 25;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                NotificationType notificationType26 = NotificationType.MESSENGER_LITE;
                iArr3[22] = 26;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                NotificationType notificationType27 = NotificationType.WHATSAPP_BUSINESS;
                iArr3[23] = 27;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                NotificationType notificationType28 = NotificationType.KAKAO_TALK;
                iArr3[28] = 28;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                NotificationType notificationType29 = NotificationType.NEWS;
                iArr3[29] = 29;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                NotificationType notificationType30 = NotificationType.SOCIAL;
                iArr3[30] = 30;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                NotificationType notificationType31 = NotificationType.SCHEDULE;
                iArr3[31] = 31;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                NotificationType notificationType32 = NotificationType.RETAIN;
                iArr3[32] = 32;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                NotificationType notificationType33 = NotificationType.HANGOUTS;
                iArr3[33] = 33;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                NotificationType notificationType34 = NotificationType.VIBER;
                iArr3[34] = 34;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                NotificationType notificationType35 = NotificationType.BOOKING;
                iArr3[35] = 35;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                NotificationType notificationType36 = NotificationType.AIRBNB;
                iArr3[36] = 36;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                NotificationType notificationType37 = NotificationType.FLIPBOARD;
                iArr3[37] = 37;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                NotificationType notificationType38 = NotificationType.SPOTIFY;
                iArr3[38] = 38;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                NotificationType notificationType39 = NotificationType.PANDORA;
                iArr3[39] = 39;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                NotificationType notificationType40 = NotificationType.DROPBOX;
                iArr3[40] = 40;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                NotificationType notificationType41 = NotificationType.WAZE;
                iArr3[41] = 41;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                NotificationType notificationType42 = NotificationType.LIFT;
                iArr3[42] = 42;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                NotificationType notificationType43 = NotificationType.SLACK;
                iArr3[43] = 43;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                NotificationType notificationType44 = NotificationType.SHAZAM;
                iArr3[44] = 44;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                NotificationType notificationType45 = NotificationType.DELIVEROO;
                iArr3[45] = 45;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                NotificationType notificationType46 = NotificationType.PINTREST;
                iArr3[46] = 46;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                NotificationType notificationType47 = NotificationType.TUMBLR;
                iArr3[47] = 47;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                NotificationType notificationType48 = NotificationType.AMAZON;
                iArr3[48] = 48;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                NotificationType notificationType49 = NotificationType.DISCORD;
                iArr3[49] = 49;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                NotificationType notificationType50 = NotificationType.GITHUB;
                iArr3[50] = 50;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                NotificationType notificationType51 = NotificationType.GOOGLE_MAPS;
                iArr3[51] = 51;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                NotificationType notificationType52 = NotificationType.NEWS_BREAK;
                iArr3[52] = 52;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                NotificationType notificationType53 = NotificationType.REDDIT;
                iArr3[53] = 53;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                NotificationType notificationType54 = NotificationType.TEAMS;
                iArr3[54] = 54;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                NotificationType notificationType55 = NotificationType.TIKTOK;
                iArr3[55] = 55;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                NotificationType notificationType56 = NotificationType.TWITCH;
                iArr3[56] = 56;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                NotificationType notificationType57 = NotificationType.UBER_EATS;
                iArr3[57] = 57;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                NotificationType notificationType58 = NotificationType.DOORDASH;
                iArr3[58] = 58;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                NotificationType notificationType59 = NotificationType.GRUBHUB;
                iArr3[59] = 59;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                NotificationType notificationType60 = NotificationType.INSTACART;
                iArr3[60] = 60;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                NotificationType notificationType61 = NotificationType.POSTMATES;
                iArr3[61] = 61;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                NotificationType notificationType62 = NotificationType.ZOOM;
                iArr3[62] = 62;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                NotificationType notificationType63 = NotificationType.UBER;
                iArr3[63] = 63;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                NotificationType notificationType64 = NotificationType.APPLE_EMAIL;
                iArr3[64] = 64;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                NotificationType notificationType65 = NotificationType.CALL;
                iArr3[0] = 65;
            } catch (NoSuchFieldError unused71) {
            }
            $EnumSwitchMapping$2 = iArr3;
            CallStatusType.values();
            $EnumSwitchMapping$3 = new int[]{2, 1};
        }
    }

    public EastApexBaseBleApiImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2935a = context;
        this.b = new Handler();
        this.c = new Handler();
        this.e = new Handler();
        this.g = 300000;
        this.h = 60000;
        this.i = 30000;
        this.j = new Handler(Looper.getMainLooper());
        this.l = new LinkedList<>();
        this.o = EastApexBaseBleApiImpl.class.getSimpleName();
        this.q = 1;
        registerEventBus();
        b();
        checkAndStartService();
        this.r = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.x0
            @Override // java.lang.Runnable
            public final void run() {
                EastApexBaseBleApiImpl.b(EastApexBaseBleApiImpl.this);
            }
        };
    }

    public static final void c(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void d(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void e(DataResultListener dataResultListener, BleBaseResponse mWatchTimeResponse) {
        Intrinsics.checkNotNullParameter(mWatchTimeResponse, "$mWatchTimeResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mWatchTimeResponse);
    }

    public static final void f(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void g(DataResultListener dataResultListener, BleBaseResponse mDistanceUnitResponse) {
        Intrinsics.checkNotNullParameter(mDistanceUnitResponse, "$mDistanceUnitResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mDistanceUnitResponse);
    }

    public static final void h(DataResultListener dataResultListener, BleBaseResponse mDistUnitResponse) {
        Intrinsics.checkNotNullParameter(mDistUnitResponse, "$mDistUnitResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mDistUnitResponse);
    }

    public static final void i(DataResultListener dataResultListener, BleBaseResponse mLiftWristResponse) {
        Intrinsics.checkNotNullParameter(mLiftWristResponse, "$mLiftWristResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mLiftWristResponse);
    }

    public static final void j(DataResultListener dataResultListener, BleBaseResponse mLiftWristResponse) {
        Intrinsics.checkNotNullParameter(mLiftWristResponse, "$mLiftWristResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mLiftWristResponse);
    }

    public static final void k(DataResultListener dataResultListener, BleBaseResponse mUserInfoResponse) {
        Intrinsics.checkNotNullParameter(mUserInfoResponse, "$mUserInfoResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mUserInfoResponse);
    }

    public static final void l(DataResultListener dataResultListener, BleBaseResponse mLiftWristResponse) {
        Intrinsics.checkNotNullParameter(mLiftWristResponse, "$mLiftWristResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mLiftWristResponse);
    }

    public static final void m(DataResultListener dataResultListener, BleBaseResponse spO2Response) {
        Intrinsics.checkNotNullParameter(spO2Response, "$spO2Response");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(spO2Response);
    }

    public static final void n(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void o(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void p(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public final void a(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        this.b.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(this.f2935a).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$scan_$1
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(@NotNull CharSequence error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ScanResultListener scanResultListener2 = scanResultListener;
                        String string = EastApexBaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                        scanResultListener2.onError(string);
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(@NotNull String messgae) {
                        Intrinsics.checkNotNullParameter(messgae, "messgae");
                        EastApexBaseBleApiImpl.this.scanResultReceieved(new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.b.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.l1
            @Override // java.lang.Runnable
            public final void run() {
                EastApexBaseBleApiImpl.a(EastApexBaseBleApiImpl.this, scanDeviceRequest, scanResultListener);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void addToQueue(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        synchronized (this.l) {
            EastApexBaseReq a2 = a(baseRequest);
            if (a2 != null) {
                if (a2.isPriority()) {
                    this.l.addFirst(new QueueObject(baseRequest));
                } else {
                    this.l.add(new QueueObject(baseRequest));
                }
            } else if (!(baseRequest instanceof ActivityModeSummaryRequest)) {
                sendCommandNotFoundError(baseRequest);
            }
        }
    }

    public final void b() {
        this.p = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$initServiceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(@NotNull ComponentName name, @NotNull IBinder service) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(service, "service");
                if (service instanceof EastapexBleService.BtServiceBinder) {
                    EastApexBaseBleApiImpl.this.setBleService(((EastapexBleService.BtServiceBinder) service).getService());
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(@NotNull ComponentName name) {
                Intrinsics.checkNotNullParameter(name, "name");
                EastApexBaseBleApiImpl.this.setBleService(null);
            }
        };
    }

    public final void bindBleService() {
        Intent intent = new Intent(this.f2935a, EastapexBleService.class);
        try {
            Context context = this.f2935a;
            ServiceConnection serviceConnection = this.p;
            Intrinsics.checkNotNull(serviceConnection);
            context.bindService(intent, serviceConnection, 1);
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this.f2935a);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return false;
    }

    public void checkAndStartService() {
        if (!checkIfServiceIsRunning()) {
            LogHelper.d(this.o, "checkAndStartService-> service is not running ++ ");
            startBleService();
            return;
        }
        bindBleService();
    }

    public final boolean checkIfServiceIsRunning() {
        Object systemService = this.f2935a.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (Intrinsics.areEqual(EastapexBleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f2935a.getPackageName(), runningServiceInfo.service.getPackageName())) {
                z = true;
            }
        }
        return z;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void cleanUpCommands() {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void clearCommandQueue() {
        synchronized (this.l) {
            LinkedList<QueueObject> linkedList = this.l;
            if (linkedList != null && linkedList.size() > 0) {
                this.l.clear();
            }
        }
        this.e.removeCallbacksAndMessages(null);
        this.f = null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(@NotNull final ConnectRequest request, @NotNull final ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d = listener;
        this.c.removeCallbacksAndMessages(null);
        this.c.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.i1
            @Override // java.lang.Runnable
            public final void run() {
                EastApexBaseBleApiImpl.a(EastApexBaseBleApiImpl.this, request, listener);
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listener.onConnectionResponse(ConnectionStatus.DISCONNECTED);
        LogHelper.d(this.o, "on disconnect");
        this.d = listener;
        EastapexBleService eastapexBleService = this.n;
        if (eastapexBleService != null) {
            eastapexBleService.disconnect();
            a();
            return;
        }
        listener.onError(new Error(Type.SERVICE_NOT_RUNNING, "service is not present"));
    }

    @Nullable
    public final EastapexBleService getBleService() {
        return this.n;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return BusyStatus.IDLE;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        if (this.n != null) {
            ConnectionStatus connectionStatus = getConnectionStatus();
            EastapexBleService eastapexBleService = this.n;
            Intrinsics.checkNotNull(eastapexBleService);
            ConnectionError connectionError = eastapexBleService.getConnectionError();
            EastapexBleService eastapexBleService2 = this.n;
            Intrinsics.checkNotNull(eastapexBleService2);
            return new ConnectionInfo(connectionStatus, connectionError, eastapexBleService2.getConnectionStageChangeTimeStamp());
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public ConnectionStatus getConnectionStatus() {
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        EastapexBleService eastapexBleService = this.n;
        if (eastapexBleService != null) {
            if (eastapexBleService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            EastapexBleService eastapexBleService2 = this.n;
            return (eastapexBleService2 != null ? eastapexBleService2.getConnectionState() : null) == CloveBleState.BleState.CONNECTING ? ConnectionStatus.CONNECTING : connectionStatus;
        }
        return connectionStatus;
    }

    @NotNull
    public final Context getContext() {
        return this.f2935a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        EastapexBleService eastapexBleService = this.n;
        if (eastapexBleService != null) {
            Intrinsics.checkNotNull(eastapexBleService);
            if (eastapexBleService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                request.setRequId(UUID.randomUUID().toString());
                request.setResponseListener(listener);
                addToQueue(request);
                sendCommandRequest();
                return;
            }
        }
        String string = this.f2935a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onDataError(new BleBaseError(string));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        EABleFeatures devSupfeatures;
        EABleFeatures devSupfeatures2;
        EABleFeatures devSupfeatures3;
        EABleFeatures devSupfeatures4;
        EABleFeatures devSupfeatures5;
        EABleFeatures devSupfeatures6;
        EABleFeatures devSupfeatures7;
        EABleFeatures devSupfeatures8;
        EABleFeatures devSupfeatures9;
        EABleFeatures devSupfeatures10;
        EABleFeatures devSupfeatures11;
        EABleFeatures devSupfeatures12;
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        this.m = deviceSupportedFeatures;
        deviceSupportedFeatures.setStepsSupported(true);
        DeviceSupportedFeatures deviceSupportedFeatures2 = this.m;
        if (deviceSupportedFeatures2 != null) {
            deviceSupportedFeatures2.setSleepSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures3 = this.m;
        if (deviceSupportedFeatures3 != null) {
            deviceSupportedFeatures3.setHeartRateSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures4 = this.m;
        if (deviceSupportedFeatures4 != null) {
            deviceSupportedFeatures4.setTemparatureHistorySupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures5 = this.m;
        if (deviceSupportedFeatures5 != null) {
            deviceSupportedFeatures5.setManualBpSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures6 = this.m;
        if (deviceSupportedFeatures6 != null) {
            deviceSupportedFeatures6.setManualSpo2SupportedOnBand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures7 = this.m;
        if (deviceSupportedFeatures7 != null) {
            deviceSupportedFeatures7.setManualStressMeasurementSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures8 = this.m;
        if (deviceSupportedFeatures8 != null) {
            deviceSupportedFeatures8.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures9 = this.m;
        if (deviceSupportedFeatures9 != null) {
            deviceSupportedFeatures9.setStepGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures10 = this.m;
        if (deviceSupportedFeatures10 != null) {
            deviceSupportedFeatures10.setCallNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures11 = this.m;
        if (deviceSupportedFeatures11 != null) {
            deviceSupportedFeatures11.setSmsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures12 = this.m;
        if (deviceSupportedFeatures12 != null) {
            deviceSupportedFeatures12.setMessageReadSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures13 = this.m;
        if (deviceSupportedFeatures13 != null) {
            deviceSupportedFeatures13.setSocialNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures14 = this.m;
        if (deviceSupportedFeatures14 != null) {
            deviceSupportedFeatures14.setHandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures15 = this.m;
        if (deviceSupportedFeatures15 != null) {
            deviceSupportedFeatures15.setPhoneFinderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures16 = this.m;
        if (deviceSupportedFeatures16 != null) {
            deviceSupportedFeatures16.setLiveStepsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures17 = this.m;
        if (deviceSupportedFeatures17 != null) {
            deviceSupportedFeatures17.setLiveHeartRateSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures18 = this.m;
        if (deviceSupportedFeatures18 != null) {
            deviceSupportedFeatures18.setLiveBPSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures19 = this.m;
        if (deviceSupportedFeatures19 != null) {
            deviceSupportedFeatures19.setHandPreferenceSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures20 = this.m;
        if (deviceSupportedFeatures20 != null) {
            deviceSupportedFeatures20.setTimeFormatSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures21 = this.m;
        if (deviceSupportedFeatures21 != null) {
            EastapexBleService eastapexBleService = this.n;
            deviceSupportedFeatures21.setDistanceUnitSettingsSupported((eastapexBleService == null || (devSupfeatures12 = eastapexBleService.getDevSupfeatures()) == null || devSupfeatures12.getUnit_settings() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures22 = this.m;
        if (deviceSupportedFeatures22 != null) {
            EastapexBleService eastapexBleService2 = this.n;
            deviceSupportedFeatures22.setLiftWristToViewSettingsSupported((eastapexBleService2 == null || (devSupfeatures11 = eastapexBleService2.getDevSupfeatures()) == null || devSupfeatures11.getGestures_wake_up_settings() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures23 = this.m;
        if (deviceSupportedFeatures23 != null) {
            deviceSupportedFeatures23.setTemperatureUnitSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures24 = this.m;
        if (deviceSupportedFeatures24 != null) {
            deviceSupportedFeatures24.setProbeFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures25 = this.m;
        if (deviceSupportedFeatures25 != null) {
            EastapexBleService eastapexBleService3 = this.n;
            deviceSupportedFeatures25.setAutoHrSettingsSupported((eastapexBleService3 == null || (devSupfeatures10 = eastapexBleService3.getDevSupfeatures()) == null || devSupfeatures10.getHr_monitoring() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures26 = this.m;
        if (deviceSupportedFeatures26 != null) {
            EastapexBleService eastapexBleService4 = this.n;
            deviceSupportedFeatures26.setAutoStressSettingsSupported((eastapexBleService4 == null || (devSupfeatures9 = eastapexBleService4.getDevSupfeatures()) == null || devSupfeatures9.getPressure_monitoring() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures27 = this.m;
        if (deviceSupportedFeatures27 != null) {
            deviceSupportedFeatures27.setMultipleAlarmsSupportedAtATime(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures28 = this.m;
        if (deviceSupportedFeatures28 != null) {
            deviceSupportedFeatures28.setOnceAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures29 = this.m;
        if (deviceSupportedFeatures29 != null) {
            deviceSupportedFeatures29.setSportsModeHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures30 = this.m;
        if (deviceSupportedFeatures30 != null) {
            deviceSupportedFeatures30.setSampleDataSupportedInSportMode(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures31 = this.m;
        if (deviceSupportedFeatures31 != null) {
            deviceSupportedFeatures31.setSyncBandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures32 = this.m;
        if (deviceSupportedFeatures32 != null) {
            deviceSupportedFeatures32.setSportModeSupportedFromApp(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures33 = this.m;
        if (deviceSupportedFeatures33 != null) {
            deviceSupportedFeatures33.setDeviceSettingsSupportedInOneCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures34 = this.m;
        if (deviceSupportedFeatures34 != null) {
            deviceSupportedFeatures34.setBandSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures35 = this.m;
        if (deviceSupportedFeatures35 != null) {
            EastapexBleService eastapexBleService5 = this.n;
            deviceSupportedFeatures35.setAutoTemperatureSettingsSupported((eastapexBleService5 == null || (devSupfeatures8 = eastapexBleService5.getDevSupfeatures()) == null || devSupfeatures8.getTemperature_monitoring() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures36 = this.m;
        if (deviceSupportedFeatures36 != null) {
            deviceSupportedFeatures36.setREMSupportedInSleep(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures37 = this.m;
        if (deviceSupportedFeatures37 != null) {
            deviceSupportedFeatures37.setScheduledDndSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures38 = this.m;
        if (deviceSupportedFeatures38 != null) {
            deviceSupportedFeatures38.setMusicMetaDataChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures39 = this.m;
        if (deviceSupportedFeatures39 != null) {
            deviceSupportedFeatures39.setMusicPlaybackStateChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures40 = this.m;
        if (deviceSupportedFeatures40 != null) {
            deviceSupportedFeatures40.setMusicVolumeChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures41 = this.m;
        if (deviceSupportedFeatures41 != null) {
            deviceSupportedFeatures41.setMaxAlarmSupportedOnBand(10);
        }
        DeviceSupportedFeatures deviceSupportedFeatures42 = this.m;
        if (deviceSupportedFeatures42 != null) {
            deviceSupportedFeatures42.setVibrationAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures43 = this.m;
        if (deviceSupportedFeatures43 != null) {
            EastapexBleService eastapexBleService6 = this.n;
            deviceSupportedFeatures43.setSedentaryReminderSupported((eastapexBleService6 == null || (devSupfeatures7 = eastapexBleService6.getDevSupfeatures()) == null || devSupfeatures7.getSedentary_monitoring() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures44 = this.m;
        if (deviceSupportedFeatures44 != null) {
            EastapexBleService eastapexBleService7 = this.n;
            deviceSupportedFeatures44.setRepeatDaysSupportedInSedentary((eastapexBleService7 == null || (devSupfeatures6 = eastapexBleService7.getDevSupfeatures()) == null || devSupfeatures6.getSedentary_monitoring() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures45 = this.m;
        if (deviceSupportedFeatures45 != null) {
            EastapexBleService eastapexBleService8 = this.n;
            deviceSupportedFeatures45.setDndSupported((eastapexBleService8 == null || (devSupfeatures5 = eastapexBleService8.getDevSupfeatures()) == null || devSupfeatures5.getDisturb_setting() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures46 = this.m;
        if (deviceSupportedFeatures46 != null) {
            deviceSupportedFeatures46.setShortcutMenuShowHideCommandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures47 = this.m;
        if (deviceSupportedFeatures47 != null) {
            EastapexBleService eastapexBleService9 = this.n;
            deviceSupportedFeatures47.setFemaleWellnessSupported((eastapexBleService9 == null || (devSupfeatures4 = eastapexBleService9.getDevSupfeatures()) == null || devSupfeatures4.getMenstrual_setting() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures48 = this.m;
        if (deviceSupportedFeatures48 != null) {
            EastapexBleService eastapexBleService10 = this.n;
            deviceSupportedFeatures48.setContactSyncSupported((eastapexBleService10 == null || (devSupfeatures3 = eastapexBleService10.getDevSupfeatures()) == null || devSupfeatures3.getPhone_contact() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures49 = this.m;
        if (deviceSupportedFeatures49 != null) {
            EastapexBleService eastapexBleService11 = this.n;
            deviceSupportedFeatures49.setDrinkingReminderSupported((eastapexBleService11 == null || (devSupfeatures2 = eastapexBleService11.getDevSupfeatures()) == null || devSupfeatures2.getMonitor_reminder() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures50 = this.m;
        if (deviceSupportedFeatures50 != null) {
            deviceSupportedFeatures50.setFindMyBandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures51 = this.m;
        if (deviceSupportedFeatures51 != null) {
            EastapexBleService eastapexBleService12 = this.n;
            deviceSupportedFeatures51.setGpsSupported((eastapexBleService12 == null || (devSupfeatures = eastapexBleService12.getDevSupfeatures()) == null || devSupfeatures.getGps_setting() != this.q) ? false : true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures52 = this.m;
        if (deviceSupportedFeatures52 != null) {
            deviceSupportedFeatures52.setWeatherEnableCommandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures53 = this.m;
        if (deviceSupportedFeatures53 != null) {
            deviceSupportedFeatures53.setWeatherSupportedInBand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures54 = this.m;
        if (deviceSupportedFeatures54 != null) {
            deviceSupportedFeatures54.setGoalSettingSupportedInSingleCommand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures55 = this.m;
        if (deviceSupportedFeatures55 != null) {
            deviceSupportedFeatures55.setBTCallingSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures56 = this.m;
        if (deviceSupportedFeatures56 != null) {
            deviceSupportedFeatures56.setMaxContactsInOneRequest(20);
        }
        DeviceSupportedFeatures deviceSupportedFeatures57 = this.m;
        if (deviceSupportedFeatures57 != null) {
            deviceSupportedFeatures57.setGenericActivityDataSampleSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures58 = this.m;
        if (deviceSupportedFeatures58 != null) {
            deviceSupportedFeatures58.setMusicDataSupportInSingleCommand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures59 = this.m;
        if (deviceSupportedFeatures59 != null) {
            deviceSupportedFeatures59.setSleepTargetSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures60 = this.m;
        if (deviceSupportedFeatures60 != null) {
            deviceSupportedFeatures60.setStressHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures61 = this.m;
        if (deviceSupportedFeatures61 != null) {
            deviceSupportedFeatures61.setCalorieGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures62 = this.m;
        if (deviceSupportedFeatures62 != null) {
            deviceSupportedFeatures62.setDistanceGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures63 = this.m;
        if (deviceSupportedFeatures63 != null) {
            deviceSupportedFeatures63.setExerciseMinutesGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures64 = this.m;
        if (deviceSupportedFeatures64 != null) {
            deviceSupportedFeatures64.setTitleSupportedInNotification(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures65 = this.m;
        if (deviceSupportedFeatures65 != null) {
            deviceSupportedFeatures65.setBandVolumeControlSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures66 = this.m;
        Intrinsics.checkNotNull(deviceSupportedFeatures66);
        return deviceSupportedFeatures66;
    }

    @Nullable
    public final BleBaseRequest getFromQueue(@NotNull EastApexBaseReq eastapexBaseReq) {
        Intrinsics.checkNotNullParameter(eastapexBaseReq, "eastapexBaseReq");
        int size = this.l.size();
        for (int i = 0; i < size; i++) {
            if (kotlin.text.m.equals(this.l.get(i).getBaseRequest().getRequId(), eastapexBaseReq.getReqId(), true)) {
                return this.l.get(i).getBaseRequest();
            }
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManagerAbstract.getInstance(this.f2935a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        return connectedDeviceMacAddress;
    }

    @NotNull
    public final LinkedList<QueueObject> getQueue() {
        return this.l;
    }

    @Nullable
    public final ServiceConnection getServiceConnection() {
        return this.p;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return false;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    @com.squareup.otto.Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onConnectionStateChangedHandler(@org.jetbrains.annotations.Nullable com.coveiot.sdk.ble.CloveBleState r4) {
        /*
            r3 = this;
            com.coveiot.android.bleabstract.models.ConnectionStatus r0 = com.coveiot.android.bleabstract.models.ConnectionStatus.DISCONNECTED
            if (r4 == 0) goto L21
            com.coveiot.sdk.ble.CloveBleState$BleState r4 = r4.getmState()
            if (r4 != 0) goto Lc
            r4 = -1
            goto L14
        Lc:
            int[] r1 = com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl.WhenMappings.$EnumSwitchMapping$0
            int r4 = r4.ordinal()
            r4 = r1[r4]
        L14:
            r1 = 1
            if (r4 == r1) goto L1e
            r1 = 2
            if (r4 == r1) goto L1b
            goto L21
        L1b:
            com.coveiot.android.bleabstract.models.ConnectionStatus r4 = com.coveiot.android.bleabstract.models.ConnectionStatus.CONNECTING
            goto L22
        L1e:
            com.coveiot.android.bleabstract.models.ConnectionStatus r4 = com.coveiot.android.bleabstract.models.ConnectionStatus.CONNECTED
            goto L22
        L21:
            r4 = r0
        L22:
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r3.k
            if (r1 == 0) goto L34
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r1.setValue(r4)
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r3.k
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r1.postValue(r4)
        L34:
            if (r4 != r0) goto L4f
            com.coveiot.android.bleabstract.request.BleBaseRequest r0 = r3.f
            boolean r1 = r0 instanceof com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest
            if (r1 != 0) goto L4e
            boolean r0 = r0 instanceof com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest
            if (r0 == 0) goto L41
            goto L4e
        L41:
            com.coveiot.android.eastapexsdk.error.EastApexError r0 = new com.coveiot.android.eastapexsdk.error.EastApexError
            com.coveiot.android.eastapexsdk.error.EastApexErrorType r1 = com.coveiot.android.eastapexsdk.error.EastApexErrorType.DEVICE_NOT_CONNECTED
            java.lang.String r2 = "Device disconnected"
            r0.<init>(r1, r2)
            r3.sendErrorAndClearQueue(r0)
            goto L4f
        L4e:
            return
        L4f:
            com.coveiot.android.bleabstract.listeners.ConnectionResultListener r0 = r3.d
            if (r0 == 0) goto L59
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r0.onConnectionResponse(r4)
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl.onConnectionStateChangedHandler(com.coveiot.sdk.ble.CloveBleState):void");
    }

    @Override // com.coveiot.android.eastapexsdk.EastApexResponseListener
    public void onFailure(@NotNull EastApexError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        EastApexErrorType errorType = error.getErrorType();
        int i = errorType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[errorType.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            sendErrorAndClearQueue(error);
        }
    }

    @Override // com.coveiot.android.eastapexsdk.EastApexResponseListener
    public void onResponse(@NotNull EastApexBaseRes baseRes) {
        boolean z;
        final BleBaseRequest bleBaseRequest;
        final EastApexBaseBleApiImpl eastApexBaseBleApiImpl = this;
        String str = "this as java.lang.String…ing(startIndex, endIndex)";
        String str2 = "timeString";
        Intrinsics.checkNotNullParameter(baseRes, "baseRes");
        try {
            final BleBaseRequest fromQueue = eastApexBaseBleApiImpl.getFromQueue(baseRes.getBaseReq());
            if (fromQueue != null) {
                if (fromQueue.getResponseListener() instanceof DataResultListener) {
                    final DataResultListener dataResultListener = (DataResultListener) fromQueue.getResponseListener();
                    EastApexBaseReq baseReq = baseRes.getBaseReq();
                    if (baseReq instanceof EastApexBatteryLevelReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            Object obj = baseRes.getObj();
                            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.apex.bluetooth.model.EABleBatInfo");
                            final DataResultListener dataResultListener2 = (DataResultListener) fromQueue.getResponseListener();
                            BatteryLevelResponse batteryLevelResponse = new BatteryLevelResponse();
                            batteryLevelResponse.setBatteryLevel(Integer.valueOf(((EABleBatInfo) obj).level));
                            batteryLevelResponse.setComplete(true);
                            final BleBaseResponse bleBaseResponse = new BleBaseResponse(fromQueue);
                            bleBaseResponse.setResponseData(batteryLevelResponse);
                            eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.u0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    EastApexBaseBleApiImpl.n(DataResultListener.this, bleBaseResponse);
                                }
                            });
                            eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        final DataResultListener dataResultListener3 = (DataResultListener) fromQueue.getResponseListener();
                        BatteryLevelResponse batteryLevelResponse2 = new BatteryLevelResponse();
                        batteryLevelResponse2.setComplete(true);
                        final BleBaseResponse bleBaseResponse2 = new BleBaseResponse(fromQueue);
                        bleBaseResponse2.setResponseData(batteryLevelResponse2);
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.v0
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.o(DataResultListener.this, bleBaseResponse2);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexGetAlarmReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            if (baseRes.getObj() instanceof EABleReminder) {
                                ArrayList arrayList = new ArrayList();
                                Object obj2 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.apex.bluetooth.model.EABleReminder");
                                List<EABleReminder.EABleReminderItem> list = ((EABleReminder) obj2).s_index;
                                if (list != null) {
                                    try {
                                        Iterator<EABleReminder.EABleReminderItem> it = list.iterator();
                                        while (it.hasNext()) {
                                            EABleReminder.EABleReminderItem next = it.next();
                                            if (next.getE_type() == EABleReminder.ReminderType.alarm) {
                                                byte week_cycle_bit = (byte) next.getWeek_cycle_bit();
                                                String binaryString = Integer.toBinaryString((byte) (week_cycle_bit & (-1)));
                                                if (binaryString.length() < 8) {
                                                    int length = 8 - binaryString.length();
                                                    for (int i = 0; i < length; i++) {
                                                        binaryString = '0' + binaryString;
                                                    }
                                                }
                                                Intrinsics.checkNotNullExpressionValue(binaryString, str2);
                                                String substring = binaryString.substring(7, binaryString.length());
                                                Intrinsics.checkNotNullExpressionValue(substring, str);
                                                Intrinsics.checkNotNullExpressionValue(binaryString, str2);
                                                String substring2 = binaryString.substring(6, 7);
                                                Intrinsics.checkNotNullExpressionValue(substring2, str);
                                                Intrinsics.checkNotNullExpressionValue(binaryString, str2);
                                                String substring3 = binaryString.substring(5, 6);
                                                Intrinsics.checkNotNullExpressionValue(substring3, str);
                                                Intrinsics.checkNotNullExpressionValue(binaryString, str2);
                                                Iterator<EABleReminder.EABleReminderItem> it2 = it;
                                                String substring4 = binaryString.substring(4, 5);
                                                Intrinsics.checkNotNullExpressionValue(substring4, str);
                                                Intrinsics.checkNotNullExpressionValue(binaryString, str2);
                                                DataResultListener dataResultListener4 = dataResultListener;
                                                String substring5 = binaryString.substring(3, 4);
                                                Intrinsics.checkNotNullExpressionValue(substring5, str);
                                                Intrinsics.checkNotNullExpressionValue(binaryString, str2);
                                                String substring6 = binaryString.substring(2, 3);
                                                Intrinsics.checkNotNullExpressionValue(substring6, str);
                                                Intrinsics.checkNotNullExpressionValue(binaryString, str2);
                                                String str3 = str2;
                                                String substring7 = binaryString.substring(1, 2);
                                                Intrinsics.checkNotNullExpressionValue(substring7, str);
                                                String str4 = str;
                                                BleBaseRequest bleBaseRequest2 = fromQueue;
                                                ArrayList arrayList2 = arrayList;
                                                Alarm alarm = new Alarm(next.getSw() > 0, next.id, next.hour, next.minute, next.getE_type().value, next.getContent(), week_cycle_bit == Byte.MAX_VALUE, kotlin.text.m.equals(substring, "1", true), kotlin.text.m.equals(substring2, "1", true), kotlin.text.m.equals(substring3, "1", true), kotlin.text.m.equals(substring4, "1", true), kotlin.text.m.equals(substring5, "1", true), kotlin.text.m.equals(substring6, "1", true), kotlin.text.m.equals(substring7, "1", true));
                                                alarm.setSnoozeDuration(next.getSleep_duration());
                                                arrayList2.add(alarm);
                                                it = it2;
                                                arrayList = arrayList2;
                                                dataResultListener = dataResultListener4;
                                                str2 = str3;
                                                str = str4;
                                                fromQueue = bleBaseRequest2;
                                            }
                                        }
                                    } catch (Exception e) {
                                        e = e;
                                        e.printStackTrace();
                                        return;
                                    }
                                }
                                ArrayList arrayList3 = arrayList;
                                final DataResultListener dataResultListener5 = dataResultListener;
                                bleBaseRequest = fromQueue;
                                final BleBaseResponse bleBaseResponse3 = new BleBaseResponse(bleBaseRequest);
                                bleBaseResponse3.setResponseData(arrayList3);
                                eastApexBaseBleApiImpl = this;
                                eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.w0
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        EastApexBaseBleApiImpl.p(DataResultListener.this, bleBaseResponse3);
                                    }
                                });
                            } else {
                                bleBaseRequest = fromQueue;
                            }
                        } else {
                            bleBaseRequest = fromQueue;
                            eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.z0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    EastApexBaseBleApiImpl.a(BleBaseRequest.this, dataResultListener);
                                }
                            });
                        }
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(bleBaseRequest);
                    } else if (baseReq instanceof EastApexDeviceInfoReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            DeviceInfoData deviceInfoData = new DeviceInfoData();
                            deviceInfoData.setMacAddress(getMacAddress());
                            deviceInfoData.setHwVersion("1.0");
                            deviceInfoData.setSerialNo(new Regex(":").replace(getMacAddress(), ""));
                            Object obj3 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type com.apex.bluetooth.model.EABleWatchInfo");
                            EABleWatchInfo eABleWatchInfo = (EABleWatchInfo) obj3;
                            deviceInfoData.setFwVersion(eABleWatchInfo.getFirmwareVersion());
                            deviceInfoData.setScreenFullHeight(Integer.valueOf(eABleWatchInfo.lcd_full_h));
                            deviceInfoData.setScreenFullWidth(Integer.valueOf(eABleWatchInfo.lcd_full_w));
                            deviceInfoData.setScreenPreviewWidth(Integer.valueOf(eABleWatchInfo.lcd_preview_w));
                            deviceInfoData.setScreenPreviewHeight(Integer.valueOf(eABleWatchInfo.lcd_preview_h));
                            deviceInfoData.setPreviewRadius(Integer.valueOf(eABleWatchInfo.lcd_preview_radius));
                            deviceInfoData.setScreenShape(Integer.valueOf(eABleWatchInfo.getLcd_full_type()));
                            deviceInfoData.setRGBAFormatWatchScreen(Boolean.valueOf(eABleWatchInfo.lcd_pixel_type == 1));
                            DeviceInfoResponse deviceInfoResponse = new DeviceInfoResponse();
                            deviceInfoResponse.setComplete(true);
                            deviceInfoResponse.setDeviceInfo(deviceInfoData);
                            final BleBaseResponse bleBaseResponse4 = new BleBaseResponse(fromQueue);
                            bleBaseResponse4.setResponseData(deviceInfoResponse);
                            eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.m1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    EastApexBaseBleApiImpl.a(DataResultListener.this, bleBaseResponse4);
                                }
                            });
                            eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        DeviceInfoData deviceInfoData2 = new DeviceInfoData();
                        final BleBaseResponse bleBaseResponse5 = new BleBaseResponse(fromQueue);
                        DeviceInfoResponse deviceInfoResponse2 = new DeviceInfoResponse();
                        deviceInfoResponse2.setDeviceInfo(deviceInfoData2);
                        bleBaseResponse5.setResponseData(deviceInfoResponse2);
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.n1
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.b(DataResultListener.this, bleBaseResponse5);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexGetDNDReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof EABleNotDisturb)) {
                            Object obj4 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type com.apex.bluetooth.model.EABleNotDisturb");
                            EABleNotDisturb eABleNotDisturb = (EABleNotDisturb) obj4;
                            if (eABleNotDisturb.getSw() == 0 && eABleNotDisturb.getWatch_sw() == 0) {
                                z = false;
                                final DNDData dNDData = new DNDData(z, eABleNotDisturb.getBegin_hour(), eABleNotDisturb.getBegin_minute(), eABleNotDisturb.getEnd_hour(), eABleNotDisturb.getEnd_minute());
                                eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.j1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        EastApexBaseBleApiImpl.a(BleBaseRequest.this, dNDData, dataResultListener);
                                    }
                                });
                            }
                            z = true;
                            final DNDData dNDData2 = new DNDData(z, eABleNotDisturb.getBegin_hour(), eABleNotDisturb.getBegin_minute(), eABleNotDisturb.getEnd_hour(), eABleNotDisturb.getEnd_minute());
                            eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.j1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    EastApexBaseBleApiImpl.a(BleBaseRequest.this, dNDData2, dataResultListener);
                                }
                            });
                        }
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexGetDialyGoalsReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            Object obj5 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type com.apex.bluetooth.model.EABleDailyGoal");
                            EABleDailyGoal eABleDailyGoal = (EABleDailyGoal) obj5;
                            final DataResultListener dataResultListener6 = (DataResultListener) fromQueue.getResponseListener();
                            GetCalorieDistanceGoalResponse getCalorieDistanceGoalResponse = new GetCalorieDistanceGoalResponse();
                            getCalorieDistanceGoalResponse.setCalorieGoal(eABleDailyGoal.getS_calorie().getGoal());
                            getCalorieDistanceGoalResponse.setDistanceGoal(eABleDailyGoal.getS_distance().getGoal());
                            getCalorieDistanceGoalResponse.setWalkHourGoal(eABleDailyGoal.getS_step().getGoal());
                            getCalorieDistanceGoalResponse.setExerciseTimeGoal(eABleDailyGoal.getS_duration().getGoal());
                            getCalorieDistanceGoalResponse.setSleepGoal(eABleDailyGoal.getS_sleep().getGoal());
                            final BleBaseResponse bleBaseResponse6 = new BleBaseResponse(fromQueue);
                            bleBaseResponse6.setResponseData(getCalorieDistanceGoalResponse);
                            eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.o1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    EastApexBaseBleApiImpl.c(DataResultListener.this, bleBaseResponse6);
                                }
                            });
                            eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        final DataResultListener dataResultListener7 = (DataResultListener) fromQueue.getResponseListener();
                        GetCalorieDistanceGoalResponse getCalorieDistanceGoalResponse2 = new GetCalorieDistanceGoalResponse();
                        final BleBaseResponse bleBaseResponse7 = new BleBaseResponse(fromQueue);
                        bleBaseResponse7.setResponseData(getCalorieDistanceGoalResponse2);
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.p1
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.d(DataResultListener.this, bleBaseResponse7);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexGetWatchTimeReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            Object obj6 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type com.apex.bluetooth.model.EABleSyncTime");
                            EABleSyncTime eABleSyncTime = (EABleSyncTime) obj6;
                            final DataResultListener dataResultListener8 = (DataResultListener) fromQueue.getResponseListener();
                            GetWatchtimeInfoResponse getWatchtimeInfoResponse = new GetWatchtimeInfoResponse();
                            getWatchtimeInfoResponse.setYear(eABleSyncTime.getYear());
                            getWatchtimeInfoResponse.setMonth(eABleSyncTime.getMonth());
                            getWatchtimeInfoResponse.setDay(eABleSyncTime.getDay());
                            getWatchtimeInfoResponse.setHour(eABleSyncTime.getHour());
                            getWatchtimeInfoResponse.setMinute(eABleSyncTime.getMinute());
                            getWatchtimeInfoResponse.setSecond(eABleSyncTime.getSecond());
                            getWatchtimeInfoResponse.set12hourFormat(eABleSyncTime.getE_hour_system().getValue() == 0);
                            if (eABleSyncTime.getE_time_zone().getValue() == 0) {
                                getWatchtimeInfoResponse.setTimeZone(TimeZone.zero.value);
                            } else if (eABleSyncTime.getE_time_zone().getValue() == 1) {
                                getWatchtimeInfoResponse.setTimeZone(TimeZone.east.value);
                            } else if (eABleSyncTime.getE_time_zone().getValue() == 2) {
                                getWatchtimeInfoResponse.setTimeZone(TimeZone.west.value);
                            } else {
                                getWatchtimeInfoResponse.setTimeZone(TimeZone.unknown.value);
                            }
                            getWatchtimeInfoResponse.setTimeZoneHour(eABleSyncTime.getTime_zone_hour());
                            getWatchtimeInfoResponse.setTimeZoneMinute(eABleSyncTime.getTime_zone_minute());
                            getWatchtimeInfoResponse.setNormalSyncMode(eABleSyncTime.getE_sync_mode().getValue() == 0);
                            final BleBaseResponse bleBaseResponse8 = new BleBaseResponse(fromQueue);
                            bleBaseResponse8.setResponseData(getWatchtimeInfoResponse);
                            eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.q1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    EastApexBaseBleApiImpl.e(DataResultListener.this, bleBaseResponse8);
                                }
                            });
                            eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        final DataResultListener dataResultListener9 = (DataResultListener) fromQueue.getResponseListener();
                        GetWatchtimeInfoResponse getWatchtimeInfoResponse2 = new GetWatchtimeInfoResponse();
                        final BleBaseResponse bleBaseResponse9 = new BleBaseResponse(fromQueue);
                        bleBaseResponse9.setResponseData(getWatchtimeInfoResponse2);
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.r1
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.f(DataResultListener.this, bleBaseResponse9);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexGetDistanceUnitReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            Object obj7 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type com.apex.bluetooth.model.EABleDevUnit");
                            final DataResultListener dataResultListener10 = (DataResultListener) fromQueue.getResponseListener();
                            DeviceSettingsInfoResponse deviceSettingsInfoResponse = new DeviceSettingsInfoResponse();
                            deviceSettingsInfoResponse.setDistanceUnitInMile(((EABleDevUnit) obj7).getE_format() != UnitFormat.metric);
                            final BleBaseResponse bleBaseResponse10 = new BleBaseResponse(fromQueue);
                            bleBaseResponse10.setResponseData(deviceSettingsInfoResponse);
                            eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.n0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    EastApexBaseBleApiImpl.g(DataResultListener.this, bleBaseResponse10);
                                }
                            });
                            eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        final DataResultListener dataResultListener11 = (DataResultListener) fromQueue.getResponseListener();
                        DeviceSettingsInfoResponse deviceSettingsInfoResponse2 = new DeviceSettingsInfoResponse();
                        final BleBaseResponse bleBaseResponse11 = new BleBaseResponse(fromQueue);
                        bleBaseResponse11.setResponseData(deviceSettingsInfoResponse2);
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.o0
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.h(DataResultListener.this, bleBaseResponse11);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexHeartRateReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            if (baseRes.getObj() instanceof List) {
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new EastApexBaseBleApiImpl$onResponse$14(baseRes, fromQueue, this, dataResultListener, null), 2, null);
                                return;
                            }
                            return;
                        }
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.a1
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.b(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexGetLiftWristReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            Object obj8 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj8, "null cannot be cast to non-null type com.apex.bluetooth.model.EABleGesturesBrightScreen");
                            final DataResultListener dataResultListener12 = (DataResultListener) fromQueue.getResponseListener();
                            GetLiftWristResponse getLiftWristResponse = new GetLiftWristResponse();
                            getLiftWristResponse.setLiftWristEnabled(((EABleGesturesBrightScreen) obj8).getBrightScreenSwitch() != EABleGesturesBrightScreen.BrightScreenSwitch.close);
                            final BleBaseResponse bleBaseResponse12 = new BleBaseResponse(fromQueue);
                            bleBaseResponse12.setResponseData(getLiftWristResponse);
                            eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.p0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    EastApexBaseBleApiImpl.i(DataResultListener.this, bleBaseResponse12);
                                }
                            });
                            eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        final DataResultListener dataResultListener13 = (DataResultListener) fromQueue.getResponseListener();
                        GetLiftWristResponse getLiftWristResponse2 = new GetLiftWristResponse();
                        final BleBaseResponse bleBaseResponse13 = new BleBaseResponse(fromQueue);
                        bleBaseResponse13.setResponseData(getLiftWristResponse2);
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.q0
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.j(DataResultListener.this, bleBaseResponse13);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexGetUserInfoReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            Object obj9 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj9, "null cannot be cast to non-null type com.apex.bluetooth.model.EABlePersonInfo");
                            EABlePersonInfo eABlePersonInfo = (EABlePersonInfo) obj9;
                            final DataResultListener dataResultListener14 = (DataResultListener) fromQueue.getResponseListener();
                            GetFitnessInfoResponse getFitnessInfoResponse = new GetFitnessInfoResponse();
                            getFitnessInfoResponse.setAge(eABlePersonInfo.age);
                            getFitnessInfoResponse.setWeight(eABlePersonInfo.weight);
                            getFitnessInfoResponse.setHeight(eABlePersonInfo.height);
                            getFitnessInfoResponse.setSkinColor(eABlePersonInfo.getE_skin_color().name());
                            getFitnessInfoResponse.setMale(eABlePersonInfo.getE_sex_info() == EABlePersonInfo.PersonSex.male);
                            getFitnessInfoResponse.setLeftHand(eABlePersonInfo.getE_hand_info() == PersonHand.left);
                            final BleBaseResponse bleBaseResponse14 = new BleBaseResponse(fromQueue);
                            bleBaseResponse14.setResponseData(getFitnessInfoResponse);
                            eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.r0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    EastApexBaseBleApiImpl.k(DataResultListener.this, bleBaseResponse14);
                                }
                            });
                            eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        final DataResultListener dataResultListener15 = (DataResultListener) fromQueue.getResponseListener();
                        GetFitnessInfoResponse getFitnessInfoResponse2 = new GetFitnessInfoResponse();
                        final BleBaseResponse bleBaseResponse15 = new BleBaseResponse(fromQueue);
                        bleBaseResponse15.setResponseData(getFitnessInfoResponse2);
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.s0
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.l(DataResultListener.this, bleBaseResponse15);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexStepsReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            if (baseRes.getObj() instanceof List) {
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new EastApexBaseBleApiImpl$onResponse$20(baseRes, fromQueue, this, dataResultListener, null), 2, null);
                                return;
                            }
                            return;
                        }
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.b1
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.c(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexSleepReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            if (baseRes.getObj() instanceof List) {
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new EastApexBaseBleApiImpl$onResponse$22(baseRes, fromQueue, this, dataResultListener, null), 2, null);
                                return;
                            }
                            return;
                        }
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.c1
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.d(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexStressReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            if (baseRes.getObj() instanceof List) {
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new EastApexBaseBleApiImpl$onResponse$24(baseRes, fromQueue, this, dataResultListener, null), 2, null);
                                return;
                            }
                            return;
                        }
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.d1
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.e(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexSpo2Req) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            if (baseRes.getObj() instanceof ReadManualSpo2Response) {
                                final BleBaseResponse bleBaseResponse16 = new BleBaseResponse(fromQueue);
                                bleBaseResponse16.setResponseData(baseRes.getObj());
                                eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.t0
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        EastApexBaseBleApiImpl.m(DataResultListener.this, bleBaseResponse16);
                                    }
                                });
                            }
                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new EastApexBaseBleApiImpl$onResponse$27(eastApexBaseBleApiImpl, null), 2, null);
                            eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.e1
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.f(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexActivityDataReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new EastApexBaseBleApiImpl$onResponse$29(baseRes, fromQueue, this, dataResultListener, null), 2, null);
                            return;
                        }
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.f1
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.g(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexSyncContactsReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.g1
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.h(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexCloudWatchFaceReq ? true : baseReq instanceof EastApexCustomWatchFaceReq) {
                        LogHelper.d(eastApexBaseBleApiImpl.o, "EastapexCloudWatchFaceReq  == " + baseRes.getObj());
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof LiveWatchFaceUploadPercentage)) {
                            ProgressType progressType = ProgressType.DETERMINATE;
                            Object obj10 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj10, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage");
                            ProgressData progressData = new ProgressData(progressType, ((LiveWatchFaceUploadPercentage) obj10).getPercentage(), fromQueue);
                            Intrinsics.checkNotNull(dataResultListener);
                            dataResultListener.onProgressUpdate(progressData);
                            return;
                        }
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.h1
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.i(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof EastApexWatchFacePositionRequest) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof EABleWatchFace)) {
                            Object obj11 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj11, "null cannot be cast to non-null type com.apex.bluetooth.model.EABleWatchFace");
                            final WatchFacePositionResponse watchFacePositionResponse = new WatchFacePositionResponse();
                            watchFacePositionResponse.setWatchFacePosition(Integer.valueOf(((EABleWatchFace) obj11).getId()));
                            watchFacePositionResponse.setComplete(true);
                            eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.k1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    EastApexBaseBleApiImpl.a(BleBaseRequest.this, watchFacePositionResponse, dataResultListener);
                                }
                            });
                        }
                        eastApexBaseBleApiImpl.setCompleteAndProcessNext(fromQueue);
                    }
                } else if (fromQueue.getResponseListener() instanceof SettingsResultListener) {
                    EastApexBaseReq baseReq2 = baseRes.getBaseReq();
                    if (baseReq2 instanceof EastApexSetDNDReq ? true : baseReq2 instanceof EastApexSetAlarmReq ? true : baseReq2 instanceof EastApexSetDialyGoalsReq ? true : baseReq2 instanceof EastApexSetHourSystemReq ? true : baseReq2 instanceof EastApexSetDistanceUnitReq ? true : baseReq2 instanceof EastApexSetMsgNotificationReq ? true : baseReq2 instanceof EastApexSetHeartRateMinitoring ? true : baseReq2 instanceof EastApexSetLiftWristReq ? true : baseReq2 instanceof EastApexSetUserInfoReq ? true : baseReq2 instanceof EastApexMusicMetaDataReq ? true : baseReq2 instanceof EastApexSetSedentaryReminderReq ? true : baseReq2 instanceof EastApexDrinkReminderReq ? true : baseReq2 instanceof EastApexSetWeatherReq ? true : baseReq2 instanceof EastApexFemaleWellnessConfigReq ? true : baseReq2 instanceof EastApexDefaultWatchFaceReq ? true : baseReq2 instanceof EastApexSetCallStatusReq) {
                        eastApexBaseBleApiImpl.e.removeCallbacksAndMessages(null);
                        LogHelper.d(eastApexBaseBleApiImpl.o, "onResponse  ==" + baseRes.getBaseReq());
                        LogHelper.d(eastApexBaseBleApiImpl.o, "onResponse  ==" + baseRes.getObj());
                        eastApexBaseBleApiImpl.j.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.y0
                            @Override // java.lang.Runnable
                            public final void run() {
                                EastApexBaseBleApiImpl.a(BleBaseRequest.this, eastApexBaseBleApiImpl);
                            }
                        });
                    }
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public final void processNextCommand() {
        LinkedList<QueueObject> linkedList = this.l;
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        EastApexBaseReq a2 = a(this.l.get(0).getBaseRequest());
        if (a2 != null) {
            BleBaseRequest bleBaseRequest = this.f;
            if (bleBaseRequest != null) {
                Intrinsics.checkNotNull(bleBaseRequest);
                if (!bleBaseRequest.isComplete()) {
                    return;
                }
            }
            sendRequestToBleService(a2);
            return;
        }
        sendCommandNotFoundError(this.l.get(0).getBaseRequest());
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.k == null) {
            this.k = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        Intrinsics.checkNotNull(connectionStatus);
        EastapexBleService eastapexBleService = this.n;
        if (eastapexBleService != null) {
            Intrinsics.checkNotNull(eastapexBleService);
            if (eastapexBleService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else {
                EastapexBleService eastapexBleService2 = this.n;
                Intrinsics.checkNotNull(eastapexBleService2);
                if (eastapexBleService2.getConnectionState() == CloveBleState.BleState.CONNECTING) {
                    connectionStatus = ConnectionStatus.CONNECTING;
                } else {
                    EastapexBleService eastapexBleService3 = this.n;
                    Intrinsics.checkNotNull(eastapexBleService3);
                    if (eastapexBleService3.getConnectionState() == CloveBleState.BleState.SCANNING) {
                        connectionStatus = ConnectionStatus.SCANNING;
                    } else {
                        EastapexBleService eastapexBleService4 = this.n;
                        Intrinsics.checkNotNull(eastapexBleService4);
                        if (eastapexBleService4.getConnectionState() == CloveBleState.BleState.DISCOVERING_SERVICES) {
                            connectionStatus = ConnectionStatus.DISCOVERING_SERVICES;
                        }
                    }
                }
            }
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.k;
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.postValue(connectionStatus);
        MutableLiveData<ConnectionStatus> mutableLiveData2 = this.k;
        Intrinsics.checkNotNull(mutableLiveData2);
        return mutableLiveData2;
    }

    public void registerEventBus() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new EastApexBaseBleApiImpl$registerEventBus$1(this, null), 2, null);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveECGDataResponse> registerForLiveEcgData() {
        return new MutableLiveData();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveTemperatureData> registerForLiveTemperatureData() {
        return new MutableLiveData();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<LiveAGPSUploadPercentage> registerLiveAGPSUploadData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveHealthData> registerLiveHealthData() {
        return new MutableLiveData();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<PPGData> registerLivePPGData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<Spo2Response> registerLiveSpo2Data() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<Spo2WaveResponse> registerLiveSpo2WaveData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public LiveData<LiveSportData> registerLiveSportsData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveStepsData> registerLiveStepsData() {
        return new MutableLiveData();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<LiveWatchFaceUploadPercentage> registerLiveWatchFaceUploadData() {
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x002c, code lost:
        r6.l.remove(r2).getBaseRequest();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void removeFromQueue(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.request.BleBaseRequest r7) {
        /*
            r6 = this;
            java.lang.String r0 = "bleBaseRequest"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$QueueObject> r0 = r6.l
            monitor-enter(r0)
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$QueueObject> r1 = r6.l     // Catch: java.lang.Throwable -> L3d
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L3d
            r2 = 0
        Lf:
            if (r2 >= r1) goto L3b
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$QueueObject> r3 = r6.l     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$QueueObject r3 = (com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl.QueueObject) r3     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.request.BleBaseRequest r3 = r3.getBaseRequest()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r3 = r3.getRequId()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = r7.getRequId()     // Catch: java.lang.Throwable -> L3d
            r5 = 1
            boolean r3 = kotlin.text.m.equals(r3, r4, r5)     // Catch: java.lang.Throwable -> L3d
            if (r3 == 0) goto L38
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$QueueObject> r7 = r6.l     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r7 = r7.remove(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$QueueObject r7 = (com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl.QueueObject) r7     // Catch: java.lang.Throwable -> L3d
            r7.getBaseRequest()     // Catch: java.lang.Throwable -> L3d
            goto L3b
        L38:
            int r2 = r2 + 1
            goto Lf
        L3b:
            monitor-exit(r0)
            return
        L3d:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl.removeFromQueue(com.coveiot.android.bleabstract.request.BleBaseRequest):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        a();
        EastapexBleService eastapexBleService = this.n;
        if (eastapexBleService != null) {
            eastapexBleService.restartService();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.m0
            @Override // java.lang.Runnable
            public final void run() {
                EastApexBaseBleApiImpl.a(EastApexBaseBleApiImpl.this);
            }
        }, 5000L);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(@NotNull ScanDeviceRequest request, @NotNull ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        a(request, listener);
    }

    public final void scanResultReceieved(@NotNull List<? extends BleDevice> devices, boolean z, @NotNull ScanDeviceRequest scanDeviceReq, @NotNull ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(devices, "devices");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "listener");
        ScanDeviceResponse scanDeviceResponse = new ScanDeviceResponse(scanDeviceReq);
        ArrayList arrayList = new ArrayList();
        for (BleDevice bleDevice : devices) {
            arrayList.add(new com.coveiot.android.bleabstract.models.BleDevice(bleDevice.getmDevice(), bleDevice.getRssi()));
        }
        scanDeviceResponse.setBluetoothDevices(arrayList);
        scanDeviceResponse.setScanComplete(z);
        if (!scanDeviceReq.isShouldProvideBatchResult()) {
            listener.onResponse(scanDeviceResponse);
        } else if (z) {
            listener.onResponse(scanDeviceResponse);
        }
    }

    public final void sendCommandNotFoundError(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        BaseListener responseListener = baseRequest.getResponseListener();
        if (responseListener instanceof DataResultListener) {
            String string = this.f2935a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.command_not_found)");
            ((DataResultListener) responseListener).onDataError(new BleBaseError(string));
        } else if (responseListener instanceof SettingsResultListener) {
            String string2 = this.f2935a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.command_not_found)");
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(string2));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, this.f2935a.getString(R.string.command_not_found)));
        }
    }

    public final void sendCommandRequest() {
        LinkedList<QueueObject> linkedList = this.l;
        if (linkedList == null || linkedList.size() <= 0 || this.f != null) {
            return;
        }
        processNextCommand();
    }

    public final void sendErrorAndClearQueue(@NotNull EastApexError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        synchronized (this.l) {
            LinkedList<QueueObject> linkedList = this.l;
            if (linkedList != null && linkedList.size() > 0) {
                try {
                    Object clone = this.l.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl.QueueObject>");
                    Iterator it = ((LinkedList) clone).iterator();
                    while (it.hasNext()) {
                        BaseListener responseListener = ((QueueObject) it.next()).getBaseRequest().getResponseListener();
                        String message = error.getMessage();
                        Intrinsics.checkNotNull(message);
                        BleBaseError bleBaseError = new BleBaseError(message);
                        if (error.getErrorType() == EastApexErrorType.COMMAND_TIME_OUT) {
                            bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_ERROR_TIMEOUT.value));
                        } else if (error.getErrorType() == EastApexErrorType.COMMAND_RESPONSE_ERROR) {
                            bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_WRITE_FAILED.value));
                        }
                        if (responseListener instanceof DataResultListener) {
                            ((DataResultListener) responseListener).onDataError(bleBaseError);
                        } else if (responseListener instanceof SettingsResultListener) {
                            ((SettingsResultListener) responseListener).onSettingsError(bleBaseError);
                        } else if (responseListener instanceof ConnectionResultListener) {
                            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, error.getMessage()));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clearCommandQueue();
            }
        }
    }

    public final void sendRequestToBleService(@NotNull EastApexBaseReq eastapexBaseReq) {
        Intrinsics.checkNotNullParameter(eastapexBaseReq, "eastapexBaseReq");
        this.f = this.l.get(0).getBaseRequest();
        if (eastapexBaseReq.isMultiPacket()) {
            if (eastapexBaseReq instanceof EastApexCloudWatchFaceReq) {
                this.e.postDelayed(this.r, this.g);
            } else if (eastapexBaseReq instanceof EastApexCustomWatchFaceReq) {
                this.e.postDelayed(this.r, this.g);
            } else {
                this.e.postDelayed(this.r, this.h);
            }
        } else {
            this.e.postDelayed(this.r, this.i);
        }
        EastapexBleService eastapexBleService = this.n;
        if (eastapexBleService != null) {
            eastapexBleService.sendRequest(eastapexBaseReq, this);
        }
    }

    public final void setBleService(@Nullable EastapexBleService eastapexBleService) {
        this.n = eastapexBleService;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setBusyStatus(value);
    }

    public final void setCompleteAndProcessNext(@Nullable BleBaseRequest bleBaseRequest) {
        if (bleBaseRequest != null) {
            removeFromQueue(bleBaseRequest);
            String str = this.o;
            LogHelper.d(str, "setCompleteAndProcessNext--> removed " + bleBaseRequest);
        }
        if (this.f != null) {
            this.f = null;
        }
        processNextCommand();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d = listener;
    }

    public final void setServiceConnection(@Nullable ServiceConnection serviceConnection) {
        this.p = serviceConnection;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        EastapexBleService eastapexBleService = this.n;
        if (eastapexBleService != null) {
            Intrinsics.checkNotNull(eastapexBleService);
            if (eastapexBleService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                if (b(request)) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
                String str = this.o;
                LogHelper.d(str, "setUserSettings->Ignore {" + request + '}');
                return;
            }
        }
        String string = this.f2935a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }

    public final void startBleService() {
        Intent intent = new Intent(this.f2935a, EastapexBleService.class);
        try {
            Context context = this.f2935a;
            ServiceConnection serviceConnection = this.p;
            Intrinsics.checkNotNull(serviceConnection);
            context.bindService(intent, serviceConnection, 1);
            if (Build.VERSION.SDK_INT >= 26) {
                this.f2935a.startForegroundService(intent);
            } else {
                this.f2935a.startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this.f2935a);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(this.f2935a).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManagerAbstract.getInstance(this.f2935a).saveConnectedDeviceMAcAddress("");
        PreferenceManagerAbstract.getInstance(this.f2935a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        EastapexBleService eastapexBleService = this.n;
        if (eastapexBleService != null) {
            Intrinsics.checkNotNull(eastapexBleService);
            eastapexBleService.stopService();
            a();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        LogHelper.d(this.o, "stopServiceAndRetainMacAddress");
        clearCommandQueue();
        PreferenceManagerAbstract.getInstance(this.f2935a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        EastapexBleService eastapexBleService = this.n;
        if (eastapexBleService != null) {
            eastapexBleService.stopServiceRetainMacAddress();
            a();
        }
    }

    public final void unbindService() {
        ServiceConnection serviceConnection = this.p;
        if (serviceConnection != null) {
            try {
                Context context = this.f2935a;
                Intrinsics.checkNotNull(serviceConnection);
                context.unbindService(serviceConnection);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
    }

    public static final void b(EastApexBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.o, "Command TimeOut,Failed");
        BleBaseRequest bleBaseRequest = this$0.f;
        if (bleBaseRequest != null) {
            Intrinsics.checkNotNull(bleBaseRequest);
            EastApexBaseReq a2 = this$0.a(bleBaseRequest);
            if (a2 != null) {
                this$0.onFailure(new EastApexError(EastApexErrorType.COMMAND_TIME_OUT, this$0.f2935a.getString(R.string.command_time_out)));
                String str = this$0.o;
                LogHelper.e(str, "End Time " + System.currentTimeMillis() + "-- currentCommand " + a2);
            } else {
                BleBaseRequest bleBaseRequest2 = this$0.f;
                Intrinsics.checkNotNull(bleBaseRequest2);
                this$0.sendCommandNotFoundError(bleBaseRequest2);
            }
        }
        this$0.f = null;
    }

    public static final void c(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(new StepsResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void d(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(new SleepResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void e(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        StressResponse stressResponse = new StressResponse();
        stressResponse.setComplete(true);
        bleBaseResponse.setResponseData(stressResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void f(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(new ArrayList());
        readManualSpo2Response.setComplete(true);
        bleBaseResponse.setResponseData(readManualSpo2Response);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void g(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void h(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(new BleBaseResponse(bleBaseRequest));
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void i(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(final EastApexBaseBleApiImpl this$0, final ScanDeviceRequest scanDeviceReq, final ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "$scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        try {
            if (!DeviceScanManager.getInstance(this$0.f2935a).isScanningInProgress()) {
                if (BleUtils.isEmpty(scanDeviceReq.getScanFilter())) {
                    DeviceScanManager.getInstance(this$0.f2935a).scanAllDevices(this$0.f2935a, scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$scan_$2$1
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            EastApexBaseBleApiImpl.this.scanResultReceieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = EastApexBaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …                        )");
                            scanResultListener.onError(string);
                        }
                    });
                } else {
                    DeviceScanManager.getInstance(this$0.f2935a).scanDevicesWithFilter(this$0.f2935a, scanDeviceReq.getScanFilter(), scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$scan_$2$2
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            EastApexBaseBleApiImpl.this.scanResultReceieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = EastApexBaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                            scanResultListener.onError(string);
                        }
                    });
                }
            } else {
                listener.onError("Device scan already started");
            }
        } catch (RuntimeException e) {
            String message = e.getMessage();
            Intrinsics.checkNotNull(message);
            String str = null;
            if (StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) "Enable Location permission for bluetooth scan", false, 2, (Object) null)) {
                str = e.getMessage();
            } else if (kotlin.text.m.equals(e.getMessage(), "Enable Bluetooth before starting scan", true)) {
                str = e.getMessage();
            }
            Intrinsics.checkNotNull(str);
            listener.onError(str);
            e.printStackTrace();
        }
    }

    public final boolean b(BleBaseRequest bleBaseRequest) {
        boolean z = true;
        try {
            BleBaseRequest bleBaseRequest2 = this.f;
            if (bleBaseRequest2 != null) {
                if (((bleBaseRequest2 instanceof CustomWatchFaceFileImageRequest) || (bleBaseRequest2 instanceof CustomWatchFaceBackgroundChangeRequest)) && bleBaseRequest != null && (bleBaseRequest instanceof SetMessageContentRequest) && ((SetMessageContentRequest) bleBaseRequest).appNotificationType != null && ((SetMessageContentRequest) bleBaseRequest).appNotificationType == NotificationType.CALL) {
                    z = false;
                    LogHelper.d(this.o, "Ignore incoming call triggered during watch face upgrade");
                    return false;
                }
                return true;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }

    public static final void b(DataResultListener dataResultListener, BleBaseResponse baseResponse) {
        Intrinsics.checkNotNullParameter(baseResponse, "$baseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(baseResponse);
    }

    public static final void b(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        HeartRateResponse heartRateResponse = new HeartRateResponse();
        heartRateResponse.setComplete(true);
        bleBaseResponse.setResponseData(heartRateResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(EastApexBaseBleApiImpl this$0, ConnectRequest request, ConnectionResultListener listener) {
        BluetoothDevice mBluetoothDevice;
        BluetoothDevice mBluetoothDevice2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        EastapexBleService eastapexBleService = this$0.n;
        if (eastapexBleService == null) {
            if (eastapexBleService == null) {
                this$0.startBleService();
                listener.onError(new Error(Type.SERVICE_NOT_RUNNING, this$0.f2935a.getString(R.string.service_not_running)));
                return;
            }
            listener.onError(new Error(Type.COMMAND_REQUEST_ERROR, this$0.f2935a.getString(R.string.request_should_not_null)));
            return;
        }
        CloveBleState.BleState connectionState = eastapexBleService.getConnectionState();
        CloveBleState.BleState bleState = CloveBleState.BleState.CONNECTED;
        if (connectionState == bleState && kotlin.text.m.equals(this$0.getMacAddress(), request.getMacAddress(), true)) {
            listener.onConnectionResponse(ConnectionStatus.CONNECTED);
            return;
        }
        EastapexBleService eastapexBleService2 = this$0.n;
        r2 = null;
        String str = null;
        r2 = null;
        String str2 = null;
        if ((eastapexBleService2 != null ? eastapexBleService2.getConnectionState() : null) == CloveBleState.BleState.DISCONNECTED) {
            EastapexBleService eastapexBleService3 = this$0.n;
            if (eastapexBleService3 != null) {
                eastapexBleService3.connect(request.getMacAddress());
                return;
            }
            return;
        }
        EastapexBleService eastapexBleService4 = this$0.n;
        if ((eastapexBleService4 != null ? eastapexBleService4.getConnectionState() : null) == bleState) {
            EastapexBleService eastapexBleService5 = this$0.n;
            if ((eastapexBleService5 != null ? eastapexBleService5.getMBluetoothDevice() : null) != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Connected to band ");
                EastapexBleService eastapexBleService6 = this$0.n;
                if (eastapexBleService6 != null && (mBluetoothDevice2 = eastapexBleService6.getMBluetoothDevice()) != null) {
                    str = mBluetoothDevice2.getAddress();
                }
                sb.append(str);
                listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, sb.toString()));
                return;
            }
            EastapexBleService eastapexBleService7 = this$0.n;
            CloveBleState.BleState connectionState2 = eastapexBleService7 != null ? eastapexBleService7.getConnectionState() : null;
            Intrinsics.checkNotNull(connectionState2);
            listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, connectionState2.getStateAsString()));
            return;
        }
        EastapexBleService eastapexBleService8 = this$0.n;
        if ((eastapexBleService8 != null ? eastapexBleService8.getConnectionState() : null) == CloveBleState.BleState.CONNECTING) {
            EastapexBleService eastapexBleService9 = this$0.n;
            if ((eastapexBleService9 != null ? eastapexBleService9.getMBluetoothDevice() : null) != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Connection in progress ");
                EastapexBleService eastapexBleService10 = this$0.n;
                if (eastapexBleService10 != null && (mBluetoothDevice = eastapexBleService10.getMBluetoothDevice()) != null) {
                    str2 = mBluetoothDevice.getAddress();
                }
                sb2.append(str2);
                String sb3 = sb2.toString();
                listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, sb3));
                String str3 = this$0.o;
                LogHelper.d(str3, "connect function called EABleConnectState.STATE_CONNECTING " + sb3);
                return;
            }
            EastapexBleService eastapexBleService11 = this$0.n;
            CloveBleState.BleState connectionState3 = eastapexBleService11 != null ? eastapexBleService11.getConnectionState() : null;
            Intrinsics.checkNotNull(connectionState3);
            String stateAsString = connectionState3.getStateAsString();
            listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, stateAsString));
            String str4 = this$0.o;
            LogHelper.d(str4, "connect function called EABleConnectState.STATE_CONNECTING mBluetoothDevice is null's else block " + stateAsString);
            return;
        }
        EastapexBleService eastapexBleService12 = this$0.n;
        CloveBleState.BleState connectionState4 = eastapexBleService12 != null ? eastapexBleService12.getConnectionState() : null;
        Intrinsics.checkNotNull(connectionState4);
        String stateAsString2 = connectionState4.getStateAsString();
        listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, stateAsString2));
        String str5 = this$0.o;
        LogHelper.d(str5, "connect function called EABleConnectState.STATE_CONNECTING else block " + stateAsString2);
    }

    public final void a() {
        if (DeviceScanManager.getInstance(this.f2935a).isScanningInProgress()) {
            DeviceScanManager.getInstance(this.f2935a).stopScan();
        }
        this.n = null;
        unbindService();
        clearCommandQueue();
        Handler handler = this.c;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.b;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.e;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
    }

    public static final void a(EastApexBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startBleService();
    }

    public static final void a(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(new ArrayList());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(DataResultListener dataResultListener, BleBaseResponse baseResponse) {
        Intrinsics.checkNotNullParameter(baseResponse, "$baseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(baseResponse);
    }

    public static final void a(BleBaseRequest bleBaseRequest, DNDData dndData, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(dndData, "$dndData");
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(dndData);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(BleBaseRequest bleBaseRequest, WatchFacePositionResponse mRes, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(mRes, "$mRes");
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(mRes);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(BleBaseRequest bleBaseRequest, EastApexBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsResultListener settingsResultListener = (SettingsResultListener) bleBaseRequest.getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse(bleBaseRequest));
        this$0.setCompleteAndProcessNext(bleBaseRequest);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x03f8  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0438  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0458  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0488  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x04a8  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x04d8  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x04f8  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0518  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0538  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0558  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0568  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0578  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0598  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x05a8  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x05b8  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x05c8  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x05d8  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x05e8  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x05f8  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0608  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0618  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0628  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0648  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0658  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0668  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0678  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0688  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0698  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x06a8  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x06b8  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x06c8  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x06d8  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x06e8  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x06f8  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0708  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0718  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0728  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0738  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0748  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0758  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0768  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0777  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0786  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0795  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x07a4  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x07b3  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x07c2  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x07d1  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0352  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.coveiot.android.eastapexsdk.api.EastApexBaseReq a(com.coveiot.android.bleabstract.request.BleBaseRequest r15) {
        /*
            Method dump skipped, instructions count: 4290
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl.a(com.coveiot.android.bleabstract.request.BleBaseRequest):com.coveiot.android.eastapexsdk.api.EastApexBaseReq");
    }
}
