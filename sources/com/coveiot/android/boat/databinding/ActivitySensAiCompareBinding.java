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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivitySensAiCompareBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clCompare;
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
    public final RecyclerView rvCompareList;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvCompareDate1;
    @NonNull
    public final TextView tvCompareDate2;
    @NonNull
    public final TextView tvCompareTitle1;
    @NonNull
    public final TextView tvCompareTitle2;
    @NonNull
    public final View viewShots;

    public ActivitySensAiCompareBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, Guideline guideline, Guideline guideline2, ImageView imageView, ImageView imageView2, RecyclerView recyclerView, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view3) {
        super(obj, view, i);
        this.clCompare = constraintLayout;
        this.clCompare1 = constraintLayout2;
        this.clCompare2 = constraintLayout3;
        this.guideline1 = guideline;
        this.guideline2 = guideline2;
        this.ivCompare1 = imageView;
        this.ivCompare2 = imageView2;
        this.rvCompareList = recyclerView;
        this.toolbar = view2;
        this.tvCompareDate1 = textView;
        this.tvCompareDate2 = textView2;
        this.tvCompareTitle1 = textView3;
        this.tvCompareTitle2 = textView4;
        this.viewShots = view3;
    }

    public static ActivitySensAiCompareBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySensAiCompareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySensAiCompareBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySensAiCompareBinding) ViewDataBinding.bind(obj, view, R.layout.activity_sens_ai_compare);
    }

    @NonNull
    @Deprecated
    public static ActivitySensAiCompareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySensAiCompareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sens_ai_compare, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySensAiCompareBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySensAiCompareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySensAiCompareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sens_ai_compare, null, false, obj);
    }
}
