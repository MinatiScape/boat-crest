package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityFirstTimeUserBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout constraintLayoutBottom;
    @NonNull
    public final Guideline guideline26;
    @NonNull
    public final Guideline guidelineBottom;
    @NonNull
    public final ImageView imageViewDot1;
    @NonNull
    public final ImageView imageViewDot2;
    @NonNull
    public final ImageView imageViewDot3;
    @NonNull
    public final LinearLayout linearLayoutDots;
    @Bindable
    public Integer mFTUItemCount;
    @Bindable
    public Integer mSelectedItemPosition;
    @NonNull
    public final Button textViewSkip;
    @NonNull
    public final ViewPager viewPagerFirstTimeUser;

    public ActivityFirstTimeUserBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Guideline guideline, Guideline guideline2, ImageView imageView, ImageView imageView2, ImageView imageView3, LinearLayout linearLayout, Button button, ViewPager viewPager) {
        super(obj, view, i);
        this.constraintLayoutBottom = constraintLayout;
        this.guideline26 = guideline;
        this.guidelineBottom = guideline2;
        this.imageViewDot1 = imageView;
        this.imageViewDot2 = imageView2;
        this.imageViewDot3 = imageView3;
        this.linearLayoutDots = linearLayout;
        this.textViewSkip = button;
        this.viewPagerFirstTimeUser = viewPager;
    }

    public static ActivityFirstTimeUserBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityFirstTimeUserBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
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
    public static ActivityFirstTimeUserBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityFirstTimeUserBinding) ViewDataBinding.bind(obj, view, R.layout.activity_first_time_user);
    }

    @NonNull
    @Deprecated
    public static ActivityFirstTimeUserBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityFirstTimeUserBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_first_time_user, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityFirstTimeUserBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityFirstTimeUserBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityFirstTimeUserBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_first_time_user, null, false, obj);
    }
}
