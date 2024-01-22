package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.coveiot.android.fitnesschallenges.databinding.NoChallengesBannerBinding;
import com.coveiot.android.theme.databinding.ArcCircularProgressBarBinding;
import com.coveiot.android.theme.databinding.LayoutDashboardDoMoreWithYourWatchGridItemBinding;
import com.coveiot.android.theme.databinding.ListItemWeekPlanLayoutBinding;
import com.coveiot.android.theme.databinding.RoundedCardNavLayoutBinding;
/* loaded from: classes3.dex */
public abstract class FragmentFitnessBinding extends ViewDataBinding {
    @NonNull
    public final ArcCircularProgressBarBinding arcProgressBar;
    @NonNull
    public final Barrier barrier;
    @NonNull
    public final LinearLayout challengeLinearLayoutDots;
    @NonNull
    public final ConstraintLayout clActivityHistoryMain;
    @NonNull
    public final ConstraintLayout clBannerLayout;
    @NonNull
    public final ConstraintLayout clChallengeHeaderSection;
    @NonNull
    public final ConstraintLayout clDailyInsights;
    @NonNull
    public final ConstraintLayout clFitnessBlogMain;
    @NonNull
    public final ConstraintLayout clFitnessJourneyMain;
    @NonNull
    public final ConstraintLayout clGetStarted;
    @NonNull
    public final ConstraintLayout clGoalInsightsMain;
    @NonNull
    public final ConstraintLayout clGoals;
    @NonNull
    public final ConstraintLayout clGoalsInsights;
    @NonNull
    public final ConstraintLayout clGuestSleep;
    @NonNull
    public final ConstraintLayout clGuestSteps;
    @NonNull
    public final ConstraintLayout clNoActivity;
    @NonNull
    public final ConstraintLayout clOops;
    @NonNull
    public final ConstraintLayout clProgress;
    @NonNull
    public final ConstraintLayout clRangeInsights;
    @NonNull
    public final ConstraintLayout clSelectedOptionValues;
    @NonNull
    public final ConstraintLayout clWorkoutVideosMain;
    @NonNull
    public final ConstraintLayout fitnessChallenge;
    @NonNull
    public final LayoutDashboardDoMoreWithYourWatchGridItemBinding fitnessJourney;
    @NonNull
    public final ListItemWeekPlanLayoutBinding fitnessJourneyOngoing;
    @NonNull
    public final ImageButton ibForward;
    @NonNull
    public final ImageButton ibPrevious;
    @NonNull
    public final ImageView imageViewDot1;
    @NonNull
    public final ImageView imageViewDot2;
    @NonNull
    public final ImageView ivGoal;
    @NonNull
    public final ImageView ivGoal1;
    @NonNull
    public final ImageView ivNoHistory;
    @NonNull
    public final ImageButton ivShare;
    @NonNull
    public final LinearLayout linearLayoutDots;
    @NonNull
    public final NoChallengesBannerBinding noChallengeView;
    @NonNull
    public final RecyclerView rvActivityHistory;
    @NonNull
    public final RecyclerView rvCalendarGoalsInsights;
    @NonNull
    public final RecyclerView rvCultVideos;
    @NonNull
    public final RecyclerView rvFitnessBlogs;
    @NonNull
    public final RecyclerView rvFitnessChallenge;
    @NonNull
    public final RecyclerView rvGoals;
    @NonNull
    public final TextView tvActivityHistory;
    @NonNull
    public final TextView tvCultVideos;
    @NonNull
    public final TextView tvCultVideosDesc;
    @NonNull
    public final TextView tvFitnessBlogs;
    @NonNull
    public final TextView tvFitnessBlogsDesc;
    @NonNull
    public final TextView tvFitnessChallengeHeader;
    @NonNull
    public final TextView tvFitnessChallengeViewMore;
    @NonNull
    public final TextView tvFitnessJourney;
    @NonNull
    public final TextView tvFitnessPlan;
    @NonNull
    public final TextView tvGetStarted;
    @NonNull
    public final TextView tvGoalAcheived;
    @NonNull
    public final TextView tvGoalDesc;
    @NonNull
    public final TextView tvGoalName;
    @NonNull
    public final TextView tvGoalName1;
    @NonNull
    public final TextView tvGoalStatus;
    @NonNull
    public final TextView tvGoalTarget;
    @NonNull
    public final TextView tvGoalTarget1;
    @NonNull
    public final TextView tvGoalValue;
    @NonNull
    public final TextView tvGoalValue1;
    @NonNull
    public final TextView tvGoalsAchieved;
    @NonNull
    public final TextView tvGoalsAchievedValue;
    @NonNull
    public final TextView tvGoalsInsights;
    @NonNull
    public final TextView tvGoalsInsightsDesc;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final TextView tvNoActivities;
    @NonNull
    public final TextView tvOops;
    @NonNull
    public final TextView tvProgressValue;
    @NonNull
    public final TextView tvSelectedTypeValue;
    @NonNull
    public final TextView tvSleepInsights;
    @NonNull
    public final TextView tvStepsInsights;
    @NonNull
    public final Guideline verticalGuidelineEnd;
    @NonNull
    public final Guideline verticalGuidelineStart;
    @NonNull
    public final View view;
    @NonNull
    public final RoundedCardNavLayoutBinding viewFitnessChallengeDashboardHeader;

