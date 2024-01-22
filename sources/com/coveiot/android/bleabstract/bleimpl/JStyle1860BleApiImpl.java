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
import com.coveiot.android.bleabstract.formatter.JStyle1860Formatter;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.AlarmTypes;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.Clove1860BleState;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.models.PPGTypes;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.android.bleabstract.models.RawPPGData;
import com.coveiot.android.bleabstract.preferences.PreferenceManager1860;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.AgpsUpdateRequest;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ChangeWatchFaceRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.request.DeleteActivityDataRequest;
import com.coveiot.android.bleabstract.request.DeleteGpsDataRequest;
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
import com.coveiot.android.bleabstract.request.ReadManualHRVRequest;
import com.coveiot.android.bleabstract.request.ReadManualSpo2Request;
import com.coveiot.android.bleabstract.request.ReadManualStressRequest;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.SessionGPSDataRequest;
import com.coveiot.android.bleabstract.request.SessionHeartRateRequest;
import com.coveiot.android.bleabstract.request.SessionStepsDataRequest;
import com.coveiot.android.bleabstract.request.SetDeviceParametersRequest;
import com.coveiot.android.bleabstract.request.SetDistanceUnitRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmListRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.bleabstract.request.StopMessageNotificationRequest;
import com.coveiot.android.bleabstract.request.TemperatureTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.CallRejectRes;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceSettingsInfoResponse;
import com.coveiot.android.bleabstract.response.GetGPSTime;
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
import com.coveiot.android.bleabstract.services.KH1860BleService;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleConstants;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils;
import com.coveiot.android.jstyle1860Sdk.JstyleResponseListener;
import com.coveiot.android.jstyle1860Sdk.api.JStyleRawBloodOxygenPPGControlReq;
import com.coveiot.android.jstyle1860Sdk.api.JStyleRawPPGControlReq;
import com.coveiot.android.jstyle1860Sdk.api.JStyleRealTimeWalkReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleActivityModeHistoryReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleAgpsFileUpdateReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleBatteryLevelReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleChangeWatchFaceReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleCustomWatchFaceReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleDeviceBaseParametersReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleDeviceVersionReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleDistanceUnitReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleEnterOTAReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleGPSDataReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleGetDeviceInfoReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleHRTimeIntervalReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleHistoryHRReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleHrvReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleLiftWristReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleMotorVibrationReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleNotificationReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleOnceHRReq;
import com.coveiot.android.jstyle1860Sdk.api.JstylePersonalInfoReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleSedentaryReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleSleepReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleSpo2Req;
import com.coveiot.android.jstyle1860Sdk.api.JstyleStepTargetReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleTimeFormatReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleVibrationAlarmReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleWalkReq;
import com.coveiot.android.jstyle1860Sdk.api.JstyleWatchFacePositionReq;
import com.coveiot.android.jstyle1860Sdk.error.JstyleError;
import com.coveiot.android.jstyle1860Sdk.error.JstyleErrorType;
import com.coveiot.android.jstyle1860Sdk.model.JstyleLiveResponse;
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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.model.Clock;
import com.jstyle.blesdk1860.model.MyAutomaticHRMonitoring;
import com.jstyle.blesdk1860.model.MyDeviceInfo;
import com.jstyle.blesdk1860.model.MyPersonalInfo;
import com.jstyle.blesdk1860.model.MySedentaryReminder;
import com.jstyle.blesdk1860.model.Notifier;
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
public class JStyle1860BleApiImpl implements BleApi, JstyleResponseListener {
    public static JStyle1860BleApiImpl t = null;
    public static final String u = "JStyle1860BleApiImpl";
    public static KH1860BleService v;
    public static String x;
    public static Context y;

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<PPGData> f3030a;
    public MutableLiveData<ConnectionStatus> d;
    public DeviceInfoData e;
    public MutableLiveData<LiveHealthData> f;
    public MutableLiveData<LiveStepsData> g;
    public MutableLiveData<LiveECGDataResponse> h;
    public MutableLiveData<LiveTemperatureData> i;
    public MutableLiveData<LiveWatchFaceUploadPercentage> j;
    public MutableLiveData<LiveAGPSUploadPercentage> k;
    public static volatile LinkedList<QueueObject> w = new LinkedList<>();
    public static ServiceConnection z = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            JStyle1860BleApiImpl.v = ((KH1860BleService.BtServiceBinder) iBinder).getService();
            if (TextUtils.isEmpty(JStyle1860BleApiImpl.x)) {
                return;
            }
            JStyle1860BleApiImpl.v.initBluetoothDevice();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            JStyle1860BleApiImpl.v = null;
        }
    };
    public Handler b = new Handler();
    public Handler c = new Handler();
    public Handler l = new Handler(Looper.getMainLooper());
    public Handler m = new Handler();
    public BleBaseRequest n = null;
    public ConnectionResultListener o = null;
    public String[] p = {AlarmTypes.ALARM.getValue(), AlarmTypes.MEDICINE.getValue(), AlarmTypes.DRINK.getValue(), AlarmTypes.FOOD.getValue()};
    public ArrayList<Integer> q = new ArrayList<>();
    public BusyStatus r = BusyStatus.SYNCING;
    public Runnable s = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.32
        @Override // java.lang.Runnable
        public void run() {
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.d("Command TimeOut", "Failed", moduleNames.getModuleName());
            JStyle1860BleApiImpl jStyle1860BleApiImpl = JStyle1860BleApiImpl.this;
            BleBaseRequest bleBaseRequest = jStyle1860BleApiImpl.n;
            if (bleBaseRequest != null) {
                JstyleBaseReq b = jStyle1860BleApiImpl.b(bleBaseRequest);
                if (b != null) {
                    JStyle1860BleApiImpl.this.onFailure(new JstyleError(JstyleErrorType.COMMAND_TIME_OUT, JStyle1860BleApiImpl.y.getString(R.string.command_time_out)));
                    LogHelper.e("Command TimeOut", "End Time " + System.currentTimeMillis() + "-- currentCommand " + b.getDataType(), moduleNames.getModuleName());
                } else {
                    JStyle1860BleApiImpl jStyle1860BleApiImpl2 = JStyle1860BleApiImpl.this;
                    jStyle1860BleApiImpl2.d(jStyle1860BleApiImpl2.n);
                }
            }
            JStyle1860BleApiImpl.this.n = null;
        }
    };

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl$33  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass33 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3054a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            Clove1860BleState.BleState.values();
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
            try {
                int[] iArr14 = b;
                NotificationType notificationType13 = NotificationType.MISSED_CALL;
                iArr14[20] = 13;
            } catch (NoSuchFieldError unused16) {
            }
            int[] iArr15 = new int[JstyleErrorType.values().length];
            f3054a = iArr15;
            try {
                iArr15[JstyleErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f3054a[JstyleErrorType.COMMAND_TIME_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public class QueueObject {

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3063a;

        public QueueObject(JStyle1860BleApiImpl jStyle1860BleApiImpl, BleBaseRequest bleBaseRequest) {
            this.f3063a = bleBaseRequest;
        }
    }

    public JStyle1860BleApiImpl() {
        e();
    }

    public static void a(JStyle1860BleApiImpl jStyle1860BleApiImpl, List list, boolean z2, ScanDeviceRequest scanDeviceRequest, ScanResultListener scanResultListener) {
        jStyle1860BleApiImpl.getClass();
        ScanDeviceResponse scanDeviceResponse = new ScanDeviceResponse(scanDeviceRequest);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            BleDevice bleDevice = (BleDevice) it.next();
            arrayList.add(new com.coveiot.android.bleabstract.models.BleDevice(bleDevice.getmDevice(), bleDevice.getRssi()));
        }
        scanDeviceResponse.setBluetoothDevices(arrayList);
        scanDeviceResponse.setScanComplete(z2);
        if (!scanDeviceRequest.isShouldProvideBatchResult()) {
            scanResultListener.onResponse(scanDeviceResponse);
        } else if (z2) {
            scanResultListener.onResponse(scanDeviceResponse);
        }
    }

    public static JStyle1860BleApiImpl getInstance(Context context) {
        if (t == null) {
            y = context.getApplicationContext();
            t = new JStyle1860BleApiImpl();
        }
        if (!a()) {
            LogHelper.d(u, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
            h();
        }
        return t;
    }

    public static void h() {
        try {
            Intent intent = new Intent(y, KH1860BleService.class);
            y.bindService(intent, z, 1);
            if (Build.VERSION.SDK_INT >= 26) {
                y.startForegroundService(intent);
            } else {
                y.startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, y);
        }
    }

    public final JstyleBaseReq b(BleBaseRequest bleBaseRequest) {
        int type_stop_tel_1860;
        int type_call_1860;
        int[] iArr;
        if (bleBaseRequest instanceof StepsDataRequest) {
            JstyleWalkReq jstyleWalkReq = new JstyleWalkReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_START());
            jstyleWalkReq.setReqId(bleBaseRequest.getRequId());
            jstyleWalkReq.setDataType(BleConst.GetDetailActivityData);
            return jstyleWalkReq;
        } else if (bleBaseRequest instanceof SleepDataRequest) {
            JstyleSleepReq jstyleSleepReq = new JstyleSleepReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_START());
            jstyleSleepReq.setReqId(bleBaseRequest.getRequId());
            jstyleSleepReq.setDataType(BleConst.GetDetailSleepData);
            return jstyleSleepReq;
        } else if (bleBaseRequest instanceof HeartRateDataRequest) {
            JstyleOnceHRReq jstyleOnceHRReq = new JstyleOnceHRReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_START());
            jstyleOnceHRReq.setReqId(bleBaseRequest.getRequId());
            jstyleOnceHRReq.setDataType(BleConst.GetStaticHR);
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
                    LogHelper.d(u, "LiveStepsControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyleRealTimeWalkReq jStyleRealTimeWalkReq = new JStyleRealTimeWalkReq();
                    jStyleRealTimeWalkReq.setStartReal(((LiveStepsControlRequest) bleBaseRequest).isEnable());
                    jStyleRealTimeWalkReq.setIstempEnable(false);
                    jStyleRealTimeWalkReq.setReqId(bleBaseRequest.getRequId());
                    jStyleRealTimeWalkReq.setDataType(BleConst.RealTimeStep);
                    return jStyleRealTimeWalkReq;
                } else if (bleBaseRequest instanceof LiveTemperatureControlRequest) {
                    LogHelper.d(u, "LiveTemperatureControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyleRealTimeWalkReq jStyleRealTimeWalkReq2 = new JStyleRealTimeWalkReq();
                    jStyleRealTimeWalkReq2.setReqId(bleBaseRequest.getRequId());
                    jStyleRealTimeWalkReq2.setDataType(BleConst.RealTimeStep);
                    LiveTemperatureControlRequest liveTemperatureControlRequest = (LiveTemperatureControlRequest) bleBaseRequest;
                    jStyleRealTimeWalkReq2.setStartReal(liveTemperatureControlRequest.isEnabled());
                    jStyleRealTimeWalkReq2.setIstempEnable(liveTemperatureControlRequest.isEnabled());
                    return jStyleRealTimeWalkReq2;
                } else if (bleBaseRequest instanceof LiveHeartRateControlRequest) {
                    LogHelper.d(u, "LiveHeartRateControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
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
                    boolean z2 = true;
                    if (bleBaseRequest instanceof SetReminderRequest) {
                        LogHelper.d(u, "SetReminderRequest", ModuleNames.BLEABSTRACT.getModuleName());
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
                            if (iArr3[i] == z2) {
                                i4 = (int) (i4 + Math.pow(2.0d, i));
                            }
                            i++;
                            i2 = 7;
                            z2 = true;
                        }
                        myAutomaticHRMonitoring.setWeek(i4);
                        myAutomaticHRMonitoring.setOpen(heartRateTimeIntervalRequest.getOpen());
                        jstyleHRTimeIntervalReq.setMyAutomaticHRMonitoring(myAutomaticHRMonitoring);
                        jstyleHRTimeIntervalReq.setReqId(bleBaseRequest.getRequId());
                        jstyleHRTimeIntervalReq.setDataType(BleConst.SetAutomaticHRMonitoring);
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
                        jstyleHRTimeIntervalReq2.setDataType(BleConst.SetAutomaticHRMonitoring);
                        return jstyleHRTimeIntervalReq2;
                    } else if (bleBaseRequest instanceof SetDeviceParametersRequest) {
                        MyDeviceInfo myDeviceInfo = new MyDeviceInfo();
                        SetDeviceParametersRequest setDeviceParametersRequest = (SetDeviceParametersRequest) bleBaseRequest;
                        myDeviceInfo.setIs12Hour(setDeviceParametersRequest.is12HourFormat());
                        myDeviceInfo.setDistanceUnit(setDeviceParametersRequest.isDistanceUnitinMile());
                        myDeviceInfo.setHand_up_light_screen_switch(setDeviceParametersRequest.isLiftWristEnabled());
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
                        String str = u;
                        ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
                        LogHelper.d(str, "appNotificationType from App" + appNotificationType, moduleNames2.getModuleName());
                        int ordinal = appNotificationType.ordinal();
                        if (ordinal == 0) {
                            type_call_1860 = JStyleConstants.Constants.getTYPE_CALL_1860();
                        } else if (ordinal == 2) {
                            type_call_1860 = JStyleConstants.Constants.getTYPE_SMS_1860();
                        } else if (ordinal == 10) {
                            type_call_1860 = JStyleConstants.Constants.getTYPE_QQ_1860();
                        } else if (ordinal != 20) {
                            switch (ordinal) {
                                case 4:
                                    type_call_1860 = JStyleConstants.Constants.getTYPE_WHATSAPP_1860();
                                    break;
                                case 5:
                                    type_call_1860 = JStyleConstants.Constants.getTYPE_WECHAT_1860();
                                    break;
                                case 6:
                                    type_call_1860 = JStyleConstants.Constants.getTYPE_FACEBOOK_1860();
                                    break;
                                case 7:
                                    type_call_1860 = JStyleConstants.Constants.getTYPE_INSTAGRAM_1860();
                                    break;
                                case 8:
                                    type_call_1860 = JStyleConstants.Constants.getTYPE_TWITTER_1860();
                                    break;
                                default:
                                    switch (ordinal) {
                                        case 14:
                                            type_call_1860 = JStyleConstants.Constants.getTYPE_SKYPE_1860();
                                            break;
                                        case 15:
                                            type_call_1860 = JStyleConstants.Constants.getTYPE_LINKEDIN_1860();
                                            break;
                                        case 16:
                                            type_call_1860 = JStyleConstants.Constants.getTYPE_VKCLIENT_1860();
                                            break;
                                        case 17:
                                            type_call_1860 = JStyleConstants.Constants.getTYPE_TELEGRAM_1860();
                                            break;
                                        default:
                                            type_call_1860 = -1;
                                            break;
                                    }
                            }
                        } else {
                            type_call_1860 = JStyleConstants.Constants.getTYPE_MISSED_CALL_1860();
                        }
                        if (type_call_1860 != -1) {
                            String str2 = setMessageContentRequest.message;
                            Notifier notifier = new Notifier();
                            notifier.setInfo(str2);
                            notifier.setType(type_call_1860);
                            LogHelper.d(str, "Notification type " + type_call_1860 + " content " + str2 + "", moduleNames2.getModuleName());
                            JstyleNotificationReq jstyleNotificationReq = new JstyleNotificationReq();
                            jstyleNotificationReq.setNotifier(notifier);
                            jstyleNotificationReq.setDataType(JStyleConstants.Notification);
                            jstyleNotificationReq.setReqId(bleBaseRequest.getRequId());
                            return jstyleNotificationReq;
                        }
                        return null;
                    } else if (bleBaseRequest instanceof StopMessageNotificationRequest) {
                        if (((StopMessageNotificationRequest) bleBaseRequest).getAppNotificationType().ordinal() != 0) {
                            type_stop_tel_1860 = JStyleConstants.Constants.getTYPE_STOP_TEL_1860();
                        } else {
                            type_stop_tel_1860 = JStyleConstants.Constants.getTYPE_STOP_TEL_1860();
                        }
                        Notifier notifier2 = new Notifier();
                        notifier2.setType(type_stop_tel_1860);
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
                                jstyleVibrationAlarmReq.setDataType(BleConst.SetAlarmClockWithAllClock);
                                return jstyleVibrationAlarmReq;
                            }
                        }
                    } else if (bleBaseRequest instanceof ActivityModeSummaryRequest) {
                        JstyleActivityModeHistoryReq jstyleActivityModeHistoryReq = new JstyleActivityModeHistoryReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_START());
                        jstyleActivityModeHistoryReq.setReqId(bleBaseRequest.getRequId());
                        jstyleActivityModeHistoryReq.setDataType(BleConst.GetActivityModeData);
                        return jstyleActivityModeHistoryReq;
                    } else if (bleBaseRequest instanceof ReadManualBpRequest) {
                        JstyleHrvReq jstyleHrvReq = new JstyleHrvReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_START());
                        jstyleHrvReq.setReqId(bleBaseRequest.getRequId());
                        jstyleHrvReq.setDataType("42");
                        return jstyleHrvReq;
                    } else if (bleBaseRequest instanceof AgpsUpdateRequest) {
                        JstyleAgpsFileUpdateReq jstyleAgpsFileUpdateReq = new JstyleAgpsFileUpdateReq();
                        jstyleAgpsFileUpdateReq.setFilePath(((AgpsUpdateRequest) bleBaseRequest).getAgpsFilePath());
                        jstyleAgpsFileUpdateReq.setReqId(bleBaseRequest.getRequId());
                        jstyleAgpsFileUpdateReq.setDataType(JStyleConstants.AGPS_UPDATE_FILE);
                        return jstyleAgpsFileUpdateReq;
                    } else if (bleBaseRequest instanceof CustomWatchFaceFileImageRequest) {
                        JstyleCustomWatchFaceReq jstyleCustomWatchFaceReq = new JstyleCustomWatchFaceReq();
                        jstyleCustomWatchFaceReq.setReqId(bleBaseRequest.getRequId());
                        CustomWatchFaceFileImageRequest customWatchFaceFileImageRequest = (CustomWatchFaceFileImageRequest) bleBaseRequest;
                        jstyleCustomWatchFaceReq.setWatchFacePosition(customWatchFaceFileImageRequest.getWatchFacePosition());
                        if (customWatchFaceFileImageRequest.getBitmap() != null) {
                            jstyleCustomWatchFaceReq.setBitmap(customWatchFaceFileImageRequest.getBitmap());
                            jstyleCustomWatchFaceReq.setDataType("DIY");
                        } else {
                            jstyleCustomWatchFaceReq.setWatchFaceFilePath(customWatchFaceFileImageRequest.getWatchFaceFilePath());
                            jstyleCustomWatchFaceReq.setDataType(JStyleConstants.CLOUD_WATCH_FACE_UPDATE);
                        }
                        return jstyleCustomWatchFaceReq;
                    } else if (bleBaseRequest instanceof ChangeWatchFaceRequest) {
                        JstyleChangeWatchFaceReq jstyleChangeWatchFaceReq = new JstyleChangeWatchFaceReq();
                        jstyleChangeWatchFaceReq.setWatchFacePosition(((ChangeWatchFaceRequest) bleBaseRequest).getWatchFacePosition());
                        jstyleChangeWatchFaceReq.setReqId(bleBaseRequest.getRequId());
                        jstyleChangeWatchFaceReq.setDataType(JStyleConstants.DEFAULT_WATCH_FACE_UPDATE);
                        return jstyleChangeWatchFaceReq;
                    } else if (bleBaseRequest instanceof GetWatchFacePositionRequest) {
                        JstyleWatchFacePositionReq jstyleWatchFacePositionReq = new JstyleWatchFacePositionReq();
                        jstyleWatchFacePositionReq.setReqId(bleBaseRequest.getRequId());
                        jstyleWatchFacePositionReq.setDataType(JStyleConstants.WATCH_FACE_POSITION);
                        return jstyleWatchFacePositionReq;
                    } else if (bleBaseRequest instanceof SessionHeartRateRequest) {
                        JstyleHistoryHRReq jstyleHistoryHRReq = new JstyleHistoryHRReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_START());
                        jstyleHistoryHRReq.setReqId(bleBaseRequest.getRequId());
                        jstyleHistoryHRReq.setDataType(BleConst.GetDynamicHR);
                        return jstyleHistoryHRReq;
                    } else if (bleBaseRequest instanceof SessionStepsDataRequest) {
                        JstyleWalkReq jstyleWalkReq2 = new JstyleWalkReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_START());
                        jstyleWalkReq2.setReqId(bleBaseRequest.getRequId());
                        jstyleWalkReq2.setDataType(JStyleConstants.SESSION_STEPS_DATA);
                        return jstyleWalkReq2;
                    } else if (bleBaseRequest instanceof ActivityModeWithSamplesRequest) {
                        if (b()) {
                            getData(new SessionStepsDataRequest(), (DataResultListener) bleBaseRequest.getResponseListener());
                        }
                        getData(new SessionHeartRateRequest(), (DataResultListener) bleBaseRequest.getResponseListener());
                        getData(new SessionGPSDataRequest(), (DataResultListener) bleBaseRequest.getResponseListener());
                        getData(new ActivityModeSummaryRequest.Builder().build(), (DataResultListener) bleBaseRequest.getResponseListener());
                    } else if (bleBaseRequest instanceof GetDeviceSettingsInfoRequest) {
                        JstyleGetDeviceInfoReq jstyleGetDeviceInfoReq = new JstyleGetDeviceInfoReq();
                        jstyleGetDeviceInfoReq.setReqId(bleBaseRequest.getRequId());
                        jstyleGetDeviceInfoReq.setDataType(BleConst.GetDeviceInfo);
                        return jstyleGetDeviceInfoReq;
                    } else if (bleBaseRequest instanceof SessionGPSDataRequest) {
                        JstyleGPSDataReq jstyleGPSDataReq = new JstyleGPSDataReq();
                        jstyleGPSDataReq.setReqId(bleBaseRequest.getRequId());
                        jstyleGPSDataReq.setDataType(BleConst.Gps);
                        return jstyleGPSDataReq;
                    } else if (bleBaseRequest instanceof ReadManualHRVRequest) {
                        JstyleHrvReq jstyleHrvReq2 = new JstyleHrvReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_START());
                        jstyleHrvReq2.setReqId(bleBaseRequest.getRequId());
                        jstyleHrvReq2.setDataType("42");
                        return jstyleHrvReq2;
                    } else if (bleBaseRequest instanceof ReadManualStressRequest) {
                        JstyleHrvReq jstyleHrvReq3 = new JstyleHrvReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_START());
                        jstyleHrvReq3.setReqId(bleBaseRequest.getRequId());
                        jstyleHrvReq3.setDataType("42");
                        return jstyleHrvReq3;
                    } else if (bleBaseRequest instanceof ReadManualSpo2Request) {
                        JstyleSpo2Req jstyleSpo2Req = new JstyleSpo2Req(Calendar.getInstance(), JStyleConstants.Constants.getMODE_START());
                        jstyleSpo2Req.setReqId(bleBaseRequest.getRequId());
                        jstyleSpo2Req.setDataType(BleConst.Blood_oxygen);
                        return jstyleSpo2Req;
                    } else if (bleBaseRequest instanceof DeleteGpsDataRequest) {
                        JstyleGPSDataReq jstyleGPSDataReq2 = new JstyleGPSDataReq();
                        jstyleGPSDataReq2.setReqId(bleBaseRequest.getRequId());
                        jstyleGPSDataReq2.setDataType(JStyleConstants.DELETE_GPS_DATA);
                        jstyleGPSDataReq2.setMode(JStyleConstants.Constants.getMODE_DELETE());
                        return jstyleGPSDataReq2;
                    } else if (bleBaseRequest instanceof DeleteActivityDataRequest) {
                        JstyleActivityModeHistoryReq jstyleActivityModeHistoryReq2 = new JstyleActivityModeHistoryReq(Calendar.getInstance(), JStyleConstants.Constants.getMODE_DELETE());
                        jstyleActivityModeHistoryReq2.setReqId(bleBaseRequest.getRequId());
                        jstyleActivityModeHistoryReq2.setDataType(JStyleConstants.DELETE_ACTIVITY_DATA);
                        return jstyleActivityModeHistoryReq2;
                    } else if (bleBaseRequest instanceof LiveRawPPGControlRequest) {
                        if (((LiveRawPPGControlRequest) bleBaseRequest).getmPPGType().equals(PPGTypes.PPG.getValue())) {
                            JStyleRawPPGControlReq jStyleRawPPGControlReq = new JStyleRawPPGControlReq();
                            jStyleRawPPGControlReq.setReqId(bleBaseRequest.getRequId());
                            jStyleRawPPGControlReq.setDataType(BleConst.Getppg);
                            jStyleRawPPGControlReq.setEnable(((LiveRawPPGControlRequest) bleBaseRequest).isEnabled());
                            return jStyleRawPPGControlReq;
                        }
                        JStyleRawBloodOxygenPPGControlReq jStyleRawBloodOxygenPPGControlReq = new JStyleRawBloodOxygenPPGControlReq();
                        jStyleRawBloodOxygenPPGControlReq.setReqId(bleBaseRequest.getRequId());
                        jStyleRawBloodOxygenPPGControlReq.setDataType(BleConst.BloodOxygen_PPG);
                        jStyleRawBloodOxygenPPGControlReq.setEnable(((LiveRawPPGControlRequest) bleBaseRequest).isEnabled());
                        return jStyleRawBloodOxygenPPGControlReq;
                    }
                }
            }
            return null;
        }
    }

    public final void c() {
        if (DeviceScanManager.getInstance(y).isScanningInProgress()) {
            DeviceScanManager.getInstance(y).stopScan();
        }
        v = null;
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
        setUserSettings(new LiveStepsControlRequest.Builder(false).build(), new SettingsResultListener() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.28
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(BleBaseError bleBaseError) {
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
                final JStyle1860BleApiImpl jStyle1860BleApiImpl = JStyle1860BleApiImpl.this;
                JStyle1860BleApiImpl jStyle1860BleApiImpl2 = JStyle1860BleApiImpl.t;
                jStyle1860BleApiImpl.getClass();
                jStyle1860BleApiImpl.setUserSettings(new LiveHeartRateControlRequest.Builder(false).build(), new SettingsResultListener() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.29
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(BleBaseError bleBaseError) {
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse2) {
                        JStyle1860BleApiImpl jStyle1860BleApiImpl3 = JStyle1860BleApiImpl.this;
                        JStyle1860BleApiImpl jStyle1860BleApiImpl4 = JStyle1860BleApiImpl.t;
                        jStyle1860BleApiImpl3.getClass();
                        jStyle1860BleApiImpl3.setUserSettings(new LiveTemperatureControlRequest.Builder(false).build(), new SettingsResultListener(jStyle1860BleApiImpl3) { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.30
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
        synchronized (w) {
            if (w != null && w.size() > 0) {
                w.clear();
            }
            KH1860BleService kH1860BleService = v;
            if (kH1860BleService != null) {
                kH1860BleService.clearQueue();
            }
        }
        Handler handler = this.m;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.n = null;
        LogHelper.d("Command clear", FirebaseAnalytics.Param.SUCCESS);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(final ConnectRequest connectRequest, final ConnectionResultListener connectionResultListener) {
        this.b.removeCallbacksAndMessages(null);
        this.o = connectionResultListener;
        this.b.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.5
            @Override // java.lang.Runnable
            public void run() {
                KH1860BleService kH1860BleService = JStyle1860BleApiImpl.v;
                if (kH1860BleService == null || connectRequest == null) {
                    if (kH1860BleService == null) {
                        JStyle1860BleApiImpl.h();
                        connectionResultListener.onError(new Error(Type.SERVICE_NOT_RUNNING, JStyle1860BleApiImpl.y.getString(R.string.service_not_running)));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.COMMAND_REQUEST_ERROR, JStyle1860BleApiImpl.y.getString(R.string.request_should_not_null)));
                    return;
                }
                JStyle1860BleApiImpl.x = JStyle1860BleApiImpl.this.getMacAddress();
                Clove1860BleState.BleState connectionState = JStyle1860BleApiImpl.v.getConnectionState();
                Clove1860BleState.BleState bleState = Clove1860BleState.BleState.CONNECTED;
                if (connectionState == bleState && JStyle1860BleApiImpl.x.equalsIgnoreCase(connectRequest.getMacAddress())) {
                    connectionResultListener.onConnectionResponse(ConnectionStatus.CONNECTED);
                } else if (JStyle1860BleApiImpl.v.getConnectionState() == Clove1860BleState.BleState.DISCONNECTED) {
                    BleBaseRequest bleBaseRequest = new BleBaseRequest();
                    bleBaseRequest.setBleCommand(BleCommand.CONNECT);
                    bleBaseRequest.setResponseListener(connectionResultListener);
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    JStyle1860BleApiImpl.v.connect(connectRequest.getMacAddress());
                } else if (JStyle1860BleApiImpl.v.getConnectionState() == bleState) {
                    KH1860BleService kH1860BleService2 = JStyle1860BleApiImpl.v;
                    if (kH1860BleService2.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, "Connected to band " + JStyle1860BleApiImpl.v.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, kH1860BleService2.getConnectionState().getStateAsString()));
                } else if (JStyle1860BleApiImpl.v.getConnectionState() == Clove1860BleState.BleState.CONNECTING) {
                    KH1860BleService kH1860BleService3 = JStyle1860BleApiImpl.v;
                    if (kH1860BleService3.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, "Connection in progress " + JStyle1860BleApiImpl.v.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, kH1860BleService3.getConnectionState().getStateAsString()));
                } else {
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, JStyle1860BleApiImpl.v.getConnectionState().getStateAsString()));
                }
            }
        });
    }

    public final void d() {
        if (w == null || w.size() <= 0) {
            return;
        }
        JstyleBaseReq b = b(w.get(0).f3063a);
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
        d(w.get(0).f3063a);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(ConnectionResultListener connectionResultListener) {
        KH1860BleService kH1860BleService = v;
        if (kH1860BleService != null) {
            kH1860BleService.disconnectAndForget();
            v.clearQueue();
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
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.2
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().register(JStyle1860BleApiImpl.this);
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
        KHJstyleEntityDeviceInfo deviceInfoBy = KHJstyleDeviceInfoRepository.getInstance(y).getDeviceInfoBy(x);
        if (deviceInfoBy == null) {
            deviceInfoBy = new KHJstyleEntityDeviceInfo();
            deviceInfoBy.setMacAddress(x);
        }
        deviceInfoBy.setLastSyncWalkTimeStamp(System.currentTimeMillis());
        KHJstyleDeviceInfoRepository.getInstance(y).insertDeviceInfo(deviceInfoBy);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return this.r;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        if (v != null) {
            return new ConnectionInfo(getConnectionStatus(), v.getConnectionError(), v.getConnectionStageChangeTimeStamp());
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public ConnectionStatus getConnectionStatus() {
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        KH1860BleService kH1860BleService = v;
        if (kH1860BleService != null) {
            if (kH1860BleService.getConnectionState() == Clove1860BleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            return v.getConnectionState() == Clove1860BleState.BleState.CONNECTING ? ConnectionStatus.CONNECTING : connectionStatus;
        }
        return connectionStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        KH1860BleService kH1860BleService = v;
        if (kH1860BleService == null || kH1860BleService.getConnectionState() != Clove1860BleState.BleState.CONNECTED) {
            dataResultListener.onDataError(new BleBaseError(y.getString(R.string.band_not_connected)));
        } else if (bleBaseRequest == null || dataResultListener == null) {
            if (dataResultListener != null) {
                Context context = y;
                int i = R.string.request_cannot_null;
                BleBaseError bleBaseError = new BleBaseError(context.getString(i));
                bleBaseError.setErrorMsg(y.getString(i));
                dataResultListener.onDataError(bleBaseError);
            }
        } else {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            bleBaseRequest.setResponseListener(dataResultListener);
            a(bleBaseRequest);
            if (w == null || w.size() <= 0) {
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
        deviceSupportedFeatures.setTemparatureHistorySupported(false);
        deviceSupportedFeatures.setPersonalInfoSupported(true);
        deviceSupportedFeatures.setStepGoalSupported(true);
        deviceSupportedFeatures.setCallNotificationSupported(true);
        deviceSupportedFeatures.setSmsSupported(true);
        deviceSupportedFeatures.setMessageReadSupported(true);
        deviceSupportedFeatures.setSocialNotificationSupported(true);
        deviceSupportedFeatures.setHandSettingsSupported(false);
        deviceSupportedFeatures.setPhoneFinderSupported(true);
        deviceSupportedFeatures.setLiveStepsSupported(true);
        deviceSupportedFeatures.setLiveHeartRateSupported(false);
        deviceSupportedFeatures.setLiveBPSupported(false);
        deviceSupportedFeatures.setHandPreferenceSettingsSupported(false);
        deviceSupportedFeatures.setTimeFormatSettingsSupported(true);
        deviceSupportedFeatures.setDistanceUnitSettingsSupported(true);
        deviceSupportedFeatures.setLiftWristToViewSettingsSupported(true);
        deviceSupportedFeatures.setTemperatureUnitSettingsSupported(false);
        deviceSupportedFeatures.setManualBpSupported(false);
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
        deviceSupportedFeatures.setMaxCharSupportedInNotification(60);
        deviceSupportedFeatures.setGPSDataSupportedInSportMode(true);
        deviceSupportedFeatures.setAlarmTypes(this.p);
        deviceSupportedFeatures.setManualHRVMeasurementSupported(true);
        deviceSupportedFeatures.setManualStressMeasurementSupported(true);
        deviceSupportedFeatures.setDynamicHRMergeSupported(true);
        deviceSupportedFeatures.setREMSupportedInSleep(true);
        deviceSupportedFeatures.setAgpsFileUploadSupported(true);
        deviceSupportedFeatures.setDeleteActivityDataSupported(true);
        deviceSupportedFeatures.setDeleteGpsDataSupported(true);
        deviceSupportedFeatures.setDndSupported(false);
        return deviceSupportedFeatures;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManager1860.getInstance(y).getConnectedDeviceMacAddress();
        x = connectedDeviceMacAddress;
        return connectedDeviceMacAddress;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return BleApiUtils.isServiceRunning(KH1860BleService.class.getName(), y);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    @com.squareup.otto.Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onConnectionStateChanged(com.coveiot.android.bleabstract.models.Clove1860BleState r4) {
        /*
            r3 = this;
            com.coveiot.android.bleabstract.models.ConnectionStatus r0 = com.coveiot.android.bleabstract.models.ConnectionStatus.DISCONNECTED
            if (r4 == 0) goto L18
            com.coveiot.android.bleabstract.models.Clove1860BleState$BleState r4 = r4.getmState()
            int r4 = r4.ordinal()
            if (r4 == 0) goto L15
            r1 = 1
            if (r4 == r1) goto L12
            goto L18
        L12:
            com.coveiot.android.bleabstract.models.ConnectionStatus r4 = com.coveiot.android.bleabstract.models.ConnectionStatus.CONNECTING
            goto L19
        L15:
            com.coveiot.android.bleabstract.models.ConnectionStatus r4 = com.coveiot.android.bleabstract.models.ConnectionStatus.CONNECTED
            goto L19
        L18:
            r4 = r0
        L19:
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r3.d
            if (r1 == 0) goto L25
            r1.setValue(r4)
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r3.d
            r1.postValue(r4)
        L25:
            com.coveiot.android.bleabstract.listeners.ConnectionResultListener r1 = r3.o
            if (r1 == 0) goto L2c
            r1.onConnectionResponse(r4)
        L2c:
            if (r4 != r0) goto L3a
            com.coveiot.android.jstyle1860Sdk.error.JstyleError r0 = new com.coveiot.android.jstyle1860Sdk.error.JstyleError
            com.coveiot.android.jstyle1860Sdk.error.JstyleErrorType r1 = com.coveiot.android.jstyle1860Sdk.error.JstyleErrorType.DEVICE_NOT_CONNECTED
            java.lang.String r2 = "Device disconnected"
            r0.<init>(r1, r2)
            r3.a(r0)
        L3a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "checking for ble on/off   "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            java.lang.String r0 = "connectionstatus"
            com.coveiot.utils.utility.LogHelper.d(r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.onConnectionStateChanged(com.coveiot.android.bleabstract.models.Clove1860BleState):void");
    }

    @Override // com.coveiot.android.jstyle1860Sdk.JstyleResponseListener
    public void onFailure(JstyleError jstyleError) {
        if (jstyleError != null) {
            int i = AnonymousClass33.f3054a[jstyleError.getErrorType().ordinal()];
            if (i == 1 || i == 2) {
                a(jstyleError);
            }
        }
    }

    @Subscribe
    public void onLiveResponseReceived(JstyleLiveResponse jstyleLiveResponse) {
        FindMyPhoneRes findMyPhoneRes;
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
            case 1698:
                if (dataType.equals(BleConst.Getppg)) {
                    c = 3;
                    break;
                }
                break;
            case 1723:
                if (dataType.equals(BleConst.BloodOxygen_PPG)) {
                    c = 4;
                    break;
                }
                break;
            case 288336258:
                if (dataType.equals(JStyleConstants.AGPS_UPLOAD_PERCENTAGE)) {
                    c = 5;
                    break;
                }
                break;
            case 344980948:
                if (dataType.equals(JStyleConstants.CUSTOM_WATCH_FACE_UPLOAD_PERCENTAGE)) {
                    c = 6;
                    break;
                }
                break;
            case 1540460619:
                if (dataType.equals(JStyleConstants.GET_GPS_TIME)) {
                    c = 7;
                    break;
                }
                break;
            case 2049043094:
                if (dataType.equals("FindMobilePhoneMode")) {
                    c = '\b';
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
                LocalBroadcastManager.getInstance(y).sendBroadcast(intent);
                return;
            case 1:
                if (jstyleLiveResponse.getObj() != null) {
                    DeviceSettingsInfoResponse convertToDeviceSettingsData = new JStyle1860Formatter(getMacAddress()).convertToDeviceSettingsData((Map) jstyleLiveResponse.getObj());
                    if (convertToDeviceSettingsData != null) {
                        Intent intent2 = new Intent(Constants.DEVICE_SETTINGS_BROADCAST_INTENT);
                        intent2.putExtra(Constants.DEVICE_SETTINGS_BROADCAST_INTENT_EXTRA, convertToDeviceSettingsData);
                        LocalBroadcastManager.getInstance(y).sendBroadcast(intent2);
                        return;
                    }
                    return;
                }
                return;
            case 2:
                if (jstyleLiveResponse.getObj() != null) {
                    Map<String, String> map = (Map) jstyleLiveResponse.getObj();
                    LiveStepsData convertToLiveStepsData = new JStyle1860Formatter(getMacAddress()).convertToLiveStepsData(map);
                    MutableLiveData<LiveStepsData> mutableLiveData = this.g;
                    if (mutableLiveData != null) {
                        mutableLiveData.setValue(convertToLiveStepsData);
                        this.g.postValue(convertToLiveStepsData);
                    }
                    LiveHealthData convertToLiveHealthData = new JStyle1860Formatter(getMacAddress()).convertToLiveHealthData(map);
                    MutableLiveData<LiveHealthData> mutableLiveData2 = this.f;
                    if (mutableLiveData2 != null) {
                        mutableLiveData2.setValue(convertToLiveHealthData);
                        this.f.postValue(convertToLiveHealthData);
                    }
                    LiveTemperatureData convertToLiveTemperatureData = new JStyle1860Formatter(getMacAddress()).convertToLiveTemperatureData(map);
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
            case 4:
                RawPPGData rawPPGData = (RawPPGData) jstyleLiveResponse.getObj();
                if (this.q.size() < 10) {
                    this.q.add(Integer.valueOf(rawPPGData.getData()));
                    return;
                }
                PPGData pPGData = new PPGData((ArrayList) this.q.clone());
                MutableLiveData<PPGData> mutableLiveData4 = this.f3030a;
                if (mutableLiveData4 != null) {
                    mutableLiveData4.setValue(pPGData);
                }
                this.q.clear();
                return;
            case 5:
                if (jstyleLiveResponse.getObj() != null) {
                    LiveAGPSUploadPercentage liveAGPSUploadPercentage = new LiveAGPSUploadPercentage();
                    liveAGPSUploadPercentage.setPercentage(Integer.parseInt(jstyleLiveResponse.getObj().toString()));
                    MutableLiveData<LiveAGPSUploadPercentage> mutableLiveData5 = this.k;
                    if (mutableLiveData5 != null) {
                        mutableLiveData5.setValue(liveAGPSUploadPercentage);
                        this.k.postValue(liveAGPSUploadPercentage);
                        return;
                    }
                    return;
                }
                return;
            case 6:
                if (jstyleLiveResponse.getObj() != null) {
                    LiveWatchFaceUploadPercentage liveWatchFaceUploadPercentage = new LiveWatchFaceUploadPercentage();
                    String obj = jstyleLiveResponse.getObj().toString();
                    if (w != null && w.size() > 0) {
                        for (int i = 0; i < w.size(); i++) {
                            try {
                                if (b(w.get(i).f3063a) instanceof JstyleCustomWatchFaceReq) {
                                    ((DataResultListener) w.get(i).f3063a.getResponseListener()).onProgressUpdate(new ProgressData(ProgressType.DETERMINATE, Integer.parseInt(obj), w.get(i).f3063a));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    LogHelper.d(u, "progressPercentage == " + Integer.parseInt(obj));
                    liveWatchFaceUploadPercentage.setPercentage(Integer.parseInt(obj));
                    MutableLiveData<LiveWatchFaceUploadPercentage> mutableLiveData6 = this.j;
                    if (mutableLiveData6 != null) {
                        mutableLiveData6.setValue(liveWatchFaceUploadPercentage);
                        this.j.postValue(liveWatchFaceUploadPercentage);
                        return;
                    }
                    return;
                }
                return;
            case 7:
                if (jstyleLiveResponse.getObj() != null) {
                    GetGPSTime getGPSTime = new GetGPSTime();
                    getGPSTime.setDate(jstyleLiveResponse.getObj().toString());
                    Intent intent3 = new Intent(Constants.GPS_TIME_BROADCAST_INTENT);
                    intent3.putExtra(Constants.GPS_TIME_BROADCAST_INTENT_EXTRA, getGPSTime);
                    LocalBroadcastManager.getInstance(y).sendBroadcast(intent3);
                    return;
                }
                return;
            case '\b':
                if (jstyleLiveResponse.getObj() != null) {
                    if (jstyleLiveResponse.getObj().equals(Boolean.TRUE)) {
                        findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.ON);
                    } else {
                        findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.OFF);
                    }
                    Intent intent4 = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
                    intent4.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes);
                    LocalBroadcastManager.getInstance(y).sendBroadcast(intent4);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:172:0x0525  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x055a A[Catch: Exception -> 0x0828, TryCatch #0 {Exception -> 0x0828, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x0037, B:16:0x005a, B:17:0x005d, B:77:0x0130, B:79:0x0134, B:80:0x0137, B:82:0x0141, B:85:0x0159, B:84:0x0147, B:86:0x0170, B:88:0x0174, B:89:0x0177, B:90:0x0199, B:92:0x019d, B:93:0x01a0, B:94:0x01e6, B:96:0x01ea, B:97:0x01ed, B:99:0x0219, B:101:0x021f, B:103:0x0227, B:105:0x022d, B:107:0x0233, B:108:0x0248, B:109:0x0254, B:111:0x0258, B:112:0x025b, B:113:0x02a1, B:115:0x02ab, B:116:0x02ae, B:117:0x02bd, B:119:0x02c1, B:120:0x02c4, B:121:0x031c, B:123:0x0320, B:124:0x0323, B:126:0x034f, B:129:0x0356, B:130:0x035f, B:132:0x0365, B:134:0x0372, B:136:0x0379, B:135:0x0376, B:137:0x038e, B:138:0x0393, B:140:0x03a9, B:142:0x03ad, B:143:0x03b0, B:147:0x03ee, B:148:0x0413, B:150:0x0417, B:151:0x041a, B:153:0x0440, B:154:0x0447, B:156:0x047f, B:157:0x0488, B:158:0x04c2, B:160:0x04c6, B:161:0x04c9, B:163:0x04f5, B:166:0x04fc, B:168:0x0504, B:173:0x0526, B:175:0x052c, B:177:0x0539, B:179:0x0540, B:178:0x053d, B:180:0x0555, B:181:0x055a, B:170:0x050a, B:182:0x0566, B:184:0x056a, B:185:0x056d, B:187:0x0599, B:190:0x05a0, B:192:0x05a8, B:197:0x05ca, B:199:0x05d0, B:201:0x05dd, B:203:0x05e4, B:202:0x05e1, B:204:0x05f9, B:205:0x05fe, B:194:0x05ae, B:206:0x060a, B:208:0x060e, B:209:0x0611, B:211:0x0617, B:213:0x061d, B:214:0x062c, B:215:0x0643, B:217:0x0647, B:218:0x064a, B:220:0x0676, B:223:0x067d, B:225:0x0685, B:230:0x06a7, B:232:0x06ad, B:234:0x06c9, B:236:0x06d0, B:235:0x06cd, B:237:0x06e5, B:238:0x06ea, B:227:0x068b, B:19:0x0062, B:22:0x006d, B:25:0x0078, B:28:0x0083, B:31:0x008e, B:34:0x0099, B:37:0x00a5, B:40:0x00b1, B:43:0x00bd, B:46:0x00c8, B:49:0x00d3, B:52:0x00dd, B:55:0x00e7, B:58:0x00f0, B:61:0x00fb, B:64:0x0106, B:67:0x0111, B:70:0x011b, B:239:0x06f6, B:241:0x06fe, B:242:0x070a, B:299:0x07d5, B:301:0x07d9, B:303:0x07dd, B:304:0x07e0, B:244:0x070f, B:247:0x071b, B:250:0x0726, B:253:0x0732, B:256:0x073e, B:259:0x074a, B:262:0x0755, B:265:0x0760, B:268:0x076b, B:271:0x0777, B:274:0x0781, B:277:0x078a, B:280:0x0794, B:283:0x079f, B:286:0x07a9, B:289:0x07b3, B:292:0x07be, B:295:0x07c9, B:305:0x0812, B:306:0x081d), top: B:311:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x05c9  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x05fe A[Catch: Exception -> 0x0828, TryCatch #0 {Exception -> 0x0828, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x0037, B:16:0x005a, B:17:0x005d, B:77:0x0130, B:79:0x0134, B:80:0x0137, B:82:0x0141, B:85:0x0159, B:84:0x0147, B:86:0x0170, B:88:0x0174, B:89:0x0177, B:90:0x0199, B:92:0x019d, B:93:0x01a0, B:94:0x01e6, B:96:0x01ea, B:97:0x01ed, B:99:0x0219, B:101:0x021f, B:103:0x0227, B:105:0x022d, B:107:0x0233, B:108:0x0248, B:109:0x0254, B:111:0x0258, B:112:0x025b, B:113:0x02a1, B:115:0x02ab, B:116:0x02ae, B:117:0x02bd, B:119:0x02c1, B:120:0x02c4, B:121:0x031c, B:123:0x0320, B:124:0x0323, B:126:0x034f, B:129:0x0356, B:130:0x035f, B:132:0x0365, B:134:0x0372, B:136:0x0379, B:135:0x0376, B:137:0x038e, B:138:0x0393, B:140:0x03a9, B:142:0x03ad, B:143:0x03b0, B:147:0x03ee, B:148:0x0413, B:150:0x0417, B:151:0x041a, B:153:0x0440, B:154:0x0447, B:156:0x047f, B:157:0x0488, B:158:0x04c2, B:160:0x04c6, B:161:0x04c9, B:163:0x04f5, B:166:0x04fc, B:168:0x0504, B:173:0x0526, B:175:0x052c, B:177:0x0539, B:179:0x0540, B:178:0x053d, B:180:0x0555, B:181:0x055a, B:170:0x050a, B:182:0x0566, B:184:0x056a, B:185:0x056d, B:187:0x0599, B:190:0x05a0, B:192:0x05a8, B:197:0x05ca, B:199:0x05d0, B:201:0x05dd, B:203:0x05e4, B:202:0x05e1, B:204:0x05f9, B:205:0x05fe, B:194:0x05ae, B:206:0x060a, B:208:0x060e, B:209:0x0611, B:211:0x0617, B:213:0x061d, B:214:0x062c, B:215:0x0643, B:217:0x0647, B:218:0x064a, B:220:0x0676, B:223:0x067d, B:225:0x0685, B:230:0x06a7, B:232:0x06ad, B:234:0x06c9, B:236:0x06d0, B:235:0x06cd, B:237:0x06e5, B:238:0x06ea, B:227:0x068b, B:19:0x0062, B:22:0x006d, B:25:0x0078, B:28:0x0083, B:31:0x008e, B:34:0x0099, B:37:0x00a5, B:40:0x00b1, B:43:0x00bd, B:46:0x00c8, B:49:0x00d3, B:52:0x00dd, B:55:0x00e7, B:58:0x00f0, B:61:0x00fb, B:64:0x0106, B:67:0x0111, B:70:0x011b, B:239:0x06f6, B:241:0x06fe, B:242:0x070a, B:299:0x07d5, B:301:0x07d9, B:303:0x07dd, B:304:0x07e0, B:244:0x070f, B:247:0x071b, B:250:0x0726, B:253:0x0732, B:256:0x073e, B:259:0x074a, B:262:0x0755, B:265:0x0760, B:268:0x076b, B:271:0x0777, B:274:0x0781, B:277:0x078a, B:280:0x0794, B:283:0x079f, B:286:0x07a9, B:289:0x07b3, B:292:0x07be, B:295:0x07c9, B:305:0x0812, B:306:0x081d), top: B:311:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:229:0x06a6  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x06ea A[Catch: Exception -> 0x0828, TryCatch #0 {Exception -> 0x0828, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x0037, B:16:0x005a, B:17:0x005d, B:77:0x0130, B:79:0x0134, B:80:0x0137, B:82:0x0141, B:85:0x0159, B:84:0x0147, B:86:0x0170, B:88:0x0174, B:89:0x0177, B:90:0x0199, B:92:0x019d, B:93:0x01a0, B:94:0x01e6, B:96:0x01ea, B:97:0x01ed, B:99:0x0219, B:101:0x021f, B:103:0x0227, B:105:0x022d, B:107:0x0233, B:108:0x0248, B:109:0x0254, B:111:0x0258, B:112:0x025b, B:113:0x02a1, B:115:0x02ab, B:116:0x02ae, B:117:0x02bd, B:119:0x02c1, B:120:0x02c4, B:121:0x031c, B:123:0x0320, B:124:0x0323, B:126:0x034f, B:129:0x0356, B:130:0x035f, B:132:0x0365, B:134:0x0372, B:136:0x0379, B:135:0x0376, B:137:0x038e, B:138:0x0393, B:140:0x03a9, B:142:0x03ad, B:143:0x03b0, B:147:0x03ee, B:148:0x0413, B:150:0x0417, B:151:0x041a, B:153:0x0440, B:154:0x0447, B:156:0x047f, B:157:0x0488, B:158:0x04c2, B:160:0x04c6, B:161:0x04c9, B:163:0x04f5, B:166:0x04fc, B:168:0x0504, B:173:0x0526, B:175:0x052c, B:177:0x0539, B:179:0x0540, B:178:0x053d, B:180:0x0555, B:181:0x055a, B:170:0x050a, B:182:0x0566, B:184:0x056a, B:185:0x056d, B:187:0x0599, B:190:0x05a0, B:192:0x05a8, B:197:0x05ca, B:199:0x05d0, B:201:0x05dd, B:203:0x05e4, B:202:0x05e1, B:204:0x05f9, B:205:0x05fe, B:194:0x05ae, B:206:0x060a, B:208:0x060e, B:209:0x0611, B:211:0x0617, B:213:0x061d, B:214:0x062c, B:215:0x0643, B:217:0x0647, B:218:0x064a, B:220:0x0676, B:223:0x067d, B:225:0x0685, B:230:0x06a7, B:232:0x06ad, B:234:0x06c9, B:236:0x06d0, B:235:0x06cd, B:237:0x06e5, B:238:0x06ea, B:227:0x068b, B:19:0x0062, B:22:0x006d, B:25:0x0078, B:28:0x0083, B:31:0x008e, B:34:0x0099, B:37:0x00a5, B:40:0x00b1, B:43:0x00bd, B:46:0x00c8, B:49:0x00d3, B:52:0x00dd, B:55:0x00e7, B:58:0x00f0, B:61:0x00fb, B:64:0x0106, B:67:0x0111, B:70:0x011b, B:239:0x06f6, B:241:0x06fe, B:242:0x070a, B:299:0x07d5, B:301:0x07d9, B:303:0x07dd, B:304:0x07e0, B:244:0x070f, B:247:0x071b, B:250:0x0726, B:253:0x0732, B:256:0x073e, B:259:0x074a, B:262:0x0755, B:265:0x0760, B:268:0x076b, B:271:0x0777, B:274:0x0781, B:277:0x078a, B:280:0x0794, B:283:0x079f, B:286:0x07a9, B:289:0x07b3, B:292:0x07be, B:295:0x07c9, B:305:0x0812, B:306:0x081d), top: B:311:0x0006 }] */
    @Override // com.coveiot.android.jstyle1860Sdk.JstyleResponseListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.jstyle1860Sdk.api.JstyleBaseRes r22) {
        /*
            Method dump skipped, instructions count: 2318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.onResponse(com.coveiot.android.jstyle1860Sdk.api.JstyleBaseRes):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.d == null) {
            this.d = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        KH1860BleService kH1860BleService = v;
        if (kH1860BleService != null) {
            if (kH1860BleService.getConnectionState() == Clove1860BleState.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else if (v.getConnectionState() == Clove1860BleState.BleState.CONNECTING) {
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
    @NotNull
    public MutableLiveData<LiveAGPSUploadPercentage> registerLiveAGPSUploadData() {
        if (this.k == null) {
            this.k = new MutableLiveData<>();
        }
        return this.k;
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
        if (this.f3030a == null) {
            this.f3030a = new MutableLiveData<>();
        }
        return this.f3030a;
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
        KH1860BleService kH1860BleService = v;
        if (kH1860BleService != null) {
            kH1860BleService.restartService();
            c();
        }
        new Handler().postDelayed(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.31
            @Override // java.lang.Runnable
            public void run() {
                JStyle1860BleApiImpl.h();
            }
        }, 5000L);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        clearCommandQueue();
        this.c.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(y).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.3
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(CharSequence charSequence) {
                        scanResultListener.onError(JStyle1860BleApiImpl.y.getString(R.string.scan_failed));
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(String str) {
                        JStyle1860BleApiImpl.a(JStyle1860BleApiImpl.this, new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.c.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!DeviceScanManager.getInstance(JStyle1860BleApiImpl.y).isScanningInProgress()) {
                        if (BleUtils.isEmpty(scanDeviceRequest.getScanFilter())) {
                            DeviceScanManager.getInstance(JStyle1860BleApiImpl.y).scanAllDevices(JStyle1860BleApiImpl.y, scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.4.1
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z2) {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    JStyle1860BleApiImpl.a(JStyle1860BleApiImpl.this, list, z2, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(JStyle1860BleApiImpl.y.getString(R.string.scan_failed));
                                }
                            });
                        } else {
                            DeviceScanManager.getInstance(JStyle1860BleApiImpl.y).scanDevicesWithFilter(JStyle1860BleApiImpl.y, scanDeviceRequest.getScanFilter(), scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl.4.2
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z2) {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    JStyle1860BleApiImpl.a(JStyle1860BleApiImpl.this, list, z2, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(JStyle1860BleApiImpl.y.getString(R.string.scan_failed));
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
        this.r = busyStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener connectionResultListener) {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(BleBaseRequest bleBaseRequest, SettingsResultListener settingsResultListener) {
        KH1860BleService kH1860BleService = v;
        if (kH1860BleService == null || kH1860BleService.getConnectionState() != Clove1860BleState.BleState.CONNECTED) {
            settingsResultListener.onSettingsError(new BleBaseError(y.getString(R.string.band_not_connected)));
        } else if (bleBaseRequest == null || settingsResultListener == null) {
            if (settingsResultListener != null) {
                Context context = y;
                int i = R.string.request_cannot_null;
                BleBaseError bleBaseError = new BleBaseError(context.getString(i));
                bleBaseError.setErrorMsg(y.getString(i));
                settingsResultListener.onSettingsError(bleBaseError);
            }
        } else if (c(bleBaseRequest)) {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            bleBaseRequest.setResponseListener(settingsResultListener);
            a(bleBaseRequest);
            if (w == null || w.size() <= 0) {
                return;
            }
            d();
        } else {
            LogHelper.d(u, "setUserSettings->Ignore {$request}");
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(y).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManager1860.getInstance(y).saveConnectedDeviceMAcAddress("");
        PreferenceManager1860.getInstance(y).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        KH1860BleService kH1860BleService = v;
        if (kH1860BleService != null) {
            kH1860BleService.stopService();
            c();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        clearCommandQueue();
        PreferenceManager1860.getInstance(y).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        KH1860BleService kH1860BleService = v;
        if (kH1860BleService != null) {
            kH1860BleService.stopServiceRetainMacAddress();
            c();
        }
    }

    public void unbindService() {
        ServiceConnection serviceConnection = z;
        if (serviceConnection != null) {
            y.unbindService(serviceConnection);
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

    public final boolean c(BleBaseRequest bleBaseRequest) {
        boolean z2 = true;
        try {
            BleBaseRequest bleBaseRequest2 = this.n;
            if (bleBaseRequest2 != null) {
                if (((bleBaseRequest2 instanceof CustomWatchFaceFileImageRequest) || (bleBaseRequest2 instanceof CustomWatchFaceBackgroundChangeRequest)) && (bleBaseRequest instanceof SetMessageContentRequest) && ((SetMessageContentRequest) bleBaseRequest).getAppNotificationType() != null && ((SetMessageContentRequest) bleBaseRequest).getAppNotificationType() == NotificationType.CALL) {
                    z2 = false;
                    LogHelper.d(u, "Ignore incoming call triggered during watch face upgrade");
                    return false;
                }
                return true;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return z2;
        }
    }

    public final void d(BleBaseRequest bleBaseRequest) {
        BaseListener responseListener = bleBaseRequest.getResponseListener();
        if (responseListener instanceof DataResultListener) {
            ((DataResultListener) responseListener).onDataError(new BleBaseError(y.getString(R.string.command_not_found)));
        } else if (responseListener instanceof SettingsResultListener) {
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(y.getString(R.string.command_not_found)));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, y.getString(R.string.command_not_found)));
        }
    }

    public static boolean a() {
        boolean z2 = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) y.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (KH1860BleService.class.getName().equals(runningServiceInfo.service.getClassName()) && y.getPackageName().equals(runningServiceInfo.service.getPackageName())) {
                z2 = true;
            }
        }
        return z2;
    }

    public final void a(JstyleError jstyleError) {
        synchronized (w) {
            if (w != null && w.size() > 0) {
                for (int i = 0; i < w.size(); i++) {
                    try {
                        BaseListener responseListener = w.get(i).f3063a.getResponseListener();
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
        for (int i = 0; i < w.size(); i++) {
            if (w.get(i).f3063a.getRequId().equalsIgnoreCase(jstyleBaseReq.getReqId())) {
                return w.remove(i).f3063a;
            }
        }
        return null;
    }

    public final void a(BleBaseRequest bleBaseRequest) {
        synchronized (w) {
            JstyleBaseReq b = b(bleBaseRequest);
            if (b != null) {
                if (b.isPriority()) {
                    w.addFirst(new QueueObject(this, bleBaseRequest));
                } else {
                    w.add(new QueueObject(this, bleBaseRequest));
                }
            } else if (!(bleBaseRequest instanceof ActivityModeWithSamplesRequest)) {
                d(bleBaseRequest);
            }
        }
    }

    public final void b(JstyleBaseReq jstyleBaseReq) {
        this.n = w.get(0).f3063a;
        if (jstyleBaseReq.isMultiPacket()) {
            if (jstyleBaseReq instanceof JstyleAgpsFileUpdateReq) {
                this.m.postDelayed(this.s, 180000L);
            } else if (jstyleBaseReq instanceof JstyleCustomWatchFaceReq) {
                this.m.postDelayed(this.s, 180000L);
            } else {
                this.m.postDelayed(this.s, com.clevertap.android.sdk.Constants.ONE_MIN_IN_MILLIS);
            }
        } else {
            this.m.postDelayed(this.s, 30000L);
        }
        v.sendRequest(jstyleBaseReq, this);
    }

    public final boolean b() {
        KHJstyleEntityDeviceInfo deviceInfoBy = KHJstyleDeviceInfoRepository.getInstance(y).getDeviceInfoBy(x);
        if (deviceInfoBy != null) {
            long lastSyncWalkTimeStamp = deviceInfoBy.getLastSyncWalkTimeStamp();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(lastSyncWalkTimeStamp);
            return JStyleUtils.getMinutesDifference(calendar, Calendar.getInstance()) > 2;
        }
        return true;
    }
}
