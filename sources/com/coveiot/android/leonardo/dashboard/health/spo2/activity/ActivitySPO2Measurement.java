package com.coveiot.android.leonardo.dashboard.health.spo2.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2BleDeviceScan;
import com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2BleMeasurement;
import com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2BleMethodInstruction;
import com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2MethodSelection;
import com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2Result;
import com.coveiot.android.leonardo.dashboard.health.spo2.listeners.SPO2ErrorHandler;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2DeviceType;
import com.coveiot.android.ocr.model.OcrDeviceType;
import com.coveiot.android.ocr.ui.FragmentOcrScan;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.textrecognition.base.BaseOcrResponse;
import com.coveiot.textrecognition.base.ResponseCallback;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivitySPO2Measurement extends BaseActivity implements FragmentSPO2MethodSelection.OnLoadSPO2MethodSelectionListener, FragmentSPO2Result.OnLoadSPO2ResultListener, FragmentSPO2BleDeviceScan.OnLoadSPO2BleDeviceSelectionListener, FragmentSPO2BleMeasurement.OnLoadSPO2BleMeasurementListener, SPO2ErrorHandler {
    @NotNull
    public static final Companion Companion;
    public static final String t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public BottomSheetDialogTwoButtons p;
    @Nullable
    public BottomSheetDialogImageTitleMessage q;
    @Nullable
    public BottomSheetDialogImageTitleMessage r;
    @Nullable
    public BottomSheetDialogImageTitleMessageTwoBtns s;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return ActivitySPO2Measurement.t;
        }
    }

    static {
        Companion companion = new Companion(null);
        Companion = companion;
        t = companion.getClass().getSimpleName();
    }

    public static final void A(ActivitySPO2Measurement this$0, View.OnClickListener onClickListener, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onClickListener, "$onClickListener");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.r;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
        onClickListener.onClick(view);
    }

    public static final void B(ActivitySPO2Measurement this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtils.openBluetoothSettings(this$0);
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void C(ActivitySPO2Measurement this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void E(ActivitySPO2Measurement this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this$0.s;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
        bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
        this$0.finish();
    }

    public static final void F(ActivitySPO2Measurement this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this$0.s;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
        bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
    }

    public static final void y() {
    }

    public static final void z(ActivitySPO2Measurement this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.q;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.finish();
    }

    public final void D(String str) {
        if (this.s == null) {
            Drawable drawable = getDrawable(R.drawable.info_icon_new);
            Intrinsics.checkNotNull(drawable);
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = new BottomSheetDialogImageTitleMessageTwoBtns(this, drawable, string, str, false);
            this.s = bottomSheetDialogImageTitleMessageTwoBtns;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
            String string2 = getString(R.string.yes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.yes)");
            bottomSheetDialogImageTitleMessageTwoBtns.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.activity.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySPO2Measurement.E(ActivitySPO2Measurement.this, view);
                }
            });
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns2 = this.s;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns2);
            String string3 = getString(R.string.f4085no);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.no)");
            bottomSheetDialogImageTitleMessageTwoBtns2.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.activity.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySPO2Measurement.F(ActivitySPO2Measurement.this, view);
                }
            });
            BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns3 = this.s;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns3);
            bottomSheetDialogImageTitleMessageTwoBtns3.setCancelable(false);
        }
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns4 = this.s;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns4);
        bottomSheetDialogImageTitleMessageTwoBtns4.show();
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

    @Nullable
    public final BottomSheetDialogImageTitleMessage getConnectionFailureDialog() {
        return this.q;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getEnableBluetoothDialog() {
        return this.p;
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessageTwoBtns getExitConfirmationDialog() {
        return this.s;
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessage getMeasurementFailureDialog() {
        return this.r;
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2BleDeviceScan.OnLoadSPO2BleDeviceSelectionListener
    public void loadBleDeviceSelectionFragment() {
        FragmentSPO2BleDeviceScan.Companion companion = FragmentSPO2BleDeviceScan.Companion;
        FragmentSPO2BleDeviceScan newInstance = companion.newInstance();
        newInstance.setListener(this, this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newInstance).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2MethodSelection.OnLoadSPO2MethodSelectionListener
    public void loadBluetoothInstructionFragment() {
        FragmentSPO2BleMethodInstruction.Companion companion = FragmentSPO2BleMethodInstruction.Companion;
        FragmentSPO2BleMethodInstruction newInstance = companion.newInstance();
        newInstance.setListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newInstance).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2BleMeasurement.OnLoadSPO2BleMeasurementListener
    public void loadBluetoothMeasurementFragment() {
        FragmentSPO2BleMeasurement.Companion companion = FragmentSPO2BleMeasurement.Companion;
        FragmentSPO2BleMeasurement newInstance = companion.newInstance();
        newInstance.setListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newInstance).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2Result.OnLoadSPO2ResultListener
    public void loadBluetoothResultFragment(@NotNull Spo2DeviceType spo2DeviceType, double d) {
        Intrinsics.checkNotNullParameter(spo2DeviceType, "spo2DeviceType");
        FragmentSPO2Result.Companion companion = FragmentSPO2Result.Companion;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, companion.newInstance(spo2DeviceType, d)).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2MethodSelection.OnLoadSPO2MethodSelectionListener
    public void loadOcrScanFragment(@NotNull ResponseCallback<BaseOcrResponse> responseCallback) {
        Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
        ArrayList<Integer> arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(Integer.valueOf((int) R.string.spo2_ocr_instruction_text1));
        ArrayList<Integer> arrayListOf2 = CollectionsKt__CollectionsKt.arrayListOf(Integer.valueOf((int) R.drawable.spo2_instruction1));
        FragmentOcrScan.Companion companion = FragmentOcrScan.Companion;
        FragmentOcrScan newInstance = companion.newInstance(arrayListOf, arrayListOf2, OcrDeviceType.OXYMETER);
        newInstance.setResponseCallback(responseCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newInstance).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof FragmentSPO2MethodSelection) {
            String string = getString(R.string.exit_dialog_message);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.exit_dialog_message)");
            D(string);
        } else if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof FragmentSPO2BleMeasurement) {
            BleApiManager.getInstance(this).getSecondaryBleImpl(DeviceType.spo2).unpairDevice();
            D(getString(R.string.spo2_measurement_in_progress) + '\n' + getString(R.string.exit_dialog_message));
        } else if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof FragmentSPO2Result) {
            D(getString(R.string.spo2_measured_value_is_not_saved) + '\n' + getString(R.string.exit_dialog_message));
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sp02_ble_measurement);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.activity.g
            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public final void onBackStackChanged() {
                ActivitySPO2Measurement.y();
            }
        });
        x();
    }

    public final void setConnectionFailureDialog(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.q = bottomSheetDialogImageTitleMessage;
    }

    public final void setEnableBluetoothDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.p = bottomSheetDialogTwoButtons;
    }

    public final void setExitConfirmationDialog(@Nullable BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns) {
        this.s = bottomSheetDialogImageTitleMessageTwoBtns;
    }

    public final void setMeasurementFailureDialog(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.r = bottomSheetDialogImageTitleMessage;
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.spo2.listeners.SPO2ErrorHandler
    public void showConnectionFailureDialog() {
        if (this.q == null) {
            Drawable drawable = getDrawable(R.drawable.ic_connection_error);
            Intrinsics.checkNotNull(drawable);
            String string = getString(R.string.connection_failed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.connection_failed)");
            String string2 = getString(R.string.please_try_again);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_try_again)");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, string, string2, false);
            this.q = bottomSheetDialogImageTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogImageTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.activity.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySPO2Measurement.z(ActivitySPO2Measurement.this, view);
                }
            });
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
            bottomSheetDialogImageTitleMessage2.setCancelable(false);
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
        if (bottomSheetDialogImageTitleMessage3.isShowing()) {
            return;
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
        bottomSheetDialogImageTitleMessage4.show();
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2Result.OnLoadSPO2ResultListener, com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2BleMeasurement.OnLoadSPO2BleMeasurementListener
    public void showFailureDialog(@NotNull String failureMessage, @NotNull final View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(failureMessage, "failureMessage");
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        if (this.r == null) {
            Drawable drawable = getDrawable(R.drawable.ic_connection_error);
            Intrinsics.checkNotNull(drawable);
            String string = getString(R.string.please_try_again);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_try_again)");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, failureMessage, string, false);
            this.r = bottomSheetDialogImageTitleMessage;
            String string2 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
            bottomSheetDialogImageTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.activity.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySPO2Measurement.A(ActivitySPO2Measurement.this, onClickListener, view);
                }
            });
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.r;
            if (bottomSheetDialogImageTitleMessage2 != null) {
                bottomSheetDialogImageTitleMessage2.setCancelable(false);
            }
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.r;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
        if (bottomSheetDialogImageTitleMessage3.isShowing()) {
            return;
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.r;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
        bottomSheetDialogImageTitleMessage4.show();
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.spo2.listeners.SPO2ErrorHandler
    public void showOpenBluetoothSettingsDialog() {
        if (this.p == null) {
            String string = getString(R.string.bluetooth_off);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_off)");
            String string2 = getString(R.string.bluetooth_off_message);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bluetooth_off_message)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.p = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.proceed);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.proceed)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.activity.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySPO2Measurement.B(ActivitySPO2Measurement.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.p;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.activity.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySPO2Measurement.C(ActivitySPO2Measurement.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.p;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
            bottomSheetDialogTwoButtons3.setCancelable(false);
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        if (bottomSheetDialogTwoButtons4.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons5);
        bottomSheetDialogTwoButtons5.show();
    }

    public final void x() {
        FragmentSPO2MethodSelection.Companion companion = FragmentSPO2MethodSelection.Companion;
        FragmentSPO2MethodSelection newInstance = companion.newInstance();
        newInstance.setListener(this, this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newInstance).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }
}
