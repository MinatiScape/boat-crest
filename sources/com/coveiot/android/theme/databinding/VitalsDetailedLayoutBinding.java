package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class VitalsDetailedLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clCenterDataAVGStepSleep;
    @NonNull
    public final ConstraintLayout clCenterDataEnergyAndStress;
    @NonNull
    public final ConstraintLayout clCenterDataHRVSpo2;
    @NonNull
    public final ConstraintLayout clCenterDataStepsSleep;
    @NonNull
    public final ConstraintLayout clMainVitals;
    @NonNull
    public final ConstraintLayout clMaxData;
    @NonNull
    public final ConstraintLayout clMinData;
    @NonNull
    public final ConstraintLayout clMinMaxData;
    @NonNull
    public final ConstraintLayout clProgress;
    @NonNull
    public final ConstraintLayout clSelectedOptionValues;
    @NonNull
    public final ConstraintLayout clStepsSleepsTotalAvg;
    @NonNull
    public final ConstraintLayout clTopSelector;
    @NonNull
    public final ConstraintLayout clVitalsData;
    @NonNull
    public final ImageButton ibForward;
    @NonNull
    public final ImageButton ibPrevious;
    @NonNull
    public final ImageButton ibShareVitals;
    @NonNull
    public final ImageView ivCenterVital;
    @NonNull
    public final ImageView ivCenterVitalBg;
    @NonNull
    public final ImageView ivMax;
    @NonNull
    public final ImageView ivMaxBg;
    @NonNull
    public final ImageView ivMin;
    @NonNull
    public final ImageView ivMinBg;
    @NonNull
    public final ImageView ivVitalsBg;
    @NonNull
    public final ProgressBar progressBar;
    @NonNull
    public final TextView tvAvgHRVSpo2Value;
    @NonNull
    public final TextView tvAvgHRVSpo2ValueUnit;
    @NonNull
    public final TextView tvAvgStepSleepTitle;
    @NonNull
    public final TextView tvAvgStepSleepValue;
    @NonNull
    public final TextView tvAvgStepSleepValueUnit;
    @NonNull
    public final TextView tvAvgStepsSleepValue;
    @NonNull
    public final TextView tvAvgType;
    @NonNull
    public final TextView tvAvgValueLevel;
    @NonNull
    public final TextView tvAvgVitalValue;
    @NonNull
    public final TextView tvDay;
    @NonNull
    public final TextView tvLastSyncTime;
    @NonNull
    public final TextView tvMonth;
    @NonNull
    public final TextView tvOutOfStepsSleepValue;
    @NonNull
    public final TextView tvSelectedTypeValue;
    @NonNull
    public final TextView tvStepSleepTotalValue;
    @NonNull
    public final TextView tvVitalInfo;
    @NonNull
    public final TextView tvVitalMax;
    @NonNull
    public final TextView tvVitalMaxValue;
    @NonNull
    public final TextView tvVitalMin;
    @NonNull
    public final TextView tvVitalMinValue;
    @NonNull
    public final TextView tvVitalName;
    @NonNull
    public final TextView tvWeek;
    @NonNull
    public final View view;

    public VitalsDetailedLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ConstraintLayout constraintLayout12, ConstraintLayout constraintLayout13, ImageButton imageButton, ImageButton imageButton2, ImageButton imageButton3, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ProgressBar progressBar, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, View view2) {
        super(obj, view, i);
        this.clCenterDataAVGStepSleep = constraintLayout;
        this.clCenterDataEnergyAndStress = constraintLayout2;
        this.clCenterDataHRVSpo2 = constraintLayout3;
        this.clCenterDataStepsSleep = constraintLayout4;
        this.clMainVitals = constraintLayout5;
        this.clMaxData = constraintLayout6;
        this.clMinData = constraintLayout7;
        this.clMinMaxData = constraintLayout8;
        this.clProgress = constraintLayout9;
        this.clSelectedOptionValues = constraintLayout10;
        this.clStepsSleepsTotalAvg = constraintLayout11;
        this.clTopSelector = constraintLayout12;
        this.clVitalsData = constraintLayout13;
        this.ibForward = imageButton;
        this.ibPrevious = imageButton2;
        this.ibShareVitals = imageButton3;
        this.ivCenterVital = imageView;
        this.ivCenterVitalBg = imageView2;
        this.ivMax = imageView3;
        this.ivMaxBg = imageView4;
        this.ivMin = imageView5;
        this.ivMinBg = imageView6;
        this.ivVitalsBg = imageView7;
        this.progressBar = progressBar;
        this.tvAvgHRVSpo2Value = textView;
        this.tvAvgHRVSpo2ValueUnit = textView2;
        this.tvAvgStepSleepTitle = textView3;
        this.tvAvgStepSleepValue = textView4;
        this.tvAvgStepSleepValueUnit = textView5;
        this.tvAvgStepsSleepValue = textView6;
        this.tvAvgType = textView7;
        this.tvAvgValueLevel = textView8;
        this.tvAvgVitalValue = textView9;
        this.tvDay = textView10;
        this.tvLastSyncTime = textView11;
        this.tvMonth = textView12;
        this.tvOutOfStepsSleepValue = textView13;
        this.tvSelectedTypeValue = textView14;
        this.tvStepSleepTotalValue = textView15;
        this.tvVitalInfo = textView16;
        this.tvVitalMax = textView17;
        this.tvVitalMaxValue = textView18;
        this.tvVitalMin = textView19;
        this.tvVitalMinValue = textView20;
        this.tvVitalName = textView21;
        this.tvWeek = textView22;
        this.view = view2;
    }

    public static VitalsDetailedLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static VitalsDetailedLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VitalsDetailedLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (VitalsDetailedLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.vitals_detailed_layout);
    }

    @NonNull
    @Deprecated
    public static VitalsDetailedLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (VitalsDetailedLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.vitals_detailed_layout, viewGroup, z, obj);
    }

    @NonNull
    public static VitalsDetailedLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static VitalsDetailedLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (VitalsDetailedLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.vitals_detailed_layout, null, false, obj);
    }
}