    public FragmentFitnessBinding(Object obj, View view, int i, ArcCircularProgressBarBinding arcCircularProgressBarBinding, Barrier barrier, LinearLayout linearLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ConstraintLayout constraintLayout12, ConstraintLayout constraintLayout13, ConstraintLayout constraintLayout14, ConstraintLayout constraintLayout15, ConstraintLayout constraintLayout16, ConstraintLayout constraintLayout17, ConstraintLayout constraintLayout18, ConstraintLayout constraintLayout19, LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding, ListItemWeekPlanLayoutBinding listItemWeekPlanLayoutBinding, ImageButton imageButton, ImageButton imageButton2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageButton imageButton3, LinearLayout linearLayout2, NoChallengesBannerBinding noChallengesBannerBinding, RecyclerView recyclerView, RecyclerView recyclerView2, RecyclerView recyclerView3, RecyclerView recyclerView4, RecyclerView recyclerView5, RecyclerView recyclerView6, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, TextView textView26, TextView textView27, TextView textView28, TextView textView29, TextView textView30, Guideline guideline, Guideline guideline2, View view2, RoundedCardNavLayoutBinding roundedCardNavLayoutBinding) {
        super(obj, view, i);
        this.arcProgressBar = arcCircularProgressBarBinding;
        this.barrier = barrier;
        this.challengeLinearLayoutDots = linearLayout;
        this.clActivityHistoryMain = constraintLayout;
        this.clBannerLayout = constraintLayout2;
        this.clChallengeHeaderSection = constraintLayout3;
        this.clDailyInsights = constraintLayout4;
        this.clFitnessBlogMain = constraintLayout5;
        this.clFitnessJourneyMain = constraintLayout6;
        this.clGetStarted = constraintLayout7;
        this.clGoalInsightsMain = constraintLayout8;
        this.clGoals = constraintLayout9;
        this.clGoalsInsights = constraintLayout10;
        this.clGuestSleep = constraintLayout11;
        this.clGuestSteps = constraintLayout12;
        this.clNoActivity = constraintLayout13;
        this.clOops = constraintLayout14;
        this.clProgress = constraintLayout15;
        this.clRangeInsights = constraintLayout16;
        this.clSelectedOptionValues = constraintLayout17;
        this.clWorkoutVideosMain = constraintLayout18;
        this.fitnessChallenge = constraintLayout19;
        this.fitnessJourney = layoutDashboardDoMoreWithYourWatchGridItemBinding;
        this.fitnessJourneyOngoing = listItemWeekPlanLayoutBinding;
        this.ibForward = imageButton;
        this.ibPrevious = imageButton2;
        this.imageViewDot1 = imageView;
        this.imageViewDot2 = imageView2;
        this.ivGoal = imageView3;
        this.ivGoal1 = imageView4;
        this.ivNoHistory = imageView5;
        this.ivShare = imageButton3;
        this.linearLayoutDots = linearLayout2;
        this.noChallengeView = noChallengesBannerBinding;
        this.rvActivityHistory = recyclerView;
        this.rvCalendarGoalsInsights = recyclerView2;
        this.rvCultVideos = recyclerView3;
        this.rvFitnessBlogs = recyclerView4;
        this.rvFitnessChallenge = recyclerView5;
        this.rvGoals = recyclerView6;
        this.tvActivityHistory = textView;
        this.tvCultVideos = textView2;
        this.tvCultVideosDesc = textView3;
        this.tvFitnessBlogs = textView4;
        this.tvFitnessBlogsDesc = textView5;
        this.tvFitnessChallengeHeader = textView6;
        this.tvFitnessChallengeViewMore = textView7;
        this.tvFitnessJourney = textView8;
        this.tvFitnessPlan = textView9;
        this.tvGetStarted = textView10;
        this.tvGoalAcheived = textView11;
        this.tvGoalDesc = textView12;
        this.tvGoalName = textView13;
        this.tvGoalName1 = textView14;
        this.tvGoalStatus = textView15;
        this.tvGoalTarget = textView16;
        this.tvGoalTarget1 = textView17;
        this.tvGoalValue = textView18;
        this.tvGoalValue1 = textView19;
        this.tvGoalsAchieved = textView20;
        this.tvGoalsAchievedValue = textView21;
        this.tvGoalsInsights = textView22;
        this.tvGoalsInsightsDesc = textView23;
        this.tvInfo = textView24;
        this.tvNoActivities = textView25;
        this.tvOops = textView26;
        this.tvProgressValue = textView27;
        this.tvSelectedTypeValue = textView28;
        this.tvSleepInsights = textView29;
        this.tvStepsInsights = textView30;
        this.verticalGuidelineEnd = guideline;
        this.verticalGuidelineStart = guideline2;
        this.view = view2;
        this.viewFitnessChallengeDashboardHeader = roundedCardNavLayoutBinding;
    }

    public static FragmentFitnessBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentFitnessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFitnessBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentFitnessBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_fitness);
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentFitnessBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentFitnessBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentFitnessBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness, null, false, obj);
    }
}
