package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionRouteSummaryAllEventFragmentBinding extends ViewDataBinding {
    @NonNull
    public final MapplsDirectionCommonToolbarBinding layoutSummaryCommonToolbar;
    @NonNull
    public final View previewView;
    @NonNull
    public final RecyclerView routeAllSummaryRecyclerView;

    public MapplsDirectionRouteSummaryAllEventFragmentBinding(Object obj, View view, int i, MapplsDirectionCommonToolbarBinding mapplsDirectionCommonToolbarBinding, View view2, RecyclerView recyclerView) {
        super(obj, view, i);
        this.layoutSummaryCommonToolbar = mapplsDirectionCommonToolbarBinding;
        this.previewView = view2;
        this.routeAllSummaryRecyclerView = recyclerView;
    }

    public static MapplsDirectionRouteSummaryAllEventFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionRouteSummaryAllEventFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryAllEventFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_route_summary_all_event_fragment);
    }

    @NonNull
    public static MapplsDirectionRouteSummaryAllEventFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionRouteSummaryAllEventFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteSummaryAllEventFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryAllEventFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_summary_all_event_fragment, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteSummaryAllEventFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionRouteSummaryAllEventFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_summary_all_event_fragment, null, false, obj);
    }
}
