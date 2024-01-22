package com.coveiot.android.respiratoryrate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.respiratoryrate.R;
/* loaded from: classes6.dex */
public abstract class FragmentRespiratoryRateShareBinding extends ViewDataBinding {
    @NonNull
    public final TextView disclaimerInfo;
    @NonNull
    public final ConstraintLayout distanceLayout;
    @NonNull
    public final Guideline fiveGuideline;
    @NonNull
    public final Guideline fourGuideline;
    @NonNull
    public final Guideline glMiddle;
    @NonNull
    public final ImageView imgRespiratoryRateMax;
    @NonNull
    public final ImageView imgRespiratoryRateMin;
    @NonNull
    public final ImageView imgvRespiratoryRate;
    @NonNull
    public final ImageView ivAppLogo;
    @NonNull
    public final ImageView ivCenterVitalBg;
    @NonNull
    public final ImageView ivMaximum;
    @NonNull
    public final ImageView ivMinimum;
    @NonNull
    public final ImageView ivPoweredCove;
    @NonNull
    public final ConstraintLayout minLayout;
    @NonNull
    public final TextView nightlyTitle;
    @NonNull
    public final TextView nightlyValue;
    @NonNull
    public final ConstraintLayout rootLayout;
    @NonNull
    public final TextView tvMax;
    @NonNull
    public final TextView tvMin;
    @NonNull
    public final TextView tvRespiratoryRateMax;
    @NonNull
    public final TextView tvRespiratoryRateMin;
    @NonNull
    public final TextView userName;
    @NonNull
    public final ImageView userPic;
    @NonNull
    public final TextView week;

    public FragmentRespiratoryRateShareBinding(Object obj, View view, int i, TextView textView, ConstraintLayout constraintLayout, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ConstraintLayout constraintLayout2, TextView textView2, TextView textView3, ConstraintLayout constraintLayout3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, ImageView imageView9, TextView textView9) {
        super(obj, view, i);
        this.disclaimerInfo = textView;
        this.distanceLayout = constraintLayout;
        this.fiveGuideline = guideline;
        this.fourGuideline = guideline2;
        this.glMiddle = guideline3;
        this.imgRespiratoryRateMax = imageView;
        this.imgRespiratoryRateMin = imageView2;
        this.imgvRespiratoryRate = imageView3;
        this.ivAppLogo = imageView4;
        this.ivCenterVitalBg = imageView5;
        this.ivMaximum = imageView6;
        this.ivMinimum = imageView7;
        this.ivPoweredCove = imageView8;
        this.minLayout = constraintLayout2;
        this.nightlyTitle = textView2;
        this.nightlyValue = textView3;
        this.rootLayout = constraintLayout3;
        this.tvMax = textView4;
        this.tvMin = textView5;
        this.tvRespiratoryRateMax = textView6;
        this.tvRespiratoryRateMin = textView7;
        this.userName = textView8;
        this.userPic = imageView9;
        this.week = textView9;
    }

    public static FragmentRespiratoryRateShareBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentRespiratoryRateShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRespiratoryRateShareBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentRespiratoryRateShareBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_respiratory_rate_share);
    }

    @NonNull
    @Deprecated
    public static FragmentRespiratoryRateShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentRespiratoryRateShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_respiratory_rate_share, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentRespiratoryRateShareBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentRespiratoryRateShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentRespiratoryRateShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_respiratory_rate_share, null, false, obj);
    }
}
