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
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.preferences.PreferenceManager2301;
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
import com.coveiot.android.bleabstract.services.jstyle2301.JStyle2301ABleManager;
import com.coveiot.android.bleabstract.services.jstyle2301.JStyle2301ABleService;
import com.coveiot.android.jstyle2301a.JstyleResponseListener;
import com.coveiot.android.jstyle2301a.api.JStyleVBaseReq;
import com.coveiot.android.jstyle2301a.error.JstyleError;
import com.coveiot.android.jstyle2301a.error.JstyleErrorType;
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
import com.jstyle.blesdk2301.callback.BleConnectionListener;
import com.jstyle.blesdk2301.model.AutoMode;
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
public class JStyle2301ABaseBleApiImpl implements BleApi, BleConnectionListener, JstyleResponseListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3131a;
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
    @NotNull
    public final LinkedList<QueueObject> l;
    @Nullable
    public DeviceSupportedFeatures m;
    public final String n;
    @Nullable
    public ServiceConnection o;
    @NotNull
    public final Runnable p;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<JStyle2301ABaseBleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, JStyle2301ABaseBleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3132a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, JStyle2301ABaseBleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public JStyle2301ABaseBleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new JStyle2301ABaseBleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3132a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public static final class QueueObject {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3133a;

        public QueueObject(@NotNull BleBaseRequest baseRequest) {
            Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
            this.f3133a = baseRequest;
        }

        @NotNull
        public final BleBaseRequest getBaseRequest() {
            return this.f3133a;
        }

        public final void setBaseRequest(@NotNull BleBaseRequest bleBaseRequest) {
            Intrinsics.checkNotNullParameter(bleBaseRequest, "<set-?>");
            this.f3133a = bleBaseRequest;
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
            int[] iArr2 = new int[JstyleErrorType.values().length];
            try {
                iArr2[JstyleErrorType.COMMAND_TIME_OUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[JstyleErrorType.COMMAND_REQUEST_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[JstyleErrorType.COMMAND_RESPONSE_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public JStyle2301ABaseBleApiImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f3131a = context;
        this.b = new Handler();
        this.c = new Handler();
        this.f = new Handler();
        this.h = 60000;
        this.i = 30000;
        this.j = new Handler(Looper.getMainLooper());
        this.l = new LinkedList<>();
        this.n = JStyle2301ABaseBleApiImpl.class.getSimpleName();
        registerEventBus();
        b();
        this.p = new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.g5
            @Override // java.lang.Runnable
            public final void run() {
                JStyle2301ABaseBleApiImpl.d(JStyle2301ABaseBleApiImpl.this);
            }
        };
    }

    public static final void c(DataResultListener dataResultListener, BleBaseResponse baseResponse) {
        Intrinsics.checkNotNullParameter(baseResponse, "$baseResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(baseResponse);
    }

    public static final void d(JStyle2301ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.n, "Command TimeOut,Failed");
        BleBaseRequest bleBaseRequest = this$0.g;
        if (bleBaseRequest != null) {
            Intrinsics.checkNotNull(bleBaseRequest);
            JStyleVBaseReq a2 = this$0.a(bleBaseRequest);
            if (a2 != null) {
                this$0.onFailure(new JstyleError(JstyleErrorType.COMMAND_TIME_OUT, this$0.f3131a.getString(R.string.command_time_out)));
                String str = this$0.n;
                LogHelper.e(str, "End Time " + System.currentTimeMillis() + "-- currentCommand " + a2);
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

    public static final void f(DataResultListener dataResultListener, BleBaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "$response");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(response);
    }

    public static final void g(DataResultListener dataResultListener, BleBaseResponse hrResponse) {
        Intrinsics.checkNotNullParameter(hrResponse, "$hrResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(hrResponse);
    }

    public static final void h(DataResultListener dataResultListener, BleBaseResponse spo2BleResponse) {
        Intrinsics.checkNotNullParameter(spo2BleResponse, "$spo2BleResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(spo2BleResponse);
    }

    public static final void i(DataResultListener dataResultListener, BleBaseResponse spo2BleResponse) {
        Intrinsics.checkNotNullParameter(spo2BleResponse, "$spo2BleResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(spo2BleResponse);
    }

    public static final void j(DataResultListener dataResultListener, BleBaseResponse walkResponse) {
        Intrinsics.checkNotNullParameter(walkResponse, "$walkResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(walkResponse);
    }

    public static final void k(DataResultListener dataResultListener, BleBaseResponse mSleepResponse) {
        Intrinsics.checkNotNullParameter(mSleepResponse, "$mSleepResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mSleepResponse);
    }

    public static final void l(DataResultListener dataResultListener, BleBaseResponse stressBleResponse) {
        Intrinsics.checkNotNullParameter(stressBleResponse, "$stressBleResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(stressBleResponse);
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

    public final JStyleVBaseReq a(BleBaseRequest bleBaseRequest) {
        throw null;
    }

    public final void a(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        this.b.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(this.f3131a).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl$scan_$1
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(@NotNull CharSequence error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ScanResultListener scanResultListener2 = scanResultListener;
                        String string = JStyle2301ABaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                        scanResultListener2.onError(string);
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(@NotNull String messgae) {
                        Intrinsics.checkNotNullParameter(messgae, "messgae");
                        JStyle2301ABaseBleApiImpl.this.scanResultReceieved(new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.b.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.j5
            @Override // java.lang.Runnable
            public final void run() {
                JStyle2301ABaseBleApiImpl.a(JStyle2301ABaseBleApiImpl.this, scanDeviceRequest, scanResultListener);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void addToQueue(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        synchronized (this.l) {
            JStyleVBaseReq a2 = a(baseRequest);
            if (a2 != null) {
                if (a2.isPriority()) {
                    this.l.addFirst(new QueueObject(baseRequest));
                } else {
                    this.l.add(new QueueObject(baseRequest));
                }
            } else if (!(baseRequest instanceof ActivityModeSummaryRequest)) {
                sendCommandNotFoundError(baseRequest);
            }
        }
    }

    public final void b() {
        JStyle2301ABleManager.init(this.f3131a);
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
        Object systemService = this.f3131a.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (Intrinsics.areEqual(JStyle2301ABleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3131a.getPackageName(), runningServiceInfo.service.getPackageName())) {
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
        synchronized (this.l) {
            LinkedList<QueueObject> linkedList = this.l;
            if (linkedList != null && linkedList.size() > 0) {
                this.l.clear();
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
        JStyle2301ABleManager.getInstance().connectDevice(request.getMacAddress(), true, this);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        LogHelper.d(this.n, "on disconnect");
        this.e = listener;
        JStyle2301ABleManager.getInstance().disconnectDevice();
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
        ConnectionStatus connectionState = JStyle2301ABleManager.getInstance().getConnectionState();
        Intrinsics.checkNotNullExpressionValue(connectionState, "getInstance().getConnectionState()");
        return connectionState;
    }

    @NotNull
    public final Context getContext() {
        return this.f3131a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        String str;
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (JStyle2301ABleManager.getInstance().isConnected()) {
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
        String string = this.f3131a.getString(R.string.band_not_connected);
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
        this.m = deviceSupportedFeatures;
        deviceSupportedFeatures.setStepsSupported(true);
        DeviceSupportedFeatures deviceSupportedFeatures2 = this.m;
        if (deviceSupportedFeatures2 != null) {
            deviceSupportedFeatures2.setSleepSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures3 = this.m;
        if (deviceSupportedFeatures3 != null) {
            deviceSupportedFeatures3.setHeartRateSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures4 = this.m;
        if (deviceSupportedFeatures4 != null) {
            deviceSupportedFeatures4.setTemparatureHistorySupported(BleApiManager.getInstance(this.f3131a).getDeviceType() == DeviceType.jstyle2301a);
        }
        DeviceSupportedFeatures deviceSupportedFeatures5 = this.m;
        if (deviceSupportedFeatures5 != null) {
            deviceSupportedFeatures5.setPeriodicSpO2Supported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures6 = this.m;
        if (deviceSupportedFeatures6 != null) {
            deviceSupportedFeatures6.setStressHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures7 = this.m;
        if (deviceSupportedFeatures7 != null) {
            deviceSupportedFeatures7.setHRVHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures8 = this.m;
        if (deviceSupportedFeatures8 != null) {
            deviceSupportedFeatures8.setAutoHrSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures9 = this.m;
        if (deviceSupportedFeatures9 != null) {
            deviceSupportedFeatures9.setAutoTemperatureSettingsSupported(BleApiManager.getInstance(this.f3131a).getDeviceType() == DeviceType.jstyle2301a);
        }
        DeviceSupportedFeatures deviceSupportedFeatures10 = this.m;
        if (deviceSupportedFeatures10 != null) {
            deviceSupportedFeatures10.setAutoSPO2SettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures11 = this.m;
        if (deviceSupportedFeatures11 != null) {
            deviceSupportedFeatures11.setAutoStressSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures12 = this.m;
        if (deviceSupportedFeatures12 != null) {
            deviceSupportedFeatures12.setBandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures13 = this.m;
        if (deviceSupportedFeatures13 != null) {
            deviceSupportedFeatures13.setManualBpSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures14 = this.m;
        if (deviceSupportedFeatures14 != null) {
            deviceSupportedFeatures14.setManualSpo2SupportedOnBand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures15 = this.m;
        if (deviceSupportedFeatures15 != null) {
            deviceSupportedFeatures15.setManualStressMeasurementSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures16 = this.m;
        if (deviceSupportedFeatures16 != null) {
            deviceSupportedFeatures16.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures17 = this.m;
        if (deviceSupportedFeatures17 != null) {
            deviceSupportedFeatures17.setStepGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures18 = this.m;
        if (deviceSupportedFeatures18 != null) {
            deviceSupportedFeatures18.setCallNotificationSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures19 = this.m;
        if (deviceSupportedFeatures19 != null) {
            deviceSupportedFeatures19.setSmsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures20 = this.m;
        if (deviceSupportedFeatures20 != null) {
            deviceSupportedFeatures20.setMessageReadSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures21 = this.m;
        if (deviceSupportedFeatures21 != null) {
            deviceSupportedFeatures21.setSocialNotificationSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures22 = this.m;
        if (deviceSupportedFeatures22 != null) {
            deviceSupportedFeatures22.setHandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures23 = this.m;
        if (deviceSupportedFeatures23 != null) {
            deviceSupportedFeatures23.setPhoneFinderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures24 = this.m;
        if (deviceSupportedFeatures24 != null) {
            deviceSupportedFeatures24.setLiveStepsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures25 = this.m;
        if (deviceSupportedFeatures25 != null) {
            deviceSupportedFeatures25.setLiveHeartRateSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures26 = this.m;
        if (deviceSupportedFeatures26 != null) {
            deviceSupportedFeatures26.setLiveBPSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures27 = this.m;
        if (deviceSupportedFeatures27 != null) {
            deviceSupportedFeatures27.setHandPreferenceSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures28 = this.m;
        if (deviceSupportedFeatures28 != null) {
            deviceSupportedFeatures28.setTimeFormatSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures29 = this.m;
        if (deviceSupportedFeatures29 != null) {
            deviceSupportedFeatures29.setDistanceUnitSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures30 = this.m;
        if (deviceSupportedFeatures30 != null) {
            deviceSupportedFeatures30.setLiftWristToViewSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures31 = this.m;
        if (deviceSupportedFeatures31 != null) {
            deviceSupportedFeatures31.setTemperatureUnitSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures32 = this.m;
        if (deviceSupportedFeatures32 != null) {
            deviceSupportedFeatures32.setProbeFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures33 = this.m;
        if (deviceSupportedFeatures33 != null) {
            deviceSupportedFeatures33.setREMSupportedInSleep(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures34 = this.m;
        if (deviceSupportedFeatures34 != null) {
            deviceSupportedFeatures34.setMultipleAlarmsSupportedAtATime(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures35 = this.m;
        if (deviceSupportedFeatures35 != null) {
            deviceSupportedFeatures35.setOnceAlarmSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures36 = this.m;
        if (deviceSupportedFeatures36 != null) {
            deviceSupportedFeatures36.setSportsModeHistorySupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures37 = this.m;
        if (deviceSupportedFeatures37 != null) {
            deviceSupportedFeatures37.setSampleDataSupportedInSportMode(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures38 = this.m;
        if (deviceSupportedFeatures38 != null) {
            deviceSupportedFeatures38.setSyncBandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures39 = this.m;
        if (deviceSupportedFeatures39 != null) {
            deviceSupportedFeatures39.setSportModeSupportedFromApp(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures40 = this.m;
        if (deviceSupportedFeatures40 != null) {
            deviceSupportedFeatures40.setDeviceSettingsSupportedInOneCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures41 = this.m;
        if (deviceSupportedFeatures41 != null) {
            deviceSupportedFeatures41.setBandSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures42 = this.m;
        if (deviceSupportedFeatures42 != null) {
            deviceSupportedFeatures42.setScheduledDndSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures43 = this.m;
        if (deviceSupportedFeatures43 != null) {
            deviceSupportedFeatures43.setMusicMetaDataChangeFromAppSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures44 = this.m;
        if (deviceSupportedFeatures44 != null) {
            deviceSupportedFeatures44.setMusicPlaybackStateChangeFromAppSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures45 = this.m;
        if (deviceSupportedFeatures45 != null) {
            deviceSupportedFeatures45.setMusicVolumeChangeFromAppSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures46 = this.m;
        if (deviceSupportedFeatures46 != null) {
            deviceSupportedFeatures46.setMaxAlarmSupportedOnBand(0);
        }
        DeviceSupportedFeatures deviceSupportedFeatures47 = this.m;
        if (deviceSupportedFeatures47 != null) {
            deviceSupportedFeatures47.setVibrationAlarmSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures48 = this.m;
        if (deviceSupportedFeatures48 != null) {
            deviceSupportedFeatures48.setWatchFaceSupport(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures49 = this.m;
        if (deviceSupportedFeatures49 != null) {
            deviceSupportedFeatures49.setNotificationSupport(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures50 = this.m;
        if (deviceSupportedFeatures50 != null) {
            deviceSupportedFeatures50.setSedentaryReminderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures51 = this.m;
        if (deviceSupportedFeatures51 != null) {
            deviceSupportedFeatures51.setRepeatDaysSupportedInSedentary(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures52 = this.m;
        if (deviceSupportedFeatures52 != null) {
            deviceSupportedFeatures52.setDndSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures53 = this.m;
        if (deviceSupportedFeatures53 != null) {
            deviceSupportedFeatures53.setShortcutMenuShowHideCommandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures54 = this.m;
        if (deviceSupportedFeatures54 != null) {
            deviceSupportedFeatures54.setFemaleWellnessSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures55 = this.m;
        if (deviceSupportedFeatures55 != null) {
            deviceSupportedFeatures55.setContactSyncSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures56 = this.m;
        if (deviceSupportedFeatures56 != null) {
            deviceSupportedFeatures56.setDrinkingReminderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures57 = this.m;
        if (deviceSupportedFeatures57 != null) {
            deviceSupportedFeatures57.setFindMyBandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures58 = this.m;
        if (deviceSupportedFeatures58 != null) {
            deviceSupportedFeatures58.setGpsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures59 = this.m;
        if (deviceSupportedFeatures59 != null) {
            deviceSupportedFeatures59.setWeatherEnableCommandSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures60 = this.m;
        if (deviceSupportedFeatures60 != null) {
            deviceSupportedFeatures60.setWeatherSupportedInBand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures61 = this.m;
        if (deviceSupportedFeatures61 != null) {
            deviceSupportedFeatures61.setGoalSettingSupportedInSingleCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures62 = this.m;
        if (deviceSupportedFeatures62 != null) {
            deviceSupportedFeatures62.setBTCallingSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures63 = this.m;
        if (deviceSupportedFeatures63 != null) {
            deviceSupportedFeatures63.setMaxContactsInOneRequest(10);
        }
        DeviceSupportedFeatures deviceSupportedFeatures64 = this.m;
        if (deviceSupportedFeatures64 != null) {
            deviceSupportedFeatures64.setGenericActivityDataSampleSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures65 = this.m;
        if (deviceSupportedFeatures65 != null) {
            deviceSupportedFeatures65.setMusicDataSupportInSingleCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures66 = this.m;
        if (deviceSupportedFeatures66 != null) {
            deviceSupportedFeatures66.setSleepTargetSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures67 = this.m;
        if (deviceSupportedFeatures67 != null) {
            deviceSupportedFeatures67.setCalorieGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures68 = this.m;
        if (deviceSupportedFeatures68 != null) {
            deviceSupportedFeatures68.setDistanceGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures69 = this.m;
        if (deviceSupportedFeatures69 != null) {
            deviceSupportedFeatures69.setExerciseMinutesGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures70 = this.m;
        if (deviceSupportedFeatures70 != null) {
            deviceSupportedFeatures70.setTitleSupportedInNotification(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures71 = this.m;
        if (deviceSupportedFeatures71 != null) {
            deviceSupportedFeatures71.setBandVolumeControlSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures72 = this.m;
        Intrinsics.checkNotNull(deviceSupportedFeatures72);
        return deviceSupportedFeatures72;
    }

    @Nullable
    public final BleBaseRequest getFromQueue(@Nullable JStyleVBaseReq jStyleVBaseReq) {
        int size = this.l.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                return null;
            }
            if (kotlin.text.m.equals(this.l.get(i).getBaseRequest().getRequId(), jStyleVBaseReq != null ? jStyleVBaseReq.getReqId() : null, true)) {
                return this.l.get(i).getBaseRequest();
            }
            i++;
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        String connectedDeviceMacAddress = PreferenceManagerAbstract.getInstance(this.f3131a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        return connectedDeviceMacAddress;
    }

    @NotNull
    public final LinkedList<QueueObject> getQueue() {
        return this.l;
    }

    @Nullable
    public final ServiceConnection getServiceConnection() {
        return this.o;
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
            int[] r1 = com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl.WhenMappings.$EnumSwitchMapping$0
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
            com.coveiot.android.jstyle2301a.error.JstyleError r0 = new com.coveiot.android.jstyle2301a.error.JstyleError
            com.coveiot.android.jstyle2301a.error.JstyleErrorType r1 = com.coveiot.android.jstyle2301a.error.JstyleErrorType.DEVICE_NOT_CONNECTED
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
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl.onConnectionStateChangedHandler(com.coveiot.sdk.ble.CloveBleState):void");
    }

    public void onFailure(@NotNull JstyleError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        JstyleErrorType errorType = error.getErrorType();
        int i = errorType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[errorType.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            sendErrorAndClearQueue(error);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0272 A[Catch: Exception -> 0x0902, TryCatch #0 {Exception -> 0x0902, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:165:0x04c3, B:168:0x04cd, B:170:0x04d1, B:171:0x04d4, B:173:0x0503, B:176:0x050a, B:177:0x0515, B:179:0x051b, B:181:0x0528, B:183:0x052f, B:182:0x052c, B:184:0x0544, B:185:0x0549, B:187:0x055f, B:190:0x0569, B:192:0x056d, B:193:0x0570, B:195:0x05a4, B:198:0x05ab, B:200:0x05b5, B:202:0x05d9, B:204:0x05dd, B:205:0x05ec, B:201:0x05bc, B:206:0x05f8, B:209:0x0602, B:211:0x0606, B:212:0x0609, B:213:0x0657, B:216:0x0661, B:218:0x0665, B:219:0x0668, B:221:0x0697, B:224:0x069e, B:226:0x06a8, B:231:0x06cd, B:233:0x06d3, B:235:0x06e0, B:237:0x06ed, B:236:0x06e7, B:238:0x0709, B:239:0x070e, B:228:0x06ae, B:240:0x071a, B:243:0x0724, B:245:0x0728, B:246:0x072b, B:248:0x075a, B:251:0x0761, B:253:0x076b, B:258:0x0790, B:260:0x0796, B:262:0x07b5, B:264:0x07bc, B:263:0x07b9, B:265:0x07d8, B:266:0x07dd, B:255:0x0771, B:16:0x0057, B:161:0x04a7, B:163:0x04b1, B:164:0x04b4, B:19:0x0061, B:22:0x006b, B:24:0x006f, B:25:0x0072, B:27:0x00a1, B:29:0x00a7, B:31:0x00b1, B:33:0x00b7, B:35:0x00bd, B:36:0x00d5, B:37:0x00e1, B:40:0x00eb, B:43:0x00f5, B:46:0x00ff, B:48:0x0103, B:49:0x0106, B:51:0x0115, B:54:0x011c, B:81:0x01ba, B:55:0x013f, B:58:0x0153, B:60:0x015d, B:62:0x0167, B:64:0x016e, B:67:0x0178, B:69:0x0182, B:73:0x018f, B:74:0x0192, B:76:0x0197, B:78:0x01a1, B:80:0x01b7, B:82:0x01bf, B:85:0x01c9, B:87:0x01cd, B:88:0x01d0, B:90:0x01ff, B:93:0x0206, B:95:0x0212, B:99:0x0236, B:101:0x023d, B:103:0x024a, B:105:0x0251, B:104:0x024e, B:106:0x026d, B:107:0x0272, B:97:0x0218, B:108:0x027e, B:111:0x0288, B:113:0x028c, B:114:0x028f, B:116:0x029e, B:117:0x02aa, B:120:0x02b1, B:122:0x02ba, B:126:0x02cd, B:127:0x02ec, B:129:0x0311, B:128:0x02f0, B:130:0x0316, B:133:0x0320, B:135:0x0324, B:136:0x032b, B:137:0x03ab, B:140:0x03b5, B:142:0x03b9, B:143:0x03bc, B:145:0x03fa, B:147:0x0407, B:146:0x0403, B:148:0x0424, B:151:0x042e, B:153:0x0432, B:154:0x0435, B:155:0x0493, B:158:0x049d, B:267:0x07e9, B:269:0x07f1, B:270:0x07fd, B:272:0x0802, B:326:0x08b3, B:328:0x08b7, B:329:0x08ba, B:275:0x080c, B:278:0x0816, B:281:0x0820, B:284:0x082a, B:287:0x0834, B:290:0x083e, B:293:0x0848, B:296:0x0852, B:299:0x085c, B:302:0x0866, B:305:0x0870, B:308:0x087a, B:311:0x0884, B:314:0x088e, B:317:0x0898, B:320:0x08a1, B:323:0x08aa, B:330:0x08ec, B:331:0x08f7), top: B:336:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:204:0x05dd A[Catch: Exception -> 0x0902, TryCatch #0 {Exception -> 0x0902, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:165:0x04c3, B:168:0x04cd, B:170:0x04d1, B:171:0x04d4, B:173:0x0503, B:176:0x050a, B:177:0x0515, B:179:0x051b, B:181:0x0528, B:183:0x052f, B:182:0x052c, B:184:0x0544, B:185:0x0549, B:187:0x055f, B:190:0x0569, B:192:0x056d, B:193:0x0570, B:195:0x05a4, B:198:0x05ab, B:200:0x05b5, B:202:0x05d9, B:204:0x05dd, B:205:0x05ec, B:201:0x05bc, B:206:0x05f8, B:209:0x0602, B:211:0x0606, B:212:0x0609, B:213:0x0657, B:216:0x0661, B:218:0x0665, B:219:0x0668, B:221:0x0697, B:224:0x069e, B:226:0x06a8, B:231:0x06cd, B:233:0x06d3, B:235:0x06e0, B:237:0x06ed, B:236:0x06e7, B:238:0x0709, B:239:0x070e, B:228:0x06ae, B:240:0x071a, B:243:0x0724, B:245:0x0728, B:246:0x072b, B:248:0x075a, B:251:0x0761, B:253:0x076b, B:258:0x0790, B:260:0x0796, B:262:0x07b5, B:264:0x07bc, B:263:0x07b9, B:265:0x07d8, B:266:0x07dd, B:255:0x0771, B:16:0x0057, B:161:0x04a7, B:163:0x04b1, B:164:0x04b4, B:19:0x0061, B:22:0x006b, B:24:0x006f, B:25:0x0072, B:27:0x00a1, B:29:0x00a7, B:31:0x00b1, B:33:0x00b7, B:35:0x00bd, B:36:0x00d5, B:37:0x00e1, B:40:0x00eb, B:43:0x00f5, B:46:0x00ff, B:48:0x0103, B:49:0x0106, B:51:0x0115, B:54:0x011c, B:81:0x01ba, B:55:0x013f, B:58:0x0153, B:60:0x015d, B:62:0x0167, B:64:0x016e, B:67:0x0178, B:69:0x0182, B:73:0x018f, B:74:0x0192, B:76:0x0197, B:78:0x01a1, B:80:0x01b7, B:82:0x01bf, B:85:0x01c9, B:87:0x01cd, B:88:0x01d0, B:90:0x01ff, B:93:0x0206, B:95:0x0212, B:99:0x0236, B:101:0x023d, B:103:0x024a, B:105:0x0251, B:104:0x024e, B:106:0x026d, B:107:0x0272, B:97:0x0218, B:108:0x027e, B:111:0x0288, B:113:0x028c, B:114:0x028f, B:116:0x029e, B:117:0x02aa, B:120:0x02b1, B:122:0x02ba, B:126:0x02cd, B:127:0x02ec, B:129:0x0311, B:128:0x02f0, B:130:0x0316, B:133:0x0320, B:135:0x0324, B:136:0x032b, B:137:0x03ab, B:140:0x03b5, B:142:0x03b9, B:143:0x03bc, B:145:0x03fa, B:147:0x0407, B:146:0x0403, B:148:0x0424, B:151:0x042e, B:153:0x0432, B:154:0x0435, B:155:0x0493, B:158:0x049d, B:267:0x07e9, B:269:0x07f1, B:270:0x07fd, B:272:0x0802, B:326:0x08b3, B:328:0x08b7, B:329:0x08ba, B:275:0x080c, B:278:0x0816, B:281:0x0820, B:284:0x082a, B:287:0x0834, B:290:0x083e, B:293:0x0848, B:296:0x0852, B:299:0x085c, B:302:0x0866, B:305:0x0870, B:308:0x087a, B:311:0x0884, B:314:0x088e, B:317:0x0898, B:320:0x08a1, B:323:0x08aa, B:330:0x08ec, B:331:0x08f7), top: B:336:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x05ec A[Catch: Exception -> 0x0902, TryCatch #0 {Exception -> 0x0902, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:165:0x04c3, B:168:0x04cd, B:170:0x04d1, B:171:0x04d4, B:173:0x0503, B:176:0x050a, B:177:0x0515, B:179:0x051b, B:181:0x0528, B:183:0x052f, B:182:0x052c, B:184:0x0544, B:185:0x0549, B:187:0x055f, B:190:0x0569, B:192:0x056d, B:193:0x0570, B:195:0x05a4, B:198:0x05ab, B:200:0x05b5, B:202:0x05d9, B:204:0x05dd, B:205:0x05ec, B:201:0x05bc, B:206:0x05f8, B:209:0x0602, B:211:0x0606, B:212:0x0609, B:213:0x0657, B:216:0x0661, B:218:0x0665, B:219:0x0668, B:221:0x0697, B:224:0x069e, B:226:0x06a8, B:231:0x06cd, B:233:0x06d3, B:235:0x06e0, B:237:0x06ed, B:236:0x06e7, B:238:0x0709, B:239:0x070e, B:228:0x06ae, B:240:0x071a, B:243:0x0724, B:245:0x0728, B:246:0x072b, B:248:0x075a, B:251:0x0761, B:253:0x076b, B:258:0x0790, B:260:0x0796, B:262:0x07b5, B:264:0x07bc, B:263:0x07b9, B:265:0x07d8, B:266:0x07dd, B:255:0x0771, B:16:0x0057, B:161:0x04a7, B:163:0x04b1, B:164:0x04b4, B:19:0x0061, B:22:0x006b, B:24:0x006f, B:25:0x0072, B:27:0x00a1, B:29:0x00a7, B:31:0x00b1, B:33:0x00b7, B:35:0x00bd, B:36:0x00d5, B:37:0x00e1, B:40:0x00eb, B:43:0x00f5, B:46:0x00ff, B:48:0x0103, B:49:0x0106, B:51:0x0115, B:54:0x011c, B:81:0x01ba, B:55:0x013f, B:58:0x0153, B:60:0x015d, B:62:0x0167, B:64:0x016e, B:67:0x0178, B:69:0x0182, B:73:0x018f, B:74:0x0192, B:76:0x0197, B:78:0x01a1, B:80:0x01b7, B:82:0x01bf, B:85:0x01c9, B:87:0x01cd, B:88:0x01d0, B:90:0x01ff, B:93:0x0206, B:95:0x0212, B:99:0x0236, B:101:0x023d, B:103:0x024a, B:105:0x0251, B:104:0x024e, B:106:0x026d, B:107:0x0272, B:97:0x0218, B:108:0x027e, B:111:0x0288, B:113:0x028c, B:114:0x028f, B:116:0x029e, B:117:0x02aa, B:120:0x02b1, B:122:0x02ba, B:126:0x02cd, B:127:0x02ec, B:129:0x0311, B:128:0x02f0, B:130:0x0316, B:133:0x0320, B:135:0x0324, B:136:0x032b, B:137:0x03ab, B:140:0x03b5, B:142:0x03b9, B:143:0x03bc, B:145:0x03fa, B:147:0x0407, B:146:0x0403, B:148:0x0424, B:151:0x042e, B:153:0x0432, B:154:0x0435, B:155:0x0493, B:158:0x049d, B:267:0x07e9, B:269:0x07f1, B:270:0x07fd, B:272:0x0802, B:326:0x08b3, B:328:0x08b7, B:329:0x08ba, B:275:0x080c, B:278:0x0816, B:281:0x0820, B:284:0x082a, B:287:0x0834, B:290:0x083e, B:293:0x0848, B:296:0x0852, B:299:0x085c, B:302:0x0866, B:305:0x0870, B:308:0x087a, B:311:0x0884, B:314:0x088e, B:317:0x0898, B:320:0x08a1, B:323:0x08aa, B:330:0x08ec, B:331:0x08f7), top: B:336:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x06cc  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x070e A[Catch: Exception -> 0x0902, TryCatch #0 {Exception -> 0x0902, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:165:0x04c3, B:168:0x04cd, B:170:0x04d1, B:171:0x04d4, B:173:0x0503, B:176:0x050a, B:177:0x0515, B:179:0x051b, B:181:0x0528, B:183:0x052f, B:182:0x052c, B:184:0x0544, B:185:0x0549, B:187:0x055f, B:190:0x0569, B:192:0x056d, B:193:0x0570, B:195:0x05a4, B:198:0x05ab, B:200:0x05b5, B:202:0x05d9, B:204:0x05dd, B:205:0x05ec, B:201:0x05bc, B:206:0x05f8, B:209:0x0602, B:211:0x0606, B:212:0x0609, B:213:0x0657, B:216:0x0661, B:218:0x0665, B:219:0x0668, B:221:0x0697, B:224:0x069e, B:226:0x06a8, B:231:0x06cd, B:233:0x06d3, B:235:0x06e0, B:237:0x06ed, B:236:0x06e7, B:238:0x0709, B:239:0x070e, B:228:0x06ae, B:240:0x071a, B:243:0x0724, B:245:0x0728, B:246:0x072b, B:248:0x075a, B:251:0x0761, B:253:0x076b, B:258:0x0790, B:260:0x0796, B:262:0x07b5, B:264:0x07bc, B:263:0x07b9, B:265:0x07d8, B:266:0x07dd, B:255:0x0771, B:16:0x0057, B:161:0x04a7, B:163:0x04b1, B:164:0x04b4, B:19:0x0061, B:22:0x006b, B:24:0x006f, B:25:0x0072, B:27:0x00a1, B:29:0x00a7, B:31:0x00b1, B:33:0x00b7, B:35:0x00bd, B:36:0x00d5, B:37:0x00e1, B:40:0x00eb, B:43:0x00f5, B:46:0x00ff, B:48:0x0103, B:49:0x0106, B:51:0x0115, B:54:0x011c, B:81:0x01ba, B:55:0x013f, B:58:0x0153, B:60:0x015d, B:62:0x0167, B:64:0x016e, B:67:0x0178, B:69:0x0182, B:73:0x018f, B:74:0x0192, B:76:0x0197, B:78:0x01a1, B:80:0x01b7, B:82:0x01bf, B:85:0x01c9, B:87:0x01cd, B:88:0x01d0, B:90:0x01ff, B:93:0x0206, B:95:0x0212, B:99:0x0236, B:101:0x023d, B:103:0x024a, B:105:0x0251, B:104:0x024e, B:106:0x026d, B:107:0x0272, B:97:0x0218, B:108:0x027e, B:111:0x0288, B:113:0x028c, B:114:0x028f, B:116:0x029e, B:117:0x02aa, B:120:0x02b1, B:122:0x02ba, B:126:0x02cd, B:127:0x02ec, B:129:0x0311, B:128:0x02f0, B:130:0x0316, B:133:0x0320, B:135:0x0324, B:136:0x032b, B:137:0x03ab, B:140:0x03b5, B:142:0x03b9, B:143:0x03bc, B:145:0x03fa, B:147:0x0407, B:146:0x0403, B:148:0x0424, B:151:0x042e, B:153:0x0432, B:154:0x0435, B:155:0x0493, B:158:0x049d, B:267:0x07e9, B:269:0x07f1, B:270:0x07fd, B:272:0x0802, B:326:0x08b3, B:328:0x08b7, B:329:0x08ba, B:275:0x080c, B:278:0x0816, B:281:0x0820, B:284:0x082a, B:287:0x0834, B:290:0x083e, B:293:0x0848, B:296:0x0852, B:299:0x085c, B:302:0x0866, B:305:0x0870, B:308:0x087a, B:311:0x0884, B:314:0x088e, B:317:0x0898, B:320:0x08a1, B:323:0x08aa, B:330:0x08ec, B:331:0x08f7), top: B:336:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x078f  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x07dd A[Catch: Exception -> 0x0902, TryCatch #0 {Exception -> 0x0902, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:165:0x04c3, B:168:0x04cd, B:170:0x04d1, B:171:0x04d4, B:173:0x0503, B:176:0x050a, B:177:0x0515, B:179:0x051b, B:181:0x0528, B:183:0x052f, B:182:0x052c, B:184:0x0544, B:185:0x0549, B:187:0x055f, B:190:0x0569, B:192:0x056d, B:193:0x0570, B:195:0x05a4, B:198:0x05ab, B:200:0x05b5, B:202:0x05d9, B:204:0x05dd, B:205:0x05ec, B:201:0x05bc, B:206:0x05f8, B:209:0x0602, B:211:0x0606, B:212:0x0609, B:213:0x0657, B:216:0x0661, B:218:0x0665, B:219:0x0668, B:221:0x0697, B:224:0x069e, B:226:0x06a8, B:231:0x06cd, B:233:0x06d3, B:235:0x06e0, B:237:0x06ed, B:236:0x06e7, B:238:0x0709, B:239:0x070e, B:228:0x06ae, B:240:0x071a, B:243:0x0724, B:245:0x0728, B:246:0x072b, B:248:0x075a, B:251:0x0761, B:253:0x076b, B:258:0x0790, B:260:0x0796, B:262:0x07b5, B:264:0x07bc, B:263:0x07b9, B:265:0x07d8, B:266:0x07dd, B:255:0x0771, B:16:0x0057, B:161:0x04a7, B:163:0x04b1, B:164:0x04b4, B:19:0x0061, B:22:0x006b, B:24:0x006f, B:25:0x0072, B:27:0x00a1, B:29:0x00a7, B:31:0x00b1, B:33:0x00b7, B:35:0x00bd, B:36:0x00d5, B:37:0x00e1, B:40:0x00eb, B:43:0x00f5, B:46:0x00ff, B:48:0x0103, B:49:0x0106, B:51:0x0115, B:54:0x011c, B:81:0x01ba, B:55:0x013f, B:58:0x0153, B:60:0x015d, B:62:0x0167, B:64:0x016e, B:67:0x0178, B:69:0x0182, B:73:0x018f, B:74:0x0192, B:76:0x0197, B:78:0x01a1, B:80:0x01b7, B:82:0x01bf, B:85:0x01c9, B:87:0x01cd, B:88:0x01d0, B:90:0x01ff, B:93:0x0206, B:95:0x0212, B:99:0x0236, B:101:0x023d, B:103:0x024a, B:105:0x0251, B:104:0x024e, B:106:0x026d, B:107:0x0272, B:97:0x0218, B:108:0x027e, B:111:0x0288, B:113:0x028c, B:114:0x028f, B:116:0x029e, B:117:0x02aa, B:120:0x02b1, B:122:0x02ba, B:126:0x02cd, B:127:0x02ec, B:129:0x0311, B:128:0x02f0, B:130:0x0316, B:133:0x0320, B:135:0x0324, B:136:0x032b, B:137:0x03ab, B:140:0x03b5, B:142:0x03b9, B:143:0x03bc, B:145:0x03fa, B:147:0x0407, B:146:0x0403, B:148:0x0424, B:151:0x042e, B:153:0x0432, B:154:0x0435, B:155:0x0493, B:158:0x049d, B:267:0x07e9, B:269:0x07f1, B:270:0x07fd, B:272:0x0802, B:326:0x08b3, B:328:0x08b7, B:329:0x08ba, B:275:0x080c, B:278:0x0816, B:281:0x0820, B:284:0x082a, B:287:0x0834, B:290:0x083e, B:293:0x0848, B:296:0x0852, B:299:0x085c, B:302:0x0866, B:305:0x0870, B:308:0x087a, B:311:0x0884, B:314:0x088e, B:317:0x0898, B:320:0x08a1, B:323:0x08aa, B:330:0x08ec, B:331:0x08f7), top: B:336:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0236 A[Catch: Exception -> 0x0902, TryCatch #0 {Exception -> 0x0902, blocks: (B:3:0x0005, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:11:0x0028, B:165:0x04c3, B:168:0x04cd, B:170:0x04d1, B:171:0x04d4, B:173:0x0503, B:176:0x050a, B:177:0x0515, B:179:0x051b, B:181:0x0528, B:183:0x052f, B:182:0x052c, B:184:0x0544, B:185:0x0549, B:187:0x055f, B:190:0x0569, B:192:0x056d, B:193:0x0570, B:195:0x05a4, B:198:0x05ab, B:200:0x05b5, B:202:0x05d9, B:204:0x05dd, B:205:0x05ec, B:201:0x05bc, B:206:0x05f8, B:209:0x0602, B:211:0x0606, B:212:0x0609, B:213:0x0657, B:216:0x0661, B:218:0x0665, B:219:0x0668, B:221:0x0697, B:224:0x069e, B:226:0x06a8, B:231:0x06cd, B:233:0x06d3, B:235:0x06e0, B:237:0x06ed, B:236:0x06e7, B:238:0x0709, B:239:0x070e, B:228:0x06ae, B:240:0x071a, B:243:0x0724, B:245:0x0728, B:246:0x072b, B:248:0x075a, B:251:0x0761, B:253:0x076b, B:258:0x0790, B:260:0x0796, B:262:0x07b5, B:264:0x07bc, B:263:0x07b9, B:265:0x07d8, B:266:0x07dd, B:255:0x0771, B:16:0x0057, B:161:0x04a7, B:163:0x04b1, B:164:0x04b4, B:19:0x0061, B:22:0x006b, B:24:0x006f, B:25:0x0072, B:27:0x00a1, B:29:0x00a7, B:31:0x00b1, B:33:0x00b7, B:35:0x00bd, B:36:0x00d5, B:37:0x00e1, B:40:0x00eb, B:43:0x00f5, B:46:0x00ff, B:48:0x0103, B:49:0x0106, B:51:0x0115, B:54:0x011c, B:81:0x01ba, B:55:0x013f, B:58:0x0153, B:60:0x015d, B:62:0x0167, B:64:0x016e, B:67:0x0178, B:69:0x0182, B:73:0x018f, B:74:0x0192, B:76:0x0197, B:78:0x01a1, B:80:0x01b7, B:82:0x01bf, B:85:0x01c9, B:87:0x01cd, B:88:0x01d0, B:90:0x01ff, B:93:0x0206, B:95:0x0212, B:99:0x0236, B:101:0x023d, B:103:0x024a, B:105:0x0251, B:104:0x024e, B:106:0x026d, B:107:0x0272, B:97:0x0218, B:108:0x027e, B:111:0x0288, B:113:0x028c, B:114:0x028f, B:116:0x029e, B:117:0x02aa, B:120:0x02b1, B:122:0x02ba, B:126:0x02cd, B:127:0x02ec, B:129:0x0311, B:128:0x02f0, B:130:0x0316, B:133:0x0320, B:135:0x0324, B:136:0x032b, B:137:0x03ab, B:140:0x03b5, B:142:0x03b9, B:143:0x03bc, B:145:0x03fa, B:147:0x0407, B:146:0x0403, B:148:0x0424, B:151:0x042e, B:153:0x0432, B:154:0x0435, B:155:0x0493, B:158:0x049d, B:267:0x07e9, B:269:0x07f1, B:270:0x07fd, B:272:0x0802, B:326:0x08b3, B:328:0x08b7, B:329:0x08ba, B:275:0x080c, B:278:0x0816, B:281:0x0820, B:284:0x082a, B:287:0x0834, B:290:0x083e, B:293:0x0848, B:296:0x0852, B:299:0x085c, B:302:0x0866, B:305:0x0870, B:308:0x087a, B:311:0x0884, B:314:0x088e, B:317:0x0898, B:320:0x08a1, B:323:0x08aa, B:330:0x08ec, B:331:0x08f7), top: B:336:0x0005 }] */
    /* JADX WARN: Type inference failed for: r15v101, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r15v107, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r15v52 */
    /* JADX WARN: Type inference failed for: r15v79 */
    /* JADX WARN: Type inference failed for: r15v90 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.jstyle2301a.api.JstyleBaseRes r15) {
        /*
            Method dump skipped, instructions count: 2460
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl.onResponse(com.coveiot.android.jstyle2301a.api.JstyleBaseRes):void");
    }

    public final void processNextCommand() {
        LinkedList<QueueObject> linkedList = this.l;
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        JStyleVBaseReq a2 = a(this.l.get(0).getBaseRequest());
        if (a2 != null) {
            BleBaseRequest bleBaseRequest = this.g;
            if (bleBaseRequest != null) {
                Intrinsics.checkNotNull(bleBaseRequest);
                if (!bleBaseRequest.isComplete()) {
                    return;
                }
            }
            sendRequestToBleService(a2);
            return;
        }
        sendCommandNotFoundError(this.l.get(0).getBaseRequest());
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.k == null) {
            this.k = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        Intrinsics.checkNotNull(connectionStatus);
        if (JStyle2301ABleManager.getInstance().isConnected()) {
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
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new JStyle2301ABaseBleApiImpl$registerEventBus$1(this, null), 2, null);
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
        r6.l.remove(r2).getBaseRequest();
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
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl$QueueObject> r0 = r6.l
            monitor-enter(r0)
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl$QueueObject> r1 = r6.l     // Catch: java.lang.Throwable -> L3d
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L3d
            r2 = 0
        Lf:
            if (r2 >= r1) goto L3b
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl$QueueObject> r3 = r6.l     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl$QueueObject r3 = (com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl.QueueObject) r3     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.request.BleBaseRequest r3 = r3.getBaseRequest()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r3 = r3.getRequId()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = r7.getRequId()     // Catch: java.lang.Throwable -> L3d
            r5 = 1
            boolean r3 = kotlin.text.m.equals(r3, r4, r5)     // Catch: java.lang.Throwable -> L3d
            if (r3 == 0) goto L38
            java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl$QueueObject> r7 = r6.l     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r7 = r7.remove(r2)     // Catch: java.lang.Throwable -> L3d
            com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl$QueueObject r7 = (com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl.QueueObject) r7     // Catch: java.lang.Throwable -> L3d
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
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl.removeFromQueue(com.coveiot.android.bleabstract.request.BleBaseRequest):void");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        a();
        JStyle2301ABleManager.getInstance().restartService();
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
            String string = this.f3131a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.command_not_found)");
            ((DataResultListener) responseListener).onDataError(new BleBaseError(string));
        } else if (responseListener instanceof SettingsResultListener) {
            String string2 = this.f3131a.getString(R.string.command_not_found);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.command_not_found)");
            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(string2));
        } else if (responseListener instanceof ConnectionResultListener) {
            ((ConnectionResultListener) responseListener).onError(new Error(Type.COMMAND_REQUEST_ERROR, this.f3131a.getString(R.string.command_not_found)));
        }
    }

    public final void sendCommandRequest() {
        LinkedList<QueueObject> linkedList = this.l;
        if (linkedList == null || linkedList.size() <= 0 || this.g != null) {
            return;
        }
        processNextCommand();
    }

    public final void sendErrorAndClearQueue(@NotNull JstyleError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        synchronized (this.l) {
            LinkedList<QueueObject> linkedList = this.l;
            if (linkedList != null && linkedList.size() > 0) {
                try {
                    Object clone = this.l.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.LinkedList<com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl.QueueObject>");
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
        this.g = this.l.get(0).getBaseRequest();
        if (jStyleVBaseReq.isMultiPacket()) {
            this.f.postDelayed(this.p, this.h);
        } else {
            this.f.postDelayed(this.p, this.i);
        }
        JStyle2301ABleManager.getInstance().writeRequest(jStyleVBaseReq);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setBusyStatus(value);
    }

    public final void setCompleteAndProcessNext(@Nullable BleBaseRequest bleBaseRequest) {
        if (bleBaseRequest != null) {
            removeFromQueue(bleBaseRequest);
            String str = this.n;
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
        this.o = serviceConnection;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (JStyle2301ABleManager.getInstance().isConnected()) {
            if (b(request)) {
                request.setRequId(UUID.randomUUID().toString());
                request.setResponseListener(listener);
                addToQueue(request);
                sendCommandRequest();
                return;
            }
            String str = this.n;
            LogHelper.d(str, "setUserSettings->Ignore {" + request + '}');
            return;
        }
        String string = this.f3131a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }

    public final void setWalkLastSyncTimeToCurrent() {
        KHJstyleEntityDeviceInfo deviceInfoBy = KHJstyleDeviceInfoRepository.getInstance(this.f3131a).getDeviceInfoBy(getMacAddress());
        if (deviceInfoBy == null) {
            deviceInfoBy = new KHJstyleEntityDeviceInfo();
            deviceInfoBy.setMacAddress(getMacAddress());
        }
        deviceInfoBy.setLastSyncWalkTimeStamp(System.currentTimeMillis());
        KHJstyleDeviceInfoRepository.getInstance(this.f3131a).insertDeviceInfo(deviceInfoBy);
    }

    public final void startBleService() {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(this.f3131a).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        PreferenceManager2301.getInstance(this.f3131a).saveConnectedDeviceMAcAddress("");
        PreferenceManager2301.getInstance(this.f3131a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        JStyle2301ABleManager.getInstance().stopService();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
        LogHelper.d(this.n, "stopServiceAndRetainMacAddress");
        clearCommandQueue();
        PreferenceManagerAbstract.getInstance(this.f3131a).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        JStyle2301ABleManager.getInstance().stopServiceAndRetainMacAddress();
    }

    public final void unbindService() {
        JStyle2301ABleManager.getInstance().unbindService();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
    }

    public static final void c(DataResultListener dataResultListener, JStyle2301ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(dataResultListener);
        String string = this$0.f3131a.getString(R.string.data_error);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …                        )");
        dataResultListener.onDataError(new BleBaseError(string));
    }

    public final boolean b(BleBaseRequest bleBaseRequest) {
        boolean z = true;
        try {
            BleBaseRequest bleBaseRequest2 = this.g;
            if (bleBaseRequest2 != null) {
                if (((bleBaseRequest2 instanceof CustomWatchFaceFileImageRequest) || (bleBaseRequest2 instanceof CustomWatchFaceBackgroundChangeRequest)) && bleBaseRequest != null && (bleBaseRequest instanceof SetMessageContentRequest) && ((SetMessageContentRequest) bleBaseRequest).appNotificationType != null && ((SetMessageContentRequest) bleBaseRequest).appNotificationType == NotificationType.CALL) {
                    z = false;
                    LogHelper.d(this.n, "Ignore incoming call triggered during watch face upgrade");
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

    public static final void b(DataResultListener dataResultListener, JStyle2301ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(dataResultListener);
        String string = this$0.f3131a.getString(R.string.data_error);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.data_error)");
        dataResultListener.onDataError(new BleBaseError(string));
    }

    public static final void c(BleBaseRequest bleBaseRequest) {
        SettingsResultListener settingsResultListener = (SettingsResultListener) bleBaseRequest.getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse(bleBaseRequest));
    }

    public static final void a(final JStyle2301ABaseBleApiImpl this$0, final ScanDeviceRequest scanDeviceReq, final ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "$scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        try {
            if (!DeviceScanManager.getInstance(this$0.f3131a).isScanningInProgress()) {
                if (BleUtils.isEmpty(scanDeviceReq.getScanFilter())) {
                    DeviceScanManager.getInstance(this$0.f3131a).scanAllDevices(this$0.f3131a, scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl$scan_$2$1
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            JStyle2301ABaseBleApiImpl.this.scanResultReceieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = JStyle2301ABaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …                        )");
                            scanResultListener.onError(string);
                        }
                    });
                } else {
                    DeviceScanManager.getInstance(this$0.f3131a).scanDevicesWithFilter(this$0.f3131a, scanDeviceReq.getScanFilter(), scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl$scan_$2$2
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            JStyle2301ABaseBleApiImpl.this.scanResultReceieved(devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = JStyle2301ABaseBleApiImpl.this.getContext().getString(R.string.scan_failed);
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

    public static final void b(DataResultListener dataResultListener, BleBaseResponse batteryResponse) {
        Intrinsics.checkNotNullParameter(batteryResponse, "$batteryResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(batteryResponse);
    }

    public static final void c(JStyle2301ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFailure(new JstyleError(JstyleErrorType.COMMAND_REQUEST_ERROR, this$0.f3131a.getString(R.string.command_req_error)));
    }

    public static final void b(JStyle2301ABaseBleApiImpl this$0, BleBaseRequest bleBaseRequest) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setCompleteAndProcessNext(bleBaseRequest);
    }

    public static final void b(JStyle2301ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFailure(new JstyleError(JstyleErrorType.COMMAND_REQUEST_ERROR, this$0.f3131a.getString(R.string.command_req_error)));
    }

    public static final void d(DataResultListener dataResultListener, JStyle2301ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(dataResultListener);
        String string = this$0.f3131a.getString(R.string.data_error);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.data_error)");
        dataResultListener.onDataError(new BleBaseError(string));
    }

    public static final void d(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public final void a() {
        if (DeviceScanManager.getInstance(this.f3131a).isScanningInProgress()) {
            DeviceScanManager.getInstance(this.f3131a).stopScan();
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

    public static final void a(DataResultListener dataResultListener, BleBaseResponse mSleepResponse) {
        Intrinsics.checkNotNullParameter(mSleepResponse, "$mSleepResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(mSleepResponse);
    }

    public static final void a(DataResultListener dataResultListener, JStyle2301ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(dataResultListener);
        String string = this$0.f3131a.getString(R.string.data_error);
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

    public static final void a(JStyle2301ABaseBleApiImpl this$0, BleBaseRequest bleBaseRequest) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setCompleteAndProcessNext(bleBaseRequest);
    }

    public static final void a(JStyle2301ABaseBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFailure(new JstyleError(JstyleErrorType.COMMAND_REQUEST_ERROR, this$0.f3131a.getString(R.string.command_req_error)));
    }
}
