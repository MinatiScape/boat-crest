package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentOverallStatsBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clBattingBowling;
    @NonNull
    public final ConstraintLayout clTopSelector;
    @NonNull
    public final FrameLayout container;
    @NonNull
    public final TextView tvBatting;
    @NonNull
    public final TextView tvBowling;
    @NonNull
    public final TextView tvMonth;
    @NonNull
    public final TextView tvWeek;
    @NonNull
    public final TextView tvYear;

    public FragmentOverallStatsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, FrameLayout frameLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.clBattingBowling = constraintLayout;
        this.clTopSelector = constraintLayout2;
        this.container = frameLayout;
        this.tvBatting = textView;
        this.tvBowling = textView2;
        this.tvMonth = textView3;
        this.tvWeek = textView4;
        this.tvYear = textView5;
    }

    public static FragmentOverallStatsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentOverallStatsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentOverallStatsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_overall_stats);
    }

    @NonNull
    @Deprecated
    public static FragmentOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentOverallStatsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_overall_stats, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentOverallStatsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_overall_stats, null, false, obj);
    }
}
