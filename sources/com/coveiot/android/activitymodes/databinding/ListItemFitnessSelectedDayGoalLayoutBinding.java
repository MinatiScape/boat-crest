package com.coveiot.android.activitymodes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.activitymodes.R;
/* loaded from: classes2.dex */
public abstract class ListItemFitnessSelectedDayGoalLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivTodayGoal;
    @Bindable
    public String mTitle;
    @NonNull
    public final TextView tvTodayGoalName;
    @NonNull
    public final TextView tvTodayGoalValue;

    public ListItemFitnessSelectedDayGoalLayoutBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ivTodayGoal = imageView;
        this.tvTodayGoalName = textView;
        this.tvTodayGoalValue = textView2;
    }

    public static ListItemFitnessSelectedDayGoalLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemFitnessSelectedDayGoalLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    public abstract void setTitle(@Nullable String str);

    @Deprecated
    public static ListItemFitnessSelectedDayGoalLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemFitnessSelectedDayGoalLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_fitness_selected_day_goal_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemFitnessSelectedDayGoalLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemFitnessSelectedDayGoalLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_fitness_selected_day_goal_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemFitnessSelectedDayGoalLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemFitnessSelectedDayGoalLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemFitnessSelectedDayGoalLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_fitness_selected_day_goal_layout, null, false, obj);
    }
}
