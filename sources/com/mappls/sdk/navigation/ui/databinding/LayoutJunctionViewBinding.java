package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public abstract class LayoutJunctionViewBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivJunctionImage;
    @NonNull
    public final TextView tvJunctionLeftDistance;

    public LayoutJunctionViewBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.ivJunctionImage = imageView;
        this.tvJunctionLeftDistance = textView;
    }

    public static LayoutJunctionViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutJunctionViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutJunctionViewBinding) ViewDataBinding.bind(obj, view, R.layout.layout_junction_view);
    }

    @NonNull
    public static LayoutJunctionViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutJunctionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutJunctionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutJunctionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_junction_view, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutJunctionViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutJunctionViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_junction_view, null, false, obj);
    }
}
