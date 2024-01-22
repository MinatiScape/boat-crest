package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.CircularArcProgressBar;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ArcCircularProgressBarBinding extends ViewDataBinding {
    @NonNull
    public final CircularArcProgressBar circularArcProgressBar;
    @NonNull
    public final CircularArcProgressBar circularArcProgressBarDummy;
    @NonNull
    public final CircularArcProgressBar circularArcProgressBarTop;

    public ArcCircularProgressBarBinding(Object obj, View view, int i, CircularArcProgressBar circularArcProgressBar, CircularArcProgressBar circularArcProgressBar2, CircularArcProgressBar circularArcProgressBar3) {
        super(obj, view, i);
        this.circularArcProgressBar = circularArcProgressBar;
        this.circularArcProgressBarDummy = circularArcProgressBar2;
        this.circularArcProgressBarTop = circularArcProgressBar3;
    }

    public static ArcCircularProgressBarBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ArcCircularProgressBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ArcCircularProgressBarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ArcCircularProgressBarBinding) ViewDataBinding.bind(obj, view, R.layout.arc_circular_progress_bar);
    }

    @NonNull
    @Deprecated
    public static ArcCircularProgressBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ArcCircularProgressBarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.arc_circular_progress_bar, viewGroup, z, obj);
    }

    @NonNull
    public static ArcCircularProgressBarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ArcCircularProgressBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ArcCircularProgressBarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.arc_circular_progress_bar, null, false, obj);
    }
}
