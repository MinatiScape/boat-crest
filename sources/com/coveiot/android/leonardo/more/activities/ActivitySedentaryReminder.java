package com.coveiot.android.leonardo.more.activities;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivitySedentaryReminderBinding;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.BatterySaverModeHelper;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.activities.ActivitySedentaryReminder;
import com.coveiot.android.leonardo.more.adapters.IntervalAdapterNew;
import com.coveiot.android.leonardo.more.listeners.ReminderListener;
import com.coveiot.android.leonardo.more.viewmodel.SedentaryReminderViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
import com.coveiot.covepreferences.data.SedentaryReminderData;
import com.coveiot.utils.utility.AppUtils;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
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
public final class ActivitySedentaryReminder extends BaseActivity implements DialogListener, ReminderListener, BatterySaverModeDialogClickCallback, View.OnClickListener, IntervalAdapterNew.IntervalSelectionListener {
    public int A;
    public int B;
    public int C;
    public int D;
    public boolean E;
    public boolean G;
    @Nullable
    public TimePickerDialog H;
    @Nullable
    public TimePickerDialog I;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage J;
    @Nullable
    public BottomSheetDialogTwoButtons K;
    public ActivitySedentaryReminderBinding binding;
    public boolean p;
    public TextView q;
    public SedentaryReminderData sedentaryReminderDataCommon;
    public SedentaryReminderViewModel sedentaryReminderViewModel;
    public int t;
    public int u;
    public int v;
    public int w;
    @Nullable
    public String x;
    public boolean y;
    public int z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int r = 60;
    public int s = 60;
    @NotNull
    public boolean[] F = {false, false, false, false, false, false, false};

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivitySedentaryReminder this$0, View view) {
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
            if (ActivitySedentaryReminder.this.getBoolSaveVisible()) {
                if (!AppUtils.isNetConnected(ActivitySedentaryReminder.this)) {
                    ActivitySedentaryReminder.this.showNoInternetMessage();
                } else if (BleApiManager.getInstance(ActivitySedentaryReminder.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    if (BleApiManager.getInstance(ActivitySedentaryReminder.this).getBleApi().getDeviceSupportedFeatures().isRepeatDaysSupportedInSedentary()) {
                        if (ArraysKt___ArraysKt.contains(ActivitySedentaryReminder.this.F, true)) {
                            ActivitySedentaryReminder.this.getSedentaryReminderViewModel().callSedentaryUserDeviceSettingsAndBleApi(ActivitySedentaryReminder.this.r, ActivitySedentaryReminder.this.y, ActivitySedentaryReminder.this.getBinding().reminderLayout.tvStartTime.getText().toString(), ActivitySedentaryReminder.this.getBinding().reminderLayout.tvEndTime.getText().toString(), ActivitySedentaryReminder.this.F);
                            return;
                        }
                        ActivitySedentaryReminder activitySedentaryReminder = ActivitySedentaryReminder.this;
                        Toast.makeText(activitySedentaryReminder, activitySedentaryReminder.getResources().getString(R.string.select_atleast_one_day), 0).show();
                        return;
                    }
                    ActivitySedentaryReminder.this.getSedentaryReminderViewModel().callSaveAndBleApi(ActivitySedentaryReminder.this.r, ActivitySedentaryReminder.this.y, ActivitySedentaryReminder.this.getBinding().reminderLayout.tvStartTime.getText().toString(), ActivitySedentaryReminder.this.getBinding().reminderLayout.tvEndTime.getText().toString());
                } else {
                    ActivitySedentaryReminder activitySedentaryReminder2 = ActivitySedentaryReminder.this;
                    String string = activitySedentaryReminder2.getString(R.string.band_not_connected);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                    String string2 = ActivitySedentaryReminder.this.getString(R.string.please_connect_the_device);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                    final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activitySedentaryReminder2, string, string2);
                    String string3 = ActivitySedentaryReminder.this.getString(R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                    final ActivitySedentaryReminder activitySedentaryReminder3 = ActivitySedentaryReminder.this;
                    bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ki
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivitySedentaryReminder.a.invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage.this, activitySedentaryReminder3, view);
                        }
                    });
                    bottomSheetDialogOneButtonTitleMessage.show();
                }
            }
        }
    }

    public static final void G(ActivitySedentaryReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.J;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }
        this$0.finish();
    }

    public static final void H(ActivitySedentaryReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.K;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.getBinding().btnSave.performClick();
    }

    public static final void I(ActivitySedentaryReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.K;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
    }

    public static final void J(ActivitySedentaryReminder this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.F = new boolean[]{z, z, z, z, z, z, z};
        this$0.updateRepeatLayout();
    }

    public static final void K(ActivitySedentaryReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getBinding().switchReminder.isChecked()) {
            this$0.E = this$0.getBinding().repeatLayout.switchRepeat.isChecked();
            return;
        }
        this$0.getBinding().repeatLayout.switchRepeat.setChecked(this$0.E);
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_sedentary_reminder), 0).show();
    }

    public static final void L(final ActivitySedentaryReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getBinding().switchReminder.isChecked()) {
            this$0.U();
            if (this$0.H == null) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(this$0, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.activities.vh
                    @Override // android.app.TimePickerDialog.OnTimeSetListener
                    public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                        ActivitySedentaryReminder.M(ActivitySedentaryReminder.this, timePicker, i, i2);
                    }
                }, this$0.z, this$0.A, DateFormat.is24HourFormat(this$0));
                this$0.H = timePickerDialog;
                timePickerDialog.show();
                TimePickerDialog timePickerDialog2 = this$0.H;
                if (timePickerDialog2 != null) {
                    timePickerDialog2.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.coveiot.android.leonardo.more.activities.di
                        @Override // android.content.DialogInterface.OnCancelListener
                        public final void onCancel(DialogInterface dialogInterface) {
                            ActivitySedentaryReminder.N(dialogInterface);
                        }
                    });
                    return;
                }
                return;
            }
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_sedentary_reminder), 0).show();
    }

    public static final void M(ActivitySedentaryReminder this$0, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onReminderTimeSet(i, i2, true);
        this$0.buttonVisible();
        this$0.z = i;
        this$0.A = i2;
    }

    public static final void N(DialogInterface dialogInterface) {
    }

    public static final void O(final ActivitySedentaryReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getBinding().switchReminder.isChecked()) {
            this$0.U();
            if (this$0.I == null) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(this$0, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.activities.bi
                    @Override // android.app.TimePickerDialog.OnTimeSetListener
                    public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                        ActivitySedentaryReminder.P(ActivitySedentaryReminder.this, timePicker, i, i2);
                    }
                }, this$0.B, this$0.C, DateFormat.is24HourFormat(this$0));
                this$0.I = timePickerDialog;
                timePickerDialog.show();
                TimePickerDialog timePickerDialog2 = this$0.I;
                if (timePickerDialog2 != null) {
                    timePickerDialog2.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.coveiot.android.leonardo.more.activities.ci
                        @Override // android.content.DialogInterface.OnCancelListener
                        public final void onCancel(DialogInterface dialogInterface) {
                            ActivitySedentaryReminder.Q(dialogInterface);
                        }
                    });
                    return;
                }
                return;
            }
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_sedentary_reminder), 0).show();
    }

    public static final void P(ActivitySedentaryReminder this_run, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this_run.onReminderTimeSet(i, i2, false);
        this_run.buttonVisible();
        this_run.B = i;
        this_run.C = i2;
    }

    public static final void Q(DialogInterface dialogInterface) {
    }

    public static final void R(ActivitySedentaryReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.F();
    }

    public static final void W(final ActivitySedentaryReminder this$0, CompoundButton compoundButton, final boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0) != null && BleApiManager.getInstance(this$0).getBleApi() != null && BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            BatterySaverModeHelper.Companion.getInstance(this$0).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivitySedentaryReminder$setOnCheckChangeListenerForSwitchReminder$1$1

                @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivitySedentaryReminder$setOnCheckChangeListenerForSwitchReminder$1$1$onBatterySavingSettingsReceived$1", f = "ActivitySedentaryReminder.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ boolean $b;
                    public final /* synthetic */ boolean $isEnabled;
                    public final /* synthetic */ int $mode;
                    public int label;
                    public final /* synthetic */ ActivitySedentaryReminder this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(boolean z, int i, ActivitySedentaryReminder activitySedentaryReminder, boolean z2, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$isEnabled = z;
                        this.$mode = i;
                        this.this$0 = activitySedentaryReminder;
                        this.$b = z2;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.$isEnabled, this.$mode, this.this$0, this.$b, continuation);
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
                                this.this$0.getBinding().switchReminder.setOnCheckedChangeListener(null);
                                this.this$0.getBinding().switchReminder.setChecked(false);
                                ActivitySedentaryReminder activitySedentaryReminder = this.this$0;
                                activitySedentaryReminder.showBatterySaverModeEnabledDialog(activitySedentaryReminder);
                            } else {
                                this.this$0.y = this.$b;
                                this.this$0.buttonVisible();
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySaverCMDFailed() {
                    Toast.makeText(ActivitySedentaryReminder.this, (int) R.string.somethings_went_wrong, 0).show();
                }

                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySavingSettingsReceived(boolean z2, int i) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivitySedentaryReminder.this), Dispatchers.getMain(), null, new a(z2, i, ActivitySedentaryReminder.this, z, null), 2, null);
                }
            });
            if (z) {
                ConstraintLayout constraintLayout = this$0.getBinding().clRepeatLayout;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clRepeatLayout");
                this$0.visible(constraintLayout);
                return;
            }
            ConstraintLayout constraintLayout2 = this$0.getBinding().clRepeatLayout;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clRepeatLayout");
            this$0.gone(constraintLayout2);
            return;
        }
        this$0.y = z;
        this$0.buttonVisible();
        if (z) {
            ConstraintLayout constraintLayout3 = this$0.getBinding().clRepeatLayout;
            Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clRepeatLayout");
            this$0.visible(constraintLayout3);
            return;
        }
        ConstraintLayout constraintLayout4 = this$0.getBinding().clRepeatLayout;
        Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.clRepeatLayout");
        this$0.gone(constraintLayout4);
    }

    public static final void X(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivitySedentaryReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void Y(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivitySedentaryReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public final void F() {
        int beggining_time_hour;
        String str;
        int end_time_hour;
        String str2;
        Boolean valueOf;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2;
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage;
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2;
        this.G = false;
        SedentaryReminderData sedentaryReminderDataFromPref = getSedentaryReminderViewModel().getSedentaryReminderDataFromPref();
        boolean[] zArr = {sedentaryReminderDataFromPref.isSunday(), sedentaryReminderDataFromPref.isMonday(), sedentaryReminderDataFromPref.isTuesday(), sedentaryReminderDataFromPref.isWednesday(), sedentaryReminderDataFromPref.isThrusday(), sedentaryReminderDataFromPref.isFriday(), sedentaryReminderDataFromPref.isSaturday()};
        if (sedentaryReminderDataFromPref.getBeggining_time_hour() > 12) {
            str = ' ' + getResources().getString(R.string.PM);
            beggining_time_hour = sedentaryReminderDataFromPref.getBeggining_time_hour() - 12;
        } else if (sedentaryReminderDataFromPref.getBeggining_time_hour() == 12) {
            str = ' ' + getResources().getString(R.string.PM);
            beggining_time_hour = sedentaryReminderDataFromPref.getBeggining_time_hour();
        } else {
            beggining_time_hour = sedentaryReminderDataFromPref.getBeggining_time_hour();
            str = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(beggining_time_hour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(sedentaryReminderDataFromPref.getBeggining_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        String sb2 = sb.toString();
        if (sedentaryReminderDataFromPref.getEnd_time_hour() > 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            end_time_hour = sedentaryReminderDataFromPref.getEnd_time_hour() - 12;
        } else if (sedentaryReminderDataFromPref.getEnd_time_hour() == 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            end_time_hour = sedentaryReminderDataFromPref.getEnd_time_hour();
        } else {
            end_time_hour = sedentaryReminderDataFromPref.getEnd_time_hour();
            str2 = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(end_time_hour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(sedentaryReminderDataFromPref.getEnd_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(str2);
        String sb4 = sb3.toString();
        if (sedentaryReminderDataFromPref.getAlarm_on_off() != this.y) {
            this.G = true;
        } else if (this.s != this.r) {
            this.G = true;
        } else if (!kotlin.text.m.equals(sb2, getBinding().reminderLayout.tvStartTime.getText().toString(), true)) {
            this.G = true;
        } else if (!kotlin.text.m.equals(sb4, getBinding().reminderLayout.tvEndTime.getText().toString(), true)) {
            this.G = true;
        } else {
            int length = this.F.length;
            for (int i = 0; i < length; i++) {
                if (this.F[i] != zArr[i]) {
                    this.G = true;
                }
            }
        }
        if (this.G) {
            if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.DISCONNECTED) {
                BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.J;
                if (bottomSheetDialogOneButtonTitleMessage3 != null) {
                    Boolean valueOf2 = bottomSheetDialogOneButtonTitleMessage3 != null ? Boolean.valueOf(bottomSheetDialogOneButtonTitleMessage3.isShowing()) : null;
                    Intrinsics.checkNotNull(valueOf2);
                    if (valueOf2.booleanValue() && (bottomSheetDialogOneButtonTitleMessage2 = this.J) != null) {
                        bottomSheetDialogOneButtonTitleMessage2.dismiss();
                    }
                    this.J = null;
                }
                String string = getString(R.string.band_not_connected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                String string2 = getString(R.string.please_connect_the_device);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage4 = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
                this.J = bottomSheetDialogOneButtonTitleMessage4;
                String string3 = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                bottomSheetDialogOneButtonTitleMessage4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.fi
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivitySedentaryReminder.G(ActivitySedentaryReminder.this, view);
                    }
                });
                BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage5 = this.J;
                valueOf = bottomSheetDialogOneButtonTitleMessage5 != null ? Boolean.valueOf(bottomSheetDialogOneButtonTitleMessage5.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue() || (bottomSheetDialogOneButtonTitleMessage = this.J) == null) {
                    return;
                }
                bottomSheetDialogOneButtonTitleMessage.show();
                return;
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.K;
            if (bottomSheetDialogTwoButtons3 != null) {
                Boolean valueOf3 = bottomSheetDialogTwoButtons3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons3.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf3);
                if (valueOf3.booleanValue() && (bottomSheetDialogTwoButtons2 = this.K) != null) {
                    bottomSheetDialogTwoButtons2.dismiss();
                }
                this.K = null;
            }
            String string4 = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.confirmation)");
            String string5 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.save_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = new BottomSheetDialogTwoButtons(this, string4, string5, false, 8, null);
            this.K = bottomSheetDialogTwoButtons4;
            String string6 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons4.setPositiveButton(string6, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.gi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySedentaryReminder.H(ActivitySedentaryReminder.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.K;
            if (bottomSheetDialogTwoButtons5 != null) {
                String string7 = getString(R.string.discard);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.discard)");
                bottomSheetDialogTwoButtons5.setNegativeButton(string7, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.wh
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivitySedentaryReminder.I(ActivitySedentaryReminder.this, view);
                    }
                });
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons6 = this.K;
            valueOf = bottomSheetDialogTwoButtons6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons6.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.K) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    public final void S() {
        initToolbar();
        initClickListeners();
        T();
    }

    public final void T() {
        int beggining_time_hour;
        String str;
        String str2;
        int end_time_hour;
        SedentaryReminderData sedentaryReminderDataFromPref = getSedentaryReminderViewModel().getSedentaryReminderDataFromPref();
        setSedentaryReminderDataCommon(getSedentaryReminderViewModel().getSedentaryReminderDataFromPref());
        this.z = sedentaryReminderDataFromPref.getBeggining_time_hour();
        this.A = sedentaryReminderDataFromPref.getBeggining_time_minutes();
        this.B = sedentaryReminderDataFromPref.getEnd_time_hour();
        this.C = sedentaryReminderDataFromPref.getEnd_time_minutes();
        this.F = (boolean[]) new boolean[]{sedentaryReminderDataFromPref.isSunday(), sedentaryReminderDataFromPref.isMonday(), sedentaryReminderDataFromPref.isTuesday(), sedentaryReminderDataFromPref.isWednesday(), sedentaryReminderDataFromPref.isThrusday(), sedentaryReminderDataFromPref.isFriday(), sedentaryReminderDataFromPref.isSaturday()}.clone();
        if (sedentaryReminderDataFromPref.getBeggining_time_hour() > 12) {
            str = ' ' + getResources().getString(R.string.PM);
            beggining_time_hour = sedentaryReminderDataFromPref.getBeggining_time_hour() - 12;
        } else if (sedentaryReminderDataFromPref.getBeggining_time_hour() == 12) {
            str = ' ' + getResources().getString(R.string.PM);
            beggining_time_hour = sedentaryReminderDataFromPref.getBeggining_time_hour();
        } else {
            beggining_time_hour = sedentaryReminderDataFromPref.getBeggining_time_hour() == 0 ? 12 : sedentaryReminderDataFromPref.getBeggining_time_hour();
            str = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(beggining_time_hour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(sedentaryReminderDataFromPref.getBeggining_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        String sb2 = sb.toString();
        if (sedentaryReminderDataFromPref.getEnd_time_hour() > 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            end_time_hour = sedentaryReminderDataFromPref.getEnd_time_hour() - 12;
        } else if (sedentaryReminderDataFromPref.getEnd_time_hour() == 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            end_time_hour = sedentaryReminderDataFromPref.getEnd_time_hour();
        } else {
            str2 = ' ' + getResources().getString(R.string.AM);
            end_time_hour = sedentaryReminderDataFromPref.getEnd_time_hour() != 0 ? sedentaryReminderDataFromPref.getEnd_time_hour() : 12;
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(end_time_hour)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(sedentaryReminderDataFromPref.getEnd_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(str2);
        String sb4 = sb3.toString();
        getBinding().reminderLayout.tvStartTime.setText(sb2);
        getBinding().reminderLayout.tvEndTime.setText(sb4);
        this.r = sedentaryReminderDataFromPref.getRemind_in();
        this.s = sedentaryReminderDataFromPref.getRemind_in();
        IntervalAdapterNew intervalAdapterNew = new IntervalAdapterNew(new Integer[]{60, 90, 120, 150}, this);
        intervalAdapterNew.setSelectedInterval(Integer.valueOf(this.r));
        intervalAdapterNew.setListner(this);
        getBinding().reminderLayout.rcvInterval.setAdapter(intervalAdapterNew);
        getBinding().switchReminder.setChecked(sedentaryReminderDataFromPref.getAlarm_on_off());
        this.y = sedentaryReminderDataFromPref.getAlarm_on_off();
        updateRepeatLayout();
    }

    public final void U() {
        TimePickerDialog timePickerDialog = this.H;
        if (timePickerDialog != null) {
            if (timePickerDialog != null) {
                timePickerDialog.dismiss();
            }
            this.H = null;
        }
        TimePickerDialog timePickerDialog2 = this.I;
        if (timePickerDialog2 != null) {
            if (timePickerDialog2 != null) {
                timePickerDialog2.dismiss();
            }
            this.I = null;
        }
    }

    public final void V() {
        getBinding().switchReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.zh
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivitySedentaryReminder.W(ActivitySedentaryReminder.this, compoundButton, z);
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
        String str;
        String str2;
        if (getSedentaryReminderDataCommon().getBeggining_time_hour() > 12) {
            str = ' ' + getResources().getString(R.string.PM);
            getSedentaryReminderDataCommon().setBeggining_time_hour(getSedentaryReminderDataCommon().getBeggining_time_hour() - 12);
        } else if (getSedentaryReminderDataCommon().getBeggining_time_hour() == 12) {
            str = ' ' + getResources().getString(R.string.PM);
            getSedentaryReminderDataCommon().setBeggining_time_hour(getSedentaryReminderDataCommon().getBeggining_time_hour());
        } else {
            getSedentaryReminderDataCommon().setBeggining_time_hour(getSedentaryReminderDataCommon().getBeggining_time_hour() == 0 ? 12 : getSedentaryReminderDataCommon().getBeggining_time_hour());
            str = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getSedentaryReminderDataCommon().getBeggining_time_hour())}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getSedentaryReminderDataCommon().getBeggining_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(str);
        String sb2 = sb.toString();
        if (getSedentaryReminderDataCommon().getEnd_time_hour() > 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            getSedentaryReminderDataCommon().setEnd_time_hour(getSedentaryReminderDataCommon().getEnd_time_hour() - 12);
        } else if (getSedentaryReminderDataCommon().getEnd_time_hour() == 12) {
            str2 = ' ' + getResources().getString(R.string.PM);
            getSedentaryReminderDataCommon().setEnd_time_hour(getSedentaryReminderDataCommon().getEnd_time_hour());
        } else {
            getSedentaryReminderDataCommon().setEnd_time_hour(getSedentaryReminderDataCommon().getEnd_time_hour() != 0 ? getSedentaryReminderDataCommon().getEnd_time_hour() : 12);
            str2 = ' ' + getResources().getString(R.string.AM);
        }
        StringBuilder sb3 = new StringBuilder();
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getSedentaryReminderDataCommon().getEnd_time_hour())}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb3.append(format3);
        sb3.append(':');
        String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(getSedentaryReminderDataCommon().getEnd_time_minutes())}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb3.append(format4);
        sb3.append(str2);
        String sb4 = sb3.toString();
        if (!Boolean.valueOf(getBinding().switchReminder.isChecked()).equals(Boolean.valueOf(getSedentaryReminderDataCommon().getAlarm_on_off()))) {
            getBinding().btnSave.setEnabled(true);
            this.p = true;
        } else if (getSedentaryReminderDataCommon().getRemind_in() != this.r) {
            getBinding().btnSave.setEnabled(true);
            this.p = true;
        } else if (!getBinding().reminderLayout.tvStartTime.getText().toString().equals(sb2)) {
            getBinding().btnSave.setEnabled(true);
            this.p = true;
        } else if (!getBinding().reminderLayout.tvEndTime.getText().toString().equals(sb4)) {
            getBinding().btnSave.setEnabled(true);
            this.p = true;
        } else {
            boolean[] zArr = {getSedentaryReminderDataCommon().isSunday(), getSedentaryReminderDataCommon().isMonday(), getSedentaryReminderDataCommon().isTuesday(), getSedentaryReminderDataCommon().isWednesday(), getSedentaryReminderDataCommon().isThrusday(), getSedentaryReminderDataCommon().isFriday(), getSedentaryReminderDataCommon().isSaturday()};
            int length = this.F.length;
            boolean z = false;
            for (int i = 0; i < length; i++) {
                if (this.F[i] != zArr[i]) {
                    z = true;
                }
            }
            if (z) {
                getBinding().btnSave.setEnabled(true);
                this.p = true;
                return;
            }
            getBinding().btnSave.setEnabled(false);
            this.p = false;
        }
    }

    @NotNull
    public final ActivitySedentaryReminderBinding getBinding() {
        ActivitySedentaryReminderBinding activitySedentaryReminderBinding = this.binding;
        if (activitySedentaryReminderBinding != null) {
            return activitySedentaryReminderBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final boolean getBoolSaveVisible() {
        return this.p;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetDialogOneButtonTitleMessageDisconnect() {
        return this.J;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationBottomSheetDialog() {
        return this.K;
    }

    public final int getDaySelectedCount() {
        return this.D;
    }

    public final boolean getHasInfoChanged() {
        return this.G;
    }

    @NotNull
    public final SedentaryReminderData getSedentaryReminderDataCommon() {
        SedentaryReminderData sedentaryReminderData = this.sedentaryReminderDataCommon;
        if (sedentaryReminderData != null) {
            return sedentaryReminderData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sedentaryReminderDataCommon");
        return null;
    }

    @NotNull
    public final SedentaryReminderViewModel getSedentaryReminderViewModel() {
        SedentaryReminderViewModel sedentaryReminderViewModel = this.sedentaryReminderViewModel;
        if (sedentaryReminderViewModel != null) {
            return sedentaryReminderViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sedentaryReminderViewModel");
        return null;
    }

    @Nullable
    public final TimePickerDialog getTimePickerDialogEndTime() {
        return this.I;
    }

    @Nullable
    public final TimePickerDialog getTimePickerDialogStartTime() {
        return this.H;
    }

    public final void initClickListeners() {
        getBinding().repeatLayout.sunday.setOnClickListener(this);
        getBinding().repeatLayout.clRepeat.setOnClickListener(this);
        getBinding().repeatLayout.monday.setOnClickListener(this);
        getBinding().repeatLayout.tuesday.setOnClickListener(this);
        getBinding().repeatLayout.wednesday.setOnClickListener(this);
        getBinding().repeatLayout.thursday.setOnClickListener(this);
        getBinding().repeatLayout.friday.setOnClickListener(this);
        getBinding().repeatLayout.saturday.setOnClickListener(this);
        getBinding().repeatLayout.cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.ai
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivitySedentaryReminder.J(ActivitySedentaryReminder.this, compoundButton, z);
            }
        });
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isRepeatDaysSupportedInSedentary()) {
            getBinding().repeatLayout.clRepeat.setVisibility(0);
            getBinding().setShowRepeatLayout(Boolean.TRUE);
        } else {
            getBinding().repeatLayout.clRepeat.setVisibility(8);
            getBinding().setShowRepeatLayout(Boolean.FALSE);
        }
        if (DeviceUtils.Companion.isIDODevice(this)) {
            getBinding().reminderLayout.clAlertIntervals.setVisibility(8);
        } else {
            getBinding().reminderLayout.clAlertIntervals.setVisibility(0);
        }
        V();
        getBinding().repeatLayout.switchRepeat.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ei
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySedentaryReminder.K(ActivitySedentaryReminder.this, view);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        getBinding().reminderLayout.rcvInterval.setLayoutManager(linearLayoutManager);
        IntervalAdapterNew intervalAdapterNew = new IntervalAdapterNew(new Integer[]{60, 90, 120, 150}, this);
        intervalAdapterNew.setSelectedInterval(Integer.valueOf(this.r));
        intervalAdapterNew.setListner(this);
        getBinding().reminderLayout.rcvInterval.setAdapter(intervalAdapterNew);
        getBinding().reminderLayout.tvStartTime.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.hi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySedentaryReminder.L(ActivitySedentaryReminder.this, view);
            }
        });
        getBinding().reminderLayout.tvEndTime.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ii
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySedentaryReminder.O(ActivitySedentaryReminder.this, view);
            }
        });
        Button button = getBinding().btnSave;
        Intrinsics.checkNotNullExpressionValue(button, "binding.btnSave");
        ViewUtilsKt.setSafeOnClickListener(button, new a());
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.sedentary_reminder));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ji
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySedentaryReminder.R(ActivitySedentaryReminder.this, view);
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
        } else {
            textView2 = textView3;
        }
        textView2.setAlpha(0.5f);
    }

    public final boolean isRepeat() {
        return this.E;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        F();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        switch (view.getId()) {
            case R.id.friday /* 2131363530 */:
                if (getBinding().switchReminder.isChecked()) {
                    boolean[] zArr = this.F;
                    zArr[5] = true ^ zArr[5];
                    updateRepeatLayout();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_sedentary_reminder), 0).show();
                }
                buttonVisible();
                return;
            case R.id.monday /* 2131364631 */:
                if (getBinding().switchReminder.isChecked()) {
                    boolean[] zArr2 = this.F;
                    zArr2[1] = !zArr2[1];
                    updateRepeatLayout();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_sedentary_reminder), 0).show();
                }
                buttonVisible();
                return;
            case R.id.saturday /* 2131365329 */:
                if (getBinding().switchReminder.isChecked()) {
                    boolean[] zArr3 = this.F;
                    zArr3[6] = true ^ zArr3[6];
                    updateRepeatLayout();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_sedentary_reminder), 0).show();
                }
                buttonVisible();
                return;
            case R.id.sunday /* 2131365692 */:
                if (getBinding().switchReminder.isChecked()) {
                    boolean[] zArr4 = this.F;
                    zArr4[0] = !zArr4[0];
                    updateRepeatLayout();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_sedentary_reminder), 0).show();
                }
                buttonVisible();
                return;
            case R.id.thursday /* 2131365917 */:
                if (getBinding().switchReminder.isChecked()) {
                    boolean[] zArr5 = this.F;
                    zArr5[4] = true ^ zArr5[4];
                    updateRepeatLayout();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_sedentary_reminder), 0).show();
                }
                buttonVisible();
                return;
            case R.id.tuesday /* 2131366038 */:
                if (getBinding().switchReminder.isChecked()) {
                    boolean[] zArr6 = this.F;
                    zArr6[2] = true ^ zArr6[2];
                    updateRepeatLayout();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_sedentary_reminder), 0).show();
                }
                buttonVisible();
                return;
            case R.id.wednesday /* 2131367457 */:
                if (getBinding().switchReminder.isChecked()) {
                    boolean[] zArr7 = this.F;
                    zArr7[3] = true ^ zArr7[3];
                    updateRepeatLayout();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.turn_on_sedentary_reminder), 0).show();
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
        ActivitySedentaryReminderBinding inflate = ActivitySedentaryReminderBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        setSedentaryReminderViewModel((SedentaryReminderViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(SedentaryReminderViewModel.class));
        getSedentaryReminderViewModel().setDialogListener(this);
        S();
        if (BleApiManager.getInstance(this) == null || BleApiManager.getInstance(this).getBleApi() == null || !BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            return;
        }
        showProgress();
        getBinding().switchReminder.setOnCheckedChangeListener(null);
        BatterySaverModeHelper.Companion.getInstance(this).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivitySedentaryReminder$onCreate$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivitySedentaryReminder$onCreate$1$onBatterySavingSettingsReceived$1", f = "ActivitySedentaryReminder.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ boolean $isEnabled;
                public final /* synthetic */ int $mode;
                public int label;
                public final /* synthetic */ ActivitySedentaryReminder this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(boolean z, int i, ActivitySedentaryReminder activitySedentaryReminder, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$isEnabled = z;
                    this.$mode = i;
                    this.this$0 = activitySedentaryReminder;
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
                            this.this$0.getBinding().switchReminder.setChecked(false);
                        }
                        this.this$0.V();
                        this.this$0.dismissProgress();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySaverCMDFailed() {
                ActivitySedentaryReminder.this.dismissProgress();
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySavingSettingsReceived(boolean z, int i) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivitySedentaryReminder.this), Dispatchers.getMain(), null, new a(z, i, ActivitySedentaryReminder.this, null), 2, null);
            }
        });
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

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onNegativeButtonClicked() {
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onPositiveButtonClicked() {
        if (isFinishing()) {
            return;
        }
        V();
    }

    @Override // com.coveiot.android.leonardo.more.listeners.ReminderListener
    public void onReminderTimeSet(int i, int i2, boolean z) {
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
            TextView textView = getBinding().reminderLayout.tvStartTime;
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
        TextView textView2 = getBinding().reminderLayout.tvEndTime;
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

    public final void setBinding(@NotNull ActivitySedentaryReminderBinding activitySedentaryReminderBinding) {
        Intrinsics.checkNotNullParameter(activitySedentaryReminderBinding, "<set-?>");
        this.binding = activitySedentaryReminderBinding;
    }

    public final void setBoolSaveVisible(boolean z) {
        this.p = z;
    }

    public final void setBottomSheetDialogOneButtonTitleMessageDisconnect(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.J = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setConfirmationBottomSheetDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.K = bottomSheetDialogTwoButtons;
    }

    public final void setDaySelectedCount(int i) {
        this.D = i;
    }

    public final void setHasInfoChanged(boolean z) {
        this.G = z;
    }

    public final void setRepeat(boolean z) {
        this.E = z;
    }

    public final void setSedentaryReminderDataCommon(@NotNull SedentaryReminderData sedentaryReminderData) {
        Intrinsics.checkNotNullParameter(sedentaryReminderData, "<set-?>");
        this.sedentaryReminderDataCommon = sedentaryReminderData;
    }

    public final void setSedentaryReminderViewModel(@NotNull SedentaryReminderViewModel sedentaryReminderViewModel) {
        Intrinsics.checkNotNullParameter(sedentaryReminderViewModel, "<set-?>");
        this.sedentaryReminderViewModel = sedentaryReminderViewModel;
    }

    public final void setTimePickerDialogEndTime(@Nullable TimePickerDialog timePickerDialog) {
        this.I = timePickerDialog;
    }

    public final void setTimePickerDialogStartTime(@Nullable TimePickerDialog timePickerDialog) {
        this.H = timePickerDialog;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.xh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySedentaryReminder.X(BottomSheetDialogOneButtonOneTitle.this, this, view);
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
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.yh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySedentaryReminder.Y(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void updateRepeatLayout() {
        if (this.F[0]) {
            getBinding().repeatLayout.sunday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            getBinding().repeatLayout.sunday.setTextColor(getResources().getColor(R.color.main_text_color));
            getBinding().repeatLayout.sunday.setTextAppearance(R.style.semi_bold);
        } else {
            getBinding().repeatLayout.sunday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            getBinding().repeatLayout.sunday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            getBinding().repeatLayout.sunday.setTextAppearance(R.style.regular);
        }
        if (this.F[1]) {
            getBinding().repeatLayout.monday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            getBinding().repeatLayout.monday.setTextColor(getResources().getColor(R.color.main_text_color));
            getBinding().repeatLayout.monday.setTextAppearance(R.style.semi_bold);
        } else {
            getBinding().repeatLayout.monday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            getBinding().repeatLayout.monday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            getBinding().repeatLayout.monday.setTextAppearance(R.style.regular);
        }
        if (this.F[2]) {
            getBinding().repeatLayout.tuesday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            getBinding().repeatLayout.tuesday.setTextColor(getResources().getColor(R.color.main_text_color));
            getBinding().repeatLayout.tuesday.setTextAppearance(R.style.semi_bold);
        } else {
            getBinding().repeatLayout.tuesday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            getBinding().repeatLayout.tuesday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            getBinding().repeatLayout.tuesday.setTextAppearance(R.style.regular);
        }
        if (this.F[3]) {
            getBinding().repeatLayout.wednesday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            getBinding().repeatLayout.wednesday.setTextColor(getResources().getColor(R.color.main_text_color));
            getBinding().repeatLayout.wednesday.setTextAppearance(R.style.semi_bold);
        } else {
            getBinding().repeatLayout.wednesday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            getBinding().repeatLayout.wednesday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            getBinding().repeatLayout.wednesday.setTextAppearance(R.style.regular);
        }
        if (this.F[4]) {
            getBinding().repeatLayout.thursday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            getBinding().repeatLayout.thursday.setTextColor(getResources().getColor(R.color.main_text_color));
            getBinding().repeatLayout.thursday.setTextAppearance(R.style.semi_bold);
        } else {
            getBinding().repeatLayout.thursday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            getBinding().repeatLayout.thursday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            getBinding().repeatLayout.thursday.setTextAppearance(R.style.regular);
        }
        if (this.F[5]) {
            getBinding().repeatLayout.friday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            getBinding().repeatLayout.friday.setTextColor(getResources().getColor(R.color.main_text_color));
            getBinding().repeatLayout.friday.setTextAppearance(R.style.semi_bold);
        } else {
            getBinding().repeatLayout.friday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            getBinding().repeatLayout.friday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            getBinding().repeatLayout.friday.setTextAppearance(R.style.regular);
        }
        if (this.F[6]) {
            getBinding().repeatLayout.saturday.setBackground(getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            getBinding().repeatLayout.saturday.setTextColor(getResources().getColor(R.color.main_text_color));
            getBinding().repeatLayout.saturday.setTextAppearance(R.style.semi_bold);
        } else {
            getBinding().repeatLayout.saturday.setBackground(getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            getBinding().repeatLayout.saturday.setTextColor(getResources().getColor(R.color.secondary_text_color));
            getBinding().repeatLayout.saturday.setTextAppearance(R.style.regular);
        }
        this.D = 0;
        for (boolean z : this.F) {
            if (z) {
                getBinding().repeatLayout.switchRepeat.setChecked(true);
                this.E = true;
                this.D++;
                return;
            }
        }
    }
}
