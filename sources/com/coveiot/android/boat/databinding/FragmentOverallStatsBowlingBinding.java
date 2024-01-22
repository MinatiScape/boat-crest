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
import com.coveiot.android.boat.R;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes3.dex */
public abstract class FragmentOverallStatsBowlingBinding extends ViewDataBinding {
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
    public final LineChart lineChartArmSpeed;
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
    public final TextView tvMax;
    @NonNull
    public final TextView tvMaxValue;
    @NonNull
    public final TextView tvMin;
    @NonNull
    public final TextView tvMinValue;
    @NonNull
    public final TextView tvNoDataFoundHandSpeed;
    @NonNull
    public final TextView tvSpeedSummary;
    @NonNull
    public final TextView tvTotalBalls;
    @NonNull
    public final View view2;
    @NonNull
    public final View view3;

    public FragmentOverallStatsBowlingBinding(Object obj, View view, int i, Barrier barrier, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, LineChart lineChart, SensAiDetailsBinding sensAiDetailsBinding, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, View view2, View view3) {
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
        this.lineChartArmSpeed = lineChart;
        this.sensAiDetails = sensAiDetailsBinding;
        this.tvAvg = textView;
        this.tvAvgHit = textView2;
        this.tvAvgValue = textView3;
        this.tvHandSpeed = textView4;
        this.tvMax = textView5;
        this.tvMaxValue = textView6;
        this.tvMin = textView7;
        this.tvMinValue = textView8;
        this.tvNoDataFoundHandSpeed = textView9;
        this.tvSpeedSummary = textView10;
        this.tvTotalBalls = textView11;
        this.view2 = view2;
        this.view3 = view3;
    }

    public static FragmentOverallStatsBowlingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentOverallStatsBowlingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentOverallStatsBowlingBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentOverallStatsBowlingBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_overall_stats_bowling);
    }

    @NonNull
    @Deprecated
    public static FragmentOverallStatsBowlingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentOverallStatsBowlingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_overall_stats_bowling, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentOverallStatsBowlingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentOverallStatsBowlingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentOverallStatsBowlingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_overall_stats_bowling, null, false, obj);
    }
}
