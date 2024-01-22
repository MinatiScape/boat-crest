package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionRouteSummaryItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView imageRouteSummaryEventType;
    @NonNull
    public final TextView subTitle;
    @NonNull
    public final TextView title;

    public MapplsDirectionRouteSummaryItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.imageRouteSummaryEventType = imageView;
        this.subTitle = textView;
        this.title = textView2;
    }

    public static MapplsDirectionRouteSummaryItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionRouteSummaryItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryItemBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_route_summary_item);
    }

    @NonNull
    public static MapplsDirectionRouteSummaryItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionRouteSummaryItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteSummaryItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_summary_item, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteSummaryItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_summary_item, null, false, obj);
    }
}
