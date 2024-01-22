package com.coveiot.android.femalewellness.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.femalewellness.BatterySaverModeFemaleWellnessHelper;
import com.coveiot.android.femalewellness.Constants;
import com.coveiot.android.femalewellness.DialogListener;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.Utils;
import com.coveiot.android.femalewellness.ViewModelFactory;
import com.coveiot.android.femalewellness.utils.ViewUtilsKt;
import com.coveiot.android.femalewellness.viewmodel.WomenWellnessViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.coveiot.utils.utility.AppUtils;
import com.jstyle.blesdk1860.constant.BleConst;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ActivityFemaleWellnessSettings extends BaseActivity implements DialogListener, BatterySaverModeDialogClickCallback {
    public WomenWellnessViewModel p;
    public boolean q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public WomenWellnessData womenWellnessDataCommon;
    public int x;
    public int y;
    public int z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final SimpleDateFormat A = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);

    /* loaded from: classes4.dex */
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
            if (ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().getLastPeriodYear() != 0) {
                Object clone = Calendar.getInstance().clone();
                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                Calendar calendar = (Calendar) clone;
                calendar.set(5, ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().getLastPeriodDay());
                calendar.set(1, ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().getLastPeriodYear());
                calendar.set(2, ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().getLastPeriodMonth() - 1);
                Utils.Companion companion = Utils.Companion;
                Date time = calendar.getTime();
                Intrinsics.checkNotNullExpressionValue(time, "cal.time");
                Date time2 = Calendar.getInstance().getTime();
                Intrinsics.checkNotNullExpressionValue(time2, "getInstance().time");
                if (companion.getDayDifference(time, time2) > 30) {
                    ActivityFemaleWellnessSettings activityFemaleWellnessSettings = ActivityFemaleWellnessSettings.this;
                    Toast.makeText(activityFemaleWellnessSettings, activityFemaleWellnessSettings.getString(R.string.can_not_select_more_than_30days), 1).show();
                    return;
                } else if (!AppUtils.isNetConnected(ActivityFemaleWellnessSettings.this)) {
                    ActivityFemaleWellnessSettings.this.showNoInternetMessage();
                    return;
                } else {
                    WomenWellnessViewModel womenWellnessViewModel = null;
                    if (BleApiManager.getInstance(ActivityFemaleWellnessSettings.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        if (UserDataManager.getInstance(ActivityFemaleWellnessSettings.this).getWomenWellnessData() == null) {
                            WomenWellnessViewModel womenWellnessViewModel2 = ActivityFemaleWellnessSettings.this.p;
                            if (womenWellnessViewModel2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            } else {
                                womenWellnessViewModel = womenWellnessViewModel2;
                            }
                            womenWellnessViewModel.sendWomenWellnessDataToBand(ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon());
                            return;
                        }
                        CharSequence text = ((TextView) ActivityFemaleWellnessSettings.this._$_findCachedViewById(R.id.last_menstrual_data_tv)).getText();
                        ActivityFemaleWellnessSettings activityFemaleWellnessSettings2 = ActivityFemaleWellnessSettings.this;
                        if (Intrinsics.areEqual(text, activityFemaleWellnessSettings2.I(activityFemaleWellnessSettings2.getMLastPeriodDay(), ActivityFemaleWellnessSettings.this.getMLastPeriodMonth() - 1, ActivityFemaleWellnessSettings.this.getMLastPeriodYear()))) {
                            WomenWellnessViewModel womenWellnessViewModel3 = ActivityFemaleWellnessSettings.this.p;
                            if (womenWellnessViewModel3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            } else {
                                womenWellnessViewModel = womenWellnessViewModel3;
                            }
                            womenWellnessViewModel.sendWomenWellnessDataToBand(ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon());
                            return;
                        }
                        ActivityFemaleWellnessSettings.this.showConfirmationDialog();
                        return;
                    }
                    BaseActivity.showBandNotConnected$default(ActivityFemaleWellnessSettings.this, false, 1, null);
                    return;
                }
            }
            ActivityFemaleWellnessSettings activityFemaleWellnessSettings3 = ActivityFemaleWellnessSettings.this;
            Toast.makeText(activityFemaleWellnessSettings3, activityFemaleWellnessSettings3.getString(R.string.select_last_menstrual_date), 1).show();
        }
    }

    public static final void L(final ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0) != null && BleApiManager.getInstance(this$0).getBleApi() != null && BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            BatterySaverModeFemaleWellnessHelper.Companion.getInstance(this$0).getBatterySaverMode(new BatterySaverModeFemaleWellnessHelper.BatterySaverModeListener() { // from class: com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessSettings$initClickListeners$1$1
                @Override // com.coveiot.android.femalewellness.BatterySaverModeFemaleWellnessHelper.BatterySaverModeListener
                public void onBatterySavingSettingsReceived(boolean z, int i) {
                    if (z && i == 1) {
                        ((SwitchCompat) ActivityFemaleWellnessSettings.this._$_findCachedViewById(R.id.switch_tracker)).setChecked(false);
                        ActivityFemaleWellnessSettings activityFemaleWellnessSettings = ActivityFemaleWellnessSettings.this;
                        activityFemaleWellnessSettings.showBatterySaverModeEnabledDialog(activityFemaleWellnessSettings);
                        return;
                    }
                    ActivityFemaleWellnessSettings activityFemaleWellnessSettings2 = ActivityFemaleWellnessSettings.this;
                    activityFemaleWellnessSettings2.setMEnabled(((SwitchCompat) activityFemaleWellnessSettings2._$_findCachedViewById(R.id.switch_tracker)).isChecked());
                    ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().setEnabled(ActivityFemaleWellnessSettings.this.getMEnabled());
                    ActivityFemaleWellnessSettings activityFemaleWellnessSettings3 = ActivityFemaleWellnessSettings.this;
                    activityFemaleWellnessSettings3.a0(activityFemaleWellnessSettings3.getMEnabled());
                    ActivityFemaleWellnessSettings.this.buttonVisible();
                }
            });
            return;
        }
        this$0.q = ((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_tracker)).isChecked();
        this$0.getWomenWellnessDataCommon().setEnabled(this$0.q);
        this$0.a0(this$0.q);
        this$0.buttonVisible();
    }

    public static final void M(ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (DeviceUtils.Companion.isEastApexDevice(this$0)) {
            this$0.q0();
            this$0.f0();
        } else if (this$0.q) {
            this$0.q0();
            this$0.f0();
        } else {
            this$0.p0();
        }
    }

    public static final void N(ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (DeviceUtils.Companion.isEastApexDevice(this$0)) {
            this$0.j0();
        } else if (this$0.q) {
            this$0.j0();
        } else {
            this$0.p0();
        }
    }

    public static final void O(ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.q) {
            this$0.i0();
        } else {
            this$0.p0();
        }
    }

    public static final void P(ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.q) {
            this$0.k0();
        } else {
            this$0.p0();
        }
    }

    public static final void Q(ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (DeviceUtils.Companion.isEastApexDevice(this$0)) {
            this$0.d0();
        } else if (this$0.q) {
            this$0.d0();
        } else {
            this$0.p0();
        }
    }

    public static final void R(ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.q) {
            this$0.l0();
        } else {
            this$0.p0();
        }
    }

    public static final void S(ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void U(ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Button button = (Button) this$0._$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNull(button);
        button.performClick();
    }

    public static final void V(BottomSheetDialogTwoButtons dialog, ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isEastApexDevice(this$0)) {
            this$0.finish();
        } else if (!companion.isCADevice(this$0) && !companion.isCYDevice(this$0) && !companion.isPS1Device(this$0) && !companion.isBESDevice(this$0)) {
            this$0.finish();
        } else {
            this$0.startActivity(new Intent(this$0, ActivityFemaleWellnessCalendarView.class));
            this$0.finish();
        }
    }

    public static final void b0(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogTwoButtons.dismiss();
        WomenWellnessViewModel womenWellnessViewModel = this$0.p;
        if (womenWellnessViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel = null;
        }
        womenWellnessViewModel.sendWomenWellnessDataToBand(this$0.getWomenWellnessDataCommon());
    }

    public static final void c0(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void e0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void g0(ActivityFemaleWellnessSettings this$0, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.T();
        ((Dialog) dialog.element).dismiss();
    }

    public static final void h0(Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((Dialog) dialog.element).dismiss();
    }

    public static final void m0(ActivityFemaleWellnessSettings this$0, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.X();
        ((Dialog) dialog.element).dismiss();
    }

    public static final void n0(Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((Dialog) dialog.element).dismiss();
    }

    public static final void o0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityFemaleWellnessSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public final String I(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i3, i2, i);
        String date = AppUtils.getSimpleDateFormat("dd MMM, yyyy").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(date, "date");
        return date;
    }

    public final boolean J() {
        String str;
        int i;
        WomenWellnessData womenWellnessData = UserDataManager.getInstance(this).getWomenWellnessData();
        int reminderHour = womenWellnessData != null ? womenWellnessData.getReminderHour() : 9;
        int reminderMinute = womenWellnessData != null ? womenWellnessData.getReminderMinute() : 0;
        boolean isEnabled = womenWellnessData != null ? womenWellnessData.isEnabled() : false;
        int menstruationPeriodLength = womenWellnessData != null ? womenWellnessData.getMenstruationPeriodLength() : 5;
        int menstruationCycleLength = womenWellnessData != null ? womenWellnessData.getMenstruationCycleLength() : 28;
        int ovulationReminderAdvance = womenWellnessData != null ? womenWellnessData.getOvulationReminderAdvance() : 2;
        int periodReminderAdvance = womenWellnessData != null ? womenWellnessData.getPeriodReminderAdvance() : 2;
        if (reminderHour > 12) {
            str = ' ' + getResources().getString(R.string.PM);
            reminderHour -= 12;
        } else if (reminderHour == 12) {
            str = ' ' + getResources().getString(R.string.PM);
        } else {
            if (reminderHour == 0) {
                reminderHour = 12;
            }
            str = ' ' + getResources().getString(R.string.AM);
        }
        CharSequence text = ((TextView) _$_findCachedViewById(R.id.reminder_time_value)).getText();
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(reminderHour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(reminderMinute)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        if (Intrinsics.areEqual(text, sb.toString()) && ((SwitchCompat) _$_findCachedViewById(R.id.switch_tracker)).isChecked() == isEnabled) {
            CharSequence text2 = ((TextView) _$_findCachedViewById(R.id.period_value_tv)).getText();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(menstruationPeriodLength);
            sb2.append(' ');
            sb2.append(getResources().getString(R.string.day_s));
            if (Intrinsics.areEqual(text2, sb2.toString())) {
                if (Intrinsics.areEqual(((TextView) _$_findCachedViewById(R.id.cycle_value_tv)).getText(), menstruationCycleLength + ' ' + getResources().getString(i))) {
                    if (Intrinsics.areEqual(((TextView) _$_findCachedViewById(R.id.select_ovulation_reminder_img)).getText(), ovulationReminderAdvance + ' ' + getResources().getString(i))) {
                        if (Intrinsics.areEqual(((TextView) _$_findCachedViewById(R.id.select_period_reminder_img)).getText(), periodReminderAdvance + ' ' + getResources().getString(i))) {
                            int i2 = R.id.last_menstrual_data_tv;
                            return (Intrinsics.areEqual(((TextView) _$_findCachedViewById(i2)).getText(), getResources().getString(R.string.select)) || Intrinsics.areEqual(((TextView) _$_findCachedViewById(i2)).getText(), I(this.v, this.u - 1, this.t))) ? false : true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    public final void K() {
        if (DeviceUtils.Companion.isEastApexDevice(this)) {
            ((ConstraintLayout) _$_findCachedViewById(R.id.linear_layout_wellness_reminder)).setVisibility(8);
            ((ConstraintLayout) _$_findCachedViewById(R.id.wellness_switch)).setVisibility(8);
        }
    }

    public final void T() {
        WomenWellnessData womenWellnessDataCommon = getWomenWellnessDataCommon();
        WomenWellnessViewModel womenWellnessViewModel = this.p;
        WomenWellnessViewModel womenWellnessViewModel2 = null;
        if (womenWellnessViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel = null;
        }
        womenWellnessDataCommon.setLastPeriodYear(Integer.parseInt(womenWellnessViewModel.getYear_var()));
        WomenWellnessData womenWellnessDataCommon2 = getWomenWellnessDataCommon();
        Utils.Companion companion = Utils.Companion;
        WomenWellnessViewModel womenWellnessViewModel3 = this.p;
        if (womenWellnessViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel3 = null;
        }
        womenWellnessDataCommon2.setLastPeriodMonth(companion.getMonthFromStringMMMM(womenWellnessViewModel3.getMonth_var()) + 1);
        WomenWellnessData womenWellnessDataCommon3 = getWomenWellnessDataCommon();
        WomenWellnessViewModel womenWellnessViewModel4 = this.p;
        if (womenWellnessViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            womenWellnessViewModel2 = womenWellnessViewModel4;
        }
        womenWellnessDataCommon3.setLastPeriodDay(Integer.parseInt(womenWellnessViewModel2.getDate_var()));
        ((TextView) _$_findCachedViewById(R.id.last_menstrual_data_tv)).setText(I(getWomenWellnessDataCommon().getLastPeriodDay(), getWomenWellnessDataCommon().getLastPeriodMonth() - 1, getWomenWellnessDataCommon().getLastPeriodYear()));
        buttonVisible();
    }

    public final void W() {
        String valueOf;
        WomenWellnessViewModel womenWellnessViewModel = this.p;
        WomenWellnessViewModel womenWellnessViewModel2 = null;
        if (womenWellnessViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel = null;
        }
        WomenWellnessData womenWellnessDataFromPref = womenWellnessViewModel.getWomenWellnessDataFromPref();
        WomenWellnessViewModel womenWellnessViewModel3 = this.p;
        if (womenWellnessViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel3 = null;
        }
        setWomenWellnessDataCommon(womenWellnessViewModel3.getWomenWellnessDataFromPref());
        this.q = womenWellnessDataFromPref.isEnabled();
        this.r = womenWellnessDataFromPref.getReminderHour();
        this.s = womenWellnessDataFromPref.getReminderMinute();
        this.y = womenWellnessDataFromPref.getPeriodReminderAdvance();
        this.z = womenWellnessDataFromPref.getOvulationReminderAdvance();
        this.t = womenWellnessDataFromPref.getLastPeriodYear();
        this.u = womenWellnessDataFromPref.getLastPeriodMonth();
        this.v = womenWellnessDataFromPref.getLastPeriodDay();
        this.w = womenWellnessDataFromPref.getMenstruationCycleLength();
        this.x = womenWellnessDataFromPref.getMenstruationPeriodLength();
        WomenWellnessViewModel womenWellnessViewModel4 = this.p;
        if (womenWellnessViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel4 = null;
        }
        int i = this.r;
        if (i == 0) {
            valueOf = BleConst.CMD_Reset;
        } else if (i > 12) {
            valueOf = String.valueOf(i - 12);
        } else {
            valueOf = String.valueOf(i);
        }
        womenWellnessViewModel4.setHour_var(valueOf);
        WomenWellnessViewModel womenWellnessViewModel5 = this.p;
        if (womenWellnessViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel5 = null;
        }
        womenWellnessViewModel5.setMin_var(String.valueOf(this.s));
        if (this.r >= 12) {
            WomenWellnessViewModel womenWellnessViewModel6 = this.p;
            if (womenWellnessViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                womenWellnessViewModel2 = womenWellnessViewModel6;
            }
            womenWellnessViewModel2.setAm_pm_var("PM");
        } else {
            WomenWellnessViewModel womenWellnessViewModel7 = this.p;
            if (womenWellnessViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                womenWellnessViewModel2 = womenWellnessViewModel7;
            }
            womenWellnessViewModel2.setAm_pm_var("AM");
        }
        Y();
    }

    public final void X() {
        Calendar calendar = Calendar.getInstance();
        WomenWellnessViewModel womenWellnessViewModel = this.p;
        WomenWellnessViewModel womenWellnessViewModel2 = null;
        if (womenWellnessViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel = null;
        }
        int parseInt = Integer.parseInt(womenWellnessViewModel.getHour_var());
        WomenWellnessViewModel womenWellnessViewModel3 = this.p;
        if (womenWellnessViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel3 = null;
        }
        boolean z = !kotlin.text.m.equals(womenWellnessViewModel3.getAm_pm_var(), "AM", true);
        if (parseInt == 12) {
            if (!z) {
                parseInt = 0;
            }
        } else if (z) {
            parseInt += 12;
        }
        calendar.set(11, parseInt);
        WomenWellnessViewModel womenWellnessViewModel4 = this.p;
        if (womenWellnessViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            womenWellnessViewModel2 = womenWellnessViewModel4;
        }
        calendar.set(12, Integer.parseInt(womenWellnessViewModel2.getMin_var()));
        getWomenWellnessDataCommon().setReminderHour(calendar.get(11));
        getWomenWellnessDataCommon().setReminderMinute(calendar.get(12));
        ((TextView) _$_findCachedViewById(R.id.reminder_time_value)).setText(this.A.format(Long.valueOf(calendar.getTimeInMillis())));
        buttonVisible();
    }

    public final void Y() {
        int i;
        String str;
        int i2;
        if (this.t != 0) {
            ((TextView) _$_findCachedViewById(R.id.last_menstrual_data_tv)).setText(I(this.v, this.u - 1, this.t));
        }
        int i3 = this.r;
        if (i3 > 12) {
            str = ' ' + getResources().getString(R.string.PM);
            i = this.r - 12;
        } else if (i3 == 12) {
            str = ' ' + getResources().getString(R.string.PM);
            i = this.r;
        } else {
            if (i3 == 0) {
                i3 = 12;
            }
            i = i3;
            str = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.s)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        ((TextView) _$_findCachedViewById(R.id.reminder_time_value)).setText(sb.toString());
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_tracker)).setChecked(getWomenWellnessDataCommon().isEnabled());
        a0(getWomenWellnessDataCommon().isEnabled());
        ((ImageView) _$_findCachedViewById(R.id.ovulation_reminder_image)).setImageResource(R.drawable.ovulation_reminder_icon);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getWomenWellnessDataCommon().getMenstruationPeriodLength());
        sb2.append(' ');
        sb2.append(getResources().getString(R.string.day_s));
        ((TextView) _$_findCachedViewById(R.id.period_value_tv)).setText(sb2.toString());
        ((TextView) _$_findCachedViewById(R.id.cycle_value_tv)).setText(getWomenWellnessDataCommon().getMenstruationCycleLength() + ' ' + getResources().getString(i2));
        ((TextView) _$_findCachedViewById(R.id.select_ovulation_reminder_img)).setText(getWomenWellnessDataCommon().getOvulationReminderAdvance() + ' ' + getResources().getString(i2));
        ((TextView) _$_findCachedViewById(R.id.select_period_reminder_img)).setText(getWomenWellnessDataCommon().getPeriodReminderAdvance() + ' ' + getResources().getString(i2));
    }

    public final void Z(View view, float f) {
        if (!(view instanceof ViewGroup)) {
            return;
        }
        int i = 0;
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        if (childCount < 0) {
            return;
        }
        while (true) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                Z(childAt, f);
            } else if (childAt instanceof TextView) {
                ((TextView) childAt).setAlpha(f);
            }
            if (i == childCount) {
                return;
            }
            i++;
        }
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

    public final void a0(boolean z) {
        if (DeviceUtils.Companion.isEastApexDevice(this)) {
            return;
        }
        if (z) {
            int i = R.id.wellnessLayout;
            ConstraintLayout wellnessLayout = (ConstraintLayout) _$_findCachedViewById(i);
            Intrinsics.checkNotNullExpressionValue(wellnessLayout, "wellnessLayout");
            Z(wellnessLayout, 1.0f);
            ((ConstraintLayout) _$_findCachedViewById(i)).setAlpha(1.0f);
            return;
        }
        int i2 = R.id.wellnessLayout;
        ConstraintLayout wellnessLayout2 = (ConstraintLayout) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(wellnessLayout2, "wellnessLayout");
        Z(wellnessLayout2, 0.5f);
        ((ConstraintLayout) _$_findCachedViewById(i2)).setAlpha(0.5f);
    }

    public final void buttonVisible() {
        if (J()) {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
        } else {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(false);
        }
    }

    public final void d0() {
        int parseInt;
        int parseInt2;
        if (DeviceUtils.Companion.isTouchELXDevice(this)) {
            parseInt = Integer.parseInt(Constants.CYCLE_START_RANGE_TOUCHELEX.getValue());
            parseInt2 = Integer.parseInt(Constants.CYCLE_END_RANGE_TOUCHELEX.getValue());
        } else {
            parseInt = Integer.parseInt(Constants.CYCLE_START_RANGE.getValue());
            parseInt2 = Integer.parseInt(Constants.CYCLE_END_RANGE.getValue());
        }
        PickerDialog.Companion companion = PickerDialog.Companion;
        String string = getString(R.string.cycle_length);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.cycle_length)");
        String string2 = getString(R.string.day_s);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.day_s)");
        companion.dataPickerWeight(this, string, string2, parseInt, parseInt2, 1, this.w, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessSettings$showCycleLengthPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().setMenstruationCycleLength(Integer.parseInt(value));
                ((TextView) ActivityFemaleWellnessSettings.this._$_findCachedViewById(R.id.cycle_value_tv)).setText(ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().getMenstruationCycleLength() + ' ' + ActivityFemaleWellnessSettings.this.getResources().getString(R.string.day_s));
                ActivityFemaleWellnessSettings activityFemaleWellnessSettings = ActivityFemaleWellnessSettings.this;
                activityFemaleWellnessSettings.setMCycleLength(activityFemaleWellnessSettings.getWomenWellnessDataCommon().getMenstruationCycleLength());
                ActivityFemaleWellnessSettings.this.buttonVisible();
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, android.app.Dialog] */
    public final void f0() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? dialog = new Dialog(this, R.style.GenericDialog);
        objectRef.element = dialog;
        ((Dialog) dialog).setContentView(R.layout.menstrual_cycle_popup);
        ((Dialog) objectRef.element).setCancelable(true);
        ((Dialog) objectRef.element).setCanceledOnTouchOutside(true);
        Window window = ((Dialog) objectRef.element).getWindow();
        Intrinsics.checkNotNull(window);
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Intrinsics.checkNotNullExpressionValue(attributes, "window.attributes");
            attributes.width = -1;
            attributes.height = -2;
            attributes.gravity = 17;
            window.setAttributes(attributes);
        }
        ((Dialog) objectRef.element).show();
        ((TextView) ((Dialog) objectRef.element).findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.p0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.g0(ActivityFemaleWellnessSettings.this, objectRef, view);
            }
        });
        ((TextView) ((Dialog) objectRef.element).findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.h0(Ref.ObjectRef.this, view);
            }
        });
        WomenWellnessViewModel womenWellnessViewModel = this.p;
        WomenWellnessViewModel womenWellnessViewModel2 = null;
        if (womenWellnessViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel = null;
        }
        womenWellnessViewModel.populateYearsDataInView((Dialog) objectRef.element);
        WomenWellnessViewModel womenWellnessViewModel3 = this.p;
        if (womenWellnessViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel3 = null;
        }
        womenWellnessViewModel3.populateMonthsDataInView((Dialog) objectRef.element);
        WomenWellnessViewModel womenWellnessViewModel4 = this.p;
        if (womenWellnessViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            womenWellnessViewModel2 = womenWellnessViewModel4;
        }
        womenWellnessViewModel2.populateDaysDataInView((Dialog) objectRef.element);
    }

    public final int getMCycleLength() {
        return this.w;
    }

    public final boolean getMEnabled() {
        return this.q;
    }

    public final int getMLastPeriodDay() {
        return this.v;
    }

    public final int getMLastPeriodMonth() {
        return this.u;
    }

    public final int getMLastPeriodYear() {
        return this.t;
    }

    public final int getMOvulationReminderAdvance() {
        return this.z;
    }

    public final int getMPeriodLength() {
        return this.x;
    }

    public final int getMPeriodReminderAdvance() {
        return this.y;
    }

    public final int getMReminderHour() {
        return this.r;
    }

    public final int getMReminderMinute() {
        return this.s;
    }

    @NotNull
    public final SimpleDateFormat getSdf() {
        return this.A;
    }

    @NotNull
    public final WomenWellnessData getWomenWellnessDataCommon() {
        WomenWellnessData womenWellnessData = this.womenWellnessDataCommon;
        if (womenWellnessData != null) {
            return womenWellnessData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("womenWellnessDataCommon");
        return null;
    }

    public final void i0() {
        String string = getString(R.string.ovulation_reminder);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ovulation_reminder)");
        if (DeviceUtils.Companion.isTouchELXDevice(this)) {
            string = getString(R.string.fertile_reminder);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fertile_reminder)");
        }
        PickerDialog.Companion companion = PickerDialog.Companion;
        String string2 = getString(R.string.day_s);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.day_s)");
        companion.dataPickerWeight(this, string, string2, Integer.parseInt(Constants.PERIOD_OVULATION_REMINDER_START_RANGE.getValue()), Integer.parseInt(Constants.PERIOD_OVULATION_REMINDER_END_RANGE.getValue()), 1, this.z, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessSettings$showOvulationReminderPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().setOvulationReminderAdvance(Integer.parseInt(value));
                ((TextView) ActivityFemaleWellnessSettings.this._$_findCachedViewById(R.id.select_ovulation_reminder_img)).setText(ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().getOvulationReminderAdvance() + ' ' + ActivityFemaleWellnessSettings.this.getResources().getString(R.string.day_s));
                ActivityFemaleWellnessSettings activityFemaleWellnessSettings = ActivityFemaleWellnessSettings.this;
                activityFemaleWellnessSettings.setMOvulationReminderAdvance(activityFemaleWellnessSettings.getWomenWellnessDataCommon().getOvulationReminderAdvance());
                ActivityFemaleWellnessSettings.this.buttonVisible();
            }
        });
    }

    public final void initClickListeners() {
        if (DeviceUtils.Companion.isMatrixDevice(this)) {
            ((ConstraintLayout) _$_findCachedViewById(R.id.cl_ovulation_reminder)).setVisibility(8);
        } else {
            ((ConstraintLayout) _$_findCachedViewById(R.id.cl_ovulation_reminder)).setVisibility(0);
            ((ImageView) _$_findCachedViewById(R.id.ovulation_reminder_image)).setBackgroundResource(R.drawable.ovulation_reminder_icon);
        }
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_tracker)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.L(ActivityFemaleWellnessSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_last_menstrual_date)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.M(ActivityFemaleWellnessSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_period_length)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.o0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.N(ActivityFemaleWellnessSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_ovulation_reminder)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.m0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.O(ActivityFemaleWellnessSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_period_reminder)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.P(ActivityFemaleWellnessSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_cycle_length)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.Q(ActivityFemaleWellnessSettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_reminder_time)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.R(ActivityFemaleWellnessSettings.this, view);
            }
        });
        Button btnSave = (Button) _$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        ViewUtilsKt.setSafeOnClickListener(btnSave, new a());
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.wellness_settings));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.k0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.S(ActivityFemaleWellnessSettings.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(false);
    }

    public final void j0() {
        int parseInt;
        int parseInt2;
        if (DeviceUtils.Companion.isTouchELXDevice(this)) {
            parseInt = Integer.parseInt(Constants.PERIOD_START_RANGE_TOUCHELEX.getValue());
            parseInt2 = Integer.parseInt(Constants.PERIOD_END_RANGE_TOUCHELEX.getValue());
        } else {
            parseInt = Integer.parseInt(Constants.PERIOD_START_RANGE.getValue());
            parseInt2 = Integer.parseInt(Constants.PERIOD_END_RANGE.getValue());
        }
        PickerDialog.Companion companion = PickerDialog.Companion;
        String string = getString(R.string.period_length);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.period_length)");
        String string2 = getString(R.string.day_s);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.day_s)");
        companion.dataPickerWeight(this, string, string2, parseInt, parseInt2, 1, this.x, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessSettings$showPeriodLengthPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().setMenstruationPeriodLength(Integer.parseInt(value));
                ((TextView) ActivityFemaleWellnessSettings.this._$_findCachedViewById(R.id.period_value_tv)).setText(ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().getMenstruationPeriodLength() + ' ' + ActivityFemaleWellnessSettings.this.getResources().getString(R.string.day_s));
                ActivityFemaleWellnessSettings activityFemaleWellnessSettings = ActivityFemaleWellnessSettings.this;
                activityFemaleWellnessSettings.setMPeriodLength(activityFemaleWellnessSettings.getWomenWellnessDataCommon().getMenstruationPeriodLength());
                ActivityFemaleWellnessSettings.this.buttonVisible();
            }
        });
    }

    public final void k0() {
        PickerDialog.Companion companion = PickerDialog.Companion;
        String string = getString(R.string.period_reminder);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.period_reminder)");
        String string2 = getString(R.string.day_s);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.day_s)");
        companion.dataPickerWeight(this, string, string2, Integer.parseInt(Constants.PERIOD_OVULATION_REMINDER_START_RANGE.getValue()), Integer.parseInt(Constants.PERIOD_OVULATION_REMINDER_END_RANGE.getValue()), 1, this.y, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessSettings$showPeriodReminderPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().setPeriodReminderAdvance(Integer.parseInt(value));
                ((TextView) ActivityFemaleWellnessSettings.this._$_findCachedViewById(R.id.select_period_reminder_img)).setText(ActivityFemaleWellnessSettings.this.getWomenWellnessDataCommon().getPeriodReminderAdvance() + ' ' + ActivityFemaleWellnessSettings.this.getResources().getString(R.string.day_s));
                ActivityFemaleWellnessSettings activityFemaleWellnessSettings = ActivityFemaleWellnessSettings.this;
                activityFemaleWellnessSettings.setMPeriodReminderAdvance(activityFemaleWellnessSettings.getWomenWellnessDataCommon().getPeriodReminderAdvance());
                ActivityFemaleWellnessSettings.this.buttonVisible();
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, android.app.Dialog] */
    public final void l0() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? dialog = new Dialog(this, R.style.DialogTheme);
        objectRef.element = dialog;
        ((Dialog) dialog).setContentView(R.layout.dialog_time_picker_female_wellness);
        ((Dialog) objectRef.element).setCancelable(true);
        ((Dialog) objectRef.element).setCanceledOnTouchOutside(true);
        Window window = ((Dialog) objectRef.element).getWindow();
        Intrinsics.checkNotNull(window);
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Intrinsics.checkNotNullExpressionValue(attributes, "window.attributes");
            attributes.width = -1;
            attributes.height = -2;
            attributes.gravity = 17;
            window.setAttributes(attributes);
        }
        ((Dialog) objectRef.element).show();
        ((TextView) ((Dialog) objectRef.element).findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.m0(ActivityFemaleWellnessSettings.this, objectRef, view);
            }
        });
        ((TextView) ((Dialog) objectRef.element).findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.n0(Ref.ObjectRef.this, view);
            }
        });
        WomenWellnessViewModel womenWellnessViewModel = this.p;
        WomenWellnessViewModel womenWellnessViewModel2 = null;
        if (womenWellnessViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel = null;
        }
        womenWellnessViewModel.populateHoursDataInView((Dialog) objectRef.element);
        WomenWellnessViewModel womenWellnessViewModel3 = this.p;
        if (womenWellnessViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel3 = null;
        }
        womenWellnessViewModel3.populateMinsDataInView((Dialog) objectRef.element);
        WomenWellnessViewModel womenWellnessViewModel4 = this.p;
        if (womenWellnessViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            womenWellnessViewModel2 = womenWellnessViewModel4;
        }
        womenWellnessViewModel2.populateAmPmDataInView((Dialog) objectRef.element);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (J()) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.n0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFemaleWellnessSettings.U(ActivityFemaleWellnessSettings.this, view);
                }
            });
            String string4 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.e0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFemaleWellnessSettings.V(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_women_wellness);
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WomenWellnessViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…essViewModel::class.java)");
        WomenWellnessViewModel womenWellnessViewModel = (WomenWellnessViewModel) viewModel;
        this.p = womenWellnessViewModel;
        if (womenWellnessViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel = null;
        }
        womenWellnessViewModel.setDialogListener(this);
        K();
        initToolbar();
        initClickListeners();
        W();
        if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            showProgress();
            BatterySaverModeFemaleWellnessHelper.Companion.getInstance(this).getBatterySaverMode(new BatterySaverModeFemaleWellnessHelper.BatterySaverModeListener() { // from class: com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessSettings$onCreate$1
                @Override // com.coveiot.android.femalewellness.BatterySaverModeFemaleWellnessHelper.BatterySaverModeListener
                public void onBatterySavingSettingsReceived(boolean z, int i) {
                    if (z && i == 1) {
                        ((SwitchCompat) ActivityFemaleWellnessSettings.this._$_findCachedViewById(R.id.switch_tracker)).setChecked(false);
                    }
                    ActivityFemaleWellnessSettings.this.dismissProgress();
                }
            });
        }
        if (DeviceUtils.Companion.isTouchELXDevice(this)) {
            ((TextView) _$_findCachedViewById(R.id.ovulation_reminder_text)).setText(getString(R.string.fertile_reminder));
        }
    }

    @Override // com.coveiot.android.femalewellness.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onNegativeButtonClicked() {
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onPositiveButtonClicked() {
    }

    @Override // com.coveiot.android.femalewellness.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void p0() {
        Toast.makeText(this, getResources().getString(R.string.turn_on_wellness_tracker), 0).show();
    }

    public final void q0() {
        WomenWellnessViewModel womenWellnessViewModel = this.p;
        WomenWellnessViewModel womenWellnessViewModel2 = null;
        if (womenWellnessViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel = null;
        }
        if (!Intrinsics.areEqual(womenWellnessViewModel.getDate_var(), "-1")) {
            WomenWellnessViewModel womenWellnessViewModel3 = this.p;
            if (womenWellnessViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                womenWellnessViewModel3 = null;
            }
            if (!Intrinsics.areEqual(womenWellnessViewModel3.getMonth_var(), "-1")) {
                WomenWellnessViewModel womenWellnessViewModel4 = this.p;
                if (womenWellnessViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    womenWellnessViewModel4 = null;
                }
                if (!Intrinsics.areEqual(womenWellnessViewModel4.getYear_var(), "-1")) {
                    return;
                }
            }
        }
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = this.t;
        if (i2 > 0) {
            i = i2;
        }
        int i3 = calendar.get(2) + 1;
        int i4 = this.u;
        if (i4 > 0) {
            i3 = i4;
        }
        int i5 = calendar.get(5);
        int i6 = this.v;
        if (i6 > 0) {
            i5 = i6;
        }
        WomenWellnessViewModel womenWellnessViewModel5 = this.p;
        if (womenWellnessViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel5 = null;
        }
        womenWellnessViewModel5.setDate_var(String.valueOf(i5));
        WomenWellnessViewModel womenWellnessViewModel6 = this.p;
        if (womenWellnessViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel6 = null;
        }
        womenWellnessViewModel6.setMonth_var(Utils.Companion.getDateMonthlyFormat(i3));
        WomenWellnessViewModel womenWellnessViewModel7 = this.p;
        if (womenWellnessViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            womenWellnessViewModel2 = womenWellnessViewModel7;
        }
        womenWellnessViewModel2.setYear_var(String.valueOf(i));
    }

    public final void setMCycleLength(int i) {
        this.w = i;
    }

    public final void setMEnabled(boolean z) {
        this.q = z;
    }

    public final void setMLastPeriodDay(int i) {
        this.v = i;
    }

    public final void setMLastPeriodMonth(int i) {
        this.u = i;
    }

    public final void setMLastPeriodYear(int i) {
        this.t = i;
    }

    public final void setMOvulationReminderAdvance(int i) {
        this.z = i;
    }

    public final void setMPeriodLength(int i) {
        this.x = i;
    }

    public final void setMPeriodReminderAdvance(int i) {
        this.y = i;
    }

    public final void setMReminderHour(int i) {
        this.r = i;
    }

    public final void setMReminderMinute(int i) {
        this.s = i;
    }

    public final void setWomenWellnessDataCommon(@NotNull WomenWellnessData womenWellnessData) {
        Intrinsics.checkNotNullParameter(womenWellnessData, "<set-?>");
        this.womenWellnessDataCommon = womenWellnessData;
    }

    public final void showConfirmationDialog() {
        String string = getString(R.string.confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
        String string2 = getString(R.string.women_wellness_settings_changes_confirmation);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.women…ngs_changes_confirmation)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.b0(BottomSheetDialogTwoButtons.this, this, view);
            }
        });
        String string4 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(\n            R.string.cancel\n        )");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.c0(BottomSheetDialogTwoButtons.this, view);
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    @Override // com.coveiot.android.femalewellness.DialogListener
    public void showErrorDialog() {
        onDismiss();
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.e0(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.femalewellness.DialogListener
    public void showSuccessDialog() {
        onDismiss();
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSettings.o0(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }
}
