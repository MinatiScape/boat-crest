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
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.activities.ActivityLiftWristToView;
import com.coveiot.android.leonardo.more.listeners.ReminderListener;
import com.coveiot.android.leonardo.more.viewmodel.LiftWristToViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.covepreferences.UserDataManager;
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
public final class ActivityLiftWristToView extends BaseActivity implements DialogListener, ReminderListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public LiftWristToViewModel liftWristToViewModel;
    public int p;
    public int q;
    public int r;
    public int s;
    @Nullable
    public String t;
    public boolean u;
    public int v;
    public int w;
    public int x;
    public int y;
    public TextView z;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityLiftWristToView this$0, View view) {
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
            if (!AppUtils.isNetConnected(ActivityLiftWristToView.this)) {
                ActivityLiftWristToView.this.showNoInternetMessage();
            } else if (BleApiManager.getInstance(ActivityLiftWristToView.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                ActivityLiftWristToView.this.getLiftWristToViewModel().callSaveAndBleApi(ActivityLiftWristToView.this.u, ((SwitchCompat) ActivityLiftWristToView.this._$_findCachedViewById(R.id.switch_schedule_reminder)).isChecked(), ((TextView) ActivityLiftWristToView.this._$_findCachedViewById(R.id.tv_start_time)).getText().toString(), ((TextView) ActivityLiftWristToView.this._$_findCachedViewById(R.id.tv_end_time)).getText().toString());
            } else {
                ActivityLiftWristToView activityLiftWristToView = ActivityLiftWristToView.this;
                String string = activityLiftWristToView.getString(R.string.band_not_connected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                String string2 = ActivityLiftWristToView.this.getString(R.string.please_connect_the_device);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activityLiftWristToView, string, string2);
                String string3 = ActivityLiftWristToView.this.getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                final ActivityLiftWristToView activityLiftWristToView2 = ActivityLiftWristToView.this;
                bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ye
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityLiftWristToView.a.invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage.this, activityLiftWristToView2, view);
                    }
                });
                bottomSheetDialogOneButtonTitleMessage.show();
            }
        }
    }

    public static final void E(ActivityLiftWristToView this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.u = z;
        ((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).setChecked(z);
        if (z) {
            ((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_schedule_reminder)).setChecked(false);
        }
    }

    public static final void F(ActivityLiftWristToView this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            ((LinearLayout) this$0._$_findCachedViewById(R.id.reminderLayout)).setVisibility(0);
            if (this$0.v == 0 && this$0.w == 0 && this$0.x == 0 && this$0.y == 0) {
                ((TextView) this$0._$_findCachedViewById(R.id.tv_start_time)).setText("8:00 AM");
                ((TextView) this$0._$_findCachedViewById(R.id.tv_end_time)).setText("10:00 PM");
            }
            ((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_reminder)).setChecked(false);
            return;
        }
        ((LinearLayout) this$0._$_findCachedViewById(R.id.reminderLayout)).setVisibility(8);
    }

    public static final void G(final ActivityLiftWristToView this$0, View view) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_schedule_reminder)).isChecked()) {
            int i3 = this$0.v;
            int i4 = this$0.w;
            if (i3 == 0 && i4 == 0 && this$0.x == 0 && this$0.y == 0) {
                i = 8;
                i2 = 0;
            } else {
                i = i3;
                i2 = i4;
            }
            TimePickerDialog timePickerDialog = new TimePickerDialog(this$0, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.activities.le
                @Override // android.app.TimePickerDialog.OnTimeSetListener
                public final void onTimeSet(TimePicker timePicker, int i5, int i6) {
                    ActivityLiftWristToView.H(ActivityLiftWristToView.this, timePicker, i5, i6);
                }
            }, i, i2, DateFormat.is24HourFormat(this$0));
            timePickerDialog.show();
            timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.coveiot.android.leonardo.more.activities.re
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    ActivityLiftWristToView.I(dialogInterface);
                }
            });
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_lift_wrist_to_view), 0).show();
    }

    public static final void H(ActivityLiftWristToView this$0, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onReminderTimeSet(i, i2, true);
        this$0.v = i;
        this$0.w = i2;
    }

    public static final void I(DialogInterface dialogInterface) {
    }

    public static final void J(final ActivityLiftWristToView this$0, View view) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.switch_schedule_reminder)).isChecked()) {
            int i3 = this$0.x;
            int i4 = this$0.y;
            if (this$0.v == 0 && this$0.w == 0 && i3 == 0 && i4 == 0) {
                i = 22;
                i2 = 0;
            } else {
                i = i3;
                i2 = i4;
            }
            TimePickerDialog timePickerDialog = new TimePickerDialog(this$0, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.activities.pe
                @Override // android.app.TimePickerDialog.OnTimeSetListener
                public final void onTimeSet(TimePicker timePicker, int i5, int i6) {
                    ActivityLiftWristToView.K(ActivityLiftWristToView.this, timePicker, i5, i6);
                }
            }, i, i2, DateFormat.is24HourFormat(this$0));
            timePickerDialog.show();
            timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.coveiot.android.leonardo.more.activities.qe
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    ActivityLiftWristToView.L(dialogInterface);
                }
            });
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.turn_on_lift_wrist_to_view), 0).show();
    }

    public static final void K(ActivityLiftWristToView this_run, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this_run.onReminderTimeSet(i, i2, false);
        this_run.x = i;
        this_run.y = i2;
    }

    public static final void L(DialogInterface dialogInterface) {
    }

    public static final void M(ActivityLiftWristToView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void O(ActivityLiftWristToView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Button button = (Button) this$0._$_findCachedViewById(R.id.btn_ok);
        Intrinsics.checkNotNull(button);
        button.performClick();
    }

    public static final void P(BottomSheetDialogTwoButtons dialog, ActivityLiftWristToView this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void R(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityLiftWristToView this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void S(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityLiftWristToView this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public final boolean D() {
        return !Intrinsics.areEqual(UserDataManager.getInstance(this).isLiftWristToViewEnable(), Boolean.valueOf(this.u));
    }

    public final void N() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        initToolbar();
        initClickListeners();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isCYDevice(this) && !companion.isCADevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
            BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
            boolean z = true;
            if (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isGetLiftWristToViewSettingsSupported()) {
                z = false;
            }
            if (!z) {
                Q();
                return;
            }
        }
        showProgress();
        getLiftWristToViewModel().getLiftWristViewSettingsFromWatch(new SuccessResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityLiftWristToView$initView$1
            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onError(@Nullable String str) {
                ActivityLiftWristToView.this.dismissProgress();
                ActivityLiftWristToView.this.Q();
            }

            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onSuccess() {
                ActivityLiftWristToView.this.dismissProgress();
                ActivityLiftWristToView.this.Q();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Q() {
        /*
            Method dump skipped, instructions count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.activities.ActivityLiftWristToView.Q():void");
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
    public final LiftWristToViewModel getLiftWristToViewModel() {
        LiftWristToViewModel liftWristToViewModel = this.liftWristToViewModel;
        if (liftWristToViewModel != null) {
            return liftWristToViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("liftWristToViewModel");
        return null;
    }

    public final void initClickListeners() {
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_reminder)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.ne
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityLiftWristToView.E(ActivityLiftWristToView.this, compoundButton, z);
            }
        });
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_schedule_reminder)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.oe
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityLiftWristToView.F(ActivityLiftWristToView.this, compoundButton, z);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_start_time)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ve
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityLiftWristToView.G(ActivityLiftWristToView.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_end_time)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.te
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityLiftWristToView.J(ActivityLiftWristToView.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.lift_wrist));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ue
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityLiftWristToView.M(ActivityLiftWristToView.this, view);
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.z = textView;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setText(getString(R.string.save_camel));
        TextView textView3 = this.z;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView3 = null;
        }
        textView3.setAlpha(1.0f);
        TextView textView4 = this.z;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
        } else {
            textView2 = textView4;
        }
        textView2.setVisibility(8);
        int i = R.id.btn_ok;
        ((Button) _$_findCachedViewById(i)).setEnabled(true);
        Button btn_ok = (Button) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(btn_ok, "btn_ok");
        ViewUtilsKt.setSafeOnClickListener(btn_ok, new a());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (D()) {
            String string = getString(R.string.lift_wrist);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.lift_wrist)");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string2 = getString(R.string.save_changes_hr);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes_hr)");
            String format = String.format(string2, Arrays.copyOf(new Object[]{getString(R.string.lift_wrist)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, format, false, 8, null);
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.se
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityLiftWristToView.O(ActivityLiftWristToView.this, view);
                }
            });
            String string4 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.me
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityLiftWristToView.P(BottomSheetDialogTwoButtons.this, this, view);
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
        setContentView(R.layout.activity_lift_wrist_to_view);
        setLiftWristToViewModel((LiftWristToViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(LiftWristToViewModel.class));
        getLiftWristToViewModel().setDialogListener(this);
        N();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.more.listeners.ReminderListener
    public void onReminderTimeSet(int i, int i2, boolean z) {
        if (z) {
            this.p = i;
            this.q = i2;
            if (i > 12) {
                this.t = ' ' + getResources().getString(R.string.PM);
                this.p = this.p - 12;
            } else if (i == 12) {
                this.t = ' ' + getResources().getString(R.string.PM);
                this.p = this.p;
            } else {
                this.t = ' ' + getResources().getString(R.string.AM);
            }
            if (this.p == 0) {
                this.p = 12;
            }
            StringBuilder sb = new StringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.p)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(':');
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.q)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb.append(format2);
            sb.append(this.t);
            ((TextView) _$_findCachedViewById(R.id.tv_start_time)).setText(sb.toString());
            return;
        }
        this.r = i;
        this.s = i2;
        if (i > 12) {
            this.t = ' ' + getResources().getString(R.string.PM);
            this.r = this.r - 12;
        } else if (i == 12) {
            this.t = ' ' + getResources().getString(R.string.PM);
            this.r = this.r;
        } else {
            this.t = ' ' + getResources().getString(R.string.AM);
        }
        if (this.r == 0) {
            this.r = 12;
        }
        StringBuilder sb2 = new StringBuilder();
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Locale locale2 = Locale.ENGLISH;
        String format3 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.r)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb2.append(format3);
        sb2.append(':');
        String format4 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.s)}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
        sb2.append(format4);
        sb2.append(this.t);
        ((TextView) _$_findCachedViewById(R.id.tv_end_time)).setText(sb2.toString());
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setLiftWristToViewModel(@NotNull LiftWristToViewModel liftWristToViewModel) {
        Intrinsics.checkNotNullParameter(liftWristToViewModel, "<set-?>");
        this.liftWristToViewModel = liftWristToViewModel;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        onDismiss();
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.xe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityLiftWristToView.R(BottomSheetDialogOneButtonOneTitle.this, this, view);
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
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.we
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityLiftWristToView.S(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }
}
