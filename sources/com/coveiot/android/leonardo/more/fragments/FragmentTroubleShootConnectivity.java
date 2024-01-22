package com.coveiot.android.leonardo.more.fragments;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.more.activities.TroubleshootingConnectivitySuccessActivity;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentScanningDeviceKt;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentTroubleShootConnectivity extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public BluetoothAdapter bluetoothAdapter;
    public boolean m;
    public boolean n;
    public boolean o;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentTroubleShootConnectivity newInstance() {
            return new FragmentTroubleShootConnectivity();
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            if (AppUtils.isBluetoothEnabled(FragmentTroubleShootConnectivity.this.requireContext())) {
                BleApiManager.getInstance(FragmentTroubleShootConnectivity.this.requireContext()).getBleApi().restartService();
                Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
                Context requireContext = FragmentTroubleShootConnectivity.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                companion.scheduleJob(requireContext);
                SyncManager.getInstance().resetSyncProgress();
                PayUtils payUtils = PayUtils.INSTANCE;
                Context requireContext2 = FragmentTroubleShootConnectivity.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                if (payUtils.checkIfScanOnDisConnectIsNeeded(requireContext2)) {
                    FragmentTroubleShootConnectivity.this.startScanOnDisconnect$app_prodRelease();
                }
                FragmentTroubleShootConnectivity.this.startActivity(new Intent(FragmentTroubleShootConnectivity.this.requireContext(), TroubleshootingConnectivitySuccessActivity.class));
                FragmentTroubleShootConnectivity.this.requireActivity().finish();
                return;
            }
            Toast.makeText(FragmentTroubleShootConnectivity.this.requireContext(), (int) R.string.bluetooth_off_message, 0).show();
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentTroubleShootConnectivity newInstance() {
        return Companion.newInstance();
    }

    public static final void o(FragmentTroubleShootConnectivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.m = z;
        if (z) {
            ((CheckBox) this$0._$_findCachedViewById(R.id.band_charged_radio)).setTextColor(this$0.requireContext().getColor(R.color.main_text_color));
        } else {
            ((CheckBox) this$0._$_findCachedViewById(R.id.band_charged_radio)).setTextColor(this$0.requireContext().getColor(R.color.color_b3b3b3));
        }
        ((Button) this$0._$_findCachedViewById(R.id.troubleshoot_fix_button)).setEnabled(this$0.m && this$0.n && this$0.o);
    }

    public static final void p(FragmentTroubleShootConnectivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.n = z;
        ((Button) this$0._$_findCachedViewById(R.id.troubleshoot_fix_button)).setEnabled(this$0.m && this$0.n && this$0.o);
        if (z) {
            ((CheckBox) this$0._$_findCachedViewById(R.id.band_nearby_radio)).setTextColor(this$0.requireContext().getColor(R.color.main_text_color));
        } else {
            ((CheckBox) this$0._$_findCachedViewById(R.id.band_nearby_radio)).setTextColor(this$0.requireContext().getColor(R.color.color_b3b3b3));
        }
    }

    public static final void q(FragmentTroubleShootConnectivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = z;
        ((Button) this$0._$_findCachedViewById(R.id.troubleshoot_fix_button)).setEnabled(this$0.m && this$0.n && this$0.o);
        if (z) {
            ((CheckBox) this$0._$_findCachedViewById(R.id.bluetooth_on_radio)).setTextColor(this$0.requireContext().getColor(R.color.main_text_color));
        } else {
            ((CheckBox) this$0._$_findCachedViewById(R.id.bluetooth_on_radio)).setTextColor(this$0.requireContext().getColor(R.color.color_b3b3b3));
        }
    }

    public static final void s(FragmentTroubleShootConnectivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getParentFragmentManager().popBackStack();
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

    @NotNull
    public final BluetoothAdapter getBluetoothAdapter() {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bluetoothAdapter");
        return null;
    }

    public final boolean isBandChargedRadioEnabled() {
        return this.m;
    }

    public final boolean isBandNearByRadioEnabled() {
        return this.n;
    }

    public final boolean isBluetoothOnRadioEnabled() {
        return this.o;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_trouble_shoot_connectivity, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (grantResults.length > 0) {
            if (grantResults[0] == 0 || grantResults[1] == 0) {
                startScanOnDisconnect$app_prodRelease();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Object systemService = requireContext().getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothAdapter adapter = ((BluetoothManager) systemService).getAdapter();
        Intrinsics.checkNotNullExpressionValue(adapter, "requireContext().getSyst…BluetoothManager).adapter");
        setBluetoothAdapter(adapter);
        Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.loadScanDeviceOnDisconnectConfiguration(requireContext);
        r();
        ((CheckBox) _$_findCachedViewById(R.id.band_charged_radio)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.fragments.l0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FragmentTroubleShootConnectivity.o(FragmentTroubleShootConnectivity.this, compoundButton, z);
            }
        });
        ((CheckBox) _$_findCachedViewById(R.id.band_nearby_radio)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.fragments.n0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FragmentTroubleShootConnectivity.p(FragmentTroubleShootConnectivity.this, compoundButton, z);
            }
        });
        ((CheckBox) _$_findCachedViewById(R.id.bluetooth_on_radio)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.fragments.m0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FragmentTroubleShootConnectivity.q(FragmentTroubleShootConnectivity.this, compoundButton, z);
            }
        });
        Button troubleshoot_fix_button = (Button) _$_findCachedViewById(R.id.troubleshoot_fix_button);
        Intrinsics.checkNotNullExpressionValue(troubleshoot_fix_button, "troubleshoot_fix_button");
        ViewUtilsKt.setSafeOnClickListener(troubleshoot_fix_button, new a());
    }

    public final void r() {
        int i = R.id.toolbar;
        _$_findCachedViewById(i).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.troubleshoot_connectivity));
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.k0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentTroubleShootConnectivity.s(FragmentTroubleShootConnectivity.this, view);
            }
        });
    }

    public final void setBandChargedRadioEnabled(boolean z) {
        this.m = z;
    }

    public final void setBandNearByRadioEnabled(boolean z) {
        this.n = z;
    }

    public final void setBluetoothAdapter(@NotNull BluetoothAdapter bluetoothAdapter) {
        Intrinsics.checkNotNullParameter(bluetoothAdapter, "<set-?>");
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public final void setBluetoothOnRadioEnabled(boolean z) {
        this.o = z;
    }

    public final void startScanOnDisconnect$app_prodRelease() {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!companion.isCZDevice(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!companion.isCADevice(requireContext2)) {
                Context requireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                if (!companion.isCYDevice(requireContext3)) {
                    Context requireContext4 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                    if (!companion.isPS1Device(requireContext4)) {
                        Context requireContext5 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                        if (!companion.isBESDevice(requireContext5)) {
                            return;
                        }
                    }
                }
            }
        }
        if (getBluetoothAdapter().isEnabled()) {
            if (ContextCompat.checkSelfPermission(requireContext(), "android.permission.ACCESS_FINE_LOCATION") != 0 && ContextCompat.checkSelfPermission(requireContext(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(requireContext(), new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
                if (checkPermissionsHasGranted.length > 0) {
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNull(requireActivity);
                    ActivityCompat.requestPermissions(requireActivity, checkPermissionsHasGranted, FragmentScanningDeviceKt.getLOCATION_PERMISSION_REQUEST_CODE());
                    return;
                }
                return;
            }
            t();
        }
    }

    public final void t() {
        if (DeviceScanManager.getInstance(requireContext()).isScanningInProgress()) {
            DeviceScanManager.getInstance(requireContext()).stopScan();
        }
        DeviceScanManager.getInstance(requireContext()).scanAllDevices(requireContext(), TimeUnit.HOURS.toMillis(Long.parseLong(AppConstants.SCAN_TIME_ON_DISCONNECTION.getValue())), false, new ScanResult() { // from class: com.coveiot.android.leonardo.more.fragments.FragmentTroubleShootConnectivity$startLeScan$1
            @Override // com.coveiot.sdk.ble.scan.ScanResult
            public void onDevicesFound(@Nullable List<BleDevice> list, boolean z) {
                String macAddress = BleApiManager.getInstance(FragmentTroubleShootConnectivity.this.requireContext()).getBleApi().getMacAddress();
                if (list == null || !(!list.isEmpty())) {
                    return;
                }
                for (BleDevice bleDevice : list) {
                    kotlin.text.m.equals(bleDevice.getmDevice().getAddress(), macAddress, true);
                }
            }

            @Override // com.coveiot.sdk.ble.scan.ScanResult
            public void onScanFailed(int i) {
            }
        });
    }
}
