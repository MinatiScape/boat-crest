package com.mappls.sdk.category.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.category.R;
/* loaded from: classes11.dex */
public abstract class MapplsCategorySearchFragmentBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout mapplsCategoryView;

    public MapplsCategorySearchFragmentBinding(Object obj, View view, int i, FrameLayout frameLayout) {
        super(obj, view, i);
        this.mapplsCategoryView = frameLayout;
    }

    public static MapplsCategorySearchFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsCategorySearchFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsCategorySearchFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_category_search_fragment);
    }

    @NonNull
    public static MapplsCategorySearchFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsCategorySearchFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsCategorySearchFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsCategorySearchFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_category_search_fragment, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsCategorySearchFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsCategorySearchFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_category_search_fragment, null, false, obj);
    }
}
