package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentRunWatchDiagnosticsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnRunDiagnostic;
    @NonNull
    public final ConstraintLayout clInstructions;
    @NonNull
    public final ConstraintLayout clTests;
    @NonNull
    public final TextView headerText;
    @NonNull
    public final TextView instructionFiveText;
    @NonNull
    public final TextView instructionFourText;
    @NonNull
    public final TextView instructionOneText;
    @NonNull
    public final TextView instructionThreeText;
    @NonNull
    public final TextView instructionTwoText;
    @NonNull
    public final RecyclerView rvDiagnosticTesting;

    public FragmentRunWatchDiagnosticsBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, RecyclerView recyclerView) {
        super(obj, view, i);
        this.btnRunDiagnostic = button;
        this.clInstructions = constraintLayout;
        this.clTests = constraintLayout2;
        this.headerText = textView;
        this.instructionFiveText = textView2;
        this.instructionFourText = textView3;
        this.instructionOneText = textView4;
        this.instructionThreeText = textView5;
        this.instructionTwoText = textView6;
        this.rvDiagnosticTesting = recyclerView;
    }

    public static FragmentRunWatchDiagnosticsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentRunWatchDiagnosticsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRunWatchDiagnosticsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentRunWatchDiagnosticsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_run_watch_diagnostics);
    }

    @NonNull
    @Deprecated
    public static FragmentRunWatchDiagnosticsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentRunWatchDiagnosticsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_run_watch_diagnostics, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentRunWatchDiagnosticsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentRunWatchDiagnosticsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentRunWatchDiagnosticsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_run_watch_diagnostics, null, false, obj);
    }
}
