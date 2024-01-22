package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ReminderLayoutNewBinding extends ViewDataBinding {
    @NonNull
    public final TextView alertIntervalTitle;
    @NonNull
    public final LinearLayout clAlertIntervals;
    @NonNull
    public final ConstraintLayout clEndTime;
    @NonNull
    public final ConstraintLayout clStartTime;
    @NonNull
    public final TextView endTimeTitle;
    @NonNull
    public final RecyclerView rcvInterval;
    @NonNull
    public final TextView startTimeTitle;
    @NonNull
    public final TextView tvEndTime;
    @NonNull
    public final TextView tvStartTime;

    public ReminderLayoutNewBinding(Object obj, View view, int i, TextView textView, LinearLayout linearLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView2, RecyclerView recyclerView, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.alertIntervalTitle = textView;
        this.clAlertIntervals = linearLayout;
        this.clEndTime = constraintLayout;
        this.clStartTime = constraintLayout2;
        this.endTimeTitle = textView2;
        this.rcvInterval = recyclerView;
        this.startTimeTitle = textView3;
        this.tvEndTime = textView4;
        this.tvStartTime = textView5;
    }

    public static ReminderLayoutNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ReminderLayoutNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ReminderLayoutNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ReminderLayoutNewBinding) ViewDataBinding.bind(obj, view, R.layout.reminder_layout_new);
    }

    @NonNull
    @Deprecated
    public static ReminderLayoutNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ReminderLayoutNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.reminder_layout_new, viewGroup, z, obj);
    }

    @NonNull
    public static ReminderLayoutNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ReminderLayoutNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ReminderLayoutNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.reminder_layout_new, null, false, obj);
    }
}
