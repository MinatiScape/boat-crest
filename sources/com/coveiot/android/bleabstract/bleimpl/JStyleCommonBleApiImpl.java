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
import com.coveiot.android.bleabstract.formatter.JStyleFormatter;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.Clove1790BleState;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.preferences.PreferenceManager1790;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.EcgDataRequest;
import com.coveiot.android.bleabstract.request.FindMyWatchRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
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
import com.coveiot.android.bleabstract.request.SetWearingHandRequest;
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
import com.coveiot.android.bleabstract.services.KHV5BleService;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleConstants;
import com.coveiot.android.jstylesdk.JstyleResponseListener;
import com.coveiot.android.jstylesdk.api.JStyleRealTimeWalkReq;
import com.coveiot.android.jstylesdk.api.JstyleActivityModeHistoryReq;
import com.coveiot.android.jstylesdk.api.JstyleBaseReq;
import com.coveiot.android.jstylesdk.api.JstyleBatteryLevelReq;
import com.coveiot.android.jstylesdk.api.JstyleDeviceBaseParametersReq;
import com.coveiot.android.jstylesdk.api.JstyleDeviceVersionReq;
import com.coveiot.android.jstylesdk.api.JstyleECGMeasureReq;
import com.coveiot.android.jstylesdk.api.JstyleEnterOTAReq;
import com.coveiot.android.jstylesdk.api.JstyleHRTimeIntervalReq;
import com.coveiot.android.jstylesdk.api.JstyleMotorVibrationReq;
import com.coveiot.android.jstylesdk.api.JstyleNotificationReq;
import com.coveiot.android.jstylesdk.api.JstyleOnceHRReq;
import com.coveiot.android.jstylesdk.api.JstylePersonalInfoReq;
import com.coveiot.android.jstylesdk.api.JstyleSedentaryReq;
import com.coveiot.android.jstylesdk.api.JstyleSleepReq;
import com.coveiot.android.jstylesdk.api.JstyleStepTargetReq;
import com.coveiot.android.jstylesdk.api.JstyleVibrationAlarmReq;
import com.coveiot.android.jstylesdk.api.JstyleWalkReq;
import com.coveiot.android.jstylesdk.error.JstyleError;
import com.coveiot.android.jstylesdk.error.JstyleErrorType;
import com.coveiot.android.jstylesdk.model.JstyleLiveResponse;
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
import com.jstyle.blesdk.model.Clock;
import com.jstyle.blesdk.model.MyAutomaticHRMonitoring;
import com.jstyle.blesdk.model.MyDeviceInfo;
import com.jstyle.blesdk.model.MyPersonalInfo;
import com.jstyle.blesdk.model.MySedentaryReminder;
import com.jstyle.blesdk.model.Notifier;
import com.jstyle.blesdk1860.constant.BleConst;
import com.neurosky.AlgoSdk.NskAlgoSdk;
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
public class JStyleCommonBleApiImpl implements BleApi, JstyleResponseListener {
    public static KHV5BleService bleService;
    public static Context context;
    public static String l;
    public static Intent serviceIntent;

