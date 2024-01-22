package com.coveiot.android.smartalert.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.smartalert.R;
/* loaded from: classes6.dex */
public abstract class ActivitySmartAlertTestingBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout ConstraintLayout3;
    @NonNull
    public final ConstraintLayout ConstraintLayout4;
    @NonNull
    public final LinearLayout appsLinear;
    @NonNull
    public final Spinner appsSpinner;
    @NonNull
    public final AppCompatButton autoTestBtn;
    @NonNull
    public final EditText content;
    @NonNull
    public final AppCompatButton submit;
    @NonNull
    public final EditText title;
    @NonNull
    public final View toolbar;

    public ActivitySmartAlertTestingBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, LinearLayout linearLayout, Spinner spinner, AppCompatButton appCompatButton, EditText editText, AppCompatButton appCompatButton2, EditText editText2, View view2) {
        super(obj, view, i);
        this.ConstraintLayout3 = constraintLayout;
        this.ConstraintLayout4 = constraintLayout2;
        this.appsLinear = linearLayout;
        this.appsSpinner = spinner;
        this.autoTestBtn = appCompatButton;
        this.content = editText;
        this.submit = appCompatButton2;
        this.title = editText2;
        this.toolbar = view2;
    }

    public static ActivitySmartAlertTestingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySmartAlertTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySmartAlertTestingBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySmartAlertTestingBinding) ViewDataBinding.bind(obj, view, R.layout.activity_smart_alert_testing);
    }

    @NonNull
    @Deprecated
    public static ActivitySmartAlertTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySmartAlertTestingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_smart_alert_testing, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySmartAlertTestingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySmartAlertTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySmartAlertTestingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_smart_alert_testing, null, false, obj);
    }
}
