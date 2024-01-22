package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes3.dex */
public abstract class ActivityOverallStatsBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView rvDays;
    @NonNull
    public final TabLayout tabLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final ViewPager viewPager;

    public ActivityOverallStatsBinding(Object obj, View view, int i, RecyclerView recyclerView, TabLayout tabLayout, View view2, ViewPager viewPager) {
        super(obj, view, i);
        this.rvDays = recyclerView;
        this.tabLayout = tabLayout;
        this.toolbar = view2;
        this.viewPager = viewPager;
    }

    public static ActivityOverallStatsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityOverallStatsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityOverallStatsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_overall_stats);
    }

    @NonNull
    @Deprecated
    public static ActivityOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityOverallStatsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_overall_stats, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityOverallStatsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_overall_stats, null, false, obj);
    }
}
