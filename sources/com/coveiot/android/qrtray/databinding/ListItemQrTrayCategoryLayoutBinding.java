package com.coveiot.android.qrtray.databinding;

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
import com.coveiot.android.qrtray.R;
import com.coveiot.coveaccess.qrtray.model.QRTrayCategoriesRes;
/* loaded from: classes5.dex */
public abstract class ListItemQrTrayCategoryLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivCategory;
    @Bindable
    public QRTrayCategoriesRes.QRItem mQrCategoryData;
    @NonNull
    public final TextView tvCategory;

    public ListItemQrTrayCategoryLayoutBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.ivCategory = imageView;
        this.tvCategory = textView;
    }

    public static ListItemQrTrayCategoryLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemQrTrayCategoryLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public QRTrayCategoriesRes.QRItem getQrCategoryData() {
        return this.mQrCategoryData;
    }

    public abstract void setQrCategoryData(@Nullable QRTrayCategoriesRes.QRItem qRItem);

    @Deprecated
    public static ListItemQrTrayCategoryLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemQrTrayCategoryLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_qr_tray_category_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemQrTrayCategoryLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemQrTrayCategoryLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_qr_tray_category_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemQrTrayCategoryLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemQrTrayCategoryLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemQrTrayCategoryLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_qr_tray_category_layout, null, false, obj);
    }
}
