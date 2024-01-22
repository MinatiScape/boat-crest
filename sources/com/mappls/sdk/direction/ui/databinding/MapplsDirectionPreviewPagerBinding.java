package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.plugin.directions.view.ManeuverView;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionPreviewPagerBinding extends ViewDataBinding {
    @NonNull
    public final TextView directionPreviewDist;
    @NonNull
    public final TextView directionPreviewText;
    @NonNull
    public final FrameLayout maneuverViewContainer;
    @NonNull
    public final ManeuverView navigateIcon;

    public MapplsDirectionPreviewPagerBinding(Object obj, View view, int i, TextView textView, TextView textView2, FrameLayout frameLayout, ManeuverView maneuverView) {
        super(obj, view, i);
        this.directionPreviewDist = textView;
        this.directionPreviewText = textView2;
        this.maneuverViewContainer = frameLayout;
        this.navigateIcon = maneuverView;
    }

    public static MapplsDirectionPreviewPagerBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionPreviewPagerBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionPreviewPagerBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_preview_pager);
    }

    @NonNull
    public static MapplsDirectionPreviewPagerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionPreviewPagerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionPreviewPagerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionPreviewPagerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_preview_pager, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionPreviewPagerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionPreviewPagerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_preview_pager, null, false, obj);
    }
}
