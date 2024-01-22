package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.compundview.ScrollDisabledViewpager;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes3.dex */
public abstract class FragmentFitnessHomeBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clTabLayout;
    @NonNull
    public final TabLayout tabLayout;
    @NonNull
    public final Guideline verticalGuidelineEnd;
    @NonNull
    public final Guideline verticalGuidelineStart;
    @NonNull
    public final ScrollDisabledViewpager viewPager;

    public FragmentFitnessHomeBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TabLayout tabLayout, Guideline guideline, Guideline guideline2, ScrollDisabledViewpager scrollDisabledViewpager) {
        super(obj, view, i);
        this.clTabLayout = constraintLayout;
        this.tabLayout = tabLayout;
        this.verticalGuidelineEnd = guideline;
        this.verticalGuidelineStart = guideline2;
        this.viewPager = scrollDisabledViewpager;
    }

    public static FragmentFitnessHomeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentFitnessHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFitnessHomeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentFitnessHomeBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_fitness_home);
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentFitnessHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_home, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentFitnessHomeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentFitnessHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_home, null, false, obj);
    }
}
