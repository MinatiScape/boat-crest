package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.plugin.directions.view.ManeuverView;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionStepAdapterBinding extends ViewDataBinding {
    @NonNull
    public final TextView distanceText;
    @NonNull
    public final FrameLayout maneuverViewContainer;
    @NonNull
    public final RelativeLayout mapplsDirectionSteplistContainer;
    @NonNull
    public final ManeuverView navigateIcon;
    @NonNull
    public final TextView stepsText;

    public MapplsDirectionStepAdapterBinding(Object obj, View view, int i, TextView textView, FrameLayout frameLayout, RelativeLayout relativeLayout, ManeuverView maneuverView, TextView textView2) {
        super(obj, view, i);
        this.distanceText = textView;
        this.maneuverViewContainer = frameLayout;
        this.mapplsDirectionSteplistContainer = relativeLayout;
        this.navigateIcon = maneuverView;
        this.stepsText = textView2;
    }

    public static MapplsDirectionStepAdapterBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionStepAdapterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionStepAdapterBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_step_adapter);
    }

    @NonNull
    public static MapplsDirectionStepAdapterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionStepAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionStepAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionStepAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_step_adapter, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionStepAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionStepAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_step_adapter, null, false, obj);
    }
}
