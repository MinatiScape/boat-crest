package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class KeepGoingLayoutDashboardBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final ImageView ivIcon;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvStatus;

    public KeepGoingLayoutDashboardBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.clMain = constraintLayout;
        this.ivIcon = imageView;
        this.tvHeader = textView;
        this.tvStatus = textView2;
    }

    public static KeepGoingLayoutDashboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static KeepGoingLayoutDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KeepGoingLayoutDashboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (KeepGoingLayoutDashboardBinding) ViewDataBinding.bind(obj, view, R.layout.keep_going_layout_dashboard);
    }

    @NonNull
    @Deprecated
    public static KeepGoingLayoutDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (KeepGoingLayoutDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.keep_going_layout_dashboard, viewGroup, z, obj);
    }

    @NonNull
    public static KeepGoingLayoutDashboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static KeepGoingLayoutDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (KeepGoingLayoutDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.keep_going_layout_dashboard, null, false, obj);
    }
}
