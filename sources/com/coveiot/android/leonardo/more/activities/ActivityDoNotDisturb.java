package com.coveiot.android.leonardo.more.activities;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.BatterySaverModeHelper;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.activities.ActivityDoNotDisturb;
import com.coveiot.android.leonardo.more.listeners.ReminderListener;
import com.coveiot.android.leonardo.more.models.DndUpdated;
import com.coveiot.android.leonardo.more.viewmodel.DoNotDisturbViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DoNotDisturbData;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.squareup.otto.Subscribe;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityDoNotDisturb extends BaseActivity implements DialogListener, ReminderListener, BatterySaverModeDialogClickCallback {
    public int A;
    public TextView B;
    public boolean C;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public DoNotDisturbData doNotDisturbDataCommon;
    public DoNotDisturbViewModel doNotDisturbViewModel;
    public boolean p;
    public int q;
    public int r;
    public int s;
    public int t;
    @Nullable
    public String u;
    public boolean v;
    public boolean w;
    public int x;
    public int y;
    public int z;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityDoNotDisturb this$0, View view) {
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
            if (ActivityDoNotDisturb.this.getBoolSaveVisible()) {
                if (ActivityDoNotDisturb.this.C) {
                    UserDataManager.getInstance(ActivityDoNotDisturb.this).saveScheuleDnd(((SwitchCompat) ActivityDoNotDisturb.this._$_findCachedViewById(R.id.switch_reminder)).isChecked());
                }
                if (!AppUtils.isNetConnected(ActivityDoNotDisturb.this)) {
                    ActivityDoNotDisturb.this.showNoInternetMessage();
                } else if (BleApiManager.getInstance(ActivityDoNotDisturb.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    if (DeviceUtils.Companion.isMatrixDevice(ActivityDoNotDisturb.this)) {
                        ActivityDoNotDisturb.this.v = false;
                    }
                    if (ActivityDoNotDisturb.this.v) {
                        ActivityDoNotDisturb activityDoNotDisturb = ActivityDoNotDisturb.this;
                        int i = R.id.tv_start_time;
                        if (Intrinsics.areEqual(((TextView) activityDoNotDisturb._$_findCachedViewById(i)).getText().toString(), "00:00 AM") || Intrinsics.areEqual(((TextView) ActivityDoNotDisturb.this._$_findCachedViewById(i)).getText().toString(), "12:00 AM")) {
                            ActivityDoNotDisturb activityDoNotDisturb2 = ActivityDoNotDisturb.this;
                            int i2 = R.id.tv_end_time;
                            if (Intrinsics.areEqual(((TextView) activityDoNotDisturb2._$_findCachedViewById(i2)).getText().toString(), "00:00 AM") || Intrinsics.areEqual(((TextView) ActivityDoNotDisturb.this._$_findCachedViewById(i2)).getText().toString(), "12:00 AM")) {
                                ActivityDoNotDisturb activityDoNotDisturb3 = ActivityDoNotDisturb.this;
                                Toast.makeText(activityDoNotDisturb3, activityDoNotDisturb3.getString(R.string.dnd_schedule_time_error_msg), 1).show();
                                return;
                            }
                        }
                    }
                    ActivityDoNotDisturb.this.getDoNotDisturbViewModel().callSaveAndBleApi(ActivityDoNotDisturb.this.v, ActivityDoNotDisturb.this.w, ((TextView) ActivityDoNotDisturb.this._$_findCachedViewById(R.id.tv_start_time)).getText().toString(), ((TextView) ActivityDoNotDisturb.this._$_findCachedViewById(R.id.tv_end_time)).getText().toString());
                } else {
                    ActivityDoNotDisturb activityDoNotDisturb4 = ActivityDoNotDisturb.this;
                    String string = activityDoNotDisturb4.getString(R.string.band_not_connected);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                    String string2 = ActivityDoNotDisturb.this.getString(R.string.please_connect_the_device);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                    final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activityDoNotDisturb4, string, string2);
                    String string3 = ActivityDoNotDisturb.this.getString(R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                    final ActivityDoNotDisturb activityDoNotDisturb5 = ActivityDoNotDisturb.this;
                    bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.m6
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityDoNotDisturb.a.invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage.this, activityDoNotDisturb5, view);
                        }
                    });
                    bottomSheetDialogOneButtonTitleMessage.show();
                }
            }
        }
    }

    public static final void H(ActivityDoNotDisturb this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.B;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setAlpha(1.0f);
        this$0.p = true;
        this$0.C = true;
    }

    public static final void I(final ActivityDoNotDisturb this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
            TextView textView = this$0.B;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveText");
                textView = null;
            }
            textView.setAlpha(1.0f);
            this$0.p = true;
            ((Button) this$0._$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this$0, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.activities.w5
                @Override // android.app.TimePickerDialog.OnTimeSetListener
                public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                    ActivityDoNotDisturb.J(ActivityDoNotDisturb.this, timePicker, i, i2);
                }
            }, this$0.z, this$0.A, DateFormat.is24HourFormat(this$0));
            timePickerDialog.show();
            timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.coveiot.android.leonardo.more.activities.e6
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    ActivityDoNotDisturb.K(dialogInterface);
                }
            });
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_Schedule_dnd), 0).show();
    }

    public static final void J(ActivityDoNotDisturb this_run, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this_run.onReminderTimeSet(i, i2, false);
        this_run.z = i;
        this_run.A = i2;
    }

    public static final void K(DialogInterface dialogInterface) {
    }

    public static final void L(ActivityDoNotDisturb this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.B;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setAlpha(1.0f);
        ((Button) this$0._$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
        this$0.p = true;
        this$0.v = z;
        ((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).setChecked(z);
        if (z) {
            ((SwitchCompat) this$0._$_findCachedViewById(R.id.dnd_switch)).setChecked(false);
            this$0.w = false;
            if (BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isScheduledDndSupported()) {
                LinearLayout reminder_layout = (LinearLayout) this$0._$_findCachedViewById(R.id.reminder_layout);
                Intrinsics.checkNotNullExpressionValue(reminder_layout, "reminder_layout");
                this$0.visible(reminder_layout);
                return;
            }
            LinearLayout reminder_layout2 = (LinearLayout) this$0._$_findCachedViewById(R.id.reminder_layout);
            Intrinsics.checkNotNullExpressionValue(reminder_layout2, "reminder_layout");
            this$0.gone(reminder_layout2);
            return;
        }
        LinearLayout reminder_layout3 = (LinearLayout) this$0._$_findCachedViewById(R.id.reminder_layout);
        Intrinsics.checkNotNullExpressionValue(reminder_layout3, "reminder_layout");
        this$0.gone(reminder_layout3);
    }

    public static final void M(ActivityDoNotDisturb this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.w = z;
        this$0.C = false;
        TextView textView = this$0.B;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setAlpha(1.0f);
        ((Button) this$0._$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
        this$0.p = true;
        if (z) {
            ((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).setChecked(false);
            this$0.v = false;
            return;
        }
        ((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).setChecked(false);
        this$0.C = true;
        this$0.v = false;
    }

    public static final void N(final ActivityDoNotDisturb this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).isChecked()) {
            TextView textView = this$0.B;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveText");
                textView = null;
            }
            textView.setAlpha(1.0f);
            this$0.p = true;
            ((Button) this$0._$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this$0, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.activities.d6
                @Override // android.app.TimePickerDialog.OnTimeSetListener
                public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                    ActivityDoNotDisturb.O(ActivityDoNotDisturb.this, timePicker, i, i2);
                }
            }, this$0.x, this$0.y, DateFormat.is24HourFormat(this$0));
            timePickerDialog.show();
            timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.coveiot.android.leonardo.more.activities.f6
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    ActivityDoNotDisturb.P(dialogInterface);
                }
            });
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_Schedule_dnd), 0).show();
    }

    public static final void O(ActivityDoNotDisturb this$0, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onReminderTimeSet(i, i2, true);
        this$0.x = i;
        this$0.y = i2;
    }

    public static final void P(DialogInterface dialogInterface) {
    }

    public static final void Q(ActivityDoNotDisturb this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void R(ActivityDoNotDisturb this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.B;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public static final void T(ActivityDoNotDisturb this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.B;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public static final void U(BottomSheetDialogTwoButtons dialog, ActivityDoNotDisturb this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void V(final ActivityDoNotDisturb this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0) == null || BleApiManager.getInstance(this$0).getBleApi() == null || !BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            return;
        }
        BatterySaverModeHelper.Companion.getInstance(this$0).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityDoNotDisturb$onCreate$2$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityDoNotDisturb$onCreate$2$1$onBatterySavingSettingsReceived$1", f = "ActivityDoNotDisturb.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ boolean $isEnabled;
                public final /* synthetic */ int $mode;
                public int label;
                public final /* synthetic */ ActivityDoNotDisturb this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(boolean z, int i, ActivityDoNotDisturb activityDoNotDisturb, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$isEnabled = z;
                    this.$mode = i;
                    this.this$0 = activityDoNotDisturb;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$isEnabled, this.$mode, this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        if (this.$isEnabled && this.$mode == 1) {
                            ((SwitchCompat) this.this$0._$_findCachedViewById(R.id.dnd_switch)).setChecked(true);
                            this.this$0.showBatterySaverModeEnabledDialog(null);
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySaverCMDFailed() {
                Toast.makeText(ActivityDoNotDisturb.this, (int) R.string.somethings_went_wrong, 0).show();
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySavingSettingsReceived(boolean z, int i) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityDoNotDisturb.this), Dispatchers.getMain(), null, new a(z, i, ActivityDoNotDisturb.this, null), 2, null);
            }
        });
    }

    public static final void X(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityDoNotDisturb this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void Y(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityDoNotDisturb this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public final boolean G() {
        DoNotDisturbData donotDisturbDataFromPref = getDoNotDisturbViewModel().getDonotDisturbDataFromPref();
        return (donotDisturbDataFromPref.isDnd_on_off() == this.w && this.x == donotDisturbDataFromPref.getBeggining_time_hour() && this.y == donotDisturbDataFromPref.getBeggining_time_minutes() && this.z == donotDisturbDataFromPref.getEnd_time_hour() && this.A == donotDisturbDataFromPref.getEnd_time_minutes()) ? false : true;
    }

    public final void S() {
        initToolbar();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isCZDevice(this) && !companion.isIDODevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isMatrixDevice(this) && !companion.isTouchELXDevice(this) && !companion.isEastApexDevice(this) && !companion.isBESDevice(this)) {
            W();
            return;
        }
        showProgress();
        SettingsSyncHelper.Companion.getInstance(this).readDndInfoFromBand(new SettingsSyncHelper.SettingsSyncListner() { // from class: com.coveiot.android.leonardo.more.activities.ActivityDoNotDisturb$initView$1
            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
            public void onProgressUpdate(int i) {
            }

            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
            public void onSettingSyncError() {
                ActivityDoNotDisturb.this.dismissProgress();
                ActivityDoNotDisturb.this.W();
            }

            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
            public void onSettingsSyncCompleted() {
                ActivityDoNotDisturb.this.dismissProgress();
                ActivityDoNotDisturb.this.W();
            }
        });
    }

    public final void W() {
        int beggining_time_hour;
        String str;
        int end_time_hour;
        String str2;
        DoNotDisturbData donotDisturbDataFromPref = getDoNotDisturbViewModel().getDonotDisturbDataFromPref();
        setDoNotDisturbDataCommon(getDoNotDisturbViewModel().getDonotDisturbDataFromPref());
        this.x = donotDisturbDataFromPref.getBeggining_time_hour();
        this.y = donotDisturbDataFromPref.getBeggining_time_minutes();
        this.z = donotDisturbDataFromPref.getEnd_time_hour();
        this.A = donotDisturbDataFromPref.getEnd_time_minutes();
        if (donotDisturbDataFromPref.getBeggining_time_hour() > 12) {
            str = ' ' + getResources().getString(R.string.PM);
            beggining_time_hour = donotDisturbDataFromPref.getBeggining_time_hour() - 12;
        } else if (donotDisturbDataFromPref.getBeggining_time_hour() == 12) {
            str = ' ' + getResources().getString(R.string.PM);
            beggining_time_hour = donotDisturbDataFromPref.getBeggining_time_hour();
        } else {
            beggining_time_hour = donotDisturbDataFromPref.getBeggining_time_hour();
            str = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(beggining_time_hour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(donotDisturbDataFromPref.getBeggining_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        String sb2 = sb.toString();
        if (donotDisturbDataFromPref.getEnd_time_hour() > 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            end_time_hour = donotDisturbDataFromPref.getEnd_time_hour() - 12;
        } else if (donotDisturbDataFromPref.getEnd_time_hour() == 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            end_time_hour = donotDisturbDataFromPref.getEnd_time_hour();
        } else {
            end_time_hour = donotDisturbDataFromPref.getEnd_time_hour();
            str2 = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(end_time_hour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(donotDisturbDataFromPref.getEnd_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(str2);
        String sb4 = sb3.toString();
        ((TextView) _$_findCachedViewById(R.id.tv_start_time)).setText(sb2);
        ((TextView) _$_findCachedViewById(R.id.tv_end_time)).setText(sb4);
        this.v = donotDisturbDataFromPref.isSchedule_on_off();
        this.w = donotDisturbDataFromPref.isDnd_on_off();
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).setChecked(donotDisturbDataFromPref.isSchedule_on_off());
        ((SwitchCompat) _$_findCachedViewById(R.id.dnd_switch)).setChecked(donotDisturbDataFromPref.isDnd_on_off());
        if (donotDisturbDataFromPref.isSchedule_on_off()) {
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isScheduledDndSupported()) {
                LinearLayout reminder_layout = (LinearLayout) _$_findCachedViewById(R.id.reminder_layout);
                Intrinsics.checkNotNullExpressionValue(reminder_layout, "reminder_layout");
                visible(reminder_layout);
            } else {
                LinearLayout reminder_layout2 = (LinearLayout) _$_findCachedViewById(R.id.reminder_layout);
                Intrinsics.checkNotNullExpressionValue(reminder_layout2, "reminder_layout");
                gone(reminder_layout2);
            }
        } else {
            LinearLayout reminder_layout3 = (LinearLayout) _$_findCachedViewById(R.id.reminder_layout);
            Intrinsics.checkNotNullExpressionValue(reminder_layout3, "reminder_layout");
            gone(reminder_layout3);
        }
        initClickListeners();
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

    public final boolean getBoolSaveVisible() {
        return this.p;
    }

    @NotNull
    public final DoNotDisturbData getDoNotDisturbDataCommon() {
        DoNotDisturbData doNotDisturbData = this.doNotDisturbDataCommon;
        if (doNotDisturbData != null) {
            return doNotDisturbData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("doNotDisturbDataCommon");
        return null;
    }

    @NotNull
    public final DoNotDisturbViewModel getDoNotDisturbViewModel() {
        DoNotDisturbViewModel doNotDisturbViewModel = this.doNotDisturbViewModel;
        if (doNotDisturbViewModel != null) {
            return doNotDisturbViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("doNotDisturbViewModel");
        return null;
    }

    public final void initClickListeners() {
        int i = R.id.switch_reminder;
        ((SwitchCompat) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.h6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDoNotDisturb.H(ActivityDoNotDisturb.this, view);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(i)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.b6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityDoNotDisturb.L(ActivityDoNotDisturb.this, compoundButton, z);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(R.id.dnd_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.c6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityDoNotDisturb.M(ActivityDoNotDisturb.this, compoundButton, z);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_start_time)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.g6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDoNotDisturb.N(ActivityDoNotDisturb.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_end_time)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.k6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDoNotDisturb.I(ActivityDoNotDisturb.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.dnd_name));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.l6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDoNotDisturb.Q(ActivityDoNotDisturb.this, view);
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.B = textView;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setText(getString(R.string.save_camel));
        TextView textView3 = this.B;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView3 = null;
        }
        textView3.setAlpha(0.5f);
        TextView textView4 = this.B;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView4 = null;
        }
        textView4.setVisibility(8);
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.x5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDoNotDisturb.R(ActivityDoNotDisturb.this, view);
            }
        });
        TextView textView5 = this.B;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
        } else {
            textView2 = textView5;
        }
        ViewUtilsKt.setSafeOnClickListener(textView2, new a());
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isScheduledDndSupported()) {
            ((ConstraintLayout) _$_findCachedViewById(R.id.cl_reminder)).setVisibility(0);
            ((LinearLayout) _$_findCachedViewById(R.id.reminder_layout)).setVisibility(0);
            return;
        }
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_reminder)).setVisibility(8);
        ((LinearLayout) _$_findCachedViewById(R.id.reminder_layout)).setVisibility(8);
        UserDataManager.getInstance(this).saveScheuleDnd(false);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (G()) {
            String string = getString(R.string.dnd_name);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dnd_name)");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string2 = getString(R.string.save_changes_hr);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes_hr)");
            String format = String.format(string2, Arrays.copyOf(new Object[]{getString(R.string.dnd_name)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, format, false, 8, null);
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.j6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDoNotDisturb.T(ActivityDoNotDisturb.this, view);
                }
            });
            String string4 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.a6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDoNotDisturb.U(BottomSheetDialogTwoButtons.this, this, view);
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
        setContentView(R.layout.activity_dnd);
        setDoNotDisturbViewModel((DoNotDisturbViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(DoNotDisturbViewModel.class));
        getDoNotDisturbViewModel().setDialogListener(this);
        S();
        if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            BatterySaverModeHelper.Companion.getInstance(this).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityDoNotDisturb$onCreate$1

                @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityDoNotDisturb$onCreate$1$onBatterySavingSettingsReceived$1", f = "ActivityDoNotDisturb.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ boolean $isEnabled;
                    public final /* synthetic */ int $mode;
                    public int label;
                    public final /* synthetic */ ActivityDoNotDisturb this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(boolean z, int i, ActivityDoNotDisturb activityDoNotDisturb, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$isEnabled = z;
                        this.$mode = i;
                        this.this$0 = activityDoNotDisturb;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.$isEnabled, this.$mode, this.this$0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            if (this.$isEnabled && this.$mode == 1) {
                                ((SwitchCompat) this.this$0._$_findCachedViewById(R.id.dnd_switch)).setChecked(true);
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySaverCMDFailed() {
                }

                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySavingSettingsReceived(boolean z, int i) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityDoNotDisturb.this), Dispatchers.getMain(), null, new a(z, i, ActivityDoNotDisturb.this, null), 2, null);
                }
            });
        }
        ((SwitchCompat) _$_findCachedViewById(R.id.dnd_switch)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.i6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDoNotDisturb.V(ActivityDoNotDisturb.this, view);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Subscribe
    public final void onDndUpdated(@NotNull DndUpdated dndUpdated) {
        Intrinsics.checkNotNullParameter(dndUpdated, "dndUpdated");
        W();
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onNegativeButtonClicked() {
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onPositiveButtonClicked() {
    }

    @Override // com.coveiot.android.leonardo.more.listeners.ReminderListener
    public void onReminderTimeSet(int i, int i2, boolean z) {
        if (z) {
            this.q = i;
            this.r = i2;
            if (i > 12) {
                this.u = ' ' + getResources().getString(R.string.PM);
                this.q = this.q - 12;
            } else if (i == 12) {
                this.u = ' ' + getResources().getString(R.string.PM);
                this.q = this.q;
            } else {
                this.u = ' ' + getResources().getString(R.string.AM);
            }
            if (this.q == 0) {
                this.q = 12;
            }
            StringBuilder sb = new StringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.q)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(':');
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.r)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb.append(format2);
            sb.append(this.u);
            ((TextView) _$_findCachedViewById(R.id.tv_start_time)).setText(sb.toString());
            return;
        }
        this.s = i;
        this.t = i2;
        if (i > 12) {
            this.u = ' ' + getResources().getString(R.string.PM);
            this.s = this.s - 12;
        } else if (i == 12) {
            this.u = ' ' + getResources().getString(R.string.PM);
            this.s = this.s;
        } else {
            this.u = ' ' + getResources().getString(R.string.AM);
        }
        if (this.s == 0) {
            this.s = 12;
        }
        StringBuilder sb2 = new StringBuilder();
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Locale locale2 = Locale.ENGLISH;
        String format3 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.s)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb2.append(format3);
        sb2.append(':');
        String format4 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.t)}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb2.append(format4);
        sb2.append(this.u);
        ((TextView) _$_findCachedViewById(R.id.tv_end_time)).setText(sb2.toString());
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    public final void setBoolSaveVisible(boolean z) {
        this.p = z;
    }

    public final void setDoNotDisturbDataCommon(@NotNull DoNotDisturbData doNotDisturbData) {
        Intrinsics.checkNotNullParameter(doNotDisturbData, "<set-?>");
        this.doNotDisturbDataCommon = doNotDisturbData;
    }

    public final void setDoNotDisturbViewModel(@NotNull DoNotDisturbViewModel doNotDisturbViewModel) {
        Intrinsics.checkNotNullParameter(doNotDisturbViewModel, "<set-?>");
        this.doNotDisturbViewModel = doNotDisturbViewModel;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        onDismiss();
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.z5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDoNotDisturb.X(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        onDismiss();
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.y5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDoNotDisturb.Y(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }
}
