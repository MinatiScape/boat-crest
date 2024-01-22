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
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
/* loaded from: classes3.dex */
public abstract class ActivitySensAiDetailsBinding extends ViewDataBinding {
    @NonNull
    public final Barrier barrier;
    @NonNull
    public final Barrier barrierGoal;
    @NonNull
    public final ConstraintLayout clArmSpeed;
    @NonNull
    public final ConstraintLayout clAvgTimePerOver;
    @NonNull
    public final ConstraintLayout clBallsDetails;
    @NonNull
    public final ConstraintLayout clCalories;
    @NonNull
    public final ConstraintLayout clEquivalentOvers;
    @NonNull
    public final ConstraintLayout clGoalAcheived;
    @NonNull
    public final ConstraintLayout clGoalDetails;
    @NonNull
    public final ConstraintLayout clGoalSummary;
    @NonNull
    public final ConstraintLayout clGoalSummary2;
    @NonNull
    public final ConstraintLayout clHeartRate;
    @NonNull
    public final ConstraintLayout clHitAnalysis;
    @NonNull
    public final ConstraintLayout clHitAnalysisDetails;
    @NonNull
    public final ConstraintLayout clHrGraph;
    @NonNull
    public final ConstraintLayout clHrZone;
    @NonNull
    public final ConstraintLayout clMaxspeed;
    @NonNull
    public final ConstraintLayout clRateSesssion;
    @NonNull
    public final ConstraintLayout clSessionHighlights;
    @NonNull
    public final ConstraintLayout clSpeed;
    @NonNull
    public final ConstraintLayout clSpeed1;
    @NonNull
    public final ConstraintLayout clSteps;
    @NonNull
    public final ConstraintLayout clTotalBallsBowled;
    @NonNull
    public final ConstraintLayout clVitalDetails;
    @NonNull
    public final ConstraintLayout clspeed;
    @NonNull
    public final Guideline firstGuideline;
    @NonNull
    public final ProgressBar goalProgressBar;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final RecyclerView gridView;
    @NonNull
    public final LineChart heartrateChart;
    @NonNull
    public final TextView heartrateTitle;
    @NonNull
    public final TextView heartrateValue;
    @NonNull
    public final TextView hrZoneTitle;
    @NonNull
    public final ImageView ivCalories;
    @NonNull
    public final ImageView ivGoalAcheived;
    @NonNull
    public final ImageView ivHeartRate;
    @NonNull
    public final ImageView ivHrZoneInfo;
    @NonNull
    public final ImageView ivMaxSpeed;
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
    public final SensAiDetailsBinding sensAiDetails;
    @NonNull
    public final RoundedBarChart speedChart;
    @NonNull
    public final TextView speedTitle;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvAvgTimePerOver;
    @NonNull
    public final TextView tvAvgTimePerOverValue;
    @NonNull
    public final TextView tvCalories;
    @NonNull
    public final TextView tvCaloriestxt;
    @NonNull
    public final TextView tvEquivalentOvers;
    @NonNull
    public final TextView tvEquivalentOversValue;
    @NonNull
    public final TextView tvGoalAchieved;
    @NonNull
    public final TextView tvGoalAchievedMsg;
    @NonNull
    public final TextView tvGoalAchievedTarget;
    @NonNull
    public final TextView tvGoalAchievedTxt;
    @NonNull
    public final TextView tvGoalCompletion;
    @NonNull
    public final TextView tvGoalCompletionValue;
    @NonNull
    public final TextView tvGoalSummaryTxt;
    @NonNull
    public final TextView tvGoalTxt;
    @NonNull
    public final TextView tvHeartRate;
    @NonNull
    public final TextView tvHeartRatetxt;
    @NonNull
    public final TextView tvHitAnalysis;
    @NonNull
    public final TextView tvMaxSpeed;
    @NonNull
    public final TextView tvMaxSpeedtxt;
    @NonNull
    public final TextView tvNoDataFound;
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
    public final TextView tvSpeed;
    @NonNull
    public final TextView tvSpeedtxt;
    @NonNull
    public final TextView tvSteps;
    @NonNull
    public final TextView tvStepstxt;
    @NonNull
    public final TextView tvTotalBalls;
    @NonNull
    public final TextView tvTotalBallsBowled;
    @NonNull
    public final TextView tvTotalBallsBowledValue;
    @NonNull
    public final View view;
    @NonNull
    public final View view1;
    @NonNull
    public final View view2;
    @NonNull
    public final View viewArmSpeed;
    @NonNull
    public final View viewEquivalentOvers;
    @NonNull
    public final View viewGoalSummary;
    @NonNull
    public final View viewTotalBallsBowled;

