package com.coveiot.android.leonardo.more.activities;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.BatterySaverModeHelper;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.activities.ActivityAutoStressHRVSettings;
import com.coveiot.android.leonardo.more.viewmodel.AutoStressSettingsViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
import com.coveiot.covepreferences.data.AutoStressSettingsData;
import com.coveiot.utils.utility.AppUtils;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAutoStressHRVSettings extends BaseActivity implements DialogListener, BatterySaverModeDialogClickCallback, View.OnClickListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AutoStressSettingsData autoStressSettingsDataCommon;
    public AutoStressSettingsViewModel autoStressSettingsViewModel;
    public boolean p;
    public int q;
    public boolean r;
    public boolean s;
    @Nullable
    public TimePickerDialog t;
    @Nullable
    public TimePickerDialog u;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityAutoStressHRVSettings this$0, View view) {
            Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            bottomSheetDialogOneButtonTitleMessage.dismiss();
            this$0.finish();
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            if (ActivityAutoStressHRVSettings.this.getBoolSaveVisible()) {
                if (!AppUtils.isNetConnected(ActivityAutoStressHRVSettings.this)) {
                    ActivityAutoStressHRVSettings.this.showNoInternetMessage();
                } else if (BleApiManager.getInstance(ActivityAutoStressHRVSettings.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    if (ActivityAutoStressHRVSettings.this.r && ActivityAutoStressHRVSettings.this.q <= 0) {
                        Toast.makeText(ActivityAutoStressHRVSettings.this, (int) R.string.please_select_interval, 0).show();
                    } else {
                        ActivityAutoStressHRVSettings.this.getAutoStressSettingsViewModel().callSaveStressSettingsAndBleApi(ActivityAutoStressHRVSettings.this.q, ActivityAutoStressHRVSettings.this.r);
                    }
                } else {
                    ActivityAutoStressHRVSettings activityAutoStressHRVSettings = ActivityAutoStressHRVSettings.this;
                    String string = activityAutoStressHRVSettings.getString(R.string.band_not_connected);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                    String string2 = ActivityAutoStressHRVSettings.this.getString(R.string.please_connect_the_device);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                    final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activityAutoStressHRVSettings, string, string2);
                    String string3 = ActivityAutoStressHRVSettings.this.getString(R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                    final ActivityAutoStressHRVSettings activityAutoStressHRVSettings2 = ActivityAutoStressHRVSettings.this;
                    bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.i1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityAutoStressHRVSettings.a.invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage.this, activityAutoStressHRVSettings2, view);
                        }
                    });
                    if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
                        return;
                    }
                    bottomSheetDialogOneButtonTitleMessage.show();
                }
            }
        }
    }

    public static final void A(BottomSheetDialogTwoButtons dialog, ActivityAutoStressHRVSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        ((Button) this$0._$_findCachedViewById(R.id.btnSave)).performClick();
    }

    public static final void B(BottomSheetDialogTwoButtons dialog, ActivityAutoStressHRVSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void C(ActivityAutoStressHRVSettings this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        switch (i) {
            case R.id.option_1_radiobutton /* 2131364808 */:
                this$0.q = 30;
                break;
            case R.id.option_2_radiobutton /* 2131364809 */:
                this$0.q = 60;
                break;
            case R.id.option_3_radiobutton /* 2131364810 */:
                this$0.q = 90;
                break;
            case R.id.option_4_radiobutton /* 2131364811 */:
                this$0.q = 120;
                break;
        }
        this$0.buttonVisible();
    }

    public static final void D(ActivityAutoStressHRVSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y();
    }

    public static final void H(final ActivityAutoStressHRVSettings this$0, CompoundButton compoundButton, final boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            ((LinearLayout) this$0._$_findCachedViewById(R.id.auto_stress_lay)).setVisibility(0);
            int i = R.id.option_1_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i)).setEnabled(true);
            int i2 = R.id.option_2_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i2)).setEnabled(true);
            int i3 = R.id.option_3_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i3)).setEnabled(true);
            int i4 = R.id.option_4_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i4)).setEnabled(true);
            ((RadioButton) this$0._$_findCachedViewById(i)).setAlpha(1.0f);
            ((RadioButton) this$0._$_findCachedViewById(i2)).setAlpha(1.0f);
            ((RadioButton) this$0._$_findCachedViewById(i3)).setAlpha(1.0f);
            ((RadioButton) this$0._$_findCachedViewById(i4)).setAlpha(1.0f);
        } else {
            ((LinearLayout) this$0._$_findCachedViewById(R.id.auto_stress_lay)).setVisibility(8);
            int i5 = R.id.option_1_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i5)).setEnabled(false);
            int i6 = R.id.option_2_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i6)).setEnabled(false);
            int i7 = R.id.option_3_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i7)).setEnabled(false);
            int i8 = R.id.option_4_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i8)).setEnabled(false);
            ((RadioButton) this$0._$_findCachedViewById(i5)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i6)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i7)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i8)).setAlpha(0.35f);
        }
        if (BleApiManager.getInstance(this$0) != null && BleApiManager.getInstance(this$0).getBleApi() != null && BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            BatterySaverModeHelper.Companion.getInstance(this$0).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAutoStressHRVSettings$setOnChangeCheckListenerforStressHRV$1$1
                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySaverCMDFailed() {
                    Toast.makeText(ActivityAutoStressHRVSettings.this, (int) R.string.somethings_went_wrong, 0).show();
                }

                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySavingSettingsReceived(boolean z2, int i9) {
                    if (z2) {
                        ActivityAutoStressHRVSettings activityAutoStressHRVSettings = ActivityAutoStressHRVSettings.this;
                        int i10 = R.id.switch_reminder;
                        ((SwitchCompat) activityAutoStressHRVSettings._$_findCachedViewById(i10)).setOnCheckedChangeListener(null);
                        ((SwitchCompat) ActivityAutoStressHRVSettings.this._$_findCachedViewById(i10)).setChecked(false);
                        ActivityAutoStressHRVSettings activityAutoStressHRVSettings2 = ActivityAutoStressHRVSettings.this;
                        activityAutoStressHRVSettings2.showBatterySaverModeEnabledDialog(activityAutoStressHRVSettings2);
                        return;
                    }
                    ActivityAutoStressHRVSettings.this.r = z;
                    ActivityAutoStressHRVSettings.this.buttonVisible();
                }
            });
            return;
        }
        this$0.r = z;
        this$0.buttonVisible();
    }

    public static final void I(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityAutoStressHRVSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void J(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityAutoStressHRVSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void z(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityAutoStressHRVSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.finish();
    }

    public final void E() {
        initToolbar();
        F();
        initClickListeners();
    }

    public final void F() {
        setAutoStressSettingsDataCommon(getAutoStressSettingsViewModel().getStressSettingsDataFromPref());
        if (getAutoStressSettingsDataCommon().getAutoStress()) {
            ((LinearLayout) _$_findCachedViewById(R.id.auto_stress_lay)).setVisibility(0);
            ((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).setChecked(true);
            int i = R.id.option_1_radiobutton;
            ((RadioButton) _$_findCachedViewById(i)).setEnabled(true);
            int i2 = R.id.option_2_radiobutton;
            ((RadioButton) _$_findCachedViewById(i2)).setEnabled(true);
            int i3 = R.id.option_3_radiobutton;
            ((RadioButton) _$_findCachedViewById(i3)).setEnabled(true);
            int i4 = R.id.option_4_radiobutton;
            ((RadioButton) _$_findCachedViewById(i4)).setEnabled(true);
            ((RadioButton) _$_findCachedViewById(i)).setAlpha(1.0f);
            ((RadioButton) _$_findCachedViewById(i2)).setAlpha(1.0f);
            ((RadioButton) _$_findCachedViewById(i3)).setAlpha(1.0f);
            ((RadioButton) _$_findCachedViewById(i4)).setAlpha(1.0f);
        } else {
            ((LinearLayout) _$_findCachedViewById(R.id.auto_stress_lay)).setVisibility(8);
            ((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).setChecked(false);
            int i5 = R.id.option_1_radiobutton;
            ((RadioButton) _$_findCachedViewById(i5)).setEnabled(false);
            int i6 = R.id.option_2_radiobutton;
            ((RadioButton) _$_findCachedViewById(i6)).setEnabled(false);
            int i7 = R.id.option_3_radiobutton;
            ((RadioButton) _$_findCachedViewById(i7)).setEnabled(false);
            int i8 = R.id.option_4_radiobutton;
            ((RadioButton) _$_findCachedViewById(i8)).setEnabled(false);
            ((RadioButton) _$_findCachedViewById(i5)).setAlpha(0.35f);
            ((RadioButton) _$_findCachedViewById(i6)).setAlpha(0.35f);
            ((RadioButton) _$_findCachedViewById(i7)).setAlpha(0.35f);
            ((RadioButton) _$_findCachedViewById(i8)).setAlpha(0.35f);
        }
        this.q = getAutoStressSettingsDataCommon().getMeasuringInterval();
        this.r = getAutoStressSettingsDataCommon().getAutoStress();
        int i9 = this.q;
        if (i9 == 30) {
            ((RadioButton) _$_findCachedViewById(R.id.option_1_radiobutton)).setChecked(true);
        } else if (i9 == 60) {
            ((RadioButton) _$_findCachedViewById(R.id.option_2_radiobutton)).setChecked(true);
        } else if (i9 == 90) {
            ((RadioButton) _$_findCachedViewById(R.id.option_3_radiobutton)).setChecked(true);
        } else if (i9 != 120) {
            ((RadioButton) _$_findCachedViewById(R.id.option_2_radiobutton)).setChecked(true);
            this.q = 60;
        } else {
            ((RadioButton) _$_findCachedViewById(R.id.option_4_radiobutton)).setChecked(true);
        }
    }

    public final void G() {
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.g1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoStressHRVSettings.H(ActivityAutoStressHRVSettings.this, compoundButton, z);
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

    public final void buttonVisible() {
        if (!Boolean.valueOf(((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).isChecked()).equals(Boolean.valueOf(getAutoStressSettingsDataCommon().getAutoStress()))) {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
            this.p = true;
        }
        if (this.q != getAutoStressSettingsDataCommon().getMeasuringInterval()) {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
            this.p = true;
        }
    }

    @NotNull
    public final AutoStressSettingsData getAutoStressSettingsDataCommon() {
        AutoStressSettingsData autoStressSettingsData = this.autoStressSettingsDataCommon;
        if (autoStressSettingsData != null) {
            return autoStressSettingsData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("autoStressSettingsDataCommon");
        return null;
    }

    @NotNull
    public final AutoStressSettingsViewModel getAutoStressSettingsViewModel() {
        AutoStressSettingsViewModel autoStressSettingsViewModel = this.autoStressSettingsViewModel;
        if (autoStressSettingsViewModel != null) {
            return autoStressSettingsViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("autoStressSettingsViewModel");
        return null;
    }

    public final boolean getBoolSaveVisible() {
        return this.p;
    }

    public final boolean getHasInfoChanged() {
        return this.s;
    }

    @Nullable
    public final TimePickerDialog getTimePickerDialogEndTime() {
        return this.u;
    }

    @Nullable
    public final TimePickerDialog getTimePickerDialogStartTime() {
        return this.t;
    }

    public final void initClickListeners() {
        ((RadioGroup) _$_findCachedViewById(R.id.radioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.h1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                ActivityAutoStressHRVSettings.C(ActivityAutoStressHRVSettings.this, radioGroup, i);
            }
        });
        G();
        Button btnSave = (Button) _$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        ViewUtilsKt.setSafeOnClickListener(btnSave, new a());
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.auto_stress_hrv));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.a1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoStressHRVSettings.D(ActivityAutoStressHRVSettings.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        y();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_auto_stress_hrv_settings);
        setAutoStressSettingsViewModel((AutoStressSettingsViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(AutoStressSettingsViewModel.class));
        getAutoStressSettingsViewModel().setDialogListener(this);
        E();
        if (BleApiManager.getInstance(this) == null || BleApiManager.getInstance(this).getBleApi() == null || !BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            return;
        }
        showProgress();
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).setOnCheckedChangeListener(null);
        BatterySaverModeHelper.Companion.getInstance(this).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAutoStressHRVSettings$onCreate$1
            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySaverCMDFailed() {
                ActivityAutoStressHRVSettings.this.dismissProgress();
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySavingSettingsReceived(boolean z, int i) {
                if (z) {
                    ((SwitchCompat) ActivityAutoStressHRVSettings.this._$_findCachedViewById(R.id.switch_reminder)).setChecked(false);
                }
                ActivityAutoStressHRVSettings.this.G();
                ActivityAutoStressHRVSettings.this.dismissProgress();
            }
        });
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onNegativeButtonClicked() {
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onPositiveButtonClicked() {
        if (isFinishing()) {
            return;
        }
        G();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setAutoStressSettingsDataCommon(@NotNull AutoStressSettingsData autoStressSettingsData) {
        Intrinsics.checkNotNullParameter(autoStressSettingsData, "<set-?>");
        this.autoStressSettingsDataCommon = autoStressSettingsData;
    }

    public final void setAutoStressSettingsViewModel(@NotNull AutoStressSettingsViewModel autoStressSettingsViewModel) {
        Intrinsics.checkNotNullParameter(autoStressSettingsViewModel, "<set-?>");
        this.autoStressSettingsViewModel = autoStressSettingsViewModel;
    }

    public final void setBoolSaveVisible(boolean z) {
        this.p = z;
    }

    public final void setHasInfoChanged(boolean z) {
        this.s = z;
    }

    public final void setTimePickerDialogEndTime(@Nullable TimePickerDialog timePickerDialog) {
        this.u = timePickerDialog;
    }

    public final void setTimePickerDialogStartTime(@Nullable TimePickerDialog timePickerDialog) {
        this.t = timePickerDialog;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.c1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoStressHRVSettings.I(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        if (bottomSheetDialogOneButtonOneTitle.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …R.string.ok\n            )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.b1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoStressHRVSettings.J(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        if (bottomSheetDialogOneButtonOneTitle.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void y() {
        this.s = false;
        AutoStressSettingsData stressSettingsDataFromPref = getAutoStressSettingsViewModel().getStressSettingsDataFromPref();
        if (stressSettingsDataFromPref.getAutoStress() != this.r) {
            this.s = true;
        }
        if (this.q != stressSettingsDataFromPref.getMeasuringInterval()) {
            this.s = true;
        }
        if (this.s) {
            if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.DISCONNECTED) {
                String string = getString(R.string.band_not_connected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                String string2 = getString(R.string.please_connect_the_device);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
                String string3 = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.d1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAutoStressHRVSettings.z(BottomSheetDialogOneButtonTitleMessage.this, this, view);
                    }
                });
                if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
                    return;
                }
                bottomSheetDialogOneButtonTitleMessage.show();
                return;
            }
            String string4 = getString(R.string.auto_stress_hrv);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.auto_stress_hrv)");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string5 = getString(R.string.save_changes_hr);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.save_changes_hr)");
            String format = String.format(string5, Arrays.copyOf(new Object[]{getString(R.string.auto_stress_hrv)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string4, format, false, 8, null);
            String string6 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons.setPositiveButton(string6, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.f1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAutoStressHRVSettings.A(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            String string7 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string7, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.e1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAutoStressHRVSettings.B(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            if (bottomSheetDialogTwoButtons.isShowing()) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }
}
