package com.coveiot.android.sleepenergyscore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.viewpagerindicator.CirclePageIndicator;
/* loaded from: classes6.dex */
public abstract class FragmentVitalEnergyMeterBinding extends ViewDataBinding {
    @NonNull
    public final CirclePageIndicator circlePageIndicatorFeedback;
    @NonNull
    public final ConstraintLayout clDrainGain;
    @NonNull
    public final ConstraintLayout clEnergyInsight;
    @NonNull
    public final ConstraintLayout clMinMax;
    @NonNull
    public final View divider;
    @NonNull
    public final RecyclerView emTipsRecycler;
    @NonNull
    public final ViewPager energyFeedbackPager;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final RecyclerView gridView;
    @NonNull
    public final Guideline guideline;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final Guideline guideline2;
    @NonNull
    public final ImageView ivEnergyIncreaseDecrease;
    @NonNull
    public final LineChart lineChartEnergyMeter;
    @NonNull
    public final View minMaxDevider;
    @NonNull
    public final NestedScrollView nestedScrollEnergyMeter;
    @NonNull
    public final PieChart pieChart;
    @NonNull
    public final TextView pieChartNoDataTv;
    @NonNull
    public final TextView tvDrainTab;
    @NonNull
    public final TextView tvEmDescription;
    @NonNull
    public final TextView tvEndScore;
    @NonNull
    public final TextView tvEndScoreHeader;
    @NonNull
    public final TextView tvEnergyBreakdownHeader;
    @NonNull
    public final TextView tvEnergyBreakdownInfo;
    @NonNull
    public final TextView tvEnergyIncreasedDecreased;
    @NonNull
    public final TextView tvEnergyInsightInfo;
    @NonNull
    public final TextView tvEnergyPercentage;
    @NonNull
    public final TextView tvGainTab;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvStartScore;
    @NonNull
    public final TextView tvStartScoreHeader;
    @NonNull
    public final TextView tvTipsToBoostEnergy;
    @NonNull
    public final TextView tvWhatIsEm;
    @NonNull
    public final LinearLayout viewPagerLayout;
    @NonNull
    public final VitalsDetailedLayoutBinding vitalsMainData;

    public FragmentVitalEnergyMeterBinding(Object obj, View view, int i, CirclePageIndicator circlePageIndicator, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, View view2, RecyclerView recyclerView, ViewPager viewPager, ConstraintLayout constraintLayout4, RecyclerView recyclerView2, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageView imageView, LineChart lineChart, View view3, NestedScrollView nestedScrollView, PieChart pieChart, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, LinearLayout linearLayout, VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding) {
        super(obj, view, i);
        this.circlePageIndicatorFeedback = circlePageIndicator;
        this.clDrainGain = constraintLayout;
        this.clEnergyInsight = constraintLayout2;
        this.clMinMax = constraintLayout3;
        this.divider = view2;
        this.emTipsRecycler = recyclerView;
        this.energyFeedbackPager = viewPager;
        this.graphBg = constraintLayout4;
        this.gridView = recyclerView2;
        this.guideline = guideline;
        this.guideline1 = guideline2;
        this.guideline2 = guideline3;
        this.ivEnergyIncreaseDecrease = imageView;
        this.lineChartEnergyMeter = lineChart;
        this.minMaxDevider = view3;
        this.nestedScrollEnergyMeter = nestedScrollView;
        this.pieChart = pieChart;
        this.pieChartNoDataTv = textView;
        this.tvDrainTab = textView2;
        this.tvEmDescription = textView3;
        this.tvEndScore = textView4;
        this.tvEndScoreHeader = textView5;
        this.tvEnergyBreakdownHeader = textView6;
        this.tvEnergyBreakdownInfo = textView7;
        this.tvEnergyIncreasedDecreased = textView8;
        this.tvEnergyInsightInfo = textView9;
        this.tvEnergyPercentage = textView10;
        this.tvGainTab = textView11;
        this.tvNoDataFound = textView12;
        this.tvStartScore = textView13;
        this.tvStartScoreHeader = textView14;
        this.tvTipsToBoostEnergy = textView15;
        this.tvWhatIsEm = textView16;
        this.viewPagerLayout = linearLayout;
        this.vitalsMainData = vitalsDetailedLayoutBinding;
    }

    public static FragmentVitalEnergyMeterBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalEnergyMeterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVitalEnergyMeterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalEnergyMeterBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_vital_energy_meter);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalEnergyMeterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalEnergyMeterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_energy_meter, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalEnergyMeterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalEnergyMeterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalEnergyMeterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_energy_meter, null, false, obj);
    }
}
