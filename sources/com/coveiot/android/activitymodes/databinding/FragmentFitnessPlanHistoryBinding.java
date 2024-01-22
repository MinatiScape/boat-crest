package com.coveiot.android.activitymodes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.theme.databinding.ListItemWeekPlanLayoutBinding;
/* loaded from: classes2.dex */
public abstract class FragmentFitnessPlanHistoryBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clOngoingPlan;
    @NonNull
    public final ListItemWeekPlanLayoutBinding fitnessJourneyOngoing;
    @NonNull
    public final RadioButton rbAll;
    @NonNull
    public final RadioButton rbCompleted;
    @NonNull
    public final RadioButton rbOngoing;
    @NonNull
    public final RadioGroup rgCategory;
    @NonNull
    public final RecyclerView rvPlanHistory;
    @NonNull
    public final TextView tvNoDataFound;

    public FragmentFitnessPlanHistoryBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ListItemWeekPlanLayoutBinding listItemWeekPlanLayoutBinding, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioGroup radioGroup, RecyclerView recyclerView, TextView textView) {
        super(obj, view, i);
        this.clOngoingPlan = constraintLayout;
        this.fitnessJourneyOngoing = listItemWeekPlanLayoutBinding;
        this.rbAll = radioButton;
        this.rbCompleted = radioButton2;
        this.rbOngoing = radioButton3;
        this.rgCategory = radioGroup;
        this.rvPlanHistory = recyclerView;
        this.tvNoDataFound = textView;
    }

    public static FragmentFitnessPlanHistoryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentFitnessPlanHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFitnessPlanHistoryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentFitnessPlanHistoryBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_fitness_plan_history);
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessPlanHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentFitnessPlanHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_plan_history, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentFitnessPlanHistoryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessPlanHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentFitnessPlanHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_plan_history, null, false, obj);
    }
}
