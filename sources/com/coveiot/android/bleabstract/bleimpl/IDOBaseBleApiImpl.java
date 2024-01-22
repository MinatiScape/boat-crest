package com.coveiot.android.bleabstract.bleimpl;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.formatter.ido.IDOActivityFormatter;
import com.coveiot.android.bleabstract.formatter.ido.IDOAmbientSoundLevelFormatter;
import com.coveiot.android.bleabstract.formatter.ido.IDOHeartRateFormatter;
import com.coveiot.android.bleabstract.formatter.ido.IDOSPO2Formatter;
import com.coveiot.android.bleabstract.formatter.ido.IDOSleepFormatter;
import com.coveiot.android.bleabstract.formatter.ido.IDOStepsFormatter;
import com.coveiot.android.bleabstract.formatter.ido.IDOStressFormatter;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ActivityTypeModel;
import com.coveiot.android.bleabstract.models.ActivityTypes;
import com.coveiot.android.bleabstract.models.Alarm;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DNDData;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.android.bleabstract.models.WatchFace;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.AmbientSoundResponse;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.CallRejectRes;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.bleabstract.response.GetActivityListResponse;
import com.coveiot.android.bleabstract.response.GetCalorieDistanceGoalResponse;
import com.coveiot.android.bleabstract.response.GetLiftWristResponse;
import com.coveiot.android.bleabstract.response.GetSupportedShortcutMenuListResponse;
import com.coveiot.android.bleabstract.response.GetWatchFaceListResponse;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.LiveAGPSUploadPercentage;
import com.coveiot.android.bleabstract.response.LiveECGDataResponse;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveMusicControlRes;
import com.coveiot.android.bleabstract.response.LiveSportData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.Spo2Response;
import com.coveiot.android.bleabstract.response.Spo2WaveResponse;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.utils.idoUtils.IDOUtils;
import com.coveiot.android.idoSdk.IDOBleCommandName;
import com.coveiot.android.idoSdk.IDOResponseListener;
import com.coveiot.android.idoSdk.api.IDOAmbientSoundDataReq;
import com.coveiot.android.idoSdk.api.IDOAmbientSoundLevelControlReq;
import com.coveiot.android.idoSdk.api.IDOBTCallControlReq;
import com.coveiot.android.idoSdk.api.IDOBaseReq;
import com.coveiot.android.idoSdk.api.IDOBaseRes;
import com.coveiot.android.idoSdk.api.IDOBatteryLevelReq;
import com.coveiot.android.idoSdk.api.IDOCameraRequest;
import com.coveiot.android.idoSdk.api.IDOChangeWatchFaceReq;
import com.coveiot.android.idoSdk.api.IDOCloudWatchFaceReq;
import com.coveiot.android.idoSdk.api.IDODIYWatchFaceReq;
import com.coveiot.android.idoSdk.api.IDODeleteWatchFaceReq;
import com.coveiot.android.idoSdk.api.IDODeviceInfoReq;
import com.coveiot.android.idoSdk.api.IDODistanceUnitReq;
import com.coveiot.android.idoSdk.api.IDODrinkReminderReq;
import com.coveiot.android.idoSdk.api.IDOFrequentContactsReq;
import com.coveiot.android.idoSdk.api.IDOGetActivityListData;
import com.coveiot.android.idoSdk.api.IDOGetAlarmListData;
import com.coveiot.android.idoSdk.api.IDOGetCalorieDistanceGoal;
import com.coveiot.android.idoSdk.api.IDOGetDNDConfig;
import com.coveiot.android.idoSdk.api.IDOGetLiftWristConfig;
import com.coveiot.android.idoSdk.api.IDOGetShortcutMenuListData;
import com.coveiot.android.idoSdk.api.IDOGetWatchFaceListData;
import com.coveiot.android.idoSdk.api.IDOHRIntervalReq;
import com.coveiot.android.idoSdk.api.IDOHeartRateDataReq;
import com.coveiot.android.idoSdk.api.IDOHourFormatReq;
import com.coveiot.android.idoSdk.api.IDOLiftWristReq;
import com.coveiot.android.idoSdk.api.IDONotificationReq;
import com.coveiot.android.idoSdk.api.IDORebootDeviceReq;
import com.coveiot.android.idoSdk.api.IDOSPO2DataReq;
import com.coveiot.android.idoSdk.api.IDOSedentaryReminderReq;
import com.coveiot.android.idoSdk.api.IDOSetActivityListReq;
import com.coveiot.android.idoSdk.api.IDOSetActivityRecognitionReq;
import com.coveiot.android.idoSdk.api.IDOSetCalorieAndDistanceGoalReq;
import com.coveiot.android.idoSdk.api.IDOSetDNDReq;
import com.coveiot.android.idoSdk.api.IDOSetGoalReq;
import com.coveiot.android.idoSdk.api.IDOSetMenuListReq;
import com.coveiot.android.idoSdk.api.IDOSetMusicMetaDataReq;
import com.coveiot.android.idoSdk.api.IDOSetMusicVolumeControlReq;
import com.coveiot.android.idoSdk.api.IDOSetUserInfoReq;
import com.coveiot.android.idoSdk.api.IDOSetWorldClockReq;
import com.coveiot.android.idoSdk.api.IDOSleepDataReq;
import com.coveiot.android.idoSdk.api.IDOSportsDataReq;
import com.coveiot.android.idoSdk.api.IDOStepsDataReq;
import com.coveiot.android.idoSdk.api.IDOStopNotificationReq;
import com.coveiot.android.idoSdk.api.IDOStressDataReq;
import com.coveiot.android.idoSdk.api.IDOStressIntervalReq;
import com.coveiot.android.idoSdk.api.IDOVibrationAlarmReq;
import com.coveiot.android.idoSdk.api.IDOWeatherReq;
import com.coveiot.android.idoSdk.api.IDOWeatherUnitReq;
import com.coveiot.android.idoSdk.api.IDOWomenWellnessReq;
import com.coveiot.android.idoSdk.error.IDOError;
import com.coveiot.android.idoSdk.error.IDOErrorType;
import com.coveiot.khidodb.activities.EntityHealthActivityV3;
import com.coveiot.khidodb.activities.EntityHealthSwimV3;
import com.coveiot.khidodb.activities.KHIDOActivityRepository;
import com.coveiot.khidodb.heartrate.EntityHealthHeartRateSecond;
import com.coveiot.khidodb.heartrate.KHIDOHeartRateRepository;
import com.coveiot.khidodb.noise.EntityHealthNoise;
import com.coveiot.khidodb.noise.KHIDONoiseRepository;
import com.coveiot.khidodb.sleep.EntityHealthSleepV3;
import com.coveiot.khidodb.sleep.KHIDOSleepRepository;
import com.coveiot.khidodb.spo2.EntityHealthSpo2;
import com.coveiot.khidodb.spo2.KHIDOSpo2Repository;
import com.coveiot.khidodb.stress.EntityHealthPressure;
import com.coveiot.khidodb.stress.KHIDOStressRepository;
import com.coveiot.khidodb.walk.EntityHealthSportV3;
import com.coveiot.khidodb.walk.KHIDOStepsRepository;
import com.coveiot.khidodb.walk.model.KHHealthSportV3Item;
import com.coveiot.sdk.ble.api.model.CameraState;
import com.coveiot.sdk.ble.api.model.FindMyPhoneState;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.sdk.ble.api.response.FindMyPhoneRes;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.scan.AssociationResult;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.business.sync.SyncPara;
import com.ido.ble.callback.AppSendDataCallBack;
import com.ido.ble.callback.BindCallBack;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.DeviceControlAppCallBack;
import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.callback.GetDeviceParaCallBack;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.callback.PhoneMsgNoticeCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.callback.SyncV3CallBack;
import com.ido.ble.callback.UnbindCallBack;
import com.ido.ble.data.manage.database.HealthActivityV3;
import com.ido.ble.data.manage.database.HealthBloodPressureV3;
import com.ido.ble.data.manage.database.HealthBodyPower;
import com.ido.ble.data.manage.database.HealthGpsV3;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthNoise;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthRespiratoryRate;
import com.ido.ble.data.manage.database.HealthSleepV3;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSportV3;
import com.ido.ble.data.manage.database.HealthSwimming;
import com.ido.ble.data.manage.database.HealthTemperature;
import com.ido.ble.file.transfer.FileTransferConfig;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.protocol.model.ActivityDataCount;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.AlarmV3;
import com.ido.ble.protocol.model.AllHealthMonitorSwitch;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.BatteryInfo;
import com.ido.ble.protocol.model.BtA2dpHfpStatus;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.CanDownLangInfo;
import com.ido.ble.protocol.model.CanDownLangInfoV3;
import com.ido.ble.protocol.model.DeviceSummarySoftVersionInfo;
import com.ido.ble.protocol.model.DeviceUpgradeState;
import com.ido.ble.protocol.model.FirmwareAndBt3Version;
import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.ble.protocol.model.HIDInfo;
import com.ido.ble.protocol.model.LiveData;
import com.ido.ble.protocol.model.MacAddressInfo;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.NoticeSwitchInfo;
import com.ido.ble.protocol.model.PressCalibrationValue;
import com.ido.ble.protocol.model.SNInfo;
import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.Sport100TypeSort;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.SupportSportInfoV3;
import com.ido.ble.protocol.model.SystemTime;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.ble.protocol.model.WallpaperFileCreateConfig;
import com.ido.ble.watch.custom.WatchPlateSetConfig;
import com.ido.ble.watch.custom.callback.WatchPlateCallBack;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.ble.watch.custom.model.WatchPlateFileInfo;
import com.ido.ble.watch.custom.model.WatchPlateScreenInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class IDOBaseBleApiImpl implements BleApi, IDOResponseListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final SyncV3CallBack.ICallBack A;
    @NotNull
    public final DeviceControlAppCallBack.ICallBack B;
    @NotNull
    public final AppSendDataCallBack.ICallBack C;
    @NotNull
    public final WatchPlateCallBack.IOperateCallBack D;
    @NotNull
    public final GetDeviceParaCallBack.ICallBack E;
    @NotNull
    public final OtherProtocolCallBack.ICallBack F;
    @NotNull
    public final OperateCallBack.ICallBack G;
    @NotNull
    public final Runnable H;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2962a;
    public final String b;
    @NotNull
    public final BluetoothAdapter c;
    @Nullable
    public ConnectionResultListener d;
    @Nullable
    public MutableLiveData<ConnectionStatus> e;
    @NotNull
    public final LinkedList<QueueObject> f;
    @NotNull
    public ConnectionStatus g;
    @Nullable
    public DeviceInfoData h;
    @NotNull
    public final Handler i;
    @NotNull
    public final MutableLiveData<LiveHealthData> j;
    @NotNull
    public final MutableLiveData<LiveStepsData> k;
    @NotNull
    public final MutableLiveData<LiveTemperatureData> l;
    @NotNull
    public final MutableLiveData<LiveWatchFaceUploadPercentage> m;
    @NotNull
    public final MutableLiveData<LiveAGPSUploadPercentage> n;
    @NotNull
    public final Handler o;
    @Nullable
    public BleBaseRequest p;
    @NotNull
    public final Handler q;
    public boolean r;
    @Nullable
    public DeviceSupportedFeatures s;
    @NotNull
    public ConnectCallBack.ICallBack t;
    @NotNull
    public ConnectCallBack.ICallBack u;
    @NotNull
    public BindCallBack.ICallBack v;
    @NotNull
    public UnbindCallBack.ICallBack w;
    @NotNull
    public SettingCallBack.ICallBack x;
    @NotNull
    public GetDeviceInfoCallBack.ICallBack y;
    @NotNull
    public final IDOBaseBleApiImpl$iCallBack$1 z;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<IDOBaseBleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, IDOBaseBleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f2963a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, IDOBaseBleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public IDOBaseBleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new IDOBaseBleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f2963a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            int[] iArr = new int[DeviceControlAppCallBack.DeviceControlEventType.values().length];
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.PREVIOUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.NEXT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.TAKE_ONE_PHOTO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.TAKE_MULTI_PHOTO.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.VOLUME_UP.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.VOLUME_DOWN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.OPEN_CAMERA.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.CLOSE_CAMERA.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.ANSWER_PHONE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[DeviceControlAppCallBack.DeviceControlEventType.REJECT_PHONE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[IDOErrorType.values().length];
            try {
                iArr2[IDOErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr2[IDOErrorType.COMMAND_TIME_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            $EnumSwitchMapping$1 = iArr2;
            NotificationType.values();
            int[] iArr3 = new int[65];
            try {
                NotificationType notificationType = NotificationType.CALL;
                iArr3[0] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                NotificationType notificationType2 = NotificationType.SMS;
                iArr3[2] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                NotificationType notificationType3 = NotificationType.WHATSAPP;
                iArr3[4] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                NotificationType notificationType4 = NotificationType.WECHAT;
                iArr3[5] = 4;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                NotificationType notificationType5 = NotificationType.FACEBOOK;
                iArr3[6] = 5;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                NotificationType notificationType6 = NotificationType.INSTAGRAM;
                iArr3[7] = 6;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                NotificationType notificationType7 = NotificationType.TWITTER;
                iArr3[8] = 7;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                NotificationType notificationType8 = NotificationType.EMAIL;
                iArr3[3] = 8;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                NotificationType notificationType9 = NotificationType.MISSED_CALL;
                iArr3[20] = 9;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                NotificationType notificationType10 = NotificationType.LINKEDIN;
                iArr3[15] = 10;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                NotificationType notificationType11 = NotificationType.SKYPE;
                iArr3[14] = 11;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                NotificationType notificationType12 = NotificationType.TELEGRAM;
                iArr3[17] = 12;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                NotificationType notificationType13 = NotificationType.OTHER_APPS;
                iArr3[18] = 13;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                NotificationType notificationType14 = NotificationType.MESSENGER;
                iArr3[9] = 14;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                NotificationType notificationType15 = NotificationType.YOUTUBE;
                iArr3[27] = 15;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                NotificationType notificationType16 = NotificationType.YAHOO_EMAIL;
                iArr3[25] = 16;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                NotificationType notificationType17 = NotificationType.OUTLOOK;
                iArr3[24] = 17;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                NotificationType notificationType18 = NotificationType.WHATSAPP_BUSINESS;
                iArr3[23] = 18;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                NotificationType notificationType19 = NotificationType.GMAIL;
                iArr3[26] = 19;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                NotificationType notificationType20 = NotificationType.SNAPCHAT;
                iArr3[12] = 20;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                NotificationType notificationType21 = NotificationType.CALENDAR;
                iArr3[1] = 21;
            } catch (NoSuchFieldError unused36) {
            }
            $EnumSwitchMapping$2 = iArr3;
            ActivityTypes.values();
            int[] iArr4 = new int[6];
            try {
                iArr4[0] = 1;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                iArr4[1] = 2;
            } catch (NoSuchFieldError unused38) {
            }
            $EnumSwitchMapping$3 = iArr4;
        }
    }

    /* JADX WARN: Type inference failed for: r3v10, types: [com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$iCallBack$1] */
    public IDOBaseBleApiImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2962a = context;
        this.b = IDOBaseBleApiImpl.class.getSimpleName();
        this.f = new LinkedList<>();
        this.g = ConnectionStatus.DISCONNECTED;
        this.i = new Handler();
        this.j = new MutableLiveData<>();
        this.k = new MutableLiveData<>();
        this.l = new MutableLiveData<>();
        this.m = new MutableLiveData<>();
        this.n = new MutableLiveData<>();
        this.o = new Handler(Looper.getMainLooper());
        this.q = new Handler();
        BLEManager.init();
        Object systemService = context.getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothAdapter adapter = ((BluetoothManager) systemService).getAdapter();
        Intrinsics.checkNotNullExpressionValue(adapter, "context.getSystemService…BluetoothManager).adapter");
        this.c = adapter;
        this.t = new ConnectCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$connectCallBack$1
            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectBreak(@NotNull String p0) {
                ConnectionResultListener connectionResultListener;
                MutableLiveData mutableLiveData;
                ConnectionResultListener connectionResultListener2;
                ConnectCallBack.ICallBack iCallBack;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                ConnectionStatus connectionStatus;
                ConnectionStatus connectionStatus2;
                Intrinsics.checkNotNullParameter(p0, "p0");
                IDOBaseBleApiImpl.this.r = false;
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "connection break");
                IDOBaseBleApiImpl.this.a();
                IDOBaseBleApiImpl iDOBaseBleApiImpl = IDOBaseBleApiImpl.this;
                ConnectionStatus connectionStatus3 = ConnectionStatus.DISCONNECTED;
                iDOBaseBleApiImpl.g = connectionStatus3;
                connectionResultListener = IDOBaseBleApiImpl.this.d;
                if (connectionResultListener != null) {
                    connectionResultListener.onConnectionResponse(connectionStatus3);
                }
                mutableLiveData = IDOBaseBleApiImpl.this.e;
                if (mutableLiveData != null) {
                    mutableLiveData2 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData2 != null) {
                        connectionStatus2 = IDOBaseBleApiImpl.this.g;
                        mutableLiveData2.setValue(connectionStatus2);
                    }
                    mutableLiveData3 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData3 != null) {
                        connectionStatus = IDOBaseBleApiImpl.this.g;
                        mutableLiveData3.postValue(connectionStatus);
                    }
                }
                connectionResultListener2 = IDOBaseBleApiImpl.this.d;
                if (connectionResultListener2 != null) {
                    connectionResultListener2.onConnectionResponse(IDOBaseBleApiImpl.this.getConnectionStatus());
                }
                if (Intrinsics.areEqual(PreferenceManagerAbstract.getInstance(IDOBaseBleApiImpl.this.getContext()).getConnectionType(), ConnectionType.RECONNECT_ON_DISCONNECT.name())) {
                    iCallBack = IDOBaseBleApiImpl.this.u;
                    BLEManager.registerConnectCallBack(iCallBack);
                    if (BLEManager.isBind() && !BLEManager.isConnected()) {
                        BLEManager.autoConnect();
                    }
                }
                IDOBaseBleApiImpl.this.sendErrorAndClearQueue(new IDOError(IDOErrorType.DEVICE_NOT_CONNECTED, "Device disconnected"));
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectFailed(@Nullable ConnectFailedReason connectFailedReason, @Nullable String str) {
                MutableLiveData mutableLiveData;
                ConnectionResultListener connectionResultListener;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                ConnectionStatus connectionStatus;
                ConnectionStatus connectionStatus2;
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "connection failed");
                IDOBaseBleApiImpl.this.r = false;
                IDOBaseBleApiImpl.this.g = ConnectionStatus.DISCONNECTED;
                mutableLiveData = IDOBaseBleApiImpl.this.e;
                if (mutableLiveData != null) {
                    mutableLiveData2 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData2 != null) {
                        connectionStatus2 = IDOBaseBleApiImpl.this.g;
                        mutableLiveData2.setValue(connectionStatus2);
                    }
                    mutableLiveData3 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData3 != null) {
                        connectionStatus = IDOBaseBleApiImpl.this.g;
                        mutableLiveData3.postValue(connectionStatus);
                    }
                }
                connectionResultListener = IDOBaseBleApiImpl.this.d;
                if (connectionResultListener != null) {
                    connectionResultListener.onConnectionResponse(IDOBaseBleApiImpl.this.getConnectionStatus());
                }
                IDOBaseBleApiImpl.this.sendErrorAndClearQueue(new IDOError(IDOErrorType.DEVICE_NOT_CONNECTED, "Device disconnected"));
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectStart(@NotNull String p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "connection started");
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectSuccess(@NotNull String p0) {
                MutableLiveData mutableLiveData;
                ConnectionResultListener connectionResultListener;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                ConnectionStatus connectionStatus;
                ConnectionStatus connectionStatus2;
                BindCallBack.ICallBack iCallBack;
                Intrinsics.checkNotNullParameter(p0, "p0");
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "connected success");
                IDOBaseBleApiImpl.access$registerCallbacks(IDOBaseBleApiImpl.this);
                IDOBaseBleApiImpl.access$setTime(IDOBaseBleApiImpl.this);
                if (!BLEManager.isBind()) {
                    iCallBack = IDOBaseBleApiImpl.this.v;
                    BLEManager.registerBindCallBack(iCallBack);
                    BLEManager.bind();
                } else {
                    IDOBaseBleApiImpl.this.g = ConnectionStatus.CONNECTED;
                    mutableLiveData = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData != null) {
                        mutableLiveData2 = IDOBaseBleApiImpl.this.e;
                        if (mutableLiveData2 != null) {
                            connectionStatus2 = IDOBaseBleApiImpl.this.g;
                            mutableLiveData2.setValue(connectionStatus2);
                        }
                        mutableLiveData3 = IDOBaseBleApiImpl.this.e;
                        if (mutableLiveData3 != null) {
                            connectionStatus = IDOBaseBleApiImpl.this.g;
                            mutableLiveData3.postValue(connectionStatus);
                        }
                    }
                    connectionResultListener = IDOBaseBleApiImpl.this.d;
                    if (connectionResultListener != null) {
                        connectionResultListener.onConnectionResponse(IDOBaseBleApiImpl.this.getConnectionStatus());
                    }
                }
                SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
                if (supportFunctionInfo == null) {
                    BLEManager.getFunctionTables();
                }
                BLEManager.setMusicSwitch(true);
                BLEManager.setWeatherSwitch(true);
                BLEManager.setFindPhoneSwitch(true);
                IDOBaseBleApiImpl iDOBaseBleApiImpl = IDOBaseBleApiImpl.this;
                if (!(iDOBaseBleApiImpl instanceof IDOConnectBleApiImpl) || supportFunctionInfo == null) {
                    return;
                }
                LogHelper.i(iDOBaseBleApiImpl.getTAG(), "IDOConnectBleApiImpl Sleep");
                supportFunctionInfo.V3_support_set_scientific_sleep_switch = true;
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnecting(@NotNull String p0) {
                MutableLiveData mutableLiveData;
                ConnectionResultListener connectionResultListener;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                ConnectionStatus connectionStatus;
                ConnectionStatus connectionStatus2;
                Intrinsics.checkNotNullParameter(p0, "p0");
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "connecting");
                IDOBaseBleApiImpl.this.g = ConnectionStatus.CONNECTING;
                mutableLiveData = IDOBaseBleApiImpl.this.e;
                if (mutableLiveData != null) {
                    mutableLiveData2 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData2 != null) {
                        connectionStatus2 = IDOBaseBleApiImpl.this.g;
                        mutableLiveData2.setValue(connectionStatus2);
                    }
                    mutableLiveData3 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData3 != null) {
                        connectionStatus = IDOBaseBleApiImpl.this.g;
                        mutableLiveData3.postValue(connectionStatus);
                    }
                }
                connectionResultListener = IDOBaseBleApiImpl.this.d;
                if (connectionResultListener != null) {
                    connectionResultListener.onConnectionResponse(IDOBaseBleApiImpl.this.getConnectionStatus());
                }
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onDeviceInNotBindStatus(@Nullable String str) {
                UnbindCallBack.ICallBack iCallBack;
                UnbindCallBack.ICallBack iCallBack2;
                String tag = IDOBaseBleApiImpl.this.getTAG();
                LogHelper.i(tag, "onDeviceInNotBindStatus" + str);
                iCallBack = IDOBaseBleApiImpl.this.w;
                BLEManager.unregisterUnbindCallBack(iCallBack);
                iCallBack2 = IDOBaseBleApiImpl.this.w;
                BLEManager.registerUnbindCallBack(iCallBack2);
                BLEManager.unbind();
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onInDfuMode(@Nullable BLEDevice bLEDevice) {
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "dfumode");
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onInitCompleted(@NotNull String p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                String tag = IDOBaseBleApiImpl.this.getTAG();
                LogHelper.i(tag, com.ido.ble.event.stat.one.d.m + p0);
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onRetry(int i, @NotNull String p1) {
                MutableLiveData mutableLiveData;
                ConnectionResultListener connectionResultListener;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                ConnectionStatus connectionStatus;
                ConnectionStatus connectionStatus2;
                Intrinsics.checkNotNullParameter(p1, "p1");
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "retry");
                IDOBaseBleApiImpl.this.g = ConnectionStatus.CONNECTING;
                mutableLiveData = IDOBaseBleApiImpl.this.e;
                if (mutableLiveData != null) {
                    mutableLiveData2 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData2 != null) {
                        connectionStatus2 = IDOBaseBleApiImpl.this.g;
                        mutableLiveData2.setValue(connectionStatus2);
                    }
                    mutableLiveData3 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData3 != null) {
                        connectionStatus = IDOBaseBleApiImpl.this.g;
                        mutableLiveData3.postValue(connectionStatus);
                    }
                }
                connectionResultListener = IDOBaseBleApiImpl.this.d;
                if (connectionResultListener != null) {
                    connectionResultListener.onConnectionResponse(IDOBaseBleApiImpl.this.getConnectionStatus());
                }
            }
        };
        this.u = new ConnectCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$autoconnectCallBack$1
            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectBreak(@NotNull String p0) {
                MutableLiveData mutableLiveData;
                ConnectionResultListener connectionResultListener;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                ConnectionStatus connectionStatus;
                ConnectionStatus connectionStatus2;
                Intrinsics.checkNotNullParameter(p0, "p0");
                IDOBaseBleApiImpl.this.r = false;
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "autoconnection break");
                IDOBaseBleApiImpl.this.a();
                IDOBaseBleApiImpl.this.g = ConnectionStatus.DISCONNECTED;
                mutableLiveData = IDOBaseBleApiImpl.this.e;
                if (mutableLiveData != null) {
                    mutableLiveData2 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData2 != null) {
                        connectionStatus2 = IDOBaseBleApiImpl.this.g;
                        mutableLiveData2.setValue(connectionStatus2);
                    }
                    mutableLiveData3 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData3 != null) {
                        connectionStatus = IDOBaseBleApiImpl.this.g;
                        mutableLiveData3.postValue(connectionStatus);
                    }
                }
                connectionResultListener = IDOBaseBleApiImpl.this.d;
                if (connectionResultListener != null) {
                    connectionResultListener.onConnectionResponse(IDOBaseBleApiImpl.this.getConnectionStatus());
                }
                if (Intrinsics.areEqual(PreferenceManagerAbstract.getInstance(IDOBaseBleApiImpl.this.getContext()).getConnectionType(), ConnectionType.RECONNECT_ON_DISCONNECT.name()) && BLEManager.isBind() && !BLEManager.isConnected()) {
                    BLEManager.autoConnect();
                }
                IDOBaseBleApiImpl.this.sendErrorAndClearQueue(new IDOError(IDOErrorType.DEVICE_NOT_CONNECTED, "Device disconnected"));
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectFailed(@Nullable ConnectFailedReason connectFailedReason, @Nullable String str) {
                MutableLiveData mutableLiveData;
                ConnectionResultListener connectionResultListener;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                ConnectionStatus connectionStatus;
                ConnectionStatus connectionStatus2;
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "autoconnection failed");
                IDOBaseBleApiImpl.this.r = false;
                IDOBaseBleApiImpl.this.g = ConnectionStatus.DISCONNECTED;
                mutableLiveData = IDOBaseBleApiImpl.this.e;
                if (mutableLiveData != null) {
                    mutableLiveData2 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData2 != null) {
                        connectionStatus2 = IDOBaseBleApiImpl.this.g;
                        mutableLiveData2.setValue(connectionStatus2);
                    }
                    mutableLiveData3 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData3 != null) {
                        connectionStatus = IDOBaseBleApiImpl.this.g;
                        mutableLiveData3.postValue(connectionStatus);
                    }
                }
                connectionResultListener = IDOBaseBleApiImpl.this.d;
                if (connectionResultListener != null) {
                    connectionResultListener.onConnectionResponse(IDOBaseBleApiImpl.this.getConnectionStatus());
                }
                IDOBaseBleApiImpl.this.sendErrorAndClearQueue(new IDOError(IDOErrorType.DEVICE_NOT_CONNECTED, "Device disconnected"));
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectStart(@NotNull String p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "autoconnection started");
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectSuccess(@NotNull String p0) {
                MutableLiveData mutableLiveData;
                ConnectionResultListener connectionResultListener;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                ConnectionStatus connectionStatus;
                ConnectionStatus connectionStatus2;
                Intrinsics.checkNotNullParameter(p0, "p0");
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "autoconnected success");
                IDOBaseBleApiImpl.access$registerCallbacks(IDOBaseBleApiImpl.this);
                IDOBaseBleApiImpl.this.g = ConnectionStatus.CONNECTED;
                mutableLiveData = IDOBaseBleApiImpl.this.e;
                if (mutableLiveData != null) {
                    mutableLiveData2 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData2 != null) {
                        connectionStatus2 = IDOBaseBleApiImpl.this.g;
                        mutableLiveData2.setValue(connectionStatus2);
                    }
                    mutableLiveData3 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData3 != null) {
                        connectionStatus = IDOBaseBleApiImpl.this.g;
                        mutableLiveData3.postValue(connectionStatus);
                    }
                }
                connectionResultListener = IDOBaseBleApiImpl.this.d;
                if (connectionResultListener != null) {
                    connectionResultListener.onConnectionResponse(IDOBaseBleApiImpl.this.getConnectionStatus());
                }
                if (LocalDataManager.getSupportFunctionInfo() == null) {
                    BLEManager.getFunctionTables();
                }
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnecting(@NotNull String p0) {
                MutableLiveData mutableLiveData;
                ConnectionResultListener connectionResultListener;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                ConnectionStatus connectionStatus;
                ConnectionStatus connectionStatus2;
                Intrinsics.checkNotNullParameter(p0, "p0");
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "autoconnecting");
                IDOBaseBleApiImpl.this.g = ConnectionStatus.CONNECTING;
                mutableLiveData = IDOBaseBleApiImpl.this.e;
                if (mutableLiveData != null) {
                    mutableLiveData2 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData2 != null) {
                        connectionStatus2 = IDOBaseBleApiImpl.this.g;
                        mutableLiveData2.setValue(connectionStatus2);
                    }
                    mutableLiveData3 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData3 != null) {
                        connectionStatus = IDOBaseBleApiImpl.this.g;
                        mutableLiveData3.postValue(connectionStatus);
                    }
                }
                connectionResultListener = IDOBaseBleApiImpl.this.d;
                if (connectionResultListener != null) {
                    connectionResultListener.onConnectionResponse(IDOBaseBleApiImpl.this.getConnectionStatus());
                }
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onDeviceInNotBindStatus(@Nullable String str) {
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onInDfuMode(@Nullable BLEDevice bLEDevice) {
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "autodfumode");
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onInitCompleted(@NotNull String p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "autoinit");
            }

            @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onRetry(int i, @NotNull String p1) {
                MutableLiveData mutableLiveData;
                ConnectionResultListener connectionResultListener;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                ConnectionStatus connectionStatus;
                ConnectionStatus connectionStatus2;
                Intrinsics.checkNotNullParameter(p1, "p1");
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "autoretry");
                IDOBaseBleApiImpl.this.g = ConnectionStatus.CONNECTING;
                mutableLiveData = IDOBaseBleApiImpl.this.e;
                if (mutableLiveData != null) {
                    mutableLiveData2 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData2 != null) {
                        connectionStatus2 = IDOBaseBleApiImpl.this.g;
                        mutableLiveData2.setValue(connectionStatus2);
                    }
                    mutableLiveData3 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData3 != null) {
                        connectionStatus = IDOBaseBleApiImpl.this.g;
                        mutableLiveData3.postValue(connectionStatus);
                    }
                }
                connectionResultListener = IDOBaseBleApiImpl.this.d;
                if (connectionResultListener != null) {
                    connectionResultListener.onConnectionResponse(IDOBaseBleApiImpl.this.getConnectionStatus());
                }
            }
        };
        this.v = new BindCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$ibindCallBack$1
            @Override // com.ido.ble.callback.BindCallBack.ICallBack
            public void onCancel() {
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "ibindCallBack onCancel", ModuleNames.BLEABSTRACT.getModuleName());
                IDOBaseBleApiImpl.access$sendDisconnectResponse(IDOBaseBleApiImpl.this);
            }

            @Override // com.ido.ble.callback.BindCallBack.ICallBack
            public void onFailed(@Nullable BindCallBack.BindFailedError bindFailedError) {
                UnbindCallBack.ICallBack iCallBack;
                UnbindCallBack.ICallBack iCallBack2;
                String tag = IDOBaseBleApiImpl.this.getTAG();
                LogHelper.i(tag, "ibindCallBack onFailed bindFailedError -- " + bindFailedError, ModuleNames.BLEABSTRACT.getModuleName());
                if (bindFailedError == BindCallBack.BindFailedError.ERROR_DEVICE_ALREADY_IN_BIND_STATE) {
                    iCallBack = IDOBaseBleApiImpl.this.w;
                    BLEManager.unregisterUnbindCallBack(iCallBack);
                    iCallBack2 = IDOBaseBleApiImpl.this.w;
                    BLEManager.registerUnbindCallBack(iCallBack2);
                    BLEManager.unbind();
                }
                IDOBaseBleApiImpl.access$sendDisconnectResponse(IDOBaseBleApiImpl.this);
            }

            @Override // com.ido.ble.callback.BindCallBack.ICallBack
            public void onNeedAuth(int i) {
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "onNeedAuth", ModuleNames.BLEABSTRACT.getModuleName());
            }

            @Override // com.ido.ble.callback.BindCallBack.ICallBack
            public void onReject() {
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "ibindCallBack onReject", ModuleNames.BLEABSTRACT.getModuleName());
                IDOBaseBleApiImpl.access$sendDisconnectResponse(IDOBaseBleApiImpl.this);
            }

            @Override // com.ido.ble.callback.BindCallBack.ICallBack
            public void onSuccess() {
                MutableLiveData mutableLiveData;
                ConnectionResultListener connectionResultListener;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                ConnectionStatus connectionStatus;
                ConnectionStatus connectionStatus2;
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "bind success", ModuleNames.BLEABSTRACT.getModuleName());
                IDOBaseBleApiImpl.this.g = ConnectionStatus.CONNECTED;
                if (LocalDataManager.getSupportFunctionInfo() == null) {
                    BLEManager.getFunctionTables();
                }
                mutableLiveData = IDOBaseBleApiImpl.this.e;
                if (mutableLiveData != null) {
                    mutableLiveData2 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData2 != null) {
                        connectionStatus2 = IDOBaseBleApiImpl.this.g;
                        mutableLiveData2.setValue(connectionStatus2);
                    }
                    mutableLiveData3 = IDOBaseBleApiImpl.this.e;
                    if (mutableLiveData3 != null) {
                        connectionStatus = IDOBaseBleApiImpl.this.g;
                        mutableLiveData3.postValue(connectionStatus);
                    }
                }
                connectionResultListener = IDOBaseBleApiImpl.this.d;
                if (connectionResultListener != null) {
                    connectionResultListener.onConnectionResponse(IDOBaseBleApiImpl.this.getConnectionStatus());
                }
            }
        };
        this.w = new UnbindCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$iUnbindCallback$1
            @Override // com.ido.ble.callback.UnbindCallBack.ICallBack
            public void onFailed() {
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "iUnbindCallback onFailed", ModuleNames.BLEABSTRACT.getModuleName());
                BLEManager.unregisterUnbindCallBack(this);
            }

            @Override // com.ido.ble.callback.UnbindCallBack.ICallBack
            public void onSuccess() {
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "iUnbindCallback onSuccess", ModuleNames.BLEABSTRACT.getModuleName());
                BLEManager.unregisterUnbindCallBack(this);
            }
        };
        this.x = new SettingCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$iSettingCallBack$1

            /* loaded from: classes2.dex */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[SettingCallBack.SettingType.values().length];
                    try {
                        iArr[SettingCallBack.SettingType.WALK_REMINDER.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.GOAL.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.USER_INFO.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.ALARM_V3.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE_V3.ordinal()] = 5;
                    } catch (NoSuchFieldError unused5) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.NOT_DISTURB.ordinal()] = 6;
                    } catch (NoSuchFieldError unused6) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.UP_HAND_GESTURE.ordinal()] = 7;
                    } catch (NoSuchFieldError unused7) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.UNIT.ordinal()] = 8;
                    } catch (NoSuchFieldError unused8) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.MUSIC_SWITCH.ordinal()] = 9;
                    } catch (NoSuchFieldError unused9) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.DIAL_PLATE.ordinal()] = 10;
                    } catch (NoSuchFieldError unused10) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.SPORT_SORT_V3.ordinal()] = 11;
                    } catch (NoSuchFieldError unused11) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.MENU_LIST_SET.ordinal()] = 12;
                    } catch (NoSuchFieldError unused12) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.ACTIVITY_SWITCH.ordinal()] = 13;
                    } catch (NoSuchFieldError unused13) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.MUSIC_CONTROL_INFO.ordinal()] = 14;
                    } catch (NoSuchFieldError unused14) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.DRINK_WATER_REMINDER.ordinal()] = 15;
                    } catch (NoSuchFieldError unused15) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.NOTICE_REMINDER_SWITCH_STATUS.ordinal()] = 16;
                    } catch (NoSuchFieldError unused16) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.WORLD_TIME.ordinal()] = 17;
                    } catch (NoSuchFieldError unused17) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.NOISE.ordinal()] = 18;
                    } catch (NoSuchFieldError unused18) {
                    }
                    try {
                        iArr[SettingCallBack.SettingType.PHONE_VOICE.ordinal()] = 19;
                    } catch (NoSuchFieldError unused19) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.ido.ble.callback.SettingCallBack.ICallBack
            public void onFailed(@Nullable SettingCallBack.SettingType settingType) {
                String tag = IDOBaseBleApiImpl.this.getTAG();
                LogHelper.i(tag, "iSettingCallBack settingType == " + settingType);
                if (settingType != null) {
                    IDOBaseBleApiImpl iDOBaseBleApiImpl = IDOBaseBleApiImpl.this;
                    iDOBaseBleApiImpl.onFailure(new IDOError(IDOErrorType.COMMAND_REQUEST_ERROR, iDOBaseBleApiImpl.getContext().getString(R.string.command_req_error)));
                }
            }

            @Override // com.ido.ble.callback.SettingCallBack.ICallBack
            public void onSuccess(@Nullable SettingCallBack.SettingType settingType, @Nullable Object obj) {
                IDOBaseReq a2;
                IDOBaseReq a3;
                IDOBaseReq a4;
                IDOBaseReq a5;
                IDOBaseReq a6;
                IDOBaseReq a7;
                IDOBaseReq a8;
                IDOBaseReq a9;
                IDOBaseReq a10;
                IDOBaseReq a11;
                IDOBaseReq a12;
                IDOBaseReq a13;
                IDOBaseReq a14;
                IDOBaseReq a15;
                IDOBaseReq a16;
                IDOBaseReq a17;
                IDOBaseReq a18;
                IDOBaseReq a19;
                IDOBaseReq a20;
                if (settingType != null) {
                    String tag = IDOBaseBleApiImpl.this.getTAG();
                    LogHelper.i(tag, "iSettingCallBack settingType == " + settingType);
                    switch (WhenMappings.$EnumSwitchMapping$0[settingType.ordinal()]) {
                        case 1:
                            BleBaseRequest fromQueue = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_SEDENTARY_REMINDER);
                            if (fromQueue != null) {
                                IDOBaseRes iDOBaseRes = new IDOBaseRes();
                                a2 = IDOBaseBleApiImpl.this.a(fromQueue);
                                if (a2 != null) {
                                    iDOBaseRes.setBaseReq(a2);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 2:
                            BleBaseRequest fromQueue2 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_WALK_TARGET);
                            if (fromQueue2 != null) {
                                IDOBaseRes iDOBaseRes2 = new IDOBaseRes();
                                a3 = IDOBaseBleApiImpl.this.a(fromQueue2);
                                if (a3 != null) {
                                    iDOBaseRes2.setBaseReq(a3);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes2);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 3:
                            BleBaseRequest fromQueue3 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_FITNESS_PERSONAL_INFO);
                            if (fromQueue3 != null) {
                                IDOBaseRes iDOBaseRes3 = new IDOBaseRes();
                                a4 = IDOBaseBleApiImpl.this.a(fromQueue3);
                                if (a4 != null) {
                                    iDOBaseRes3.setBaseReq(a4);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes3);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 4:
                            BleBaseRequest fromQueue4 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_VIBRATION_ALARM);
                            if (fromQueue4 != null) {
                                IDOBaseRes iDOBaseRes4 = new IDOBaseRes();
                                a5 = IDOBaseBleApiImpl.this.a(fromQueue4);
                                if (a5 != null) {
                                    iDOBaseRes4.setBaseReq(a5);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes4);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 5:
                            BleBaseRequest fromQueue5 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_HR_TIME_INTERVAL);
                            if (fromQueue5 != null) {
                                IDOBaseRes iDOBaseRes5 = new IDOBaseRes();
                                a6 = IDOBaseBleApiImpl.this.a(fromQueue5);
                                if (a6 != null) {
                                    iDOBaseRes5.setBaseReq(a6);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes5);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 6:
                            BleBaseRequest fromQueue6 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_DND_MODE);
                            if (fromQueue6 != null) {
                                IDOBaseRes iDOBaseRes6 = new IDOBaseRes();
                                a7 = IDOBaseBleApiImpl.this.a(fromQueue6);
                                if (a7 != null) {
                                    iDOBaseRes6.setBaseReq(a7);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes6);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 7:
                            BleBaseRequest fromQueue7 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_LIFT_WRIST);
                            if (fromQueue7 != null) {
                                IDOBaseRes iDOBaseRes7 = new IDOBaseRes();
                                a8 = IDOBaseBleApiImpl.this.a(fromQueue7);
                                if (a8 != null) {
                                    iDOBaseRes7.setBaseReq(a8);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes7);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 8:
                            BleBaseRequest fromQueue8 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_BAND_SETTINGS);
                            if (fromQueue8 != null) {
                                IDOBaseRes iDOBaseRes8 = new IDOBaseRes();
                                a9 = IDOBaseBleApiImpl.this.a(fromQueue8);
                                if (a9 != null) {
                                    iDOBaseRes8.setBaseReq(a9);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes8);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 9:
                            BleBaseRequest fromQueue9 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.MUSIC_CONTROL);
                            if (fromQueue9 != null) {
                                IDOBaseRes iDOBaseRes9 = new IDOBaseRes();
                                a10 = IDOBaseBleApiImpl.this.a(fromQueue9);
                                if (a10 != null) {
                                    iDOBaseRes9.setBaseReq(a10);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes9);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 10:
                            BleBaseRequest fromQueue10 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_CURRENT_WATCH_FACE);
                            if (fromQueue10 != null) {
                                IDOBaseRes iDOBaseRes10 = new IDOBaseRes();
                                a11 = IDOBaseBleApiImpl.this.a(fromQueue10);
                                if (a11 != null) {
                                    iDOBaseRes10.setBaseReq(a11);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes10);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 11:
                            BleBaseRequest fromQueue11 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_ACTIVITY_LIST);
                            if (fromQueue11 != null) {
                                IDOBaseRes iDOBaseRes11 = new IDOBaseRes();
                                a12 = IDOBaseBleApiImpl.this.a(fromQueue11);
                                if (a12 != null) {
                                    iDOBaseRes11.setBaseReq(a12);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes11);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 12:
                            BleBaseRequest fromQueue12 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_SHORTCUT_MENU_LIST);
                            if (fromQueue12 != null) {
                                IDOBaseRes iDOBaseRes12 = new IDOBaseRes();
                                a13 = IDOBaseBleApiImpl.this.a(fromQueue12);
                                if (a13 != null) {
                                    iDOBaseRes12.setBaseReq(a13);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes12);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 13:
                            BleBaseRequest fromQueue13 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.AUTO_ACTIVITY_RECOGNITION);
                            if (fromQueue13 != null) {
                                IDOBaseRes iDOBaseRes13 = new IDOBaseRes();
                                a14 = IDOBaseBleApiImpl.this.a(fromQueue13);
                                if (a14 != null) {
                                    iDOBaseRes13.setBaseReq(a14);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes13);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 14:
                            BleBaseRequest fromQueue14 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.MUSIC_META_DATA);
                            if (fromQueue14 != null) {
                                IDOBaseRes iDOBaseRes14 = new IDOBaseRes();
                                a15 = IDOBaseBleApiImpl.this.a(fromQueue14);
                                if (a15 != null) {
                                    iDOBaseRes14.setBaseReq(a15);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes14);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 15:
                            BleBaseRequest fromQueue15 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_DRINK_REMINDER);
                            if (fromQueue15 != null) {
                                IDOBaseRes iDOBaseRes15 = new IDOBaseRes();
                                a16 = IDOBaseBleApiImpl.this.a(fromQueue15);
                                if (a16 != null) {
                                    iDOBaseRes15.setBaseReq(a16);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes15);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 16:
                            BleBaseRequest fromQueue16 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.BT_CALL_CONTROL);
                            if (fromQueue16 != null) {
                                IDOBaseRes iDOBaseRes16 = new IDOBaseRes();
                                a17 = IDOBaseBleApiImpl.this.a(fromQueue16);
                                if (a17 != null) {
                                    iDOBaseRes16.setBaseReq(a17);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes16);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 17:
                            BleBaseRequest fromQueue17 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_WORLD_CLOCK_LIST);
                            if (fromQueue17 != null) {
                                IDOBaseRes iDOBaseRes17 = new IDOBaseRes();
                                a18 = IDOBaseBleApiImpl.this.a(fromQueue17);
                                if (a18 != null) {
                                    iDOBaseRes17.setBaseReq(a18);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes17);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 18:
                            BleBaseRequest fromQueue18 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_AMBIENT_SOUND_LEVEL_CONTROL);
                            if (fromQueue18 != null) {
                                IDOBaseRes iDOBaseRes18 = new IDOBaseRes();
                                a19 = IDOBaseBleApiImpl.this.a(fromQueue18);
                                if (a19 != null) {
                                    iDOBaseRes18.setBaseReq(a19);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes18);
                                    return;
                                }
                                return;
                            }
                            return;
                        case 19:
                            BleBaseRequest fromQueue19 = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.VOLUME_CONTROL);
                            if (fromQueue19 != null) {
                                IDOBaseRes iDOBaseRes19 = new IDOBaseRes();
                                a20 = IDOBaseBleApiImpl.this.a(fromQueue19);
                                if (a20 != null) {
                                    iDOBaseRes19.setBaseReq(a20);
                                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes19);
                                    return;
                                }
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        };
        this.y = new GetDeviceInfoCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$iDeviceinfoCallback$1
            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetActivityCount(@Nullable ActivityDataCount activityDataCount) {
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetBasicInfo(@Nullable BasicInfo basicInfo) {
                DeviceInfoData deviceInfoData;
                DeviceInfoData deviceInfoData2;
                DeviceInfoData deviceInfoData3;
                IDOBaseReq a2;
                DeviceInfoData deviceInfoData4;
                if (basicInfo != null) {
                    IDOBaseBleApiImpl.this.h = null;
                    String macAddress = IDOBaseBleApiImpl.this.getMacAddress();
                    deviceInfoData = IDOBaseBleApiImpl.this.h;
                    if (deviceInfoData == null) {
                        IDOBaseBleApiImpl.this.h = new DeviceInfoData();
                    }
                    deviceInfoData2 = IDOBaseBleApiImpl.this.h;
                    Intrinsics.checkNotNull(deviceInfoData2);
                    deviceInfoData2.setMacAddress(macAddress);
                    deviceInfoData3 = IDOBaseBleApiImpl.this.h;
                    Intrinsics.checkNotNull(deviceInfoData3);
                    deviceInfoData3.setFwVersion(String.valueOf(basicInfo.firmwareVersion));
                    BleBaseRequest fromQueue = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.DEVICE_INFO);
                    if (fromQueue != null) {
                        IDOBaseRes iDOBaseRes = new IDOBaseRes();
                        a2 = IDOBaseBleApiImpl.this.a(fromQueue);
                        if (a2 != null) {
                            iDOBaseRes.setBaseReq(a2);
                            deviceInfoData4 = IDOBaseBleApiImpl.this.h;
                            iDOBaseRes.setObj(deviceInfoData4);
                            IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                        }
                    }
                }
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetBatteryInfo(@Nullable BatteryInfo batteryInfo) {
                BleBaseRequest fromQueue;
                IDOBaseReq a2;
                if (batteryInfo == null || (fromQueue = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.READ_BATTERY_LEVEL)) == null) {
                    return;
                }
                IDOBaseRes iDOBaseRes = new IDOBaseRes();
                a2 = IDOBaseBleApiImpl.this.a(fromQueue);
                if (a2 != null) {
                    iDOBaseRes.setBaseReq(a2);
                    iDOBaseRes.setObj(batteryInfo);
                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                }
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetCanDownloadLangInfo(@Nullable CanDownLangInfo canDownLangInfo) {
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetCanDownloadLangInfoV3(@Nullable CanDownLangInfoV3 canDownLangInfoV3) {
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetDeviceSummarySoftVersionInfo(@Nullable DeviceSummarySoftVersionInfo deviceSummarySoftVersionInfo) {
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetFlashBinInfo(@Nullable FlashBinInfo flashBinInfo) {
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetFunctionTable(@Nullable SupportFunctionInfo supportFunctionInfo) {
                LogHelper.i("onGetFunctionTable", String.valueOf(supportFunctionInfo));
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetHIDInfo(@Nullable HIDInfo hIDInfo) {
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetLiveData(@Nullable LiveData liveData) {
                IDOBaseReq a2;
                if (liveData != null) {
                    String tag = IDOBaseBleApiImpl.this.getTAG();
                    LogHelper.i(tag, "onGetLiveData" + liveData);
                    BleBaseRequest fromQueue = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.READ_BATTERY_LEVEL);
                    if (fromQueue != null) {
                        IDOBaseRes iDOBaseRes = new IDOBaseRes();
                        a2 = IDOBaseBleApiImpl.this.a(fromQueue);
                        if (a2 != null) {
                            iDOBaseRes.setBaseReq(a2);
                            iDOBaseRes.setObj(liveData);
                            IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                        }
                    }
                }
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetMacAddress(@Nullable MacAddressInfo macAddressInfo) {
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetNoticeCenterSwitchStatus(@Nullable NoticeSwitchInfo noticeSwitchInfo) {
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetNoticeReminderSwitchStatus(@Nullable NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetSNInfo(@Nullable SNInfo sNInfo) {
            }

            @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetTime(@Nullable SystemTime systemTime) {
            }
        };
        this.z = new PhoneMsgNoticeCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$iCallBack$1
            @Override // com.ido.ble.callback.PhoneMsgNoticeCallBack.ICallBack
            public void onCalling() {
                IDOBaseReq a2;
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "onCalling");
                BleBaseRequest fromQueue = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_SOCIAL_NOTIFICATION);
                if (fromQueue != null) {
                    IDOBaseRes iDOBaseRes = new IDOBaseRes();
                    a2 = IDOBaseBleApiImpl.this.a(fromQueue);
                    if (a2 != null) {
                        iDOBaseRes.setBaseReq(a2);
                        IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                    }
                }
            }

            @Override // com.ido.ble.callback.PhoneMsgNoticeCallBack.ICallBack
            public void onNewMessage() {
                IDOBaseReq a2;
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "onNewMessage");
                BleBaseRequest fromQueue = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_SOCIAL_NOTIFICATION);
                if (fromQueue != null) {
                    IDOBaseRes iDOBaseRes = new IDOBaseRes();
                    a2 = IDOBaseBleApiImpl.this.a(fromQueue);
                    if (a2 != null) {
                        iDOBaseRes.setBaseReq(a2);
                        IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                    }
                }
            }

            @Override // com.ido.ble.callback.PhoneMsgNoticeCallBack.ICallBack
            public void onStopCall() {
            }

            @Override // com.ido.ble.callback.PhoneMsgNoticeCallBack.ICallBack
            public void onUnReadMessage() {
            }

            @Override // com.ido.ble.callback.PhoneMsgNoticeCallBack.ICallBack
            public void onV3MessageNotice(int i) {
                IDOBaseReq a2;
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "onV3MessageNotice");
                BleBaseRequest fromQueue = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SET_SOCIAL_NOTIFICATION);
                if (fromQueue != null) {
                    IDOBaseRes iDOBaseRes = new IDOBaseRes();
                    a2 = IDOBaseBleApiImpl.this.a(fromQueue);
                    if (a2 != null) {
                        iDOBaseRes.setBaseReq(a2);
                        IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                    }
                }
            }
        };
        this.A = new SyncV3CallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$iSyncV3CallBack$1
            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onFailed() {
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "sync v3 data failed");
                IDOBaseBleApiImpl.this.r = false;
                IDOBaseBleApiImpl iDOBaseBleApiImpl = IDOBaseBleApiImpl.this;
                iDOBaseBleApiImpl.onFailure(new IDOError(IDOErrorType.COMMAND_REQUEST_ERROR, iDOBaseBleApiImpl.getContext().getString(R.string.command_req_error)));
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthActivityV3Data(@NotNull HealthActivityV3 healthActivityV3) {
                Intrinsics.checkNotNullParameter(healthActivityV3, "healthActivityV3");
                EntityHealthActivityV3 convertHealthActivityToEntity = IDOActivityFormatter.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).convertHealthActivityToEntity(healthActivityV3);
                if (convertHealthActivityToEntity != null) {
                    KHIDOActivityRepository.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).insertActivityData(convertHealthActivityToEntity);
                }
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthBloodPressure(@NotNull HealthBloodPressureV3 healthBloodPressureV3) {
                Intrinsics.checkNotNullParameter(healthBloodPressureV3, "healthBloodPressureV3");
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthBodyPower(@Nullable HealthBodyPower healthBodyPower) {
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthGpsV3Data(@NotNull HealthGpsV3 healthGpsV3) {
                Intrinsics.checkNotNullParameter(healthGpsV3, "healthGpsV3");
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthHeartRateSecondData(@NotNull HealthHeartRateSecond healthHeartRateSecond, boolean z) {
                Intrinsics.checkNotNullParameter(healthHeartRateSecond, "healthHeartRateSecond");
                EntityHealthHeartRateSecond convertHealthHeartRateSecondToEntity = IDOHeartRateFormatter.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).convertHealthHeartRateSecondToEntity(healthHeartRateSecond);
                if (convertHealthHeartRateSecondToEntity != null) {
                    KHIDOHeartRateRepository.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).insertHeartRateSecondData(convertHealthHeartRateSecondToEntity);
                }
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthNoiseData(@NotNull HealthNoise healthNoise) {
                Intrinsics.checkNotNullParameter(healthNoise, "healthNoise");
                EntityHealthNoise convertAmbientSoundLevelToEntity = IDOAmbientSoundLevelFormatter.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).convertAmbientSoundLevelToEntity(healthNoise);
                if (convertAmbientSoundLevelToEntity != null) {
                    KHIDONoiseRepository.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).insertNoiseData(convertAmbientSoundLevelToEntity);
                }
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthPressureData(@NotNull HealthPressure healthPressure, @NotNull List<? extends HealthPressureItem> itemList, boolean z) {
                Intrinsics.checkNotNullParameter(healthPressure, "healthPressure");
                Intrinsics.checkNotNullParameter(itemList, "itemList");
                EntityHealthPressure convertHealthPressureToEntity = IDOStressFormatter.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).convertHealthPressureToEntity(healthPressure, itemList);
                if (convertHealthPressureToEntity != null) {
                    KHIDOStressRepository.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).insertStressData(convertHealthPressureToEntity);
                }
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthRespiratoryRate(@Nullable HealthRespiratoryRate healthRespiratoryRate) {
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthSleepV3Data(@NotNull HealthSleepV3 healthSleepV3) {
                Intrinsics.checkNotNullParameter(healthSleepV3, "healthSleepV3");
                EntityHealthSleepV3 convertHealthSleepV3ToEntity = IDOSleepFormatter.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).convertHealthSleepV3ToEntity(healthSleepV3);
                if (convertHealthSleepV3ToEntity != null) {
                    KHIDOSleepRepository.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).insertSleepData(convertHealthSleepV3ToEntity);
                }
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthSpO2Data(@NotNull HealthSpO2 healthSpO2, @NotNull List<? extends HealthSpO2Item> itemList, boolean z) {
                Intrinsics.checkNotNullParameter(healthSpO2, "healthSpO2");
                Intrinsics.checkNotNullParameter(itemList, "itemList");
                EntityHealthSpo2 convertHealthSpo2ToEntity = IDOSPO2Formatter.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).convertHealthSpo2ToEntity(healthSpO2, itemList);
                if (convertHealthSpo2ToEntity != null) {
                    KHIDOSpo2Repository.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).insertSpo2Data(convertHealthSpo2ToEntity);
                }
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthSportV3Data(@NotNull HealthSportV3 healthSportV3) {
                Intrinsics.checkNotNullParameter(healthSportV3, "healthSportV3");
                EntityHealthSportV3 convertHealthSportV3ToEntity = IDOStepsFormatter.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).convertHealthSportV3ToEntity(healthSportV3);
                if (convertHealthSportV3ToEntity != null) {
                    KHIDOStepsRepository.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).insertStepsData(convertHealthSportV3ToEntity);
                }
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthSwimmingData(@NotNull HealthSwimming healthSwimming) {
                Intrinsics.checkNotNullParameter(healthSwimming, "healthSwimming");
                EntityHealthSwimV3 convertHealthSwimActivityToEntity = IDOActivityFormatter.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).convertHealthSwimActivityToEntity(healthSwimming);
                if (convertHealthSwimActivityToEntity != null) {
                    KHIDOActivityRepository.Companion.getInstance(IDOBaseBleApiImpl.this.getContext()).insertSwimActivityData(convertHealthSwimActivityToEntity);
                }
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onGetHealthTemperature(@NotNull HealthTemperature healthTemperature) {
                Intrinsics.checkNotNullParameter(healthTemperature, "healthTemperature");
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onProgress(int i) {
                String tag = IDOBaseBleApiImpl.this.getTAG();
                LogHelper.i(tag, "sync v3 data progress = " + i + '%');
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onStart() {
                IDOBaseBleApiImpl.this.r = true;
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "start sync v3 data");
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onStop() {
                IDOBaseBleApiImpl.this.r = false;
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "sync v3 data stop!");
            }

            @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
            public void onSuccess() {
                LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "sync v3 data success!");
                IDOBaseBleApiImpl.this.r = false;
                BleBaseRequest fromQueue = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.GET_STEPS_DATA);
                if (fromQueue != null) {
                    kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$iSyncV3CallBack$1$onSuccess$1(IDOBaseBleApiImpl.this, fromQueue, null), 2, null);
                }
            }
        };
        this.B = new DeviceControlAppCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$deviceControlAppCallBack$1
            @Override // com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
            public void onAntiLostNotice(boolean z, long j) {
            }

            @Override // com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
            public void onControlEvent(@Nullable DeviceControlAppCallBack.DeviceControlEventType deviceControlEventType, int i) {
                IDOBaseBleApiImpl iDOBaseBleApiImpl = IDOBaseBleApiImpl.this;
                Intrinsics.checkNotNull(deviceControlEventType);
                IDOBaseBleApiImpl.access$handleControlEvent(iDOBaseBleApiImpl, deviceControlEventType);
            }

            @Override // com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
            public void onFindPhone(boolean z, long j) {
                FindMyPhoneRes findMyPhoneRes;
                if (z) {
                    findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.ON);
                } else {
                    findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.OFF);
                }
                Intent intent = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
                intent.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes);
                LocalBroadcastManager.getInstance(IDOBaseBleApiImpl.this.getContext()).sendBroadcast(intent);
            }

            @Override // com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
            public void onOneKeySOS(boolean z, long j) {
            }
        };
        this.C = new AppSendDataCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$appSendDataCallBack$1
            @Override // com.ido.ble.callback.AppSendDataCallBack.ICallBack
            public void onFailed(@NotNull AppSendDataCallBack.DataType type) {
                Intrinsics.checkNotNullParameter(type, "type");
            }

            @Override // com.ido.ble.callback.AppSendDataCallBack.ICallBack
            public void onSuccess(@NotNull AppSendDataCallBack.DataType type) {
                BleBaseRequest fromQueue;
                IDOBaseReq a2;
                Intrinsics.checkNotNullParameter(type, "type");
                if (type != AppSendDataCallBack.DataType.WEATHER_V3 || (fromQueue = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.SEND_WEATHER_DATA)) == null) {
                    return;
                }
                IDOBaseRes iDOBaseRes = new IDOBaseRes();
                a2 = IDOBaseBleApiImpl.this.a(fromQueue);
                if (a2 != null) {
                    iDOBaseRes.setBaseReq(a2);
                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                }
            }
        };
        this.D = new WatchPlateCallBack.IOperateCallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$watchFaceIOperateCallBack$1
            @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
            public void onDeletePlate(boolean z) {
                IDOBaseReq a2;
                BleBaseRequest fromQueue = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.DELETE_WATCH_FACE);
                if (fromQueue != null) {
                    IDOBaseRes iDOBaseRes = new IDOBaseRes();
                    a2 = IDOBaseBleApiImpl.this.a(fromQueue);
                    if (a2 != null) {
                        iDOBaseRes.setBaseReq(a2);
                        IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                    }
                }
            }

            @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
            public void onGetCurrentPlate(@Nullable String str) {
            }

            @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
            public void onGetDialPlateParam(@Nullable DialPlateParam dialPlateParam) {
            }

            @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
            public void onGetPlateFileInfo(@Nullable WatchPlateFileInfo watchPlateFileInfo) {
                IDOBaseReq a2;
                BleBaseRequest fromQueue = IDOBaseBleApiImpl.this.getFromQueue(IDOBleCommandName.GET_WATCH_FACE_LIST);
                if (fromQueue != null) {
                    IDOBaseRes iDOBaseRes = new IDOBaseRes();
                    a2 = IDOBaseBleApiImpl.this.a(fromQueue);
                    if (a2 != null) {
                        iDOBaseRes.setBaseReq(a2);
                        iDOBaseRes.setObj(watchPlateFileInfo);
                        IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                    }
                }
            }

            @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
            public void onGetScreenInfo(@Nullable WatchPlateScreenInfo watchPlateScreenInfo) {
            }

            @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
            public void onSetPlate(boolean z) {
            }
        };
        this.E = new GetDeviceParaCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$getDeviceParaCallBack$1
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public final IDOBaseRes f2969a = new IDOBaseRes();

            @NotNull
            public final IDOBaseRes getIdoBaseRes() {
                return this.f2969a;
            }

            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            public void onGetActivitySwitch(@Nullable ActivitySwitch activitySwitch) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:5:0x000c, code lost:
                r0 = r2.b.a(r0);
             */
            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onGetAlarmV3(@org.jetbrains.annotations.Nullable java.util.List<com.ido.ble.protocol.model.AlarmV3> r3) {
                /*
                    r2 = this;
                    if (r3 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.IDOBleCommandName r1 = com.coveiot.android.idoSdk.IDOBleCommandName.GET_ALARM_LIST
                    com.coveiot.android.bleabstract.request.BleBaseRequest r0 = r0.getFromQueue(r1)
                    if (r0 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseReq r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.access$getIDOBLERequest(r1, r0)
                    if (r0 == 0) goto L25
                    com.coveiot.android.idoSdk.api.IDOBaseRes r1 = r2.f2969a
                    r1.setBaseReq(r0)
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r2.f2969a
                    r0.setObj(r3)
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r3 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r2.f2969a
                    r3.onResponse(r0)
                L25:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$getDeviceParaCallBack$1.onGetAlarmV3(java.util.List):void");
            }

            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            public void onGetAllHealthMonitorSwitch(@Nullable AllHealthMonitorSwitch allHealthMonitorSwitch) {
            }

            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            public void onGetBtA2dpHfpStatus(@Nullable BtA2dpHfpStatus btA2dpHfpStatus) {
            }

            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            public void onGetContactReceiveTime(boolean z) {
            }

            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            public void onGetDeviceUpgradeState(@Nullable DeviceUpgradeState deviceUpgradeState) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:5:0x000c, code lost:
                r0 = r2.b.a(r0);
             */
            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onGetDoNotDisturbPara(@org.jetbrains.annotations.Nullable com.ido.ble.protocol.model.NotDisturbPara r3) {
                /*
                    r2 = this;
                    if (r3 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.IDOBleCommandName r1 = com.coveiot.android.idoSdk.IDOBleCommandName.GET_DND_MODE_SETTINGS
                    com.coveiot.android.bleabstract.request.BleBaseRequest r0 = r0.getFromQueue(r1)
                    if (r0 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseReq r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.access$getIDOBLERequest(r1, r0)
                    if (r0 == 0) goto L25
                    com.coveiot.android.idoSdk.api.IDOBaseRes r1 = r2.f2969a
                    r1.setBaseReq(r0)
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r2.f2969a
                    r0.setObj(r3)
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r3 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r2.f2969a
                    r3.onResponse(r0)
                L25:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$getDeviceParaCallBack$1.onGetDoNotDisturbPara(com.ido.ble.protocol.model.NotDisturbPara):void");
            }

            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            public void onGetFirmwareAndBt3Version(@Nullable FirmwareAndBt3Version firmwareAndBt3Version) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:5:0x000c, code lost:
                r0 = r2.b.a(r0);
             */
            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onGetMenuList(@org.jetbrains.annotations.Nullable com.ido.ble.protocol.model.MenuList.DeviceReturnInfo r3) {
                /*
                    r2 = this;
                    if (r3 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.IDOBleCommandName r1 = com.coveiot.android.idoSdk.IDOBleCommandName.GET_SHORTCUT_MENU_LIST
                    com.coveiot.android.bleabstract.request.BleBaseRequest r0 = r0.getFromQueue(r1)
                    if (r0 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseReq r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.access$getIDOBLERequest(r1, r0)
                    if (r0 == 0) goto L25
                    com.coveiot.android.idoSdk.api.IDOBaseRes r1 = r2.f2969a
                    r1.setBaseReq(r0)
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r2.f2969a
                    r0.setObj(r3)
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r3 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r2.f2969a
                    r3.onResponse(r0)
                L25:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$getDeviceParaCallBack$1.onGetMenuList(com.ido.ble.protocol.model.MenuList$DeviceReturnInfo):void");
            }

            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            public void onGetPressCalibrationValue(@Nullable PressCalibrationValue pressCalibrationValue) {
            }

            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            public void onGetScheduleReminderV3(@Nullable List<ScheduleReminderV3> list) {
            }

            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            public void onGetScreenBrightness(@Nullable ScreenBrightness screenBrightness) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:5:0x000c, code lost:
                r3 = r1.b.a(r3);
             */
            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onGetSportThreeCircleGoal(@org.jetbrains.annotations.Nullable com.ido.ble.protocol.model.CalorieAndDistanceGoal r2, @org.jetbrains.annotations.Nullable java.lang.String r3) {
                /*
                    r1 = this;
                    if (r2 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r3 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.IDOBleCommandName r0 = com.coveiot.android.idoSdk.IDOBleCommandName.GET_CALORIE_DISTANCE_TARGET
                    com.coveiot.android.bleabstract.request.BleBaseRequest r3 = r3.getFromQueue(r0)
                    if (r3 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseReq r3 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.access$getIDOBLERequest(r0, r3)
                    if (r3 == 0) goto L25
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r1.f2969a
                    r0.setBaseReq(r3)
                    com.coveiot.android.idoSdk.api.IDOBaseRes r3 = r1.f2969a
                    r3.setObj(r2)
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r2 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseRes r3 = r1.f2969a
                    r2.onResponse(r3)
                L25:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$getDeviceParaCallBack$1.onGetSportThreeCircleGoal(com.ido.ble.protocol.model.CalorieAndDistanceGoal, java.lang.String):void");
            }

            /* JADX WARN: Code restructure failed: missing block: B:5:0x000c, code lost:
                r0 = r2.b.a(r0);
             */
            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onGetSupportSportInfoV3(@org.jetbrains.annotations.Nullable com.ido.ble.protocol.model.SupportSportInfoV3 r3) {
                /*
                    r2 = this;
                    if (r3 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.IDOBleCommandName r1 = com.coveiot.android.idoSdk.IDOBleCommandName.GET_SUPPORTED_ACTIVITY_LIST
                    com.coveiot.android.bleabstract.request.BleBaseRequest r0 = r0.getFromQueue(r1)
                    if (r0 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseReq r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.access$getIDOBLERequest(r1, r0)
                    if (r0 == 0) goto L25
                    com.coveiot.android.idoSdk.api.IDOBaseRes r1 = r2.f2969a
                    r1.setBaseReq(r0)
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r2.f2969a
                    r0.setObj(r3)
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r3 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r2.f2969a
                    r3.onResponse(r0)
                L25:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$getDeviceParaCallBack$1.onGetSupportSportInfoV3(com.ido.ble.protocol.model.SupportSportInfoV3):void");
            }

            /* JADX WARN: Code restructure failed: missing block: B:5:0x000c, code lost:
                r0 = r2.b.a(r0);
             */
            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onGetUpHandGesture(@org.jetbrains.annotations.Nullable com.ido.ble.protocol.model.UpHandGesture r3) {
                /*
                    r2 = this;
                    if (r3 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.IDOBleCommandName r1 = com.coveiot.android.idoSdk.IDOBleCommandName.GET_LIFT_WRIST
                    com.coveiot.android.bleabstract.request.BleBaseRequest r0 = r0.getFromQueue(r1)
                    if (r0 == 0) goto L25
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseReq r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.access$getIDOBLERequest(r1, r0)
                    if (r0 == 0) goto L25
                    com.coveiot.android.idoSdk.api.IDOBaseRes r1 = r2.f2969a
                    r1.setBaseReq(r0)
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r2.f2969a
                    r0.setObj(r3)
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r3 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r2.f2969a
                    r3.onResponse(r0)
                L25:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$getDeviceParaCallBack$1.onGetUpHandGesture(com.ido.ble.protocol.model.UpHandGesture):void");
            }

            @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
            public void onGetWalkReminder(@Nullable WalkReminder walkReminder) {
            }
        };
        this.F = new OtherProtocolCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$mOtherSettingCallback$1

            /* loaded from: classes2.dex */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[OtherProtocolCallBack.SettingType.values().length];
                    try {
                        iArr[OtherProtocolCallBack.SettingType.PRESSURE.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[OtherProtocolCallBack.SettingType.MENSTRUAL_REMIND.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[OtherProtocolCallBack.SettingType.CALORIE_DISTANCE_GOAL.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
            public void onFailed(@NotNull OtherProtocolCallBack.SettingType settingType) {
                Intrinsics.checkNotNullParameter(settingType, "settingType");
                String tag = IDOBaseBleApiImpl.this.getTAG();
                LogHelper.i(tag, "OtherProtocolCallBack settingType == " + settingType);
                IDOBaseBleApiImpl iDOBaseBleApiImpl = IDOBaseBleApiImpl.this;
                iDOBaseBleApiImpl.onFailure(new IDOError(IDOErrorType.COMMAND_REQUEST_ERROR, iDOBaseBleApiImpl.getContext().getString(R.string.command_req_error)));
            }

            /* JADX WARN: Code restructure failed: missing block: B:11:0x0040, code lost:
                r5 = r4.f2978a.a(r5);
             */
            /* JADX WARN: Code restructure failed: missing block: B:16:0x005b, code lost:
                r5 = r4.f2978a.a(r5);
             */
            /* JADX WARN: Code restructure failed: missing block: B:21:0x0076, code lost:
                r5 = r4.f2978a.a(r5);
             */
            @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onSuccess(@org.jetbrains.annotations.NotNull com.ido.ble.callback.OtherProtocolCallBack.SettingType r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "settingType"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = new com.coveiot.android.idoSdk.api.IDOBaseRes
                    r0.<init>()
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    java.lang.String r1 = r1.getTAG()
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "mOtherSettingCallback settingType == "
                    r2.append(r3)
                    r2.append(r5)
                    java.lang.String r2 = r2.toString()
                    com.coveiot.utils.utility.LogHelper.i(r1, r2)
                    int[] r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$mOtherSettingCallback$1.WhenMappings.$EnumSwitchMapping$0
                    int r5 = r5.ordinal()
                    r5 = r1[r5]
                    r1 = 1
                    if (r5 == r1) goto L6c
                    r1 = 2
                    if (r5 == r1) goto L51
                    r1 = 3
                    if (r5 == r1) goto L36
                    goto L86
                L36:
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r5 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.IDOBleCommandName r1 = com.coveiot.android.idoSdk.IDOBleCommandName.SET_CALORIE_DISTANCE_TARGET
                    com.coveiot.android.bleabstract.request.BleBaseRequest r5 = r5.getFromQueue(r1)
                    if (r5 == 0) goto L86
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseReq r5 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.access$getIDOBLERequest(r1, r5)
                    if (r5 == 0) goto L86
                    r0.setBaseReq(r5)
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r5 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    r5.onResponse(r0)
                    goto L86
                L51:
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r5 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.IDOBleCommandName r1 = com.coveiot.android.idoSdk.IDOBleCommandName.SET_WOMEN_WELLNESS
                    com.coveiot.android.bleabstract.request.BleBaseRequest r5 = r5.getFromQueue(r1)
                    if (r5 == 0) goto L86
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseReq r5 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.access$getIDOBLERequest(r1, r5)
                    if (r5 == 0) goto L86
                    r0.setBaseReq(r5)
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r5 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    r5.onResponse(r0)
                    goto L86
                L6c:
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r5 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.IDOBleCommandName r1 = com.coveiot.android.idoSdk.IDOBleCommandName.SET_STRESS_INTERVAL
                    com.coveiot.android.bleabstract.request.BleBaseRequest r5 = r5.getFromQueue(r1)
                    if (r5 == 0) goto L86
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseReq r5 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.access$getIDOBLERequest(r1, r5)
                    if (r5 == 0) goto L86
                    r0.setBaseReq(r5)
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r5 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    r5.onResponse(r0)
                L86:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$mOtherSettingCallback$1.onSuccess(com.ido.ble.callback.OtherProtocolCallBack$SettingType):void");
            }
        };
        this.G = new OperateCallBack.ICallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$mOperateCallBack$1
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public final IDOBaseRes f2977a = new IDOBaseRes();

            @NotNull
            public final IDOBaseRes getIdoBaseRes() {
                return this.f2977a;
            }

            @Override // com.ido.ble.callback.OperateCallBack.ICallBack
            public void onAddResult(@Nullable OperateCallBack.OperateType operateType, boolean z) {
            }

            @Override // com.ido.ble.callback.OperateCallBack.ICallBack
            public void onDeleteResult(@Nullable OperateCallBack.OperateType operateType, boolean z) {
            }

            @Override // com.ido.ble.callback.OperateCallBack.ICallBack
            public void onModifyResult(@Nullable OperateCallBack.OperateType operateType, boolean z) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:6:0x0015, code lost:
                r2 = r1.b.a(r2);
             */
            @Override // com.ido.ble.callback.OperateCallBack.ICallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onQueryResult(@org.jetbrains.annotations.Nullable com.ido.ble.callback.OperateCallBack.OperateType r2, @org.jetbrains.annotations.Nullable java.lang.Object r3) {
                /*
                    r1 = this;
                    com.ido.ble.callback.OperateCallBack$OperateType r0 = com.ido.ble.callback.OperateCallBack.OperateType.SPORT_100_TYPE_SORT
                    if (r2 != r0) goto L2e
                    java.lang.String r2 = "null cannot be cast to non-null type com.ido.ble.protocol.model.Sport100TypeSort"
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r2)
                    com.ido.ble.protocol.model.Sport100TypeSort r3 = (com.ido.ble.protocol.model.Sport100TypeSort) r3
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r2 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.IDOBleCommandName r0 = com.coveiot.android.idoSdk.IDOBleCommandName.GET_SUPPORTED_ACTIVITY_LIST
                    com.coveiot.android.bleabstract.request.BleBaseRequest r2 = r2.getFromQueue(r0)
                    if (r2 == 0) goto L2e
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r0 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseReq r2 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.access$getIDOBLERequest(r0, r2)
                    if (r2 == 0) goto L2e
                    com.coveiot.android.idoSdk.api.IDOBaseRes r0 = r1.f2977a
                    r0.setBaseReq(r2)
                    com.coveiot.android.idoSdk.api.IDOBaseRes r2 = r1.f2977a
                    r2.setObj(r3)
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r2 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseRes r3 = r1.f2977a
                    r2.onResponse(r3)
                L2e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$mOperateCallBack$1.onQueryResult(com.ido.ble.callback.OperateCallBack$OperateType, java.lang.Object):void");
            }

            /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
                r1 = r0.b.a(r1);
             */
            @Override // com.ido.ble.callback.OperateCallBack.ICallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onSetResult(@org.jetbrains.annotations.Nullable com.ido.ble.callback.OperateCallBack.OperateType r1, boolean r2) {
                /*
                    r0 = this;
                    if (r1 == 0) goto L24
                    com.ido.ble.callback.OperateCallBack$OperateType r2 = com.ido.ble.callback.OperateCallBack.OperateType.FREQUENT_CONTACTS
                    if (r1 != r2) goto L24
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.IDOBleCommandName r2 = com.coveiot.android.idoSdk.IDOBleCommandName.SET_FREQUENT_CONTACT_LIST
                    com.coveiot.android.bleabstract.request.BleBaseRequest r1 = r1.getFromQueue(r2)
                    if (r1 == 0) goto L24
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r2 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseReq r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.access$getIDOBLERequest(r2, r1)
                    if (r1 == 0) goto L24
                    com.coveiot.android.idoSdk.api.IDOBaseRes r2 = r0.f2977a
                    r2.setBaseReq(r1)
                    com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl r1 = com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.this
                    com.coveiot.android.idoSdk.api.IDOBaseRes r2 = r0.f2977a
                    r1.onResponse(r2)
                L24:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$mOperateCallBack$1.onSetResult(com.ido.ble.callback.OperateCallBack$OperateType, boolean):void");
            }
        };
        this.H = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.x1
            @Override // java.lang.Runnable
            public final void run() {
                IDOBaseBleApiImpl.a(IDOBaseBleApiImpl.this);
            }
        };
    }

    public static final void access$handleControlEvent(IDOBaseBleApiImpl iDOBaseBleApiImpl, DeviceControlAppCallBack.DeviceControlEventType deviceControlEventType) {
        iDOBaseBleApiImpl.getClass();
        new LiveMusicControlRes(MusicControlState.UNKNOWN);
        Intent intent = new Intent(Constants.MUSIC_CONTROL_BROADCAST_INTENT);
        CameraState cameraState = CameraState.CAPTURE;
        new CameraEventRes(cameraState);
        Intent intent2 = new Intent(Constants.CAMERA_BROADCAST_INTENT);
        switch (WhenMappings.$EnumSwitchMapping$0[deviceControlEventType.ordinal()]) {
            case 1:
                intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.PLAY));
                LocalBroadcastManager.getInstance(iDOBaseBleApiImpl.f2962a).sendBroadcast(intent);
                return;
            case 2:
                intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.PAUSE));
                LocalBroadcastManager.getInstance(iDOBaseBleApiImpl.f2962a).sendBroadcast(intent);
                return;
            case 3:
            case 7:
            default:
                return;
            case 4:
                intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.PREVIOUS));
                LocalBroadcastManager.getInstance(iDOBaseBleApiImpl.f2962a).sendBroadcast(intent);
                return;
            case 5:
                intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.NEXT));
                LocalBroadcastManager.getInstance(iDOBaseBleApiImpl.f2962a).sendBroadcast(intent);
                return;
            case 6:
                intent2.putExtra(Constants.CAMERA_BROADCAST_INTENT_EXTRA, new CameraEventRes(cameraState));
                LocalBroadcastManager.getInstance(iDOBaseBleApiImpl.f2962a).sendBroadcast(intent2);
                return;
            case 8:
                intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.VOLUME_UP));
                LocalBroadcastManager.getInstance(iDOBaseBleApiImpl.f2962a).sendBroadcast(intent);
                return;
            case 9:
                intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.VOLUME_DOWN));
                LocalBroadcastManager.getInstance(iDOBaseBleApiImpl.f2962a).sendBroadcast(intent);
                return;
            case 10:
                intent2.putExtra(Constants.CAMERA_BROADCAST_INTENT_EXTRA, new CameraEventRes(CameraState.ENTER));
                LocalBroadcastManager.getInstance(iDOBaseBleApiImpl.f2962a).sendBroadcast(intent2);
                return;
            case 11:
                intent2.putExtra(Constants.CAMERA_BROADCAST_INTENT_EXTRA, new CameraEventRes(CameraState.EXIT));
                LocalBroadcastManager.getInstance(iDOBaseBleApiImpl.f2962a).sendBroadcast(intent2);
                return;
            case 12:
                CallRejectRes callRejectRes = new CallRejectRes(false);
                callRejectRes.shouldReject = false;
                Intent intent3 = new Intent(Constants.CALL_REJECT_BROADCAST_INTENT);
                intent3.putExtra(Constants.CALL_REJECT__BROADCAST_INTENT_EXTRA, callRejectRes);
                LocalBroadcastManager.getInstance(iDOBaseBleApiImpl.f2962a).sendBroadcast(intent3);
                return;
            case 13:
                CallRejectRes callRejectRes2 = new CallRejectRes(true);
                callRejectRes2.shouldReject = true;
                Intent intent4 = new Intent(Constants.CALL_REJECT_BROADCAST_INTENT);
                intent4.putExtra(Constants.CALL_REJECT__BROADCAST_INTENT_EXTRA, callRejectRes2);
                LocalBroadcastManager.getInstance(iDOBaseBleApiImpl.f2962a).sendBroadcast(intent4);
                return;
        }
    }

    public static final void access$registerCallbacks(IDOBaseBleApiImpl iDOBaseBleApiImpl) {
        iDOBaseBleApiImpl.a();
        BLEManager.registerGetDeviceInfoCallBack(iDOBaseBleApiImpl.y);
        BLEManager.registerSettingCallBack(iDOBaseBleApiImpl.x);
        BLEManager.registerSyncV3CallBack(iDOBaseBleApiImpl.A);
        BLEManager.registerPhoneMsgNoticeCallBack(iDOBaseBleApiImpl.z);
        BLEManager.registerDeviceControlAppCallBack(iDOBaseBleApiImpl.B);
        BLEManager.registerAppSendDataCallBack(iDOBaseBleApiImpl.C);
        BLEManager.registerWatchOperateCallBack(iDOBaseBleApiImpl.D);
        BLEManager.registerGetDeviceParaCallBack(iDOBaseBleApiImpl.E);
        BLEManager.registerOtherProtocolCallBack(iDOBaseBleApiImpl.F);
        BLEManager.registerOperateCallBack(iDOBaseBleApiImpl.G);
    }

    public static final void access$scanResultRecieved(IDOBaseBleApiImpl iDOBaseBleApiImpl, List list, boolean z, ScanDeviceRequest scanDeviceRequest, ScanResultListener scanResultListener) {
        iDOBaseBleApiImpl.getClass();
        ScanDeviceResponse scanDeviceResponse = new ScanDeviceResponse(scanDeviceRequest);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            BleDevice bleDevice = (BleDevice) it.next();
            arrayList.add(new com.coveiot.android.bleabstract.models.BleDevice(bleDevice.getmDevice(), bleDevice.getRssi()));
        }
        scanDeviceResponse.setBluetoothDevices(arrayList);
        scanDeviceResponse.setScanComplete(z);
        if (!scanDeviceRequest.isShouldProvideBatchResult()) {
            scanResultListener.onResponse(scanDeviceResponse);
        } else if (z) {
            scanResultListener.onResponse(scanDeviceResponse);
        }
    }

    public static final void access$sendDisconnectResponse(IDOBaseBleApiImpl iDOBaseBleApiImpl) {
        iDOBaseBleApiImpl.getClass();
        if (BLEManager.isConnected()) {
            BLEManager.disConnect();
        }
        PreferenceManagerAbstract.getInstance(iDOBaseBleApiImpl.f2962a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        iDOBaseBleApiImpl.g = connectionStatus;
        MutableLiveData<ConnectionStatus> mutableLiveData = iDOBaseBleApiImpl.e;
        if (mutableLiveData != null) {
            mutableLiveData.setValue(connectionStatus);
            MutableLiveData<ConnectionStatus> mutableLiveData2 = iDOBaseBleApiImpl.e;
            if (mutableLiveData2 != null) {
                mutableLiveData2.postValue(iDOBaseBleApiImpl.g);
            }
        }
        ConnectionResultListener connectionResultListener = iDOBaseBleApiImpl.d;
        if (connectionResultListener != null) {
            connectionResultListener.onConnectionResponse(iDOBaseBleApiImpl.getConnectionStatus());
        }
    }

    public static final void access$setTime(IDOBaseBleApiImpl iDOBaseBleApiImpl) {
        iDOBaseBleApiImpl.getClass();
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(5);
        int i3 = calendar.get(11);
        int i4 = calendar.get(12);
        int i5 = calendar.get(13);
        SystemTime systemTime = new SystemTime();
        systemTime.year = i;
        systemTime.monuth = calendar.get(2) + 1;
        systemTime.day = i2;
        systemTime.hour = i3;
        systemTime.minute = i4;
        systemTime.second = i5;
        BLEManager.setTime(systemTime);
    }

    public static final void b(DataResultListener dataResultListener, BleBaseResponse sleepRes) {
        Intrinsics.checkNotNullParameter(sleepRes, "$sleepRes");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sleepRes);
    }

    public static final void d(DataResultListener dataResultListener, BleBaseResponse spo2BaseResponse) {
        Intrinsics.checkNotNullParameter(spo2BaseResponse, "$spo2BaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(spo2BaseResponse);
    }

    public static final void e(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void f(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void g(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void h(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public static final void i(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public static final void j(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public static final void k(DataResultListener dataResultListener, BleBaseResponse deviceres) {
        Intrinsics.checkNotNullParameter(deviceres, "$deviceres");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(deviceres);
    }

    public static final void l(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void m(DataResultListener dataResultListener, BleBaseResponse getWFListResponse) {
        Intrinsics.checkNotNullParameter(getWFListResponse, "$getWFListResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(getWFListResponse);
    }

    public static final void n(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void o(DataResultListener dataResultListener, BleBaseResponse supportedSportResponse) {
        Intrinsics.checkNotNullParameter(supportedSportResponse, "$supportedSportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(supportedSportResponse);
    }

    public static final void p(DataResultListener dataResultListener, BleBaseResponse supportedSportResponse) {
        Intrinsics.checkNotNullParameter(supportedSportResponse, "$supportedSportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(supportedSportResponse);
    }

    public static final void q(DataResultListener dataResultListener, BleBaseResponse supportedSportResponse) {
        Intrinsics.checkNotNullParameter(supportedSportResponse, "$supportedSportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(supportedSportResponse);
    }

    public static final void r(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void s(DataResultListener dataResultListener, BleBaseResponse stepRes) {
        Intrinsics.checkNotNullParameter(stepRes, "$stepRes");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(stepRes);
    }

    public static final void t(DataResultListener dataResultListener, BleBaseResponse stepRes) {
        Intrinsics.checkNotNullParameter(stepRes, "$stepRes");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(stepRes);
    }

    public static final void u(DataResultListener dataResultListener, BleBaseResponse stepRes) {
        Intrinsics.checkNotNullParameter(stepRes, "$stepRes");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(stepRes);
    }

    public static final void v(DataResultListener dataResultListener, BleBaseResponse mSleepRes) {
        Intrinsics.checkNotNullParameter(mSleepRes, "$mSleepRes");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mSleepRes);
    }

    public final void a(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        this.i.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(this.f2962a).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$scan_$1
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(@NotNull CharSequence error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ScanResultListener scanResultListener2 = scanResultListener;
                        String string = IDOBaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                        scanResultListener2.onError(string);
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(@NotNull String messgae) {
                        Intrinsics.checkNotNullParameter(messgae, "messgae");
                        IDOBaseBleApiImpl.access$scanResultRecieved(IDOBaseBleApiImpl.this, new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.i.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.i2
            @Override // java.lang.Runnable
            public final void run() {
                IDOBaseBleApiImpl.a(IDOBaseBleApiImpl.this, scanDeviceRequest, scanResultListener);
            }
        }, com.clevertap.android.sdk.Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void addToQueue(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        synchronized (this.f) {
            IDOBaseReq a2 = a(baseRequest);
            if (a2 != null) {
                String str = this.b;
                LogHelper.i(str, "--addToQueue baseRequest --" + baseRequest);
                if (a2.isPriority()) {
                    this.f.addFirst(new QueueObject(a2.getCommandName(), baseRequest, a2.getTimeOut(), null, 8, null));
                } else {
                    this.f.add(new QueueObject(a2.getCommandName(), baseRequest, a2.getTimeOut(), null, 8, null));
                }
            } else {
                sendCommandNotFoundError(baseRequest);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0027, code lost:
        r6.f.remove(r2).getBaseRequest();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(com.coveiot.android.bleabstract.request.BleBaseRequest r7) {
        /*
            r6 = this;
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$QueueObject> r0 = r6.f
            monitor-enter(r0)
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$QueueObject> r1 = r6.f     // Catch: java.lang.Throwable -> L38
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L38
            r2 = 0
        La:
            if (r2 >= r1) goto L36
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$QueueObject> r3 = r6.f     // Catch: java.lang.Throwable -> L38
            java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.Throwable -> L38
            com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$QueueObject r3 = (com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.QueueObject) r3     // Catch: java.lang.Throwable -> L38
            com.coveiot.android.bleabstract.request.BleBaseRequest r3 = r3.getBaseRequest()     // Catch: java.lang.Throwable -> L38
            java.lang.String r3 = r3.getRequId()     // Catch: java.lang.Throwable -> L38
            java.lang.String r4 = r7.getRequId()     // Catch: java.lang.Throwable -> L38
            r5 = 1
            boolean r3 = kotlin.text.m.equals(r3, r4, r5)     // Catch: java.lang.Throwable -> L38
            if (r3 == 0) goto L33
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$QueueObject> r7 = r6.f     // Catch: java.lang.Throwable -> L38
            java.lang.Object r7 = r7.remove(r2)     // Catch: java.lang.Throwable -> L38
            com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$QueueObject r7 = (com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.QueueObject) r7     // Catch: java.lang.Throwable -> L38
            r7.getBaseRequest()     // Catch: java.lang.Throwable -> L38
            goto L36
        L33:
            int r2 = r2 + 1
            goto La
        L36:
            monitor-exit(r0)
            return
        L38:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.c(com.coveiot.android.bleabstract.request.BleBaseRequest):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return false;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void cleanUpCommands() {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void clearCommandQueue() {
        synchronized (this.f) {
            LinkedList<QueueObject> linkedList = this.f;
            if (linkedList != null && linkedList.size() > 0) {
                this.f.clear();
                LogHelper.d(this.b, "clearCommandQueue");
            }
        }
        this.q.removeCallbacksAndMessages(null);
        this.p = null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(@NotNull ConnectRequest request, @NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d = listener;
        BluetoothDevice remoteDevice = this.c.getRemoteDevice(request.getMacAddress());
        BLEDevice bLEDevice = new BLEDevice();
        BlePreferenceManager.savePreference(this.f2962a, CommonPreference.BLE_DEVICE_ADDRESS, request.getMacAddress());
        bLEDevice.mDeviceAddress = request.getMacAddress();
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this.f2962a, "android.permission.BLUETOOTH_CONNECT") == 0) {
            bLEDevice.mDeviceName = remoteDevice.getName();
            PreferenceManagerAbstract.getInstance(this.f2962a).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
            BLEManager.registerConnectCallBack(this.t);
            BLEManager.connect(bLEDevice);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listener.onConnectionResponse(ConnectionStatus.DISCONNECTED);
        LogHelper.d(this.b, "disconnect called");
        this.d = listener;
        PreferenceManagerAbstract.getInstance(this.f2962a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        clearCommandQueue();
        BLEManager.disConnect();
        BLEManager.unregisterConnectCallBack(this.t);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return BusyStatus.IDLE;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        return new ConnectionInfo(this.g, new ConnectionError(0, 0L), System.currentTimeMillis());
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public ConnectionStatus getConnectionStatus() {
        return this.g;
    }

    @NotNull
    public final Context getContext() {
        return this.f2962a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (BLEManager.isConnected()) {
            request.setRequId(UUID.randomUUID().toString());
            request.setResponseListener(listener);
            addToQueue(request);
            sendCommandRequest();
            return;
        }
        String string = this.f2962a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onDataError(new BleBaseError(string));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        if (this.s == null) {
            DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
            this.s = deviceSupportedFeatures;
            deviceSupportedFeatures.setSleepSupported(true);
            DeviceSupportedFeatures deviceSupportedFeatures2 = this.s;
            if (deviceSupportedFeatures2 != null) {
                deviceSupportedFeatures2.setStepsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures3 = this.s;
            if (deviceSupportedFeatures3 != null) {
                deviceSupportedFeatures3.setHeartRateSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures4 = this.s;
            if (deviceSupportedFeatures4 != null) {
                deviceSupportedFeatures4.setSedentaryReminderSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures5 = this.s;
            if (deviceSupportedFeatures5 != null) {
                deviceSupportedFeatures5.setPersonalInfoSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures6 = this.s;
            if (deviceSupportedFeatures6 != null) {
                deviceSupportedFeatures6.setStepGoalSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures7 = this.s;
            if (deviceSupportedFeatures7 != null) {
                deviceSupportedFeatures7.setCallNotificationSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures8 = this.s;
            if (deviceSupportedFeatures8 != null) {
                deviceSupportedFeatures8.setSmsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures9 = this.s;
            if (deviceSupportedFeatures9 != null) {
                deviceSupportedFeatures9.setMessageReadSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures10 = this.s;
            if (deviceSupportedFeatures10 != null) {
                deviceSupportedFeatures10.setSocialNotificationSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures11 = this.s;
            if (deviceSupportedFeatures11 != null) {
                deviceSupportedFeatures11.setHandSettingsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures12 = this.s;
            if (deviceSupportedFeatures12 != null) {
                deviceSupportedFeatures12.setPersonalInfoSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures13 = this.s;
            if (deviceSupportedFeatures13 != null) {
                deviceSupportedFeatures13.setLiveHeartRateSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures14 = this.s;
            if (deviceSupportedFeatures14 != null) {
                deviceSupportedFeatures14.setLiveStepsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures15 = this.s;
            if (deviceSupportedFeatures15 != null) {
                deviceSupportedFeatures15.setLiveBPSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures16 = this.s;
            if (deviceSupportedFeatures16 != null) {
                deviceSupportedFeatures16.setSportModeSupportedFromApp(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures17 = this.s;
            if (deviceSupportedFeatures17 != null) {
                deviceSupportedFeatures17.setBatteryLevelRequestSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures18 = this.s;
            if (deviceSupportedFeatures18 != null) {
                deviceSupportedFeatures18.setBandSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures19 = this.s;
            if (deviceSupportedFeatures19 != null) {
                deviceSupportedFeatures19.setDistanceUnitSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures20 = this.s;
            if (deviceSupportedFeatures20 != null) {
                deviceSupportedFeatures20.setLiftWristToViewSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures21 = this.s;
            if (deviceSupportedFeatures21 != null) {
                deviceSupportedFeatures21.setVibrationAlarmSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures22 = this.s;
            if (deviceSupportedFeatures22 != null) {
                deviceSupportedFeatures22.setREMSupportedInSleep(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures23 = this.s;
            if (deviceSupportedFeatures23 != null) {
                deviceSupportedFeatures23.setPhoneFinderSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures24 = this.s;
            if (deviceSupportedFeatures24 != null) {
                deviceSupportedFeatures24.setCameraFeatureSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures25 = this.s;
            if (deviceSupportedFeatures25 != null) {
                deviceSupportedFeatures25.setManualSpo2SupportedOnBand(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures26 = this.s;
            if (deviceSupportedFeatures26 != null) {
                deviceSupportedFeatures26.setTimeFormatSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures27 = this.s;
            if (deviceSupportedFeatures27 != null) {
                deviceSupportedFeatures27.setAutoHrSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures28 = this.s;
            if (deviceSupportedFeatures28 != null) {
                deviceSupportedFeatures28.setMultipleAlarmsSupportedAtATime(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures29 = this.s;
            if (deviceSupportedFeatures29 != null) {
                deviceSupportedFeatures29.setFemaleWellnessSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures30 = this.s;
            if (deviceSupportedFeatures30 != null) {
                deviceSupportedFeatures30.setScheduledDndSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures31 = this.s;
            if (deviceSupportedFeatures31 != null) {
                deviceSupportedFeatures31.setWeatherSupportedInBand(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures32 = this.s;
            if (deviceSupportedFeatures32 != null) {
                deviceSupportedFeatures32.setMaxDaysOfStepsDataOnBand(7);
            }
            DeviceSupportedFeatures deviceSupportedFeatures33 = this.s;
            if (deviceSupportedFeatures33 != null) {
                deviceSupportedFeatures33.setMaxDaysOfSleepDataOnBand(7);
            }
            DeviceSupportedFeatures deviceSupportedFeatures34 = this.s;
            if (deviceSupportedFeatures34 != null) {
                deviceSupportedFeatures34.setMaxDaysOfHeartRateDataOnBand(7);
            }
            DeviceSupportedFeatures deviceSupportedFeatures35 = this.s;
            if (deviceSupportedFeatures35 != null) {
                deviceSupportedFeatures35.setRepeatDaysSupportedInSedentary(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures36 = this.s;
            if (deviceSupportedFeatures36 != null) {
                deviceSupportedFeatures36.setSnoozeSettingsSupportedInAlarm(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures37 = this.s;
            if (deviceSupportedFeatures37 != null) {
                deviceSupportedFeatures37.setActivityShowHideCommandSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures38 = this.s;
            if (deviceSupportedFeatures38 != null) {
                deviceSupportedFeatures38.setShortcutMenuShowHideCommandSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures39 = this.s;
            if (deviceSupportedFeatures39 != null) {
                deviceSupportedFeatures39.setStressHistorySupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures40 = this.s;
            if (deviceSupportedFeatures40 != null) {
                deviceSupportedFeatures40.setSportsModeHistorySupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures41 = this.s;
            if (deviceSupportedFeatures41 != null) {
                deviceSupportedFeatures41.setSampleDataSupportedInSportMode(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures42 = this.s;
            if (deviceSupportedFeatures42 != null) {
                deviceSupportedFeatures42.setSyncBandSettingsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures43 = this.s;
            if (deviceSupportedFeatures43 != null) {
                deviceSupportedFeatures43.setSportModeSupportedFromApp(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures44 = this.s;
            if (deviceSupportedFeatures44 != null) {
                deviceSupportedFeatures44.setMaxAlarmSupportedOnBand(10);
            }
            DeviceSupportedFeatures deviceSupportedFeatures45 = this.s;
            if (deviceSupportedFeatures45 != null) {
                deviceSupportedFeatures45.setMusicMetaDataChangeFromAppSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures46 = this.s;
            if (deviceSupportedFeatures46 != null) {
                deviceSupportedFeatures46.setSleepScoreSupportsFromBand(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures47 = this.s;
            if (deviceSupportedFeatures47 != null) {
                deviceSupportedFeatures47.setActiveTimeSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures48 = this.s;
            if (deviceSupportedFeatures48 != null) {
                deviceSupportedFeatures48.setDrinkingReminderSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures49 = this.s;
            if (deviceSupportedFeatures49 != null) {
                deviceSupportedFeatures49.setActivityAutoRecognitionSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures50 = this.s;
            if (deviceSupportedFeatures50 != null) {
                deviceSupportedFeatures50.setBandVolumeControlSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures51 = this.s;
            if (deviceSupportedFeatures51 != null) {
                deviceSupportedFeatures51.setCalorieDistanceGoalGetSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures52 = this.s;
            if (deviceSupportedFeatures52 != null) {
                deviceSupportedFeatures52.setAutoStressSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures53 = this.s;
            if (deviceSupportedFeatures53 != null) {
                deviceSupportedFeatures53.setGenericActivityDataSampleSupported(true);
            }
        }
        DeviceSupportedFeatures deviceSupportedFeatures54 = this.s;
        Intrinsics.checkNotNull(deviceSupportedFeatures54);
        return deviceSupportedFeatures54;
    }

    @Nullable
    public final BleBaseRequest getFromQueue(@NotNull IDOBleCommandName commandName) {
        Intrinsics.checkNotNullParameter(commandName, "commandName");
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            if (this.f.get(i).getBleCommandName() == commandName) {
                return this.f.get(i).getBaseRequest();
            }
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        Object preference = BlePreferenceManager.getPreference(this.f2962a, CommonPreference.BLE_DEVICE_ADDRESS, "");
        Intrinsics.checkNotNullExpressionValue(preference, "getPreference(\n         …ICE_ADDRESS, \"\"\n        )");
        return (String) preference;
    }

    @NotNull
    public final MutableLiveData<LiveAGPSUploadPercentage> getMutableLiveAGPSUploadData() {
        return this.n;
    }

    @NotNull
    public final MutableLiveData<LiveHealthData> getMutableLiveHealthData() {
        return this.j;
    }

    @NotNull
    public final MutableLiveData<LiveStepsData> getMutableLiveStepsData() {
        return this.k;
    }

    @NotNull
    public final MutableLiveData<LiveTemperatureData> getMutableLiveTemperatureData() {
        return this.l;
    }

    @NotNull
    public final MutableLiveData<LiveWatchFaceUploadPercentage> getMutableLiveWatchFaceUploadData() {
        return this.m;
    }

    public final String getTAG() {
        return this.b;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return BLEManager.isBind();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Override // com.coveiot.android.idoSdk.IDOResponseListener
    public void onFailure(@NotNull IDOError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        IDOErrorType errorType = error.getErrorType();
        int i = errorType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[errorType.ordinal()];
        if (i == 1 || i == 2) {
            sendErrorAndClearQueue(error);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v14, types: [com.coveiot.android.bleabstract.models.DNDData, T] */
    @Override // com.coveiot.android.idoSdk.IDOResponseListener
    public void onResponse(@NotNull IDOBaseRes response) {
        AmbientSoundResponse ambientSoundResponse;
        List<String> list;
        List list2;
        StressResponse stressResponse;
        HeartRateResponse heartRateResponse;
        List<KHHealthSportV3Item> items;
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            final BleBaseRequest fromQueue = getFromQueue(response.getBaseReq().getCommandName());
            if (fromQueue != null) {
                boolean z = true;
                if (fromQueue.getResponseListener() instanceof DataResultListener) {
                    final DataResultListener dataResultListener = (DataResultListener) fromQueue.getResponseListener();
                    IDOBaseReq baseReq = response.getBaseReq();
                    if (baseReq instanceof IDOStepsDataReq) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null) {
                            if (response.getObj() instanceof EntityHealthSportV3) {
                                Object obj = response.getObj();
                                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.coveiot.khidodb.walk.EntityHealthSportV3");
                                EntityHealthSportV3 entityHealthSportV3 = (EntityHealthSportV3) obj;
                                Intrinsics.checkNotNull(entityHealthSportV3.getItems());
                                if (!items.isEmpty()) {
                                    StepsResponse stepResponse = IDOStepsFormatter.Companion.getInstance(this.f2962a).getStepResponse(entityHealthSportV3);
                                    StepsDayData stepsDayData = stepResponse.getStepsDayData();
                                    if (stepsDayData != null) {
                                        stepsDayData.mMacAddress = (String) BlePreferenceManager.getPreference(this.f2962a, CommonPreference.BLE_DEVICE_ADDRESS, "");
                                    }
                                    stepResponse.setCaloriesDistanceCalculatedFromBand(true);
                                    stepResponse.setDistanceIsInMetresFromBand(true);
                                    stepResponse.setComplete(response.isComplete());
                                    final BleBaseResponse bleBaseResponse = new BleBaseResponse(fromQueue);
                                    bleBaseResponse.setResponseData(stepResponse);
                                    this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.j2
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            IDOBaseBleApiImpl.s(DataResultListener.this, bleBaseResponse);
                                        }
                                    });
                                } else {
                                    StepsResponse stepsResponse = new StepsResponse();
                                    stepsResponse.setComplete(true);
                                    final BleBaseResponse bleBaseResponse2 = new BleBaseResponse(fromQueue);
                                    bleBaseResponse2.setResponseData(stepsResponse);
                                    this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.k2
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            IDOBaseBleApiImpl.t(DataResultListener.this, bleBaseResponse2);
                                        }
                                    });
                                    setCompleteAndProcessNext(fromQueue);
                                }
                                if (response.isComplete()) {
                                    setCompleteAndProcessNext(fromQueue);
                                    kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$onResponse$3(this, null), 2, null);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        StepsResponse stepsResponse2 = new StepsResponse();
                        stepsResponse2.setComplete(true);
                        final BleBaseResponse bleBaseResponse3 = new BleBaseResponse(fromQueue);
                        bleBaseResponse3.setResponseData(stepsResponse2);
                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.l2
                            @Override // java.lang.Runnable
                            public final void run() {
                                IDOBaseBleApiImpl.u(DataResultListener.this, bleBaseResponse3);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                        return;
                    }
                    int i = 0;
                    if (baseReq instanceof IDOSleepDataReq) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null) {
                            Object obj2 = response.getObj();
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.khidodb.sleep.EntityHealthSleepV3>");
                            ArrayList<SleepResponse> convertSleepData = IDOSleepFormatter.Companion.getInstance(this.f2962a).convertSleepData((List) obj2);
                            if (convertSleepData != null && convertSleepData.size() > 0) {
                                int size = convertSleepData.size();
                                int i2 = 0;
                                while (i2 < size) {
                                    SleepResponse sleepResponse = convertSleepData.get(i2);
                                    Intrinsics.checkNotNullExpressionValue(sleepResponse, "sleepsResponseList[i]");
                                    SleepResponse sleepResponse2 = sleepResponse;
                                    sleepResponse2.setComplete(i2 == convertSleepData.size() - 1);
                                    final BleBaseResponse bleBaseResponse4 = new BleBaseResponse(fromQueue);
                                    bleBaseResponse4.setResponseData(sleepResponse2);
                                    this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.m2
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            IDOBaseBleApiImpl.v(DataResultListener.this, bleBaseResponse4);
                                        }
                                    });
                                    if (sleepResponse2.isComplete()) {
                                        setCompleteAndProcessNext(fromQueue);
                                        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$onResponse$6(this, null), 2, null);
                                    }
                                    i2++;
                                }
                                return;
                            }
                            SleepResponse sleepResponse3 = new SleepResponse();
                            sleepResponse3.setComplete(true);
                            final BleBaseResponse bleBaseResponse5 = new BleBaseResponse(fromQueue);
                            bleBaseResponse5.setResponseData(sleepResponse3);
                            this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.t2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    IDOBaseBleApiImpl.a(DataResultListener.this, bleBaseResponse5);
                                }
                            });
                            setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        SleepResponse sleepResponse4 = new SleepResponse();
                        sleepResponse4.setComplete(true);
                        final BleBaseResponse bleBaseResponse6 = new BleBaseResponse(fromQueue);
                        bleBaseResponse6.setResponseData(sleepResponse4);
                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.b3
                            @Override // java.lang.Runnable
                            public final void run() {
                                IDOBaseBleApiImpl.b(DataResultListener.this, bleBaseResponse6);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOHeartRateDataReq) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null) {
                            Object obj3 = response.getObj();
                            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.khidodb.heartrate.EntityHealthHeartRateSecond>");
                            List<EntityHealthHeartRateSecond> list3 = (List) obj3;
                            if (!list3.isEmpty()) {
                                HashMap hashMap = new HashMap();
                                for (EntityHealthHeartRateSecond entityHealthHeartRateSecond : list3) {
                                    String key = AppUtils.formatDate(entityHealthHeartRateSecond.getDate(), "yyyy-MM-dd");
                                    if (!hashMap.containsKey(key)) {
                                        Intrinsics.checkNotNullExpressionValue(key, "key");
                                        hashMap.put(key, CollectionsKt__CollectionsKt.arrayListOf(entityHealthHeartRateSecond));
                                    } else {
                                        ArrayList arrayList = (ArrayList) hashMap.get(key);
                                        if (arrayList != null) {
                                            arrayList.add(entityHealthHeartRateSecond);
                                        }
                                        Intrinsics.checkNotNullExpressionValue(key, "key");
                                        Intrinsics.checkNotNull(arrayList);
                                        hashMap.put(key, arrayList);
                                    }
                                }
                                Set keySet = hashMap.keySet();
                                Intrinsics.checkNotNullExpressionValue(keySet, "entityHashMap.keys");
                                List<String> sorted = CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(keySet));
                                for (String str : sorted) {
                                    final BleBaseResponse bleBaseResponse7 = new BleBaseResponse(fromQueue);
                                    ArrayList arrayList2 = (ArrayList) hashMap.get(str);
                                    if (arrayList2 != null) {
                                        heartRateResponse = IDOHeartRateFormatter.Companion.getInstance(this.f2962a).convertEntityHeartRateToHeartRateResponse(CollectionsKt___CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$onResponse$lambda$13$$inlined$sortedBy$1
                                            @Override // java.util.Comparator
                                            public final int compare(T t, T t2) {
                                                return kotlin.comparisons.f.compareValues(Integer.valueOf(((EntityHealthHeartRateSecond) t).getStartTime()), Integer.valueOf(((EntityHealthHeartRateSecond) t2).getStartTime()));
                                            }
                                        }));
                                        heartRateResponse.setComplete(Intrinsics.areEqual(str, sorted.get(sorted.size() - 1)));
                                    } else {
                                        heartRateResponse = null;
                                    }
                                    bleBaseResponse7.setResponseData(heartRateResponse);
                                    this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.c3
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            IDOBaseBleApiImpl.c(DataResultListener.this, bleBaseResponse7);
                                        }
                                    });
                                }
                                setCompleteAndProcessNext(fromQueue);
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$onResponse$11(this, null), 2, null);
                                return;
                            }
                            this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.o2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    IDOBaseBleApiImpl.a(BleBaseRequest.this, dataResultListener);
                                }
                            });
                            setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.p2
                            @Override // java.lang.Runnable
                            public final void run() {
                                IDOBaseBleApiImpl.b(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOSPO2DataReq) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null) {
                            Object obj4 = response.getObj();
                            Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.khidodb.spo2.EntityHealthSpo2>");
                            List list4 = (List) obj4;
                            if (!list4.isEmpty()) {
                                int size2 = list4.size();
                                int i3 = 0;
                                while (i3 < size2) {
                                    response.setComplete(i3 == list4.size() - 1);
                                    EntityHealthSpo2 entityHealthSpo2 = (EntityHealthSpo2) list4.get(i3);
                                    if (entityHealthSpo2 != null) {
                                        ReadManualSpo2Response sPO2Data = IDOSPO2Formatter.Companion.getInstance(this.f2962a).getSPO2Data(entityHealthSpo2);
                                        final BleBaseResponse bleBaseResponse8 = new BleBaseResponse(fromQueue);
                                        bleBaseResponse8.setResponseData(sPO2Data);
                                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.d3
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                IDOBaseBleApiImpl.d(DataResultListener.this, bleBaseResponse8);
                                            }
                                        });
                                    }
                                    i3++;
                                }
                            } else {
                                final BleBaseResponse bleBaseResponse9 = new BleBaseResponse(fromQueue);
                                ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(new ArrayList());
                                readManualSpo2Response.setComplete(true);
                                bleBaseResponse9.setResponseData(readManualSpo2Response);
                                this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.e3
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        IDOBaseBleApiImpl.e(DataResultListener.this, bleBaseResponse9);
                                    }
                                });
                                setCompleteAndProcessNext(fromQueue);
                            }
                            if (response.isComplete()) {
                                setCompleteAndProcessNext(fromQueue);
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$onResponse$16(this, null), 2, null);
                                return;
                            }
                            return;
                        }
                        final BleBaseResponse bleBaseResponse10 = new BleBaseResponse(fromQueue);
                        ReadManualSpo2Response readManualSpo2Response2 = new ReadManualSpo2Response(new ArrayList());
                        readManualSpo2Response2.setComplete(true);
                        bleBaseResponse10.setResponseData(readManualSpo2Response2);
                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.f3
                            @Override // java.lang.Runnable
                            public final void run() {
                                IDOBaseBleApiImpl.f(DataResultListener.this, bleBaseResponse10);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOStressDataReq) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null) {
                            Object obj5 = response.getObj();
                            Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.khidodb.stress.EntityHealthPressure>");
                            List<EntityHealthPressure> list5 = (List) obj5;
                            if (!list5.isEmpty()) {
                                HashMap hashMap2 = new HashMap();
                                for (EntityHealthPressure entityHealthPressure : list5) {
                                    String key2 = AppUtils.formatDate(entityHealthPressure.getDate(), "yyyy-MM-dd");
                                    if (!hashMap2.containsKey(key2)) {
                                        Intrinsics.checkNotNullExpressionValue(key2, "key");
                                        hashMap2.put(key2, CollectionsKt__CollectionsKt.arrayListOf(entityHealthPressure));
                                    } else {
                                        ArrayList arrayList3 = (ArrayList) hashMap2.get(key2);
                                        if (arrayList3 != null) {
                                            arrayList3.add(entityHealthPressure);
                                        }
                                        Intrinsics.checkNotNullExpressionValue(key2, "key");
                                        Intrinsics.checkNotNull(arrayList3);
                                        hashMap2.put(key2, arrayList3);
                                    }
                                }
                                Set keySet2 = hashMap2.keySet();
                                Intrinsics.checkNotNullExpressionValue(keySet2, "entityHashMap.keys");
                                List<String> sorted2 = CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(keySet2));
                                for (String str2 : sorted2) {
                                    final BleBaseResponse bleBaseResponse11 = new BleBaseResponse(fromQueue);
                                    ArrayList arrayList4 = (ArrayList) hashMap2.get(str2);
                                    if (arrayList4 != null) {
                                        stressResponse = IDOStressFormatter.Companion.getInstance(this.f2962a).convertEntityPressureToStressResponse(CollectionsKt___CollectionsKt.sortedWith(arrayList4, new Comparator() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$onResponse$lambda$21$$inlined$sortedBy$1
                                            @Override // java.util.Comparator
                                            public final int compare(T t, T t2) {
                                                return kotlin.comparisons.f.compareValues(Integer.valueOf(((EntityHealthPressure) t).getStartTime()), Integer.valueOf(((EntityHealthPressure) t2).getStartTime()));
                                            }
                                        }));
                                        stressResponse.setComplete(Intrinsics.areEqual(str2, sorted2.get(sorted2.size() - 1)));
                                    } else {
                                        stressResponse = null;
                                    }
                                    bleBaseResponse11.setResponseData(stressResponse);
                                    this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.g3
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            IDOBaseBleApiImpl.g(DataResultListener.this, bleBaseResponse11);
                                        }
                                    });
                                }
                                setCompleteAndProcessNext(fromQueue);
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$onResponse$20(this, null), 2, null);
                                return;
                            }
                            this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.q2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    IDOBaseBleApiImpl.c(BleBaseRequest.this, dataResultListener);
                                }
                            });
                            setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.r2
                            @Override // java.lang.Runnable
                            public final void run() {
                                IDOBaseBleApiImpl.d(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOSportsDataReq) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null) {
                            if (response.getObj() instanceof List) {
                                Object obj6 = response.getObj();
                                Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.khidodb.activities.EntityHealthActivityV3>");
                                List list6 = (List) obj6;
                                if (response.getObj2() != null) {
                                    Object obj22 = response.getObj2();
                                    Intrinsics.checkNotNull(obj22, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.khidodb.activities.EntityHealthSwimV3>");
                                    list2 = (List) obj22;
                                } else {
                                    list2 = null;
                                }
                                List<ActivityModeSummaryResponse> activityModeSummaryData = IDOActivityFormatter.Companion.getInstance(this.f2962a).getActivityModeSummaryData(list6, list2);
                                if (!activityModeSummaryData.isEmpty() && activityModeSummaryData.size() != 0) {
                                    int size3 = activityModeSummaryData.size();
                                    int i4 = 0;
                                    while (i4 < size3) {
                                        ActivityModeSummaryResponse activityModeSummaryResponse = activityModeSummaryData.get(i4);
                                        activityModeSummaryResponse.setComplete(i4 == activityModeSummaryData.size() - 1);
                                        final BleBaseResponse bleBaseResponse12 = new BleBaseResponse(fromQueue);
                                        bleBaseResponse12.setResponseData(activityModeSummaryResponse);
                                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.y1
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                IDOBaseBleApiImpl.i(DataResultListener.this, bleBaseResponse12);
                                            }
                                        });
                                        if (activityModeSummaryResponse.isComplete()) {
                                            setCompleteAndProcessNext(fromQueue);
                                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$onResponse$25(this, null), 2, null);
                                        }
                                        i4++;
                                    }
                                    return;
                                }
                                final BleBaseResponse bleBaseResponse13 = new BleBaseResponse(fromQueue);
                                bleBaseResponse13.setResponseData(null);
                                this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.h3
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        IDOBaseBleApiImpl.h(DataResultListener.this, bleBaseResponse13);
                                    }
                                });
                                setCompleteAndProcessNext(fromQueue);
                                return;
                            }
                            return;
                        }
                        final BleBaseResponse bleBaseResponse14 = new BleBaseResponse(fromQueue);
                        bleBaseResponse14.setResponseData(null);
                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.z1
                            @Override // java.lang.Runnable
                            public final void run() {
                                IDOBaseBleApiImpl.j(DataResultListener.this, bleBaseResponse14);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDODIYWatchFaceReq ? true : baseReq instanceof IDOCloudWatchFaceReq) {
                        LogHelper.d(this.b, "IDODIYWatchFaceReq or IDOCloudWatchFaceReq == " + response.getObj());
                        if (response.getObj() != null && (response.getObj() instanceof LiveWatchFaceUploadPercentage)) {
                            ProgressType progressType = ProgressType.DETERMINATE;
                            Object obj7 = response.getObj();
                            Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage");
                            ProgressData progressData = new ProgressData(progressType, ((LiveWatchFaceUploadPercentage) obj7).getPercentage(), fromQueue);
                            Intrinsics.checkNotNull(dataResultListener);
                            dataResultListener.onProgressUpdate(progressData);
                            return;
                        }
                        this.q.removeCallbacksAndMessages(null);
                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.s2
                            @Override // java.lang.Runnable
                            public final void run() {
                                IDOBaseBleApiImpl.e(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDODeviceInfoReq) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null) {
                            Object obj8 = response.getObj();
                            Intrinsics.checkNotNull(obj8, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoData");
                            DeviceInfoData deviceInfoData = (DeviceInfoData) obj8;
                            DeviceInfoResponse deviceInfoResponse = new DeviceInfoResponse();
                            if (this.h == null) {
                                this.h = new DeviceInfoData();
                            }
                            DeviceInfoData deviceInfoData2 = this.h;
                            Intrinsics.checkNotNull(deviceInfoData2);
                            deviceInfoData2.setMacAddress(deviceInfoData.getMacAddress());
                            DeviceInfoData deviceInfoData3 = this.h;
                            Intrinsics.checkNotNull(deviceInfoData3);
                            deviceInfoData3.setFwVersion('V' + deviceInfoData.getFwVersion());
                            deviceInfoResponse.setDeviceInfo(this.h);
                            deviceInfoResponse.setComplete(true);
                            final BleBaseResponse bleBaseResponse15 = new BleBaseResponse(fromQueue);
                            bleBaseResponse15.setResponseData(deviceInfoResponse);
                            this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.a2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    IDOBaseBleApiImpl.k(DataResultListener.this, bleBaseResponse15);
                                }
                            });
                        }
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOBatteryLevelReq) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null) {
                            Object obj9 = response.getObj();
                            Intrinsics.checkNotNull(obj9, "null cannot be cast to non-null type com.ido.ble.protocol.model.BatteryInfo");
                            BatteryLevelResponse batteryLevelResponse = new BatteryLevelResponse();
                            batteryLevelResponse.setBatteryLevel(Integer.valueOf(((BatteryInfo) obj9).level));
                            batteryLevelResponse.setComplete(true);
                            final BleBaseResponse bleBaseResponse16 = new BleBaseResponse(fromQueue);
                            bleBaseResponse16.setResponseData(batteryLevelResponse);
                            this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.b2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    IDOBaseBleApiImpl.l(DataResultListener.this, bleBaseResponse16);
                                }
                            });
                        }
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOGetWatchFaceListData) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null && (response.getObj() instanceof WatchPlateFileInfo)) {
                            Object obj10 = response.getObj();
                            Intrinsics.checkNotNull(obj10, "null cannot be cast to non-null type com.ido.ble.watch.custom.model.WatchPlateFileInfo");
                            WatchPlateFileInfo watchPlateFileInfo = (WatchPlateFileInfo) obj10;
                            Intrinsics.checkNotNullExpressionValue(watchPlateFileInfo.fileNameList, "watchPlateFileInfo.fileNameList");
                            if (!list.isEmpty()) {
                                GetWatchFaceListResponse getWatchFaceListResponse = new GetWatchFaceListResponse();
                                ArrayList<WatchFace> arrayList5 = new ArrayList<>();
                                int size4 = watchPlateFileInfo.fileNameList.size();
                                while (i < size4) {
                                    WatchFace watchFace = new WatchFace(i);
                                    watchFace.setWatchFaceName(watchPlateFileInfo.fileNameList.get(i));
                                    arrayList5.add(watchFace);
                                    i++;
                                }
                                getWatchFaceListResponse.setWatchFaceList(arrayList5);
                                final BleBaseResponse bleBaseResponse17 = new BleBaseResponse(fromQueue);
                                bleBaseResponse17.setResponseData(getWatchFaceListResponse);
                                this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.c2
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        IDOBaseBleApiImpl.m(DataResultListener.this, bleBaseResponse17);
                                    }
                                });
                            } else {
                                Intrinsics.checkNotNull(dataResultListener);
                                dataResultListener.onDataError(new BleBaseError("Data Error"));
                            }
                        }
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOGetAlarmListData) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null && (response.getObj() instanceof List)) {
                            ArrayList arrayList6 = new ArrayList();
                            Object obj11 = response.getObj();
                            Intrinsics.checkNotNull(obj11, "null cannot be cast to non-null type kotlin.collections.List<com.ido.ble.protocol.model.AlarmV3>");
                            for (AlarmV3 alarmV3 : (List) obj11) {
                                if (alarmV3.status == 85) {
                                    Alarm alarm = new Alarm(alarmV3.isOn_off(), alarmV3.alarm_id, alarmV3.hour, alarmV3.minute, alarmV3.type, alarmV3.name, true, alarmV3.getWeekRepeat()[6], alarmV3.getWeekRepeat()[0], alarmV3.getWeekRepeat()[1], alarmV3.getWeekRepeat()[2], alarmV3.getWeekRepeat()[3], alarmV3.getWeekRepeat()[4], alarmV3.getWeekRepeat()[5]);
                                    alarm.setSnoozeDuration(alarmV3.delay_min);
                                    alarm.setSnoozeRepeatTimes(alarmV3.repeat_times);
                                    arrayList6.add(alarm);
                                }
                            }
                            final BleBaseResponse bleBaseResponse18 = new BleBaseResponse(fromQueue);
                            bleBaseResponse18.setResponseData(arrayList6);
                            this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.d2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    IDOBaseBleApiImpl.n(DataResultListener.this, bleBaseResponse18);
                                }
                            });
                        }
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOGetActivityListData) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null) {
                            if (response.getObj() instanceof SupportSportInfoV3) {
                                Object obj12 = response.getObj();
                                Intrinsics.checkNotNull(obj12, "null cannot be cast to non-null type com.ido.ble.protocol.model.SupportSportInfoV3");
                                SupportSportInfoV3 supportSportInfoV3 = (SupportSportInfoV3) obj12;
                                if (supportSportInfoV3.sportTypes != null) {
                                    ArrayList arrayList7 = new ArrayList();
                                    ArrayList<Integer> supportedSportsTypeList = IDOUtils.INSTANCE.getSupportedSportsTypeList();
                                    int size5 = supportedSportsTypeList.size();
                                    for (int i5 = 0; i5 < size5; i5++) {
                                        Integer num = supportedSportsTypeList.get(i5);
                                        Intrinsics.checkNotNullExpressionValue(num, "getSupportedTypeList[i]");
                                        arrayList7.add(new ActivityTypeModel(num.intValue()));
                                    }
                                    ArrayList arrayList8 = new ArrayList();
                                    int size6 = supportSportInfoV3.sportTypes.size();
                                    while (i < size6) {
                                        Integer num2 = supportSportInfoV3.sportTypes.get(i);
                                        Intrinsics.checkNotNullExpressionValue(num2, "supportSportInfoV3.sportTypes[i]");
                                        arrayList8.add(new ActivityTypeModel(num2.intValue()));
                                        i++;
                                    }
                                    GetActivityListResponse getActivityListResponse = new GetActivityListResponse(arrayList7, arrayList8);
                                    getActivityListResponse.setComplete(true);
                                    final BleBaseResponse bleBaseResponse19 = new BleBaseResponse(fromQueue);
                                    bleBaseResponse19.setResponseData(getActivityListResponse);
                                    this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.e2
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            IDOBaseBleApiImpl.o(DataResultListener.this, bleBaseResponse19);
                                        }
                                    });
                                }
                            } else if (response.getObj() instanceof Sport100TypeSort) {
                                Object obj13 = response.getObj();
                                Intrinsics.checkNotNull(obj13, "null cannot be cast to non-null type com.ido.ble.protocol.model.Sport100TypeSort");
                                Sport100TypeSort sport100TypeSort = (Sport100TypeSort) obj13;
                                if (sport100TypeSort.items != null) {
                                    ArrayList arrayList9 = new ArrayList();
                                    int size7 = sport100TypeSort.items.size();
                                    for (int i6 = 0; i6 < size7; i6++) {
                                        arrayList9.add(new ActivityTypeModel(sport100TypeSort.items.get(i6).type));
                                    }
                                    ArrayList arrayList10 = new ArrayList();
                                    int i7 = sport100TypeSort.now_user_location;
                                    while (i < i7) {
                                        arrayList10.add(new ActivityTypeModel(sport100TypeSort.items.get(i).type));
                                        i++;
                                    }
                                    GetActivityListResponse getActivityListResponse2 = new GetActivityListResponse(arrayList9, arrayList10);
                                    getActivityListResponse2.setComplete(true);
                                    final BleBaseResponse bleBaseResponse20 = new BleBaseResponse(fromQueue);
                                    bleBaseResponse20.setResponseData(getActivityListResponse2);
                                    this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.f2
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            IDOBaseBleApiImpl.p(DataResultListener.this, bleBaseResponse20);
                                        }
                                    });
                                }
                            }
                        }
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOGetShortcutMenuListData) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null && (response.getObj() instanceof MenuList.DeviceReturnInfo)) {
                            Object obj14 = response.getObj();
                            Intrinsics.checkNotNull(obj14, "null cannot be cast to non-null type com.ido.ble.protocol.model.MenuList.DeviceReturnInfo");
                            MenuList.DeviceReturnInfo deviceReturnInfo = (MenuList.DeviceReturnInfo) obj14;
                            if (deviceReturnInfo.items != null) {
                                ArrayList arrayList11 = new ArrayList();
                                ArrayList arrayList12 = new ArrayList();
                                int size8 = deviceReturnInfo.items.size();
                                while (i < size8) {
                                    if (i < deviceReturnInfo.currentShowNum) {
                                        arrayList12.add(Integer.valueOf(deviceReturnInfo.items.get(i).value));
                                    }
                                    arrayList11.add(Integer.valueOf(deviceReturnInfo.items.get(i).value));
                                    i++;
                                }
                                GetSupportedShortcutMenuListResponse getSupportedShortcutMenuListResponse = new GetSupportedShortcutMenuListResponse(arrayList11, arrayList12);
                                getSupportedShortcutMenuListResponse.setComplete(true);
                                final BleBaseResponse bleBaseResponse21 = new BleBaseResponse(fromQueue);
                                bleBaseResponse21.setResponseData(getSupportedShortcutMenuListResponse);
                                this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.g2
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        IDOBaseBleApiImpl.q(DataResultListener.this, bleBaseResponse21);
                                    }
                                });
                            }
                        }
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOGetDNDConfig) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null && (response.getObj() instanceof NotDisturbPara)) {
                            Object obj15 = response.getObj();
                            Intrinsics.checkNotNull(obj15, "null cannot be cast to non-null type com.ido.ble.protocol.model.NotDisturbPara");
                            NotDisturbPara notDisturbPara = (NotDisturbPara) obj15;
                            boolean z2 = notDisturbPara.onOFf == 170;
                            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                            objectRef.element = new DNDData(z2, notDisturbPara.startHour, notDisturbPara.startMinute, notDisturbPara.endHour, notDisturbPara.endMinute);
                            this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.a3
                                @Override // java.lang.Runnable
                                public final void run() {
                                    IDOBaseBleApiImpl.a(BleBaseRequest.this, objectRef, dataResultListener);
                                }
                            });
                        }
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOGetLiftWristConfig) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null && (response.getObj() instanceof UpHandGesture)) {
                            Object obj16 = response.getObj();
                            Intrinsics.checkNotNull(obj16, "null cannot be cast to non-null type com.ido.ble.protocol.model.UpHandGesture");
                            if (((UpHandGesture) obj16).onOff != 170) {
                                z = false;
                            }
                            final GetLiftWristResponse getLiftWristResponse = new GetLiftWristResponse();
                            getLiftWristResponse.setLiftWristEnabled(z);
                            this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.z2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    IDOBaseBleApiImpl.a(BleBaseRequest.this, getLiftWristResponse, dataResultListener);
                                }
                            });
                        }
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOFrequentContactsReq) {
                        this.q.removeCallbacksAndMessages(null);
                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.u2
                            @Override // java.lang.Runnable
                            public final void run() {
                                IDOBaseBleApiImpl.f(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOGetCalorieDistanceGoal) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null && (response.getObj() instanceof CalorieAndDistanceGoal)) {
                            Object obj17 = response.getObj();
                            Intrinsics.checkNotNull(obj17, "null cannot be cast to non-null type com.ido.ble.protocol.model.CalorieAndDistanceGoal");
                            CalorieAndDistanceGoal calorieAndDistanceGoal = (CalorieAndDistanceGoal) obj17;
                            final GetCalorieDistanceGoalResponse getCalorieDistanceGoalResponse = new GetCalorieDistanceGoalResponse();
                            getCalorieDistanceGoalResponse.setCalorieGoal(calorieAndDistanceGoal.calorie);
                            getCalorieDistanceGoalResponse.setDistanceGoal(calorieAndDistanceGoal.distance);
                            getCalorieDistanceGoalResponse.setExerciseTimeGoal((int) (calorieAndDistanceGoal.mid_high_time_goal / 60));
                            getCalorieDistanceGoalResponse.setWalkHourGoal(calorieAndDistanceGoal.walk_goal_time);
                            this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.y2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    IDOBaseBleApiImpl.a(BleBaseRequest.this, getCalorieDistanceGoalResponse, dataResultListener);
                                }
                            });
                        }
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOBTCallControlReq) {
                        this.q.removeCallbacksAndMessages(null);
                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.v2
                            @Override // java.lang.Runnable
                            public final void run() {
                                IDOBaseBleApiImpl.g(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof IDOAmbientSoundDataReq) {
                        this.q.removeCallbacksAndMessages(null);
                        if (response.getObj() != null) {
                            Object obj18 = response.getObj();
                            Intrinsics.checkNotNull(obj18, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.khidodb.noise.EntityHealthNoise>");
                            List<EntityHealthNoise> list7 = (List) obj18;
                            if (!list7.isEmpty()) {
                                HashMap hashMap3 = new HashMap();
                                for (EntityHealthNoise entityHealthNoise : list7) {
                                    String convertDateFormat = IDOUtils.INSTANCE.convertDateFormat(entityHealthNoise.getYear(), entityHealthNoise.getMonth(), entityHealthNoise.getDay());
                                    if (!hashMap3.containsKey(convertDateFormat)) {
                                        hashMap3.put(convertDateFormat, CollectionsKt__CollectionsKt.arrayListOf(entityHealthNoise));
                                    } else {
                                        ArrayList arrayList13 = (ArrayList) hashMap3.get(convertDateFormat);
                                        if (arrayList13 != null) {
                                            arrayList13.add(entityHealthNoise);
                                        }
                                        Intrinsics.checkNotNull(arrayList13);
                                        hashMap3.put(convertDateFormat, arrayList13);
                                    }
                                }
                                Set keySet3 = hashMap3.keySet();
                                Intrinsics.checkNotNullExpressionValue(keySet3, "entityHashMap.keys");
                                List<String> sorted3 = CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(keySet3));
                                for (String str3 : sorted3) {
                                    final BleBaseResponse bleBaseResponse22 = new BleBaseResponse(fromQueue);
                                    ArrayList arrayList14 = (ArrayList) hashMap3.get(str3);
                                    if (arrayList14 != null) {
                                        ambientSoundResponse = IDOAmbientSoundLevelFormatter.Companion.getInstance(this.f2962a).convertEntityHealthNoiseToAmbientSoundResponse(CollectionsKt___CollectionsKt.sortedWith(arrayList14, new Comparator() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$onResponse$lambda$42$$inlined$sortedBy$1
                                            @Override // java.util.Comparator
                                            public final int compare(T t, T t2) {
                                                return kotlin.comparisons.f.compareValues(Integer.valueOf(((EntityHealthNoise) t).getStartTime()), Integer.valueOf(((EntityHealthNoise) t2).getStartTime()));
                                            }
                                        }));
                                        ambientSoundResponse.setComplete(Intrinsics.areEqual(str3, sorted3.get(sorted3.size() - 1)));
                                    } else {
                                        ambientSoundResponse = null;
                                    }
                                    bleBaseResponse22.setResponseData(ambientSoundResponse);
                                    this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.h2
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            IDOBaseBleApiImpl.r(DataResultListener.this, bleBaseResponse22);
                                        }
                                    });
                                }
                                setCompleteAndProcessNext(fromQueue);
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$onResponse$42(this, null), 2, null);
                                return;
                            }
                            this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.w2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    IDOBaseBleApiImpl.h(BleBaseRequest.this, dataResultListener);
                                }
                            });
                            setCompleteAndProcessNext(fromQueue);
                            return;
                        }
                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.x2
                            @Override // java.lang.Runnable
                            public final void run() {
                                IDOBaseBleApiImpl.i(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    }
                } else if (fromQueue.getResponseListener() instanceof SettingsResultListener) {
                    IDOBaseReq baseReq2 = response.getBaseReq();
                    if (!(baseReq2 instanceof IDOSedentaryReminderReq ? true : baseReq2 instanceof IDOSetUserInfoReq ? true : baseReq2 instanceof IDOSetGoalReq ? true : baseReq2 instanceof IDOVibrationAlarmReq ? true : baseReq2 instanceof IDOLiftWristReq ? true : baseReq2 instanceof IDOHRIntervalReq ? true : baseReq2 instanceof IDODistanceUnitReq ? true : baseReq2 instanceof IDOHourFormatReq ? true : baseReq2 instanceof IDONotificationReq ? true : baseReq2 instanceof IDOStopNotificationReq ? true : baseReq2 instanceof IDOSetDNDReq ? true : baseReq2 instanceof IDOWeatherReq ? true : baseReq2 instanceof IDOCameraRequest ? true : baseReq2 instanceof IDOWomenWellnessReq ? true : baseReq2 instanceof IDOChangeWatchFaceReq ? true : baseReq2 instanceof IDODeleteWatchFaceReq ? true : baseReq2 instanceof IDOStressIntervalReq ? true : baseReq2 instanceof IDOSetActivityListReq ? true : baseReq2 instanceof IDOSetMenuListReq ? true : baseReq2 instanceof IDOSetActivityRecognitionReq ? true : baseReq2 instanceof IDOWeatherUnitReq ? true : baseReq2 instanceof IDOSetMusicMetaDataReq ? true : baseReq2 instanceof IDORebootDeviceReq ? true : baseReq2 instanceof IDODrinkReminderReq ? true : baseReq2 instanceof IDOSetCalorieAndDistanceGoalReq ? true : baseReq2 instanceof IDOSetWorldClockReq ? true : baseReq2 instanceof IDOAmbientSoundLevelControlReq)) {
                        z = baseReq2 instanceof IDOSetMusicVolumeControlReq;
                    }
                    if (z) {
                        this.q.removeCallbacksAndMessages(null);
                        LogHelper.d(this.b, "onResponse  ==" + response.getBaseReq());
                        this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.n2
                            @Override // java.lang.Runnable
                            public final void run() {
                                IDOBaseBleApiImpl.a(BleBaseRequest.this, this);
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
        BleBaseRequest bleBaseRequest;
        LinkedList<QueueObject> linkedList = this.f;
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        IDOBaseReq a2 = a(this.f.get(0).getBaseRequest());
        if (a2 != null) {
            if (a2.isPriority() && (bleBaseRequest = this.p) != null) {
                Intrinsics.checkNotNull(bleBaseRequest);
                IDOBaseReq a3 = a(bleBaseRequest);
                Intrinsics.checkNotNull(a3);
                if (!a3.isPriority()) {
                    a(a2);
                    return;
                }
            }
            BleBaseRequest bleBaseRequest2 = this.p;
            if (bleBaseRequest2 != null) {
                Intrinsics.checkNotNull(bleBaseRequest2);
                if (!bleBaseRequest2.isComplete()) {
                    return;
                }
            }
            this.p = this.f.get(0).getBaseRequest();
            a(a2);
            return;
        }
        sendCommandNotFoundError(this.f.get(0).getBaseRequest());
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public androidx.lifecycle.LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.e == null) {
            this.e = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        if (BLEManager.isConnected()) {
            connectionStatus = ConnectionStatus.CONNECTED;
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.e;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(connectionStatus);
        }
        MutableLiveData<ConnectionStatus> mutableLiveData2 = this.e;
        Intrinsics.checkNotNull(mutableLiveData2, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus>");
        return mutableLiveData2;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public androidx.lifecycle.LiveData<LiveECGDataResponse> registerForLiveEcgData() {
        return new MutableLiveData();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public androidx.lifecycle.LiveData<LiveTemperatureData> registerForLiveTemperatureData() {
        return this.l;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<LiveAGPSUploadPercentage> registerLiveAGPSUploadData() {
        return this.n;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public androidx.lifecycle.LiveData<LiveHealthData> registerLiveHealthData() {
        return this.j;
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
    public androidx.lifecycle.LiveData<LiveSportData> registerLiveSportsData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public androidx.lifecycle.LiveData<LiveStepsData> registerLiveStepsData() {
        return this.k;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<LiveWatchFaceUploadPercentage> registerLiveWatchFaceUploadData() {
        return this.m;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        PreferenceManagerAbstract.getInstance(this.f2962a).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        clearCommandQueue();
        BLEManager.registerConnectCallBack(this.u);
        if (BLEManager.isBind() && !BLEManager.isConnected()) {
            BLEManager.autoConnect();
        } else if (BLEManager.isBind()) {
        } else {
            BluetoothDevice remoteDevice = this.c.getRemoteDevice(getMacAddress());
            BLEDevice bLEDevice = new BLEDevice();
            bLEDevice.mDeviceAddress = getMacAddress();
            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this.f2962a, "android.permission.BLUETOOTH_CONNECT") == 0) {
                bLEDevice.mDeviceName = remoteDevice.getName();
                BLEManager.connect(bLEDevice);
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(@NotNull ScanDeviceRequest scanDeviceReq, @NotNull ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(scanDeviceReq, "scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "listener");
        clearCommandQueue();
        a(scanDeviceReq, listener);
    }

    public final void sendCommandNotFoundError(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        BaseListener responseListener = baseRequest.getResponseListener();
        if (responseListener instanceof DataResultListener) {
            String string = this.f2962a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.command_not_found)");
            ((DataResultListener) responseListener).onDataError(new BleBaseError(string));
        } else if (responseListener instanceof SettingsResultListener) {
            String string2 = this.f2962a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.command_not_found)");
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(string2));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, this.f2962a.getString(R.string.command_not_found)));
        }
    }

    public final void sendCommandRequest() {
        LinkedList<QueueObject> linkedList = this.f;
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        processNextCommand();
    }

    public final void sendErrorAndClearQueue(@NotNull IDOError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        synchronized (this.f) {
            LinkedList<QueueObject> linkedList = this.f;
            if (linkedList != null && linkedList.size() > 0) {
                try {
                    Object clone = this.f.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.QueueObject>");
                    Iterator it = ((LinkedList) clone).iterator();
                    while (it.hasNext()) {
                        BaseListener responseListener = ((QueueObject) it.next()).getBaseRequest().getResponseListener();
                        String message = error.getMessage();
                        Intrinsics.checkNotNull(message);
                        BleBaseError bleBaseError = new BleBaseError(message);
                        if (error.getErrorType() == IDOErrorType.COMMAND_TIME_OUT) {
                            bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_ERROR_TIMEOUT.value));
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

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setBusyStatus(value);
    }

    public final void setCompleteAndProcessNext(@Nullable BleBaseRequest bleBaseRequest) {
        if (bleBaseRequest != null) {
            c(bleBaseRequest);
            String str = this.b;
            LogHelper.d(str, "setCompleteAndProcessNext--> removed " + bleBaseRequest);
        }
        BleBaseRequest bleBaseRequest2 = this.p;
        if (bleBaseRequest2 != null) {
            Intrinsics.checkNotNull(bleBaseRequest2);
            bleBaseRequest2.setComplete(true);
        }
        processNextCommand();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d = listener;
    }

    public final void setPairingSupported(boolean z) {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (BLEManager.isConnected()) {
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
        String string = this.f2962a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(this.f2962a).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManagerAbstract.getInstance(this.f2962a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        BLEManager.disConnect();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
        BLEManager.unregisterBindCallBack(this.v);
    }

    public static final void b(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(new HeartRateResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void d(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void e(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void f(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(new BleBaseResponse(bleBaseRequest));
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void g(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(new BleBaseResponse(bleBaseRequest));
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void h(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(new AmbientSoundResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void i(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(new AmbientSoundResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    /* loaded from: classes2.dex */
    public static final class QueueObject {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final IDOBleCommandName f2964a;
        @NotNull
        public BleBaseRequest b;
        public int c;
        @Nullable
        public final Object d;

        public QueueObject(@NotNull IDOBleCommandName bleCommandName, @NotNull BleBaseRequest baseRequest, int i, @Nullable Object obj) {
            Intrinsics.checkNotNullParameter(bleCommandName, "bleCommandName");
            Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
            this.f2964a = bleCommandName;
            this.b = baseRequest;
            this.c = i;
            this.d = obj;
        }

        @NotNull
        public final BleBaseRequest getBaseRequest() {
            return this.b;
        }

        @NotNull
        public final IDOBleCommandName getBleCommandName() {
            return this.f2964a;
        }

        @Nullable
        public final Object getData() {
            return this.d;
        }

        public final int getTimeOut() {
            return this.c;
        }

        public final void setBaseRequest(@NotNull BleBaseRequest bleBaseRequest) {
            Intrinsics.checkNotNullParameter(bleBaseRequest, "<set-?>");
            this.b = bleBaseRequest;
        }

        public final void setTimeOut(int i) {
            this.c = i;
        }

        public /* synthetic */ QueueObject(IDOBleCommandName iDOBleCommandName, BleBaseRequest bleBaseRequest, int i, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(iDOBleCommandName, bleBaseRequest, i, (i2 & 8) != 0 ? null : obj);
        }
    }

    public final boolean b(BleBaseRequest bleBaseRequest) {
        boolean z = true;
        try {
            BleBaseRequest bleBaseRequest2 = this.p;
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

    public static final void c(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void c(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(final IDOBaseBleApiImpl this$0, final ScanDeviceRequest scanDeviceReq, final ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "$scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        try {
            if (!DeviceScanManager.getInstance(this$0.f2962a).isScanningInProgress()) {
                if (BleUtils.isEmpty(scanDeviceReq.getScanFilter())) {
                    DeviceScanManager.getInstance(this$0.f2962a).scanAllDevices(LeonardoBleApiImpl.context, scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$scan_$2$1
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            IDOBaseBleApiImpl.access$scanResultRecieved(IDOBaseBleApiImpl.this, devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = LeonardoBleApiImpl.context.getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                            scanResultListener.onError(string);
                        }
                    });
                } else {
                    DeviceScanManager.getInstance(this$0.f2962a).scanDevicesWithFilter(LeonardoBleApiImpl.context, scanDeviceReq.getScanFilter(), scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$scan_$2$2
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            IDOBaseBleApiImpl.access$scanResultRecieved(IDOBaseBleApiImpl.this, devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = LeonardoBleApiImpl.context.getString(R.string.scan_failed);
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

    public final void a() {
        BLEManager.unregisterGetDeviceInfoCallBack(this.y);
        BLEManager.unregisterSettingCallBack(this.x);
        BLEManager.unregisterSyncV3CallBack(this.A);
        BLEManager.unregisterPhoneMsgNoticeCallBack(this.z);
        BLEManager.unregisterDeviceControlAppCallBack(this.B);
        BLEManager.unregisterAppSendDataCallBack(this.C);
        BLEManager.unregisterWatchOperateCallBack(this.D);
        BLEManager.unregisterGetDeviceParaCallBack(this.E);
        BLEManager.unregisterOtherProtocolCallBack(this.F);
        BLEManager.unregisterOperateCallBack(this.G);
    }

    public static final void a(IDOBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.b, "Command TimeOut,Failed");
        BleBaseRequest bleBaseRequest = this$0.p;
        if (bleBaseRequest != null) {
            Intrinsics.checkNotNull(bleBaseRequest);
            IDOBaseReq a2 = this$0.a(bleBaseRequest);
            if (a2 != null) {
                this$0.onFailure(new IDOError(IDOErrorType.COMMAND_TIME_OUT, this$0.f2962a.getString(R.string.command_time_out)));
                LogHelper.e("Command TimeOut", "End Time " + System.currentTimeMillis() + "-- currentCommand " + a2);
            } else {
                BleBaseRequest bleBaseRequest2 = this$0.p;
                Intrinsics.checkNotNull(bleBaseRequest2);
                this$0.sendCommandNotFoundError(bleBaseRequest2);
            }
        }
        this$0.p = null;
    }

    public static final void a(DataResultListener dataResultListener, BleBaseResponse sleepRes) {
        Intrinsics.checkNotNullParameter(sleepRes, "$sleepRes");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sleepRes);
    }

    public static final void a(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(new HeartRateResponse());
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(BleBaseRequest bleBaseRequest, Ref.ObjectRef dndData, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(dndData, "$dndData");
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(dndData.element);
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

    public static final void a(BleBaseRequest bleBaseRequest, GetCalorieDistanceGoalResponse calorieDistanceResponse, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(calorieDistanceResponse, "$calorieDistanceResponse");
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(calorieDistanceResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(BleBaseRequest bleBaseRequest, IDOBaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsResultListener settingsResultListener = (SettingsResultListener) bleBaseRequest.getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse(bleBaseRequest));
        this$0.setCompleteAndProcessNext(bleBaseRequest);
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0487  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x049f  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x04ab  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x04c3  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x04da  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x04e3  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x04f7  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0500  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x050b  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0531  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.coveiot.android.idoSdk.api.IDOBaseReq a(com.coveiot.android.bleabstract.request.BleBaseRequest r18) {
        /*
            Method dump skipped, instructions count: 3572
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl.a(com.coveiot.android.bleabstract.request.BleBaseRequest):com.coveiot.android.idoSdk.api.IDOBaseReq");
    }

    public final void a(IDOBaseReq iDOBaseReq) {
        this.q.postDelayed(this.H, iDOBaseReq.getTimeOut());
        final IDOBaseRes iDOBaseRes = new IDOBaseRes();
        iDOBaseRes.setBaseReq(iDOBaseReq);
        if (iDOBaseReq instanceof IDOStepsDataReq) {
            if (LocalDataManager.getSupportFunctionInfo() == null) {
                BLEManager.getFunctionTables();
            }
            SyncPara syncPara = new SyncPara();
            syncPara.isNeedSyncConfigData = false;
            if (this.r) {
                return;
            }
            BLEManager.syncAllData(syncPara);
        } else if (iDOBaseReq instanceof IDOSleepDataReq) {
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$sendRequestToBle$1(this, iDOBaseRes, null), 2, null);
        } else if (iDOBaseReq instanceof IDOHeartRateDataReq) {
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$sendRequestToBle$2(this, iDOBaseRes, null), 2, null);
        } else if (iDOBaseReq instanceof IDOSPO2DataReq) {
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$sendRequestToBle$3(this, iDOBaseRes, null), 2, null);
        } else if (iDOBaseReq instanceof IDOStressDataReq) {
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$sendRequestToBle$4(this, iDOBaseRes, null), 2, null);
        } else if (iDOBaseReq instanceof IDOSportsDataReq) {
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$sendRequestToBle$5(this, iDOBaseRes, null), 2, null);
        } else if (iDOBaseReq instanceof IDODeviceInfoReq) {
            BLEManager.getBasicInfo();
        } else if (iDOBaseReq instanceof IDOBatteryLevelReq) {
            BLEManager.getBatteryInfo();
        } else if (iDOBaseReq instanceof IDOGetAlarmListData) {
            BLEManager.getAlarmV3();
        } else if (iDOBaseReq instanceof IDOGetWatchFaceListData) {
            BLEManager.getWatchPlateList();
        } else if (iDOBaseReq instanceof IDOGetActivityListData) {
            if (LocalDataManager.getSupportFunctionInfo().V3_set_100_sport_sort) {
                BLEManager.querySport100TypeSort();
            } else {
                BLEManager.getSupportSportInfoV3();
            }
        } else if (iDOBaseReq instanceof IDOGetShortcutMenuListData) {
            BLEManager.getMenuList();
        } else if (iDOBaseReq instanceof IDOGetDNDConfig) {
            BLEManager.getDoNotDisturbPara();
        } else if (iDOBaseReq instanceof IDOCloudWatchFaceReq) {
            WatchPlateSetConfig watchPlateConfig = ((IDOCloudWatchFaceReq) iDOBaseReq).getWatchPlateConfig();
            watchPlateConfig.stateListener = new WatchPlateCallBack.IAutoSetPlateCallBack() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$sendRequestToBle$6
                @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IAutoSetPlateCallBack
                public void onFailed() {
                    LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "Cloud onFailed");
                    IDOBaseBleApiImpl iDOBaseBleApiImpl = IDOBaseBleApiImpl.this;
                    iDOBaseBleApiImpl.onFailure(new IDOError(IDOErrorType.COMMAND_REQUEST_ERROR, iDOBaseBleApiImpl.getContext().getString(R.string.command_req_error)));
                }

                @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IAutoSetPlateCallBack
                public void onProgress(int i) {
                    String tag = IDOBaseBleApiImpl.this.getTAG();
                    LogHelper.i(tag, "Cloud onProgress" + i);
                    LiveWatchFaceUploadPercentage liveWatchFaceUploadPercentage = new LiveWatchFaceUploadPercentage();
                    liveWatchFaceUploadPercentage.setPercentage(i);
                    iDOBaseRes.setObj(liveWatchFaceUploadPercentage);
                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                }

                @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IAutoSetPlateCallBack
                public void onStart() {
                    LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "Cloud onStart");
                }

                @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IAutoSetPlateCallBack
                public void onSuccess() {
                    LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "Cloud onSuccess");
                    iDOBaseRes.setObj(null);
                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                }
            };
            BLEManager.startSetPlateFileToWatch(watchPlateConfig);
        } else if (iDOBaseReq instanceof IDODIYWatchFaceReq) {
            IDODIYWatchFaceReq iDODIYWatchFaceReq = (IDODIYWatchFaceReq) iDOBaseReq;
            final WallpaperFileCreateConfig wallPaperConFig = iDODIYWatchFaceReq.getWallPaperConFig();
            BLEManager.createPlateWallpaperFile(wallPaperConFig);
            final PhotoWallpaperOperation.SetParams setParams = new PhotoWallpaperOperation.SetParams();
            setParams.operate = 1;
            int textColor = iDODIYWatchFaceReq.getTextColor();
            setParams.time_color = textColor;
            setParams.widget_icon_color = textColor;
            setParams.widget_num_color = textColor;
            FileTransferConfig defaultTransPictureConfig = FileTransferConfig.getDefaultTransPictureConfig(wallPaperConFig.getOutFilePath(), new IFileTransferListener() { // from class: com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$sendRequestToBle$fileConfig$1
                @Override // com.ido.ble.file.transfer.IFileTransferListener
                public void onFailed(@NotNull String errorMsg) {
                    Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
                    LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "CustomWatchFace onFailed");
                    new File(wallPaperConFig.getOutFilePath()).delete();
                    IDOBaseBleApiImpl iDOBaseBleApiImpl = IDOBaseBleApiImpl.this;
                    iDOBaseBleApiImpl.onFailure(new IDOError(IDOErrorType.COMMAND_REQUEST_ERROR, iDOBaseBleApiImpl.getContext().getString(R.string.command_req_error)));
                }

                @Override // com.ido.ble.file.transfer.IFileTransferListener
                public void onProgress(int i) {
                    String tag = IDOBaseBleApiImpl.this.getTAG();
                    LogHelper.i(tag, "CustomWatchFace onProgress" + i);
                    LiveWatchFaceUploadPercentage liveWatchFaceUploadPercentage = new LiveWatchFaceUploadPercentage();
                    liveWatchFaceUploadPercentage.setPercentage(i);
                    iDOBaseRes.setObj(liveWatchFaceUploadPercentage);
                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                }

                @Override // com.ido.ble.file.transfer.IFileTransferListener
                public void onStart() {
                    LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "CustomWatchFace onStart");
                }

                @Override // com.ido.ble.file.transfer.IFileTransferListener
                public void onSuccess() {
                    LogHelper.i(IDOBaseBleApiImpl.this.getTAG(), "CustomWatchFace onSuccess");
                    BLEManager.photoWallpaperOperate(setParams);
                    new File(wallPaperConFig.getOutFilePath()).delete();
                    iDOBaseRes.setObj(null);
                    IDOBaseBleApiImpl.this.onResponse(iDOBaseRes);
                }
            });
            defaultTransPictureConfig.maxRetryTimes = 0;
            BLEManager.stopTranCommonFile();
            BLEManager.startTranCommonFile(defaultTransPictureConfig);
        } else if (iDOBaseReq instanceof IDOSedentaryReminderReq) {
            BLEManager.setWalkReminder(((IDOSedentaryReminderReq) iDOBaseReq).getWalkReminder());
        } else if (iDOBaseReq instanceof IDOSetUserInfoReq) {
            BLEManager.setUserInfo(((IDOSetUserInfoReq) iDOBaseReq).getUserInfo());
        } else if (iDOBaseReq instanceof IDOSetGoalReq) {
            BLEManager.setGoal(((IDOSetGoalReq) iDOBaseReq).getGoal());
        } else if (iDOBaseReq instanceof IDOVibrationAlarmReq) {
            BLEManager.setAlarmV3(((IDOVibrationAlarmReq) iDOBaseReq).getAlarmList());
        } else if (iDOBaseReq instanceof IDOLiftWristReq) {
            BLEManager.setUpHandGesture(((IDOLiftWristReq) iDOBaseReq).getUpHandGesture());
        } else if (iDOBaseReq instanceof IDOHRIntervalReq) {
            BLEManager.setHeartRateMeasureModeV3(((IDOHRIntervalReq) iDOBaseReq).getHeartRateMeasureModeV3());
        } else if (iDOBaseReq instanceof IDONotificationReq) {
            IDONotificationReq iDONotificationReq = (IDONotificationReq) iDOBaseReq;
            if (iDONotificationReq.getIncomingCallInfo() != null) {
                BLEManager.setIncomingCallInfo(iDONotificationReq.getIncomingCallInfo());
            } else {
                BLEManager.setNewMessageDetailInfo(iDONotificationReq.getMessageInfo());
            }
        } else if (iDOBaseReq instanceof IDOStopNotificationReq) {
            BLEManager.setStopInComingCall();
        } else if (iDOBaseReq instanceof IDOHourFormatReq) {
            BLEManager.setUnit(((IDOHourFormatReq) iDOBaseReq).getUnits());
        } else if (iDOBaseReq instanceof IDODistanceUnitReq) {
            BLEManager.setUnit(((IDODistanceUnitReq) iDOBaseReq).getUnits());
        } else if (iDOBaseReq instanceof IDOSetDNDReq) {
            BLEManager.setNotDisturbPara(((IDOSetDNDReq) iDOBaseReq).getNotDisturbPara());
        } else if (iDOBaseReq instanceof IDOCameraRequest) {
            if (((IDOCameraRequest) iDOBaseReq).getCameraStatus()) {
                BLEManager.enterCameraMode();
            } else {
                BLEManager.exitCameraMode();
            }
        } else if (iDOBaseReq instanceof IDOWeatherReq) {
            BLEManager.setWeatherDataV3(((IDOWeatherReq) iDOBaseReq).getWeatherInfoV3());
        } else if (iDOBaseReq instanceof IDOWomenWellnessReq) {
            IDOWomenWellnessReq iDOWomenWellnessReq = (IDOWomenWellnessReq) iDOBaseReq;
            BLEManager.setMenstrual(iDOWomenWellnessReq.getMenstrual());
            BLEManager.setMenstrualRemind(iDOWomenWellnessReq.getMenstrualRemind());
        } else if (iDOBaseReq instanceof IDOChangeWatchFaceReq) {
            BLEManager.setDialPlate(((IDOChangeWatchFaceReq) iDOBaseReq).getDialPlate());
        } else if (iDOBaseReq instanceof IDODeleteWatchFaceReq) {
            BLEManager.deleteWatchPlate(((IDODeleteWatchFaceReq) iDOBaseReq).getWatchFaceName());
        } else if (iDOBaseReq instanceof IDOStressIntervalReq) {
            BLEManager.setPressureParam(((IDOStressIntervalReq) iDOBaseReq).getPressureParam());
        } else if (iDOBaseReq instanceof IDOSetActivityListReq) {
            if (LocalDataManager.getSupportFunctionInfo().V3_set_100_sport_sort) {
                IDOSetActivityListReq iDOSetActivityListReq = (IDOSetActivityListReq) iDOBaseReq;
                BLEManager.setSport100TypeSort(iDOSetActivityListReq.getSport100TypeList(), iDOSetActivityListReq.getShowListSize());
                return;
            }
            BLEManager.setSportModeSortInfoV3(((IDOSetActivityListReq) iDOBaseReq).getSportModeSortV3());
        } else if (iDOBaseReq instanceof IDOSetMenuListReq) {
            BLEManager.setMenuList(((IDOSetMenuListReq) iDOBaseReq).getMenuList());
        } else if (iDOBaseReq instanceof IDOSetActivityRecognitionReq) {
            BLEManager.setActivitySwitch(((IDOSetActivityRecognitionReq) iDOBaseReq).getActivitySwitch());
        } else if (iDOBaseReq instanceof IDOWeatherUnitReq) {
            BLEManager.setUnit(((IDOWeatherUnitReq) iDOBaseReq).getUnits());
        } else if (iDOBaseReq instanceof IDOSetMusicMetaDataReq) {
            BLEManager.setMusicControlInfo(((IDOSetMusicMetaDataReq) iDOBaseReq).getMusicControlInfo());
        } else if (iDOBaseReq instanceof IDOGetLiftWristConfig) {
            BLEManager.getUpHandGesture();
        } else if (iDOBaseReq instanceof IDORebootDeviceReq) {
            BLEManager.reBoot();
        } else if (iDOBaseReq instanceof IDODrinkReminderReq) {
            BLEManager.setDrinkWaterReminder(((IDODrinkReminderReq) iDOBaseReq).getDrinkWaterReminder());
        } else if (iDOBaseReq instanceof IDOSetCalorieAndDistanceGoalReq) {
            BLEManager.setCalorieAndDistanceGoal(((IDOSetCalorieAndDistanceGoalReq) iDOBaseReq).getCalorieAndDistanceGoal());
        } else if (iDOBaseReq instanceof IDOFrequentContactsReq) {
            BLEManager.setFrequentContactsV3(((IDOFrequentContactsReq) iDOBaseReq).getFrequentContactsList());
        } else if (iDOBaseReq instanceof IDOGetCalorieDistanceGoal) {
            BLEManager.getSportThreeCircleGoal();
        } else if (iDOBaseReq instanceof IDOBTCallControlReq) {
            BLEManager.setNoticeReminderSwitchStatus(((IDOBTCallControlReq) iDOBaseReq).getNoticeReminderSwitchStatus());
        } else if (iDOBaseReq instanceof IDOSetWorldClockReq) {
            BLEManager.setWorldTime(((IDOSetWorldClockReq) iDOBaseReq).getWorldClockList());
        } else if (iDOBaseReq instanceof IDOAmbientSoundLevelControlReq) {
            BLEManager.setNoisePara(((IDOAmbientSoundLevelControlReq) iDOBaseReq).getNoisePara());
        } else if (iDOBaseReq instanceof IDOAmbientSoundDataReq) {
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new IDOBaseBleApiImpl$sendRequestToBle$7(this, iDOBaseRes, null), 2, null);
        } else if (iDOBaseReq instanceof IDOSetMusicVolumeControlReq) {
            BLEManager.setPhoneVoice(((IDOSetMusicVolumeControlReq) iDOBaseReq).getPhoneVoice());
        }
    }
}
