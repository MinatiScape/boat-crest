package com.coveiot.android.leonardo.onboarding.paring.viewmodel;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.models.BleDevice;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentScanningDeviceViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5262a;
    @NotNull
    public MutableLiveData<Boolean> b;
    @NotNull
    public final String c;
    public ViewModelListener viewModelListener;

    public FragmentScanningDeviceViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5262a = context;
        this.b = new MutableLiveData<>();
        this.c = "FragmentScanningDevice";
    }

    public final void a(BluetoothDevice bluetoothDevice) {
        Object systemService = this.f5262a.getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothAdapter adapter = ((BluetoothManager) systemService).getAdapter();
        Intrinsics.checkNotNullExpressionValue(adapter, "context.getSystemService…BluetoothManager).adapter");
        BleDevice bleDevice = new BleDevice(adapter.getRemoteDevice(bluetoothDevice.getAddress()));
        Context context = this.f5262a;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ActivityPairing.connectTo$default((ActivityPairing) context, bleDevice, null, 2, null);
        Context context2 = this.f5262a;
        Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ((ActivityPairing) context2).dismissProgress();
    }

    @NotNull
    public final Context getContext() {
        return this.f5262a;
    }

    @NotNull
    public final ViewModelListener getViewModelListener() {
        ViewModelListener viewModelListener = this.viewModelListener;
        if (viewModelListener != null) {
            return viewModelListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelListener");
        return null;
    }

    @NotNull
    public final MutableLiveData<Boolean> isScanning() {
        return this.b;
    }

    public final void setScanning(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setViewModelListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.viewModelListener = viewModelListener;
    }

    public final void startAssociationForSingleDevice(@NotNull String deviceName, @NotNull Activity activity, int i, boolean z) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public final void startScan(@Nullable String str, @NotNull FragmentActivity activity, int i, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        MutableLiveData<Boolean> mutableLiveData = this.b;
        Boolean bool = Boolean.TRUE;
        mutableLiveData.setValue(bool);
        this.b.postValue(bool);
        Intrinsics.checkNotNull(str);
        String lowerCase = str.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        String lowerCase2 = AppConstants.SCAN_FILTER_DEVICE_O8_BA1V1.getValue().toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null)) {
            SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.v7_device));
            BleApiManager.getInstance(this.f5262a).init(DeviceType.v7);
            SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
            SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
        } else {
            String lowerCase3 = str.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
            String lowerCase4 = AppConstants.SCAN_FILTER_CZ1_COVE.getValue().toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) lowerCase4, false, 2, (Object) null)) {
                SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.cove_cz1));
                BleApiManager.getInstance(this.f5262a).init(DeviceType.CZ0);
                SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
            } else {
                String lowerCase5 = str.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase()");
                String lowerCase6 = AppConstants.SCAN_FILTER_WAVEPLUS.getValue().toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase6, "this as java.lang.String).toLowerCase()");
                if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase5, (CharSequence) lowerCase6, false, 2, (Object) null)) {
                    String lowerCase7 = str.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase7, "this as java.lang.String).toLowerCase()");
                    String lowerCase8 = AppConstants.SCAN_FILTER_XTENDSPORT.getValue().toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase8, "this as java.lang.String).toLowerCase()");
                    if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase7, (CharSequence) lowerCase8, false, 2, (Object) null)) {
                        String lowerCase9 = str.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase9, "this as java.lang.String).toLowerCase()");
                        String lowerCase10 = AppConstants.SCAN_FILTER_WAVEPRIME.getValue().toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase10, "this as java.lang.String).toLowerCase()");
                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase9, (CharSequence) lowerCase10, false, 2, (Object) null)) {
                            SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.cove_wave_prime));
                            BleApiManager.getInstance(this.f5262a).init(DeviceType.wavePrime);
                            SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                            SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                        } else {
                            String lowerCase11 = str.toLowerCase();
                            Intrinsics.checkNotNullExpressionValue(lowerCase11, "this as java.lang.String).toLowerCase()");
                            String lowerCase12 = AppConstants.SCAN_FILTER_WAVEELITE.getValue().toLowerCase();
                            Intrinsics.checkNotNullExpressionValue(lowerCase12, "this as java.lang.String).toLowerCase()");
                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase11, (CharSequence) lowerCase12, false, 2, (Object) null)) {
                                SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.cove_wave_elite));
                                BleApiManager.getInstance(this.f5262a).init(DeviceType.WAVE_ELITE);
                                SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                            } else {
                                String lowerCase13 = str.toLowerCase();
                                Intrinsics.checkNotNullExpressionValue(lowerCase13, "this as java.lang.String).toLowerCase()");
                                String lowerCase14 = AppConstants.SCAN_FILTER_DEVICE_1810G.getValue().toLowerCase();
                                Intrinsics.checkNotNullExpressionValue(lowerCase14, "this as java.lang.String).toLowerCase()");
                                if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase13, (CharSequence) lowerCase14, false, 2, (Object) null)) {
                                    String lowerCase15 = str.toLowerCase();
                                    Intrinsics.checkNotNullExpressionValue(lowerCase15, "this as java.lang.String).toLowerCase()");
                                    String lowerCase16 = AppConstants.SCAN_FILTER_DEVICE_COVE_BA.getValue().toLowerCase();
                                    Intrinsics.checkNotNullExpressionValue(lowerCase16, "this as java.lang.String).toLowerCase()");
                                    if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase15, (CharSequence) lowerCase16, false, 2, (Object) null)) {
                                        String lowerCase17 = str.toLowerCase();
                                        Intrinsics.checkNotNullExpressionValue(lowerCase17, "this as java.lang.String).toLowerCase()");
                                        String lowerCase18 = AppConstants.SCAN_FILTER_DEVICE_C_BA1010V11.getValue().toLowerCase();
                                        Intrinsics.checkNotNullExpressionValue(lowerCase18, "this as java.lang.String).toLowerCase()");
                                        if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase17, (CharSequence) lowerCase18, false, 2, (Object) null)) {
                                            String lowerCase19 = str.toLowerCase();
                                            Intrinsics.checkNotNullExpressionValue(lowerCase19, "this as java.lang.String).toLowerCase()");
                                            AppConstants appConstants = AppConstants.SCAN_FILTER_DEVICE_COVE_;
                                            String lowerCase20 = appConstants.getValue().toLowerCase();
                                            Intrinsics.checkNotNullExpressionValue(lowerCase20, "this as java.lang.String).toLowerCase()");
                                            if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase19, (CharSequence) lowerCase20, false, 2, (Object) null)) {
                                                String lowerCase21 = str.toLowerCase();
                                                Intrinsics.checkNotNullExpressionValue(lowerCase21, "this as java.lang.String).toLowerCase()");
                                                String lowerCase22 = AppConstants.SCAN_FILTER_DEVICE_CO19.getValue().toLowerCase();
                                                Intrinsics.checkNotNullExpressionValue(lowerCase22, "this as java.lang.String).toLowerCase()");
                                                if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase21, (CharSequence) lowerCase22, false, 2, (Object) null)) {
                                                    String lowerCase23 = str.toLowerCase();
                                                    Intrinsics.checkNotNullExpressionValue(lowerCase23, "this as java.lang.String).toLowerCase()");
                                                    String lowerCase24 = AppConstants.SCAN_FILTER_DEVICE_COVE9.getValue().toLowerCase();
                                                    Intrinsics.checkNotNullExpressionValue(lowerCase24, "this as java.lang.String).toLowerCase()");
                                                    if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase23, (CharSequence) lowerCase24, false, 2, (Object) null)) {
                                                        String lowerCase25 = str.toLowerCase();
                                                        Intrinsics.checkNotNullExpressionValue(lowerCase25, "this as java.lang.String).toLowerCase()");
                                                        String lowerCase26 = AppConstants.SCAN_FILTER_DEVICE_1963D.getValue().toLowerCase();
                                                        Intrinsics.checkNotNullExpressionValue(lowerCase26, "this as java.lang.String).toLowerCase()");
                                                        if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase25, (CharSequence) lowerCase26, false, 2, (Object) null)) {
                                                            String lowerCase27 = str.toLowerCase();
                                                            Intrinsics.checkNotNullExpressionValue(lowerCase27, "this as java.lang.String).toLowerCase()");
                                                            AppConstants appConstants2 = AppConstants.SCAN_FILTER_DEVICE_F_WA1002V11;
                                                            String lowerCase28 = appConstants2.getValue().toLowerCase();
                                                            Intrinsics.checkNotNullExpressionValue(lowerCase28, "this as java.lang.String).toLowerCase()");
                                                            if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase27, (CharSequence) lowerCase28, false, 2, (Object) null)) {
                                                                String lowerCase29 = str.toLowerCase();
                                                                Intrinsics.checkNotNullExpressionValue(lowerCase29, "this as java.lang.String).toLowerCase()");
                                                                String lowerCase30 = appConstants2.getValue().toLowerCase();
                                                                Intrinsics.checkNotNullExpressionValue(lowerCase30, "this as java.lang.String).toLowerCase()");
                                                                if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase29, (CharSequence) lowerCase30, false, 2, (Object) null)) {
                                                                    String lowerCase31 = str.toLowerCase();
                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase31, "this as java.lang.String).toLowerCase()");
                                                                    String lowerCase32 = AppConstants.SCAN_FILTER_DEVICE_TX_WA1V1.getValue().toLowerCase();
                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase32, "this as java.lang.String).toLowerCase()");
                                                                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase31, (CharSequence) lowerCase32, false, 2, (Object) null)) {
                                                                        SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.j1963yh_device));
                                                                        BleApiManager.getInstance(this.f5262a).init(DeviceType.jstyle1963YH);
                                                                        SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                        SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                    } else {
                                                                        String lowerCase33 = str.toLowerCase();
                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase33, "this as java.lang.String).toLowerCase()");
                                                                        String lowerCase34 = AppConstants.SCAN_FILTER_DEVICE.getValue().toLowerCase();
                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase34, "this as java.lang.String).toLowerCase()");
                                                                        if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase33, (CharSequence) lowerCase34, false, 2, (Object) null)) {
                                                                            String lowerCase35 = str.toLowerCase();
                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase35, "this as java.lang.String).toLowerCase()");
                                                                            String lowerCase36 = AppConstants.SCAN_FILTER_IDOC.getValue().toLowerCase();
                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase36, "this as java.lang.String).toLowerCase()");
                                                                            if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase35, (CharSequence) lowerCase36, false, 2, (Object) null)) {
                                                                                String lowerCase37 = str.toLowerCase();
                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase37, "this as java.lang.String).toLowerCase()");
                                                                                String lowerCase38 = AppConstants.SCAN_FILTER_DEVICE_C_.getValue().toLowerCase();
                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase38, "this as java.lang.String).toLowerCase()");
                                                                                if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase37, (CharSequence) lowerCase38, false, 2, (Object) null)) {
                                                                                    String lowerCase39 = str.toLowerCase();
                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase39, "this as java.lang.String).toLowerCase()");
                                                                                    String lowerCase40 = appConstants.getValue().toLowerCase();
                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase40, "this as java.lang.String).toLowerCase()");
                                                                                    if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase39, (CharSequence) lowerCase40, false, 2, (Object) null)) {
                                                                                        String lowerCase41 = str.toLowerCase();
                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase41, "this as java.lang.String).toLowerCase()");
                                                                                        String lowerCase42 = AppConstants.SCAN_FILTER_DEVICE_1790.getValue().toLowerCase();
                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase42, "this as java.lang.String).toLowerCase()");
                                                                                        if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase41, (CharSequence) lowerCase42, false, 2, (Object) null)) {
                                                                                            String lowerCase43 = str.toLowerCase();
                                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase43, "this as java.lang.String).toLowerCase()");
                                                                                            String lowerCase44 = AppConstants.SCAN_FILTER_DEVICE_ARM.getValue().toLowerCase();
                                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase44, "this as java.lang.String).toLowerCase()");
                                                                                            if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase43, (CharSequence) lowerCase44, false, 2, (Object) null)) {
                                                                                                String lowerCase45 = str.toLowerCase();
                                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase45, "this as java.lang.String).toLowerCase()");
                                                                                                String lowerCase46 = AppConstants.SCAN_FILTER_DEVICE_COVE_BA1009.getValue().toLowerCase();
                                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase46, "this as java.lang.String).toLowerCase()");
                                                                                                if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase45, (CharSequence) lowerCase46, false, 2, (Object) null)) {
                                                                                                    String lowerCase47 = str.toLowerCase();
                                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase47, "this as java.lang.String).toLowerCase()");
                                                                                                    String lowerCase48 = AppConstants.SCAN_FILTER_DEVICE_1860.getValue().toLowerCase();
                                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase48, "this as java.lang.String).toLowerCase()");
                                                                                                    if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase47, (CharSequence) lowerCase48, false, 2, (Object) null)) {
                                                                                                        String lowerCase49 = str.toLowerCase();
                                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase49, "this as java.lang.String).toLowerCase()");
                                                                                                        String lowerCase50 = AppConstants.SCAN_FILTER_DEVICE_WANDERER.getValue().toLowerCase();
                                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase50, "this as java.lang.String).toLowerCase()");
                                                                                                        if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase49, (CharSequence) lowerCase50, false, 2, (Object) null)) {
                                                                                                            String lowerCase51 = str.toLowerCase();
                                                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase51, "this as java.lang.String).toLowerCase()");
                                                                                                            String lowerCase52 = AppConstants.SCAN_FILTER_DEVICE_SMA_F2.getValue().toLowerCase();
                                                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase52, "this as java.lang.String).toLowerCase()");
                                                                                                            if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase51, (CharSequence) lowerCase52, false, 2, (Object) null)) {
                                                                                                                String lowerCase53 = str.toLowerCase();
                                                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase53, "this as java.lang.String).toLowerCase()");
                                                                                                                String lowerCase54 = AppConstants.SCAN_FILTER_DEVICE_SMA_MERCURY.getValue().toLowerCase();
                                                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase54, "this as java.lang.String).toLowerCase()");
                                                                                                                if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase53, (CharSequence) lowerCase54, false, 2, (Object) null)) {
                                                                                                                    String lowerCase55 = str.toLowerCase();
                                                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase55, "this as java.lang.String).toLowerCase()");
                                                                                                                    String lowerCase56 = AppConstants.SCAN_FILTER_DEVICE_MOYANG_Y20.getValue().toLowerCase();
                                                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase56, "this as java.lang.String).toLowerCase()");
                                                                                                                    if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase55, (CharSequence) lowerCase56, false, 2, (Object) null)) {
                                                                                                                        String lowerCase57 = str.toLowerCase();
                                                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase57, "this as java.lang.String).toLowerCase()");
                                                                                                                        String lowerCase58 = AppConstants.SCAN_FILTER_DEVICE_MOYANG_VERTEX.getValue().toLowerCase();
                                                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase58, "this as java.lang.String).toLowerCase()");
                                                                                                                        if (!StringsKt__StringsKt.contains$default((CharSequence) lowerCase57, (CharSequence) lowerCase58, false, 2, (Object) null)) {
                                                                                                                            String lowerCase59 = str.toLowerCase();
                                                                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase59, "this as java.lang.String).toLowerCase()");
                                                                                                                            String lowerCase60 = AppConstants.SCAN_FILTER_DEVICE_MATRIX_LA07.getValue().toLowerCase();
                                                                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase60, "this as java.lang.String).toLowerCase()");
                                                                                                                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase59, (CharSequence) lowerCase60, false, 2, (Object) null)) {
                                                                                                                                SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.matrix_device));
                                                                                                                                BleApiManager.getInstance(this.f5262a).init(DeviceType.matrix);
                                                                                                                                SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                                                                SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                                                            } else {
                                                                                                                                String lowerCase61 = str.toLowerCase();
                                                                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase61, "this as java.lang.String).toLowerCase()");
                                                                                                                                String lowerCase62 = AppConstants.SCAN_FILTER_DEVICE_MOYANG_WAVEFIT.getValue().toLowerCase();
                                                                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase62, "this as java.lang.String).toLowerCase()");
                                                                                                                                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase61, (CharSequence) lowerCase62, false, 2, (Object) null)) {
                                                                                                                                    SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.moyangygpf5_device));
                                                                                                                                    BleApiManager.getInstance(this.f5262a).init(DeviceType.crpGPF5);
                                                                                                                                    SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                                                                    SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                                                                } else {
                                                                                                                                    String lowerCase63 = str.toLowerCase();
                                                                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase63, "this as java.lang.String).toLowerCase()");
                                                                                                                                    String lowerCase64 = AppConstants.SCAN_FILTER_CA_0.getValue().toLowerCase();
                                                                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase64, "this as java.lang.String).toLowerCase()");
                                                                                                                                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase63, (CharSequence) lowerCase64, false, 2, (Object) null)) {
                                                                                                                                        SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.cove_ca0));
                                                                                                                                        BleApiManager.getInstance(this.f5262a).init(DeviceType.CA0);
                                                                                                                                        SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                                                                        SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                                                                    } else {
                                                                                                                                        String lowerCase65 = str.toLowerCase();
                                                                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase65, "this as java.lang.String).toLowerCase()");
                                                                                                                                        String lowerCase66 = AppConstants.SCAN_FILTER_STORMPRO.getValue().toLowerCase();
                                                                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase66, "this as java.lang.String).toLowerCase()");
                                                                                                                                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase65, (CharSequence) lowerCase66, false, 2, (Object) null)) {
                                                                                                                                            SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.cove_ca3));
                                                                                                                                            BleApiManager.getInstance(this.f5262a).init(DeviceType.CA3);
                                                                                                                                            SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                                                                            SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                                                                        } else {
                                                                                                                                            String lowerCase67 = str.toLowerCase();
                                                                                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase67, "this as java.lang.String).toLowerCase()");
                                                                                                                                            String lowerCase68 = AppConstants.SCAN_FILTER_XTENDPRO.getValue().toLowerCase();
                                                                                                                                            Intrinsics.checkNotNullExpressionValue(lowerCase68, "this as java.lang.String).toLowerCase()");
                                                                                                                                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase67, (CharSequence) lowerCase68, false, 2, (Object) null)) {
                                                                                                                                                SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.cove_ca3_bt));
                                                                                                                                                BleApiManager.getInstance(this.f5262a).init(DeviceType.CA3_BT_CALL);
                                                                                                                                                SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                                                                                SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                                                                            } else {
                                                                                                                                                String lowerCase69 = str.toLowerCase();
                                                                                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase69, "this as java.lang.String).toLowerCase()");
                                                                                                                                                String lowerCase70 = AppConstants.SCAN_FILTER_PRIMIA_VOICE.getValue().toLowerCase();
                                                                                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase70, "this as java.lang.String).toLowerCase()");
                                                                                                                                                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase69, (CharSequence) lowerCase70, false, 2, (Object) null)) {
                                                                                                                                                    SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.cy1_primia_voice));
                                                                                                                                                    BleApiManager.getInstance(this.f5262a).init(DeviceType.CY1_PRIMIA_VOICE);
                                                                                                                                                    SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                                                                                    SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                                                                                } else {
                                                                                                                                                    String lowerCase71 = str.toLowerCase();
                                                                                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase71, "this as java.lang.String).toLowerCase()");
                                                                                                                                                    String lowerCase72 = AppConstants.SCAN_FILTER_LOOP_CALL_PRO.getValue().toLowerCase();
                                                                                                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase72, "this as java.lang.String).toLowerCase()");
                                                                                                                                                    if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase71, (CharSequence) lowerCase72, false, 2, (Object) null)) {
                                                                                                                                                        SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.cy1_loop_call_pro));
                                                                                                                                                        BleApiManager.getInstance(this.f5262a).init(DeviceType.CY1_LOOP_CALL_PRO);
                                                                                                                                                        SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                                                                                        SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                                                                                    } else {
                                                                                                                                                        String lowerCase73 = str.toLowerCase();
                                                                                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase73, "this as java.lang.String).toLowerCase()");
                                                                                                                                                        String lowerCase74 = AppConstants.SCAN_FILTER_LOOP_CONNECT_PRO.getValue().toLowerCase();
                                                                                                                                                        Intrinsics.checkNotNullExpressionValue(lowerCase74, "this as java.lang.String).toLowerCase()");
                                                                                                                                                        if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase73, (CharSequence) lowerCase74, false, 2, (Object) null)) {
                                                                                                                                                            SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.cy1_loop_connect_pro));
                                                                                                                                                            BleApiManager.getInstance(this.f5262a).init(DeviceType.CY1_LOOP_CONNECT_PRO);
                                                                                                                                                            SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                                                                                            SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                                                                                        } else if (DeviceUtils.Companion.isIDODevice(this.f5262a)) {
                                                                                                                                                            Context context = this.f5262a;
                                                                                                                                                            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                                                                                                                                                            ((ActivityPairing) context).showProgress();
                                                                                                                                                            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{"="}, false, 0, 6, (Object) null).get(1));
                                                                                                                                                            Intrinsics.checkNotNull(remoteDevice);
                                                                                                                                                            a(remoteDevice);
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                    SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.moyangy20_device));
                                                                                                                    BleApiManager.getInstance(this.f5262a).init(DeviceType.kh17);
                                                                                                                    SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                                                    SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                                                }
                                                                                                            }
                                                                                                            SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.smaf2_device));
                                                                                                            BleApiManager.getInstance(this.f5262a).init(DeviceType.smaF2);
                                                                                                            SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                                            SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                                        }
                                                                                                    }
                                                                                                    SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.j1860_device));
                                                                                                    BleApiManager.getInstance(this.f5262a).init(DeviceType.jstyle1860);
                                                                                                    SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                                    SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.j1790_device));
                                                                                        BleApiManager.getInstance(this.f5262a).init(DeviceType.jstyle1790);
                                                                                        SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                                        SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.v2_device));
                                                                        BleApiManager.getInstance(this.f5262a).init(DeviceType.v2);
                                                                        SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                                        SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.j1963d_device));
                                                        BleApiManager.getInstance(this.f5262a).init(DeviceType.jstyle1963D);
                                                        SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                                        SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.j1810g_device));
                                BleApiManager.getInstance(this.f5262a).init(DeviceType.jstyle1810G);
                                SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                                SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
                            }
                        }
                    }
                }
                SessionManager.getInstance(this.f5262a).saveDeviceType(this.f5262a.getResources().getString(R.string.cove_cz3));
                BleApiManager.getInstance(this.f5262a).init(DeviceType.CZ3);
                SessionManager.getInstance(this.f5262a).setSelectedDeviceTypePhoneOnly(false);
                SessionManager.getInstance(this.f5262a).setScanAllDevice(false);
            }
        }
        if (BleApiManager.getInstance(this.f5262a).getBleApi() == null) {
            getViewModelListener().onDataFailure(AppConstants.EMPTY_SPACE.getValue());
            return;
        }
        String str2 = this.c;
        LogHelper.d(str2, "Scanning started for device name " + str);
        if (DeviceUtils.Companion.isIDODevice(this.f5262a)) {
            return;
        }
        ScanDeviceRequest scanReq = new ScanDeviceRequest.Builder().setScanDuration(15000L).setScanFilter(new String[]{str}).setShouldProvideBatchResult(false).setActivity(activity).setRequestId(i).setSingleDevice(z).build();
        BleApi bleApi = BleApiManager.getInstance(this.f5262a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(scanReq, "scanReq");
        bleApi.scan(scanReq, new ScanResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentScanningDeviceViewModel$startScan$1
            @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
            public void onError(@NotNull String error) {
                String str3;
                Intrinsics.checkNotNullParameter(error, "error");
                FragmentScanningDeviceViewModel.this.stopScan();
                FragmentScanningDeviceViewModel.this.getViewModelListener().onDataFailure(AppConstants.EMPTY_SPACE.getValue());
                str3 = FragmentScanningDeviceViewModel.this.c;
                LogHelper.d(str3, "Scan error due to " + error);
            }

            @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
            public void onResponse(@NotNull BleBaseResponse response) {
                String str3;
                Intrinsics.checkNotNullParameter(response, "response");
                if (response instanceof ScanDeviceResponse) {
                    List<BleDevice> bluetoothDevices = ((ScanDeviceResponse) response).getBluetoothDevices();
                    Intrinsics.checkNotNull(bluetoothDevices);
                    if (bluetoothDevices.isEmpty()) {
                        FragmentScanningDeviceViewModel.this.getViewModelListener().onDataFailure(AppConstants.EMPTY_SPACE.getValue());
                        MutableLiveData<Boolean> isScanning = FragmentScanningDeviceViewModel.this.isScanning();
                        Boolean bool2 = Boolean.FALSE;
                        isScanning.setValue(bool2);
                        FragmentScanningDeviceViewModel.this.isScanning().postValue(bool2);
                        return;
                    }
                    Context context2 = FragmentScanningDeviceViewModel.this.getContext();
                    Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                    ((ActivityPairing) context2).dismissProgress();
                    FragmentScanningDeviceViewModel.this.stopScan();
                    FragmentScanningDeviceViewModel fragmentScanningDeviceViewModel = FragmentScanningDeviceViewModel.this;
                    BluetoothDevice bluetoothDevice = bluetoothDevices.get(0).getmDevice();
                    Intrinsics.checkNotNullExpressionValue(bluetoothDevice, "device.get(0).getmDevice()");
                    fragmentScanningDeviceViewModel.a(bluetoothDevice);
                    str3 = FragmentScanningDeviceViewModel.this.c;
                    LogHelper.d(str3, "Device found during scan and connecting to " + bluetoothDevices.get(0).getmDevice().getAddress());
                }
            }
        });
    }

    public final void stopScan() {
        BleApiManager.getInstance(this.f5262a).getBleApi().stopScan();
    }
}
