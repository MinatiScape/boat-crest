package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class ItemLayoutRecentHistoryBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivItemRecentHistoryLocationIcon;
    @NonNull
    public final ImageView ivItemRecentHistoryRightArrowIcon;
    @NonNull
    public final TextView tvRecentHistoryAddressLine1;
    @NonNull
    public final TextView tvRecentHistoryAddressLine2;

    public ItemLayoutRecentHistoryBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ivItemRecentHistoryLocationIcon = imageView;
        this.ivItemRecentHistoryRightArrowIcon = imageView2;
        this.tvRecentHistoryAddressLine1 = textView;
        this.tvRecentHistoryAddressLine2 = textView2;
    }

    public static ItemLayoutRecentHistoryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemLayoutRecentHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLayoutRecentHistoryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemLayoutRecentHistoryBinding) ViewDataBinding.bind(obj, view, R.layout.item_layout_recent_history);
    }

    @NonNull
    @Deprecated
    public static ItemLayoutRecentHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemLayoutRecentHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_layout_recent_history, viewGroup, z, obj);
    }

    @NonNull
    public static ItemLayoutRecentHistoryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemLayoutRecentHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemLayoutRecentHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_layout_recent_history, null, false, obj);
    }
}
