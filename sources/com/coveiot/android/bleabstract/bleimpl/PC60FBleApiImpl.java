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
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.request.Spo2DataRequest;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
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
import com.coveiot.android.bleabstract.services.PC60FBleService;
import com.coveiot.android.spo2sdk.CloveBleStatePC60F;
import com.coveiot.android.spo2sdk.events.ConnectionTypeSpo2;
import com.coveiot.android.spo2sdk.utils.Spo2PrefConstants;
import com.coveiot.android.spo2sdk.utils.Spo2PreferenceManager;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.scan.AssociationResult;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.creative.FingerOximeter.FingerOximeter;
import com.creative.base.Ireader;
import com.creative.base.Isender;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
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
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class PC60FBleApiImpl implements BleApi, Ireader, Isender {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3204a;
    @NotNull
    public final String b;
    @NotNull
    public final Handler c;
    @NotNull
    public final Handler d;
    @Nullable
    public ConnectionResultListener e;
    @Nullable
    public PC60FBleService f;
    @Nullable
    public MutableLiveData<ConnectionStatus> g;
    @NotNull
    public final Handler h;
    @Nullable
    public FingerOximeter i;
    @Nullable
    public MutableLiveData<Spo2WaveResponse> j;
    @Nullable
    public MutableLiveData<Spo2Response> k;
    @Nullable
    public DeviceSupportedFeatures l;
    @NotNull
    public final ConnectionStatus m;
    @Nullable
    public Handler n;
    @NotNull
    public final PC60FBleApiImpl$serviceConnection$1 o;

    @DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.PC60FBleApiImpl$1", f = "PC60FBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.coveiot.android.bleabstract.bleimpl.PC60FBleApiImpl$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public /* synthetic */ Object f3205a;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.f3205a = obj;
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
            BleEventBusManager.getInstance().getEventBus().register((CoroutineScope) this.f3205a);
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<PC60FBleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.PC60FBleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, PC60FBleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3206a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, PC60FBleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public PC60FBleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new PC60FBleApiImpl(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3206a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public final class QueueObject {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public BleBaseRequest f3207a;

        public QueueObject(@Nullable PC60FBleApiImpl pC60FBleApiImpl, BleBaseRequest bleBaseRequest) {
            this.f3207a = bleBaseRequest;
        }

        @Nullable
        public final BleBaseRequest getBaseRequest() {
            return this.f3207a;
        }

        public final void setBaseRequest(@Nullable BleBaseRequest bleBaseRequest) {
            this.f3207a = bleBaseRequest;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CloveBleStatePC60F.BleState.values().length];
            try {
                iArr[CloveBleStatePC60F.BleState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CloveBleStatePC60F.BleState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CloveBleStatePC60F.BleState.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.coveiot.android.bleabstract.bleimpl.PC60FBleApiImpl$serviceConnection$1, android.content.ServiceConnection] */
    public PC60FBleApiImpl(Context context) {
        this.f3204a = context;
        this.b = "PC60FBleApiImpl";
        this.c = new Handler();
        this.d = new Handler();
        this.h = new Handler(Looper.getMainLooper());
        this.m = ConnectionStatus.DISCONNECTED;
        this.n = new Handler();
        ?? r1 = new ServiceConnection() { // from class: com.coveiot.android.bleabstract.bleimpl.PC60FBleApiImpl$serviceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(@NotNull ComponentName name, @NotNull IBinder service) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(service, "service");
                PC60FBleApiImpl.this.f = ((PC60FBleService.BtServiceBinder) service).getService();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(@NotNull ComponentName name) {
                Intrinsics.checkNotNullParameter(name, "name");
                PC60FBleApiImpl.this.f = null;
            }
        };
        this.o = r1;
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(null), 2, null);
        if (a()) {
            return;
        }
        LogHelper.d("PC60FBleApiImpl", "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
        try {
            Intent intent = new Intent(context, PC60FBleService.class);
            context.bindService(intent, r1, 1);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this.f3204a);
        }
    }

    public /* synthetic */ PC60FBleApiImpl(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static final void access$scanResultRecieved(PC60FBleApiImpl pC60FBleApiImpl, List list, boolean z, ScanDeviceRequest scanDeviceRequest, ScanResultListener scanResultListener) {
        pC60FBleApiImpl.getClass();
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
        this.c.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            if (scanDeviceRequest.getActivity() != null) {
                DeviceScanManager.getInstance(this.f3204a).startAssociation(scanDeviceRequest.getScanFilter(), scanDeviceRequest.getActivity(), scanDeviceRequest.getReqCode(), scanDeviceRequest.getScanSingleDevice(), new AssociationResult() { // from class: com.coveiot.android.bleabstract.bleimpl.PC60FBleApiImpl$scan_$1
                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationFailed(@NotNull CharSequence error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ScanResultListener scanResultListener2 = scanResultListener;
                        String string = PC60FBleApiImpl.this.getContext().getString(R.string.scan_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                        scanResultListener2.onError(string);
                    }

                    @Override // com.coveiot.sdk.ble.scan.AssociationResult
                    public void onAssociationSuccess(@NotNull String messgae) {
                        Intrinsics.checkNotNullParameter(messgae, "messgae");
                        PC60FBleApiImpl.access$scanResultRecieved(PC60FBleApiImpl.this, new ArrayList(), true, scanDeviceRequest, scanResultListener);
                    }
                });
                return;
            }
            throw new RuntimeException("Activity instance is null");
        }
        this.c.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.o6
            @Override // java.lang.Runnable
            public final void run() {
                PC60FBleApiImpl.a(PC60FBleApiImpl.this, scanDeviceRequest, scanResultListener);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public int available() {
        PC60FBleService pC60FBleService = this.f;
        if (pC60FBleService != null) {
            return pC60FBleService.available();
        }
        return 0;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean canTerminateConnectionNow() {
        return getBusyStatus() == BusyStatus.IDLE;
    }

    public void clean() {
        PC60FBleService pC60FBleService = this.f;
        if (pC60FBleService != null) {
            pC60FBleService.clearBuffer();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void cleanUpCommands() {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void clearCommandQueue() {
        PC60FBleService pC60FBleService = this.f;
        if (pC60FBleService != null) {
            pC60FBleService.clearBuffer();
        }
    }

    public final void clearParameters() {
        if (DeviceScanManager.getInstance(this.f3204a).isScanningInProgress()) {
            DeviceScanManager.getInstance(this.f3204a).stopScan();
        }
        this.f = null;
        unbindService();
        Handler handler = this.d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.c;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.n;
        if (handler3 != null) {
            Intrinsics.checkNotNull(handler3);
            handler3.removeCallbacksAndMessages(null);
        }
    }

    public void close() {
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void connect(@NotNull final ConnectRequest request, @NotNull final ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d.removeCallbacksAndMessages(null);
        this.e = listener;
        this.d.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.n6
            @Override // java.lang.Runnable
            public final void run() {
                PC60FBleApiImpl.a(PC60FBleApiImpl.this, request, listener);
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void disconnect(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.f != null) {
            BleBaseRequest bleBaseRequest = new BleBaseRequest();
            bleBaseRequest.setBleCommand(BleCommand.DISCONNECT);
            bleBaseRequest.setResponseListener(listener);
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            PC60FBleService pC60FBleService = this.f;
            Intrinsics.checkNotNull(pC60FBleService);
            pC60FBleService.disconnectAndForget();
            PC60FBleService pC60FBleService2 = this.f;
            Intrinsics.checkNotNull(pC60FBleService2);
            pC60FBleService2.clearBuffer();
            clearParameters();
            return;
        }
        listener.onError(new Error(Type.SERVICE_NOT_RUNNING, "service is not present"));
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public BusyStatus getBusyStatus() {
        return BusyStatus.SYNCING;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public ConnectionInfo getConnectionInfo() {
        if (this.f != null) {
            ConnectionStatus connectionStatus = getConnectionStatus();
            PC60FBleService pC60FBleService = this.f;
            Intrinsics.checkNotNull(pC60FBleService);
            ConnectionError connectionError = pC60FBleService.getConnectionError();
            PC60FBleService pC60FBleService2 = this.f;
            Intrinsics.checkNotNull(pC60FBleService2);
            return new ConnectionInfo(connectionStatus, connectionError, pC60FBleService2.getConnectionStageChangeTimeStamp());
        }
        return null;
    }

    @Nullable
    public final ConnectionResultListener getConnectionResultListener$bleabstract_release() {
        return this.e;
    }

    @Nullable
    public final MutableLiveData<ConnectionStatus> getConnectionStateLiveData() {
        return this.g;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public ConnectionStatus getConnectionStatus() {
        return this.m;
    }

    @NotNull
    public final Context getContext() {
        return this.f3204a;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.i == null) {
            this.i = new FingerOximeter(this, this, new PC60FBleApiImpl$getFingerOximeter$1(this, null, request, listener));
        }
        FingerOximeter fingerOximeter = this.i;
        if (!(request instanceof DeviceInfoRequest) || fingerOximeter == null) {
            return;
        }
        fingerOximeter.QueryDeviceVer();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        if (this.l == null) {
            DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
            this.l = deviceSupportedFeatures;
            deviceSupportedFeatures.setSleepSupported(false);
            DeviceSupportedFeatures deviceSupportedFeatures2 = this.l;
            if (deviceSupportedFeatures2 != null) {
                deviceSupportedFeatures2.setStepsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures3 = this.l;
            if (deviceSupportedFeatures3 != null) {
                deviceSupportedFeatures3.setHeartRateSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures4 = this.l;
            if (deviceSupportedFeatures4 != null) {
                deviceSupportedFeatures4.setSedentaryReminderSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures5 = this.l;
            if (deviceSupportedFeatures5 != null) {
                deviceSupportedFeatures5.setPersonalInfoSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures6 = this.l;
            if (deviceSupportedFeatures6 != null) {
                deviceSupportedFeatures6.setStepGoalSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures7 = this.l;
            if (deviceSupportedFeatures7 != null) {
                deviceSupportedFeatures7.setCallNotificationSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures8 = this.l;
            if (deviceSupportedFeatures8 != null) {
                deviceSupportedFeatures8.setSmsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures9 = this.l;
            if (deviceSupportedFeatures9 != null) {
                deviceSupportedFeatures9.setMessageReadSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures10 = this.l;
            if (deviceSupportedFeatures10 != null) {
                deviceSupportedFeatures10.setSocialNotificationSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures11 = this.l;
            if (deviceSupportedFeatures11 != null) {
                deviceSupportedFeatures11.setHandSettingsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures12 = this.l;
            if (deviceSupportedFeatures12 != null) {
                deviceSupportedFeatures12.setPersonalInfoSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures13 = this.l;
            if (deviceSupportedFeatures13 != null) {
                deviceSupportedFeatures13.setMaxDaysOfStepsDataOnBand(0);
            }
            DeviceSupportedFeatures deviceSupportedFeatures14 = this.l;
            if (deviceSupportedFeatures14 != null) {
                deviceSupportedFeatures14.setMaxDaysOfSleepDataOnBand(0);
            }
            DeviceSupportedFeatures deviceSupportedFeatures15 = this.l;
            if (deviceSupportedFeatures15 != null) {
                deviceSupportedFeatures15.setMaxDaysOfHeartRateDataOnBand(0);
            }
        }
        DeviceSupportedFeatures deviceSupportedFeatures16 = this.l;
        Intrinsics.checkNotNull(deviceSupportedFeatures16);
        return deviceSupportedFeatures16;
    }

    @Nullable
    public final FingerOximeter getMFingerOximeter() {
        return this.i;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public String getMacAddress() {
        Object preference = Spo2PreferenceManager.getPreference(this.f3204a, Spo2PrefConstants.BLE_DEVICE_ADDRESS.name(), "");
        Intrinsics.checkNotNullExpressionValue(preference, "getPreference(\n         …\n            \"\"\n        )");
        return (String) preference;
    }

    @Nullable
    public final Handler getSyncTimeOutHandler() {
        return this.n;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        String name = PC60FBleService.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "PC60FBleService::class.java.name");
        return BleApiUtils.isServiceRunning(name, this.f3204a);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Subscribe
    public final void onConnectionStateChanged(@NotNull CloveBleStatePC60F state) {
        Intrinsics.checkNotNullParameter(state, "state");
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        CloveBleStatePC60F.BleState bleState = state.getmState();
        int i = bleState == null ? -1 : WhenMappings.$EnumSwitchMapping$0[bleState.ordinal()];
        if (i == 1) {
            connectionStatus = ConnectionStatus.CONNECTED;
        } else if (i == 2) {
            connectionStatus = ConnectionStatus.CONNECTING;
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.g;
        if (mutableLiveData != null) {
            mutableLiveData.setValue(connectionStatus);
        }
        MutableLiveData<ConnectionStatus> mutableLiveData2 = this.g;
        if (mutableLiveData2 != null) {
            mutableLiveData2.postValue(connectionStatus);
        }
        ConnectionResultListener connectionResultListener = this.e;
        if (connectionResultListener != null) {
            connectionResultListener.onConnectionResponse(connectionStatus);
        }
    }

    public int read(@Nullable byte[] bArr) {
        PC60FBleService pC60FBleService = this.f;
        if (pC60FBleService != null) {
            return pC60FBleService.read(bArr);
        }
        return 0;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<ConnectionStatus> registerConnectionStatus() {
        if (this.g == null) {
            this.g = new MutableLiveData<>();
        }
        ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED;
        PC60FBleService pC60FBleService = this.f;
        if (pC60FBleService != null) {
            if (pC60FBleService.getConnectionState() == CloveBleStatePC60F.BleState.CONNECTED) {
                connectionStatus = ConnectionStatus.CONNECTED;
            } else {
                PC60FBleService pC60FBleService2 = this.f;
                if ((pC60FBleService2 != null ? pC60FBleService2.getConnectionState() : null) == CloveBleStatePC60F.BleState.CONNECTING) {
                    connectionStatus = ConnectionStatus.CONNECTING;
                } else {
                    PC60FBleService pC60FBleService3 = this.f;
                    if ((pC60FBleService3 != null ? pC60FBleService3.getConnectionState() : null) == CloveBleStatePC60F.BleState.SCANNING) {
                        connectionStatus = ConnectionStatus.SCANNING;
                    } else {
                        PC60FBleService pC60FBleService4 = this.f;
                        if ((pC60FBleService4 != null ? pC60FBleService4.getConnectionState() : null) == CloveBleStatePC60F.BleState.DISCOVERING_SERVICES) {
                            connectionStatus = ConnectionStatus.DISCOVERING_SERVICES;
                        }
                    }
                }
            }
        }
        MutableLiveData<ConnectionStatus> mutableLiveData = this.g;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(connectionStatus);
        }
        MutableLiveData<ConnectionStatus> mutableLiveData2 = this.g;
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
        if (this.k == null) {
            this.k = new MutableLiveData<>();
        }
        return this.k;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<Spo2WaveResponse> registerLiveSpo2WaveData() {
        if (this.j == null) {
            this.j = new MutableLiveData<>();
        }
        return this.j;
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
        PC60FBleService pC60FBleService = this.f;
        if (pC60FBleService != null) {
            pC60FBleService.restartService();
            clearParameters();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.m6
            @Override // java.lang.Runnable
            public final void run() {
                PC60FBleApiImpl.a(PC60FBleApiImpl.this);
            }
        }, 5000L);
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void scan(@NotNull ScanDeviceRequest scanDeviceReq, @NotNull ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(scanDeviceReq, "scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "listener");
        clearCommandQueue();
        a(scanDeviceReq, listener);
    }

    public void send(@Nullable byte[] bArr) {
        PC60FBleService pC60FBleService = this.f;
        if (pC60FBleService != null) {
            pC60FBleService.writeValue(bArr);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setBusyStatus(@NotNull BusyStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setConnectionChangeListener(@NotNull ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
    }

    public final void setConnectionResultListener$bleabstract_release(@Nullable ConnectionResultListener connectionResultListener) {
        this.e = connectionResultListener;
    }

    public final void setConnectionStateLiveData(@Nullable MutableLiveData<ConnectionStatus> mutableLiveData) {
        this.g = mutableLiveData;
    }

    public final void setMFingerOximeter(@Nullable FingerOximeter fingerOximeter) {
        this.i = fingerOximeter;
    }

    public final void setSyncTimeOutHandler(@Nullable Handler handler) {
        this.n = handler;
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.i == null) {
            this.i = new FingerOximeter(this, this, new PC60FBleApiImpl$getFingerOximeter$1(this, listener, request, null));
        }
        FingerOximeter fingerOximeter = this.i;
        if (request instanceof Spo2DataRequest) {
            Spo2DataRequest spo2DataRequest = (Spo2DataRequest) request;
            if (spo2DataRequest.isWaveEnabled()) {
                if (fingerOximeter != null) {
                    fingerOximeter.SetWaveAction(true);
                }
            } else if (fingerOximeter != null) {
                fingerOximeter.SetWaveAction(false);
            }
            if (spo2DataRequest.isEnabled()) {
                LogHelper.d(this.b, "FingerOximeter: started", ModuleNames.BLEABSTRACT.getModuleName());
                if (fingerOximeter != null) {
                    fingerOximeter.Start();
                    return;
                }
                return;
            }
            LogHelper.d(this.b, "FingerOximeter: stopped", ModuleNames.BLEABSTRACT.getModuleName());
            if (fingerOximeter != null) {
                fingerOximeter.Stop();
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopScan() {
        DeviceScanManager.getInstance(this.f3204a).stopScan();
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopService() {
        clearCommandQueue();
        Spo2PreferenceManager.savePreference(this.f3204a, Spo2PrefConstants.BLE_DEVICE_ADDRESS.name(), "");
        Spo2PreferenceManager.savePreference(this.f3204a, Spo2PrefConstants.BLE_CONNECTION_TYPE.name(), ConnectionTypeSpo2.DONT_CONNECT_ON_DISCONNECT.name());
        PC60FBleService pC60FBleService = this.f;
        if (pC60FBleService != null) {
            Intrinsics.checkNotNull(pC60FBleService);
            pC60FBleService.stopService();
            clearParameters();
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void stopServiceAndRetainMacAddress() {
    }

    public final void unbindService() {
        PC60FBleApiImpl$serviceConnection$1 pC60FBleApiImpl$serviceConnection$1 = this.o;
        if (pC60FBleApiImpl$serviceConnection$1 != null) {
            this.f3204a.unbindService(pC60FBleApiImpl$serviceConnection$1);
        }
    }

    @Override // com.coveiot.android.bleabstract.api.BleApi
    public void unpairDevice() {
        clearCommandQueue();
        if (this.f != null) {
            new BleBaseRequest().setBleCommand(BleCommand.DISCONNECT);
            PC60FBleService pC60FBleService = this.f;
            if (pC60FBleService != null) {
                pC60FBleService.disconnectAndForget();
            }
            clearParameters();
        }
    }

    public static final void a(final PC60FBleApiImpl this$0, final ScanDeviceRequest scanDeviceReq, final ScanResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scanDeviceReq, "$scanDeviceReq");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        try {
            if (!DeviceScanManager.getInstance(this$0.f3204a).isScanningInProgress()) {
                if (BleUtils.isEmpty(scanDeviceReq.getScanFilter())) {
                    DeviceScanManager.getInstance(this$0.f3204a).scanAllDevices(this$0.f3204a, scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.PC60FBleApiImpl$scan_$2$1
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            PC60FBleApiImpl.access$scanResultRecieved(PC60FBleApiImpl.this, devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = PC60FBleApiImpl.this.getContext().getString(R.string.scan_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                            scanResultListener.onError(string);
                        }
                    });
                } else {
                    DeviceScanManager.getInstance(this$0.f3204a).scanDevicesWithFilter(this$0.f3204a, scanDeviceReq.getScanFilter(), scanDeviceReq.getScanDuration(), scanDeviceReq.isShouldProvideBatchResult(), new ScanResult() { // from class: com.coveiot.android.bleabstract.bleimpl.PC60FBleApiImpl$scan_$2$2
                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onDevicesFound(@NotNull List<? extends BleDevice> devices, boolean z) {
                            Intrinsics.checkNotNullParameter(devices, "devices");
                            PC60FBleApiImpl.access$scanResultRecieved(PC60FBleApiImpl.this, devices, z, scanDeviceReq, listener);
                        }

                        @Override // com.coveiot.sdk.ble.scan.ScanResult
                        public void onScanFailed(int i) {
                            ScanResultListener scanResultListener = listener;
                            String string = PC60FBleApiImpl.this.getContext().getString(R.string.scan_failed);
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

    public static final void a(PC60FBleApiImpl this$0, ConnectRequest request, ConnectionResultListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        PC60FBleService pC60FBleService = this$0.f;
        if (pC60FBleService == null) {
            if (pC60FBleService == null) {
                try {
                    Intent intent = new Intent(this$0.f3204a, PC60FBleService.class);
                    this$0.f3204a.bindService(intent, this$0.o, 1);
                    if (Build.VERSION.SDK_INT >= 26) {
                        this$0.f3204a.startForegroundService(intent);
                    } else {
                        this$0.f3204a.startService(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    BleApiUtils.checkExceptionAndShowNotification(e, this$0.f3204a);
                }
                listener.onError(new Error(Type.SERVICE_NOT_RUNNING, "Service not running, call connect again"));
                return;
            }
            listener.onError(new Error(Type.COMMAND_REQUEST_ERROR, "Request should not be null"));
            return;
        }
        Intrinsics.checkNotNull(pC60FBleService);
        CloveBleStatePC60F.BleState connectionState = pC60FBleService.getConnectionState();
        CloveBleStatePC60F.BleState bleState = CloveBleStatePC60F.BleState.CONNECTED;
        if (connectionState == bleState) {
            PC60FBleService pC60FBleService2 = this$0.f;
            Intrinsics.checkNotNull(pC60FBleService2);
            if (kotlin.text.m.equals((String) Spo2PreferenceManager.getPreference(pC60FBleService2.getApplicationContext(), Spo2PrefConstants.BLE_DEVICE_ADDRESS.name(), ""), request.getMacAddress(), true)) {
                listener.onConnectionResponse(ConnectionStatus.CONNECTED);
                return;
            }
        }
        PC60FBleService pC60FBleService3 = this$0.f;
        Intrinsics.checkNotNull(pC60FBleService3);
        if (pC60FBleService3.getConnectionState() == CloveBleStatePC60F.BleState.DISCONNECTED) {
            BleBaseRequest bleBaseRequest = new BleBaseRequest();
            bleBaseRequest.setBleCommand(BleCommand.CONNECT);
            bleBaseRequest.setResponseListener(listener);
            PC60FBleService pC60FBleService4 = this$0.f;
            Intrinsics.checkNotNull(pC60FBleService4);
            pC60FBleService4.connect(request.getMacAddress());
            return;
        }
        PC60FBleService pC60FBleService5 = this$0.f;
        Intrinsics.checkNotNull(pC60FBleService5);
        if (pC60FBleService5.getConnectionState() == bleState) {
            StringBuilder sb = new StringBuilder();
            sb.append("Connected to band ");
            PC60FBleService pC60FBleService6 = this$0.f;
            Intrinsics.checkNotNull(pC60FBleService6);
            sb.append(pC60FBleService6.mBluetoothDevice.getAddress());
            listener.onError(new Error(Type.BAND_ALREADY_CONNECTED, sb.toString()));
            return;
        }
        PC60FBleService pC60FBleService7 = this$0.f;
        Intrinsics.checkNotNull(pC60FBleService7);
        if (pC60FBleService7.getConnectionState() == CloveBleStatePC60F.BleState.CONNECTING) {
            PC60FBleService pC60FBleService8 = this$0.f;
            Intrinsics.checkNotNull(pC60FBleService8);
            if (pC60FBleService8.mBluetoothDevice != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Connection in progress ");
                PC60FBleService pC60FBleService9 = this$0.f;
                Intrinsics.checkNotNull(pC60FBleService9);
                sb2.append(pC60FBleService9.mBluetoothDevice.getAddress());
                listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, sb2.toString()));
                return;
            }
            PC60FBleService pC60FBleService10 = this$0.f;
            Intrinsics.checkNotNull(pC60FBleService10);
            listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, pC60FBleService10.getConnectionState().getStateAsString()));
            return;
        }
        PC60FBleService pC60FBleService11 = this$0.f;
        Intrinsics.checkNotNull(pC60FBleService11);
        listener.onError(new Error(Type.CONNECTION_IN_PROGRESS, pC60FBleService11.getConnectionState().getStateAsString()));
    }

    public static final void a(PC60FBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0.f3204a, PC60FBleService.class);
        this$0.f3204a.bindService(intent, this$0.o, 1);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                this$0.f3204a.startForegroundService(intent);
            } else {
                this$0.f3204a.startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this$0.f3204a);
        }
    }

    public final boolean a() {
        Object systemService = this.f3204a.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (Intrinsics.areEqual(PC60FBleService.class.getName(), runningServiceInfo.service.getClassName()) && Intrinsics.areEqual(this.f3204a.getPackageName(), runningServiceInfo.service.getPackageName())) {
                z = true;
            }
        }
        return z;
    }
}
