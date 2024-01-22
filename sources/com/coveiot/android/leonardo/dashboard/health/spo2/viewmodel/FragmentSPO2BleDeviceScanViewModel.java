package com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel;

import android.content.Context;
import android.os.CountDownTimer;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.models.BleDevice;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.SPO2BluetoothDeviceInfo;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentSPO2BleDeviceScanViewModel extends ViewModel {
    public static final long MAX_SCAN_TIME = 150000;
    public static final long SECONDARY_SCAN_TIME = 10000;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4749a;
    @NotNull
    public MutableLiveData<ArrayList<BleDevice>> b;
    @NotNull
    public MutableLiveData<Boolean> c;
    @NotNull
    public MutableLiveData<String> d;
    @Nullable
    public CountDownTimer e;
    @NotNull
    public MutableLiveData<String> f;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String g = FragmentSPO2BleDeviceScanViewModel.class.getSimpleName();

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return FragmentSPO2BleDeviceScanViewModel.g;
        }
    }

    public FragmentSPO2BleDeviceScanViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4749a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
    }

    public final void a(final long j) {
        CountDownTimer countDownTimer = new CountDownTimer(j) { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleDeviceScanViewModel$startTimer$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
                long j3 = j2 / 1000;
                long j4 = 60;
                long j5 = j3 / j4;
                long j6 = j3 % j4;
                String str = "" + j6;
                if (j6 < 10) {
                    StringBuilder sb = new StringBuilder();
                    sb.append('0');
                    sb.append(j6);
                    str = sb.toString();
                }
                this.getTimerText().postValue(j5 + ':' + str + ' ' + this.getContext().getString(R.string.min));
            }
        };
        this.e = countDownTimer;
        countDownTimer.start();
    }

    public final void connect(@NotNull ConnectRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        BleApiManager.getInstance(this.f4749a).getSecondaryBleImpl(DeviceType.spo2).connect(request, new ConnectionResultListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleDeviceScanViewModel$connect$1
            @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
            public void onConnectionResponse(@NotNull ConnectionStatus status) {
                Intrinsics.checkNotNullParameter(status, "status");
                FragmentSPO2BleDeviceScanViewModel.this.getConnectionStatus().postValue(status.name());
            }

            @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
            public void onError(@NotNull Error error) {
                Intrinsics.checkNotNullParameter(error, "error");
                String tag = FragmentSPO2BleDeviceScanViewModel.Companion.getTAG();
                LogsHelper.d(tag, "Connection error: " + error);
                FragmentSPO2BleDeviceScanViewModel.this.getConnectionStatus().postValue(error.getType().name());
            }
        });
    }

    @NotNull
    public final MutableLiveData<ArrayList<BleDevice>> getBleDevices() {
        return this.b;
    }

    @NotNull
    public final MutableLiveData<String> getConnectionStatus() {
        return this.f;
    }

    @NotNull
    public final Context getContext() {
        return this.f4749a;
    }

    @Nullable
    public final CountDownTimer getCountDownTimer() {
        return this.e;
    }

    @NotNull
    public final MutableLiveData<String> getTimerText() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Boolean> isScanning() {
        return this.c;
    }

    public final void scanAndConnectPreviousDevice(@NotNull final SPO2BluetoothDeviceInfo deviceInfo) {
        Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
        ScanDeviceRequest.Builder scanDuration = new ScanDeviceRequest.Builder().setScanDuration(10000L);
        BleDevice bleDevice = deviceInfo.getBleDevice();
        Intrinsics.checkNotNull(bleDevice);
        ScanDeviceRequest scanReq = scanDuration.setScanFilter(new String[]{bleDevice.getmDevice().getName()}).setShouldProvideBatchResult(true).build();
        BleApi secondaryBleImpl = BleApiManager.getInstance(this.f4749a).getSecondaryBleImpl(DeviceType.spo2);
        Intrinsics.checkNotNullExpressionValue(scanReq, "scanReq");
        secondaryBleImpl.scan(scanReq, new ScanResultListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleDeviceScanViewModel$scanAndConnectPreviousDevice$1
            @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
            public void onError(@NotNull String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                String tag = FragmentSPO2BleDeviceScanViewModel.Companion.getTAG();
                LogHelper.d(tag, "Seconday Scan error: " + error);
                FragmentSPO2BleDeviceScanViewModel.this.getConnectionStatus().postValue(Type.DEVICE_NOT_CONNECTED.name());
            }

            @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
            public void onResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                String tag = FragmentSPO2BleDeviceScanViewModel.Companion.getTAG();
                LogHelper.d(tag, "Seconday Scan response: " + response);
                if (response instanceof ScanDeviceResponse) {
                    FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel = FragmentSPO2BleDeviceScanViewModel.this;
                    BleDevice bleDevice2 = deviceInfo.getBleDevice();
                    Intrinsics.checkNotNull(bleDevice2);
                    String address = bleDevice2.getmDevice().getAddress();
                    Intrinsics.checkNotNullExpressionValue(address, "deviceInfo.bleDevice!!.getmDevice().address");
                    fragmentSPO2BleDeviceScanViewModel.connect(new ConnectRequest(address));
                }
            }
        });
    }

    public final void setBleDevices(@NotNull MutableLiveData<ArrayList<BleDevice>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setConnectionStatus(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void setCountDownTimer(@Nullable CountDownTimer countDownTimer) {
        this.e = countDownTimer;
    }

    public final void setScanning(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setTimerText(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void startScan() {
        BleApiManager bleApiManager = BleApiManager.getInstance(this.f4749a);
        DeviceType deviceType = DeviceType.spo2;
        bleApiManager.getSecondaryBleImpl(deviceType).unpairDevice();
        MutableLiveData<Boolean> mutableLiveData = this.c;
        Boolean bool = Boolean.TRUE;
        mutableLiveData.setValue(bool);
        this.c.postValue(bool);
        ScanDeviceRequest scanReq = new ScanDeviceRequest.Builder().setScanDuration(MAX_SCAN_TIME).setScanFilter(new String[]{AppConstants.SCAN_FILTER_DEVICE_SPO2.getValue()}).setShouldProvideBatchResult(false).build();
        a(MAX_SCAN_TIME);
        BleApi secondaryBleImpl = BleApiManager.getInstance(this.f4749a).getSecondaryBleImpl(deviceType);
        Intrinsics.checkNotNullExpressionValue(scanReq, "scanReq");
        secondaryBleImpl.scan(scanReq, new ScanResultListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleDeviceScanViewModel$startScan$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleDeviceScanViewModel$startScan$1$onResponse$1", f = "FragmentSPO2BleDeviceScanViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes3.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ ScanDeviceResponse $scanDeviceResponse;
                public int label;
                public final /* synthetic */ FragmentSPO2BleDeviceScanViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel, ScanDeviceResponse scanDeviceResponse, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentSPO2BleDeviceScanViewModel;
                    this.$scanDeviceResponse = scanDeviceResponse;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$scanDeviceResponse, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableLiveData<ArrayList<BleDevice>> bleDevices = this.this$0.getBleDevices();
                        List<BleDevice> bluetoothDevices = this.$scanDeviceResponse.getBluetoothDevices();
                        Intrinsics.checkNotNull(bluetoothDevices, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.bleabstract.models.BleDevice>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.bleabstract.models.BleDevice> }");
                        bleDevices.setValue((ArrayList) bluetoothDevices);
                        this.this$0.getBleDevices().postValue(this.this$0.getBleDevices().getValue());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
            public void onError(@NotNull String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                String tag = FragmentSPO2BleDeviceScanViewModel.Companion.getTAG();
                LogHelper.d(tag, "Scan error: " + error);
                FragmentSPO2BleDeviceScanViewModel.this.stopTimer();
                MutableLiveData<Boolean> isScanning = FragmentSPO2BleDeviceScanViewModel.this.isScanning();
                Boolean bool2 = Boolean.FALSE;
                isScanning.setValue(bool2);
                FragmentSPO2BleDeviceScanViewModel.this.isScanning().postValue(bool2);
            }

            @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
            public void onResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response instanceof ScanDeviceResponse) {
                    e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(FragmentSPO2BleDeviceScanViewModel.this, (ScanDeviceResponse) response, null), 2, null);
                    Context context = FragmentSPO2BleDeviceScanViewModel.this.getContext();
                    Intrinsics.checkNotNull(context);
                    if (DeviceScanManager.getInstance(context).isScanningInProgress()) {
                        return;
                    }
                    MutableLiveData<Boolean> isScanning = FragmentSPO2BleDeviceScanViewModel.this.isScanning();
                    Boolean bool2 = Boolean.FALSE;
                    isScanning.setValue(bool2);
                    FragmentSPO2BleDeviceScanViewModel.this.isScanning().postValue(bool2);
                }
            }
        });
    }

    public final void stopScan() {
        BleApiManager.getInstance(this.f4749a).getSecondaryBleImpl(DeviceType.spo2).stopScan();
        stopTimer();
        MutableLiveData<Boolean> mutableLiveData = this.c;
        Boolean bool = Boolean.FALSE;
        mutableLiveData.setValue(bool);
        this.c.postValue(bool);
    }

    public final void stopTimer() {
        CountDownTimer countDownTimer = this.e;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
