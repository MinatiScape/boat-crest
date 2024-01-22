package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.viewmodel.BpCalibrationViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.covepreferences.data.BpCalibrationData;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityBpCalibration extends BaseActivity implements DialogListener {
    public BpCalibrationViewModel bpCalibrationViewModel;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final boolean[] p = new boolean[4];
    public final int q = 3;
    public final int r = 3;

    public static final void D(ActivityBpCalibration this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((EditText) this$0._$_findCachedViewById(R.id.edt_this_bp_1)).requestFocus();
        this$0.N();
    }

    public static final void E(ActivityBpCalibration this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((EditText) this$0._$_findCachedViewById(R.id.edt_ref_bp_1)).requestFocus();
        this$0.N();
    }

    public static final void F(final ActivityBpCalibration this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((Button) this$0._$_findCachedViewById(R.id.btn_calibrate)).isEnabled()) {
            if (!AppUtils.isNetConnected(this$0)) {
                this$0.showNoInternetMessage();
                return;
            }
            BpCalibrationData z = this$0.z();
            if (z != null) {
                if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    this$0.getBpCalibrationViewModel().calibrateBp(z);
                    return;
                }
                String string = this$0.getString(R.string.band_not_connected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                String string2 = this$0.getString(R.string.please_connect_the_device);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this$0, string, string2);
                String string3 = this$0.getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.d4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        ActivityBpCalibration.G(BottomSheetDialogOneButtonTitleMessage.this, this$0, view2);
                    }
                });
                bottomSheetDialogOneButtonTitleMessage.show();
            }
        }
    }

    public static final void G(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityBpCalibration this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.finish();
    }

    public static final boolean I(ActivityBpCalibration this$0, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 6) {
            this$0.B();
            return true;
        }
        return false;
    }

    public static final boolean J(ActivityBpCalibration this$0, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 6) {
            this$0.B();
            return true;
        }
        return false;
    }

    public static final void K(ActivityBpCalibration this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void M(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityBpCalibration this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void O(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityBpCalibration this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public final void A() {
        Button button = (Button) _$_findCachedViewById(R.id.btn_calibrate);
        boolean[] zArr = this.p;
        int length = zArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            } else if (!true || !zArr[i]) {
                break;
            } else {
                i++;
            }
        }
        button.setEnabled(z);
    }

    public final void B() {
        Object systemService = getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        View currentFocus = getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(this);
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public final void C() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_easy_care_bp)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.a4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBpCalibration.D(ActivityBpCalibration.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.ll_ref_bp)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.y3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBpCalibration.E(ActivityBpCalibration.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_calibrate)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.z3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBpCalibration.F(ActivityBpCalibration.this, view);
            }
        });
    }

    public final void H() {
        ((EditText) _$_findCachedViewById(R.id.edt_this_bp_1)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBpCalibration$initWatcher$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                boolean[] boolArray = ActivityBpCalibration.this.getBoolArray();
                ActivityBpCalibration activityBpCalibration = ActivityBpCalibration.this;
                int i4 = R.id.edt_this_bp_1;
                boolArray[0] = ((EditText) activityBpCalibration._$_findCachedViewById(i4)).getText().toString().length() >= ActivityBpCalibration.this.getMaxSystolicLength() - 1;
                if (((EditText) ActivityBpCalibration.this._$_findCachedViewById(i4)).getText().toString().length() == ActivityBpCalibration.this.getMaxSystolicLength()) {
                    ((EditText) ActivityBpCalibration.this._$_findCachedViewById(R.id.edt_this_bp_2)).requestFocus();
                }
                ActivityBpCalibration.this.A();
            }
        });
        int i = R.id.edt_this_bp_2;
        ((EditText) _$_findCachedViewById(i)).setImeOptions(6);
        ((EditText) _$_findCachedViewById(i)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBpCalibration$initWatcher$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                ActivityBpCalibration.this.getBoolArray()[1] = ((EditText) ActivityBpCalibration.this._$_findCachedViewById(R.id.edt_this_bp_2)).getText().toString().length() >= ActivityBpCalibration.this.getMaxDiastolicLength() - 1;
                ActivityBpCalibration.this.A();
            }
        });
        ((EditText) _$_findCachedViewById(i)).setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.coveiot.android.leonardo.more.activities.f4
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                boolean I;
                I = ActivityBpCalibration.I(ActivityBpCalibration.this, textView, i2, keyEvent);
                return I;
            }
        });
        ((EditText) _$_findCachedViewById(R.id.edt_ref_bp_1)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBpCalibration$initWatcher$4
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                boolean[] boolArray = ActivityBpCalibration.this.getBoolArray();
                ActivityBpCalibration activityBpCalibration = ActivityBpCalibration.this;
                int i5 = R.id.edt_ref_bp_1;
                boolArray[2] = ((EditText) activityBpCalibration._$_findCachedViewById(i5)).getText().toString().length() >= ActivityBpCalibration.this.getMaxSystolicLength() - 1;
                if (((EditText) ActivityBpCalibration.this._$_findCachedViewById(i5)).getText().toString().length() == ActivityBpCalibration.this.getMaxSystolicLength()) {
                    ((EditText) ActivityBpCalibration.this._$_findCachedViewById(R.id.edt_ref_bp_2)).requestFocus();
                }
                ActivityBpCalibration.this.A();
            }
        });
        int i2 = R.id.edt_ref_bp_2;
        ((EditText) _$_findCachedViewById(i2)).setImeOptions(6);
        ((EditText) _$_findCachedViewById(i2)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBpCalibration$initWatcher$5
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
                ActivityBpCalibration.this.getBoolArray()[3] = ((EditText) ActivityBpCalibration.this._$_findCachedViewById(R.id.edt_ref_bp_2)).getText().toString().length() >= ActivityBpCalibration.this.getMaxDiastolicLength() - 1;
                ActivityBpCalibration.this.A();
            }
        });
        ((EditText) _$_findCachedViewById(i2)).setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.coveiot.android.leonardo.more.activities.e4
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i3, KeyEvent keyEvent) {
                boolean J;
                J = ActivityBpCalibration.J(ActivityBpCalibration.this, textView, i3, keyEvent);
                return J;
            }
        });
    }

    public final void L() {
        String string = getString(R.string.bp_calibration_warning_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bp_calibration_warning_title)");
        String string2 = getString(R.string.bp_calibration_warning_message);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bp_calibration_warning_message)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBpCalibration$showBpCalibrationWarning$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogTwoButtons.this.dismiss();
            }
        });
        String string4 = getString(R.string.proceed);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.proceed)");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityBpCalibration$showBpCalibrationWarning$2
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                ActivityBpCalibration.this.finish();
                bottomSheetDialogTwoButtons.dismiss();
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    public final void N() {
        Object systemService = getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        View currentFocus = getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(this);
        }
        inputMethodManager.showSoftInput(currentFocus, 1);
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
    public final boolean[] getBoolArray() {
        return this.p;
    }

    @NotNull
    public final BpCalibrationViewModel getBpCalibrationViewModel() {
        BpCalibrationViewModel bpCalibrationViewModel = this.bpCalibrationViewModel;
        if (bpCalibrationViewModel != null) {
            return bpCalibrationViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bpCalibrationViewModel");
        return null;
    }

    public final int getMaxDiastolicLength() {
        return this.r;
    }

    public final int getMaxSystolicLength() {
        return this.q;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        L();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bp_calibration);
        setBpCalibrationViewModel((BpCalibrationViewModel) ViewModelProviders.of(this).get(BpCalibrationViewModel.class));
        getBpCalibrationViewModel().setDialogListener(this);
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.calibrate_bp));
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.x3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBpCalibration.K(ActivityBpCalibration.this, view);
            }
        });
        H();
        C();
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

    public final void setBpCalibrationViewModel(@NotNull BpCalibrationViewModel bpCalibrationViewModel) {
        Intrinsics.checkNotNullParameter(bpCalibrationViewModel, "<set-?>");
        this.bpCalibrationViewModel = bpCalibrationViewModel;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        onDismiss();
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.b4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBpCalibration.M(BottomSheetDialogOneButtonOneTitle.this, this, view);
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
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.c4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBpCalibration.O(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final BpCalibrationData z() {
        BpCalibrationData bpCalibrationData = new BpCalibrationData();
        try {
            int parseInt = Integer.parseInt(((EditText) _$_findCachedViewById(R.id.edt_this_bp_1)).getText().toString());
            int parseInt2 = Integer.parseInt(((EditText) _$_findCachedViewById(R.id.edt_this_bp_2)).getText().toString());
            int parseInt3 = Integer.parseInt(((EditText) _$_findCachedViewById(R.id.edt_ref_bp_1)).getText().toString());
            int parseInt4 = Integer.parseInt(((EditText) _$_findCachedViewById(R.id.edt_ref_bp_2)).getText().toString());
            bpCalibrationData.setSbp(Math.abs(parseInt3 - parseInt));
            if (parseInt3 >= parseInt) {
                bpCalibrationData.setCalculatingSignSbp(1);
            } else {
                bpCalibrationData.setCalculatingSignSbp(2);
            }
            bpCalibrationData.setDbp(Math.abs(parseInt4 - parseInt2));
            if (parseInt4 >= parseInt2) {
                bpCalibrationData.setCalculatingSignDbp(1);
            } else {
                bpCalibrationData.setCalculatingSignDbp(2);
            }
            bpCalibrationData.setTimestamp(System.currentTimeMillis());
            return bpCalibrationData;
        } catch (Exception unused) {
            return null;
        }
    }
}
