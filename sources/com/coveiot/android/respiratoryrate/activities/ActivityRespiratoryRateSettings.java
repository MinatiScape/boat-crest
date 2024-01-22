package com.coveiot.android.respiratoryrate.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.respiratoryrate.BatterySaverModeRespiratoryRateHelper;
import com.coveiot.android.respiratoryrate.R;
import com.coveiot.android.respiratoryrate.databinding.ActivityRespiratoryRateSettingsBinding;
import com.coveiot.android.respiratoryrate.listener.DialogListener;
import com.coveiot.android.respiratoryrate.utils.ViewModelFactory;
import com.coveiot.android.respiratoryrate.utils.ViewUtilsKt;
import com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateSettingsViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
import com.coveiot.covepreferences.UserDataManager;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class ActivityRespiratoryRateSettings extends BaseActivity implements DialogListener, BatterySaverModeDialogClickCallback {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public TextView backArrow;
    public ActivityRespiratoryRateSettingsBinding p;
    public boolean q;
    public boolean r;
    @Nullable
    public BottomSheetDialogTwoButtons s;
    public TextView saveText;
    public TextView titleText;
    public RespiratoryRateSettingsViewModel viewModel;

    public static final void D(final ActivityRespiratoryRateSettings this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0) != null && BleApiManager.getInstance(this$0).getBleApi() != null && BleApiManager.getInstance(this$0).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
            BatterySaverModeRespiratoryRateHelper.Companion.getInstance(this$0).getBatterySaverMode(new BatterySaverModeRespiratoryRateHelper.BatterySaverModeListener() { // from class: com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateSettings$setOnCheckChangeListenerForRespiratoryRate$1$1

                @DebugMetadata(c = "com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateSettings$setOnCheckChangeListenerForRespiratoryRate$1$1$onBatterySavingSettingsReceived$1", f = "ActivityRespiratoryRateSettings.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes6.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ boolean $isEnabled;
                    public int label;
                    public final /* synthetic */ ActivityRespiratoryRateSettings this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(boolean z, ActivityRespiratoryRateSettings activityRespiratoryRateSettings, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$isEnabled = z;
                        this.this$0 = activityRespiratoryRateSettings;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.$isEnabled, this.this$0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        ActivityRespiratoryRateSettingsBinding activityRespiratoryRateSettingsBinding;
                        ActivityRespiratoryRateSettingsBinding activityRespiratoryRateSettingsBinding2;
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            if (this.$isEnabled) {
                                activityRespiratoryRateSettingsBinding = this.this$0.p;
                                ActivityRespiratoryRateSettingsBinding activityRespiratoryRateSettingsBinding3 = null;
                                if (activityRespiratoryRateSettingsBinding == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityRespiratoryRateSettingsBinding = null;
                                }
                                activityRespiratoryRateSettingsBinding.switchRespiratoryRate.setOnCheckedChangeListener(null);
                                activityRespiratoryRateSettingsBinding2 = this.this$0.p;
                                if (activityRespiratoryRateSettingsBinding2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityRespiratoryRateSettingsBinding3 = activityRespiratoryRateSettingsBinding2;
                                }
                                activityRespiratoryRateSettingsBinding3.switchRespiratoryRate.setChecked(false);
                                ActivityRespiratoryRateSettings activityRespiratoryRateSettings = this.this$0;
                                activityRespiratoryRateSettings.showBatterySaverModeEnabledDialog(activityRespiratoryRateSettings);
                            } else {
                                this.this$0.B();
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.respiratoryrate.BatterySaverModeRespiratoryRateHelper.BatterySaverModeListener
                public void onBatterySavingSettingsReceived(boolean z2, int i) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityRespiratoryRateSettings.this), Dispatchers.getMain(), null, new a(z2, ActivityRespiratoryRateSettings.this, null), 2, null);
                }
            });
        } else {
            this$0.B();
        }
    }

    public static final void F(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityRespiratoryRateSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void G(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityRespiratoryRateSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void w(ActivityRespiratoryRateSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void x(ActivityRespiratoryRateSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getSaveText().performClick();
    }

    public static final void z(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityRespiratoryRateSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.finish();
    }

    public final void A() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        if (this.s == null) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.s = bottomSheetDialogTwoButtons2;
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateSettings$saveChangesDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    ActivityRespiratoryRateSettings.this.setBoolSaveVisible(false);
                    ActivityRespiratoryRateSettings.this.y();
                    BottomSheetDialogTwoButtons confirmationDialog = ActivityRespiratoryRateSettings.this.getConfirmationDialog();
                    if (confirmationDialog != null) {
                        confirmationDialog.dismiss();
                    }
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.s;
            if (bottomSheetDialogTwoButtons3 != null) {
                String string4 = getString(R.string.discard);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
                bottomSheetDialogTwoButtons3.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateSettings$saveChangesDialog$2
                    @Override // android.view.View.OnClickListener
                    public void onClick(@Nullable View view) {
                        BottomSheetDialogTwoButtons confirmationDialog = ActivityRespiratoryRateSettings.this.getConfirmationDialog();
                        if (confirmationDialog != null) {
                            confirmationDialog.dismiss();
                        }
                        ActivityRespiratoryRateSettings.this.finish();
                    }
                });
            }
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.s;
        Boolean valueOf = bottomSheetDialogTwoButtons4 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.s) == null) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    public final void B() {
        boolean z = this.r;
        ActivityRespiratoryRateSettingsBinding activityRespiratoryRateSettingsBinding = this.p;
        if (activityRespiratoryRateSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRespiratoryRateSettingsBinding = null;
        }
        if (z != activityRespiratoryRateSettingsBinding.switchRespiratoryRate.isChecked()) {
            getSaveText().setEnabled(true);
            getSaveText().setAlpha(1.0f);
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
            return;
        }
        getSaveText().setEnabled(false);
        getSaveText().setAlpha(0.5f);
        ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(false);
    }

    public final void C() {
        ActivityRespiratoryRateSettingsBinding activityRespiratoryRateSettingsBinding = this.p;
        if (activityRespiratoryRateSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRespiratoryRateSettingsBinding = null;
        }
        activityRespiratoryRateSettingsBinding.switchRespiratoryRate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.respiratoryrate.activities.g
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityRespiratoryRateSettings.D(ActivityRespiratoryRateSettings.this, compoundButton, z);
            }
        });
    }

    public final void E() {
        this.r = UserDataManager.getInstance(this).isRespiratoryRateFeatureEnabled(this);
        ActivityRespiratoryRateSettingsBinding activityRespiratoryRateSettingsBinding = this.p;
        if (activityRespiratoryRateSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRespiratoryRateSettingsBinding = null;
        }
        activityRespiratoryRateSettingsBinding.switchRespiratoryRate.setChecked(this.r);
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
        boolean z = this.r;
        ActivityRespiratoryRateSettingsBinding activityRespiratoryRateSettingsBinding = this.p;
        if (activityRespiratoryRateSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRespiratoryRateSettingsBinding = null;
        }
        if (z != activityRespiratoryRateSettingsBinding.switchRespiratoryRate.isChecked()) {
            A();
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

    public final boolean getBoolSaveVisible() {
        return this.q;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationDialog() {
        return this.s;
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

    @NotNull
    public final RespiratoryRateSettingsViewModel getViewModel() {
        RespiratoryRateSettingsViewModel respiratoryRateSettingsViewModel = this.viewModel;
        if (respiratoryRateSettingsViewModel != null) {
            return respiratoryRateSettingsViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
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
        getSaveText().setTextColor(getResources().getColor(R.color.colorPrimary));
        getSaveText().setVisibility(8);
        View findViewById3 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById<TextView>(R.id.toolbar_back_arrow)");
        setBackArrow((TextView) findViewById3);
        getBackArrow().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRespiratoryRateSettings.w(ActivityRespiratoryRateSettings.this, view);
            }
        });
        getTitleText().setText(getString(R.string.nightly_breathing_rate));
        ((Button) _$_findCachedViewById(R.id.btnSave)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRespiratoryRateSettings.x(ActivityRespiratoryRateSettings.this, view);
            }
        });
        E();
        B();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        backPress();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityRespiratoryRateSettingsBinding inflate = ActivityRespiratoryRateSettingsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(RespiratoryRateSettingsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this@ActivityRespirat…ngsViewModel::class.java)");
        setViewModel((RespiratoryRateSettingsViewModel) viewModel);
        getViewModel().setDialogListener(this);
        init();
        C();
        ViewUtilsKt.setSafeOnClickListener(getSaveText(), new Function1<View, Unit>() { // from class: com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateSettings$onCreate$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull View it) {
                ActivityRespiratoryRateSettingsBinding activityRespiratoryRateSettingsBinding;
                Intrinsics.checkNotNullParameter(it, "it");
                ActivityRespiratoryRateSettings.this.getSaveText().setEnabled(false);
                ((Button) ActivityRespiratoryRateSettings.this._$_findCachedViewById(R.id.btnSave)).setEnabled(false);
                ActivityRespiratoryRateSettings.this.showProgress();
                ActivityRespiratoryRateSettings.this.y();
                if (BleApiManager.getInstance(ActivityRespiratoryRateSettings.this) == null || BleApiManager.getInstance(ActivityRespiratoryRateSettings.this).getBleApi() == null || !BleApiManager.getInstance(ActivityRespiratoryRateSettings.this).getBleApi().getDeviceSupportedFeatures().isBatterySaverConfigSupported()) {
                    return;
                }
                ActivityRespiratoryRateSettings.this.showProgress();
                activityRespiratoryRateSettingsBinding = ActivityRespiratoryRateSettings.this.p;
                if (activityRespiratoryRateSettingsBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityRespiratoryRateSettingsBinding = null;
                }
                activityRespiratoryRateSettingsBinding.switchRespiratoryRate.setOnCheckedChangeListener(null);
                final ActivityRespiratoryRateSettings activityRespiratoryRateSettings = ActivityRespiratoryRateSettings.this;
                BatterySaverModeRespiratoryRateHelper.Companion.getInstance(ActivityRespiratoryRateSettings.this).getBatterySaverMode(new BatterySaverModeRespiratoryRateHelper.BatterySaverModeListener() { // from class: com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateSettings$onCreate$1.1

                    @DebugMetadata(c = "com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateSettings$onCreate$1$1$onBatterySavingSettingsReceived$1", f = "ActivityRespiratoryRateSettings.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateSettings$onCreate$1$1$a */
                    /* loaded from: classes6.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public final /* synthetic */ boolean $isEnabled;
                        public int label;
                        public final /* synthetic */ ActivityRespiratoryRateSettings this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(boolean z, ActivityRespiratoryRateSettings activityRespiratoryRateSettings, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.$isEnabled = z;
                            this.this$0 = activityRespiratoryRateSettings;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new a(this.$isEnabled, this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        @Nullable
                        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            ActivityRespiratoryRateSettingsBinding activityRespiratoryRateSettingsBinding;
                            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                            if (this.label == 0) {
                                ResultKt.throwOnFailure(obj);
                                if (this.$isEnabled) {
                                    activityRespiratoryRateSettingsBinding = this.this$0.p;
                                    if (activityRespiratoryRateSettingsBinding == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        activityRespiratoryRateSettingsBinding = null;
                                    }
                                    activityRespiratoryRateSettingsBinding.switchRespiratoryRate.setChecked(false);
                                }
                                this.this$0.C();
                                this.this$0.dismissProgress();
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @Override // com.coveiot.android.respiratoryrate.BatterySaverModeRespiratoryRateHelper.BatterySaverModeListener
                    public void onBatterySavingSettingsReceived(boolean z, int i) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityRespiratoryRateSettings.this), Dispatchers.getMain(), null, new a(z, ActivityRespiratoryRateSettings.this, null), 2, null);
                    }
                });
            }
        });
    }

    @Override // com.coveiot.android.respiratoryrate.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onNegativeButtonClicked() {
    }

    @Override // com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback
    public void onPositiveButtonClicked() {
    }

    @Override // com.coveiot.android.respiratoryrate.listener.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setBackArrow(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.backArrow = textView;
    }

    public final void setBoolSaveVisible(boolean z) {
        this.q = z;
    }

    public final void setConfirmationDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.s = bottomSheetDialogTwoButtons;
    }

    public final void setSaveText(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.saveText = textView;
    }

    public final void setTitleText(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.titleText = textView;
    }

    public final void setViewModel(@NotNull RespiratoryRateSettingsViewModel respiratoryRateSettingsViewModel) {
        Intrinsics.checkNotNullParameter(respiratoryRateSettingsViewModel, "<set-?>");
        this.viewModel = respiratoryRateSettingsViewModel;
    }

    @Override // com.coveiot.android.respiratoryrate.listener.DialogListener
    public void showErrorDialog(@Nullable String str) {
        onDismiss();
        if (str == null || str.length() == 0) {
            str = getString(R.string.setting_couldnot_save);
        }
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, str);
        String string = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.activities.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRespiratoryRateSettings.F(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.respiratoryrate.listener.DialogListener
    public void showSuccessDialog() {
        onDismiss();
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRespiratoryRateSettings.G(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void y() {
        if (!AppUtils.isNetConnected(this)) {
            getSaveText().setEnabled(true);
            ((Button) _$_findCachedViewById(R.id.btnSave)).setEnabled(true);
            dismissProgress();
            showNoInternetMessage();
        } else if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            RespiratoryRateSettingsViewModel viewModel = getViewModel();
            ActivityRespiratoryRateSettingsBinding activityRespiratoryRateSettingsBinding = this.p;
            if (activityRespiratoryRateSettingsBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRespiratoryRateSettingsBinding = null;
            }
            viewModel.sendSettingsToWatch(activityRespiratoryRateSettingsBinding.switchRespiratoryRate.isChecked());
        } else {
            dismissProgress();
            String string = getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
            String string2 = getString(R.string.please_connect_the_device);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
            final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.activities.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityRespiratoryRateSettings.z(BottomSheetDialogOneButtonTitleMessage.this, this, view);
                }
            });
            bottomSheetDialogOneButtonTitleMessage.show();
        }
    }
}
