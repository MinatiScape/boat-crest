package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentContainerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityWatchDiagnosticsBinding extends ViewDataBinding {
    @NonNull
    public final FragmentContainerView navHostFragment;

    public ActivityWatchDiagnosticsBinding(Object obj, View view, int i, FragmentContainerView fragmentContainerView) {
        super(obj, view, i);
        this.navHostFragment = fragmentContainerView;
    }

    public static ActivityWatchDiagnosticsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityWatchDiagnosticsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWatchDiagnosticsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityWatchDiagnosticsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_watch_diagnostics);
    }

    @NonNull
    @Deprecated
    public static ActivityWatchDiagnosticsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityWatchDiagnosticsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_watch_diagnostics, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityWatchDiagnosticsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityWatchDiagnosticsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityWatchDiagnosticsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_watch_diagnostics, null, false, obj);
    }
}
