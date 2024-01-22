package com.coveiot.android.activitymodes.databinding;

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
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.utils.UntouchableRecyclerView;
/* loaded from: classes2.dex */
public abstract class ActivityHistoryBeanLayoutBinding extends ViewDataBinding {
    @NonNull
    public final UntouchableRecyclerView activityBeanRecyclerView;
    @NonNull
    public final TextView activityDay;
    @NonNull
    public final ImageView activityIcon;
    @NonNull
    public final TextView activityLegendInfo;
    @NonNull
    public final TextView activityName;
    @NonNull
    public final ConstraintLayout clAutoActivity;
    @NonNull
    public final TextView dateText;
    @NonNull
    public final TextView timeText;
    @NonNull
    public final ConstraintLayout topCl;
    @NonNull
    public final TextView tvAutoActivity;
    @NonNull
    public final Guideline verticalGuideline;
    @NonNull
    public final Guideline verticalGuideline1;

    public ActivityHistoryBeanLayoutBinding(Object obj, View view, int i, UntouchableRecyclerView untouchableRecyclerView, TextView textView, ImageView imageView, TextView textView2, TextView textView3, ConstraintLayout constraintLayout, TextView textView4, TextView textView5, ConstraintLayout constraintLayout2, TextView textView6, Guideline guideline, Guideline guideline2) {
        super(obj, view, i);
        this.activityBeanRecyclerView = untouchableRecyclerView;
        this.activityDay = textView;
        this.activityIcon = imageView;
        this.activityLegendInfo = textView2;
        this.activityName = textView3;
        this.clAutoActivity = constraintLayout;
        this.dateText = textView4;
        this.timeText = textView5;
        this.topCl = constraintLayout2;
        this.tvAutoActivity = textView6;
        this.verticalGuideline = guideline;
        this.verticalGuideline1 = guideline2;
    }

    public static ActivityHistoryBeanLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityHistoryBeanLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHistoryBeanLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityHistoryBeanLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.activity_history_bean_layout);
    }

    @NonNull
    @Deprecated
    public static ActivityHistoryBeanLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityHistoryBeanLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_history_bean_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityHistoryBeanLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityHistoryBeanLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityHistoryBeanLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_history_bean_layout, null, false, obj);
    }
}
