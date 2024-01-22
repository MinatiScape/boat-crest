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
import com.coveiot.android.bleabstract.models.ActivityTypeModel;
import com.coveiot.android.bleabstract.models.Alarm;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ContactData;
import com.coveiot.android.bleabstract.models.DNDData;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.EventReminder;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.CallStatusType;
import com.coveiot.android.bleabstract.request.CameraControlRequest;
import com.coveiot.android.bleabstract.request.ChangeWatchFaceRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.FindMyWatchRequest;
import com.coveiot.android.bleabstract.request.GetActivityListRequest;
import com.coveiot.android.bleabstract.request.GetAlarmDataRequest;
import com.coveiot.android.bleabstract.request.GetDNDModeSettingsRequest;
import com.coveiot.android.bleabstract.request.GetEventReminderRequest;
import com.coveiot.android.bleabstract.request.GetLiftWristSettingsRequest;
import com.coveiot.android.bleabstract.request.GetSupportedShortcutMenuListRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.PeriodicSPO2BleRequest;
import com.coveiot.android.bleabstract.request.ReadManualSpo2Request;
import com.coveiot.android.bleabstract.request.ReminderType;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.SendCallStatusRequest;
import com.coveiot.android.bleabstract.request.SendWeatherRequest;
import com.coveiot.android.bleabstract.request.SetActivityListRequest;
import com.coveiot.android.bleabstract.request.SetCalorieTargetRequest;
import com.coveiot.android.bleabstract.request.SetDNDModeRequest;
import com.coveiot.android.bleabstract.request.SetDistanceTargetRequest;
import com.coveiot.android.bleabstract.request.SetDistanceUnitRequest;
import com.coveiot.android.bleabstract.request.SetEventReminderListRequest;
import com.coveiot.android.bleabstract.request.SetEventReminderRequest;
import com.coveiot.android.bleabstract.request.SetExerciseMinutesTargetRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.request.SetMusicMetaDataPlaybackVolumeRequest;
import com.coveiot.android.bleabstract.request.SetQuickReplyRequest;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.request.SetShortcutMenuListRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmListRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.bleabstract.request.SetWalkHourTargetRequest;
import com.coveiot.android.bleabstract.request.SetWeatherConfigInfoRequest;
import com.coveiot.android.bleabstract.request.SetWomenWellnessSettingsRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.Spo2TimeIntervalRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.bleabstract.request.StressDataRequest;
import com.coveiot.android.bleabstract.request.StressTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.SyncContactsRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.bleabstract.response.GetActivityListResponse;
import com.coveiot.android.bleabstract.response.GetLiftWristResponse;
import com.coveiot.android.bleabstract.response.GetSupportedShortcutMenuListResponse;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.LiveAGPSUploadPercentage;
import com.coveiot.android.bleabstract.response.LiveECGDataResponse;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveSportData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.bleabstract.response.PeriodicSpo2Response;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.Spo2Response;
import com.coveiot.android.bleabstract.response.Spo2WaveResponse;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.services.TouchELXService;
import com.coveiot.android.bleabstract.utils.touchUtils.TouchDeviceManager;
import com.coveiot.android.bleabstract.utils.touchUtils.TouchELXUtils;
import com.coveiot.android.idoSdk.IDOConstants;
import com.coveiot.android.touchsdk.TouchELXResponseListener;
import com.coveiot.android.touchsdk.api.TouchActivityDataReq;
import com.coveiot.android.touchsdk.api.TouchAlarmReq;
import com.coveiot.android.touchsdk.api.TouchBatteryLevelReq;
import com.coveiot.android.touchsdk.api.TouchCameraControlReq;
import com.coveiot.android.touchsdk.api.TouchCloudWatchFaceReq;
import com.coveiot.android.touchsdk.api.TouchDIYWatchFaceReq;
import com.coveiot.android.touchsdk.api.TouchDefaultWatchFaceReq;
import com.coveiot.android.touchsdk.api.TouchDeviceInfoReq;
import com.coveiot.android.touchsdk.api.TouchDrinkReminderReq;
import com.coveiot.android.touchsdk.api.TouchELXBaseReq;
import com.coveiot.android.touchsdk.api.TouchELXBaseRes;
import com.coveiot.android.touchsdk.api.TouchEventReminderReq;
import com.coveiot.android.touchsdk.api.TouchFemaleWellnessConfigReq;
import com.coveiot.android.touchsdk.api.TouchFindDeviceReq;
import com.coveiot.android.touchsdk.api.TouchGetAlarmReq;
import com.coveiot.android.touchsdk.api.TouchGetDNDReq;
import com.coveiot.android.touchsdk.api.TouchGetEventReminderReq;
import com.coveiot.android.touchsdk.api.TouchGetLiftWristReq;
import com.coveiot.android.touchsdk.api.TouchGetQuickCardReq;
import com.coveiot.android.touchsdk.api.TouchGetWorkoutModesReq;
import com.coveiot.android.touchsdk.api.TouchHeartRateIntervalReq;
import com.coveiot.android.touchsdk.api.TouchHeartRateReq;
import com.coveiot.android.touchsdk.api.TouchLiftWristReq;
import com.coveiot.android.touchsdk.api.TouchManualSpo2Req;
import com.coveiot.android.touchsdk.api.TouchMusicMetaDataReq;
import com.coveiot.android.touchsdk.api.TouchQuickReplyReq;
import com.coveiot.android.touchsdk.api.TouchSPO2IntervalReq;
import com.coveiot.android.touchsdk.api.TouchSedentaryReminderReq;
import com.coveiot.android.touchsdk.api.TouchSetCallNotificationReq;
import com.coveiot.android.touchsdk.api.TouchSetCallStatusReq;
import com.coveiot.android.touchsdk.api.TouchSetDNDReq;
import com.coveiot.android.touchsdk.api.TouchSetGoalReq;
import com.coveiot.android.touchsdk.api.TouchSetMsgNotificationReq;
import com.coveiot.android.touchsdk.api.TouchSetQuickCardsReq;
import com.coveiot.android.touchsdk.api.TouchSetUnitReq;
import com.coveiot.android.touchsdk.api.TouchSetWeatherConfigReq;
import com.coveiot.android.touchsdk.api.TouchSetWeatherUnitReq;
import com.coveiot.android.touchsdk.api.TouchSetWorkoutModesReq;
import com.coveiot.android.touchsdk.api.TouchSleepReq;
import com.coveiot.android.touchsdk.api.TouchSpo2Req;
import com.coveiot.android.touchsdk.api.TouchStepsReq;
import com.coveiot.android.touchsdk.api.TouchStressIntervalReq;
import com.coveiot.android.touchsdk.api.TouchStressReq;
import com.coveiot.android.touchsdk.api.TouchSyncContactsReq;
import com.coveiot.android.touchsdk.api.TouchUserInfoReq;
import com.coveiot.android.touchsdk.error.TouchELXError;
import com.coveiot.android.touchsdk.error.TouchELXErrorType;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.BleQuickReplyModel;
import com.coveiot.sdk.ble.api.model.SendWeatherModel;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.scan.AssociationResult;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.touchgui.sdk.TGDial;
import com.touchgui.sdk.TGPhotoDialBuilder;
import com.touchgui.sdk.bean.TGAlarm;
import com.touchgui.sdk.bean.TGContacts;
import com.touchgui.sdk.bean.TGEventReminder;
import com.touchgui.sdk.bean.TGHeartRateMonitoringModeConfig;
import com.touchgui.sdk.bean.TGMessage;
import com.touchgui.sdk.bean.TGMusicInfo;
import com.touchgui.sdk.bean.TGNotDisturbConfig;
import com.touchgui.sdk.bean.TGPhysiologicalCycle;
import com.touchgui.sdk.bean.TGProfile;
import com.touchgui.sdk.bean.TGQuickCard;
import com.touchgui.sdk.bean.TGQuickReply;
import com.touchgui.sdk.bean.TGRaiseWristConfig;
import com.touchgui.sdk.bean.TGRemindDrinking;
import com.touchgui.sdk.bean.TGSedentaryConfig;
import com.touchgui.sdk.bean.TGSpo2Config;
import com.touchgui.sdk.bean.TGStressConfig;
import com.touchgui.sdk.bean.TGUnitConfig;
import com.touchgui.sdk.bean.TGWeather;
import com.touchgui.sdk.bean.TGWorkoutMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import kotlin.collections.CollectionsKt___CollectionsKt;
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
public class TouchELXBaseBleImpl implements BleApi, TouchELXResponseListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3268a;
    @NotNull
    public final String b;
    @Nullable
    public MutableLiveData<ConnectionStatus> c;
    @Nullable
    public ConnectionResultListener d;
    @NotNull
    public final Handler e;
    @NotNull
    public final Handler f;
    @Nullable
    public TouchELXService g;
    @NotNull
    public final Handler h;
    @Nullable
    public BleBaseRequest i;
    public final int j;
    public final int k;
    public final int l;
    @Nullable
    public TouchDeviceManager m;
    @Nullable
    public ServiceConnection n;
    @NotNull
    public final LinkedList<QueueObject> o;
    @NotNull
    public final Handler p;
    @Nullable
    public DeviceSupportedFeatures q;
    @NotNull
    public final Runnable r;
    @NotNull
    public final int[] s;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<TouchELXBaseBleImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, TouchELXBaseBleImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3269a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, TouchELXBaseBleImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public TouchELXBaseBleImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new TouchELXBaseBleImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3269a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public static final class QueueObject {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3270a;

        public QueueObject(@NotNull BleBaseRequest baseRequest) {
            Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
            this.f3270a = baseRequest;
        }

        @NotNull
        public final BleBaseRequest getBaseRequest() {
            return this.f3270a;
        }

        public final void setBaseRequest(@NotNull BleBaseRequest bleBaseRequest) {
            Intrinsics.checkNotNullParameter(bleBaseRequest, "<set-?>");
            this.f3270a = bleBaseRequest;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

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
            NotificationType.values();
            int[] iArr2 = new int[65];
            try {
                NotificationType notificationType = NotificationType.SMS;
                iArr2[2] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                NotificationType notificationType2 = NotificationType.WHATSAPP;
                iArr2[4] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                NotificationType notificationType3 = NotificationType.WECHAT;
                iArr2[5] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                NotificationType notificationType4 = NotificationType.FACEBOOK;
                iArr2[6] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                NotificationType notificationType5 = NotificationType.INSTAGRAM;
                iArr2[7] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                NotificationType notificationType6 = NotificationType.TWITTER;
                iArr2[8] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                NotificationType notificationType7 = NotificationType.MISSED_CALL;
                iArr2[20] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                NotificationType notificationType8 = NotificationType.LINKEDIN;
                iArr2[15] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                NotificationType notificationType9 = NotificationType.SKYPE;
                iArr2[14] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                NotificationType notificationType10 = NotificationType.TELEGRAM;
                iArr2[17] = 10;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                NotificationType notificationType11 = NotificationType.OTHER_APPS;
                iArr2[18] = 11;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                NotificationType notificationType12 = NotificationType.MESSENGER;
                iArr2[9] = 12;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                NotificationType notificationType13 = NotificationType.YOUTUBE;
                iArr2[27] = 13;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                NotificationType notificationType14 = NotificationType.YAHOO_EMAIL;
                iArr2[25] = 14;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                NotificationType notificationType15 = NotificationType.OUTLOOK;
                iArr2[24] = 15;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                NotificationType notificationType16 = NotificationType.GMAIL;
                iArr2[26] = 16;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                NotificationType notificationType17 = NotificationType.SNAPCHAT;
                iArr2[12] = 17;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                NotificationType notificationType18 = NotificationType.CALENDAR;
                iArr2[1] = 18;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                NotificationType notificationType19 = NotificationType.EMAIL;
                iArr2[3] = 19;
            } catch (NoSuchFieldError unused22) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public TouchELXBaseBleImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f3268a = context;
        String simpleName = TouchELXBaseBleImpl.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "TouchELXBaseBleImpl::class.java.simpleName");
        this.b = simpleName;
        this.e = new Handler();
        this.f = new Handler();
        this.h = new Handler();
        this.j = 180000;
        this.k = IDOConstants.MULTI_PACKET_CMD_MS_TIMER;
        this.l = 30000;
        this.o = new LinkedList<>();
        this.p = new Handler(Looper.getMainLooper());
        registerEventBus();
        b();
        checkAndStartService();
        this.m = TouchDeviceManager.Companion.get(context);
        this.r = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.p9
            @Override // java.lang.Runnable
            public final void run() {
                TouchELXBaseBleImpl.b(TouchELXBaseBleImpl.this);
            }
        };
        this.s = new int[]{128, 2, 4, 8, 16, 32, 64};
    }

    public static final void c(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void d(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void e(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void f(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void g(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void h(DataResultListener dataResultListener, BleBaseResponse baseResponse) {
        Intrinsics.checkNotNullParameter(baseResponse, "$baseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(baseResponse);
    }

    public static final void i(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        StepsResponse stepsResponse = new StepsResponse();
        stepsResponse.setComplete(true);
        bleBaseResponse.setResponseData(stepsResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void j(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void k(DataResultListener dataResultListener, BleBaseResponse supportedSportResponse) {
        Intrinsics.checkNotNullParameter(supportedSportResponse, "$supportedSportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(supportedSportResponse);
    }

    public static final void l(DataResultListener dataResultListener, BleBaseResponse supportedSportResponse) {
        Intrinsics.checkNotNullParameter(supportedSportResponse, "$supportedSportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(supportedSportResponse);
    }

    public static final void m(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void n(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public final void a(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        this.e.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(this.f3268a).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$scan_$1
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(@NotNull CharSequence error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ScanResultListener scanResultListener2 = scanResultListener;
                        String string = TouchELXBaseBleImpl.this.getContext().getString(R.string.scan_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                        scanResultListener2.onError(string);
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(@NotNull String messgae) {
                        Intrinsics.checkNotNullParameter(messgae, "messgae");
                        TouchELXBaseBleImpl.this.scanResultReceieved(new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.e.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.ba
            @Override // java.lang.Runnable
            public final void run() {
                TouchELXBaseBleImpl.a(TouchELXBaseBleImpl.this, scanDeviceRequest, scanResultListener);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void addToQueue(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        synchronized (this.o) {
            TouchELXBaseReq a2 = a(baseRequest);
            if (a2 != null) {
                if (a2.isPriority()) {
                    this.o.addFirst(new QueueObject(baseRequest));
                } else {
                    this.o.add(new QueueObject(baseRequest));
                }
            } else if (!(baseRequest instanceof ActivityModeWithSamplesRequest)) {
                sendCommandNotFoundError(baseRequest);
            }
        }
    }

    public final void b() {
        this.n = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$initServiceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(@NotNull ComponentName name, @NotNull IBinder service) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(service, "service");
                if (service instanceof TouchELXService.BtServiceBinder) {
                    TouchELXBaseBleImpl.this.setBleService(((TouchELXService.BtServiceBinder) service).getService());
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(@NotNull ComponentName name) {
                Intrinsics.checkNotNullParameter(name, "name");
                TouchELXBaseBleImpl.this.setBleService(null);
            }
        };
    }

    public final void bindBleService() {
        Intent intent = new Intent(this.f3268a, TouchELXService.class);
        try {
            Context context = this.f3268a;
            ServiceConnection serviceConnection = this.n;
            Intrinsics.checkNotNull(serviceConnection);
            context.bindService(intent, serviceConnection, 1);
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this.f3268a);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return false;
    }

    public void checkAndStartService() {
        if (!checkIfServiceIsRunning()) {
            LogHelper.d(this.b, "checkAndStartService-> service is not running ++ ");
            startBleService();
            return;
        }
        bindBleService();
    }

    public final boolean checkIfServiceIsRunning() {
        Object systemService = this.f3268a.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (Intrinsics.areEqual(TouchELXService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3268a.getPackageName(), runningServiceInfo.service.getPackageName())) {
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
        synchronized (this.o) {
            LinkedList<QueueObject> linkedList = this.o;
            if (linkedList != null && linkedList.size() > 0) {
                this.o.clear();
            }
        }
        this.h.removeCallbacksAndMessages(null);
        this.i = null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(@NotNull final ConnectRequest request, @NotNull final ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d = listener;
        this.f.removeCallbacksAndMessages(null);
        this.f.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.aa
            @Override // java.lang.Runnable
            public final void run() {
                TouchELXBaseBleImpl.a(TouchELXBaseBleImpl.this, request, listener);
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listener.onConnectionResponse(ConnectionStatus.DISCONNECTED);
        LogHelper.d(this.b, "disconnect called");
        this.d = listener;
        if (getBleService() != null) {
            TouchELXService bleService = getBleService();
            if (bleService != null) {
                bleService.disconnectAndForget();
            }
            a();
            return;
        }
        listener.onError(new Error(Type.SERVICE_NOT_RUNNING, "service is not present"));
    }

    @Nullable
    public TouchELXService getBleService() {
        return this.g;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return BusyStatus.IDLE;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        if (getBleService() != null) {
            ConnectionStatus connectionStatus = getConnectionStatus();
            TouchELXService bleService = getBleService();
            Intrinsics.checkNotNull(bleService);
            ConnectionError connectionError = bleService.getConnectionError();
            TouchELXService bleService2 = getBleService();
            Intrinsics.checkNotNull(bleService2);
            return new ConnectionInfo(connectionStatus, connectionError, bleService2.getConnectionStageChangeTimeStamp());
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public ConnectionStatus getConnectionStatus() {
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        if (getBleService() != null) {
            TouchELXService bleService = getBleService();
            if ((bleService != null ? bleService.getConnectionState() : null) == CloveBleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            TouchELXService bleService2 = getBleService();
            return (bleService2 != null ? bleService2.getConnectionState() : null) == CloveBleState.BleState.CONNECTING ? ConnectionStatus.CONNECTING : connectionStatus;
        }
        return connectionStatus;
    }

    @NotNull
    public final Context getContext() {
        return this.f3268a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (getBleService() != null) {
            TouchELXService bleService = getBleService();
            Intrinsics.checkNotNull(bleService);
            if (bleService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                TouchDeviceManager touchDeviceManager = this.m;
                Boolean valueOf = touchDeviceManager != null ? Boolean.valueOf(touchDeviceManager.isConnected()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue()) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
            }
        }
        String string = this.f3268a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onDataError(new BleBaseError(string));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        this.q = deviceSupportedFeatures;
        deviceSupportedFeatures.setMaxDaysOfStepsDataOnBand(7);
        DeviceSupportedFeatures deviceSupportedFeatures2 = this.q;
        if (deviceSupportedFeatures2 != null) {
            deviceSupportedFeatures2.setMaxDaysOfHeartRateDataOnBand(7);
        }
        DeviceSupportedFeatures deviceSupportedFeatures3 = this.q;
        if (deviceSupportedFeatures3 != null) {
            deviceSupportedFeatures3.setMaxDaysOfSleepDataOnBand(7);
        }
        DeviceSupportedFeatures deviceSupportedFeatures4 = this.q;
        if (deviceSupportedFeatures4 != null) {
            deviceSupportedFeatures4.setStepsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures5 = this.q;
        if (deviceSupportedFeatures5 != null) {
            deviceSupportedFeatures5.setSleepSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures6 = this.q;
        if (deviceSupportedFeatures6 != null) {
            deviceSupportedFeatures6.setHeartRateSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures7 = this.q;
        if (deviceSupportedFeatures7 != null) {
            deviceSupportedFeatures7.setStressHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures8 = this.q;
        if (deviceSupportedFeatures8 != null) {
            deviceSupportedFeatures8.setPeriodicSpO2Supported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures9 = this.q;
        if (deviceSupportedFeatures9 != null) {
            deviceSupportedFeatures9.setLiveStepsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures10 = this.q;
        if (deviceSupportedFeatures10 != null) {
            deviceSupportedFeatures10.setLiveHeartRateSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures11 = this.q;
        if (deviceSupportedFeatures11 != null) {
            deviceSupportedFeatures11.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures12 = this.q;
        if (deviceSupportedFeatures12 != null) {
            deviceSupportedFeatures12.setStepGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures13 = this.q;
        if (deviceSupportedFeatures13 != null) {
            deviceSupportedFeatures13.setCallNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures14 = this.q;
        if (deviceSupportedFeatures14 != null) {
            deviceSupportedFeatures14.setSmsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures15 = this.q;
        if (deviceSupportedFeatures15 != null) {
            deviceSupportedFeatures15.setMessageReadSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures16 = this.q;
        if (deviceSupportedFeatures16 != null) {
            deviceSupportedFeatures16.setSocialNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures17 = this.q;
        if (deviceSupportedFeatures17 != null) {
            deviceSupportedFeatures17.setLiftWristToViewSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures18 = this.q;
        if (deviceSupportedFeatures18 != null) {
            deviceSupportedFeatures18.setAutoHrSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures19 = this.q;
        if (deviceSupportedFeatures19 != null) {
            deviceSupportedFeatures19.setMultipleAlarmsSupportedAtATime(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures20 = this.q;
        if (deviceSupportedFeatures20 != null) {
            deviceSupportedFeatures20.setREMSupportedInSleep(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures21 = this.q;
        if (deviceSupportedFeatures21 != null) {
            deviceSupportedFeatures21.setSedentaryReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures22 = this.q;
        if (deviceSupportedFeatures22 != null) {
            deviceSupportedFeatures22.setVibrationAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures23 = this.q;
        if (deviceSupportedFeatures23 != null) {
            deviceSupportedFeatures23.setCalorieGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures24 = this.q;
        if (deviceSupportedFeatures24 != null) {
            deviceSupportedFeatures24.setExerciseMinutesGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures25 = this.q;
        if (deviceSupportedFeatures25 != null) {
            deviceSupportedFeatures25.setWalkingHourGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures26 = this.q;
        if (deviceSupportedFeatures26 != null) {
            deviceSupportedFeatures26.setDistanceGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures27 = this.q;
        if (deviceSupportedFeatures27 != null) {
            deviceSupportedFeatures27.setSyncBandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures28 = this.q;
        if (deviceSupportedFeatures28 != null) {
            deviceSupportedFeatures28.setMaxAlarmSupportedOnBand(10);
        }
        DeviceSupportedFeatures deviceSupportedFeatures29 = this.q;
        if (deviceSupportedFeatures29 != null) {
            deviceSupportedFeatures29.setMusicMetaDataChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures30 = this.q;
        if (deviceSupportedFeatures30 != null) {
            deviceSupportedFeatures30.setMusicPlaybackStateChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures31 = this.q;
        if (deviceSupportedFeatures31 != null) {
            deviceSupportedFeatures31.setSleepScoreSupportsFromBand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures32 = this.q;
        if (deviceSupportedFeatures32 != null) {
            deviceSupportedFeatures32.setActiveTimeSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures33 = this.q;
        if (deviceSupportedFeatures33 != null) {
            deviceSupportedFeatures33.setDrinkingReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures34 = this.q;
        if (deviceSupportedFeatures34 != null) {
            deviceSupportedFeatures34.setBatteryLevelRequestSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures35 = this.q;
        if (deviceSupportedFeatures35 != null) {
            deviceSupportedFeatures35.setBandSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures36 = this.q;
        if (deviceSupportedFeatures36 != null) {
            deviceSupportedFeatures36.setDistanceUnitSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures37 = this.q;
        if (deviceSupportedFeatures37 != null) {
            deviceSupportedFeatures37.setTimeFormatSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures38 = this.q;
        if (deviceSupportedFeatures38 != null) {
            deviceSupportedFeatures38.setScheduledDndSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures39 = this.q;
        if (deviceSupportedFeatures39 != null) {
            deviceSupportedFeatures39.setDndSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures40 = this.q;
        if (deviceSupportedFeatures40 != null) {
            deviceSupportedFeatures40.setRepeatDaysSupportedInSedentary(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures41 = this.q;
        if (deviceSupportedFeatures41 != null) {
            deviceSupportedFeatures41.setBandVolumeControlSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures42 = this.q;
        if (deviceSupportedFeatures42 != null) {
            deviceSupportedFeatures42.setCameraFeatureSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures43 = this.q;
        if (deviceSupportedFeatures43 != null) {
            deviceSupportedFeatures43.setMaxContactsInOneRequest(10);
        }
        DeviceSupportedFeatures deviceSupportedFeatures44 = this.q;
        if (deviceSupportedFeatures44 != null) {
            deviceSupportedFeatures44.setContactSyncSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures45 = this.q;
        if (deviceSupportedFeatures45 != null) {
            deviceSupportedFeatures45.setSportsModeHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures46 = this.q;
        if (deviceSupportedFeatures46 != null) {
            deviceSupportedFeatures46.setSampleDataSupportedInSportMode(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures47 = this.q;
        if (deviceSupportedFeatures47 != null) {
            deviceSupportedFeatures47.setSportModeSupportedFromApp(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures48 = this.q;
        if (deviceSupportedFeatures48 != null) {
            deviceSupportedFeatures48.setWeatherEnableCommandSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures49 = this.q;
        if (deviceSupportedFeatures49 != null) {
            deviceSupportedFeatures49.setWeatherSupportedInBand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures50 = this.q;
        if (deviceSupportedFeatures50 != null) {
            deviceSupportedFeatures50.setActivityAutoRecognitionSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures51 = this.q;
        if (deviceSupportedFeatures51 != null) {
            deviceSupportedFeatures51.setCameraEnableSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures52 = this.q;
        if (deviceSupportedFeatures52 != null) {
            TouchDeviceManager touchDeviceManager = this.m;
            Boolean valueOf = touchDeviceManager != null ? Boolean.valueOf(touchDeviceManager.isAutoStressSupported()) : null;
            Intrinsics.checkNotNull(valueOf);
            deviceSupportedFeatures52.setAutoStressSettingsSupported(valueOf.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures53 = this.q;
        if (deviceSupportedFeatures53 != null) {
            TouchDeviceManager touchDeviceManager2 = this.m;
            Boolean valueOf2 = touchDeviceManager2 != null ? Boolean.valueOf(touchDeviceManager2.isAutoSPO2Supported()) : null;
            Intrinsics.checkNotNull(valueOf2);
            deviceSupportedFeatures53.setAutoSPO2SettingsSupported(valueOf2.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures54 = this.q;
        if (deviceSupportedFeatures54 != null) {
            TouchDeviceManager touchDeviceManager3 = this.m;
            Boolean valueOf3 = touchDeviceManager3 != null ? Boolean.valueOf(touchDeviceManager3.isQuickReplySupported()) : null;
            Intrinsics.checkNotNull(valueOf3);
            deviceSupportedFeatures54.setQuickReplySupported(valueOf3.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures55 = this.q;
        if (deviceSupportedFeatures55 != null) {
            TouchDeviceManager touchDeviceManager4 = this.m;
            Boolean valueOf4 = touchDeviceManager4 != null ? Boolean.valueOf(touchDeviceManager4.isFindDeviceSupported()) : null;
            Intrinsics.checkNotNull(valueOf4);
            deviceSupportedFeatures55.setFindMyBandSupported(valueOf4.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures56 = this.q;
        if (deviceSupportedFeatures56 != null) {
            TouchDeviceManager touchDeviceManager5 = this.m;
            Boolean valueOf5 = touchDeviceManager5 != null ? Boolean.valueOf(touchDeviceManager5.isSportModeSupported()) : null;
            Intrinsics.checkNotNull(valueOf5);
            deviceSupportedFeatures56.setActivityShowHideCommandSupported(valueOf5.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures57 = this.q;
        if (deviceSupportedFeatures57 != null) {
            TouchDeviceManager touchDeviceManager6 = this.m;
            Boolean valueOf6 = touchDeviceManager6 != null ? Boolean.valueOf(touchDeviceManager6.isQuickCardsSupported()) : null;
            Intrinsics.checkNotNull(valueOf6);
            deviceSupportedFeatures57.setShortcutMenuShowHideCommandSupported(valueOf6.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures58 = this.q;
        if (deviceSupportedFeatures58 != null) {
            TouchDeviceManager touchDeviceManager7 = this.m;
            Boolean valueOf7 = touchDeviceManager7 != null ? Boolean.valueOf(touchDeviceManager7.isFemaleWellnessSupported()) : null;
            Intrinsics.checkNotNull(valueOf7);
            deviceSupportedFeatures58.setFemaleWellnessSupported(valueOf7.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures59 = this.q;
        if (deviceSupportedFeatures59 != null) {
            TouchDeviceManager touchDeviceManager8 = this.m;
            Boolean valueOf8 = touchDeviceManager8 != null ? Boolean.valueOf(touchDeviceManager8.isBTCallingSupported()) : null;
            Intrinsics.checkNotNull(valueOf8);
            deviceSupportedFeatures59.setBTCallingSupported(valueOf8.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures60 = this.q;
        if (deviceSupportedFeatures60 != null) {
            TouchDeviceManager touchDeviceManager9 = this.m;
            Boolean valueOf9 = touchDeviceManager9 != null ? Boolean.valueOf(touchDeviceManager9.isEventReminderSupported()) : null;
            Intrinsics.checkNotNull(valueOf9);
            deviceSupportedFeatures60.setGenericEventReminderSupported(valueOf9.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures61 = this.q;
        if (deviceSupportedFeatures61 != null) {
            deviceSupportedFeatures61.setMusicDataSupportInSingleCommand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures62 = this.q;
        if (deviceSupportedFeatures62 != null) {
            deviceSupportedFeatures62.setTitleSupportedInNotification(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures63 = this.q;
        if (deviceSupportedFeatures63 != null) {
            deviceSupportedFeatures63.setGenericActivityDataSampleSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures64 = this.q;
        Intrinsics.checkNotNull(deviceSupportedFeatures64);
        return deviceSupportedFeatures64;
    }

    @Nullable
    public final BleBaseRequest getFromQueue(@NotNull TouchELXBaseReq touchELXBaseReq) {
        Intrinsics.checkNotNullParameter(touchELXBaseReq, "touchELXBaseReq");
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            if (kotlin.text.m.equals(this.o.get(i).getBaseRequest().getRequId(), touchELXBaseReq.getReqId(), true)) {
                return this.o.get(i).getBaseRequest();
            }
        }
        return null;
    }

    @Nullable
    public final TouchDeviceManager getMTouchDeviceManager() {
        return this.m;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManagerAbstract.getInstance(this.f3268a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        return connectedDeviceMacAddress;
    }

    @NotNull
    public final LinkedList<QueueObject> getQueue() {
        return this.o;
    }

    @Nullable
    public final ServiceConnection getServiceConnection() {
        return this.n;
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
            int[] r1 = com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl.WhenMappings.$EnumSwitchMapping$0
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
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r3.c
            if (r1 == 0) goto L34
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r1.setValue(r4)
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r3.c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r1.postValue(r4)
        L34:
            if (r4 != r0) goto L4f
            com.coveiot.android.bleabstract.request.BleBaseRequest r0 = r3.i
            boolean r1 = r0 instanceof com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest
            if (r1 != 0) goto L4e
            boolean r0 = r0 instanceof com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest
            if (r0 == 0) goto L41
            goto L4e
        L41:
            com.coveiot.android.touchsdk.error.TouchELXError r0 = new com.coveiot.android.touchsdk.error.TouchELXError
            com.coveiot.android.touchsdk.error.TouchELXErrorType r1 = com.coveiot.android.touchsdk.error.TouchELXErrorType.DEVICE_NOT_CONNECTED
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
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl.onConnectionStateChangedHandler(com.coveiot.sdk.ble.CloveBleState):void");
    }

    @Override // com.coveiot.android.touchsdk.TouchELXResponseListener
    public void onFailure(@NotNull TouchELXError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        sendErrorAndClearQueue(error);
    }

    @Override // com.coveiot.android.touchsdk.TouchELXResponseListener
    public void onResponse(@NotNull TouchELXBaseRes baseRes) {
        boolean z;
        boolean z2;
        int i;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Intrinsics.checkNotNullParameter(baseRes, "baseRes");
        try {
            final BleBaseRequest fromQueue = getFromQueue(baseRes.getBaseReq());
            if (fromQueue != null) {
                if (fromQueue.getResponseListener() instanceof DataResultListener) {
                    final DataResultListener dataResultListener = (DataResultListener) fromQueue.getResponseListener();
                    TouchELXBaseReq baseReq = baseRes.getBaseReq();
                    if (baseReq instanceof TouchStepsReq) {
                        this.h.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof List)) {
                            Intrinsics.checkNotNull(baseRes.getObj(), "null cannot be cast to non-null type kotlin.collections.List<*>");
                            if (!((List) obj6).isEmpty()) {
                                Object obj7 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                for (Object obj8 : (List) obj7) {
                                    if (obj8 instanceof StepsResponse) {
                                        final BleBaseResponse bleBaseResponse = new BleBaseResponse(fromQueue);
                                        ((StepsResponse) obj8).setCaloriesDistanceCalculatedFromBand(true);
                                        ((StepsResponse) obj8).setDistanceIsInMetresFromBand(true);
                                        bleBaseResponse.setResponseData(obj8);
                                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.m9
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                TouchELXBaseBleImpl.n(DataResultListener.this, bleBaseResponse);
                                            }
                                        });
                                        if (((StepsResponse) obj8).isComplete()) {
                                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXBaseBleImpl$onResponse$2(this, null), 2, null);
                                            setCompleteAndProcessNext(fromQueue);
                                        }
                                    }
                                }
                                return;
                            }
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.x9
                            @Override // java.lang.Runnable
                            public final void run() {
                                TouchELXBaseBleImpl.i(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof TouchSleepReq) {
                        this.h.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof List)) {
                            Intrinsics.checkNotNull(baseRes.getObj(), "null cannot be cast to non-null type kotlin.collections.List<*>");
                            if (!((List) obj5).isEmpty()) {
                                Object obj9 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj9, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                for (Object obj10 : (List) obj9) {
                                    if (obj10 instanceof SleepResponse) {
                                        final BleBaseResponse bleBaseResponse2 = new BleBaseResponse(fromQueue);
                                        bleBaseResponse2.setResponseData(obj10);
                                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.ca
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                TouchELXBaseBleImpl.a(DataResultListener.this, bleBaseResponse2);
                                            }
                                        });
                                        if (((SleepResponse) obj10).isComplete()) {
                                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXBaseBleImpl$onResponse$5(this, null), 2, null);
                                            setCompleteAndProcessNext(fromQueue);
                                        }
                                    }
                                }
                                return;
                            }
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.o9
                            @Override // java.lang.Runnable
                            public final void run() {
                                TouchELXBaseBleImpl.a(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof TouchHeartRateReq) {
                        this.h.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof List)) {
                            Intrinsics.checkNotNull(baseRes.getObj(), "null cannot be cast to non-null type kotlin.collections.List<*>");
                            if (!((List) obj4).isEmpty()) {
                                Object obj11 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj11, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                for (Object obj12 : (List) obj11) {
                                    if (obj12 instanceof HeartRateResponse) {
                                        final BleBaseResponse bleBaseResponse3 = new BleBaseResponse(fromQueue);
                                        bleBaseResponse3.setResponseData(obj12);
                                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.da
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                TouchELXBaseBleImpl.b(DataResultListener.this, bleBaseResponse3);
                                            }
                                        });
                                        if (((HeartRateResponse) obj12).isComplete()) {
                                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXBaseBleImpl$onResponse$8(this, null), 2, null);
                                            setCompleteAndProcessNext(fromQueue);
                                        }
                                    }
                                }
                                return;
                            }
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.q9
                            @Override // java.lang.Runnable
                            public final void run() {
                                TouchELXBaseBleImpl.b(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof TouchStressReq) {
                        this.h.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof List)) {
                            Intrinsics.checkNotNull(baseRes.getObj(), "null cannot be cast to non-null type kotlin.collections.List<*>");
                            if (!((List) obj3).isEmpty()) {
                                Object obj13 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj13, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                for (Object obj14 : (List) obj13) {
                                    if (obj14 instanceof StressResponse) {
                                        final BleBaseResponse bleBaseResponse4 = new BleBaseResponse(fromQueue);
                                        bleBaseResponse4.setResponseData(obj14);
                                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.ea
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                TouchELXBaseBleImpl.c(DataResultListener.this, bleBaseResponse4);
                                            }
                                        });
                                        if (((StressResponse) obj14).isComplete()) {
                                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXBaseBleImpl$onResponse$11(this, null), 2, null);
                                            setCompleteAndProcessNext(fromQueue);
                                        }
                                    }
                                }
                                return;
                            }
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.r9
                            @Override // java.lang.Runnable
                            public final void run() {
                                TouchELXBaseBleImpl.c(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof TouchSpo2Req) {
                        this.h.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof List)) {
                            Intrinsics.checkNotNull(baseRes.getObj(), "null cannot be cast to non-null type kotlin.collections.List<*>");
                            if (!((List) obj2).isEmpty()) {
                                Object obj15 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj15, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                for (Object obj16 : (List) obj15) {
                                    if (obj16 instanceof PeriodicSpo2Response) {
                                        final BleBaseResponse bleBaseResponse5 = new BleBaseResponse(fromQueue);
                                        bleBaseResponse5.setResponseData(obj16);
                                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.fa
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                TouchELXBaseBleImpl.d(DataResultListener.this, bleBaseResponse5);
                                            }
                                        });
                                        if (((PeriodicSpo2Response) obj16).isComplete()) {
                                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXBaseBleImpl$onResponse$14(this, null), 2, null);
                                            setCompleteAndProcessNext(fromQueue);
                                        }
                                    }
                                }
                                return;
                            }
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.s9
                            @Override // java.lang.Runnable
                            public final void run() {
                                TouchELXBaseBleImpl.d(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof TouchManualSpo2Req) {
                        this.h.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof List)) {
                            Intrinsics.checkNotNull(baseRes.getObj(), "null cannot be cast to non-null type kotlin.collections.List<*>");
                            if (!((List) obj).isEmpty()) {
                                Object obj17 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj17, "null cannot be cast to non-null type kotlin.collections.List<*>");
                                for (Object obj18 : (List) obj17) {
                                    if (obj18 instanceof ReadManualSpo2Response) {
                                        final BleBaseResponse bleBaseResponse6 = new BleBaseResponse(fromQueue);
                                        bleBaseResponse6.setResponseData(obj18);
                                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.ga
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                TouchELXBaseBleImpl.e(DataResultListener.this, bleBaseResponse6);
                                            }
                                        });
                                        if (((ReadManualSpo2Response) obj18).isComplete()) {
                                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXBaseBleImpl$onResponse$17(this, null), 2, null);
                                            setCompleteAndProcessNext(fromQueue);
                                        }
                                    }
                                }
                                return;
                            }
                        }
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.t9
                            @Override // java.lang.Runnable
                            public final void run() {
                                TouchELXBaseBleImpl.e(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof TouchBatteryLevelReq) {
                        this.h.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            LogHelper.d(this.b, "GetDeviceBatteryLevel == " + baseRes.getObj());
                            Object obj19 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj19, "null cannot be cast to non-null type kotlin.Int");
                            int intValue = ((Integer) obj19).intValue();
                            final DataResultListener dataResultListener2 = (DataResultListener) fromQueue.getResponseListener();
                            BatteryLevelResponse batteryLevelResponse = new BatteryLevelResponse();
                            batteryLevelResponse.setBatteryLevel(Integer.valueOf(intValue));
                            batteryLevelResponse.setComplete(true);
                            final BleBaseResponse bleBaseResponse7 = new BleBaseResponse(fromQueue);
                            bleBaseResponse7.setResponseData(batteryLevelResponse);
                            this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.ha
                                @Override // java.lang.Runnable
                                public final void run() {
                                    TouchELXBaseBleImpl.f(DataResultListener.this, bleBaseResponse7);
                                }
                            });
                            setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        final DataResultListener dataResultListener3 = (DataResultListener) fromQueue.getResponseListener();
                        BatteryLevelResponse batteryLevelResponse2 = new BatteryLevelResponse();
                        batteryLevelResponse2.setComplete(true);
                        final BleBaseResponse bleBaseResponse8 = new BleBaseResponse(fromQueue);
                        bleBaseResponse8.setResponseData(batteryLevelResponse2);
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.f9
                            @Override // java.lang.Runnable
                            public final void run() {
                                TouchELXBaseBleImpl.g(DataResultListener.this, bleBaseResponse8);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof TouchDeviceInfoReq) {
                        this.h.removeCallbacksAndMessages(null);
                        if (baseRes.getObj() != null) {
                            LogHelper.d(this.b, "TouchDeviceInfoReq == " + baseRes.getObj());
                            DeviceInfoData deviceInfoData = new DeviceInfoData();
                            deviceInfoData.setMacAddress(getMacAddress());
                            deviceInfoData.setHwVersion("1.0");
                            deviceInfoData.setSerialNo(new Regex(":").replace(getMacAddress(), ""));
                            Object obj20 = baseRes.getObj();
                            Intrinsics.checkNotNull(obj20, "null cannot be cast to non-null type kotlin.String");
                            deviceInfoData.setFwVersion((String) obj20);
                            DeviceInfoResponse deviceInfoResponse = new DeviceInfoResponse();
                            deviceInfoResponse.setComplete(true);
                            deviceInfoResponse.setDeviceInfo(deviceInfoData);
                            final BleBaseResponse bleBaseResponse9 = new BleBaseResponse(fromQueue);
                            bleBaseResponse9.setResponseData(deviceInfoResponse);
                            this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.g9
                                @Override // java.lang.Runnable
                                public final void run() {
                                    TouchELXBaseBleImpl.h(DataResultListener.this, bleBaseResponse9);
                                }
                            });
                            setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        DeviceInfoData deviceInfoData2 = new DeviceInfoData();
                        final BleBaseResponse bleBaseResponse10 = new BleBaseResponse(fromQueue);
                        DeviceInfoResponse deviceInfoResponse2 = new DeviceInfoResponse();
                        deviceInfoResponse2.setDeviceInfo(deviceInfoData2);
                        bleBaseResponse10.setResponseData(deviceInfoResponse2);
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.h9
                            @Override // java.lang.Runnable
                            public final void run() {
                                TouchELXBaseBleImpl.i(DataResultListener.this, bleBaseResponse10);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else {
                        if (baseReq instanceof TouchGetAlarmReq) {
                            this.h.removeCallbacksAndMessages(null);
                            if (baseRes.getObj() != null && (baseRes.getObj() instanceof List)) {
                                ArrayList arrayList = new ArrayList();
                                Object obj21 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj21, "null cannot be cast to non-null type kotlin.collections.List<com.touchgui.sdk.bean.TGAlarm>");
                                for (TGAlarm tGAlarm : (List) obj21) {
                                    if (tGAlarm.isShow()) {
                                        Alarm alarm = new Alarm(tGAlarm.isOn(), tGAlarm.getId(), tGAlarm.getHour(), tGAlarm.getMinute(), tGAlarm.getType(), "", false, false, false, false, false, false, false, false, 16320, null);
                                        List<Integer> eachBit = BleApiUtils.INSTANCE.getEachBit(tGAlarm.getRepeat());
                                        alarm.setMondayEnabled(eachBit.get(1).intValue() == 1);
                                        alarm.setTuesdayEnabled(eachBit.get(2).intValue() == 1);
                                        alarm.setWednesdayEnabled(eachBit.get(3).intValue() == 1);
                                        alarm.setThursdayEnabled(eachBit.get(4).intValue() == 1);
                                        alarm.setFridayEnabled(eachBit.get(5).intValue() == 1);
                                        alarm.setSaturdayEnabled(eachBit.get(6).intValue() == 1);
                                        alarm.setSundayEnabled(eachBit.get(7).intValue() == 1);
                                        alarm.setRepeatEnabled(eachBit.get(0).intValue() == 1);
                                        arrayList.add(alarm);
                                    }
                                }
                                final BleBaseResponse bleBaseResponse11 = new BleBaseResponse(fromQueue);
                                bleBaseResponse11.setResponseData(arrayList);
                                this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.i9
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        TouchELXBaseBleImpl.j(DataResultListener.this, bleBaseResponse11);
                                    }
                                });
                            }
                            setCompleteAndProcessNext(fromQueue);
                        } else if (baseReq instanceof TouchGetDNDReq) {
                            this.h.removeCallbacksAndMessages(null);
                            if (baseRes.getObj() != null && (baseRes.getObj() instanceof TGNotDisturbConfig)) {
                                Object obj22 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj22, "null cannot be cast to non-null type com.touchgui.sdk.bean.TGNotDisturbConfig");
                                TGNotDisturbConfig tGNotDisturbConfig = (TGNotDisturbConfig) obj22;
                                final DNDData dNDData = new DNDData(tGNotDisturbConfig.isOn(), tGNotDisturbConfig.getStartHour(), tGNotDisturbConfig.getStartMinute(), tGNotDisturbConfig.getStopHour(), tGNotDisturbConfig.getStopMinute());
                                this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.y9
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        TouchELXBaseBleImpl.a(BleBaseRequest.this, dNDData, dataResultListener);
                                    }
                                });
                            }
                            setCompleteAndProcessNext(fromQueue);
                        } else if (baseReq instanceof TouchGetLiftWristReq) {
                            this.h.removeCallbacksAndMessages(null);
                            if (baseRes.getObj() != null && (baseRes.getObj() instanceof TGRaiseWristConfig)) {
                                Object obj23 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj23, "null cannot be cast to non-null type com.touchgui.sdk.bean.TGRaiseWristConfig");
                                TGRaiseWristConfig tGRaiseWristConfig = (TGRaiseWristConfig) obj23;
                                final GetLiftWristResponse getLiftWristResponse = new GetLiftWristResponse();
                                getLiftWristResponse.setLiftWristEnabled(tGRaiseWristConfig.isOn());
                                getLiftWristResponse.setStartHour(tGRaiseWristConfig.getStartHour());
                                getLiftWristResponse.setStartMinute(tGRaiseWristConfig.getStartMinute());
                                getLiftWristResponse.setEndHour(tGRaiseWristConfig.getStopHour());
                                getLiftWristResponse.setEndMinute(tGRaiseWristConfig.getStopMinute());
                                this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.z9
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        TouchELXBaseBleImpl.a(BleBaseRequest.this, getLiftWristResponse, dataResultListener);
                                    }
                                });
                            }
                            setCompleteAndProcessNext(fromQueue);
                        } else if (baseReq instanceof TouchGetWorkoutModesReq) {
                            this.h.removeCallbacksAndMessages(null);
                            if (baseRes.getObj() != null && baseRes.getObj2() != null) {
                                ArrayList arrayList2 = new ArrayList();
                                Object obj24 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj24, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Int>");
                                for (Number number : (List) obj24) {
                                    arrayList2.add(new ActivityTypeModel(number.intValue()));
                                }
                                ArrayList arrayList3 = new ArrayList();
                                Object obj25 = baseRes.getObj2();
                                Intrinsics.checkNotNull(obj25, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Int>");
                                for (Number number2 : (List) obj25) {
                                    arrayList3.add(new ActivityTypeModel(number2.intValue()));
                                }
                                GetActivityListResponse getActivityListResponse = new GetActivityListResponse(arrayList2, arrayList3);
                                getActivityListResponse.setComplete(true);
                                final BleBaseResponse bleBaseResponse12 = new BleBaseResponse(fromQueue);
                                bleBaseResponse12.setResponseData(getActivityListResponse);
                                this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.j9
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        TouchELXBaseBleImpl.k(DataResultListener.this, bleBaseResponse12);
                                    }
                                });
                            }
                            setCompleteAndProcessNext(fromQueue);
                        } else if (baseReq instanceof TouchGetQuickCardReq) {
                            this.h.removeCallbacksAndMessages(null);
                            if (baseRes.getObj() != null) {
                                Object obj26 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj26, "null cannot be cast to non-null type kotlin.collections.List<com.touchgui.sdk.bean.TGQuickCard>");
                                List list = (List) obj26;
                                ArrayList arrayList4 = new ArrayList();
                                ArrayList arrayList5 = new ArrayList();
                                int size = list.size();
                                for (int i2 = 0; i2 < size; i2++) {
                                    arrayList4.add(Integer.valueOf(((TGQuickCard) list.get(i2)).getId()));
                                    if (((TGQuickCard) list.get(i2)).isVisible()) {
                                        arrayList5.add(Integer.valueOf(((TGQuickCard) list.get(i2)).getId()));
                                    }
                                }
                                GetSupportedShortcutMenuListResponse getSupportedShortcutMenuListResponse = new GetSupportedShortcutMenuListResponse(arrayList4, CollectionsKt___CollectionsKt.sorted(arrayList5));
                                getSupportedShortcutMenuListResponse.setComplete(true);
                                final BleBaseResponse bleBaseResponse13 = new BleBaseResponse(fromQueue);
                                bleBaseResponse13.setResponseData(getSupportedShortcutMenuListResponse);
                                this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.k9
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        TouchELXBaseBleImpl.l(DataResultListener.this, bleBaseResponse13);
                                    }
                                });
                            }
                            setCompleteAndProcessNext(fromQueue);
                        } else if (baseReq instanceof TouchCloudWatchFaceReq ? true : baseReq instanceof TouchDIYWatchFaceReq) {
                            LogHelper.d(this.b, "TouchCloudWatchFaceReq or TouchDIYWatchFaceReq == " + baseRes.getObj());
                            if (baseRes.getObj() != null && (baseRes.getObj() instanceof LiveWatchFaceUploadPercentage)) {
                                ProgressType progressType = ProgressType.DETERMINATE;
                                Object obj27 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj27, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage");
                                ProgressData progressData = new ProgressData(progressType, ((LiveWatchFaceUploadPercentage) obj27).getPercentage(), fromQueue);
                                Intrinsics.checkNotNull(dataResultListener);
                                dataResultListener.onProgressUpdate(progressData);
                                return;
                            }
                            this.h.removeCallbacksAndMessages(null);
                            this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.u9
                                @Override // java.lang.Runnable
                                public final void run() {
                                    TouchELXBaseBleImpl.f(BleBaseRequest.this, dataResultListener);
                                }
                            });
                            setCompleteAndProcessNext(fromQueue);
                        } else if (baseReq instanceof TouchGetEventReminderReq) {
                            this.h.removeCallbacksAndMessages(null);
                            if (baseRes.getObj() != null && (baseRes.getObj() instanceof List)) {
                                ArrayList arrayList6 = new ArrayList();
                                Object obj28 = baseRes.getObj();
                                Intrinsics.checkNotNull(obj28, "null cannot be cast to non-null type kotlin.collections.List<com.touchgui.sdk.bean.TGEventReminder>");
                                for (TGEventReminder tGEventReminder : (List) obj28) {
                                    EventReminder eventReminder = new EventReminder(tGEventReminder.getId(), tGEventReminder.getContent(), tGEventReminder.getYear(), tGEventReminder.getMonth(), tGEventReminder.getDay(), tGEventReminder.getHour(), tGEventReminder.getMinute());
                                    boolean[] weekRepeat = tGEventReminder.getWeekRepeat();
                                    Intrinsics.checkNotNullExpressionValue(weekRepeat, "eventReminderData.weekRepeat");
                                    int length = weekRepeat.length;
                                    int i3 = 0;
                                    while (true) {
                                        if (i3 >= length) {
                                            z = true;
                                            break;
                                        } else if (!weekRepeat[i3]) {
                                            z = false;
                                            break;
                                        } else {
                                            i3++;
                                        }
                                    }
                                    if (z) {
                                        i = 1;
                                    } else {
                                        boolean[] weekRepeat2 = tGEventReminder.getWeekRepeat();
                                        Intrinsics.checkNotNullExpressionValue(weekRepeat2, "eventReminderData.weekRepeat");
                                        int length2 = weekRepeat2.length;
                                        int i4 = 0;
                                        while (true) {
                                            if (i4 >= length2) {
                                                z2 = false;
                                                break;
                                            } else if (weekRepeat2[i4]) {
                                                z2 = true;
                                                break;
                                            } else {
                                                i4++;
                                            }
                                        }
                                        if (z2) {
                                            i = 2;
                                        } else if (tGEventReminder.isMonthRepeat()) {
                                            i = 3;
                                        } else {
                                            i = tGEventReminder.isYearRepeat() ? 4 : 0;
                                        }
                                    }
                                    eventReminder.setRepeatType(i);
                                    eventReminder.setType(tGEventReminder.getType());
                                    arrayList6.add(eventReminder);
                                }
                                final BleBaseResponse bleBaseResponse14 = new BleBaseResponse(fromQueue);
                                bleBaseResponse14.setResponseData(arrayList6);
                                this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.l9
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        TouchELXBaseBleImpl.m(DataResultListener.this, bleBaseResponse14);
                                    }
                                });
                            }
                            setCompleteAndProcessNext(fromQueue);
                        } else if (baseReq instanceof TouchActivityDataReq) {
                            this.h.removeCallbacksAndMessages(null);
                            if (baseRes.getObj() != null) {
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXBaseBleImpl$onResponse$30(baseRes, fromQueue, this, dataResultListener, null), 2, null);
                                return;
                            }
                            this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.v9
                                @Override // java.lang.Runnable
                                public final void run() {
                                    TouchELXBaseBleImpl.g(BleBaseRequest.this, dataResultListener);
                                }
                            });
                            setCompleteAndProcessNext(fromQueue);
                        } else if (baseReq instanceof TouchSyncContactsReq) {
                            this.h.removeCallbacksAndMessages(null);
                            this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.w9
                                @Override // java.lang.Runnable
                                public final void run() {
                                    TouchELXBaseBleImpl.h(BleBaseRequest.this, dataResultListener);
                                }
                            });
                            setCompleteAndProcessNext(fromQueue);
                        }
                    }
                } else if (fromQueue.getResponseListener() instanceof SettingsResultListener) {
                    TouchELXBaseReq baseReq2 = baseRes.getBaseReq();
                    if (baseReq2 instanceof TouchUserInfoReq ? true : baseReq2 instanceof TouchAlarmReq ? true : baseReq2 instanceof TouchSedentaryReminderReq ? true : baseReq2 instanceof TouchDrinkReminderReq ? true : baseReq2 instanceof TouchSetDNDReq ? true : baseReq2 instanceof TouchLiftWristReq ? true : baseReq2 instanceof TouchHeartRateIntervalReq ? true : baseReq2 instanceof TouchStressIntervalReq ? true : baseReq2 instanceof TouchSPO2IntervalReq ? true : baseReq2 instanceof TouchSetGoalReq ? true : baseReq2 instanceof TouchSetUnitReq ? true : baseReq2 instanceof TouchSetMsgNotificationReq ? true : baseReq2 instanceof TouchSetCallNotificationReq ? true : baseReq2 instanceof TouchSetWeatherUnitReq ? true : baseReq2 instanceof TouchSetWeatherConfigReq ? true : baseReq2 instanceof TouchFemaleWellnessConfigReq ? true : baseReq2 instanceof TouchMusicMetaDataReq ? true : baseReq2 instanceof TouchFindDeviceReq ? true : baseReq2 instanceof TouchQuickReplyReq ? true : baseReq2 instanceof TouchSetWorkoutModesReq ? true : baseReq2 instanceof TouchDefaultWatchFaceReq ? true : baseReq2 instanceof TouchCameraControlReq ? true : baseReq2 instanceof TouchSetQuickCardsReq ? true : baseReq2 instanceof TouchEventReminderReq ? true : baseReq2 instanceof TouchSetCallStatusReq) {
                        this.h.removeCallbacksAndMessages(null);
                        LogHelper.d(this.b, "onResponse  ==" + baseRes.getBaseReq());
                        this.p.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.n9
                            @Override // java.lang.Runnable
                            public final void run() {
                                TouchELXBaseBleImpl.a(BleBaseRequest.this, this);
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void processNextCommand() {
        LinkedList<QueueObject> linkedList = this.o;
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        TouchELXBaseReq a2 = a(this.o.get(0).getBaseRequest());
        if (a2 != null) {
            BleBaseRequest bleBaseRequest = this.i;
            if (bleBaseRequest != null) {
                Intrinsics.checkNotNull(bleBaseRequest);
                if (!bleBaseRequest.isComplete()) {
                    return;
                }
            }
            sendRequestToBleService(a2);
            return;
        }
        sendCommandNotFoundError(this.o.get(0).getBaseRequest());
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.c == null) {
            this.c = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        Intrinsics.checkNotNull(connectionStatus);
        if (getBleService() != null) {
            TouchELXService bleService = getBleService();
            Intrinsics.checkNotNull(bleService);
            if (bleService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else {
                TouchELXService bleService2 = getBleService();
                Intrinsics.checkNotNull(bleService2);
                if (bleService2.getConnectionState() == CloveBleState.BleState.CONNECTING) {
                    connectionStatus = ConnectionStatus.CONNECTING;
                } else {
                    TouchELXService bleService3 = getBleService();
                    Intrinsics.checkNotNull(bleService3);
                    if (bleService3.getConnectionState() == CloveBleState.BleState.SCANNING) {
                        connectionStatus = ConnectionStatus.SCANNING;
                    } else {
                        TouchELXService bleService4 = getBleService();
                        Intrinsics.checkNotNull(bleService4);
                        if (bleService4.getConnectionState() == CloveBleState.BleState.DISCOVERING_SERVICES) {
                            connectionStatus = ConnectionStatus.DISCOVERING_SERVICES;
                        }
                    }
                }
            }
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.c;
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.postValue(connectionStatus);
        MutableLiveData<ConnectionStatus> mutableLiveData2 = this.c;
        Intrinsics.checkNotNull(mutableLiveData2);
        return mutableLiveData2;
    }

    public void registerEventBus() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new TouchELXBaseBleImpl$registerEventBus$1(this, null), 2, null);
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
        r6.o.remove(r2).getBaseRequest();
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
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$QueueObject> r0 = r6.o
            monitor-enter(r0)
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$QueueObject> r1 = r6.o     // Catch: java.lang.Throwable -> L3d
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L3d
            r2 = 0
        Lf:
            if (r2 >= r1) goto L3b
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$QueueObject> r3 = r6.o     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$QueueObject r3 = (com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl.QueueObject) r3     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.request.BleBaseRequest r3 = r3.getBaseRequest()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r3 = r3.getRequId()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = r7.getRequId()     // Catch: java.lang.Throwable -> L3d
            r5 = 1
            boolean r3 = kotlin.text.m.equals(r3, r4, r5)     // Catch: java.lang.Throwable -> L3d
            if (r3 == 0) goto L38
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$QueueObject> r7 = r6.o     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r7 = r7.remove(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$QueueObject r7 = (com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl.QueueObject) r7     // Catch: java.lang.Throwable -> L3d
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
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl.removeFromQueue(com.coveiot.android.bleabstract.request.BleBaseRequest):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        a();
        TouchELXService bleService = getBleService();
        if (bleService != null) {
            bleService.restartService();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.e9
            @Override // java.lang.Runnable
            public final void run() {
                TouchELXBaseBleImpl.a(TouchELXBaseBleImpl.this);
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
            String string = this.f3268a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.command_not_found)");
            ((DataResultListener) responseListener).onDataError(new BleBaseError(string));
        } else if (responseListener instanceof SettingsResultListener) {
            String string2 = this.f3268a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.command_not_found)");
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(string2));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, this.f3268a.getString(R.string.command_not_found)));
        }
    }

    public final void sendCommandRequest() {
        LinkedList<QueueObject> linkedList = this.o;
        if (linkedList == null || linkedList.size() <= 0 || this.i != null) {
            return;
        }
        processNextCommand();
    }

    public final void sendErrorAndClearQueue(@NotNull TouchELXError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        synchronized (this.o) {
            LinkedList<QueueObject> linkedList = this.o;
            if (linkedList != null && linkedList.size() > 0) {
                try {
                    Object clone = this.o.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl.QueueObject>");
                    Iterator it = ((LinkedList) clone).iterator();
                    while (it.hasNext()) {
                        BaseListener responseListener = ((QueueObject) it.next()).getBaseRequest().getResponseListener();
                        String message = error.getMessage();
                        Intrinsics.checkNotNull(message);
                        BleBaseError bleBaseError = new BleBaseError(message);
                        if (error.getErrorType() == TouchELXErrorType.COMMAND_TIME_OUT) {
                            bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_ERROR_TIMEOUT.value));
                        } else if (error.getErrorType() == TouchELXErrorType.COMMAND_RESPONSE_ERROR) {
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

    public final void sendRequestToBleService(@Nullable TouchELXBaseReq touchELXBaseReq) {
        this.i = this.o.get(0).getBaseRequest();
        if (touchELXBaseReq == null) {
            sendCommandNotFoundError(this.o.get(0).getBaseRequest());
        } else if (touchELXBaseReq.isMultiPacket()) {
            if (touchELXBaseReq instanceof TouchDIYWatchFaceReq) {
                this.h.postDelayed(this.r, this.j);
            } else if (touchELXBaseReq instanceof TouchCloudWatchFaceReq) {
                this.h.postDelayed(this.r, this.j);
            } else {
                this.h.postDelayed(this.r, this.k);
            }
        } else {
            this.h.postDelayed(this.r, this.l);
        }
        TouchDeviceManager touchDeviceManager = this.m;
        Boolean valueOf = touchDeviceManager != null ? Boolean.valueOf(touchDeviceManager.isConnected()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() && touchELXBaseReq != null && getBleService() != null) {
            TouchELXService bleService = getBleService();
            if (bleService != null) {
                bleService.sendRequest(touchELXBaseReq, this);
                return;
            }
            return;
        }
        onFailure(new TouchELXError(TouchELXErrorType.DEVICE_NOT_CONNECTED, this.f3268a.getString(R.string.band_not_connected)));
    }

    public void setBleService(@Nullable TouchELXService touchELXService) {
        this.g = touchELXService;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setBusyStatus(value);
    }

    public final void setCompleteAndProcessNext(@Nullable BleBaseRequest bleBaseRequest) {
        if (bleBaseRequest != null) {
            removeFromQueue(bleBaseRequest);
            String str = this.b;
            LogHelper.d(str, "setCompleteAndProcessNext--> removed " + bleBaseRequest);
        }
        if (this.i != null) {
            this.i = null;
        }
        processNextCommand();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d = listener;
    }

    public final void setMTouchDeviceManager(@Nullable TouchDeviceManager touchDeviceManager) {
        this.m = touchDeviceManager;
    }

    public final void setServiceConnection(@Nullable ServiceConnection serviceConnection) {
        this.n = serviceConnection;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (getBleService() != null) {
            TouchELXService bleService = getBleService();
            Intrinsics.checkNotNull(bleService);
            if (bleService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                TouchDeviceManager touchDeviceManager = this.m;
                Boolean valueOf = touchDeviceManager != null ? Boolean.valueOf(touchDeviceManager.isConnected()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue()) {
                    if (b(request)) {
                        request.setRequId(UUID.randomUUID().toString());
                        request.setResponseListener(listener);
                        addToQueue(request);
                        sendCommandRequest();
                        return;
                    }
                    String str = this.b;
                    LogHelper.d(str, "setUserSettings->Ignore {" + request + '}');
                    return;
                }
            }
        }
        String string = this.f3268a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }

    public final void startBleService() {
        Intent intent = new Intent(this.f3268a, TouchELXService.class);
        try {
            Context context = this.f3268a;
            ServiceConnection serviceConnection = this.n;
            Intrinsics.checkNotNull(serviceConnection);
            context.bindService(intent, serviceConnection, 1);
            if (Build.VERSION.SDK_INT >= 26) {
                this.f3268a.startForegroundService(intent);
            } else {
                this.f3268a.startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this.f3268a);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(this.f3268a).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManagerAbstract.getInstance(this.f3268a).saveConnectedDeviceMAcAddress("");
        PreferenceManagerAbstract.getInstance(this.f3268a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        if (getBleService() != null) {
            TouchELXService bleService = getBleService();
            Intrinsics.checkNotNull(bleService);
            bleService.stopService();
            a();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        LogHelper.d(this.b, "stopServiceAndRetainMacAddress");
        clearCommandQueue();
        PreferenceManagerAbstract.getInstance(this.f3268a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        if (getBleService() != null) {
            TouchELXService bleService = getBleService();
            if (bleService != null) {
                bleService.stopServiceRetainMacAddress();
            }
            a();
        }
    }

    public final void unbindService() {
        ServiceConnection serviceConnection = this.n;
        if (serviceConnection != null) {
            try {
                Context context = this.f3268a;
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

    public static final void b(TouchELXBaseBleImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.b, "Command TimeOut,Failed");
        BleBaseRequest bleBaseRequest = this$0.i;
        if (bleBaseRequest != null) {
            Intrinsics.checkNotNull(bleBaseRequest);
            TouchELXBaseReq a2 = this$0.a(bleBaseRequest);
            if (a2 != null) {
                this$0.onFailure(new TouchELXError(TouchELXErrorType.COMMAND_TIME_OUT, this$0.f3268a.getString(R.string.command_time_out)));
                LogHelper.e("Command TimeOut", "End Time " + System.currentTimeMillis() + "-- currentCommand " + a2);
            } else {
                BleBaseRequest bleBaseRequest2 = this$0.i;
                Intrinsics.checkNotNull(bleBaseRequest2);
                this$0.sendCommandNotFoundError(bleBaseRequest2);
            }
        }
        this$0.i = null;
    }

    public static final void c(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        StressResponse stressResponse = new StressResponse();
        stressResponse.setComplete(true);
        bleBaseResponse.setResponseData(stressResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void d(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        PeriodicSpo2Response periodicSpo2Response = new PeriodicSpo2Response();
        periodicSpo2Response.setComplete(true);
        bleBaseResponse.setResponseData(periodicSpo2Response);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void e(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(new ArrayList());
        readManualSpo2Response.setComplete(true);
        bleBaseResponse.setResponseData(readManualSpo2Response);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void f(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(null);
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

    public static final void i(DataResultListener dataResultListener, BleBaseResponse baseResponse) {
        Intrinsics.checkNotNullParameter(baseResponse, "$baseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(baseResponse);
    }

    public static final void a(final TouchELXBaseBleImpl this$0, final ScanDeviceRequest scanDeviceReq, final ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "$scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        try {
            if (!DeviceScanManager.getInstance(this$0.f3268a).isScanningInProgress()) {
                if (BleUtils.isEmpty(scanDeviceReq.getScanFilter())) {
                    DeviceScanManager.getInstance(this$0.f3268a).scanAllDevices(this$0.f3268a, scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$scan_$2$1
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            TouchELXBaseBleImpl.this.scanResultReceieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = TouchELXBaseBleImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …                        )");
                            scanResultListener.onError(string);
                        }
                    });
                } else {
                    DeviceScanManager.getInstance(this$0.f3268a).scanDevicesWithFilter(this$0.f3268a, scanDeviceReq.getScanFilter(), scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$scan_$2$2
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            TouchELXBaseBleImpl.this.scanResultReceieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = TouchELXBaseBleImpl.this.getContext().getString(R.string.scan_failed);
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
            BleBaseRequest bleBaseRequest2 = this.i;
            if (bleBaseRequest2 != null) {
                if (((bleBaseRequest2 instanceof CustomWatchFaceFileImageRequest) || (bleBaseRequest2 instanceof CustomWatchFaceBackgroundChangeRequest)) && bleBaseRequest != null && (bleBaseRequest instanceof SetMessageContentRequest) && ((SetMessageContentRequest) bleBaseRequest).appNotificationType != null && ((SetMessageContentRequest) bleBaseRequest).appNotificationType == NotificationType.CALL) {
                    z = false;
                    LogHelper.d(this.b, "Ignore incoming call triggered during watch face upgrade");
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

    public static final void b(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void b(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        HeartRateResponse heartRateResponse = new HeartRateResponse();
        heartRateResponse.setComplete(true);
        bleBaseResponse.setResponseData(heartRateResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(TouchELXBaseBleImpl this$0, ConnectRequest request, ConnectionResultListener listener) {
        BluetoothDevice mBluetoothDevice;
        BluetoothDevice mBluetoothDevice2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        if (this$0.getBleService() != null) {
            TouchELXService bleService = this$0.getBleService();
            r1 = null;
            String str = null;
            r1 = null;
            String str2 = null;
            CloveBleState.BleState connectionState = bleService != null ? bleService.getConnectionState() : null;
            CloveBleState.BleState bleState = CloveBleState.BleState.CONNECTED;
            if (connectionState == bleState && kotlin.text.m.equals(this$0.getMacAddress(), request.getMacAddress(), true)) {
                listener.onConnectionResponse(ConnectionStatus.CONNECTED);
                return;
            }
            TouchELXService bleService2 = this$0.getBleService();
            if ((bleService2 != null ? bleService2.getConnectionState() : null) == CloveBleState.BleState.DISCONNECTED) {
                TouchELXService bleService3 = this$0.getBleService();
                if (bleService3 != null) {
                    bleService3.connect(request.getMacAddress());
                    return;
                }
                return;
            }
            TouchELXService bleService4 = this$0.getBleService();
            if ((bleService4 != null ? bleService4.getConnectionState() : null) == bleState) {
                TouchELXService bleService5 = this$0.getBleService();
                if ((bleService5 != null ? bleService5.getMBluetoothDevice() : null) != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Connected to band ");
                    TouchELXService bleService6 = this$0.getBleService();
                    if (bleService6 != null && (mBluetoothDevice2 = bleService6.getMBluetoothDevice()) != null) {
                        str = mBluetoothDevice2.getAddress();
                    }
                    sb.append(str);
                    listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, sb.toString()));
                    return;
                }
                TouchELXService bleService7 = this$0.getBleService();
                CloveBleState.BleState connectionState2 = bleService7 != null ? bleService7.getConnectionState() : null;
                Intrinsics.checkNotNull(connectionState2);
                listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, connectionState2.getStateAsString()));
                return;
            }
            TouchELXService bleService8 = this$0.getBleService();
            if ((bleService8 != null ? bleService8.getConnectionState() : null) == CloveBleState.BleState.CONNECTING) {
                TouchELXService bleService9 = this$0.getBleService();
                if ((bleService9 != null ? bleService9.getMBluetoothDevice() : null) != null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Connection in progress ");
                    TouchELXService bleService10 = this$0.getBleService();
                    if (bleService10 != null && (mBluetoothDevice = bleService10.getMBluetoothDevice()) != null) {
                        str2 = mBluetoothDevice.getAddress();
                    }
                    sb2.append(str2);
                    listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, sb2.toString()));
                    return;
                }
                TouchELXService bleService11 = this$0.getBleService();
                CloveBleState.BleState connectionState3 = bleService11 != null ? bleService11.getConnectionState() : null;
                Intrinsics.checkNotNull(connectionState3);
                listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, connectionState3.getStateAsString()));
                return;
            }
            TouchELXService bleService12 = this$0.getBleService();
            CloveBleState.BleState connectionState4 = bleService12 != null ? bleService12.getConnectionState() : null;
            Intrinsics.checkNotNull(connectionState4);
            listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, connectionState4.getStateAsString()));
        } else if (this$0.getBleService() == null) {
            this$0.startBleService();
            listener.onError(new Error(Type.SERVICE_NOT_RUNNING, this$0.f3268a.getString(R.string.service_not_running)));
        } else {
            listener.onError(new Error(Type.COMMAND_REQUEST_ERROR, this$0.f3268a.getString(R.string.request_should_not_null)));
        }
    }

    public final void a() {
        if (DeviceScanManager.getInstance(this.f3268a).isScanningInProgress()) {
            DeviceScanManager.getInstance(this.f3268a).stopScan();
        }
        setBleService(null);
        unbindService();
        clearCommandQueue();
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.e;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.h;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
    }

    public static final void a(TouchELXBaseBleImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startBleService();
    }

    public static final void a(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        SleepResponse sleepResponse = new SleepResponse();
        sleepResponse.setComplete(true);
        bleBaseResponse.setResponseData(sleepResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(BleBaseRequest bleBaseRequest, DNDData dndData, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(dndData, "$dndData");
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(dndData);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(BleBaseRequest bleBaseRequest, GetLiftWristResponse liftWristResponse, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(liftWristResponse, "$liftWristResponse");
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(liftWristResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(BleBaseRequest bleBaseRequest, TouchELXBaseBleImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsResultListener settingsResultListener = (SettingsResultListener) bleBaseRequest.getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse(bleBaseRequest));
        this$0.setCompleteAndProcessNext(bleBaseRequest);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9 */
    public final TouchELXBaseReq a(BleBaseRequest bleBaseRequest) {
        List<SetEventReminderRequest> reminderRequestList;
        ?? r14;
        boolean z;
        ArrayList<ContactData> contactDataArrayList;
        List<SendWeatherModel> listSendWeatherModel;
        TouchELXBaseReq touchSetCallStatusReq;
        TouchELXBaseReq touchSetUnitReq;
        if (bleBaseRequest instanceof StepsDataRequest) {
            touchSetCallStatusReq = new TouchStepsReq();
            touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
        } else if (bleBaseRequest instanceof SleepDataRequest) {
            touchSetCallStatusReq = new TouchSleepReq();
            touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
        } else if (bleBaseRequest instanceof HeartRateDataRequest) {
            touchSetCallStatusReq = new TouchHeartRateReq();
            touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
        } else if (bleBaseRequest instanceof StressDataRequest) {
            touchSetCallStatusReq = new TouchStressReq();
            touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
        } else if (bleBaseRequest instanceof PeriodicSPO2BleRequest) {
            touchSetCallStatusReq = new TouchSpo2Req();
            touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
        } else if (bleBaseRequest instanceof ReadManualSpo2Request) {
            touchSetCallStatusReq = new TouchManualSpo2Req();
            touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
        } else if (bleBaseRequest instanceof ActivityModeWithSamplesRequest) {
            touchSetCallStatusReq = new TouchActivityDataReq();
            touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
        } else {
            char c = 5;
            int i = 2;
            int i2 = 0;
            if (bleBaseRequest instanceof SetFitnessInfoRequest) {
                TGProfile tGProfile = new TGProfile();
                SetFitnessInfoRequest setFitnessInfoRequest = (SetFitnessInfoRequest) bleBaseRequest;
                tGProfile.setHeight(setFitnessInfoRequest.getHeight());
                tGProfile.setWeight((int) setFitnessInfoRequest.getWeight());
                if (setFitnessInfoRequest.isMale()) {
                    tGProfile.setGender(1);
                } else {
                    tGProfile.setGender(0);
                }
                Calendar calendar = Calendar.getInstance();
                calendar.set(1, calendar.get(1) - setFitnessInfoRequest.getAge());
                calendar.set(2, 0);
                calendar.set(5, 1);
                tGProfile.setBirthday(calendar.getTime());
                touchSetUnitReq = new TouchUserInfoReq(tGProfile);
                touchSetUnitReq.setReqId(bleBaseRequest.getRequId());
            } else if (bleBaseRequest instanceof BatteryLevelRequest) {
                touchSetCallStatusReq = new TouchBatteryLevelReq();
                touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
            } else if (bleBaseRequest instanceof DeviceInfoRequest) {
                touchSetCallStatusReq = new TouchDeviceInfoReq();
                touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
            } else {
                char c2 = 4;
                char c3 = 6;
                if (bleBaseRequest instanceof SetVibrationAlarmListRequest) {
                    ArrayList arrayList = new ArrayList();
                    SetVibrationAlarmListRequest setVibrationAlarmListRequest = (SetVibrationAlarmListRequest) bleBaseRequest;
                    int size = setVibrationAlarmListRequest.getVibrationAlarmRequests().size();
                    int i3 = 0;
                    while (i3 < size) {
                        SetVibrationAlarmRequest setVibrationAlarmRequest = setVibrationAlarmListRequest.getVibrationAlarmRequests().get(i3);
                        boolean[] zArr = new boolean[7];
                        zArr[0] = setVibrationAlarmRequest.isMondayEnabled();
                        zArr[1] = setVibrationAlarmRequest.isTuesdayEnabled();
                        zArr[2] = setVibrationAlarmRequest.isWednesdayEnabled();
                        zArr[3] = setVibrationAlarmRequest.isThursdayEnabled();
                        zArr[c2] = setVibrationAlarmRequest.isFridayEnabled();
                        zArr[c] = setVibrationAlarmRequest.isSaturdayEnabled();
                        zArr[c3] = setVibrationAlarmRequest.isSundayEnabled();
                        TGAlarm tGAlarm = new TGAlarm();
                        tGAlarm.setId(setVibrationAlarmRequest.getAlarmId());
                        tGAlarm.setShow(true);
                        tGAlarm.setHour(setVibrationAlarmRequest.getHour());
                        tGAlarm.setMinute(setVibrationAlarmRequest.getMinute());
                        tGAlarm.setType(7);
                        int i4 = 1;
                        for (int i5 = 0; i5 < 7; i5++) {
                            if (zArr[i5]) {
                                i4 += 1 << (i5 + 1);
                            }
                        }
                        tGAlarm.setRepeat(setVibrationAlarmRequest.isEnabled() ? i4 | 1 : i4 & 254);
                        arrayList.add(tGAlarm);
                        i3++;
                        c = 5;
                        c2 = 4;
                        c3 = 6;
                    }
                    touchSetUnitReq = new TouchAlarmReq(arrayList);
                    touchSetUnitReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof GetAlarmDataRequest) {
                    touchSetCallStatusReq = new TouchGetAlarmReq();
                    touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof SetReminderRequest) {
                    SetReminderRequest setReminderRequest = (SetReminderRequest) bleBaseRequest;
                    if (setReminderRequest.getReminderType().equals(ReminderType.SEDENTARY_REMINDER)) {
                        boolean[] zArr2 = {setReminderRequest.isSundayEnabled(), setReminderRequest.isMondayEnabled(), setReminderRequest.isTuesdayEnabled(), setReminderRequest.isWednesdayEnabled(), setReminderRequest.isThursdayEnabled(), setReminderRequest.isFridayEnabled(), setReminderRequest.isSaturdayEnabled()};
                        TGSedentaryConfig tGSedentaryConfig = new TGSedentaryConfig();
                        tGSedentaryConfig.setStartHour(setReminderRequest.getStartHour1());
                        tGSedentaryConfig.setStartMinute(setReminderRequest.getStartMin1());
                        tGSedentaryConfig.setStopHour(setReminderRequest.getEndHour1());
                        tGSedentaryConfig.setStopMinute(setReminderRequest.getEndMin1());
                        tGSedentaryConfig.setInterval(setReminderRequest.getReminderInterval());
                        int i6 = 0;
                        while (i2 < 7) {
                            if (zArr2[i2]) {
                                i6 += this.s[i2];
                            }
                            i2++;
                        }
                        if (setReminderRequest.isEnabled()) {
                            i6++;
                        }
                        tGSedentaryConfig.setRepeat(i6);
                        touchSetCallStatusReq = new TouchSedentaryReminderReq(tGSedentaryConfig);
                        touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
                    } else {
                        if (setReminderRequest.getReminderType().equals(ReminderType.DRINK_WATER_REMINDER)) {
                            boolean[] zArr3 = {setReminderRequest.isSundayEnabled(), setReminderRequest.isMondayEnabled(), setReminderRequest.isTuesdayEnabled(), setReminderRequest.isWednesdayEnabled(), setReminderRequest.isThursdayEnabled(), setReminderRequest.isFridayEnabled(), setReminderRequest.isSaturdayEnabled()};
                            TGRemindDrinking tGRemindDrinking = new TGRemindDrinking();
                            tGRemindDrinking.setStartHour(setReminderRequest.getStartHour1());
                            tGRemindDrinking.setStartMinute(setReminderRequest.getStartMin1());
                            tGRemindDrinking.setStopHour(setReminderRequest.getEndHour1());
                            tGRemindDrinking.setStopMinute(setReminderRequest.getEndMin1());
                            tGRemindDrinking.setInterval(setReminderRequest.getReminderInterval());
                            int i7 = 0;
                            while (i2 < 7) {
                                if (zArr3[i2]) {
                                    i7 += this.s[i2];
                                }
                                i2++;
                            }
                            if (setReminderRequest.isEnabled()) {
                                i7++;
                            }
                            tGRemindDrinking.setRepeat(i7);
                            touchSetCallStatusReq = new TouchDrinkReminderReq(tGRemindDrinking);
                            touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
                        }
                        touchSetCallStatusReq = null;
                    }
                } else if (bleBaseRequest instanceof SetLiftWristRequest) {
                    TGRaiseWristConfig tGRaiseWristConfig = new TGRaiseWristConfig();
                    SetLiftWristRequest setLiftWristRequest = (SetLiftWristRequest) bleBaseRequest;
                    tGRaiseWristConfig.setOn(setLiftWristRequest.isLiftWristEnabled());
                    tGRaiseWristConfig.setStartHour(setLiftWristRequest.getStartHour());
                    tGRaiseWristConfig.setStartMinute(setLiftWristRequest.getStartMinute());
                    tGRaiseWristConfig.setStopHour(setLiftWristRequest.getEndHour());
                    tGRaiseWristConfig.setStopMinute(setLiftWristRequest.getEndMinute());
                    touchSetUnitReq = new TouchLiftWristReq(tGRaiseWristConfig);
                    touchSetUnitReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof GetLiftWristSettingsRequest) {
                    touchSetCallStatusReq = new TouchGetLiftWristReq();
                    touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof SetDNDModeRequest) {
                    TGNotDisturbConfig tGNotDisturbConfig = new TGNotDisturbConfig();
                    SetDNDModeRequest setDNDModeRequest = (SetDNDModeRequest) bleBaseRequest;
                    tGNotDisturbConfig.setOn(setDNDModeRequest.isDNDEnabled());
                    tGNotDisturbConfig.setStartHour(setDNDModeRequest.getStartHour());
                    tGNotDisturbConfig.setStartMinute(setDNDModeRequest.getStartMin());
                    tGNotDisturbConfig.setStopHour(setDNDModeRequest.getEndHour());
                    tGNotDisturbConfig.setStopMinute(setDNDModeRequest.getEndMin());
                    touchSetUnitReq = new TouchSetDNDReq(tGNotDisturbConfig);
                    touchSetUnitReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof GetDNDModeSettingsRequest) {
                    touchSetCallStatusReq = new TouchGetDNDReq();
                    touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof HeartRateTimeIntervalRequest) {
                    TGHeartRateMonitoringModeConfig tGHeartRateMonitoringModeConfig = new TGHeartRateMonitoringModeConfig();
                    HeartRateTimeIntervalRequest heartRateTimeIntervalRequest = (HeartRateTimeIntervalRequest) bleBaseRequest;
                    if (heartRateTimeIntervalRequest.getTimeInterval() != 0) {
                        tGHeartRateMonitoringModeConfig.setMode(136);
                    } else {
                        tGHeartRateMonitoringModeConfig.setMode(85);
                    }
                    tGHeartRateMonitoringModeConfig.setStartHour(heartRateTimeIntervalRequest.getStartHour());
                    tGHeartRateMonitoringModeConfig.setStartMinute(heartRateTimeIntervalRequest.getStartMinute());
                    tGHeartRateMonitoringModeConfig.setStopHour(heartRateTimeIntervalRequest.getEndHour());
                    tGHeartRateMonitoringModeConfig.setStopMinute(heartRateTimeIntervalRequest.getEndMinute());
                    tGHeartRateMonitoringModeConfig.setInterval(heartRateTimeIntervalRequest.getTimeInterval());
                    touchSetUnitReq = new TouchHeartRateIntervalReq(tGHeartRateMonitoringModeConfig);
                    touchSetUnitReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof StressTimeIntervalRequest) {
                    TGStressConfig tGStressConfig = new TGStressConfig();
                    if (((StressTimeIntervalRequest) bleBaseRequest).getTimeInterval() != 0) {
                        tGStressConfig.setMode(136);
                    } else {
                        tGStressConfig.setMode(0);
                    }
                    tGStressConfig.setStartHour(0);
                    tGStressConfig.setStartMinute(0);
                    tGStressConfig.setStopHour(23);
                    tGStressConfig.setStopMinute(59);
                    tGStressConfig.setInterval(10);
                    touchSetUnitReq = new TouchStressIntervalReq(tGStressConfig);
                    touchSetUnitReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof Spo2TimeIntervalRequest) {
                    TGSpo2Config tGSpo2Config = new TGSpo2Config();
                    if (((Spo2TimeIntervalRequest) bleBaseRequest).getTimeInterval() != 0) {
                        tGSpo2Config.setMode(136);
                    } else {
                        tGSpo2Config.setMode(0);
                    }
                    tGSpo2Config.setStartHour(0);
                    tGSpo2Config.setStartMinute(0);
                    tGSpo2Config.setStopHour(23);
                    tGSpo2Config.setStopMinute(59);
                    tGSpo2Config.setInterval(10);
                    touchSetUnitReq = new TouchSPO2IntervalReq(tGSpo2Config);
                    touchSetUnitReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof StepsTargetRequest) {
                    touchSetCallStatusReq = new TouchSetGoalReq(((StepsTargetRequest) bleBaseRequest).getTarget(), 0);
                    touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof SetCalorieTargetRequest) {
                    touchSetCallStatusReq = new TouchSetGoalReq(((SetCalorieTargetRequest) bleBaseRequest).getTarget$bleabstract_release(), 1);
                    touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof SetDistanceTargetRequest) {
                    touchSetCallStatusReq = new TouchSetGoalReq(((SetDistanceTargetRequest) bleBaseRequest).getTarget$bleabstract_release(), 2);
                    touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof SetExerciseMinutesTargetRequest) {
                    touchSetCallStatusReq = new TouchSetGoalReq(((SetExerciseMinutesTargetRequest) bleBaseRequest).getTarget$bleabstract_release(), 5);
                    touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof SetWalkHourTargetRequest) {
                    touchSetCallStatusReq = new TouchSetGoalReq(((SetWalkHourTargetRequest) bleBaseRequest).getTarget$bleabstract_release(), 6);
                    touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof SetDistanceUnitRequest) {
                    TGUnitConfig tGUnitConfig = new TGUnitConfig();
                    if (((SetDistanceUnitRequest) bleBaseRequest).isDistanceUnitinMile()) {
                        tGUnitConfig.setDistance(2);
                    } else {
                        tGUnitConfig.setDistance(1);
                    }
                    touchSetUnitReq = new TouchSetUnitReq(tGUnitConfig);
                    touchSetUnitReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof SetHourFormatRequest) {
                    TGUnitConfig tGUnitConfig2 = new TGUnitConfig();
                    if (((SetHourFormatRequest) bleBaseRequest).is12HourFormat()) {
                        tGUnitConfig2.setTimeMode(2);
                    } else {
                        tGUnitConfig2.setTimeMode(1);
                    }
                    touchSetUnitReq = new TouchSetUnitReq(tGUnitConfig2);
                    touchSetUnitReq.setReqId(bleBaseRequest.getRequId());
                } else if (bleBaseRequest instanceof SetWeatherConfigInfoRequest) {
                    TGUnitConfig tGUnitConfig3 = new TGUnitConfig();
                    SetWeatherConfigInfoRequest setWeatherConfigInfoRequest = (SetWeatherConfigInfoRequest) bleBaseRequest;
                    if (setWeatherConfigInfoRequest.getMeasurementUnitType() == MeasurementUnitType.METRIC) {
                        tGUnitConfig3.setTemp(1);
                    } else {
                        tGUnitConfig3.setTemp(2);
                    }
                    TouchSetWeatherUnitReq touchSetWeatherUnitReq = new TouchSetWeatherUnitReq(setWeatherConfigInfoRequest.isOn(), tGUnitConfig3);
                    touchSetWeatherUnitReq.setReqId(bleBaseRequest.getRequId());
                    return touchSetWeatherUnitReq;
                } else {
                    int i8 = 12;
                    String str = "";
                    if (bleBaseRequest instanceof SetMessageContentRequest) {
                        SetMessageContentRequest setMessageContentRequest = (SetMessageContentRequest) bleBaseRequest;
                        NotificationType appNotificationType = setMessageContentRequest.getAppNotificationType();
                        String str2 = setMessageContentRequest.title;
                        if (str2 != null) {
                            Intrinsics.checkNotNullExpressionValue(str2, "request.title");
                            if (str2.length() > 0) {
                                str = setMessageContentRequest.title;
                                Intrinsics.checkNotNullExpressionValue(str, "request.title");
                            }
                        }
                        TGMessage tGMessage = new TGMessage();
                        switch (appNotificationType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[appNotificationType.ordinal()]) {
                            case 1:
                                tGMessage.setType(1);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 2:
                                tGMessage.setType(8);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 3:
                                tGMessage.setType(3);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 4:
                                tGMessage.setType(6);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 5:
                                tGMessage.setType(10);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 6:
                                tGMessage.setType(7);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 7:
                                tGMessage.setType(57);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 8:
                                tGMessage.setType(11);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 9:
                                tGMessage.setType(13);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 10:
                                tGMessage.setType(23);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 11:
                                tGMessage.setType(128);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 12:
                                tGMessage.setType(9);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 13:
                                tGMessage.setType(36);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 14:
                                tGMessage.setType(34);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 15:
                                tGMessage.setType(21);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 16:
                                tGMessage.setType(20);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 17:
                                tGMessage.setType(22);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 18:
                                tGMessage.setType(12);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                            case 19:
                                tGMessage.setType(2);
                                tGMessage.setContent(setMessageContentRequest.message);
                                tGMessage.setName(str);
                                break;
                        }
                        if (appNotificationType == NotificationType.CALL) {
                            String str3 = setMessageContentRequest.message;
                            Intrinsics.checkNotNullExpressionValue(str3, "request.message");
                            String str4 = setMessageContentRequest.title;
                            Intrinsics.checkNotNullExpressionValue(str4, "request.title");
                            TouchSetCallNotificationReq touchSetCallNotificationReq = new TouchSetCallNotificationReq(str3, str4);
                            touchSetCallNotificationReq.setReqId(bleBaseRequest.getRequId());
                            return touchSetCallNotificationReq;
                        }
                        TouchSetMsgNotificationReq touchSetMsgNotificationReq = new TouchSetMsgNotificationReq(tGMessage);
                        touchSetMsgNotificationReq.setReqId(bleBaseRequest.getRequId());
                        return touchSetMsgNotificationReq;
                    }
                    if (bleBaseRequest instanceof SendCallStatusRequest) {
                        SendCallStatusRequest sendCallStatusRequest = (SendCallStatusRequest) bleBaseRequest;
                        if (sendCallStatusRequest.getCallStatus() != null) {
                            if (sendCallStatusRequest.getCallStatus() == CallStatusType.ACCEPT) {
                                TouchSetCallStatusReq touchSetCallStatusReq2 = new TouchSetCallStatusReq(1);
                                touchSetCallStatusReq2.setReqId(bleBaseRequest.getRequId());
                                return touchSetCallStatusReq2;
                            }
                            touchSetCallStatusReq = new TouchSetCallStatusReq(2);
                            touchSetCallStatusReq.setReqId(bleBaseRequest.getRequId());
                        }
                    } else if (bleBaseRequest instanceof SendWeatherRequest) {
                        SendWeatherRequest sendWeatherRequest = (SendWeatherRequest) bleBaseRequest;
                        Intrinsics.checkNotNull(sendWeatherRequest.getListSendWeatherModel());
                        if (!listSendWeatherModel.isEmpty()) {
                            TGWeather tGWeather = new TGWeather();
                            TouchELXUtils touchELXUtils = TouchELXUtils.INSTANCE;
                            String weatherText = sendWeatherRequest.getListSendWeatherModel().get(0).getWeatherText();
                            Intrinsics.checkNotNullExpressionValue(weatherText, "request.listSendWeatherModel[0].weatherText");
                            tGWeather.setType(touchELXUtils.getWeatherType(weatherText));
                            tGWeather.setCurrentTemp(sendWeatherRequest.getListSendWeatherModel().get(0).getTemp());
                            tGWeather.setMaxTemp(sendWeatherRequest.getListSendWeatherModel().get(0).getTempMax());
                            tGWeather.setMinTemp(sendWeatherRequest.getListSendWeatherModel().get(0).getTempMin());
                            tGWeather.setHumidity(sendWeatherRequest.getListSendWeatherModel().get(0).getHumidity());
                            tGWeather.setSunriseHour(sendWeatherRequest.getListSendWeatherModel().get(0).getSunRise() / 60);
                            tGWeather.setSunriseMinute(sendWeatherRequest.getListSendWeatherModel().get(0).getSunRise() % 60);
                            tGWeather.setSunsetHour(sendWeatherRequest.getListSendWeatherModel().get(0).getSunSet() / 60);
                            tGWeather.setSunsetMinute(sendWeatherRequest.getListSendWeatherModel().get(0).getSunSet() % 60);
                            if (sendWeatherRequest.getListSendWeatherModel().size() > 5) {
                                ArrayList arrayList2 = new ArrayList();
                                int size2 = sendWeatherRequest.getListSendWeatherModel().size();
                                for (int i9 = 1; i9 < size2; i9++) {
                                    TGWeather.FutureWeather futureWeather = new TGWeather.FutureWeather();
                                    TouchELXUtils touchELXUtils2 = TouchELXUtils.INSTANCE;
                                    String weatherText2 = sendWeatherRequest.getListSendWeatherModel().get(i9).getWeatherText();
                                    Intrinsics.checkNotNullExpressionValue(weatherText2, "request.listSendWeatherModel[i].weatherText");
                                    futureWeather.setType(touchELXUtils2.getWeatherType(weatherText2));
                                    futureWeather.setMaxTemp(sendWeatherRequest.getListSendWeatherModel().get(i9).getTempMax());
                                    futureWeather.setMinTemp(sendWeatherRequest.getListSendWeatherModel().get(i9).getTempMin());
                                    arrayList2.add(futureWeather);
                                }
                                tGWeather.setFutureWeatherList(arrayList2);
                            }
                            String placeName = sendWeatherRequest.getPlaceName();
                            Intrinsics.checkNotNull(placeName);
                            TouchSetWeatherConfigReq touchSetWeatherConfigReq = new TouchSetWeatherConfigReq(tGWeather, placeName);
                            touchSetWeatherConfigReq.setReqId(bleBaseRequest.getRequId());
                            return touchSetWeatherConfigReq;
                        }
                    } else if (bleBaseRequest instanceof SetWomenWellnessSettingsRequest) {
                        TGPhysiologicalCycle tGPhysiologicalCycle = new TGPhysiologicalCycle();
                        Calendar calendar2 = Calendar.getInstance();
                        SetWomenWellnessSettingsRequest setWomenWellnessSettingsRequest = (SetWomenWellnessSettingsRequest) bleBaseRequest;
                        calendar2.set(1, setWomenWellnessSettingsRequest.getMLastPeriodYear());
                        calendar2.set(2, setWomenWellnessSettingsRequest.getMLastPeriodMonth() - 1);
                        calendar2.set(5, setWomenWellnessSettingsRequest.getMLastPeriodDay());
                        tGPhysiologicalCycle.setEnable(setWomenWellnessSettingsRequest.getMEnabled());
                        tGPhysiologicalCycle.setLastDate(calendar2.getTime());
                        tGPhysiologicalCycle.setMenstrualCycleDays(setWomenWellnessSettingsRequest.getMMenstruationCycleLength());
                        tGPhysiologicalCycle.setMenstrualDuration(setWomenWellnessSettingsRequest.getMMenstruationPeriodLength());
                        tGPhysiologicalCycle.setRemindOvulation(setWomenWellnessSettingsRequest.getMOvulationReminderAdvance());
                        tGPhysiologicalCycle.setRemindMenstrual(setWomenWellnessSettingsRequest.getMPeriodReminderAdvance());
                        tGPhysiologicalCycle.setRemindHour(setWomenWellnessSettingsRequest.getMReminderHour());
                        tGPhysiologicalCycle.setRemindMinute(setWomenWellnessSettingsRequest.getMReminderMinute());
                        TouchFemaleWellnessConfigReq touchFemaleWellnessConfigReq = new TouchFemaleWellnessConfigReq(tGPhysiologicalCycle);
                        touchFemaleWellnessConfigReq.setReqId(bleBaseRequest.getRequId());
                        return touchFemaleWellnessConfigReq;
                    } else if (bleBaseRequest instanceof SetMusicMetaDataPlaybackVolumeRequest) {
                        TGMusicInfo tGMusicInfo = new TGMusicInfo();
                        SetMusicMetaDataPlaybackVolumeRequest setMusicMetaDataPlaybackVolumeRequest = (SetMusicMetaDataPlaybackVolumeRequest) bleBaseRequest;
                        tGMusicInfo.setMusicName(setMusicMetaDataPlaybackVolumeRequest.getTitle());
                        tGMusicInfo.setPlaying(setMusicMetaDataPlaybackVolumeRequest.getMusicControlState() == MusicControlState.PLAY);
                        tGMusicInfo.setMaxVol(setMusicMetaDataPlaybackVolumeRequest.getMaxVolume());
                        tGMusicInfo.setCurVol(setMusicMetaDataPlaybackVolumeRequest.getVolume());
                        TouchMusicMetaDataReq touchMusicMetaDataReq = new TouchMusicMetaDataReq(tGMusicInfo);
                        touchMusicMetaDataReq.setReqId(bleBaseRequest.getRequId());
                        return touchMusicMetaDataReq;
                    } else if (bleBaseRequest instanceof FindMyWatchRequest) {
                        TouchFindDeviceReq touchFindDeviceReq = new TouchFindDeviceReq(true, 10);
                        touchFindDeviceReq.setReqId(bleBaseRequest.getRequId());
                        return touchFindDeviceReq;
                    } else if (bleBaseRequest instanceof SetQuickReplyRequest) {
                        ArrayList arrayList3 = new ArrayList();
                        SetQuickReplyRequest setQuickReplyRequest = (SetQuickReplyRequest) bleBaseRequest;
                        List<BleQuickReplyModel> bleQuickReplyModelList = setQuickReplyRequest.getBleQuickReplyModelList();
                        Intrinsics.checkNotNull(bleQuickReplyModelList);
                        int size3 = bleQuickReplyModelList.size();
                        int i10 = 0;
                        while (i10 < size3) {
                            TGQuickReply tGQuickReply = new TGQuickReply();
                            tGQuickReply.setMsgType(1);
                            i10++;
                            tGQuickReply.setMsgIndex(i10);
                            tGQuickReply.setContent(setQuickReplyRequest.getBleQuickReplyModelList().get(i10).getReplyText());
                            arrayList3.add(tGQuickReply);
                        }
                        LogHelper.i("SetQuickReplyRequest", arrayList3.toString() + "");
                        TouchQuickReplyReq touchQuickReplyReq = new TouchQuickReplyReq(arrayList3);
                        touchQuickReplyReq.setReqId(bleBaseRequest.getRequId());
                        return touchQuickReplyReq;
                    } else if (bleBaseRequest instanceof SyncContactsRequest) {
                        SyncContactsRequest syncContactsRequest = (SyncContactsRequest) bleBaseRequest;
                        if (syncContactsRequest.getContactDataArrayList() != null) {
                            Intrinsics.checkNotNull(syncContactsRequest.getContactDataArrayList());
                            if (!contactDataArrayList.isEmpty()) {
                                ArrayList arrayList4 = new ArrayList();
                                Iterator<ContactData> it = syncContactsRequest.getContactDataArrayList().iterator();
                                while (it.hasNext()) {
                                    ContactData next = it.next();
                                    TGContacts tGContacts = new TGContacts();
                                    tGContacts.setName(next.getName());
                                    tGContacts.setPhoneNum(kotlin.text.m.replace$default(next.getMobileNumber(), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null));
                                    arrayList4.add(tGContacts);
                                }
                                TouchSyncContactsReq touchSyncContactsReq = new TouchSyncContactsReq(arrayList4);
                                touchSyncContactsReq.setReqId(bleBaseRequest.getRequId());
                                return touchSyncContactsReq;
                            }
                        }
                        TouchSyncContactsReq touchSyncContactsReq2 = new TouchSyncContactsReq(new ArrayList());
                        touchSyncContactsReq2.setReqId(bleBaseRequest.getRequId());
                        return touchSyncContactsReq2;
                    } else if (bleBaseRequest instanceof SetActivityListRequest) {
                        TGWorkoutMode tGWorkoutMode = new TGWorkoutMode();
                        ArrayList arrayList5 = new ArrayList();
                        SetActivityListRequest setActivityListRequest = (SetActivityListRequest) bleBaseRequest;
                        int size4 = setActivityListRequest.getActivityTypeModelList().size();
                        for (int i11 = 0; i11 < size4; i11++) {
                            arrayList5.add(Integer.valueOf(setActivityListRequest.getActivityTypeModelList().get(i11).getType()));
                        }
                        tGWorkoutMode.setWorkouts(CollectionsKt___CollectionsKt.sorted(arrayList5));
                        TouchSetWorkoutModesReq touchSetWorkoutModesReq = new TouchSetWorkoutModesReq(tGWorkoutMode);
                        touchSetWorkoutModesReq.setReqId(bleBaseRequest.getRequId());
                        return touchSetWorkoutModesReq;
                    } else if (bleBaseRequest instanceof GetActivityListRequest) {
                        TouchGetWorkoutModesReq touchGetWorkoutModesReq = new TouchGetWorkoutModesReq();
                        touchGetWorkoutModesReq.setReqId(bleBaseRequest.getRequId());
                        return touchGetWorkoutModesReq;
                    } else if (bleBaseRequest instanceof SetShortcutMenuListRequest) {
                        ArrayList arrayList6 = new ArrayList();
                        List sorted = CollectionsKt___CollectionsKt.sorted(((SetShortcutMenuListRequest) bleBaseRequest).getMenuList());
                        int size5 = sorted.size();
                        for (int i12 = 0; i12 < size5; i12++) {
                            TGQuickCard tGQuickCard = new TGQuickCard();
                            tGQuickCard.setId(((Number) sorted.get(i12)).intValue());
                            tGQuickCard.setVisible(true);
                            arrayList6.add(tGQuickCard);
                        }
                        TouchSetQuickCardsReq touchSetQuickCardsReq = new TouchSetQuickCardsReq(arrayList6);
                        touchSetQuickCardsReq.setReqId(bleBaseRequest.getRequId());
                        return touchSetQuickCardsReq;
                    } else if (bleBaseRequest instanceof GetSupportedShortcutMenuListRequest) {
                        TouchGetQuickCardReq touchGetQuickCardReq = new TouchGetQuickCardReq();
                        touchGetQuickCardReq.setReqId(bleBaseRequest.getRequId());
                        return touchGetQuickCardReq;
                    } else if (bleBaseRequest instanceof CameraControlRequest) {
                        TouchCameraControlReq touchCameraControlReq = new TouchCameraControlReq(((CameraControlRequest) bleBaseRequest).isEnabled());
                        touchCameraControlReq.setReqId(bleBaseRequest.getRequId());
                        return touchCameraControlReq;
                    } else if (bleBaseRequest instanceof ChangeWatchFaceRequest) {
                        TouchDefaultWatchFaceReq touchDefaultWatchFaceReq = new TouchDefaultWatchFaceReq(((ChangeWatchFaceRequest) bleBaseRequest).watchFacePosition);
                        touchDefaultWatchFaceReq.setReqId(bleBaseRequest.getRequId());
                        return touchDefaultWatchFaceReq;
                    } else if (bleBaseRequest instanceof CustomWatchFaceFileImageRequest) {
                        CustomWatchFaceFileImageRequest customWatchFaceFileImageRequest = (CustomWatchFaceFileImageRequest) bleBaseRequest;
                        int i13 = customWatchFaceFileImageRequest.watchFaceID;
                        String str5 = customWatchFaceFileImageRequest.watchFaceFilePath;
                        Intrinsics.checkNotNullExpressionValue(str5, "request.watchFaceFilePath");
                        TouchCloudWatchFaceReq touchCloudWatchFaceReq = new TouchCloudWatchFaceReq(i13, str5);
                        touchCloudWatchFaceReq.setReqId(bleBaseRequest.getRequId());
                        return touchCloudWatchFaceReq;
                    } else if (bleBaseRequest instanceof CustomWatchFaceBackgroundChangeRequest) {
                        CustomWatchFaceBackgroundChangeRequest customWatchFaceBackgroundChangeRequest = (CustomWatchFaceBackgroundChangeRequest) bleBaseRequest;
                        TGDial dial = new TGPhotoDialBuilder().setFilePath(customWatchFaceBackgroundChangeRequest.getWatchFaceFilePath()).setBackground(customWatchFaceBackgroundChangeRequest.getBitmap()).setColor(customWatchFaceBackgroundChangeRequest.getCustomWatchFaceLayoutChangeRequest().getTextColor()).setTimePosition(customWatchFaceBackgroundChangeRequest.getCustomWatchFaceLayoutChangeRequest().getTimePosition()).setTimeStyle(customWatchFaceBackgroundChangeRequest.getCustomWatchFaceLayoutChangeRequest().getStylePosition()).build();
                        Intrinsics.checkNotNullExpressionValue(dial, "dial");
                        TouchDIYWatchFaceReq touchDIYWatchFaceReq = new TouchDIYWatchFaceReq(dial);
                        touchDIYWatchFaceReq.setReqId(bleBaseRequest.getRequId());
                        return touchDIYWatchFaceReq;
                    } else if (bleBaseRequest instanceof SetEventReminderListRequest) {
                        SetEventReminderListRequest setEventReminderListRequest = (SetEventReminderListRequest) bleBaseRequest;
                        if (setEventReminderListRequest.getReminderRequestList() != null) {
                            Intrinsics.checkNotNull(setEventReminderListRequest.getReminderRequestList());
                            if (!reminderRequestList.isEmpty()) {
                                ArrayList arrayList7 = new ArrayList();
                                List<SetEventReminderRequest> reminderRequestList2 = setEventReminderListRequest.getReminderRequestList();
                                Intrinsics.checkNotNull(reminderRequestList2);
                                for (SetEventReminderRequest setEventReminderRequest : reminderRequestList2) {
                                    TGEventReminder tGEventReminder = new TGEventReminder();
                                    tGEventReminder.setId(setEventReminderRequest.getEventId());
                                    tGEventReminder.setContent(setEventReminderRequest.getEventName());
                                    tGEventReminder.setDay(setEventReminderRequest.getDay());
                                    tGEventReminder.setMonth(setEventReminderRequest.getMonth() - 1);
                                    tGEventReminder.setYear(setEventReminderRequest.getYear());
                                    tGEventReminder.setHour(setEventReminderRequest.getHour());
                                    tGEventReminder.setMinute(setEventReminderRequest.getMinute());
                                    tGEventReminder.setEnable(true);
                                    Calendar calendar3 = Calendar.getInstance();
                                    calendar3.set(1, setEventReminderRequest.getYear());
                                    calendar3.set(i, setEventReminderRequest.getMonth());
                                    calendar3.set(5, setEventReminderRequest.getDay());
                                    calendar3.set(11, setEventReminderRequest.getHour());
                                    calendar3.set(i8, setEventReminderRequest.getMinute());
                                    calendar3.set(13, 0);
                                    int repeatType = setEventReminderRequest.getRepeatType();
                                    if (repeatType == 0) {
                                        tGEventReminder.setEnable(true);
                                    } else if (repeatType == 1) {
                                        tGEventReminder.setWeekRepeat(new boolean[]{true, true, true, true, true, true, true});
                                    } else if (repeatType == i) {
                                        int i14 = calendar3.get(7);
                                        boolean[] zArr4 = new boolean[7];
                                        if (i14 == i) {
                                            z = true;
                                            r14 = 0;
                                        } else {
                                            r14 = 0;
                                            z = false;
                                        }
                                        zArr4[r14] = z;
                                        zArr4[1] = i14 == 3 ? true : r14;
                                        zArr4[i] = i14 == 4 ? true : r14;
                                        zArr4[3] = i14 == 5 ? true : r14;
                                        zArr4[4] = i14 == 6 ? true : r14;
                                        zArr4[5] = i14 == 7 ? true : r14;
                                        zArr4[6] = i14 == 1 ? true : r14;
                                        tGEventReminder.setWeekRepeat(zArr4);
                                    } else if (repeatType == 3) {
                                        tGEventReminder.setMonthRepeat(true);
                                    } else if (repeatType == 4) {
                                        tGEventReminder.setYearRepeat(true);
                                    }
                                    arrayList7.add(tGEventReminder);
                                    i8 = 12;
                                    i = 2;
                                }
                                TouchEventReminderReq touchEventReminderReq = new TouchEventReminderReq(arrayList7);
                                touchEventReminderReq.setReqId(bleBaseRequest.getRequId());
                                return touchEventReminderReq;
                            }
                        }
                        TouchEventReminderReq touchEventReminderReq2 = new TouchEventReminderReq(new ArrayList());
                        touchEventReminderReq2.setReqId(bleBaseRequest.getRequId());
                        return touchEventReminderReq2;
                    } else if (bleBaseRequest instanceof GetEventReminderRequest) {
                        TouchGetEventReminderReq touchGetEventReminderReq = new TouchGetEventReminderReq();
                        touchGetEventReminderReq.setReqId(bleBaseRequest.getRequId());
                        return touchGetEventReminderReq;
                    }
                    touchSetCallStatusReq = null;
                }
            }
            touchSetCallStatusReq = touchSetUnitReq;
        }
        return touchSetCallStatusReq;
    }
}
