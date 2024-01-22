package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
/* loaded from: classes3.dex */
public abstract class ActivitySessionInsightsDetailsBinding extends ViewDataBinding {
    @NonNull
    public final TextView avgTimeOver;
    @NonNull
    public final TextView avgTimeOverCount;
    @NonNull
    public final LinearLayout cardViewHrGraph;
    @NonNull
    public final ConstraintLayout clAnalysis;
    @NonNull
    public final ConstraintLayout clBowlingAnalysis;
    @NonNull
    public final ConstraintLayout clCalories;
    @NonNull
    public final ConstraintLayout clDetails;
    @NonNull
    public final ConstraintLayout clDuration;
    @NonNull
    public final ConstraintLayout clGoalSummary;
    @NonNull
    public final ConstraintLayout clGoalSummary2;
    @NonNull
    public final ConstraintLayout clHeartRate;
    @NonNull
    public final ConstraintLayout clHit;
    @NonNull
    public final ConstraintLayout clHrZone;
    @NonNull
    public final ConstraintLayout clMaxspeed;
    @NonNull
    public final ConstraintLayout clMissed;
    @NonNull
    public final ConstraintLayout clSpeed1;
    @NonNull
    public final ConstraintLayout clSteps;
    @NonNull
    public final ConstraintLayout clspeed;
    @NonNull
    public final LinearLayout cvBowlingAnalysis;
    @NonNull
    public final LinearLayout cvRateSession;
    @NonNull
    public final LinearLayout cvSessionInsightsData;
    @NonNull
    public final LinearLayout cvSpeedCard;
    @NonNull
    public final LinearLayout cvTotalSwings;
    @NonNull
    public final TextView equivalentOver;
    @NonNull
    public final TextView equivalentOverCount;
    @NonNull
    public final Guideline firstGuideline;
    @NonNull
    public final ProgressBar goalProgressBar;
    @NonNull
    public final LineChart heartrateChart;
    @NonNull
    public final TextView heartrateTitle;
    @NonNull
    public final TextView heartrateValue;
    @NonNull
    public final LinearLayout hrZoneCardView;
    @NonNull
    public final TextView hrZoneTitle;
    @NonNull
    public final ImageView ivCalories;
    @NonNull
    public final ImageView ivDuration;
    @NonNull
    public final ImageView ivGoalAchieved;
    @NonNull
    public final ImageView ivHeartRate;
    @NonNull
    public final ImageView ivHit;
    @NonNull
    public final ImageView ivHrZoneInfo;
    @NonNull
    public final ImageView ivMaxSpeed;
    @NonNull
    public final ImageView ivMissed;
    @NonNull
    public final ImageView ivSessionType;
    @NonNull
    public final ImageView ivSpeed;
    @NonNull
    public final ImageView ivSteps;
    @NonNull
    public final LinearLayout llZoneCardio;
    @NonNull
    public final LinearLayout llZoneFatBurn;
    @NonNull
    public final LinearLayout llZonePeak;
    @NonNull
    public final LinearLayout llZoneThreshold;
    @NonNull
    public final LinearLayout llZoneWarm;
    @NonNull
    public final ProgressBar pbCardio;
    @NonNull
    public final ProgressBar pbFatBurn;
    @NonNull
    public final ProgressBar pbPeak;
    @NonNull
    public final ProgressBar pbThreshold;
    @NonNull
    public final ProgressBar pbWarm;
    @NonNull
    public final PieChart pieChart;
    @NonNull
    public final TextView rateSession;
    @NonNull
    public final RecyclerView rvFeedback;
    @NonNull
    public final Guideline secondGuideline;
    @NonNull
    public final TextView shareButton;
    @NonNull
    public final BarChart speedChart;
    @NonNull
    public final TextView speedTitle;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView totalBalls;
    @NonNull
    public final TextView totalBallsCount;
    @NonNull
    public final TextView totalSwings;
    @NonNull
    public final TextView totalSwingsCount;
    @NonNull
    public final TextView tvActualShots;
    @NonNull
    public final TextView tvActualShotsTxt;
    @NonNull
    public final TextView tvBowlingAnalysisTxt;
    @NonNull
    public final TextView tvCalories;
    @NonNull
    public final TextView tvCaloriestxt;
    @NonNull
    public final TextView tvDurationTxt;
    @NonNull
    public final TextView tvGoalSummaryTxt;
    @NonNull
    public final TextView tvGoalTxt;
    @NonNull
    public final TextView tvHeartRate;
    @NonNull
    public final TextView tvHeartRatetxt;
    @NonNull
    public final TextView tvMaxSpeed;
    @NonNull
    public final TextView tvMaxSpeedtxt;
    @NonNull
    public final TextView tvProgressCardio;
    @NonNull
    public final TextView tvProgressFatBurn;
    @NonNull
    public final TextView tvProgressPeak;
    @NonNull
    public final TextView tvProgressThreshold;
    @NonNull
    public final TextView tvProgressWarm;
    @NonNull
    public final TextView tvSessionInsights;
    @NonNull
    public final TextView tvSessionType;
    @NonNull
    public final TextView tvShotAnalysisTxt;
    @NonNull
    public final TextView tvSpeed;
    @NonNull
    public final TextView tvSpeedtxt;
    @NonNull
    public final TextView tvSteps;
    @NonNull
    public final TextView tvStepstxt;
    @NonNull
    public final TextView tvTargetShots;
    @NonNull
    public final TextView tvTargetShotsTxt;
    @NonNull
    public final TextView tvTime;
    @NonNull
    public final View view;
    @NonNull
    public final View viewGoalSummary;
    @NonNull
    public final View viewTotalSwings;

