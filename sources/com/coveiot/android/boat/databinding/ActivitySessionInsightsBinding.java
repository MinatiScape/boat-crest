package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivitySessionInsightsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnCompare;
    @NonNull
    public final TextView btnFilter;
    @NonNull
    public final TextView btnReset;
    @NonNull
    public final ConstraintLayout clFilter;
    @NonNull
    public final ConstraintLayout clSession;
    @NonNull
    public final RecyclerView rvSessionInsightsList;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvFiltersApplied;
    @NonNull
    public final TextView tvNoSessions;

    public ActivitySessionInsightsBinding(Object obj, View view, int i, Button button, TextView textView, TextView textView2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, RecyclerView recyclerView, View view2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.btnCompare = button;
        this.btnFilter = textView;
        this.btnReset = textView2;
        this.clFilter = constraintLayout;
        this.clSession = constraintLayout2;
        this.rvSessionInsightsList = recyclerView;
        this.toolbar = view2;
        this.tvFiltersApplied = textView3;
        this.tvNoSessions = textView4;
    }

    public static ActivitySessionInsightsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySessionInsightsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySessionInsightsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySessionInsightsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_session_insights);
    }

    @NonNull
    @Deprecated
    public static ActivitySessionInsightsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySessionInsightsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_session_insights, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySessionInsightsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySessionInsightsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySessionInsightsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_session_insights, null, false, obj);
    }
}
