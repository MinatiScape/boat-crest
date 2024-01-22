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
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.formatter.JStyle1963DFormatter;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.Clove1963DBleState;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.models.RawPPGData;
import com.coveiot.android.bleabstract.preferences.PreferenceManager1963D;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ChangeWatchFaceRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceRequest;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.FindMyWatchRequest;
import com.coveiot.android.bleabstract.request.GetDeviceSettingsInfoRequest;
import com.coveiot.android.bleabstract.request.GetWatchFacePositionRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.LiveHeartRateControlRequest;
import com.coveiot.android.bleabstract.request.LiveRawPPGControlRequest;
import com.coveiot.android.bleabstract.request.LiveStepsControlRequest;
import com.coveiot.android.bleabstract.request.LiveTemperatureControlRequest;
import com.coveiot.android.bleabstract.request.MotorVibrationRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.OTAModeRequest;
import com.coveiot.android.bleabstract.request.ReadManualBpRequest;
import com.coveiot.android.bleabstract.request.ReadManualSpo2Request;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.SessionHeartRateRequest;
import com.coveiot.android.bleabstract.request.SessionStepsDataRequest;
import com.coveiot.android.bleabstract.request.SetDeviceParametersRequest;
import com.coveiot.android.bleabstract.request.SetDistanceUnitRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.request.SetTemperatureUnitRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmListRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.bleabstract.request.StopMessageNotificationRequest;
import com.coveiot.android.bleabstract.request.TemperatureCalibrationDataRequest;
import com.coveiot.android.bleabstract.request.TemperatureDataRequest;
import com.coveiot.android.bleabstract.request.TemperatureTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.CallRejectRes;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceSettingsInfoResponse;
import com.coveiot.android.bleabstract.response.LiveAGPSUploadPercentage;
import com.coveiot.android.bleabstract.response.LiveECGDataResponse;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveSportData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.android.bleabstract.response.Spo2Response;
import com.coveiot.android.bleabstract.response.Spo2WaveResponse;
import com.coveiot.android.bleabstract.services.KH1963DBleService;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleConstants;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils;
import com.coveiot.android.jstyle1963dsdk.JstyleResponseListener;
import com.coveiot.android.jstyle1963dsdk.api.JStyleRawPPGControlReq;
import com.coveiot.android.jstyle1963dsdk.api.JStyleRealTimeWalkReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleActivityModeHistoryReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleBaseReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleBatteryLevelReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleChangeWatchFaceReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleCustomWatchFaceReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleDeviceBaseParametersReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleDeviceVersionReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleDistanceUnitReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleEnterOTAReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleGetDeviceInfoReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleHRTimeIntervalReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleHistoryHRReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleHrvReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleLiftWristReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleMotorVibrationReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleNotificationReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleOnceHRReq;
import com.coveiot.android.jstyle1963dsdk.api.JstylePersonalInfoReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleSedentaryReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleSleepReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleSpo2Req;
import com.coveiot.android.jstyle1963dsdk.api.JstyleStepTargetReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleTemperatureCalibrationReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleTemperatureReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleTemperatureUnitReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleTimeFormatReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleVibrationAlarmReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleWalkReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleWatchFacePositionReq;
import com.coveiot.android.jstyle1963dsdk.error.JstyleError;
import com.coveiot.android.jstyle1963dsdk.error.JstyleErrorType;
import com.coveiot.android.jstyle1963dsdk.model.JstyleLiveResponse;
import com.coveiot.khjstyledb.deviceinfo.KHJstyleDeviceInfoRepository;
import com.coveiot.khjstyledb.deviceinfo.KHJstyleEntityDeviceInfo;
import com.coveiot.sdk.ble.api.model.FindMyPhoneState;
import com.coveiot.sdk.ble.api.response.FindMyPhoneRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.scan.AssociationResult;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1963.model.Clock;
import com.jstyle.blesdk1963.model.MyAutomaticHRMonitoring;
import com.jstyle.blesdk1963.model.MyDeviceInfo;
import com.jstyle.blesdk1963.model.MyPersonalInfo;
import com.jstyle.blesdk1963.model.MySedentaryReminder;
import com.jstyle.blesdk1963.model.Notifier;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class JStyle1963YHBleApiImpl implements BleApi, JstyleResponseListener {
    public static JStyle1963YHBleApiImpl r;
    public static KH1963DBleService t;
    public static String v;
    public static Context w;

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<PPGData> f3090a;
    public MutableLiveData<ConnectionStatus> d;
    public DeviceInfoData e;
    public MutableLiveData<LiveHealthData> f;
    public MutableLiveData<LiveStepsData> g;
    public MutableLiveData<LiveECGDataResponse> h;
    public MutableLiveData<LiveTemperatureData> i;
    public MutableLiveData<LiveWatchFaceUploadPercentage> j;
    public static final String s = JStyle1963YHBleApiImpl.class.getSimpleName();
    public static volatile LinkedList<QueueObject> u = new LinkedList<>();
    public static ServiceConnection x = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            JStyle1963YHBleApiImpl.t = ((KH1963DBleService.BtServiceBinder) iBinder).getService();
            if (TextUtils.isEmpty(JStyle1963YHBleApiImpl.v)) {
                return;
            }
            JStyle1963YHBleApiImpl.t.initBluetoothDevice();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            JStyle1963YHBleApiImpl.t = null;
        }
    };
    public Handler b = new Handler();
    public Handler c = new Handler();
    public Handler k = new Handler(Looper.getMainLooper());
    public ArrayList<Integer> l = new ArrayList<>();
    public Handler m = new Handler();
    public BleBaseRequest n = null;
    public ConnectionResultListener o = null;
    public BusyStatus p = BusyStatus.SYNCING;
    public Runnable q = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.32
        @Override // java.lang.Runnable
        public void run() {
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.d("Command TimeOut", "Failed", moduleNames.getModuleName());
            JStyle1963YHBleApiImpl jStyle1963YHBleApiImpl = JStyle1963YHBleApiImpl.this;
            BleBaseRequest bleBaseRequest = jStyle1963YHBleApiImpl.n;
            if (bleBaseRequest != null) {
                JstyleBaseReq b = jStyle1963YHBleApiImpl.b(bleBaseRequest);
                if (b != null) {
                    JStyle1963YHBleApiImpl.this.onFailure(new JstyleError(JstyleErrorType.COMMAND_TIME_OUT, JStyle1963YHBleApiImpl.w.getString(R.string.command_time_out)));
                    LogHelper.e("Command TimeOut", "End Time " + System.currentTimeMillis() + "-- currentCommand " + b.getDataType(), moduleNames.getModuleName());
                } else {
                    JStyle1963YHBleApiImpl jStyle1963YHBleApiImpl2 = JStyle1963YHBleApiImpl.this;
                    jStyle1963YHBleApiImpl2.c(jStyle1963YHBleApiImpl2.n);
                }
            }
            JStyle1963YHBleApiImpl.this.n = null;
        }
    };

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl$33  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass33 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3114a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            Clove1963DBleState.BleState.values();
            int[] iArr = new int[5];
            c = iArr;
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            NotificationType.values();
            int[] iArr2 = new int[65];
            b = iArr2;
            try {
                NotificationType notificationType = NotificationType.SMS;
                iArr2[2] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                int[] iArr3 = b;
                NotificationType notificationType2 = NotificationType.CALL;
                iArr3[0] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                int[] iArr4 = b;
                NotificationType notificationType3 = NotificationType.WECHAT;
                iArr4[5] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                int[] iArr5 = b;
                NotificationType notificationType4 = NotificationType.FACEBOOK;
                iArr5[6] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                int[] iArr6 = b;
                NotificationType notificationType5 = NotificationType.WHATSAPP;
                iArr6[4] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                int[] iArr7 = b;
                NotificationType notificationType6 = NotificationType.TELEGRAM;
                iArr7[17] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                int[] iArr8 = b;
                NotificationType notificationType7 = NotificationType.QQ;
                iArr8[10] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                int[] iArr9 = b;
                NotificationType notificationType8 = NotificationType.INSTAGRAM;
                iArr9[7] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                int[] iArr10 = b;
                NotificationType notificationType9 = NotificationType.SKYPE;
                iArr10[14] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                int[] iArr11 = b;
                NotificationType notificationType10 = NotificationType.TWITTER;
                iArr11[8] = 10;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                int[] iArr12 = b;
                NotificationType notificationType11 = NotificationType.VKCLIENT;
                iArr12[16] = 11;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                int[] iArr13 = b;
                NotificationType notificationType12 = NotificationType.LINKEDIN;
                iArr13[15] = 12;
            } catch (NoSuchFieldError unused15) {
            }
            int[] iArr14 = new int[JstyleErrorType.values().length];
            f3114a = iArr14;
            try {
                iArr14[JstyleErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f3114a[JstyleErrorType.COMMAND_TIME_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public class QueueObject {

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3123a;

        public QueueObject(JStyle1963YHBleApiImpl jStyle1963YHBleApiImpl, BleBaseRequest bleBaseRequest) {
            this.f3123a = bleBaseRequest;
        }
    }

    public JStyle1963YHBleApiImpl() {
        e();
    }

    public static void a(JStyle1963YHBleApiImpl jStyle1963YHBleApiImpl, List list, boolean z, ScanDeviceRequest scanDeviceRequest, ScanResultListener scanResultListener) {
        jStyle1963YHBleApiImpl.getClass();
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

    public static JStyle1963YHBleApiImpl getInstance(Context context) {
        if (r == null) {
            w = context.getApplicationContext();
            r = new JStyle1963YHBleApiImpl();
        }
        if (!a()) {
            LogHelper.d(s, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
            h();
        }
        return r;
    }

    public static void h() {
        try {
            Intent intent = new Intent(w, KH1963DBleService.class);
            w.bindService(intent, x, 1);
            if (Build.VERSION.SDK_INT >= 26) {
                w.startForegroundService(intent);
            } else {
                w.startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, w);
        }
    }

    public final JstyleBaseReq b(BleBaseRequest bleBaseRequest) {
        int type_stop_tel_1963;
        int type_call_1963;
        int[] iArr;
        if (bleBaseRequest instanceof StepsDataRequest) {
            JstyleWalkReq jstyleWalkReq = new JstyleWalkReq();
            jstyleWalkReq.setReqId(bleBaseRequest.getRequId());
            jstyleWalkReq.setDataType(BleConst.GetDetailActivityData);
            jstyleWalkReq.setMode(JStyleConstants.Constants.getMODE_START());
            return jstyleWalkReq;
        } else if (bleBaseRequest instanceof SleepDataRequest) {
            JstyleSleepReq jstyleSleepReq = new JstyleSleepReq();
            jstyleSleepReq.setReqId(bleBaseRequest.getRequId());
            jstyleSleepReq.setDataType(BleConst.GetDetailSleepData);
            jstyleSleepReq.setMode(JStyleConstants.Constants.getMODE_START());
            return jstyleSleepReq;
        } else if (bleBaseRequest instanceof HeartRateDataRequest) {
            JstyleOnceHRReq jstyleOnceHRReq = new JstyleOnceHRReq();
            jstyleOnceHRReq.setReqId(bleBaseRequest.getRequId());
            jstyleOnceHRReq.setDataType(BleConst.GetStaticHR);
            jstyleOnceHRReq.setMode(JStyleConstants.Constants.getMODE_START());
            return jstyleOnceHRReq;
        } else {
            if (bleBaseRequest instanceof DeviceInfoRequest) {
                this.e = null;
                DeviceInfoRequest deviceInfoRequest = (DeviceInfoRequest) bleBaseRequest;
                if (deviceInfoRequest.isMacAddress()) {
                    if (this.e == null) {
                        this.e = new DeviceInfoData();
                    }
                    this.e.setMacAddress(getMacAddress());
                }
                if (deviceInfoRequest.isFwVersion()) {
                    JstyleDeviceVersionReq jstyleDeviceVersionReq = new JstyleDeviceVersionReq();
                    jstyleDeviceVersionReq.setReqId(bleBaseRequest.getRequId());
                    jstyleDeviceVersionReq.setDataType(BleConst.GetDeviceVersion);
                    return jstyleDeviceVersionReq;
                }
            } else if (bleBaseRequest instanceof BatteryLevelRequest) {
                JstyleBatteryLevelReq jstyleBatteryLevelReq = new JstyleBatteryLevelReq();
                jstyleBatteryLevelReq.setReqId(bleBaseRequest.getRequId());
                jstyleBatteryLevelReq.setDataType(BleConst.GetDeviceBatteryLevel);
                return jstyleBatteryLevelReq;
            } else if (bleBaseRequest instanceof LiveRawPPGControlRequest) {
                JStyleRawPPGControlReq jStyleRawPPGControlReq = new JStyleRawPPGControlReq();
                jStyleRawPPGControlReq.setReqId(bleBaseRequest.getRequId());
                jStyleRawPPGControlReq.setDataType(BleConst.SetMusicControl);
                jStyleRawPPGControlReq.setEnable(((LiveRawPPGControlRequest) bleBaseRequest).isEnabled());
                return jStyleRawPPGControlReq;
            } else if (bleBaseRequest instanceof TemperatureDataRequest) {
                JstyleTemperatureReq jstyleTemperatureReq = new JstyleTemperatureReq();
                jstyleTemperatureReq.setReqId(bleBaseRequest.getRequId());
                jstyleTemperatureReq.setDataType(BleConst.Sendmsg);
                jstyleTemperatureReq.setMode(JStyleConstants.Constants.getMODE_START());
                return jstyleTemperatureReq;
            } else if (bleBaseRequest instanceof SetFitnessInfoRequest) {
                JstylePersonalInfoReq jstylePersonalInfoReq = new JstylePersonalInfoReq();
                MyPersonalInfo myPersonalInfo = new MyPersonalInfo();
                SetFitnessInfoRequest setFitnessInfoRequest = (SetFitnessInfoRequest) bleBaseRequest;
                myPersonalInfo.setHeight(setFitnessInfoRequest.getHeight());
                myPersonalInfo.setWeight((int) setFitnessInfoRequest.getWeight());
                myPersonalInfo.setSex(setFitnessInfoRequest.isMale() ? 1 : 0);
                myPersonalInfo.setStepLength((int) setFitnessInfoRequest.getStride());
                jstylePersonalInfoReq.setPersonalInfo(myPersonalInfo);
                jstylePersonalInfoReq.setDataType("3");
                jstylePersonalInfoReq.setReqId(bleBaseRequest.getRequId());
                return jstylePersonalInfoReq;
            } else if (bleBaseRequest instanceof StepsTargetRequest) {
                JstyleStepTargetReq jstyleStepTargetReq = new JstyleStepTargetReq();
                jstyleStepTargetReq.setGoal(((StepsTargetRequest) bleBaseRequest).getTarget());
                jstyleStepTargetReq.setDataType(BleConst.SetStepGoal);
                jstyleStepTargetReq.setReqId(bleBaseRequest.getRequId());
                return jstyleStepTargetReq;
            } else {
                int i = 0;
                if (bleBaseRequest instanceof LiveStepsControlRequest) {
                    LogHelper.d(s, "LiveStepsControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyleRealTimeWalkReq jStyleRealTimeWalkReq = new JStyleRealTimeWalkReq();
                    jStyleRealTimeWalkReq.setStartReal(((LiveStepsControlRequest) bleBaseRequest).isEnable());
                    jStyleRealTimeWalkReq.setIstempEnable(false);
                    jStyleRealTimeWalkReq.setReqId(bleBaseRequest.getRequId());
                    jStyleRealTimeWalkReq.setDataType(BleConst.RealTimeStep);
                    return jStyleRealTimeWalkReq;
                } else if (bleBaseRequest instanceof LiveTemperatureControlRequest) {
                    LogHelper.d(s, "LiveTemperatureControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyleRealTimeWalkReq jStyleRealTimeWalkReq2 = new JStyleRealTimeWalkReq();
                    jStyleRealTimeWalkReq2.setReqId(bleBaseRequest.getRequId());
                    jStyleRealTimeWalkReq2.setDataType(BleConst.RealTimeStep);
                    LiveTemperatureControlRequest liveTemperatureControlRequest = (LiveTemperatureControlRequest) bleBaseRequest;
                    jStyleRealTimeWalkReq2.setStartReal(liveTemperatureControlRequest.isEnabled());
                    jStyleRealTimeWalkReq2.setIstempEnable(liveTemperatureControlRequest.isEnabled());
                    return jStyleRealTimeWalkReq2;
                } else if (bleBaseRequest instanceof LiveHeartRateControlRequest) {
                    LogHelper.d(s, "LiveHeartRateControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyleRealTimeWalkReq jStyleRealTimeWalkReq3 = new JStyleRealTimeWalkReq();
                    jStyleRealTimeWalkReq3.setReqId(bleBaseRequest.getRequId());
                    jStyleRealTimeWalkReq3.setDataType(BleConst.RealTimeStep);
                    LiveHeartRateControlRequest liveHeartRateControlRequest = (LiveHeartRateControlRequest) bleBaseRequest;
                    jStyleRealTimeWalkReq3.setStartReal(liveHeartRateControlRequest.isEnabled());
                    jStyleRealTimeWalkReq3.setIstempEnable(liveHeartRateControlRequest.isEnabled());
                    return jStyleRealTimeWalkReq3;
                } else {
                    char c = 6;
                    char c2 = 4;
                    char c3 = 3;
                    char c4 = 5;
                    char c5 = 2;
                    int i2 = 7;
                    boolean z = true;
                    if (bleBaseRequest instanceof SetReminderRequest) {
                        LogHelper.d(s, "SetReminderRequest", ModuleNames.BLEABSTRACT.getModuleName());
                        JstyleSedentaryReq jstyleSedentaryReq = new JstyleSedentaryReq();
                        MySedentaryReminder mySedentaryReminder = new MySedentaryReminder();
                        SetReminderRequest setReminderRequest = (SetReminderRequest) bleBaseRequest;
                        mySedentaryReminder.setEnable(setReminderRequest.isEnabled());
                        mySedentaryReminder.setStartHour(setReminderRequest.getStartHour1());
                        mySedentaryReminder.setStartMinute(setReminderRequest.getStartMin1());
                        mySedentaryReminder.setEndHour(setReminderRequest.getEndHour1());
                        mySedentaryReminder.setEndMinute(setReminderRequest.getEndMin1());
                        mySedentaryReminder.setIntervalTime(setReminderRequest.getReminderInterval());
                        mySedentaryReminder.setLeastStep(setReminderRequest.getLeastStep());
                        int[] iArr2 = {setReminderRequest.isSundayEnabled() ? 1 : 0, setReminderRequest.isMondayEnabled() ? 1 : 0, setReminderRequest.isTuesdayEnabled() ? 1 : 0, setReminderRequest.isWednesdayEnabled() ? 1 : 0, setReminderRequest.isThursdayEnabled() ? 1 : 0, setReminderRequest.isFridayEnabled() ? 1 : 0, setReminderRequest.isSaturdayEnabled() ? 1 : 0};
                        int i3 = 0;
                        while (i < 7) {
                            if (iArr2[i] == 1) {
                                i3 = (int) (i3 + Math.pow(2.0d, i));
                            }
                            i++;
                        }
                        mySedentaryReminder.setWeek(i3);
                        jstyleSedentaryReq.setSedentaryReminder(mySedentaryReminder);
                        jstyleSedentaryReq.setReqId(bleBaseRequest.getRequId());
                        jstyleSedentaryReq.setDataType(BleConst.SetSedentaryReminder);
                        return jstyleSedentaryReq;
                    } else if (bleBaseRequest instanceof HeartRateTimeIntervalRequest) {
                        JstyleHRTimeIntervalReq jstyleHRTimeIntervalReq = new JstyleHRTimeIntervalReq();
                        MyAutomaticHRMonitoring myAutomaticHRMonitoring = new MyAutomaticHRMonitoring();
                        HeartRateTimeIntervalRequest heartRateTimeIntervalRequest = (HeartRateTimeIntervalRequest) bleBaseRequest;
                        myAutomaticHRMonitoring.setStartHour(heartRateTimeIntervalRequest.getStartHour());
                        myAutomaticHRMonitoring.setStartMinute(heartRateTimeIntervalRequest.getStartMinute());
                        myAutomaticHRMonitoring.setEndHour(heartRateTimeIntervalRequest.getEndHour());
                        myAutomaticHRMonitoring.setEndMinute(heartRateTimeIntervalRequest.getEndMinute());
                        myAutomaticHRMonitoring.setTime(heartRateTimeIntervalRequest.getTimeInterval());
                        int[] iArr3 = {heartRateTimeIntervalRequest.isSundayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isMondayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isTuesdayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isWednesdayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isThursdayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isFridayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isSaturdayEnabled() ? 1 : 0};
                        int i4 = 0;
                        while (i < i2) {
                            if (iArr3[i] == z) {
                                i4 = (int) (i4 + Math.pow(2.0d, i));
                            }
                            i++;
                            i2 = 7;
                            z = true;
                        }
                        myAutomaticHRMonitoring.setWeek(i4);
                        myAutomaticHRMonitoring.setOpen(heartRateTimeIntervalRequest.getOpen());
                        jstyleHRTimeIntervalReq.setMyAutomaticHRMonitoring(myAutomaticHRMonitoring);
                        jstyleHRTimeIntervalReq.setReqId(bleBaseRequest.getRequId());
                        jstyleHRTimeIntervalReq.setDataType(BleConst.GetAutomaticHRMonitoring);
                        return jstyleHRTimeIntervalReq;
                    } else if (bleBaseRequest instanceof TemperatureTimeIntervalRequest) {
                        JstyleHRTimeIntervalReq jstyleHRTimeIntervalReq2 = new JstyleHRTimeIntervalReq();
                        MyAutomaticHRMonitoring myAutomaticHRMonitoring2 = new MyAutomaticHRMonitoring();
                        TemperatureTimeIntervalRequest temperatureTimeIntervalRequest = (TemperatureTimeIntervalRequest) bleBaseRequest;
                        myAutomaticHRMonitoring2.setStartHour(temperatureTimeIntervalRequest.getStartHour());
                        myAutomaticHRMonitoring2.setStartMinute(temperatureTimeIntervalRequest.getStartMinute());
                        myAutomaticHRMonitoring2.setEndHour(temperatureTimeIntervalRequest.getEndHour());
                        myAutomaticHRMonitoring2.setEndMinute(temperatureTimeIntervalRequest.getEndMinute());
                        myAutomaticHRMonitoring2.setTime(temperatureTimeIntervalRequest.getTimeInterval());
                        int[] iArr4 = {temperatureTimeIntervalRequest.isSundayEnabled() ? 1 : 0, temperatureTimeIntervalRequest.isMondayEnabled() ? 1 : 0, temperatureTimeIntervalRequest.isTuesdayEnabled() ? 1 : 0, temperatureTimeIntervalRequest.isWednesdayEnabled() ? 1 : 0, temperatureTimeIntervalRequest.isThursdayEnabled() ? 1 : 0, temperatureTimeIntervalRequest.isFridayEnabled() ? 1 : 0, temperatureTimeIntervalRequest.isSaturdayEnabled() ? 1 : 0};
                        int i5 = 0;
                        while (i < 7) {
                            if (iArr4[i] == 1) {
                                iArr = iArr4;
                                i5 = (int) (i5 + Math.pow(2.0d, i));
                            } else {
                                iArr = iArr4;
                            }
                            i++;
                            iArr4 = iArr;
                        }
                        myAutomaticHRMonitoring2.setWeek(i5);
                        myAutomaticHRMonitoring2.setOpen(temperatureTimeIntervalRequest.getOpen());
                        jstyleHRTimeIntervalReq2.setMyAutomaticHRMonitoring(myAutomaticHRMonitoring2);
                        jstyleHRTimeIntervalReq2.setReqId(bleBaseRequest.getRequId());
                        jstyleHRTimeIntervalReq2.setDataType(BleConst.GetAutomaticHRMonitoring);
                        return jstyleHRTimeIntervalReq2;
                    } else if (bleBaseRequest instanceof SetDeviceParametersRequest) {
                        MyDeviceInfo myDeviceInfo = new MyDeviceInfo();
                        SetDeviceParametersRequest setDeviceParametersRequest = (SetDeviceParametersRequest) bleBaseRequest;
                        myDeviceInfo.setIs12Hour(setDeviceParametersRequest.is12HourFormat());
                        myDeviceInfo.setDistanceUnit(setDeviceParametersRequest.isDistanceUnitinMile());
                        myDeviceInfo.setBright_screen(setDeviceParametersRequest.isLiftWristEnabled());
                        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                        LogHelper.d("myDeviceInfo", "isLeftHand " + setDeviceParametersRequest.isLeftHand(), moduleNames.getModuleName());
                        LogHelper.d("myDeviceInfo", "is12HourFormat " + setDeviceParametersRequest.is12HourFormat(), moduleNames.getModuleName());
                        LogHelper.d("myDeviceInfo", "isDistanceUnitinMile " + setDeviceParametersRequest.isDistanceUnitinMile(), moduleNames.getModuleName());
                        LogHelper.d("myDeviceInfo", "isLiftWristEnabled " + setDeviceParametersRequest.isLiftWristEnabled(), moduleNames.getModuleName());
                        JstyleDeviceBaseParametersReq jstyleDeviceBaseParametersReq = new JstyleDeviceBaseParametersReq();
                        jstyleDeviceBaseParametersReq.setDeviceBaseParameter(myDeviceInfo);
                        jstyleDeviceBaseParametersReq.setReqId(bleBaseRequest.getRequId());
                        jstyleDeviceBaseParametersReq.setDataType(BleConst.SetDeviceInfo);
                        return jstyleDeviceBaseParametersReq;
                    } else if (bleBaseRequest instanceof SetHourFormatRequest) {
                        JstyleTimeFormatReq jstyleTimeFormatReq = new JstyleTimeFormatReq();
                        jstyleTimeFormatReq.set12HourFormat(((SetHourFormatRequest) bleBaseRequest).is12HourFormat());
                        jstyleTimeFormatReq.setDataType(JStyleConstants.HOUR_FORMAT);
                        jstyleTimeFormatReq.setReqId(bleBaseRequest.getRequId());
                        return jstyleTimeFormatReq;
                    } else if (bleBaseRequest instanceof SetLiftWristRequest) {
                        JstyleLiftWristReq jstyleLiftWristReq = new JstyleLiftWristReq();
                        jstyleLiftWristReq.setLiftWristEnabled(((SetLiftWristRequest) bleBaseRequest).isLiftWristEnabled());
                        jstyleLiftWristReq.setDataType(JStyleConstants.LIFT_WRIST);
                        jstyleLiftWristReq.setReqId(bleBaseRequest.getRequId());
                        return jstyleLiftWristReq;
                    } else if (bleBaseRequest instanceof SetDistanceUnitRequest) {
                        JstyleDistanceUnitReq jstyleDistanceUnitReq = new JstyleDistanceUnitReq();
                        jstyleDistanceUnitReq.setDistanceUnitInMile(((SetDistanceUnitRequest) bleBaseRequest).isDistanceUnitinMile());
                        jstyleDistanceUnitReq.setDataType(JStyleConstants.DISTANCE_UNIT);
                        jstyleDistanceUnitReq.setReqId(bleBaseRequest.getRequId());
                        return jstyleDistanceUnitReq;
                    } else if (bleBaseRequest instanceof SetMessageContentRequest) {
                        SetMessageContentRequest setMessageContentRequest = (SetMessageContentRequest) bleBaseRequest;
                        NotificationType appNotificationType = setMessageContentRequest.getAppNotificationType();
                        String str = s;
                        ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
                        LogHelper.d(str, "appNotificationType from App" + appNotificationType, moduleNames2.getModuleName());
                        int ordinal = appNotificationType.ordinal();
                        if (ordinal == 0) {
                            type_call_1963 = JStyleConstants.Constants.getTYPE_CALL_1963();
                        } else if (ordinal == 2) {
                            type_call_1963 = JStyleConstants.Constants.getTYPE_SMS_1963();
                        } else if (ordinal != 10) {
                            switch (ordinal) {
                                case 4:
                                    type_call_1963 = JStyleConstants.Constants.getTYPE_WHATSAPP_1963();
                                    break;
                                case 5:
                                    type_call_1963 = JStyleConstants.Constants.getTYPE_WECHAT_1963();
                                    break;
                                case 6:
                                    type_call_1963 = JStyleConstants.Constants.getTYPE_FACEBOOK_1963();
                                    break;
                                case 7:
                                    type_call_1963 = JStyleConstants.Constants.getTYPE_INSTAGRAM_1963();
                                    break;
                                case 8:
                                    type_call_1963 = JStyleConstants.Constants.getTYPE_TWITTER_1963();
                                    break;
                                default:
                                    switch (ordinal) {
                                        case 14:
                                            type_call_1963 = JStyleConstants.Constants.getTYPE_SKYPE_1963();
                                            break;
                                        case 15:
                                            type_call_1963 = JStyleConstants.Constants.getTYPE_LINKEDIN_1983();
                                            break;
                                        case 16:
                                            type_call_1963 = JStyleConstants.Constants.getTYPE_VKCLIENT_1963();
                                            break;
                                        case 17:
                                            type_call_1963 = JStyleConstants.Constants.getTYPE_TELEGRAM_1963();
                                            break;
                                        default:
                                            type_call_1963 = -1;
                                            break;
                                    }
                            }
                        } else {
                            type_call_1963 = JStyleConstants.Constants.getTYPE_QQ_1963();
                        }
                        if (type_call_1963 != -1) {
                            String str2 = setMessageContentRequest.message;
                            Notifier notifier = new Notifier();
                            notifier.setInfo(str2);
                            notifier.setType(type_call_1963);
                            LogHelper.d(str, "Notification type " + type_call_1963 + " content " + str2 + "", moduleNames2.getModuleName());
                            JstyleNotificationReq jstyleNotificationReq = new JstyleNotificationReq();
                            jstyleNotificationReq.setNotifier(notifier);
                            jstyleNotificationReq.setDataType(JStyleConstants.Notification);
                            jstyleNotificationReq.setReqId(bleBaseRequest.getRequId());
                            return jstyleNotificationReq;
                        }
                        return null;
                    } else if (bleBaseRequest instanceof StopMessageNotificationRequest) {
                        if (((StopMessageNotificationRequest) bleBaseRequest).getAppNotificationType().ordinal() != 0) {
                            type_stop_tel_1963 = JStyleConstants.Constants.getTYPE_STOP_TEL_1963();
                        } else {
                            type_stop_tel_1963 = JStyleConstants.Constants.getTYPE_STOP_TEL_1963();
                        }
                        Notifier notifier2 = new Notifier();
                        notifier2.setType(type_stop_tel_1963);
                        JstyleNotificationReq jstyleNotificationReq2 = new JstyleNotificationReq();
                        jstyleNotificationReq2.setNotifier(notifier2);
                        jstyleNotificationReq2.setDataType(JStyleConstants.Notification);
                        jstyleNotificationReq2.setReqId(bleBaseRequest.getRequId());
                        return jstyleNotificationReq2;
                    } else if (bleBaseRequest instanceof OTAModeRequest) {
                        JstyleEnterOTAReq jstyleEnterOTAReq = new JstyleEnterOTAReq();
                        jstyleEnterOTAReq.setReqId(bleBaseRequest.getRequId());
                        jstyleEnterOTAReq.setDataType(JStyleConstants.EnterOTA);
                        return jstyleEnterOTAReq;
                    } else if (bleBaseRequest instanceof MotorVibrationRequest) {
                        JstyleMotorVibrationReq jstyleMotorVibrationReq = new JstyleMotorVibrationReq();
                        jstyleMotorVibrationReq.setReqId(bleBaseRequest.getRequId());
                        jstyleMotorVibrationReq.setDataType(BleConst.SetMotorVibrationWithTimes);
                        jstyleMotorVibrationReq.setTimes(((MotorVibrationRequest) bleBaseRequest).getTimes());
                        return jstyleMotorVibrationReq;
                    } else if (bleBaseRequest instanceof FindMyWatchRequest) {
                        JstyleMotorVibrationReq jstyleMotorVibrationReq2 = new JstyleMotorVibrationReq();
                        jstyleMotorVibrationReq2.setReqId(bleBaseRequest.getRequId());
                        jstyleMotorVibrationReq2.setDataType(BleConst.SetMotorVibrationWithTimes);
                        jstyleMotorVibrationReq2.setTimes(5);
                        return jstyleMotorVibrationReq2;
                    } else if (bleBaseRequest instanceof SetVibrationAlarmListRequest) {
                        JstyleVibrationAlarmReq jstyleVibrationAlarmReq = new JstyleVibrationAlarmReq();
                        ArrayList arrayList = new ArrayList();
                        int i6 = 0;
                        while (true) {
                            SetVibrationAlarmListRequest setVibrationAlarmListRequest = (SetVibrationAlarmListRequest) bleBaseRequest;
                            if (i6 < setVibrationAlarmListRequest.getVibrationAlarmRequests().size()) {
                                SetVibrationAlarmRequest setVibrationAlarmRequest = setVibrationAlarmListRequest.getVibrationAlarmRequests().get(i6);
                                Clock clock = new Clock();
                                clock.setContent(setVibrationAlarmRequest.getEventName());
                                clock.setEnable(setVibrationAlarmRequest.isEnabled());
                                clock.setHour(setVibrationAlarmRequest.getHour());
                                clock.setMinute(setVibrationAlarmRequest.getMinute());
                                clock.setNumber(setVibrationAlarmRequest.getAlarmId());
                                int[] iArr5 = new int[7];
                                iArr5[i] = setVibrationAlarmRequest.isSundayEnabled() ? 1 : 0;
                                iArr5[1] = setVibrationAlarmRequest.isMondayEnabled() ? 1 : 0;
                                iArr5[c5] = setVibrationAlarmRequest.isTuesdayEnabled() ? 1 : 0;
                                iArr5[c3] = setVibrationAlarmRequest.isWednesdayEnabled() ? 1 : 0;
                                iArr5[c2] = setVibrationAlarmRequest.isThursdayEnabled() ? 1 : 0;
                                iArr5[c4] = setVibrationAlarmRequest.isFridayEnabled() ? 1 : 0;
                                iArr5[c] = setVibrationAlarmRequest.isSaturdayEnabled() ? 1 : 0;
                                int i7 = i;
                                int i8 = i7;
                                for (int i9 = 7; i7 < i9; i9 = 7) {
                                    if (iArr5[i7] == 1) {
                                        i8 = (int) (i8 + Math.pow(2.0d, i7));
                                    }
                                    i7++;
                                }
                                clock.setType(setVibrationAlarmRequest.getAlarmType());
                                clock.setWeek((byte) i8);
                                arrayList.add(clock);
                                i6++;
                                c = 6;
                                i = 0;
                                c2 = 4;
                                c3 = 3;
                                c4 = 5;
                                c5 = 2;
                            } else {
                                jstyleVibrationAlarmReq.setClockList(arrayList);
                                jstyleVibrationAlarmReq.setReqId(bleBaseRequest.getRequId());
                                jstyleVibrationAlarmReq.setDataType(BleConst.GetAlarmClock);
                                return jstyleVibrationAlarmReq;
                            }
                        }
                    } else if (bleBaseRequest instanceof ActivityModeSummaryRequest) {
                        JstyleActivityModeHistoryReq jstyleActivityModeHistoryReq = new JstyleActivityModeHistoryReq();
                        jstyleActivityModeHistoryReq.setReqId(bleBaseRequest.getRequId());
                        jstyleActivityModeHistoryReq.setDataType(BleConst.GetActivityModeData);
                        jstyleActivityModeHistoryReq.setMode(JStyleConstants.Constants.getMODE_START());
                        return jstyleActivityModeHistoryReq;
                    } else if (bleBaseRequest instanceof SetTemperatureUnitRequest) {
                        JstyleTemperatureUnitReq jstyleTemperatureUnitReq = new JstyleTemperatureUnitReq();
                        jstyleTemperatureUnitReq.setTemperatureInCelsius(!((SetTemperatureUnitRequest) bleBaseRequest).isTemperatureInCelsius());
                        jstyleTemperatureUnitReq.setReqId(bleBaseRequest.getRequId());
                        jstyleTemperatureUnitReq.setDataType(JStyleConstants.TempUnit);
                        return jstyleTemperatureUnitReq;
                    } else if (bleBaseRequest instanceof TemperatureCalibrationDataRequest) {
                        TemperatureCalibrationDataRequest temperatureCalibrationDataRequest = (TemperatureCalibrationDataRequest) bleBaseRequest;
                        int temperature = (int) (temperatureCalibrationDataRequest.getTemperature() * 10.0d);
                        if (temperatureCalibrationDataRequest.getTempCalculatingSign() == 2) {
                            temperature *= -1;
                        }
                        JstyleTemperatureCalibrationReq jstyleTemperatureCalibrationReq = new JstyleTemperatureCalibrationReq();
                        jstyleTemperatureCalibrationReq.setTempValue(temperature);
                        jstyleTemperatureCalibrationReq.setReqId(bleBaseRequest.getRequId());
                        jstyleTemperatureCalibrationReq.setDataType("TemperatureCorrectionValue");
                        return jstyleTemperatureCalibrationReq;
                    } else if (bleBaseRequest instanceof ReadManualBpRequest) {
                        JstyleHrvReq jstyleHrvReq = new JstyleHrvReq();
                        jstyleHrvReq.setReqId(bleBaseRequest.getRequId());
                        jstyleHrvReq.setDataType("42");
                        jstyleHrvReq.setMode(JStyleConstants.Constants.getMODE_START());
                        return jstyleHrvReq;
                    } else if (bleBaseRequest instanceof ReadManualSpo2Request) {
                        JstyleSpo2Req jstyleSpo2Req = new JstyleSpo2Req();
                        jstyleSpo2Req.setReqId(bleBaseRequest.getRequId());
                        jstyleSpo2Req.setDataType("55");
                        jstyleSpo2Req.setMode(JStyleConstants.Constants.getMODE_START());
                        return jstyleSpo2Req;
                    } else if (bleBaseRequest instanceof CustomWatchFaceRequest) {
                        JstyleCustomWatchFaceReq jstyleCustomWatchFaceReq = new JstyleCustomWatchFaceReq();
                        jstyleCustomWatchFaceReq.setWatchFacePosition(((CustomWatchFaceRequest) bleBaseRequest).getWatchFacePosition());
                        jstyleCustomWatchFaceReq.setReqId(bleBaseRequest.getRequId());
                        jstyleCustomWatchFaceReq.setDataType(JStyleConstants.CUSTOM_WATCH_FACE);
                        return jstyleCustomWatchFaceReq;
                    } else if (bleBaseRequest instanceof CustomWatchFaceFileImageRequest) {
                        JstyleCustomWatchFaceReq jstyleCustomWatchFaceReq2 = new JstyleCustomWatchFaceReq();
                        CustomWatchFaceFileImageRequest customWatchFaceFileImageRequest = (CustomWatchFaceFileImageRequest) bleBaseRequest;
                        jstyleCustomWatchFaceReq2.setWatchFacePosition(customWatchFaceFileImageRequest.getWatchFacePosition());
                        jstyleCustomWatchFaceReq2.setBitmap(customWatchFaceFileImageRequest.getBitmap());
                        jstyleCustomWatchFaceReq2.setReqId(bleBaseRequest.getRequId());
                        jstyleCustomWatchFaceReq2.setDataType("DIY");
                        return jstyleCustomWatchFaceReq2;
                    } else if (bleBaseRequest instanceof ChangeWatchFaceRequest) {
                        JstyleChangeWatchFaceReq jstyleChangeWatchFaceReq = new JstyleChangeWatchFaceReq();
                        jstyleChangeWatchFaceReq.setWatchFacePosition(((ChangeWatchFaceRequest) bleBaseRequest).getWatchFacePosition());
                        jstyleChangeWatchFaceReq.setReqId(bleBaseRequest.getRequId());
                        jstyleChangeWatchFaceReq.setDataType(BleConst.ReadHeartateSensorstatus);
                        return jstyleChangeWatchFaceReq;
                    } else if (bleBaseRequest instanceof GetWatchFacePositionRequest) {
                        JstyleWatchFacePositionReq jstyleWatchFacePositionReq = new JstyleWatchFacePositionReq();
                        jstyleWatchFacePositionReq.setReqId(bleBaseRequest.getRequId());
                        jstyleWatchFacePositionReq.setDataType(JStyleConstants.WATCH_FACE_POSITION);
                        return jstyleWatchFacePositionReq;
                    } else if (bleBaseRequest instanceof SessionHeartRateRequest) {
                        JstyleHistoryHRReq jstyleHistoryHRReq = new JstyleHistoryHRReq();
                        jstyleHistoryHRReq.setReqId(bleBaseRequest.getRequId());
                        jstyleHistoryHRReq.setDataType(BleConst.GetDynamicHR);
                        jstyleHistoryHRReq.setMode(JStyleConstants.Constants.getMODE_START());
                        return jstyleHistoryHRReq;
                    } else if (bleBaseRequest instanceof SessionStepsDataRequest) {
                        JstyleWalkReq jstyleWalkReq2 = new JstyleWalkReq();
                        jstyleWalkReq2.setReqId(bleBaseRequest.getRequId());
                        jstyleWalkReq2.setDataType(JStyleConstants.SESSION_STEPS_DATA);
                        jstyleWalkReq2.setMode(JStyleConstants.Constants.getMODE_START());
                        return jstyleWalkReq2;
                    } else if (bleBaseRequest instanceof ActivityModeWithSamplesRequest) {
                        if (b()) {
                            getData(new SessionStepsDataRequest(), (DataResultListener) bleBaseRequest.getResponseListener());
                        }
                        getData(new SessionHeartRateRequest(), (DataResultListener) bleBaseRequest.getResponseListener());
                        getData(new ActivityModeSummaryRequest.Builder().build(), (DataResultListener) bleBaseRequest.getResponseListener());
                    } else if (bleBaseRequest instanceof GetDeviceSettingsInfoRequest) {
                        JstyleGetDeviceInfoReq jstyleGetDeviceInfoReq = new JstyleGetDeviceInfoReq();
                        jstyleGetDeviceInfoReq.setReqId(bleBaseRequest.getRequId());
                        jstyleGetDeviceInfoReq.setDataType(BleConst.GetDeviceInfo);
                        return jstyleGetDeviceInfoReq;
                    }
                }
            }
            return null;
        }
    }

    public final void c() {
        if (DeviceScanManager.getInstance(w).isScanningInProgress()) {
            DeviceScanManager.getInstance(w).stopScan();
        }
        t = null;
        unbindService();
        Handler handler = this.b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.c;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.m;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return false;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void cleanUpCommands() {
        setUserSettings(new LiveStepsControlRequest.Builder(false).build(), new SettingsResultListener() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.28
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(BleBaseError bleBaseError) {
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
                final JStyle1963YHBleApiImpl jStyle1963YHBleApiImpl = JStyle1963YHBleApiImpl.this;
                JStyle1963YHBleApiImpl jStyle1963YHBleApiImpl2 = JStyle1963YHBleApiImpl.r;
                jStyle1963YHBleApiImpl.getClass();
                jStyle1963YHBleApiImpl.setUserSettings(new LiveHeartRateControlRequest.Builder(false).build(), new SettingsResultListener() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.29
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(BleBaseError bleBaseError) {
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse2) {
                        JStyle1963YHBleApiImpl jStyle1963YHBleApiImpl3 = JStyle1963YHBleApiImpl.this;
                        JStyle1963YHBleApiImpl jStyle1963YHBleApiImpl4 = JStyle1963YHBleApiImpl.r;
                        jStyle1963YHBleApiImpl3.getClass();
                        jStyle1963YHBleApiImpl3.setUserSettings(new LiveTemperatureControlRequest.Builder(false).build(), new SettingsResultListener(jStyle1963YHBleApiImpl3) { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.30
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
        synchronized (u) {
            if (u != null && u.size() > 0) {
                u.clear();
            }
            KH1963DBleService kH1963DBleService = t;
            if (kH1963DBleService != null) {
                kH1963DBleService.clearQueue();
            }
        }
        Handler handler = this.m;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.n = null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(final ConnectRequest connectRequest, final ConnectionResultListener connectionResultListener) {
        this.b.removeCallbacksAndMessages(null);
        this.o = connectionResultListener;
        this.b.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.5
            @Override // java.lang.Runnable
            public void run() {
                KH1963DBleService kH1963DBleService = JStyle1963YHBleApiImpl.t;
                if (kH1963DBleService == null || connectRequest == null) {
                    if (kH1963DBleService == null) {
                        JStyle1963YHBleApiImpl.h();
                        connectionResultListener.onError(new Error(Type.SERVICE_NOT_RUNNING, JStyle1963YHBleApiImpl.w.getString(R.string.service_not_running)));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.COMMAND_REQUEST_ERROR, JStyle1963YHBleApiImpl.w.getString(R.string.request_should_not_null)));
                    return;
                }
                JStyle1963YHBleApiImpl.v = JStyle1963YHBleApiImpl.this.getMacAddress();
                Clove1963DBleState.BleState connectionState = JStyle1963YHBleApiImpl.t.getConnectionState();
                Clove1963DBleState.BleState bleState = Clove1963DBleState.BleState.CONNECTED;
                if (connectionState == bleState && JStyle1963YHBleApiImpl.v.equalsIgnoreCase(connectRequest.getMacAddress())) {
                    connectionResultListener.onConnectionResponse(ConnectionStatus.CONNECTED);
                } else if (JStyle1963YHBleApiImpl.t.getConnectionState() == Clove1963DBleState.BleState.DISCONNECTED) {
                    BleBaseRequest bleBaseRequest = new BleBaseRequest();
                    bleBaseRequest.setBleCommand(BleCommand.CONNECT);
                    bleBaseRequest.setResponseListener(connectionResultListener);
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    JStyle1963YHBleApiImpl.t.connect(connectRequest.getMacAddress());
                } else if (JStyle1963YHBleApiImpl.t.getConnectionState() == bleState) {
                    KH1963DBleService kH1963DBleService2 = JStyle1963YHBleApiImpl.t;
                    if (kH1963DBleService2.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, "Connected to band " + JStyle1963YHBleApiImpl.t.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, kH1963DBleService2.getConnectionState().getStateAsString()));
                } else if (JStyle1963YHBleApiImpl.t.getConnectionState() == Clove1963DBleState.BleState.CONNECTING) {
                    KH1963DBleService kH1963DBleService3 = JStyle1963YHBleApiImpl.t;
                    if (kH1963DBleService3.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, "Connection in progress " + JStyle1963YHBleApiImpl.t.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, kH1963DBleService3.getConnectionState().getStateAsString()));
                } else {
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, JStyle1963YHBleApiImpl.t.getConnectionState().getStateAsString()));
                }
            }
        });
    }

    public final void d() {
        if (u == null || u.size() <= 0) {
            return;
        }
        JstyleBaseReq b = b(u.get(0).f3123a);
        if (b != null) {
            BleBaseRequest bleBaseRequest = this.n;
            if (bleBaseRequest == null) {
                b(b);
                return;
            } else if (bleBaseRequest.isComplete()) {
                b(b);
                return;
            } else {
                return;
            }
        }
        c(u.get(0).f3123a);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(ConnectionResultListener connectionResultListener) {
        KH1963DBleService kH1963DBleService = t;
        if (kH1963DBleService != null) {
            kH1963DBleService.disconnectAndForget();
            t.clearQueue();
            c();
            if (connectionResultListener != null) {
                connectionResultListener.onConnectionResponse(ConnectionStatus.DISCONNECTED);
                return;
            }
            return;
        }
        connectionResultListener.onError(new Error(Type.SERVICE_NOT_RUNNING, "service is not present"));
    }

    public final void e() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.2
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().register(JStyle1963YHBleApiImpl.this);
            }
        });
    }

    public final void f() {
        BleBaseRequest bleBaseRequest = this.n;
        if (bleBaseRequest != null) {
            bleBaseRequest.setComplete(true);
        }
        d();
    }

    public final void g() {
        KHJstyleEntityDeviceInfo deviceInfoBy = KHJstyleDeviceInfoRepository.getInstance(w).getDeviceInfoBy(v);
        if (deviceInfoBy == null) {
            deviceInfoBy = new KHJstyleEntityDeviceInfo();
            deviceInfoBy.setMacAddress(v);
        }
        deviceInfoBy.setLastSyncWalkTimeStamp(System.currentTimeMillis());
        KHJstyleDeviceInfoRepository.getInstance(w).insertDeviceInfo(deviceInfoBy);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return this.p;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        if (t != null) {
            return new ConnectionInfo(getConnectionStatus(), t.getConnectionError(), t.getConnectionStageChangeTimeStamp());
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public ConnectionStatus getConnectionStatus() {
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        KH1963DBleService kH1963DBleService = t;
        if (kH1963DBleService != null) {
            if (kH1963DBleService.getConnectionState() == Clove1963DBleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            return t.getConnectionState() == Clove1963DBleState.BleState.CONNECTING ? ConnectionStatus.CONNECTING : connectionStatus;
        }
        return connectionStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        KH1963DBleService kH1963DBleService = t;
        if (kH1963DBleService == null || kH1963DBleService.getConnectionState() != Clove1963DBleState.BleState.CONNECTED) {
            dataResultListener.onDataError(new BleBaseError(w.getString(R.string.band_not_connected)));
        } else if (bleBaseRequest == null || dataResultListener == null) {
            if (dataResultListener != null) {
                Context context = w;
                int i = R.string.request_cannot_null;
                BleBaseError bleBaseError = new BleBaseError(context.getString(i));
                bleBaseError.setErrorMsg(w.getString(i));
                dataResultListener.onDataError(bleBaseError);
            }
        } else {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            bleBaseRequest.setResponseListener(dataResultListener);
            a(bleBaseRequest);
            if (u == null || u.size() <= 0) {
                return;
            }
            d();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        deviceSupportedFeatures.setStepsSupported(true);
        deviceSupportedFeatures.setSleepSupported(true);
        deviceSupportedFeatures.setHeartRateSupported(true);
        deviceSupportedFeatures.setTemparatureHistorySupported(true);
        deviceSupportedFeatures.setPersonalInfoSupported(true);
        deviceSupportedFeatures.setStepGoalSupported(true);
        deviceSupportedFeatures.setCallNotificationSupported(true);
        deviceSupportedFeatures.setSmsSupported(true);
        deviceSupportedFeatures.setMessageReadSupported(true);
        deviceSupportedFeatures.setSocialNotificationSupported(true);
        deviceSupportedFeatures.setHandSettingsSupported(false);
        deviceSupportedFeatures.setPhoneFinderSupported(true);
        deviceSupportedFeatures.setLiveStepsSupported(true);
        deviceSupportedFeatures.setLiveHeartRateSupported(true);
        deviceSupportedFeatures.setLiveBPSupported(false);
        deviceSupportedFeatures.setHandPreferenceSettingsSupported(false);
        deviceSupportedFeatures.setTimeFormatSettingsSupported(true);
        deviceSupportedFeatures.setDistanceUnitSettingsSupported(true);
        deviceSupportedFeatures.setLiftWristToViewSettingsSupported(true);
        deviceSupportedFeatures.setTemperatureUnitSettingsSupported(true);
        deviceSupportedFeatures.setManualBpSupported(true);
        deviceSupportedFeatures.setManualSpo2SupportedOnBand(true);
        deviceSupportedFeatures.setProbeFeatureSupported(false);
        deviceSupportedFeatures.setAutoHrSettingsSupported(true);
        deviceSupportedFeatures.setMultipleAlarmsSupportedAtATime(true);
        deviceSupportedFeatures.setOnceAlarmSupported(false);
        deviceSupportedFeatures.setSportsModeHistorySupported(true);
        deviceSupportedFeatures.setSampleDataSupportedInSportMode(true);
        deviceSupportedFeatures.setSyncBandSettingsSupported(true);
        deviceSupportedFeatures.setSportModeSupportedFromApp(false);
        deviceSupportedFeatures.setDeviceSettingsSupportedInOneCommand(false);
        deviceSupportedFeatures.setDynamicHRMergeSupported(true);
        deviceSupportedFeatures.setMaxCharSupportedInNotification(60);
        deviceSupportedFeatures.setDndSupported(false);
        return deviceSupportedFeatures;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManager1963D.getInstance(w).getConnectedDeviceMacAddress();
        v = connectedDeviceMacAddress;
        return connectedDeviceMacAddress;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return BleApiUtils.isServiceRunning(KH1963DBleService.class.getName(), w);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    @com.squareup.otto.Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onConnectionStateChanged(com.coveiot.android.bleabstract.models.Clove1963DBleState r3) {
        /*
            r2 = this;
            com.coveiot.android.bleabstract.models.ConnectionStatus r0 = com.coveiot.android.bleabstract.models.ConnectionStatus.DISCONNECTED
            if (r3 == 0) goto L18
            com.coveiot.android.bleabstract.models.Clove1963DBleState$BleState r3 = r3.getmState()
            int r3 = r3.ordinal()
            if (r3 == 0) goto L15
            r1 = 1
            if (r3 == r1) goto L12
            goto L18
        L12:
            com.coveiot.android.bleabstract.models.ConnectionStatus r3 = com.coveiot.android.bleabstract.models.ConnectionStatus.CONNECTING
            goto L19
        L15:
            com.coveiot.android.bleabstract.models.ConnectionStatus r3 = com.coveiot.android.bleabstract.models.ConnectionStatus.CONNECTED
            goto L19
        L18:
            r3 = r0
        L19:
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r2.d
            if (r1 == 0) goto L25
            r1.setValue(r3)
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r2.d
            r1.postValue(r3)
        L25:
            com.coveiot.android.bleabstract.listeners.ConnectionResultListener r1 = r2.o
            if (r1 == 0) goto L2c
            r1.onConnectionResponse(r3)
        L2c:
            if (r3 != r0) goto L3a
            com.coveiot.android.jstyle1963dsdk.error.JstyleError r3 = new com.coveiot.android.jstyle1963dsdk.error.JstyleError
            com.coveiot.android.jstyle1963dsdk.error.JstyleErrorType r0 = com.coveiot.android.jstyle1963dsdk.error.JstyleErrorType.DEVICE_NOT_CONNECTED
            java.lang.String r1 = "Device disconnected"
            r3.<init>(r0, r1)
            r2.a(r3)
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.onConnectionStateChanged(com.coveiot.android.bleabstract.models.Clove1963DBleState):void");
    }

    public void onFailure(JstyleError jstyleError) {
        if (jstyleError != null) {
            int i = AnonymousClass33.f3114a[jstyleError.getErrorType().ordinal()];
            if (i == 1 || i == 2) {
                a(jstyleError);
            }
        }
    }

    @Subscribe
    public void onLiveResponseReceived(JstyleLiveResponse jstyleLiveResponse) {
        if (jstyleLiveResponse == null || jstyleLiveResponse.getDataType() == null) {
            return;
        }
        String dataType = jstyleLiveResponse.getDataType();
        dataType.hashCode();
        char c = 65535;
        switch (dataType.hashCode()) {
            case -315311201:
                if (dataType.equals("RejectTelMode")) {
                    c = 0;
                    break;
                }
                break;
            case 52:
                if (dataType.equals(BleConst.GetDeviceInfo)) {
                    c = 1;
                    break;
                }
                break;
            case 1601:
                if (dataType.equals(BleConst.RealTimeStep)) {
                    c = 2;
                    break;
                }
                break;
            case 1726:
                if (dataType.equals(BleConst.SetMusicControl)) {
                    c = 3;
                    break;
                }
                break;
            case 344980948:
                if (dataType.equals(JStyleConstants.CUSTOM_WATCH_FACE_UPLOAD_PERCENTAGE)) {
                    c = 4;
                    break;
                }
                break;
            case 2049043094:
                if (dataType.equals("FindMobilePhoneMode")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                CallRejectRes callRejectRes = new CallRejectRes(true);
                callRejectRes.shouldReject = true;
                Intent intent = new Intent(Constants.CALL_REJECT_BROADCAST_INTENT);
                intent.putExtra(Constants.CALL_REJECT__BROADCAST_INTENT_EXTRA, callRejectRes);
                LocalBroadcastManager.getInstance(w).sendBroadcast(intent);
                return;
            case 1:
                if (jstyleLiveResponse.getObj() != null) {
                    DeviceSettingsInfoResponse convertToDeviceSettingsData = new JStyle1963DFormatter(getMacAddress()).convertToDeviceSettingsData((Map) jstyleLiveResponse.getObj());
                    if (convertToDeviceSettingsData != null) {
                        Intent intent2 = new Intent(Constants.DEVICE_SETTINGS_BROADCAST_INTENT);
                        intent2.putExtra(Constants.DEVICE_SETTINGS_BROADCAST_INTENT_EXTRA, convertToDeviceSettingsData);
                        LocalBroadcastManager.getInstance(w).sendBroadcast(intent2);
                        return;
                    }
                    return;
                }
                return;
            case 2:
                if (jstyleLiveResponse.getObj() != null) {
                    Map<String, String> map = (Map) jstyleLiveResponse.getObj();
                    LiveStepsData convertToLiveStepsData = new JStyle1963DFormatter(getMacAddress()).convertToLiveStepsData(map);
                    MutableLiveData<LiveStepsData> mutableLiveData = this.g;
                    if (mutableLiveData != null) {
                        mutableLiveData.setValue(convertToLiveStepsData);
                        this.g.postValue(convertToLiveStepsData);
                    }
                    LiveHealthData convertToLiveHealthData = new JStyle1963DFormatter(getMacAddress()).convertToLiveHealthData(map);
                    MutableLiveData<LiveHealthData> mutableLiveData2 = this.f;
                    if (mutableLiveData2 != null) {
                        mutableLiveData2.setValue(convertToLiveHealthData);
                        this.f.postValue(convertToLiveHealthData);
                    }
                    LiveTemperatureData convertToLiveTemperatureData = new JStyle1963DFormatter(getMacAddress()).convertToLiveTemperatureData(map);
                    MutableLiveData<LiveTemperatureData> mutableLiveData3 = this.i;
                    if (mutableLiveData3 != null) {
                        mutableLiveData3.setValue(convertToLiveTemperatureData);
                        this.i.postValue(convertToLiveTemperatureData);
                        return;
                    }
                    return;
                }
                return;
            case 3:
                RawPPGData rawPPGData = (RawPPGData) jstyleLiveResponse.getObj();
                if (this.l.size() < 10) {
                    this.l.add(Integer.valueOf(rawPPGData.getData()));
                    return;
                }
                PPGData pPGData = new PPGData((ArrayList) this.l.clone());
                MutableLiveData<PPGData> mutableLiveData4 = this.f3090a;
                if (mutableLiveData4 != null) {
                    mutableLiveData4.setValue(pPGData);
                }
                this.l.clear();
                return;
            case 4:
                if (jstyleLiveResponse.getObj() != null) {
                    LiveWatchFaceUploadPercentage liveWatchFaceUploadPercentage = new LiveWatchFaceUploadPercentage();
                    liveWatchFaceUploadPercentage.setPercentage(Integer.parseInt(jstyleLiveResponse.getObj().toString()));
                    MutableLiveData<LiveWatchFaceUploadPercentage> mutableLiveData5 = this.j;
                    if (mutableLiveData5 != null) {
                        mutableLiveData5.setValue(liveWatchFaceUploadPercentage);
                        this.j.postValue(liveWatchFaceUploadPercentage);
                        return;
                    }
                    return;
                }
                return;
            case 5:
                FindMyPhoneRes findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.ON);
                Intent intent3 = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
                intent3.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes);
                LocalBroadcastManager.getInstance(w).sendBroadcast(intent3);
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x026a A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02f7 A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x038f A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x039b A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0405 A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x04b4 A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0517  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x054c A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0558 A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x05bb  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x05f0 A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x05fc A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:218:0x063c A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0698 A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:243:0x06fb  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x073f A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:366:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0103 A[Catch: Exception -> 0x0889, TRY_ENTER, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0150 A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01be A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x020b A[Catch: Exception -> 0x0889, TryCatch #0 {Exception -> 0x0889, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002d, B:28:0x006a, B:73:0x0103, B:75:0x0107, B:76:0x010a, B:77:0x0150, B:79:0x0154, B:80:0x0157, B:82:0x0183, B:84:0x0189, B:86:0x0191, B:88:0x0197, B:90:0x019d, B:91:0x01b2, B:92:0x01be, B:94:0x01c2, B:95:0x01c5, B:96:0x020b, B:98:0x020f, B:99:0x0212, B:100:0x026a, B:102:0x026e, B:103:0x0271, B:105:0x029d, B:108:0x02a4, B:109:0x02ad, B:111:0x02b3, B:113:0x02c0, B:115:0x02c7, B:114:0x02c4, B:116:0x02dc, B:117:0x02e1, B:119:0x02f7, B:121:0x02fb, B:122:0x02fe, B:124:0x032a, B:127:0x0331, B:129:0x0339, B:134:0x035b, B:136:0x0361, B:138:0x036e, B:140:0x0375, B:139:0x0372, B:141:0x038a, B:142:0x038f, B:131:0x033f, B:143:0x039b, B:145:0x039f, B:146:0x03a2, B:150:0x03e0, B:151:0x0405, B:153:0x0409, B:154:0x040c, B:156:0x0432, B:157:0x0439, B:159:0x0471, B:160:0x047a, B:161:0x04b4, B:163:0x04b8, B:164:0x04bb, B:166:0x04e7, B:169:0x04ee, B:171:0x04f6, B:176:0x0518, B:178:0x051e, B:180:0x052b, B:182:0x0532, B:181:0x052f, B:183:0x0547, B:184:0x054c, B:173:0x04fc, B:185:0x0558, B:187:0x055c, B:188:0x055f, B:190:0x058b, B:193:0x0592, B:195:0x059a, B:200:0x05bc, B:202:0x05c2, B:204:0x05cf, B:206:0x05d6, B:205:0x05d3, B:207:0x05eb, B:208:0x05f0, B:197:0x05a0, B:209:0x05fc, B:211:0x0600, B:212:0x0603, B:214:0x060d, B:217:0x0625, B:216:0x0613, B:218:0x063c, B:220:0x0640, B:221:0x0643, B:223:0x064d, B:226:0x0654, B:228:0x0681, B:227:0x0664, B:229:0x0698, B:231:0x069c, B:232:0x069f, B:234:0x06cb, B:237:0x06d2, B:239:0x06da, B:244:0x06fc, B:246:0x0702, B:248:0x071e, B:250:0x0725, B:249:0x0722, B:251:0x073a, B:252:0x073f, B:241:0x06e0, B:30:0x006f, B:33:0x007b, B:36:0x0086, B:39:0x0092, B:42:0x009d, B:45:0x00a7, B:48:0x00b2, B:51:0x00bb, B:54:0x00c5, B:57:0x00cf, B:60:0x00d9, B:63:0x00e3, B:66:0x00ee, B:253:0x074b, B:255:0x0753, B:256:0x075f, B:316:0x0836, B:318:0x083a, B:320:0x083e, B:321:0x0841, B:258:0x0764, B:261:0x0770, B:264:0x077a, B:267:0x0786, B:270:0x0792, B:273:0x079e, B:276:0x07aa, B:279:0x07b5, B:282:0x07c0, B:285:0x07cb, B:288:0x07d7, B:291:0x07e1, B:294:0x07eb, B:297:0x07f5, B:300:0x0800, B:303:0x080b, B:306:0x0816, B:309:0x0821, B:312:0x082b, B:322:0x0873, B:323:0x087e), top: B:328:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.jstyle1963dsdk.api.JstyleBaseRes r18) {
        /*
            Method dump skipped, instructions count: 2366
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.onResponse(com.coveiot.android.jstyle1963dsdk.api.JstyleBaseRes):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.d == null) {
            this.d = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        KH1963DBleService kH1963DBleService = t;
        if (kH1963DBleService != null) {
            if (kH1963DBleService.getConnectionState() == Clove1963DBleState.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else if (t.getConnectionState() == Clove1963DBleState.BleState.CONNECTING) {
                connectionStatus = ConnectionStatus.CONNECTING;
            }
        }
        this.d.postValue(connectionStatus);
        return this.d;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveECGDataResponse> registerForLiveEcgData() {
        if (this.h == null) {
            this.h = new MutableLiveData<>();
        }
        return this.h;
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
        if (this.f == null) {
            this.f = new MutableLiveData<>();
        }
        return this.f;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public MutableLiveData<PPGData> registerLivePPGData() {
        if (this.f3090a == null) {
            this.f3090a = new MutableLiveData<>();
        }
        return this.f3090a;
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
    @NotNull
    public LiveData<LiveStepsData> registerLiveStepsData() {
        if (this.g == null) {
            this.g = new MutableLiveData<>();
        }
        return this.g;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public MutableLiveData<LiveWatchFaceUploadPercentage> registerLiveWatchFaceUploadData() {
        if (this.j == null) {
            this.j = new MutableLiveData<>();
        }
        return this.j;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        clearCommandQueue();
        KH1963DBleService kH1963DBleService = t;
        if (kH1963DBleService != null) {
            kH1963DBleService.restartService();
            c();
        }
        new Handler().postDelayed(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.31
            @Override // java.lang.Runnable
            public void run() {
                JStyle1963YHBleApiImpl.h();
            }
        }, 5000L);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        clearCommandQueue();
        this.c.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(w).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.3
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(CharSequence charSequence) {
                        scanResultListener.onError(JStyle1963YHBleApiImpl.w.getString(R.string.scan_failed));
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(String str) {
                        JStyle1963YHBleApiImpl.a(JStyle1963YHBleApiImpl.this, new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.c.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!DeviceScanManager.getInstance(JStyle1963YHBleApiImpl.w).isScanningInProgress()) {
                        if (BleUtils.isEmpty(scanDeviceRequest.getScanFilter())) {
                            DeviceScanManager.getInstance(JStyle1963YHBleApiImpl.w).scanAllDevices(JStyle1963YHBleApiImpl.w, scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.4.1
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    JStyle1963YHBleApiImpl.a(JStyle1963YHBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(JStyle1963YHBleApiImpl.w.getString(R.string.scan_failed));
                                }
                            });
                        } else {
                            DeviceScanManager.getInstance(JStyle1963YHBleApiImpl.w).scanDevicesWithFilter(JStyle1963YHBleApiImpl.w, scanDeviceRequest.getScanFilter(), scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl.4.2
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    JStyle1963YHBleApiImpl.a(JStyle1963YHBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(JStyle1963YHBleApiImpl.w.getString(R.string.scan_failed));
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

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus busyStatus) {
        this.p = busyStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener connectionResultListener) {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(BleBaseRequest bleBaseRequest, SettingsResultListener settingsResultListener) {
        KH1963DBleService kH1963DBleService = t;
        if (kH1963DBleService == null || kH1963DBleService.getConnectionState() != Clove1963DBleState.BleState.CONNECTED) {
            settingsResultListener.onSettingsError(new BleBaseError(w.getString(R.string.band_not_connected)));
        } else if (bleBaseRequest == null || settingsResultListener == null) {
            if (settingsResultListener != null) {
                Context context = w;
                int i = R.string.request_cannot_null;
                BleBaseError bleBaseError = new BleBaseError(context.getString(i));
                bleBaseError.setErrorMsg(w.getString(i));
                settingsResultListener.onSettingsError(bleBaseError);
            }
        } else {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            bleBaseRequest.setResponseListener(settingsResultListener);
            a(bleBaseRequest);
            if (u == null || u.size() <= 0) {
                return;
            }
            d();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(w).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManager1963D.getInstance(w).saveConnectedDeviceMAcAddress("");
        PreferenceManager1963D.getInstance(w).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        KH1963DBleService kH1963DBleService = t;
        if (kH1963DBleService != null) {
            kH1963DBleService.stopService();
            c();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        clearCommandQueue();
        PreferenceManager1963D.getInstance(w).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        KH1963DBleService kH1963DBleService = t;
        if (kH1963DBleService != null) {
            kH1963DBleService.stopServiceRetainMacAddress();
            c();
        }
    }

    public void unbindService() {
        ServiceConnection serviceConnection = x;
        if (serviceConnection != null) {
            w.unbindService(serviceConnection);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public MutableLiveData<LiveTemperatureData> registerForLiveTemperatureData() {
        if (this.i == null) {
            this.i = new MutableLiveData<>();
        }
        return this.i;
    }

    public final void c(BleBaseRequest bleBaseRequest) {
        BaseListener responseListener = bleBaseRequest.getResponseListener();
        if (responseListener instanceof DataResultListener) {
            ((DataResultListener) responseListener).onDataError(new BleBaseError(w.getString(R.string.command_not_found)));
        } else if (responseListener instanceof SettingsResultListener) {
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(w.getString(R.string.command_not_found)));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, w.getString(R.string.command_not_found)));
        }
    }

    public static boolean a() {
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) w.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (KH1963DBleService.class.getName().equals(runningServiceInfo.service.getClassName()) && w.getPackageName().equals(runningServiceInfo.service.getPackageName())) {
                z = true;
            }
        }
        return z;
    }

    public final void a(JstyleError jstyleError) {
        synchronized (u) {
            if (u != null && u.size() > 0) {
                for (int i = 0; i < u.size(); i++) {
                    try {
                        BaseListener responseListener = u.get(i).f3123a.getResponseListener();
                        BleBaseError bleBaseError = new BleBaseError(jstyleError.getMessage());
                        if (jstyleError.getErrorType() == JstyleErrorType.COMMAND_TIME_OUT) {
                            bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_ERROR_TIMEOUT.value));
                        }
                        if (responseListener instanceof DataResultListener) {
                            ((DataResultListener) responseListener).onDataError(bleBaseError);
                        } else if (responseListener instanceof SettingsResultListener) {
                            ((SettingsResultListener) responseListener).onSettingsError(bleBaseError);
                        } else if (responseListener instanceof ConnectionResultListener) {
                            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, jstyleError.getMessage()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                clearCommandQueue();
            }
        }
    }

    public final BleBaseRequest a(JstyleBaseReq jstyleBaseReq) {
        for (int i = 0; i < u.size(); i++) {
            if (u.get(i).f3123a.getRequId().equalsIgnoreCase(jstyleBaseReq.getReqId())) {
                return u.remove(i).f3123a;
            }
        }
        return null;
    }

    public final void a(BleBaseRequest bleBaseRequest) {
        synchronized (u) {
            JstyleBaseReq b = b(bleBaseRequest);
            if (b != null) {
                if (b.isPriority()) {
                    u.addFirst(new QueueObject(this, bleBaseRequest));
                } else {
                    u.add(new QueueObject(this, bleBaseRequest));
                }
            } else {
                c(bleBaseRequest);
            }
        }
    }

    public final void b(JstyleBaseReq jstyleBaseReq) {
        this.n = u.get(0).f3123a;
        if (jstyleBaseReq.isMultiPacket()) {
            this.m.postDelayed(this.q, com.clevertap.android.sdk.Constants.ONE_MIN_IN_MILLIS);
        } else {
            this.m.postDelayed(this.q, 30000L);
        }
        t.sendRequest(jstyleBaseReq, this);
    }

    public final boolean b() {
        KHJstyleEntityDeviceInfo deviceInfoBy = KHJstyleDeviceInfoRepository.getInstance(w).getDeviceInfoBy(v);
        if (deviceInfoBy != null) {
            long lastSyncWalkTimeStamp = deviceInfoBy.getLastSyncWalkTimeStamp();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(lastSyncWalkTimeStamp);
            return JStyleUtils.getMinutesDifference(calendar, Calendar.getInstance()) > 2;
        }
        return true;
    }
}
