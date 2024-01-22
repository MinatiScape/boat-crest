package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityProfileGoalsBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView rvGoals;
    @NonNull
    public final View toolbar;
    @NonNull
    public final Guideline verticalGuidelineEnd;
    @NonNull
    public final Guideline verticalGuidelineStart;

    public ActivityProfileGoalsBinding(Object obj, View view, int i, RecyclerView recyclerView, View view2, Guideline guideline, Guideline guideline2) {
        super(obj, view, i);
        this.rvGoals = recyclerView;
        this.toolbar = view2;
        this.verticalGuidelineEnd = guideline;
        this.verticalGuidelineStart = guideline2;
    }

    public static ActivityProfileGoalsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityProfileGoalsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityProfileGoalsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityProfileGoalsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_profile_goals);
    }

    @NonNull
    @Deprecated
    public static ActivityProfileGoalsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityProfileGoalsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_profile_goals, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityProfileGoalsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityProfileGoalsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityProfileGoalsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_profile_goals, null, false, obj);
    }
}
