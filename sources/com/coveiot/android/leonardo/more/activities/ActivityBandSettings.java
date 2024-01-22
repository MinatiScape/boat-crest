package com.coveiot.android.leonardo.more.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.BpCalibrationDataRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.BatterySaverModeHelper;
import com.coveiot.android.leonardo.listener.OnSuccessListener;
import com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel;
import com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation;
import com.coveiot.android.leonardo.quickreply.activity.ActivityQuickReplySettings;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityBandSettings extends BaseActivity implements OnSuccessListener, BatterySaverModeDialogClickCallback {
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
    public BottomSheetDialogImageTitleMessage bottomSheetDialogOneButtonTitleMessage;
    public ActivityBandSettingsViewModel p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public boolean q = true;
    public boolean r = true;
    public boolean s = true;
    public boolean t = true;
    public boolean u = true;

    public static final void L(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    public static final void M(ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0)) {
            this$0.startActivity(new Intent(this$0, ActivityNotifications.class));
        } else {
            this$0.h0();
        }
    }

    public static final void N(ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0)) {
            if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                this$0.g0();
                return;
            } else {
                Toast.makeText(this$0, this$0.getString(R.string.band_not_connected), 1).show();
                return;
            }
        }
        this$0.h0();
    }

    public static final void O(ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0)) {
            this$0.startActivity(new Intent(this$0, ActivityDoNotDisturb.class));
        } else {
            this$0.h0();
        }
    }

    public static final void P(ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0)) {
            this$0.startActivity(new Intent(this$0, ActivityQuickReplySettings.class));
        } else {
            this$0.h0();
        }
    }

    public static final void Q(ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0)) {
            this$0.startActivity(new Intent(this$0, ActivityBatterySaverMode.class));
        } else {
            this$0.h0();
        }
    }

    public static final void R(ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0)) {
            if (BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isScheduledLiftWristViewSettingsSupported()) {
                this$0.startActivity(new Intent(this$0, ActivityLiftWristToView.class));
                return;
            }
            return;
        }
        this$0.h0();
    }

    public static final void S(final ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0)) {
            PickerDialog.Companion companion = PickerDialog.Companion;
            boolean z = this$0.q;
            String string = this$0.getString(R.string.hand_selection);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hand_selection)");
            companion.handPicker(this$0, z, string, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBandSettings$initClickListeners$1$1
                @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
                public void onValueSelected(@NotNull String value) {
                    ActivityBandSettingsViewModel activityBandSettingsViewModel;
                    boolean z2;
                    Intrinsics.checkNotNullParameter(value, "value");
                    ((TextView) ActivityBandSettings.this._$_findCachedViewById(R.id.selected_hand_text)).setText(value);
                    ActivityBandSettings activityBandSettings = ActivityBandSettings.this;
                    activityBandSettings.q = !kotlin.text.m.equals(value, activityBandSettings.getString(R.string.left_hand), true);
                    if (BleApiManager.getInstance(ActivityBandSettings.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        activityBandSettingsViewModel = ActivityBandSettings.this.p;
                        if (activityBandSettingsViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                            activityBandSettingsViewModel = null;
                        }
                        z2 = ActivityBandSettings.this.q;
                        activityBandSettingsViewModel.handPreference(z2);
                        return;
                    }
                    ActivityBandSettings.this.bandDisconnectDialog();
                }
            });
            return;
        }
        this$0.h0();
    }

    public static final void T(final ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0)) {
            PickerDialog.Companion companion = PickerDialog.Companion;
            boolean z = this$0.r;
            String string = this$0.getString(R.string.hour_selection);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hour_selection)");
            companion.hourFormatPicker(this$0, z, string, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBandSettings$initClickListeners$2$1
                @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
                public void onValueSelected(@NotNull String value) {
                    ActivityBandSettingsViewModel activityBandSettingsViewModel;
                    boolean z2;
                    Intrinsics.checkNotNullParameter(value, "value");
                    ((TextView) ActivityBandSettings.this._$_findCachedViewById(R.id.selected_hour_text)).setText(value);
                    ActivityBandSettings activityBandSettings = ActivityBandSettings.this;
                    activityBandSettings.r = !kotlin.text.m.equals(value, activityBandSettings.getString(R.string.hour_24), true);
                    if (BleApiManager.getInstance(ActivityBandSettings.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        activityBandSettingsViewModel = ActivityBandSettings.this.p;
                        if (activityBandSettingsViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                            activityBandSettingsViewModel = null;
                        }
                        z2 = ActivityBandSettings.this.r;
                        activityBandSettingsViewModel.hourFormatPreference(z2);
                        return;
                    }
                    ActivityBandSettings.this.bandDisconnectDialog();
                }
            });
            return;
        }
        this$0.h0();
    }

    public static final void U(final ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0)) {
            PickerDialog.Companion companion = PickerDialog.Companion;
            boolean z = this$0.s;
            String string = this$0.getString(R.string.unit_selection);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unit_selection)");
            companion.distanceUnitPicker(this$0, z, string, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBandSettings$initClickListeners$3$1
                @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
                public void onValueSelected(@NotNull String value) {
                    ActivityBandSettingsViewModel activityBandSettingsViewModel;
                    boolean z2;
                    Intrinsics.checkNotNullParameter(value, "value");
                    ((TextView) ActivityBandSettings.this._$_findCachedViewById(R.id.selected_distance_unit_text)).setText(value);
                    ActivityBandSettings activityBandSettings = ActivityBandSettings.this;
                    activityBandSettings.s = !kotlin.text.m.equals(value, activityBandSettings.getString(R.string.dist_km), true);
                    if (BleApiManager.getInstance(ActivityBandSettings.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        activityBandSettingsViewModel = ActivityBandSettings.this.p;
                        if (activityBandSettingsViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                            activityBandSettingsViewModel = null;
                        }
                        z2 = ActivityBandSettings.this.s;
                        activityBandSettingsViewModel.distanceUnitPreference(z2);
                        return;
                    }
                    ActivityBandSettings.this.bandDisconnectDialog();
                }
            });
            return;
        }
        this$0.h0();
    }

    public static final void V(final ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0)) {
            PickerDialog.Companion companion = PickerDialog.Companion;
            boolean z = this$0.t;
            String string = this$0.getString(R.string.unit_selection);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unit_selection)");
            companion.temperatureUnitPicker(this$0, z, string, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBandSettings$initClickListeners$4$1
                @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
                public void onValueSelected(@NotNull String value) {
                    ActivityBandSettingsViewModel activityBandSettingsViewModel;
                    boolean z2;
                    Intrinsics.checkNotNullParameter(value, "value");
                    ((TextView) ActivityBandSettings.this._$_findCachedViewById(R.id.selected_temp_unit)).setText(value);
                    ActivityBandSettings activityBandSettings = ActivityBandSettings.this;
                    activityBandSettings.t = !kotlin.text.m.equals(value, activityBandSettings.getString(R.string.celsius), true);
                    if (BleApiManager.getInstance(ActivityBandSettings.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        activityBandSettingsViewModel = ActivityBandSettings.this.p;
                        if (activityBandSettingsViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                            activityBandSettingsViewModel = null;
                        }
                        z2 = ActivityBandSettings.this.t;
                        activityBandSettingsViewModel.temperatureUnitPreference(z2);
                        return;
                    }
                    ActivityBandSettings.this.bandDisconnectDialog();
                }
            });
            return;
        }
        this$0.h0();
    }

    public static final void W(ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this$0) && !themesUtils.isPairDeviceLater(this$0)) {
            this$0.startActivity(new Intent(this$0, ActivityBandDisplay.class));
        } else {
            this$0.h0();
        }
    }

    public static final void X(ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonTitleMessage().dismiss();
    }

    public static final void Y(ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitle().dismiss();
    }

    public static final void a0(ActivityBandSettings this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.u = it.booleanValue();
        ((SwitchCompat) this$0._$_findCachedViewById(R.id.lift_wrist_switch)).setChecked(this$0.u);
        ActivityBandSettingsViewModel activityBandSettingsViewModel = this$0.p;
        if (activityBandSettingsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            activityBandSettingsViewModel = null;
        }
        activityBandSettingsViewModel.isLiftWristObserver().removeObservers(this$0);
    }

    public static final void b0(ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void c0(ActivityBandSettings this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        boolean booleanValue = it.booleanValue();
        this$0.r = booleanValue;
        if (booleanValue) {
            ((TextView) this$0._$_findCachedViewById(R.id.selected_hour_text)).setText(this$0.getResources().getString(R.string.hour_12));
        } else {
            ((TextView) this$0._$_findCachedViewById(R.id.selected_hour_text)).setText(this$0.getResources().getString(R.string.hour_24));
        }
        ActivityBandSettingsViewModel activityBandSettingsViewModel = this$0.p;
        if (activityBandSettingsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            activityBandSettingsViewModel = null;
        }
        activityBandSettingsViewModel.is12HourFormatObserver().removeObservers(this$0);
    }

    public static final void d0(ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, ActivityCustomise4HButton.class));
    }

    public static final void f0(final ActivityBandSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0) != null && BleApiManager.getInstance(this$0).getBleApi() != null && BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
                BatterySaverModeHelper.Companion.getInstance(this$0).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBandSettings$setOnCheckChangeListenerForWakeGesture$1$1
                    @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                    public void onBatterySaverCMDFailed() {
                        Toast.makeText(ActivityBandSettings.this, (int) R.string.somethings_went_wrong, 0).show();
                    }

                    @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                    public void onBatterySavingSettingsReceived(boolean z, int i) {
                        ActivityBandSettingsViewModel activityBandSettingsViewModel;
                        boolean z2;
                        if (z) {
                            ((SwitchCompat) ActivityBandSettings.this._$_findCachedViewById(R.id.lift_wrist_switch)).setChecked(false);
                            ActivityBandSettings activityBandSettings = ActivityBandSettings.this;
                            activityBandSettings.showBatterySaverModeEnabledDialog(activityBandSettings);
                            return;
                        }
                        ActivityBandSettings activityBandSettings2 = ActivityBandSettings.this;
                        activityBandSettings2.u = ((SwitchCompat) activityBandSettings2._$_findCachedViewById(R.id.lift_wrist_switch)).isChecked();
                        activityBandSettingsViewModel = ActivityBandSettings.this.p;
                        if (activityBandSettingsViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                            activityBandSettingsViewModel = null;
                        }
                        z2 = ActivityBandSettings.this.u;
                        activityBandSettingsViewModel.liftWristToViewPreference(z2);
                    }
                });
                return;
            }
            this$0.u = ((SwitchCompat) this$0._$_findCachedViewById(R.id.lift_wrist_switch)).isChecked();
            ActivityBandSettingsViewModel activityBandSettingsViewModel = this$0.p;
            if (activityBandSettingsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                activityBandSettingsViewModel = null;
            }
            activityBandSettingsViewModel.liftWristToViewPreference(this$0.u);
            return;
        }
        this$0.bandDisconnectDialog();
    }

    public static final void i0(BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        guestOrPairDevicePopup.dismiss();
    }

    public static final void j0(ActivityBandSettings this$0, BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        if (ThemesUtils.INSTANCE.isGuestUser(this$0)) {
            Intent intent = new Intent(this$0, ActivityPhoneValidation.class);
            intent.addFlags(335577088);
            this$0.startActivity(intent);
            SessionManager.getInstance(this$0).setIsGuestUser(false);
            SessionManager.getInstance(this$0).setOnBoardingComplete(false);
            this$0.finish();
        } else {
            AppNavigator.Companion.navigateToPairYourDevice(this$0);
        }
        guestOrPairDevicePopup.dismiss();
        this$0.finish();
    }

    public final void Z() {
        ActivityBandSettingsViewModel activityBandSettingsViewModel = this.p;
        if (activityBandSettingsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            activityBandSettingsViewModel = null;
        }
        activityBandSettingsViewModel.isLiftWristObserver().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.n3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityBandSettings.a0(ActivityBandSettings.this, (Boolean) obj);
            }
        });
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

    public final void bandDisconnectDialog() {
        String string = getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.j3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.L(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void e0() {
        ((SwitchCompat) _$_findCachedViewById(R.id.lift_wrist_switch)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.t3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.f0(ActivityBandSettings.this, view);
            }
        });
    }

    public final void g0() {
        String string = getString(R.string.bp_calibration_reset_warning_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bp_ca…tion_reset_warning_title)");
        String string2 = getString(R.string.bp_calibration_reset_warning_message);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bp_ca…on_reset_warning_message)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBandSettings$showBpCallibrationResetWarning$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogTwoButtons.this.dismiss();
            }
        });
        String string4 = getString(R.string.proceed);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.proceed)");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBandSettings$showBpCallibrationResetWarning$2
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                ActivityBandSettings activityBandSettings = ActivityBandSettings.this;
                String string5 = activityBandSettings.getString(R.string.please_wait);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.please_wait)");
                activityBandSettings.showProgressWithTitle(string5);
                BpCalibrationDataRequest build = new BpCalibrationDataRequest.Builder().setDbpCalculatingSign(1).setDbp(0).setSbpCalculatingSign(1).setSbp(0).build();
                BleApi bleApi = BleApiManager.getInstance(ActivityBandSettings.this).getBleApi();
                final ActivityBandSettings activityBandSettings2 = ActivityBandSettings.this;
                bleApi.setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBandSettings$showBpCallibrationResetWarning$2$onClick$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        ActivityBandSettings activityBandSettings3 = ActivityBandSettings.this;
                        Toast.makeText(activityBandSettings3, activityBandSettings3.getString(R.string.something_went_wrong), 0).show();
                        ActivityBandSettings.this.dismissProgress();
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        ActivityBandSettings.this.dismissProgress();
                        ActivityBandSettings.this.startActivity(new Intent(ActivityBandSettings.this, ActivityBpCalibration.class));
                    }
                });
                bottomSheetDialogTwoButtons.dismiss();
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    @NotNull
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitle() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.bottomSheetDialogOneButtonOneTitle;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            return bottomSheetDialogOneButtonOneTitle;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogOneButtonOneTitle");
        return null;
    }

    @NotNull
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogOneButtonTitleMessage() {
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this.bottomSheetDialogOneButtonTitleMessage;
        if (bottomSheetDialogImageTitleMessage != null) {
            return bottomSheetDialogImageTitleMessage;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogOneButtonTitleMessage");
        return null;
    }

    public final void h0() {
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        String string = getString(themesUtils.isGuestUser(this) ? R.string.login : R.string.pair_device);
        Intrinsics.checkNotNullExpressionValue(string, "if(isGuestUser())getStri…ing(R.string.pair_device)");
        String string2 = getString(themesUtils.isGuestUser(this) ? R.string.please_login_to_use_this_feature : R.string.please_pair_with_your_bluetooth_device_to_use_this_feature);
        Intrinsics.checkNotNullExpressionValue(string2, "if(isGuestUser()) getStr…vice_to_use_this_feature)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.k3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.i0(BottomSheetDialogTwoButtons.this, view);
            }
        });
        String string4 = getString(themesUtils.isGuestUser(this) ? R.string.login_now : R.string.pair_now);
        Intrinsics.checkNotNullExpressionValue(string4, "if(isGuestUser())getStri…String(R.string.pair_now)");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.i3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.j0(ActivityBandSettings.this, bottomSheetDialogTwoButtons, view);
            }
        });
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    public final void initClickListeners() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_left_right_hand)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.d3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.S(ActivityBandSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_12h_24h)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.c3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.T(ActivityBandSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_km_mile)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.b3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.U(ActivityBandSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_celsius_fahrenheit)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.q3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.V(ActivityBandSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_band_display)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.r3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.W(ActivityBandSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_notifications)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.g3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.M(ActivityBandSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_bp_calibration)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.v3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.N(ActivityBandSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_donot_disturb)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.f3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.O(ActivityBandSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_quick_reply)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.p3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.P(ActivityBandSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_battery_saver_mode)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.h3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.Q(ActivityBandSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_lift_wrist)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.s3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.R(ActivityBandSettings.this, view);
            }
        });
    }

    public final void initDialog() {
        Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.info_icon_new)");
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        String string2 = getString(R.string.turn_off_dnd_enable_lift_wrist);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.turn_off_dnd_enable_lift_wrist)");
        setBottomSheetDialogOneButtonTitleMessage(new BottomSheetDialogImageTitleMessage(this, drawable, string, string2, false));
        getBottomSheetDialogOneButtonTitleMessage().setCancelable(false);
        getBottomSheetDialogOneButtonTitleMessage().setCancelableOutside(false);
        BottomSheetDialogImageTitleMessage bottomSheetDialogOneButtonTitleMessage = getBottomSheetDialogOneButtonTitleMessage();
        String string3 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(\n   …    R.string.ok\n        )");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.m3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.X(ActivityBandSettings.this, view);
            }
        });
        String string4 = getResources().getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.success_message)");
        setBottomSheetDialogOneButtonOneTitle(new BottomSheetDialogOneButtonOneTitle(this, string4));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = getBottomSheetDialogOneButtonOneTitle();
        String string5 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(\n   …    R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string5, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.e3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.Y(ActivityBandSettings.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.watch_controls));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.u3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBandSettings.b0(ActivityBandSettings.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_band_settings);
        initToolbar();
        initDialog();
        ActivityBandSettingsViewModel activityBandSettingsViewModel = (ActivityBandSettingsViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityBandSettingsViewModel.class);
        this.p = activityBandSettingsViewModel;
        ActivityBandSettingsViewModel activityBandSettingsViewModel2 = null;
        if (activityBandSettingsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            activityBandSettingsViewModel = null;
        }
        activityBandSettingsViewModel.setViewModelListener(this);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this) && !themesUtils.isPairDeviceLater(this)) {
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isHandPreferenceSettingsSupported()) {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_left_right_hand)).setVisibility(0);
            } else {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_left_right_hand)).setVisibility(8);
            }
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isTimeFormatSettingsSupported()) {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_12h_24h)).setVisibility(0);
            } else {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_12h_24h)).setVisibility(8);
            }
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isDistanceUnitSettingsSupported()) {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_km_mile)).setVisibility(0);
            } else {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_km_mile)).setVisibility(8);
            }
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isTemperatureUnitSettingsSupported()) {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_celsius_fahrenheit)).setVisibility(0);
            } else {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_celsius_fahrenheit)).setVisibility(8);
            }
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isLiftWristToViewSettingsSupported()) {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_lift_wrist)).setVisibility(0);
            } else {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_lift_wrist)).setVisibility(8);
            }
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBPCalibrationSupported()) {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_bp_calibration)).setVisibility(0);
            } else {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_bp_calibration)).setVisibility(8);
            }
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isDndSupported()) {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_donot_disturb)).setVisibility(0);
            } else {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_donot_disturb)).setVisibility(8);
            }
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isQuickReplySupported()) {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_quick_reply)).setVisibility(0);
            } else {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_quick_reply)).setVisibility(8);
            }
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_battery_saver_mode)).setVisibility(0);
            } else {
                ((ConstraintLayout) _$_findCachedViewById(R.id.cl_battery_saver_mode)).setVisibility(8);
            }
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isIDODevice(this) && !companion.isTouchELXDevice(this) && !companion.isEastApexDevice(this)) {
                Boolean isLiftWristToViewEnable = UserDataManager.getInstance(this).isLiftWristToViewEnable();
                Intrinsics.checkNotNullExpressionValue(isLiftWristToViewEnable, "getInstance(this@Activit…isLiftWristToViewEnable()");
                this.u = isLiftWristToViewEnable.booleanValue();
                ((SwitchCompat) _$_findCachedViewById(R.id.lift_wrist_switch)).setChecked(this.u);
            } else {
                Boolean isLiftWristToViewEnable2 = UserDataManager.getInstance(this).isLiftWristToViewEnable();
                Intrinsics.checkNotNullExpressionValue(isLiftWristToViewEnable2, "getInstance(this@Activit…isLiftWristToViewEnable()");
                this.u = isLiftWristToViewEnable2.booleanValue();
                ((SwitchCompat) _$_findCachedViewById(R.id.lift_wrist_switch)).setChecked(this.u);
                ActivityBandSettingsViewModel activityBandSettingsViewModel3 = this.p;
                if (activityBandSettingsViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    activityBandSettingsViewModel3 = null;
                }
                activityBandSettingsViewModel3.getLiftWristFromBand();
                Z();
            }
            Boolean isRightHandSelected = UserDataManager.getInstance(this).isRightHandSelected();
            Intrinsics.checkNotNullExpressionValue(isRightHandSelected, "getInstance(this@Activit…gs).isRightHandSelected()");
            if (isRightHandSelected.booleanValue()) {
                ((TextView) _$_findCachedViewById(R.id.selected_hand_text)).setText(getResources().getString(R.string.right_hand));
                this.q = true;
            } else {
                ((TextView) _$_findCachedViewById(R.id.selected_hand_text)).setText(getResources().getString(R.string.left_hand));
                this.q = false;
            }
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(this).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this@Activit…s).isDistanceUnitInMile()");
            if (isDistanceUnitInMile.booleanValue()) {
                ((TextView) _$_findCachedViewById(R.id.selected_distance_unit_text)).setText(getResources().getString(R.string.dist_mile));
                this.s = true;
            } else {
                ((TextView) _$_findCachedViewById(R.id.selected_distance_unit_text)).setText(getResources().getString(R.string.dist_km));
                this.s = false;
            }
            if (companion.isEastApexDevice(this)) {
                ActivityBandSettingsViewModel activityBandSettingsViewModel4 = this.p;
                if (activityBandSettingsViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                } else {
                    activityBandSettingsViewModel2 = activityBandSettingsViewModel4;
                }
                activityBandSettingsViewModel2.is12HourFormatObserver().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.l3
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        ActivityBandSettings.c0(ActivityBandSettings.this, (Boolean) obj);
                    }
                });
            } else {
                Boolean isTimeIn12HourFormat = UserDataManager.getInstance(this).isTimeIn12HourFormat();
                Intrinsics.checkNotNullExpressionValue(isTimeIn12HourFormat, "getInstance(this@Activit…s).isTimeIn12HourFormat()");
                if (isTimeIn12HourFormat.booleanValue()) {
                    ((TextView) _$_findCachedViewById(R.id.selected_hour_text)).setText(getResources().getString(R.string.hour_12));
                    this.r = true;
                } else {
                    ((TextView) _$_findCachedViewById(R.id.selected_hour_text)).setText(getResources().getString(R.string.hour_24));
                    this.r = false;
                }
            }
            Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(this).isTemperatureUnitInFahrenheit();
            Intrinsics.checkNotNullExpressionValue(isTemperatureUnitInFahrenheit, "getInstance(this@Activit…eratureUnitInFahrenheit()");
            if (isTemperatureUnitInFahrenheit.booleanValue()) {
                ((TextView) _$_findCachedViewById(R.id.selected_temp_unit)).setText(getResources().getString(R.string.setting_fahrenheit));
                this.t = true;
            } else {
                ((TextView) _$_findCachedViewById(R.id.selected_temp_unit)).setText(getResources().getString(R.string.setting_celsius));
                this.t = false;
            }
            e0();
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isScheduledLiftWristViewSettingsSupported()) {
                ((ImageView) _$_findCachedViewById(R.id.lift_wrist_arrow)).setVisibility(0);
                ((SwitchCompat) _$_findCachedViewById(R.id.lift_wrist_switch)).setVisibility(8);
            } else {
                ((ImageView) _$_findCachedViewById(R.id.lift_wrist_arrow)).setVisibility(8);
                ((SwitchCompat) _$_findCachedViewById(R.id.lift_wrist_switch)).setVisibility(0);
            }
            if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
                showProgress();
                BatterySaverModeHelper.Companion.getInstance(this).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBandSettings$onCreate$2
                    @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                    public void onBatterySaverCMDFailed() {
                        ActivityBandSettings.this.dismissProgress();
                    }

                    @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                    public void onBatterySavingSettingsReceived(boolean z, int i) {
                        if (z) {
                            ((SwitchCompat) ActivityBandSettings.this._$_findCachedViewById(R.id.lift_wrist_switch)).setChecked(false);
                        }
                        ActivityBandSettings.this.e0();
                        ActivityBandSettings.this.dismissProgress();
                    }
                });
            }
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().is4hButtonFunctionSupported()) {
                ConstraintLayout cl_customise_4h_button = (ConstraintLayout) _$_findCachedViewById(R.id.cl_customise_4h_button);
                Intrinsics.checkNotNullExpressionValue(cl_customise_4h_button, "cl_customise_4h_button");
                visible(cl_customise_4h_button);
            } else {
                ConstraintLayout cl_customise_4h_button2 = (ConstraintLayout) _$_findCachedViewById(R.id.cl_customise_4h_button);
                Intrinsics.checkNotNullExpressionValue(cl_customise_4h_button2, "cl_customise_4h_button");
                gone(cl_customise_4h_button2);
            }
            ((ConstraintLayout) _$_findCachedViewById(R.id.cl_customise_4h_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.o3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityBandSettings.d0(ActivityBandSettings.this, view);
                }
            });
        } else {
            ConstraintLayout clFeatureInfo = (ConstraintLayout) _$_findCachedViewById(R.id.clFeatureInfo);
            Intrinsics.checkNotNullExpressionValue(clFeatureInfo, "clFeatureInfo");
            visible(clFeatureInfo);
            ((TextView) _$_findCachedViewById(R.id.selected_hand_text)).setText(getResources().getString(R.string.right_hand));
            ((TextView) _$_findCachedViewById(R.id.selected_distance_unit_text)).setText(getResources().getString(R.string.dist_km));
            ((TextView) _$_findCachedViewById(R.id.selected_hour_text)).setText(getResources().getString(R.string.hour_12));
            ((TextView) _$_findCachedViewById(R.id.selected_temp_unit)).setText(getResources().getString(R.string.setting_celsius));
            ((SwitchCompat) _$_findCachedViewById(R.id.lift_wrist_switch)).setEnabled(false);
            ((ConstraintLayout) _$_findCachedViewById(R.id.llMain)).setAlpha(0.5f);
            View clBlockerView = _$_findCachedViewById(R.id.clBlockerView);
            Intrinsics.checkNotNullExpressionValue(clBlockerView, "clBlockerView");
            visible(clBlockerView);
        }
        initClickListeners();
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onNegativeButtonClicked() {
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onPositiveButtonClicked() {
    }

    @Override // com.coveiot.android.leonardo.listener.OnSuccessListener
    public void onSuccess(boolean z) {
        if (z) {
            if (getBottomSheetDialogOneButtonTitleMessage().isShowing()) {
                return;
            }
            getBottomSheetDialogOneButtonTitleMessage().show();
        } else if (getBottomSheetDialogOneButtonOneTitle().isShowing()) {
        } else {
            getBottomSheetDialogOneButtonOneTitle().show();
        }
    }

    public final void setBottomSheetDialogOneButtonOneTitle(@NotNull BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "<set-?>");
        this.bottomSheetDialogOneButtonOneTitle = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setBottomSheetDialogOneButtonTitleMessage(@NotNull BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "<set-?>");
        this.bottomSheetDialogOneButtonTitleMessage = bottomSheetDialogImageTitleMessage;
    }
}