    public ActivitySensAiDetailsBinding(Object obj, View view, int i, Barrier barrier, Barrier barrier2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ConstraintLayout constraintLayout12, ConstraintLayout constraintLayout13, ConstraintLayout constraintLayout14, ConstraintLayout constraintLayout15, ConstraintLayout constraintLayout16, ConstraintLayout constraintLayout17, ConstraintLayout constraintLayout18, ConstraintLayout constraintLayout19, ConstraintLayout constraintLayout20, ConstraintLayout constraintLayout21, ConstraintLayout constraintLayout22, ConstraintLayout constraintLayout23, Guideline guideline, ProgressBar progressBar, ConstraintLayout constraintLayout24, RecyclerView recyclerView, LineChart lineChart, TextView textView, TextView textView2, TextView textView3, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, ProgressBar progressBar2, ProgressBar progressBar3, ProgressBar progressBar4, ProgressBar progressBar5, ProgressBar progressBar6, PieChart pieChart, TextView textView4, RecyclerView recyclerView2, Guideline guideline2, SensAiDetailsBinding sensAiDetailsBinding, RoundedBarChart roundedBarChart, TextView textView5, View view2, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, TextView textView26, TextView textView27, TextView textView28, TextView textView29, TextView textView30, TextView textView31, TextView textView32, TextView textView33, TextView textView34, TextView textView35, TextView textView36, TextView textView37, TextView textView38, View view3, View view4, View view5, View view6, View view7, View view8, View view9) {
        super(obj, view, i);
        this.barrier = barrier;
        this.barrierGoal = barrier2;
        this.clArmSpeed = constraintLayout;
        this.clAvgTimePerOver = constraintLayout2;
        this.clBallsDetails = constraintLayout3;
        this.clCalories = constraintLayout4;
        this.clEquivalentOvers = constraintLayout5;
        this.clGoalAcheived = constraintLayout6;
        this.clGoalDetails = constraintLayout7;
        this.clGoalSummary = constraintLayout8;
        this.clGoalSummary2 = constraintLayout9;
        this.clHeartRate = constraintLayout10;
        this.clHitAnalysis = constraintLayout11;
        this.clHitAnalysisDetails = constraintLayout12;
        this.clHrGraph = constraintLayout13;
        this.clHrZone = constraintLayout14;
        this.clMaxspeed = constraintLayout15;
        this.clRateSesssion = constraintLayout16;
        this.clSessionHighlights = constraintLayout17;
        this.clSpeed = constraintLayout18;
        this.clSpeed1 = constraintLayout19;
        this.clSteps = constraintLayout20;
        this.clTotalBallsBowled = constraintLayout21;
        this.clVitalDetails = constraintLayout22;
        this.clspeed = constraintLayout23;
        this.firstGuideline = guideline;
        this.goalProgressBar = progressBar;
        this.graphBg = constraintLayout24;
        this.gridView = recyclerView;
        this.heartrateChart = lineChart;
        this.heartrateTitle = textView;
        this.heartrateValue = textView2;
        this.hrZoneTitle = textView3;
        this.ivCalories = imageView;
        this.ivGoalAcheived = imageView2;
        this.ivHeartRate = imageView3;
        this.ivHrZoneInfo = imageView4;
        this.ivMaxSpeed = imageView5;
        this.ivSpeed = imageView6;
        this.ivSteps = imageView7;
        this.llZoneCardio = linearLayout;
        this.llZoneFatBurn = linearLayout2;
        this.llZonePeak = linearLayout3;
        this.llZoneThreshold = linearLayout4;
        this.llZoneWarm = linearLayout5;
        this.pbCardio = progressBar2;
        this.pbFatBurn = progressBar3;
        this.pbPeak = progressBar4;
        this.pbThreshold = progressBar5;
        this.pbWarm = progressBar6;
        this.pieChart = pieChart;
        this.rateSession = textView4;
        this.rvFeedback = recyclerView2;
        this.secondGuideline = guideline2;
        this.sensAiDetails = sensAiDetailsBinding;
        this.speedChart = roundedBarChart;
        this.speedTitle = textView5;
        this.toolbar = view2;
        this.tvAvgTimePerOver = textView6;
        this.tvAvgTimePerOverValue = textView7;
        this.tvCalories = textView8;
        this.tvCaloriestxt = textView9;
        this.tvEquivalentOvers = textView10;
        this.tvEquivalentOversValue = textView11;
        this.tvGoalAchieved = textView12;
        this.tvGoalAchievedMsg = textView13;
        this.tvGoalAchievedTarget = textView14;
        this.tvGoalAchievedTxt = textView15;
        this.tvGoalCompletion = textView16;
        this.tvGoalCompletionValue = textView17;
        this.tvGoalSummaryTxt = textView18;
        this.tvGoalTxt = textView19;
        this.tvHeartRate = textView20;
        this.tvHeartRatetxt = textView21;
        this.tvHitAnalysis = textView22;
        this.tvMaxSpeed = textView23;
        this.tvMaxSpeedtxt = textView24;
        this.tvNoDataFound = textView25;
        this.tvProgressCardio = textView26;
        this.tvProgressFatBurn = textView27;
        this.tvProgressPeak = textView28;
        this.tvProgressThreshold = textView29;
        this.tvProgressWarm = textView30;
        this.tvSessionInsights = textView31;
        this.tvSpeed = textView32;
        this.tvSpeedtxt = textView33;
        this.tvSteps = textView34;
        this.tvStepstxt = textView35;
        this.tvTotalBalls = textView36;
        this.tvTotalBallsBowled = textView37;
        this.tvTotalBallsBowledValue = textView38;
        this.view = view3;
        this.view1 = view4;
        this.view2 = view5;
        this.viewArmSpeed = view6;
        this.viewEquivalentOvers = view7;
        this.viewGoalSummary = view8;
        this.viewTotalBallsBowled = view9;
    }

    public static ActivitySensAiDetailsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySensAiDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySensAiDetailsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySensAiDetailsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_sens_ai_details);
    }

    @NonNull
    @Deprecated
    public static ActivitySensAiDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySensAiDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sens_ai_details, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySensAiDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySensAiDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySensAiDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sens_ai_details, null, false, obj);
    }
}
