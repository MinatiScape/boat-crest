package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
/* loaded from: classes3.dex */
public abstract class FragmentOverallStatsBattingBinding extends ViewDataBinding {
    @NonNull
    public final Barrier barrierSpeed;
    @NonNull
    public final ConstraintLayout clArmSpeedDetails;
    @NonNull
    public final ConstraintLayout clAvg;
    @NonNull
    public final ConstraintLayout clHandSpeedGraph;
    @NonNull
    public final ConstraintLayout clHitAnalysis;
    @NonNull
    public final ConstraintLayout clHitAnalysisDetails;
    @NonNull
    public final ConstraintLayout clHitAnalysisPerc;
    @NonNull
    public final ConstraintLayout clMax;
    @NonNull
    public final ConstraintLayout clMin;
    @NonNull
    public final RecyclerView gridView;
    @NonNull
    public final ConstraintLayout hitGraph;
    @NonNull
    public final LineChart lineChartArmSpeed;
    @NonNull
    public final RoundedBarChart lineChartHitPerc;
    @NonNull
    public final PieChart pieChart;
    @NonNull
    public final SensAiDetailsBinding sensAiDetails;
    @NonNull
    public final TextView tvAvg;
    @NonNull
    public final TextView tvAvgHit;
    @NonNull
    public final TextView tvAvgValue;
    @NonNull
    public final TextView tvHandSpeed;
    @NonNull
    public final TextView tvHitAnalysis;
    @NonNull
    public final TextView tvHitRate;
    @NonNull
    public final TextView tvMax;
    @NonNull
    public final TextView tvMaxValue;
    @NonNull
    public final TextView tvMin;
    @NonNull
    public final TextView tvMinValue;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvNoDataFoundHandSpeed;
    @NonNull
    public final TextView tvSpeedSummary;
    @NonNull
    public final TextView tvTotalBalls;
    @NonNull
    public final View view;
    @NonNull
    public final View view2;
    @NonNull
    public final View view3;

    public FragmentOverallStatsBattingBinding(Object obj, View view, int i, Barrier barrier, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, RecyclerView recyclerView, ConstraintLayout constraintLayout9, LineChart lineChart, RoundedBarChart roundedBarChart, PieChart pieChart, SensAiDetailsBinding sensAiDetailsBinding, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, View view2, View view3, View view4) {
        super(obj, view, i);
        this.barrierSpeed = barrier;
        this.clArmSpeedDetails = constraintLayout;
        this.clAvg = constraintLayout2;
        this.clHandSpeedGraph = constraintLayout3;
        this.clHitAnalysis = constraintLayout4;
        this.clHitAnalysisDetails = constraintLayout5;
        this.clHitAnalysisPerc = constraintLayout6;
        this.clMax = constraintLayout7;
        this.clMin = constraintLayout8;
        this.gridView = recyclerView;
        this.hitGraph = constraintLayout9;
        this.lineChartArmSpeed = lineChart;
        this.lineChartHitPerc = roundedBarChart;
        this.pieChart = pieChart;
        this.sensAiDetails = sensAiDetailsBinding;
        this.tvAvg = textView;
        this.tvAvgHit = textView2;
        this.tvAvgValue = textView3;
        this.tvHandSpeed = textView4;
        this.tvHitAnalysis = textView5;
        this.tvHitRate = textView6;
        this.tvMax = textView7;
        this.tvMaxValue = textView8;
        this.tvMin = textView9;
        this.tvMinValue = textView10;
        this.tvNoDataFound = textView11;
        this.tvNoDataFoundHandSpeed = textView12;
        this.tvSpeedSummary = textView13;
        this.tvTotalBalls = textView14;
        this.view = view2;
        this.view2 = view3;
        this.view3 = view4;
    }

    public static FragmentOverallStatsBattingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentOverallStatsBattingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentOverallStatsBattingBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentOverallStatsBattingBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_overall_stats_batting);
    }

    @NonNull
    @Deprecated
    public static FragmentOverallStatsBattingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentOverallStatsBattingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_overall_stats_batting, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentOverallStatsBattingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentOverallStatsBattingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentOverallStatsBattingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_overall_stats_batting, null, false, obj);
    }
}
