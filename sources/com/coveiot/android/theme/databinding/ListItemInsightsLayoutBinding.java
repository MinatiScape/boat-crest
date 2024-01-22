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
public abstract class ListItemInsightsLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clInsight;
    @NonNull
    public final ImageView ivInsightDecreaseIncrease;
    @NonNull
    public final TextView tvInsightHeader;
    @NonNull
    public final TextView tvInsightInfo;
    @NonNull
    public final TextView tvInsightValue;

    public ListItemInsightsLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.clInsight = constraintLayout;
        this.ivInsightDecreaseIncrease = imageView;
        this.tvInsightHeader = textView;
        this.tvInsightInfo = textView2;
        this.tvInsightValue = textView3;
    }

    public static ListItemInsightsLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemInsightsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemInsightsLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemInsightsLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_insights_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemInsightsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemInsightsLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_insights_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemInsightsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemInsightsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemInsightsLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_insights_layout, null, false, obj);
    }
}
