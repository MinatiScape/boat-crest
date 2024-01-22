package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentTroubleshoootingFTUBinding extends ViewDataBinding {
    @NonNull
    public final ImageView imageViewDot1;
    @NonNull
    public final ImageView imageViewDot2;
    @NonNull
    public final ImageView imageViewDot3;
    @Bindable
    public Integer mFTUItemCount;
    @Bindable
    public Integer mSelectedItemPosition;
    @NonNull
    public final ConstraintLayout pageIndicatorLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final ViewPager viewPager;

    public FragmentTroubleshoootingFTUBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, ConstraintLayout constraintLayout, View view2, ViewPager viewPager) {
        super(obj, view, i);
        this.imageViewDot1 = imageView;
        this.imageViewDot2 = imageView2;
        this.imageViewDot3 = imageView3;
        this.pageIndicatorLayout = constraintLayout;
        this.toolbar = view2;
        this.viewPager = viewPager;
    }

    public static FragmentTroubleshoootingFTUBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentTroubleshoootingFTUBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
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
    public static FragmentTroubleshoootingFTUBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentTroubleshoootingFTUBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_troubleshoooting_f_t_u);
    }

    @NonNull
    @Deprecated
    public static FragmentTroubleshoootingFTUBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentTroubleshoootingFTUBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_troubleshoooting_f_t_u, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentTroubleshoootingFTUBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentTroubleshoootingFTUBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentTroubleshoootingFTUBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_troubleshoooting_f_t_u, null, false, obj);
    }
}
