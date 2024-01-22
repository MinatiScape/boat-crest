package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentWatchDiagnosticDashboardBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout diagnosticContainer;
    @NonNull
    public final View toolBar;

    public FragmentWatchDiagnosticDashboardBinding(Object obj, View view, int i, FrameLayout frameLayout, View view2) {
        super(obj, view, i);
        this.diagnosticContainer = frameLayout;
        this.toolBar = view2;
    }

    public static FragmentWatchDiagnosticDashboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentWatchDiagnosticDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWatchDiagnosticDashboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentWatchDiagnosticDashboardBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_watch_diagnostic_dashboard);
    }

    @NonNull
    @Deprecated
    public static FragmentWatchDiagnosticDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentWatchDiagnosticDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_watch_diagnostic_dashboard, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentWatchDiagnosticDashboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentWatchDiagnosticDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentWatchDiagnosticDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_watch_diagnostic_dashboard, null, false, obj);
    }
}
