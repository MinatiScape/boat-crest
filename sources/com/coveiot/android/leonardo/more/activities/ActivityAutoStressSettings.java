package com.coveiot.android.leonardo.more.activities;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.activities.ActivityAutoStressSettings;
import com.coveiot.android.leonardo.more.listeners.ReminderListener;
import com.coveiot.android.leonardo.more.viewmodel.AutoStressSettingsViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.covepreferences.data.AutoStressSettingsData;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAutoStressSettings extends BaseActivity implements DialogListener, ReminderListener, View.OnClickListener {
    public int A;
    public int B;
    public int C;
    public int D;
    public boolean F;
    @Nullable
    public TimePickerDialog G;
    @Nullable
    public TimePickerDialog H;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage I;
    @Nullable
    public BottomSheetDialogTwoButtons J;
    public AutoStressSettingsData autoStressSettingsDataCommon;
    public AutoStressSettingsViewModel autoStressSettingsViewModel;
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitleError;
    public boolean p;
    public TextView q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    @Nullable
    public String w;
    public boolean x;
    public boolean y;
    public int z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public boolean[] E = {true, true, true, true, true, true, true};

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityAutoStressSettings this$0, View view) {
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
            if (ActivityAutoStressSettings.this.getBoolSaveVisible()) {
                if (!AppUtils.isNetConnected(ActivityAutoStressSettings.this)) {
                    ActivityAutoStressSettings.this.showNoInternetMessage();
                } else if (BleApiManager.getInstance(ActivityAutoStressSettings.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    ActivityAutoStressSettings.this.getAutoStressSettingsViewModel().callSaveStressSettingsAndBleApi(ActivityAutoStressSettings.this.r, ActivityAutoStressSettings.this.x, ((TextView) ActivityAutoStressSettings.this._$_findCachedViewById(R.id.tv_start_time)).getText().toString(), ((TextView) ActivityAutoStressSettings.this._$_findCachedViewById(R.id.tv_end_time)).getText().toString(), ActivityAutoStressSettings.this.E, ActivityAutoStressSettings.this.y);
                } else {
                    ActivityAutoStressSettings activityAutoStressSettings = ActivityAutoStressSettings.this;
                    String string = activityAutoStressSettings.getString(R.string.band_not_connected);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                    String string2 = ActivityAutoStressSettings.this.getString(R.string.please_connect_the_device);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                    final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activityAutoStressSettings, string, string2);
                    String string3 = ActivityAutoStressSettings.this.getString(R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                    final ActivityAutoStressSettings activityAutoStressSettings2 = ActivityAutoStressSettings.this;
                    bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.z1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityAutoStressSettings.a.invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage.this, activityAutoStressSettings2, view);
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

    public static final void H(ActivityAutoStressSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.I;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }
        this$0.finish();
    }

    public static final void I(ActivityAutoStressSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.J;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        TextView textView = this$0.q;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public static final void J(ActivityAutoStressSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.J;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
    }

    public static final void K(ActivityAutoStressSettings this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x = z;
        if (!z) {
            ((LinearLayout) this$0._$_findCachedViewById(R.id.reminder_layout)).setBackground(this$0.getResources().getDrawable(R.drawable.reminder_bg));
            ((LinearLayout) this$0._$_findCachedViewById(R.id.reminder_and_info_layout)).setVisibility(8);
        } else {
            ((LinearLayout) this$0._$_findCachedViewById(R.id.reminder_layout)).setBackground(null);
            if (DeviceUtils.Companion.isTouchELXDevice(this$0)) {
                ((LinearLayout) this$0._$_findCachedViewById(R.id.reminder_and_info_layout)).setVisibility(8);
            } else {
                ((LinearLayout) this$0._$_findCachedViewById(R.id.reminder_and_info_layout)).setVisibility(0);
            }
        }
        LinearLayout reminder_layout = (LinearLayout) this$0._$_findCachedViewById(R.id.reminder_layout);
        Intrinsics.checkNotNullExpressionValue(reminder_layout, "reminder_layout");
        this$0.b0(z, reminder_layout);
        boolean isChecked = ((SwitchCompat) this$0._$_findCachedViewById(R.id.high_stress_switch)).isChecked();
        LinearLayout linear_layout = (LinearLayout) this$0._$_findCachedViewById(R.id.linear_layout);
        Intrinsics.checkNotNullExpressionValue(linear_layout, "linear_layout");
        this$0.b0(isChecked, linear_layout);
        this$0.buttonVisible();
    }

    public static final void L(ActivityAutoStressSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
            this$0.y = ((SwitchCompat) this$0._$_findCachedViewById(R.id.high_stress_switch)).isChecked();
        } else {
            ((SwitchCompat) this$0._$_findCachedViewById(R.id.high_stress_switch)).setChecked(this$0.y);
            Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_auto_stress), 0).show();
        }
        boolean z = this$0.y;
        LinearLayout linear_layout = (LinearLayout) this$0._$_findCachedViewById(R.id.linear_layout);
        Intrinsics.checkNotNullExpressionValue(linear_layout, "linear_layout");
        this$0.b0(z, linear_layout);
        this$0.buttonVisible();
    }

    public static final void M(final ActivityAutoStressSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
            if (((SwitchCompat) this$0._$_findCachedViewById(R.id.high_stress_switch)).isChecked()) {
                this$0.Z();
                if (this$0.H == null) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this$0, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.activities.j1
                        @Override // android.app.TimePickerDialog.OnTimeSetListener
                        public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                            ActivityAutoStressSettings.N(ActivityAutoStressSettings.this, timePicker, i, i2);
                        }
                    }, this$0.B, this$0.C, DateFormat.is24HourFormat(this$0));
                    this$0.H = timePickerDialog;
                    timePickerDialog.show();
                    TimePickerDialog timePickerDialog2 = this$0.H;
                    if (timePickerDialog2 != null) {
                        timePickerDialog2.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.coveiot.android.leonardo.more.activities.s1
                            @Override // android.content.DialogInterface.OnCancelListener
                            public final void onCancel(DialogInterface dialogInterface) {
                                ActivityAutoStressSettings.O(dialogInterface);
                            }
                        });
                        return;
                    }
                    return;
                }
                return;
            }
            Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_high_stress), 0).show();
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_auto_stress), 0).show();
    }

    public static final void N(ActivityAutoStressSettings this_run, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this_run.onReminderTimeSet(i, i2, false);
        this_run.buttonVisible();
        this_run.B = i;
        this_run.C = i2;
    }

    public static final void O(DialogInterface dialogInterface) {
    }

    public static final void P(final ActivityAutoStressSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
            if (((SwitchCompat) this$0._$_findCachedViewById(R.id.high_stress_switch)).isChecked()) {
                String obj = ((TextView) this$0._$_findCachedViewById(R.id.tv_alert_interval)).getText().toString();
                String string = this$0.getResources().getString(R.string.min);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.min)");
                PickerDialog.Companion.minutesPickerAutoStress(this$0, Integer.parseInt(kotlin.text.m.replace$default(kotlin.text.m.replace$default(obj, string, "", false, 4, (Object) null), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null)), 30, 120, 30, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAutoStressSettings$initClickListeners$3$1
                    @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
                    public void onValueSelected(@NotNull String value) {
                        Intrinsics.checkNotNullParameter(value, "value");
                        if (ActivityAutoStressSettings.this.isFinishing()) {
                            return;
                        }
                        ActivityAutoStressSettings.this.r = Integer.parseInt(value);
                        ((TextView) ActivityAutoStressSettings.this._$_findCachedViewById(R.id.tv_alert_interval)).setText(ActivityAutoStressSettings.this.r + ' ' + ActivityAutoStressSettings.this.getResources().getString(R.string.min));
                        ActivityAutoStressSettings.this.setBoolSaveVisible(true);
                        ActivityAutoStressSettings.this.buttonVisible();
                    }
                });
                return;
            }
            Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_high_stress), 0).show();
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_auto_stress), 0).show();
    }

    public static final void Q(final ActivityAutoStressSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
            if (((SwitchCompat) this$0._$_findCachedViewById(R.id.high_stress_switch)).isChecked()) {
                this$0.Z();
                if (this$0.G == null) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this$0, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.activities.q1
                        @Override // android.app.TimePickerDialog.OnTimeSetListener
                        public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                            ActivityAutoStressSettings.R(ActivityAutoStressSettings.this, timePicker, i, i2);
                        }
                    }, this$0.z, this$0.A, DateFormat.is24HourFormat(this$0));
                    this$0.G = timePickerDialog;
                    timePickerDialog.show();
                    TimePickerDialog timePickerDialog2 = this$0.G;
                    if (timePickerDialog2 != null) {
                        timePickerDialog2.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.coveiot.android.leonardo.more.activities.r1
                            @Override // android.content.DialogInterface.OnCancelListener
                            public final void onCancel(DialogInterface dialogInterface) {
                                ActivityAutoStressSettings.S(dialogInterface);
                            }
                        });
                        return;
                    }
                    return;
                }
                return;
            }
            Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_high_stress), 0).show();
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_auto_stress), 0).show();
    }

    public static final void R(ActivityAutoStressSettings this$0, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onReminderTimeSet(i, i2, true);
        this$0.buttonVisible();
        this$0.z = i;
        this$0.A = i2;
    }

    public static final void S(DialogInterface dialogInterface) {
    }

    public static final void T(ActivityAutoStressSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitle().dismiss();
        this$0.finish();
    }

    public static final void U(ActivityAutoStressSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitleError().dismiss();
        this$0.finish();
    }

    public static final void V(ActivityAutoStressSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.G();
    }

    public static final void W(ActivityAutoStressSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.q;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public final void G() {
        int beggining_time_hour;
        String str;
        int end_time_hour;
        String str2;
        Boolean valueOf;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2;
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage;
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2;
        this.F = false;
        AutoStressSettingsData stressSettingsDataFromPref = getAutoStressSettingsViewModel().getStressSettingsDataFromPref();
        boolean[] zArr = {stressSettingsDataFromPref.isSunday(), stressSettingsDataFromPref.isMonday(), stressSettingsDataFromPref.isTuesday(), stressSettingsDataFromPref.isWednesday(), stressSettingsDataFromPref.isThrusday(), stressSettingsDataFromPref.isFriday(), stressSettingsDataFromPref.isSaturday()};
        if (stressSettingsDataFromPref.getBeggining_time_hour() > 12) {
            str = ' ' + getResources().getString(R.string.pm);
            beggining_time_hour = stressSettingsDataFromPref.getBeggining_time_hour() - 12;
        } else if (stressSettingsDataFromPref.getBeggining_time_hour() == 12) {
            str = ' ' + getResources().getString(R.string.pm);
            beggining_time_hour = stressSettingsDataFromPref.getBeggining_time_hour();
        } else {
            beggining_time_hour = stressSettingsDataFromPref.getBeggining_time_hour();
            str = ' ' + getResources().getString(R.string.am);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(beggining_time_hour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(stressSettingsDataFromPref.getBeggining_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        String sb2 = sb.toString();
        if (stressSettingsDataFromPref.getEnd_time_hour() > 12) {
            str2 = ' ' + getResources().getString(R.string.pm);
            end_time_hour = stressSettingsDataFromPref.getEnd_time_hour() - 12;
        } else if (stressSettingsDataFromPref.getEnd_time_hour() == 12) {
            str2 = ' ' + getResources().getString(R.string.pm);
            end_time_hour = stressSettingsDataFromPref.getEnd_time_hour();
        } else {
            end_time_hour = stressSettingsDataFromPref.getEnd_time_hour();
            str2 = ' ' + getResources().getString(R.string.am);
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(end_time_hour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(stressSettingsDataFromPref.getEnd_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(str2);
        String sb4 = sb3.toString();
        if (stressSettingsDataFromPref.getAutoStress() != this.x) {
            this.F = true;
        } else if (stressSettingsDataFromPref.isHighStressReminder() != this.y) {
            this.F = true;
        } else {
            if (!kotlin.text.m.equals(stressSettingsDataFromPref.getRemind_in() + getResources().getString(R.string.min), ((TextView) _$_findCachedViewById(R.id.tv_alert_interval)).getText().toString(), true)) {
                this.F = true;
            } else if (!kotlin.text.m.equals(sb2, ((TextView) _$_findCachedViewById(R.id.tv_start_time)).getText().toString(), true)) {
                this.F = true;
            } else if (!kotlin.text.m.equals(sb4, ((TextView) _$_findCachedViewById(R.id.tv_end_time)).getText().toString(), true)) {
                this.F = true;
            } else {
                int length = this.E.length;
                for (int i = 0; i < length; i++) {
                    if (this.E[i] != zArr[i]) {
                        this.F = true;
                    }
                }
            }
        }
        if (this.F) {
            if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.DISCONNECTED) {
                BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.I;
                if (bottomSheetDialogOneButtonTitleMessage3 != null) {
                    Boolean valueOf2 = bottomSheetDialogOneButtonTitleMessage3 != null ? Boolean.valueOf(bottomSheetDialogOneButtonTitleMessage3.isShowing()) : null;
                    Intrinsics.checkNotNull(valueOf2);
                    if (valueOf2.booleanValue() && (bottomSheetDialogOneButtonTitleMessage2 = this.I) != null) {
                        bottomSheetDialogOneButtonTitleMessage2.dismiss();
                    }
                    this.I = null;
                }
                String string = getString(R.string.band_not_connected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                String string2 = getString(R.string.please_connect_the_device);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage4 = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
                this.I = bottomSheetDialogOneButtonTitleMessage4;
                String string3 = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                bottomSheetDialogOneButtonTitleMessage4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.x1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAutoStressSettings.H(ActivityAutoStressSettings.this, view);
                    }
                });
                BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage5 = this.I;
                valueOf = bottomSheetDialogOneButtonTitleMessage5 != null ? Boolean.valueOf(bottomSheetDialogOneButtonTitleMessage5.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue() || (bottomSheetDialogOneButtonTitleMessage = this.I) == null) {
                    return;
                }
                bottomSheetDialogOneButtonTitleMessage.show();
                return;
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.J;
            if (bottomSheetDialogTwoButtons3 != null) {
                Boolean valueOf3 = bottomSheetDialogTwoButtons3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons3.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf3);
                if (valueOf3.booleanValue() && (bottomSheetDialogTwoButtons2 = this.J) != null) {
                    bottomSheetDialogTwoButtons2.dismiss();
                }
                this.J = null;
            }
            String string4 = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.confirmation)");
            String string5 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.save_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = new BottomSheetDialogTwoButtons(this, string4, string5, false, 8, null);
            this.J = bottomSheetDialogTwoButtons4;
            String string6 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons4.setPositiveButton(string6, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.l1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAutoStressSettings.I(ActivityAutoStressSettings.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.J;
            if (bottomSheetDialogTwoButtons5 != null) {
                String string7 = getString(R.string.discard);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.discard)");
                bottomSheetDialogTwoButtons5.setNegativeButton(string7, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.n1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAutoStressSettings.J(ActivityAutoStressSettings.this, view);
                    }
                });
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons6 = this.J;
            valueOf = bottomSheetDialogTwoButtons6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons6.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.J) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    public final void X() {
        initToolbar();
        initDialogs();
        initClickListeners();
        Y();
    }

    public final void Y() {
        int beggining_time_hour;
        String str;
        String str2;
        int end_time_hour;
        AutoStressSettingsData stressSettingsDataFromPref = getAutoStressSettingsViewModel().getStressSettingsDataFromPref();
        setAutoStressSettingsDataCommon(getAutoStressSettingsViewModel().getStressSettingsDataFromPref());
        this.z = stressSettingsDataFromPref.getBeggining_time_hour();
        this.A = stressSettingsDataFromPref.getBeggining_time_minutes();
        this.B = stressSettingsDataFromPref.getEnd_time_hour();
        this.C = stressSettingsDataFromPref.getEnd_time_minutes();
        this.E = (boolean[]) new boolean[]{stressSettingsDataFromPref.isSunday(), stressSettingsDataFromPref.isMonday(), stressSettingsDataFromPref.isTuesday(), stressSettingsDataFromPref.isWednesday(), stressSettingsDataFromPref.isThrusday(), stressSettingsDataFromPref.isFriday(), stressSettingsDataFromPref.isSaturday()}.clone();
        if (stressSettingsDataFromPref.getBeggining_time_hour() > 12) {
            str = ' ' + getResources().getString(R.string.pm);
            beggining_time_hour = stressSettingsDataFromPref.getBeggining_time_hour() - 12;
        } else if (stressSettingsDataFromPref.getBeggining_time_hour() == 12) {
            str = ' ' + getResources().getString(R.string.pm);
            beggining_time_hour = stressSettingsDataFromPref.getBeggining_time_hour();
        } else {
            beggining_time_hour = stressSettingsDataFromPref.getBeggining_time_hour() == 0 ? 12 : stressSettingsDataFromPref.getBeggining_time_hour();
            str = ' ' + getResources().getString(R.string.am);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(beggining_time_hour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(stressSettingsDataFromPref.getBeggining_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        String sb2 = sb.toString();
        if (stressSettingsDataFromPref.getEnd_time_hour() > 12) {
            str2 = ' ' + getResources().getString(R.string.pm);
            end_time_hour = stressSettingsDataFromPref.getEnd_time_hour() - 12;
        } else if (stressSettingsDataFromPref.getEnd_time_hour() == 12) {
            str2 = ' ' + getResources().getString(R.string.pm);
            end_time_hour = stressSettingsDataFromPref.getEnd_time_hour();
        } else {
            str2 = ' ' + getResources().getString(R.string.am);
            end_time_hour = stressSettingsDataFromPref.getEnd_time_hour() != 0 ? stressSettingsDataFromPref.getEnd_time_hour() : 12;
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(end_time_hour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(stressSettingsDataFromPref.getEnd_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(str2);
        String sb4 = sb3.toString();
        ((TextView) _$_findCachedViewById(R.id.tv_start_time)).setText(sb2);
        ((TextView) _$_findCachedViewById(R.id.tv_end_time)).setText(sb4);
        this.r = stressSettingsDataFromPref.getRemind_in();
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_alert_interval);
        StringBuilder sb5 = new StringBuilder();
        int i = this.r;
        if (i == 0) {
            i = 60;
        }
        sb5.append(i);
        sb5.append(getResources().getString(R.string.min));
        textView.setText(sb5.toString());
        int i2 = R.id.switch_reminder;
        ((SwitchCompat) _$_findCachedViewById(i2)).setChecked(stressSettingsDataFromPref.getAutoStress());
        this.x = stressSettingsDataFromPref.getAutoStress();
        this.y = stressSettingsDataFromPref.isHighStressReminder();
        int i3 = R.id.high_stress_switch;
        ((SwitchCompat) _$_findCachedViewById(i3)).setChecked(this.y);
        if (!this.x) {
            ((LinearLayout) _$_findCachedViewById(R.id.reminder_layout)).setBackground(getResources().getDrawable(R.drawable.reminder_bg));
        } else {
            ((LinearLayout) _$_findCachedViewById(R.id.reminder_layout)).setBackground(null);
        }
        boolean isChecked = ((SwitchCompat) _$_findCachedViewById(i2)).isChecked();
        LinearLayout reminder_layout = (LinearLayout) _$_findCachedViewById(R.id.reminder_layout);
        Intrinsics.checkNotNullExpressionValue(reminder_layout, "reminder_layout");
        b0(isChecked, reminder_layout);
        boolean isChecked2 = ((SwitchCompat) _$_findCachedViewById(i3)).isChecked();
        LinearLayout linear_layout = (LinearLayout) _$_findCachedViewById(R.id.linear_layout);
        Intrinsics.checkNotNullExpressionValue(linear_layout, "linear_layout");
        b0(isChecked2, linear_layout);
        updateRepeatLayout();
    }

    public final void Z() {
        TimePickerDialog timePickerDialog = this.G;
        if (timePickerDialog != null) {
            if (timePickerDialog != null) {
                timePickerDialog.dismiss();
            }
            this.G = null;
        }
        TimePickerDialog timePickerDialog2 = this.H;
        if (timePickerDialog2 != null) {
            if (timePickerDialog2 != null) {
                timePickerDialog2.dismiss();
            }
            this.H = null;
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

    public final void a0(View view, float f) {
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
                a0(childAt, f);
            } else if (childAt instanceof TextView) {
                ((TextView) childAt).setAlpha(f);
            }
            if (i == childCount) {
                return;
            }
            i++;
        }
    }

    public final void b0(boolean z, LinearLayout linearLayout) {
        if (z) {
            a0(linearLayout, 1.0f);
        } else {
            a0(linearLayout, 0.5f);
        }
    }

    public final void buttonVisible() {
        String str;
        String str2;
        if (getAutoStressSettingsDataCommon().getBeggining_time_hour() > 12) {
            str = ' ' + getResources().getString(R.string.pm);
            getAutoStressSettingsDataCommon().setBeggining_time_hour(getAutoStressSettingsDataCommon().getBeggining_time_hour() - 12);
        } else if (getAutoStressSettingsDataCommon().getBeggining_time_hour() == 12) {
            str = ' ' + getResources().getString(R.string.pm);
            getAutoStressSettingsDataCommon().setBeggining_time_hour(getAutoStressSettingsDataCommon().getBeggining_time_hour());
        } else {
            getAutoStressSettingsDataCommon().setBeggining_time_hour(getAutoStressSettingsDataCommon().getBeggining_time_hour() == 0 ? 12 : getAutoStressSettingsDataCommon().getBeggining_time_hour());
            str = ' ' + getResources().getString(R.string.am);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getAutoStressSettingsDataCommon().getBeggining_time_hour())}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getAutoStressSettingsDataCommon().getBeggining_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        String sb2 = sb.toString();
        if (getAutoStressSettingsDataCommon().getEnd_time_hour() > 12) {
            str2 = ' ' + getResources().getString(R.string.pm);
            getAutoStressSettingsDataCommon().setEnd_time_hour(getAutoStressSettingsDataCommon().getEnd_time_hour() - 12);
        } else if (getAutoStressSettingsDataCommon().getEnd_time_hour() == 12) {
            str2 = ' ' + getResources().getString(R.string.pm);
            getAutoStressSettingsDataCommon().setEnd_time_hour(getAutoStressSettingsDataCommon().getEnd_time_hour());
        } else {
            getAutoStressSettingsDataCommon().setEnd_time_hour(getAutoStressSettingsDataCommon().getEnd_time_hour() != 0 ? getAutoStressSettingsDataCommon().getEnd_time_hour() : 12);
            str2 = ' ' + getResources().getString(R.string.am);
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getAutoStressSettingsDataCommon().getEnd_time_hour())}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getAutoStressSettingsDataCommon().getEnd_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(str2);
        String sb4 = sb3.toString();
        TextView textView = null;
        if (!Boolean.valueOf(((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).isChecked()).equals(Boolean.valueOf(getAutoStressSettingsDataCommon().getAutoStress()))) {
            TextView textView2 = this.q;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveText");
            } else {
                textView = textView2;
            }
            textView.setAlpha(1.0f);
            this.p = true;
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
        } else if (getAutoStressSettingsDataCommon().getRemind_in() != this.r) {
            TextView textView3 = this.q;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveText");
            } else {
                textView = textView3;
            }
            textView.setAlpha(1.0f);
            this.p = true;
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
        } else if (!((TextView) _$_findCachedViewById(R.id.tv_start_time)).getText().toString().equals(sb2)) {
            TextView textView4 = this.q;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveText");
            } else {
                textView = textView4;
            }
            textView.setAlpha(1.0f);
            this.p = true;
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
        } else if (!((TextView) _$_findCachedViewById(R.id.tv_end_time)).getText().toString().equals(sb4)) {
            TextView textView5 = this.q;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveText");
            } else {
                textView = textView5;
            }
            textView.setAlpha(1.0f);
            this.p = true;
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
        } else {
            boolean[] zArr = {getAutoStressSettingsDataCommon().isSunday(), getAutoStressSettingsDataCommon().isMonday(), getAutoStressSettingsDataCommon().isTuesday(), getAutoStressSettingsDataCommon().isWednesday(), getAutoStressSettingsDataCommon().isThrusday(), getAutoStressSettingsDataCommon().isFriday(), getAutoStressSettingsDataCommon().isSaturday()};
            int length = this.E.length;
            boolean z = false;
            for (int i = 0; i < length; i++) {
                if (this.E[i] != zArr[i]) {
                    z = true;
                }
            }
            if (z) {
                TextView textView6 = this.q;
                if (textView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("saveText");
                } else {
                    textView = textView6;
                }
                textView.setAlpha(1.0f);
                this.p = true;
                ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
            } else {
                TextView textView7 = this.q;
                if (textView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("saveText");
                } else {
                    textView = textView7;
                }
                textView.setAlpha(0.5f);
                this.p = false;
                ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(false);
            }
        }
        ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(!Boolean.valueOf(((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).isChecked()).equals(Boolean.valueOf(getAutoStressSettingsDataCommon().getAutoStress())));
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
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitleError() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.bottomSheetDialogOneButtonOneTitleError;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            return bottomSheetDialogOneButtonOneTitle;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogOneButtonOneTitleError");
        return null;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetDialogOneButtonTitleMessageDisconnect() {
        return this.I;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationBottomSheetDialog() {
        return this.J;
    }

    public final int getDaySelectedCount() {
        return this.D;
    }

    public final boolean getHasInfoChanged() {
        return this.F;
    }

    @Nullable
    public final TimePickerDialog getTimePickerDialogEndTime() {
        return this.H;
    }

    @Nullable
    public final TimePickerDialog getTimePickerDialogStartTime() {
        return this.G;
    }

    public final void initClickListeners() {
        if (DeviceUtils.Companion.isTouchELXDevice(this)) {
            ((LinearLayout) _$_findCachedViewById(R.id.reminder_and_info_layout)).setVisibility(8);
        } else {
            ((LinearLayout) _$_findCachedViewById(R.id.reminder_and_info_layout)).setVisibility(0);
        }
        ((TextView) _$_findCachedViewById(R.id.sunday)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.monday)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.tuesday)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.wednesday)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.thursday)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.friday)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.saturday)).setOnClickListener(this);
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.p1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoStressSettings.K(ActivityAutoStressSettings.this, compoundButton, z);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(R.id.high_stress_switch)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.u1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoStressSettings.L(ActivityAutoStressSettings.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_alert_interval)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.o1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoStressSettings.P(ActivityAutoStressSettings.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_start_time)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.t1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoStressSettings.Q(ActivityAutoStressSettings.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_end_time)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.y1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoStressSettings.M(ActivityAutoStressSettings.this, view);
            }
        });
        TextView textView = this.q;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        ViewUtilsKt.setSafeOnClickListener(textView, new a());
    }

    public final void initDialogs() {
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        setBottomSheetDialogOneButtonOneTitle(new BottomSheetDialogOneButtonOneTitle(this, string));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = getBottomSheetDialogOneButtonOneTitle();
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.v1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoStressSettings.T(ActivityAutoStressSettings.this, view);
            }
        });
        String string3 = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.setting_couldnot_save)");
        setBottomSheetDialogOneButtonOneTitleError(new BottomSheetDialogOneButtonOneTitle(this, string3));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitleError = getBottomSheetDialogOneButtonOneTitleError();
        String string4 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitleError.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.m1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoStressSettings.U(ActivityAutoStressSettings.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.stress_measurements));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.k1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoStressSettings.V(ActivityAutoStressSettings.this, view);
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.q = textView;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setText(getString(R.string.save_camel));
        TextView textView3 = this.q;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView3 = null;
        }
        textView3.setAlpha(0.5f);
        TextView textView4 = this.q;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
        } else {
            textView2 = textView4;
        }
        textView2.setVisibility(8);
        int i = R.id.btnSave;
        ((Button) _$_findCachedViewById(i)).setEnabled(false);
        ((Button) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.w1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoStressSettings.W(ActivityAutoStressSettings.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        G();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        switch (view.getId()) {
            case R.id.friday /* 2131363530 */:
                if (((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
                    if (((SwitchCompat) _$_findCachedViewById(R.id.high_stress_switch)).isChecked()) {
                        boolean[] zArr = this.E;
                        zArr[5] = !zArr[5];
                        updateRepeatLayout();
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.turn_on_high_stress), 0).show();
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_auto_stress), 0).show();
                }
                buttonVisible();
                return;
            case R.id.monday /* 2131364631 */:
                if (((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
                    if (((SwitchCompat) _$_findCachedViewById(R.id.high_stress_switch)).isChecked()) {
                        boolean[] zArr2 = this.E;
                        zArr2[1] = !zArr2[1];
                        updateRepeatLayout();
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.turn_on_high_stress), 0).show();
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_auto_stress), 0).show();
                }
                buttonVisible();
                return;
            case R.id.saturday /* 2131365329 */:
                if (((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
                    if (((SwitchCompat) _$_findCachedViewById(R.id.high_stress_switch)).isChecked()) {
                        boolean[] zArr3 = this.E;
                        zArr3[6] = !zArr3[6];
                        updateRepeatLayout();
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.turn_on_high_stress), 0).show();
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_auto_stress), 0).show();
                }
                buttonVisible();
                return;
            case R.id.sunday /* 2131365692 */:
                if (((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
                    if (((SwitchCompat) _$_findCachedViewById(R.id.high_stress_switch)).isChecked()) {
                        boolean[] zArr4 = this.E;
                        zArr4[0] = !zArr4[0];
                        updateRepeatLayout();
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.turn_on_high_stress), 0).show();
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_auto_stress), 0).show();
                }
                buttonVisible();
                return;
            case R.id.thursday /* 2131365917 */:
                if (((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
                    if (((SwitchCompat) _$_findCachedViewById(R.id.high_stress_switch)).isChecked()) {
                        boolean[] zArr5 = this.E;
                        zArr5[4] = !zArr5[4];
                        updateRepeatLayout();
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.turn_on_high_stress), 0).show();
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_auto_stress), 0).show();
                }
                buttonVisible();
                return;
            case R.id.tuesday /* 2131366038 */:
                if (((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
                    if (((SwitchCompat) _$_findCachedViewById(R.id.high_stress_switch)).isChecked()) {
                        boolean[] zArr6 = this.E;
                        zArr6[2] = !zArr6[2];
                        updateRepeatLayout();
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.turn_on_high_stress), 0).show();
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_auto_stress), 0).show();
                }
                buttonVisible();
                return;
            case R.id.wednesday /* 2131367457 */:
                if (((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
                    if (((SwitchCompat) _$_findCachedViewById(R.id.high_stress_switch)).isChecked()) {
                        boolean[] zArr7 = this.E;
                        zArr7[3] = !zArr7[3];
                        updateRepeatLayout();
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.turn_on_high_stress), 0).show();
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_auto_stress), 0).show();
                }
                buttonVisible();
                return;
            default:
                return;
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_auto_stress_settings);
        setAutoStressSettingsViewModel((AutoStressSettingsViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(AutoStressSettingsViewModel.class));
        getAutoStressSettingsViewModel().setDialogListener(this);
        X();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.more.listeners.ReminderListener
    public void onReminderTimeSet(int i, int i2, boolean z) {
        if (z) {
            this.s = i;
            this.t = i2;
            if (i > 12) {
                this.w = ' ' + getResources().getString(R.string.pm);
                this.s = this.s - 12;
            } else if (i == 12) {
                this.w = ' ' + getResources().getString(R.string.pm);
                this.s = this.s;
            } else {
                this.w = ' ' + getResources().getString(R.string.am);
            }
            if (this.s == 0) {
                this.s = 12;
            }
            StringBuilder sb = new StringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.s)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(':');
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.t)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb.append(format2);
            sb.append(this.w);
            ((TextView) _$_findCachedViewById(R.id.tv_start_time)).setText(sb.toString());
            return;
        }
        this.u = i;
        this.v = i2;
        if (i > 12) {
            this.w = ' ' + getResources().getString(R.string.pm);
            this.u = this.u - 12;
        } else if (i == 12) {
            this.w = ' ' + getResources().getString(R.string.pm);
            this.u = this.u;
        } else {
            this.w = ' ' + getResources().getString(R.string.am);
        }
        if (this.u == 0) {
            this.u = 12;
        }
        StringBuilder sb2 = new StringBuilder();
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Locale locale2 = Locale.ENGLISH;
        String format3 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.u)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb2.append(format3);
        sb2.append(':');
        String format4 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.v)}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb2.append(format4);
        sb2.append(this.w);
        ((TextView) _$_findCachedViewById(R.id.tv_end_time)).setText(sb2.toString());
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

    public final void setBottomSheetDialogOneButtonOneTitle(@NotNull BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "<set-?>");
        this.bottomSheetDialogOneButtonOneTitle = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setBottomSheetDialogOneButtonOneTitleError(@NotNull BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "<set-?>");
        this.bottomSheetDialogOneButtonOneTitleError = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setBottomSheetDialogOneButtonTitleMessageDisconnect(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.I = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setConfirmationBottomSheetDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.J = bottomSheetDialogTwoButtons;
    }

    public final void setDaySelectedCount(int i) {
        this.D = i;
    }

    public final void setHasInfoChanged(boolean z) {
        this.F = z;
    }

    public final void setTimePickerDialogEndTime(@Nullable TimePickerDialog timePickerDialog) {
        this.H = timePickerDialog;
    }

    public final void setTimePickerDialogStartTime(@Nullable TimePickerDialog timePickerDialog) {
        this.G = timePickerDialog;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        if (getBottomSheetDialogOneButtonOneTitleError().isShowing()) {
            return;
        }
        getBottomSheetDialogOneButtonOneTitleError().show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        if (getBottomSheetDialogOneButtonOneTitle().isShowing()) {
            return;
        }
        getBottomSheetDialogOneButtonOneTitle().show();
    }

    public final void updateRepeatLayout() {
        if (this.E[0]) {
            int i = R.id.sunday;
            ((TextView) _$_findCachedViewById(i)).setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ((TextView) _$_findCachedViewById(i)).setTextColor(getResources().getColor(R.color.main_text_color));
            ((TextView) _$_findCachedViewById(i)).setTextAppearance(R.style.semi_bold);
        } else {
            int i2 = R.id.sunday;
            ((TextView) _$_findCachedViewById(i2)).setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ((TextView) _$_findCachedViewById(i2)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            ((TextView) _$_findCachedViewById(i2)).setTextAppearance(R.style.regular);
        }
        if (this.E[1]) {
            int i3 = R.id.monday;
            ((TextView) _$_findCachedViewById(i3)).setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ((TextView) _$_findCachedViewById(i3)).setTextColor(getResources().getColor(R.color.main_text_color));
            ((TextView) _$_findCachedViewById(i3)).setTextAppearance(R.style.semi_bold);
        } else {
            int i4 = R.id.monday;
            ((TextView) _$_findCachedViewById(i4)).setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ((TextView) _$_findCachedViewById(i4)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            ((TextView) _$_findCachedViewById(i4)).setTextAppearance(R.style.regular);
        }
        if (this.E[2]) {
            int i5 = R.id.tuesday;
            ((TextView) _$_findCachedViewById(i5)).setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ((TextView) _$_findCachedViewById(i5)).setTextColor(getResources().getColor(R.color.main_text_color));
            ((TextView) _$_findCachedViewById(i5)).setTextAppearance(R.style.semi_bold);
        } else {
            int i6 = R.id.tuesday;
            ((TextView) _$_findCachedViewById(i6)).setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ((TextView) _$_findCachedViewById(i6)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            ((TextView) _$_findCachedViewById(i6)).setTextAppearance(R.style.regular);
        }
        if (this.E[3]) {
            int i7 = R.id.wednesday;
            ((TextView) _$_findCachedViewById(i7)).setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ((TextView) _$_findCachedViewById(i7)).setTextColor(getResources().getColor(R.color.main_text_color));
            ((TextView) _$_findCachedViewById(i7)).setTextAppearance(R.style.semi_bold);
        } else {
            int i8 = R.id.wednesday;
            ((TextView) _$_findCachedViewById(i8)).setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ((TextView) _$_findCachedViewById(i8)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            ((TextView) _$_findCachedViewById(i8)).setTextAppearance(R.style.regular);
        }
        if (this.E[4]) {
            int i9 = R.id.thursday;
            ((TextView) _$_findCachedViewById(i9)).setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ((TextView) _$_findCachedViewById(i9)).setTextColor(getResources().getColor(R.color.main_text_color));
            ((TextView) _$_findCachedViewById(i9)).setTextAppearance(R.style.semi_bold);
        } else {
            int i10 = R.id.thursday;
            ((TextView) _$_findCachedViewById(i10)).setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ((TextView) _$_findCachedViewById(i10)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            ((TextView) _$_findCachedViewById(i10)).setTextAppearance(R.style.regular);
        }
        if (this.E[5]) {
            int i11 = R.id.friday;
            ((TextView) _$_findCachedViewById(i11)).setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ((TextView) _$_findCachedViewById(i11)).setTextColor(getResources().getColor(R.color.main_text_color));
            ((TextView) _$_findCachedViewById(i11)).setTextAppearance(R.style.semi_bold);
        } else {
            int i12 = R.id.friday;
            ((TextView) _$_findCachedViewById(i12)).setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ((TextView) _$_findCachedViewById(i12)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            ((TextView) _$_findCachedViewById(i12)).setTextAppearance(R.style.regular);
        }
        if (this.E[6]) {
            int i13 = R.id.saturday;
            ((TextView) _$_findCachedViewById(i13)).setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            ((TextView) _$_findCachedViewById(i13)).setTextColor(getResources().getColor(R.color.main_text_color));
            ((TextView) _$_findCachedViewById(i13)).setTextAppearance(R.style.semi_bold);
        } else {
            int i14 = R.id.saturday;
            ((TextView) _$_findCachedViewById(i14)).setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            ((TextView) _$_findCachedViewById(i14)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            ((TextView) _$_findCachedViewById(i14)).setTextAppearance(R.style.regular);
        }
        this.D = 0;
        for (boolean z : this.E) {
            if (z) {
                ((LinearLayout) _$_findCachedViewById(R.id.repeat_layout)).setVisibility(0);
                ((SwitchCompat) _$_findCachedViewById(R.id.switch_repeat)).setChecked(true);
                this.D++;
                return;
            }
        }
    }
}
