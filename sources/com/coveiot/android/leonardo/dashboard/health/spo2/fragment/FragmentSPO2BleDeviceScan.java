package com.coveiot.android.leonardo.dashboard.health.spo2.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.Type;
import com.coveiot.android.bleabstract.models.BleDevice;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.adapters.SPO2BluetoothDeviceScanAdapter;
import com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2BleMeasurement;
import com.coveiot.android.leonardo.dashboard.health.spo2.listeners.SPO2ErrorHandler;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.SPO2BluetoothDeviceInfo;
import com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleDeviceScanViewModel;
import com.coveiot.android.leonardo.dashboard.home.adapters.RemotePagerAdapter;
import com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing;
import com.coveiot.android.leonardo.utils.PagerContainer;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentSPO2BleDeviceScan extends BaseFragment implements Observer<ArrayList<BleDevice>> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final Observer<String> A;
    @Nullable
    public SPO2BluetoothDeviceScanAdapter m;
    public FragmentSPO2BleDeviceScanViewModel n;
    public int q;
    @Nullable
    public BleDevice r;
    @Nullable
    public SPO2BluetoothDeviceInfo s;
    @Nullable
    public FragmentSPO2BleMeasurement.OnLoadSPO2BleMeasurementListener t;
    @Nullable
    public SPO2ErrorHandler u;
    @Nullable
    public SessionManager v;
    @Nullable
    public BottomSheetDialogImageTitleMessageTwoBtns w;
    @NotNull
    public final BluetoothAdapter x;
    @NotNull
    public final Observer<Boolean> y;
    @NotNull
    public final Observer<String> z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final int[] o = {R.string.spo2_bluetooth_instruction_text_2};
    @NotNull
    public final int[] p = {R.drawable.ic_spo2_bluetooth_instruction_2};

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentSPO2BleDeviceScan newInstance() {
            return new FragmentSPO2BleDeviceScan();
        }
    }

    /* loaded from: classes3.dex */
    public interface OnLoadSPO2BleDeviceSelectionListener {
        void loadBleDeviceSelectionFragment();
    }

    public FragmentSPO2BleDeviceScan() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        Intrinsics.checkNotNullExpressionValue(defaultAdapter, "getDefaultAdapter()");
        this.x = defaultAdapter;
        this.y = new Observer() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentSPO2BleDeviceScan.H(FragmentSPO2BleDeviceScan.this, (Boolean) obj);
            }
        };
        this.z = new Observer() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.i
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentSPO2BleDeviceScan.N(FragmentSPO2BleDeviceScan.this, (String) obj);
            }
        };
        this.A = new Observer() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.h
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentSPO2BleDeviceScan.v(FragmentSPO2BleDeviceScan.this, (String) obj);
            }
        };
    }

    public static final void C(FragmentSPO2BleDeviceScan this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.n == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel = this$0.n;
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel2 = null;
        if (fragmentSPO2BleDeviceScanViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleDeviceScanViewModel = null;
        }
        MutableLiveData<Boolean> isScanning = fragmentSPO2BleDeviceScanViewModel.isScanning();
        if (isScanning != null ? Intrinsics.areEqual(isScanning.getValue(), Boolean.TRUE) : false) {
            FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel3 = this$0.n;
            if (fragmentSPO2BleDeviceScanViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentSPO2BleDeviceScanViewModel2 = fragmentSPO2BleDeviceScanViewModel3;
            }
            fragmentSPO2BleDeviceScanViewModel2.stopScan();
        }
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.onBackPressed();
    }

    public static final void D(FragmentSPO2BleDeviceScan this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel = this$0.n;
        if (fragmentSPO2BleDeviceScanViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleDeviceScanViewModel = null;
        }
        MutableLiveData<Boolean> isScanning = fragmentSPO2BleDeviceScanViewModel.isScanning();
        if (isScanning != null ? Intrinsics.areEqual(isScanning.getValue(), Boolean.TRUE) : false) {
            Context context = this$0.getContext();
            Intrinsics.checkNotNull(context);
            Toast.makeText(context, this$0.getString(R.string.scan_already_started), 0).show();
            return;
        }
        this$0.M();
    }

    public static final void F(FragmentSPO2BleDeviceScan this$0, String macAddress, String deviceName, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(macAddress, "$macAddress");
        Intrinsics.checkNotNullParameter(deviceName, "$deviceName");
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
        bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
        BluetoothDevice remoteDevice = BluetoothAdapter.checkBluetoothAddress(macAddress) ? this$0.x.getRemoteDevice(macAddress) : null;
        if (remoteDevice == null) {
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity);
            FragmentActivity activity2 = this$0.getActivity();
            Intrinsics.checkNotNull(activity2);
            Toast.makeText(activity, activity2.getString(R.string.connection_failed), 0).show();
            this$0.M();
            return;
        }
        ArrayList arrayList = new ArrayList();
        SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo = new SPO2BluetoothDeviceInfo();
        sPO2BluetoothDeviceInfo.setBleDevice(new BleDevice(remoteDevice));
        sPO2BluetoothDeviceInfo.setConnecting(true);
        sPO2BluetoothDeviceInfo.setPreviousDeviceName(deviceName);
        arrayList.add(sPO2BluetoothDeviceInfo);
        SPO2BluetoothDeviceScanAdapter sPO2BluetoothDeviceScanAdapter = this$0.m;
        if (sPO2BluetoothDeviceScanAdapter != null) {
            Intrinsics.checkNotNull(sPO2BluetoothDeviceScanAdapter);
            sPO2BluetoothDeviceScanAdapter.setScanDeviceList(arrayList);
        }
        this$0.r = sPO2BluetoothDeviceInfo.getBleDevice();
        this$0.q = 0;
        SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo2 = new SPO2BluetoothDeviceInfo();
        this$0.s = sPO2BluetoothDeviceInfo2;
        sPO2BluetoothDeviceInfo2.setConnecting(true);
        SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo3 = this$0.s;
        if (sPO2BluetoothDeviceInfo3 != null) {
            sPO2BluetoothDeviceInfo3.setBleDevice(sPO2BluetoothDeviceInfo.getBleDevice());
        }
        this$0.w(sPO2BluetoothDeviceInfo, true);
    }

    public static final void G(FragmentSPO2BleDeviceScan this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
        bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
        SessionManager sessionManager = this$0.v;
        if (sessionManager != null) {
            sessionManager.setLastConnectedOxmDeviceMacAddress("");
        }
        SessionManager sessionManager2 = this$0.v;
        if (sessionManager2 != null) {
            sessionManager2.setLastConnectedOxmDeviceName("");
        }
        this$0.M();
    }

    public static final void H(FragmentSPO2BleDeviceScan this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.t(bool);
    }

    public static final void J(FragmentSPO2BleDeviceScan this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        AppUtils.openLocationSettings(activity, 102);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void L(FragmentSPO2BleDeviceScan this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        AppUtils.openAppSettings(activity, -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void N(FragmentSPO2BleDeviceScan this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((TextView) this$0._$_findCachedViewById(R.id.count_down_timer_tv)).setText(str);
    }

    public static final void v(FragmentSPO2BleDeviceScan this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(str, ConnectionStatus.CONNECTED.name())) {
            this$0.B();
        } else if (Intrinsics.areEqual(str, ConnectionStatus.CONNECTING.name())) {
            SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo = this$0.s;
            if (sPO2BluetoothDeviceInfo != null) {
                sPO2BluetoothDeviceInfo.setConnecting(true);
            }
        } else if (Intrinsics.areEqual(str, ConnectionStatus.DISCONNECTED.name())) {
            if (!this$0.A()) {
                return;
            }
            SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo2 = this$0.s;
            if (sPO2BluetoothDeviceInfo2 != null) {
                sPO2BluetoothDeviceInfo2.setConnecting(false);
            }
            SPO2ErrorHandler sPO2ErrorHandler = this$0.u;
            if (sPO2ErrorHandler != null) {
                sPO2ErrorHandler.showConnectionFailureDialog();
            }
        } else if (Intrinsics.areEqual(str, Type.BAND_ALREADY_CONNECTED.name())) {
            this$0.B();
            return;
        } else if (Intrinsics.areEqual(str, Type.CONNECTION_IN_PROGRESS.name())) {
            return;
        } else {
            SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo3 = this$0.s;
            if (sPO2BluetoothDeviceInfo3 != null) {
                sPO2BluetoothDeviceInfo3.setConnecting(false);
            }
            if (Intrinsics.areEqual(str, Type.SERVICE_NOT_RUNNING.name()) && this$0.q < 4) {
                SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo4 = this$0.s;
                if (sPO2BluetoothDeviceInfo4 != null) {
                    x(this$0, sPO2BluetoothDeviceInfo4, false, 2, null);
                }
            } else {
                SPO2ErrorHandler sPO2ErrorHandler2 = this$0.u;
                if (sPO2ErrorHandler2 != null) {
                    sPO2ErrorHandler2.showConnectionFailureDialog();
                }
            }
        }
        SPO2BluetoothDeviceScanAdapter sPO2BluetoothDeviceScanAdapter = this$0.m;
        if (sPO2BluetoothDeviceScanAdapter != null) {
            sPO2BluetoothDeviceScanAdapter.notifyDataSetChanged();
        }
    }

    public static /* synthetic */ void x(FragmentSPO2BleDeviceScan fragmentSPO2BleDeviceScan, SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        fragmentSPO2BleDeviceScan.w(sPO2BluetoothDeviceInfo, z);
    }

    public final boolean A() {
        ConnectionInfo connectionInfo = BleApiManager.getInstance(getContext()).getSecondaryBleImpl(DeviceType.spo2).getConnectionInfo();
        if ((connectionInfo != null ? connectionInfo.getConnectionStatus() : null) != ConnectionStatus.DISCONNECTED || connectionInfo.getConnectionError() == null) {
            return false;
        }
        return connectionInfo.getConnectionError().getGattError() == 133 || System.currentTimeMillis() - connectionInfo.getLastUpdateTimeStamp() > TimeUnit.MINUTES.toMillis(1L);
    }

    public final void B() {
        BluetoothDevice bluetoothDevice;
        BluetoothDevice bluetoothDevice2;
        SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo = this.s;
        if (sPO2BluetoothDeviceInfo != null) {
            sPO2BluetoothDeviceInfo.setConnecting(false);
        }
        BleDevice bleDevice = this.r;
        String str = null;
        String address = (bleDevice == null || (bluetoothDevice2 = bleDevice.getmDevice()) == null) ? null : bluetoothDevice2.getAddress();
        BleDevice bleDevice2 = this.r;
        if (bleDevice2 != null && (bluetoothDevice = bleDevice2.getmDevice()) != null) {
            str = bluetoothDevice.getName();
        }
        SessionManager sessionManager = this.v;
        if (sessionManager != null) {
            sessionManager.setLastConnectedOxmDeviceMacAddress(address);
        }
        SessionManager sessionManager2 = this.v;
        if (sessionManager2 != null) {
            sessionManager2.setLastConnectedOxmDeviceName(str);
        }
        FragmentSPO2BleMeasurement.OnLoadSPO2BleMeasurementListener onLoadSPO2BleMeasurementListener = this.t;
        if (onLoadSPO2BleMeasurementListener != null) {
            onLoadSPO2BleMeasurementListener.loadBluetoothMeasurementFragment();
        }
    }

    public final void E(final String str, final String str2) {
        if (this.w == null) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2);
            Drawable drawable = activity2.getDrawable(R.drawable.info_icon_new);
            Intrinsics.checkNotNull(drawable);
            String string = getString(R.string.fit_connect);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fit_connect)");
            String string2 = getString(R.string.would_you_like_to_connect_with_prev_connected_device, str2);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.would…ected_device, deviceName)");
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = new BottomSheetDialogImageTitleMessageTwoBtns(activity, drawable, string, string2, false);
            this.w = bottomSheetDialogImageTitleMessageTwoBtns;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
            String string3 = getString(R.string.yes);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
            bottomSheetDialogImageTitleMessageTwoBtns.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentSPO2BleDeviceScan.F(FragmentSPO2BleDeviceScan.this, str, str2, view);
                }
            });
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns2 = this.w;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns2);
            String string4 = getString(R.string.f4085no);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
            bottomSheetDialogImageTitleMessageTwoBtns2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentSPO2BleDeviceScan.G(FragmentSPO2BleDeviceScan.this, view);
                }
            });
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns3 = this.w;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns3);
            bottomSheetDialogImageTitleMessageTwoBtns3.setCancelable(false);
        }
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns4 = this.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns4);
        bottomSheetDialogImageTitleMessageTwoBtns4.show();
    }

    public final void I() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String string = getString(R.string.enable_location_services);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enable_location_services)");
        String string2 = getString(R.string.enable_location_access_in_the_next_screen_for_the_app_to_find_your_band);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.enabl…he_app_to_find_your_band)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(context, string, string2);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentSPO2BleDeviceScan.J(FragmentSPO2BleDeviceScan.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void K() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …on_required\n            )");
        String string2 = getString(R.string.enable_location_access_in_the_next_screen_for_the_app_to_find_your_band);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …d_your_band\n            )");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(context, string, string2);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentSPO2BleDeviceScan.L(FragmentSPO2BleDeviceScan.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void M() {
        ((ImageView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.share_iv)).setVisibility(0);
        if (this.n == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel = this.n;
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel2 = null;
        if (fragmentSPO2BleDeviceScanViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleDeviceScanViewModel = null;
        }
        MutableLiveData<Boolean> isScanning = fragmentSPO2BleDeviceScanViewModel.isScanning();
        if (isScanning != null ? Intrinsics.areEqual(isScanning.getValue(), Boolean.TRUE) : false) {
            FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel3 = this.n;
            if (fragmentSPO2BleDeviceScanViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentSPO2BleDeviceScanViewModel3 = null;
            }
            fragmentSPO2BleDeviceScanViewModel3.stopScan();
        }
        if (z()) {
            FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel4 = this.n;
            if (fragmentSPO2BleDeviceScanViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentSPO2BleDeviceScanViewModel2 = fragmentSPO2BleDeviceScanViewModel4;
            }
            fragmentSPO2BleDeviceScanViewModel2.startScan();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Nullable
    public final SPO2BluetoothDeviceScanAdapter getAdapter() {
        return this.m;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel = null;
        if (i != 102) {
            if (i != 202) {
                return;
            }
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            Object systemService = context.getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (((BluetoothManager) systemService).getAdapter().isEnabled()) {
                FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel2 = this.n;
                if (fragmentSPO2BleDeviceScanViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fragmentSPO2BleDeviceScanViewModel = fragmentSPO2BleDeviceScanViewModel2;
                }
                fragmentSPO2BleDeviceScanViewModel.startScan();
                return;
            }
            return;
        }
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        if (AppUtils.isGpsEnabled(context2)) {
            Context context3 = getContext();
            Intrinsics.checkNotNull(context3);
            Object systemService2 = context3.getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (!((BluetoothManager) systemService2).getAdapter().isEnabled()) {
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                ((ActivityPairing) activity).showOpenBluetoothSettingsDialog();
                return;
            }
            FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel3 = this.n;
            if (fragmentSPO2BleDeviceScanViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentSPO2BleDeviceScanViewModel = fragmentSPO2BleDeviceScanViewModel3;
            }
            fragmentSPO2BleDeviceScanViewModel.startScan();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_spo2_bluetooth_device_selection, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel = this.n;
        if (fragmentSPO2BleDeviceScanViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleDeviceScanViewModel = null;
        }
        fragmentSPO2BleDeviceScanViewModel.stopScan();
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == 1001) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                Context context = getContext();
                Intrinsics.checkNotNull(context);
                if (!AppUtils.isGpsEnabled(context)) {
                    I();
                    return;
                }
                Context context2 = getContext();
                Intrinsics.checkNotNull(context2);
                Object systemService = context2.getSystemService("bluetooth");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
                if (!((BluetoothManager) systemService).getAdapter().isEnabled()) {
                    FragmentActivity activity = getActivity();
                    Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                    ((ActivityPairing) activity).showOpenBluetoothSettingsDialog();
                    return;
                }
                FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel = this.n;
                if (fragmentSPO2BleDeviceScanViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentSPO2BleDeviceScanViewModel = null;
                }
                fragmentSPO2BleDeviceScanViewModel.startScan();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (z()) {
            u();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        this.v = SessionManager.getInstance(activity);
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSPO2BleDeviceScan.C(FragmentSPO2BleDeviceScan.this, view2);
            }
        });
        ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setImageResource(R.drawable.ic_refresh);
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setColorFilter(activity2.getResources().getColor(R.color.colorPrimary, null));
        ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.fit_connect));
        FragmentActivity activity3 = getActivity();
        Intrinsics.checkNotNull(activity3);
        this.n = (FragmentSPO2BleDeviceScanViewModel) ViewModelProviders.of(this, new ViewModelFactory(activity3)).get(FragmentSPO2BleDeviceScanViewModel.class);
        y();
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel = this.n;
        if (fragmentSPO2BleDeviceScanViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleDeviceScanViewModel = null;
        }
        fragmentSPO2BleDeviceScanViewModel.getBleDevices().observe(this, this);
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel2 = this.n;
        if (fragmentSPO2BleDeviceScanViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleDeviceScanViewModel2 = null;
        }
        fragmentSPO2BleDeviceScanViewModel2.isScanning().observe(this, this.y);
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel3 = this.n;
        if (fragmentSPO2BleDeviceScanViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleDeviceScanViewModel3 = null;
        }
        fragmentSPO2BleDeviceScanViewModel3.getTimerText().observe(this, this.z);
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel4 = this.n;
        if (fragmentSPO2BleDeviceScanViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleDeviceScanViewModel4 = null;
        }
        fragmentSPO2BleDeviceScanViewModel4.getConnectionStatus().observe(this, this.A);
        int i2 = R.id.rv_spo2_bluetooth_device;
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new LinearLayoutManager(getActivity()));
        FragmentActivity activity4 = getActivity();
        SPO2BluetoothDeviceScanAdapter sPO2BluetoothDeviceScanAdapter = activity4 != null ? new SPO2BluetoothDeviceScanAdapter(activity4) : null;
        this.m = sPO2BluetoothDeviceScanAdapter;
        if (sPO2BluetoothDeviceScanAdapter != null) {
            sPO2BluetoothDeviceScanAdapter.setDeviceSelectionListener(new SPO2BluetoothDeviceScanAdapter.DeviceSelectionListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2BleDeviceScan$onViewCreated$3
                @Override // com.coveiot.android.leonardo.dashboard.health.spo2.adapters.SPO2BluetoothDeviceScanAdapter.DeviceSelectionListener
                public void onConnectClick(@NotNull SPO2BluetoothDeviceInfo deviceInfo) {
                    FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel5;
                    Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
                    FragmentSPO2BleDeviceScan.this.s = deviceInfo;
                    FragmentSPO2BleDeviceScan.this.r = deviceInfo.getBleDevice();
                    FragmentSPO2BleDeviceScan.this.q = 0;
                    FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel6 = null;
                    FragmentSPO2BleDeviceScan.x(FragmentSPO2BleDeviceScan.this, deviceInfo, false, 2, null);
                    fragmentSPO2BleDeviceScanViewModel5 = FragmentSPO2BleDeviceScan.this.n;
                    if (fragmentSPO2BleDeviceScanViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        fragmentSPO2BleDeviceScanViewModel6 = fragmentSPO2BleDeviceScanViewModel5;
                    }
                    fragmentSPO2BleDeviceScanViewModel6.stopScan();
                }
            });
        }
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(this.m);
        ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSPO2BleDeviceScan.D(FragmentSPO2BleDeviceScan.this, view2);
            }
        });
        u();
    }

    public final void setAdapter(@Nullable SPO2BluetoothDeviceScanAdapter sPO2BluetoothDeviceScanAdapter) {
        this.m = sPO2BluetoothDeviceScanAdapter;
    }

    public final void setListener(@NotNull FragmentSPO2BleMeasurement.OnLoadSPO2BleMeasurementListener listener, @NotNull SPO2ErrorHandler errorListener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(errorListener, "errorListener");
        this.t = listener;
        this.u = errorListener;
    }

    public final void t(Boolean bool) {
        if (bool != null) {
            if (bool.booleanValue()) {
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                rotateAnimation.setInterpolator(new LinearInterpolator());
                rotateAnimation.setDuration(1000L);
                rotateAnimation.setRepeatMode(-1);
                rotateAnimation.setRepeatCount(-1);
                ((ImageView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.share_iv)).startAnimation(rotateAnimation);
                LogHelper.d("FragmentSPO2BleDeviceSelection", "Animation started");
                return;
            }
            ((ImageView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.share_iv)).clearAnimation();
            LogHelper.d("FragmentSPO2BleDeviceSelection", "Animation stopped");
        }
    }

    public final void u() {
        SessionManager sessionManager = this.v;
        if (sessionManager != null) {
            Intrinsics.checkNotNull(sessionManager);
            if (sessionManager.getLastConnectedOxmDeviceMacAddress() != null) {
                SessionManager sessionManager2 = this.v;
                Intrinsics.checkNotNull(sessionManager2);
                String lastConnectedOxmDeviceMacAddress = sessionManager2.getLastConnectedOxmDeviceMacAddress();
                Intrinsics.checkNotNull(lastConnectedOxmDeviceMacAddress);
                if (lastConnectedOxmDeviceMacAddress.length() > 0) {
                    ((ImageView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.share_iv)).setVisibility(8);
                    SessionManager sessionManager3 = this.v;
                    Intrinsics.checkNotNull(sessionManager3);
                    String lastConnectedOxmDeviceMacAddress2 = sessionManager3.getLastConnectedOxmDeviceMacAddress();
                    Intrinsics.checkNotNullExpressionValue(lastConnectedOxmDeviceMacAddress2, "sessionManager!!.lastConnectedOxmDeviceMacAddress");
                    SessionManager sessionManager4 = this.v;
                    Intrinsics.checkNotNull(sessionManager4);
                    String lastConnectedOxmDeviceName = sessionManager4.getLastConnectedOxmDeviceName();
                    Intrinsics.checkNotNullExpressionValue(lastConnectedOxmDeviceName, "sessionManager!!.lastConnectedOxmDeviceName");
                    E(lastConnectedOxmDeviceMacAddress2, lastConnectedOxmDeviceName);
                    return;
                }
            }
        }
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel = this.n;
        if (fragmentSPO2BleDeviceScanViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleDeviceScanViewModel = null;
        }
        if (Intrinsics.areEqual(fragmentSPO2BleDeviceScanViewModel.isScanning().getValue(), Boolean.TRUE)) {
            return;
        }
        M();
    }

    public final void w(SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo, boolean z) {
        BluetoothDevice bluetoothDevice;
        this.q++;
        BleDevice bleDevice = sPO2BluetoothDeviceInfo.getBleDevice();
        FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel = null;
        String address = (bleDevice == null || (bluetoothDevice = bleDevice.getmDevice()) == null) ? null : bluetoothDevice.getAddress();
        if (address != null) {
            FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel2 = this.n;
            if (fragmentSPO2BleDeviceScanViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentSPO2BleDeviceScanViewModel2 = null;
            }
            fragmentSPO2BleDeviceScanViewModel2.stopScan();
            if (z()) {
                ((TextView) _$_findCachedViewById(R.id.select_spo2_bluetooth_device_tv)).setText(getString(R.string.connecting_to_oximeter));
                if (z) {
                    FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel3 = this.n;
                    if (fragmentSPO2BleDeviceScanViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        fragmentSPO2BleDeviceScanViewModel = fragmentSPO2BleDeviceScanViewModel3;
                    }
                    fragmentSPO2BleDeviceScanViewModel.scanAndConnectPreviousDevice(sPO2BluetoothDeviceInfo);
                    return;
                }
                FragmentSPO2BleDeviceScanViewModel fragmentSPO2BleDeviceScanViewModel4 = this.n;
                if (fragmentSPO2BleDeviceScanViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fragmentSPO2BleDeviceScanViewModel = fragmentSPO2BleDeviceScanViewModel4;
                }
                fragmentSPO2BleDeviceScanViewModel.connect(new ConnectRequest(address));
            }
        }
    }

    public final void y() {
        PagerContainer pagerContainer = (PagerContainer) _$_findCachedViewById(R.id.viewpager_spo2_bluetooth_connect_instruction);
        Intrinsics.checkNotNull(pagerContainer, "null cannot be cast to non-null type com.coveiot.android.leonardo.utils.PagerContainer");
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        pagerContainer.setAdapter(new RemotePagerAdapter(childFragmentManager, activity, this.o, this.p));
        pagerContainer.setAnimationEnabled(true);
        pagerContainer.setFadeEnabled(true);
        pagerContainer.setFadeFactor(0.6f);
        int i = R.id.circlePageIndicator_spo2_bluetooth_connect_instruction;
        ((CirclePageIndicator) _$_findCachedViewById(i)).setViewPager(pagerContainer);
        ((CirclePageIndicator) _$_findCachedViewById(i)).setVisibility(0);
    }

    public final boolean z() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String[] unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(context, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"});
        Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
        if (!(unGrantedPermissions.length == 0)) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_FINE_LOCATION")) {
                K();
            } else {
                FragmentActivity activity2 = getActivity();
                Intrinsics.checkNotNull(activity2);
                ActivityCompat.requestPermissions(activity2, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1001);
            }
            return false;
        }
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        if (!AppUtils.isGpsEnabled(context2)) {
            I();
            return false;
        }
        Context context3 = getContext();
        Intrinsics.checkNotNull(context3);
        Object systemService = context3.getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        if (((BluetoothManager) systemService).getAdapter().isEnabled()) {
            return true;
        }
        SPO2ErrorHandler sPO2ErrorHandler = this.u;
        if (sPO2ErrorHandler != null) {
            sPO2ErrorHandler.showOpenBluetoothSettingsDialog();
        }
        return false;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ArrayList<BleDevice> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<BleDevice> it = arrayList.iterator();
            while (it.hasNext()) {
                SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo = new SPO2BluetoothDeviceInfo();
                sPO2BluetoothDeviceInfo.setBleDevice(it.next());
                sPO2BluetoothDeviceInfo.setConnecting(false);
                arrayList2.add(sPO2BluetoothDeviceInfo);
            }
        }
        SPO2BluetoothDeviceScanAdapter sPO2BluetoothDeviceScanAdapter = this.m;
        Intrinsics.checkNotNull(sPO2BluetoothDeviceScanAdapter);
        sPO2BluetoothDeviceScanAdapter.setScanDeviceList(arrayList2);
        if (arrayList2.size() == 0) {
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            Toast.makeText(context, getString(R.string.band_not_found), 0).show();
        }
    }
}
