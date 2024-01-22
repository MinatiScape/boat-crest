package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityAutoSpo2SettingsBinding;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.activities.ActivityAutoSPO2Settings;
import com.coveiot.android.leonardo.more.viewmodel.AutoSPO2SettingsViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.covepreferences.data.AutoSPO2SettingsData;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAutoSPO2Settings extends BaseActivity implements DialogListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AutoSPO2SettingsData autoSPO2SettingsData;
    public AutoSPO2SettingsViewModel autoSpo2SettingsViewModel;
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitleError;
    public boolean p;
    public TextView q;
    public boolean r;
    public boolean s;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage t;
    @Nullable
    public BottomSheetDialogTwoButtons u;
    public ActivityAutoSpo2SettingsBinding v;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityAutoSPO2Settings this$0, View view) {
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
            if (ActivityAutoSPO2Settings.this.getBoolSaveVisible()) {
                if (!AppUtils.isNetConnected(ActivityAutoSPO2Settings.this)) {
                    ActivityAutoSPO2Settings.this.showNoInternetMessage();
                } else if (BleApiManager.getInstance(ActivityAutoSPO2Settings.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    ActivityAutoSPO2Settings.this.getAutoSpo2SettingsViewModel().callSaveSpo2SettingsAndBleApi(ActivityAutoSPO2Settings.this.r);
                } else {
                    ActivityAutoSPO2Settings activityAutoSPO2Settings = ActivityAutoSPO2Settings.this;
                    String string = activityAutoSPO2Settings.getString(R.string.band_not_connected);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                    String string2 = ActivityAutoSPO2Settings.this.getString(R.string.please_connect_the_device);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                    final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activityAutoSPO2Settings, string, string2);
                    String string3 = ActivityAutoSPO2Settings.this.getString(R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                    final ActivityAutoSPO2Settings activityAutoSPO2Settings2 = ActivityAutoSPO2Settings.this;
                    bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.z0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityAutoSPO2Settings.a.invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage.this, activityAutoSPO2Settings2, view);
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

    public static final void A(ActivityAutoSPO2Settings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.u;
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

    public static final void B(ActivityAutoSPO2Settings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.u;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
    }

    public static final void C(ActivityAutoSPO2Settings this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r = z;
        this$0.buttonVisible();
    }

    public static final void D(ActivityAutoSPO2Settings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitle().dismiss();
        this$0.finish();
    }

    public static final void E(ActivityAutoSPO2Settings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitleError().dismiss();
        this$0.finish();
    }

    public static final void F(ActivityAutoSPO2Settings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y();
    }

    public static final void G(ActivityAutoSPO2Settings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.q;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public static final void z(ActivityAutoSPO2Settings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.t;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }
        this$0.finish();
    }

    public final void H() {
        initToolbar();
        initDialogs();
        initClickListeners();
        I();
    }

    public final void I() {
        AutoSPO2SettingsData sPo2SettingsDataFromPref = getAutoSpo2SettingsViewModel().getSPo2SettingsDataFromPref();
        setAutoSPO2SettingsData(getAutoSpo2SettingsViewModel().getSPo2SettingsDataFromPref());
        ActivityAutoSpo2SettingsBinding activityAutoSpo2SettingsBinding = this.v;
        if (activityAutoSpo2SettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoSpo2SettingsBinding = null;
        }
        activityAutoSpo2SettingsBinding.switchReminder.setChecked(sPo2SettingsDataFromPref.isAutoSpO2());
        this.r = sPo2SettingsDataFromPref.isAutoSpO2();
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
        ActivityAutoSpo2SettingsBinding activityAutoSpo2SettingsBinding = this.v;
        ActivityAutoSpo2SettingsBinding activityAutoSpo2SettingsBinding2 = null;
        if (activityAutoSpo2SettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoSpo2SettingsBinding = null;
        }
        if (!Boolean.valueOf(activityAutoSpo2SettingsBinding.switchReminder.isChecked()).equals(Boolean.valueOf(getAutoSPO2SettingsData().isAutoSpO2()))) {
            TextView textView = this.q;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveText");
                textView = null;
            }
            textView.setAlpha(1.0f);
            this.p = true;
            ActivityAutoSpo2SettingsBinding activityAutoSpo2SettingsBinding3 = this.v;
            if (activityAutoSpo2SettingsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAutoSpo2SettingsBinding2 = activityAutoSpo2SettingsBinding3;
            }
            activityAutoSpo2SettingsBinding2.btnSave.setEnabled(true);
            return;
        }
        TextView textView2 = this.q;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView2 = null;
        }
        textView2.setAlpha(0.5f);
        this.p = false;
        ActivityAutoSpo2SettingsBinding activityAutoSpo2SettingsBinding4 = this.v;
        if (activityAutoSpo2SettingsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAutoSpo2SettingsBinding2 = activityAutoSpo2SettingsBinding4;
        }
        activityAutoSpo2SettingsBinding2.btnSave.setEnabled(false);
    }

    @NotNull
    public final AutoSPO2SettingsData getAutoSPO2SettingsData() {
        AutoSPO2SettingsData autoSPO2SettingsData = this.autoSPO2SettingsData;
        if (autoSPO2SettingsData != null) {
            return autoSPO2SettingsData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("autoSPO2SettingsData");
        return null;
    }

    @NotNull
    public final AutoSPO2SettingsViewModel getAutoSpo2SettingsViewModel() {
        AutoSPO2SettingsViewModel autoSPO2SettingsViewModel = this.autoSpo2SettingsViewModel;
        if (autoSPO2SettingsViewModel != null) {
            return autoSPO2SettingsViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("autoSpo2SettingsViewModel");
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
        return this.t;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationBottomSheetDialog() {
        return this.u;
    }

    public final boolean getHasInfoChanged() {
        return this.s;
    }

    public final void initClickListeners() {
        ActivityAutoSpo2SettingsBinding activityAutoSpo2SettingsBinding = this.v;
        TextView textView = null;
        if (activityAutoSpo2SettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoSpo2SettingsBinding = null;
        }
        activityAutoSpo2SettingsBinding.switchReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.y0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoSPO2Settings.C(ActivityAutoSPO2Settings.this, compoundButton, z);
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

    public final void initDialogs() {
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        setBottomSheetDialogOneButtonOneTitle(new BottomSheetDialogOneButtonOneTitle(this, string));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = getBottomSheetDialogOneButtonOneTitle();
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.s0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoSPO2Settings.D(ActivityAutoSPO2Settings.this, view);
            }
        });
        String string3 = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.setting_couldnot_save)");
        setBottomSheetDialogOneButtonOneTitleError(new BottomSheetDialogOneButtonOneTitle(this, string3));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitleError = getBottomSheetDialogOneButtonOneTitleError();
        String string4 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitleError.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.v0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoSPO2Settings.E(ActivityAutoSPO2Settings.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.spo2_measurements));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.r0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoSPO2Settings.F(ActivityAutoSPO2Settings.this, view);
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.q = textView;
        ActivityAutoSpo2SettingsBinding activityAutoSpo2SettingsBinding = null;
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
        TextView textView3 = this.q;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView3 = null;
        }
        textView3.setVisibility(8);
        ActivityAutoSpo2SettingsBinding activityAutoSpo2SettingsBinding2 = this.v;
        if (activityAutoSpo2SettingsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAutoSpo2SettingsBinding2 = null;
        }
        activityAutoSpo2SettingsBinding2.btnSave.setEnabled(false);
        ActivityAutoSpo2SettingsBinding activityAutoSpo2SettingsBinding3 = this.v;
        if (activityAutoSpo2SettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAutoSpo2SettingsBinding = activityAutoSpo2SettingsBinding3;
        }
        activityAutoSpo2SettingsBinding.btnSave.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.t0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoSPO2Settings.G(ActivityAutoSPO2Settings.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        y();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityAutoSpo2SettingsBinding inflate = ActivityAutoSpo2SettingsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.v = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        setAutoSpo2SettingsViewModel((AutoSPO2SettingsViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(AutoSPO2SettingsViewModel.class));
        getAutoSpo2SettingsViewModel().setDialogListener(this);
        H();
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

    public final void setAutoSPO2SettingsData(@NotNull AutoSPO2SettingsData autoSPO2SettingsData) {
        Intrinsics.checkNotNullParameter(autoSPO2SettingsData, "<set-?>");
        this.autoSPO2SettingsData = autoSPO2SettingsData;
    }

    public final void setAutoSpo2SettingsViewModel(@NotNull AutoSPO2SettingsViewModel autoSPO2SettingsViewModel) {
        Intrinsics.checkNotNullParameter(autoSPO2SettingsViewModel, "<set-?>");
        this.autoSpo2SettingsViewModel = autoSPO2SettingsViewModel;
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
        this.t = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setConfirmationBottomSheetDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.u = bottomSheetDialogTwoButtons;
    }

    public final void setHasInfoChanged(boolean z) {
        this.s = z;
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

    public final void y() {
        Boolean valueOf;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2;
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage;
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2;
        this.s = false;
        if (getAutoSpo2SettingsViewModel().getSPo2SettingsDataFromPref().isAutoSpO2() != this.r) {
            this.s = true;
        }
        if (this.s) {
            if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.DISCONNECTED) {
                BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.t;
                if (bottomSheetDialogOneButtonTitleMessage3 != null) {
                    Boolean valueOf2 = bottomSheetDialogOneButtonTitleMessage3 != null ? Boolean.valueOf(bottomSheetDialogOneButtonTitleMessage3.isShowing()) : null;
                    Intrinsics.checkNotNull(valueOf2);
                    if (valueOf2.booleanValue() && (bottomSheetDialogOneButtonTitleMessage2 = this.t) != null) {
                        bottomSheetDialogOneButtonTitleMessage2.dismiss();
                    }
                    this.t = null;
                }
                String string = getString(R.string.band_not_connected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                String string2 = getString(R.string.please_connect_the_device);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage4 = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
                this.t = bottomSheetDialogOneButtonTitleMessage4;
                String string3 = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                bottomSheetDialogOneButtonTitleMessage4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.w0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAutoSPO2Settings.z(ActivityAutoSPO2Settings.this, view);
                    }
                });
                BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage5 = this.t;
                valueOf = bottomSheetDialogOneButtonTitleMessage5 != null ? Boolean.valueOf(bottomSheetDialogOneButtonTitleMessage5.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue() || (bottomSheetDialogOneButtonTitleMessage = this.t) == null) {
                    return;
                }
                bottomSheetDialogOneButtonTitleMessage.show();
                return;
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.u;
            if (bottomSheetDialogTwoButtons3 != null) {
                Boolean valueOf3 = bottomSheetDialogTwoButtons3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons3.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf3);
                if (valueOf3.booleanValue() && (bottomSheetDialogTwoButtons2 = this.u) != null) {
                    bottomSheetDialogTwoButtons2.dismiss();
                }
                this.u = null;
            }
            String string4 = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.confirmation)");
            String string5 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.save_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = new BottomSheetDialogTwoButtons(this, string4, string5, false, 8, null);
            this.u = bottomSheetDialogTwoButtons4;
            String string6 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons4.setPositiveButton(string6, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.x0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAutoSPO2Settings.A(ActivityAutoSPO2Settings.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.u;
            if (bottomSheetDialogTwoButtons5 != null) {
                String string7 = getString(R.string.discard);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.discard)");
                bottomSheetDialogTwoButtons5.setNegativeButton(string7, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.u0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAutoSPO2Settings.B(ActivityAutoSPO2Settings.this, view);
                    }
                });
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons6 = this.u;
            valueOf = bottomSheetDialogTwoButtons6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons6.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.u) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }
}
