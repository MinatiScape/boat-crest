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
public abstract class LayoutBottomSheetAdapterBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivBottomItem;
    @NonNull
    public final TextView tvBottomItem;

    public LayoutBottomSheetAdapterBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.ivBottomItem = imageView;
        this.tvBottomItem = textView;
    }

    public static LayoutBottomSheetAdapterBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutBottomSheetAdapterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutBottomSheetAdapterBinding) ViewDataBinding.bind(obj, view, R.layout.layout_bottom_sheet_adapter);
    }

    @NonNull
    public static LayoutBottomSheetAdapterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutBottomSheetAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutBottomSheetAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutBottomSheetAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_bottom_sheet_adapter, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutBottomSheetAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutBottomSheetAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_bottom_sheet_adapter, null, false, obj);
    }
}
