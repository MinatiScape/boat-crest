package com.coveiot.android.leonardo.more.activities;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityDrinkReminderBinding;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.activities.ActivityDrinkWaterReminder;
import com.coveiot.android.leonardo.more.adapters.IntervalAdapterNew;
import com.coveiot.android.leonardo.more.listeners.ReminderListener;
import com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.covepreferences.data.DrinkWaterReminderData;
import com.coveiot.utils.utility.AppUtils;
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
public final class ActivityDrinkWaterReminder extends BaseActivity implements DialogListener, ReminderListener, IntervalAdapterNew.IntervalSelectionListener {
    public int A;
    public int B;
    public int C;
    public ActivityDrinkReminderBinding D;
    public boolean E;
    @Nullable
    public BottomSheetDialogTwoButtons F;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public DrinkWaterReminderData drinkWaterReminderDataCommon;
    public DrinkWaterReminderViewModel drinkWaterReminderViewModel;
    public boolean p;
    public TextView q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    @Nullable
    public String x;
    public boolean y;
    public int z;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityDrinkWaterReminder this$0, View view) {
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
            if (ActivityDrinkWaterReminder.this.getBoolSaveVisible()) {
                if (!AppUtils.isNetConnected(ActivityDrinkWaterReminder.this)) {
                    ActivityDrinkWaterReminder.this.showNoInternetMessage();
                } else if (BleApiManager.getInstance(ActivityDrinkWaterReminder.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    DrinkWaterReminderViewModel drinkWaterReminderViewModel = ActivityDrinkWaterReminder.this.getDrinkWaterReminderViewModel();
                    int i = ActivityDrinkWaterReminder.this.r;
                    boolean z = ActivityDrinkWaterReminder.this.y;
                    ActivityDrinkReminderBinding activityDrinkReminderBinding = ActivityDrinkWaterReminder.this.D;
                    ActivityDrinkReminderBinding activityDrinkReminderBinding2 = null;
                    if (activityDrinkReminderBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityDrinkReminderBinding = null;
                    }
                    String obj = activityDrinkReminderBinding.reminderLayout.tvStartTime.getText().toString();
                    ActivityDrinkReminderBinding activityDrinkReminderBinding3 = ActivityDrinkWaterReminder.this.D;
                    if (activityDrinkReminderBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityDrinkReminderBinding2 = activityDrinkReminderBinding3;
                    }
                    drinkWaterReminderViewModel.callSaveAndBleApi(i, z, obj, activityDrinkReminderBinding2.reminderLayout.tvEndTime.getText().toString());
                } else {
                    ActivityDrinkWaterReminder activityDrinkWaterReminder = ActivityDrinkWaterReminder.this;
                    String string = activityDrinkWaterReminder.getString(R.string.band_not_connected);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                    String string2 = ActivityDrinkWaterReminder.this.getString(R.string.please_connect_the_device);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                    final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activityDrinkWaterReminder, string, string2);
                    String string3 = ActivityDrinkWaterReminder.this.getString(R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                    final ActivityDrinkWaterReminder activityDrinkWaterReminder2 = ActivityDrinkWaterReminder.this;
                    bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.b7
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityDrinkWaterReminder.a.invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage.this, activityDrinkWaterReminder2, view);
                        }
                    });
                    bottomSheetDialogOneButtonTitleMessage.show();
                }
            }
        }
    }

    public static final void F(ActivityDrinkWaterReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.F;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
    }

    public static final void G(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityDrinkWaterReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void H(ActivityDrinkWaterReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.F;
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

    public static final void I(ActivityDrinkWaterReminder this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y = z;
        this$0.U(z);
        this$0.buttonVisible();
        if (this$0.y) {
            View reminderLayout = this$0._$_findCachedViewById(R.id.reminderLayout);
            Intrinsics.checkNotNullExpressionValue(reminderLayout, "reminderLayout");
            this$0.visible(reminderLayout);
            return;
        }
        View reminderLayout2 = this$0._$_findCachedViewById(R.id.reminderLayout);
        Intrinsics.checkNotNullExpressionValue(reminderLayout2, "reminderLayout");
        this$0.gone(reminderLayout2);
    }

    public static final void J(final ActivityDrinkWaterReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this$0, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.activities.s6
                @Override // android.app.TimePickerDialog.OnTimeSetListener
                public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                    ActivityDrinkWaterReminder.K(ActivityDrinkWaterReminder.this, timePicker, i, i2);
                }
            }, this$0.z, this$0.A, DateFormat.is24HourFormat(this$0));
            timePickerDialog.show();
            timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.coveiot.android.leonardo.more.activities.t6
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    ActivityDrinkWaterReminder.L(dialogInterface);
                }
            });
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_drink_reminder), 0).show();
    }

    public static final void K(ActivityDrinkWaterReminder this$0, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onReminderTimeSet(i, i2, true);
        this$0.buttonVisible();
        this$0.z = i;
        this$0.A = i2;
    }

    public static final void L(DialogInterface dialogInterface) {
    }

    public static final void M(final ActivityDrinkWaterReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this$0, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.activities.n6
                @Override // android.app.TimePickerDialog.OnTimeSetListener
                public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                    ActivityDrinkWaterReminder.N(ActivityDrinkWaterReminder.this, timePicker, i, i2);
                }
            }, this$0.B, this$0.C, DateFormat.is24HourFormat(this$0));
            timePickerDialog.show();
            timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.coveiot.android.leonardo.more.activities.u6
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    ActivityDrinkWaterReminder.O(dialogInterface);
                }
            });
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_drink_reminder), 0).show();
    }

    public static final void N(ActivityDrinkWaterReminder this_run, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this_run.onReminderTimeSet(i, i2, false);
        this_run.buttonVisible();
        this_run.B = i;
        this_run.C = i2;
    }

    public static final void O(DialogInterface dialogInterface) {
    }

    public static final void P(ActivityDrinkWaterReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.E();
    }

    public static final void Q(ActivityDrinkWaterReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.q;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public static final void V(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityDrinkWaterReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void W(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityDrinkWaterReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public final void E() {
        int startHour;
        String str;
        int endHour;
        String str2;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        this.E = false;
        DrinkWaterReminderData drinkWaterReminderDataFromPref = getDrinkWaterReminderViewModel().getDrinkWaterReminderDataFromPref();
        if (drinkWaterReminderDataFromPref.getStartHour() > 12) {
            str = ' ' + getResources().getString(R.string.PM);
            startHour = drinkWaterReminderDataFromPref.getStartHour() - 12;
        } else if (drinkWaterReminderDataFromPref.getStartHour() == 12) {
            str = ' ' + getResources().getString(R.string.PM);
            startHour = drinkWaterReminderDataFromPref.getStartHour();
        } else {
            startHour = drinkWaterReminderDataFromPref.getStartHour();
            str = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(startHour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(drinkWaterReminderDataFromPref.getStartMinute())}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        String sb2 = sb.toString();
        if (drinkWaterReminderDataFromPref.getEndHour() > 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            endHour = drinkWaterReminderDataFromPref.getEndHour() - 12;
        } else if (drinkWaterReminderDataFromPref.getEndHour() == 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            endHour = drinkWaterReminderDataFromPref.getEndHour();
        } else {
            endHour = drinkWaterReminderDataFromPref.getEndHour();
            str2 = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(endHour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(drinkWaterReminderDataFromPref.getEndMinute())}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(str2);
        String sb4 = sb3.toString();
        if (drinkWaterReminderDataFromPref.isReminderEnable() != this.y) {
            this.E = true;
        } else if (this.s != this.r) {
            this.E = true;
        } else {
            ActivityDrinkReminderBinding activityDrinkReminderBinding = this.D;
            if (activityDrinkReminderBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDrinkReminderBinding = null;
            }
            if (!kotlin.text.m.equals(sb2, activityDrinkReminderBinding.reminderLayout.tvStartTime.getText().toString(), true)) {
                this.E = true;
            } else {
                ActivityDrinkReminderBinding activityDrinkReminderBinding2 = this.D;
                if (activityDrinkReminderBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDrinkReminderBinding2 = null;
                }
                if (!kotlin.text.m.equals(sb4, activityDrinkReminderBinding2.reminderLayout.tvEndTime.getText().toString(), true)) {
                    this.E = true;
                }
            }
        }
        if (this.E) {
            if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.DISCONNECTED) {
                String string = getString(R.string.band_not_connected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                String string2 = getString(R.string.please_connect_the_device);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
                String string3 = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.q6
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityDrinkWaterReminder.G(BottomSheetDialogOneButtonTitleMessage.this, this, view);
                    }
                });
                bottomSheetDialogOneButtonTitleMessage.show();
                return;
            }
            if (this.F == null) {
                String string4 = getString(R.string.confirmation);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.confirmation)");
                String string5 = getString(R.string.save_changes);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.save_changes)");
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = new BottomSheetDialogTwoButtons(this, string4, string5, false, 8, null);
                this.F = bottomSheetDialogTwoButtons2;
                String string6 = getString(R.string.save_changes_btn);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.save_changes_btn)");
                bottomSheetDialogTwoButtons2.setPositiveButton(string6, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.a7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityDrinkWaterReminder.H(ActivityDrinkWaterReminder.this, view);
                    }
                });
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.F;
                if (bottomSheetDialogTwoButtons3 != null) {
                    String string7 = getString(R.string.discard);
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.discard)");
                    bottomSheetDialogTwoButtons3.setNegativeButton(string7, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.y6
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityDrinkWaterReminder.F(ActivityDrinkWaterReminder.this, view);
                        }
                    });
                }
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.F;
            Boolean valueOf = bottomSheetDialogTwoButtons4 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons4.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.F) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    public final void R() {
        initToolbar();
        initClickListeners();
        S();
    }

    public final void S() {
        int startHour;
        String str;
        int endHour;
        String str2;
        DrinkWaterReminderData drinkWaterReminderDataFromPref = getDrinkWaterReminderViewModel().getDrinkWaterReminderDataFromPref();
        setDrinkWaterReminderDataCommon(getDrinkWaterReminderViewModel().getDrinkWaterReminderDataFromPref());
        this.z = drinkWaterReminderDataFromPref.getStartHour();
        this.A = drinkWaterReminderDataFromPref.getStartMinute();
        this.B = drinkWaterReminderDataFromPref.getEndHour();
        this.C = drinkWaterReminderDataFromPref.getEndMinute();
        if (drinkWaterReminderDataFromPref.getStartHour() > 12) {
            str = ' ' + getResources().getString(R.string.PM);
            startHour = drinkWaterReminderDataFromPref.getStartHour() - 12;
        } else if (drinkWaterReminderDataFromPref.getStartHour() == 12) {
            str = ' ' + getResources().getString(R.string.PM);
            startHour = drinkWaterReminderDataFromPref.getStartHour();
        } else {
            startHour = drinkWaterReminderDataFromPref.getStartHour();
            str = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(startHour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(drinkWaterReminderDataFromPref.getStartMinute())}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        String sb2 = sb.toString();
        if (drinkWaterReminderDataFromPref.getEndHour() > 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            endHour = drinkWaterReminderDataFromPref.getEndHour() - 12;
        } else if (drinkWaterReminderDataFromPref.getEndHour() == 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            endHour = drinkWaterReminderDataFromPref.getEndHour();
        } else {
            endHour = drinkWaterReminderDataFromPref.getEndHour();
            str2 = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(endHour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(drinkWaterReminderDataFromPref.getEndMinute())}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(str2);
        String sb4 = sb3.toString();
        ActivityDrinkReminderBinding activityDrinkReminderBinding = this.D;
        ActivityDrinkReminderBinding activityDrinkReminderBinding2 = null;
        if (activityDrinkReminderBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDrinkReminderBinding = null;
        }
        activityDrinkReminderBinding.reminderLayout.tvStartTime.setText(sb2);
        ActivityDrinkReminderBinding activityDrinkReminderBinding3 = this.D;
        if (activityDrinkReminderBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDrinkReminderBinding3 = null;
        }
        activityDrinkReminderBinding3.reminderLayout.tvEndTime.setText(sb4);
        this.r = drinkWaterReminderDataFromPref.getRemindInterval();
        this.s = drinkWaterReminderDataFromPref.getRemindInterval();
        IntervalAdapterNew intervalAdapterNew = new IntervalAdapterNew(new Integer[]{60, 90, 120, 150}, this);
        intervalAdapterNew.setSelectedInterval(Integer.valueOf(this.r));
        intervalAdapterNew.setListner(this);
        ActivityDrinkReminderBinding activityDrinkReminderBinding4 = this.D;
        if (activityDrinkReminderBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDrinkReminderBinding2 = activityDrinkReminderBinding4;
        }
        activityDrinkReminderBinding2.reminderLayout.rcvInterval.setAdapter(intervalAdapterNew);
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).setChecked(drinkWaterReminderDataFromPref.isReminderEnable());
        this.y = drinkWaterReminderDataFromPref.isReminderEnable();
    }

    public final void T(View view, float f) {
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
                T(childAt, f);
            } else if (childAt instanceof TextView) {
                ((TextView) childAt).setAlpha(f);
            }
            if (i == childCount) {
                return;
            }
            i++;
        }
    }

    public final void U(boolean z) {
        ActivityDrinkReminderBinding activityDrinkReminderBinding = null;
        if (z) {
            ActivityDrinkReminderBinding activityDrinkReminderBinding2 = this.D;
            if (activityDrinkReminderBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDrinkReminderBinding = activityDrinkReminderBinding2;
            }
            View root = activityDrinkReminderBinding.reminderLayout.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.reminderLayout.root");
            T(root, 1.0f);
            return;
        }
        ActivityDrinkReminderBinding activityDrinkReminderBinding3 = this.D;
        if (activityDrinkReminderBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDrinkReminderBinding = activityDrinkReminderBinding3;
        }
        View root2 = activityDrinkReminderBinding.reminderLayout.getRoot();
        Intrinsics.checkNotNullExpressionValue(root2, "binding.reminderLayout.root");
        T(root2, 0.5f);
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
        String str;
        String str2;
        if (getDrinkWaterReminderDataCommon().getStartHour() > 12) {
            str = ' ' + getResources().getString(R.string.PM);
            getDrinkWaterReminderDataCommon().setStartHour(getDrinkWaterReminderDataCommon().getStartHour() - 12);
        } else if (getDrinkWaterReminderDataCommon().getStartHour() == 12) {
            str = ' ' + getResources().getString(R.string.PM);
            getDrinkWaterReminderDataCommon().setStartHour(getDrinkWaterReminderDataCommon().getStartHour());
        } else {
            str = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getDrinkWaterReminderDataCommon().getStartHour())}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getDrinkWaterReminderDataCommon().getStartMinute())}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        String sb2 = sb.toString();
        if (getDrinkWaterReminderDataCommon().getEndHour() > 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            getDrinkWaterReminderDataCommon().setEndHour(getDrinkWaterReminderDataCommon().getEndHour() - 12);
        } else if (getDrinkWaterReminderDataCommon().getEndHour() == 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            getDrinkWaterReminderDataCommon().setEndHour(getDrinkWaterReminderDataCommon().getEndHour());
        } else {
            str2 = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getDrinkWaterReminderDataCommon().getEndHour())}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getDrinkWaterReminderDataCommon().getEndMinute())}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(str2);
        String sb4 = sb3.toString();
        ActivityDrinkReminderBinding activityDrinkReminderBinding = null;
        if (!Boolean.valueOf(((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).isChecked()).equals(Boolean.valueOf(getDrinkWaterReminderDataCommon().isReminderEnable()))) {
            ActivityDrinkReminderBinding activityDrinkReminderBinding2 = this.D;
            if (activityDrinkReminderBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDrinkReminderBinding = activityDrinkReminderBinding2;
            }
            activityDrinkReminderBinding.btnSave.setEnabled(true);
            this.p = true;
        } else if (getDrinkWaterReminderDataCommon().getRemindInterval() != this.r) {
            ActivityDrinkReminderBinding activityDrinkReminderBinding3 = this.D;
            if (activityDrinkReminderBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDrinkReminderBinding = activityDrinkReminderBinding3;
            }
            activityDrinkReminderBinding.btnSave.setEnabled(true);
            this.p = true;
        } else {
            ActivityDrinkReminderBinding activityDrinkReminderBinding4 = this.D;
            if (activityDrinkReminderBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDrinkReminderBinding4 = null;
            }
            if (!activityDrinkReminderBinding4.reminderLayout.tvStartTime.getText().toString().equals(sb2)) {
                ActivityDrinkReminderBinding activityDrinkReminderBinding5 = this.D;
                if (activityDrinkReminderBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityDrinkReminderBinding = activityDrinkReminderBinding5;
                }
                activityDrinkReminderBinding.btnSave.setEnabled(true);
                this.p = true;
                return;
            }
            ActivityDrinkReminderBinding activityDrinkReminderBinding6 = this.D;
            if (activityDrinkReminderBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDrinkReminderBinding6 = null;
            }
            if (!activityDrinkReminderBinding6.reminderLayout.tvEndTime.getText().toString().equals(sb4)) {
                ActivityDrinkReminderBinding activityDrinkReminderBinding7 = this.D;
                if (activityDrinkReminderBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityDrinkReminderBinding = activityDrinkReminderBinding7;
                }
                activityDrinkReminderBinding.btnSave.setEnabled(true);
                this.p = true;
                return;
            }
            ActivityDrinkReminderBinding activityDrinkReminderBinding8 = this.D;
            if (activityDrinkReminderBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDrinkReminderBinding = activityDrinkReminderBinding8;
            }
            activityDrinkReminderBinding.btnSave.setEnabled(false);
            this.p = false;
        }
    }

    public final boolean getBoolSaveVisible() {
        return this.p;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationDialog() {
        return this.F;
    }

    @NotNull
    public final DrinkWaterReminderData getDrinkWaterReminderDataCommon() {
        DrinkWaterReminderData drinkWaterReminderData = this.drinkWaterReminderDataCommon;
        if (drinkWaterReminderData != null) {
            return drinkWaterReminderData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("drinkWaterReminderDataCommon");
        return null;
    }

    @NotNull
    public final DrinkWaterReminderViewModel getDrinkWaterReminderViewModel() {
        DrinkWaterReminderViewModel drinkWaterReminderViewModel = this.drinkWaterReminderViewModel;
        if (drinkWaterReminderViewModel != null) {
            return drinkWaterReminderViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("drinkWaterReminderViewModel");
        return null;
    }

    public final boolean getHasInfoChanged() {
        return this.E;
    }

    public final void initClickListeners() {
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.r6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityDrinkWaterReminder.I(ActivityDrinkWaterReminder.this, compoundButton, z);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        ActivityDrinkReminderBinding activityDrinkReminderBinding = this.D;
        TextView textView = null;
        if (activityDrinkReminderBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDrinkReminderBinding = null;
        }
        activityDrinkReminderBinding.reminderLayout.rcvInterval.setLayoutManager(linearLayoutManager);
        IntervalAdapterNew intervalAdapterNew = new IntervalAdapterNew(new Integer[]{60, 90, 120, 150}, this);
        intervalAdapterNew.setSelectedInterval(Integer.valueOf(this.r));
        intervalAdapterNew.setListner(this);
        ActivityDrinkReminderBinding activityDrinkReminderBinding2 = this.D;
        if (activityDrinkReminderBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDrinkReminderBinding2 = null;
        }
        activityDrinkReminderBinding2.reminderLayout.rcvInterval.setAdapter(intervalAdapterNew);
        ActivityDrinkReminderBinding activityDrinkReminderBinding3 = this.D;
        if (activityDrinkReminderBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDrinkReminderBinding3 = null;
        }
        activityDrinkReminderBinding3.reminderLayout.tvStartTime.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.x6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDrinkWaterReminder.J(ActivityDrinkWaterReminder.this, view);
            }
        });
        ActivityDrinkReminderBinding activityDrinkReminderBinding4 = this.D;
        if (activityDrinkReminderBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDrinkReminderBinding4 = null;
        }
        activityDrinkReminderBinding4.reminderLayout.tvEndTime.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.v6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDrinkWaterReminder.M(ActivityDrinkWaterReminder.this, view);
            }
        });
        TextView textView2 = this.q;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
        } else {
            textView = textView2;
        }
        ViewUtilsKt.setSafeOnClickListener(textView, new a());
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.drink_reminder));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.z6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDrinkWaterReminder.P(ActivityDrinkWaterReminder.this, view);
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.q = textView;
        ActivityDrinkReminderBinding activityDrinkReminderBinding = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setText(getString(R.string.save_camel));
        TextView textView2 = this.q;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView2 = null;
        }
        textView2.setAlpha(0.5f);
        ActivityDrinkReminderBinding activityDrinkReminderBinding2 = this.D;
        if (activityDrinkReminderBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDrinkReminderBinding = activityDrinkReminderBinding2;
        }
        activityDrinkReminderBinding.btnSave.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.w6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDrinkWaterReminder.Q(ActivityDrinkWaterReminder.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        E();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityDrinkReminderBinding inflate = ActivityDrinkReminderBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.D = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        setDrinkWaterReminderViewModel((DrinkWaterReminderViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(DrinkWaterReminderViewModel.class));
        getDrinkWaterReminderViewModel().setDialogListener(this);
        R();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.IntervalAdapterNew.IntervalSelectionListener
    public void onIntervalSelected(int i) {
        this.r = i;
        buttonVisible();
    }

    @Override // com.coveiot.android.leonardo.more.listeners.ReminderListener
    public void onReminderTimeSet(int i, int i2, boolean z) {
        ActivityDrinkReminderBinding activityDrinkReminderBinding = null;
        if (z) {
            this.t = i;
            this.u = i2;
            if (i > 12) {
                this.x = ' ' + getResources().getString(R.string.PM);
                this.t = this.t - 12;
            } else if (i == 12) {
                this.x = ' ' + getResources().getString(R.string.PM);
                this.t = this.t;
            } else {
                this.x = ' ' + getResources().getString(R.string.AM);
            }
            if (this.t == 0) {
                this.t = 12;
            }
            ActivityDrinkReminderBinding activityDrinkReminderBinding2 = this.D;
            if (activityDrinkReminderBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDrinkReminderBinding = activityDrinkReminderBinding2;
            }
            TextView textView = activityDrinkReminderBinding.reminderLayout.tvStartTime;
            StringBuilder sb = new StringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.t)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(':');
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.u)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb.append(format2);
            sb.append(this.x);
            textView.setText(sb.toString());
            return;
        }
        this.v = i;
        this.w = i2;
        if (i > 12) {
            this.x = ' ' + getResources().getString(R.string.PM);
            this.v = this.v - 12;
        } else if (i == 12) {
            this.x = ' ' + getResources().getString(R.string.PM);
            this.v = this.v;
        } else {
            this.x = ' ' + getResources().getString(R.string.AM);
        }
        if (this.v == 0) {
            this.v = 12;
        }
        ActivityDrinkReminderBinding activityDrinkReminderBinding3 = this.D;
        if (activityDrinkReminderBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDrinkReminderBinding = activityDrinkReminderBinding3;
        }
        TextView textView2 = activityDrinkReminderBinding.reminderLayout.tvEndTime;
        StringBuilder sb2 = new StringBuilder();
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Locale locale2 = Locale.ENGLISH;
        String format3 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.v)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb2.append(format3);
        sb2.append(':');
        String format4 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.w)}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb2.append(format4);
        sb2.append(this.x);
        textView2.setText(sb2.toString());
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setBoolSaveVisible(boolean z) {
        this.p = z;
    }

    public final void setConfirmationDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.F = bottomSheetDialogTwoButtons;
    }

    public final void setDrinkWaterReminderDataCommon(@NotNull DrinkWaterReminderData drinkWaterReminderData) {
        Intrinsics.checkNotNullParameter(drinkWaterReminderData, "<set-?>");
        this.drinkWaterReminderDataCommon = drinkWaterReminderData;
    }

    public final void setDrinkWaterReminderViewModel(@NotNull DrinkWaterReminderViewModel drinkWaterReminderViewModel) {
        Intrinsics.checkNotNullParameter(drinkWaterReminderViewModel, "<set-?>");
        this.drinkWaterReminderViewModel = drinkWaterReminderViewModel;
    }

    public final void setHasInfoChanged(boolean z) {
        this.E = z;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.p6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDrinkWaterReminder.V(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.o6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDrinkWaterReminder.W(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }
}
