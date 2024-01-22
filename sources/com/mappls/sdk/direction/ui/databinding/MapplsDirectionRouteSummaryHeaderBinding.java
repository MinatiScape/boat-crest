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
public abstract class MapplsDirectionRouteSummaryHeaderBinding extends ViewDataBinding {
    @NonNull
    public final ImageView mapplsDirectionImageEventType;
    @NonNull
    public final TextView textReportSummaryEventCount;
    @NonNull
    public final TextView textReportSummaryEventName;
    @NonNull
    public final TextView textReportSummaryViewAll;

    public MapplsDirectionRouteSummaryHeaderBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.mapplsDirectionImageEventType = imageView;
        this.textReportSummaryEventCount = textView;
        this.textReportSummaryEventName = textView2;
        this.textReportSummaryViewAll = textView3;
    }

    public static MapplsDirectionRouteSummaryHeaderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionRouteSummaryHeaderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryHeaderBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_route_summary_header);
    }

    @NonNull
    public static MapplsDirectionRouteSummaryHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionRouteSummaryHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteSummaryHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_summary_header, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteSummaryHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_summary_header, null, false, obj);
    }
}
