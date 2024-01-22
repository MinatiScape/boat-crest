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
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.ScanDeviceResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentQRScanDeviceViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5261a;
    @NotNull
    public MutableLiveData<ArrayList<BleDevice>> b;
    @NotNull
    public MutableLiveData<Boolean> c;
    @NotNull
    public final String d;
    public ViewModelListener viewModelListener;

    public FragmentQRScanDeviceViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5261a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = "FragmentQRScanDevice";
    }

    public static /* synthetic */ void b(FragmentQRScanDeviceViewModel fragmentQRScanDeviceViewModel, BluetoothDevice bluetoothDevice, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        fragmentQRScanDeviceViewModel.a(bluetoothDevice, str);
    }

    public final void a(BluetoothDevice bluetoothDevice, String str) {
        Object systemService = this.f5261a.getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothAdapter adapter = ((BluetoothManager) systemService).getAdapter();
        Intrinsics.checkNotNullExpressionValue(adapter, "context.getSystemService…BluetoothManager).adapter");
        BleDevice bleDevice = new BleDevice(adapter.getRemoteDevice(bluetoothDevice.getAddress()));
        Context context = this.f5261a;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ((ActivityPairing) context).connectTo(bleDevice, str);
        Context context2 = this.f5261a;
        Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ((ActivityPairing) context2).dismissProgress();
    }

    @NotNull
    public final MutableLiveData<ArrayList<BleDevice>> getBleDevices() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f5261a;
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
        return this.c;
    }

    public final void setBleDevices(@NotNull MutableLiveData<ArrayList<BleDevice>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setScanning(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setViewModelListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.viewModelListener = viewModelListener;
    }

    public final void startAssociationForSingleDevice(@NotNull String deviceName, @NotNull Activity activity, int i, boolean z) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public final void startQRScan(@Nullable String str, @NotNull FragmentActivity activity, int i, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        MutableLiveData<Boolean> mutableLiveData = this.c;
        Boolean bool = Boolean.TRUE;
        mutableLiveData.setValue(bool);
        this.c.postValue(bool);
        Intrinsics.checkNotNull(str);
        String substringAfter$default = StringsKt__StringsKt.substringAfter$default(str, "btname=", (String) null, 2, (Object) null);
        String substringBefore$default = StringsKt__StringsKt.substringBefore$default(substringAfter$default, "_", (String) null, 2, (Object) null);
        DeviceModelBean deviceModelBean = SessionManager.getInstance(this.f5261a).getDeviceModelBean();
        if (deviceModelBean == null || substringAfter$default == null) {
            return;
        }
        if (!substringBefore$default.equals(deviceModelBean.getScanFilter().get(0))) {
            getViewModelListener().onDataFailure(AppConstants.EMPTY_STRING.getValue());
            return;
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "mc=", false, 2, (Object) null)) {
            String substringAfter$default2 = StringsKt__StringsKt.substringAfter$default(str, "mc=", (String) null, 2, (Object) null);
            if (!(substringAfter$default2 == null || substringAfter$default2.length() == 0)) {
                String take = StringsKt___StringsKt.take(new Regex("(.{2})").replace(StringsKt___StringsKt.take(StringsKt__StringsKt.substringAfter$default(str, "mc=", (String) null, 2, (Object) null), 12), "$1:"), 17);
                String str2 = this.d;
                LogHelper.d(str2, "Scanning started for device with macAddress " + take);
                BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(take);
                Intrinsics.checkNotNull(remoteDevice);
                a(remoteDevice, substringBefore$default);
                return;
            }
        }
        ScanDeviceRequest scanReq = new ScanDeviceRequest.Builder().setScanDuration(15000L).setScanFilter(new String[]{substringAfter$default}).setShouldProvideBatchResult(false).setActivity(activity).setRequestId(i).setSingleDevice(z).build();
        BleApi bleApi = BleApiManager.getInstance(this.f5261a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(scanReq, "scanReq");
        bleApi.scan(scanReq, new ScanResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentQRScanDeviceViewModel$startQRScan$1
            @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
            public void onError(@NotNull String error) {
                String str3;
                Intrinsics.checkNotNullParameter(error, "error");
                FragmentQRScanDeviceViewModel.this.stopScan();
                ViewModelListener viewModelListener = FragmentQRScanDeviceViewModel.this.getViewModelListener();
                String string = FragmentQRScanDeviceViewModel.this.getContext().getString(R.string.something_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.something_went_wrong)");
                viewModelListener.onDataFailure(string);
                str3 = FragmentQRScanDeviceViewModel.this.d;
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
                        ViewModelListener viewModelListener = FragmentQRScanDeviceViewModel.this.getViewModelListener();
                        String string = FragmentQRScanDeviceViewModel.this.getContext().getString(R.string.something_went_wrong);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.something_went_wrong)");
                        viewModelListener.onDataFailure(string);
                        MutableLiveData<Boolean> isScanning = FragmentQRScanDeviceViewModel.this.isScanning();
                        Boolean bool2 = Boolean.FALSE;
                        isScanning.setValue(bool2);
                        FragmentQRScanDeviceViewModel.this.isScanning().postValue(bool2);
                        return;
                    }
                    Context context = FragmentQRScanDeviceViewModel.this.getContext();
                    Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                    ((ActivityPairing) context).dismissProgress();
                    FragmentQRScanDeviceViewModel.this.stopScan();
                    FragmentQRScanDeviceViewModel fragmentQRScanDeviceViewModel = FragmentQRScanDeviceViewModel.this;
                    BluetoothDevice bluetoothDevice = bluetoothDevices.get(0).getmDevice();
                    Intrinsics.checkNotNullExpressionValue(bluetoothDevice, "device.get(0).getmDevice()");
                    FragmentQRScanDeviceViewModel.b(fragmentQRScanDeviceViewModel, bluetoothDevice, null, 2, null);
                    str3 = FragmentQRScanDeviceViewModel.this.d;
                    LogHelper.d(str3, "Device found during scan and connecting to " + bluetoothDevices.get(0).getmDevice().getAddress());
                }
            }
        });
    }

    public final void stopScan() {
        BleApiManager.getInstance(this.f5261a).getBleApi().stopScan();
    }
}
