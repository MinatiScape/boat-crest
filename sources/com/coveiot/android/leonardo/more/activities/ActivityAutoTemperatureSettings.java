package com.coveiot.android.leonardo.more.activities;

import android.graphics.drawable.Drawable;
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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.viewmodel.AutoTemperatureViewModel;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAutoTemperatureSettings extends BaseActivity implements DialogListener {
    public AutoTemperatureViewModel autoTemperatureViewModel;
    public TextView backArrow;
    public boolean q;
    public int s;
    public TextView saveText;
    public boolean t;
    public TextView titleText;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String p = ActivityAutoTemperatureSettings.class.getSimpleName();
    public int r = 60;

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
            if (ActivityAutoTemperatureSettings.this.isAutoTemperatureSwitchEnabled() != ((SwitchCompat) ActivityAutoTemperatureSettings.this._$_findCachedViewById(R.id.switch_auto_temperature)).isChecked()) {
                ActivityAutoTemperatureSettings.this.E();
            } else if (ActivityAutoTemperatureSettings.this.getAutoTemperatureIntervalValue() != ActivityAutoTemperatureSettings.this.getAutoTemperatureDefaultInterval()) {
                ActivityAutoTemperatureSettings.this.E();
            } else {
                ActivityAutoTemperatureSettings activityAutoTemperatureSettings = ActivityAutoTemperatureSettings.this;
                Toast.makeText(activityAutoTemperatureSettings, activityAutoTemperatureSettings.getResources().getString(R.string.no_changes), 0).show();
            }
        }
    }

    public static final void B(ActivityAutoTemperatureSettings this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0, str, 0).show();
    }

    public static final void C(ActivityAutoTemperatureSettings this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this$0, it);
        String string = this$0.getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.e2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoTemperatureSettings.D(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public static final void D(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    public static final void F(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityAutoTemperatureSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void J(ActivityAutoTemperatureSettings this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.P();
            ((LinearLayout) this$0._$_findCachedViewById(R.id.interval_layout)).setVisibility(0);
            int i = R.id.option_1_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i)).setEnabled(true);
            int i2 = R.id.option_2_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i2)).setEnabled(true);
            int i3 = R.id.option_3_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i3)).setEnabled(true);
            int i4 = R.id.option_4_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i4)).setEnabled(true);
            int i5 = R.id.option_5_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i5)).setEnabled(true);
            ((RadioButton) this$0._$_findCachedViewById(i)).setAlpha(1.0f);
            ((RadioButton) this$0._$_findCachedViewById(i2)).setAlpha(1.0f);
            ((RadioButton) this$0._$_findCachedViewById(i3)).setAlpha(1.0f);
            ((RadioButton) this$0._$_findCachedViewById(i4)).setAlpha(1.0f);
            ((RadioButton) this$0._$_findCachedViewById(i5)).setAlpha(1.0f);
        } else {
            ((LinearLayout) this$0._$_findCachedViewById(R.id.interval_layout)).setVisibility(8);
            int i6 = R.id.option_1_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i6)).setEnabled(false);
            int i7 = R.id.option_2_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i7)).setEnabled(false);
            int i8 = R.id.option_3_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i8)).setEnabled(false);
            int i9 = R.id.option_4_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i9)).setEnabled(false);
            int i10 = R.id.option_5_radiobutton;
            ((RadioButton) this$0._$_findCachedViewById(i10)).setEnabled(false);
            ((RadioButton) this$0._$_findCachedViewById(i6)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i7)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i8)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i9)).setAlpha(0.35f);
            ((RadioButton) this$0._$_findCachedViewById(i10)).setAlpha(0.35f);
            ((TextView) this$0._$_findCachedViewById(R.id.select_frequency)).setAlpha(0.35f);
        }
        this$0.r = this$0.s;
        this$0.H();
    }

    public static final void K(ActivityAutoTemperatureSettings this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        switch (i) {
            case R.id.option_1_radiobutton /* 2131364808 */:
                this$0.r = 60;
                break;
            case R.id.option_2_radiobutton /* 2131364809 */:
                this$0.r = 120;
                break;
            case R.id.option_3_radiobutton /* 2131364810 */:
                this$0.r = 180;
                break;
            case R.id.option_4_radiobutton /* 2131364811 */:
                this$0.r = 240;
                break;
            case R.id.option_5_radiobutton /* 2131364812 */:
                this$0.r = 300;
                break;
        }
        this$0.H();
    }

    public static final void L(ActivityAutoTemperatureSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getSaveText().performClick();
    }

    public static final void M(ActivityAutoTemperatureSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void N(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityAutoTemperatureSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void O(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityAutoTemperatureSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void Q(BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        bottomSheetDialogImageTitleMessage.dismiss();
    }

    public final void E() {
        LogHelper.d(this.p, String.valueOf(this.r));
        if (!AppUtils.isNetConnected(this)) {
            showNoInternetMessage();
        } else if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            getAutoTemperatureViewModel().callSaveAndBleApi(this.r, ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_temperature)).isChecked());
        } else {
            String string = getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
            String string2 = getString(R.string.please_connect_the_device);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
            final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.h2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAutoTemperatureSettings.F(BottomSheetDialogOneButtonTitleMessage.this, this, view);
                }
            });
            bottomSheetDialogOneButtonTitleMessage.show();
        }
    }

    public final void G() {
        String string = getString(R.string.auto_temperature);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.auto_temperature)");
        String string2 = getString(R.string.save_changes_hr, new Object[]{getString(R.string.auto_temperature)});
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_…string.auto_temperature))");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.save_changes_btn);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAutoTemperatureSettings$saveChangesDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                ActivityAutoTemperatureSettings.this.setBoolSaveVisible(false);
                ActivityAutoTemperatureSettings.this.E();
                bottomSheetDialogTwoButtons.dismiss();
            }
        });
        String string4 = getString(R.string.discard);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAutoTemperatureSettings$saveChangesDialog$2
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogTwoButtons.this.dismiss();
                this.finish();
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    public final void H() {
        if (this.t != ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_temperature)).isChecked()) {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
            getSaveText().setAlpha(1.0f);
        } else if (this.s != this.r) {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
            getSaveText().setAlpha(1.0f);
        } else {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(false);
            getSaveText().setAlpha(0.5f);
        }
    }

    public final void I() {
        getSaveText().setEnabled(false);
        getSaveText().setAlpha(0.5f);
        this.s = UserDataManager.getInstance(this).getAutoTemperatureInterval();
        boolean autoTemperatureEnabled = UserDataManager.getInstance(this).getAutoTemperatureEnabled();
        this.t = autoTemperatureEnabled;
        if (autoTemperatureEnabled) {
            ((LinearLayout) _$_findCachedViewById(R.id.interval_layout)).setVisibility(0);
            ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_temperature)).setChecked(true);
            int i = R.id.option_1_radiobutton;
            ((RadioButton) _$_findCachedViewById(i)).setEnabled(true);
            int i2 = R.id.option_2_radiobutton;
            ((RadioButton) _$_findCachedViewById(i2)).setEnabled(true);
            int i3 = R.id.option_3_radiobutton;
            ((RadioButton) _$_findCachedViewById(i3)).setEnabled(true);
            int i4 = R.id.option_4_radiobutton;
            ((RadioButton) _$_findCachedViewById(i4)).setEnabled(true);
            int i5 = R.id.option_5_radiobutton;
            ((RadioButton) _$_findCachedViewById(i5)).setEnabled(true);
            ((RadioButton) _$_findCachedViewById(i)).setAlpha(1.0f);
            ((RadioButton) _$_findCachedViewById(i2)).setAlpha(1.0f);
            ((RadioButton) _$_findCachedViewById(i3)).setAlpha(1.0f);
            ((RadioButton) _$_findCachedViewById(i4)).setAlpha(1.0f);
            ((RadioButton) _$_findCachedViewById(i5)).setAlpha(1.0f);
        } else {
            ((LinearLayout) _$_findCachedViewById(R.id.interval_layout)).setVisibility(8);
            ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_temperature)).setChecked(false);
            int i6 = R.id.option_1_radiobutton;
            ((RadioButton) _$_findCachedViewById(i6)).setEnabled(false);
            int i7 = R.id.option_2_radiobutton;
            ((RadioButton) _$_findCachedViewById(i7)).setEnabled(false);
            int i8 = R.id.option_3_radiobutton;
            ((RadioButton) _$_findCachedViewById(i8)).setEnabled(false);
            int i9 = R.id.option_4_radiobutton;
            ((RadioButton) _$_findCachedViewById(i9)).setEnabled(false);
            int i10 = R.id.option_5_radiobutton;
            ((RadioButton) _$_findCachedViewById(i10)).setEnabled(false);
            ((RadioButton) _$_findCachedViewById(i6)).setAlpha(0.35f);
            ((RadioButton) _$_findCachedViewById(i7)).setAlpha(0.35f);
            ((RadioButton) _$_findCachedViewById(i8)).setAlpha(0.35f);
            ((RadioButton) _$_findCachedViewById(i9)).setAlpha(0.35f);
            ((RadioButton) _$_findCachedViewById(i10)).setAlpha(0.35f);
        }
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_temperature)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.i2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoTemperatureSettings.J(ActivityAutoTemperatureSettings.this, compoundButton, z);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.radioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.j2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i11) {
                ActivityAutoTemperatureSettings.K(ActivityAutoTemperatureSettings.this, radioGroup, i11);
            }
        });
        int i11 = this.s;
        if (i11 == 60) {
            ((RadioButton) _$_findCachedViewById(R.id.option_1_radiobutton)).setChecked(true);
        } else if (i11 == 120) {
            ((RadioButton) _$_findCachedViewById(R.id.option_2_radiobutton)).setChecked(true);
        } else if (i11 == 180) {
            ((RadioButton) _$_findCachedViewById(R.id.option_3_radiobutton)).setChecked(true);
        } else if (i11 == 240) {
            ((RadioButton) _$_findCachedViewById(R.id.option_4_radiobutton)).setChecked(true);
        } else if (i11 != 300) {
            ((RadioButton) _$_findCachedViewById(R.id.option_4_radiobutton)).setChecked(true);
        } else {
            ((RadioButton) _$_findCachedViewById(R.id.option_5_radiobutton)).setChecked(true);
        }
        ViewUtilsKt.setSafeOnClickListener(getSaveText(), new a());
        ((Button) _$_findCachedViewById(R.id.btnSave)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.c2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoTemperatureSettings.L(ActivityAutoTemperatureSettings.this, view);
            }
        });
        getBackArrow().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.a2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoTemperatureSettings.M(ActivityAutoTemperatureSettings.this, view);
            }
        });
    }

    public final void P() {
        Drawable drawable = getResources().getDrawable(R.drawable.warning_image);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.warning_image)");
        String string = getString(R.string.warning_message_temperature);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.warning_message_temperature)");
        final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, string, "", true);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogImageTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.d2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoTemperatureSettings.Q(BottomSheetDialogImageTitleMessage.this, view);
            }
        });
        bottomSheetDialogImageTitleMessage.show();
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

    public final void backPress() {
        if (this.t == ((SwitchCompat) _$_findCachedViewById(R.id.switch_auto_temperature)).isChecked() && this.s == this.r) {
            super.onBackPressed();
        } else {
            G();
        }
    }

    public final int getAutoTemperatureDefaultInterval() {
        return this.r;
    }

    public final int getAutoTemperatureIntervalValue() {
        return this.s;
    }

    @NotNull
    public final AutoTemperatureViewModel getAutoTemperatureViewModel() {
        AutoTemperatureViewModel autoTemperatureViewModel = this.autoTemperatureViewModel;
        if (autoTemperatureViewModel != null) {
            return autoTemperatureViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("autoTemperatureViewModel");
        return null;
    }

    @NotNull
    public final TextView getBackArrow() {
        TextView textView = this.backArrow;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("backArrow");
        return null;
    }

    public final boolean getBoolSaveVisible() {
        return this.q;
    }

    @NotNull
    public final TextView getSaveText() {
        TextView textView = this.saveText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("saveText");
        return null;
    }

    @NotNull
    public final TextView getTitleText() {
        TextView textView = this.titleText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("titleText");
        return null;
    }

    public final void init() {
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        setSaveText((TextView) findViewById);
        View findViewById2 = findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById<TextView>(R.id.toolbar_title)");
        setTitleText((TextView) findViewById2);
        getSaveText().setText(getResources().getString(R.string.save_camel));
        getTitleText().setText(getResources().getString(R.string.auto_temperature_tracker));
        ((TextView) _$_findCachedViewById(R.id.autotemperature_switch_title)).setText(getString(R.string.auto_temperature));
        getSaveText().setTextColor(getResources().getColor(R.color.colorPrimary));
        getSaveText().setVisibility(0);
        View findViewById3 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById<TextView>(R.id.toolbar_back_arrow)");
        setBackArrow((TextView) findViewById3);
        I();
    }

    public final boolean isAutoTemperatureSwitchEnabled() {
        return this.t;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        backPress();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_temperature_tracker);
        setAutoTemperatureViewModel((AutoTemperatureViewModel) ViewModelProviders.of(this).get(AutoTemperatureViewModel.class));
        getAutoTemperatureViewModel().setDialogListener(this);
        init();
        LiveData<String> showToastMessage = getAutoTemperatureViewModel().showToastMessage();
        if (showToastMessage != null) {
            showToastMessage.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.b2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ActivityAutoTemperatureSettings.B(ActivityAutoTemperatureSettings.this, (String) obj);
                }
            });
        }
        LiveData<String> showDialogMessage = getAutoTemperatureViewModel().showDialogMessage();
        if (showDialogMessage != null) {
            showDialogMessage.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.k2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ActivityAutoTemperatureSettings.C(ActivityAutoTemperatureSettings.this, (String) obj);
                }
            });
        }
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setAutoTemperatureDefaultInterval(int i) {
        this.r = i;
    }

    public final void setAutoTemperatureIntervalValue(int i) {
        this.s = i;
    }

    public final void setAutoTemperatureSwitchEnabled(boolean z) {
        this.t = z;
    }

    public final void setAutoTemperatureViewModel(@NotNull AutoTemperatureViewModel autoTemperatureViewModel) {
        Intrinsics.checkNotNullParameter(autoTemperatureViewModel, "<set-?>");
        this.autoTemperatureViewModel = autoTemperatureViewModel;
    }

    public final void setBackArrow(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.backArrow = textView;
    }

    public final void setBoolSaveVisible(boolean z) {
        this.q = z;
    }

    public final void setSaveText(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.saveText = textView;
    }

    public final void setTitleText(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.titleText = textView;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        onDismiss();
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.g2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoTemperatureSettings.N(BottomSheetDialogOneButtonOneTitle.this, this, view);
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
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.f2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoTemperatureSettings.O(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }
}
