package com.coveiot.android.bleabstract.bleimpl;

import android.app.ActivityManager;
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.formatter.LeonardoFormatter;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.android.bleabstract.models.RawPPGData;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.ActivityPauseResumeRequest;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.BpCalibrationDataRequest;
import com.coveiot.android.bleabstract.request.BpDataRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.DeleteNearbyDevicesRequest;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.GetCalorieDataRequest;
import com.coveiot.android.bleabstract.request.GetDistanceDataRequest;
import com.coveiot.android.bleabstract.request.GetNearbyDeviceListRequest;
import com.coveiot.android.bleabstract.request.GetSensAISummaryRequest;
import com.coveiot.android.bleabstract.request.GetSocialDistanceSettingsRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.LiveBpControlRequest;
import com.coveiot.android.bleabstract.request.LiveHeartRateControlRequest;
import com.coveiot.android.bleabstract.request.LiveRawPPGControlRequest;
import com.coveiot.android.bleabstract.request.LiveStepsControlRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.PeriodicSPO2BleRequest;
import com.coveiot.android.bleabstract.request.ProbeDataRequest;
import com.coveiot.android.bleabstract.request.ReadManualBpRequest;
import com.coveiot.android.bleabstract.request.ReadManualSpo2Request;
import com.coveiot.android.bleabstract.request.ReadRawPPGHistoryDataRequest;
import com.coveiot.android.bleabstract.request.ReadSedentaryDataRequest;
import com.coveiot.android.bleabstract.request.RrDataRequest;
import com.coveiot.android.bleabstract.request.RrHistoryRequest;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.SetBandDispalyRequest;
import com.coveiot.android.bleabstract.request.SetCallSmsSocialNotificationRequest;
import com.coveiot.android.bleabstract.request.SetDistanceUnitRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.request.SetSocialDistanceScanSettingsRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.bleabstract.request.SetWearingHandRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.SportModeRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.bleabstract.request.StopMessageNotificationRequest;
import com.coveiot.android.bleabstract.request.StressDataRequest;
import com.coveiot.android.bleabstract.request.TemperatureDataRequest;
import com.coveiot.android.bleabstract.request.TodaysStepsDataRequest;
import com.coveiot.android.bleabstract.request.Vo2MaxRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.BpResponse;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.LiveAGPSUploadPercentage;
import com.coveiot.android.bleabstract.response.LiveECGDataResponse;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveSportData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.ReadManualBpResponse;
import com.coveiot.android.bleabstract.response.RrResponse;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.Spo2Response;
import com.coveiot.android.bleabstract.response.Spo2WaveResponse;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.TodaysStepsData;
import com.coveiot.android.bleabstract.response.TodaysStepsResponse;
import com.coveiot.android.bleabstract.services.LeonardoBleCmdService;
import com.coveiot.android.bleabstract.services.LeonardoBleService;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.ResponseListener;
import com.coveiot.sdk.ble.api.model.AppNotificationType;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.api.model.HrBpData;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.model.TodaysWalkData;
import com.coveiot.sdk.ble.api.request.ActivityPauseResumetReq;
import com.coveiot.sdk.ble.api.request.BandDisplaySettingsReq;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.BloodPressureMeasurementReq;
import com.coveiot.sdk.ble.api.request.ChangeDistanceUnitReq;
import com.coveiot.sdk.ble.api.request.ChangeWearingHandReq;
import com.coveiot.sdk.ble.api.request.CurrentSportModeReq;
import com.coveiot.sdk.ble.api.request.DeleteNearbyDevicesReq;
import com.coveiot.sdk.ble.api.request.GetActivitySummaryReq;
import com.coveiot.sdk.ble.api.request.GetCalorieDataReq;
import com.coveiot.sdk.ble.api.request.GetDistanceDataReq;
import com.coveiot.sdk.ble.api.request.GetNearbyDeviceListReq;
import com.coveiot.sdk.ble.api.request.GetSensAISummaryDataReq;
import com.coveiot.sdk.ble.api.request.GetSocialDistanceScanSettingsReq;
import com.coveiot.sdk.ble.api.request.HeartRateBpReq;
import com.coveiot.sdk.ble.api.request.HeartRateMeasurementReq;
import com.coveiot.sdk.ble.api.request.LiveStepsControlReq;
import com.coveiot.sdk.ble.api.request.MessageAlertSwitchesReq;
import com.coveiot.sdk.ble.api.request.MessageContentReq;
import com.coveiot.sdk.ble.api.request.PPGControlReq;
import com.coveiot.sdk.ble.api.request.PeriodicSPO2BaseReq;
import com.coveiot.sdk.ble.api.request.ReadBatteryLevelReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceFirmwareVersionReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceHardwareVersionReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceManufacturerReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceModelReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceNameReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceSerialNumberReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceSoftwareVersionReq;
import com.coveiot.sdk.ble.api.request.ReadManualBpReq;
import com.coveiot.sdk.ble.api.request.ReadRawPPGDataReq;
import com.coveiot.sdk.ble.api.request.ReadSedentaryDataReq;
import com.coveiot.sdk.ble.api.request.RrDataReq;
import com.coveiot.sdk.ble.api.request.SPO2DataReq;
import com.coveiot.sdk.ble.api.request.SaveBpCalibrationReq;
import com.coveiot.sdk.ble.api.request.SaveFitnessProfileReq;
import com.coveiot.sdk.ble.api.request.SaveProbeReq;
import com.coveiot.sdk.ble.api.request.SedentaryReminderReq;
import com.coveiot.sdk.ble.api.request.SetAlarmReq;
import com.coveiot.sdk.ble.api.request.SetSocialDistanceScanSettingsReq;
import com.coveiot.sdk.ble.api.request.SetTimeFormatReq;
import com.coveiot.sdk.ble.api.request.SetWalkTargetReq;
import com.coveiot.sdk.ble.api.request.SleepDataReq;
import com.coveiot.sdk.ble.api.request.StopMessageNotificationReq;
import com.coveiot.sdk.ble.api.request.StressDataDataReq;
import com.coveiot.sdk.ble.api.request.TemperatureDataDataReq;
import com.coveiot.sdk.ble.api.request.TimeIntervalForAutomaticHeartRateReq;
import com.coveiot.sdk.ble.api.request.TodaysStepsDataReq;
import com.coveiot.sdk.ble.api.request.Vo2MaxReq;
import com.coveiot.sdk.ble.api.request.WalkDataReq;
import com.coveiot.sdk.ble.api.response.ActivityPauseResumeRes;
import com.coveiot.sdk.ble.api.response.BandDisplaySettingsRes;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.BloodPressureMeasurementRes;
import com.coveiot.sdk.ble.api.response.ChangeDistanceUnitRes;
import com.coveiot.sdk.ble.api.response.ChangeWearingHandRes;
import com.coveiot.sdk.ble.api.response.CurrentSportModesRes;
import com.coveiot.sdk.ble.api.response.DeleteNearbyDevicesRes;
import com.coveiot.sdk.ble.api.response.FindMyPhoneRes;
import com.coveiot.sdk.ble.api.response.GetNearbyDevicesMacRes;
import com.coveiot.sdk.ble.api.response.GetNearbyDevicesRes;
import com.coveiot.sdk.ble.api.response.GetSocialDistanceScanSettingsRes;
import com.coveiot.sdk.ble.api.response.HeartRateMeasurementRes;
import com.coveiot.sdk.ble.api.response.HrBpDataRes;
import com.coveiot.sdk.ble.api.response.LiveHealthRes;
import com.coveiot.sdk.ble.api.response.LiveStepsControlRes;
import com.coveiot.sdk.ble.api.response.LiveStepsRes;
import com.coveiot.sdk.ble.api.response.MessageAlertSwitchesRes;
import com.coveiot.sdk.ble.api.response.MessageContentRes;
import com.coveiot.sdk.ble.api.response.ReadBatteryLevelRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceFirmwareVersionRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceHardwareVersionRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceManufacturerRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceModelRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceNameRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceSerialNumberRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceSoftwareVersionRes;
import com.coveiot.sdk.ble.api.response.ReadManualBpRes;
import com.coveiot.sdk.ble.api.response.RrDataRes;
import com.coveiot.sdk.ble.api.response.SaveBpCalibrationRes;
import com.coveiot.sdk.ble.api.response.SaveFitnessProfileRes;
import com.coveiot.sdk.ble.api.response.SaveProbeRes;
import com.coveiot.sdk.ble.api.response.SetAlarmRes;
import com.coveiot.sdk.ble.api.response.SetSedentaryReminderRes;
import com.coveiot.sdk.ble.api.response.SetSocialDistanceScanSettingsRes;
import com.coveiot.sdk.ble.api.response.SetTimeFormatRes;
import com.coveiot.sdk.ble.api.response.SetWalkTargetRes;
import com.coveiot.sdk.ble.api.response.SleepDataRes;
import com.coveiot.sdk.ble.api.response.StopMessageNotificationRes;
import com.coveiot.sdk.ble.api.response.TimeIntervalForAutomaticHeartRateRes;
import com.coveiot.sdk.ble.api.response.TodaysStepsDataRes;
import com.coveiot.sdk.ble.api.response.Vo2MaxRes;
import com.coveiot.sdk.ble.api.response.WalkDataRes;
import com.coveiot.sdk.ble.api.response.WomenWellnessSettingsRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.ResponseData;
import com.coveiot.sdk.ble.scan.AssociationResult;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class LeonardoBleApiImpl implements BleApi, ResponseListener {
    public static LeonardoBleCmdService bleService = null;
    public static Context context = null;
    public static Intent intent = null;
    public static final String l = "LeonardoBleApiImpl";
    public static LeonardoBleApiImpl m;
    public static volatile LinkedList<QueueObject> n = new LinkedList<>();
    public static ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder instanceof LeonardoBleCmdService.BtServiceBinder) {
                LeonardoBleApiImpl.bleService = ((LeonardoBleCmdService.BtServiceBinder) iBinder).getService();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LeonardoBleApiImpl.bleService = null;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<LiveHealthData> f3162a;
    public MutableLiveData<PPGData> b;
    public MutableLiveData<ConnectionStatus> connectionStateLiveData;
    public DeviceInfoData j;
    public MutableLiveData<LiveStepsData> liveStepsDataMutableLiveData;
    public Handler uiHandler;
    public BusyStatus c = BusyStatus.SYNCING;
    public Handler d = new Handler(Looper.getMainLooper());
    public Handler e = new Handler(Looper.getMainLooper());
    public int f = 0;
    public int sleepDataSize = 0;
    public int g = 0;
    public Map<String, ResponseData> h = new HashMap();
    public ArrayList<Integer> i = new ArrayList<>();
    public boolean k = true;

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl$17  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass17 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3172a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;
        public static final /* synthetic */ int[] d;
        public static final /* synthetic */ int[] e;
        public static final /* synthetic */ int[] f;

        static {
            Type.values();
            int[] iArr = new int[8];
            f = iArr;
            try {
                iArr[7] = 1;
            } catch (NoSuchFieldError unused) {
            }
            int[] iArr2 = new int[CloveBleState.BleState.values().length];
            e = iArr2;
            try {
                iArr2[CloveBleState.BleState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[CloveBleState.BleState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[CloveBleState.BleState.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                e[CloveBleState.BleState.SCANNING.ordinal()] = 4;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                e[CloveBleState.BleState.DISCOVERING_SERVICES.ordinal()] = 5;
            } catch (NoSuchFieldError unused6) {
            }
            NotificationType.values();
            int[] iArr3 = new int[65];
            d = iArr3;
            try {
                NotificationType notificationType = NotificationType.SMS;
                iArr3[2] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                int[] iArr4 = d;
                NotificationType notificationType2 = NotificationType.CALENDAR;
                iArr4[1] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                int[] iArr5 = d;
                NotificationType notificationType3 = NotificationType.CALL;
                iArr5[0] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                int[] iArr6 = d;
                NotificationType notificationType4 = NotificationType.QQ;
                iArr6[10] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                int[] iArr7 = d;
                NotificationType notificationType5 = NotificationType.LINE;
                iArr7[13] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                int[] iArr8 = d;
                NotificationType notificationType6 = NotificationType.EMAIL;
                iArr8[3] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                int[] iArr9 = d;
                NotificationType notificationType7 = NotificationType.QZONE;
                iArr9[11] = 7;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                int[] iArr10 = d;
                NotificationType notificationType8 = NotificationType.SKYPE;
                iArr10[14] = 8;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                int[] iArr11 = d;
                NotificationType notificationType9 = NotificationType.WECHAT;
                iArr11[5] = 9;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                int[] iArr12 = d;
                NotificationType notificationType10 = NotificationType.TWITTER;
                iArr12[8] = 10;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                int[] iArr13 = d;
                NotificationType notificationType11 = NotificationType.FACEBOOK;
                iArr13[6] = 11;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                int[] iArr14 = d;
                NotificationType notificationType12 = NotificationType.SNAPCHAT;
                iArr14[12] = 12;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                int[] iArr15 = d;
                NotificationType notificationType13 = NotificationType.WHATSAPP;
                iArr15[4] = 13;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                int[] iArr16 = d;
                NotificationType notificationType14 = NotificationType.INSTAGRAM;
                iArr16[7] = 14;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                int[] iArr17 = d;
                NotificationType notificationType15 = NotificationType.MESSENGER;
                iArr17[9] = 15;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                int[] iArr18 = d;
                NotificationType notificationType16 = NotificationType.TELEGRAM;
                iArr18[17] = 16;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                int[] iArr19 = d;
                NotificationType notificationType17 = NotificationType.CUSTOM_EVENT;
                iArr19[19] = 17;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                int[] iArr20 = d;
                NotificationType notificationType18 = NotificationType.OTHER_APPS;
                iArr20[18] = 18;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                int[] iArr21 = d;
                NotificationType notificationType19 = NotificationType.GMAIL;
                iArr21[26] = 19;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                int[] iArr22 = d;
                NotificationType notificationType20 = NotificationType.KAKAO_TALK;
                iArr22[28] = 20;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                int[] iArr23 = d;
                NotificationType notificationType21 = NotificationType.YOUTUBE;
                iArr23[27] = 21;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                int[] iArr24 = d;
                NotificationType notificationType22 = NotificationType.NEWS;
                iArr24[29] = 22;
            } catch (NoSuchFieldError unused28) {
            }
            int[] iArr25 = new int[com.coveiot.sdk.ble.api.error.Type.values().length];
            c = iArr25;
            try {
                iArr25[com.coveiot.sdk.ble.api.error.Type.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                c[com.coveiot.sdk.ble.api.error.Type.SERVICE_BUSY.ordinal()] = 2;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                c[com.coveiot.sdk.ble.api.error.Type.COMMAND_TIMED_OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                c[com.coveiot.sdk.ble.api.error.Type.COMMAND_WRITE_FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                c[com.coveiot.sdk.ble.api.error.Type.WATCH_RESPONSE_FAILED.ordinal()] = 5;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                c[com.coveiot.sdk.ble.api.error.Type.WATCH_BUSY.ordinal()] = 6;
            } catch (NoSuchFieldError unused34) {
            }
            int[] iArr26 = new int[ResponseType.values().length];
            b = iArr26;
            try {
                iArr26[ResponseType.GET_LIVE_HEALTH_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                b[ResponseType.GET_LIVE_STEPS.ordinal()] = 2;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                b[ResponseType.FIND_MY_PHONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                b[ResponseType.PROBE.ordinal()] = 4;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                b[ResponseType.LIVE_RAW_PPG.ordinal()] = 5;
            } catch (NoSuchFieldError unused39) {
            }
            int[] iArr27 = new int[CommandType.values().length];
            f3172a = iArr27;
            try {
                iArr27[CommandType.SET_ALARM.ordinal()] = 1;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                f3172a[CommandType.HOUR_FORMAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                f3172a[CommandType.SAVE_HEIGHT_WEIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                f3172a[CommandType.SET_BAND_DISPLAY_SETTINGS.ordinal()] = 4;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                f3172a[CommandType.SET_SEDENTARY_REMINDER.ordinal()] = 5;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                f3172a[CommandType.SET_HAND_PREFERENCE.ordinal()] = 6;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                f3172a[CommandType.SET_WALK_TARGET.ordinal()] = 7;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                f3172a[CommandType.ACTIVITY_PAUSE.ordinal()] = 8;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                f3172a[CommandType.SET_FITNESS_INFO.ordinal()] = 9;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                f3172a[CommandType.SET_BP_CALIBRATION.ordinal()] = 10;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                f3172a[CommandType.SET_PROBE.ordinal()] = 11;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                f3172a[CommandType.SET_MESSGAE_ALERT.ordinal()] = 12;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                f3172a[CommandType.SEND_MESSAGE_CONTENT.ordinal()] = 13;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                f3172a[CommandType.STOP_MESSAGE_NOTIFICATION.ordinal()] = 14;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                f3172a[CommandType.TIME_INTERVAL_FOR_AUTOMATIC_HEART_RATE.ordinal()] = 15;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                f3172a[CommandType.CURRENT_SPORT_MODE.ordinal()] = 16;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                f3172a[CommandType.VO2MAX.ordinal()] = 17;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                f3172a[CommandType.GET_TODAYS_WALK_DATA.ordinal()] = 18;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                f3172a[CommandType.WALK.ordinal()] = 19;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                f3172a[CommandType.SLEEP.ordinal()] = 20;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                f3172a[CommandType.HR_BP.ordinal()] = 21;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                f3172a[CommandType.RR.ordinal()] = 22;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                f3172a[CommandType.READ_DEVICE_INFO.ordinal()] = 23;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                f3172a[CommandType.HEART_RATE_CONTROL.ordinal()] = 24;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                f3172a[CommandType.BLOOD_PRESSURE_CONTROL.ordinal()] = 25;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                f3172a[CommandType.LIVE_STEPS_CONTROL.ordinal()] = 26;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                f3172a[CommandType.READ_BATTERY_LEVEL.ordinal()] = 27;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                f3172a[CommandType.READ_MANUAL_BP.ordinal()] = 28;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                f3172a[CommandType.SET_DISTANCE_UNIT.ordinal()] = 29;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                f3172a[CommandType.GET_SOCIAL_DISTANCE_SETTINGS.ordinal()] = 30;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                f3172a[CommandType.SET_SOCIAL_DISTANCE_SETTINGS.ordinal()] = 31;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                f3172a[CommandType.DELETE_NEARBY_DEVICE_LIST.ordinal()] = 32;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                f3172a[CommandType.GET_NEARBY_DEVICE_LIST.ordinal()] = 33;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                f3172a[CommandType.SET_WOMEN_WELLNESS.ordinal()] = 34;
            } catch (NoSuchFieldError unused73) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public class EventBusRegistrationInMainThreadRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public LeonardoBleApiImpl f3180a;

        public EventBusRegistrationInMainThreadRunnable(LeonardoBleApiImpl leonardoBleApiImpl, LeonardoBleApiImpl leonardoBleApiImpl2) {
            this.f3180a = null;
            this.f3180a = leonardoBleApiImpl2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().register(this.f3180a);
        }
    }

    /* loaded from: classes2.dex */
    public class QueueObject {

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3181a;

        public QueueObject(LeonardoBleApiImpl leonardoBleApiImpl, BleBaseRequest bleBaseRequest) {
            this.f3181a = bleBaseRequest;
        }
    }

    public LeonardoBleApiImpl() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.12
            @Override // java.lang.Runnable
            public void run() {
                LeonardoBleApiImpl.this.uiHandler = new Handler(Looper.getMainLooper());
                LeonardoBleApiImpl leonardoBleApiImpl = LeonardoBleApiImpl.this;
                leonardoBleApiImpl.uiHandler.post(new EventBusRegistrationInMainThreadRunnable(leonardoBleApiImpl, leonardoBleApiImpl));
            }
        });
    }

    public static void a(LeonardoBleApiImpl leonardoBleApiImpl, List list, boolean z, ScanDeviceRequest scanDeviceRequest, ScanResultListener scanResultListener) {
        leonardoBleApiImpl.getClass();
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

    public static boolean checkIfServiceIsRunning() {
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (LeonardoBleCmdService.class.getName().equals(runningServiceInfo.service.getClassName()) && context.getPackageName().equals(runningServiceInfo.service.getPackageName())) {
                z = true;
            }
        }
        return z;
    }

    public static LeonardoBleApiImpl getInstance(Context context2) {
        if (m == null) {
            context = context2.getApplicationContext();
            m = new LeonardoBleApiImpl();
        }
        String str = l;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "check for service ++ ", moduleNames.getModuleName());
        intent = new Intent(context, LeonardoBleCmdService.class);
        if (!checkIfServiceIsRunning()) {
            LogHelper.d(str, "service is not running ++ ", moduleNames.getModuleName());
            try {
                context.bindService(intent, serviceConnection, 1);
                if (Build.VERSION.SDK_INT >= 26) {
                    context.startForegroundService(intent);
                } else {
                    context.startService(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
                BleApiUtils.checkExceptionAndShowNotification(e, context);
            }
        } else if (bleService == null) {
            context.bindService(intent, serviceConnection, 1);
        }
        return m;
    }

    public void addToQueue(BleBaseRequest bleBaseRequest) {
        synchronized (n) {
            n.add(new QueueObject(this, bleBaseRequest));
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return getBusyStatus() == BusyStatus.IDLE;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void cleanUpCommands() {
        setUserSettings(new LiveStepsControlRequest.Builder(false).build(), new SettingsResultListener() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.10
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(BleBaseError bleBaseError) {
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
                final LeonardoBleApiImpl leonardoBleApiImpl = LeonardoBleApiImpl.this;
                String str = LeonardoBleApiImpl.l;
                leonardoBleApiImpl.getClass();
                leonardoBleApiImpl.setUserSettings(new LiveHeartRateControlRequest.Builder(false).build(), new SettingsResultListener() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.11
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(BleBaseError bleBaseError) {
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse2) {
                        LeonardoBleApiImpl leonardoBleApiImpl2 = LeonardoBleApiImpl.this;
                        String str2 = LeonardoBleApiImpl.l;
                        leonardoBleApiImpl2.getClass();
                        leonardoBleApiImpl2.setUserSettings(new SportModeRequest.Builder().setIsIndoor(false).setModes(false, false, false, false, false).build(), new SettingsResultListener(leonardoBleApiImpl2) { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.9
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(BleBaseError bleBaseError) {
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse3) {
                            }
                        });
                    }
                });
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void clearCommandQueue() {
        synchronized (n) {
            if (n != null && n.size() > 0) {
                synchronized (n) {
                    n.clear();
                }
            }
            LeonardoBleCmdService leonardoBleCmdService = bleService;
            if (leonardoBleCmdService != null) {
                leonardoBleCmdService.clearQueue();
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(final ConnectRequest connectRequest, final ConnectionResultListener connectionResultListener) {
        clearCommandQueue();
        this.d.removeCallbacksAndMessages(null);
        this.d.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.15
            @Override // java.lang.Runnable
            public void run() {
                LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
                if (leonardoBleCmdService == null || connectRequest == null) {
                    if (leonardoBleCmdService == null) {
                        try {
                            Intent intent2 = new Intent(LeonardoBleApiImpl.context, LeonardoBleCmdService.class);
                            LeonardoBleApiImpl.context.bindService(intent2, LeonardoBleApiImpl.serviceConnection, 1);
                            if (Build.VERSION.SDK_INT >= 26) {
                                LeonardoBleApiImpl.context.startForegroundService(intent2);
                            } else {
                                LeonardoBleApiImpl.context.startService(intent2);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            BleApiUtils.checkExceptionAndShowNotification(e, LeonardoBleApiImpl.context);
                        }
                        connectionResultListener.onError(new Error(Type.SERVICE_NOT_RUNNING, "Service not running, call connect again"));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.COMMAND_REQUEST_ERROR, "Request should not be null"));
                    return;
                }
                CloveBleState.BleState connectionState = leonardoBleCmdService.getConnectionState();
                CloveBleState.BleState bleState = CloveBleState.BleState.CONNECTED;
                if (connectionState == bleState && ((String) BlePreferenceManager.getPreference(LeonardoBleApiImpl.bleService.getApplicationContext(), CommonPreference.BLE_DEVICE_ADDRESS, "")).equalsIgnoreCase(connectRequest.getMacAddress())) {
                    connectionResultListener.onConnectionResponse(ConnectionStatus.CONNECTED);
                } else if (LeonardoBleApiImpl.bleService.getConnectionState() == CloveBleState.BleState.DISCONNECTED) {
                    BleBaseRequest bleBaseRequest = new BleBaseRequest();
                    bleBaseRequest.setBleCommand(BleCommand.CONNECT);
                    bleBaseRequest.setResponseListener(connectionResultListener);
                    LeonardoBleApiImpl.this.addToQueue(bleBaseRequest);
                    LeonardoBleApiImpl.bleService.connect(connectRequest.getMacAddress());
                } else if (LeonardoBleApiImpl.bleService.getConnectionState() == bleState) {
                    connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, "Connected to band " + LeonardoBleApiImpl.bleService.mBluetoothDevice.getAddress()));
                } else if (LeonardoBleApiImpl.bleService.getConnectionState() == CloveBleState.BleState.CONNECTING) {
                    LeonardoBleCmdService leonardoBleCmdService2 = LeonardoBleApiImpl.bleService;
                    if (leonardoBleCmdService2.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, "Connection in progress " + LeonardoBleApiImpl.bleService.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, leonardoBleCmdService2.getConnectionState().getStateAsString()));
                } else {
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, LeonardoBleApiImpl.bleService.getConnectionState().getStateAsString()));
                }
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(final ConnectionResultListener connectionResultListener) {
        if (bleService != null) {
            BleBaseRequest bleBaseRequest = new BleBaseRequest();
            bleBaseRequest.setBleCommand(BleCommand.DISCONNECT);
            bleBaseRequest.setResponseListener(connectionResultListener);
            addToQueue(bleBaseRequest);
            bleService.disconnectAndForget();
            bleService.clearQueue();
            a();
            this.d.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.16
                @Override // java.lang.Runnable
                public void run() {
                    connectionResultListener.onConnectionResponse(LeonardoBleApiImpl.this.getConnectionStatus());
                }
            }, 1000L);
            return;
        }
        connectionResultListener.onError(new Error(Type.SERVICE_NOT_RUNNING, "service is not present"));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return this.c;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        if (bleService != null) {
            return new ConnectionInfo(getConnectionStatus(), bleService.getConnectionError(), bleService.getConnectionStageChangeTimeStamp());
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public ConnectionStatus getConnectionStatus() {
        return a(ConnectionStatus.DISCONNECTED);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        LeonardoBleCmdService leonardoBleCmdService = bleService;
        if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            int i = Calendar.getInstance().get(11);
            if (Calendar.getInstance().get(12) > 0) {
                i++;
            }
            boolean z = bleBaseRequest instanceof StepsDataRequest;
            int i2 = TimeConstants.DAY;
            int i3 = 0;
            long j = 6;
            if (z) {
                StepsDataRequest stepsDataRequest = (StepsDataRequest) bleBaseRequest;
                this.f = 0;
                long findDateDifference = BleApiUtils.findDateDifference(stepsDataRequest.getStartDate(), stepsDataRequest.getEndDate());
                Date startDate = stepsDataRequest.getStartDate();
                if (findDateDifference > 6) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startDate);
                    calendar.setTimeInMillis(calendar.getTimeInMillis() + ((findDateDifference - 6) * 86400000));
                    startDate = calendar.getTime();
                } else {
                    j = findDateDifference;
                }
                BleCommand bleCommand = BleCommand.GET_STEPS_DATA;
                if (getSameCommandCount(bleCommand) > 0) {
                    removeSimilarCommand(bleCommand);
                }
                int i4 = (int) ((567 * j) + (i * 24));
                int i5 = 0;
                while (i5 <= j) {
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(startDate);
                    calendar2.setTimeInMillis(calendar2.getTimeInMillis() + (i5 * i2));
                    Date time = calendar2.getTime();
                    int startHour = stepsDataRequest.getStartHour();
                    int endHour = stepsDataRequest.getEndHour();
                    if (startHour == -1) {
                        startHour = 0;
                    }
                    if (endHour == -1) {
                        endHour = 23;
                    }
                    WalkDataReq build = new WalkDataReq.Builder().setStartDate(time).setEndDate(time).setStartHour(startHour).setEndHour(endHour).setStrideLength(stepsDataRequest.getStrideLength()).setRunStrideLength(stepsDataRequest.getRunStrideLength()).setId(new ExpectedDataSize(i4)).build();
                    BleBaseRequest build2 = new StepsDataRequest.Builder().setStartDate(time).setEndDate(time).setStartHour(startHour).setEndHour(endHour).build();
                    build2.setBleCommand(BleCommand.GET_STEPS_DATA);
                    build2.setResponseListener(dataResultListener);
                    build2.setRequId(UUID.randomUUID().toString());
                    addToQueue(build2);
                    build.setReqId(build2.getRequId());
                    bleService.sendRequest(build, this);
                    i5++;
                    i2 = TimeConstants.DAY;
                }
            } else if (bleBaseRequest instanceof SleepDataRequest) {
                SleepDataRequest sleepDataRequest = (SleepDataRequest) bleBaseRequest;
                long findDateDifference2 = BleApiUtils.findDateDifference(sleepDataRequest.getStartDate(), sleepDataRequest.getEndDate());
                this.sleepDataSize = 0;
                Date startDate2 = sleepDataRequest.getStartDate();
                if (findDateDifference2 > 6) {
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(startDate2);
                    calendar3.setTimeInMillis(((findDateDifference2 - 6) * 86400000) + calendar3.getTimeInMillis());
                    startDate2 = calendar3.getTime();
                } else {
                    j = findDateDifference2;
                }
                BleCommand bleCommand2 = BleCommand.GET_SLEEP_DATA;
                if (getSameCommandCount(bleCommand2) > 0) {
                    removeSimilarCommand(bleCommand2);
                }
                int i6 = (int) ((1 + j) * 360);
                while (i3 <= j) {
                    Calendar calendar4 = Calendar.getInstance();
                    calendar4.setTime(startDate2);
                    int i7 = i6;
                    calendar4.setTimeInMillis(calendar4.getTimeInMillis() + (i3 * TimeConstants.DAY));
                    Date time2 = calendar4.getTime();
                    int startHour2 = sleepDataRequest.getStartHour();
                    int endHour2 = sleepDataRequest.getEndHour();
                    if (startHour2 == -1) {
                        startHour2 = 12;
                    }
                    if (endHour2 == -1) {
                        endHour2 = 11;
                    }
                    SleepDataReq build3 = new SleepDataReq.Builder().setStartDate(time2).setEndDate(time2).setStartHour(startHour2 < 12 ? 12 : startHour2).setEndHour(endHour2 > 11 ? 11 : endHour2).setId(new ExpectedDataSize(i7)).build();
                    BleBaseRequest build4 = new SleepDataRequest.Builder().setStartDate(time2).setEndDate(time2).setStartHour(startHour2).setEndHour(endHour2).build();
                    build4.setResponseListener(dataResultListener);
                    build4.setBleCommand(BleCommand.GET_SLEEP_DATA);
                    build4.setRequId(UUID.randomUUID().toString());
                    addToQueue(build4);
                    build3.setReqId(build4.getRequId());
                    LogHelper.d(l, "queue size is ++ " + n.size() + "count value is ++" + i3, ModuleNames.BLEABSTRACT.getModuleName());
                    bleService.sendRequest(build3, this);
                    i3++;
                    i6 = i7;
                }
            } else {
                Integer num = (Integer) BlePreferenceManager.getPreference(context, CommonPreference.HR_INTERVAL_VALUE, 5);
                Integer num2 = 5;
                if (bleBaseRequest instanceof HeartRateDataRequest) {
                    this.g = 0;
                    HeartRateDataRequest heartRateDataRequest = (HeartRateDataRequest) bleBaseRequest;
                    long findDateDifference3 = BleApiUtils.findDateDifference(heartRateDataRequest.getStartDate(), heartRateDataRequest.getEndDate());
                    Date startDate3 = heartRateDataRequest.getStartDate();
                    if (findDateDifference3 > 6) {
                        Calendar calendar5 = Calendar.getInstance();
                        calendar5.setTime(startDate3);
                        calendar5.setTimeInMillis(calendar5.getTimeInMillis() + ((findDateDifference3 - 6) * 86400000));
                        startDate3 = calendar5.getTime();
                    } else {
                        j = findDateDifference3;
                    }
                    BleCommand bleCommand3 = BleCommand.GET_HEARTRATE_DATA;
                    if (getSameCommandCount(bleCommand3) > 0) {
                        removeSimilarCommand(bleCommand3);
                    }
                    int i8 = (int) ((1152 * j) + (i * 48));
                    int i9 = 0;
                    while (i9 <= j) {
                        Calendar calendar6 = Calendar.getInstance();
                        calendar6.setTime(startDate3);
                        long j2 = j;
                        calendar6.setTimeInMillis(calendar6.getTimeInMillis() + (i9 * TimeConstants.DAY));
                        Date time3 = calendar6.getTime();
                        int startHour3 = heartRateDataRequest.getStartHour();
                        int endHour3 = heartRateDataRequest.getEndHour();
                        if (startHour3 == -1) {
                            startHour3 = 0;
                        }
                        if (endHour3 == -1) {
                            endHour3 = 23;
                        }
                        HeartRateBpReq build5 = new HeartRateBpReq.Builder().setStartDate(time3).setEndDate(time3).setStartHour(startHour3).setEndHour(endHour3).setTimeInterval(num2.intValue()).setId(new ExpectedDataSize(i8)).build();
                        BleBaseRequest build6 = new HeartRateDataRequest.Builder().setStartDate(time3).setEndDate(time3).setStartHour(startHour3).setEndHour(endHour3).build();
                        build6.setResponseListener(dataResultListener);
                        build6.setBleCommand(BleCommand.GET_HEARTRATE_DATA);
                        build6.setRequId(UUID.randomUUID().toString());
                        addToQueue(build6);
                        build5.setReqId(build6.getRequId());
                        bleService.sendRequest(build5, this);
                        i9++;
                        j = j2;
                    }
                } else if (bleBaseRequest instanceof BpDataRequest) {
                    BpDataRequest bpDataRequest = (BpDataRequest) bleBaseRequest;
                    long findDateDifference4 = BleApiUtils.findDateDifference(bpDataRequest.getStartDate(), bpDataRequest.getEndDate());
                    Date startDate4 = bpDataRequest.getStartDate();
                    if (findDateDifference4 > 6) {
                        Calendar calendar7 = Calendar.getInstance();
                        calendar7.setTime(startDate4);
                        calendar7.setTimeInMillis(calendar7.getTimeInMillis() + ((findDateDifference4 - 6) * 86400000));
                        startDate4 = calendar7.getTime();
                    } else {
                        j = findDateDifference4;
                    }
                    BleCommand bleCommand4 = BleCommand.GET_BP_DATA;
                    if (getSameCommandCount(bleCommand4) > 0) {
                        removeSimilarCommand(bleCommand4);
                    }
                    int i10 = 0;
                    while (true) {
                        int i11 = (i10 > j ? 1 : (i10 == j ? 0 : -1));
                        if (i11 > 0) {
                            return;
                        }
                        Calendar calendar8 = Calendar.getInstance();
                        calendar8.setTime(startDate4);
                        int i12 = i10;
                        calendar8.setTimeInMillis(calendar8.getTimeInMillis() + (i10 * TimeConstants.DAY));
                        Date time4 = calendar8.getTime();
                        int startHour4 = bpDataRequest.getStartHour();
                        int endHour4 = bpDataRequest.getEndHour();
                        if (startHour4 == -1) {
                            startHour4 = 0;
                        }
                        if (endHour4 == -1) {
                            endHour4 = 23;
                        }
                        HeartRateBpReq build7 = new HeartRateBpReq.Builder().setStartDate(time4).setEndDate(time4).setStartHour(startHour4).setEndHour(endHour4).setTimeInterval(num2.intValue()).build();
                        BpDataRequest build8 = new BpDataRequest.Builder().setStartDate(time4).setEndDate(time4).setStartHour(startHour4).setEndHour(endHour4).build();
                        build8.setResponseListener(dataResultListener);
                        build8.setBleCommand(BleCommand.GET_BP_DATA);
                        build8.setRequId(UUID.randomUUID().toString());
                        build7.setReqId(build8.getRequId());
                        HrBpDataRes hrBpDataRes = new HrBpDataRes(CommandType.HR_BP, build7);
                        hrBpDataRes.setResponseData(this.h.get(BleUtils.getDateFormater("yyyy-MM-dd").format(time4)));
                        BpResponse bpResponse = LeonardoFormatter.getBpResponse(hrBpDataRes.getHrBpData());
                        if (i11 == 0) {
                            bpResponse.setComplete(true);
                        } else {
                            bpResponse.setComplete(false);
                        }
                        LogHelper.d(l, "bp isComplete ++ " + bpResponse.isComplete(), ModuleNames.BLEABSTRACT.getModuleName());
                        BleBaseResponse bleBaseResponse = new BleBaseResponse(build8);
                        bleBaseResponse.setResponseData(bpResponse);
                        onDataResponse(bleBaseResponse, (DataResultListener) build8.getResponseListener());
                        i10 = i12 + 1;
                    }
                } else if (bleBaseRequest instanceof RrHistoryRequest) {
                    RrHistoryRequest rrHistoryRequest = (RrHistoryRequest) bleBaseRequest;
                    long findDateDifference5 = BleApiUtils.findDateDifference(rrHistoryRequest.getStartDate(), rrHistoryRequest.getEndDate());
                    Date startDate5 = rrHistoryRequest.getStartDate();
                    if (findDateDifference5 > 6) {
                        Calendar calendar9 = Calendar.getInstance();
                        calendar9.setTime(startDate5);
                        calendar9.setTimeInMillis(calendar9.getTimeInMillis() + ((findDateDifference5 - 6) * 86400000));
                        startDate5 = calendar9.getTime();
                    } else {
                        j = findDateDifference5;
                    }
                    BleCommand bleCommand5 = BleCommand.GET_RR_HISTORY_DATA;
                    if (getSameCommandCount(bleCommand5) > 0) {
                        removeSimilarCommand(bleCommand5);
                    }
                    int i13 = 0;
                    while (true) {
                        int i14 = (i13 > j ? 1 : (i13 == j ? 0 : -1));
                        if (i14 <= 0) {
                            Calendar calendar10 = Calendar.getInstance();
                            calendar10.setTime(startDate5);
                            calendar10.setTimeInMillis(calendar10.getTimeInMillis() + (i13 * TimeConstants.DAY));
                            Date time5 = calendar10.getTime();
                            int startHour5 = rrHistoryRequest.getStartHour();
                            int endHour5 = rrHistoryRequest.getEndHour();
                            if (startHour5 == -1) {
                                startHour5 = 0;
                            }
                            if (endHour5 == -1) {
                                endHour5 = 23;
                            }
                            HeartRateBpReq build9 = new HeartRateBpReq.Builder().setStartDate(time5).setEndDate(time5).setStartHour(startHour5).setEndHour(endHour5).setTimeInterval(num2.intValue()).build();
                            RrHistoryRequest build10 = new RrHistoryRequest.Builder().setStartDate(time5).setEndDate(time5).setStartHour(startHour5).setEndHour(endHour5).build();
                            build10.setResponseListener(dataResultListener);
                            build10.setBleCommand(BleCommand.GET_RR_HISTORY_DATA);
                            build10.setRequId(UUID.randomUUID().toString());
                            build9.setReqId(build10.getRequId());
                            HrBpDataRes hrBpDataRes2 = new HrBpDataRes(CommandType.HR_BP, build9);
                            hrBpDataRes2.setResponseData(this.h.get(BleUtils.getDateFormater("yyyy-MM-dd").format(time5)));
                            RrResponse rrResponse = LeonardoFormatter.getRrResponse(hrBpDataRes2.getHrBpData());
                            if (i14 == 0) {
                                rrResponse.setComplete(true);
                            } else {
                                rrResponse.setComplete(false);
                            }
                            LogHelper.d(l, "RR isComplete ++ " + rrResponse.isComplete(), ModuleNames.BLEABSTRACT.getModuleName());
                            BleBaseResponse bleBaseResponse2 = new BleBaseResponse(build10);
                            bleBaseResponse2.setResponseData(rrResponse);
                            onDataResponse(bleBaseResponse2, (DataResultListener) build10.getResponseListener());
                            i13++;
                        } else {
                            this.h.clear();
                            return;
                        }
                    }
                } else if (bleBaseRequest instanceof RrDataRequest) {
                    RrDataRequest rrDataRequest = (RrDataRequest) bleBaseRequest;
                    long findDateDifference6 = BleApiUtils.findDateDifference(rrDataRequest.getStartDate(), rrDataRequest.getEndDate());
                    Date startDate6 = rrDataRequest.getStartDate();
                    if (findDateDifference6 > 6) {
                        Calendar calendar11 = Calendar.getInstance();
                        calendar11.setTime(startDate6);
                        calendar11.setTimeInMillis(calendar11.getTimeInMillis() + ((findDateDifference6 - 6) * 86400000));
                        startDate6 = calendar11.getTime();
                    } else {
                        j = findDateDifference6;
                    }
                    BleCommand bleCommand6 = BleCommand.GET_RR_DATA;
                    if (getSameCommandCount(bleCommand6) > 0) {
                        removeSimilarCommand(bleCommand6);
                    }
                    for (int i15 = 0; i15 <= j; i15++) {
                        Calendar calendar12 = Calendar.getInstance();
                        calendar12.setTime(startDate6);
                        calendar12.setTimeInMillis(calendar12.getTimeInMillis() + (i15 * TimeConstants.DAY));
                        Date time6 = calendar12.getTime();
                        int startHour6 = rrDataRequest.getStartHour();
                        int endHour6 = rrDataRequest.getEndHour();
                        if (startHour6 == -1) {
                            startHour6 = 0;
                        }
                        if (endHour6 == -1) {
                            endHour6 = 23;
                        }
                        RrDataReq build11 = new RrDataReq.Builder().setStartDate(time6).setEndDate(time6).setStartHour(startHour6).setEndHour(endHour6).build();
                        BleBaseRequest build12 = new RrDataRequest.Builder().setStartDate(time6).setEndDate(time6).setStartHour(startHour6).setEndHour(endHour6).build();
                        build12.setResponseListener(dataResultListener);
                        build12.setBleCommand(BleCommand.GET_RR_DATA);
                        build12.setRequId(UUID.randomUUID().toString());
                        addToQueue(build12);
                        build11.setReqId(build12.getRequId());
                        bleService.sendRequest(build11, this);
                    }
                } else if (bleBaseRequest instanceof TodaysStepsDataRequest) {
                    TodaysStepsDataReq build13 = new TodaysStepsDataReq.Builder().build();
                    build13.setResponseListener(this);
                    bleBaseRequest.setResponseListener(dataResultListener);
                    bleBaseRequest.setBleCommand(BleCommand.GET_TODAYS_TOTAL_WALK_DATA);
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    addToQueue(bleBaseRequest);
                    build13.setReqId(bleBaseRequest.getRequId());
                    bleService.sendRequest(build13, this);
                } else if (bleBaseRequest instanceof DeviceInfoRequest) {
                    bleBaseRequest.setBleCommand(BleCommand.DEVICE_INFO);
                    bleBaseRequest.setResponseListener(dataResultListener);
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    this.j = null;
                    DeviceInfoRequest deviceInfoRequest = (DeviceInfoRequest) bleBaseRequest;
                    if (deviceInfoRequest.isSerialNo()) {
                        ReadDeviceSerialNumberReq build14 = new ReadDeviceSerialNumberReq.Builder().build();
                        build14.setResponseListener(this);
                        addToQueue(bleBaseRequest);
                        build14.setReqId(bleBaseRequest.getRequId());
                        bleService.sendRequest(build14, this);
                    }
                    if (deviceInfoRequest.isDevicName()) {
                        ReadDeviceNameReq build15 = new ReadDeviceNameReq.Builder().build();
                        build15.setResponseListener(this);
                        addToQueue(bleBaseRequest);
                        build15.setReqId(bleBaseRequest.getRequId());
                        bleService.sendRequest(build15, this);
                    }
                    if (deviceInfoRequest.isMacAddress()) {
                        String macAddress = getMacAddress();
                        if (this.j == null) {
                            this.j = new DeviceInfoData();
                        }
                        this.j.setMacAddress(macAddress);
                    }
                    if (deviceInfoRequest.isFwVersion()) {
                        ReadDeviceFirmwareVersionReq build16 = new ReadDeviceFirmwareVersionReq.Builder().build();
                        build16.setResponseListener(this);
                        addToQueue(bleBaseRequest);
                        build16.setReqId(bleBaseRequest.getRequId());
                        bleService.sendRequest(build16, this);
                    }
                    if (deviceInfoRequest.isHwVersion()) {
                        ReadDeviceHardwareVersionReq build17 = new ReadDeviceHardwareVersionReq.Builder().build();
                        build17.setResponseListener(this);
                        addToQueue(bleBaseRequest);
                        build17.setReqId(bleBaseRequest.getRequId());
                        bleService.sendRequest(build17, this);
                    }
                    if (deviceInfoRequest.isModelNo()) {
                        ReadDeviceModelReq build18 = new ReadDeviceModelReq.Builder().build();
                        build18.setResponseListener(this);
                        addToQueue(bleBaseRequest);
                        build18.setReqId(bleBaseRequest.getRequId());
                        bleService.sendRequest(build18, this);
                    }
                    if (deviceInfoRequest.isSoftwareRevision()) {
                        ReadDeviceSoftwareVersionReq build19 = new ReadDeviceSoftwareVersionReq.Builder().build();
                        build19.setResponseListener(this);
                        addToQueue(bleBaseRequest);
                        build19.setReqId(bleBaseRequest.getRequId());
                        bleService.sendRequest(build19, this);
                    }
                    if (deviceInfoRequest.isManufacturerName()) {
                        ReadDeviceManufacturerReq build20 = new ReadDeviceManufacturerReq.Builder().build();
                        build20.setResponseListener(this);
                        addToQueue(bleBaseRequest);
                        build20.setReqId(bleBaseRequest.getRequId());
                        bleService.sendRequest(build20, this);
                    }
                } else if (bleBaseRequest instanceof BatteryLevelRequest) {
                    bleBaseRequest.setResponseListener(dataResultListener);
                    ReadBatteryLevelReq build21 = new ReadBatteryLevelReq.Builder().build();
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    bleBaseRequest.setBleCommand(BleCommand.READ_BATTERY_LEVEL);
                    build21.setResponseListener(this);
                    addToQueue(bleBaseRequest);
                    build21.setReqId(bleBaseRequest.getRequId());
                    bleService.sendRequest(build21, this);
                } else if (bleBaseRequest instanceof ReadManualBpRequest) {
                    bleBaseRequest.setResponseListener(dataResultListener);
                    ReadManualBpReq readManualBpReq = new ReadManualBpReq(null);
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    bleBaseRequest.setBleCommand(BleCommand.READ_MANUAL_BP);
                    readManualBpReq.setResponseListener(this);
                    addToQueue(bleBaseRequest);
                    readManualBpReq.setReqId(bleBaseRequest.getRequId());
                    bleService.sendRequest(readManualBpReq, this);
                } else if (bleBaseRequest instanceof GetSocialDistanceSettingsRequest) {
                    GetSocialDistanceScanSettingsReq build22 = new GetSocialDistanceScanSettingsReq.Builder().build();
                    build22.setResponseListener(this);
                    bleBaseRequest.setResponseListener(dataResultListener);
                    bleBaseRequest.setBleCommand(BleCommand.GET_SOCIAL_DISTANCE_SETTINGS);
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    addToQueue(bleBaseRequest);
                    build22.setReqId(bleBaseRequest.getRequId());
                    bleService.sendRequest(build22, this);
                } else if (bleBaseRequest instanceof GetNearbyDeviceListRequest) {
                    GetNearbyDeviceListRequest getNearbyDeviceListRequest = (GetNearbyDeviceListRequest) bleBaseRequest;
                    GetNearbyDeviceListReq build23 = new GetNearbyDeviceListReq.Builder().setIsDeviceName(getNearbyDeviceListRequest.isDeviceName()).setTimeOut(getNearbyDeviceListRequest.getTimeOut()).build();
                    build23.setResponseListener(this);
                    bleBaseRequest.setResponseListener(dataResultListener);
                    bleBaseRequest.setBleCommand(BleCommand.GET_NEARBY_DEVICE_LIST);
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    addToQueue(bleBaseRequest);
                    build23.setReqId(bleBaseRequest.getRequId());
                    bleService.sendRequest(build23, this);
                }
            }
        } else {
            onDataError(dataResultListener, new BleBaseError("Band not connected", bleBaseRequest.getBleCommand()));
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        deviceSupportedFeatures.setMaxDaysOfStepsDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfSleepDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfBpDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfRunDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfHeartRateDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfRrDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfCyclingDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfSwimmingDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfEcgRrDataOnBand(0);
        deviceSupportedFeatures.setStepsSupported(true);
        deviceSupportedFeatures.setSleepSupported(true);
        deviceSupportedFeatures.setRunSupported(false);
        deviceSupportedFeatures.setHeartRateSupported(true);
        deviceSupportedFeatures.setBpSupported(true);
        deviceSupportedFeatures.setRrSupported(false);
        deviceSupportedFeatures.setEcgSupported(false);
        deviceSupportedFeatures.setSwimmingSupported(false);
        deviceSupportedFeatures.setCyclingSupported(false);
        deviceSupportedFeatures.setShouldKeepDeviceConnectedAlways(true);
        deviceSupportedFeatures.setGpsSupported(false);
        deviceSupportedFeatures.setCalendarSyncSupported(true);
        deviceSupportedFeatures.setCallNotificationSupported(true);
        deviceSupportedFeatures.setSmsSupported(true);
        deviceSupportedFeatures.setMessageReadSupported(true);
        deviceSupportedFeatures.setSocialNotificationSupported(true);
        deviceSupportedFeatures.setSosSupported(false);
        deviceSupportedFeatures.setSedentaryReminderSupported(true);
        deviceSupportedFeatures.setVibrationAlarmSupported(true);
        deviceSupportedFeatures.setBandDisplaySupported(true);
        deviceSupportedFeatures.setHandSettingsSupported(true);
        deviceSupportedFeatures.setCameraFeatureSupported(true);
        deviceSupportedFeatures.setPhoneFinderSupported(true);
        deviceSupportedFeatures.setPersonalInfoSupported(true);
        deviceSupportedFeatures.setTimeFormatSettingsSupported(true);
        deviceSupportedFeatures.setStepGoalSupported(true);
        deviceSupportedFeatures.setPhoneTypeCommandSupported(this.k);
        deviceSupportedFeatures.setTimeFormartCommandSupported(true);
        deviceSupportedFeatures.setProbeFeatureSupported(true);
        deviceSupportedFeatures.setBPCalibrationSupported(true);
        deviceSupportedFeatures.setAutoHrSettingsSupported(true);
        deviceSupportedFeatures.setAppSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setBandSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setMaxCharSupportedInNotification(12);
        return BleApiUtils.updateDeviceSupportedFeatureBasedOnFWAndAppCapability(context, getMacAddress(), deviceSupportedFeatures);
    }

    public QueueObject getFromQueue(BleCommand bleCommand, BaseResponse baseResponse) {
        synchronized (n) {
            Iterator<QueueObject> it = n.iterator();
            while (it.hasNext()) {
                QueueObject next = it.next();
                if (next.f3181a.getBleCommand() == bleCommand && next.f3181a.getRequId().equals(baseResponse.getBaseRequest().getReqId())) {
                    it.remove();
                    return next;
                }
            }
            return null;
        }
    }

    public QueueObject getFromQueueWith(BaseRequest baseRequest) {
        synchronized (n) {
            Iterator<QueueObject> it = n.iterator();
            while (it.hasNext()) {
                QueueObject next = it.next();
                if (next.f3181a.getRequId().equals(baseRequest.getReqId())) {
                    return next;
                }
            }
            return null;
        }
    }

    public QueueObject getFromQueueWithoutRemoving(BleCommand bleCommand) {
        if (n == null || n.size() <= 0) {
            return null;
        }
        Iterator<QueueObject> it = n.iterator();
        while (it.hasNext()) {
            QueueObject next = it.next();
            if (next.f3181a.getBleCommand() == bleCommand) {
                return next;
            }
        }
        return null;
    }

    public QueueObject getFromQueuebasedOnDate(BleCommand bleCommand, BaseRequest baseRequest) {
        synchronized (n) {
            Iterator<QueueObject> it = n.iterator();
            while (it.hasNext()) {
                QueueObject next = it.next();
                if (next.f3181a.getBleCommand() == bleCommand) {
                    if (baseRequest instanceof WalkDataReq) {
                        Date startDate = ((StepsDataRequest) next.f3181a).getStartDate();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(startDate);
                        Calendar calendar2 = Calendar.getInstance();
                        calendar2.setTime(((WalkDataReq) baseRequest).getStartDate());
                        if (calendar.get(6) == calendar2.get(6)) {
                            it.remove();
                            return next;
                        }
                    } else if (baseRequest instanceof SleepDataReq) {
                        Date startDate2 = ((SleepDataRequest) next.f3181a).getStartDate();
                        Calendar calendar3 = Calendar.getInstance();
                        calendar3.setTime(startDate2);
                        Calendar calendar4 = Calendar.getInstance();
                        calendar4.setTime(((SleepDataReq) baseRequest).getStartDate());
                        if (calendar3.get(6) == calendar4.get(6)) {
                            it.remove();
                            return next;
                        }
                    } else if (baseRequest instanceof GetDistanceDataReq) {
                        Date startDate3 = ((GetDistanceDataRequest) next.f3181a).getStartDate();
                        Calendar calendar5 = Calendar.getInstance();
                        calendar5.setTime(startDate3);
                        Calendar calendar6 = Calendar.getInstance();
                        calendar6.setTime(((GetDistanceDataReq) baseRequest).getmDate());
                        if (calendar5.get(6) == calendar6.get(6)) {
                            it.remove();
                            return next;
                        }
                    } else if (baseRequest instanceof GetCalorieDataReq) {
                        Date startDate4 = ((GetCalorieDataRequest) next.f3181a).getStartDate();
                        Calendar calendar7 = Calendar.getInstance();
                        calendar7.setTime(startDate4);
                        Calendar calendar8 = Calendar.getInstance();
                        calendar8.setTime(((GetCalorieDataReq) baseRequest).getmDate());
                        if (calendar7.get(6) == calendar8.get(6)) {
                            it.remove();
                            return next;
                        }
                    } else if (baseRequest instanceof TemperatureDataDataReq) {
                        Date startDate5 = ((TemperatureDataRequest) next.f3181a).getStartDate();
                        Calendar calendar9 = Calendar.getInstance();
                        calendar9.setTime(startDate5);
                        Calendar calendar10 = Calendar.getInstance();
                        calendar10.setTime(((TemperatureDataDataReq) baseRequest).getStartDate());
                        if (calendar9.get(6) == calendar10.get(6)) {
                            it.remove();
                            return next;
                        }
                    } else if (baseRequest instanceof StressDataDataReq) {
                        Date startDate6 = ((StressDataRequest) next.f3181a).getStartDate();
                        Calendar calendar11 = Calendar.getInstance();
                        calendar11.setTime(startDate6);
                        Calendar calendar12 = Calendar.getInstance();
                        calendar12.setTime(((StressDataDataReq) baseRequest).getStartDate());
                        if (calendar11.get(6) == calendar12.get(6)) {
                            it.remove();
                            return next;
                        }
                    } else if (baseRequest instanceof HeartRateBpReq) {
                        BleBaseRequest bleBaseRequest = next.f3181a;
                        if (bleBaseRequest instanceof HeartRateDataRequest) {
                            Date startDate7 = ((HeartRateDataRequest) bleBaseRequest).getStartDate();
                            Calendar calendar13 = Calendar.getInstance();
                            calendar13.setTime(startDate7);
                            Calendar calendar14 = Calendar.getInstance();
                            calendar14.setTime(((HeartRateBpReq) baseRequest).getStartDate());
                            if (calendar13.get(6) == calendar14.get(6)) {
                                it.remove();
                                return next;
                            }
                        } else if (bleBaseRequest instanceof BpDataRequest) {
                            Date startDate8 = ((BpDataRequest) bleBaseRequest).getStartDate();
                            Calendar calendar15 = Calendar.getInstance();
                            calendar15.setTime(startDate8);
                            Calendar calendar16 = Calendar.getInstance();
                            calendar16.setTime(((HeartRateBpReq) baseRequest).getStartDate());
                            if (calendar15.get(6) == calendar16.get(6)) {
                                it.remove();
                                return next;
                            }
                        } else if (bleBaseRequest instanceof RrHistoryRequest) {
                            Date startDate9 = ((RrHistoryRequest) bleBaseRequest).getStartDate();
                            Calendar calendar17 = Calendar.getInstance();
                            calendar17.setTime(startDate9);
                            Calendar calendar18 = Calendar.getInstance();
                            calendar18.setTime(((HeartRateBpReq) baseRequest).getStartDate());
                            if (calendar17.get(6) == calendar18.get(6)) {
                                it.remove();
                                return next;
                            }
                        }
                    } else {
                        BleBaseRequest bleBaseRequest2 = next.f3181a;
                        if (bleBaseRequest2 instanceof ReadManualSpo2Request) {
                            Date startDate10 = ((ReadManualSpo2Request) bleBaseRequest2).getStartDate();
                            Calendar calendar19 = Calendar.getInstance();
                            calendar19.setTime(startDate10);
                            Calendar calendar20 = Calendar.getInstance();
                            calendar20.setTime(((SPO2DataReq) baseRequest).getStartDate());
                            if (calendar19.get(6) == calendar20.get(6)) {
                                it.remove();
                                return next;
                            }
                        } else if (bleBaseRequest2 instanceof ActivityModeWithSamplesRequest) {
                            Date startDate11 = ((ActivityModeWithSamplesRequest) bleBaseRequest2).getStartDate();
                            Calendar calendar21 = Calendar.getInstance();
                            calendar21.setTime(startDate11);
                            Calendar calendar22 = Calendar.getInstance();
                            calendar22.setTime(((GetActivitySummaryReq) baseRequest).getmDate());
                            if (calendar21.get(6) == calendar22.get(6)) {
                                it.remove();
                                return next;
                            }
                        } else if (bleBaseRequest2 instanceof PeriodicSPO2BleRequest) {
                            Date startDate12 = ((PeriodicSPO2BleRequest) bleBaseRequest2).getStartDate();
                            Calendar calendar23 = Calendar.getInstance();
                            calendar23.setTime(startDate12);
                            Calendar calendar24 = Calendar.getInstance();
                            calendar24.setTime(((PeriodicSPO2BaseReq) baseRequest).getStartDate());
                            if (calendar23.get(6) == calendar24.get(6)) {
                                it.remove();
                                return next;
                            }
                        } else if (bleBaseRequest2 instanceof ReadSedentaryDataRequest) {
                            Date startDate13 = ((ReadSedentaryDataRequest) bleBaseRequest2).getStartDate();
                            Calendar calendar25 = Calendar.getInstance();
                            calendar25.setTime(startDate13);
                            Calendar calendar26 = Calendar.getInstance();
                            calendar26.setTime(((ReadSedentaryDataReq) baseRequest).getStartDate());
                            if (calendar25.get(6) == calendar26.get(6)) {
                                it.remove();
                                return next;
                            }
                        } else if (bleBaseRequest2 instanceof GetSensAISummaryRequest) {
                            Date startDate14 = ((GetSensAISummaryRequest) bleBaseRequest2).getStartDate();
                            Calendar calendar27 = Calendar.getInstance();
                            calendar27.setTime(startDate14);
                            Calendar calendar28 = Calendar.getInstance();
                            calendar28.setTime(((GetSensAISummaryDataReq) baseRequest).getmDate());
                            if (calendar27.get(6) == calendar28.get(6)) {
                                it.remove();
                                return next;
                            }
                        }
                    }
                    if (baseRequest instanceof ReadRawPPGDataReq) {
                        Date startDate15 = ((ReadRawPPGHistoryDataRequest) next.f3181a).getStartDate();
                        Calendar calendar29 = Calendar.getInstance();
                        calendar29.setTime(startDate15);
                        Calendar calendar30 = Calendar.getInstance();
                        calendar30.setTime(((ReadRawPPGDataReq) baseRequest).getStartDate());
                        if (calendar29.get(6) == calendar30.get(6)) {
                            it.remove();
                            return next;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        return (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
    }

    public int getSameCommandCount(BleCommand bleCommand) {
        int i = 0;
        if (n != null && n.size() > 0) {
            Iterator<QueueObject> it = n.iterator();
            while (it.hasNext()) {
                if (it.next().f3181a.getBleCommand() == bleCommand) {
                    i++;
                }
            }
        }
        return i;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return BleApiUtils.isServiceRunning(LeonardoBleService.class.getName(), context);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Subscribe
    public void onConnectionStateChanged(CloveBleState cloveBleState) {
        ConnectionStatus connectionStatus;
        BleBaseRequest bleBaseRequest;
        BleBaseRequest bleBaseRequest2;
        ConnectionStatus connectionStatus2 = ConnectionStatus.DISCONNECTED;
        if (cloveBleState != null) {
            int i = AnonymousClass17.e[cloveBleState.getmState().ordinal()];
            if (i == 1) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else if (i == 2) {
                connectionStatus = ConnectionStatus.CONNECTING;
            } else if (i != 4) {
                connectionStatus = i != 5 ? connectionStatus2 : ConnectionStatus.DISCOVERING_SERVICES;
            } else {
                connectionStatus = ConnectionStatus.SCANNING;
            }
        } else {
            connectionStatus = ConnectionStatus.CONNECTING;
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.connectionStateLiveData;
        if (mutableLiveData != null) {
            mutableLiveData.setValue(connectionStatus);
            this.connectionStateLiveData.postValue(connectionStatus);
        }
        QueueObject fromQueueWithoutRemoving = getFromQueueWithoutRemoving(BleCommand.CONNECT);
        if (fromQueueWithoutRemoving != null && (bleBaseRequest2 = fromQueueWithoutRemoving.f3181a) != null && bleBaseRequest2.getResponseListener() != null && (fromQueueWithoutRemoving.f3181a.getResponseListener() instanceof ConnectionResultListener) && fromQueueWithoutRemoving.f3181a.getResponseListener() != null) {
            ((ConnectionResultListener) fromQueueWithoutRemoving.f3181a.getResponseListener()).onConnectionResponse(connectionStatus);
        }
        QueueObject fromQueue = getFromQueue(BleCommand.DISCONNECT);
        if (fromQueue != null && (bleBaseRequest = fromQueue.f3181a) != null && bleBaseRequest.getResponseListener() != null && (fromQueue.f3181a.getResponseListener() instanceof ConnectionResultListener)) {
            ((ConnectionResultListener) fromQueue.f3181a.getResponseListener()).onConnectionResponse(connectionStatus);
        }
        if (connectionStatus == connectionStatus2) {
            sendErrorAndClearQueue(new com.coveiot.sdk.ble.api.error.Error(com.coveiot.sdk.ble.api.error.Type.DEVICE_NOT_CONNECTED, "Device disconnected"));
        }
    }

    public void onDataError(final DataResultListener dataResultListener, final BleBaseError bleBaseError) {
        try {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(bleBaseError.getErrorMsg());
            ArrayList arrayList = new ArrayList();
            arrayList.add(bleBaseError.getBleCommands().name());
            analyticsLog.setData(arrayList);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.uiHandler.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.3
            @Override // java.lang.Runnable
            public void run() {
                dataResultListener.onDataError(bleBaseError);
            }
        });
    }

    public void onDataResponse(final BleBaseResponse bleBaseResponse, final DataResultListener dataResultListener) {
        this.uiHandler.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.4
            @Override // java.lang.Runnable
            public void run() {
                dataResultListener.onDataResponse(bleBaseResponse);
            }
        });
    }

    @Subscribe
    public void onError(Error error) {
        if (error != null) {
            if (error.getType().ordinal() != 7) {
                onFailure(new com.coveiot.sdk.ble.api.error.Error(com.coveiot.sdk.ble.api.error.Type.COMMAND_REQUEST_ERROR, error.getMessage()));
            } else {
                onFailure(new com.coveiot.sdk.ble.api.error.Error(com.coveiot.sdk.ble.api.error.Type.COMMAND_REQUEST_ERROR, error.getMessage()));
            }
        }
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void onFailure(com.coveiot.sdk.ble.api.error.Error error) {
        if (error != null) {
            String str = l;
            LogHelper.e(str, "Command onFailure " + error.toString());
            switch (AnonymousClass17.c[error.getType().ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    try {
                        AnalyticsLog analyticsLog = new AnalyticsLog();
                        analyticsLog.setEventName(error.getType().name());
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(error.getMessage());
                        analyticsLog.setData(arrayList);
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sendErrorAndClearQueue(error);
                    return;
                default:
                    return;
            }
        }
    }

    public void onProgressUpdate(ProgressDataBean progressDataBean) {
        QueueObject fromQueueWith = getFromQueueWith(progressDataBean.getBaseRequest());
        if (fromQueueWith == null) {
            return;
        }
        if (progressDataBean.getBaseRequest().getCommandName() == CommandNames.GET_NEARBY_DEVICE_LIST) {
            ((DataResultListener) fromQueueWith.f3181a.getResponseListener()).onProgressUpdate(new ProgressData(ProgressType.DETERMINATE, progressDataBean.getValue(), fromQueueWith.f3181a));
        } else if (progressDataBean.getBaseRequest().getCommandName() == CommandNames.GET_5MIN_WALK_DATA) {
            DataResultListener dataResultListener = (DataResultListener) fromQueueWith.f3181a.getResponseListener();
            if (progressDataBean.getProgressType() == com.coveiot.sdk.ble.api.model.ProgressType.DATA_BYTES) {
                this.f += progressDataBean.getValue();
            }
            dataResultListener.onProgressUpdate(new ProgressData(ProgressType.DETERMINATE, (int) ((this.f / ((ExpectedDataSize) progressDataBean.getBaseRequest().getId()).getSize()) * 100.0f), fromQueueWith.f3181a));
        } else if (progressDataBean.getBaseRequest().getCommandName() == CommandNames.GET_1MIN_SLEEP_DATA) {
            DataResultListener dataResultListener2 = (DataResultListener) fromQueueWith.f3181a.getResponseListener();
            if (progressDataBean.getProgressType() == com.coveiot.sdk.ble.api.model.ProgressType.DATA_BYTES) {
                this.sleepDataSize += progressDataBean.getValue();
            }
            dataResultListener2.onProgressUpdate(new ProgressData(ProgressType.DETERMINATE, (int) ((this.sleepDataSize / ((ExpectedDataSize) progressDataBean.getBaseRequest().getId()).getSize()) * 100.0f), fromQueueWith.f3181a));
        } else if (progressDataBean.getBaseRequest().getCommandName() == CommandNames.GET_HISTORY_DATA_FOR_AUTOMATIC_HR_BP_INTERVAL) {
            DataResultListener dataResultListener3 = (DataResultListener) fromQueueWith.f3181a.getResponseListener();
            if (progressDataBean.getProgressType() == com.coveiot.sdk.ble.api.model.ProgressType.DATA_BYTES) {
                this.g += progressDataBean.getValue();
            }
            dataResultListener3.onProgressUpdate(new ProgressData(ProgressType.DETERMINATE, (int) ((this.g / ((ExpectedDataSize) progressDataBean.getBaseRequest().getId()).getSize()) * 100.0f), fromQueueWith.f3181a));
        }
    }

    public void onResponse(BaseResponse baseResponse) {
        if (baseResponse != null) {
            try {
                switch (AnonymousClass17.f3172a[baseResponse.getActivityType().ordinal()]) {
                    case 1:
                        BleCommand bleCommand = BleCommand.SET_VIBRATION_ALARM;
                        QueueObject fromQueue = getFromQueue(bleCommand, baseResponse);
                        if (fromQueue != null) {
                            SettingsResultListener settingsResultListener = (SettingsResultListener) fromQueue.f3181a.getResponseListener();
                            if (((SetAlarmRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener, new BleBaseResponse(fromQueue.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener, new BleBaseError("Vibration alarm setting failed", bleCommand));
                                return;
                            }
                        }
                        return;
                    case 2:
                        BleCommand bleCommand2 = BleCommand.HOUR_FORMAT;
                        QueueObject fromQueue2 = getFromQueue(bleCommand2, baseResponse);
                        if (fromQueue2 != null) {
                            SettingsResultListener settingsResultListener2 = (SettingsResultListener) fromQueue2.f3181a.getResponseListener();
                            if (((SetTimeFormatRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener2, new BleBaseResponse(fromQueue2.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener2, new BleBaseError("Vibration alarm setting failed", bleCommand2));
                                return;
                            }
                        }
                        return;
                    case 3:
                        QueueObject fromQueue3 = getFromQueue(BleCommand.SET_FITNESS_PERSONAL_INFO, baseResponse);
                        if (fromQueue3 != null) {
                            SettingsResultListener settingsResultListener3 = (SettingsResultListener) fromQueue3.f3181a.getResponseListener();
                            if (((SaveFitnessProfileRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener3, new BleBaseResponse(fromQueue3.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener3, new BleBaseError("Vibration alarm setting failed", BleCommand.SET_VIBRATION_ALARM));
                                return;
                            }
                        }
                        return;
                    case 4:
                        BleCommand bleCommand3 = BleCommand.SET_BAND_DISPLAY;
                        QueueObject fromQueue4 = getFromQueue(bleCommand3, baseResponse);
                        if (fromQueue4 != null) {
                            SettingsResultListener settingsResultListener4 = (SettingsResultListener) fromQueue4.f3181a.getResponseListener();
                            BleBaseError bleBaseError = new BleBaseError("Band display setting failed", bleCommand3);
                            if (((BandDisplaySettingsRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener4, new BleBaseResponse(fromQueue4.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener4, bleBaseError);
                                return;
                            }
                        }
                        return;
                    case 5:
                        BleCommand bleCommand4 = BleCommand.SET_SEDENTARY_REMINDER;
                        QueueObject fromQueue5 = getFromQueue(bleCommand4, baseResponse);
                        if (fromQueue5 != null) {
                            SettingsResultListener settingsResultListener5 = (SettingsResultListener) fromQueue5.f3181a.getResponseListener();
                            if (((SetSedentaryReminderRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener5, new BleBaseResponse(fromQueue5.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener5, new BleBaseError("Sedentary reminder setting failed", bleCommand4));
                                return;
                            }
                        }
                        return;
                    case 6:
                        BleCommand bleCommand5 = BleCommand.SET_HAND_PREFERENCE;
                        QueueObject fromQueue6 = getFromQueue(bleCommand5, baseResponse);
                        if (fromQueue6 != null) {
                            SettingsResultListener settingsResultListener6 = (SettingsResultListener) fromQueue6.f3181a.getResponseListener();
                            if (((ChangeWearingHandRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener6, new BleBaseResponse(fromQueue6.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener6, new BleBaseError("Hand setting failed", bleCommand5));
                                return;
                            }
                        }
                        return;
                    case 7:
                        BleCommand bleCommand6 = BleCommand.SET_WALK_TARGET;
                        QueueObject fromQueue7 = getFromQueue(bleCommand6, baseResponse);
                        if (fromQueue7 != null) {
                            SettingsResultListener settingsResultListener7 = (SettingsResultListener) fromQueue7.f3181a.getResponseListener();
                            if (((SetWalkTargetRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener7, new BleBaseResponse(fromQueue7.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener7, new BleBaseError("Walk target setting failed", bleCommand6));
                                return;
                            }
                        }
                        return;
                    case 8:
                        BleCommand bleCommand7 = BleCommand.ACTIVITY_PAUSE;
                        QueueObject fromQueue8 = getFromQueue(bleCommand7, baseResponse);
                        if (fromQueue8 != null) {
                            SettingsResultListener settingsResultListener8 = (SettingsResultListener) fromQueue8.f3181a.getResponseListener();
                            if (((ActivityPauseResumeRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener8, new BleBaseResponse(fromQueue8.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener8, new BleBaseError("Walk target setting failed", bleCommand7));
                                return;
                            }
                        }
                        return;
                    case 9:
                        BleCommand bleCommand8 = BleCommand.SET_FITNESS_PERSONAL_INFO;
                        QueueObject fromQueue9 = getFromQueue(bleCommand8, baseResponse);
                        if (fromQueue9 != null) {
                            SettingsResultListener settingsResultListener9 = (SettingsResultListener) fromQueue9.f3181a.getResponseListener();
                            BleBaseResponse bleBaseResponse = new BleBaseResponse(fromQueue9.f3181a);
                            if (((SaveFitnessProfileRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener9, bleBaseResponse);
                                return;
                            } else {
                                onSettingsError(settingsResultListener9, new BleBaseError("Fitness personal info setting failed", bleCommand8));
                                return;
                            }
                        }
                        return;
                    case 10:
                        String str = l;
                        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                        LogHelper.d(str, "BP Calibration response received from parser!!!", moduleNames.getModuleName());
                        BleCommand bleCommand9 = BleCommand.SET_BP_CALIBRATION;
                        QueueObject fromQueue10 = getFromQueue(bleCommand9, baseResponse);
                        LogHelper.d(str, "BP Calibration response : " + fromQueue10, moduleNames.getModuleName());
                        if (fromQueue10 != null) {
                            SettingsResultListener settingsResultListener10 = (SettingsResultListener) fromQueue10.f3181a.getResponseListener();
                            BleBaseResponse bleBaseResponse2 = new BleBaseResponse(fromQueue10.f3181a);
                            LogHelper.d(str, "BP Calibration  ((SaveBpCalibrationRes) response).isSuccess(): " + ((SaveBpCalibrationRes) baseResponse).isSuccess(), moduleNames.getModuleName());
                            if (((SaveBpCalibrationRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener10, bleBaseResponse2);
                                return;
                            } else {
                                onSettingsError(settingsResultListener10, new BleBaseError("Bp Calibration setting failed", bleCommand9));
                                return;
                            }
                        }
                        return;
                    case 11:
                        String str2 = l;
                        ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
                        LogHelper.d(str2, "SET_PROBE command response received from parser!!!", moduleNames2.getModuleName());
                        BleCommand bleCommand10 = BleCommand.SET_PROBE;
                        QueueObject fromQueue11 = getFromQueue(bleCommand10, baseResponse);
                        LogHelper.d(str2, "Probe response : " + fromQueue11, moduleNames2.getModuleName());
                        if (fromQueue11 != null) {
                            SettingsResultListener settingsResultListener11 = (SettingsResultListener) fromQueue11.f3181a.getResponseListener();
                            BleBaseResponse bleBaseResponse3 = new BleBaseResponse(fromQueue11.f3181a);
                            LogHelper.d(str2, "Probe  ((SaveProbeRes) response).isSuccess(): " + ((SaveProbeRes) baseResponse).isSuccess(), moduleNames2.getModuleName());
                            if (((SaveProbeRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener11, bleBaseResponse3);
                                return;
                            } else {
                                onSettingsError(settingsResultListener11, new BleBaseError("Probe setting failed", bleCommand10));
                                return;
                            }
                        }
                        return;
                    case 12:
                        BleCommand bleCommand11 = BleCommand.SET_MESSAGE_ALERT_SWITCH;
                        QueueObject fromQueue12 = getFromQueue(bleCommand11, baseResponse);
                        if (fromQueue12 != null) {
                            SettingsResultListener settingsResultListener12 = (SettingsResultListener) fromQueue12.f3181a.getResponseListener();
                            if (((MessageAlertSwitchesRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener12, new BleBaseResponse(fromQueue12.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener12, new BleBaseError("Message alert setting failed", bleCommand11));
                                return;
                            }
                        }
                        return;
                    case 13:
                        BleCommand bleCommand12 = BleCommand.SET_MESSAGE_CONTENT;
                        QueueObject fromQueue13 = getFromQueue(bleCommand12, baseResponse);
                        if (fromQueue13 != null) {
                            SettingsResultListener settingsResultListener13 = (SettingsResultListener) fromQueue13.f3181a.getResponseListener();
                            if (((MessageContentRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener13, new BleBaseResponse(fromQueue13.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener13, new BleBaseError("Message content setting failed", bleCommand12));
                                return;
                            }
                        }
                        return;
                    case 14:
                        QueueObject fromQueue14 = getFromQueue(BleCommand.STOP_MESSAGE_NOTIFICATION, baseResponse);
                        if (fromQueue14 != null) {
                            SettingsResultListener settingsResultListener14 = (SettingsResultListener) fromQueue14.f3181a.getResponseListener();
                            if (((StopMessageNotificationRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener14, new BleBaseResponse(fromQueue14.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener14, new BleBaseError("Stop message content setting failed", BleCommand.SET_MESSAGE_CONTENT));
                                return;
                            }
                        }
                        return;
                    case 15:
                        BleCommand bleCommand13 = BleCommand.SET_HR_TIME_INTERVAL;
                        QueueObject fromQueue15 = getFromQueue(bleCommand13, baseResponse);
                        if (fromQueue15 != null) {
                            SettingsResultListener settingsResultListener15 = (SettingsResultListener) fromQueue15.f3181a.getResponseListener();
                            if (((TimeIntervalForAutomaticHeartRateRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener15, new BleBaseResponse(fromQueue15.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener15, new BleBaseError("Heart Rate time interval setting failed", bleCommand13));
                                return;
                            }
                        }
                        return;
                    case 16:
                        BleCommand bleCommand14 = BleCommand.SET_CURRENT_SPORT_MODE;
                        QueueObject fromQueue16 = getFromQueue(bleCommand14, baseResponse);
                        if (fromQueue16 != null) {
                            SettingsResultListener settingsResultListener16 = (SettingsResultListener) fromQueue16.f3181a.getResponseListener();
                            if (((CurrentSportModesRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener16, new BleBaseResponse(fromQueue16.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener16, new BleBaseError("Sports mode setting failed", bleCommand14));
                                return;
                            }
                        }
                        return;
                    case 17:
                        BleCommand bleCommand15 = BleCommand.UPDATE_V02MAX_VALUE;
                        QueueObject fromQueue17 = getFromQueue(bleCommand15, baseResponse);
                        if (fromQueue17 != null) {
                            SettingsResultListener settingsResultListener17 = (SettingsResultListener) fromQueue17.f3181a.getResponseListener();
                            if (((Vo2MaxRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener17, new BleBaseResponse(fromQueue17.f3181a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener17, new BleBaseError("Vo2max value updation failed", bleCommand15));
                                return;
                            }
                        }
                        return;
                    case 18:
                        BleCommand bleCommand16 = BleCommand.GET_TODAYS_TOTAL_WALK_DATA;
                        QueueObject fromQueue18 = getFromQueue(bleCommand16, baseResponse);
                        BleBaseResponse bleBaseResponse4 = new BleBaseResponse(fromQueue18.f3181a);
                        DataResultListener dataResultListener = (DataResultListener) fromQueue18.f3181a.getResponseListener();
                        try {
                            if (((TodaysStepsDataRes) baseResponse).isSuccess()) {
                                TodaysStepsResponse todaysStepsResponse = new TodaysStepsResponse();
                                TodaysWalkData stepsData = ((TodaysStepsDataRes) baseResponse).getStepsData();
                                TodaysStepsData todaysStepsData = new TodaysStepsData();
                                todaysStepsData.setTotalCalories(stepsData.getTotalCalories());
                                todaysStepsData.setTotalSteps(stepsData.getTotalSteps());
                                todaysStepsData.setTotalDistance(stepsData.getTotalDistance());
                                todaysStepsResponse.setTodaysStepsData(todaysStepsData);
                                if (this.liveStepsDataMutableLiveData != null) {
                                    final LiveStepsData liveStepsData = new LiveStepsData();
                                    liveStepsData.setLiveSteps(stepsData.getTotalSteps());
                                    liveStepsData.setMeters(stepsData.getTotalDistance());
                                    liveStepsData.setCalories(stepsData.getTotalCalories());
                                    this.uiHandler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.2
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            LeonardoBleApiImpl.this.liveStepsDataMutableLiveData.setValue(liveStepsData);
                                            LeonardoBleApiImpl.this.liveStepsDataMutableLiveData.postValue(liveStepsData);
                                        }
                                    });
                                }
                                bleBaseResponse4.setResponseData(todaysStepsResponse);
                                onDataResponse(bleBaseResponse4, dataResultListener);
                                return;
                            }
                            onDataError(dataResultListener, new BleBaseError("Get Today's Steps Data failed", bleCommand16));
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            onDataError(dataResultListener, new BleBaseError(e.getMessage(), BleCommand.GET_TODAYS_TOTAL_WALK_DATA));
                            return;
                        }
                    case 19:
                        onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, com.coveiot.sdk.ble.api.model.ProgressType.PACKETS));
                        BleCommand bleCommand17 = BleCommand.GET_STEPS_DATA;
                        QueueObject fromQueuebasedOnDate = getFromQueuebasedOnDate(bleCommand17, baseResponse.getBaseRequest());
                        if (fromQueuebasedOnDate != null) {
                            try {
                                int sameCommandCount = getSameCommandCount(bleCommand17);
                                String str3 = l;
                                ModuleNames moduleNames3 = ModuleNames.BLEABSTRACT;
                                LogHelper.d(str3, "walk queue size is ++ " + sameCommandCount, moduleNames3.getModuleName());
                                StepsResponse stepsResponse = LeonardoFormatter.getStepsResponse(((WalkDataRes) baseResponse).getStepsData());
                                if (sameCommandCount == 0) {
                                    stepsResponse.setComplete(true);
                                } else {
                                    stepsResponse.setComplete(false);
                                }
                                LogHelper.d(str3, "walk isComplete ++ " + stepsResponse.isComplete(), moduleNames3.getModuleName());
                                BleBaseResponse bleBaseResponse5 = new BleBaseResponse(fromQueuebasedOnDate.f3181a);
                                bleBaseResponse5.setResponseData(stepsResponse);
                                onDataResponse(bleBaseResponse5, (DataResultListener) fromQueuebasedOnDate.f3181a.getResponseListener());
                                return;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                onDataError((DataResultListener) fromQueuebasedOnDate.f3181a.getResponseListener(), new BleBaseError(e2.getMessage(), fromQueuebasedOnDate.f3181a.getBleCommand()));
                                return;
                            }
                        }
                        return;
                    case 20:
                        onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, com.coveiot.sdk.ble.api.model.ProgressType.PACKETS));
                        BleCommand bleCommand18 = BleCommand.GET_SLEEP_DATA;
                        QueueObject fromQueuebasedOnDate2 = getFromQueuebasedOnDate(bleCommand18, baseResponse.getBaseRequest());
                        if (fromQueuebasedOnDate2 != null) {
                            try {
                                int sameCommandCount2 = getSameCommandCount(bleCommand18);
                                String str4 = l;
                                ModuleNames moduleNames4 = ModuleNames.BLEABSTRACT;
                                LogHelper.d(str4, "sleep queue size is ++ " + sameCommandCount2, moduleNames4.getModuleName());
                                SleepResponse sleepResponse = LeonardoFormatter.getSleepResponse(((SleepDataRes) baseResponse).getSleepData());
                                if (Calendar.getInstance().get(11) >= 12) {
                                    if (sameCommandCount2 <= 1) {
                                        sleepResponse.setComplete(true);
                                        removeSimilarCommand(bleCommand18);
                                    } else {
                                        sleepResponse.setComplete(false);
                                    }
                                } else if (sameCommandCount2 == 0) {
                                    sleepResponse.setComplete(true);
                                } else {
                                    sleepResponse.setComplete(false);
                                }
                                LogHelper.d(str4, "isComplete  ++ " + sleepResponse.isComplete(), moduleNames4.getModuleName());
                                BleBaseResponse bleBaseResponse6 = new BleBaseResponse(fromQueuebasedOnDate2.f3181a);
                                bleBaseResponse6.setResponseData(sleepResponse);
                                onDataResponse(bleBaseResponse6, (DataResultListener) fromQueuebasedOnDate2.f3181a.getResponseListener());
                                return;
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                onDataError((DataResultListener) fromQueuebasedOnDate2.f3181a.getResponseListener(), new BleBaseError(e3.getMessage(), fromQueuebasedOnDate2.f3181a.getBleCommand()));
                                return;
                            }
                        }
                        return;
                    case 21:
                        onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, com.coveiot.sdk.ble.api.model.ProgressType.PACKETS));
                        BleCommand bleCommand19 = BleCommand.GET_HEARTRATE_DATA;
                        QueueObject fromQueuebasedOnDate3 = getFromQueuebasedOnDate(bleCommand19, baseResponse.getBaseRequest());
                        QueueObject fromQueuebasedOnDate4 = getFromQueuebasedOnDate(BleCommand.GET_BP_DATA, baseResponse.getBaseRequest());
                        QueueObject fromQueuebasedOnDate5 = getFromQueuebasedOnDate(BleCommand.GET_RR_HISTORY_DATA, baseResponse.getBaseRequest());
                        try {
                            HrBpData hrBpData = ((HrBpDataRes) baseResponse).getHrBpData();
                            if (fromQueuebasedOnDate3 != null) {
                                int sameCommandCount3 = getSameCommandCount(bleCommand19);
                                String str5 = l;
                                ModuleNames moduleNames5 = ModuleNames.BLEABSTRACT;
                                LogHelper.d(str5, "hr queue size is ++ " + sameCommandCount3, moduleNames5.getModuleName());
                                HeartRateResponse heartRateResponse = LeonardoFormatter.getHeartRateResponse(hrBpData);
                                if (sameCommandCount3 == 0) {
                                    heartRateResponse.setComplete(true);
                                } else {
                                    heartRateResponse.setComplete(false);
                                }
                                this.h.put(BleUtils.getDateFormater("yyyy-MM-dd").format(((HeartRateBpReq) baseResponse.getBaseRequest()).getStartDate()), baseResponse.getResponseData());
                                LogHelper.d(str5, "hr isComplete ++ " + heartRateResponse.isComplete(), moduleNames5.getModuleName());
                                BleBaseResponse bleBaseResponse7 = new BleBaseResponse(fromQueuebasedOnDate3.f3181a);
                                bleBaseResponse7.setResponseData(heartRateResponse);
                                onDataResponse(bleBaseResponse7, (DataResultListener) fromQueuebasedOnDate3.f3181a.getResponseListener());
                            }
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            onDataError((DataResultListener) fromQueuebasedOnDate3.f3181a.getResponseListener(), new BleBaseError(e4.getMessage(), fromQueuebasedOnDate3.f3181a.getBleCommand()));
                        }
                        try {
                            HrBpData hrBpData2 = ((HrBpDataRes) baseResponse).getHrBpData();
                            if (fromQueuebasedOnDate4 != null) {
                                int sameCommandCount4 = getSameCommandCount(BleCommand.GET_BP_DATA);
                                String str6 = l;
                                ModuleNames moduleNames6 = ModuleNames.BLEABSTRACT;
                                LogHelper.d(str6, "bp queue size is ++ " + sameCommandCount4, moduleNames6.getModuleName());
                                BpResponse bpResponse = LeonardoFormatter.getBpResponse(hrBpData2);
                                if (sameCommandCount4 == 0) {
                                    bpResponse.setComplete(true);
                                } else {
                                    bpResponse.setComplete(false);
                                }
                                LogHelper.d(str6, "bp isComplete ++ " + bpResponse.isComplete(), moduleNames6.getModuleName());
                                BleBaseResponse bleBaseResponse8 = new BleBaseResponse(fromQueuebasedOnDate4.f3181a);
                                bleBaseResponse8.setResponseData(bpResponse);
                                onDataResponse(bleBaseResponse8, (DataResultListener) fromQueuebasedOnDate4.f3181a.getResponseListener());
                            }
                        } catch (Exception e5) {
                            e5.printStackTrace();
                            onDataError((DataResultListener) fromQueuebasedOnDate4.f3181a.getResponseListener(), new BleBaseError(e5.getMessage(), fromQueuebasedOnDate4.f3181a.getBleCommand()));
                        }
                        try {
                            HrBpData hrBpData3 = ((HrBpDataRes) baseResponse).getHrBpData();
                            if (fromQueuebasedOnDate5 != null) {
                                int sameCommandCount5 = getSameCommandCount(BleCommand.GET_RR_HISTORY_DATA);
                                String str7 = l;
                                ModuleNames moduleNames7 = ModuleNames.BLEABSTRACT;
                                LogHelper.d(str7, "rr queue size is ++ " + sameCommandCount5, moduleNames7.getModuleName());
                                RrResponse rrResponse = LeonardoFormatter.getRrResponse(hrBpData3);
                                if (sameCommandCount5 == 0) {
                                    rrResponse.setComplete(true);
                                } else {
                                    rrResponse.setComplete(false);
                                }
                                LogHelper.d(str7, "rr isComplete ++ " + rrResponse.isComplete(), moduleNames7.getModuleName());
                                BleBaseResponse bleBaseResponse9 = new BleBaseResponse(fromQueuebasedOnDate5.f3181a);
                                bleBaseResponse9.setResponseData(rrResponse);
                                onDataResponse(bleBaseResponse9, (DataResultListener) fromQueuebasedOnDate5.f3181a.getResponseListener());
                                return;
                            }
                            return;
                        } catch (Exception e6) {
                            e6.printStackTrace();
                            onDataError((DataResultListener) fromQueuebasedOnDate5.f3181a.getResponseListener(), new BleBaseError(e6.getMessage(), fromQueuebasedOnDate5.f3181a.getBleCommand()));
                            return;
                        }
                    case 22:
                        RrResponse rrResponse2 = LeonardoFormatter.getRrResponse(((RrDataRes) baseResponse).getRrData());
                        BleCommand bleCommand20 = BleCommand.GET_RR_DATA;
                        QueueObject fromQueuebasedOnDate6 = getFromQueuebasedOnDate(bleCommand20, baseResponse.getBaseRequest());
                        if (fromQueuebasedOnDate6 != null) {
                            try {
                                if (getSameCommandCount(bleCommand20) == 0) {
                                    rrResponse2.setComplete(true);
                                } else {
                                    rrResponse2.setComplete(false);
                                }
                                BleBaseResponse bleBaseResponse10 = new BleBaseResponse(fromQueuebasedOnDate6.f3181a);
                                bleBaseResponse10.setResponseData(rrResponse2);
                                onDataResponse(bleBaseResponse10, (DataResultListener) fromQueuebasedOnDate6.f3181a.getResponseListener());
                                return;
                            } catch (Exception e7) {
                                e7.printStackTrace();
                                onDataError((DataResultListener) fromQueuebasedOnDate6.f3181a.getResponseListener(), new BleBaseError(e7.getMessage(), fromQueuebasedOnDate6.f3181a.getBleCommand()));
                                return;
                            }
                        }
                        return;
                    case 23:
                        BleCommand bleCommand21 = BleCommand.DEVICE_INFO;
                        QueueObject fromQueue19 = getFromQueue(bleCommand21, baseResponse);
                        if (fromQueue19 == null) {
                            return;
                        }
                        int sameCommandCount6 = getSameCommandCount(bleCommand21);
                        if (this.j == null) {
                            this.j = new DeviceInfoData();
                        }
                        if (baseResponse instanceof ReadDeviceNameRes) {
                            this.j.setDeviceName(((ReadDeviceNameRes) baseResponse).getName());
                        } else if (baseResponse instanceof ReadDeviceSerialNumberRes) {
                            String serialNumber = ((ReadDeviceSerialNumberRes) baseResponse).getSerialNumber();
                            if (serialNumber != null && !serialNumber.isEmpty()) {
                                if (!serialNumber.equalsIgnoreCase("N123456") && !serialNumber.equalsIgnoreCase("kh23456")) {
                                    this.j.setSerialNo(serialNumber);
                                }
                                this.j.setSerialNo(((String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "")).replaceAll(":", ""));
                            } else {
                                this.j.setSerialNo(((String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "")).replaceAll(":", ""));
                            }
                        } else if (baseResponse instanceof ReadDeviceManufacturerRes) {
                            this.j.setManufacturerName(((ReadDeviceManufacturerRes) baseResponse).getManufacturerName());
                        } else if (baseResponse instanceof ReadDeviceHardwareVersionRes) {
                            this.j.setHwVersion(((ReadDeviceHardwareVersionRes) baseResponse).getHardwareVersion());
                        } else if (baseResponse instanceof ReadDeviceSoftwareVersionRes) {
                            this.j.setSoftwareRevision(((ReadDeviceSoftwareVersionRes) baseResponse).getSoftwareVersion());
                        } else if (baseResponse instanceof ReadDeviceFirmwareVersionRes) {
                            this.j.setFwVersion(((ReadDeviceFirmwareVersionRes) baseResponse).getFirmwareVersion());
                        } else if (baseResponse instanceof ReadDeviceModelRes) {
                            this.j.setModelNo(((ReadDeviceModelRes) baseResponse).getModelNumber());
                        }
                        DataResultListener dataResultListener2 = (DataResultListener) fromQueue19.f3181a.getResponseListener();
                        DeviceInfoResponse deviceInfoResponse = new DeviceInfoResponse();
                        if (sameCommandCount6 == 0) {
                            deviceInfoResponse.setComplete(true);
                        } else {
                            deviceInfoResponse.setComplete(false);
                        }
                        deviceInfoResponse.setDeviceInfo(this.j);
                        BleBaseResponse bleBaseResponse11 = new BleBaseResponse(fromQueue19.f3181a);
                        bleBaseResponse11.setResponseData(deviceInfoResponse);
                        onDataResponse(bleBaseResponse11, dataResultListener2);
                        return;
                    case 24:
                        BleCommand bleCommand22 = BleCommand.HEART_RATE_MEASUREMENT_CONTROL;
                        QueueObject fromQueue20 = getFromQueue(bleCommand22, baseResponse);
                        if (fromQueue20 == null || !(baseResponse instanceof HeartRateMeasurementRes)) {
                            return;
                        }
                        SettingsResultListener settingsResultListener18 = (SettingsResultListener) fromQueue20.f3181a.getResponseListener();
                        LogHelper.d(l, "ResponseListner" + settingsResultListener18.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                        if (((HeartRateMeasurementRes) baseResponse).isSuccess()) {
                            onSettingsResponse(settingsResultListener18, new BleBaseResponse(fromQueue20.f3181a));
                            return;
                        } else {
                            onSettingsError(settingsResultListener18, new BleBaseError("Error while setting heart rate control", bleCommand22));
                            return;
                        }
                    case 25:
                        QueueObject fromQueue21 = getFromQueue(BleCommand.BP_MEASUREMENT_CONTROL, baseResponse);
                        if (fromQueue21 == null || !(baseResponse instanceof BloodPressureMeasurementRes)) {
                            return;
                        }
                        SettingsResultListener settingsResultListener19 = (SettingsResultListener) fromQueue21.f3181a.getResponseListener();
                        if (((BloodPressureMeasurementRes) baseResponse).isSuccess()) {
                            onSettingsResponse(settingsResultListener19, new BleBaseResponse(fromQueue21.f3181a));
                            return;
                        } else {
                            onSettingsError(settingsResultListener19, new BleBaseError("Error while setting Blood pressure control", BleCommand.HEART_RATE_MEASUREMENT_CONTROL));
                            return;
                        }
                    case 26:
                        BleCommand bleCommand23 = BleCommand.GET_LIVE_STEPS_CONTROL;
                        QueueObject fromQueue22 = getFromQueue(bleCommand23, baseResponse);
                        if (fromQueue22 == null || !(baseResponse instanceof LiveStepsControlRes)) {
                            return;
                        }
                        SettingsResultListener settingsResultListener20 = (SettingsResultListener) fromQueue22.f3181a.getResponseListener();
                        BleBaseResponse bleBaseResponse12 = new BleBaseResponse(fromQueue22.f3181a);
                        if (((LiveStepsControlRes) baseResponse).isSuccess()) {
                            onSettingsResponse(settingsResultListener20, bleBaseResponse12);
                            return;
                        } else {
                            onSettingsError(settingsResultListener20, new BleBaseError("Error while setting live steps control", bleCommand23));
                            return;
                        }
                    case 27:
                        QueueObject fromQueue23 = getFromQueue(BleCommand.READ_BATTERY_LEVEL, baseResponse);
                        if (fromQueue23 == null || !(baseResponse instanceof ReadBatteryLevelRes)) {
                            return;
                        }
                        BatteryLevelResponse batteryLevelResponse = new BatteryLevelResponse();
                        batteryLevelResponse.setBatteryLevel(Integer.valueOf(((ReadBatteryLevelRes) baseResponse).getBatteryLevel()));
                        batteryLevelResponse.setComplete(true);
                        BleBaseResponse bleBaseResponse13 = new BleBaseResponse(fromQueue23.f3181a);
                        bleBaseResponse13.setResponseData(batteryLevelResponse);
                        onDataResponse(bleBaseResponse13, (DataResultListener) fromQueue23.f3181a.getResponseListener());
                        return;
                    case 28:
                        QueueObject fromQueue24 = getFromQueue(BleCommand.READ_MANUAL_BP, baseResponse);
                        if (fromQueue24 == null || !(baseResponse instanceof ReadManualBpRes)) {
                            return;
                        }
                        Object readManualBpResponse = new ReadManualBpResponse(LeonardoFormatter.getManualBpReadingList(((ReadManualBpRes) baseResponse).getManualBpReadings()));
                        BleBaseResponse bleBaseResponse14 = new BleBaseResponse(fromQueue24.f3181a);
                        bleBaseResponse14.setResponseData(readManualBpResponse);
                        onDataResponse(bleBaseResponse14, (DataResultListener) fromQueue24.f3181a.getResponseListener());
                        return;
                    case 29:
                        String str8 = l;
                        ModuleNames moduleNames8 = ModuleNames.BLEABSTRACT;
                        LogHelper.d(str8, "SET_DISTANCE_UNIT command response received from parser!!!", moduleNames8.getModuleName());
                        BleCommand bleCommand24 = BleCommand.SET_DISTANCE_UNIT;
                        QueueObject fromQueue25 = getFromQueue(bleCommand24, baseResponse);
                        if (fromQueue25 != null) {
                            SettingsResultListener settingsResultListener21 = (SettingsResultListener) fromQueue25.f3181a.getResponseListener();
                            BleBaseResponse bleBaseResponse15 = new BleBaseResponse(fromQueue25.f3181a);
                            LogHelper.d(str8, "((ChangeTemperatureUnitRes) response).isSuccess(): " + ((ChangeDistanceUnitRes) baseResponse).isSuccess(), moduleNames8.getModuleName());
                            if (((ChangeDistanceUnitRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener21, bleBaseResponse15);
                                return;
                            } else {
                                onSettingsError(settingsResultListener21, new BleBaseError("ChangeTemperatureUnitRes setting failed", bleCommand24));
                                return;
                            }
                        }
                        return;
                    case 30:
                        QueueObject fromQueue26 = getFromQueue(BleCommand.GET_SOCIAL_DISTANCE_SETTINGS, baseResponse);
                        if (fromQueue26 == null || !(baseResponse instanceof GetSocialDistanceScanSettingsRes)) {
                            return;
                        }
                        BleBaseResponse bleBaseResponse16 = new BleBaseResponse(fromQueue26.f3181a);
                        bleBaseResponse16.setResponseData(((GetSocialDistanceScanSettingsRes) baseResponse).getSocialDistanceScanSettingsData());
                        onDataResponse(bleBaseResponse16, (DataResultListener) fromQueue26.f3181a.getResponseListener());
                        return;
                    case 31:
                        BleCommand bleCommand25 = BleCommand.SET_SOCIAL_DISTANCE_SETTINGS;
                        QueueObject fromQueue27 = getFromQueue(bleCommand25, baseResponse);
                        if (fromQueue27 == null || !(baseResponse instanceof SetSocialDistanceScanSettingsRes)) {
                            return;
                        }
                        SettingsResultListener settingsResultListener22 = (SettingsResultListener) fromQueue27.f3181a.getResponseListener();
                        if (((SetSocialDistanceScanSettingsRes) baseResponse).isSuccess()) {
                            onSettingsResponse(settingsResultListener22, new BleBaseResponse(fromQueue27.f3181a));
                            return;
                        } else {
                            onSettingsError(settingsResultListener22, new BleBaseError("Error while setting Social Distance parameters", bleCommand25));
                            return;
                        }
                    case 32:
                        BleCommand bleCommand26 = BleCommand.DELETE_NEARBY_DEVICE_LIST;
                        QueueObject fromQueue28 = getFromQueue(bleCommand26, baseResponse);
                        if (fromQueue28 == null || !(baseResponse instanceof DeleteNearbyDevicesRes)) {
                            return;
                        }
                        SettingsResultListener settingsResultListener23 = (SettingsResultListener) fromQueue28.f3181a.getResponseListener();
                        if (((DeleteNearbyDevicesRes) baseResponse).isSuccess()) {
                            onSettingsResponse(settingsResultListener23, new BleBaseResponse(fromQueue28.f3181a));
                            return;
                        } else {
                            onSettingsError(settingsResultListener23, new BleBaseError("Error while deleting Nearby devices", bleCommand26));
                            return;
                        }
                    case 33:
                        QueueObject fromQueue29 = getFromQueue(BleCommand.GET_NEARBY_DEVICE_LIST, baseResponse);
                        if (fromQueue29 != null) {
                            if (baseResponse instanceof GetNearbyDevicesMacRes) {
                                BleBaseResponse bleBaseResponse17 = new BleBaseResponse(fromQueue29.f3181a);
                                bleBaseResponse17.setResponseData(((GetNearbyDevicesMacRes) baseResponse).getNearbyDevices());
                                onDataResponse(bleBaseResponse17, (DataResultListener) fromQueue29.f3181a.getResponseListener());
                                return;
                            } else if (baseResponse instanceof GetNearbyDevicesRes) {
                                BleBaseResponse bleBaseResponse18 = new BleBaseResponse(fromQueue29.f3181a);
                                bleBaseResponse18.setResponseData(((GetNearbyDevicesRes) baseResponse).getNearbyDevices());
                                onDataResponse(bleBaseResponse18, (DataResultListener) fromQueue29.f3181a.getResponseListener());
                                return;
                            } else {
                                return;
                            }
                        }
                        return;
                    case 34:
                        BleCommand bleCommand27 = BleCommand.SET_WOMEN_WELLNESS;
                        QueueObject fromQueue30 = getFromQueue(bleCommand27, baseResponse);
                        if (fromQueue30 == null || !(baseResponse instanceof WomenWellnessSettingsRes)) {
                            return;
                        }
                        SettingsResultListener settingsResultListener24 = (SettingsResultListener) fromQueue30.f3181a.getResponseListener();
                        if (((WomenWellnessSettingsRes) baseResponse).isSuccess()) {
                            onSettingsResponse(settingsResultListener24, new BleBaseResponse(fromQueue30.f3181a));
                            return;
                        } else {
                            onSettingsError(settingsResultListener24, new BleBaseError("Error while setting Social Distance parameters", bleCommand27));
                            return;
                        }
                    default:
                        LogHelper.d(l, "Unknown activity type in Leonardo Impl " + baseResponse.getActivityType(), ModuleNames.BLEABSTRACT.getModuleName());
                        return;
                }
            } catch (Exception e8) {
                onFailure(new com.coveiot.sdk.ble.api.error.Error(com.coveiot.sdk.ble.api.error.Type.COMMAND_REQUEST_ERROR, e8.getMessage()));
                e8.printStackTrace();
            }
            onFailure(new com.coveiot.sdk.ble.api.error.Error(com.coveiot.sdk.ble.api.error.Type.COMMAND_REQUEST_ERROR, e8.getMessage()));
            e8.printStackTrace();
        }
    }

    @Subscribe
    public void onResponseEventReceivved(ResponseEvent responseEvent) {
        int i = AnonymousClass17.b[responseEvent.getType().ordinal()];
        if (i == 1) {
            LiveHealthRes liveHealthRes = (LiveHealthRes) responseEvent.getData();
            MutableLiveData<LiveHealthData> mutableLiveData = this.f3162a;
            if (mutableLiveData != null) {
                mutableLiveData.setValue(Mapper.a(liveHealthRes.getLiveHealthData()));
                this.f3162a.postValue(Mapper.a(liveHealthRes.getLiveHealthData()));
            }
        } else if (i == 2) {
            LiveStepsRes liveStepsRes = (LiveStepsRes) responseEvent.getData();
            MutableLiveData<LiveStepsData> mutableLiveData2 = this.liveStepsDataMutableLiveData;
            if (mutableLiveData2 != null) {
                mutableLiveData2.setValue(Mapper.a(liveStepsRes.getLiveStepsData()));
                this.liveStepsDataMutableLiveData.postValue(Mapper.a(liveStepsRes.getLiveStepsData()));
            }
        } else if (i == 3) {
            LeonardoBleCmdService leonardoBleCmdService = bleService;
            if (leonardoBleCmdService != null) {
                leonardoBleCmdService.sendFindMyPhoneACK();
            }
            FindMyPhoneRes findMyPhoneRes = (FindMyPhoneRes) responseEvent.getData();
            if (findMyPhoneRes != null) {
                Intent intent2 = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
                intent2.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);
            }
        } else if (i == 4) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Constants.PROBE_DATA_BROADCAST_INTENT));
        } else if (i != 5) {
        } else {
            RawPPGData rawPPGData = (RawPPGData) responseEvent.getData();
            if (this.i.size() < 10) {
                this.i.add(Integer.valueOf(rawPPGData.getData()));
                return;
            }
            PPGData pPGData = new PPGData((ArrayList) this.i.clone());
            MutableLiveData<PPGData> mutableLiveData3 = this.b;
            if (mutableLiveData3 != null) {
                mutableLiveData3.setValue(pPGData);
            }
            this.i.clear();
        }
    }

    public void onSettingsError(final SettingsResultListener settingsResultListener, final BleBaseError bleBaseError) {
        Handler handler = this.uiHandler;
        if (handler == null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.5
                @Override // java.lang.Runnable
                public void run() {
                    LeonardoBleApiImpl.this.uiHandler = new Handler(Looper.getMainLooper());
                    LeonardoBleApiImpl leonardoBleApiImpl = LeonardoBleApiImpl.this;
                    leonardoBleApiImpl.uiHandler.post(new EventBusRegistrationInMainThreadRunnable(leonardoBleApiImpl, leonardoBleApiImpl));
                    LeonardoBleApiImpl.this.uiHandler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass5 anonymousClass5 = AnonymousClass5.this;
                            settingsResultListener.onSettingsError(bleBaseError);
                        }
                    });
                }
            });
        } else {
            handler.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.6
                @Override // java.lang.Runnable
                public void run() {
                    settingsResultListener.onSettingsError(bleBaseError);
                }
            });
        }
    }

    public void onSettingsResponse(final SettingsResultListener settingsResultListener, final BleBaseResponse bleBaseResponse) {
        this.uiHandler.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.7
            @Override // java.lang.Runnable
            public void run() {
                settingsResultListener.onSettingsResponse(bleBaseResponse);
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.connectionStateLiveData == null) {
            this.connectionStateLiveData = new MutableLiveData<>();
            this.connectionStateLiveData.setValue(a(getConnectionStatus()));
        }
        return this.connectionStateLiveData;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveECGDataResponse> registerForLiveEcgData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveTemperatureData> registerForLiveTemperatureData() {
        return null;
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
    public MutableLiveData<PPGData> registerLivePPGData() {
        if (this.b == null) {
            this.b = new MutableLiveData<>();
        }
        return this.b;
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
    @androidx.annotation.Nullable
    public LiveData<LiveSportData> registerLiveSportsData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<LiveWatchFaceUploadPercentage> registerLiveWatchFaceUploadData() {
        return null;
    }

    public void removeSimilarCommand(BleCommand bleCommand) {
        synchronized (n) {
            Iterator<QueueObject> it = n.iterator();
            while (it.hasNext()) {
                if (it.next().f3181a.getBleCommand() == bleCommand) {
                    it.remove();
                }
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        com.coveiot.sdk.ble.api.error.Error error = new com.coveiot.sdk.ble.api.error.Error(com.coveiot.sdk.ble.api.error.Type.COMMAND_TIMED_OUT, "Device disconnected");
        error.setShouldClearCommandQueue(true);
        sendErrorAndClearQueue(error);
        LeonardoBleCmdService leonardoBleCmdService = bleService;
        if (leonardoBleCmdService != null) {
            leonardoBleCmdService.restartService();
            a();
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Intent intent2 = new Intent(LeonardoBleApiImpl.context, LeonardoBleCmdService.class);
                    LeonardoBleApiImpl.context.bindService(intent2, LeonardoBleApiImpl.serviceConnection, 1);
                    if (Build.VERSION.SDK_INT >= 26) {
                        LeonardoBleApiImpl.context.startForegroundService(intent2);
                    } else {
                        LeonardoBleApiImpl.context.startService(intent2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    BleApiUtils.checkExceptionAndShowNotification(e, LeonardoBleApiImpl.context);
                }
            }
        }, 5000L);
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void retryCommand(BaseRequest baseRequest) {
        if (bleService != null) {
            LogHelper.d(l, "Retrying command", ModuleNames.BLEABSTRACT.getModuleName());
            bleService.sendRequest(baseRequest, this);
            return;
        }
        onFailure(new com.coveiot.sdk.ble.api.error.Error(com.coveiot.sdk.ble.api.error.Type.SERVICE_NOT_RUNNING, "Service not running"));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        clearCommandQueue();
        this.e.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(context).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.13
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(CharSequence charSequence) {
                        scanResultListener.onError(LeonardoBleApiImpl.context.getString(R.string.scan_failed));
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(String str) {
                        LeonardoBleApiImpl.a(LeonardoBleApiImpl.this, new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.e.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.14
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!DeviceScanManager.getInstance(LeonardoBleApiImpl.context).isScanningInProgress()) {
                        if (BleUtils.isEmpty(scanDeviceRequest.getScanFilter())) {
                            DeviceScanManager.getInstance(LeonardoBleApiImpl.context).scanAllDevices(LeonardoBleApiImpl.context, scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.14.1
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass14 anonymousClass14 = AnonymousClass14.this;
                                    LeonardoBleApiImpl.a(LeonardoBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(LeonardoBleApiImpl.context.getString(R.string.scan_failed));
                                }
                            });
                        } else {
                            DeviceScanManager.getInstance(LeonardoBleApiImpl.context).scanDevicesWithFilter(LeonardoBleApiImpl.context, scanDeviceRequest.getScanFilter(), scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl.14.2
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass14 anonymousClass14 = AnonymousClass14.this;
                                    LeonardoBleApiImpl.a(LeonardoBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(LeonardoBleApiImpl.context.getString(R.string.scan_failed));
                                }
                            });
                        }
                    } else {
                        scanResultListener.onError("Device scan already started");
                    }
                } catch (RuntimeException e) {
                    String str = null;
                    if (e.getMessage().contains("Enable Location permission for bluetooth scan")) {
                        str = e.getMessage();
                    } else if (e.getMessage().equalsIgnoreCase("Enable Bluetooth before starting scan")) {
                        str = e.getMessage();
                    }
                    scanResultListener.onError(str);
                    e.printStackTrace();
                }
            }
        }, com.clevertap.android.sdk.Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public void sendErrorAndClearQueue(com.coveiot.sdk.ble.api.error.Error error) {
        if (n != null && n.size() > 0) {
            QueueObject queueObject = null;
            try {
                Iterator it = ((LinkedList) n.clone()).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    QueueObject queueObject2 = (QueueObject) it.next();
                    if (error.getReqId() != null && error.getReqId().equals(queueObject2.f3181a.getRequId())) {
                        a(error, queueObject2);
                        queueObject = queueObject2;
                        break;
                    }
                }
                if (queueObject != null) {
                    synchronized (n) {
                        n.remove(queueObject);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (error.isShouldClearCommandQueue()) {
                Iterator it2 = ((LinkedList) n.clone()).iterator();
                while (it2.hasNext()) {
                    a(error, (QueueObject) it2.next());
                }
                clearCommandQueue();
                return;
            }
            return;
        }
        LogHelper.d(l, "Command queue is empty");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus busyStatus) {
        this.c = busyStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(ConnectionResultListener connectionResultListener) {
        removeSimilarCommand(BleCommand.CONNECT);
    }

    public void setPairingSupported(boolean z) {
        this.k = z;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(BleBaseRequest bleBaseRequest, SettingsResultListener settingsResultListener) {
        AppNotificationType appNotificationType;
        AppNotificationType appNotificationType2;
        SetAlarmReq build;
        LeonardoBleCmdService leonardoBleCmdService = bleService;
        if (leonardoBleCmdService == null || leonardoBleCmdService.getConnectionState() != CloveBleState.BleState.CONNECTED) {
            if (bleBaseRequest != null && bleBaseRequest.getBleCommand() != null) {
                onSettingsError(settingsResultListener, new BleBaseError("Band is not connected", bleBaseRequest.getBleCommand()));
            } else {
                onSettingsError(settingsResultListener, new BleBaseError("Band is not connected"));
            }
        } else if (bleBaseRequest == null) {
            if (settingsResultListener != null) {
                BleBaseError bleBaseError = new BleBaseError("Request cannot be null");
                bleBaseError.setErrorMsg("Request cannot be null");
                onSettingsError(settingsResultListener, bleBaseError);
            }
        } else {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            if (bleBaseRequest instanceof SetReminderRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_SEDENTARY_REMINDER);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                SetReminderRequest setReminderRequest = (SetReminderRequest) bleBaseRequest;
                SedentaryReminderReq build2 = new SedentaryReminderReq.Builder().setEnabled(setReminderRequest.isEnabled()).setStartHour1(setReminderRequest.getStartHour1()).setStartHour2(setReminderRequest.getStartHour2()).setEndHour1(setReminderRequest.getEndHour1()).setEndHour2(setReminderRequest.getEndHour2()).setStartMin1(setReminderRequest.getStartMin1()).setStartMin2(setReminderRequest.getStartMin2()).setEndMin1(setReminderRequest.getEndMin1()).setEndMin2(setReminderRequest.getEndMin2()).setReminderInterval(setReminderRequest.getReminderInterval()).build();
                build2.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build2, this);
            } else if (bleBaseRequest instanceof SetVibrationAlarmRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_VIBRATION_ALARM);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                SetVibrationAlarmRequest setVibrationAlarmRequest = (SetVibrationAlarmRequest) bleBaseRequest;
                if (setVibrationAlarmRequest.isRepeatEnabled()) {
                    build = new SetAlarmReq.Builder(setVibrationAlarmRequest.getAlarmId(), setVibrationAlarmRequest.getHour(), setVibrationAlarmRequest.getMinute()).setAlarmOn(setVibrationAlarmRequest.isEnabled()).setEventName(setVibrationAlarmRequest.getEventName()).setDays(setVibrationAlarmRequest.isSundayEnabled(), setVibrationAlarmRequest.isMondayEnabled(), setVibrationAlarmRequest.isTuesdayEnabled(), setVibrationAlarmRequest.isWednesdayEnabled(), setVibrationAlarmRequest.isThursdayEnabled(), setVibrationAlarmRequest.isFridayEnabled(), setVibrationAlarmRequest.isSaturdayEnabled()).build();
                } else {
                    build = new SetAlarmReq.Builder(setVibrationAlarmRequest.getAlarmId(), setVibrationAlarmRequest.getHour(), setVibrationAlarmRequest.getMinute()).setAlarmOn(setVibrationAlarmRequest.isEnabled()).setEventName(setVibrationAlarmRequest.getEventName()).setDays(false, false, false, false, false, false, false).build();
                }
                build.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build, this);
            } else if (bleBaseRequest instanceof SetBandDispalyRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_BAND_DISPLAY);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                SetBandDispalyRequest setBandDispalyRequest = (SetBandDispalyRequest) bleBaseRequest;
                BandDisplaySettingsReq build3 = new BandDisplaySettingsReq.Builder().shouldShowActiviteTime(setBandDispalyRequest.isShowActiveTime()).shouldShowStepCount(setBandDispalyRequest.isShowStepCount()).shouldShowDistance(setBandDispalyRequest.isShowDistance()).shouldShowCaloriesCount(setBandDispalyRequest.isShowCaloriesCount()).shouldShowEmotion(setBandDispalyRequest.isShowEmotion()).shouldShowActivityProgress(setBandDispalyRequest.isShowActivityProgress()).shouldShowAlarm(setBandDispalyRequest.isShowAlarm()).shouldShowDateAndTime(setBandDispalyRequest.isShowDateAndTime()).build();
                build3.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build3, this);
            } else if (bleBaseRequest instanceof SetWearingHandRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_HAND_PREFERENCE);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                ChangeWearingHandReq build4 = new ChangeWearingHandReq.Builder(((SetWearingHandRequest) bleBaseRequest).isRightHand()).build();
                build4.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build4, this);
            } else if (bleBaseRequest instanceof SetFitnessInfoRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_FITNESS_PERSONAL_INFO);
                addToQueue(bleBaseRequest);
                SetFitnessInfoRequest setFitnessInfoRequest = (SetFitnessInfoRequest) bleBaseRequest;
                SaveFitnessProfileReq build5 = new SaveFitnessProfileReq.Builder(setFitnessInfoRequest.getHeight(), (int) setFitnessInfoRequest.getWeight(), (int) setFitnessInfoRequest.getStride(), (int) setFitnessInfoRequest.getRunstride(), setFitnessInfoRequest.isMale()).build();
                build5.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build5, this);
            } else if (bleBaseRequest instanceof SetCallSmsSocialNotificationRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_MESSAGE_ALERT_SWITCH);
                addToQueue(bleBaseRequest);
                SetCallSmsSocialNotificationRequest setCallSmsSocialNotificationRequest = (SetCallSmsSocialNotificationRequest) bleBaseRequest;
                MessageAlertSwitchesReq build6 = new MessageAlertSwitchesReq.Builder().setAppAlerts(setCallSmsSocialNotificationRequest.isCallEnabled(), setCallSmsSocialNotificationRequest.isCalendarEnabled(), setCallSmsSocialNotificationRequest.isSmsEnabled(), setCallSmsSocialNotificationRequest.isEmailEnabled(), setCallSmsSocialNotificationRequest.isWhatsAppEnabled(), setCallSmsSocialNotificationRequest.isWeChatEnabled(), setCallSmsSocialNotificationRequest.isFacebookEnabled(), setCallSmsSocialNotificationRequest.isInstagramEnabled(), setCallSmsSocialNotificationRequest.isTwitterEnabled(), setCallSmsSocialNotificationRequest.isMessengerEnabled(), setCallSmsSocialNotificationRequest.isQQEnabled(), setCallSmsSocialNotificationRequest.isQzoneEnabled(), setCallSmsSocialNotificationRequest.isSnapchatEnabled(), setCallSmsSocialNotificationRequest.isSkypeEnabled(), setCallSmsSocialNotificationRequest.isLineEnabled(), setCallSmsSocialNotificationRequest.isTelegramEnabled(), setCallSmsSocialNotificationRequest.isLinkedInEnabled()).setYoutubeEnabled(setCallSmsSocialNotificationRequest.isYoutubeEnabled()).setCommonAppEnabled(setCallSmsSocialNotificationRequest.isOtherAppEnabled()).setKaKaoTalkEnabled(setCallSmsSocialNotificationRequest.isKaKaoTalkEnabled()).setGmailEnabled(setCallSmsSocialNotificationRequest.isGmailEnabled()).setNewsEnabled(setCallSmsSocialNotificationRequest.isNewsEnabled()).setCustomEventEnabled(setCallSmsSocialNotificationRequest.isCustomEventEnabled()).build();
                build6.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build6, this);
            } else if (bleBaseRequest instanceof StepsTargetRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_WALK_TARGET);
                addToQueue(bleBaseRequest);
                SetWalkTargetReq build7 = new SetWalkTargetReq.Builder().setTarget(((StepsTargetRequest) bleBaseRequest).getTarget()).build();
                build7.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build7, this);
            } else if (bleBaseRequest instanceof ActivityPauseResumeRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.ACTIVITY_PAUSE);
                addToQueue(bleBaseRequest);
                ActivityPauseResumetReq build8 = new ActivityPauseResumetReq.Builder().pauseSession(((ActivityPauseResumeRequest) bleBaseRequest).isPause()).build();
                build8.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build8, this);
            } else if (bleBaseRequest instanceof LiveHeartRateControlRequest) {
                bleBaseRequest.setBleCommand(BleCommand.HEART_RATE_MEASUREMENT_CONTROL);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                HeartRateMeasurementReq build9 = new HeartRateMeasurementReq.Builder(((LiveHeartRateControlRequest) bleBaseRequest).isEnabled()).build();
                build9.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build9, this);
            } else if (bleBaseRequest instanceof LiveRawPPGControlRequest) {
                bleBaseRequest.setBleCommand(BleCommand.RAW_PPG_CONTROL);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                PPGControlReq build10 = new PPGControlReq.Builder().setEnabled(((LiveRawPPGControlRequest) bleBaseRequest).isEnabled()).build();
                build10.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build10, this);
            } else if (bleBaseRequest instanceof LiveBpControlRequest) {
                bleBaseRequest.setBleCommand(BleCommand.BP_MEASUREMENT_CONTROL);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                BloodPressureMeasurementReq build11 = new BloodPressureMeasurementReq.Builder(((LiveBpControlRequest) bleBaseRequest).isEnabled()).build();
                build11.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build11, this);
            } else if (bleBaseRequest instanceof LiveStepsControlRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.GET_LIVE_STEPS_CONTROL);
                addToQueue(bleBaseRequest);
                LiveStepsControlReq liveStepsControlReq = new LiveStepsControlReq(((LiveStepsControlRequest) bleBaseRequest).isEnable());
                liveStepsControlReq.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(liveStepsControlReq, this);
            } else if (bleBaseRequest instanceof HeartRateTimeIntervalRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_HR_TIME_INTERVAL);
                addToQueue(bleBaseRequest);
                HeartRateTimeIntervalRequest heartRateTimeIntervalRequest = (HeartRateTimeIntervalRequest) bleBaseRequest;
                int timeInterval = heartRateTimeIntervalRequest.getTimeInterval();
                if (heartRateTimeIntervalRequest.getOpen() == 0) {
                    timeInterval = 0;
                }
                TimeIntervalForAutomaticHeartRateReq timeIntervalForAutomaticHeartRateReq = new TimeIntervalForAutomaticHeartRateReq(timeInterval);
                BlePreferenceManager.savePreference(context, CommonPreference.HR_INTERVAL_VALUE, Integer.valueOf(timeIntervalForAutomaticHeartRateReq.getTimeInterval()));
                timeIntervalForAutomaticHeartRateReq.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(timeIntervalForAutomaticHeartRateReq, this);
            } else if (bleBaseRequest instanceof SetMessageContentRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_MESSAGE_CONTENT);
                addToQueue(bleBaseRequest);
                SetMessageContentRequest setMessageContentRequest = (SetMessageContentRequest) bleBaseRequest;
                int ordinal = setMessageContentRequest.getAppNotificationType().ordinal();
                switch (ordinal) {
                    case 0:
                        appNotificationType2 = AppNotificationType.CALL;
                        break;
                    case 1:
                        appNotificationType2 = AppNotificationType.CALENDAR;
                        break;
                    case 2:
                        appNotificationType2 = AppNotificationType.SMS;
                        break;
                    case 3:
                        appNotificationType2 = AppNotificationType.EMAIL;
                        break;
                    case 4:
                        appNotificationType2 = AppNotificationType.WHATSAPP;
                        break;
                    case 5:
                        appNotificationType2 = AppNotificationType.WECHAT;
                        break;
                    case 6:
                        appNotificationType2 = AppNotificationType.FACEBOOK;
                        break;
                    case 7:
                        appNotificationType2 = AppNotificationType.INSTAGRAM;
                        break;
                    case 8:
                        appNotificationType2 = AppNotificationType.TWITTER;
                        break;
                    case 9:
                        appNotificationType2 = AppNotificationType.MESSENGER;
                        break;
                    case 10:
                        appNotificationType2 = AppNotificationType.QQ;
                        break;
                    case 11:
                        appNotificationType2 = AppNotificationType.QZONE;
                        break;
                    case 12:
                        appNotificationType2 = AppNotificationType.SNAPCHAT;
                        break;
                    case 13:
                        appNotificationType2 = AppNotificationType.LINE;
                        break;
                    case 14:
                        appNotificationType2 = AppNotificationType.SKYPE;
                        break;
                    default:
                        switch (ordinal) {
                            case 17:
                                appNotificationType2 = AppNotificationType.TELEGRAM;
                                break;
                            case 18:
                                appNotificationType2 = AppNotificationType.OTHER_APPS;
                                break;
                            case 19:
                                appNotificationType2 = AppNotificationType.CUSTOM_EVENT;
                                break;
                            default:
                                switch (ordinal) {
                                    case 26:
                                        appNotificationType2 = AppNotificationType.GMAIL;
                                        break;
                                    case 27:
                                        appNotificationType2 = AppNotificationType.YOUTUBE;
                                        break;
                                    case 28:
                                        appNotificationType2 = AppNotificationType.KAKAO_TALK;
                                        break;
                                    case 29:
                                        appNotificationType2 = AppNotificationType.NEWS;
                                        break;
                                    default:
                                        appNotificationType2 = AppNotificationType.SMS;
                                        break;
                                }
                        }
                }
                MessageContentReq build12 = new MessageContentReq.Builder(appNotificationType2, setMessageContentRequest.title + HexStringBuilder.DEFAULT_SEPARATOR + setMessageContentRequest.message).build();
                build12.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build12, this);
            } else if (bleBaseRequest instanceof StopMessageNotificationRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.STOP_MESSAGE_NOTIFICATION);
                addToQueue(bleBaseRequest);
                int ordinal2 = ((StopMessageNotificationRequest) bleBaseRequest).getAppNotificationType().ordinal();
                switch (ordinal2) {
                    case 0:
                        appNotificationType = AppNotificationType.CALL;
                        break;
                    case 1:
                        appNotificationType = AppNotificationType.CALENDAR;
                        break;
                    case 2:
                        appNotificationType = AppNotificationType.SMS;
                        break;
                    case 3:
                        appNotificationType = AppNotificationType.EMAIL;
                        break;
                    case 4:
                        appNotificationType = AppNotificationType.WHATSAPP;
                        break;
                    case 5:
                        appNotificationType = AppNotificationType.WECHAT;
                        break;
                    case 6:
                        appNotificationType = AppNotificationType.FACEBOOK;
                        break;
                    case 7:
                        appNotificationType = AppNotificationType.INSTAGRAM;
                        break;
                    case 8:
                        appNotificationType = AppNotificationType.TWITTER;
                        break;
                    case 9:
                        appNotificationType = AppNotificationType.MESSENGER;
                        break;
                    case 10:
                        appNotificationType = AppNotificationType.QQ;
                        break;
                    case 11:
                        appNotificationType = AppNotificationType.QZONE;
                        break;
                    case 12:
                        appNotificationType = AppNotificationType.SNAPCHAT;
                        break;
                    case 13:
                        appNotificationType = AppNotificationType.LINE;
                        break;
                    case 14:
                        appNotificationType = AppNotificationType.SKYPE;
                        break;
                    default:
                        switch (ordinal2) {
                            case 17:
                                appNotificationType = AppNotificationType.TELEGRAM;
                                break;
                            case 18:
                                appNotificationType = AppNotificationType.OTHER_APPS;
                                break;
                            case 19:
                                appNotificationType = AppNotificationType.CUSTOM_EVENT;
                                break;
                            default:
                                switch (ordinal2) {
                                    case 26:
                                        appNotificationType = AppNotificationType.GMAIL;
                                        break;
                                    case 27:
                                        appNotificationType = AppNotificationType.YOUTUBE;
                                        break;
                                    case 28:
                                        appNotificationType = AppNotificationType.KAKAO_TALK;
                                        break;
                                    case 29:
                                        appNotificationType = AppNotificationType.NEWS;
                                        break;
                                    default:
                                        appNotificationType = AppNotificationType.SMS;
                                        break;
                                }
                        }
                }
                StopMessageNotificationReq build13 = new StopMessageNotificationReq.Builder(appNotificationType).build();
                build13.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build13, this);
            } else if (bleBaseRequest instanceof SportModeRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_CURRENT_SPORT_MODE);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                SportModeRequest sportModeRequest = (SportModeRequest) bleBaseRequest;
                CurrentSportModeReq build14 = new CurrentSportModeReq.Builder().setModes(sportModeRequest.isRunning(), sportModeRequest.isSwimming(), sportModeRequest.isCycling(), sportModeRequest.isWalking(), sportModeRequest.isTaichi()).setIsIndoor(sportModeRequest.isIndoor()).build();
                build14.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build14, this);
            } else if (bleBaseRequest instanceof Vo2MaxRequest) {
                bleBaseRequest.setBleCommand(BleCommand.UPDATE_V02MAX_VALUE);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                Vo2MaxRequest vo2MaxRequest = (Vo2MaxRequest) bleBaseRequest;
                Vo2MaxReq build15 = new Vo2MaxReq.Builder(vo2MaxRequest.getVo2MaxValue(), vo2MaxRequest.getYear(), vo2MaxRequest.getMonth(), vo2MaxRequest.getDay()).build();
                build15.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build15, this);
            } else if (bleBaseRequest instanceof BpCalibrationDataRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_BP_CALIBRATION);
                addToQueue(bleBaseRequest);
                String str = l;
                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                LogHelper.d(str, "BP Calibration request added to queue!!!", moduleNames.getModuleName());
                BpCalibrationDataRequest bpCalibrationDataRequest = (BpCalibrationDataRequest) bleBaseRequest;
                SaveBpCalibrationReq build16 = new SaveBpCalibrationReq.Builder(bpCalibrationDataRequest.getSbpCalculatingSign(), bpCalibrationDataRequest.getSbp(), bpCalibrationDataRequest.getDbpCalculatingSign(), bpCalibrationDataRequest.getDbp()).build();
                build16.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build16, this);
                LogHelper.d(str, "BP Calibration request command send to Ble Command Service!!!", moduleNames.getModuleName());
            } else if (bleBaseRequest instanceof ProbeDataRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_PROBE);
                addToQueue(bleBaseRequest);
                String str2 = l;
                ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
                LogHelper.d(str2, "Prob request added to queue!!!", moduleNames2.getModuleName());
                SaveProbeReq build17 = new SaveProbeReq.Builder(((ProbeDataRequest) bleBaseRequest).getInterval()).build();
                build17.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build17, this);
                LogHelper.d(str2, "Prob request command send to Ble Command Service!!!", moduleNames2.getModuleName());
            } else if (bleBaseRequest instanceof SetDistanceUnitRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_DISTANCE_UNIT);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                ChangeDistanceUnitReq build18 = new ChangeDistanceUnitReq.Builder().setInMiles(((SetDistanceUnitRequest) bleBaseRequest).isDistanceUnitinMile()).build();
                build18.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build18, this);
            } else if (bleBaseRequest instanceof SetSocialDistanceScanSettingsRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_SOCIAL_DISTANCE_SETTINGS);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                SetSocialDistanceScanSettingsRequest setSocialDistanceScanSettingsRequest = (SetSocialDistanceScanSettingsRequest) bleBaseRequest;
                SetSocialDistanceScanSettingsReq build19 = new SetSocialDistanceScanSettingsReq.Builder().setScanPeriod(setSocialDistanceScanSettingsRequest.getScanPeriod()).setScanUnit(setSocialDistanceScanSettingsRequest.getScanUnit()).setBandAlert(setSocialDistanceScanSettingsRequest.getBandAlert()).setRssiFilter(setSocialDistanceScanSettingsRequest.getRssiFilter()).setAddressFilter(setSocialDistanceScanSettingsRequest.getAddressFilter()).setUuidFilter(setSocialDistanceScanSettingsRequest.getUuidFilter()).setScanInterval(setSocialDistanceScanSettingsRequest.getScanInterval()).setScanWindow(setSocialDistanceScanSettingsRequest.getScanWindow()).setScanTimeout(setSocialDistanceScanSettingsRequest.getScanTimeout()).build();
                build19.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build19, this);
            } else if (bleBaseRequest instanceof DeleteNearbyDevicesRequest) {
                DeleteNearbyDevicesReq build20 = new DeleteNearbyDevicesReq.Builder().build();
                build20.setResponseListener(this);
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.DELETE_NEARBY_DEVICE_LIST);
                bleBaseRequest.setRequId(UUID.randomUUID().toString());
                addToQueue(bleBaseRequest);
                build20.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(build20, this);
            } else if (bleBaseRequest instanceof SetHourFormatRequest) {
                SetTimeFormatReq setTimeFormatReq = new SetTimeFormatReq(null, ((SetHourFormatRequest) bleBaseRequest).is12HourFormat());
                setTimeFormatReq.setResponseListener(this);
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.HOUR_FORMAT);
                bleBaseRequest.setRequId(UUID.randomUUID().toString());
                addToQueue(bleBaseRequest);
                setTimeFormatReq.setReqId(bleBaseRequest.getRequId());
                bleService.sendRequest(setTimeFormatReq, this);
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(context).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        BlePreferenceManager.savePreference(context, CommonPreference.BLE_DEVICE_ADDRESS, "");
        BlePreferenceManager.savePreference(context, CommonPreference.BLE_CONNECTION_TYPE, ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        a(ConnectionStatus.DISCONNECTED);
        LeonardoBleCmdService leonardoBleCmdService = bleService;
        if (leonardoBleCmdService != null) {
            leonardoBleCmdService.stopService();
            a();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        clearCommandQueue();
        LeonardoBleCmdService leonardoBleCmdService = bleService;
        if (leonardoBleCmdService != null) {
            leonardoBleCmdService.stopService();
            a();
        }
    }

    public void unbindService() {
        ServiceConnection serviceConnection2 = serviceConnection;
        if (serviceConnection2 != null) {
            try {
                context.unbindService(serviceConnection2);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
        clearCommandQueue();
        if (bleService != null) {
            new BleBaseRequest().setBleCommand(BleCommand.DISCONNECT);
            bleService.disconnectAndForget();
            a();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public MutableLiveData<LiveHealthData> registerLiveHealthData() {
        if (this.f3162a == null) {
            this.f3162a = new MutableLiveData<>();
        }
        return this.f3162a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public MutableLiveData<LiveStepsData> registerLiveStepsData() {
        if (this.liveStepsDataMutableLiveData == null) {
            this.liveStepsDataMutableLiveData = new MutableLiveData<>();
        }
        return this.liveStepsDataMutableLiveData;
    }

    public QueueObject getFromQueue(BleCommand bleCommand) {
        synchronized (n) {
            Iterator<QueueObject> it = n.iterator();
            while (it.hasNext()) {
                QueueObject next = it.next();
                if (next.f3181a.getBleCommand() == bleCommand) {
                    it.remove();
                    return next;
                }
            }
            return null;
        }
    }

    public final void a(com.coveiot.sdk.ble.api.error.Error error, QueueObject queueObject) {
        BaseListener responseListener = queueObject.f3181a.getResponseListener();
        BleBaseError bleBaseError = new BleBaseError(error.getMessage(), queueObject.f3181a.getBleCommand());
        if (error.getType() == com.coveiot.sdk.ble.api.error.Type.COMMAND_TIMED_OUT) {
            bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_ERROR_TIMEOUT.value));
        } else if (error.getType() == com.coveiot.sdk.ble.api.error.Type.SERVICE_BUSY) {
            bleBaseError.setErrorCode(Integer.valueOf(CommandError.SERVICE_BUSY.value));
        } else if (error.getType() == com.coveiot.sdk.ble.api.error.Type.WATCH_BUSY) {
            bleBaseError.setErrorCode(Integer.valueOf(CommandError.WATCH_BUSY.value));
        } else if (error.getType() == com.coveiot.sdk.ble.api.error.Type.COMMAND_WRITE_FAILED) {
            bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_WRITE_FAILED.value));
        }
        if (responseListener instanceof DataResultListener) {
            onDataError((DataResultListener) responseListener, bleBaseError);
        } else if (responseListener instanceof SettingsResultListener) {
            onSettingsError((SettingsResultListener) responseListener, bleBaseError);
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, error.getMessage()));
        }
    }

    public final void a() {
        if (DeviceScanManager.getInstance(context).isScanningInProgress()) {
            DeviceScanManager.getInstance(context).stopScan();
        }
        bleService = null;
        unbindService();
        this.d.removeCallbacksAndMessages(null);
        this.e.removeCallbacksAndMessages(null);
        m = null;
    }

    public final ConnectionStatus a(ConnectionStatus connectionStatus) {
        LeonardoBleCmdService leonardoBleCmdService = bleService;
        if (leonardoBleCmdService != null) {
            if (leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            if (bleService.getConnectionState() == CloveBleState.BleState.CONNECTING) {
                return ConnectionStatus.CONNECTING;
            }
            if (bleService.getConnectionState() == CloveBleState.BleState.SCANNING) {
                return ConnectionStatus.SCANNING;
            }
            if (bleService.getConnectionState() == CloveBleState.BleState.DISCOVERING_SERVICES) {
                return ConnectionStatus.DISCOVERING_SERVICES;
            }
            return ConnectionStatus.DISCONNECTED;
        }
        return connectionStatus;
    }
}
