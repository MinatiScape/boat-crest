package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class TroubleshootingftuViewpagerLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ftuImg;
    @NonNull
    public final TextView troubleshootDescTv;
    @NonNull
    public final TextView troubleshootTitleTv;

    public TroubleshootingftuViewpagerLayoutBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ftuImg = imageView;
        this.troubleshootDescTv = textView;
        this.troubleshootTitleTv = textView2;
    }

    public static TroubleshootingftuViewpagerLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TroubleshootingftuViewpagerLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TroubleshootingftuViewpagerLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (TroubleshootingftuViewpagerLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.troubleshootingftu_viewpager_layout);
    }

    @NonNull
    @Deprecated
    public static TroubleshootingftuViewpagerLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (TroubleshootingftuViewpagerLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.troubleshootingftu_viewpager_layout, viewGroup, z, obj);
    }

    @NonNull
    public static TroubleshootingftuViewpagerLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static TroubleshootingftuViewpagerLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (TroubleshootingftuViewpagerLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.troubleshootingftu_viewpager_layout, null, false, obj);
    }
}
