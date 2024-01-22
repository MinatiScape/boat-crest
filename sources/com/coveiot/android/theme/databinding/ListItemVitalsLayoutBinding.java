package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ListItemVitalsLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivVitalImage;
    @NonNull
    public final ImageView ivVitalsBg;
    @NonNull
    public final TextView tvVitalName;

    public ListItemVitalsLayoutBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, TextView textView) {
        super(obj, view, i);
        this.ivVitalImage = imageView;
        this.ivVitalsBg = imageView2;
        this.tvVitalName = textView;
    }

    public static ListItemVitalsLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemVitalsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemVitalsLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemVitalsLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_vitals_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemVitalsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemVitalsLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_vitals_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemVitalsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemVitalsLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemVitalsLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_vitals_layout, null, false, obj);
    }
}
