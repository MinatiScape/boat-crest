package com.coveiot.android.fitnessbuddies.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.fitnessbuddies.R;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes4.dex */
public abstract class FragmentFitnessBuddiesBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clTabLayout;
    @NonNull
    public final TabLayout tabLayout;
    @NonNull
    public final ViewPager viewPager;

    public FragmentFitnessBuddiesBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TabLayout tabLayout, ViewPager viewPager) {
        super(obj, view, i);
        this.clTabLayout = constraintLayout;
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;
    }

    public static FragmentFitnessBuddiesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentFitnessBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFitnessBuddiesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentFitnessBuddiesBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_fitness_buddies);
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentFitnessBuddiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_buddies, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentFitnessBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentFitnessBuddiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_buddies, null, false, obj);
    }
}
