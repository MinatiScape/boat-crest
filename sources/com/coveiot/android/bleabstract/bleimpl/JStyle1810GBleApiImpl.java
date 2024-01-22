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
import com.coveiot.android.bleabstract.formatter.JStyle1810GFormatter;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.Clove1810GBleState;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.preferences.PreferenceManager1810G;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.FindMyWatchRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.LiveHeartRateControlRequest;
import com.coveiot.android.bleabstract.request.LiveRawPPGControlRequest;
import com.coveiot.android.bleabstract.request.LiveStepsControlRequest;
import com.coveiot.android.bleabstract.request.LiveTemperatureControlRequest;
import com.coveiot.android.bleabstract.request.MotorVibrationRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.OTAModeRequest;
import com.coveiot.android.bleabstract.request.ProbeDataRequest;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
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
import com.coveiot.android.bleabstract.request.SetWearingHandRequest;
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
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
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
import com.coveiot.android.bleabstract.services.KH1810GBleService;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleConstants;
import com.coveiot.android.jstyle1810gsdk.JstyleResponseListener;
import com.coveiot.android.jstyle1810gsdk.api.JStyleRawPPGControlReq;
import com.coveiot.android.jstyle1810gsdk.api.JStyleRealTimeWalkReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleActivityModeHistoryReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleBaseReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleBatteryLevelReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleDeviceBaseParametersReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleDeviceVersionReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleEnterOTAReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleHRTimeIntervalReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleHeartBeatPacketsIntervalReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleMotorVibrationReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleNotificationReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleOnceHRReq;
import com.coveiot.android.jstyle1810gsdk.api.JstylePersonalInfoReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleSedentaryReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleSleepReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleStepTargetReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleTemperatureCalibrationReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleTemperatureReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleTemperatureUnitReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleVibrationAlarmReq;
import com.coveiot.android.jstyle1810gsdk.api.JstyleWalkReq;
import com.coveiot.android.jstyle1810gsdk.error.JstyleError;
import com.coveiot.android.jstyle1810gsdk.error.JstyleErrorType;
import com.coveiot.android.jstyle1810gsdk.model.JstyleLiveResponse;
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
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.jstyle.sdk1810g.model.Clock;
import com.jstyle.sdk1810g.model.MyAutomaticHRMonitoring;
import com.jstyle.sdk1810g.model.MyDeviceInfo;
import com.jstyle.sdk1810g.model.MyPersonalInfo;
import com.jstyle.sdk1810g.model.MySedentaryReminder;
import com.jstyle.sdk1810g.model.Notifier;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class JStyle1810GBleApiImpl implements BleApi, JstyleResponseListener {
    public static KH1810GBleService bleService;
    public static Context context;
    public static JStyle1810GBleApiImpl o;
    public static String r;
    public static Intent serviceIntent;

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<PPGData> f3002a;
    public Handler b = new Handler();
    public Handler c = new Handler();
    public MutableLiveData<ConnectionStatus> connectionStateLiveData;
    public DeviceInfoData d;
    public MutableLiveData<LiveHealthData> e;
    public MutableLiveData<LiveStepsData> f;
    public MutableLiveData<LiveTemperatureData> g;
    public Handler h;
    public Handler i;
    public BleBaseRequest j;
    public ArrayList<Integer> k;
    public ConnectionResultListener l;
    public MutableLiveData<LiveECGDataResponse> liveECGDataMutableLiveData;
    public BusyStatus m;
    public Runnable n;
    public static final String p = JStyle1810GBleApiImpl.class.getSimpleName();
    public static volatile LinkedList<QueueObject> q = new LinkedList<>();
    public static ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            JStyle1810GBleApiImpl.bleService = ((KH1810GBleService.BtServiceBinder) iBinder).getService();
            if (TextUtils.isEmpty(JStyle1810GBleApiImpl.r)) {
                return;
            }
            JStyle1810GBleApiImpl.bleService.initBluetoothDevice();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            JStyle1810GBleApiImpl.bleService = null;
        }
    };

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl$23  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass23 implements SettingsResultListener {
        public AnonymousClass23() {
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsError(BleBaseError bleBaseError) {
            JStyle1810GBleApiImpl jStyle1810GBleApiImpl = JStyle1810GBleApiImpl.this;
            JStyle1810GBleApiImpl jStyle1810GBleApiImpl2 = JStyle1810GBleApiImpl.o;
            jStyle1810GBleApiImpl.getClass();
            jStyle1810GBleApiImpl.setUserSettings(new LiveTemperatureControlRequest.Builder(false).build(), new AnonymousClass24(jStyle1810GBleApiImpl));
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
            JStyle1810GBleApiImpl jStyle1810GBleApiImpl = JStyle1810GBleApiImpl.this;
            JStyle1810GBleApiImpl jStyle1810GBleApiImpl2 = JStyle1810GBleApiImpl.o;
            jStyle1810GBleApiImpl.getClass();
            jStyle1810GBleApiImpl.setUserSettings(new LiveTemperatureControlRequest.Builder(false).build(), new AnonymousClass24(jStyle1810GBleApiImpl));
        }
    }

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl$24  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass24 implements SettingsResultListener {
        public AnonymousClass24(JStyle1810GBleApiImpl jStyle1810GBleApiImpl) {
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsError(BleBaseError bleBaseError) {
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
        }
    }

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl$27  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass27 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3019a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            Clove1810GBleState.BleState.values();
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
                NotificationType notificationType8 = NotificationType.LINE;
                iArr9[13] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr10 = new int[JstyleErrorType.values().length];
            f3019a = iArr10;
            try {
                iArr10[JstyleErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f3019a[JstyleErrorType.COMMAND_TIME_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public class QueueObject {

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3029a;

        public QueueObject(JStyle1810GBleApiImpl jStyle1810GBleApiImpl, BleBaseRequest bleBaseRequest) {
            this.f3029a = bleBaseRequest;
        }
    }

    public JStyle1810GBleApiImpl() {
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        this.h = new Handler(Looper.getMainLooper());
        this.i = new Handler();
        this.j = null;
        this.k = new ArrayList<>();
        this.l = null;
        this.m = BusyStatus.SYNCING;
        this.n = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.26
            @Override // java.lang.Runnable
            public void run() {
                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                LogHelper.d("Command TimeOut", "Failed", moduleNames.getModuleName());
                JStyle1810GBleApiImpl jStyle1810GBleApiImpl = JStyle1810GBleApiImpl.this;
                BleBaseRequest bleBaseRequest = jStyle1810GBleApiImpl.j;
                if (bleBaseRequest != null) {
                    JstyleBaseReq a2 = jStyle1810GBleApiImpl.a(bleBaseRequest);
                    if (a2 != null) {
                        JStyle1810GBleApiImpl.this.onFailure(new JstyleError(JstyleErrorType.COMMAND_TIME_OUT, JStyle1810GBleApiImpl.context.getString(R.string.command_time_out)));
                        LogHelper.e("Command TimeOut", "End Time " + System.currentTimeMillis() + "-- currentCommand " + a2.getDataType(), moduleNames.getModuleName());
                    } else {
                        JStyle1810GBleApiImpl jStyle1810GBleApiImpl2 = JStyle1810GBleApiImpl.this;
                        jStyle1810GBleApiImpl2.b(jStyle1810GBleApiImpl2.j);
                    }
                }
                JStyle1810GBleApiImpl.this.j = null;
            }
        };
        registerEvenBus();
    }

    public static void a(JStyle1810GBleApiImpl jStyle1810GBleApiImpl, List list, boolean z, ScanDeviceRequest scanDeviceRequest, ScanResultListener scanResultListener) {
        jStyle1810GBleApiImpl.getClass();
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

    public static void c() {
        try {
            Intent intent = new Intent(context, KH1810GBleService.class);
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
    }

    public static boolean checkIfServiceIsRunning() {
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (KH1810GBleService.class.getName().equals(runningServiceInfo.service.getClassName()) && context.getPackageName().equals(runningServiceInfo.service.getPackageName())) {
                z = true;
            }
        }
        return z;
    }

    public static JStyle1810GBleApiImpl getInstance(Context context2) {
        if (o == null) {
            context = context2.getApplicationContext();
            o = new JStyle1810GBleApiImpl();
        }
        if (!checkIfServiceIsRunning()) {
            LogHelper.d(p, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
            c();
        }
        return o;
    }

    public void addToQueue(BleBaseRequest bleBaseRequest) {
        synchronized (q) {
            JstyleBaseReq a2 = a(bleBaseRequest);
            if (a2 != null) {
                if (a2.isPriority()) {
                    q.addFirst(new QueueObject(this, bleBaseRequest));
                } else {
                    q.add(new QueueObject(this, bleBaseRequest));
                }
            } else {
                b(bleBaseRequest);
            }
        }
    }

    public final void b() {
        BleBaseRequest bleBaseRequest = this.j;
        if (bleBaseRequest != null) {
            bleBaseRequest.setComplete(true);
        }
        a();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return false;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void cleanUpCommands() {
        setUserSettings(new LiveStepsControlRequest.Builder(false).build(), new SettingsResultListener() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.22
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(BleBaseError bleBaseError) {
                JStyle1810GBleApiImpl jStyle1810GBleApiImpl = JStyle1810GBleApiImpl.this;
                JStyle1810GBleApiImpl jStyle1810GBleApiImpl2 = JStyle1810GBleApiImpl.o;
                jStyle1810GBleApiImpl.getClass();
                jStyle1810GBleApiImpl.setUserSettings(new LiveHeartRateControlRequest.Builder(false).build(), new AnonymousClass23());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
                JStyle1810GBleApiImpl jStyle1810GBleApiImpl = JStyle1810GBleApiImpl.this;
                JStyle1810GBleApiImpl jStyle1810GBleApiImpl2 = JStyle1810GBleApiImpl.o;
                jStyle1810GBleApiImpl.getClass();
                jStyle1810GBleApiImpl.setUserSettings(new LiveHeartRateControlRequest.Builder(false).build(), new AnonymousClass23());
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void clearCommandQueue() {
        synchronized (q) {
            if (q != null && q.size() > 0) {
                q.clear();
            }
            KH1810GBleService kH1810GBleService = bleService;
            if (kH1810GBleService != null) {
                kH1810GBleService.clearQueue();
            }
        }
        Handler handler = this.i;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.j = null;
    }

    public void clearParameters() {
        if (DeviceScanManager.getInstance(context).isScanningInProgress()) {
            DeviceScanManager.getInstance(context).stopScan();
        }
        bleService = null;
        unbindService();
        Handler handler = this.b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.c;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.i;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(final ConnectRequest connectRequest, final ConnectionResultListener connectionResultListener) {
        this.b.removeCallbacksAndMessages(null);
        this.l = connectionResultListener;
        this.b.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.5
            @Override // java.lang.Runnable
            public void run() {
                KH1810GBleService kH1810GBleService = JStyle1810GBleApiImpl.bleService;
                if (kH1810GBleService == null || connectRequest == null) {
                    if (kH1810GBleService == null) {
                        JStyle1810GBleApiImpl.c();
                        connectionResultListener.onError(new Error(Type.SERVICE_NOT_RUNNING, JStyle1810GBleApiImpl.context.getString(R.string.service_not_running)));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.COMMAND_REQUEST_ERROR, JStyle1810GBleApiImpl.context.getString(R.string.request_should_not_null)));
                    return;
                }
                JStyle1810GBleApiImpl.r = JStyle1810GBleApiImpl.this.getMacAddress();
                Clove1810GBleState.BleState connectionState = JStyle1810GBleApiImpl.bleService.getConnectionState();
                Clove1810GBleState.BleState bleState = Clove1810GBleState.BleState.CONNECTED;
                if (connectionState == bleState && JStyle1810GBleApiImpl.r.equalsIgnoreCase(connectRequest.getMacAddress())) {
                    connectionResultListener.onConnectionResponse(ConnectionStatus.CONNECTED);
                } else if (JStyle1810GBleApiImpl.bleService.getConnectionState() == Clove1810GBleState.BleState.DISCONNECTED) {
                    BleBaseRequest bleBaseRequest = new BleBaseRequest();
                    bleBaseRequest.setBleCommand(BleCommand.CONNECT);
                    bleBaseRequest.setResponseListener(connectionResultListener);
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    JStyle1810GBleApiImpl.bleService.connect(connectRequest.getMacAddress());
                } else if (JStyle1810GBleApiImpl.bleService.getConnectionState() == bleState) {
                    KH1810GBleService kH1810GBleService2 = JStyle1810GBleApiImpl.bleService;
                    if (kH1810GBleService2.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, "Connected to band " + JStyle1810GBleApiImpl.bleService.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, kH1810GBleService2.getConnectionState().getStateAsString()));
                } else if (JStyle1810GBleApiImpl.bleService.getConnectionState() == Clove1810GBleState.BleState.CONNECTING) {
                    KH1810GBleService kH1810GBleService3 = JStyle1810GBleApiImpl.bleService;
                    if (kH1810GBleService3.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, "Connection in progress " + JStyle1810GBleApiImpl.bleService.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, kH1810GBleService3.getConnectionState().getStateAsString()));
                } else {
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, JStyle1810GBleApiImpl.bleService.getConnectionState().getStateAsString()));
                }
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(ConnectionResultListener connectionResultListener) {
        KH1810GBleService kH1810GBleService = bleService;
        if (kH1810GBleService != null) {
            kH1810GBleService.disconnectAndForget();
            bleService.clearQueue();
            clearParameters();
            if (connectionResultListener != null) {
                connectionResultListener.onConnectionResponse(ConnectionStatus.DISCONNECTED);
                return;
            }
            return;
        }
        connectionResultListener.onError(new Error(Type.SERVICE_NOT_RUNNING, "service is not present"));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return this.m;
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
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        KH1810GBleService kH1810GBleService = bleService;
        if (kH1810GBleService != null) {
            if (kH1810GBleService.getConnectionState() == Clove1810GBleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            return bleService.getConnectionState() == Clove1810GBleState.BleState.CONNECTING ? ConnectionStatus.CONNECTING : connectionStatus;
        }
        return connectionStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        KH1810GBleService kH1810GBleService = bleService;
        if (kH1810GBleService == null || kH1810GBleService.getConnectionState() != Clove1810GBleState.BleState.CONNECTED) {
            dataResultListener.onDataError(new BleBaseError(context.getString(R.string.band_not_connected)));
        } else if (bleBaseRequest == null || dataResultListener == null) {
            if (dataResultListener != null) {
                Context context2 = context;
                int i = R.string.request_cannot_null;
                BleBaseError bleBaseError = new BleBaseError(context2.getString(i));
                bleBaseError.setErrorMsg(context.getString(i));
                dataResultListener.onDataError(bleBaseError);
            }
        } else {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            bleBaseRequest.setResponseListener(dataResultListener);
            addToQueue(bleBaseRequest);
            if (q == null || q.size() <= 0) {
                return;
            }
            a();
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
        deviceSupportedFeatures.setProbeFeatureSupported(true);
        deviceSupportedFeatures.setAutoHrSettingsSupported(true);
        deviceSupportedFeatures.setMultipleAlarmsSupportedAtATime(true);
        deviceSupportedFeatures.setOnceAlarmSupported(false);
        deviceSupportedFeatures.setSportsModeHistorySupported(true);
        deviceSupportedFeatures.setSampleDataSupportedInSportMode(false);
        deviceSupportedFeatures.setSportModeSupportedFromApp(false);
        deviceSupportedFeatures.setDeviceSettingsSupportedInOneCommand(true);
        deviceSupportedFeatures.setAppSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setBandSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setMaxCharSupportedInNotification(60);
        deviceSupportedFeatures.setDndSupported(false);
        return deviceSupportedFeatures;
    }

    public BleBaseRequest getFromQueue(JstyleBaseReq jstyleBaseReq) {
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i).f3029a.getRequId().equalsIgnoreCase(jstyleBaseReq.getReqId())) {
                return q.remove(i).f3029a;
            }
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManager1810G.getInstance(context).getConnectedDeviceMacAddress();
        r = connectedDeviceMacAddress;
        return connectedDeviceMacAddress;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return BleApiUtils.isServiceRunning(KH1810GBleService.class.getName(), context);
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
    public void onConnectionStateChanged(com.coveiot.android.bleabstract.models.Clove1810GBleState r3) {
        /*
            r2 = this;
            com.coveiot.android.bleabstract.models.ConnectionStatus r0 = com.coveiot.android.bleabstract.models.ConnectionStatus.DISCONNECTED
            if (r3 == 0) goto L18
            com.coveiot.android.bleabstract.models.Clove1810GBleState$BleState r3 = r3.getmState()
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
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r2.connectionStateLiveData
            if (r1 == 0) goto L25
            r1.setValue(r3)
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r2.connectionStateLiveData
            r1.postValue(r3)
        L25:
            com.coveiot.android.bleabstract.listeners.ConnectionResultListener r1 = r2.l
            if (r1 == 0) goto L2c
            r1.onConnectionResponse(r3)
        L2c:
            if (r3 != r0) goto L3a
            com.coveiot.android.jstyle1810gsdk.error.JstyleError r3 = new com.coveiot.android.jstyle1810gsdk.error.JstyleError
            com.coveiot.android.jstyle1810gsdk.error.JstyleErrorType r0 = com.coveiot.android.jstyle1810gsdk.error.JstyleErrorType.DEVICE_NOT_CONNECTED
            java.lang.String r1 = "Device disconnected"
            r3.<init>(r0, r1)
            r2.a(r3)
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.onConnectionStateChanged(com.coveiot.android.bleabstract.models.Clove1810GBleState):void");
    }

    public void onFailure(JstyleError jstyleError) {
        if (jstyleError != null) {
            int i = AnonymousClass27.f3019a[jstyleError.getErrorType().ordinal()];
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
            case -639880425:
                if (dataType.equals("RealTimeStep")) {
                    c = 0;
                    break;
                }
                break;
            case 343843441:
                if (dataType.equals("PPGDATA")) {
                    c = 1;
                    break;
                }
                break;
            case 1669104719:
                if (dataType.equals("HeartBeatPackets")) {
                    c = 2;
                    break;
                }
                break;
            case 2049043094:
                if (dataType.equals("FindMobilePhoneMode")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (jstyleLiveResponse.getObj() != null) {
                    Map<String, String> map = (Map) jstyleLiveResponse.getObj();
                    LiveStepsData convertToLiveStepsData = new JStyle1810GFormatter(getMacAddress()).convertToLiveStepsData(map);
                    MutableLiveData<LiveStepsData> mutableLiveData = this.f;
                    if (mutableLiveData != null) {
                        mutableLiveData.setValue(convertToLiveStepsData);
                        this.f.postValue(convertToLiveStepsData);
                    }
                    LiveHealthData convertToLiveHealthData = new JStyle1810GFormatter(getMacAddress()).convertToLiveHealthData(map);
                    MutableLiveData<LiveHealthData> mutableLiveData2 = this.e;
                    if (mutableLiveData2 != null) {
                        mutableLiveData2.setValue(convertToLiveHealthData);
                        this.e.postValue(convertToLiveHealthData);
                    }
                    LiveTemperatureData convertToLiveTemperatureData = new JStyle1810GFormatter(getMacAddress()).convertToLiveTemperatureData(map);
                    MutableLiveData<LiveTemperatureData> mutableLiveData3 = this.g;
                    if (mutableLiveData3 != null) {
                        mutableLiveData3.setValue(convertToLiveTemperatureData);
                        this.g.postValue(convertToLiveTemperatureData);
                        return;
                    }
                    return;
                }
                return;
            case 1:
                Map map2 = (Map) jstyleLiveResponse.getObj();
                if (this.k.size() < 10) {
                    this.k.add(Integer.valueOf(Integer.parseInt((String) map2.get(DeviceKey.PPGValue))));
                    return;
                }
                PPGData pPGData = new PPGData((ArrayList) this.k.clone());
                MutableLiveData<PPGData> mutableLiveData4 = this.f3002a;
                if (mutableLiveData4 != null) {
                    mutableLiveData4.setValue(pPGData);
                }
                this.k.clear();
                return;
            case 2:
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Constants.PROBE_DATA_BROADCAST_INTENT));
                LogHelper.d(p, "Local broadcast sent for probe packet", ModuleNames.BLEABSTRACT.getModuleName());
                return;
            case 3:
                FindMyPhoneRes findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.ON);
                Intent intent = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
                intent.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0377 A[Catch: Exception -> 0x05d1, TryCatch #0 {Exception -> 0x05d1, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0012, B:9:0x001a, B:11:0x002b, B:12:0x0046, B:39:0x0097, B:41:0x009b, B:42:0x009e, B:44:0x00ca, B:47:0x00d1, B:48:0x00d8, B:50:0x00de, B:52:0x00eb, B:54:0x00f2, B:53:0x00ef, B:55:0x0107, B:56:0x010c, B:58:0x0122, B:60:0x0126, B:61:0x0129, B:63:0x0155, B:66:0x015c, B:68:0x0164, B:73:0x0186, B:75:0x018c, B:77:0x0199, B:79:0x01a0, B:78:0x019d, B:80:0x01b5, B:81:0x01ba, B:70:0x016a, B:82:0x01c6, B:84:0x01ca, B:85:0x01cd, B:89:0x020b, B:90:0x0230, B:92:0x0234, B:93:0x0237, B:95:0x025d, B:96:0x0264, B:98:0x029c, B:99:0x02a5, B:100:0x02df, B:102:0x02e3, B:103:0x02e6, B:105:0x0312, B:108:0x0319, B:110:0x0321, B:115:0x0343, B:117:0x0349, B:119:0x0356, B:121:0x035d, B:120:0x035a, B:122:0x0372, B:123:0x0377, B:112:0x0327, B:124:0x0383, B:126:0x0387, B:127:0x038a, B:129:0x03b6, B:132:0x03bd, B:134:0x03c5, B:139:0x03e7, B:141:0x03ed, B:143:0x03fa, B:145:0x0401, B:144:0x03fe, B:146:0x0416, B:147:0x041b, B:136:0x03cb, B:148:0x0427, B:150:0x042b, B:151:0x042e, B:153:0x045a, B:156:0x0461, B:158:0x0469, B:163:0x048b, B:165:0x0491, B:167:0x04a1, B:169:0x04a8, B:168:0x04a5, B:170:0x04bd, B:171:0x04c2, B:160:0x046f, B:14:0x004a, B:17:0x0054, B:20:0x005d, B:23:0x0067, B:26:0x0071, B:29:0x007b, B:32:0x0085, B:172:0x04ce, B:174:0x04d6, B:175:0x04e2, B:220:0x057e, B:222:0x0582, B:224:0x0586, B:225:0x0589, B:177:0x04e7, B:180:0x04f2, B:183:0x04fd, B:186:0x0508, B:189:0x0513, B:192:0x051f, B:195:0x052a, B:198:0x0535, B:201:0x053e, B:204:0x0549, B:207:0x0553, B:210:0x055d, B:213:0x0568, B:216:0x0572, B:226:0x05bb, B:227:0x05c6), top: B:232:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x041b A[Catch: Exception -> 0x05d1, TryCatch #0 {Exception -> 0x05d1, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0012, B:9:0x001a, B:11:0x002b, B:12:0x0046, B:39:0x0097, B:41:0x009b, B:42:0x009e, B:44:0x00ca, B:47:0x00d1, B:48:0x00d8, B:50:0x00de, B:52:0x00eb, B:54:0x00f2, B:53:0x00ef, B:55:0x0107, B:56:0x010c, B:58:0x0122, B:60:0x0126, B:61:0x0129, B:63:0x0155, B:66:0x015c, B:68:0x0164, B:73:0x0186, B:75:0x018c, B:77:0x0199, B:79:0x01a0, B:78:0x019d, B:80:0x01b5, B:81:0x01ba, B:70:0x016a, B:82:0x01c6, B:84:0x01ca, B:85:0x01cd, B:89:0x020b, B:90:0x0230, B:92:0x0234, B:93:0x0237, B:95:0x025d, B:96:0x0264, B:98:0x029c, B:99:0x02a5, B:100:0x02df, B:102:0x02e3, B:103:0x02e6, B:105:0x0312, B:108:0x0319, B:110:0x0321, B:115:0x0343, B:117:0x0349, B:119:0x0356, B:121:0x035d, B:120:0x035a, B:122:0x0372, B:123:0x0377, B:112:0x0327, B:124:0x0383, B:126:0x0387, B:127:0x038a, B:129:0x03b6, B:132:0x03bd, B:134:0x03c5, B:139:0x03e7, B:141:0x03ed, B:143:0x03fa, B:145:0x0401, B:144:0x03fe, B:146:0x0416, B:147:0x041b, B:136:0x03cb, B:148:0x0427, B:150:0x042b, B:151:0x042e, B:153:0x045a, B:156:0x0461, B:158:0x0469, B:163:0x048b, B:165:0x0491, B:167:0x04a1, B:169:0x04a8, B:168:0x04a5, B:170:0x04bd, B:171:0x04c2, B:160:0x046f, B:14:0x004a, B:17:0x0054, B:20:0x005d, B:23:0x0067, B:26:0x0071, B:29:0x007b, B:32:0x0085, B:172:0x04ce, B:174:0x04d6, B:175:0x04e2, B:220:0x057e, B:222:0x0582, B:224:0x0586, B:225:0x0589, B:177:0x04e7, B:180:0x04f2, B:183:0x04fd, B:186:0x0508, B:189:0x0513, B:192:0x051f, B:195:0x052a, B:198:0x0535, B:201:0x053e, B:204:0x0549, B:207:0x0553, B:210:0x055d, B:213:0x0568, B:216:0x0572, B:226:0x05bb, B:227:0x05c6), top: B:232:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x04c2 A[Catch: Exception -> 0x05d1, TryCatch #0 {Exception -> 0x05d1, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0012, B:9:0x001a, B:11:0x002b, B:12:0x0046, B:39:0x0097, B:41:0x009b, B:42:0x009e, B:44:0x00ca, B:47:0x00d1, B:48:0x00d8, B:50:0x00de, B:52:0x00eb, B:54:0x00f2, B:53:0x00ef, B:55:0x0107, B:56:0x010c, B:58:0x0122, B:60:0x0126, B:61:0x0129, B:63:0x0155, B:66:0x015c, B:68:0x0164, B:73:0x0186, B:75:0x018c, B:77:0x0199, B:79:0x01a0, B:78:0x019d, B:80:0x01b5, B:81:0x01ba, B:70:0x016a, B:82:0x01c6, B:84:0x01ca, B:85:0x01cd, B:89:0x020b, B:90:0x0230, B:92:0x0234, B:93:0x0237, B:95:0x025d, B:96:0x0264, B:98:0x029c, B:99:0x02a5, B:100:0x02df, B:102:0x02e3, B:103:0x02e6, B:105:0x0312, B:108:0x0319, B:110:0x0321, B:115:0x0343, B:117:0x0349, B:119:0x0356, B:121:0x035d, B:120:0x035a, B:122:0x0372, B:123:0x0377, B:112:0x0327, B:124:0x0383, B:126:0x0387, B:127:0x038a, B:129:0x03b6, B:132:0x03bd, B:134:0x03c5, B:139:0x03e7, B:141:0x03ed, B:143:0x03fa, B:145:0x0401, B:144:0x03fe, B:146:0x0416, B:147:0x041b, B:136:0x03cb, B:148:0x0427, B:150:0x042b, B:151:0x042e, B:153:0x045a, B:156:0x0461, B:158:0x0469, B:163:0x048b, B:165:0x0491, B:167:0x04a1, B:169:0x04a8, B:168:0x04a5, B:170:0x04bd, B:171:0x04c2, B:160:0x046f, B:14:0x004a, B:17:0x0054, B:20:0x005d, B:23:0x0067, B:26:0x0071, B:29:0x007b, B:32:0x0085, B:172:0x04ce, B:174:0x04d6, B:175:0x04e2, B:220:0x057e, B:222:0x0582, B:224:0x0586, B:225:0x0589, B:177:0x04e7, B:180:0x04f2, B:183:0x04fd, B:186:0x0508, B:189:0x0513, B:192:0x051f, B:195:0x052a, B:198:0x0535, B:201:0x053e, B:204:0x0549, B:207:0x0553, B:210:0x055d, B:213:0x0568, B:216:0x0572, B:226:0x05bb, B:227:0x05c6), top: B:232:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01ba A[Catch: Exception -> 0x05d1, TryCatch #0 {Exception -> 0x05d1, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0012, B:9:0x001a, B:11:0x002b, B:12:0x0046, B:39:0x0097, B:41:0x009b, B:42:0x009e, B:44:0x00ca, B:47:0x00d1, B:48:0x00d8, B:50:0x00de, B:52:0x00eb, B:54:0x00f2, B:53:0x00ef, B:55:0x0107, B:56:0x010c, B:58:0x0122, B:60:0x0126, B:61:0x0129, B:63:0x0155, B:66:0x015c, B:68:0x0164, B:73:0x0186, B:75:0x018c, B:77:0x0199, B:79:0x01a0, B:78:0x019d, B:80:0x01b5, B:81:0x01ba, B:70:0x016a, B:82:0x01c6, B:84:0x01ca, B:85:0x01cd, B:89:0x020b, B:90:0x0230, B:92:0x0234, B:93:0x0237, B:95:0x025d, B:96:0x0264, B:98:0x029c, B:99:0x02a5, B:100:0x02df, B:102:0x02e3, B:103:0x02e6, B:105:0x0312, B:108:0x0319, B:110:0x0321, B:115:0x0343, B:117:0x0349, B:119:0x0356, B:121:0x035d, B:120:0x035a, B:122:0x0372, B:123:0x0377, B:112:0x0327, B:124:0x0383, B:126:0x0387, B:127:0x038a, B:129:0x03b6, B:132:0x03bd, B:134:0x03c5, B:139:0x03e7, B:141:0x03ed, B:143:0x03fa, B:145:0x0401, B:144:0x03fe, B:146:0x0416, B:147:0x041b, B:136:0x03cb, B:148:0x0427, B:150:0x042b, B:151:0x042e, B:153:0x045a, B:156:0x0461, B:158:0x0469, B:163:0x048b, B:165:0x0491, B:167:0x04a1, B:169:0x04a8, B:168:0x04a5, B:170:0x04bd, B:171:0x04c2, B:160:0x046f, B:14:0x004a, B:17:0x0054, B:20:0x005d, B:23:0x0067, B:26:0x0071, B:29:0x007b, B:32:0x0085, B:172:0x04ce, B:174:0x04d6, B:175:0x04e2, B:220:0x057e, B:222:0x0582, B:224:0x0586, B:225:0x0589, B:177:0x04e7, B:180:0x04f2, B:183:0x04fd, B:186:0x0508, B:189:0x0513, B:192:0x051f, B:195:0x052a, B:198:0x0535, B:201:0x053e, B:204:0x0549, B:207:0x0553, B:210:0x055d, B:213:0x0568, B:216:0x0572, B:226:0x05bb, B:227:0x05c6), top: B:232:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.jstyle1810gsdk.api.JstyleBaseRes r15) {
        /*
            Method dump skipped, instructions count: 1642
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.onResponse(com.coveiot.android.jstyle1810gsdk.api.JstyleBaseRes):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.connectionStateLiveData == null) {
            this.connectionStateLiveData = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        KH1810GBleService kH1810GBleService = bleService;
        if (kH1810GBleService != null) {
            if (kH1810GBleService.getConnectionState() == Clove1810GBleState.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else if (bleService.getConnectionState() == Clove1810GBleState.BleState.CONNECTING) {
                connectionStatus = ConnectionStatus.CONNECTING;
            }
        }
        this.connectionStateLiveData.postValue(connectionStatus);
        return this.connectionStateLiveData;
    }

    public void registerEvenBus() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.2
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().register(JStyle1810GBleApiImpl.this);
            }
        });
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
        if (this.g == null) {
            this.g = new MutableLiveData<>();
        }
        return this.g;
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
        if (this.e == null) {
            this.e = new MutableLiveData<>();
        }
        return this.e;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public MutableLiveData<PPGData> registerLivePPGData() {
        if (this.f3002a == null) {
            this.f3002a = new MutableLiveData<>();
        }
        return this.f3002a;
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
        if (this.f == null) {
            this.f = new MutableLiveData<>();
        }
        return this.f;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<LiveWatchFaceUploadPercentage> registerLiveWatchFaceUploadData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        clearCommandQueue();
        KH1810GBleService kH1810GBleService = bleService;
        if (kH1810GBleService != null) {
            kH1810GBleService.restartService();
            clearParameters();
        }
        new Handler().postDelayed(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.25
            @Override // java.lang.Runnable
            public void run() {
                JStyle1810GBleApiImpl.c();
            }
        }, 5000L);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        clearCommandQueue();
        this.c.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(context).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.3
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(CharSequence charSequence) {
                        scanResultListener.onError(JStyle1810GBleApiImpl.context.getString(R.string.scan_failed));
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(String str) {
                        JStyle1810GBleApiImpl.a(JStyle1810GBleApiImpl.this, new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.c.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!DeviceScanManager.getInstance(JStyle1810GBleApiImpl.context).isScanningInProgress()) {
                        if (BleUtils.isEmpty(scanDeviceRequest.getScanFilter())) {
                            DeviceScanManager.getInstance(JStyle1810GBleApiImpl.context).scanAllDevices(JStyle1810GBleApiImpl.context, scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.4.1
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    JStyle1810GBleApiImpl.a(JStyle1810GBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(JStyle1810GBleApiImpl.context.getString(R.string.scan_failed));
                                }
                            });
                        } else {
                            DeviceScanManager.getInstance(JStyle1810GBleApiImpl.context).scanDevicesWithFilter(JStyle1810GBleApiImpl.context, scanDeviceRequest.getScanFilter(), scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl.4.2
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    JStyle1810GBleApiImpl.a(JStyle1810GBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(JStyle1810GBleApiImpl.context.getString(R.string.scan_failed));
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
        this.m = busyStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener connectionResultListener) {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(BleBaseRequest bleBaseRequest, SettingsResultListener settingsResultListener) {
        KH1810GBleService kH1810GBleService = bleService;
        if (kH1810GBleService == null || kH1810GBleService.getConnectionState() != Clove1810GBleState.BleState.CONNECTED) {
            settingsResultListener.onSettingsError(new BleBaseError(context.getString(R.string.band_not_connected)));
        } else if (bleBaseRequest == null || settingsResultListener == null) {
            if (settingsResultListener != null) {
                Context context2 = context;
                int i = R.string.request_cannot_null;
                BleBaseError bleBaseError = new BleBaseError(context2.getString(i));
                bleBaseError.setErrorMsg(context.getString(i));
                settingsResultListener.onSettingsError(bleBaseError);
            }
        } else {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            bleBaseRequest.setResponseListener(settingsResultListener);
            addToQueue(bleBaseRequest);
            if (q == null || q.size() <= 0) {
                return;
            }
            a();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(context).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManager1810G.getInstance(context).saveConnectedDeviceMAcAddress("");
        PreferenceManager1810G.getInstance(context).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        KH1810GBleService kH1810GBleService = bleService;
        if (kH1810GBleService != null) {
            kH1810GBleService.stopService();
            clearParameters();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        clearCommandQueue();
        PreferenceManager1810G.getInstance(context).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        KH1810GBleService kH1810GBleService = bleService;
        if (kH1810GBleService != null) {
            kH1810GBleService.stopServiceRetainMacAddress();
            clearParameters();
        }
    }

    public void unbindService() {
        ServiceConnection serviceConnection2 = serviceConnection;
        if (serviceConnection2 != null) {
            context.unbindService(serviceConnection2);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
    }

    public final void b(BleBaseRequest bleBaseRequest) {
        BaseListener responseListener = bleBaseRequest.getResponseListener();
        if (responseListener instanceof DataResultListener) {
            ((DataResultListener) responseListener).onDataError(new BleBaseError(context.getString(R.string.command_not_found)));
        } else if (responseListener instanceof SettingsResultListener) {
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(context.getString(R.string.command_not_found)));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, context.getString(R.string.command_not_found)));
        }
    }

    public final void a(JstyleError jstyleError) {
        synchronized (q) {
            if (q != null && q.size() > 0) {
                try {
                    Iterator it = ((LinkedList) q.clone()).iterator();
                    while (it.hasNext()) {
                        QueueObject queueObject = (QueueObject) it.next();
                        BaseListener responseListener = queueObject.f3029a.getResponseListener();
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
                        q.remove(queueObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clearCommandQueue();
            }
        }
    }

    public final JstyleDeviceBaseParametersReq a(MyDeviceInfo myDeviceInfo, BleBaseRequest bleBaseRequest) {
        JstyleDeviceBaseParametersReq jstyleDeviceBaseParametersReq = new JstyleDeviceBaseParametersReq();
        jstyleDeviceBaseParametersReq.setDeviceBaseParameter(myDeviceInfo);
        jstyleDeviceBaseParametersReq.setReqId(bleBaseRequest.getRequId());
        jstyleDeviceBaseParametersReq.setDataType("SetDeviceInfo");
        return jstyleDeviceBaseParametersReq;
    }

    public final JstyleBaseReq a(BleBaseRequest bleBaseRequest) {
        int type_stop_tel_1810;
        int type_call_1810;
        int[] iArr;
        if (bleBaseRequest instanceof StepsDataRequest) {
            JstyleWalkReq jstyleWalkReq = new JstyleWalkReq();
            jstyleWalkReq.setReqId(bleBaseRequest.getRequId());
            jstyleWalkReq.setDataType("GetDetailActivityData");
            jstyleWalkReq.setMode(JStyleConstants.Constants.getMODE_START());
            return jstyleWalkReq;
        } else if (bleBaseRequest instanceof SleepDataRequest) {
            JstyleSleepReq jstyleSleepReq = new JstyleSleepReq();
            jstyleSleepReq.setReqId(bleBaseRequest.getRequId());
            jstyleSleepReq.setDataType("GetDetailSleepData");
            jstyleSleepReq.setMode(JStyleConstants.Constants.getMODE_START());
            return jstyleSleepReq;
        } else if (bleBaseRequest instanceof HeartRateDataRequest) {
            JstyleOnceHRReq jstyleOnceHRReq = new JstyleOnceHRReq();
            jstyleOnceHRReq.setReqId(bleBaseRequest.getRequId());
            jstyleOnceHRReq.setDataType("GetStaticHR");
            jstyleOnceHRReq.setMode(JStyleConstants.Constants.getMODE_START());
            return jstyleOnceHRReq;
        } else {
            if (bleBaseRequest instanceof DeviceInfoRequest) {
                this.d = null;
                DeviceInfoRequest deviceInfoRequest = (DeviceInfoRequest) bleBaseRequest;
                if (deviceInfoRequest.isMacAddress()) {
                    if (this.d == null) {
                        this.d = new DeviceInfoData();
                    }
                    this.d.setMacAddress(getMacAddress());
                }
                if (deviceInfoRequest.isFwVersion()) {
                    JstyleDeviceVersionReq jstyleDeviceVersionReq = new JstyleDeviceVersionReq();
                    jstyleDeviceVersionReq.setReqId(bleBaseRequest.getRequId());
                    jstyleDeviceVersionReq.setDataType("GetDeviceVersion");
                    return jstyleDeviceVersionReq;
                }
            } else if (bleBaseRequest instanceof BatteryLevelRequest) {
                JstyleBatteryLevelReq jstyleBatteryLevelReq = new JstyleBatteryLevelReq();
                jstyleBatteryLevelReq.setReqId(bleBaseRequest.getRequId());
                jstyleBatteryLevelReq.setDataType("GetDeviceBatteryLevel");
                return jstyleBatteryLevelReq;
            } else if (bleBaseRequest instanceof TemperatureDataRequest) {
                JstyleTemperatureReq jstyleTemperatureReq = new JstyleTemperatureReq();
                jstyleTemperatureReq.setReqId(bleBaseRequest.getRequId());
                jstyleTemperatureReq.setDataType("GetTempHistoryData");
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
                jstylePersonalInfoReq.setDataType("SetPersonalInfo");
                jstylePersonalInfoReq.setReqId(bleBaseRequest.getRequId());
                return jstylePersonalInfoReq;
            } else if (bleBaseRequest instanceof StepsTargetRequest) {
                JstyleStepTargetReq jstyleStepTargetReq = new JstyleStepTargetReq();
                jstyleStepTargetReq.setGoal(((StepsTargetRequest) bleBaseRequest).getTarget());
                jstyleStepTargetReq.setDataType("SetStepGoal");
                jstyleStepTargetReq.setReqId(bleBaseRequest.getRequId());
                return jstyleStepTargetReq;
            } else {
                int i = 0;
                if (bleBaseRequest instanceof LiveStepsControlRequest) {
                    LogHelper.d(p, "LiveStepsControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyleRealTimeWalkReq jStyleRealTimeWalkReq = new JStyleRealTimeWalkReq();
                    jStyleRealTimeWalkReq.setStartReal(((LiveStepsControlRequest) bleBaseRequest).isEnable());
                    jStyleRealTimeWalkReq.setIstempEnable(false);
                    jStyleRealTimeWalkReq.setReqId(bleBaseRequest.getRequId());
                    jStyleRealTimeWalkReq.setDataType("RealTimeStep");
                    return jStyleRealTimeWalkReq;
                } else if (bleBaseRequest instanceof LiveTemperatureControlRequest) {
                    LogHelper.d(p, "LiveTemperatureControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyleRealTimeWalkReq jStyleRealTimeWalkReq2 = new JStyleRealTimeWalkReq();
                    jStyleRealTimeWalkReq2.setReqId(bleBaseRequest.getRequId());
                    jStyleRealTimeWalkReq2.setDataType("RealTimeStep");
                    LiveTemperatureControlRequest liveTemperatureControlRequest = (LiveTemperatureControlRequest) bleBaseRequest;
                    jStyleRealTimeWalkReq2.setStartReal(liveTemperatureControlRequest.isEnabled());
                    jStyleRealTimeWalkReq2.setIstempEnable(liveTemperatureControlRequest.isEnabled());
                    return jStyleRealTimeWalkReq2;
                } else if (bleBaseRequest instanceof LiveRawPPGControlRequest) {
                    LogHelper.d(p, "LiveRawPPGControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyleRawPPGControlReq jStyleRawPPGControlReq = new JStyleRawPPGControlReq();
                    jStyleRawPPGControlReq.setReqId(bleBaseRequest.getRequId());
                    jStyleRawPPGControlReq.setDataType("PPGDATA");
                    jStyleRawPPGControlReq.setEnable(((LiveRawPPGControlRequest) bleBaseRequest).isEnabled());
                    return jStyleRawPPGControlReq;
                } else if (bleBaseRequest instanceof LiveHeartRateControlRequest) {
                    LogHelper.d(p, "LiveHeartRateControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyleRealTimeWalkReq jStyleRealTimeWalkReq3 = new JStyleRealTimeWalkReq();
                    jStyleRealTimeWalkReq3.setReqId(bleBaseRequest.getRequId());
                    jStyleRealTimeWalkReq3.setDataType("RealTimeStep");
                    LiveHeartRateControlRequest liveHeartRateControlRequest = (LiveHeartRateControlRequest) bleBaseRequest;
                    jStyleRealTimeWalkReq3.setStartReal(liveHeartRateControlRequest.isEnabled());
                    jStyleRealTimeWalkReq3.setIstempEnable(liveHeartRateControlRequest.isEnabled());
                    return jStyleRealTimeWalkReq3;
                } else {
                    char c = 3;
                    char c2 = 6;
                    char c3 = 4;
                    char c4 = 5;
                    char c5 = 2;
                    int i2 = 7;
                    boolean z = true;
                    if (bleBaseRequest instanceof SetReminderRequest) {
                        LogHelper.d(p, "SetReminderRequest", ModuleNames.BLEABSTRACT.getModuleName());
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
                        jstyleSedentaryReq.setDataType("SetSedentaryReminder");
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
                        jstyleHRTimeIntervalReq.setDataType("SetAutomaticHRMonitoring");
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
                        jstyleHRTimeIntervalReq2.setDataType("SetAutomaticHRMonitoring");
                        return jstyleHRTimeIntervalReq2;
                    } else if (bleBaseRequest instanceof SetDeviceParametersRequest) {
                        MyDeviceInfo myDeviceInfo = new MyDeviceInfo();
                        SetDeviceParametersRequest setDeviceParametersRequest = (SetDeviceParametersRequest) bleBaseRequest;
                        myDeviceInfo.setHandleState(setDeviceParametersRequest.isLeftHand());
                        myDeviceInfo.setShowHour(setDeviceParametersRequest.is12HourFormat());
                        myDeviceInfo.setDistanceUnit(setDeviceParametersRequest.isDistanceUnitinMile());
                        myDeviceInfo.setRainHandEnable(setDeviceParametersRequest.isLiftWristEnabled());
                        myDeviceInfo.setUnitC(setDeviceParametersRequest.isTemperatureUnitInCelsius());
                        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                        LogHelper.d("myDeviceInfo", "isLeftHand " + setDeviceParametersRequest.isLeftHand(), moduleNames.getModuleName());
                        LogHelper.d("myDeviceInfo", "is12HourFormat " + setDeviceParametersRequest.is12HourFormat(), moduleNames.getModuleName());
                        LogHelper.d("myDeviceInfo", "isDistanceUnitinMile " + setDeviceParametersRequest.isDistanceUnitinMile(), moduleNames.getModuleName());
                        LogHelper.d("myDeviceInfo", "isLiftWristEnabled " + setDeviceParametersRequest.isLiftWristEnabled(), moduleNames.getModuleName());
                        return a(myDeviceInfo, bleBaseRequest);
                    } else if (bleBaseRequest instanceof SetWearingHandRequest) {
                        MyDeviceInfo myDeviceInfo2 = new MyDeviceInfo();
                        myDeviceInfo2.setHandleState(!((SetWearingHandRequest) bleBaseRequest).isRightHand());
                        return a(myDeviceInfo2, bleBaseRequest);
                    } else if (bleBaseRequest instanceof SetHourFormatRequest) {
                        MyDeviceInfo myDeviceInfo3 = new MyDeviceInfo();
                        myDeviceInfo3.setShowHour(((SetHourFormatRequest) bleBaseRequest).is12HourFormat());
                        return a(myDeviceInfo3, bleBaseRequest);
                    } else if (bleBaseRequest instanceof SetLiftWristRequest) {
                        MyDeviceInfo myDeviceInfo4 = new MyDeviceInfo();
                        myDeviceInfo4.setRainHandEnable(((SetLiftWristRequest) bleBaseRequest).isLiftWristEnabled());
                        return a(myDeviceInfo4, bleBaseRequest);
                    } else if (bleBaseRequest instanceof SetDistanceUnitRequest) {
                        MyDeviceInfo myDeviceInfo5 = new MyDeviceInfo();
                        myDeviceInfo5.setDistanceUnit(((SetDistanceUnitRequest) bleBaseRequest).isDistanceUnitinMile());
                        return a(myDeviceInfo5, bleBaseRequest);
                    } else if (bleBaseRequest instanceof SetMessageContentRequest) {
                        SetMessageContentRequest setMessageContentRequest = (SetMessageContentRequest) bleBaseRequest;
                        NotificationType appNotificationType = setMessageContentRequest.getAppNotificationType();
                        String str = p;
                        ModuleNames moduleNames2 = ModuleNames.BLEABSTRACT;
                        LogHelper.d(str, "appNotificationType from App" + appNotificationType, moduleNames2.getModuleName());
                        int ordinal = appNotificationType.ordinal();
                        if (ordinal == 0) {
                            type_call_1810 = JStyleConstants.Constants.getTYPE_CALL_1810();
                        } else if (ordinal == 2) {
                            type_call_1810 = JStyleConstants.Constants.getTYPE_SMS_1810();
                        } else if (ordinal == 10) {
                            type_call_1810 = JStyleConstants.Constants.getTYPE_QQ_1810();
                        } else if (ordinal == 13) {
                            type_call_1810 = JStyleConstants.Constants.getTYPE_LINE_1810();
                        } else if (ordinal == 17) {
                            type_call_1810 = JStyleConstants.Constants.getTYPE_TELEGRAM_1810();
                        } else if (ordinal == 4) {
                            type_call_1810 = JStyleConstants.Constants.getTYPE_WHATSAPP_1810();
                        } else if (ordinal != 5) {
                            type_call_1810 = ordinal != 6 ? -1 : JStyleConstants.Constants.getTYPE_FACEBOOK_1810();
                        } else {
                            type_call_1810 = JStyleConstants.Constants.getTYPE_WECHAT_1810();
                        }
                        if (type_call_1810 != -1) {
                            String str2 = setMessageContentRequest.message;
                            Notifier notifier = new Notifier();
                            notifier.setInfo(str2);
                            notifier.setType(type_call_1810);
                            LogHelper.d(str, "Notification type " + type_call_1810 + " content " + str2 + "", moduleNames2.getModuleName());
                            JstyleNotificationReq jstyleNotificationReq = new JstyleNotificationReq();
                            jstyleNotificationReq.setNotifier(notifier);
                            jstyleNotificationReq.setDataType(JStyleConstants.Notification);
                            jstyleNotificationReq.setReqId(bleBaseRequest.getRequId());
                            return jstyleNotificationReq;
                        }
                        return null;
                    } else if (bleBaseRequest instanceof StopMessageNotificationRequest) {
                        if (((StopMessageNotificationRequest) bleBaseRequest).getAppNotificationType().ordinal() != 0) {
                            type_stop_tel_1810 = JStyleConstants.Constants.getTYPE_STOP_TEL_1810();
                        } else {
                            type_stop_tel_1810 = JStyleConstants.Constants.getTYPE_STOP_TEL_1810();
                        }
                        Notifier notifier2 = new Notifier();
                        notifier2.setType(type_stop_tel_1810);
                        JstyleNotificationReq jstyleNotificationReq2 = new JstyleNotificationReq();
                        jstyleNotificationReq2.setNotifier(notifier2);
                        jstyleNotificationReq2.setDataType(JStyleConstants.Notification);
                        jstyleNotificationReq2.setReqId(bleBaseRequest.getRequId());
                        return jstyleNotificationReq2;
                    } else if (bleBaseRequest instanceof ProbeDataRequest) {
                        JstyleHeartBeatPacketsIntervalReq jstyleHeartBeatPacketsIntervalReq = new JstyleHeartBeatPacketsIntervalReq();
                        jstyleHeartBeatPacketsIntervalReq.setInterval(((ProbeDataRequest) bleBaseRequest).getInterval());
                        jstyleHeartBeatPacketsIntervalReq.setReqId(bleBaseRequest.getRequId());
                        jstyleHeartBeatPacketsIntervalReq.setDataType("SetHeartBeatPackets");
                        return jstyleHeartBeatPacketsIntervalReq;
                    } else if (bleBaseRequest instanceof SetTemperatureUnitRequest) {
                        JstyleTemperatureUnitReq jstyleTemperatureUnitReq = new JstyleTemperatureUnitReq();
                        jstyleTemperatureUnitReq.setUnit(((SetTemperatureUnitRequest) bleBaseRequest).isTemperatureInCelsius() ? Byte.MIN_VALUE : (byte) -127);
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
                    } else if (bleBaseRequest instanceof OTAModeRequest) {
                        JstyleEnterOTAReq jstyleEnterOTAReq = new JstyleEnterOTAReq();
                        jstyleEnterOTAReq.setReqId(bleBaseRequest.getRequId());
                        jstyleEnterOTAReq.setDataType(JStyleConstants.EnterOTA);
                        return jstyleEnterOTAReq;
                    } else if (bleBaseRequest instanceof MotorVibrationRequest) {
                        JstyleMotorVibrationReq jstyleMotorVibrationReq = new JstyleMotorVibrationReq();
                        jstyleMotorVibrationReq.setReqId(bleBaseRequest.getRequId());
                        jstyleMotorVibrationReq.setDataType("SetMotorVibrationWithTimes");
                        jstyleMotorVibrationReq.setTimes(((MotorVibrationRequest) bleBaseRequest).getTimes());
                        return jstyleMotorVibrationReq;
                    } else if (bleBaseRequest instanceof FindMyWatchRequest) {
                        JstyleMotorVibrationReq jstyleMotorVibrationReq2 = new JstyleMotorVibrationReq();
                        jstyleMotorVibrationReq2.setReqId(bleBaseRequest.getRequId());
                        jstyleMotorVibrationReq2.setDataType("SetMotorVibrationWithTimes");
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
                                iArr5[c] = setVibrationAlarmRequest.isWednesdayEnabled() ? 1 : 0;
                                iArr5[c3] = setVibrationAlarmRequest.isThursdayEnabled() ? 1 : 0;
                                iArr5[c4] = setVibrationAlarmRequest.isFridayEnabled() ? 1 : 0;
                                iArr5[c2] = setVibrationAlarmRequest.isSaturdayEnabled() ? 1 : 0;
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
                                c = 3;
                                i = 0;
                                c2 = 6;
                                c3 = 4;
                                c4 = 5;
                                c5 = 2;
                            } else {
                                jstyleVibrationAlarmReq.setClockList(arrayList);
                                jstyleVibrationAlarmReq.setReqId(bleBaseRequest.getRequId());
                                jstyleVibrationAlarmReq.setDataType("SetAlarmClockWithAllClock");
                                return jstyleVibrationAlarmReq;
                            }
                        }
                    } else if (bleBaseRequest instanceof ActivityModeSummaryRequest) {
                        JstyleActivityModeHistoryReq jstyleActivityModeHistoryReq = new JstyleActivityModeHistoryReq();
                        jstyleActivityModeHistoryReq.setReqId(bleBaseRequest.getRequId());
                        jstyleActivityModeHistoryReq.setDataType("GetActivityModeData");
                        jstyleActivityModeHistoryReq.setMode(JStyleConstants.Constants.getMODE_START());
                        return jstyleActivityModeHistoryReq;
                    }
                }
            }
            return null;
        }
    }

    public final void a() {
        if (q == null || q.size() <= 0) {
            return;
        }
        JstyleBaseReq a2 = a(q.get(0).f3029a);
        if (a2 != null) {
            BleBaseRequest bleBaseRequest = this.j;
            if (bleBaseRequest == null) {
                a(a2);
                return;
            } else if (bleBaseRequest.isComplete()) {
                a(a2);
                return;
            } else {
                return;
            }
        }
        b(q.get(0).f3029a);
    }

    public final void a(JstyleBaseReq jstyleBaseReq) {
        this.j = q.get(0).f3029a;
        if (jstyleBaseReq.isMultiPacket()) {
            this.i.postDelayed(this.n, com.clevertap.android.sdk.Constants.ONE_MIN_IN_MILLIS);
        } else {
            this.i.postDelayed(this.n, 30000L);
        }
        bleService.sendRequest(jstyleBaseReq, this);
    }
}
