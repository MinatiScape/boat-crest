package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.viewmodel.ActivityAmbientSoundSettingsViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.covepreferences.UserDataManager;
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
public final class ActivityAmbientSoundSettings extends BaseActivity implements DialogListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public TextView p;
    public ActivityAmbientSoundSettingsViewModel q;
    public boolean r;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle s;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle t;
    @Nullable
    public BottomSheetDialogTwoButtons u;

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
            if (BleApiManager.getInstance(ActivityAmbientSoundSettings.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                if (AppUtils.isNetConnected(ActivityAmbientSoundSettings.this)) {
                    ActivityAmbientSoundSettings.this.showProgress();
                    ActivityAmbientSoundSettingsViewModel activityAmbientSoundSettingsViewModel = ActivityAmbientSoundSettings.this.q;
                    if (activityAmbientSoundSettingsViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        activityAmbientSoundSettingsViewModel = null;
                    }
                    activityAmbientSoundSettingsViewModel.setAmbientSoundSettingsToWatch(((SwitchCompat) ActivityAmbientSoundSettings.this._$_findCachedViewById(R.id.ambient_sound_switch)).isChecked());
                    return;
                }
                ActivityAmbientSoundSettings activityAmbientSoundSettings = ActivityAmbientSoundSettings.this;
                Toast.makeText(activityAmbientSoundSettings, activityAmbientSoundSettings.getResources().getString(R.string.please_check_network), 1).show();
                return;
            }
            Toast.makeText(ActivityAmbientSoundSettings.this, (int) R.string.band_not_connected, 1).show();
        }
    }

    public static final void A(ActivityAmbientSoundSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.u;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
    }

    public static final void B(ActivityAmbientSoundSettings this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(compoundButton, "compoundButton");
        if (!Intrinsics.areEqual(Boolean.valueOf(z), UserDataManager.getInstance(this$0).isAmbientSoundSettingsEnabled())) {
            this$0.w(true);
        } else {
            this$0.w(false);
        }
    }

    public static final void C(ActivityAmbientSoundSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.s;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
        this$0.finish();
    }

    public static final void D(ActivityAmbientSoundSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.t;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
        this$0.finish();
    }

    public static final void y(ActivityAmbientSoundSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void z(ActivityAmbientSoundSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.u;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        TextView textView = this$0.p;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
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
        return this.r;
    }

    @Nullable
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitleError() {
        return this.s;
    }

    @Nullable
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitleSucess() {
        return this.t;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationBottomSheetDialog() {
        return this.u;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.ambient_sound_level_txt));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAmbientSoundSettings.y(ActivityAmbientSoundSettings.this, view);
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.p = textView;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setText(getString(R.string.save_camel));
        TextView textView3 = this.p;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView3 = null;
        }
        textView3.setAlpha(0.5f);
        TextView textView4 = this.p;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
        } else {
            textView2 = textView4;
        }
        textView2.setVisibility(0);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2;
        if (this.r) {
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.u;
            if (bottomSheetDialogTwoButtons3 != null) {
                Boolean valueOf = bottomSheetDialogTwoButtons3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons3.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue() && (bottomSheetDialogTwoButtons2 = this.u) != null) {
                    bottomSheetDialogTwoButtons2.dismiss();
                }
                this.u = null;
            }
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.u = bottomSheetDialogTwoButtons4;
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogTwoButtons4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.y
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAmbientSoundSettings.z(ActivityAmbientSoundSettings.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.u;
            if (bottomSheetDialogTwoButtons5 != null) {
                String string4 = getString(R.string.cancel);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
                bottomSheetDialogTwoButtons5.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.z
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAmbientSoundSettings.A(ActivityAmbientSoundSettings.this, view);
                    }
                });
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons6 = this.u;
            Boolean valueOf2 = bottomSheetDialogTwoButtons6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons6.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf2);
            if (valueOf2.booleanValue() || (bottomSheetDialogTwoButtons = this.u) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ambient_sound_settings);
        ActivityAmbientSoundSettingsViewModel activityAmbientSoundSettingsViewModel = (ActivityAmbientSoundSettingsViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityAmbientSoundSettingsViewModel.class);
        this.q = activityAmbientSoundSettingsViewModel;
        if (activityAmbientSoundSettingsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityAmbientSoundSettingsViewModel = null;
        }
        activityAmbientSoundSettingsViewModel.setDialogListener(this);
        initToolbar();
        x();
        w(false);
        int i = R.id.ambient_sound_switch;
        Boolean isAmbientSoundSettingsEnabled = UserDataManager.getInstance(this).isAmbientSoundSettingsEnabled();
        Intrinsics.checkNotNullExpressionValue(isAmbientSoundSettingsEnabled, "getInstance(this).isAmbientSoundSettingsEnabled");
        ((SwitchCompat) _$_findCachedViewById(i)).setChecked(isAmbientSoundSettingsEnabled.booleanValue());
        ((SwitchCompat) _$_findCachedViewById(i)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.d0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAmbientSoundSettings.B(ActivityAmbientSoundSettings.this, compoundButton, z);
            }
        });
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

    public final void setBoolSaveVisible(boolean z) {
        this.r = z;
    }

    public final void setBottomSheetDialogOneButtonOneTitleError(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.s = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setBottomSheetDialogOneButtonOneTitleSucess(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.t = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setConfirmationBottomSheetDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.u = bottomSheetDialogTwoButtons;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.s;
        if (bottomSheetDialogOneButtonOneTitle3 != null) {
            Boolean valueOf = bottomSheetDialogOneButtonOneTitle3 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle3.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialogOneButtonOneTitle2 = this.s) != null) {
                bottomSheetDialogOneButtonOneTitle2.dismiss();
            }
            this.s = null;
        }
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = new BottomSheetDialogOneButtonOneTitle(this, string);
        this.s = bottomSheetDialogOneButtonOneTitle4;
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle4.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAmbientSoundSettings.C(ActivityAmbientSoundSettings.this, view);
            }
        });
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle5 = this.s;
        Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle5 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle5.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (bottomSheetDialogOneButtonOneTitle = this.s) == null) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.t;
        if (bottomSheetDialogOneButtonOneTitle3 != null) {
            Boolean valueOf = bottomSheetDialogOneButtonOneTitle3 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle3.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialogOneButtonOneTitle2 = this.t) != null) {
                bottomSheetDialogOneButtonOneTitle2.dismiss();
            }
            this.t = null;
        }
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = new BottomSheetDialogOneButtonOneTitle(this, string);
        this.t = bottomSheetDialogOneButtonOneTitle4;
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle4.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAmbientSoundSettings.D(ActivityAmbientSoundSettings.this, view);
            }
        });
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle5 = this.t;
        Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle5 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle5.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (bottomSheetDialogOneButtonOneTitle = this.t) == null) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void w(boolean z) {
        TextView textView = null;
        if (z) {
            TextView textView2 = this.p;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveText");
            } else {
                textView = textView2;
            }
            textView.setAlpha(1.0f);
            this.r = true;
            return;
        }
        TextView textView3 = this.p;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
        } else {
            textView = textView3;
        }
        textView.setAlpha(0.5f);
        this.r = false;
    }

    public final void x() {
        TextView textView = this.p;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        ViewUtilsKt.setSafeOnClickListener(textView, new a());
    }
}
