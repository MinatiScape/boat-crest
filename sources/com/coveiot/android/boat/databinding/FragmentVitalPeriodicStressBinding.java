package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.viewpagerindicator.CirclePageIndicator;
/* loaded from: classes3.dex */
public abstract class FragmentVitalPeriodicStressBinding extends ViewDataBinding {
    @NonNull
    public final CirclePageIndicator circlePageIndicatorFeedback;
    @NonNull
    public final ConstraintLayout clStressInsight;
    @NonNull
    public final View divider;
    @NonNull
    public final View divider1;
    @NonNull
    public final View divider2;
    @NonNull
    public final ViewPager feedbackPager;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final Guideline guideline;
    @NonNull
    public final TextView high;
    @NonNull
    public final HorizontalScrollView hlLegendLayout;
    @NonNull
    public final ImageView ivStressIncreaseDecrease;
    @NonNull
    public final ConstraintLayout legendLayout;
    @NonNull
    public final TextView mild;
    @NonNull
    public final TextView moderate;
    @NonNull
    public final NestedScrollView nestedScrollStress;
    @NonNull
    public final TextView relaxed;
    @NonNull
    public final RoundedBarChart stressGraph;
    @NonNull
    public final ImageView stressRangeBar;
    @NonNull
    public final RecyclerView stressTipsRecycler;
    @NonNull
    public final TextView tvEnableHrvSettings;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvStressIncreasedDecreased;
    @NonNull
    public final TextView tvStressInsightInfo;
    @NonNull
    public final TextView tvStressPercentage;
    @NonNull
    public final TextView tvTipsToReduce;
    @NonNull
    public final TextView tvWhatIsStress;
    @NonNull
    public final TextView tvdisclaimer;
    @NonNull
    public final TextView tvstressdescription;
    @NonNull
    public final LinearLayout viewPagerLayout;
    @NonNull
    public final VitalsDetailedLayoutBinding vitalsMainData;

    public FragmentVitalPeriodicStressBinding(Object obj, View view, int i, CirclePageIndicator circlePageIndicator, ConstraintLayout constraintLayout, View view2, View view3, View view4, ViewPager viewPager, ConstraintLayout constraintLayout2, Guideline guideline, TextView textView, HorizontalScrollView horizontalScrollView, ImageView imageView, ConstraintLayout constraintLayout3, TextView textView2, TextView textView3, NestedScrollView nestedScrollView, TextView textView4, RoundedBarChart roundedBarChart, ImageView imageView2, RecyclerView recyclerView, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, LinearLayout linearLayout, VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding) {
        super(obj, view, i);
        this.circlePageIndicatorFeedback = circlePageIndicator;
        this.clStressInsight = constraintLayout;
        this.divider = view2;
        this.divider1 = view3;
        this.divider2 = view4;
        this.feedbackPager = viewPager;
        this.graphBg = constraintLayout2;
        this.guideline = guideline;
        this.high = textView;
        this.hlLegendLayout = horizontalScrollView;
        this.ivStressIncreaseDecrease = imageView;
        this.legendLayout = constraintLayout3;
        this.mild = textView2;
        this.moderate = textView3;
        this.nestedScrollStress = nestedScrollView;
        this.relaxed = textView4;
        this.stressGraph = roundedBarChart;
        this.stressRangeBar = imageView2;
        this.stressTipsRecycler = recyclerView;
        this.tvEnableHrvSettings = textView5;
        this.tvNoDataFound = textView6;
        this.tvStressIncreasedDecreased = textView7;
        this.tvStressInsightInfo = textView8;
        this.tvStressPercentage = textView9;
        this.tvTipsToReduce = textView10;
        this.tvWhatIsStress = textView11;
        this.tvdisclaimer = textView12;
        this.tvstressdescription = textView13;
        this.viewPagerLayout = linearLayout;
        this.vitalsMainData = vitalsDetailedLayoutBinding;
    }

    public static FragmentVitalPeriodicStressBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalPeriodicStressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVitalPeriodicStressBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalPeriodicStressBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_vital_periodic_stress);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalPeriodicStressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalPeriodicStressBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_periodic_stress, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalPeriodicStressBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalPeriodicStressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalPeriodicStressBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_periodic_stress, null, false, obj);
    }
}
