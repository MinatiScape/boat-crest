package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivitySensaiFilterBinding extends ViewDataBinding {
    @NonNull
    public final Button btnApply;
    @NonNull
    public final TextView clearFilters;
    @NonNull
    public final ConstraintLayout constraintLayout;
    @NonNull
    public final FrameLayout fragmentContainer;
    @NonNull
    public final RecyclerView rcvFilterTypes;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvFiltersApplied;

    public ActivitySensaiFilterBinding(Object obj, View view, int i, Button button, TextView textView, ConstraintLayout constraintLayout, FrameLayout frameLayout, RecyclerView recyclerView, View view2, TextView textView2) {
        super(obj, view, i);
        this.btnApply = button;
        this.clearFilters = textView;
        this.constraintLayout = constraintLayout;
        this.fragmentContainer = frameLayout;
        this.rcvFilterTypes = recyclerView;
        this.toolbar = view2;
        this.tvFiltersApplied = textView2;
    }

    public static ActivitySensaiFilterBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySensaiFilterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySensaiFilterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySensaiFilterBinding) ViewDataBinding.bind(obj, view, R.layout.activity_sensai_filter);
    }

    @NonNull
    @Deprecated
    public static ActivitySensaiFilterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySensaiFilterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sensai_filter, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySensaiFilterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySensaiFilterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySensaiFilterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sensai_filter, null, false, obj);
    }
}
