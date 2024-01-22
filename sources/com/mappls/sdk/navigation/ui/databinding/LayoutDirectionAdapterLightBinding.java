package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.plugin.directions.view.ManeuverView;
/* loaded from: classes11.dex */
public abstract class LayoutDirectionAdapterLightBinding extends ViewDataBinding {
    @NonNull
    public final ManeuverView directionImage;
    @NonNull
    public final TextView directionText;
    @NonNull
    public final FrameLayout maneuverViewContainer;
    @NonNull
    public final TextView tvDistance;

    public LayoutDirectionAdapterLightBinding(Object obj, View view, int i, ManeuverView maneuverView, TextView textView, FrameLayout frameLayout, TextView textView2) {
        super(obj, view, i);
        this.directionImage = maneuverView;
        this.directionText = textView;
        this.maneuverViewContainer = frameLayout;
        this.tvDistance = textView2;
    }

    public static LayoutDirectionAdapterLightBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDirectionAdapterLightBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutDirectionAdapterLightBinding) ViewDataBinding.bind(obj, view, R.layout.layout_direction_adapter_light);
    }

    @NonNull
    public static LayoutDirectionAdapterLightBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutDirectionAdapterLightBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutDirectionAdapterLightBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutDirectionAdapterLightBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_direction_adapter_light, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutDirectionAdapterLightBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutDirectionAdapterLightBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_direction_adapter_light, null, false, obj);
    }
}
