package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.github.anastr.speedviewlib.SpeedView;
/* loaded from: classes3.dex */
public abstract class GridviewlayoutSsBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clClSsRunning;
    @NonNull
    public final ConstraintLayout clClSsStress;
    @NonNull
    public final ConstraintLayout clSsBadge;
    @NonNull
    public final ConstraintLayout clSsEmBadges;
    @NonNull
    public final ConstraintLayout clSsHrHrvSpo2;
    @NonNull
    public final ConstraintLayout clSsSleep;
    @NonNull
    public final CardView cvHrspo2hrv;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final Guideline guideline20;
    @NonNull
    public final ImageView icCalorieSmall;
    @NonNull
    public final ImageView icHeartSmall;
    @NonNull
    public final ImageView icTimer;
    @NonNull
    public final ImageView ivBadgeEarned;
    @NonNull
    public final ImageView ivBadgeSs;
    @NonNull
    public final ImageView ivRunning;
    @NonNull
    public final ImageView ivSleepFace;
    @NonNull
    public final ImageView ivSleepScore;
    @NonNull
    public final ImageView ivSs;
    @NonNull
    public final ImageView ivStress;
    @NonNull
    public final ImageView ivStressScore;
    @NonNull
    public final SpeedView speedViewHomeSs;
    @NonNull
    public final TextView tvBadgeDesc;
    @NonNull
    public final TextView tvBadgeName;
    @NonNull
    public final TextView tvCal;
    @NonNull
    public final TextView tvEnergyMeterName;
    @NonNull
    public final TextView tvEnergyMeterStatus;
    @NonNull
    public final TextView tvHeart;
    @NonNull
    public final TextView tvHeartMax;
    @NonNull
    public final TextView tvHeartMin;
    @NonNull
    public final TextView tvMaxRate;
    @NonNull
    public final TextView tvMinRate;
    @NonNull
    public final TextView tvRunningName;
    @NonNull
    public final TextView tvRunningTimer;
    @NonNull
    public final TextView tvSleepHourUnit;
    @NonNull
    public final TextView tvSleepMinuteUnit;
    @NonNull
    public final TextView tvSleepScoreHour;
    @NonNull
    public final TextView tvSleepScoreMinute;
    @NonNull
    public final TextView tvSleepScoreName;
    @NonNull
    public final TextView tvSleepScoreStatus;
    @NonNull
    public final TextView tvStressDesc;
    @NonNull
    public final TextView tvStressScoreName;
    @NonNull
    public final TextView tvStressScoreStatus;
    @NonNull
    public final TextView tvTitleName;

    public GridviewlayoutSsBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, CardView cardView, Guideline guideline, Guideline guideline2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, ImageView imageView11, SpeedView speedView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22) {
        super(obj, view, i);
        this.clClSsRunning = constraintLayout;
        this.clClSsStress = constraintLayout2;
        this.clSsBadge = constraintLayout3;
        this.clSsEmBadges = constraintLayout4;
        this.clSsHrHrvSpo2 = constraintLayout5;
        this.clSsSleep = constraintLayout6;
        this.cvHrspo2hrv = cardView;
        this.guideline1 = guideline;
        this.guideline20 = guideline2;
        this.icCalorieSmall = imageView;
        this.icHeartSmall = imageView2;
        this.icTimer = imageView3;
        this.ivBadgeEarned = imageView4;
        this.ivBadgeSs = imageView5;
        this.ivRunning = imageView6;
        this.ivSleepFace = imageView7;
        this.ivSleepScore = imageView8;
        this.ivSs = imageView9;
        this.ivStress = imageView10;
        this.ivStressScore = imageView11;
        this.speedViewHomeSs = speedView;
        this.tvBadgeDesc = textView;
        this.tvBadgeName = textView2;
        this.tvCal = textView3;
        this.tvEnergyMeterName = textView4;
        this.tvEnergyMeterStatus = textView5;
        this.tvHeart = textView6;
        this.tvHeartMax = textView7;
        this.tvHeartMin = textView8;
        this.tvMaxRate = textView9;
        this.tvMinRate = textView10;
        this.tvRunningName = textView11;
        this.tvRunningTimer = textView12;
        this.tvSleepHourUnit = textView13;
        this.tvSleepMinuteUnit = textView14;
        this.tvSleepScoreHour = textView15;
        this.tvSleepScoreMinute = textView16;
        this.tvSleepScoreName = textView17;
        this.tvSleepScoreStatus = textView18;
        this.tvStressDesc = textView19;
        this.tvStressScoreName = textView20;
        this.tvStressScoreStatus = textView21;
        this.tvTitleName = textView22;
    }

    public static GridviewlayoutSsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static GridviewlayoutSsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static GridviewlayoutSsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (GridviewlayoutSsBinding) ViewDataBinding.bind(obj, view, R.layout.gridviewlayout_ss);
    }

    @NonNull
    @Deprecated
    public static GridviewlayoutSsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (GridviewlayoutSsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.gridviewlayout_ss, viewGroup, z, obj);
    }

    @NonNull
    public static GridviewlayoutSsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static GridviewlayoutSsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (GridviewlayoutSsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.gridviewlayout_ss, null, false, obj);
    }
}
