package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionRouteViewBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView recyclerStop;
    @NonNull
    public final TabItem tabBike;
    @NonNull
    public final TabItem tabDrive;
    @NonNull
    public final TabLayout tabLayoutProfile;
    @NonNull
    public final TabItem tabWalk;

    public MapplsDirectionRouteViewBinding(Object obj, View view, int i, RecyclerView recyclerView, TabItem tabItem, TabItem tabItem2, TabLayout tabLayout, TabItem tabItem3) {
        super(obj, view, i);
        this.recyclerStop = recyclerView;
        this.tabBike = tabItem;
        this.tabDrive = tabItem2;
        this.tabLayoutProfile = tabLayout;
        this.tabWalk = tabItem3;
    }

    public static MapplsDirectionRouteViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionRouteViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionRouteViewBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_route_view);
    }

    @NonNull
    public static MapplsDirectionRouteViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionRouteViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionRouteViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_view, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionRouteViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionRouteViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_route_view, null, false, obj);
    }
}
