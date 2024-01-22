package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class LayoutDashboardDoMoreWithYourWatchGridItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout cardBackground;
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final ImageView ivIcon;
    @NonNull
    public final ImageView ivIconBackground;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final View view;

    public LayoutDashboardDoMoreWithYourWatchGridItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, Guideline guideline, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.cardBackground = constraintLayout;
        this.clMain = constraintLayout2;
        this.guideline1 = guideline;
        this.ivIcon = imageView;
        this.ivIconBackground = imageView2;
        this.tvHeader = textView;
        this.tvInfo = textView2;
        this.view = view2;
    }

    public static LayoutDashboardDoMoreWithYourWatchGridItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutDashboardDoMoreWithYourWatchGridItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDashboardDoMoreWithYourWatchGridItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutDashboardDoMoreWithYourWatchGridItemBinding) ViewDataBinding.bind(obj, view, R.layout.layout_dashboard_do_more_with_your_watch_grid_item);
    }

    @NonNull
    @Deprecated
    public static LayoutDashboardDoMoreWithYourWatchGridItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutDashboardDoMoreWithYourWatchGridItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_dashboard_do_more_with_your_watch_grid_item, viewGroup, z, obj);
    }

    @NonNull
    public static LayoutDashboardDoMoreWithYourWatchGridItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutDashboardDoMoreWithYourWatchGridItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutDashboardDoMoreWithYourWatchGridItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_dashboard_do_more_with_your_watch_grid_item, null, false, obj);
    }
}
