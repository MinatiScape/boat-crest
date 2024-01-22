package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public abstract class LayoutDirectionAdapterHeaderBinding extends ViewDataBinding {
    @NonNull
    public final ImageView directionImage;
    @NonNull
    public final FrameLayout maneuverViewContainer;
    @NonNull
    public final TextView navigationDirectionPlaceName;
    @NonNull
    public final TextView navigationListDesc;

    public LayoutDirectionAdapterHeaderBinding(Object obj, View view, int i, ImageView imageView, FrameLayout frameLayout, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.directionImage = imageView;
        this.maneuverViewContainer = frameLayout;
        this.navigationDirectionPlaceName = textView;
        this.navigationListDesc = textView2;
    }

    public static LayoutDirectionAdapterHeaderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDirectionAdapterHeaderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutDirectionAdapterHeaderBinding) ViewDataBinding.bind(obj, view, R.layout.layout_direction_adapter_header);
    }

    @NonNull
    public static LayoutDirectionAdapterHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutDirectionAdapterHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutDirectionAdapterHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutDirectionAdapterHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_direction_adapter_header, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutDirectionAdapterHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutDirectionAdapterHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_direction_adapter_header, null, false, obj);
    }
}
