package com.coveiot.android.leonardo.onboarding.paring.viewmodel;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.models.BleDevice;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentDeviceListingViewModelCompanionDM extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5257a;
    @NotNull
    public MutableLiveData<ArrayList<BleDevice>> b;
    @NotNull
    public MutableLiveData<Boolean> c;
    @NotNull
    public MutableLiveData<Boolean> d;

    @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentDeviceListingViewModelCompanionDM$sortDevices$2", f = "FragmentDeviceListingViewModelCompanionDM.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<BleDevice>>, Object> {
        public final /* synthetic */ ScanDeviceResponse $scanDeviceResponse;
        public int label;
        public final /* synthetic */ FragmentDeviceListingViewModelCompanionDM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ScanDeviceResponse scanDeviceResponse, FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$scanDeviceResponse = scanDeviceResponse;
            this.this$0 = fragmentDeviceListingViewModelCompanionDM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$scanDeviceResponse, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<BleDevice>> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ArrayList arrayList = new ArrayList();
                List<BleDevice> bluetoothDevices = this.$scanDeviceResponse.getBluetoothDevices();
                Intrinsics.checkNotNull(bluetoothDevices, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.bleabstract.models.BleDevice>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.bleabstract.models.BleDevice> }");
                Iterator it = ((ArrayList) bluetoothDevices).iterator();
                while (it.hasNext()) {
                    BleDevice bleDevice = (BleDevice) it.next();
                    ContextCompat.checkSelfPermission(this.this$0.getContext(), "android.permission.BLUETOOTH_CONNECT");
                    BluetoothDevice bluetoothDevice = bleDevice.getmDevice();
                    if ((bluetoothDevice != null ? bluetoothDevice.getName() : null) != null) {
                        BluetoothDevice bluetoothDevice2 = bleDevice.getmDevice();
                        String name = bluetoothDevice2 != null ? bluetoothDevice2.getName() : null;
                        Intrinsics.checkNotNull(name);
                        String lowerCase = name.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                        String lowerCase2 = "COVE_V2".toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null)) {
                            arrayList.add(0, bleDevice);
                        }
                    }
                    arrayList.add(bleDevice);
                }
                return arrayList;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public FragmentDeviceListingViewModelCompanionDM(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5257a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
    }

    public final Object a(ScanDeviceResponse scanDeviceResponse, Continuation<? super ArrayList<BleDevice>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new a(scanDeviceResponse, this, null), continuation);
    }

    @NotNull
    public final MutableLiveData<ArrayList<BleDevice>> getBleDevices() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f5257a;
    }

    @NotNull
    public final MutableLiveData<Boolean> getNoDeviceFound() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Boolean> isScanning() {
        return this.c;
    }

    public final void setBleDevices(@NotNull MutableLiveData<ArrayList<BleDevice>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setNoDeviceFound(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setScanning(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void startScan(@NotNull Activity activity, int i, boolean z, @Nullable ArrayList<String> arrayList) {
        ScanDeviceRequest build;
        Intrinsics.checkNotNullParameter(activity, "activity");
        MutableLiveData<Boolean> mutableLiveData = this.c;
        Boolean bool = Boolean.TRUE;
        mutableLiveData.setValue(bool);
        this.c.postValue(bool);
        this.d.postValue(Boolean.FALSE);
        if (SessionManager.getInstance(this.f5257a).isScanAllDeviceChoosen()) {
            build = new ScanDeviceRequest.Builder().setScanDuration(15000L).setActivity(activity).setShouldProvideBatchResult(false).setSingleDevice(z).setRequestId(i).build();
        } else {
            build = new ScanDeviceRequest.Builder().setScanDuration(15000L).setScanFilter(arrayList != null ? (String[]) arrayList.toArray(new String[0]) : null).setShouldProvideBatchResult(false).setActivity(activity).setSingleDevice(z).setRequestId(i).build();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            startTimer();
        }
        BleApi bleApi = BleApiManager.getInstance(this.f5257a).getBleApi();
        if (bleApi != null) {
            bleApi.scan(build, new ScanResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentDeviceListingViewModelCompanionDM$startScan$1

                @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentDeviceListingViewModelCompanionDM$startScan$1$onResponse$1", f = "FragmentDeviceListingViewModelCompanionDM.kt", i = {}, l = {105}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ScanDeviceResponse $scanDeviceResponse;
                    public int label;
                    public final /* synthetic */ FragmentDeviceListingViewModelCompanionDM this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM, ScanDeviceResponse scanDeviceResponse, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = fragmentDeviceListingViewModelCompanionDM;
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
                        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM = this.this$0;
                            ScanDeviceResponse scanDeviceResponse = this.$scanDeviceResponse;
                            this.label = 1;
                            obj = fragmentDeviceListingViewModelCompanionDM.a(scanDeviceResponse, this);
                            if (obj == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        this.this$0.getBleDevices().setValue((ArrayList) obj);
                        this.this$0.getBleDevices().postValue(this.this$0.getBleDevices().getValue());
                        return Unit.INSTANCE;
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
                public void onError(@NotNull String error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    MutableLiveData<Boolean> isScanning = FragmentDeviceListingViewModelCompanionDM.this.isScanning();
                    Boolean bool2 = Boolean.FALSE;
                    isScanning.setValue(bool2);
                    FragmentDeviceListingViewModelCompanionDM.this.isScanning().postValue(bool2);
                }

                @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
                public void onResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response instanceof ScanDeviceResponse) {
                        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(FragmentDeviceListingViewModelCompanionDM.this, (ScanDeviceResponse) response, null), 2, null);
                        Context context = FragmentDeviceListingViewModelCompanionDM.this.getContext();
                        Intrinsics.checkNotNull(context);
                        if (DeviceScanManager.getInstance(context).isScanningInProgress()) {
                            return;
                        }
                        FragmentDeviceListingViewModelCompanionDM.this.getNoDeviceFound().postValue(Boolean.FALSE);
                    }
                }
            });
        }
    }

    public final void startTimer() {
        new CountDownTimer() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentDeviceListingViewModelCompanionDM$startTimer$timer$1
            {
                super(20000L, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                MutableLiveData<Boolean> isScanning = FragmentDeviceListingViewModelCompanionDM.this.isScanning();
                Boolean bool = Boolean.FALSE;
                isScanning.setValue(bool);
                FragmentDeviceListingViewModelCompanionDM.this.isScanning().postValue(bool);
                FragmentDeviceListingViewModelCompanionDM.this.getNoDeviceFound().postValue(Boolean.TRUE);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }
        }.start();
    }

    public final void stopScan() {
        BleApiManager.getInstance(this.f5257a).getBleApi().stopScan();
    }
}
