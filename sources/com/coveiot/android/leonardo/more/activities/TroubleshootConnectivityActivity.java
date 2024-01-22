package com.coveiot.android.leonardo.more.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentScanningDeviceKt;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class TroubleshootConnectivityActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public BluetoothAdapter bluetoothAdapter;
    public boolean p;
    public boolean q;
    public boolean r;

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
            if (AppUtils.isBluetoothEnabled(TroubleshootConnectivityActivity.this)) {
                TroubleshootConnectivityActivity.this.showProgress();
                BleApiManager.getInstance(TroubleshootConnectivityActivity.this).getBleApi().restartService();
                Dashboard2Utils.Companion.scheduleJob(TroubleshootConnectivityActivity.this);
                SyncManager.getInstance().resetSyncProgress();
                if (PayUtils.INSTANCE.checkIfScanOnDisConnectIsNeeded(TroubleshootConnectivityActivity.this)) {
                    TroubleshootConnectivityActivity.this.startScanOnDisconnect$app_prodRelease();
                }
                TroubleshootConnectivityActivity.this.startActivity(new Intent(TroubleshootConnectivityActivity.this, TroubleshootingConnectivitySuccessActivity.class));
                return;
            }
            Toast.makeText(TroubleshootConnectivityActivity.this, (int) R.string.bluetooth_off_message, 0).show();
        }
    }

    public static final void u(TroubleshootConnectivityActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void v(TroubleshootConnectivityActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = z;
        ((Button) this$0._$_findCachedViewById(R.id.troubleshoot_fix_button)).setEnabled(this$0.p && this$0.q && this$0.r);
    }

    public static final void w(TroubleshootConnectivityActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.q = z;
        ((Button) this$0._$_findCachedViewById(R.id.troubleshoot_fix_button)).setEnabled(this$0.p && this$0.q && this$0.r);
    }

    public static final void x(TroubleshootConnectivityActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r = z;
        ((Button) this$0._$_findCachedViewById(R.id.troubleshoot_fix_button)).setEnabled(this$0.p && this$0.q && this$0.r);
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
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

    public final void initToolbar(String str) {
        ((TextView) findViewById(R.id.toolbar_title)).setText(str);
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.sk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TroubleshootConnectivityActivity.u(TroubleshootConnectivityActivity.this, view);
            }
        });
    }

    public final boolean isBandChargedRadioEnabled() {
        return this.p;
    }

    public final boolean isBandNearByRadioEnabled() {
        return this.q;
    }

    public final boolean isBluetoothOnRadioEnabled() {
        return this.r;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_troubleshoot_connectivity);
        int i = R.id.troubleshoot_fix_button;
        ((Button) _$_findCachedViewById(i)).setEnabled(false);
        Object systemService = getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothAdapter adapter = ((BluetoothManager) systemService).getAdapter();
        Intrinsics.checkNotNullExpressionValue(adapter, "getSystemService(Context…BluetoothManager).adapter");
        setBluetoothAdapter(adapter);
        Dashboard2Utils.Companion.loadScanDeviceOnDisconnectConfiguration(this);
        String string = getString(R.string.troubleshoot_connectivity);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.troubleshoot_connectivity)");
        initToolbar(string);
        ((RadioButton) _$_findCachedViewById(R.id.band_charged_radio)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.uk
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                TroubleshootConnectivityActivity.v(TroubleshootConnectivityActivity.this, compoundButton, z);
            }
        });
        ((RadioButton) _$_findCachedViewById(R.id.band_nearby_radio)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.tk
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                TroubleshootConnectivityActivity.w(TroubleshootConnectivityActivity.this, compoundButton, z);
            }
        });
        ((RadioButton) _$_findCachedViewById(R.id.bluetooth_on_radio)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.vk
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                TroubleshootConnectivityActivity.x(TroubleshootConnectivityActivity.this, compoundButton, z);
            }
        });
        Button troubleshoot_fix_button = (Button) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(troubleshoot_fix_button, "troubleshoot_fix_button");
        ViewUtilsKt.setSafeOnClickListener(troubleshoot_fix_button, new a());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        z();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
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

    public final void setBandChargedRadioEnabled(boolean z) {
        this.p = z;
    }

    public final void setBandNearByRadioEnabled(boolean z) {
        this.q = z;
    }

    public final void setBluetoothAdapter(@NotNull BluetoothAdapter bluetoothAdapter) {
        Intrinsics.checkNotNullParameter(bluetoothAdapter, "<set-?>");
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public final void setBluetoothOnRadioEnabled(boolean z) {
        this.r = z;
    }

    public final void startScanOnDisconnect$app_prodRelease() {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if ((companion.isCZDevice(this) || companion.isCADevice(this) || companion.isCYDevice(this) || companion.isPS1Device(this) || companion.isBESDevice(this)) && getBluetoothAdapter().isEnabled()) {
            if (checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0 && checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
                String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
                if (checkPermissionsHasGranted.length > 0) {
                    Intrinsics.checkNotNull(this);
                    ActivityCompat.requestPermissions(this, checkPermissionsHasGranted, FragmentScanningDeviceKt.getLOCATION_PERMISSION_REQUEST_CODE());
                    return;
                }
                return;
            }
            y();
        }
    }

    public final void y() {
        if (DeviceScanManager.getInstance(this).isScanningInProgress()) {
            DeviceScanManager.getInstance(this).stopScan();
        }
        DeviceScanManager.getInstance(this).scanAllDevices(this, TimeUnit.HOURS.toMillis(Long.parseLong(AppConstants.SCAN_TIME_ON_DISCONNECTION.getValue())), false, new ScanResult() { // from class: com.coveiot.android.leonardo.more.activities.TroubleshootConnectivityActivity$startLeScan$1
            @Override // com.coveiot.sdk.ble.scan.ScanResult
            public void onDevicesFound(@Nullable List<BleDevice> list, boolean z) {
                String macAddress = BleApiManager.getInstance(TroubleshootConnectivityActivity.this).getBleApi().getMacAddress();
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

    public final void z() {
        try {
            DeviceScanManager.getInstance(this).stopScan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
