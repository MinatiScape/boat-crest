package com.coveiot.android.weeklyreport.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.weeklyreport.R;
/* loaded from: classes8.dex */
public abstract class ActivityWeeklyReportCommonBinding extends ViewDataBinding {
    @NonNull
    public final RelativeLayout containerLayout;
    @NonNull
    public final View toolbar;

    public ActivityWeeklyReportCommonBinding(Object obj, View view, int i, RelativeLayout relativeLayout, View view2) {
        super(obj, view, i);
        this.containerLayout = relativeLayout;
        this.toolbar = view2;
    }

    public static ActivityWeeklyReportCommonBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityWeeklyReportCommonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWeeklyReportCommonBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityWeeklyReportCommonBinding) ViewDataBinding.bind(obj, view, R.layout.activity_weekly_report_common);
    }

    @NonNull
    @Deprecated
    public static ActivityWeeklyReportCommonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityWeeklyReportCommonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_weekly_report_common, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityWeeklyReportCommonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityWeeklyReportCommonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityWeeklyReportCommonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_weekly_report_common, null, false, obj);
    }
}
