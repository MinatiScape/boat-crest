package com.mappls.sdk.maps;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.mappls.sdk.maps.utils.MapFragmentUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class MapFragment extends Fragment implements OnMapReadyCallback {
    public final List<OnMapReadyCallback> h = new ArrayList();
    public OnMapViewReadyCallback i;
    public MapplsMap j;
    public MapView k;

    /* loaded from: classes11.dex */
    public interface OnMapViewReadyCallback {
        void onMapViewReady(MapView mapView);
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public void getMapAsync(@NonNull OnMapReadyCallback onMapReadyCallback) {
        MapplsMap mapplsMap = this.j;
        if (mapplsMap == null) {
            this.h.add(onMapReadyCallback);
        } else {
            onMapReadyCallback.onMapReady(mapplsMap);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMapViewReadyCallback) {
            this.i = (OnMapViewReadyCallback) context;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        Context context = layoutInflater.getContext();
        MapView mapView = new MapView(context, MapFragmentUtils.resolveArgs(context, getArguments()));
        this.k = mapView;
        return mapView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.h.clear();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.k.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(@NonNull Context context, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        setArguments(MapFragmentUtils.createFragmentArgs(MapplsMapOptions.createFromAttributes(context, attributeSet)));
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        MapView mapView = this.k;
        if (mapView == null || mapView.isDestroyed()) {
            return;
        }
        this.k.onLowMemory();
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapError(int i, String str) {
        for (OnMapReadyCallback onMapReadyCallback : this.h) {
            onMapReadyCallback.onMapError(i, str);
        }
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapReady(@NonNull MapplsMap mapplsMap) {
        this.j = mapplsMap;
        for (OnMapReadyCallback onMapReadyCallback : this.h) {
            onMapReadyCallback.onMapReady(mapplsMap);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.k.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.k.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        MapView mapView = this.k;
        if (mapView == null || mapView.isDestroyed()) {
            return;
        }
        this.k.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.k.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.k.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.k.onCreate(bundle);
        this.k.getMapAsync(this);
        OnMapViewReadyCallback onMapViewReadyCallback = this.i;
        if (onMapViewReadyCallback != null) {
            onMapViewReadyCallback.onMapViewReady(this.k);
        }
    }

    @NonNull
    public static MapFragment newInstance(@Nullable MapplsMapOptions mapplsMapOptions) {
        MapFragment mapFragment = new MapFragment();
        mapFragment.setArguments(MapFragmentUtils.createFragmentArgs(mapplsMapOptions));
        return mapFragment;
    }
}
