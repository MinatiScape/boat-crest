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
public abstract class ProfileGoalsItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivGoal;
    @Bindable
    public GoalsModel mGoalsData;
    @NonNull
    public final TextView tvEdit;
    @NonNull
    public final TextView tvGoalName;
    @NonNull
    public final TextView tvGoalValue;

    public ProfileGoalsItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ivGoal = imageView;
        this.tvEdit = textView;
        this.tvGoalName = textView2;
        this.tvGoalValue = textView3;
    }

    public static ProfileGoalsItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ProfileGoalsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public GoalsModel getGoalsData() {
        return this.mGoalsData;
    }

    public abstract void setGoalsData(@Nullable GoalsModel goalsModel);

    @Deprecated
    public static ProfileGoalsItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ProfileGoalsItemBinding) ViewDataBinding.bind(obj, view, R.layout.profile_goals_item);
    }

    @NonNull
    @Deprecated
    public static ProfileGoalsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ProfileGoalsItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_goals_item, viewGroup, z, obj);
    }

    @NonNull
    public static ProfileGoalsItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ProfileGoalsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ProfileGoalsItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_goals_item, null, false, obj);
    }
}
