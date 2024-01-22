package com.coveiot.android.bleabstract.bleimpl;

import android.app.ActivityManager;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.formatter.JStyle2208AFormatter;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ActivityState;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.preferences.PreferenceManager2208;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.PeriodicSPO2BleRequest;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.Spo2TimeIntervalRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.request.StressDataRequest;
import com.coveiot.android.bleabstract.request.StressTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.TemperatureDataRequest;
import com.coveiot.android.bleabstract.request.TemperatureTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
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
import com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleManager;
import com.coveiot.android.bleabstract.services.jstyle2301.JStyle2208ABleService;
import com.coveiot.android.jstyle2208a.JstyleResponseListener;
import com.coveiot.android.jstyle2208a.api.JStyleVBaseReq;
import com.coveiot.android.jstyle2208a.error.JstyleError;
import com.coveiot.android.jstyle2208a.error.JstyleErrorType;
import com.coveiot.android.jstyle2208a.model.JstyleLiveResponse;
import com.coveiot.khjstyledb.deviceinfo.KHJstyleDeviceInfoRepository;
import com.coveiot.khjstyledb.deviceinfo.KHJstyleEntityDeviceInfo;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.scan.AssociationResult;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.jstyle.blesdk2208.callback.BleConnectionListener;
import com.jstyle.blesdk2208.model.AutoMode;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
public class JStyle2208ABaseBleApiImpl implements BleApi, BleConnectionListener, JstyleResponseListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3124a;
    @NotNull
    public final Handler b;
    @NotNull
    public final Handler c;
    @Nullable
    public DeviceInfoData d;
    @Nullable
    public ConnectionResultListener e;
    @NotNull
    public final Handler f;
    @Nullable
    public BleBaseRequest g;
    public final int h;
    public final int i;
    @NotNull
    public final Handler j;
    @Nullable
    public MutableLiveData<ConnectionStatus> k;
    @Nullable
    public MutableLiveData<LiveHealthData> l;
    @Nullable
    public MutableLiveData<LiveStepsData> m;
    @Nullable
    public MutableLiveData<LiveSportData> n;
    @NotNull
    public final LinkedList<QueueObject> o;
    @Nullable
    public DeviceSupportedFeatures p;
    public final String q;
    @Nullable
    public ServiceConnection r;
    @NotNull
    public final Runnable s;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<JStyle2208ABaseBleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, JStyle2208ABaseBleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3125a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, JStyle2208ABaseBleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public JStyle2208ABaseBleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new JStyle2208ABaseBleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3125a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public static final class QueueObject {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3126a;

        public QueueObject(@NotNull BleBaseRequest baseRequest) {
            Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
            this.f3126a = baseRequest;
        }

        @NotNull
        public final BleBaseRequest getBaseRequest() {
            return this.f3126a;
        }

        public final void setBaseRequest(@NotNull BleBaseRequest bleBaseRequest) {
            Intrinsics.checkNotNullParameter(bleBaseRequest, "<set-?>");
            this.f3126a = bleBaseRequest;
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
            NotificationType.values();
            int[] iArr2 = new int[65];
            try {
                NotificationType notificationType = NotificationType.SMS;
                iArr2[2] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                NotificationType notificationType2 = NotificationType.CALL;
                iArr2[0] = 2;
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
                NotificationType notificationType6 = NotificationType.SKYPE;
                iArr2[14] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                NotificationType notificationType7 = NotificationType.TELEGRAM;
                iArr2[17] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                NotificationType notificationType8 = NotificationType.TWITTER;
                iArr2[8] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                NotificationType notificationType9 = NotificationType.VKCLIENT;
                iArr2[16] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                NotificationType notificationType10 = NotificationType.WHATSAPP;
                iArr2[4] = 10;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                NotificationType notificationType11 = NotificationType.QQ;
                iArr2[10] = 11;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                NotificationType notificationType12 = NotificationType.LINKEDIN;
                iArr2[15] = 12;
            } catch (NoSuchFieldError unused15) {
            }
            $EnumSwitchMapping$1 = iArr2;
            ActivityState.values();
            $EnumSwitchMapping$2 = new int[]{1, 2, 3, 4};
            int[] iArr3 = new int[JstyleErrorType.values().length];
            try {
                iArr3[JstyleErrorType.COMMAND_TIME_OUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr3[JstyleErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr3[JstyleErrorType.COMMAND_RESPONSE_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            $EnumSwitchMapping$3 = iArr3;
        }
    }

    public JStyle2208ABaseBleApiImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f3124a = context;
        this.b = new Handler();
        this.c = new Handler();
        this.f = new Handler();
        this.h = 60000;
        this.i = 30000;
        this.j = new Handler(Looper.getMainLooper());
        this.o = new LinkedList<>();
        this.q = JStyle2208ABaseBleApiImpl.class.getSimpleName();
        registerEventBus();
        b();
        this.s = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.g4
            @Override // java.lang.Runnable
            public final void run() {
                JStyle2208ABaseBleApiImpl.d(JStyle2208ABaseBleApiImpl.this);
            }
        };
    }

    public static final void c(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void d(JStyle2208ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.q, "Command TimeOut,Failed");
        BleBaseRequest bleBaseRequest = this$0.g;
        if (bleBaseRequest != null) {
            Intrinsics.checkNotNull(bleBaseRequest);
            JStyleVBaseReq jStyleBaseRequest = this$0.getJStyleBaseRequest(bleBaseRequest);
            if (jStyleBaseRequest != null) {
                this$0.onFailure(new JstyleError(JstyleErrorType.COMMAND_TIME_OUT, this$0.f3124a.getString(R.string.command_time_out)));
                String str = this$0.q;
                LogHelper.e(str, "End Time " + System.currentTimeMillis() + "-- currentCommand " + jStyleBaseRequest);
            } else {
                BleBaseRequest bleBaseRequest2 = this$0.g;
                Intrinsics.checkNotNull(bleBaseRequest2);
                this$0.sendCommandNotFoundError(bleBaseRequest2);
            }
        }
        this$0.g = null;
    }

    public static final void e(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public static final void f(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public static final void g(DataResultListener dataResultListener, BleBaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "$response");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(response);
    }

    public static final void h(DataResultListener dataResultListener, BleBaseResponse hrResponse) {
        Intrinsics.checkNotNullParameter(hrResponse, "$hrResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(hrResponse);
    }

    public static final void i(DataResultListener dataResultListener, BleBaseResponse spo2BleResponse) {
        Intrinsics.checkNotNullParameter(spo2BleResponse, "$spo2BleResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(spo2BleResponse);
    }

    public static final void j(DataResultListener dataResultListener, BleBaseResponse spo2BleResponse) {
        Intrinsics.checkNotNullParameter(spo2BleResponse, "$spo2BleResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(spo2BleResponse);
    }

    public static final void k(DataResultListener dataResultListener, BleBaseResponse walkResponse) {
        Intrinsics.checkNotNullParameter(walkResponse, "$walkResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(walkResponse);
    }

    public static final void l(DataResultListener dataResultListener, BleBaseResponse walkResponse) {
        Intrinsics.checkNotNullParameter(walkResponse, "$walkResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(walkResponse);
    }

    public static final void m(DataResultListener dataResultListener, BleBaseResponse mSleepResponse) {
        Intrinsics.checkNotNullParameter(mSleepResponse, "$mSleepResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mSleepResponse);
    }

    public void BleStatus(int i, int i2) {
    }

    public void BluetoothSwitchIsTurnedOff() {
        ConnectionResultListener connectionResultListener = this.e;
        if (connectionResultListener != null) {
            connectionResultListener.onConnectionResponse(ConnectionStatus.DISCONNECTED);
        }
    }

    public void Connecting() {
        ConnectionResultListener connectionResultListener = this.e;
        if (connectionResultListener != null) {
            connectionResultListener.onConnectionResponse(ConnectionStatus.CONNECTING);
        }
    }

    public void ConnectionFailed() {
        ConnectionResultListener connectionResultListener = this.e;
        if (connectionResultListener != null) {
            connectionResultListener.onConnectionResponse(ConnectionStatus.DISCONNECTED);
        }
    }

    public void ConnectionSucceeded() {
        ConnectionResultListener connectionResultListener = this.e;
        if (connectionResultListener != null) {
            connectionResultListener.onConnectionResponse(ConnectionStatus.CONNECTED);
        }
    }

    public void OnReconnect() {
        ConnectionResultListener connectionResultListener = this.e;
        if (connectionResultListener != null) {
            connectionResultListener.onConnectionResponse(ConnectionStatus.CONNECTING);
        }
    }

    public final void a(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        this.b.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(this.f3124a).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl$scan_$1
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(@NotNull CharSequence error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ScanResultListener scanResultListener2 = scanResultListener;
                        String string = JStyle2208ABaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                        scanResultListener2.onError(string);
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(@NotNull String messgae) {
                        Intrinsics.checkNotNullParameter(messgae, "messgae");
                        JStyle2208ABaseBleApiImpl.this.scanResultReceieved(new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.b.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.j4
            @Override // java.lang.Runnable
            public final void run() {
                JStyle2208ABaseBleApiImpl.a(JStyle2208ABaseBleApiImpl.this, scanDeviceRequest, scanResultListener);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void addToQueue(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        synchronized (this.o) {
            JStyleVBaseReq jStyleBaseRequest = getJStyleBaseRequest(baseRequest);
            if (jStyleBaseRequest != null) {
                if (jStyleBaseRequest.isPriority()) {
                    this.o.addFirst(new QueueObject(baseRequest));
                } else {
                    this.o.add(new QueueObject(baseRequest));
                }
            } else if (!(baseRequest instanceof ActivityModeSummaryRequest)) {
                sendCommandNotFoundError(baseRequest);
            }
        }
    }

    public final void b() {
        JStyle2208ABleManager.init(this.f3124a);
    }

    public final void bindBleService() {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return false;
    }

    public void checkAndStartService() {
    }

    public final boolean checkIfServiceIsRunning() {
        Object systemService = this.f3124a.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (Intrinsics.areEqual(JStyle2208ABleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3124a.getPackageName(), runningServiceInfo.service.getPackageName())) {
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
        this.f.removeCallbacksAndMessages(null);
        this.g = null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(@NotNull ConnectRequest request, @NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.e = listener;
        JStyle2208ABleManager.getInstance().connectDevice(request.getMacAddress(), true, this);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        LogHelper.d(this.q, "on disconnect");
        this.e = listener;
        JStyle2208ABleManager.getInstance().disconnectDevice();
        a();
        listener.onConnectionResponse(ConnectionStatus.DISCONNECTED);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return BusyStatus.IDLE;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public ConnectionStatus getConnectionStatus() {
        ConnectionStatus connectionState = JStyle2208ABleManager.getInstance().getConnectionState();
        Intrinsics.checkNotNullExpressionValue(connectionState, "getInstance().getConnectionState()");
        return connectionState;
    }

    @NotNull
    public final Context getContext() {
        return this.f3124a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        String str;
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (JStyle2208ABleManager.getInstance().isConnected()) {
            if (request instanceof StepsDataRequest) {
                str = BleConst.GetDetailActivityData;
            } else if (request instanceof SleepDataRequest) {
                str = BleConst.GetDetailSleepData;
            } else if (request instanceof HeartRateDataRequest) {
                str = BleConst.GetDynamicHR;
            } else if (request instanceof TemperatureDataRequest) {
                str = BleConst.GetTempHistoryData;
            } else if (request instanceof PeriodicSPO2BleRequest) {
                str = "55";
            } else if (request instanceof StressDataRequest) {
                str = "42";
            } else if (request instanceof HeartRateTimeIntervalRequest) {
                str = BleConst.GetAutomaticHRMonitoring + AutoMode.AutoHeartRate;
            } else if (request instanceof TemperatureTimeIntervalRequest) {
                str = BleConst.GetAutomaticHRMonitoring + AutoMode.AutoTemp;
            } else if (request instanceof Spo2TimeIntervalRequest) {
                str = BleConst.GetAutomaticHRMonitoring + AutoMode.AutoSpo2;
            } else if (request instanceof StressTimeIntervalRequest) {
                str = BleConst.GetAutomaticHRMonitoring + AutoMode.AutoHrv;
            } else {
                str = null;
            }
            request.setRequId(str);
            request.setResponseListener(listener);
            addToQueue(request);
            sendCommandRequest();
            return;
        }
        String string = this.f3124a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onDataError(new BleBaseError(string));
    }

    @Nullable
    public String getDataType(@Nullable Map<String, Object> map) {
        return map == null ? "" : (String) map.get(DeviceKey.DataType);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        this.p = deviceSupportedFeatures;
        deviceSupportedFeatures.setStepsSupported(true);
        DeviceSupportedFeatures deviceSupportedFeatures2 = this.p;
        if (deviceSupportedFeatures2 != null) {
            deviceSupportedFeatures2.setSleepSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures3 = this.p;
        if (deviceSupportedFeatures3 != null) {
            deviceSupportedFeatures3.setHeartRateSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures4 = this.p;
        if (deviceSupportedFeatures4 != null) {
            deviceSupportedFeatures4.setTemparatureHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures5 = this.p;
        if (deviceSupportedFeatures5 != null) {
            deviceSupportedFeatures5.setPeriodicSpO2Supported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures6 = this.p;
        if (deviceSupportedFeatures6 != null) {
            deviceSupportedFeatures6.setStressHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures7 = this.p;
        if (deviceSupportedFeatures7 != null) {
            deviceSupportedFeatures7.setHRVHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures8 = this.p;
        if (deviceSupportedFeatures8 != null) {
            deviceSupportedFeatures8.setAutoHrSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures9 = this.p;
        if (deviceSupportedFeatures9 != null) {
            deviceSupportedFeatures9.setAutoTemperatureSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures10 = this.p;
        if (deviceSupportedFeatures10 != null) {
            deviceSupportedFeatures10.setAutoSPO2SettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures11 = this.p;
        if (deviceSupportedFeatures11 != null) {
            deviceSupportedFeatures11.setAutoStressSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures12 = this.p;
        if (deviceSupportedFeatures12 != null) {
            deviceSupportedFeatures12.setVibrationAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures13 = this.p;
        if (deviceSupportedFeatures13 != null) {
            deviceSupportedFeatures13.setBandSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures14 = this.p;
        if (deviceSupportedFeatures14 != null) {
            deviceSupportedFeatures14.setREMSupportedInSleep(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures15 = this.p;
        if (deviceSupportedFeatures15 != null) {
            deviceSupportedFeatures15.setNotificationSupport(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures16 = this.p;
        if (deviceSupportedFeatures16 != null) {
            deviceSupportedFeatures16.setLiveStepsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures17 = this.p;
        if (deviceSupportedFeatures17 != null) {
            deviceSupportedFeatures17.setLiveHeartRateSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures18 = this.p;
        if (deviceSupportedFeatures18 != null) {
            deviceSupportedFeatures18.setSedentaryReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures19 = this.p;
        if (deviceSupportedFeatures19 != null) {
            deviceSupportedFeatures19.setRepeatDaysSupportedInSedentary(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures20 = this.p;
        if (deviceSupportedFeatures20 != null) {
            deviceSupportedFeatures20.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures21 = this.p;
        if (deviceSupportedFeatures21 != null) {
            deviceSupportedFeatures21.setSportModeSupportedFromApp(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures22 = this.p;
        if (deviceSupportedFeatures22 != null) {
            deviceSupportedFeatures22.setSportsModeHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures23 = this.p;
        if (deviceSupportedFeatures23 != null) {
            deviceSupportedFeatures23.setFitnessValueCommandSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures24 = this.p;
        if (deviceSupportedFeatures24 != null) {
            deviceSupportedFeatures24.setManualBpSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures25 = this.p;
        if (deviceSupportedFeatures25 != null) {
            deviceSupportedFeatures25.setManualSpo2SupportedOnBand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures26 = this.p;
        if (deviceSupportedFeatures26 != null) {
            deviceSupportedFeatures26.setManualStressMeasurementSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures27 = this.p;
        if (deviceSupportedFeatures27 != null) {
            deviceSupportedFeatures27.setStepGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures28 = this.p;
        if (deviceSupportedFeatures28 != null) {
            deviceSupportedFeatures28.setCallNotificationSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures29 = this.p;
        if (deviceSupportedFeatures29 != null) {
            deviceSupportedFeatures29.setSmsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures30 = this.p;
        if (deviceSupportedFeatures30 != null) {
            deviceSupportedFeatures30.setMessageReadSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures31 = this.p;
        if (deviceSupportedFeatures31 != null) {
            deviceSupportedFeatures31.setSocialNotificationSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures32 = this.p;
        if (deviceSupportedFeatures32 != null) {
            deviceSupportedFeatures32.setHandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures33 = this.p;
        if (deviceSupportedFeatures33 != null) {
            deviceSupportedFeatures33.setPhoneFinderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures34 = this.p;
        if (deviceSupportedFeatures34 != null) {
            deviceSupportedFeatures34.setLiveBPSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures35 = this.p;
        if (deviceSupportedFeatures35 != null) {
            deviceSupportedFeatures35.setHandPreferenceSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures36 = this.p;
        if (deviceSupportedFeatures36 != null) {
            deviceSupportedFeatures36.setTimeFormatSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures37 = this.p;
        if (deviceSupportedFeatures37 != null) {
            deviceSupportedFeatures37.setDistanceUnitSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures38 = this.p;
        if (deviceSupportedFeatures38 != null) {
            deviceSupportedFeatures38.setLiftWristToViewSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures39 = this.p;
        if (deviceSupportedFeatures39 != null) {
            deviceSupportedFeatures39.setTemperatureUnitSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures40 = this.p;
        if (deviceSupportedFeatures40 != null) {
            deviceSupportedFeatures40.setProbeFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures41 = this.p;
        if (deviceSupportedFeatures41 != null) {
            deviceSupportedFeatures41.setMultipleAlarmsSupportedAtATime(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures42 = this.p;
        if (deviceSupportedFeatures42 != null) {
            deviceSupportedFeatures42.setOnceAlarmSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures43 = this.p;
        if (deviceSupportedFeatures43 != null) {
            deviceSupportedFeatures43.setSampleDataSupportedInSportMode(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures44 = this.p;
        if (deviceSupportedFeatures44 != null) {
            deviceSupportedFeatures44.setSyncBandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures45 = this.p;
        if (deviceSupportedFeatures45 != null) {
            deviceSupportedFeatures45.setDeviceSettingsSupportedInOneCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures46 = this.p;
        if (deviceSupportedFeatures46 != null) {
            deviceSupportedFeatures46.setBandSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures47 = this.p;
        if (deviceSupportedFeatures47 != null) {
            deviceSupportedFeatures47.setScheduledDndSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures48 = this.p;
        if (deviceSupportedFeatures48 != null) {
            deviceSupportedFeatures48.setMusicMetaDataChangeFromAppSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures49 = this.p;
        if (deviceSupportedFeatures49 != null) {
            deviceSupportedFeatures49.setMusicPlaybackStateChangeFromAppSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures50 = this.p;
        if (deviceSupportedFeatures50 != null) {
            deviceSupportedFeatures50.setMusicVolumeChangeFromAppSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures51 = this.p;
        if (deviceSupportedFeatures51 != null) {
            deviceSupportedFeatures51.setMaxAlarmSupportedOnBand(5);
        }
        DeviceSupportedFeatures deviceSupportedFeatures52 = this.p;
        if (deviceSupportedFeatures52 != null) {
            deviceSupportedFeatures52.setWatchFaceSupport(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures53 = this.p;
        if (deviceSupportedFeatures53 != null) {
            deviceSupportedFeatures53.setDndSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures54 = this.p;
        if (deviceSupportedFeatures54 != null) {
            deviceSupportedFeatures54.setShortcutMenuShowHideCommandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures55 = this.p;
        if (deviceSupportedFeatures55 != null) {
            deviceSupportedFeatures55.setFemaleWellnessSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures56 = this.p;
        if (deviceSupportedFeatures56 != null) {
            deviceSupportedFeatures56.setContactSyncSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures57 = this.p;
        if (deviceSupportedFeatures57 != null) {
            deviceSupportedFeatures57.setDrinkingReminderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures58 = this.p;
        if (deviceSupportedFeatures58 != null) {
            deviceSupportedFeatures58.setFindMyBandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures59 = this.p;
        if (deviceSupportedFeatures59 != null) {
            deviceSupportedFeatures59.setGpsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures60 = this.p;
        if (deviceSupportedFeatures60 != null) {
            deviceSupportedFeatures60.setWeatherEnableCommandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures61 = this.p;
        if (deviceSupportedFeatures61 != null) {
            deviceSupportedFeatures61.setWeatherSupportedInBand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures62 = this.p;
        if (deviceSupportedFeatures62 != null) {
            deviceSupportedFeatures62.setGoalSettingSupportedInSingleCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures63 = this.p;
        if (deviceSupportedFeatures63 != null) {
            deviceSupportedFeatures63.setBTCallingSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures64 = this.p;
        if (deviceSupportedFeatures64 != null) {
            deviceSupportedFeatures64.setMaxContactsInOneRequest(10);
        }
        DeviceSupportedFeatures deviceSupportedFeatures65 = this.p;
        if (deviceSupportedFeatures65 != null) {
            deviceSupportedFeatures65.setGenericActivityDataSampleSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures66 = this.p;
        if (deviceSupportedFeatures66 != null) {
            deviceSupportedFeatures66.setMusicDataSupportInSingleCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures67 = this.p;
        if (deviceSupportedFeatures67 != null) {
            deviceSupportedFeatures67.setSleepTargetSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures68 = this.p;
        if (deviceSupportedFeatures68 != null) {
            deviceSupportedFeatures68.setCalorieGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures69 = this.p;
        if (deviceSupportedFeatures69 != null) {
            deviceSupportedFeatures69.setDistanceGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures70 = this.p;
        if (deviceSupportedFeatures70 != null) {
            deviceSupportedFeatures70.setExerciseMinutesGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures71 = this.p;
        if (deviceSupportedFeatures71 != null) {
            deviceSupportedFeatures71.setTitleSupportedInNotification(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures72 = this.p;
        if (deviceSupportedFeatures72 != null) {
            deviceSupportedFeatures72.setBandVolumeControlSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures73 = this.p;
        Intrinsics.checkNotNull(deviceSupportedFeatures73);
        return deviceSupportedFeatures73;
    }

    @Nullable
    public final BleBaseRequest getFromQueue(@Nullable JStyleVBaseReq jStyleVBaseReq) {
        int size = this.o.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                return null;
            }
            if (kotlin.text.m.equals(this.o.get(i).getBaseRequest().getRequId(), jStyleVBaseReq != null ? jStyleVBaseReq.getReqId() : null, true)) {
                return this.o.get(i).getBaseRequest();
            }
            i++;
        }
    }

    @Nullable
    public final JStyleVBaseReq getJStyleBaseRequest(@NotNull BleBaseRequest bleBaseRequest) {
        throw null;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManagerAbstract.getInstance(this.f3124a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        return connectedDeviceMacAddress;
    }

    @NotNull
    public final LinkedList<QueueObject> getQueue() {
        return this.o;
    }

    @Nullable
    public final ServiceConnection getServiceConnection() {
        return this.r;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return checkIfServiceIsRunning();
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
            int[] r1 = com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl.WhenMappings.$EnumSwitchMapping$0
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
            com.coveiot.android.bleabstract.request.BleBaseRequest r0 = r3.g
            boolean r1 = r0 instanceof com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest
            if (r1 != 0) goto L4e
            boolean r0 = r0 instanceof com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest
            if (r0 == 0) goto L41
            goto L4e
        L41:
            com.coveiot.android.jstyle2208a.error.JstyleError r0 = new com.coveiot.android.jstyle2208a.error.JstyleError
            com.coveiot.android.jstyle2208a.error.JstyleErrorType r1 = com.coveiot.android.jstyle2208a.error.JstyleErrorType.DEVICE_NOT_CONNECTED
            java.lang.String r2 = "Device disconnected"
            r0.<init>(r1, r2)
            r3.sendErrorAndClearQueue(r0)
            goto L4f
        L4e:
            return
        L4f:
            com.coveiot.android.bleabstract.listeners.ConnectionResultListener r0 = r3.e
            if (r0 == 0) goto L59
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r0.onConnectionResponse(r4)
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl.onConnectionStateChangedHandler(com.coveiot.sdk.ble.CloveBleState):void");
    }

    public void onFailure(@NotNull JstyleError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        JstyleErrorType errorType = error.getErrorType();
        int i = errorType == null ? -1 : WhenMappings.$EnumSwitchMapping$3[errorType.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            sendErrorAndClearQueue(error);
        }
    }

    @Subscribe
    public void onLiveResponseReceived(@Nullable JstyleLiveResponse jstyleLiveResponse) {
        if (jstyleLiveResponse == null || jstyleLiveResponse.getDataType() == null) {
            return;
        }
        String dataType = jstyleLiveResponse.getDataType();
        if (!Intrinsics.areEqual(dataType, BleConst.RealTimeStep)) {
            if (Intrinsics.areEqual(dataType, "82")) {
                Object obj = jstyleLiveResponse.getObj();
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.String>");
                Map map = (Map) obj;
                String str = (String) map.get(DeviceKey.Step);
                Integer valueOf = str != null ? Integer.valueOf(Integer.parseInt(str)) : null;
                String str2 = (String) map.get("calories");
                Double valueOf2 = str2 != null ? Double.valueOf(Double.parseDouble(str2)) : null;
                String str3 = (String) map.get(DeviceKey.HeartRate);
                LiveSportData liveSportData = new LiveSportData(valueOf, valueOf2, str3 != null ? Integer.valueOf(Integer.parseInt(str3)) : null);
                MutableLiveData<LiveSportData> mutableLiveData = this.n;
                if (mutableLiveData != null) {
                    mutableLiveData.setValue(liveSportData);
                }
                MutableLiveData<LiveSportData> mutableLiveData2 = this.n;
                if (mutableLiveData2 != null) {
                    mutableLiveData2.postValue(liveSportData);
                }
            }
        } else if (jstyleLiveResponse.getObj() != null) {
            Object obj2 = jstyleLiveResponse.getObj();
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.String>");
            Map<String, String> map2 = (Map) obj2;
            LiveStepsData convertToLiveStepsData = new JStyle2208AFormatter(getMacAddress()).convertToLiveStepsData(map2);
            MutableLiveData<LiveStepsData> mutableLiveData3 = this.m;
            if (mutableLiveData3 != null) {
                Intrinsics.checkNotNull(mutableLiveData3);
                mutableLiveData3.setValue(convertToLiveStepsData);
                MutableLiveData<LiveStepsData> mutableLiveData4 = this.m;
                Intrinsics.checkNotNull(mutableLiveData4);
                mutableLiveData4.postValue(convertToLiveStepsData);
            }
            LiveHealthData convertToLiveHealthData = new JStyle2208AFormatter(getMacAddress()).convertToLiveHealthData(map2);
            MutableLiveData<LiveHealthData> mutableLiveData5 = this.l;
            if (mutableLiveData5 != null) {
                Intrinsics.checkNotNull(mutableLiveData5);
                mutableLiveData5.setValue(convertToLiveHealthData);
                MutableLiveData<LiveHealthData> mutableLiveData6 = this.l;
                Intrinsics.checkNotNull(mutableLiveData6);
                mutableLiveData6.postValue(convertToLiveHealthData);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0238 A[Catch: Exception -> 0x09d7, TryCatch #0 {Exception -> 0x09d7, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:166:0x04c5, B:169:0x04cf, B:171:0x04d3, B:172:0x04d6, B:174:0x0505, B:177:0x050c, B:178:0x0517, B:180:0x051d, B:182:0x052a, B:184:0x0531, B:183:0x052e, B:185:0x054d, B:186:0x0552, B:188:0x056b, B:191:0x0575, B:193:0x0579, B:194:0x057c, B:196:0x05b0, B:199:0x05b7, B:201:0x05c1, B:203:0x05e5, B:205:0x05e9, B:206:0x05f8, B:202:0x05c8, B:207:0x0604, B:210:0x060e, B:212:0x0612, B:213:0x0615, B:214:0x0663, B:217:0x066d, B:219:0x0671, B:220:0x0674, B:222:0x06a3, B:225:0x06aa, B:227:0x06b4, B:232:0x06d9, B:234:0x06df, B:236:0x06ec, B:238:0x06f9, B:237:0x06f3, B:239:0x0715, B:240:0x071a, B:229:0x06ba, B:241:0x0726, B:244:0x0730, B:246:0x0734, B:247:0x0737, B:249:0x0764, B:252:0x076b, B:254:0x0775, B:259:0x079a, B:261:0x07a0, B:263:0x07bf, B:265:0x07c6, B:264:0x07c3, B:266:0x07e2, B:267:0x07e7, B:256:0x077b, B:268:0x07f3, B:271:0x07fd, B:273:0x0801, B:274:0x0804, B:276:0x0836, B:279:0x083d, B:281:0x089a, B:280:0x0887, B:17:0x0059, B:162:0x04a9, B:164:0x04b3, B:165:0x04b6, B:20:0x0063, B:23:0x006d, B:25:0x0071, B:26:0x0074, B:28:0x00a3, B:30:0x00a9, B:32:0x00b3, B:34:0x00b9, B:36:0x00bf, B:37:0x00d7, B:38:0x00e3, B:41:0x00ed, B:44:0x00f7, B:47:0x0101, B:49:0x0105, B:50:0x0108, B:52:0x0117, B:55:0x011e, B:82:0x01bc, B:56:0x0141, B:59:0x0155, B:61:0x015f, B:63:0x0169, B:65:0x0170, B:68:0x017a, B:70:0x0184, B:74:0x0191, B:75:0x0194, B:77:0x0199, B:79:0x01a3, B:81:0x01b9, B:83:0x01c1, B:86:0x01cb, B:88:0x01cf, B:89:0x01d2, B:91:0x0201, B:94:0x0208, B:96:0x0214, B:100:0x0238, B:102:0x023f, B:104:0x024c, B:106:0x0253, B:105:0x0250, B:107:0x026f, B:108:0x0274, B:98:0x021a, B:109:0x0280, B:112:0x028a, B:114:0x028e, B:115:0x0291, B:117:0x02a0, B:118:0x02ac, B:121:0x02b3, B:123:0x02bc, B:127:0x02cf, B:128:0x02ee, B:130:0x0313, B:129:0x02f2, B:131:0x0318, B:134:0x0322, B:136:0x0326, B:137:0x032d, B:138:0x03ad, B:141:0x03b7, B:143:0x03bb, B:144:0x03be, B:146:0x03fc, B:148:0x0409, B:147:0x0405, B:149:0x0426, B:152:0x0430, B:154:0x0434, B:155:0x0437, B:156:0x0495, B:159:0x049f, B:282:0x08b4, B:284:0x08bc, B:285:0x08c8, B:287:0x08cd, B:344:0x0988, B:346:0x098c, B:347:0x098f, B:290:0x08d7, B:293:0x08e1, B:296:0x08eb, B:299:0x08f5, B:302:0x08ff, B:305:0x0909, B:308:0x0913, B:311:0x091d, B:314:0x0927, B:317:0x0931, B:320:0x093b, B:323:0x0945, B:326:0x094f, B:329:0x0959, B:332:0x0963, B:335:0x096d, B:338:0x0976, B:341:0x097f, B:348:0x09c1, B:349:0x09cc), top: B:354:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0274 A[Catch: Exception -> 0x09d7, TryCatch #0 {Exception -> 0x09d7, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:166:0x04c5, B:169:0x04cf, B:171:0x04d3, B:172:0x04d6, B:174:0x0505, B:177:0x050c, B:178:0x0517, B:180:0x051d, B:182:0x052a, B:184:0x0531, B:183:0x052e, B:185:0x054d, B:186:0x0552, B:188:0x056b, B:191:0x0575, B:193:0x0579, B:194:0x057c, B:196:0x05b0, B:199:0x05b7, B:201:0x05c1, B:203:0x05e5, B:205:0x05e9, B:206:0x05f8, B:202:0x05c8, B:207:0x0604, B:210:0x060e, B:212:0x0612, B:213:0x0615, B:214:0x0663, B:217:0x066d, B:219:0x0671, B:220:0x0674, B:222:0x06a3, B:225:0x06aa, B:227:0x06b4, B:232:0x06d9, B:234:0x06df, B:236:0x06ec, B:238:0x06f9, B:237:0x06f3, B:239:0x0715, B:240:0x071a, B:229:0x06ba, B:241:0x0726, B:244:0x0730, B:246:0x0734, B:247:0x0737, B:249:0x0764, B:252:0x076b, B:254:0x0775, B:259:0x079a, B:261:0x07a0, B:263:0x07bf, B:265:0x07c6, B:264:0x07c3, B:266:0x07e2, B:267:0x07e7, B:256:0x077b, B:268:0x07f3, B:271:0x07fd, B:273:0x0801, B:274:0x0804, B:276:0x0836, B:279:0x083d, B:281:0x089a, B:280:0x0887, B:17:0x0059, B:162:0x04a9, B:164:0x04b3, B:165:0x04b6, B:20:0x0063, B:23:0x006d, B:25:0x0071, B:26:0x0074, B:28:0x00a3, B:30:0x00a9, B:32:0x00b3, B:34:0x00b9, B:36:0x00bf, B:37:0x00d7, B:38:0x00e3, B:41:0x00ed, B:44:0x00f7, B:47:0x0101, B:49:0x0105, B:50:0x0108, B:52:0x0117, B:55:0x011e, B:82:0x01bc, B:56:0x0141, B:59:0x0155, B:61:0x015f, B:63:0x0169, B:65:0x0170, B:68:0x017a, B:70:0x0184, B:74:0x0191, B:75:0x0194, B:77:0x0199, B:79:0x01a3, B:81:0x01b9, B:83:0x01c1, B:86:0x01cb, B:88:0x01cf, B:89:0x01d2, B:91:0x0201, B:94:0x0208, B:96:0x0214, B:100:0x0238, B:102:0x023f, B:104:0x024c, B:106:0x0253, B:105:0x0250, B:107:0x026f, B:108:0x0274, B:98:0x021a, B:109:0x0280, B:112:0x028a, B:114:0x028e, B:115:0x0291, B:117:0x02a0, B:118:0x02ac, B:121:0x02b3, B:123:0x02bc, B:127:0x02cf, B:128:0x02ee, B:130:0x0313, B:129:0x02f2, B:131:0x0318, B:134:0x0322, B:136:0x0326, B:137:0x032d, B:138:0x03ad, B:141:0x03b7, B:143:0x03bb, B:144:0x03be, B:146:0x03fc, B:148:0x0409, B:147:0x0405, B:149:0x0426, B:152:0x0430, B:154:0x0434, B:155:0x0437, B:156:0x0495, B:159:0x049f, B:282:0x08b4, B:284:0x08bc, B:285:0x08c8, B:287:0x08cd, B:344:0x0988, B:346:0x098c, B:347:0x098f, B:290:0x08d7, B:293:0x08e1, B:296:0x08eb, B:299:0x08f5, B:302:0x08ff, B:305:0x0909, B:308:0x0913, B:311:0x091d, B:314:0x0927, B:317:0x0931, B:320:0x093b, B:323:0x0945, B:326:0x094f, B:329:0x0959, B:332:0x0963, B:335:0x096d, B:338:0x0976, B:341:0x097f, B:348:0x09c1, B:349:0x09cc), top: B:354:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x05e9 A[Catch: Exception -> 0x09d7, TryCatch #0 {Exception -> 0x09d7, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:166:0x04c5, B:169:0x04cf, B:171:0x04d3, B:172:0x04d6, B:174:0x0505, B:177:0x050c, B:178:0x0517, B:180:0x051d, B:182:0x052a, B:184:0x0531, B:183:0x052e, B:185:0x054d, B:186:0x0552, B:188:0x056b, B:191:0x0575, B:193:0x0579, B:194:0x057c, B:196:0x05b0, B:199:0x05b7, B:201:0x05c1, B:203:0x05e5, B:205:0x05e9, B:206:0x05f8, B:202:0x05c8, B:207:0x0604, B:210:0x060e, B:212:0x0612, B:213:0x0615, B:214:0x0663, B:217:0x066d, B:219:0x0671, B:220:0x0674, B:222:0x06a3, B:225:0x06aa, B:227:0x06b4, B:232:0x06d9, B:234:0x06df, B:236:0x06ec, B:238:0x06f9, B:237:0x06f3, B:239:0x0715, B:240:0x071a, B:229:0x06ba, B:241:0x0726, B:244:0x0730, B:246:0x0734, B:247:0x0737, B:249:0x0764, B:252:0x076b, B:254:0x0775, B:259:0x079a, B:261:0x07a0, B:263:0x07bf, B:265:0x07c6, B:264:0x07c3, B:266:0x07e2, B:267:0x07e7, B:256:0x077b, B:268:0x07f3, B:271:0x07fd, B:273:0x0801, B:274:0x0804, B:276:0x0836, B:279:0x083d, B:281:0x089a, B:280:0x0887, B:17:0x0059, B:162:0x04a9, B:164:0x04b3, B:165:0x04b6, B:20:0x0063, B:23:0x006d, B:25:0x0071, B:26:0x0074, B:28:0x00a3, B:30:0x00a9, B:32:0x00b3, B:34:0x00b9, B:36:0x00bf, B:37:0x00d7, B:38:0x00e3, B:41:0x00ed, B:44:0x00f7, B:47:0x0101, B:49:0x0105, B:50:0x0108, B:52:0x0117, B:55:0x011e, B:82:0x01bc, B:56:0x0141, B:59:0x0155, B:61:0x015f, B:63:0x0169, B:65:0x0170, B:68:0x017a, B:70:0x0184, B:74:0x0191, B:75:0x0194, B:77:0x0199, B:79:0x01a3, B:81:0x01b9, B:83:0x01c1, B:86:0x01cb, B:88:0x01cf, B:89:0x01d2, B:91:0x0201, B:94:0x0208, B:96:0x0214, B:100:0x0238, B:102:0x023f, B:104:0x024c, B:106:0x0253, B:105:0x0250, B:107:0x026f, B:108:0x0274, B:98:0x021a, B:109:0x0280, B:112:0x028a, B:114:0x028e, B:115:0x0291, B:117:0x02a0, B:118:0x02ac, B:121:0x02b3, B:123:0x02bc, B:127:0x02cf, B:128:0x02ee, B:130:0x0313, B:129:0x02f2, B:131:0x0318, B:134:0x0322, B:136:0x0326, B:137:0x032d, B:138:0x03ad, B:141:0x03b7, B:143:0x03bb, B:144:0x03be, B:146:0x03fc, B:148:0x0409, B:147:0x0405, B:149:0x0426, B:152:0x0430, B:154:0x0434, B:155:0x0437, B:156:0x0495, B:159:0x049f, B:282:0x08b4, B:284:0x08bc, B:285:0x08c8, B:287:0x08cd, B:344:0x0988, B:346:0x098c, B:347:0x098f, B:290:0x08d7, B:293:0x08e1, B:296:0x08eb, B:299:0x08f5, B:302:0x08ff, B:305:0x0909, B:308:0x0913, B:311:0x091d, B:314:0x0927, B:317:0x0931, B:320:0x093b, B:323:0x0945, B:326:0x094f, B:329:0x0959, B:332:0x0963, B:335:0x096d, B:338:0x0976, B:341:0x097f, B:348:0x09c1, B:349:0x09cc), top: B:354:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x05f8 A[Catch: Exception -> 0x09d7, TryCatch #0 {Exception -> 0x09d7, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:166:0x04c5, B:169:0x04cf, B:171:0x04d3, B:172:0x04d6, B:174:0x0505, B:177:0x050c, B:178:0x0517, B:180:0x051d, B:182:0x052a, B:184:0x0531, B:183:0x052e, B:185:0x054d, B:186:0x0552, B:188:0x056b, B:191:0x0575, B:193:0x0579, B:194:0x057c, B:196:0x05b0, B:199:0x05b7, B:201:0x05c1, B:203:0x05e5, B:205:0x05e9, B:206:0x05f8, B:202:0x05c8, B:207:0x0604, B:210:0x060e, B:212:0x0612, B:213:0x0615, B:214:0x0663, B:217:0x066d, B:219:0x0671, B:220:0x0674, B:222:0x06a3, B:225:0x06aa, B:227:0x06b4, B:232:0x06d9, B:234:0x06df, B:236:0x06ec, B:238:0x06f9, B:237:0x06f3, B:239:0x0715, B:240:0x071a, B:229:0x06ba, B:241:0x0726, B:244:0x0730, B:246:0x0734, B:247:0x0737, B:249:0x0764, B:252:0x076b, B:254:0x0775, B:259:0x079a, B:261:0x07a0, B:263:0x07bf, B:265:0x07c6, B:264:0x07c3, B:266:0x07e2, B:267:0x07e7, B:256:0x077b, B:268:0x07f3, B:271:0x07fd, B:273:0x0801, B:274:0x0804, B:276:0x0836, B:279:0x083d, B:281:0x089a, B:280:0x0887, B:17:0x0059, B:162:0x04a9, B:164:0x04b3, B:165:0x04b6, B:20:0x0063, B:23:0x006d, B:25:0x0071, B:26:0x0074, B:28:0x00a3, B:30:0x00a9, B:32:0x00b3, B:34:0x00b9, B:36:0x00bf, B:37:0x00d7, B:38:0x00e3, B:41:0x00ed, B:44:0x00f7, B:47:0x0101, B:49:0x0105, B:50:0x0108, B:52:0x0117, B:55:0x011e, B:82:0x01bc, B:56:0x0141, B:59:0x0155, B:61:0x015f, B:63:0x0169, B:65:0x0170, B:68:0x017a, B:70:0x0184, B:74:0x0191, B:75:0x0194, B:77:0x0199, B:79:0x01a3, B:81:0x01b9, B:83:0x01c1, B:86:0x01cb, B:88:0x01cf, B:89:0x01d2, B:91:0x0201, B:94:0x0208, B:96:0x0214, B:100:0x0238, B:102:0x023f, B:104:0x024c, B:106:0x0253, B:105:0x0250, B:107:0x026f, B:108:0x0274, B:98:0x021a, B:109:0x0280, B:112:0x028a, B:114:0x028e, B:115:0x0291, B:117:0x02a0, B:118:0x02ac, B:121:0x02b3, B:123:0x02bc, B:127:0x02cf, B:128:0x02ee, B:130:0x0313, B:129:0x02f2, B:131:0x0318, B:134:0x0322, B:136:0x0326, B:137:0x032d, B:138:0x03ad, B:141:0x03b7, B:143:0x03bb, B:144:0x03be, B:146:0x03fc, B:148:0x0409, B:147:0x0405, B:149:0x0426, B:152:0x0430, B:154:0x0434, B:155:0x0437, B:156:0x0495, B:159:0x049f, B:282:0x08b4, B:284:0x08bc, B:285:0x08c8, B:287:0x08cd, B:344:0x0988, B:346:0x098c, B:347:0x098f, B:290:0x08d7, B:293:0x08e1, B:296:0x08eb, B:299:0x08f5, B:302:0x08ff, B:305:0x0909, B:308:0x0913, B:311:0x091d, B:314:0x0927, B:317:0x0931, B:320:0x093b, B:323:0x0945, B:326:0x094f, B:329:0x0959, B:332:0x0963, B:335:0x096d, B:338:0x0976, B:341:0x097f, B:348:0x09c1, B:349:0x09cc), top: B:354:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:231:0x06d8  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x071a A[Catch: Exception -> 0x09d7, TryCatch #0 {Exception -> 0x09d7, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:166:0x04c5, B:169:0x04cf, B:171:0x04d3, B:172:0x04d6, B:174:0x0505, B:177:0x050c, B:178:0x0517, B:180:0x051d, B:182:0x052a, B:184:0x0531, B:183:0x052e, B:185:0x054d, B:186:0x0552, B:188:0x056b, B:191:0x0575, B:193:0x0579, B:194:0x057c, B:196:0x05b0, B:199:0x05b7, B:201:0x05c1, B:203:0x05e5, B:205:0x05e9, B:206:0x05f8, B:202:0x05c8, B:207:0x0604, B:210:0x060e, B:212:0x0612, B:213:0x0615, B:214:0x0663, B:217:0x066d, B:219:0x0671, B:220:0x0674, B:222:0x06a3, B:225:0x06aa, B:227:0x06b4, B:232:0x06d9, B:234:0x06df, B:236:0x06ec, B:238:0x06f9, B:237:0x06f3, B:239:0x0715, B:240:0x071a, B:229:0x06ba, B:241:0x0726, B:244:0x0730, B:246:0x0734, B:247:0x0737, B:249:0x0764, B:252:0x076b, B:254:0x0775, B:259:0x079a, B:261:0x07a0, B:263:0x07bf, B:265:0x07c6, B:264:0x07c3, B:266:0x07e2, B:267:0x07e7, B:256:0x077b, B:268:0x07f3, B:271:0x07fd, B:273:0x0801, B:274:0x0804, B:276:0x0836, B:279:0x083d, B:281:0x089a, B:280:0x0887, B:17:0x0059, B:162:0x04a9, B:164:0x04b3, B:165:0x04b6, B:20:0x0063, B:23:0x006d, B:25:0x0071, B:26:0x0074, B:28:0x00a3, B:30:0x00a9, B:32:0x00b3, B:34:0x00b9, B:36:0x00bf, B:37:0x00d7, B:38:0x00e3, B:41:0x00ed, B:44:0x00f7, B:47:0x0101, B:49:0x0105, B:50:0x0108, B:52:0x0117, B:55:0x011e, B:82:0x01bc, B:56:0x0141, B:59:0x0155, B:61:0x015f, B:63:0x0169, B:65:0x0170, B:68:0x017a, B:70:0x0184, B:74:0x0191, B:75:0x0194, B:77:0x0199, B:79:0x01a3, B:81:0x01b9, B:83:0x01c1, B:86:0x01cb, B:88:0x01cf, B:89:0x01d2, B:91:0x0201, B:94:0x0208, B:96:0x0214, B:100:0x0238, B:102:0x023f, B:104:0x024c, B:106:0x0253, B:105:0x0250, B:107:0x026f, B:108:0x0274, B:98:0x021a, B:109:0x0280, B:112:0x028a, B:114:0x028e, B:115:0x0291, B:117:0x02a0, B:118:0x02ac, B:121:0x02b3, B:123:0x02bc, B:127:0x02cf, B:128:0x02ee, B:130:0x0313, B:129:0x02f2, B:131:0x0318, B:134:0x0322, B:136:0x0326, B:137:0x032d, B:138:0x03ad, B:141:0x03b7, B:143:0x03bb, B:144:0x03be, B:146:0x03fc, B:148:0x0409, B:147:0x0405, B:149:0x0426, B:152:0x0430, B:154:0x0434, B:155:0x0437, B:156:0x0495, B:159:0x049f, B:282:0x08b4, B:284:0x08bc, B:285:0x08c8, B:287:0x08cd, B:344:0x0988, B:346:0x098c, B:347:0x098f, B:290:0x08d7, B:293:0x08e1, B:296:0x08eb, B:299:0x08f5, B:302:0x08ff, B:305:0x0909, B:308:0x0913, B:311:0x091d, B:314:0x0927, B:317:0x0931, B:320:0x093b, B:323:0x0945, B:326:0x094f, B:329:0x0959, B:332:0x0963, B:335:0x096d, B:338:0x0976, B:341:0x097f, B:348:0x09c1, B:349:0x09cc), top: B:354:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0799  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x07e7 A[Catch: Exception -> 0x09d7, TryCatch #0 {Exception -> 0x09d7, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:166:0x04c5, B:169:0x04cf, B:171:0x04d3, B:172:0x04d6, B:174:0x0505, B:177:0x050c, B:178:0x0517, B:180:0x051d, B:182:0x052a, B:184:0x0531, B:183:0x052e, B:185:0x054d, B:186:0x0552, B:188:0x056b, B:191:0x0575, B:193:0x0579, B:194:0x057c, B:196:0x05b0, B:199:0x05b7, B:201:0x05c1, B:203:0x05e5, B:205:0x05e9, B:206:0x05f8, B:202:0x05c8, B:207:0x0604, B:210:0x060e, B:212:0x0612, B:213:0x0615, B:214:0x0663, B:217:0x066d, B:219:0x0671, B:220:0x0674, B:222:0x06a3, B:225:0x06aa, B:227:0x06b4, B:232:0x06d9, B:234:0x06df, B:236:0x06ec, B:238:0x06f9, B:237:0x06f3, B:239:0x0715, B:240:0x071a, B:229:0x06ba, B:241:0x0726, B:244:0x0730, B:246:0x0734, B:247:0x0737, B:249:0x0764, B:252:0x076b, B:254:0x0775, B:259:0x079a, B:261:0x07a0, B:263:0x07bf, B:265:0x07c6, B:264:0x07c3, B:266:0x07e2, B:267:0x07e7, B:256:0x077b, B:268:0x07f3, B:271:0x07fd, B:273:0x0801, B:274:0x0804, B:276:0x0836, B:279:0x083d, B:281:0x089a, B:280:0x0887, B:17:0x0059, B:162:0x04a9, B:164:0x04b3, B:165:0x04b6, B:20:0x0063, B:23:0x006d, B:25:0x0071, B:26:0x0074, B:28:0x00a3, B:30:0x00a9, B:32:0x00b3, B:34:0x00b9, B:36:0x00bf, B:37:0x00d7, B:38:0x00e3, B:41:0x00ed, B:44:0x00f7, B:47:0x0101, B:49:0x0105, B:50:0x0108, B:52:0x0117, B:55:0x011e, B:82:0x01bc, B:56:0x0141, B:59:0x0155, B:61:0x015f, B:63:0x0169, B:65:0x0170, B:68:0x017a, B:70:0x0184, B:74:0x0191, B:75:0x0194, B:77:0x0199, B:79:0x01a3, B:81:0x01b9, B:83:0x01c1, B:86:0x01cb, B:88:0x01cf, B:89:0x01d2, B:91:0x0201, B:94:0x0208, B:96:0x0214, B:100:0x0238, B:102:0x023f, B:104:0x024c, B:106:0x0253, B:105:0x0250, B:107:0x026f, B:108:0x0274, B:98:0x021a, B:109:0x0280, B:112:0x028a, B:114:0x028e, B:115:0x0291, B:117:0x02a0, B:118:0x02ac, B:121:0x02b3, B:123:0x02bc, B:127:0x02cf, B:128:0x02ee, B:130:0x0313, B:129:0x02f2, B:131:0x0318, B:134:0x0322, B:136:0x0326, B:137:0x032d, B:138:0x03ad, B:141:0x03b7, B:143:0x03bb, B:144:0x03be, B:146:0x03fc, B:148:0x0409, B:147:0x0405, B:149:0x0426, B:152:0x0430, B:154:0x0434, B:155:0x0437, B:156:0x0495, B:159:0x049f, B:282:0x08b4, B:284:0x08bc, B:285:0x08c8, B:287:0x08cd, B:344:0x0988, B:346:0x098c, B:347:0x098f, B:290:0x08d7, B:293:0x08e1, B:296:0x08eb, B:299:0x08f5, B:302:0x08ff, B:305:0x0909, B:308:0x0913, B:311:0x091d, B:314:0x0927, B:317:0x0931, B:320:0x093b, B:323:0x0945, B:326:0x094f, B:329:0x0959, B:332:0x0963, B:335:0x096d, B:338:0x0976, B:341:0x097f, B:348:0x09c1, B:349:0x09cc), top: B:354:0x0005 }] */
    /* JADX WARN: Type inference failed for: r15v100 */
    /* JADX WARN: Type inference failed for: r15v111, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r15v117, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r15v52 */
    /* JADX WARN: Type inference failed for: r15v89 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.jstyle2208a.api.JstyleBaseRes r15) {
        /*
            Method dump skipped, instructions count: 2678
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl.onResponse(com.coveiot.android.jstyle2208a.api.JstyleBaseRes):void");
    }

    public final void processNextCommand() {
        LinkedList<QueueObject> linkedList = this.o;
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        JStyleVBaseReq jStyleBaseRequest = getJStyleBaseRequest(this.o.get(0).getBaseRequest());
        if (jStyleBaseRequest != null) {
            BleBaseRequest bleBaseRequest = this.g;
            if (bleBaseRequest != null) {
                Intrinsics.checkNotNull(bleBaseRequest);
                if (!bleBaseRequest.isComplete()) {
                    return;
                }
            }
            sendRequestToBleService(jStyleBaseRequest);
            return;
        }
        sendCommandNotFoundError(this.o.get(0).getBaseRequest());
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.k == null) {
            this.k = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        Intrinsics.checkNotNull(connectionStatus);
        if (JStyle2208ABleManager.getInstance().isConnected()) {
            connectionStatus = ConnectionStatus.CONNECTED;
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.k;
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.postValue(connectionStatus);
        MutableLiveData<ConnectionStatus> mutableLiveData2 = this.k;
        Intrinsics.checkNotNull(mutableLiveData2);
        return mutableLiveData2;
    }

    public void registerEventBus() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new JStyle2208ABaseBleApiImpl$registerEventBus$1(this, null), 2, null);
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
        if (this.l == null) {
            this.l = new MutableLiveData<>();
        }
        MutableLiveData<LiveHealthData> mutableLiveData = this.l;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData;
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
        if (this.n == null) {
            this.n = new MutableLiveData<>();
        }
        MutableLiveData<LiveSportData> mutableLiveData = this.n;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveStepsData> registerLiveStepsData() {
        if (this.m == null) {
            this.m = new MutableLiveData<>();
        }
        MutableLiveData<LiveStepsData> mutableLiveData = this.m;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData;
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
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl$QueueObject> r0 = r6.o
            monitor-enter(r0)
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl$QueueObject> r1 = r6.o     // Catch: java.lang.Throwable -> L3d
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L3d
            r2 = 0
        Lf:
            if (r2 >= r1) goto L3b
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl$QueueObject> r3 = r6.o     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl$QueueObject r3 = (com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl.QueueObject) r3     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.request.BleBaseRequest r3 = r3.getBaseRequest()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r3 = r3.getRequId()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = r7.getRequId()     // Catch: java.lang.Throwable -> L3d
            r5 = 1
            boolean r3 = kotlin.text.m.equals(r3, r4, r5)     // Catch: java.lang.Throwable -> L3d
            if (r3 == 0) goto L38
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl$QueueObject> r7 = r6.o     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r7 = r7.remove(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl$QueueObject r7 = (com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl.QueueObject) r7     // Catch: java.lang.Throwable -> L3d
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
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl.removeFromQueue(com.coveiot.android.bleabstract.request.BleBaseRequest):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        a();
        JStyle2208ABleManager.getInstance().restartService();
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
            String string = this.f3124a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.command_not_found)");
            ((DataResultListener) responseListener).onDataError(new BleBaseError(string));
        } else if (responseListener instanceof SettingsResultListener) {
            String string2 = this.f3124a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.command_not_found)");
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(string2));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, this.f3124a.getString(R.string.command_not_found)));
        }
    }

    public final void sendCommandRequest() {
        LinkedList<QueueObject> linkedList = this.o;
        if (linkedList == null || linkedList.size() <= 0 || this.g != null) {
            return;
        }
        processNextCommand();
    }

    public final void sendErrorAndClearQueue(@NotNull JstyleError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        synchronized (this.o) {
            LinkedList<QueueObject> linkedList = this.o;
            if (linkedList != null && linkedList.size() > 0) {
                try {
                    Object clone = this.o.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl.QueueObject>");
                    Iterator it = ((LinkedList) clone).iterator();
                    while (it.hasNext()) {
                        BaseListener responseListener = ((QueueObject) it.next()).getBaseRequest().getResponseListener();
                        String message = error.getMessage();
                        Intrinsics.checkNotNull(message);
                        BleBaseError bleBaseError = new BleBaseError(message);
                        bleBaseError.setErrorMsg("Command request error");
                        if (error.getErrorType() == JstyleErrorType.COMMAND_TIME_OUT) {
                            bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_ERROR_TIMEOUT.value));
                            bleBaseError.setErrorMsg("Command timed out");
                        } else if (error.getErrorType() == JstyleErrorType.COMMAND_RESPONSE_ERROR) {
                            bleBaseError.setErrorCode(Integer.valueOf(CommandError.COMMAND_WRITE_FAILED.value));
                            bleBaseError.setErrorMsg("Command write failed");
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

    public final void sendRequestToBleService(@NotNull JStyleVBaseReq jStyleVBaseReq) {
        Intrinsics.checkNotNullParameter(jStyleVBaseReq, "jStyleVBaseReq");
        this.g = this.o.get(0).getBaseRequest();
        if (jStyleVBaseReq.isMultiPacket()) {
            this.f.postDelayed(this.s, this.h);
        } else {
            this.f.postDelayed(this.s, this.i);
        }
        JStyle2208ABleManager.getInstance().writeRequest(jStyleVBaseReq);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setBusyStatus(value);
    }

    public final void setCompleteAndProcessNext(@Nullable BleBaseRequest bleBaseRequest) {
        if (bleBaseRequest != null) {
            removeFromQueue(bleBaseRequest);
            String str = this.q;
            LogHelper.d(str, "setCompleteAndProcessNext--> removed " + bleBaseRequest);
        }
        if (this.g != null) {
            this.g = null;
        }
        processNextCommand();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.e = listener;
    }

    public final void setServiceConnection(@Nullable ServiceConnection serviceConnection) {
        this.r = serviceConnection;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (JStyle2208ABleManager.getInstance().isConnected()) {
            if (a(request)) {
                request.setRequId(UUID.randomUUID().toString());
                request.setResponseListener(listener);
                addToQueue(request);
                sendCommandRequest();
                return;
            }
            String str = this.q;
            LogHelper.d(str, "setUserSettings->Ignore {" + request + '}');
            return;
        }
        String string = this.f3124a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }

    public final void setWalkLastSyncTimeToCurrent() {
        KHJstyleEntityDeviceInfo deviceInfoBy = KHJstyleDeviceInfoRepository.getInstance(this.f3124a).getDeviceInfoBy(getMacAddress());
        if (deviceInfoBy == null) {
            deviceInfoBy = new KHJstyleEntityDeviceInfo();
            deviceInfoBy.setMacAddress(getMacAddress());
        }
        deviceInfoBy.setLastSyncWalkTimeStamp(System.currentTimeMillis());
        KHJstyleDeviceInfoRepository.getInstance(this.f3124a).insertDeviceInfo(deviceInfoBy);
    }

    public final void startBleService() {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(this.f3124a).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManager2208.getInstance(this.f3124a).saveConnectedDeviceMAcAddress("");
        PreferenceManager2208.getInstance(this.f3124a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        JStyle2208ABleManager.getInstance().stopService();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        LogHelper.d(this.q, "stopServiceAndRetainMacAddress");
        clearCommandQueue();
        PreferenceManagerAbstract.getInstance(this.f3124a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        JStyle2208ABleManager.getInstance().stopServiceAndRetainMacAddress();
    }

    public final void unbindService() {
        JStyle2208ABleManager.getInstance().unbindService();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
    }

    public static final void b(DataResultListener dataResultListener, BleBaseResponse mSleepResponse) {
        Intrinsics.checkNotNullParameter(mSleepResponse, "$mSleepResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mSleepResponse);
    }

    public static final void c(DataResultListener dataResultListener, JStyle2208ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(dataResultListener);
        String string = this$0.f3124a.getString(R.string.data_error);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …                        )");
        dataResultListener.onDataError(new BleBaseError(string));
    }

    public static final void b(DataResultListener dataResultListener, JStyle2208ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(dataResultListener);
        String string = this$0.f3124a.getString(R.string.data_error);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.data_error)");
        dataResultListener.onDataError(new BleBaseError(string));
    }

    public static final void b(JStyle2208ABaseBleApiImpl this$0, BleBaseRequest bleBaseRequest) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setCompleteAndProcessNext(bleBaseRequest);
    }

    public static final void b(BleBaseRequest bleBaseRequest) {
        SettingsResultListener settingsResultListener = (SettingsResultListener) bleBaseRequest.getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse(bleBaseRequest));
    }

    public static final void c(JStyle2208ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFailure(new JstyleError(JstyleErrorType.COMMAND_REQUEST_ERROR, this$0.f3124a.getString(R.string.command_req_error)));
    }

    public static final void a(final JStyle2208ABaseBleApiImpl this$0, final ScanDeviceRequest scanDeviceReq, final ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "$scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        try {
            if (!DeviceScanManager.getInstance(this$0.f3124a).isScanningInProgress()) {
                if (BleUtils.isEmpty(scanDeviceReq.getScanFilter())) {
                    DeviceScanManager.getInstance(this$0.f3124a).scanAllDevices(this$0.f3124a, scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl$scan_$2$1
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            JStyle2208ABaseBleApiImpl.this.scanResultReceieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = JStyle2208ABaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …                        )");
                            scanResultListener.onError(string);
                        }
                    });
                } else {
                    DeviceScanManager.getInstance(this$0.f3124a).scanDevicesWithFilter(this$0.f3124a, scanDeviceReq.getScanFilter(), scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl$scan_$2$2
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            JStyle2208ABaseBleApiImpl.this.scanResultReceieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = JStyle2208ABaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
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

    public static final void b(JStyle2208ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFailure(new JstyleError(JstyleErrorType.COMMAND_REQUEST_ERROR, this$0.f3124a.getString(R.string.command_req_error)));
    }

    public static final void d(DataResultListener dataResultListener, JStyle2208ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(dataResultListener);
        String string = this$0.f3124a.getString(R.string.data_error);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.data_error)");
        dataResultListener.onDataError(new BleBaseError(string));
    }

    public static final void d(DataResultListener dataResultListener, BleBaseResponse baseResponse) {
        Intrinsics.checkNotNullParameter(baseResponse, "$baseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(baseResponse);
    }

    public final void a() {
        if (DeviceScanManager.getInstance(this.f3124a).isScanningInProgress()) {
            DeviceScanManager.getInstance(this.f3124a).stopScan();
        }
        clearCommandQueue();
        Handler handler = this.c;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.b;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.f;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
    }

    public final boolean a(BleBaseRequest bleBaseRequest) {
        boolean z = true;
        try {
            BleBaseRequest bleBaseRequest2 = this.g;
            if (bleBaseRequest2 != null) {
                if (((bleBaseRequest2 instanceof CustomWatchFaceFileImageRequest) || (bleBaseRequest2 instanceof CustomWatchFaceBackgroundChangeRequest)) && bleBaseRequest != null && (bleBaseRequest instanceof SetMessageContentRequest) && ((SetMessageContentRequest) bleBaseRequest).appNotificationType != null && ((SetMessageContentRequest) bleBaseRequest).appNotificationType == NotificationType.CALL) {
                    z = false;
                    LogHelper.d(this.q, "Ignore incoming call triggered during watch face upgrade");
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

    public static final void a(DataResultListener dataResultListener, BleBaseResponse stressBleResponse) {
        Intrinsics.checkNotNullParameter(stressBleResponse, "$stressBleResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(stressBleResponse);
    }

    public static final void a(DataResultListener dataResultListener, JStyle2208ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(dataResultListener);
        String string = this$0.f3124a.getString(R.string.data_error);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.data_error)");
        dataResultListener.onDataError(new BleBaseError(string));
    }

    public static final void a(Ref.ObjectRef hrResponseList, BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(hrResponseList, "$hrResponseList");
        for (int i = 0; i < ((List) hrResponseList.element).size(); i++) {
            HeartRateResponse heartRateResponse = (HeartRateResponse) ((List) hrResponseList.element).get(i);
            if (i == ((List) hrResponseList.element).size() - 1) {
                heartRateResponse.setComplete(true);
            } else {
                heartRateResponse.setComplete(false);
            }
            BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
            bleBaseResponse.setResponseData(heartRateResponse);
            bleBaseResponse.setCompleted(heartRateResponse.isComplete());
            Intrinsics.checkNotNull(dataResultListener);
            dataResultListener.onDataResponse(bleBaseResponse);
        }
    }

    public static final void a(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(JStyle2208ABaseBleApiImpl this$0, BleBaseRequest bleBaseRequest) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setCompleteAndProcessNext(bleBaseRequest);
    }

    public static final void a(JStyle2208ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFailure(new JstyleError(JstyleErrorType.COMMAND_REQUEST_ERROR, this$0.f3124a.getString(R.string.command_req_error)));
    }
}
