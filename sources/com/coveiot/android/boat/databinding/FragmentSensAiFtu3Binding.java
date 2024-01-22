package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentSensAiFtu3Binding extends ViewDataBinding {
    @NonNull
    public final Button btnNext;
    @NonNull
    public final ConstraintLayout clAge;
    @NonNull
    public final ConstraintLayout clGender;
    @NonNull
    public final ConstraintLayout clHeight;
    @NonNull
    public final ConstraintLayout clProfileDetails;
    @NonNull
    public final ConstraintLayout clProfileDetails1;
    @NonNull
    public final ConstraintLayout clProfileEdit;
    @NonNull
    public final ConstraintLayout clWakeGesture;
    @NonNull
    public final ConstraintLayout clWeight;
    @NonNull
    public final ImageView ivInfo;
    @NonNull
    public final ImageView ivInfoIcon;
    @NonNull
    public final ImageView ivProfile;
    @NonNull
    public final TextView tvAge;
    @NonNull
    public final TextView tvAgeValue;
    @NonNull
    public final TextView tvEnableWakeGestureInfo;
    @NonNull
    public final TextView tvGender;
    @NonNull
    public final TextView tvGenderValue;
    @NonNull
    public final TextView tvHeight;
    @NonNull
    public final TextView tvHeightValue;
    @NonNull
    public final TextView tvProfile;
    @NonNull
    public final TextView tvWeight;
    @NonNull
    public final TextView tvWeightValue;

    public FragmentSensAiFtu3Binding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10) {
        super(obj, view, i);
        this.btnNext = button;
        this.clAge = constraintLayout;
        this.clGender = constraintLayout2;
        this.clHeight = constraintLayout3;
        this.clProfileDetails = constraintLayout4;
        this.clProfileDetails1 = constraintLayout5;
        this.clProfileEdit = constraintLayout6;
        this.clWakeGesture = constraintLayout7;
        this.clWeight = constraintLayout8;
        this.ivInfo = imageView;
        this.ivInfoIcon = imageView2;
        this.ivProfile = imageView3;
        this.tvAge = textView;
        this.tvAgeValue = textView2;
        this.tvEnableWakeGestureInfo = textView3;
        this.tvGender = textView4;
        this.tvGenderValue = textView5;
        this.tvHeight = textView6;
        this.tvHeightValue = textView7;
        this.tvProfile = textView8;
        this.tvWeight = textView9;
        this.tvWeightValue = textView10;
    }

    public static FragmentSensAiFtu3Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSensAiFtu3Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSensAiFtu3Binding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSensAiFtu3Binding) ViewDataBinding.bind(obj, view, R.layout.fragment_sens_ai_ftu_3);
    }

    @NonNull
    @Deprecated
    public static FragmentSensAiFtu3Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSensAiFtu3Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sens_ai_ftu_3, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSensAiFtu3Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSensAiFtu3Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSensAiFtu3Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sens_ai_ftu_3, null, false, obj);
    }
}
