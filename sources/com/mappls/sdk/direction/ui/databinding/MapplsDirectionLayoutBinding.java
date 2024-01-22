package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionLayoutBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout fragmentContainer;
    @NonNull
    public final FrameLayout mapContainer;

    public MapplsDirectionLayoutBinding(Object obj, View view, int i, FrameLayout frameLayout, FrameLayout frameLayout2) {
        super(obj, view, i);
        this.fragmentContainer = frameLayout;
        this.mapContainer = frameLayout2;
    }

    public static MapplsDirectionLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_layout);
    }

    @NonNull
    public static MapplsDirectionLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_layout, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_layout, null, false, obj);
    }
}
