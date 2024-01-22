package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentRunDiagnosticTestingBinding extends ViewDataBinding {
    @NonNull
    public final Button btnNo;
    @NonNull
    public final Button btnProceed;
    @NonNull
    public final Button btnRepeat;
    @NonNull
    public final Button btnYes;
    @NonNull
    public final ConstraintLayout clBottomButton;
    @NonNull
    public final ConstraintLayout clHeader;
    @NonNull
    public final ImageView ivCharging;
    @Bindable
    public String mHeaderData;
    @Bindable
    public Boolean mShowButtonProceed;
    @Bindable
    public Boolean mShowButtonRepeat;
    @Bindable
    public Boolean mShowCharging;
    @Bindable
    public Boolean mShowClBottomButton;
    @Bindable
    public Boolean mShowTimer;
    @Bindable
    public String mTimerData;
    @NonNull
    public final RecyclerView rvDiagnosticTesting;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final TextView tvTestHeader;
    @NonNull
    public final TextView tvTimer;

    public FragmentRunDiagnosticTestingBinding(Object obj, View view, int i, Button button, Button button2, Button button3, Button button4, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.btnNo = button;
        this.btnProceed = button2;
        this.btnRepeat = button3;
        this.btnYes = button4;
        this.clBottomButton = constraintLayout;
        this.clHeader = constraintLayout2;
        this.ivCharging = imageView;
        this.rvDiagnosticTesting = recyclerView;
        this.tvInfo = textView;
        this.tvTestHeader = textView2;
        this.tvTimer = textView3;
    }

    public static FragmentRunDiagnosticTestingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentRunDiagnosticTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public String getHeaderData() {
        return this.mHeaderData;
    }

    @Nullable
    public Boolean getShowButtonProceed() {
        return this.mShowButtonProceed;
    }

    @Nullable
    public Boolean getShowButtonRepeat() {
        return this.mShowButtonRepeat;
    }

    @Nullable
    public Boolean getShowCharging() {
        return this.mShowCharging;
    }

    @Nullable
    public Boolean getShowClBottomButton() {
        return this.mShowClBottomButton;
    }

    @Nullable
    public Boolean getShowTimer() {
        return this.mShowTimer;
    }

    @Nullable
    public String getTimerData() {
        return this.mTimerData;
    }

    public abstract void setHeaderData(@Nullable String str);

    public abstract void setShowButtonProceed(@Nullable Boolean bool);

    public abstract void setShowButtonRepeat(@Nullable Boolean bool);

    public abstract void setShowCharging(@Nullable Boolean bool);

    public abstract void setShowClBottomButton(@Nullable Boolean bool);

    public abstract void setShowTimer(@Nullable Boolean bool);

    public abstract void setTimerData(@Nullable String str);

    @Deprecated
    public static FragmentRunDiagnosticTestingBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentRunDiagnosticTestingBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_run_diagnostic_testing);
    }

    @NonNull
    @Deprecated
    public static FragmentRunDiagnosticTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentRunDiagnosticTestingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_run_diagnostic_testing, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentRunDiagnosticTestingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentRunDiagnosticTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentRunDiagnosticTestingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_run_diagnostic_testing, null, false, obj);
    }
}
