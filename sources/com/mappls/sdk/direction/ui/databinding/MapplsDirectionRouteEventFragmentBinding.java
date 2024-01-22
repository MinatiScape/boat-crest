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
public abstract class MapplsDirectionRouteEventFragmentBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView routeEventRecyclerView;

    public MapplsDirectionRouteEventFragmentBinding(Object obj, View view, int i, RecyclerView recyclerView) {
        super(obj, view, i);
        this.routeEventRecyclerView = recyclerView;
    }

    public static MapplsDirectionRouteEventFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionRouteEventFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionRouteEventFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_route_event_fragment);
    }

    @NonNull
    public static MapplsDirectionRouteEventFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionRouteEventFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteEventFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionRouteEventFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_event_fragment, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteEventFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionRouteEventFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_event_fragment, null, false, obj);
    }
}
