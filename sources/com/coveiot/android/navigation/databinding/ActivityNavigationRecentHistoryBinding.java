package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class ActivityNavigationRecentHistoryBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clRecentHistory;
    @NonNull
    public final RecyclerView rvRecentHistory;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvClearAllRecentHistory;
    @NonNull
    public final TextView tvNoRecentHistory;
    @NonNull
    public final TextView tvYourSearches;

    public ActivityNavigationRecentHistoryBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, RecyclerView recyclerView, View view2, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.clRecentHistory = constraintLayout;
        this.rvRecentHistory = recyclerView;
        this.toolbar = view2;
        this.tvClearAllRecentHistory = textView;
        this.tvNoRecentHistory = textView2;
        this.tvYourSearches = textView3;
    }

    public static ActivityNavigationRecentHistoryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityNavigationRecentHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNavigationRecentHistoryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityNavigationRecentHistoryBinding) ViewDataBinding.bind(obj, view, R.layout.activity_navigation_recent_history);
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationRecentHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityNavigationRecentHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_recent_history, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityNavigationRecentHistoryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationRecentHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityNavigationRecentHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_recent_history, null, false, obj);
    }
}
