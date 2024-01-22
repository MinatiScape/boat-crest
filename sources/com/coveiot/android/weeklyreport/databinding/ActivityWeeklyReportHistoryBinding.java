package com.coveiot.android.weeklyreport.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.weeklyreport.R;
/* loaded from: classes8.dex */
public abstract class ActivityWeeklyReportHistoryBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout historyViewContainer;
    @NonNull
    public final ImageView ivEmtyHistory;
    @NonNull
    public final RecyclerView rvFitnessReport;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvEmpty;
    @NonNull
    public final TextView tvMsg;

    public ActivityWeeklyReportHistoryBinding(Object obj, View view, int i, FrameLayout frameLayout, ImageView imageView, RecyclerView recyclerView, View view2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.historyViewContainer = frameLayout;
        this.ivEmtyHistory = imageView;
        this.rvFitnessReport = recyclerView;
        this.toolbar = view2;
        this.tvEmpty = textView;
        this.tvMsg = textView2;
    }

    public static ActivityWeeklyReportHistoryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityWeeklyReportHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWeeklyReportHistoryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityWeeklyReportHistoryBinding) ViewDataBinding.bind(obj, view, R.layout.activity_weekly_report_history);
    }

    @NonNull
    @Deprecated
    public static ActivityWeeklyReportHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityWeeklyReportHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_weekly_report_history, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityWeeklyReportHistoryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityWeeklyReportHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityWeeklyReportHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_weekly_report_history, null, false, obj);
    }
}
