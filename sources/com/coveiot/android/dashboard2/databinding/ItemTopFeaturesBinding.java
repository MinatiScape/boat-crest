package com.coveiot.android.dashboard2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.dashboard2.R;
import com.coveiot.coveaccess.smartgrid.model.GetSmartGridRes;
/* loaded from: classes4.dex */
public abstract class ItemTopFeaturesBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivTopFeatureIcon;
    @Bindable
    public GetSmartGridRes.GridItem mFeatureData;
    @NonNull
    public final TextView tvTag;
    @NonNull
    public final TextView tvTopFeaturesTitle;

    public ItemTopFeaturesBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ivTopFeatureIcon = imageView;
        this.tvTag = textView;
        this.tvTopFeaturesTitle = textView2;
    }

    public static ItemTopFeaturesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemTopFeaturesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public GetSmartGridRes.GridItem getFeatureData() {
        return this.mFeatureData;
    }

    public abstract void setFeatureData(@Nullable GetSmartGridRes.GridItem gridItem);

    @Deprecated
    public static ItemTopFeaturesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemTopFeaturesBinding) ViewDataBinding.bind(obj, view, R.layout.item_top_features);
    }

    @NonNull
    @Deprecated
    public static ItemTopFeaturesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemTopFeaturesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_top_features, viewGroup, z, obj);
    }

    @NonNull
    public static ItemTopFeaturesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemTopFeaturesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemTopFeaturesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_top_features, null, false, obj);
    }
}
