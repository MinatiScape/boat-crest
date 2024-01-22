package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewNew;
/* loaded from: classes7.dex */
public abstract class DialogCalendarRangeDateBinding extends ViewDataBinding {
    @NonNull
    public final DateRangeCalendarViewNew calendar;
    @NonNull
    public final TextView cancelPopup;
    @NonNull
    public final TextView okPopup;

    public DialogCalendarRangeDateBinding(Object obj, View view, int i, DateRangeCalendarViewNew dateRangeCalendarViewNew, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.calendar = dateRangeCalendarViewNew;
        this.cancelPopup = textView;
        this.okPopup = textView2;
    }

    public static DialogCalendarRangeDateBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DialogCalendarRangeDateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCalendarRangeDateBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DialogCalendarRangeDateBinding) ViewDataBinding.bind(obj, view, R.layout.dialog_calendar_range_date);
    }

    @NonNull
    @Deprecated
    public static DialogCalendarRangeDateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (DialogCalendarRangeDateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_calendar_range_date, viewGroup, z, obj);
    }

    @NonNull
    public static DialogCalendarRangeDateBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DialogCalendarRangeDateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (DialogCalendarRangeDateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_calendar_range_date, null, false, obj);
    }
}
