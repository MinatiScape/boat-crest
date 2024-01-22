package com.coveiot.android.leonardo.bt3call.viewmodel;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.SendUnbindBTCallIRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener;
import com.coveiot.android.theme.model.OnWatchBT3EnabledEvent;
import com.coveiot.android.theme.utils.BT3Utils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.LogHelper;
import java.lang.reflect.Method;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BT3CallViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4659a;
    public final String b;
    @Nullable
    public IBT3ConnectionChangeListener c;

    @DebugMetadata(c = "com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel$connectToBT3Watch$2", f = "BT3CallViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
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
                IBT3ConnectionChangeListener bT3ConnectionChangeListener = BT3CallViewModel.this.getBT3ConnectionChangeListener();
                if (bT3ConnectionChangeListener != null) {
                    bT3ConnectionChangeListener.onInitialCheckFailed(BT3CallViewModel.this.getContext().getString(R.string.band_disconnected));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel$disconnectToBT3Watch$2", f = "BT3CallViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                IBT3ConnectionChangeListener bT3ConnectionChangeListener = BT3CallViewModel.this.getBT3ConnectionChangeListener();
                if (bT3ConnectionChangeListener != null) {
                    bT3ConnectionChangeListener.onBT3Disconnected();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel$resetBT3BondedStatusInCaseChangedFromPhoneSettings$1$1$1", f = "BT3CallViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $bonded;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(boolean z, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$bonded = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$bonded, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoveEventBusManager.getInstance().getEventBus().post(new OnWatchBT3EnabledEvent(this.$bonded));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public BT3CallViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4659a = context;
        this.b = BT3CallViewModel.class.getSimpleName();
    }

    public static /* synthetic */ void connectToBT3Watch$default(BT3CallViewModel bT3CallViewModel, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        bT3CallViewModel.connectToBT3Watch(z, z2, z3);
    }

    public static /* synthetic */ void createBT3Bonding$default(BT3CallViewModel bT3CallViewModel, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        bT3CallViewModel.createBT3Bonding(str, z, z2);
    }

    public final boolean a(BluetoothDevice bluetoothDevice) {
        Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("createBond", Integer.TYPE);
        Intrinsics.checkNotNullExpressionValue(declaredMethod, "BluetoothDevice::class.j…vaPrimitiveType\n        )");
        Object invoke = declaredMethod.invoke(bluetoothDevice, 1);
        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.Boolean");
        return ((Boolean) invoke).booleanValue();
    }

    public final void connectToBT3Watch(boolean z, boolean z2, boolean z3) {
        String str;
        LogHelper.d(this.b, "connectToBT3Watch");
        BleApi bleApi = BleApiManager.getInstance(this.f4659a).getBleApi();
        String str2 = null;
        if ((bleApi != null ? bleApi.getConnectionStatus() : null) != ConnectionStatus.CONNECTED) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
            return;
        }
        IBT3ConnectionChangeListener iBT3ConnectionChangeListener = this.c;
        if (iBT3ConnectionChangeListener != null) {
            iBT3ConnectionChangeListener.onBT3Connecting();
        }
        if (!z3) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new BT3CallViewModel$connectToBT3Watch$1(this, z, null), 2, null);
            return;
        }
        String macAddress = BleApiManager.getInstance(this.f4659a).getBleApi().getMacAddress();
        UserDataManager userDataManager = UserDataManager.getInstance(this.f4659a);
        if (macAddress != null) {
            str = macAddress.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toUpperCase()");
        } else {
            str = null;
        }
        userDataManager.saveConnectedBTCallDeviceMac(str);
        UserDataManager.getInstance(this.f4659a).saveConnectedBTCallDeviceName(SessionManager.getInstance(this.f4659a).getConnectedDeviceName());
        if (macAddress != null) {
            str2 = macAddress.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toUpperCase()");
        }
        createBT3Bonding(str2, z, z2);
    }

    public final void createBT3Bonding(@Nullable String str, boolean z, boolean z2) {
        if (str != null) {
            BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
            if (device != null) {
                Intrinsics.checkNotNullExpressionValue(device, "device");
                try {
                    if (ContextCompat.checkSelfPermission(this.f4659a, "android.permission.BLUETOOTH_CONNECT") != 0 && Build.VERSION.SDK_INT >= 31) {
                        Unit unit = Unit.INSTANCE;
                    } else if (device.getBondState() == 12) {
                        String str2 = this.b;
                        LogHelper.d(str2, "BT3 device already paired! for macAddress " + str);
                        Unit unit2 = Unit.INSTANCE;
                    } else if (!z2) {
                        device.createBond();
                    } else {
                        try {
                            a(device);
                        } catch (Exception e) {
                            e.printStackTrace();
                            createBT3Bonding(str, z, false);
                            Unit unit3 = Unit.INSTANCE;
                        }
                    }
                    return;
                } catch (SecurityException e2) {
                    e2.printStackTrace();
                    Unit unit4 = Unit.INSTANCE;
                    return;
                }
            }
            String str3 = this.b;
            LogHelper.d(str3, "BT3 device not found! for macAddress " + str);
            return;
        }
        LogHelper.d(this.b, "BT3 device macaddress is null!");
    }

    public final void disconnectToBT3Watch() {
        String macAddress;
        LogHelper.d(this.b, "disconnectToBT3Watch");
        if (BleApiManager.getInstance(this.f4659a).getBleApi() != null) {
            if (!BleApiManager.getInstance(this.f4659a).getBleApi().getDeviceSupportedFeatures().isOneClickToConnectSupported() && !DeviceUtils.Companion.isTouchELXDevice(this.f4659a)) {
                BleApiManager.getInstance(this.f4659a).getBleApi().setUserSettings(new SendUnbindBTCallIRequest(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel$disconnectToBT3Watch$3

                    @DebugMetadata(c = "com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel$disconnectToBT3Watch$3$onSettingsError$1", f = "BT3CallViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* loaded from: classes2.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public int label;
                        public final /* synthetic */ BT3CallViewModel this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(BT3CallViewModel bT3CallViewModel, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.this$0 = bT3CallViewModel;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new a(this.this$0, continuation);
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
                                String modelNumber = DeviceUtils.Companion.getModelNumber(this.this$0.getContext());
                                if (modelNumber == null) {
                                    modelNumber = "BT";
                                }
                                IBT3ConnectionChangeListener bT3ConnectionChangeListener = this.this$0.getBT3ConnectionChangeListener();
                                if (bT3ConnectionChangeListener != null) {
                                    bT3ConnectionChangeListener.onBT3ConnectionFailure(this.this$0.getContext().getString(R.string.xtendpro3_disconnection_failed, modelNumber));
                                }
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @DebugMetadata(c = "com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel$disconnectToBT3Watch$3$onSettingsResponse$2", f = "BT3CallViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* loaded from: classes2.dex */
                    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public int label;
                        public final /* synthetic */ BT3CallViewModel this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public b(BT3CallViewModel bT3CallViewModel, Continuation<? super b> continuation) {
                            super(2, continuation);
                            this.this$0 = bT3CallViewModel;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new b(this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        @Nullable
                        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                            if (this.label == 0) {
                                ResultKt.throwOnFailure(obj);
                                IBT3ConnectionChangeListener bT3ConnectionChangeListener = this.this$0.getBT3ConnectionChangeListener();
                                if (bT3ConnectionChangeListener != null) {
                                    bT3ConnectionChangeListener.onBT3Disconnected();
                                }
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        String tag = BT3CallViewModel.this.getTAG();
                        LogHelper.e(tag, "BT Call disconnect error " + error.getErrorMsg());
                        e.e(ViewModelKt.getViewModelScope(BT3CallViewModel.this), Dispatchers.getMain(), null, new a(BT3CallViewModel.this, null), 2, null);
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        LogHelper.d(BT3CallViewModel.this.getTAG(), "BT3 disconnect sent.");
                        String connectedBTCallDeviceMac = UserDataManager.getInstance(BT3CallViewModel.this.getContext()).getConnectedBTCallDeviceMac();
                        if (connectedBTCallDeviceMac != null) {
                            BT3Utils.unpairDevice(connectedBTCallDeviceMac);
                        }
                        e.e(ViewModelKt.getViewModelScope(BT3CallViewModel.this), Dispatchers.getMain(), null, new b(BT3CallViewModel.this, null), 2, null);
                    }
                });
                return;
            }
            if (DeviceUtils.Companion.isTouchELXDevice(this.f4659a)) {
                macAddress = PreferenceManagerAbstract.getInstance(this.f4659a).getConnectedDeviceBT3MacAddress();
            } else {
                macAddress = BleApiManager.getInstance(this.f4659a).getBleApi().getMacAddress();
            }
            if (macAddress != null) {
                BT3Utils.unpairDevice(macAddress);
            }
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
        }
    }

    @Nullable
    public final IBT3ConnectionChangeListener getBT3ConnectionChangeListener() {
        return this.c;
    }

    @NotNull
    public final Context getContext() {
        return this.f4659a;
    }

    public final String getTAG() {
        return this.b;
    }

    public final void resetBT3BondedStatusInCaseChangedFromPhoneSettings() {
        String connectedBTCallDeviceMac = UserDataManager.getInstance(this.f4659a).getConnectedBTCallDeviceMac();
        if (connectedBTCallDeviceMac != null) {
            boolean z = true;
            if (connectedBTCallDeviceMac.length() > 0) {
                try {
                    BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(connectedBTCallDeviceMac);
                    if (device != null) {
                        Intrinsics.checkNotNullExpressionValue(device, "device");
                        if (device.getBondState() != 11) {
                            if (device.getBondState() != 12) {
                                z = false;
                            }
                            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new c(z, null), 2, null);
                        } else {
                            LogHelper.d(this.b, "BT3 device pairing!");
                        }
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (SecurityException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public final void setBT3ConnectionChangeListener(@Nullable IBT3ConnectionChangeListener iBT3ConnectionChangeListener) {
        this.c = iBT3ConnectionChangeListener;
    }
}
