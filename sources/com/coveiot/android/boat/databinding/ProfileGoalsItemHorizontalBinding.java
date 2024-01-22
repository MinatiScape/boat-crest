package com.coveiot.android.boat.databinding;

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
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.models.GoalsModel;
/* loaded from: classes3.dex */
public abstract class ProfileGoalsItemHorizontalBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivGoal;
    @Bindable
    public GoalsModel mGoalsData;
    @NonNull
    public final TextView tvGoalName;
    @NonNull
    public final TextView tvGoalValue;

    public ProfileGoalsItemHorizontalBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ivGoal = imageView;
        this.tvGoalName = textView;
        this.tvGoalValue = textView2;
    }

    public static ProfileGoalsItemHorizontalBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ProfileGoalsItemHorizontalBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public GoalsModel getGoalsData() {
        return this.mGoalsData;
    }

    public abstract void setGoalsData(@Nullable GoalsModel goalsModel);

    @Deprecated
    public static ProfileGoalsItemHorizontalBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ProfileGoalsItemHorizontalBinding) ViewDataBinding.bind(obj, view, R.layout.profile_goals_item_horizontal);
    }

    @NonNull
    @Deprecated
    public static ProfileGoalsItemHorizontalBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ProfileGoalsItemHorizontalBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_goals_item_horizontal, viewGroup, z, obj);
    }

    @NonNull
    public static ProfileGoalsItemHorizontalBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ProfileGoalsItemHorizontalBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ProfileGoalsItemHorizontalBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_goals_item_horizontal, null, false, obj);
    }
}
