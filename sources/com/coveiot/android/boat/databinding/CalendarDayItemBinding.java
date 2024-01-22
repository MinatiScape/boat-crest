package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class CalendarDayItemBinding extends ViewDataBinding {
    @NonNull
    public final TextView tabTitle;

    public CalendarDayItemBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.tabTitle = textView;
    }

    public static CalendarDayItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CalendarDayItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CalendarDayItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CalendarDayItemBinding) ViewDataBinding.bind(obj, view, R.layout.calendar_day_item);
    }

    @NonNull
    @Deprecated
    public static CalendarDayItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CalendarDayItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.calendar_day_item, viewGroup, z, obj);
    }

    @NonNull
    public static CalendarDayItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CalendarDayItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CalendarDayItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.calendar_day_item, null, false, obj);
    }
}
