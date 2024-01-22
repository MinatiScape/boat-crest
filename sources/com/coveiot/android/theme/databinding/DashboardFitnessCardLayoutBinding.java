package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.CircularArcProgressBar;
import com.coveiot.android.theme.R;
import com.coveiot.covepreferences.data.StepsDataModel;
/* loaded from: classes7.dex */
public abstract class DashboardFitnessCardLayoutBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout clDashboardFitness;
    @Bindable
    public StepsDataModel mStepsDataModel;
    @Bindable
    public Integer mStepsGoal;
    @NonNull
    public final CircularArcProgressBar progressBar;
    @NonNull
    public final CircularArcProgressBar progressBar1;
    @NonNull
    public final TextView tvGoalDetail;
    @NonNull
    public final TextView tvStepsCount;

    public DashboardFitnessCardLayoutBinding(Object obj, View view, int i, FrameLayout frameLayout, CircularArcProgressBar circularArcProgressBar, CircularArcProgressBar circularArcProgressBar2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.clDashboardFitness = frameLayout;
        this.progressBar = circularArcProgressBar;
        this.progressBar1 = circularArcProgressBar2;
        this.tvGoalDetail = textView;
        this.tvStepsCount = textView2;
    }

    public static DashboardFitnessCardLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DashboardFitnessCardLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public StepsDataModel getStepsDataModel() {
        return this.mStepsDataModel;
    }

    @Nullable
    public Integer getStepsGoal() {
        return this.mStepsGoal;
    }

    public abstract void setStepsDataModel(@Nullable StepsDataModel stepsDataModel);

    public abstract void setStepsGoal(@Nullable Integer num);

    @Deprecated
    public static DashboardFitnessCardLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DashboardFitnessCardLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.dashboard_fitness_card_layout);
    }

    @NonNull
    @Deprecated
    public static DashboardFitnessCardLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (DashboardFitnessCardLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dashboard_fitness_card_layout, viewGroup, z, obj);
    }

    @NonNull
    public static DashboardFitnessCardLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DashboardFitnessCardLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (DashboardFitnessCardLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dashboard_fitness_card_layout, null, false, obj);
    }
}
