package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
/* loaded from: classes3.dex */
public abstract class FragmentVitalStepsBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clAchievements;
    @NonNull
    public final ConstraintLayout clActiveTimes;
    @NonNull
    public final ConstraintLayout clInsights;
    @NonNull
    public final ConstraintLayout clSedentaryTimes;
    @NonNull
    public final View divider;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final RecyclerView rvAchievements;
    @NonNull
    public final RecyclerView rvInsights;
    @NonNull
    public final RoundedBarChart stepsGraph;
    @NonNull
    public final TextView tvAchievementInfo;
    @NonNull
    public final TextView tvAchievementsViewMore;
    @NonNull
    public final TextView tvActiveTimes;
    @NonNull
    public final TextView tvActiveTimesValue;
    @NonNull
    public final TextView tvEditVitalNameGoal;
    @NonNull
    public final TextView tvInsightsHeader;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvSedentaryTimes;
    @NonNull
    public final TextView tvSedentaryTimesValue;
    @NonNull
    public final TextView tvYourAchievements;
    @NonNull
    public final View viewInsights;
    @NonNull
    public final VitalsDetailedLayoutBinding vitalsMainData;

    public FragmentVitalStepsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, View view2, ConstraintLayout constraintLayout5, RecyclerView recyclerView, RecyclerView recyclerView2, RoundedBarChart roundedBarChart, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, View view3, VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding) {
        super(obj, view, i);
        this.clAchievements = constraintLayout;
        this.clActiveTimes = constraintLayout2;
        this.clInsights = constraintLayout3;
        this.clSedentaryTimes = constraintLayout4;
        this.divider = view2;
        this.graphBg = constraintLayout5;
        this.rvAchievements = recyclerView;
        this.rvInsights = recyclerView2;
        this.stepsGraph = roundedBarChart;
        this.tvAchievementInfo = textView;
        this.tvAchievementsViewMore = textView2;
        this.tvActiveTimes = textView3;
        this.tvActiveTimesValue = textView4;
        this.tvEditVitalNameGoal = textView5;
        this.tvInsightsHeader = textView6;
        this.tvNoDataFound = textView7;
        this.tvSedentaryTimes = textView8;
        this.tvSedentaryTimesValue = textView9;
        this.tvYourAchievements = textView10;
        this.viewInsights = view3;
        this.vitalsMainData = vitalsDetailedLayoutBinding;
    }

    public static FragmentVitalStepsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalStepsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVitalStepsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalStepsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_vital_steps);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalStepsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalStepsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_steps, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalStepsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalStepsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalStepsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_steps, null, false, obj);
    }
}
