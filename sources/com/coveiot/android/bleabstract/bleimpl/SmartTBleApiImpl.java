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
import com.blankj.utilcode.constant.TimeConstants;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.formatter.LeonardoFormatter;
import com.coveiot.android.bleabstract.formatter.SmartTFormatter;
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
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.BpDataRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.EcgDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.LiveBpControlRequest;
import com.coveiot.android.bleabstract.request.LiveHeartRateControlRequest;
import com.coveiot.android.bleabstract.request.LiveStepsControlRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.RrDataRequest;
import com.coveiot.android.bleabstract.request.RrHistoryRequest;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.SetBandDispalyRequest;
import com.coveiot.android.bleabstract.request.SetCallSmsSocialNotificationRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.bleabstract.request.SetWearingHandRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.SportModeRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
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
import com.coveiot.sdk.ble.api.model.HrBpData;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.model.TodaysWalkData;
import com.coveiot.sdk.ble.api.request.BandDisplaySettingsReq;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.BloodPressureMeasurementReq;
import com.coveiot.sdk.ble.api.request.ChangeWearingHandReq;
import com.coveiot.sdk.ble.api.request.CurrentSportModeReq;
import com.coveiot.sdk.ble.api.request.HeartRateBpReq;
import com.coveiot.sdk.ble.api.request.HeartRateMeasurementReq;
import com.coveiot.sdk.ble.api.request.LiveStepsControlReq;
import com.coveiot.sdk.ble.api.request.MessageAlertSwitchesReq;
import com.coveiot.sdk.ble.api.request.MessageContentReq;
import com.coveiot.sdk.ble.api.request.RawEcgMeasurementReq;
import com.coveiot.sdk.ble.api.request.ReadBatteryLevelReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceFirmwareVersionReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceHardwareVersionReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceManufacturerReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceModelReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceNameReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceSerialNumberReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceSoftwareVersionReq;
import com.coveiot.sdk.ble.api.request.RrDataReq;
import com.coveiot.sdk.ble.api.request.SaveFitnessProfileReq;
import com.coveiot.sdk.ble.api.request.SedentaryReminderReq;
import com.coveiot.sdk.ble.api.request.SetAlarmReq;
import com.coveiot.sdk.ble.api.request.SetWalkTargetReq;
import com.coveiot.sdk.ble.api.request.SleepDataReq;
import com.coveiot.sdk.ble.api.request.TimeIntervalForAutomaticHeartRateReq;
import com.coveiot.sdk.ble.api.request.TodaysStepsDataReq;
import com.coveiot.sdk.ble.api.request.Vo2MaxReq;
import com.coveiot.sdk.ble.api.request.WalkDataReq;
import com.coveiot.sdk.ble.api.response.BandDisplaySettingsRes;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.BloodPressureMeasurementRes;
import com.coveiot.sdk.ble.api.response.ChangeWearingHandRes;
import com.coveiot.sdk.ble.api.response.CurrentSportModesRes;
import com.coveiot.sdk.ble.api.response.HeartRateMeasurementRes;
import com.coveiot.sdk.ble.api.response.HrBpDataRes;
import com.coveiot.sdk.ble.api.response.LiveHealthRes;
import com.coveiot.sdk.ble.api.response.LiveStepsControlRes;
import com.coveiot.sdk.ble.api.response.LiveStepsRes;
import com.coveiot.sdk.ble.api.response.MessageAlertSwitchesRes;
import com.coveiot.sdk.ble.api.response.MessageContentRes;
import com.coveiot.sdk.ble.api.response.RawEcgRes;
import com.coveiot.sdk.ble.api.response.ReadBatteryLevelRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceFirmwareVersionRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceHardwareVersionRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceManufacturerRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceModelRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceNameRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceSerialNumberRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceSoftwareVersionRes;
import com.coveiot.sdk.ble.api.response.RrDataRes;
import com.coveiot.sdk.ble.api.response.SaveFitnessProfileRes;
import com.coveiot.sdk.ble.api.response.SetAlarmRes;
import com.coveiot.sdk.ble.api.response.SetSedentaryReminderRes;
import com.coveiot.sdk.ble.api.response.SetWalkTargetRes;
import com.coveiot.sdk.ble.api.response.SleepDataRes;
import com.coveiot.sdk.ble.api.response.TimeIntervalForAutomaticHeartRateRes;
import com.coveiot.sdk.ble.api.response.TodaysStepsDataRes;
import com.coveiot.sdk.ble.api.response.Vo2MaxRes;
import com.coveiot.sdk.ble.api.response.WalkDataRes;
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
public class SmartTBleApiImpl implements BleApi, ResponseListener {
    public static final String o = "SmartTBleApiImpl";
    public static SmartTBleApiImpl p;
    public static LeonardoBleCmdService q;
    public static Context s;
    public static Intent t;

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<LiveHealthData> f3255a;
    public MutableLiveData<LiveStepsData> b;
    public MutableLiveData<ConnectionStatus> c;
    public BusyStatus d = BusyStatus.SYNCING;
    public Handler e = new Handler();
    public Handler f = new Handler();
    public Map<String, ResponseData> g = new HashMap();
    public List<Integer> h = new ArrayList();
    public int i = 1536;
    public int j;
    public String k;
    public String l;
    public MutableLiveData<LiveECGDataResponse> liveECGDataMutableLiveData;
    public Handler m;
    public DeviceInfoData n;
    public static volatile LinkedList<QueueObject> r = new LinkedList<>();
    public static ServiceConnection u = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SmartTBleApiImpl.q = ((LeonardoBleCmdService.BtServiceBinder) iBinder).getService();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            SmartTBleApiImpl.q = null;
        }
    };

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl$13  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass13 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3261a;
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
            int[] iArr18 = new int[com.coveiot.sdk.ble.api.error.Type.values().length];
            c = iArr18;
            try {
                iArr18[com.coveiot.sdk.ble.api.error.Type.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                c[com.coveiot.sdk.ble.api.error.Type.COMMAND_TIMED_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused23) {
            }
            int[] iArr19 = new int[ResponseType.values().length];
            b = iArr19;
            try {
                iArr19[ResponseType.GET_LIVE_HEALTH_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                b[ResponseType.GET_LIVE_STEPS.ordinal()] = 2;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                b[ResponseType.GET_LIVE_RAW_ECG.ordinal()] = 3;
            } catch (NoSuchFieldError unused26) {
            }
            int[] iArr20 = new int[CommandType.values().length];
            f3261a = iArr20;
            try {
                iArr20[CommandType.SET_ALARM.ordinal()] = 1;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f3261a[CommandType.SAVE_HEIGHT_WEIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                f3261a[CommandType.SET_BAND_DISPLAY_SETTINGS.ordinal()] = 3;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                f3261a[CommandType.SET_SEDENTARY_REMINDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                f3261a[CommandType.SET_HAND_PREFERENCE.ordinal()] = 5;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                f3261a[CommandType.SET_WALK_TARGET.ordinal()] = 6;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                f3261a[CommandType.SET_FITNESS_INFO.ordinal()] = 7;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                f3261a[CommandType.SET_MESSGAE_ALERT.ordinal()] = 8;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                f3261a[CommandType.SEND_MESSAGE_CONTENT.ordinal()] = 9;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                f3261a[CommandType.TIME_INTERVAL_FOR_AUTOMATIC_HEART_RATE.ordinal()] = 10;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                f3261a[CommandType.CURRENT_SPORT_MODE.ordinal()] = 11;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                f3261a[CommandType.VO2MAX.ordinal()] = 12;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                f3261a[CommandType.GET_TODAYS_WALK_DATA.ordinal()] = 13;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                f3261a[CommandType.WALK.ordinal()] = 14;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                f3261a[CommandType.SLEEP.ordinal()] = 15;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                f3261a[CommandType.HR_BP.ordinal()] = 16;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                f3261a[CommandType.RR.ordinal()] = 17;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                f3261a[CommandType.READ_DEVICE_INFO.ordinal()] = 18;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                f3261a[CommandType.HEART_RATE_CONTROL.ordinal()] = 19;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                f3261a[CommandType.BLOOD_PRESSURE_CONTROL.ordinal()] = 20;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                f3261a[CommandType.LIVE_STEPS_CONTROL.ordinal()] = 21;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                f3261a[CommandType.READ_BATTERY_LEVEL.ordinal()] = 22;
            } catch (NoSuchFieldError unused48) {
            }
        }
    }

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl$8  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass8 implements SettingsResultListener {
        public AnonymousClass8(SmartTBleApiImpl smartTBleApiImpl) {
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsError(BleBaseError bleBaseError) {
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
        }
    }

    /* loaded from: classes2.dex */
    public class QueueObject {

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3267a;

        public QueueObject(SmartTBleApiImpl smartTBleApiImpl, BleBaseRequest bleBaseRequest) {
            this.f3267a = bleBaseRequest;
        }
    }

    public SmartTBleApiImpl() {
        Handler handler = new Handler(Looper.getMainLooper());
        this.m = handler;
        handler.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.9
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().register(this);
            }
        });
    }

    public static void a(SmartTBleApiImpl smartTBleApiImpl, List list, boolean z, ScanDeviceRequest scanDeviceRequest, ScanResultListener scanResultListener) {
        smartTBleApiImpl.getClass();
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

    public static SmartTBleApiImpl getInstance(Context context) {
        if (p == null) {
            s = context.getApplicationContext();
            p = new SmartTBleApiImpl();
        }
        String str = o;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "check for service ++ ", moduleNames.getModuleName());
        t = new Intent(s, LeonardoBleCmdService.class);
        if (!a()) {
            LogHelper.d(str, "service is not running ++ ", moduleNames.getModuleName());
            try {
                s.bindService(t, u, 1);
                if (Build.VERSION.SDK_INT >= 26) {
                    s.startForegroundService(t);
                } else {
                    s.startService(t);
                }
            } catch (Exception e) {
                e.printStackTrace();
                BleApiUtils.checkExceptionAndShowNotification(e, s);
            }
        } else if (q == null) {
            s.bindService(t, u, 1);
        }
        return p;
    }

    public final void b() {
        if (DeviceScanManager.getInstance(s).isScanningInProgress()) {
            DeviceScanManager.getInstance(s).stopScan();
        }
        q = null;
        unbindService();
        this.e.removeCallbacksAndMessages(null);
        this.f.removeCallbacksAndMessages(null);
        p = null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return getBusyStatus() == BusyStatus.IDLE;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void cleanUpCommands() {
        setUserSettings(new LiveStepsControlRequest.Builder(false).build(), new SettingsResultListener() { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.7
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(BleBaseError bleBaseError) {
                SmartTBleApiImpl smartTBleApiImpl = SmartTBleApiImpl.this;
                String str = SmartTBleApiImpl.o;
                smartTBleApiImpl.getClass();
                smartTBleApiImpl.setUserSettings(new LiveHeartRateControlRequest.Builder(false).build(), new AnonymousClass8(smartTBleApiImpl));
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
                SmartTBleApiImpl smartTBleApiImpl = SmartTBleApiImpl.this;
                String str = SmartTBleApiImpl.o;
                smartTBleApiImpl.getClass();
                smartTBleApiImpl.setUserSettings(new LiveHeartRateControlRequest.Builder(false).build(), new AnonymousClass8(smartTBleApiImpl));
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void clearCommandQueue() {
        synchronized (r) {
            if (r != null && r.size() > 0) {
                r.clear();
            }
            LeonardoBleCmdService leonardoBleCmdService = q;
            if (leonardoBleCmdService != null) {
                leonardoBleCmdService.clearQueue();
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(final ConnectRequest connectRequest, final ConnectionResultListener connectionResultListener) {
        clearCommandQueue();
        this.e.removeCallbacksAndMessages(null);
        this.e.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.12
            @Override // java.lang.Runnable
            public void run() {
                LeonardoBleCmdService leonardoBleCmdService = SmartTBleApiImpl.q;
                if (leonardoBleCmdService == null || connectRequest == null) {
                    if (leonardoBleCmdService == null) {
                        try {
                            Intent intent = new Intent(SmartTBleApiImpl.s, LeonardoBleCmdService.class);
                            SmartTBleApiImpl.s.bindService(intent, SmartTBleApiImpl.u, 1);
                            if (Build.VERSION.SDK_INT >= 26) {
                                SmartTBleApiImpl.s.startForegroundService(intent);
                            } else {
                                SmartTBleApiImpl.s.startService(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            BleApiUtils.checkExceptionAndShowNotification(e, SmartTBleApiImpl.s);
                        }
                        connectionResultListener.onError(new Error(Type.SERVICE_NOT_RUNNING, "Service not running, call connect again"));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.COMMAND_REQUEST_ERROR, "Request should not be null"));
                    return;
                }
                CloveBleState.BleState connectionState = leonardoBleCmdService.getConnectionState();
                CloveBleState.BleState bleState = CloveBleState.BleState.CONNECTED;
                if (connectionState == bleState && ((String) BlePreferenceManager.getPreference(SmartTBleApiImpl.q.getApplicationContext(), CommonPreference.BLE_DEVICE_ADDRESS, "")).equalsIgnoreCase(connectRequest.getMacAddress())) {
                    connectionResultListener.onConnectionResponse(ConnectionStatus.CONNECTED);
                } else if (SmartTBleApiImpl.q.getConnectionState() == CloveBleState.BleState.DISCONNECTED) {
                    BleBaseRequest bleBaseRequest = new BleBaseRequest();
                    bleBaseRequest.setBleCommand(BleCommand.CONNECT);
                    bleBaseRequest.setResponseListener(connectionResultListener);
                    SmartTBleApiImpl.this.a(bleBaseRequest);
                    SmartTBleApiImpl.q.connect(connectRequest.getMacAddress());
                } else if (SmartTBleApiImpl.q.getConnectionState() == bleState) {
                    connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, "Connected to band " + SmartTBleApiImpl.q.mBluetoothDevice.getAddress()));
                } else if (SmartTBleApiImpl.q.getConnectionState() == CloveBleState.BleState.CONNECTING) {
                    LeonardoBleCmdService leonardoBleCmdService2 = SmartTBleApiImpl.q;
                    if (leonardoBleCmdService2.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, "Connection in progress " + SmartTBleApiImpl.q.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, leonardoBleCmdService2.getConnectionState().getStateAsString()));
                } else {
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, SmartTBleApiImpl.q.getConnectionState().getStateAsString()));
                }
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(ConnectionResultListener connectionResultListener) {
        if (q != null) {
            BleBaseRequest bleBaseRequest = new BleBaseRequest();
            bleBaseRequest.setBleCommand(BleCommand.DISCONNECT);
            bleBaseRequest.setResponseListener(connectionResultListener);
            a(bleBaseRequest);
            q.disconnectAndForget();
            q.clearQueue();
            b();
            return;
        }
        connectionResultListener.onError(new Error(Type.SERVICE_NOT_RUNNING, "service is not present"));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return this.d;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        if (q != null) {
            return new ConnectionInfo(getConnectionStatus(), q.getConnectionError(), q.getConnectionStageChangeTimeStamp());
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public ConnectionStatus getConnectionStatus() {
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        LeonardoBleCmdService leonardoBleCmdService = q;
        if (leonardoBleCmdService != null) {
            if (leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            return q.getConnectionState() == CloveBleState.BleState.CONNECTING ? ConnectionStatus.CONNECTING : connectionStatus;
        }
        return connectionStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        LeonardoBleCmdService leonardoBleCmdService = q;
        if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            if (bleBaseRequest instanceof StepsDataRequest) {
                StepsDataRequest stepsDataRequest = (StepsDataRequest) bleBaseRequest;
                long findDateDifference = BleApiUtils.findDateDifference(stepsDataRequest.getStartDate(), stepsDataRequest.getEndDate());
                Date startDate = stepsDataRequest.getStartDate();
                if (findDateDifference > getDeviceSupportedFeatures().getMaxDaysOfStepsDataOnBand()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startDate);
                    calendar.setTimeInMillis(calendar.getTimeInMillis() + ((findDateDifference - getDeviceSupportedFeatures().getMaxDaysOfStepsDataOnBand()) * 86400000));
                    startDate = calendar.getTime();
                    findDateDifference = getDeviceSupportedFeatures().getMaxDaysOfStepsDataOnBand();
                }
                BleCommand bleCommand = BleCommand.GET_STEPS_DATA;
                if (getSameCommandCount(bleCommand) > 0) {
                    removeSimilarCommand(bleCommand);
                }
                for (int i = 0; i <= findDateDifference; i++) {
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(startDate);
                    calendar2.setTimeInMillis(calendar2.getTimeInMillis() + (i * TimeConstants.DAY));
                    Date time = calendar2.getTime();
                    int startHour = stepsDataRequest.getStartHour();
                    int endHour = stepsDataRequest.getEndHour();
                    if (startHour == -1) {
                        startHour = 0;
                    }
                    if (endHour == -1) {
                        endHour = 23;
                    }
                    WalkDataReq build = new WalkDataReq.Builder().setStartDate(time).setEndDate(time).setStartHour(startHour).setEndHour(endHour).build();
                    BleBaseRequest build2 = new StepsDataRequest.Builder().setStartDate(time).setEndDate(time).setStartHour(startHour).setEndHour(endHour).build();
                    build2.setBleCommand(BleCommand.GET_STEPS_DATA);
                    build2.setResponseListener(dataResultListener);
                    build2.setRequId(UUID.randomUUID().toString());
                    a(build2);
                    build.setReqId(build2.getRequId());
                    q.sendRequest(build, this);
                }
            } else if (bleBaseRequest instanceof SleepDataRequest) {
                SleepDataRequest sleepDataRequest = (SleepDataRequest) bleBaseRequest;
                long findDateDifference2 = BleApiUtils.findDateDifference(sleepDataRequest.getStartDate(), sleepDataRequest.getEndDate());
                Date startDate2 = sleepDataRequest.getStartDate();
                if (findDateDifference2 > getDeviceSupportedFeatures().getMaxDaysOfSleepDataOnBand()) {
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(startDate2);
                    calendar3.setTimeInMillis(calendar3.getTimeInMillis() + ((findDateDifference2 - getDeviceSupportedFeatures().getMaxDaysOfSleepDataOnBand()) * 86400000));
                    startDate2 = calendar3.getTime();
                    findDateDifference2 = getDeviceSupportedFeatures().getMaxDaysOfSleepDataOnBand();
                }
                BleCommand bleCommand2 = BleCommand.GET_SLEEP_DATA;
                if (getSameCommandCount(bleCommand2) > 0) {
                    removeSimilarCommand(bleCommand2);
                }
                for (int i2 = 0; i2 <= findDateDifference2; i2++) {
                    Calendar calendar4 = Calendar.getInstance();
                    calendar4.setTime(startDate2);
                    calendar4.setTimeInMillis(calendar4.getTimeInMillis() + (i2 * TimeConstants.DAY));
                    Date time2 = calendar4.getTime();
                    int startHour2 = sleepDataRequest.getStartHour();
                    int endHour2 = sleepDataRequest.getEndHour();
                    if (startHour2 == -1) {
                        startHour2 = 12;
                    }
                    int i3 = 11;
                    if (endHour2 == -1) {
                        endHour2 = 11;
                    }
                    SleepDataReq.Builder startHour3 = new SleepDataReq.Builder().setStartDate(time2).setEndDate(time2).setStartHour(startHour2 >= 12 ? startHour2 : 12);
                    if (endHour2 <= 11) {
                        i3 = endHour2;
                    }
                    SleepDataReq build3 = startHour3.setEndHour(i3).build();
                    BleBaseRequest build4 = new SleepDataRequest.Builder().setStartDate(time2).setEndDate(time2).setStartHour(startHour2).setEndHour(endHour2).build();
                    build4.setResponseListener(dataResultListener);
                    build4.setBleCommand(BleCommand.GET_SLEEP_DATA);
                    build4.setRequId(UUID.randomUUID().toString());
                    a(build4);
                    build3.setReqId(build3.getReqId());
                    LogHelper.d(o, "queue size is ++ " + r.size() + "count value is ++" + i2, ModuleNames.BLEABSTRACT.getModuleName());
                    q.sendRequest(build3, this);
                }
            } else if (bleBaseRequest instanceof HeartRateDataRequest) {
                Integer num = (Integer) BlePreferenceManager.getPreference(s, CommonPreference.HR_INTERVAL_VALUE, 5);
                Integer num2 = 5;
                HeartRateDataRequest heartRateDataRequest = (HeartRateDataRequest) bleBaseRequest;
                long findDateDifference3 = BleApiUtils.findDateDifference(heartRateDataRequest.getStartDate(), heartRateDataRequest.getEndDate());
                Date startDate3 = heartRateDataRequest.getStartDate();
                if (findDateDifference3 > getDeviceSupportedFeatures().getMaxDaysOfHeartRateDataOnBand()) {
                    Calendar calendar5 = Calendar.getInstance();
                    calendar5.setTime(startDate3);
                    calendar5.setTimeInMillis(calendar5.getTimeInMillis() + ((findDateDifference3 - getDeviceSupportedFeatures().getMaxDaysOfHeartRateDataOnBand()) * 86400000));
                    startDate3 = calendar5.getTime();
                    findDateDifference3 = getDeviceSupportedFeatures().getMaxDaysOfHeartRateDataOnBand();
                }
                BleCommand bleCommand3 = BleCommand.GET_HEARTRATE_DATA;
                if (getSameCommandCount(bleCommand3) > 0) {
                    removeSimilarCommand(bleCommand3);
                }
                for (int i4 = 0; i4 <= findDateDifference3; i4++) {
                    Calendar calendar6 = Calendar.getInstance();
                    calendar6.setTime(startDate3);
                    calendar6.setTimeInMillis(calendar6.getTimeInMillis() + (i4 * TimeConstants.DAY));
                    Date time3 = calendar6.getTime();
                    int startHour4 = heartRateDataRequest.getStartHour();
                    int endHour3 = heartRateDataRequest.getEndHour();
                    if (startHour4 == -1) {
                        startHour4 = 0;
                    }
                    if (endHour3 == -1) {
                        endHour3 = 23;
                    }
                    HeartRateBpReq build5 = new HeartRateBpReq.Builder().setStartDate(time3).setEndDate(time3).setStartHour(startHour4).setEndHour(endHour3).setTimeInterval(num2.intValue()).build();
                    BleBaseRequest build6 = new HeartRateDataRequest.Builder().setStartDate(time3).setEndDate(time3).setStartHour(startHour4).setEndHour(endHour3).build();
                    build6.setResponseListener(dataResultListener);
                    build6.setBleCommand(BleCommand.GET_HEARTRATE_DATA);
                    build6.setRequId(UUID.randomUUID().toString());
                    a(build6);
                    build5.setReqId(build6.getRequId());
                    q.sendRequest(build5, this);
                }
            } else if (bleBaseRequest instanceof BpDataRequest) {
                BpDataRequest bpDataRequest = (BpDataRequest) bleBaseRequest;
                long findDateDifference4 = BleApiUtils.findDateDifference(bpDataRequest.getStartDate(), bpDataRequest.getEndDate());
                Date startDate4 = bpDataRequest.getStartDate();
                if (findDateDifference4 > getDeviceSupportedFeatures().getMaxDaysOfBpDataOnBand()) {
                    Calendar calendar7 = Calendar.getInstance();
                    calendar7.setTime(startDate4);
                    calendar7.setTimeInMillis(calendar7.getTimeInMillis() + ((findDateDifference4 - getDeviceSupportedFeatures().getMaxDaysOfBpDataOnBand()) * 86400000));
                    startDate4 = calendar7.getTime();
                    findDateDifference4 = getDeviceSupportedFeatures().getMaxDaysOfBpDataOnBand();
                }
                BleCommand bleCommand4 = BleCommand.GET_BP_DATA;
                if (getSameCommandCount(bleCommand4) > 0) {
                    removeSimilarCommand(bleCommand4);
                }
                int i5 = 0;
                while (true) {
                    int i6 = (i5 > findDateDifference4 ? 1 : (i5 == findDateDifference4 ? 0 : -1));
                    if (i6 > 0) {
                        return;
                    }
                    Calendar calendar8 = Calendar.getInstance();
                    calendar8.setTime(startDate4);
                    calendar8.setTimeInMillis(calendar8.getTimeInMillis() + (i5 * TimeConstants.DAY));
                    Date time4 = calendar8.getTime();
                    int startHour5 = bpDataRequest.getStartHour();
                    int endHour4 = bpDataRequest.getEndHour();
                    if (startHour5 == -1) {
                        startHour5 = 0;
                    }
                    if (endHour4 == -1) {
                        endHour4 = 23;
                    }
                    BpDataRequest bpDataRequest2 = bpDataRequest;
                    HeartRateBpReq build7 = new HeartRateBpReq.Builder().setStartDate(time4).setEndDate(time4).setStartHour(startHour5).setEndHour(endHour4).setTimeInterval(((Integer) BlePreferenceManager.getPreference(s, CommonPreference.HR_INTERVAL_VALUE, 5)).intValue()).build();
                    BpDataRequest build8 = new BpDataRequest.Builder().setStartDate(time4).setEndDate(time4).setStartHour(startHour5).setEndHour(endHour4).build();
                    build8.setResponseListener(dataResultListener);
                    build8.setBleCommand(BleCommand.GET_BP_DATA);
                    build8.setRequId(UUID.randomUUID().toString());
                    build7.setReqId(build8.getRequId());
                    HrBpDataRes hrBpDataRes = new HrBpDataRes(CommandType.HR_BP, build7);
                    hrBpDataRes.setResponseData(this.g.get(BleUtils.getDateFormater("yyyy-MM-dd").format(time4)));
                    BpResponse bpResponse = LeonardoFormatter.getBpResponse(hrBpDataRes.getHrBpData());
                    if (i6 == 0) {
                        bpResponse.setComplete(true);
                    } else {
                        bpResponse.setComplete(false);
                    }
                    LogHelper.d(o, "bp isComplete ++ " + bpResponse.isComplete(), ModuleNames.BLEABSTRACT.getModuleName());
                    BleBaseResponse bleBaseResponse = new BleBaseResponse(build8);
                    bleBaseResponse.setResponseData(bpResponse);
                    onDataResponse(bleBaseResponse, (DataResultListener) build8.getResponseListener());
                    i5++;
                    bpDataRequest = bpDataRequest2;
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
                    findDateDifference5 = 6;
                }
                BleCommand bleCommand5 = BleCommand.GET_RR_HISTORY_DATA;
                if (getSameCommandCount(bleCommand5) > 0) {
                    removeSimilarCommand(bleCommand5);
                }
                int i7 = 0;
                while (true) {
                    int i8 = (i7 > findDateDifference5 ? 1 : (i7 == findDateDifference5 ? 0 : -1));
                    if (i8 <= 0) {
                        Calendar calendar10 = Calendar.getInstance();
                        calendar10.setTime(startDate5);
                        calendar10.setTimeInMillis(calendar10.getTimeInMillis() + (i7 * TimeConstants.DAY));
                        Date time5 = calendar10.getTime();
                        int startHour6 = rrHistoryRequest.getStartHour();
                        int endHour5 = rrHistoryRequest.getEndHour();
                        if (startHour6 == -1) {
                            startHour6 = 0;
                        }
                        if (endHour5 == -1) {
                            endHour5 = 23;
                        }
                        RrHistoryRequest rrHistoryRequest2 = rrHistoryRequest;
                        HeartRateBpReq build9 = new HeartRateBpReq.Builder().setStartDate(time5).setEndDate(time5).setStartHour(startHour6).setEndHour(endHour5).setTimeInterval(((Integer) BlePreferenceManager.getPreference(s, CommonPreference.HR_INTERVAL_VALUE, 5)).intValue()).build();
                        RrHistoryRequest build10 = new RrHistoryRequest.Builder().setStartDate(time5).setEndDate(time5).setStartHour(startHour6).setEndHour(endHour5).build();
                        build10.setResponseListener(dataResultListener);
                        build10.setBleCommand(BleCommand.GET_RR_HISTORY_DATA);
                        build10.setRequId(UUID.randomUUID().toString());
                        build9.setReqId(build10.getRequId());
                        HrBpDataRes hrBpDataRes2 = new HrBpDataRes(CommandType.HR_BP, build9);
                        hrBpDataRes2.setResponseData(this.g.get(BleUtils.getDateFormater("yyyy-MM-dd").format(time5)));
                        RrResponse rrResponse = LeonardoFormatter.getRrResponse(hrBpDataRes2.getHrBpData());
                        if (i8 == 0) {
                            rrResponse.setComplete(true);
                        } else {
                            rrResponse.setComplete(false);
                        }
                        LogHelper.d(o, "RR isComplete ++ " + rrResponse.isComplete(), ModuleNames.BLEABSTRACT.getModuleName());
                        BleBaseResponse bleBaseResponse2 = new BleBaseResponse(build10);
                        bleBaseResponse2.setResponseData(rrResponse);
                        onDataResponse(bleBaseResponse2, (DataResultListener) build10.getResponseListener());
                        i7++;
                        rrHistoryRequest = rrHistoryRequest2;
                    } else {
                        this.g.clear();
                        return;
                    }
                }
            } else if (bleBaseRequest instanceof RrDataRequest) {
                RrDataRequest rrDataRequest = (RrDataRequest) bleBaseRequest;
                long findDateDifference6 = BleApiUtils.findDateDifference(rrDataRequest.getStartDate(), rrDataRequest.getEndDate());
                Date startDate6 = rrDataRequest.getStartDate();
                if (findDateDifference6 > getDeviceSupportedFeatures().getMaxDaysOfHeartRateDataOnBand()) {
                    Calendar calendar11 = Calendar.getInstance();
                    calendar11.setTime(startDate6);
                    calendar11.setTimeInMillis(calendar11.getTimeInMillis() + ((findDateDifference6 - getDeviceSupportedFeatures().getMaxDaysOfHeartRateDataOnBand()) * 86400000));
                    startDate6 = calendar11.getTime();
                    findDateDifference6 = getDeviceSupportedFeatures().getMaxDaysOfHeartRateDataOnBand();
                }
                BleCommand bleCommand6 = BleCommand.GET_RR_DATA;
                if (getSameCommandCount(bleCommand6) > 0) {
                    removeSimilarCommand(bleCommand6);
                }
                for (int i9 = 0; i9 <= findDateDifference6; i9++) {
                    Calendar calendar12 = Calendar.getInstance();
                    calendar12.setTime(startDate6);
                    calendar12.setTimeInMillis(calendar12.getTimeInMillis() + (i9 * TimeConstants.DAY));
                    Date time6 = calendar12.getTime();
                    int startHour7 = rrDataRequest.getStartHour();
                    int endHour6 = rrDataRequest.getEndHour();
                    if (startHour7 == -1) {
                        startHour7 = 0;
                    }
                    if (endHour6 == -1) {
                        endHour6 = 23;
                    }
                    RrDataReq build11 = new RrDataReq.Builder().setStartDate(time6).setEndDate(time6).setStartHour(startHour7).setEndHour(endHour6).build();
                    BleBaseRequest build12 = new RrDataRequest.Builder().setStartDate(time6).setEndDate(time6).setStartHour(startHour7).setEndHour(endHour6).build();
                    build12.setResponseListener(dataResultListener);
                    build12.setBleCommand(BleCommand.GET_RR_DATA);
                    build12.setRequId(UUID.randomUUID().toString());
                    a(build12);
                    build11.setReqId(build12.getRequId());
                    q.sendRequest(build11, this);
                }
            } else if (bleBaseRequest instanceof TodaysStepsDataRequest) {
                TodaysStepsDataReq build13 = new TodaysStepsDataReq.Builder().build();
                build13.setResponseListener(this);
                bleBaseRequest.setResponseListener(dataResultListener);
                bleBaseRequest.setBleCommand(BleCommand.GET_TODAYS_TOTAL_WALK_DATA);
                bleBaseRequest.setRequId(UUID.randomUUID().toString());
                a(bleBaseRequest);
                build13.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build13, this);
            } else if (bleBaseRequest instanceof DeviceInfoRequest) {
                bleBaseRequest.setBleCommand(BleCommand.DEVICE_INFO);
                bleBaseRequest.setResponseListener(dataResultListener);
                bleBaseRequest.setRequId(UUID.randomUUID().toString());
                this.n = null;
                DeviceInfoRequest deviceInfoRequest = (DeviceInfoRequest) bleBaseRequest;
                if (deviceInfoRequest.isSerialNo()) {
                    ReadDeviceSerialNumberReq build14 = new ReadDeviceSerialNumberReq.Builder().build();
                    build14.setResponseListener(this);
                    a(bleBaseRequest);
                    build14.setReqId(bleBaseRequest.getRequId());
                    q.sendRequest(build14, this);
                }
                if (deviceInfoRequest.isDevicName()) {
                    ReadDeviceNameReq build15 = new ReadDeviceNameReq.Builder().build();
                    build15.setResponseListener(this);
                    a(bleBaseRequest);
                    build15.setReqId(bleBaseRequest.getRequId());
                    q.sendRequest(build15, this);
                }
                if (deviceInfoRequest.isMacAddress()) {
                    String macAddress = getMacAddress();
                    if (this.n == null) {
                        this.n = new DeviceInfoData();
                    }
                    this.n.setMacAddress(macAddress);
                }
                if (deviceInfoRequest.isFwVersion()) {
                    ReadDeviceFirmwareVersionReq build16 = new ReadDeviceFirmwareVersionReq.Builder().build();
                    build16.setResponseListener(this);
                    a(bleBaseRequest);
                    build16.setReqId(bleBaseRequest.getRequId());
                    q.sendRequest(build16, this);
                }
                if (deviceInfoRequest.isHwVersion()) {
                    ReadDeviceHardwareVersionReq build17 = new ReadDeviceHardwareVersionReq.Builder().build();
                    build17.setResponseListener(this);
                    a(bleBaseRequest);
                    build17.setReqId(bleBaseRequest.getRequId());
                    q.sendRequest(build17, this);
                }
                if (deviceInfoRequest.isModelNo()) {
                    ReadDeviceModelReq build18 = new ReadDeviceModelReq.Builder().build();
                    build18.setResponseListener(this);
                    a(bleBaseRequest);
                    build18.setReqId(bleBaseRequest.getRequId());
                    q.sendRequest(build18, this);
                }
                if (deviceInfoRequest.isSoftwareRevision()) {
                    ReadDeviceSoftwareVersionReq build19 = new ReadDeviceSoftwareVersionReq.Builder().build();
                    build19.setResponseListener(this);
                    a(bleBaseRequest);
                    build19.setReqId(bleBaseRequest.getRequId());
                    q.sendRequest(build19, this);
                }
                if (deviceInfoRequest.isManufacturerName()) {
                    ReadDeviceManufacturerReq build20 = new ReadDeviceManufacturerReq.Builder().build();
                    build20.setResponseListener(this);
                    a(bleBaseRequest);
                    build20.setReqId(bleBaseRequest.getRequId());
                    q.sendRequest(build20, this);
                }
            } else if (bleBaseRequest instanceof BatteryLevelRequest) {
                bleBaseRequest.setResponseListener(dataResultListener);
                ReadBatteryLevelReq build21 = new ReadBatteryLevelReq.Builder().build();
                bleBaseRequest.setRequId(UUID.randomUUID().toString());
                bleBaseRequest.setBleCommand(BleCommand.READ_BATTERY_LEVEL);
                build21.setResponseListener(this);
                a(bleBaseRequest);
                build21.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build21, this);
            } else if (bleBaseRequest instanceof EcgDataRequest) {
                EcgDataRequest ecgDataRequest = (EcgDataRequest) bleBaseRequest;
                RawEcgMeasurementReq build22 = new RawEcgMeasurementReq.Builder(ecgDataRequest.isEnabled()).build();
                build22.setResponseListener(this);
                if (!ecgDataRequest.isEnabled()) {
                    BleBaseResponse bleBaseResponse3 = new BleBaseResponse(bleBaseRequest);
                    bleBaseResponse3.setResponseData(new SmartTFormatter(getMacAddress()).getECGResultResponse(this.h, this.k, this.l).m102clone());
                    dataResultListener.onDataResponse(bleBaseResponse3);
                } else {
                    this.h.clear();
                    this.j = 0;
                }
                bleBaseRequest.setResponseListener(dataResultListener);
                bleBaseRequest.setBleCommand(BleCommand.GET_ECG_RAW_DATA);
                r.add(new QueueObject(this, bleBaseRequest));
                q.sendRequest(build22, this);
            }
        } else {
            onDataError(dataResultListener, new BleBaseError("Band not connected", bleBaseRequest.getBleCommand()));
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        deviceSupportedFeatures.setMaxDaysOfStepsDataOnBand(4);
        deviceSupportedFeatures.setMaxDaysOfSleepDataOnBand(4);
        deviceSupportedFeatures.setMaxDaysOfBpDataOnBand(4);
        deviceSupportedFeatures.setMaxDaysOfRunDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfHeartRateDataOnBand(4);
        deviceSupportedFeatures.setMaxDaysOfRrDataOnBand(4);
        deviceSupportedFeatures.setMaxDaysOfCyclingDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfSwimmingDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfEcgRrDataOnBand(0);
        deviceSupportedFeatures.setStepsSupported(true);
        deviceSupportedFeatures.setSleepSupported(false);
        deviceSupportedFeatures.setRunSupported(false);
        deviceSupportedFeatures.setHeartRateSupported(true);
        deviceSupportedFeatures.setBpSupported(false);
        deviceSupportedFeatures.setRrSupported(false);
        deviceSupportedFeatures.setEcgSupported(true);
        deviceSupportedFeatures.setSwimmingSupported(false);
        deviceSupportedFeatures.setCyclingSupported(false);
        deviceSupportedFeatures.setShouldKeepDeviceConnectedAlways(false);
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
        deviceSupportedFeatures.setStepGoalSupported(false);
        deviceSupportedFeatures.setPhoneTypeCommandSupported(false);
        deviceSupportedFeatures.setTimeFormartCommandSupported(false);
        deviceSupportedFeatures.setLiveStepsSupported(true);
        deviceSupportedFeatures.setLiveHeartRateSupported(true);
        deviceSupportedFeatures.setLiveBPSupported(false);
        deviceSupportedFeatures.setAutoHrSettingsSupported(false);
        deviceSupportedFeatures.setBandSettingsSupported(false);
        deviceSupportedFeatures.setAppSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setBandSocialDistanceFeatureSupported(false);
        return deviceSupportedFeatures;
    }

    public QueueObject getFromQueue(BleCommand bleCommand, BaseResponse baseResponse) {
        synchronized (r) {
            Iterator<QueueObject> it = r.iterator();
            while (it.hasNext()) {
                QueueObject next = it.next();
                if (next.f3267a.getBleCommand() == bleCommand && next.f3267a.getRequId().equals(baseResponse.getBaseRequest().getReqId())) {
                    it.remove();
                    return next;
                }
            }
            return null;
        }
    }

    public QueueObject getFromQueueWithoutRemoving(BleCommand bleCommand) {
        if (r == null || r.size() <= 0) {
            return null;
        }
        Iterator<QueueObject> it = r.iterator();
        while (it.hasNext()) {
            QueueObject next = it.next();
            if (next.f3267a.getBleCommand() == bleCommand) {
                return next;
            }
        }
        return null;
    }

    public QueueObject getFromQueuebasedOnDate(BleCommand bleCommand, BaseRequest baseRequest) {
        synchronized (r) {
            Iterator<QueueObject> it = r.iterator();
            while (it.hasNext()) {
                QueueObject next = it.next();
                if (next.f3267a.getBleCommand() == bleCommand) {
                    if (baseRequest instanceof WalkDataReq) {
                        Date startDate = ((StepsDataRequest) next.f3267a).getStartDate();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(startDate);
                        Calendar calendar2 = Calendar.getInstance();
                        calendar2.setTime(((WalkDataReq) baseRequest).getStartDate());
                        if (calendar.get(6) == calendar2.get(6)) {
                            it.remove();
                            return next;
                        }
                    } else if (baseRequest instanceof SleepDataReq) {
                        Date startDate2 = ((SleepDataRequest) next.f3267a).getStartDate();
                        Calendar calendar3 = Calendar.getInstance();
                        calendar3.setTime(startDate2);
                        Calendar calendar4 = Calendar.getInstance();
                        calendar4.setTime(((SleepDataReq) baseRequest).getStartDate());
                        if (calendar3.get(6) == calendar4.get(6)) {
                            it.remove();
                            return next;
                        }
                    } else if (baseRequest instanceof HeartRateBpReq) {
                        BleBaseRequest bleBaseRequest = next.f3267a;
                        if (bleBaseRequest instanceof HeartRateDataRequest) {
                            Date startDate3 = ((HeartRateDataRequest) bleBaseRequest).getStartDate();
                            Calendar calendar5 = Calendar.getInstance();
                            calendar5.setTime(startDate3);
                            Calendar calendar6 = Calendar.getInstance();
                            calendar6.setTime(((HeartRateBpReq) baseRequest).getStartDate());
                            if (calendar5.get(6) == calendar6.get(6)) {
                                it.remove();
                                return next;
                            }
                        } else if (bleBaseRequest instanceof BpDataRequest) {
                            Date startDate4 = ((BpDataRequest) bleBaseRequest).getStartDate();
                            Calendar calendar7 = Calendar.getInstance();
                            calendar7.setTime(startDate4);
                            Calendar calendar8 = Calendar.getInstance();
                            calendar8.setTime(((HeartRateBpReq) baseRequest).getStartDate());
                            if (calendar7.get(6) == calendar8.get(6)) {
                                it.remove();
                                return next;
                            }
                        } else if (bleBaseRequest instanceof RrHistoryRequest) {
                            Date startDate5 = ((RrHistoryRequest) bleBaseRequest).getStartDate();
                            Calendar calendar9 = Calendar.getInstance();
                            calendar9.setTime(startDate5);
                            Calendar calendar10 = Calendar.getInstance();
                            calendar10.setTime(((HeartRateBpReq) baseRequest).getStartDate());
                            if (calendar9.get(6) == calendar10.get(6)) {
                                it.remove();
                                return next;
                            }
                        } else {
                            continue;
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
        return (String) BlePreferenceManager.getPreference(s, CommonPreference.BLE_DEVICE_ADDRESS, "");
    }

    public int getSameCommandCount(BleCommand bleCommand) {
        int i = 0;
        if (r != null && r.size() > 0) {
            Iterator<QueueObject> it = r.iterator();
            while (it.hasNext()) {
                if (it.next().f3267a.getBleCommand() == bleCommand) {
                    i++;
                }
            }
        }
        return i;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return BleApiUtils.isServiceRunning(LeonardoBleService.class.getName(), s);
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
            int i = AnonymousClass13.e[cloveBleState.getmState().ordinal()];
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
        MutableLiveData<ConnectionStatus> mutableLiveData = this.c;
        if (mutableLiveData != null) {
            mutableLiveData.setValue(connectionStatus);
            this.c.postValue(connectionStatus);
        }
        QueueObject fromQueueWithoutRemoving = getFromQueueWithoutRemoving(BleCommand.CONNECT);
        if (fromQueueWithoutRemoving != null && (bleBaseRequest2 = fromQueueWithoutRemoving.f3267a) != null && bleBaseRequest2.getResponseListener() != null && (fromQueueWithoutRemoving.f3267a.getResponseListener() instanceof ConnectionResultListener) && fromQueueWithoutRemoving.f3267a.getResponseListener() != null) {
            ((ConnectionResultListener) fromQueueWithoutRemoving.f3267a.getResponseListener()).onConnectionResponse(connectionStatus);
        }
        QueueObject fromQueue = getFromQueue(BleCommand.DISCONNECT);
        if (fromQueue != null && (bleBaseRequest = fromQueue.f3267a) != null && bleBaseRequest.getResponseListener() != null && (fromQueue.f3267a.getResponseListener() instanceof ConnectionResultListener)) {
            ((ConnectionResultListener) fromQueue.f3267a.getResponseListener()).onConnectionResponse(connectionStatus);
        }
        if (connectionStatus == connectionStatus2) {
            a(new com.coveiot.sdk.ble.api.error.Error(com.coveiot.sdk.ble.api.error.Type.DEVICE_NOT_CONNECTED, "Device disconnected"));
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
        this.m.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.2
            @Override // java.lang.Runnable
            public void run() {
                dataResultListener.onDataError(bleBaseError);
            }
        });
    }

    public void onDataResponse(final BleBaseResponse bleBaseResponse, final DataResultListener dataResultListener) {
        this.m.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.3
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
            int i = AnonymousClass13.c[error.getType().ordinal()];
            if (i == 1 || i == 2) {
                a(error);
            }
        }
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(ProgressDataBean progressDataBean) {
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(BaseResponse baseResponse) {
        if (baseResponse != null) {
            try {
                switch (AnonymousClass13.f3261a[baseResponse.getActivityType().ordinal()]) {
                    case 1:
                        BleCommand bleCommand = BleCommand.SET_VIBRATION_ALARM;
                        QueueObject fromQueue = getFromQueue(bleCommand, baseResponse);
                        if (fromQueue != null) {
                            SettingsResultListener settingsResultListener = (SettingsResultListener) fromQueue.f3267a.getResponseListener();
                            if (((SetAlarmRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener, new BleBaseResponse(fromQueue.f3267a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener, new BleBaseError("Vibration alarm setting failed", bleCommand));
                                return;
                            }
                        }
                        return;
                    case 2:
                        QueueObject fromQueue2 = getFromQueue(BleCommand.SET_FITNESS_PERSONAL_INFO, baseResponse);
                        if (fromQueue2 != null) {
                            SettingsResultListener settingsResultListener2 = (SettingsResultListener) fromQueue2.f3267a.getResponseListener();
                            if (((SaveFitnessProfileRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener2, new BleBaseResponse(fromQueue2.f3267a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener2, new BleBaseError("Vibration alarm setting failed", BleCommand.SET_VIBRATION_ALARM));
                                return;
                            }
                        }
                        return;
                    case 3:
                        BleCommand bleCommand2 = BleCommand.SET_BAND_DISPLAY;
                        QueueObject fromQueue3 = getFromQueue(bleCommand2, baseResponse);
                        if (fromQueue3 != null) {
                            SettingsResultListener settingsResultListener3 = (SettingsResultListener) fromQueue3.f3267a.getResponseListener();
                            BleBaseError bleBaseError = new BleBaseError("Band display setting failed", bleCommand2);
                            if (((BandDisplaySettingsRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener3, new BleBaseResponse(fromQueue3.f3267a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener3, bleBaseError);
                                return;
                            }
                        }
                        return;
                    case 4:
                        BleCommand bleCommand3 = BleCommand.SET_SEDENTARY_REMINDER;
                        QueueObject fromQueue4 = getFromQueue(bleCommand3, baseResponse);
                        if (fromQueue4 != null) {
                            SettingsResultListener settingsResultListener4 = (SettingsResultListener) fromQueue4.f3267a.getResponseListener();
                            if (((SetSedentaryReminderRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener4, new BleBaseResponse(fromQueue4.f3267a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener4, new BleBaseError("Sedentary reminder setting failed", bleCommand3));
                                return;
                            }
                        }
                        return;
                    case 5:
                        BleCommand bleCommand4 = BleCommand.SET_HAND_PREFERENCE;
                        QueueObject fromQueue5 = getFromQueue(bleCommand4, baseResponse);
                        if (fromQueue5 != null) {
                            SettingsResultListener settingsResultListener5 = (SettingsResultListener) fromQueue5.f3267a.getResponseListener();
                            if (((ChangeWearingHandRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener5, new BleBaseResponse(fromQueue5.f3267a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener5, new BleBaseError("Hand setting failed", bleCommand4));
                                return;
                            }
                        }
                        return;
                    case 6:
                        BleCommand bleCommand5 = BleCommand.SET_WALK_TARGET;
                        QueueObject fromQueue6 = getFromQueue(bleCommand5, baseResponse);
                        if (fromQueue6 != null) {
                            SettingsResultListener settingsResultListener6 = (SettingsResultListener) fromQueue6.f3267a.getResponseListener();
                            if (((SetWalkTargetRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener6, new BleBaseResponse(fromQueue6.f3267a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener6, new BleBaseError("Walk target setting failed", bleCommand5));
                                return;
                            }
                        }
                        return;
                    case 7:
                        BleCommand bleCommand6 = BleCommand.SET_FITNESS_PERSONAL_INFO;
                        QueueObject fromQueue7 = getFromQueue(bleCommand6, baseResponse);
                        if (fromQueue7 != null) {
                            SettingsResultListener settingsResultListener7 = (SettingsResultListener) fromQueue7.f3267a.getResponseListener();
                            BleBaseResponse bleBaseResponse = new BleBaseResponse(fromQueue7.f3267a);
                            if (((SaveFitnessProfileRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener7, bleBaseResponse);
                                return;
                            } else {
                                onSettingsError(settingsResultListener7, new BleBaseError("Fitness personal info setting failed", bleCommand6));
                                return;
                            }
                        }
                        return;
                    case 8:
                        BleCommand bleCommand7 = BleCommand.SET_MESSAGE_ALERT_SWITCH;
                        QueueObject fromQueue8 = getFromQueue(bleCommand7, baseResponse);
                        if (fromQueue8 != null) {
                            SettingsResultListener settingsResultListener8 = (SettingsResultListener) fromQueue8.f3267a.getResponseListener();
                            if (((MessageAlertSwitchesRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener8, new BleBaseResponse(fromQueue8.f3267a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener8, new BleBaseError("Message alert setting failed", bleCommand7));
                                return;
                            }
                        }
                        return;
                    case 9:
                        BleCommand bleCommand8 = BleCommand.SET_MESSAGE_CONTENT;
                        QueueObject fromQueue9 = getFromQueue(bleCommand8, baseResponse);
                        if (fromQueue9 != null) {
                            SettingsResultListener settingsResultListener9 = (SettingsResultListener) fromQueue9.f3267a.getResponseListener();
                            if (((MessageContentRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener9, new BleBaseResponse(fromQueue9.f3267a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener9, new BleBaseError("Message content setting failed", bleCommand8));
                                return;
                            }
                        }
                        return;
                    case 10:
                        BleCommand bleCommand9 = BleCommand.SET_HR_TIME_INTERVAL;
                        QueueObject fromQueue10 = getFromQueue(bleCommand9, baseResponse);
                        if (fromQueue10 != null) {
                            SettingsResultListener settingsResultListener10 = (SettingsResultListener) fromQueue10.f3267a.getResponseListener();
                            if (((TimeIntervalForAutomaticHeartRateRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener10, new BleBaseResponse(fromQueue10.f3267a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener10, new BleBaseError("Heart Rate time interval setting failed", bleCommand9));
                                return;
                            }
                        }
                        return;
                    case 11:
                        BleCommand bleCommand10 = BleCommand.SET_CURRENT_SPORT_MODE;
                        QueueObject fromQueue11 = getFromQueue(bleCommand10, baseResponse);
                        if (fromQueue11 != null) {
                            SettingsResultListener settingsResultListener11 = (SettingsResultListener) fromQueue11.f3267a.getResponseListener();
                            if (((CurrentSportModesRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener11, new BleBaseResponse(fromQueue11.f3267a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener11, new BleBaseError("Sports mode setting failed", bleCommand10));
                                return;
                            }
                        }
                        return;
                    case 12:
                        BleCommand bleCommand11 = BleCommand.UPDATE_V02MAX_VALUE;
                        QueueObject fromQueue12 = getFromQueue(bleCommand11, baseResponse);
                        if (fromQueue12 != null) {
                            SettingsResultListener settingsResultListener12 = (SettingsResultListener) fromQueue12.f3267a.getResponseListener();
                            if (((Vo2MaxRes) baseResponse).isSuccess()) {
                                onSettingsResponse(settingsResultListener12, new BleBaseResponse(fromQueue12.f3267a));
                                return;
                            } else {
                                onSettingsError(settingsResultListener12, new BleBaseError("Vo2max value updation failed", bleCommand11));
                                return;
                            }
                        }
                        return;
                    case 13:
                        BleCommand bleCommand12 = BleCommand.GET_TODAYS_TOTAL_WALK_DATA;
                        QueueObject fromQueue13 = getFromQueue(bleCommand12, baseResponse);
                        BleBaseResponse bleBaseResponse2 = new BleBaseResponse(fromQueue13.f3267a);
                        DataResultListener dataResultListener = (DataResultListener) fromQueue13.f3267a.getResponseListener();
                        try {
                            if (((TodaysStepsDataRes) baseResponse).isSuccess()) {
                                TodaysStepsResponse todaysStepsResponse = new TodaysStepsResponse();
                                TodaysWalkData stepsData = ((TodaysStepsDataRes) baseResponse).getStepsData();
                                TodaysStepsData todaysStepsData = new TodaysStepsData();
                                todaysStepsData.setTotalCalories(stepsData.getTotalCalories());
                                todaysStepsData.setTotalSteps(stepsData.getTotalSteps());
                                todaysStepsData.setTotalDistance(stepsData.getTotalDistance());
                                todaysStepsResponse.setTodaysStepsData(todaysStepsData);
                                bleBaseResponse2.setResponseData(todaysStepsResponse);
                                onDataResponse(bleBaseResponse2, dataResultListener);
                            } else {
                                onDataError(dataResultListener, new BleBaseError("Get Today's Steps Data failed", bleCommand12));
                            }
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            onDataError(dataResultListener, new BleBaseError(e.getMessage(), BleCommand.GET_TODAYS_TOTAL_WALK_DATA));
                            return;
                        }
                    case 14:
                        BleCommand bleCommand13 = BleCommand.GET_STEPS_DATA;
                        QueueObject fromQueuebasedOnDate = getFromQueuebasedOnDate(bleCommand13, baseResponse.getBaseRequest());
                        if (fromQueuebasedOnDate != null) {
                            try {
                                int sameCommandCount = getSameCommandCount(bleCommand13);
                                String str = o;
                                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                                LogHelper.d(str, "walk queue size is ++ " + sameCommandCount, moduleNames.getModuleName());
                                StepsResponse stepsResponse = LeonardoFormatter.getStepsResponse(((WalkDataRes) baseResponse).getStepsData());
                                if (sameCommandCount == 0) {
                                    stepsResponse.setComplete(true);
                                } else {
                                    stepsResponse.setComplete(false);
                                }
                                LogHelper.d(str, "walk isComplete ++ " + stepsResponse.isComplete(), moduleNames.getModuleName());
                                BleBaseResponse bleBaseResponse3 = new BleBaseResponse(fromQueuebasedOnDate.f3267a);
                                bleBaseResponse3.setResponseData(stepsResponse);
                                onDataResponse(bleBaseResponse3, (DataResultListener) fromQueuebasedOnDate.f3267a.getResponseListener());
                                return;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                onDataError((DataResultListener) fromQueuebasedOnDate.f3267a.getResponseListener(), new BleBaseError(e2.getMessage(), fromQueuebasedOnDate.f3267a.getBleCommand()));
                                return;
                            }
                        }
                        return;
                    case 15:
                        BleCommand bleCommand14 = BleCommand.GET_SLEEP_DATA;
                        QueueObject fromQueuebasedOnDate2 = getFromQueuebasedOnDate(bleCommand14, baseResponse.getBaseRequest());
                        if (fromQueuebasedOnDate2 != null) {
                            try {
                                int sameCommandCount2 = getSameCommandCount(bleCommand14);
                                String str2 = o;
                                ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
                                LogHelper.d(str2, "sleep queue size is ++ " + sameCommandCount2, moduleNames2.getModuleName());
                                SleepResponse sleepResponse = LeonardoFormatter.getSleepResponse(((SleepDataRes) baseResponse).getSleepData());
                                if (Calendar.getInstance().get(11) >= 12) {
                                    if (sameCommandCount2 <= 1) {
                                        sleepResponse.setComplete(true);
                                        removeSimilarCommand(bleCommand14);
                                    } else {
                                        sleepResponse.setComplete(false);
                                    }
                                } else if (sameCommandCount2 == 0) {
                                    sleepResponse.setComplete(true);
                                } else {
                                    sleepResponse.setComplete(false);
                                }
                                LogHelper.d(str2, "isComplete  ++ " + sleepResponse.isComplete(), moduleNames2.getModuleName());
                                BleBaseResponse bleBaseResponse4 = new BleBaseResponse(fromQueuebasedOnDate2.f3267a);
                                bleBaseResponse4.setResponseData(sleepResponse);
                                onDataResponse(bleBaseResponse4, (DataResultListener) fromQueuebasedOnDate2.f3267a.getResponseListener());
                                return;
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                onDataError((DataResultListener) fromQueuebasedOnDate2.f3267a.getResponseListener(), new BleBaseError(e3.getMessage(), fromQueuebasedOnDate2.f3267a.getBleCommand()));
                                return;
                            }
                        }
                        return;
                    case 16:
                        BleCommand bleCommand15 = BleCommand.GET_HEARTRATE_DATA;
                        QueueObject fromQueuebasedOnDate3 = getFromQueuebasedOnDate(bleCommand15, baseResponse.getBaseRequest());
                        QueueObject fromQueuebasedOnDate4 = getFromQueuebasedOnDate(BleCommand.GET_BP_DATA, baseResponse.getBaseRequest());
                        QueueObject fromQueuebasedOnDate5 = getFromQueuebasedOnDate(BleCommand.GET_RR_HISTORY_DATA, baseResponse.getBaseRequest());
                        try {
                            HrBpData hrBpData = ((HrBpDataRes) baseResponse).getHrBpData();
                            if (fromQueuebasedOnDate3 != null) {
                                int sameCommandCount3 = getSameCommandCount(bleCommand15);
                                String str3 = o;
                                ModuleNames moduleNames3 = ModuleNames.BLEABSTRACT;
                                LogHelper.d(str3, "hr queue size is ++ " + sameCommandCount3, moduleNames3.getModuleName());
                                HeartRateResponse heartRateResponse = LeonardoFormatter.getHeartRateResponse(hrBpData);
                                if (sameCommandCount3 == 0) {
                                    heartRateResponse.setComplete(true);
                                } else {
                                    heartRateResponse.setComplete(false);
                                }
                                this.g.put(BleUtils.getDateFormater("yyyy-MM-dd").format(((HeartRateBpReq) baseResponse.getBaseRequest()).getStartDate()), baseResponse.getResponseData());
                                LogHelper.d(str3, "hr isComplete ++ " + heartRateResponse.isComplete(), moduleNames3.getModuleName());
                                BleBaseResponse bleBaseResponse5 = new BleBaseResponse(fromQueuebasedOnDate3.f3267a);
                                bleBaseResponse5.setResponseData(heartRateResponse);
                                onDataResponse(bleBaseResponse5, (DataResultListener) fromQueuebasedOnDate3.f3267a.getResponseListener());
                            }
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            onDataError((DataResultListener) fromQueuebasedOnDate3.f3267a.getResponseListener(), new BleBaseError(e4.getMessage(), fromQueuebasedOnDate3.f3267a.getBleCommand()));
                        }
                        try {
                            HrBpData hrBpData2 = ((HrBpDataRes) baseResponse).getHrBpData();
                            if (fromQueuebasedOnDate4 != null) {
                                int sameCommandCount4 = getSameCommandCount(BleCommand.GET_BP_DATA);
                                String str4 = o;
                                ModuleNames moduleNames4 = ModuleNames.BLEABSTRACT;
                                LogHelper.d(str4, "bp queue size is ++ " + sameCommandCount4, moduleNames4.getModuleName());
                                BpResponse bpResponse = LeonardoFormatter.getBpResponse(hrBpData2);
                                if (sameCommandCount4 == 0) {
                                    bpResponse.setComplete(true);
                                } else {
                                    bpResponse.setComplete(false);
                                }
                                LogHelper.d(str4, "bp isComplete ++ " + bpResponse.isComplete(), moduleNames4.getModuleName());
                                BleBaseResponse bleBaseResponse6 = new BleBaseResponse(fromQueuebasedOnDate4.f3267a);
                                bleBaseResponse6.setResponseData(bpResponse);
                                onDataResponse(bleBaseResponse6, (DataResultListener) fromQueuebasedOnDate4.f3267a.getResponseListener());
                            }
                        } catch (Exception e5) {
                            e5.printStackTrace();
                            onDataError((DataResultListener) fromQueuebasedOnDate4.f3267a.getResponseListener(), new BleBaseError(e5.getMessage(), fromQueuebasedOnDate4.f3267a.getBleCommand()));
                        }
                        try {
                            HrBpData hrBpData3 = ((HrBpDataRes) baseResponse).getHrBpData();
                            if (fromQueuebasedOnDate5 != null) {
                                int sameCommandCount5 = getSameCommandCount(BleCommand.GET_RR_HISTORY_DATA);
                                String str5 = o;
                                ModuleNames moduleNames5 = ModuleNames.BLEABSTRACT;
                                LogHelper.d(str5, "rr queue size is ++ " + sameCommandCount5, moduleNames5.getModuleName());
                                RrResponse rrResponse = LeonardoFormatter.getRrResponse(hrBpData3);
                                if (sameCommandCount5 == 0) {
                                    rrResponse.setComplete(true);
                                } else {
                                    rrResponse.setComplete(false);
                                }
                                LogHelper.d(str5, "rr isComplete ++ " + rrResponse.isComplete(), moduleNames5.getModuleName());
                                BleBaseResponse bleBaseResponse7 = new BleBaseResponse(fromQueuebasedOnDate5.f3267a);
                                bleBaseResponse7.setResponseData(rrResponse);
                                onDataResponse(bleBaseResponse7, (DataResultListener) fromQueuebasedOnDate5.f3267a.getResponseListener());
                                return;
                            }
                            return;
                        } catch (Exception e6) {
                            e6.printStackTrace();
                            onDataError((DataResultListener) fromQueuebasedOnDate5.f3267a.getResponseListener(), new BleBaseError(e6.getMessage(), fromQueuebasedOnDate5.f3267a.getBleCommand()));
                            return;
                        }
                    case 17:
                        RrResponse rrResponse2 = LeonardoFormatter.getRrResponse(((RrDataRes) baseResponse).getRrData());
                        BleCommand bleCommand16 = BleCommand.GET_RR_DATA;
                        QueueObject fromQueuebasedOnDate6 = getFromQueuebasedOnDate(bleCommand16, baseResponse.getBaseRequest());
                        if (fromQueuebasedOnDate6 != null) {
                            try {
                                if (getSameCommandCount(bleCommand16) == 0) {
                                    rrResponse2.setComplete(true);
                                } else {
                                    rrResponse2.setComplete(false);
                                }
                                BleBaseResponse bleBaseResponse8 = new BleBaseResponse(fromQueuebasedOnDate6.f3267a);
                                bleBaseResponse8.setResponseData(rrResponse2);
                                onDataResponse(bleBaseResponse8, (DataResultListener) fromQueuebasedOnDate6.f3267a.getResponseListener());
                                return;
                            } catch (Exception e7) {
                                e7.printStackTrace();
                                onDataError((DataResultListener) fromQueuebasedOnDate6.f3267a.getResponseListener(), new BleBaseError(e7.getMessage(), fromQueuebasedOnDate6.f3267a.getBleCommand()));
                                return;
                            }
                        }
                        return;
                    case 18:
                        BleCommand bleCommand17 = BleCommand.DEVICE_INFO;
                        QueueObject fromQueue14 = getFromQueue(bleCommand17, baseResponse);
                        if (fromQueue14 == null) {
                            return;
                        }
                        int sameCommandCount6 = getSameCommandCount(bleCommand17);
                        if (this.n == null) {
                            this.n = new DeviceInfoData();
                        }
                        if (baseResponse instanceof ReadDeviceNameRes) {
                            this.n.setDeviceName(((ReadDeviceNameRes) baseResponse).getName());
                        } else if (baseResponse instanceof ReadDeviceSerialNumberRes) {
                            if (!((ReadDeviceSerialNumberRes) baseResponse).getSerialNumber().equalsIgnoreCase("N123456") && !((ReadDeviceSerialNumberRes) baseResponse).getSerialNumber().equalsIgnoreCase("kh23456")) {
                                this.n.setSerialNo(((ReadDeviceSerialNumberRes) baseResponse).getSerialNumber());
                            }
                            this.n.setSerialNo(((String) BlePreferenceManager.getPreference(s, CommonPreference.BLE_DEVICE_ADDRESS, "")).replaceAll(":", ""));
                        } else if (baseResponse instanceof ReadDeviceManufacturerRes) {
                            this.n.setManufacturerName(((ReadDeviceManufacturerRes) baseResponse).getManufacturerName());
                        } else if (baseResponse instanceof ReadDeviceHardwareVersionRes) {
                            this.n.setHwVersion(((ReadDeviceHardwareVersionRes) baseResponse).getHardwareVersion());
                        } else if (baseResponse instanceof ReadDeviceSoftwareVersionRes) {
                            this.n.setSoftwareRevision(((ReadDeviceSoftwareVersionRes) baseResponse).getSoftwareVersion());
                        } else if (baseResponse instanceof ReadDeviceFirmwareVersionRes) {
                            this.n.setFwVersion(((ReadDeviceFirmwareVersionRes) baseResponse).getFirmwareVersion());
                        } else if (baseResponse instanceof ReadDeviceModelRes) {
                            this.n.setModelNo(((ReadDeviceModelRes) baseResponse).getModelNumber());
                        }
                        DataResultListener dataResultListener2 = (DataResultListener) fromQueue14.f3267a.getResponseListener();
                        DeviceInfoResponse deviceInfoResponse = new DeviceInfoResponse();
                        if (sameCommandCount6 == 0) {
                            deviceInfoResponse.setComplete(true);
                        } else {
                            deviceInfoResponse.setComplete(false);
                        }
                        deviceInfoResponse.setDeviceInfo(this.n);
                        BleBaseResponse bleBaseResponse9 = new BleBaseResponse(fromQueue14.f3267a);
                        bleBaseResponse9.setResponseData(deviceInfoResponse);
                        onDataResponse(bleBaseResponse9, dataResultListener2);
                        return;
                    case 19:
                        BleCommand bleCommand18 = BleCommand.HEART_RATE_MEASUREMENT_CONTROL;
                        QueueObject fromQueue15 = getFromQueue(bleCommand18, baseResponse);
                        if (fromQueue15 == null || !(baseResponse instanceof HeartRateMeasurementRes)) {
                            return;
                        }
                        SettingsResultListener settingsResultListener13 = (SettingsResultListener) fromQueue15.f3267a.getResponseListener();
                        LogHelper.d(o, "ResponseListner" + settingsResultListener13.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                        if (((HeartRateMeasurementRes) baseResponse).isSuccess()) {
                            onSettingsResponse(settingsResultListener13, new BleBaseResponse(fromQueue15.f3267a));
                            return;
                        } else {
                            onSettingsError(settingsResultListener13, new BleBaseError("Error while setting heart rate control", bleCommand18));
                            return;
                        }
                    case 20:
                        QueueObject fromQueue16 = getFromQueue(BleCommand.BP_MEASUREMENT_CONTROL, baseResponse);
                        if (fromQueue16 == null || !(baseResponse instanceof BloodPressureMeasurementRes)) {
                            return;
                        }
                        SettingsResultListener settingsResultListener14 = (SettingsResultListener) fromQueue16.f3267a.getResponseListener();
                        if (((BloodPressureMeasurementRes) baseResponse).isSuccess()) {
                            onSettingsResponse(settingsResultListener14, new BleBaseResponse(fromQueue16.f3267a));
                            return;
                        } else {
                            onSettingsError(settingsResultListener14, new BleBaseError("Error while setting Blood pressure control", BleCommand.HEART_RATE_MEASUREMENT_CONTROL));
                            return;
                        }
                    case 21:
                        BleCommand bleCommand19 = BleCommand.GET_LIVE_STEPS_CONTROL;
                        QueueObject fromQueue17 = getFromQueue(bleCommand19, baseResponse);
                        if (fromQueue17 == null || !(baseResponse instanceof LiveStepsControlRes)) {
                            return;
                        }
                        SettingsResultListener settingsResultListener15 = (SettingsResultListener) fromQueue17.f3267a.getResponseListener();
                        BleBaseResponse bleBaseResponse10 = new BleBaseResponse(fromQueue17.f3267a);
                        if (((LiveStepsControlRes) baseResponse).isSuccess()) {
                            onSettingsResponse(settingsResultListener15, bleBaseResponse10);
                            return;
                        } else {
                            onSettingsError(settingsResultListener15, new BleBaseError("Error while setting live steps control", bleCommand19));
                            return;
                        }
                    case 22:
                        QueueObject fromQueue18 = getFromQueue(BleCommand.READ_BATTERY_LEVEL, baseResponse);
                        if (fromQueue18 == null || !(baseResponse instanceof ReadBatteryLevelRes)) {
                            return;
                        }
                        BatteryLevelResponse batteryLevelResponse = new BatteryLevelResponse();
                        batteryLevelResponse.setBatteryLevel(Integer.valueOf(((ReadBatteryLevelRes) baseResponse).getBatteryLevel()));
                        batteryLevelResponse.setComplete(true);
                        BleBaseResponse bleBaseResponse11 = new BleBaseResponse(fromQueue18.f3267a);
                        bleBaseResponse11.setResponseData(batteryLevelResponse);
                        onDataResponse(bleBaseResponse11, (DataResultListener) fromQueue18.f3267a.getResponseListener());
                        return;
                    default:
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
        int i = AnonymousClass13.b[responseEvent.getType().ordinal()];
        if (i == 1) {
            LiveHealthRes liveHealthRes = (LiveHealthRes) responseEvent.getData();
            MutableLiveData<LiveHealthData> mutableLiveData = this.f3255a;
            if (mutableLiveData != null) {
                mutableLiveData.setValue(Mapper.a(liveHealthRes.getLiveHealthData()));
                this.f3255a.postValue(Mapper.a(liveHealthRes.getLiveHealthData()));
            }
        } else if (i == 2) {
            LiveStepsRes liveStepsRes = (LiveStepsRes) responseEvent.getData();
            MutableLiveData<LiveStepsData> mutableLiveData2 = this.b;
            if (mutableLiveData2 != null) {
                mutableLiveData2.setValue(Mapper.a(liveStepsRes.getLiveStepsData()));
                this.b.postValue(Mapper.a(liveStepsRes.getLiveStepsData()));
            }
        } else if (i != 3) {
        } else {
            this.h.addAll(((RawEcgRes) responseEvent.getData()).getRawECGData().getLiveEcg());
            if (this.h.size() > this.i) {
                this.j++;
            }
            LiveECGDataResponse liveECGDataResponse = new LiveECGDataResponse();
            liveECGDataResponse.setDataType("ECGDATA");
            liveECGDataResponse.setQueueEcg(this.h);
            liveECGDataResponse.setEcgIndex(this.j);
            MutableLiveData<LiveECGDataResponse> mutableLiveData3 = this.liveECGDataMutableLiveData;
            if (mutableLiveData3 != null) {
                mutableLiveData3.postValue(liveECGDataResponse.m103clone());
            }
        }
    }

    public void onSettingsError(final SettingsResultListener settingsResultListener, final BleBaseError bleBaseError) {
        this.m.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.4
            @Override // java.lang.Runnable
            public void run() {
                settingsResultListener.onSettingsError(bleBaseError);
            }
        });
    }

    public void onSettingsResponse(final SettingsResultListener settingsResultListener, final BleBaseResponse bleBaseResponse) {
        this.m.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.5
            @Override // java.lang.Runnable
            public void run() {
                settingsResultListener.onSettingsResponse(bleBaseResponse);
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.c == null) {
            this.c = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        LeonardoBleCmdService leonardoBleCmdService = q;
        if (leonardoBleCmdService != null) {
            if (leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else if (q.getConnectionState() == CloveBleState.BleState.CONNECTING) {
                connectionStatus = ConnectionStatus.CONNECTING;
            } else if (q.getConnectionState() == CloveBleState.BleState.SCANNING) {
                connectionStatus = ConnectionStatus.SCANNING;
            } else if (q.getConnectionState() == CloveBleState.BleState.DISCOVERING_SERVICES) {
                connectionStatus = ConnectionStatus.DISCOVERING_SERVICES;
            }
        }
        this.c.postValue(connectionStatus);
        return this.c;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveECGDataResponse> registerForLiveEcgData() {
        if (this.liveECGDataMutableLiveData == null) {
            this.liveECGDataMutableLiveData = new MutableLiveData<>();
        }
        return this.liveECGDataMutableLiveData;
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
    @NotNull
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
        synchronized (r) {
            Iterator<QueueObject> it = r.iterator();
            while (it.hasNext()) {
                if (it.next().f3267a.getBleCommand() == bleCommand) {
                    it.remove();
                }
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        com.coveiot.sdk.ble.api.error.Error error = new com.coveiot.sdk.ble.api.error.Error(com.coveiot.sdk.ble.api.error.Type.COMMAND_TIMED_OUT, "Device disconnected");
        error.setShouldClearCommandQueue(true);
        a(error);
        LeonardoBleCmdService leonardoBleCmdService = q;
        if (leonardoBleCmdService != null) {
            leonardoBleCmdService.restartService();
            b();
        }
        new Handler().postDelayed(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Intent intent = new Intent(SmartTBleApiImpl.s, LeonardoBleCmdService.class);
                    SmartTBleApiImpl.s.bindService(intent, SmartTBleApiImpl.u, 1);
                    if (Build.VERSION.SDK_INT >= 26) {
                        SmartTBleApiImpl.s.startForegroundService(intent);
                    } else {
                        SmartTBleApiImpl.s.startService(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    BleApiUtils.checkExceptionAndShowNotification(e, SmartTBleApiImpl.s);
                }
            }
        }, 5000L);
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void retryCommand(BaseRequest baseRequest) {
        if (q != null) {
            LogHelper.d(o, "Retrying command", ModuleNames.BLEABSTRACT.getModuleName());
            q.sendRequest(baseRequest, this);
            return;
        }
        onFailure(new com.coveiot.sdk.ble.api.error.Error(com.coveiot.sdk.ble.api.error.Type.SERVICE_NOT_RUNNING, "Service not running"));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        clearCommandQueue();
        this.f.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(s).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.10
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(CharSequence charSequence) {
                        scanResultListener.onError(SmartTBleApiImpl.s.getString(R.string.scan_failed));
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(String str) {
                        SmartTBleApiImpl.a(SmartTBleApiImpl.this, new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.f.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.11
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!DeviceScanManager.getInstance(SmartTBleApiImpl.s).isScanningInProgress()) {
                        if (BleUtils.isEmpty(scanDeviceRequest.getScanFilter())) {
                            DeviceScanManager.getInstance(SmartTBleApiImpl.s).scanAllDevices(SmartTBleApiImpl.s, scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.11.1
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass11 anonymousClass11 = AnonymousClass11.this;
                                    SmartTBleApiImpl.a(SmartTBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(SmartTBleApiImpl.s.getString(R.string.scan_failed));
                                }
                            });
                        } else {
                            DeviceScanManager.getInstance(SmartTBleApiImpl.s).scanDevicesWithFilter(SmartTBleApiImpl.s, scanDeviceRequest.getScanFilter(), scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl.11.2
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass11 anonymousClass11 = AnonymousClass11.this;
                                    SmartTBleApiImpl.a(SmartTBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(SmartTBleApiImpl.s.getString(R.string.scan_failed));
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
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus busyStatus) {
        this.d = busyStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(ConnectionResultListener connectionResultListener) {
        removeSimilarCommand(BleCommand.CONNECT);
    }

    public void setEcgEndDate(String str) {
        this.l = str;
    }

    public void setEcgStartDate(String str) {
        this.k = str;
    }

    public void setPairingSupported(boolean z) {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(BleBaseRequest bleBaseRequest, SettingsResultListener settingsResultListener) {
        AppNotificationType appNotificationType;
        SetAlarmReq build;
        LeonardoBleCmdService leonardoBleCmdService = q;
        if (leonardoBleCmdService == null || leonardoBleCmdService.getConnectionState() != CloveBleState.BleState.CONNECTED) {
            if (bleBaseRequest != null && bleBaseRequest.getBleCommand() != null) {
                settingsResultListener.onSettingsError(new BleBaseError("Band is not connected", bleBaseRequest.getBleCommand()));
            } else {
                settingsResultListener.onSettingsError(new BleBaseError("Band is not connected"));
            }
        } else if (bleBaseRequest == null) {
            if (settingsResultListener != null) {
                BleBaseError bleBaseError = new BleBaseError("Request cannot be null");
                bleBaseError.setErrorMsg("Request cannot be null");
                settingsResultListener.onSettingsError(bleBaseError);
            }
        } else {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            if (bleBaseRequest instanceof SetReminderRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_SEDENTARY_REMINDER);
                bleBaseRequest.setResponseListener(settingsResultListener);
                a(bleBaseRequest);
                SetReminderRequest setReminderRequest = (SetReminderRequest) bleBaseRequest;
                SedentaryReminderReq build2 = new SedentaryReminderReq.Builder().setEnabled(setReminderRequest.isEnabled()).setStartHour1(setReminderRequest.getStartHour1()).setStartHour2(setReminderRequest.getStartHour2()).setEndHour1(setReminderRequest.getEndHour1()).setEndHour2(setReminderRequest.getEndHour2()).setStartMin1(setReminderRequest.getStartMin1()).setStartMin2(setReminderRequest.getStartMin2()).setEndMin1(setReminderRequest.getEndMin1()).setEndMin2(setReminderRequest.getEndMin2()).setReminderInterval(setReminderRequest.getReminderInterval()).build();
                build2.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build2, this);
            } else if (bleBaseRequest instanceof SetVibrationAlarmRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_VIBRATION_ALARM);
                bleBaseRequest.setResponseListener(settingsResultListener);
                a(bleBaseRequest);
                SetVibrationAlarmRequest setVibrationAlarmRequest = (SetVibrationAlarmRequest) bleBaseRequest;
                if (setVibrationAlarmRequest.isRepeatEnabled()) {
                    build = new SetAlarmReq.Builder(setVibrationAlarmRequest.getAlarmId(), setVibrationAlarmRequest.getHour(), setVibrationAlarmRequest.getMinute()).setAlarmOn(setVibrationAlarmRequest.isEnabled()).setEventName(setVibrationAlarmRequest.getEventName()).setDays(setVibrationAlarmRequest.isSundayEnabled(), setVibrationAlarmRequest.isMondayEnabled(), setVibrationAlarmRequest.isTuesdayEnabled(), setVibrationAlarmRequest.isWednesdayEnabled(), setVibrationAlarmRequest.isThursdayEnabled(), setVibrationAlarmRequest.isFridayEnabled(), setVibrationAlarmRequest.isSaturdayEnabled()).build();
                } else {
                    build = new SetAlarmReq.Builder(setVibrationAlarmRequest.getAlarmId(), setVibrationAlarmRequest.getHour(), setVibrationAlarmRequest.getMinute()).setAlarmOn(setVibrationAlarmRequest.isEnabled()).setEventName(setVibrationAlarmRequest.getEventName()).setDays(false, false, false, false, false, false, false).build();
                }
                build.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build, this);
            } else if (bleBaseRequest instanceof SetBandDispalyRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_BAND_DISPLAY);
                bleBaseRequest.setResponseListener(settingsResultListener);
                a(bleBaseRequest);
                SetBandDispalyRequest setBandDispalyRequest = (SetBandDispalyRequest) bleBaseRequest;
                BandDisplaySettingsReq build3 = new BandDisplaySettingsReq.Builder().shouldShowActiviteTime(setBandDispalyRequest.isShowActiveTime()).shouldShowStepCount(setBandDispalyRequest.isShowStepCount()).shouldShowDistance(setBandDispalyRequest.isShowDistance()).shouldShowCaloriesCount(setBandDispalyRequest.isShowCaloriesCount()).shouldShowEmotion(setBandDispalyRequest.isShowEmotion()).shouldShowActivityProgress(setBandDispalyRequest.isShowActivityProgress()).shouldShowAlarm(setBandDispalyRequest.isShowAlarm()).shouldShowDateAndTime(setBandDispalyRequest.isShowDateAndTime()).build();
                build3.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build3, this);
            } else if (bleBaseRequest instanceof SetWearingHandRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_HAND_PREFERENCE);
                bleBaseRequest.setResponseListener(settingsResultListener);
                a(bleBaseRequest);
                ChangeWearingHandReq build4 = new ChangeWearingHandReq.Builder(((SetWearingHandRequest) bleBaseRequest).isRightHand()).build();
                build4.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build4, this);
            } else if (bleBaseRequest instanceof SetFitnessInfoRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_FITNESS_PERSONAL_INFO);
                a(bleBaseRequest);
                SetFitnessInfoRequest setFitnessInfoRequest = (SetFitnessInfoRequest) bleBaseRequest;
                SaveFitnessProfileReq build5 = new SaveFitnessProfileReq.Builder(setFitnessInfoRequest.getHeight(), (int) setFitnessInfoRequest.getWeight(), (int) setFitnessInfoRequest.getStride(), (int) setFitnessInfoRequest.getRunstride(), setFitnessInfoRequest.isMale()).build();
                build5.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build5, this);
            } else if (bleBaseRequest instanceof SetCallSmsSocialNotificationRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_MESSAGE_ALERT_SWITCH);
                a(bleBaseRequest);
                SetCallSmsSocialNotificationRequest setCallSmsSocialNotificationRequest = (SetCallSmsSocialNotificationRequest) bleBaseRequest;
                MessageAlertSwitchesReq build6 = new MessageAlertSwitchesReq.Builder().setAppAlerts(setCallSmsSocialNotificationRequest.isCallEnabled(), setCallSmsSocialNotificationRequest.isCalendarEnabled(), setCallSmsSocialNotificationRequest.isSmsEnabled(), setCallSmsSocialNotificationRequest.isEmailEnabled(), setCallSmsSocialNotificationRequest.isWhatsAppEnabled(), setCallSmsSocialNotificationRequest.isWeChatEnabled(), setCallSmsSocialNotificationRequest.isFacebookEnabled(), setCallSmsSocialNotificationRequest.isInstagramEnabled(), setCallSmsSocialNotificationRequest.isTwitterEnabled(), setCallSmsSocialNotificationRequest.isMessengerEnabled(), setCallSmsSocialNotificationRequest.isQQEnabled(), setCallSmsSocialNotificationRequest.isQzoneEnabled(), setCallSmsSocialNotificationRequest.isSnapchatEnabled(), setCallSmsSocialNotificationRequest.isSkypeEnabled(), setCallSmsSocialNotificationRequest.isLineEnabled(), setCallSmsSocialNotificationRequest.isTelegramEnabled(), setCallSmsSocialNotificationRequest.isLinkedInEnabled()).build();
                build6.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build6, this);
            } else if (bleBaseRequest instanceof StepsTargetRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_WALK_TARGET);
                a(bleBaseRequest);
                SetWalkTargetReq build7 = new SetWalkTargetReq.Builder().setTarget(((StepsTargetRequest) bleBaseRequest).getTarget()).build();
                build7.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build7, this);
            } else if (bleBaseRequest instanceof LiveHeartRateControlRequest) {
                bleBaseRequest.setBleCommand(BleCommand.HEART_RATE_MEASUREMENT_CONTROL);
                bleBaseRequest.setResponseListener(settingsResultListener);
                a(bleBaseRequest);
                HeartRateMeasurementReq build8 = new HeartRateMeasurementReq.Builder(((LiveHeartRateControlRequest) bleBaseRequest).isEnabled()).build();
                build8.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build8, this);
            } else if (bleBaseRequest instanceof LiveBpControlRequest) {
                bleBaseRequest.setBleCommand(BleCommand.BP_MEASUREMENT_CONTROL);
                bleBaseRequest.setResponseListener(settingsResultListener);
                a(bleBaseRequest);
                BloodPressureMeasurementReq build9 = new BloodPressureMeasurementReq.Builder(((LiveBpControlRequest) bleBaseRequest).isEnabled()).build();
                build9.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build9, this);
            } else if (bleBaseRequest instanceof LiveStepsControlRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.GET_LIVE_STEPS_CONTROL);
                a(bleBaseRequest);
                LiveStepsControlReq liveStepsControlReq = new LiveStepsControlReq(((LiveStepsControlRequest) bleBaseRequest).isEnable());
                liveStepsControlReq.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(liveStepsControlReq, this);
            } else if (bleBaseRequest instanceof HeartRateTimeIntervalRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_HR_TIME_INTERVAL);
                a(bleBaseRequest);
                TimeIntervalForAutomaticHeartRateReq timeIntervalForAutomaticHeartRateReq = new TimeIntervalForAutomaticHeartRateReq(((HeartRateTimeIntervalRequest) bleBaseRequest).getTimeInterval());
                BlePreferenceManager.savePreference(s, CommonPreference.HR_INTERVAL_VALUE, Integer.valueOf(timeIntervalForAutomaticHeartRateReq.getTimeInterval()));
                timeIntervalForAutomaticHeartRateReq.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(timeIntervalForAutomaticHeartRateReq, this);
            } else if (bleBaseRequest instanceof SetMessageContentRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_MESSAGE_CONTENT);
                a(bleBaseRequest);
                SetMessageContentRequest setMessageContentRequest = (SetMessageContentRequest) bleBaseRequest;
                switch (setMessageContentRequest.getAppNotificationType().ordinal()) {
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
                        appNotificationType = AppNotificationType.SMS;
                        break;
                }
                MessageContentReq build10 = new MessageContentReq.Builder(appNotificationType, setMessageContentRequest.title + HexStringBuilder.DEFAULT_SEPARATOR + setMessageContentRequest.message).build();
                build10.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build10, this);
            } else if (bleBaseRequest instanceof SportModeRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_CURRENT_SPORT_MODE);
                bleBaseRequest.setResponseListener(settingsResultListener);
                a(bleBaseRequest);
                SportModeRequest sportModeRequest = (SportModeRequest) bleBaseRequest;
                CurrentSportModeReq build11 = new CurrentSportModeReq.Builder().setModes(sportModeRequest.isRunning(), sportModeRequest.isSwimming(), sportModeRequest.isCycling(), sportModeRequest.isWalking(), sportModeRequest.isTaichi()).setIsIndoor(sportModeRequest.isIndoor()).build();
                build11.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build11, this);
            } else if (bleBaseRequest instanceof Vo2MaxRequest) {
                bleBaseRequest.setBleCommand(BleCommand.UPDATE_V02MAX_VALUE);
                bleBaseRequest.setResponseListener(settingsResultListener);
                a(bleBaseRequest);
                Vo2MaxRequest vo2MaxRequest = (Vo2MaxRequest) bleBaseRequest;
                Vo2MaxReq build12 = new Vo2MaxReq.Builder(vo2MaxRequest.getVo2MaxValue(), vo2MaxRequest.getYear(), vo2MaxRequest.getMonth(), vo2MaxRequest.getDay()).build();
                build12.setReqId(bleBaseRequest.getRequId());
                q.sendRequest(build12, this);
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(s).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        BlePreferenceManager.savePreference(s, CommonPreference.BLE_DEVICE_ADDRESS, "");
        BlePreferenceManager.savePreference(s, CommonPreference.BLE_CONNECTION_TYPE, ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        LeonardoBleCmdService leonardoBleCmdService = q;
        if (leonardoBleCmdService != null) {
            leonardoBleCmdService.stopService();
            b();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
    }

    public void unbindService() {
        ServiceConnection serviceConnection = u;
        if (serviceConnection != null) {
            s.unbindService(serviceConnection);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
        clearCommandQueue();
        if (q != null) {
            new BleBaseRequest().setBleCommand(BleCommand.DISCONNECT);
            q.disconnectAndForget();
            b();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public MutableLiveData<LiveHealthData> registerLiveHealthData() {
        if (this.f3255a == null) {
            this.f3255a = new MutableLiveData<>();
        }
        return this.f3255a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public MutableLiveData<LiveStepsData> registerLiveStepsData() {
        if (this.b == null) {
            this.b = new MutableLiveData<>();
        }
        return this.b;
    }

    public QueueObject getFromQueue(BleCommand bleCommand) {
        synchronized (r) {
            Iterator<QueueObject> it = r.iterator();
            while (it.hasNext()) {
                QueueObject next = it.next();
                if (next.f3267a.getBleCommand() == bleCommand) {
                    it.remove();
                    return next;
                }
            }
            return null;
        }
    }

    public final void a(com.coveiot.sdk.ble.api.error.Error error) {
        if (r == null || r.size() <= 0) {
            return;
        }
        try {
            Iterator<QueueObject> it = r.iterator();
            while (it.hasNext()) {
                QueueObject next = it.next();
                BaseListener responseListener = next.f3267a.getResponseListener();
                BleBaseError bleBaseError = new BleBaseError(error.getMessage(), next.f3267a.getBleCommand());
                if (error.getType() == com.coveiot.sdk.ble.api.error.Type.COMMAND_TIMED_OUT) {
                    bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_ERROR_TIMEOUT.value));
                }
                if (responseListener instanceof DataResultListener) {
                    onDataError((DataResultListener) responseListener, bleBaseError);
                } else if (responseListener instanceof SettingsResultListener) {
                    onSettingsError((SettingsResultListener) responseListener, bleBaseError);
                } else if (responseListener instanceof ConnectionResultListener) {
                    ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, error.getMessage()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        clearCommandQueue();
    }

    public static boolean a() {
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) s.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (LeonardoBleCmdService.class.getName().equals(runningServiceInfo.service.getClassName()) && s.getPackageName().equals(runningServiceInfo.service.getPackageName())) {
                z = true;
            }
        }
        return z;
    }

    public final void a(BleBaseRequest bleBaseRequest) {
        synchronized (r) {
            r.add(new QueueObject(this, bleBaseRequest));
        }
    }
}
