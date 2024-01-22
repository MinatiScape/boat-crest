package com.coveiot.android.watchfaceui.databinding;

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
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.model.Categories;
/* loaded from: classes8.dex */
public abstract class ListWfCategoryItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivCategory;
    @Bindable
    public Categories mCloudCategoryData;
    @NonNull
    public final TextView tvCategory;

    public ListWfCategoryItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.ivCategory = imageView;
        this.tvCategory = textView;
    }

    public static ListWfCategoryItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListWfCategoryItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Categories getCloudCategoryData() {
        return this.mCloudCategoryData;
    }

    public abstract void setCloudCategoryData(@Nullable Categories categories);

    @Deprecated
    public static ListWfCategoryItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListWfCategoryItemBinding) ViewDataBinding.bind(obj, view, R.layout.list_wf_category_item);
    }

    @NonNull
    @Deprecated
    public static ListWfCategoryItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListWfCategoryItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_wf_category_item, viewGroup, z, obj);
    }

    @NonNull
    public static ListWfCategoryItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListWfCategoryItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListWfCategoryItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_wf_category_item, null, false, obj);
    }
}
