package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityCameraSettingsBinding;
import com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.utils.StringExtensionsKt;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.utils.BT3Utils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.CameraSettingsData;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityCameraSettings extends BaseActivity implements DialogListener {
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitleError;
    public CameraSettingsData cameraSettingsData;
    public boolean p;
    public TextView q;
    public boolean r;
    public boolean s;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage t;
    @Nullable
    public BottomSheetDialogTwoButtons u;
    public ActivityCameraSettingsBinding v;
    public BT3CallViewModel w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String x = "ActivityCameraSettings";

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<Integer, Unit> {
        public final /* synthetic */ TextView $this_apply;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(TextView textView) {
            super(1);
            this.$this_apply = textView;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke(num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            if (!ActivityCameraSettings.this.r) {
                Toast.makeText(ActivityCameraSettings.this, this.$this_apply.getResources().getText(R.string.please_turn_on_remote_camera_control), 1).show();
                return;
            }
            String connectedDeviceMacAddress = SessionManager.getInstance(ActivityCameraSettings.this).getConnectedDeviceMacAddress();
            Integer bTDeviceBondedState = BT3Utils.getBTDeviceBondedState(connectedDeviceMacAddress, ActivityCameraSettings.this);
            if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 10) {
                ActivityCameraSettings.this.y(connectedDeviceMacAddress);
            }
        }
    }

    public static final void B(ActivityCameraSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.t;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }
        this$0.finish();
    }

    public static final void C(ActivityCameraSettings this$0, View view) {
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

    public static final void D(ActivityCameraSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.u;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
    }

    public static final void E(ActivityCameraSettings this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r = z;
        this$0.buttonVisible();
    }

    public static final void F(ActivityCameraSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitle().dismiss();
        if (BleApiManager.getInstance(this$0) != null && BleApiManager.getInstance(this$0).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
            String connectedDeviceMacAddress = SessionManager.getInstance(this$0).getConnectedDeviceMacAddress();
            Integer bTDeviceBondedState = BT3Utils.getBTDeviceBondedState(connectedDeviceMacAddress, this$0);
            if (this$0.r && bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 10) {
                this$0.y(connectedDeviceMacAddress);
            }
        }
        this$0.finish();
    }

    public static final void G(ActivityCameraSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitleError().dismiss();
        this$0.finish();
    }

    public static final void H(ActivityCameraSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.A();
    }

    public static final void I(ActivityCameraSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.q;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public final void A() {
        Boolean valueOf;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2;
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage;
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2;
        this.s = false;
        if (z().isCameraEnabled() != this.r) {
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
                bottomSheetDialogOneButtonTitleMessage4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.k4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityCameraSettings.B(ActivityCameraSettings.this, view);
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
            bottomSheetDialogTwoButtons4.setPositiveButton(string6, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.m4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityCameraSettings.C(ActivityCameraSettings.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.u;
            if (bottomSheetDialogTwoButtons5 != null) {
                String string7 = getString(R.string.discard);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.discard)");
                bottomSheetDialogTwoButtons5.setNegativeButton(string7, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.o4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityCameraSettings.D(ActivityCameraSettings.this, view);
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

    public final void J() {
        initToolbar();
        initDialogs();
        initClickListeners();
        K();
    }

    public final void K() {
        CameraSettingsData z = z();
        setCameraSettingsData(z());
        ActivityCameraSettingsBinding activityCameraSettingsBinding = this.v;
        ActivityCameraSettingsBinding activityCameraSettingsBinding2 = null;
        if (activityCameraSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraSettingsBinding = null;
        }
        activityCameraSettingsBinding.switchCamera.setChecked(z.isCameraEnabled());
        this.r = z.isCameraEnabled();
        if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
            Integer bTDeviceBondedState = BT3Utils.getBTDeviceBondedState(SessionManager.getInstance(this).getConnectedDeviceMacAddress(), this);
            if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 10) {
                L();
                return;
            }
            ActivityCameraSettingsBinding activityCameraSettingsBinding3 = this.v;
            if (activityCameraSettingsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityCameraSettingsBinding2 = activityCameraSettingsBinding3;
            }
            activityCameraSettingsBinding2.tvCameraInstructionsText.setText(getResources().getString(R.string.camera_control_info));
            return;
        }
        ActivityCameraSettingsBinding activityCameraSettingsBinding4 = this.v;
        if (activityCameraSettingsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCameraSettingsBinding2 = activityCameraSettingsBinding4;
        }
        activityCameraSettingsBinding2.tvCameraInstructionsText.setText(getResources().getString(R.string.camera_control_info));
    }

    public final void L() {
        ActivityCameraSettingsBinding activityCameraSettingsBinding = this.v;
        if (activityCameraSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraSettingsBinding = null;
        }
        TextView textView = activityCameraSettingsBinding.tvCameraInstructionsText;
        CharSequence text = textView.getResources().getText(R.string.camera_control_hid_info);
        Intrinsics.checkNotNullExpressionValue(text, "resources.getText(R.stri….camera_control_hid_info)");
        textView.setText(StringExtensionsKt.makeUnderlineClickable(text, new a(textView)));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(0);
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
        ActivityCameraSettingsBinding activityCameraSettingsBinding = this.v;
        ActivityCameraSettingsBinding activityCameraSettingsBinding2 = null;
        if (activityCameraSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraSettingsBinding = null;
        }
        if (!Boolean.valueOf(activityCameraSettingsBinding.switchCamera.isChecked()).equals(Boolean.valueOf(getCameraSettingsData().isCameraEnabled()))) {
            TextView textView = this.q;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveText");
                textView = null;
            }
            textView.setAlpha(1.0f);
            this.p = true;
            ActivityCameraSettingsBinding activityCameraSettingsBinding3 = this.v;
            if (activityCameraSettingsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityCameraSettingsBinding2 = activityCameraSettingsBinding3;
            }
            activityCameraSettingsBinding2.btnOk.setEnabled(true);
            return;
        }
        TextView textView2 = this.q;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView2 = null;
        }
        textView2.setAlpha(0.5f);
        this.p = false;
        ActivityCameraSettingsBinding activityCameraSettingsBinding4 = this.v;
        if (activityCameraSettingsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCameraSettingsBinding2 = activityCameraSettingsBinding4;
        }
        activityCameraSettingsBinding2.btnOk.setEnabled(false);
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

    @NotNull
    public final CameraSettingsData getCameraSettingsData() {
        CameraSettingsData cameraSettingsData = this.cameraSettingsData;
        if (cameraSettingsData != null) {
            return cameraSettingsData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("cameraSettingsData");
        return null;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationBottomSheetDialog() {
        return this.u;
    }

    public final boolean getHasInfoChanged() {
        return this.s;
    }

    @NotNull
    public final String getTAG() {
        return this.x;
    }

    public final void initClickListeners() {
        ActivityCameraSettingsBinding activityCameraSettingsBinding = this.v;
        TextView textView = null;
        if (activityCameraSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraSettingsBinding = null;
        }
        activityCameraSettingsBinding.switchCamera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.p4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityCameraSettings.E(ActivityCameraSettings.this, compoundButton, z);
            }
        });
        TextView textView2 = this.q;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
        } else {
            textView = textView2;
        }
        ViewUtilsKt.setSafeOnClickListener(textView, new ActivityCameraSettings$initClickListeners$2(this));
    }

    public final void initDialogs() {
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        setBottomSheetDialogOneButtonOneTitle(new BottomSheetDialogOneButtonOneTitle(this, string));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = getBottomSheetDialogOneButtonOneTitle();
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.n4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCameraSettings.F(ActivityCameraSettings.this, view);
            }
        });
        String string3 = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.setting_couldnot_save)");
        setBottomSheetDialogOneButtonOneTitleError(new BottomSheetDialogOneButtonOneTitle(this, string3));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitleError = getBottomSheetDialogOneButtonOneTitleError();
        String string4 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitleError.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.l4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCameraSettings.G(ActivityCameraSettings.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.remote_camera_control));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.j4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCameraSettings.H(ActivityCameraSettings.this, view);
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.q = textView;
        ActivityCameraSettingsBinding activityCameraSettingsBinding = null;
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
        ActivityCameraSettingsBinding activityCameraSettingsBinding2 = this.v;
        if (activityCameraSettingsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraSettingsBinding2 = null;
        }
        activityCameraSettingsBinding2.btnOk.setEnabled(false);
        ActivityCameraSettingsBinding activityCameraSettingsBinding3 = this.v;
        if (activityCameraSettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCameraSettingsBinding = activityCameraSettingsBinding3;
        }
        activityCameraSettingsBinding.btnOk.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.i4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCameraSettings.I(ActivityCameraSettings.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        A();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityCameraSettingsBinding inflate = ActivityCameraSettingsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.v = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        this.w = (BT3CallViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(BT3CallViewModel.class);
        J();
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

    public final void setCameraSettingsData(@NotNull CameraSettingsData cameraSettingsData) {
        Intrinsics.checkNotNullParameter(cameraSettingsData, "<set-?>");
        this.cameraSettingsData = cameraSettingsData;
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

    public final void y(String str) {
        BT3CallViewModel bT3CallViewModel = this.w;
        if (bT3CallViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
            bT3CallViewModel = null;
        }
        bT3CallViewModel.createBT3Bonding(str, false, false);
    }

    public final CameraSettingsData z() {
        try {
            CameraSettingsData cameraSettingsData = UserDataManager.getInstance(this).getCameraSettingsData();
            if (cameraSettingsData == null) {
                CameraSettingsData companion = CameraSettingsData.Companion.getInstance();
                Intrinsics.checkNotNull(companion);
                companion.setCameraEnabled(false);
            }
            Intrinsics.checkNotNullExpressionValue(cameraSettingsData, "{\n            val camera…raSettingsData2\n        }");
            return cameraSettingsData;
        } catch (Exception unused) {
            CameraSettingsData companion2 = CameraSettingsData.Companion.getInstance();
            Intrinsics.checkNotNull(companion2);
            companion2.setCameraEnabled(false);
            return companion2;
        }
    }
}
