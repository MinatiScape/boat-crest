package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.airbnb.lottie.LottieAnimationView;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ActivityStrideLengthAnimationBinding extends ViewDataBinding {
    @NonNull
    public final LottieAnimationView animationView;
    @NonNull
    public final Button btnCalculate;
    @NonNull
    public final TextView calculate;
    @NonNull
    public final ConstraintLayout clDistance;
    @NonNull
    public final ConstraintLayout clLottie;
    @NonNull
    public final ConstraintLayout clSteps;
    @NonNull
    public final TextView cm;
    @NonNull
    public final TextView distanceBetweenAB;
    @NonNull
    public final EditText etDistance;
    @NonNull
    public final EditText etSteps;
    @NonNull
    public final ImageView ivClose;
    @NonNull
    public final ImageView ivStep1;
    @NonNull
    public final ImageView ivStep2;
    @NonNull
    public final ImageView ivVideo;
    @NonNull
    public final NestedScrollView nestedScrollView;
    @NonNull
    public final TextView noSteps;
    @NonNull
    public final TextView step1;
    @NonNull
    public final TextView step1Info;
    @NonNull
    public final TextView step2;
    @NonNull
    public final TextView step2Info;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvCalculateInfo;

    public ActivityStrideLengthAnimationBinding(Object obj, View view, int i, LottieAnimationView lottieAnimationView, Button button, TextView textView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, TextView textView2, TextView textView3, EditText editText, EditText editText2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, NestedScrollView nestedScrollView, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, View view2, TextView textView9) {
        super(obj, view, i);
        this.animationView = lottieAnimationView;
        this.btnCalculate = button;
        this.calculate = textView;
        this.clDistance = constraintLayout;
        this.clLottie = constraintLayout2;
        this.clSteps = constraintLayout3;
        this.cm = textView2;
        this.distanceBetweenAB = textView3;
        this.etDistance = editText;
        this.etSteps = editText2;
        this.ivClose = imageView;
        this.ivStep1 = imageView2;
        this.ivStep2 = imageView3;
        this.ivVideo = imageView4;
        this.nestedScrollView = nestedScrollView;
        this.noSteps = textView4;
        this.step1 = textView5;
        this.step1Info = textView6;
        this.step2 = textView7;
        this.step2Info = textView8;
        this.toolbar = view2;
        this.tvCalculateInfo = textView9;
    }

    public static ActivityStrideLengthAnimationBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityStrideLengthAnimationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityStrideLengthAnimationBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityStrideLengthAnimationBinding) ViewDataBinding.bind(obj, view, R.layout.activity_stride_length_animation);
    }

    @NonNull
    @Deprecated
    public static ActivityStrideLengthAnimationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityStrideLengthAnimationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_stride_length_animation, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityStrideLengthAnimationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityStrideLengthAnimationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityStrideLengthAnimationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_stride_length_animation, null, false, obj);
    }
}
