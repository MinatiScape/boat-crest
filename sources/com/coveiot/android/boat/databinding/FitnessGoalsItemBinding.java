package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FitnessGoalsItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivGoal;
    @NonNull
    public final TextView tvGoalName;
    @NonNull
    public final TextView tvGoalTarget;
    @NonNull
    public final TextView tvGoalValue;
    @NonNull
    public final View view;

    public FitnessGoalsItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.ivGoal = imageView;
        this.tvGoalName = textView;
        this.tvGoalTarget = textView2;
        this.tvGoalValue = textView3;
        this.view = view2;
    }

    public static FitnessGoalsItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FitnessGoalsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FitnessGoalsItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FitnessGoalsItemBinding) ViewDataBinding.bind(obj, view, R.layout.fitness_goals_item);
    }

    @NonNull
    @Deprecated
    public static FitnessGoalsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FitnessGoalsItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_goals_item, viewGroup, z, obj);
    }

    @NonNull
    public static FitnessGoalsItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FitnessGoalsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FitnessGoalsItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_goals_item, null, false, obj);
    }
}
