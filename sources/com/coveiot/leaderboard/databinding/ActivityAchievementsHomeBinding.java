package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.leaderboard.R;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes9.dex */
public abstract class ActivityAchievementsHomeBinding extends ViewDataBinding {
    @NonNull
    public final CurrentLocationDetailsBinding location;
    @NonNull
    public final TabLayout tabLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final ViewPager viewPager;

    public ActivityAchievementsHomeBinding(Object obj, View view, int i, CurrentLocationDetailsBinding currentLocationDetailsBinding, TabLayout tabLayout, View view2, ViewPager viewPager) {
        super(obj, view, i);
        this.location = currentLocationDetailsBinding;
        this.tabLayout = tabLayout;
        this.toolbar = view2;
        this.viewPager = viewPager;
    }

    public static ActivityAchievementsHomeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAchievementsHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAchievementsHomeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAchievementsHomeBinding) ViewDataBinding.bind(obj, view, R.layout.activity_achievements_home);
    }

    @NonNull
    @Deprecated
    public static ActivityAchievementsHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAchievementsHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_achievements_home, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAchievementsHomeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAchievementsHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAchievementsHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_achievements_home, null, false, obj);
    }
}
