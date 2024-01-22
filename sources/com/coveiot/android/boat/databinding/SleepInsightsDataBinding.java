package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class SleepInsightsDataBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clAwakeTime;
    @NonNull
    public final ConstraintLayout clDeepSleep;
    @NonNull
    public final ConstraintLayout clTotalSleep;
    @NonNull
    public final TextView tvAwakeDesc;
    @NonNull
    public final TextView tvAwakePerc;
    @NonNull
    public final TextView tvAwakeTime;
    @NonNull
    public final TextView tvDeepDesc;
    @NonNull
    public final TextView tvDeepPerc;
    @NonNull
    public final TextView tvDeepTime;
    @NonNull
    public final TextView tvInsights;
    @NonNull
    public final TextView tvSleepDesc;
    @NonNull
    public final TextView tvSleepPerc;
    @NonNull
    public final TextView tvSleepTime;

    public SleepInsightsDataBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10) {
        super(obj, view, i);
        this.clAwakeTime = constraintLayout;
        this.clDeepSleep = constraintLayout2;
        this.clTotalSleep = constraintLayout3;
        this.tvAwakeDesc = textView;
        this.tvAwakePerc = textView2;
        this.tvAwakeTime = textView3;
        this.tvDeepDesc = textView4;
        this.tvDeepPerc = textView5;
        this.tvDeepTime = textView6;
        this.tvInsights = textView7;
        this.tvSleepDesc = textView8;
        this.tvSleepPerc = textView9;
        this.tvSleepTime = textView10;
    }

    public static SleepInsightsDataBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SleepInsightsDataBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SleepInsightsDataBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SleepInsightsDataBinding) ViewDataBinding.bind(obj, view, R.layout.sleep_insights_data);
    }

    @NonNull
    @Deprecated
    public static SleepInsightsDataBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SleepInsightsDataBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sleep_insights_data, viewGroup, z, obj);
    }

    @NonNull
    public static SleepInsightsDataBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SleepInsightsDataBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SleepInsightsDataBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sleep_insights_data, null, false, obj);
    }
}