    /* renamed from: a  reason: collision with root package name */
    public Handler f3138a = new Handler();
    public Handler b = new Handler();
    public DeviceInfoData c;
    public MutableLiveData<ConnectionStatus> connectionStateLiveData;
    public MutableLiveData<LiveHealthData> d;
    public MutableLiveData<LiveStepsData> e;
    public Handler f;
    public BleBaseRequest g;
    public ConnectionResultListener h;
    public BusyStatus i;
    public Runnable j;
    public MutableLiveData<LiveECGDataResponse> liveECGDataMutableLiveData;
    public NskAlgoSdk nskAlgoSdk;
    public Handler syncTimeOutHandler;
    public static final String k = JStyleCommonBleApiImpl.class.getSimpleName();
    public static volatile LinkedList<QueueObject> queue = new LinkedList<>();
    public static ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            JStyleCommonBleApiImpl.bleService = ((KHV5BleService.BtServiceBinder) iBinder).getService();
            if (TextUtils.isEmpty(JStyleCommonBleApiImpl.l)) {
                return;
            }
            JStyleCommonBleApiImpl.bleService.initBluetoothDevice();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            JStyleCommonBleApiImpl.bleService = null;
        }
    };

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl$23  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass23 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3151a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            Clove1790BleState.BleState.values();
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
                NotificationType notificationType3 = NotificationType.SKYPE;
                iArr4[14] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                int[] iArr5 = b;
                NotificationType notificationType4 = NotificationType.WECHAT;
                iArr5[5] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                int[] iArr6 = b;
                NotificationType notificationType5 = NotificationType.TWITTER;
                iArr6[8] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                int[] iArr7 = b;
                NotificationType notificationType6 = NotificationType.FACEBOOK;
                iArr7[6] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                int[] iArr8 = b;
                NotificationType notificationType7 = NotificationType.WHATSAPP;
                iArr8[4] = 7;
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
                NotificationType notificationType9 = NotificationType.TELEGRAM;
                iArr10[17] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr11 = new int[JstyleErrorType.values().length];
            f3151a = iArr11;
            try {
                iArr11[JstyleErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f3151a[JstyleErrorType.COMMAND_TIME_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public class QueueObject {

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3161a;

        public QueueObject(JStyleCommonBleApiImpl jStyleCommonBleApiImpl, BleBaseRequest bleBaseRequest) {
            this.f3161a = bleBaseRequest;
        }
    }

    public JStyleCommonBleApiImpl() {
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        this.f = new Handler(Looper.getMainLooper());
        this.syncTimeOutHandler = new Handler();
        this.g = null;
        this.h = null;
        this.i = BusyStatus.SYNCING;
        this.j = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.22
            @Override // java.lang.Runnable
            public void run() {
                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                LogHelper.d("Command TimeOut", "Failed", moduleNames.getModuleName());
                JStyleCommonBleApiImpl jStyleCommonBleApiImpl = JStyleCommonBleApiImpl.this;
                BleBaseRequest bleBaseRequest = jStyleCommonBleApiImpl.g;
                if (bleBaseRequest != null) {
                    JstyleBaseReq a2 = jStyleCommonBleApiImpl.a(bleBaseRequest, false);
                    if (a2 != null) {
                        JStyleCommonBleApiImpl.this.onFailure(new JstyleError(JstyleErrorType.COMMAND_TIME_OUT, JStyleCommonBleApiImpl.context.getString(R.string.command_time_out)));
                        LogHelper.e("Command TimeOut", "End Time " + System.currentTimeMillis() + "-- currentCommand " + a2.getDataType(), moduleNames.getModuleName());
                    } else {
                        JStyleCommonBleApiImpl jStyleCommonBleApiImpl2 = JStyleCommonBleApiImpl.this;
                        jStyleCommonBleApiImpl2.a(jStyleCommonBleApiImpl2.g);
                    }
                }
                JStyleCommonBleApiImpl.this.g = null;
            }
        };
    }

    public static void a(JStyleCommonBleApiImpl jStyleCommonBleApiImpl, List list, boolean z, ScanDeviceRequest scanDeviceRequest, ScanResultListener scanResultListener) {
        jStyleCommonBleApiImpl.getClass();
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
            if (KHV5BleService.class.getName().equals(runningServiceInfo.service.getClassName()) && context.getPackageName().equals(runningServiceInfo.service.getPackageName())) {
                z = true;
            }
        }
        return z;
    }

    public static void startBleService() {
        try {
            Intent intent = new Intent(context, KHV5BleService.class);
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

    public void addToQueue(BleBaseRequest bleBaseRequest) {
        synchronized (queue) {
            JstyleBaseReq a2 = a(bleBaseRequest, false);
            if (a2 != null) {
                if (a2.isPriority()) {
                    queue.addFirst(new QueueObject(this, bleBaseRequest));
                } else {
                    queue.add(new QueueObject(this, bleBaseRequest));
                }
            } else {
                a(bleBaseRequest);
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return false;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void cleanUpCommands() {
        setUserSettings(new LiveStepsControlRequest.Builder(false).build(), new SettingsResultListener(this) { // from class: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.20
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(BleBaseError bleBaseError) {
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse bleBaseResponse) {
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void clearCommandQueue() {
        synchronized (queue) {
            if (queue != null && queue.size() > 0) {
                queue.clear();
            }
            KHV5BleService kHV5BleService = bleService;
            if (kHV5BleService != null) {
                kHV5BleService.clearQueue();
            }
        }
        Handler handler = this.syncTimeOutHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.g = null;
    }

    public void clearParameters() {
        if (DeviceScanManager.getInstance(context).isScanningInProgress()) {
            DeviceScanManager.getInstance(context).stopScan();
        }
        bleService = null;
        unbindService();
        Handler handler = this.f3138a;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.b;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.syncTimeOutHandler;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(final ConnectRequest connectRequest, final ConnectionResultListener connectionResultListener) {
        this.f3138a.removeCallbacksAndMessages(null);
        this.h = connectionResultListener;
        this.f3138a.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.5
            @Override // java.lang.Runnable
            public void run() {
                KHV5BleService kHV5BleService = JStyleCommonBleApiImpl.bleService;
                if (kHV5BleService == null || connectRequest == null) {
                    if (kHV5BleService == null) {
                        JStyleCommonBleApiImpl.startBleService();
                        connectionResultListener.onError(new Error(Type.SERVICE_NOT_RUNNING, JStyleCommonBleApiImpl.context.getString(R.string.service_not_running)));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.COMMAND_REQUEST_ERROR, JStyleCommonBleApiImpl.context.getString(R.string.request_should_not_null)));
                    return;
                }
                JStyleCommonBleApiImpl.l = JStyleCommonBleApiImpl.this.getMacAddress();
                Clove1790BleState.BleState connectionState = JStyleCommonBleApiImpl.bleService.getConnectionState();
                Clove1790BleState.BleState bleState = Clove1790BleState.BleState.CONNECTED;
                if (connectionState == bleState && JStyleCommonBleApiImpl.l.equalsIgnoreCase(connectRequest.getMacAddress())) {
                    connectionResultListener.onConnectionResponse(ConnectionStatus.CONNECTED);
                } else if (JStyleCommonBleApiImpl.bleService.getConnectionState() == Clove1790BleState.BleState.DISCONNECTED) {
                    BleBaseRequest bleBaseRequest = new BleBaseRequest();
                    bleBaseRequest.setBleCommand(BleCommand.CONNECT);
                    bleBaseRequest.setResponseListener(connectionResultListener);
                    bleBaseRequest.setRequId(UUID.randomUUID().toString());
                    JStyleCommonBleApiImpl.bleService.connect(connectRequest.getMacAddress());
                } else if (JStyleCommonBleApiImpl.bleService.getConnectionState() == bleState) {
                    KHV5BleService kHV5BleService2 = JStyleCommonBleApiImpl.bleService;
                    if (kHV5BleService2.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, "Connected to band " + JStyleCommonBleApiImpl.bleService.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.BAND_ALREADY_CONNECTED, kHV5BleService2.getConnectionState().getStateAsString()));
                } else if (JStyleCommonBleApiImpl.bleService.getConnectionState() == Clove1790BleState.BleState.CONNECTING) {
                    KHV5BleService kHV5BleService3 = JStyleCommonBleApiImpl.bleService;
                    if (kHV5BleService3.mBluetoothDevice != null) {
                        connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, "Connection in progress " + JStyleCommonBleApiImpl.bleService.mBluetoothDevice.getAddress()));
                        return;
                    }
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, kHV5BleService3.getConnectionState().getStateAsString()));
                } else {
                    connectionResultListener.onError(new Error(Type.CONNECTION_IN_PROGRESS, JStyleCommonBleApiImpl.bleService.getConnectionState().getStateAsString()));
                }
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(ConnectionResultListener connectionResultListener) {
        KHV5BleService kHV5BleService = bleService;
        if (kHV5BleService != null) {
            kHV5BleService.disconnectAndForget();
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
        return this.i;
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
        KHV5BleService kHV5BleService = bleService;
        if (kHV5BleService != null) {
            if (kHV5BleService.getConnectionState() == Clove1790BleState.BleState.CONNECTED) {
                return ConnectionStatus.CONNECTED;
            }
            return bleService.getConnectionState() == Clove1790BleState.BleState.CONNECTING ? ConnectionStatus.CONNECTING : connectionStatus;
        }
        return connectionStatus;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        KHV5BleService kHV5BleService = bleService;
        if (kHV5BleService == null || kHV5BleService.getConnectionState() != Clove1790BleState.BleState.CONNECTED) {
            dataResultListener.onDataError(new BleBaseError(context.getString(R.string.band_not_connected)));
        } else if (bleBaseRequest != null && dataResultListener != null) {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            bleBaseRequest.setResponseListener(dataResultListener);
            addToQueue(bleBaseRequest);
            sendCommandRequest();
        } else if (dataResultListener != null) {
            Context context2 = context;
            int i = R.string.request_cannot_null;
            BleBaseError bleBaseError = new BleBaseError(context2.getString(i));
            bleBaseError.setErrorMsg(context.getString(i));
            dataResultListener.onDataError(bleBaseError);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        deviceSupportedFeatures.setStepsSupported(true);
        deviceSupportedFeatures.setSleepSupported(true);
        deviceSupportedFeatures.setHeartRateSupported(true);
        deviceSupportedFeatures.setPersonalInfoSupported(true);
        deviceSupportedFeatures.setStepGoalSupported(true);
        deviceSupportedFeatures.setCallNotificationSupported(true);
        deviceSupportedFeatures.setSmsSupported(true);
        deviceSupportedFeatures.setMessageReadSupported(true);
        deviceSupportedFeatures.setSocialNotificationSupported(true);
        deviceSupportedFeatures.setHandSettingsSupported(true);
        deviceSupportedFeatures.setPhoneFinderSupported(true);
        deviceSupportedFeatures.setLiveStepsSupported(true);
        deviceSupportedFeatures.setLiveHeartRateSupported(false);
        deviceSupportedFeatures.setLiveBPSupported(false);
        deviceSupportedFeatures.setHandPreferenceSettingsSupported(true);
        deviceSupportedFeatures.setTimeFormatSettingsSupported(true);
        deviceSupportedFeatures.setDistanceUnitSettingsSupported(true);
        deviceSupportedFeatures.setLiftWristToViewSettingsSupported(true);
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
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i).f3161a.getRequId().equalsIgnoreCase(jstyleBaseReq.getReqId())) {
                return queue.remove(i).f3161a;
            }
        }
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManager1790.getInstance(context).getConnectedDeviceMacAddress();
        l = connectedDeviceMacAddress;
        return connectedDeviceMacAddress;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return false;
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
    public void onConnectionStateChanged(com.coveiot.android.bleabstract.models.Clove1790BleState r3) {
        /*
            r2 = this;
            com.coveiot.android.bleabstract.models.ConnectionStatus r0 = com.coveiot.android.bleabstract.models.ConnectionStatus.DISCONNECTED
            if (r3 == 0) goto L18
            com.coveiot.android.bleabstract.models.Clove1790BleState$BleState r3 = r3.getmState()
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
            com.coveiot.android.bleabstract.listeners.ConnectionResultListener r1 = r2.h
            if (r1 == 0) goto L2c
            r1.onConnectionResponse(r3)
        L2c:
            if (r3 != r0) goto L3a
            com.coveiot.android.jstylesdk.error.JstyleError r3 = new com.coveiot.android.jstylesdk.error.JstyleError
            com.coveiot.android.jstylesdk.error.JstyleErrorType r0 = com.coveiot.android.jstylesdk.error.JstyleErrorType.DEVICE_NOT_CONNECTED
            java.lang.String r1 = "Device disconnected"
            r3.<init>(r0, r1)
            r2.sendErrorAndClearQueue(r3)
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.onConnectionStateChanged(com.coveiot.android.bleabstract.models.Clove1790BleState):void");
    }

    public void onFailure(JstyleError jstyleError) {
        if (jstyleError == null || jstyleError.getErrorType() == null) {
            return;
        }
        int i = AnonymousClass23.f3151a[jstyleError.getErrorType().ordinal()];
        if (i == 1 || i == 2) {
            sendErrorAndClearQueue(jstyleError);
        }
    }

    @Subscribe
    public void onLiveResponseReceived(JstyleLiveResponse jstyleLiveResponse) {
        if (jstyleLiveResponse == null || jstyleLiveResponse.getDataType() == null) {
            return;
        }
        String dataType = jstyleLiveResponse.getDataType();
        dataType.hashCode();
        if (!dataType.equals("RealTimeStep")) {
            if (dataType.equals("FindMobilePhoneMode")) {
                FindMyPhoneRes findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.ON);
                Intent intent = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
                intent.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        } else if (jstyleLiveResponse.getObj() != null) {
            Map<String, String> map = (Map) jstyleLiveResponse.getObj();
            LiveStepsData convertToLiveStepsData = new JStyleFormatter(getMacAddress()).convertToLiveStepsData(map);
            MutableLiveData<LiveStepsData> mutableLiveData = this.e;
            if (mutableLiveData != null) {
                mutableLiveData.setValue(convertToLiveStepsData);
                this.e.postValue(convertToLiveStepsData);
            }
            LiveHealthData convertToLiveHealthData = new JStyleFormatter(getMacAddress()).convertToLiveHealthData(map);
            MutableLiveData<LiveHealthData> mutableLiveData2 = this.d;
            if (mutableLiveData2 != null) {
                mutableLiveData2.setValue(convertToLiveHealthData);
                this.d.postValue(convertToLiveHealthData);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x036e A[Catch: Exception -> 0x04f4, TryCatch #0 {Exception -> 0x04f4, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0012, B:9:0x001a, B:11:0x002b, B:12:0x0046, B:40:0x0095, B:42:0x0099, B:43:0x009c, B:45:0x00c8, B:48:0x00cf, B:49:0x00d6, B:51:0x00dc, B:53:0x00e9, B:55:0x00f0, B:54:0x00ed, B:56:0x0105, B:57:0x010a, B:59:0x011d, B:61:0x0121, B:62:0x0124, B:66:0x0162, B:67:0x0187, B:69:0x018b, B:70:0x018e, B:72:0x01b4, B:73:0x01bb, B:75:0x01f3, B:76:0x01fc, B:77:0x0236, B:79:0x023a, B:80:0x023d, B:82:0x0269, B:85:0x0270, B:87:0x0278, B:92:0x029a, B:94:0x02a0, B:96:0x02ad, B:98:0x02b4, B:97:0x02b1, B:99:0x02cc, B:89:0x027e, B:100:0x02d8, B:102:0x02dc, B:103:0x02df, B:105:0x030b, B:108:0x0312, B:110:0x031a, B:115:0x033c, B:117:0x0342, B:119:0x034f, B:121:0x0356, B:120:0x0353, B:122:0x036e, B:112:0x0320, B:123:0x037a, B:125:0x037e, B:126:0x0381, B:128:0x03ad, B:131:0x03b4, B:133:0x03bc, B:138:0x03de, B:140:0x03e4, B:142:0x03f7, B:144:0x03fe, B:143:0x03fb, B:145:0x0416, B:135:0x03c2, B:14:0x004a, B:17:0x0054, B:20:0x005e, B:23:0x0068, B:26:0x0072, B:29:0x007c, B:146:0x0422, B:148:0x042a, B:149:0x0436, B:181:0x04a1, B:183:0x04a5, B:185:0x04a9, B:186:0x04ac, B:151:0x043b, B:154:0x0446, B:157:0x0450, B:160:0x045a, B:163:0x0464, B:166:0x046f, B:169:0x047a, B:172:0x0484, B:175:0x048e, B:178:0x0498, B:187:0x04de, B:188:0x04e9), top: B:193:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0416 A[Catch: Exception -> 0x04f4, TryCatch #0 {Exception -> 0x04f4, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0012, B:9:0x001a, B:11:0x002b, B:12:0x0046, B:40:0x0095, B:42:0x0099, B:43:0x009c, B:45:0x00c8, B:48:0x00cf, B:49:0x00d6, B:51:0x00dc, B:53:0x00e9, B:55:0x00f0, B:54:0x00ed, B:56:0x0105, B:57:0x010a, B:59:0x011d, B:61:0x0121, B:62:0x0124, B:66:0x0162, B:67:0x0187, B:69:0x018b, B:70:0x018e, B:72:0x01b4, B:73:0x01bb, B:75:0x01f3, B:76:0x01fc, B:77:0x0236, B:79:0x023a, B:80:0x023d, B:82:0x0269, B:85:0x0270, B:87:0x0278, B:92:0x029a, B:94:0x02a0, B:96:0x02ad, B:98:0x02b4, B:97:0x02b1, B:99:0x02cc, B:89:0x027e, B:100:0x02d8, B:102:0x02dc, B:103:0x02df, B:105:0x030b, B:108:0x0312, B:110:0x031a, B:115:0x033c, B:117:0x0342, B:119:0x034f, B:121:0x0356, B:120:0x0353, B:122:0x036e, B:112:0x0320, B:123:0x037a, B:125:0x037e, B:126:0x0381, B:128:0x03ad, B:131:0x03b4, B:133:0x03bc, B:138:0x03de, B:140:0x03e4, B:142:0x03f7, B:144:0x03fe, B:143:0x03fb, B:145:0x0416, B:135:0x03c2, B:14:0x004a, B:17:0x0054, B:20:0x005e, B:23:0x0068, B:26:0x0072, B:29:0x007c, B:146:0x0422, B:148:0x042a, B:149:0x0436, B:181:0x04a1, B:183:0x04a5, B:185:0x04a9, B:186:0x04ac, B:151:0x043b, B:154:0x0446, B:157:0x0450, B:160:0x045a, B:163:0x0464, B:166:0x046f, B:169:0x047a, B:172:0x0484, B:175:0x048e, B:178:0x0498, B:187:0x04de, B:188:0x04e9), top: B:193:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02cc A[Catch: Exception -> 0x04f4, TryCatch #0 {Exception -> 0x04f4, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0012, B:9:0x001a, B:11:0x002b, B:12:0x0046, B:40:0x0095, B:42:0x0099, B:43:0x009c, B:45:0x00c8, B:48:0x00cf, B:49:0x00d6, B:51:0x00dc, B:53:0x00e9, B:55:0x00f0, B:54:0x00ed, B:56:0x0105, B:57:0x010a, B:59:0x011d, B:61:0x0121, B:62:0x0124, B:66:0x0162, B:67:0x0187, B:69:0x018b, B:70:0x018e, B:72:0x01b4, B:73:0x01bb, B:75:0x01f3, B:76:0x01fc, B:77:0x0236, B:79:0x023a, B:80:0x023d, B:82:0x0269, B:85:0x0270, B:87:0x0278, B:92:0x029a, B:94:0x02a0, B:96:0x02ad, B:98:0x02b4, B:97:0x02b1, B:99:0x02cc, B:89:0x027e, B:100:0x02d8, B:102:0x02dc, B:103:0x02df, B:105:0x030b, B:108:0x0312, B:110:0x031a, B:115:0x033c, B:117:0x0342, B:119:0x034f, B:121:0x0356, B:120:0x0353, B:122:0x036e, B:112:0x0320, B:123:0x037a, B:125:0x037e, B:126:0x0381, B:128:0x03ad, B:131:0x03b4, B:133:0x03bc, B:138:0x03de, B:140:0x03e4, B:142:0x03f7, B:144:0x03fe, B:143:0x03fb, B:145:0x0416, B:135:0x03c2, B:14:0x004a, B:17:0x0054, B:20:0x005e, B:23:0x0068, B:26:0x0072, B:29:0x007c, B:146:0x0422, B:148:0x042a, B:149:0x0436, B:181:0x04a1, B:183:0x04a5, B:185:0x04a9, B:186:0x04ac, B:151:0x043b, B:154:0x0446, B:157:0x0450, B:160:0x045a, B:163:0x0464, B:166:0x046f, B:169:0x047a, B:172:0x0484, B:175:0x048e, B:178:0x0498, B:187:0x04de, B:188:0x04e9), top: B:193:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.jstylesdk.api.JstyleBaseRes r15) {
        /*
            Method dump skipped, instructions count: 1376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.onResponse(com.coveiot.android.jstylesdk.api.JstyleBaseRes):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.connectionStateLiveData == null) {
            this.connectionStateLiveData = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        KHV5BleService kHV5BleService = bleService;
        if (kHV5BleService != null) {
            if (kHV5BleService.getConnectionState() == Clove1790BleState.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else if (bleService.getConnectionState() == Clove1790BleState.BleState.CONNECTING) {
                connectionStatus = ConnectionStatus.CONNECTING;
            }
        }
        this.connectionStateLiveData.postValue(connectionStatus);
        return this.connectionStateLiveData;
    }

    public void registerEvenBus() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.2
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().register(JStyleCommonBleApiImpl.this);
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
        if (this.d == null) {
            this.d = new MutableLiveData<>();
        }
        return this.d;
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
    @NotNull
    public LiveData<LiveStepsData> registerLiveStepsData() {
        if (this.e == null) {
            this.e = new MutableLiveData<>();
        }
        return this.e;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<LiveWatchFaceUploadPercentage> registerLiveWatchFaceUploadData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        sendErrorAndClearQueue(new JstyleError(JstyleErrorType.DEVICE_NOT_CONNECTED, "Device Disconnected"));
        KHV5BleService kHV5BleService = bleService;
        if (kHV5BleService != null) {
            kHV5BleService.restartService();
            clearParameters();
        }
        new Handler().postDelayed(new Runnable(this) { // from class: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.21
            @Override // java.lang.Runnable
            public void run() {
                JStyleCommonBleApiImpl.startBleService();
            }
        }, 5000L);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        clearCommandQueue();
        this.b.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(context).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.3
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(CharSequence charSequence) {
                        scanResultListener.onError(JStyleCommonBleApiImpl.context.getString(R.string.scan_failed));
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(String str) {
                        JStyleCommonBleApiImpl.a(JStyleCommonBleApiImpl.this, new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.b.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!DeviceScanManager.getInstance(JStyleCommonBleApiImpl.context).isScanningInProgress()) {
                        if (BleUtils.isEmpty(scanDeviceRequest.getScanFilter())) {
                            DeviceScanManager.getInstance(JStyleCommonBleApiImpl.context).scanAllDevices(JStyleCommonBleApiImpl.context, scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.4.1
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    JStyleCommonBleApiImpl.a(JStyleCommonBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(JStyleCommonBleApiImpl.context.getString(R.string.scan_failed));
                                }
                            });
                        } else {
                            DeviceScanManager.getInstance(JStyleCommonBleApiImpl.context).scanDevicesWithFilter(JStyleCommonBleApiImpl.context, scanDeviceRequest.getScanFilter(), scanDeviceRequest.getScanDuration(), scanDeviceRequest.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.4.2
                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onDevicesFound(List<BleDevice> list, boolean z) {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    JStyleCommonBleApiImpl.a(JStyleCommonBleApiImpl.this, list, z, scanDeviceRequest, scanResultListener);
                                }

                                @Override // com.coveiot.sdk.ble.scan.ScanResult
                                public void onScanFailed(int i) {
                                    scanResultListener.onError(JStyleCommonBleApiImpl.context.getString(R.string.scan_failed));
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

    public void sendCommandRequest() {
        if (queue == null || queue.size() <= 0) {
            return;
        }
        a();
    }

    public void sendErrorAndClearQueue(JstyleError jstyleError) {
        synchronized (queue) {
            if (queue != null && queue.size() > 0) {
                for (int i = 0; i < queue.size(); i++) {
                    try {
                        BleBaseError bleBaseError = new BleBaseError(jstyleError.getMessage());
                        if (jstyleError.getErrorType() == JstyleErrorType.COMMAND_TIME_OUT) {
                            bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_ERROR_TIMEOUT.value));
                        }
                        BaseListener responseListener = queue.get(i).f3161a.getResponseListener();
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

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus busyStatus) {
        this.i = busyStatus;
    }

    public void setCompleteAndProcessNext() {
        BleBaseRequest bleBaseRequest = this.g;
        if (bleBaseRequest != null) {
            bleBaseRequest.setComplete(true);
        }
        a();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener connectionResultListener) {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(BleBaseRequest bleBaseRequest, SettingsResultListener settingsResultListener) {
        KHV5BleService kHV5BleService = bleService;
        if (kHV5BleService == null || kHV5BleService.getConnectionState() != Clove1790BleState.BleState.CONNECTED) {
            settingsResultListener.onSettingsError(new BleBaseError(context.getString(R.string.band_not_connected)));
        } else if (bleBaseRequest != null && settingsResultListener != null) {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            bleBaseRequest.setResponseListener(settingsResultListener);
            addToQueue(bleBaseRequest);
            sendCommandRequest();
        } else if (settingsResultListener != null) {
            Context context2 = context;
            int i = R.string.request_cannot_null;
            BleBaseError bleBaseError = new BleBaseError(context2.getString(i));
            bleBaseError.setErrorMsg(context.getString(i));
            settingsResultListener.onSettingsError(bleBaseError);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(context).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManager1790.getInstance(context).saveConnectedDeviceMAcAddress("");
        PreferenceManager1790.getInstance(context).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        KHV5BleService kHV5BleService = bleService;
        if (kHV5BleService != null) {
            kHV5BleService.stopService();
            clearParameters();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        clearCommandQueue();
        PreferenceManager1790.getInstance(context).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        KHV5BleService kHV5BleService = bleService;
        if (kHV5BleService != null) {
            kHV5BleService.stopServiceRetainMacAddress();
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

    public final JstyleDeviceBaseParametersReq a(MyDeviceInfo myDeviceInfo, BleBaseRequest bleBaseRequest) {
        JstyleDeviceBaseParametersReq jstyleDeviceBaseParametersReq = new JstyleDeviceBaseParametersReq();
        jstyleDeviceBaseParametersReq.setDeviceBaseParameter(myDeviceInfo);
        jstyleDeviceBaseParametersReq.setReqId(bleBaseRequest.getRequId());
        jstyleDeviceBaseParametersReq.setDataType("SetDeviceInfo");
        return jstyleDeviceBaseParametersReq;
    }

    public final JstyleBaseReq a(BleBaseRequest bleBaseRequest, boolean z) {
        int type_stop_tel;
        int type_call;
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
                this.c = null;
                DeviceInfoRequest deviceInfoRequest = (DeviceInfoRequest) bleBaseRequest;
                if (deviceInfoRequest.isMacAddress()) {
                    if (this.c == null) {
                        this.c = new DeviceInfoData();
                    }
                    this.c.setMacAddress(getMacAddress());
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
            } else if (bleBaseRequest instanceof SetFitnessInfoRequest) {
                JstylePersonalInfoReq jstylePersonalInfoReq = new JstylePersonalInfoReq();
                MyPersonalInfo myPersonalInfo = new MyPersonalInfo();
                SetFitnessInfoRequest setFitnessInfoRequest = (SetFitnessInfoRequest) bleBaseRequest;
                myPersonalInfo.setAge(setFitnessInfoRequest.getAge());
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
            } else if (bleBaseRequest instanceof LiveStepsControlRequest) {
                JStyleRealTimeWalkReq jStyleRealTimeWalkReq = new JStyleRealTimeWalkReq();
                jStyleRealTimeWalkReq.setStartReal(((LiveStepsControlRequest) bleBaseRequest).isEnable());
                jStyleRealTimeWalkReq.setReqId(bleBaseRequest.getRequId());
                jStyleRealTimeWalkReq.setDataType("RealTimeStep");
                return jStyleRealTimeWalkReq;
            } else {
                char c = 6;
                char c2 = 4;
                char c3 = 3;
                char c4 = 5;
                char c5 = 2;
                int i = 7;
                char c6 = 1;
                int i2 = 0;
                if (bleBaseRequest instanceof SetReminderRequest) {
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
                    int i3 = 0;
                    while (i2 < 7) {
                        if (iArr[i2] == 1) {
                            i3 = (int) (i3 + Math.pow(2.0d, i2));
                        }
                        i2++;
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
                    int[] iArr2 = {heartRateTimeIntervalRequest.isSundayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isMondayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isTuesdayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isWednesdayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isThursdayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isFridayEnabled() ? 1 : 0, heartRateTimeIntervalRequest.isSaturdayEnabled() ? 1 : 0};
                    int i4 = 0;
                    while (i2 < 7) {
                        if (iArr2[i2] == 1) {
                            i4 = (int) (i4 + Math.pow(2.0d, i2));
                        }
                        i2++;
                    }
                    myAutomaticHRMonitoring.setWeek(i4);
                    myAutomaticHRMonitoring.setOpen(heartRateTimeIntervalRequest.getOpen());
                    jstyleHRTimeIntervalReq.setMyAutomaticHRMonitoring(myAutomaticHRMonitoring);
                    jstyleHRTimeIntervalReq.setReqId(bleBaseRequest.getRequId());
                    jstyleHRTimeIntervalReq.setDataType("SetAutomaticHRMonitoring");
                    return jstyleHRTimeIntervalReq;
                } else if (bleBaseRequest instanceof SetDeviceParametersRequest) {
                    MyDeviceInfo myDeviceInfo = new MyDeviceInfo();
                    SetDeviceParametersRequest setDeviceParametersRequest = (SetDeviceParametersRequest) bleBaseRequest;
                    myDeviceInfo.setHandleState(setDeviceParametersRequest.isLeftHand());
                    myDeviceInfo.setShowHour(setDeviceParametersRequest.is12HourFormat());
                    myDeviceInfo.setDistanceUnit(setDeviceParametersRequest.isDistanceUnitinMile());
                    myDeviceInfo.setRainHandEnable(setDeviceParametersRequest.isLiftWristEnabled());
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
                    int ordinal = setMessageContentRequest.getAppNotificationType().ordinal();
                    if (ordinal == 0) {
                        type_call = JStyleConstants.Constants.getTYPE_CALL();
                    } else if (ordinal == 2) {
                        type_call = JStyleConstants.Constants.getTYPE_SMS();
                    } else if (ordinal == 14) {
                        type_call = JStyleConstants.Constants.getTYPE_SKYPE();
                    } else if (ordinal != 17) {
                        switch (ordinal) {
                            case 4:
                                type_call = JStyleConstants.Constants.getTYPE_WHATSAPP();
                                break;
                            case 5:
                                type_call = JStyleConstants.Constants.getTYPE_WECHAT();
                                break;
                            case 6:
                                type_call = JStyleConstants.Constants.getTYPE_FACEBOOK();
                                break;
                            case 7:
                                type_call = JStyleConstants.Constants.getTYPE_INSTAGRAM();
                                break;
                            case 8:
                                type_call = JStyleConstants.Constants.getTYPE_TWITTER();
                                break;
                            default:
                                type_call = -1;
                                break;
                        }
                    } else {
                        type_call = JStyleConstants.Constants.getTYPE_TELEGRAM();
                    }
                    if (type_call != -1) {
                        String str = setMessageContentRequest.message;
                        Notifier notifier = new Notifier();
                        notifier.setInfo(str);
                        notifier.setType(type_call);
                        JstyleNotificationReq jstyleNotificationReq = new JstyleNotificationReq();
                        jstyleNotificationReq.setNotifier(notifier);
                        jstyleNotificationReq.setDataType(JStyleConstants.Notification);
                        jstyleNotificationReq.setReqId(bleBaseRequest.getRequId());
                        return jstyleNotificationReq;
                    }
                    return null;
                } else if (bleBaseRequest instanceof StopMessageNotificationRequest) {
                    if (((StopMessageNotificationRequest) bleBaseRequest).getAppNotificationType().ordinal() != 0) {
                        type_stop_tel = JStyleConstants.Constants.getTYPE_STOP_TEL();
                    } else {
                        type_stop_tel = JStyleConstants.Constants.getTYPE_STOP_TEL();
                    }
                    Notifier notifier2 = new Notifier();
                    notifier2.setType(type_stop_tel);
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
                    jstyleMotorVibrationReq.setDataType("MotorVibrationWithTimes");
                    jstyleMotorVibrationReq.setTimes(((MotorVibrationRequest) bleBaseRequest).getTimes());
                    return jstyleMotorVibrationReq;
                } else if (bleBaseRequest instanceof FindMyWatchRequest) {
                    JstyleMotorVibrationReq jstyleMotorVibrationReq2 = new JstyleMotorVibrationReq();
                    jstyleMotorVibrationReq2.setReqId(bleBaseRequest.getRequId());
                    jstyleMotorVibrationReq2.setDataType("MotorVibrationWithTimes");
                    jstyleMotorVibrationReq2.setTimes(5);
                    return jstyleMotorVibrationReq2;
                } else if (bleBaseRequest instanceof EcgDataRequest) {
                    JstyleECGMeasureReq jstyleECGMeasureReq = new JstyleECGMeasureReq();
                    jstyleECGMeasureReq.setReqId(bleBaseRequest.getRequId());
                    jstyleECGMeasureReq.setDataType(BleConst.ENTERECG);
                    if (z) {
                        NskAlgoSdk.NskAlgoStart(false);
                    }
                    return jstyleECGMeasureReq;
                } else if (bleBaseRequest instanceof SetVibrationAlarmListRequest) {
                    JstyleVibrationAlarmReq jstyleVibrationAlarmReq = new JstyleVibrationAlarmReq();
                    ArrayList arrayList = new ArrayList();
                    int i5 = 0;
                    while (true) {
                        SetVibrationAlarmListRequest setVibrationAlarmListRequest = (SetVibrationAlarmListRequest) bleBaseRequest;
                        if (i5 < setVibrationAlarmListRequest.getVibrationAlarmRequests().size()) {
                            SetVibrationAlarmRequest setVibrationAlarmRequest = setVibrationAlarmListRequest.getVibrationAlarmRequests().get(i5);
                            Clock clock = new Clock();
                            clock.setContent(setVibrationAlarmRequest.getEventName());
                            clock.setEnable(setVibrationAlarmRequest.isEnabled());
                            clock.setHour(setVibrationAlarmRequest.getHour());
                            clock.setMinute(setVibrationAlarmRequest.getMinute());
                            clock.setNumber(setVibrationAlarmRequest.getAlarmId());
                            int[] iArr3 = new int[i];
                            iArr3[0] = setVibrationAlarmRequest.isSundayEnabled() ? 1 : 0;
                            iArr3[c6] = setVibrationAlarmRequest.isMondayEnabled() ? 1 : 0;
                            iArr3[c5] = setVibrationAlarmRequest.isTuesdayEnabled() ? 1 : 0;
                            iArr3[c3] = setVibrationAlarmRequest.isWednesdayEnabled() ? 1 : 0;
                            iArr3[c2] = setVibrationAlarmRequest.isThursdayEnabled() ? 1 : 0;
                            iArr3[c4] = setVibrationAlarmRequest.isFridayEnabled() ? 1 : 0;
                            iArr3[c] = setVibrationAlarmRequest.isSaturdayEnabled() ? 1 : 0;
                            int i6 = 0;
                            int i7 = 0;
                            while (i6 < i) {
                                if (iArr3[i6] == c6) {
                                    i7 = (int) (i7 + Math.pow(2.0d, i6));
                                }
                                i6++;
                                i = 7;
                                c6 = 1;
                            }
                            clock.setType(setVibrationAlarmRequest.getAlarmType());
                            clock.setWeek((byte) i7);
                            arrayList.add(clock);
                            i5++;
                            c = 6;
                            c2 = 4;
                            c3 = 3;
                            c4 = 5;
                            c5 = 2;
                            i = 7;
                            c6 = 1;
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
            return null;
        }
    }

    public final void a(BleBaseRequest bleBaseRequest) {
        BaseListener responseListener = bleBaseRequest.getResponseListener();
        if (responseListener instanceof DataResultListener) {
            ((DataResultListener) responseListener).onDataError(new BleBaseError(context.getString(R.string.command_not_found)));
        } else if (responseListener instanceof SettingsResultListener) {
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(context.getString(R.string.command_not_found)));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, context.getString(R.string.command_not_found)));
        }
    }

    public final void a() {
        if (queue == null || queue.size() <= 0) {
            return;
        }
        JstyleBaseReq a2 = a(queue.get(0).f3161a, true);
        if (a2 != null) {
            BleBaseRequest bleBaseRequest = this.g;
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
        a(queue.get(0).f3161a);
    }

    public final void a(JstyleBaseReq jstyleBaseReq) {
        this.g = queue.get(0).f3161a;
        if (jstyleBaseReq.getDataType() != null && jstyleBaseReq.getDataType().equalsIgnoreCase(BleConst.ENTERECG)) {
            this.syncTimeOutHandler.postDelayed(this.j, 120000L);
        } else if (jstyleBaseReq.isMultiPacket()) {
            this.syncTimeOutHandler.postDelayed(this.j, com.clevertap.android.sdk.Constants.ONE_MIN_IN_MILLIS);
        } else {
            this.syncTimeOutHandler.postDelayed(this.j, 30000L);
        }
        bleService.sendRequest(jstyleBaseReq, this);
    }
}
