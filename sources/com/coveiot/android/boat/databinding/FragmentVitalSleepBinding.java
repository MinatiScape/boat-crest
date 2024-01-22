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
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.coveiot.android.theme.databinding.ListItemSleepStagesLayoutBinding;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.viewpagerindicator.CirclePageIndicator;
/* loaded from: classes3.dex */
public abstract class FragmentVitalSleepBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout aboutSleepStageConstraint;
    @NonNull
    public final TextView awake;
    @NonNull
    public final TextView awakeAtText;
    @NonNull
    public final TextView awakeAtValue;
    @NonNull
    public final ListItemSleepStagesLayoutBinding awakeSleepAbout;
    @NonNull
    public final ConstraintLayout awakeSleepAboutConstraint;
    @NonNull
    public final LinearLayout awakeSleepLayout;
    @NonNull
    public final CirclePageIndicator circlePageIndicatorFeedback;
    @NonNull
    public final ConstraintLayout clMaxRespiratoryRateSleep;
    @NonNull
    public final ConstraintLayout clMinRespiratoryRateSleep;
    @NonNull
    public final ConstraintLayout consistancySleeplayout;
    @NonNull
    public final ConstraintLayout consistencyLay;
    @NonNull
    public final TextView deepSleep;
    @NonNull
    public final ListItemSleepStagesLayoutBinding deepSleepAbout;
    @NonNull
    public final ConstraintLayout deepSleepAboutConstraint;
    @NonNull
    public final View divider;
    @NonNull
    public final HorizontalBarChart horizontalBarGraph;
    @NonNull
    public final ImageView imgvRespiratoryRateSleep;
    @NonNull
    public final TextView lightSleep;
    @NonNull
    public final ListItemSleepStagesLayoutBinding lightSleepAbout;
    @NonNull
    public final ConstraintLayout lightSleepAboutConstraint;
    @NonNull
    public final LinearLayout llRespiratoryMaxSleep;
    @NonNull
    public final LinearLayout llRespiratoryMinSleep;
    @NonNull
    public final NestedScrollView nestedScrollSleep;
    @NonNull
    public final ConstraintLayout nightlyBreathingRate;
    @NonNull
    public final TextView remSleep;
    @NonNull
    public final ListItemSleepStagesLayoutBinding remSleepAbout;
    @NonNull
    public final ConstraintLayout remSleepAboutConstraint;
    @NonNull
    public final LinearLayout remSleepLayout;
    @NonNull
    public final TextView sleepAtText;
    @NonNull
    public final TextView sleepAtValue;
    @NonNull
    public final LinearLayout sleepBottomLayout;
    @NonNull
    public final TextView sleepConsistency;
    @NonNull
    public final TextView sleepConsistencyValue;
    @NonNull
    public final ViewPager sleepFeedbackPager;
    @NonNull
    public final ConstraintLayout sleepGraphAndDetail;
    @NonNull
    public final ConstraintLayout sleepGraphBg;
    @NonNull
    public final RoundedBarChart sleepGraphChart;
    @NonNull
    public final SleepInsightsDataBinding sleepInsights;
    @NonNull
    public final ConstraintLayout sleepScoreConstraint;
    @NonNull
    public final ConstraintLayout sleepScoreLayout;
    @NonNull
    public final TextView sleepScoreTxt;
    @NonNull
    public final RecyclerView sleepTipsRecycler;
    @NonNull
    public final ImageView slpimg;
    @NonNull
    public final ConstraintLayout timeTodeepSleeplayout;
    @NonNull
    public final TextView timeTotalSleepTxt;
    @NonNull
    public final TextView timeTotalSleepValue;
    @NonNull
    public final TextView tipsSleepTitleTv;
    @NonNull
    public final TextView totalSleepTxt;
    @NonNull
    public final TextView totalSleepValue;
    @NonNull
    public final ConstraintLayout totalSleeplayout;
    @NonNull
    public final TextView tvAboutSleepStages;
    @NonNull
    public final TextView tvEditVitalNameGoal;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvNoSleepDataFound;
    @NonNull
    public final TextView tvRespiratoryRateMax;
    @NonNull
    public final TextView tvRespiratoryRateMaxUnit;
    @NonNull
    public final TextView tvRespiratoryRateMaxVal;
    @NonNull
    public final TextView tvRespiratoryRateMin;
    @NonNull
    public final TextView tvRespiratoryRateMinUnit;
    @NonNull
    public final TextView tvRespiratoryRateMinVal;
    @NonNull
    public final TextView tvRespiratoryRateSleep;
    @NonNull
    public final TextView tvSleepScore;
    @NonNull
    public final ImageView tvSleepScoreImg;
    @NonNull
    public final TextView tvsleepdescription;
    @NonNull
    public final View view2;
    @NonNull
    public final View view3;
    @NonNull
    public final LinearLayout viewPagerLayout;
    @NonNull
    public final VitalsDetailedLayoutBinding vitalsMainData;
    @NonNull
    public final TextView wasoTxt;
    @NonNull
    public final TextView wasoValue;
    @NonNull
    public final ConstraintLayout wasolayout;
    @NonNull
    public final ConstraintLayout yourSlpScore;

    public FragmentVitalSleepBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, ListItemSleepStagesLayoutBinding listItemSleepStagesLayoutBinding, ConstraintLayout constraintLayout2, LinearLayout linearLayout, CirclePageIndicator circlePageIndicator, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, TextView textView4, ListItemSleepStagesLayoutBinding listItemSleepStagesLayoutBinding2, ConstraintLayout constraintLayout7, View view2, HorizontalBarChart horizontalBarChart, ImageView imageView, TextView textView5, ListItemSleepStagesLayoutBinding listItemSleepStagesLayoutBinding3, ConstraintLayout constraintLayout8, LinearLayout linearLayout2, LinearLayout linearLayout3, NestedScrollView nestedScrollView, ConstraintLayout constraintLayout9, TextView textView6, ListItemSleepStagesLayoutBinding listItemSleepStagesLayoutBinding4, ConstraintLayout constraintLayout10, LinearLayout linearLayout4, TextView textView7, TextView textView8, LinearLayout linearLayout5, TextView textView9, TextView textView10, ViewPager viewPager, ConstraintLayout constraintLayout11, ConstraintLayout constraintLayout12, RoundedBarChart roundedBarChart, SleepInsightsDataBinding sleepInsightsDataBinding, ConstraintLayout constraintLayout13, ConstraintLayout constraintLayout14, TextView textView11, RecyclerView recyclerView, ImageView imageView2, ConstraintLayout constraintLayout15, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, ConstraintLayout constraintLayout16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, TextView textView26, TextView textView27, TextView textView28, ImageView imageView3, TextView textView29, View view3, View view4, LinearLayout linearLayout6, VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding, TextView textView30, TextView textView31, ConstraintLayout constraintLayout17, ConstraintLayout constraintLayout18) {
        super(obj, view, i);
        this.aboutSleepStageConstraint = constraintLayout;
        this.awake = textView;
        this.awakeAtText = textView2;
        this.awakeAtValue = textView3;
        this.awakeSleepAbout = listItemSleepStagesLayoutBinding;
        this.awakeSleepAboutConstraint = constraintLayout2;
        this.awakeSleepLayout = linearLayout;
        this.circlePageIndicatorFeedback = circlePageIndicator;
        this.clMaxRespiratoryRateSleep = constraintLayout3;
        this.clMinRespiratoryRateSleep = constraintLayout4;
        this.consistancySleeplayout = constraintLayout5;
        this.consistencyLay = constraintLayout6;
        this.deepSleep = textView4;
        this.deepSleepAbout = listItemSleepStagesLayoutBinding2;
        this.deepSleepAboutConstraint = constraintLayout7;
        this.divider = view2;
        this.horizontalBarGraph = horizontalBarChart;
        this.imgvRespiratoryRateSleep = imageView;
        this.lightSleep = textView5;
        this.lightSleepAbout = listItemSleepStagesLayoutBinding3;
        this.lightSleepAboutConstraint = constraintLayout8;
        this.llRespiratoryMaxSleep = linearLayout2;
        this.llRespiratoryMinSleep = linearLayout3;
        this.nestedScrollSleep = nestedScrollView;
        this.nightlyBreathingRate = constraintLayout9;
        this.remSleep = textView6;
        this.remSleepAbout = listItemSleepStagesLayoutBinding4;
        this.remSleepAboutConstraint = constraintLayout10;
        this.remSleepLayout = linearLayout4;
        this.sleepAtText = textView7;
        this.sleepAtValue = textView8;
        this.sleepBottomLayout = linearLayout5;
        this.sleepConsistency = textView9;
        this.sleepConsistencyValue = textView10;
        this.sleepFeedbackPager = viewPager;
        this.sleepGraphAndDetail = constraintLayout11;
        this.sleepGraphBg = constraintLayout12;
        this.sleepGraphChart = roundedBarChart;
        this.sleepInsights = sleepInsightsDataBinding;
        this.sleepScoreConstraint = constraintLayout13;
        this.sleepScoreLayout = constraintLayout14;
        this.sleepScoreTxt = textView11;
        this.sleepTipsRecycler = recyclerView;
        this.slpimg = imageView2;
        this.timeTodeepSleeplayout = constraintLayout15;
        this.timeTotalSleepTxt = textView12;
        this.timeTotalSleepValue = textView13;
        this.tipsSleepTitleTv = textView14;
        this.totalSleepTxt = textView15;
        this.totalSleepValue = textView16;
        this.totalSleeplayout = constraintLayout16;
        this.tvAboutSleepStages = textView17;
        this.tvEditVitalNameGoal = textView18;
        this.tvNoDataFound = textView19;
        this.tvNoSleepDataFound = textView20;
        this.tvRespiratoryRateMax = textView21;
        this.tvRespiratoryRateMaxUnit = textView22;
        this.tvRespiratoryRateMaxVal = textView23;
        this.tvRespiratoryRateMin = textView24;
        this.tvRespiratoryRateMinUnit = textView25;
        this.tvRespiratoryRateMinVal = textView26;
        this.tvRespiratoryRateSleep = textView27;
        this.tvSleepScore = textView28;
        this.tvSleepScoreImg = imageView3;
        this.tvsleepdescription = textView29;
        this.view2 = view3;
        this.view3 = view4;
        this.viewPagerLayout = linearLayout6;
        this.vitalsMainData = vitalsDetailedLayoutBinding;
        this.wasoTxt = textView30;
        this.wasoValue = textView31;
        this.wasolayout = constraintLayout17;
        this.yourSlpScore = constraintLayout18;
    }

    public static FragmentVitalSleepBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalSleepBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVitalSleepBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalSleepBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_vital_sleep);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalSleepBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalSleepBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_sleep, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalSleepBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalSleepBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalSleepBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_sleep, null, false, obj);
    }
}
