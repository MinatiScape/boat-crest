package com.mappls.sdk.navigation.ui.map;

import android.content.Context;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.UiSettings;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.location.LocationComponent;
import com.mappls.sdk.maps.location.LocationComponentActivationOptions;
import com.mappls.sdk.maps.location.LocationComponentOptions;
import com.mappls.sdk.maps.location.engine.LocationEngine;
import com.mappls.sdk.maps.location.engine.LocationEngineProvider;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.camera.INavigation;
import com.mappls.sdk.navigation.camera.NavigationCamera;
import com.mappls.sdk.navigation.camera.ProgressChangeListener;
import com.mappls.sdk.navigation.camera.RouteInformation;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.map.route.NavigationMapRoute;
import com.mappls.sdk.navigation.ui.map.route.v;
import com.mappls.sdk.navigation.ui.utils.c;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import com.realsil.sdk.dfu.DfuException;
import java.util.List;
/* loaded from: classes11.dex */
public class b implements INavigation {
    public static final int[] r = {0, 0, 0, 0};
    public MapplsMap h;
    public MapView i;
    public NavigationMapRoute j;
    public com.mappls.sdk.navigation.ui.map.a k;
    public com.mappls.sdk.navigation.ui.map.plugins.a l;
    public NavigationCamera m;
    public LocationComponent n;
    public boolean q = false;
    public c o = new c();
    public com.mappls.sdk.navigation.ui.c p = new com.mappls.sdk.navigation.ui.c();

