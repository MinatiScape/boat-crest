package com.coveiot.android.activitymodes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.activitymodes.R;
/* loaded from: classes2.dex */
public abstract class ListItemFitnessDaysLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clDay;
    @Bindable
    public String mDayCount;
    @NonNull
    public final TextView tvDay;
    @NonNull
    public final TextView tvDayCount;

    public ListItemFitnessDaysLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.clDay = constraintLayout;
        this.tvDay = textView;
        this.tvDayCount = textView2;
    }

    public static ListItemFitnessDaysLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemFitnessDaysLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public String getDayCount() {
        return this.mDayCount;
    }

    public abstract void setDayCount(@Nullable String str);

    @Deprecated
    public static ListItemFitnessDaysLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemFitnessDaysLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_fitness_days_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemFitnessDaysLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemFitnessDaysLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_fitness_days_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemFitnessDaysLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemFitnessDaysLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemFitnessDaysLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_fitness_days_layout, null, false, obj);
    }
}
