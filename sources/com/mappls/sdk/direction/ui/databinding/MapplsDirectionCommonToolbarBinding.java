package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionCommonToolbarBinding extends ViewDataBinding {
    @NonNull
    public final AppBarLayout commonAppBar;
    @NonNull
    public final LinearLayout layoutCommonToolbar;
    @NonNull
    public final Toolbar toolbar;

    public MapplsDirectionCommonToolbarBinding(Object obj, View view, int i, AppBarLayout appBarLayout, LinearLayout linearLayout, Toolbar toolbar) {
        super(obj, view, i);
        this.commonAppBar = appBarLayout;
        this.layoutCommonToolbar = linearLayout;
        this.toolbar = toolbar;
    }

    public static MapplsDirectionCommonToolbarBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionCommonToolbarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionCommonToolbarBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_common_toolbar);
    }

    @NonNull
    public static MapplsDirectionCommonToolbarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionCommonToolbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionCommonToolbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionCommonToolbarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_common_toolbar, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionCommonToolbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionCommonToolbarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_common_toolbar, null, false, obj);
    }
}
