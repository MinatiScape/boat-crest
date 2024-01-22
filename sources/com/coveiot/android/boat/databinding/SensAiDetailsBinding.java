package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class SensAiDetailsBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clDetails;
    @NonNull
    public final ConstraintLayout clDuration;
    @NonNull
    public final ConstraintLayout clGoalAcheived;
    @NonNull
    public final ConstraintLayout clOverallStatsDetails;
    @NonNull
    public final ConstraintLayout clSelectedOptionValues;
    @NonNull
    public final ConstraintLayout clSessionDetails;
    @NonNull
    public final ConstraintLayout clSessionTime;
    @NonNull
    public final ConstraintLayout clSessionsDuration;
    @NonNull
    public final ConstraintLayout clTotalSessions;
    @NonNull
    public final Guideline firstGuideline;
    @NonNull
    public final ImageButton ibShare;
    @NonNull
    public final ImageView ivCenterVitalBg;
    @NonNull
    public final ImageView ivSensAIBg;
    @NonNull
    public final Guideline secondGuideline;
    @NonNull
    public final TextView tvDuration;
    @NonNull
    public final TextView tvDurationValue;
    @NonNull
    public final TextView tvGoalAcheived;
    @NonNull
    public final TextView tvGoalAcheivedValue;
    @NonNull
    public final TextView tvSelectedTypeValue;
    @NonNull
    public final TextView tvSessionTime;
    @NonNull
    public final TextView tvSessionTimeValue;
    @NonNull
    public final TextView tvSessionsDuration;
    @NonNull
    public final TextView tvSessionsDurationValue;
    @NonNull
    public final TextView tvTitle;
    @NonNull
    public final TextView tvTotalSesionsValue;
    @NonNull
    public final TextView tvTotalSessions;
    @NonNull
    public final View view;
    @NonNull
    public final View view1;
    @NonNull
    public final View view2;

    public SensAiDetailsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, Guideline guideline, ImageButton imageButton, ImageView imageView, ImageView imageView2, Guideline guideline2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, View view2, View view3, View view4) {
        super(obj, view, i);
        this.clDetails = constraintLayout;
        this.clDuration = constraintLayout2;
        this.clGoalAcheived = constraintLayout3;
        this.clOverallStatsDetails = constraintLayout4;
        this.clSelectedOptionValues = constraintLayout5;
        this.clSessionDetails = constraintLayout6;
        this.clSessionTime = constraintLayout7;
        this.clSessionsDuration = constraintLayout8;
        this.clTotalSessions = constraintLayout9;
        this.firstGuideline = guideline;
        this.ibShare = imageButton;
        this.ivCenterVitalBg = imageView;
        this.ivSensAIBg = imageView2;
        this.secondGuideline = guideline2;
        this.tvDuration = textView;
        this.tvDurationValue = textView2;
        this.tvGoalAcheived = textView3;
        this.tvGoalAcheivedValue = textView4;
        this.tvSelectedTypeValue = textView5;
        this.tvSessionTime = textView6;
        this.tvSessionTimeValue = textView7;
        this.tvSessionsDuration = textView8;
        this.tvSessionsDurationValue = textView9;
        this.tvTitle = textView10;
        this.tvTotalSesionsValue = textView11;
        this.tvTotalSessions = textView12;
        this.view = view2;
        this.view1 = view3;
        this.view2 = view4;
    }

    public static SensAiDetailsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SensAiDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SensAiDetailsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SensAiDetailsBinding) ViewDataBinding.bind(obj, view, R.layout.sens_ai_details);
    }

    @NonNull
    @Deprecated
    public static SensAiDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SensAiDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_details, viewGroup, z, obj);
    }

    @NonNull
    public static SensAiDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SensAiDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SensAiDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_details, null, false, obj);
    }
}
