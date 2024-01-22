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
public abstract class MapplsDirectionWidgetBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout mapplsDirectionContainerFragment;

    public MapplsDirectionWidgetBinding(Object obj, View view, int i, FrameLayout frameLayout) {
        super(obj, view, i);
        this.mapplsDirectionContainerFragment = frameLayout;
    }

    public static MapplsDirectionWidgetBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionWidgetBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionWidgetBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_widget);
    }

    @NonNull
    public static MapplsDirectionWidgetBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionWidgetBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionWidgetBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionWidgetBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_widget, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionWidgetBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionWidgetBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_widget, null, false, obj);
    }
}
