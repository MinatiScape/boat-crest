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
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.ReadManualBpRequest;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
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
import com.coveiot.android.bleabstract.services.BpMeasurementService;
import com.coveiot.android.bpsdk.CloveOmronBpBleState;
import com.coveiot.android.bpsdk.events.ConnectionTypeOmronBP;
import com.coveiot.android.bpsdk.utils.OmronBpConstants;
import com.coveiot.android.bpsdk.utils.OmronBpPreferenceManager;
import com.coveiot.sdk.ble.api.ResponseListener;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.ReadDeviceFirmwareVersionReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceHardwareVersionReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceManufacturerReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceModelReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceNameReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceSerialNumberReq;
import com.coveiot.sdk.ble.api.request.ReadDeviceSoftwareVersionReq;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.ReadDeviceFirmwareVersionRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceHardwareVersionRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceManufacturerRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceModelRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceNameRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceSerialNumberRes;
import com.coveiot.sdk.ble.api.response.ReadDeviceSoftwareVersionRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BpMeasurementImpl implements BleApi, ResponseListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2913a;
    @NotNull
    public final Handler b;
    public final String c;
    @NotNull
    public final Handler d;
    @NotNull
    public final Handler e;
    @Nullable
    public ConnectionResultListener f;
    @Nullable
    public BpMeasurementService g;
    @Nullable
    public MutableLiveData<ConnectionStatus> h;
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> i;
    @NotNull
    public final BpMeasurementImpl$serviceConnection$1 j;
    @Nullable
    public DeviceSupportedFeatures k;
    @NotNull
    public final ConnectionStatus l;
    @Nullable
    public DeviceInfoData m;
    @NotNull
    public final LinkedList<QueueObject> n;

    @DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.BpMeasurementImpl$1", f = "BpMeasurementImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.coveiot.android.bleabstract.bleimpl.BpMeasurementImpl$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public /* synthetic */ Object f2914a;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.f2914a = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            BleEventBusManager.getInstance().getEventBus().register((CoroutineScope) this.f2914a);
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<BpMeasurementImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.BpMeasurementImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, BpMeasurementImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f2915a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, BpMeasurementImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public BpMeasurementImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new BpMeasurementImpl(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f2915a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public static final class QueueObject {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f2916a;

        public QueueObject(@Nullable BleBaseRequest bleBaseRequest) {
            this.f2916a = bleBaseRequest;
        }

        @Nullable
        public final BleBaseRequest getBaseRequest() {
            return this.f2916a;
        }

        public final void setBaseRequest(@Nullable BleBaseRequest bleBaseRequest) {
            this.f2916a = bleBaseRequest;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[ResponseType.values().length];
            try {
                iArr[ResponseType.OMRON_BP_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CloveOmronBpBleState.BleState.values().length];
            try {
                iArr2[CloveOmronBpBleState.BleState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr2[CloveOmronBpBleState.BleState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[CloveOmronBpBleState.BleState.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[CommandType.values().length];
            try {
                iArr3[CommandType.READ_DEVICE_INFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    /* JADX WARN: Type inference failed for: r7v6, types: [com.coveiot.android.bleabstract.bleimpl.BpMeasurementImpl$serviceConnection$1] */
    public BpMeasurementImpl(Context context) {
        this.f2913a = context;
        this.b = new Handler(Looper.getMainLooper());
        this.c = BpMeasurementImpl.class.getSimpleName();
        this.d = new Handler();
        this.e = new Handler();
        this.j = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.BpMeasurementImpl$serviceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(@NotNull ComponentName name, @NotNull IBinder service) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(service, "service");
                BpMeasurementImpl.this.g = ((BpMeasurementService.BtServiceBinder) service).getService();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(@NotNull ComponentName name) {
                Intrinsics.checkNotNullParameter(name, "name");
                BpMeasurementImpl.this.g = null;
            }
        };
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(null), 2, null);
        this.l = ConnectionStatus.DISCONNECTED;
        this.n = new LinkedList<>();
    }

    public /* synthetic */ BpMeasurementImpl(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static final void access$scanResultRecieved(BpMeasurementImpl bpMeasurementImpl, List list, boolean z, ScanDeviceRequest scanDeviceRequest, ScanResultListener scanResultListener) {
        bpMeasurementImpl.getClass();
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

    public final void a(final ScanDeviceRequest scanDeviceRequest, final ScanResultListener scanResultListener) {
        this.d.removeCallbacksAndMessages(null);
        this.d.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.c
            @Override // java.lang.Runnable
            public final void run() {
                BpMeasurementImpl.a(BpMeasurementImpl.this, scanDeviceRequest, scanResultListener);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void addToQueue(@Nullable BleBaseRequest bleBaseRequest) {
        synchronized (this.n) {
            this.n.add(new QueueObject(bleBaseRequest));
        }
    }

    public final void b() {
        LogHelper.d(this.c, this.f2913a.getString(R.string.service_not_running), ModuleNames.BLEABSTRACT.getModuleName());
        try {
            Intent intent = new Intent(this.f2913a, BpMeasurementService.class);
            this.f2913a.bindService(intent, this.j, 1);
            if (Build.VERSION.SDK_INT >= 26) {
                this.f2913a.startForegroundService(intent);
            } else {
                this.f2913a.startService(intent);
            }
        } catch (Exception e) {
            BleApiUtils.checkExceptionAndShowNotification(e, this.f2913a);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return getBusyStatus() == BusyStatus.IDLE;
    }

    public final void checkAndStartService() {
        if (a()) {
            return;
        }
        LogHelper.d(this.c, "checkAndStartService-> service is not running ++ ");
        b();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void cleanUpCommands() {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void clearCommandQueue() {
    }

    public final void clearParameters() {
        if (DeviceScanManager.getInstance(this.f2913a).isScanningInProgress()) {
            DeviceScanManager.getInstance(this.f2913a).stopScan();
        }
        this.g = null;
        unbindService();
        Handler handler = this.e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.d;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(@NotNull final ConnectRequest request, @NotNull final ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.e.removeCallbacksAndMessages(null);
        this.f = listener;
        this.e.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.b
            @Override // java.lang.Runnable
            public final void run() {
                BpMeasurementImpl.a(BpMeasurementImpl.this, request, listener);
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        BpMeasurementService bpMeasurementService = this.g;
        if (bpMeasurementService != null) {
            Intrinsics.checkNotNull(bpMeasurementService);
            bpMeasurementService.disconnectAndForget();
            clearParameters();
            return;
        }
        listener.onError(new Error(Type.SERVICE_NOT_RUNNING, this.f2913a.getString(R.string.service_not_present)));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return getBusyStatus();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        if (this.g != null) {
            ConnectionStatus connectionStatus = getConnectionStatus();
            BpMeasurementService bpMeasurementService = this.g;
            Intrinsics.checkNotNull(bpMeasurementService);
            ConnectionError connectionError = bpMeasurementService.getConnectionError();
            BpMeasurementService bpMeasurementService2 = this.g;
            Intrinsics.checkNotNull(bpMeasurementService2);
            return new ConnectionInfo(connectionStatus, connectionError, bpMeasurementService2.getConnectionStageChangeTimeStamp());
        }
        return null;
    }

    @Nullable
    public final ConnectionResultListener getConnectionResultListener$bleabstract_release() {
        return this.f;
    }

    @Nullable
    public final MutableLiveData<ConnectionStatus> getConnectionStateLiveData() {
        return this.h;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public ConnectionStatus getConnectionStatus() {
        return this.l;
    }

    @NotNull
    public final Context getContext() {
        return this.f2913a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BpMeasurementService bpMeasurementService = this.g;
        if (bpMeasurementService != null) {
            if (request instanceof DeviceInfoRequest) {
                request.setBleCommand(BleCommand.DEVICE_INFO);
                request.setResponseListener(listener);
                request.setRequId(UUID.randomUUID().toString());
                this.m = null;
                DeviceInfoRequest deviceInfoRequest = (DeviceInfoRequest) request;
                if (deviceInfoRequest.isSerialNo()) {
                    ReadDeviceSerialNumberReq build = new ReadDeviceSerialNumberReq.Builder().build();
                    build.setResponseListener(this);
                    addToQueue(request);
                    build.setReqId(request.getRequId());
                    BpMeasurementService bpMeasurementService2 = this.g;
                    Intrinsics.checkNotNull(bpMeasurementService2);
                    bpMeasurementService2.sendRequest(build, this);
                }
                if (deviceInfoRequest.isDevicName()) {
                    ReadDeviceNameReq build2 = new ReadDeviceNameReq.Builder().build();
                    build2.setResponseListener(this);
                    addToQueue(request);
                    build2.setReqId(request.getRequId());
                    BpMeasurementService bpMeasurementService3 = this.g;
                    Intrinsics.checkNotNull(bpMeasurementService3);
                    bpMeasurementService3.sendRequest(build2, this);
                }
                if (deviceInfoRequest.isMacAddress()) {
                    String macAddress = getMacAddress();
                    if (this.m == null) {
                        this.m = new DeviceInfoData();
                    }
                    DeviceInfoData deviceInfoData = this.m;
                    Intrinsics.checkNotNull(deviceInfoData);
                    deviceInfoData.setMacAddress(macAddress);
                }
                if (deviceInfoRequest.isFwVersion()) {
                    ReadDeviceFirmwareVersionReq build3 = new ReadDeviceFirmwareVersionReq.Builder().build();
                    build3.setResponseListener(this);
                    addToQueue(request);
                    build3.setReqId(request.getRequId());
                    BpMeasurementService bpMeasurementService4 = this.g;
                    Intrinsics.checkNotNull(bpMeasurementService4);
                    bpMeasurementService4.sendRequest(build3, this);
                }
                if (deviceInfoRequest.isHwVersion()) {
                    ReadDeviceHardwareVersionReq build4 = new ReadDeviceHardwareVersionReq.Builder().build();
                    build4.setResponseListener(this);
                    addToQueue(request);
                    build4.setReqId(request.getRequId());
                    BpMeasurementService bpMeasurementService5 = this.g;
                    Intrinsics.checkNotNull(bpMeasurementService5);
                    bpMeasurementService5.sendRequest(build4, this);
                }
                if (deviceInfoRequest.isModelNo()) {
                    ReadDeviceModelReq build5 = new ReadDeviceModelReq.Builder().build();
                    build5.setResponseListener(this);
                    addToQueue(request);
                    build5.setReqId(request.getRequId());
                    BpMeasurementService bpMeasurementService6 = this.g;
                    Intrinsics.checkNotNull(bpMeasurementService6);
                    bpMeasurementService6.sendRequest(build5, this);
                }
                if (deviceInfoRequest.isSoftwareRevision()) {
                    ReadDeviceSoftwareVersionReq build6 = new ReadDeviceSoftwareVersionReq.Builder().build();
                    build6.setResponseListener(this);
                    addToQueue(request);
                    build6.setReqId(request.getRequId());
                    BpMeasurementService bpMeasurementService7 = this.g;
                    Intrinsics.checkNotNull(bpMeasurementService7);
                    bpMeasurementService7.sendRequest(build6, this);
                }
                if (deviceInfoRequest.isManufacturerName()) {
                    ReadDeviceManufacturerReq build7 = new ReadDeviceManufacturerReq.Builder().build();
                    build7.setResponseListener(this);
                    addToQueue(request);
                    build7.setReqId(request.getRequId());
                    BpMeasurementService bpMeasurementService8 = this.g;
                    Intrinsics.checkNotNull(bpMeasurementService8);
                    bpMeasurementService8.sendRequest(build7, this);
                }
            } else if (request instanceof ReadManualBpRequest) {
                Intrinsics.checkNotNull(bpMeasurementService);
                bpMeasurementService.readBpDataFromDevice();
                onDataResponse(new BleBaseResponse(request), listener);
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        if (this.k == null) {
            DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
            this.k = deviceSupportedFeatures;
            deviceSupportedFeatures.setSleepSupported(false);
            DeviceSupportedFeatures deviceSupportedFeatures2 = this.k;
            if (deviceSupportedFeatures2 != null) {
                deviceSupportedFeatures2.setStepsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures3 = this.k;
            if (deviceSupportedFeatures3 != null) {
                deviceSupportedFeatures3.setHeartRateSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures4 = this.k;
            if (deviceSupportedFeatures4 != null) {
                deviceSupportedFeatures4.setSedentaryReminderSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures5 = this.k;
            if (deviceSupportedFeatures5 != null) {
                deviceSupportedFeatures5.setPersonalInfoSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures6 = this.k;
            if (deviceSupportedFeatures6 != null) {
                deviceSupportedFeatures6.setStepGoalSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures7 = this.k;
            if (deviceSupportedFeatures7 != null) {
                deviceSupportedFeatures7.setCallNotificationSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures8 = this.k;
            if (deviceSupportedFeatures8 != null) {
                deviceSupportedFeatures8.setSmsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures9 = this.k;
            if (deviceSupportedFeatures9 != null) {
                deviceSupportedFeatures9.setMessageReadSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures10 = this.k;
            if (deviceSupportedFeatures10 != null) {
                deviceSupportedFeatures10.setSocialNotificationSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures11 = this.k;
            if (deviceSupportedFeatures11 != null) {
                deviceSupportedFeatures11.setHandSettingsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures12 = this.k;
            if (deviceSupportedFeatures12 != null) {
                deviceSupportedFeatures12.setPersonalInfoSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures13 = this.k;
            if (deviceSupportedFeatures13 != null) {
                deviceSupportedFeatures13.setBpSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures14 = this.k;
            if (deviceSupportedFeatures14 != null) {
                deviceSupportedFeatures14.setMaxDaysOfStepsDataOnBand(0);
            }
            DeviceSupportedFeatures deviceSupportedFeatures15 = this.k;
            if (deviceSupportedFeatures15 != null) {
                deviceSupportedFeatures15.setMaxDaysOfSleepDataOnBand(0);
            }
            DeviceSupportedFeatures deviceSupportedFeatures16 = this.k;
            if (deviceSupportedFeatures16 != null) {
                deviceSupportedFeatures16.setMaxDaysOfHeartRateDataOnBand(0);
            }
        }
        DeviceSupportedFeatures deviceSupportedFeatures17 = this.k;
        Intrinsics.checkNotNull(deviceSupportedFeatures17);
        return deviceSupportedFeatures17;
    }

    @Nullable
    public final QueueObject getFromQueue(@NotNull BleCommand bleCommand, @NotNull BaseResponse response) {
        Intrinsics.checkNotNullParameter(bleCommand, "bleCommand");
        Intrinsics.checkNotNullParameter(response, "response");
        synchronized (this.n) {
            Iterator<QueueObject> it = this.n.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "queue.iterator()");
            while (it.hasNext()) {
                QueueObject next = it.next();
                Intrinsics.checkNotNullExpressionValue(next, "iterator.next()");
                QueueObject queueObject = next;
                BleBaseRequest baseRequest = queueObject.getBaseRequest();
                Intrinsics.checkNotNull(baseRequest);
                if (baseRequest.getBleCommand() == bleCommand) {
                    BleBaseRequest baseRequest2 = queueObject.getBaseRequest();
                    Intrinsics.checkNotNull(baseRequest2);
                    if (Intrinsics.areEqual(baseRequest2.getRequId(), response.getBaseRequest().getReqId())) {
                        it.remove();
                        return queueObject;
                    }
                }
            }
            return null;
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        Object preference = OmronBpPreferenceManager.getPreference(this.f2913a, "ble_address", "");
        Intrinsics.checkNotNullExpressionValue(preference, "getPreference(\n         …\n            \"\"\n        )");
        return (String) preference;
    }

    public final int getSameCommandCount(@NotNull BleCommand bleCommand) {
        Intrinsics.checkNotNullParameter(bleCommand, "bleCommand");
        LinkedList<QueueObject> linkedList = this.n;
        int i = 0;
        if (linkedList != null && linkedList.size() > 0) {
            Iterator<QueueObject> it = this.n.iterator();
            while (it.hasNext()) {
                BleBaseRequest baseRequest = it.next().getBaseRequest();
                Intrinsics.checkNotNull(baseRequest);
                if (baseRequest.getBleCommand() == bleCommand) {
                    i++;
                }
            }
        }
        return i;
    }

    public final String getTAG() {
        return this.c;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        String name = BpMeasurementService.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "BpMeasurementService::class.java.name");
        return BleApiUtils.isServiceRunning(name, this.f2913a);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Subscribe
    public final void onConnectionStateChanged(@NotNull CloveOmronBpBleState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        CloveOmronBpBleState.BleState bleState = state.getmState();
        int i = bleState == null ? -1 : WhenMappings.$EnumSwitchMapping$1[bleState.ordinal()];
        if (i == 1) {
            connectionStatus = ConnectionStatus.CONNECTED;
        } else if (i == 2) {
            connectionStatus = ConnectionStatus.CONNECTING;
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.h;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(connectionStatus);
        }
        ConnectionResultListener connectionResultListener = this.f;
        if (connectionResultListener != null) {
            connectionResultListener.onConnectionResponse(connectionStatus);
        }
    }

    public final void onDataResponse(@Nullable final BleBaseResponse bleBaseResponse, @NotNull final DataResultListener responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.b.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.d
            @Override // java.lang.Runnable
            public final void run() {
                BpMeasurementImpl.a(DataResultListener.this, bleBaseResponse);
            }
        });
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void onFailure(@Nullable com.coveiot.sdk.ble.api.error.Error error) {
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(@Nullable BaseResponse baseResponse) {
        BleCommand bleCommand;
        QueueObject fromQueue;
        if (baseResponse != null) {
            CommandType activityType = baseResponse.getActivityType();
            if ((activityType == null ? -1 : WhenMappings.$EnumSwitchMapping$2[activityType.ordinal()]) != 1 || (fromQueue = getFromQueue((bleCommand = BleCommand.DEVICE_INFO), baseResponse)) == null) {
                return;
            }
            int sameCommandCount = getSameCommandCount(bleCommand);
            if (this.m == null) {
                this.m = new DeviceInfoData();
            }
            if (baseResponse instanceof ReadDeviceNameRes) {
                DeviceInfoData deviceInfoData = this.m;
                Intrinsics.checkNotNull(deviceInfoData);
                deviceInfoData.setDeviceName(((ReadDeviceNameRes) baseResponse).getName());
            } else if (baseResponse instanceof ReadDeviceSerialNumberRes) {
                ReadDeviceSerialNumberRes readDeviceSerialNumberRes = (ReadDeviceSerialNumberRes) baseResponse;
                if (!kotlin.text.m.equals(readDeviceSerialNumberRes.getSerialNumber(), "N123456", true) && !kotlin.text.m.equals(readDeviceSerialNumberRes.getSerialNumber(), "kh23456", true)) {
                    DeviceInfoData deviceInfoData2 = this.m;
                    Intrinsics.checkNotNull(deviceInfoData2);
                    deviceInfoData2.setSerialNo(readDeviceSerialNumberRes.getSerialNumber());
                } else {
                    String macAddress = (String) BlePreferenceManager.getPreference(this.f2913a, CommonPreference.BLE_DEVICE_ADDRESS, "");
                    Intrinsics.checkNotNullExpressionValue(macAddress, "macAddress");
                    String replace = new Regex(":").replace(macAddress, "");
                    DeviceInfoData deviceInfoData3 = this.m;
                    Intrinsics.checkNotNull(deviceInfoData3);
                    deviceInfoData3.setSerialNo(replace);
                }
            } else if (baseResponse instanceof ReadDeviceManufacturerRes) {
                DeviceInfoData deviceInfoData4 = this.m;
                Intrinsics.checkNotNull(deviceInfoData4);
                deviceInfoData4.setManufacturerName(((ReadDeviceManufacturerRes) baseResponse).getManufacturerName());
            } else if (baseResponse instanceof ReadDeviceHardwareVersionRes) {
                DeviceInfoData deviceInfoData5 = this.m;
                Intrinsics.checkNotNull(deviceInfoData5);
                deviceInfoData5.setHwVersion(((ReadDeviceHardwareVersionRes) baseResponse).getHardwareVersion());
            } else if (baseResponse instanceof ReadDeviceSoftwareVersionRes) {
                DeviceInfoData deviceInfoData6 = this.m;
                Intrinsics.checkNotNull(deviceInfoData6);
                deviceInfoData6.setSoftwareRevision(((ReadDeviceSoftwareVersionRes) baseResponse).getSoftwareVersion());
            } else if (baseResponse instanceof ReadDeviceFirmwareVersionRes) {
                DeviceInfoData deviceInfoData7 = this.m;
                Intrinsics.checkNotNull(deviceInfoData7);
                deviceInfoData7.setFwVersion(((ReadDeviceFirmwareVersionRes) baseResponse).getFirmwareVersion());
            } else if (baseResponse instanceof ReadDeviceModelRes) {
                DeviceInfoData deviceInfoData8 = this.m;
                Intrinsics.checkNotNull(deviceInfoData8);
                deviceInfoData8.setModelNo(((ReadDeviceModelRes) baseResponse).getModelNumber());
            }
            BleBaseRequest baseRequest = fromQueue.getBaseRequest();
            Intrinsics.checkNotNull(baseRequest);
            DataResultListener dataResultListener = (DataResultListener) baseRequest.getResponseListener();
            DeviceInfoResponse deviceInfoResponse = new DeviceInfoResponse();
            if (sameCommandCount == 0) {
                deviceInfoResponse.setComplete(true);
            } else {
                deviceInfoResponse.setComplete(false);
            }
            deviceInfoResponse.setDeviceInfo(this.m);
            BleBaseRequest baseRequest2 = fromQueue.getBaseRequest();
            Intrinsics.checkNotNull(baseRequest2);
            BleBaseResponse bleBaseResponse = new BleBaseResponse(baseRequest2);
            bleBaseResponse.setResponseData(deviceInfoResponse);
            Intrinsics.checkNotNull(dataResultListener);
            onDataResponse(bleBaseResponse, dataResultListener);
        }
    }

    @Subscribe
    public final void onResponseEventReceived(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        ResponseType type = responseEvent.getType();
        if ((type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) == 1) {
            MutableLiveData<BloodPressureMeasurement> mutableLiveData = this.i;
            Intrinsics.checkNotNull(mutableLiveData);
            Object data = responseEvent.getData();
            Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BloodPressureMeasurement");
            mutableLiveData.postValue((BloodPressureMeasurement) data);
            return;
        }
        LogHelper.e(this.c, this.f2913a.getString(R.string.no_bp_data));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.h == null) {
            this.h = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        BpMeasurementService bpMeasurementService = this.g;
        if (bpMeasurementService != null) {
            if (bpMeasurementService.getConnectionState() == CloveOmronBpBleState.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else {
                BpMeasurementService bpMeasurementService2 = this.g;
                if ((bpMeasurementService2 != null ? bpMeasurementService2.getConnectionState() : null) == CloveOmronBpBleState.BleState.CONNECTING) {
                    connectionStatus = ConnectionStatus.CONNECTING;
                } else {
                    BpMeasurementService bpMeasurementService3 = this.g;
                    if ((bpMeasurementService3 != null ? bpMeasurementService3.getConnectionState() : null) == CloveOmronBpBleState.BleState.SCANNING) {
                        connectionStatus = ConnectionStatus.SCANNING;
                    } else {
                        BpMeasurementService bpMeasurementService4 = this.g;
                        if ((bpMeasurementService4 != null ? bpMeasurementService4.getConnectionState() : null) == CloveOmronBpBleState.BleState.DISCOVERING_SERVICES) {
                            connectionStatus = ConnectionStatus.DISCOVERING_SERVICES;
                        }
                    }
                }
            }
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.h;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(connectionStatus);
        }
        MutableLiveData<ConnectionStatus> mutableLiveData2 = this.h;
        Intrinsics.checkNotNull(mutableLiveData2);
        return mutableLiveData2;
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
        if (this.i == null) {
            this.i = new MutableLiveData<>();
        }
        return this.i;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveHealthData> registerLiveHealthData() {
        return new MutableLiveData();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<PPGData> registerLivePPGData() {
        return new MutableLiveData<>();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<Spo2Response> registerLiveSpo2Data() {
        return new MutableLiveData<>();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<Spo2WaveResponse> registerLiveSpo2WaveData() {
        return new MutableLiveData<>();
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

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void restartService() {
        clearCommandQueue();
        BpMeasurementService bpMeasurementService = this.g;
        if (bpMeasurementService != null) {
            bpMeasurementService.restartService();
            clearParameters();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.a
            @Override // java.lang.Runnable
            public final void run() {
                BpMeasurementImpl.a(BpMeasurementImpl.this);
            }
        }, 5000L);
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void retryCommand(@Nullable BaseRequest baseRequest) {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(@NotNull ScanDeviceRequest request, @NotNull ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        a(request, listener);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setBusyStatus(value);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
    }

    public final void setConnectionResultListener$bleabstract_release(@Nullable ConnectionResultListener connectionResultListener) {
        this.f = connectionResultListener;
    }

    public final void setConnectionStateLiveData(@Nullable MutableLiveData<ConnectionStatus> mutableLiveData) {
        this.h = mutableLiveData;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(this.f2913a).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        OmronBpPreferenceManager.savePreference(this.f2913a, OmronBpConstants.BLE_DEVICE_ADDRESS.name(), "");
        OmronBpPreferenceManager.savePreference(this.f2913a, OmronBpConstants.BLE_CONNECTION_TYPE.name(), ConnectionTypeOmronBP.DONT_CONNECT_ON_DISCONNECT.name());
        BpMeasurementService bpMeasurementService = this.g;
        if (bpMeasurementService != null) {
            Intrinsics.checkNotNull(bpMeasurementService);
            bpMeasurementService.stopService();
            clearParameters();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
    }

    public final void unbindService() {
        BpMeasurementImpl$serviceConnection$1 bpMeasurementImpl$serviceConnection$1 = this.j;
        if (bpMeasurementImpl$serviceConnection$1 != null) {
            this.f2913a.unbindService(bpMeasurementImpl$serviceConnection$1);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
        clearCommandQueue();
        BpMeasurementService bpMeasurementService = this.g;
        if (bpMeasurementService != null) {
            bpMeasurementService.disconnectAndForget();
            clearParameters();
        }
    }

    public static final void a(final BpMeasurementImpl this$0, final ScanDeviceRequest scanDeviceReq, final ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "$scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        try {
            if (!DeviceScanManager.getInstance(this$0.f2913a).isScanningInProgress()) {
                if (BleUtils.isEmpty(scanDeviceReq.getScanFilter())) {
                    DeviceScanManager.getInstance(this$0.f2913a).scanAllDevices(this$0.f2913a, scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.BpMeasurementImpl$scanDevice$1$1
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            BpMeasurementImpl.access$scanResultRecieved(BpMeasurementImpl.this, devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = BpMeasurementImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                            scanResultListener.onError(string);
                        }
                    });
                } else {
                    DeviceScanManager.getInstance(this$0.f2913a).scanDevicesWithFilter(this$0.f2913a, scanDeviceReq.getScanFilter(), scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.BpMeasurementImpl$scanDevice$1$2
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            BpMeasurementImpl.access$scanResultRecieved(BpMeasurementImpl.this, devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = BpMeasurementImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                            scanResultListener.onError(string);
                        }
                    });
                }
            } else {
                String string = this$0.f2913a.getString(R.string.scan_already_started);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_already_started)");
                listener.onError(string);
            }
        } catch (RuntimeException e) {
            String message = e.getMessage();
            Intrinsics.checkNotNull(message);
            String string2 = this$0.f2913a.getString(R.string.enable_location_permission);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…able_location_permission)");
            String str = null;
            if (StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) string2, false, 2, (Object) null)) {
                str = e.getMessage();
            } else if (kotlin.text.m.equals(e.getMessage(), this$0.f2913a.getString(R.string.enable_bluetooth_scan), true)) {
                str = e.getMessage();
            }
            Intrinsics.checkNotNull(str);
            listener.onError(str);
            e.printStackTrace();
        }
    }

    public static final void a(BpMeasurementImpl this$0, ConnectRequest request, ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        BpMeasurementService bpMeasurementService = this$0.g;
        if (bpMeasurementService == null) {
            if (bpMeasurementService == null) {
                OmronBpPreferenceManager.savePreference(this$0.f2913a, OmronBpConstants.BLE_DEVICE_ADDRESS.getName(), request.getMacAddress());
                this$0.b();
                listener.onError(new Error(Type.SERVICE_NOT_RUNNING, this$0.f2913a.getString(R.string.service_not_running_call_again)));
                return;
            }
            listener.onError(new Error(Type.COMMAND_REQUEST_ERROR, this$0.f2913a.getString(R.string.request_should_not_null)));
            return;
        }
        Intrinsics.checkNotNull(bpMeasurementService);
        if (bpMeasurementService.getConnectionState() == CloveOmronBpBleState.BleState.CONNECTED) {
            BpMeasurementService bpMeasurementService2 = this$0.g;
            Intrinsics.checkNotNull(bpMeasurementService2);
            if (kotlin.text.m.equals((String) OmronBpPreferenceManager.getPreference(bpMeasurementService2.getApplicationContext(), OmronBpConstants.BLE_DEVICE_ADDRESS.name(), ""), request.getMacAddress(), true)) {
                listener.onConnectionResponse(ConnectionStatus.CONNECTED);
                return;
            }
        }
        BpMeasurementService bpMeasurementService3 = this$0.g;
        Intrinsics.checkNotNull(bpMeasurementService3);
        if (bpMeasurementService3.getConnectionState() == CloveOmronBpBleState.BleState.DISCONNECTED) {
            BpMeasurementService bpMeasurementService4 = this$0.g;
            Intrinsics.checkNotNull(bpMeasurementService4);
            bpMeasurementService4.connect(request.getMacAddress());
            return;
        }
        BpMeasurementService bpMeasurementService5 = this$0.g;
        Intrinsics.checkNotNull(bpMeasurementService5);
        if (bpMeasurementService5.getConnectionState() == CloveOmronBpBleState.BleState.CONNECTED) {
            StringBuilder sb = new StringBuilder();
            sb.append("Connected to band ");
            BpMeasurementService bpMeasurementService6 = this$0.g;
            Intrinsics.checkNotNull(bpMeasurementService6);
            sb.append(bpMeasurementService6.mBluetoothDevice.getAddress());
            listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, sb.toString()));
            return;
        }
        BpMeasurementService bpMeasurementService7 = this$0.g;
        Intrinsics.checkNotNull(bpMeasurementService7);
        if (bpMeasurementService7.getConnectionState() == CloveOmronBpBleState.BleState.CONNECTING) {
            BpMeasurementService bpMeasurementService8 = this$0.g;
            Intrinsics.checkNotNull(bpMeasurementService8);
            if (bpMeasurementService8.mBluetoothDevice != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Connection in progress ");
                BpMeasurementService bpMeasurementService9 = this$0.g;
                Intrinsics.checkNotNull(bpMeasurementService9);
                sb2.append(bpMeasurementService9.mBluetoothDevice.getAddress());
                listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, sb2.toString()));
                return;
            }
            BpMeasurementService bpMeasurementService10 = this$0.g;
            Intrinsics.checkNotNull(bpMeasurementService10);
            listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, bpMeasurementService10.getConnectionState().getStateAsString()));
            return;
        }
        BpMeasurementService bpMeasurementService11 = this$0.g;
        Intrinsics.checkNotNull(bpMeasurementService11);
        listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, bpMeasurementService11.getConnectionState().getStateAsString()));
    }

    public static final void a(BpMeasurementImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b();
    }

    public final boolean a() {
        Object systemService = this.f2913a.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (Intrinsics.areEqual(BpMeasurementService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f2913a.getPackageName(), runningServiceInfo.service.getPackageName())) {
                z = true;
            }
        }
        return z;
    }

    public static final void a(DataResultListener responseListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(responseListener, "$responseListener");
        Intrinsics.checkNotNull(bleBaseResponse);
        responseListener.onDataResponse(bleBaseResponse);
    }
}
