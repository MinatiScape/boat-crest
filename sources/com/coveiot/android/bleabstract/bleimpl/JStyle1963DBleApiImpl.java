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
import com.coveiot.android.bleabstract.preferences.PreferenceManager1963D;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.FindMyWatchRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.LiveHeartRateControlRequest;
import com.coveiot.android.bleabstract.request.LiveStepsControlRequest;
import com.coveiot.android.bleabstract.request.MotorVibrationRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.OTAModeRequest;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
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
import com.coveiot.android.bleabstract.services.KH1963DBleService;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleConstants;
import com.coveiot.android.jstyle1963dsdk.JstyleResponseListener;
import com.coveiot.android.jstyle1963dsdk.api.JStyleRealTimeWalkReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleActivityModeHistoryReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleBaseReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleBatteryLevelReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleDeviceBaseParametersReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleDeviceVersionReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleDistanceUnitReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleEnterOTAReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleHRTimeIntervalReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleLiftWristReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleMotorVibrationReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleNotificationReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleOnceHRReq;
import com.coveiot.android.jstyle1963dsdk.api.JstylePersonalInfoReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleSedentaryReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleSleepReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleStepTargetReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleTimeFormatReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleVibrationAlarmReq;
import com.coveiot.android.jstyle1963dsdk.api.JstyleWalkReq;
import com.coveiot.android.jstyle1963dsdk.error.JstyleError;
import com.coveiot.android.jstyle1963dsdk.error.JstyleErrorType;
import com.coveiot.android.jstyle1963dsdk.model.JstyleLiveResponse;
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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class JStyle1963DBleApiImpl implements BleApi, JstyleResponseListener {
    public static KH1963DBleService bleService;
    public static Context context;
    public static JStyle1963DBleApiImpl m;
    public static String p;

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<PPGData> f3065a;
    public MutableLiveData<ConnectionStatus> connectionStateLiveData;
    public DeviceInfoData d;
    public MutableLiveData<LiveHealthData> e;
    public MutableLiveData<LiveStepsData> f;
    public MutableLiveData<LiveECGDataResponse> liveECGDataMutableLiveData;
    public static final String n = JStyle1963DBleApiImpl.class.getSimpleName();
    public static volatile LinkedList<QueueObject> o = new LinkedList<>();
    public static ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            JStyle1963DBleApiImpl.bleService = ((KH1963DBleService.BtServiceBinder) iBinder).getService();
            if (TextUtils.isEmpty(JStyle1963DBleApiImpl.p)) {
                return;
            }
            JStyle1963DBleApiImpl.bleService.initBluetoothDevice();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            JStyle1963DBleApiImpl.bleService = null;
        }
    };
    public Handler b = new Handler();
    public Handler c = new Handler();
    public Handler g = new Handler(Looper.getMainLooper());
    public Handler h = new Handler();
    public BleBaseRequest i = null;
    public ConnectionResultListener j = null;
    public BusyStatus k = BusyStatus.SYNCING;
    public Runnable l = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.23
        @Override // java.lang.Runnable
        public void run() {
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.d("Command TimeOut", "Failed", moduleNames.getModuleName());
            JStyle1963DBleApiImpl jStyle1963DBleApiImpl = JStyle1963DBleApiImpl.this;
            BleBaseRequest bleBaseRequest = jStyle1963DBleApiImpl.i;
            if (bleBaseRequest != null) {
                JstyleBaseReq a2 = jStyle1963DBleApiImpl.a(bleBaseRequest);
                if (a2 != null) {
                    JStyle1963DBleApiImpl.this.onFailure(new JstyleError(JstyleErrorType.COMMAND_TIME_OUT, JStyle1963DBleApiImpl.context.getString(R.string.command_time_out)));
                    LogHelper.e("Command TimeOut", "End Time " + System.currentTimeMillis() + "-- currentCommand " + a2.getDataType(), moduleNames.getModuleName());
                } else {
                    JStyle1963DBleApiImpl jStyle1963DBleApiImpl2 = JStyle1963DBleApiImpl.this;
                    jStyle1963DBleApiImpl2.b(jStyle1963DBleApiImpl2.i);
                }
            }
            JStyle1963DBleApiImpl.this.i = null;
        }
    };

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl$21  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass21 implements SettingsResultListener {
        public AnonymousClass21(JStyle1963DBleApiImpl jStyle1963DBleApiImpl) {
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsError(BleBaseError bleBaseError) {
        }

        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
        public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
        }
    }

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl$24  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass24 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3079a;
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
            f3079a = iArr14;
            try {
                iArr14[JstyleErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f3079a[JstyleErrorType.COMMAND_TIME_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public class QueueObject {

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3089a;

        public QueueObject(JStyle1963DBleApiImpl jStyle1963DBleApiImpl, BleBaseRequest bleBaseRequest) {
            this.f3089a = bleBaseRequest;
        }
    }

    public JStyle1963DBleApiImpl() {
        registerEvenBus();
    }

    public static void a(JStyle1963DBleApiImpl jStyle1963DBleApiImpl, List list, boolean z, ScanDeviceRequest scanDeviceRequest, ScanResultListener scanResultListener) {
        jStyle1963DBleApiImpl.getClass();
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
            Intent intent = new Intent(context, KH1963DBleService.class);
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
            if (KH1963DBleService.class.getName().equals(runningServiceInfo.service.getClassName()) && context.getPackageName().equals(runningServiceInfo.service.getPackageName())) {
                z = true;
            }
        }
        return z;
    }

    public static JStyle1963DBleApiImpl getInstance(Context context2) {
        if (m == null) {
            context = context2.getApplicationContext();
            m = new JStyle1963DBleApiImpl();
        }
        if (!checkIfServiceIsRunning()) {
            LogHelper.d(n, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
            c();
        }
        return m;
    }

    public void addToQueue(BleBaseRequest bleBaseRequest) {
        synchronized (o) {
            JstyleBaseReq a2 = a(bleBaseRequest);
            if (a2 != null) {
                if (a2.isPriority()) {
                    o.addFirst(new QueueObject(this, bleBaseRequest));
                } else {
                    o.add(new QueueObject(this, bleBaseRequest));
                }
            } else {
                b(bleBaseRequest);
            }
        }
    }

    public final void b() {
        BleBaseRequest bleBaseRequest = this.i;
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
        setUserSettings(new LiveStepsControlRequest.Builder(false).build(), new SettingsResultListener() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.20
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(BleBaseError bleBaseError) {
                JStyle1963DBleApiImpl jStyle1963DBleApiImpl = JStyle1963DBleApiImpl.this;
                JStyle1963DBleApiImpl jStyle1963DBleApiImpl2 = JStyle1963DBleApiImpl.m;
                jStyle1963DBleApiImpl.getClass();
                jStyle1963DBleApiImpl.setUserSettings(new LiveHeartRateControlRequest.Builder(false).build(), new AnonymousClass21(jStyle1963DBleApiImpl));
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
                JStyle1963DBleApiImpl jStyle1963DBleApiImpl = JStyle1963DBleApiImpl.this;
                JStyle1963DBleApiImpl jStyle1963DBleApiImpl2 = JStyle1963DBleApiImpl.m;
                jStyle1963DBleApiImpl.getClass();
                jStyle1963DBleApiImpl.setUserSettings(new LiveHeartRateControlRequest.Builder(false).build(), new AnonymousClass21(jStyle1963DBleApiImpl));
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void clearCommandQueue() {
        synchronized (o) {
            if (o != null && o.size() > 0) {
                o.clear();
            }
            KH1963DBleService kH1963DBleService = bleService;
            if (kH1963DBleService != null) {
                kH1963DBleService.clearQueue();
            }
        }
        Handler handler = this.h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.i = null;
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
        Handler handler3 = this.h;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(final ConnectRequest connectRequest, final ConnectionResultListener connectionResultListener) {
        this.b.removeCallbacksAndMessages(null);
        this.j = connectionResultListener;
        this.b.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.5
            @Override // java.lang.Runnable
            public void run() {
                KH1963DBleService kH1963DBleService = JStyle1963DBleApiImpl.bleService;
                if (kH1963DBleService == null || connectRequest == null) {
                    if (kH1963DBleService == null) {
                        JStyle1963DBleApiImpl.c();
                        connectionResultListener.onError(new Error(Type.SERVICE_NOT_RUNNING, JStyle1963DBleApiImpl.context.getString(R.string.service_not_running)));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.COMMAND_REQUEST_ERROR, JStyle1963DBleApiImpl.context.getString(R.string.request_should_not_null)));
                    return;
                }
                JStyle1963DBleApiImpl.p = JStyle1963DBleApiImpl.this.getMacAddress();
                Clove1963DBleState.BleState connectionState = JStyle1963DBleApiImpl.bleService.getConnectionState();
                Clove1963DBleState.BleState bleState = Clove1963DBleState.BleState.CONNECTED;
                if (connectionState == bleState && JStyle1963DBleApiImpl.p.equalsIgnoreCase(connectRequest.getMacAddress())) {
                    connectionResultListener.onConnectionResponse(ConnectionStatus.CONNECTED);
                } else if (JStyle1963DBleApiImpl.bleService.getConnectionState() == Clove1963DBleState.BleState.DISCONNECTED) {
                    BleBaseRequest bleBaseRequest = new BleBaseRequest();
                    bleBaseRequest.setBleCommand(BleCommand.CONNECT);
                    bleBaseRequest.setResponseListener(connectionResultListener);
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    JStyle1963DBleApiImpl.bleService.connect(connectRequest.getMacAddress());
                } else if (JStyle1963DBleApiImpl.bleService.getConnectionState() == bleState) {
                    KH1963DBleService kH1963DBleService2 = JStyle1963DBleApiImpl.bleService;
                    if (kH1963DBleService2.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, "Connected to band " + JStyle1963DBleApiImpl.bleService.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, kH1963DBleService2.getConnectionState().getStateAsString()));
                } else if (JStyle1963DBleApiImpl.bleService.getConnectionState() == Clove1963DBleState.BleState.CONNECTING) {
                    KH1963DBleService kH1963DBleService3 = JStyle1963DBleApiImpl.bleService;
                    if (kH1963DBleService3.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, "Connection in progress " + JStyle1963DBleApiImpl.bleService.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, kH1963DBleService3.getConnectionState().getStateAsString()));
                } else {
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, JStyle1963DBleApiImpl.bleService.getConnectionState().getStateAsString()));
                }
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(ConnectionResultListener connectionResultListener) {
        KH1963DBleService kH1963DBleService = bleService;
        if (kH1963DBleService != null) {
            kH1963DBleService.disconnectAndForget();
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
        return this.k;
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
        KH1963DBleService kH1963DBleService = bleService;
        if (kH1963DBleService != null) {
            if (kH1963DBleService.getConnectionState() == Clove1963DBleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            return bleService.getConnectionState() == Clove1963DBleState.BleState.CONNECTING ? ConnectionStatus.CONNECTING : connectionStatus;
        }
        return connectionStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        KH1963DBleService kH1963DBleService = bleService;
        if (kH1963DBleService == null || kH1963DBleService.getConnectionState() != Clove1963DBleState.BleState.CONNECTED) {
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
            if (o == null || o.size() <= 0) {
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
        deviceSupportedFeatures.setLiveHeartRateSupported(true);
        deviceSupportedFeatures.setLiveBPSupported(false);
        deviceSupportedFeatures.setHandPreferenceSettingsSupported(false);
        deviceSupportedFeatures.setTimeFormatSettingsSupported(true);
        deviceSupportedFeatures.setDistanceUnitSettingsSupported(true);
        deviceSupportedFeatures.setLiftWristToViewSettingsSupported(true);
        deviceSupportedFeatures.setTemperatureUnitSettingsSupported(false);
        deviceSupportedFeatures.setProbeFeatureSupported(false);
        deviceSupportedFeatures.setAutoHrSettingsSupported(true);
        deviceSupportedFeatures.setMultipleAlarmsSupportedAtATime(true);
        deviceSupportedFeatures.setOnceAlarmSupported(false);
        deviceSupportedFeatures.setSportsModeHistorySupported(true);
        deviceSupportedFeatures.setSampleDataSupportedInSportMode(false);
        deviceSupportedFeatures.setSportModeSupportedFromApp(false);
        deviceSupportedFeatures.setDeviceSettingsSupportedInOneCommand(false);
        deviceSupportedFeatures.setMaxCharSupportedInNotification(60);
        deviceSupportedFeatures.setDndSupported(false);
        return deviceSupportedFeatures;
    }

    public BleBaseRequest getFromQueue(JstyleBaseReq jstyleBaseReq) {
        for (int i = 0; i < o.size(); i++) {
            if (o.get(i).f3089a.getRequId().equalsIgnoreCase(jstyleBaseReq.getReqId())) {
                return o.remove(i).f3089a;
            }
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManager1963D.getInstance(context).getConnectedDeviceMacAddress();
        p = connectedDeviceMacAddress;
        return connectedDeviceMacAddress;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return BleApiUtils.isServiceRunning(KH1963DBleService.class.getName(), context);
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
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r2.connectionStateLiveData
            if (r1 == 0) goto L25
            r1.setValue(r3)
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r2.connectionStateLiveData
            r1.postValue(r3)
        L25:
            com.coveiot.android.bleabstract.listeners.ConnectionResultListener r1 = r2.j
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
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.onConnectionStateChanged(com.coveiot.android.bleabstract.models.Clove1963DBleState):void");
    }

    public void onFailure(JstyleError jstyleError) {
        if (jstyleError != null) {
            int i = AnonymousClass24.f3079a[jstyleError.getErrorType().ordinal()];
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
        if (!dataType.equals(BleConst.RealTimeStep)) {
            if (dataType.equals("FindMobilePhoneMode")) {
                FindMyPhoneRes findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.ON);
                Intent intent = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
                intent.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        } else if (jstyleLiveResponse.getObj() != null) {
            Map<String, String> map = (Map) jstyleLiveResponse.getObj();
            LiveStepsData convertToLiveStepsData = new JStyle1963DFormatter(getMacAddress()).convertToLiveStepsData(map);
            MutableLiveData<LiveStepsData> mutableLiveData = this.f;
            if (mutableLiveData != null) {
                mutableLiveData.setValue(convertToLiveStepsData);
                this.f.postValue(convertToLiveStepsData);
            }
            LiveHealthData convertToLiveHealthData = new JStyle1963DFormatter(getMacAddress()).convertToLiveHealthData(map);
            MutableLiveData<LiveHealthData> mutableLiveData2 = this.e;
            if (mutableLiveData2 != null) {
                mutableLiveData2.setValue(convertToLiveHealthData);
                this.e.postValue(convertToLiveHealthData);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02ed A[Catch: Exception -> 0x053c, TryCatch #0 {Exception -> 0x053c, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002e, B:52:0x00af, B:54:0x00b3, B:55:0x00b6, B:57:0x00e2, B:60:0x00e9, B:61:0x00f2, B:63:0x00f8, B:65:0x0105, B:67:0x010c, B:66:0x0109, B:68:0x0121, B:69:0x0126, B:71:0x013c, B:73:0x0140, B:74:0x0143, B:78:0x0181, B:79:0x01a6, B:81:0x01aa, B:82:0x01ad, B:84:0x01d3, B:85:0x01da, B:87:0x0212, B:88:0x021b, B:89:0x0255, B:91:0x0259, B:92:0x025c, B:94:0x0288, B:97:0x028f, B:99:0x0297, B:104:0x02b9, B:106:0x02bf, B:108:0x02cc, B:110:0x02d3, B:109:0x02d0, B:111:0x02e8, B:112:0x02ed, B:101:0x029d, B:113:0x02f9, B:115:0x02fd, B:116:0x0300, B:118:0x032c, B:121:0x0333, B:123:0x033b, B:128:0x035d, B:130:0x0363, B:132:0x0370, B:134:0x0377, B:133:0x0374, B:135:0x038c, B:136:0x0391, B:125:0x0341, B:137:0x039d, B:139:0x03a1, B:140:0x03a4, B:142:0x03d0, B:145:0x03d7, B:147:0x03df, B:152:0x0401, B:154:0x0407, B:156:0x0417, B:158:0x041e, B:157:0x041b, B:159:0x0433, B:160:0x0438, B:149:0x03e5, B:25:0x0062, B:28:0x006c, B:31:0x0076, B:34:0x0080, B:37:0x008a, B:40:0x0094, B:161:0x0444, B:163:0x044c, B:164:0x0458, B:206:0x04e9, B:208:0x04ed, B:210:0x04f1, B:211:0x04f4, B:166:0x045d, B:169:0x0469, B:172:0x0474, B:175:0x0480, B:178:0x048b, B:181:0x0495, B:184:0x049f, B:187:0x04aa, B:190:0x04b4, B:193:0x04be, B:196:0x04c8, B:199:0x04d3, B:202:0x04de, B:212:0x0526, B:213:0x0531), top: B:218:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0391 A[Catch: Exception -> 0x053c, TryCatch #0 {Exception -> 0x053c, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002e, B:52:0x00af, B:54:0x00b3, B:55:0x00b6, B:57:0x00e2, B:60:0x00e9, B:61:0x00f2, B:63:0x00f8, B:65:0x0105, B:67:0x010c, B:66:0x0109, B:68:0x0121, B:69:0x0126, B:71:0x013c, B:73:0x0140, B:74:0x0143, B:78:0x0181, B:79:0x01a6, B:81:0x01aa, B:82:0x01ad, B:84:0x01d3, B:85:0x01da, B:87:0x0212, B:88:0x021b, B:89:0x0255, B:91:0x0259, B:92:0x025c, B:94:0x0288, B:97:0x028f, B:99:0x0297, B:104:0x02b9, B:106:0x02bf, B:108:0x02cc, B:110:0x02d3, B:109:0x02d0, B:111:0x02e8, B:112:0x02ed, B:101:0x029d, B:113:0x02f9, B:115:0x02fd, B:116:0x0300, B:118:0x032c, B:121:0x0333, B:123:0x033b, B:128:0x035d, B:130:0x0363, B:132:0x0370, B:134:0x0377, B:133:0x0374, B:135:0x038c, B:136:0x0391, B:125:0x0341, B:137:0x039d, B:139:0x03a1, B:140:0x03a4, B:142:0x03d0, B:145:0x03d7, B:147:0x03df, B:152:0x0401, B:154:0x0407, B:156:0x0417, B:158:0x041e, B:157:0x041b, B:159:0x0433, B:160:0x0438, B:149:0x03e5, B:25:0x0062, B:28:0x006c, B:31:0x0076, B:34:0x0080, B:37:0x008a, B:40:0x0094, B:161:0x0444, B:163:0x044c, B:164:0x0458, B:206:0x04e9, B:208:0x04ed, B:210:0x04f1, B:211:0x04f4, B:166:0x045d, B:169:0x0469, B:172:0x0474, B:175:0x0480, B:178:0x048b, B:181:0x0495, B:184:0x049f, B:187:0x04aa, B:190:0x04b4, B:193:0x04be, B:196:0x04c8, B:199:0x04d3, B:202:0x04de, B:212:0x0526, B:213:0x0531), top: B:218:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x039d A[Catch: Exception -> 0x053c, TryCatch #0 {Exception -> 0x053c, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002e, B:52:0x00af, B:54:0x00b3, B:55:0x00b6, B:57:0x00e2, B:60:0x00e9, B:61:0x00f2, B:63:0x00f8, B:65:0x0105, B:67:0x010c, B:66:0x0109, B:68:0x0121, B:69:0x0126, B:71:0x013c, B:73:0x0140, B:74:0x0143, B:78:0x0181, B:79:0x01a6, B:81:0x01aa, B:82:0x01ad, B:84:0x01d3, B:85:0x01da, B:87:0x0212, B:88:0x021b, B:89:0x0255, B:91:0x0259, B:92:0x025c, B:94:0x0288, B:97:0x028f, B:99:0x0297, B:104:0x02b9, B:106:0x02bf, B:108:0x02cc, B:110:0x02d3, B:109:0x02d0, B:111:0x02e8, B:112:0x02ed, B:101:0x029d, B:113:0x02f9, B:115:0x02fd, B:116:0x0300, B:118:0x032c, B:121:0x0333, B:123:0x033b, B:128:0x035d, B:130:0x0363, B:132:0x0370, B:134:0x0377, B:133:0x0374, B:135:0x038c, B:136:0x0391, B:125:0x0341, B:137:0x039d, B:139:0x03a1, B:140:0x03a4, B:142:0x03d0, B:145:0x03d7, B:147:0x03df, B:152:0x0401, B:154:0x0407, B:156:0x0417, B:158:0x041e, B:157:0x041b, B:159:0x0433, B:160:0x0438, B:149:0x03e5, B:25:0x0062, B:28:0x006c, B:31:0x0076, B:34:0x0080, B:37:0x008a, B:40:0x0094, B:161:0x0444, B:163:0x044c, B:164:0x0458, B:206:0x04e9, B:208:0x04ed, B:210:0x04f1, B:211:0x04f4, B:166:0x045d, B:169:0x0469, B:172:0x0474, B:175:0x0480, B:178:0x048b, B:181:0x0495, B:184:0x049f, B:187:0x04aa, B:190:0x04b4, B:193:0x04be, B:196:0x04c8, B:199:0x04d3, B:202:0x04de, B:212:0x0526, B:213:0x0531), top: B:218:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0438 A[Catch: Exception -> 0x053c, TryCatch #0 {Exception -> 0x053c, blocks: (B:4:0x0006, B:6:0x000c, B:8:0x0016, B:10:0x001e, B:12:0x002e, B:52:0x00af, B:54:0x00b3, B:55:0x00b6, B:57:0x00e2, B:60:0x00e9, B:61:0x00f2, B:63:0x00f8, B:65:0x0105, B:67:0x010c, B:66:0x0109, B:68:0x0121, B:69:0x0126, B:71:0x013c, B:73:0x0140, B:74:0x0143, B:78:0x0181, B:79:0x01a6, B:81:0x01aa, B:82:0x01ad, B:84:0x01d3, B:85:0x01da, B:87:0x0212, B:88:0x021b, B:89:0x0255, B:91:0x0259, B:92:0x025c, B:94:0x0288, B:97:0x028f, B:99:0x0297, B:104:0x02b9, B:106:0x02bf, B:108:0x02cc, B:110:0x02d3, B:109:0x02d0, B:111:0x02e8, B:112:0x02ed, B:101:0x029d, B:113:0x02f9, B:115:0x02fd, B:116:0x0300, B:118:0x032c, B:121:0x0333, B:123:0x033b, B:128:0x035d, B:130:0x0363, B:132:0x0370, B:134:0x0377, B:133:0x0374, B:135:0x038c, B:136:0x0391, B:125:0x0341, B:137:0x039d, B:139:0x03a1, B:140:0x03a4, B:142:0x03d0, B:145:0x03d7, B:147:0x03df, B:152:0x0401, B:154:0x0407, B:156:0x0417, B:158:0x041e, B:157:0x041b, B:159:0x0433, B:160:0x0438, B:149:0x03e5, B:25:0x0062, B:28:0x006c, B:31:0x0076, B:34:0x0080, B:37:0x008a, B:40:0x0094, B:161:0x0444, B:163:0x044c, B:164:0x0458, B:206:0x04e9, B:208:0x04ed, B:210:0x04f1, B:211:0x04f4, B:166:0x045d, B:169:0x0469, B:172:0x0474, B:175:0x0480, B:178:0x048b, B:181:0x0495, B:184:0x049f, B:187:0x04aa, B:190:0x04b4, B:193:0x04be, B:196:0x04c8, B:199:0x04d3, B:202:0x04de, B:212:0x0526, B:213:0x0531), top: B:218:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.jstyle1963dsdk.api.JstyleBaseRes r17) {
        /*
            Method dump skipped, instructions count: 1440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.onResponse(com.coveiot.android.jstyle1963dsdk.api.JstyleBaseRes):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.connectionStateLiveData == null) {
            this.connectionStateLiveData = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        KH1963DBleService kH1963DBleService = bleService;
        if (kH1963DBleService != null) {
            if (kH1963DBleService.getConnectionState() == Clove1963DBleState.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else if (bleService.getConnectionState() == Clove1963DBleState.BleState.CONNECTING) {
                connectionStatus = ConnectionStatus.CONNECTING;
            }
        }
        this.connectionStateLiveData.postValue(connectionStatus);
        return this.connectionStateLiveData;
    }

    public void registerEvenBus() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.2
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().register(JStyle1963DBleApiImpl.this);
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
    public LiveData<LiveHealthData> registerLiveHealthData() {
        if (this.e == null) {
            this.e = new MutableLiveData<>();
        }
        return this.e;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public MutableLiveData<PPGData> registerLivePPGData() {
        if (this.f3065a == null) {
            this.f3065a = new MutableLiveData<>();
        }
        return this.f3065a;
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
        a(new JstyleError(JstyleErrorType.DEVICE_NOT_CONNECTED, "Device Disconnected"));
        KH1963DBleService kH1963DBleService = bleService;
        if (kH1963DBleService != null) {
            kH1963DBleService.restartService();
            clearParameters();
        }
        new Handler().postDelayed(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.22
            @Override // java.lang.Runnable
            public void run() {
                JStyle1963DBleApiImpl.c();
            }
        }, 5000L);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        clearCommandQueue();
        this.c.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(context).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.3
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(CharSequence charSequence) {
                        scanResultListener.onError(JStyle1963DBleApiImpl.context.getString(R.string.scan_failed));
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(String str) {
                        JStyle1963DBleApiImpl.a(JStyle1963DBleApiImpl.this, new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.c.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!DeviceScanManager.getInstance(JStyle1963DBleApiImpl.context).isScanningInProgress()) {
                        if (BleUtils.isEmpty(scanDeviceRequest.getScanFilter())) {
                            DeviceScanManager.getInstance(JStyle1963DBleApiImpl.context).scanAllDevices(JStyle1963DBleApiImpl.context, scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.4.1
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    JStyle1963DBleApiImpl.a(JStyle1963DBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(JStyle1963DBleApiImpl.context.getString(R.string.scan_failed));
                                }
                            });
                        } else {
                            DeviceScanManager.getInstance(JStyle1963DBleApiImpl.context).scanDevicesWithFilter(JStyle1963DBleApiImpl.context, scanDeviceRequest.getScanFilter(), scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl.4.2
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    JStyle1963DBleApiImpl.a(JStyle1963DBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(JStyle1963DBleApiImpl.context.getString(R.string.scan_failed));
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
        this.k = busyStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener connectionResultListener) {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(BleBaseRequest bleBaseRequest, SettingsResultListener settingsResultListener) {
        KH1963DBleService kH1963DBleService = bleService;
        if (kH1963DBleService == null || kH1963DBleService.getConnectionState() != Clove1963DBleState.BleState.CONNECTED) {
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
            if (o == null || o.size() <= 0) {
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
        PreferenceManager1963D.getInstance(context).saveConnectedDeviceMAcAddress("");
        PreferenceManager1963D.getInstance(context).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        KH1963DBleService kH1963DBleService = bleService;
        if (kH1963DBleService != null) {
            kH1963DBleService.stopService();
            clearParameters();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        clearCommandQueue();
        PreferenceManager1963D.getInstance(context).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        KH1963DBleService kH1963DBleService = bleService;
        if (kH1963DBleService != null) {
            kH1963DBleService.stopServiceRetainMacAddress();
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
        synchronized (o) {
            if (o != null && o.size() > 0) {
                for (int i = 0; i < o.size(); i++) {
                    try {
                        BaseListener responseListener = o.get(i).f3089a.getResponseListener();
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

    public final JstyleBaseReq a(BleBaseRequest bleBaseRequest) {
        int i;
        int type_stop_tel_1963;
        int type_call_1963;
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
                int i2 = 0;
                if (bleBaseRequest instanceof LiveStepsControlRequest) {
                    LogHelper.d(n, "LiveStepsControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyleRealTimeWalkReq jStyleRealTimeWalkReq = new JStyleRealTimeWalkReq();
                    jStyleRealTimeWalkReq.setStartReal(((LiveStepsControlRequest) bleBaseRequest).isEnable());
                    jStyleRealTimeWalkReq.setIstempEnable(false);
                    jStyleRealTimeWalkReq.setReqId(bleBaseRequest.getRequId());
                    jStyleRealTimeWalkReq.setDataType(BleConst.RealTimeStep);
                    return jStyleRealTimeWalkReq;
                } else if (bleBaseRequest instanceof LiveHeartRateControlRequest) {
                    LogHelper.d(n, "LiveHeartRateControlRequest", ModuleNames.BLEABSTRACT.getModuleName());
                    JStyleRealTimeWalkReq jStyleRealTimeWalkReq2 = new JStyleRealTimeWalkReq();
                    jStyleRealTimeWalkReq2.setReqId(bleBaseRequest.getRequId());
                    jStyleRealTimeWalkReq2.setDataType(BleConst.RealTimeStep);
                    LiveHeartRateControlRequest liveHeartRateControlRequest = (LiveHeartRateControlRequest) bleBaseRequest;
                    jStyleRealTimeWalkReq2.setStartReal(liveHeartRateControlRequest.isEnabled());
                    jStyleRealTimeWalkReq2.setIstempEnable(liveHeartRateControlRequest.isEnabled());
                    return jStyleRealTimeWalkReq2;
                } else {
                    char c = 6;
                    char c2 = 4;
                    char c3 = 3;
                    char c4 = 5;
                    char c5 = 2;
                    int i3 = 7;
                    char c6 = 1;
                    if (bleBaseRequest instanceof SetReminderRequest) {
                        LogHelper.d(n, "SetReminderRequest", ModuleNames.BLEABSTRACT.getModuleName());
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
                        int[] iArr = {setReminderRequest.isSundayEnabled() ? 1 : 0, setReminderRequest.isMondayEnabled() ? 1 : 0, setReminderRequest.isTuesdayEnabled() ? 1 : 0, setReminderRequest.isWednesdayEnabled() ? 1 : 0, setReminderRequest.isThursdayEnabled() ? 1 : 0, setReminderRequest.isFridayEnabled() ? 1 : 0, setReminderRequest.isSaturdayEnabled() ? 1 : 0};
                        int i4 = 0;
                        while (i2 < 7) {
                            if (iArr[i2] == 1) {
                                i4 = (int) (i4 + Math.pow(2.0d, i2));
                            }
                            i2++;
                        }
                        mySedentaryReminder.setWeek(i4);
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
                        int[] iArr2 = {heartRateTimeIntervalRequest.isSundayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isMondayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isTuesdayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isWednesdayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isThursdayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isFridayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isSaturdayEnabled() ? 1 : 0};
                        int i5 = 0;
                        while (i2 < 7) {
                            if (iArr2[i2] == 1) {
                                i5 = (int) (i5 + Math.pow(2.0d, i2));
                            }
                            i2++;
                        }
                        myAutomaticHRMonitoring.setWeek(i5);
                        myAutomaticHRMonitoring.setOpen(heartRateTimeIntervalRequest.getOpen());
                        jstyleHRTimeIntervalReq.setMyAutomaticHRMonitoring(myAutomaticHRMonitoring);
                        jstyleHRTimeIntervalReq.setReqId(bleBaseRequest.getRequId());
                        jstyleHRTimeIntervalReq.setDataType(BleConst.GetAutomaticHRMonitoring);
                        return jstyleHRTimeIntervalReq;
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
                        String str = n;
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
                                int[] iArr3 = new int[i3];
                                iArr3[i2] = setVibrationAlarmRequest.isSundayEnabled() ? 1 : 0;
                                iArr3[c6] = setVibrationAlarmRequest.isMondayEnabled() ? 1 : 0;
                                iArr3[c5] = setVibrationAlarmRequest.isTuesdayEnabled() ? 1 : 0;
                                iArr3[c3] = setVibrationAlarmRequest.isWednesdayEnabled() ? 1 : 0;
                                iArr3[c2] = setVibrationAlarmRequest.isThursdayEnabled() ? 1 : 0;
                                iArr3[c4] = setVibrationAlarmRequest.isFridayEnabled() ? 1 : 0;
                                iArr3[c] = setVibrationAlarmRequest.isSaturdayEnabled() ? 1 : 0;
                                int i7 = i2;
                                while (i7 < i3) {
                                    if (iArr3[i7] == c6) {
                                        i = i6;
                                        i2 = (int) (i2 + Math.pow(2.0d, i7));
                                    } else {
                                        i = i6;
                                    }
                                    i7++;
                                    i6 = i;
                                    i3 = 7;
                                    c6 = 1;
                                }
                                clock.setType(setVibrationAlarmRequest.getAlarmType());
                                clock.setWeek((byte) i2);
                                arrayList.add(clock);
                                i2 = 0;
                                c2 = 4;
                                c3 = 3;
                                c4 = 5;
                                c5 = 2;
                                i3 = 7;
                                c6 = 1;
                                i6++;
                                c = 6;
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
                    }
                }
            }
            return null;
        }
    }

    public final void a() {
        if (o == null || o.size() <= 0) {
            return;
        }
        JstyleBaseReq a2 = a(o.get(0).f3089a);
        if (a2 != null) {
            BleBaseRequest bleBaseRequest = this.i;
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
        b(o.get(0).f3089a);
    }

    public final void a(JstyleBaseReq jstyleBaseReq) {
        this.i = o.get(0).f3089a;
        if (jstyleBaseReq.isMultiPacket()) {
            this.h.postDelayed(this.l, com.clevertap.android.sdk.Constants.ONE_MIN_IN_MILLIS);
        } else {
            this.h.postDelayed(this.l, 30000L);
        }
        bleService.sendRequest(jstyleBaseReq, this);
    }
}
