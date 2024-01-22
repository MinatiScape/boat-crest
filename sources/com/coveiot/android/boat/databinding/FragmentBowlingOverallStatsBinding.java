package com.coveiot.android.boat.databinding;

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
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes3.dex */
public abstract class FragmentBowlingOverallStatsBinding extends ViewDataBinding {
    @NonNull
    public final TextView avgArmSpeedTitle;
    @NonNull
    public final LinearLayout cardViewHandSpeedGraph;
    @NonNull
    public final ConstraintLayout clAvgArmSpeed;
    @NonNull
    public final ConstraintLayout clGoalAchieved;
    @NonNull
    public final ConstraintLayout clMaxArmSpeed;
    @NonNull
    public final ConstraintLayout clSession;
    @NonNull
    public final ConstraintLayout clTotalBalls;
    @NonNull
    public final ConstraintLayout clspeed;
    @NonNull
    public final Guideline firstGuideline;
    @NonNull
    public final ImageView ivArmSpeed;
    @NonNull
    public final ImageView ivCalender;
    @NonNull
    public final ImageView ivDuration;
    @NonNull
    public final ImageView ivGoal;
    @NonNull
    public final ImageView ivMaxArmSpeed;
    @NonNull
    public final LineChart lineChartArmSpeed;
    @NonNull
    public final RecyclerView rvDays;
    @NonNull
    public final Guideline secondGuideline;
    @NonNull
    public final TextView tvAcheivedPerc;
    @NonNull
    public final TextView tvAvgArmSpeed;
    @NonNull
    public final TextView tvAvgArmSpeedTxt;
    @NonNull
    public final TextView tvDuration;
    @NonNull
    public final TextView tvDurationText;
    @NonNull
    public final TextView tvGoalTxt;
    @NonNull
    public final TextView tvGoals;
    @NonNull
    public final TextView tvMaxArmSpeed;
    @NonNull
    public final TextView tvMaxArmSpeedTxt;
    @NonNull
    public final TextView tvSessions;
    @NonNull
    public final TextView tvSessionsTxt;
    @NonNull
    public final TextView tvTotalBalls;
    @NonNull
    public final TextView tvTotalBallsTxt;
    @NonNull
    public final View view;
    @NonNull
    public final View viewDuration;
    @NonNull
    public final View viewTotalBalls;

    public FragmentBowlingOverallStatsBinding(Object obj, View view, int i, TextView textView, LinearLayout linearLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, Guideline guideline, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LineChart lineChart, RecyclerView recyclerView, Guideline guideline2, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, View view2, View view3, View view4) {
        super(obj, view, i);
        this.avgArmSpeedTitle = textView;
        this.cardViewHandSpeedGraph = linearLayout;
        this.clAvgArmSpeed = constraintLayout;
        this.clGoalAchieved = constraintLayout2;
        this.clMaxArmSpeed = constraintLayout3;
        this.clSession = constraintLayout4;
        this.clTotalBalls = constraintLayout5;
        this.clspeed = constraintLayout6;
        this.firstGuideline = guideline;
        this.ivArmSpeed = imageView;
        this.ivCalender = imageView2;
        this.ivDuration = imageView3;
        this.ivGoal = imageView4;
        this.ivMaxArmSpeed = imageView5;
        this.lineChartArmSpeed = lineChart;
        this.rvDays = recyclerView;
        this.secondGuideline = guideline2;
        this.tvAcheivedPerc = textView2;
        this.tvAvgArmSpeed = textView3;
        this.tvAvgArmSpeedTxt = textView4;
        this.tvDuration = textView5;
        this.tvDurationText = textView6;
        this.tvGoalTxt = textView7;
        this.tvGoals = textView8;
        this.tvMaxArmSpeed = textView9;
        this.tvMaxArmSpeedTxt = textView10;
        this.tvSessions = textView11;
        this.tvSessionsTxt = textView12;
        this.tvTotalBalls = textView13;
        this.tvTotalBallsTxt = textView14;
        this.view = view2;
        this.viewDuration = view3;
        this.viewTotalBalls = view4;
    }

    public static FragmentBowlingOverallStatsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentBowlingOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBowlingOverallStatsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentBowlingOverallStatsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_bowling_overall_stats);
    }

    @NonNull
    @Deprecated
    public static FragmentBowlingOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentBowlingOverallStatsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_bowling_overall_stats, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentBowlingOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentBowlingOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentBowlingOverallStatsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_bowling_overall_stats, null, false, obj);
    }
}
