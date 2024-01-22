package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class TappayftuViewpagerLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivFtu;

    public TappayftuViewpagerLayoutBinding(Object obj, View view, int i, ImageView imageView) {
        super(obj, view, i);
        this.ivFtu = imageView;
    }

    public static TappayftuViewpagerLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TappayftuViewpagerLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TappayftuViewpagerLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (TappayftuViewpagerLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.tappayftu_viewpager_layout);
    }

    @NonNull
    @Deprecated
    public static TappayftuViewpagerLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (TappayftuViewpagerLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tappayftu_viewpager_layout, viewGroup, z, obj);
    }

    @NonNull
    public static TappayftuViewpagerLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static TappayftuViewpagerLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (TappayftuViewpagerLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tappayftu_viewpager_layout, null, false, obj);
    }
}
