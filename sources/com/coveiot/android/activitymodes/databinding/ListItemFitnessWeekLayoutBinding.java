package com.coveiot.android.activitymodes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.activitymodes.R;
/* loaded from: classes2.dex */
public abstract class ListItemFitnessWeekLayoutBinding extends ViewDataBinding {
    @Bindable
    public String mWeekCount;
    @NonNull
    public final TextView tvWeek;

    public ListItemFitnessWeekLayoutBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.tvWeek = textView;
    }

    public static ListItemFitnessWeekLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemFitnessWeekLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public String getWeekCount() {
        return this.mWeekCount;
    }

    public abstract void setWeekCount(@Nullable String str);

    @Deprecated
    public static ListItemFitnessWeekLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemFitnessWeekLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_fitness_week_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemFitnessWeekLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemFitnessWeekLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_fitness_week_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemFitnessWeekLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemFitnessWeekLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemFitnessWeekLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_fitness_week_layout, null, false, obj);
    }
}
