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
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityOnboardingFtuBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout FTULinearLayoutDots;
    @NonNull
    public final Button btnGetStarted;
    @NonNull
    public final ImageView dot1;
    @NonNull
    public final ImageView dot2;
    @NonNull
    public final ImageView dot3;
    @NonNull
    public final ImageView dot4;
    @NonNull
    public final ImageView dot5;
    @NonNull
    public final Guideline guidelineBottom;
    @NonNull
    public final Guideline guidelineTop;
    @Bindable
    public Integer mFTUItemCount;
    @Bindable
    public Integer mSelectedItemPosition;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvFtuInfo;
    @NonNull
    public final ViewPager2 vpOnboardingFTU;

    public ActivityOnboardingFtuBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Button button, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, Guideline guideline, Guideline guideline2, View view2, TextView textView, ViewPager2 viewPager2) {
        super(obj, view, i);
        this.FTULinearLayoutDots = constraintLayout;
        this.btnGetStarted = button;
        this.dot1 = imageView;
        this.dot2 = imageView2;
        this.dot3 = imageView3;
        this.dot4 = imageView4;
        this.dot5 = imageView5;
        this.guidelineBottom = guideline;
        this.guidelineTop = guideline2;
        this.toolbar = view2;
        this.tvFtuInfo = textView;
        this.vpOnboardingFTU = viewPager2;
    }

    public static ActivityOnboardingFtuBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityOnboardingFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Integer getFTUItemCount() {
        return this.mFTUItemCount;
    }

    @Nullable
    public Integer getSelectedItemPosition() {
        return this.mSelectedItemPosition;
    }

    public abstract void setFTUItemCount(@Nullable Integer num);

    public abstract void setSelectedItemPosition(@Nullable Integer num);

    @Deprecated
    public static ActivityOnboardingFtuBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityOnboardingFtuBinding) ViewDataBinding.bind(obj, view, R.layout.activity_onboarding_ftu);
    }

    @NonNull
    @Deprecated
    public static ActivityOnboardingFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityOnboardingFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_onboarding_ftu, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityOnboardingFtuBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityOnboardingFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityOnboardingFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_onboarding_ftu, null, false, obj);
    }
}
