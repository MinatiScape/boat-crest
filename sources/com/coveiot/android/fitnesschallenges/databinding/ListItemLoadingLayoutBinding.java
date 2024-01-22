package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class ListItemLoadingLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ProgressBar progressBar;

    public ListItemLoadingLayoutBinding(Object obj, View view, int i, ProgressBar progressBar) {
        super(obj, view, i);
        this.progressBar = progressBar;
    }

    public static ListItemLoadingLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemLoadingLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemLoadingLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemLoadingLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_loading_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemLoadingLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemLoadingLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_loading_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemLoadingLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemLoadingLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemLoadingLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_loading_layout, null, false, obj);
    }
}
