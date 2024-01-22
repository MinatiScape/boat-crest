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
import com.github.mikephil.charting.charts.PieChart;
/* loaded from: classes3.dex */
public abstract class FragmentBattingOverallStatsBinding extends ViewDataBinding {
    @NonNull
    public final TextView avgArmSpeedTitle;
    @NonNull
    public final LinearLayout cardViewHandSpeedGraph;
    @NonNull
    public final ConstraintLayout clAvgHandSpeed;
    @NonNull
    public final ConstraintLayout clGoalAchieved;
    @NonNull
    public final ConstraintLayout clHit;
    @NonNull
    public final ConstraintLayout clMaxHandSpeed;
    @NonNull
    public final ConstraintLayout clMissed;
    @NonNull
    public final ConstraintLayout clSession;
    @NonNull
    public final ConstraintLayout clspeed;
    @NonNull
    public final LinearLayout cvTotalSwings;
    @NonNull
    public final Guideline firstGuideline;
    @NonNull
    public final ImageView ivCalender;
    @NonNull
    public final ImageView ivDuration;
    @NonNull
    public final ImageView ivGoal;
    @NonNull
    public final ImageView ivHandSpeed;
    @NonNull
    public final ImageView ivHit;
    @NonNull
    public final ImageView ivMaxHandSpeed;
    @NonNull
    public final ImageView ivMissed;
    @NonNull
    public final LineChart lineChartArmSpeed;
    @NonNull
    public final LineChart lineChartHitPerc;
    @NonNull
    public final PieChart pieChart;
    @NonNull
    public final RecyclerView rvDays;
    @NonNull
    public final Guideline secondGuideline;
    @NonNull
    public final TextView totalHit;
    @NonNull
    public final TextView totalHitCount;
    @NonNull
    public final TextView totalSwings;
    @NonNull
    public final TextView totalSwingsCount;
    @NonNull
    public final TextView tvAcheivedPerc;
    @NonNull
    public final TextView tvAvgHandSpeed;
    @NonNull
    public final TextView tvAvgHandSpeedTxt;
    @NonNull
    public final TextView tvDuration;
    @NonNull
    public final TextView tvDurationValue;
    @NonNull
    public final TextView tvGoalTxt;
    @NonNull
    public final TextView tvGoals;
    @NonNull
    public final TextView tvHitPerc;
    @NonNull
    public final TextView tvMaxHandSpeed;
    @NonNull
    public final TextView tvMaxHandSpeedTxt;
    @NonNull
    public final TextView tvSessions;
    @NonNull
    public final TextView tvSessionsTxt;
    @NonNull
    public final View view;
    @NonNull
    public final View viewDuration;
    @NonNull
    public final View viewHit;
    @NonNull
    public final View viewTotalBalls;

    public FragmentBattingOverallStatsBinding(Object obj, View view, int i, TextView textView, LinearLayout linearLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, LinearLayout linearLayout2, Guideline guideline, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, LineChart lineChart, LineChart lineChart2, PieChart pieChart, RecyclerView recyclerView, Guideline guideline2, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, View view2, View view3, View view4, View view5) {
        super(obj, view, i);
        this.avgArmSpeedTitle = textView;
        this.cardViewHandSpeedGraph = linearLayout;
        this.clAvgHandSpeed = constraintLayout;
        this.clGoalAchieved = constraintLayout2;
        this.clHit = constraintLayout3;
        this.clMaxHandSpeed = constraintLayout4;
        this.clMissed = constraintLayout5;
        this.clSession = constraintLayout6;
        this.clspeed = constraintLayout7;
        this.cvTotalSwings = linearLayout2;
        this.firstGuideline = guideline;
        this.ivCalender = imageView;
        this.ivDuration = imageView2;
        this.ivGoal = imageView3;
        this.ivHandSpeed = imageView4;
        this.ivHit = imageView5;
        this.ivMaxHandSpeed = imageView6;
        this.ivMissed = imageView7;
        this.lineChartArmSpeed = lineChart;
        this.lineChartHitPerc = lineChart2;
        this.pieChart = pieChart;
        this.rvDays = recyclerView;
        this.secondGuideline = guideline2;
        this.totalHit = textView2;
        this.totalHitCount = textView3;
        this.totalSwings = textView4;
        this.totalSwingsCount = textView5;
        this.tvAcheivedPerc = textView6;
        this.tvAvgHandSpeed = textView7;
        this.tvAvgHandSpeedTxt = textView8;
        this.tvDuration = textView9;
        this.tvDurationValue = textView10;
        this.tvGoalTxt = textView11;
        this.tvGoals = textView12;
        this.tvHitPerc = textView13;
        this.tvMaxHandSpeed = textView14;
        this.tvMaxHandSpeedTxt = textView15;
        this.tvSessions = textView16;
        this.tvSessionsTxt = textView17;
        this.view = view2;
        this.viewDuration = view3;
        this.viewHit = view4;
        this.viewTotalBalls = view5;
    }

    public static FragmentBattingOverallStatsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentBattingOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBattingOverallStatsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentBattingOverallStatsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_batting_overall_stats);
    }

    @NonNull
    @Deprecated
    public static FragmentBattingOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentBattingOverallStatsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_batting_overall_stats, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentBattingOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentBattingOverallStatsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentBattingOverallStatsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_batting_overall_stats, null, false, obj);
    }
}
