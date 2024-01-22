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
public abstract class FragmentSessionInsightsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnCompare;
    @NonNull
    public final ConstraintLayout clBattingBowling;
    @NonNull
    public final ConstraintLayout clFilter;
    @NonNull
    public final ConstraintLayout clSession;
    @NonNull
    public final RecyclerView rvFilterList;
    @NonNull
    public final RecyclerView rvSessionInsightsList;
    @NonNull
    public final SensAiSortByDialogBinding sortByDialog;
    @NonNull
    public final TextView tvBatting;
    @NonNull
    public final TextView tvBowling;
    @NonNull
    public final TextView tvNoSessions;
    @NonNull
    public final TextView tvSortBy;

    public FragmentSessionInsightsBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, RecyclerView recyclerView, RecyclerView recyclerView2, SensAiSortByDialogBinding sensAiSortByDialogBinding, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.btnCompare = button;
        this.clBattingBowling = constraintLayout;
        this.clFilter = constraintLayout2;
        this.clSession = constraintLayout3;
        this.rvFilterList = recyclerView;
        this.rvSessionInsightsList = recyclerView2;
        this.sortByDialog = sensAiSortByDialogBinding;
        this.tvBatting = textView;
        this.tvBowling = textView2;
        this.tvNoSessions = textView3;
        this.tvSortBy = textView4;
    }

    public static FragmentSessionInsightsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSessionInsightsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSessionInsightsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSessionInsightsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_session_insights);
    }

    @NonNull
    @Deprecated
    public static FragmentSessionInsightsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSessionInsightsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_session_insights, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSessionInsightsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSessionInsightsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSessionInsightsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_session_insights, null, false, obj);
    }
}