    public ActivitySessionInsightsDetailsBinding(Object obj, View view, int i, TextView textView, TextView textView2, LinearLayout linearLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ConstraintLayout constraintLayout12, ConstraintLayout constraintLayout13, ConstraintLayout constraintLayout14, ConstraintLayout constraintLayout15, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, TextView textView3, TextView textView4, Guideline guideline, ProgressBar progressBar, LineChart lineChart, TextView textView5, TextView textView6, LinearLayout linearLayout7, TextView textView7, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, ImageView imageView11, LinearLayout linearLayout8, LinearLayout linearLayout9, LinearLayout linearLayout10, LinearLayout linearLayout11, LinearLayout linearLayout12, ProgressBar progressBar2, ProgressBar progressBar3, ProgressBar progressBar4, ProgressBar progressBar5, ProgressBar progressBar6, PieChart pieChart, TextView textView8, RecyclerView recyclerView, Guideline guideline2, TextView textView9, BarChart barChart, TextView textView10, View view2, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, TextView textView26, TextView textView27, TextView textView28, TextView textView29, TextView textView30, TextView textView31, TextView textView32, TextView textView33, TextView textView34, TextView textView35, TextView textView36, TextView textView37, TextView textView38, TextView textView39, TextView textView40, TextView textView41, View view3, View view4, View view5) {
        super(obj, view, i);
        this.avgTimeOver = textView;
        this.avgTimeOverCount = textView2;
        this.cardViewHrGraph = linearLayout;
        this.clAnalysis = constraintLayout;
        this.clBowlingAnalysis = constraintLayout2;
        this.clCalories = constraintLayout3;
        this.clDetails = constraintLayout4;
        this.clDuration = constraintLayout5;
        this.clGoalSummary = constraintLayout6;
        this.clGoalSummary2 = constraintLayout7;
        this.clHeartRate = constraintLayout8;
        this.clHit = constraintLayout9;
        this.clHrZone = constraintLayout10;
        this.clMaxspeed = constraintLayout11;
        this.clMissed = constraintLayout12;
        this.clSpeed1 = constraintLayout13;
        this.clSteps = constraintLayout14;
        this.clspeed = constraintLayout15;
        this.cvBowlingAnalysis = linearLayout2;
        this.cvRateSession = linearLayout3;
        this.cvSessionInsightsData = linearLayout4;
        this.cvSpeedCard = linearLayout5;
        this.cvTotalSwings = linearLayout6;
        this.equivalentOver = textView3;
        this.equivalentOverCount = textView4;
        this.firstGuideline = guideline;
        this.goalProgressBar = progressBar;
        this.heartrateChart = lineChart;
        this.heartrateTitle = textView5;
        this.heartrateValue = textView6;
        this.hrZoneCardView = linearLayout7;
        this.hrZoneTitle = textView7;
        this.ivCalories = imageView;
        this.ivDuration = imageView2;
        this.ivGoalAchieved = imageView3;
        this.ivHeartRate = imageView4;
        this.ivHit = imageView5;
        this.ivHrZoneInfo = imageView6;
        this.ivMaxSpeed = imageView7;
        this.ivMissed = imageView8;
        this.ivSessionType = imageView9;
        this.ivSpeed = imageView10;
        this.ivSteps = imageView11;
        this.llZoneCardio = linearLayout8;
        this.llZoneFatBurn = linearLayout9;
        this.llZonePeak = linearLayout10;
        this.llZoneThreshold = linearLayout11;
        this.llZoneWarm = linearLayout12;
        this.pbCardio = progressBar2;
        this.pbFatBurn = progressBar3;
        this.pbPeak = progressBar4;
        this.pbThreshold = progressBar5;
        this.pbWarm = progressBar6;
        this.pieChart = pieChart;
        this.rateSession = textView8;
        this.rvFeedback = recyclerView;
        this.secondGuideline = guideline2;
        this.shareButton = textView9;
        this.speedChart = barChart;
        this.speedTitle = textView10;
        this.toolbar = view2;
        this.totalBalls = textView11;
        this.totalBallsCount = textView12;
        this.totalSwings = textView13;
        this.totalSwingsCount = textView14;
        this.tvActualShots = textView15;
        this.tvActualShotsTxt = textView16;
        this.tvBowlingAnalysisTxt = textView17;
        this.tvCalories = textView18;
        this.tvCaloriestxt = textView19;
        this.tvDurationTxt = textView20;
        this.tvGoalSummaryTxt = textView21;
        this.tvGoalTxt = textView22;
        this.tvHeartRate = textView23;
        this.tvHeartRatetxt = textView24;
        this.tvMaxSpeed = textView25;
        this.tvMaxSpeedtxt = textView26;
        this.tvProgressCardio = textView27;
        this.tvProgressFatBurn = textView28;
        this.tvProgressPeak = textView29;
        this.tvProgressThreshold = textView30;
        this.tvProgressWarm = textView31;
        this.tvSessionInsights = textView32;
        this.tvSessionType = textView33;
        this.tvShotAnalysisTxt = textView34;
        this.tvSpeed = textView35;
        this.tvSpeedtxt = textView36;
        this.tvSteps = textView37;
        this.tvStepstxt = textView38;
        this.tvTargetShots = textView39;
        this.tvTargetShotsTxt = textView40;
        this.tvTime = textView41;
        this.view = view3;
        this.viewGoalSummary = view4;
        this.viewTotalSwings = view5;
    }

    public static ActivitySessionInsightsDetailsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySessionInsightsDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySessionInsightsDetailsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySessionInsightsDetailsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_session_insights_details);
    }

    @NonNull
    @Deprecated
    public static ActivitySessionInsightsDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySessionInsightsDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_session_insights_details, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySessionInsightsDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySessionInsightsDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySessionInsightsDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_session_insights_details, null, false, obj);
    }
}