    /* loaded from: classes11.dex */
    public class a implements Style.OnStyleLoaded {
        public a() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            b.b(b.this);
            b.d(b.this);
            b.this.d(true);
            b.c(b.this, style);
            b.e(b.this);
        }
    }

    public b(MapView mapView, MapplsMap mapplsMap) {
        this.h = mapplsMap;
        this.i = mapView;
        mapplsMap.setMinZoomPreference(3.0d);
        mapplsMap.setMaxZoomPreference(18.5d);
        o();
        mapplsMap.setCameraPosition(new CameraPosition.Builder().target(new LatLng(28.0d, 77.0d)).zoom(6.0d).build());
        this.n = mapplsMap.getLocationComponent();
        if (this.p != null && this.m != null) {
            f(true);
        }
        m();
        mapplsMap.getStyle(new a());
    }

    public static void b(b bVar) {
        bVar.j = new NavigationMapRoute(bVar.i, bVar.h, "highway_name", bVar, com.mappls.sdk.navigation.ui.theme.a.a(bVar.i.getContext(), R.attr.navigationViewRouteStyle, R.style.NavigationMapRoute));
        bVar.h.enableTraffic(bVar.q);
        com.mappls.sdk.navigation.ui.map.plugins.a aVar = new com.mappls.sdk.navigation.ui.map.plugins.a(bVar.i, bVar.h);
        bVar.l = aVar;
        aVar.a(false);
        bVar.l.a(0.0f, null);
    }

    public static void c(b bVar, Style style) {
        LocationComponentOptions.Builder trackingGesturesManagement = LocationComponentOptions.builder(bVar.i.getContext()).trackingGesturesManagement(true);
        Context context = bVar.i.getContext();
        int i = R.attr.navigationBearingIcon;
        LocationComponentOptions build = trackingGesturesManagement.gpsDrawable(com.mappls.sdk.navigation.ui.theme.a.e(context, i)).gpsStaleDrawable(com.mappls.sdk.navigation.ui.theme.a.e(bVar.i.getContext(), i)).accuracyColor(ContextCompat.getColor(bVar.i.getContext(), R.color.mapboxGreen)).build();
        LocationComponent locationComponent = bVar.h.getLocationComponent();
        locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(bVar.i.getContext(), style).locationComponentOptions(build).build());
        locationComponent.setLocationComponentEnabled(true);
        locationComponent.setCameraMode(34);
        locationComponent.setRenderMode(4);
    }

    public static void d(b bVar) {
        bVar.k = new com.mappls.sdk.navigation.ui.map.a(bVar.i, bVar.h);
    }

    public static void e(b bVar) {
        bVar.getClass();
        NavigationCamera navigationCamera = new NavigationCamera(bVar.h);
        bVar.m = navigationCamera;
        navigationCamera.addProgressChangeListener(bVar);
        bVar.m.start(MapplsNavigationHelper.getInstance().getCurrentRoute());
    }

    public final int a(float f) {
        return (int) (f * this.i.getResources().getDisplayMetrics().density);
    }

    public void a() {
        LocationComponent locationComponent = this.n;
        if (locationComponent == null || !locationComponent.isLocationComponentActivated()) {
            return;
        }
        this.n.setRenderMode(18);
    }

    public void a(float f, LatLng latLng) {
        com.mappls.sdk.navigation.ui.map.plugins.a aVar = this.l;
        if (aVar != null) {
            aVar.a(f, latLng);
        }
    }

    public void a(Location location) {
        LocationComponent locationComponent = this.n;
        if (locationComponent == null || !locationComponent.isLocationComponentActivated()) {
            return;
        }
        this.n.forceLocationUpdate(location);
    }

    public void a(Location location, AdviseInfo adviseInfo) {
        RouteInformation routeInformation = new RouteInformation(MapplsNavigationHelper.getInstance().getCurrentRoute(), location, adviseInfo);
        com.mappls.sdk.navigation.ui.c cVar = this.p;
        if (cVar != null) {
            cVar.a(location, routeInformation);
        }
    }

    public void a(LatLng latLng, double d, double d2, double d3) {
        if (this.h != null) {
            this.h.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().tilt(d2).zoom(d).bearing(d3).target(latLng).build()), 2000);
        }
    }

    public void a(@NonNull v vVar) {
        this.j.a(vVar);
    }

    public void a(List<ReportDetails> list) {
        NavigationMapRoute navigationMapRoute = this.j;
        if (navigationMapRoute != null) {
            navigationMapRoute.a(list);
        }
    }

    public void a(List<DirectionsRoute> list, int i) {
        this.j.a(list, i);
    }

    public void a(boolean z) {
        LocationComponent locationComponent;
        LocationEngine bestLocationEngine;
        if (z) {
            LocationComponent locationComponent2 = this.n;
            if (locationComponent2 == null || !locationComponent2.isLocationComponentActivated()) {
                return;
            }
            locationComponent = this.n;
            bestLocationEngine = this.o;
        } else {
            LocationComponent locationComponent3 = this.n;
            if (locationComponent3 == null || !locationComponent3.isLocationComponentActivated()) {
                return;
            }
            locationComponent = this.n;
            bestLocationEngine = LocationEngineProvider.getBestLocationEngine(this.i.getContext());
        }
        locationComponent.setLocationEngine(bestLocationEngine);
        this.n.setRenderMode(8);
    }

    public void b() {
        NavigationMapRoute navigationMapRoute = this.j;
        if (navigationMapRoute != null) {
            navigationMapRoute.b();
        }
    }

    public void b(boolean z) {
        this.q = z;
        MapplsMap mapplsMap = this.h;
        if (mapplsMap != null) {
            mapplsMap.enableTraffic(z);
        }
    }

    public Location c() {
        LocationComponent locationComponent = this.n;
        if (locationComponent == null || !locationComponent.isLocationComponentActivated()) {
            return null;
        }
        return this.n.getLastKnownLocation();
    }

    public void c(boolean z) {
        com.mappls.sdk.navigation.ui.map.plugins.a aVar = this.l;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    public double d() {
        MapplsMap mapplsMap = this.h;
        if (mapplsMap != null) {
            return mapplsMap.getMaxZoomLevel();
        }
        return 18.0d;
    }

    public void e(boolean z) {
        NavigationCamera navigationCamera = this.m;
        if (navigationCamera != null) {
            navigationCamera.toggleTilt(z);
        }
    }

    public boolean e() {
        NavigationCamera navigationCamera = this.m;
        return navigationCamera != null && navigationCamera.isTrackingEnabled();
    }

    public void f(boolean z) {
        if (z) {
            NavigationCamera navigationCamera = this.m;
            if (navigationCamera != null) {
                navigationCamera.resetCameraPositionWith(0);
                return;
            }
            return;
        }
        NavigationCamera navigationCamera2 = this.m;
        if (navigationCamera2 != null) {
            navigationCamera2.updateCameraTrackingMode(2);
        }
    }

    public boolean f() {
        MapplsMap mapplsMap = this.h;
        return mapplsMap == null ? this.q : mapplsMap.isEnableTraffic();
    }

    public void g() {
        NavigationMapRoute navigationMapRoute = this.j;
        if (navigationMapRoute != null) {
            navigationMapRoute.c();
        }
    }

    public void h() {
        NavigationMapRoute navigationMapRoute = this.j;
        if (navigationMapRoute != null) {
            navigationMapRoute.onStart();
        }
        NavigationCamera navigationCamera = this.m;
        if (navigationCamera != null) {
            navigationCamera.onStart();
        }
    }

    public void i() {
        NavigationMapRoute navigationMapRoute = this.j;
        if (navigationMapRoute != null) {
            navigationMapRoute.onStop();
        }
        NavigationCamera navigationCamera = this.m;
        if (navigationCamera != null) {
            navigationCamera.onStop();
        }
    }

    public void j() {
        NavigationCamera navigationCamera = this.m;
        if (navigationCamera != null) {
            navigationCamera.resetNavigationMode();
        }
    }

    public void k() {
        this.k.a();
    }

    public void l() {
        NavigationCamera navigationCamera = this.m;
        if (navigationCamera != null) {
            LocationComponent locationComponent = this.n;
            navigationCamera.resume((locationComponent == null || !locationComponent.isLocationComponentActivated()) ? null : this.n.getLastKnownLocation());
        }
    }

    public void m() {
        UiSettings uiSettings;
        int a2;
        float f;
        n();
        int a3 = a(8.0f);
        int a4 = a(8.0f);
        this.i.getCompassView().setPadding(a3, a3, a3, a3);
        ViewCompat.setElevation(this.i.getCompassView(), a4);
        if (this.i.getResources().getConfiguration().orientation == 1) {
            uiSettings = this.h.getUiSettings();
            a2 = a(20.0f);
            f = 150.0f;
        } else {
            uiSettings = this.h.getUiSettings();
            a2 = a(20.0f);
            f = 40.0f;
        }
        uiSettings.setCompassMargins(a2, a(f), a(20.0f), a(20.0f));
    }

    public void n() {
        UiSettings uiSettings;
        Context context;
        int i;
        this.i.getContext();
        if (com.mappls.sdk.navigation.ui.theme.a.a()) {
            uiSettings = this.h.getUiSettings();
            context = this.i.getContext();
            i = R.drawable.compass_north_up_dark;
        } else {
            uiSettings = this.h.getUiSettings();
            context = this.i.getContext();
            i = R.drawable.compass_north_up_light;
        }
        uiSettings.setCompassImage(ContextCompat.getDrawable(context, i));
    }

    public void o() {
        UiSettings uiSettings;
        int i;
        if (this.i.getResources().getConfiguration().orientation == 1) {
            uiSettings = this.h.getUiSettings();
            i = 300;
        } else {
            uiSettings = this.h.getUiSettings();
            i = 150;
        }
        uiSettings.setLogoMargins(0, 0, 0, i);
        this.h.getUiSettings().setCompassEnabled(true);
    }

    public void p() {
        this.h.getUiSettings().setLogoMargins(0, 0, 0, 100);
        this.h.getUiSettings().setCompassEnabled(false);
        this.k.b(r);
    }

    public void q() {
        this.k.b(r);
        NavigationCamera navigationCamera = this.m;
        if (navigationCamera != null) {
            navigationCamera.showRouteGeometryOverview(new int[]{20, 320, 20, DfuException.ERROR_ENTER_OTA_MODE_FAILED});
        }
    }

    @Override // com.mappls.sdk.navigation.camera.INavigation
    public void removeProgressChangeListener(@Nullable ProgressChangeListener progressChangeListener) {
        this.p.b(progressChangeListener);
    }

    @Override // com.mappls.sdk.navigation.camera.INavigation
    public void setProgressChangeListener(@Nullable ProgressChangeListener progressChangeListener) {
        this.p.a(progressChangeListener);
    }

    public void d(boolean z) {
        if (this.h == null) {
            return;
        }
        if (z) {
            this.k.a();
        } else {
            this.k.b(r);
        }
    }
}
