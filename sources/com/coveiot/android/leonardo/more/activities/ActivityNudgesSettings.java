package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.leonardo.BatterySaverModeHelper;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.viewmodel.NudgesSettingsViewModel;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNudgesSettings extends BaseActivity implements DialogListener, BatterySaverModeDialogClickCallback {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public TextView backArrow;
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
    public NudgesSettingsViewModel nudgesSettingsViewModel;
    public boolean p;
    public boolean q;
    public TextView r;
    public TextView saveText;
    public TextView titleText;

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
            if (((Button) ActivityNudgesSettings.this._$_findCachedViewById(R.id.btnSave)).isEnabled()) {
                if (AppUtils.isNetConnected(ActivityNudgesSettings.this)) {
                    ActivityNudgesSettings.this.showProgress();
                    ActivityNudgesSettings.this.getNudgesSettingsViewModel().callNudgeSettingsSaveAPI(((SwitchCompat) ActivityNudgesSettings.this._$_findCachedViewById(R.id.switch_nudges)).isChecked(), ((SwitchCompat) ActivityNudgesSettings.this._$_findCachedViewById(R.id.switch_vibrations)).isChecked());
                    return;
                }
                ActivityNudgesSettings.this.showNoInternetMessage();
            }
        }
    }

    public static final void A(final ActivityNudgesSettings this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.E(z);
        if (BleApiManager.getInstance(this$0) != null && BleApiManager.getInstance(this$0).getBleApi() != null && BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            BatterySaverModeHelper.Companion.getInstance(this$0).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNudgesSettings$setOnCheckChangeListenerForSwitchNudges$1$1

                @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityNudgesSettings$setOnCheckChangeListenerForSwitchNudges$1$1$onBatterySavingSettingsReceived$1", f = "ActivityNudgesSettings.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ boolean $isEnabled;
                    public final /* synthetic */ int $mode;
                    public int label;
                    public final /* synthetic */ ActivityNudgesSettings this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(boolean z, int i, ActivityNudgesSettings activityNudgesSettings, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$isEnabled = z;
                        this.$mode = i;
                        this.this$0 = activityNudgesSettings;
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
                            if (!this.$isEnabled || this.$mode != 1) {
                                this.this$0.y();
                            } else {
                                ActivityNudgesSettings activityNudgesSettings = this.this$0;
                                int i = R.id.switch_nudges;
                                ((SwitchCompat) activityNudgesSettings._$_findCachedViewById(i)).setOnCheckedChangeListener(null);
                                ((SwitchCompat) this.this$0._$_findCachedViewById(i)).setChecked(false);
                                ActivityNudgesSettings activityNudgesSettings2 = this.this$0;
                                activityNudgesSettings2.showBatterySaverModeEnabledDialog(activityNudgesSettings2);
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySaverCMDFailed() {
                    Toast.makeText(ActivityNudgesSettings.this, (int) R.string.somethings_went_wrong, 0).show();
                }

                @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
                public void onBatterySavingSettingsReceived(boolean z2, int i) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityNudgesSettings.this), Dispatchers.getMain(), null, new a(z2, i, ActivityNudgesSettings.this, null), 2, null);
                }
            });
        } else {
            this$0.y();
        }
    }

    public static final void C(ActivityNudgesSettings this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y();
    }

    public static final void D(ActivityNudgesSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void v(ActivityNudgesSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.r;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvSave");
            textView = null;
        }
        textView.performClick();
    }

    public static final void w(ActivityNudgesSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitle().dismiss();
        this$0.finish();
    }

    public final void B() {
        TextView textView = this.r;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvSave");
            textView = null;
        }
        textView.setEnabled(false);
        TextView textView3 = this.r;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvSave");
        } else {
            textView2 = textView3;
        }
        textView2.setAlpha(0.5f);
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isCustomMessageSupported()) {
            ((ConstraintLayout) _$_findCachedViewById(R.id.cl_vibration)).setVisibility(0);
        } else {
            ((ConstraintLayout) _$_findCachedViewById(R.id.cl_vibration)).setVisibility(8);
        }
        Boolean isPerformanceBasedNotificationEnabled = KHPerformancePreferenceManager.getInstance(this).isPerformanceBasedNotificationEnabled();
        Intrinsics.checkNotNullExpressionValue(isPerformanceBasedNotificationEnabled, "getInstance(this@Activit…eBasedNotificationEnabled");
        this.p = isPerformanceBasedNotificationEnabled.booleanValue();
        Boolean isPerformanceBasedNotificationVibrationEnabled = KHPerformancePreferenceManager.getInstance(this).isPerformanceBasedNotificationVibrationEnabled();
        Intrinsics.checkNotNullExpressionValue(isPerformanceBasedNotificationVibrationEnabled, "getInstance(this@Activit…ificationVibrationEnabled");
        this.q = isPerformanceBasedNotificationVibrationEnabled.booleanValue();
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_nudges)).setChecked(this.p);
        int i = R.id.switch_vibrations;
        ((SwitchCompat) _$_findCachedViewById(i)).setChecked(this.q);
        E(this.p);
        z();
        ((SwitchCompat) _$_findCachedViewById(i)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.eg
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityNudgesSettings.C(ActivityNudgesSettings.this, compoundButton, z);
            }
        });
        Button btnSave = (Button) _$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        ViewUtilsKt.setSafeOnClickListener(btnSave, new a());
        getBackArrow().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.bg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNudgesSettings.D(ActivityNudgesSettings.this, view);
            }
        });
    }

    public final void E(boolean z) {
        if (z) {
            ((TextView) _$_findCachedViewById(R.id.vibration_switch_title)).setAlpha(1.0f);
            int i = R.id.switch_vibrations;
            ((SwitchCompat) _$_findCachedViewById(i)).setEnabled(true);
            ((SwitchCompat) _$_findCachedViewById(i)).setFocusable(true);
            ((SwitchCompat) _$_findCachedViewById(i)).setClickable(true);
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.vibration_switch_title)).setAlpha(0.5f);
        int i2 = R.id.switch_vibrations;
        ((SwitchCompat) _$_findCachedViewById(i2)).setEnabled(false);
        ((SwitchCompat) _$_findCachedViewById(i2)).setFocusable(false);
        ((SwitchCompat) _$_findCachedViewById(i2)).setClickable(false);
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
        if (this.p != ((SwitchCompat) _$_findCachedViewById(R.id.switch_nudges)).isChecked()) {
            x();
        } else if (this.q != ((SwitchCompat) _$_findCachedViewById(R.id.switch_vibrations)).isChecked()) {
            x();
        } else {
            super.onBackPressed();
        }
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
    public final NudgesSettingsViewModel getNudgesSettingsViewModel() {
        NudgesSettingsViewModel nudgesSettingsViewModel = this.nudgesSettingsViewModel;
        if (nudgesSettingsViewModel != null) {
            return nudgesSettingsViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("nudgesSettingsViewModel");
        return null;
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
        View findViewById = findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.toolbar_title)");
        setTitleText((TextView) findViewById);
        getTitleText().setText(getResources().getString(R.string.nudges_settings));
        View findViewById2 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById<TextView>(R.id.toolbar_back_arrow)");
        setBackArrow((TextView) findViewById2);
        View findViewById3 = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById3;
        this.r = textView;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvSave");
            textView = null;
        }
        textView.setText(getString(R.string.save_uppercase));
        TextView textView3 = this.r;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvSave");
        } else {
            textView2 = textView3;
        }
        textView2.setTextColor(ContextCompat.getColor(this, R.color.color_E51C23));
        ((Button) _$_findCachedViewById(R.id.btnSave)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.cg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNudgesSettings.v(ActivityNudgesSettings.this, view);
            }
        });
        B();
    }

    public final void initDialog() {
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        setBottomSheetDialogOneButtonOneTitle(new BottomSheetDialogOneButtonOneTitle(this, string));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = getBottomSheetDialogOneButtonOneTitle();
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.dg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNudgesSettings.w(ActivityNudgesSettings.this, view);
            }
        });
    }

    public final boolean isNudgesSwitchEnabled() {
        return this.p;
    }

    public final boolean isVibrationSwitchEnabled() {
        return this.q;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        backPress();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_nudge_settings);
        setNudgesSettingsViewModel((NudgesSettingsViewModel) ViewModelProviders.of(this).get(NudgesSettingsViewModel.class));
        getNudgesSettingsViewModel().setDialogListener(this);
        init();
        initDialog();
        if (BleApiManager.getInstance(this) == null || BleApiManager.getInstance(this).getBleApi() == null || !BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            return;
        }
        showProgress();
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_nudges)).setOnCheckedChangeListener(null);
        BatterySaverModeHelper.Companion.getInstance(this).getBatterySaverMode(new BatterySaverModeHelper.BatterySaverModeListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNudgesSettings$onCreate$1
            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySaverCMDFailed() {
                ActivityNudgesSettings.this.dismissProgress();
            }

            @Override // com.coveiot.android.leonardo.BatterySaverModeHelper.BatterySaverModeListener
            public void onBatterySavingSettingsReceived(boolean z, int i) {
                if (z && i == 1) {
                    ((SwitchCompat) ActivityNudgesSettings.this._$_findCachedViewById(R.id.switch_nudges)).setChecked(false);
                }
                ActivityNudgesSettings.this.z();
                ActivityNudgesSettings.this.dismissProgress();
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
        z();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setBackArrow(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.backArrow = textView;
    }

    public final void setBottomSheetDialogOneButtonOneTitle(@NotNull BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "<set-?>");
        this.bottomSheetDialogOneButtonOneTitle = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setNudgesSettingsViewModel(@NotNull NudgesSettingsViewModel nudgesSettingsViewModel) {
        Intrinsics.checkNotNullParameter(nudgesSettingsViewModel, "<set-?>");
        this.nudgesSettingsViewModel = nudgesSettingsViewModel;
    }

    public final void setNudgesSwitchEnabled(boolean z) {
        this.p = z;
    }

    public final void setSaveText(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.saveText = textView;
    }

    public final void setTitleText(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.titleText = textView;
    }

    public final void setVibrationSwitchEnabled(boolean z) {
        this.q = z;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        onDismiss();
        TextView textView = this.r;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvSave");
            textView = null;
        }
        textView.setEnabled(true);
        TextView textView3 = this.r;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvSave");
        } else {
            textView2 = textView3;
        }
        textView2.setAlpha(1.0f);
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        onDismiss();
        TextView textView = this.r;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvSave");
            textView = null;
        }
        textView.setEnabled(true);
        TextView textView3 = this.r;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvSave");
        } else {
            textView2 = textView3;
        }
        textView2.setAlpha(1.0f);
        if (getBottomSheetDialogOneButtonOneTitle().isShowing()) {
            return;
        }
        getBottomSheetDialogOneButtonOneTitle().show();
    }

    public final void x() {
        dismissProgress();
        String string = getString(R.string.confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
        String string2 = getString(R.string.save_changes);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.save_changes_btn);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNudgesSettings$saveChangesDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                if (AppUtils.isNetConnected(ActivityNudgesSettings.this)) {
                    ActivityNudgesSettings.this.getNudgesSettingsViewModel().callNudgeSettingsSaveAPI(((SwitchCompat) ActivityNudgesSettings.this._$_findCachedViewById(R.id.switch_nudges)).isChecked(), ((SwitchCompat) ActivityNudgesSettings.this._$_findCachedViewById(R.id.switch_vibrations)).isChecked());
                } else {
                    ActivityNudgesSettings.this.showNoInternetMessage();
                }
                bottomSheetDialogTwoButtons.dismiss();
            }
        });
        String string4 = getString(R.string.discard);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNudgesSettings$saveChangesDialog$2
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogTwoButtons.this.dismiss();
                this.finish();
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    public final void y() {
        if (this.p != ((SwitchCompat) _$_findCachedViewById(R.id.switch_nudges)).isChecked()) {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
        } else if (this.q != ((SwitchCompat) _$_findCachedViewById(R.id.switch_vibrations)).isChecked()) {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
        } else {
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(false);
        }
    }

    public final void z() {
        ((SwitchCompat) _$_findCachedViewById(R.id.switch_nudges)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.fg
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityNudgesSettings.A(ActivityNudgesSettings.this, compoundButton, z);
            }
        });
    }
}
