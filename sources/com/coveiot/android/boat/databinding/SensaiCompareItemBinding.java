package com.coveiot.android.boat.databinding;

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
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class SensaiCompareItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clCompare1;
    @NonNull
    public final ConstraintLayout clCompare2;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final Guideline guideline2;
    @NonNull
    public final ImageView ivCompare1;
    @NonNull
    public final ImageView ivCompare2;
    @NonNull
    public final TextView tvCompareDetails1;
    @NonNull
    public final TextView tvCompareDetails2;
    @NonNull
    public final TextView tvCompareTitle1;
    @NonNull
    public final TextView tvCompareTitle2;
    @NonNull
    public final View view6;

    public SensaiCompareItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, Guideline guideline, Guideline guideline2, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view2) {
        super(obj, view, i);
        this.clCompare1 = constraintLayout;
        this.clCompare2 = constraintLayout2;
        this.guideline1 = guideline;
        this.guideline2 = guideline2;
        this.ivCompare1 = imageView;
        this.ivCompare2 = imageView2;
        this.tvCompareDetails1 = textView;
        this.tvCompareDetails2 = textView2;
        this.tvCompareTitle1 = textView3;
        this.tvCompareTitle2 = textView4;
        this.view6 = view2;
    }

    public static SensaiCompareItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SensaiCompareItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SensaiCompareItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SensaiCompareItemBinding) ViewDataBinding.bind(obj, view, R.layout.sensai_compare_item);
    }

    @NonNull
    @Deprecated
    public static SensaiCompareItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SensaiCompareItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sensai_compare_item, viewGroup, z, obj);
    }

    @NonNull
    public static SensaiCompareItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SensaiCompareItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SensaiCompareItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sensai_compare_item, null, false, obj);
    }
}
