package com.mappls.sdk.direction.ui.fragment;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.mappls.sdk.category.model.PoiResult;
import com.mappls.sdk.direction.ui.DirectionViewModel;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.adapters.j;
import com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBinding;
import com.mappls.sdk.direction.ui.model.DirectionPoint;
import com.mappls.sdk.direction.ui.model.StopModel;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.utils.PolylineUtils;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.location.LocationComponent;
import com.mappls.sdk.maps.location.LocationComponentActivationOptions;
import com.mappls.sdk.maps.location.LocationComponentOptions;
import com.mappls.sdk.maps.location.engine.LocationEngine;
import com.mappls.sdk.maps.location.engine.LocationEngineCallback;
import com.mappls.sdk.maps.location.engine.LocationEngineRequest;
import com.mappls.sdk.maps.location.engine.LocationEngineResult;
import com.mappls.sdk.plugins.places.autocomplete.model.MapplsFavoritePlace;
import com.mappls.sdk.plugins.places.autocomplete.ui.PlaceAutocompleteFragment;
import com.mappls.sdk.plugins.places.autocomplete.ui.PlaceSelectionListener;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.DirectionsWaypoint;
import com.mappls.sdk.services.api.directions.models.LegAnnotation;
import com.mappls.sdk.services.api.directions.models.RouteClasses;
import com.mappls.sdk.services.api.directions.models.RouteLeg;
import com.mappls.sdk.services.api.event.route.model.RouteReportSummaryResponse;
import com.mappls.sdk.services.utils.MapplsUtils;
import com.mappls.sdk.turf.TurfMeasurement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public class l extends Fragment implements LocationEngineCallback<LocationEngineResult>, OnMapReadyCallback {

    /* renamed from: a  reason: collision with root package name */
    public boolean f12594a;
    public Location h;
    public MapplsRoutingLayoutBinding i;
    public DirectionViewModel j;
    public com.mappls.sdk.direction.ui.adapters.j k;
    public com.mappls.sdk.direction.ui.adapters.b l;
    public BottomSheetBehavior<RelativeLayout> m;
    public MapplsMap n;
    public LocationEngine o;
    public MapView p;
    public ArrayList q;
    public RecyclerViewDragDropManager r;
    public h s;
    @StyleRes
    public int t;
    public Context u;
    public DirectionPoint v;
    public DirectionPoint w;

    /* loaded from: classes11.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (l.this.j.tollHashMap.containsKey(Integer.valueOf(l.this.j.getSelectedIndex()))) {
                if (l.this.s != null) {
                    l.this.s.onTripEstimation(l.this.j.tollHashMap.get(Integer.valueOf(l.this.j.getSelectedIndex())), l.this.j.getDirectionsResponse(), l.this.j.getSelectedIndex());
                }
            } else if (l.this.s != null) {
                l.this.s.onTripEstimation(null, l.this.j.getDirectionsResponse(), l.this.j.getSelectedIndex());
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            l.this.a();
        }
    }

    /* loaded from: classes11.dex */
    public class c implements j.a {

        /* loaded from: classes11.dex */
        public class a implements PlaceSelectionListener {
            public final /* synthetic */ PlaceAutocompleteFragment h;
            public final /* synthetic */ int i;

            public a(PlaceAutocompleteFragment placeAutocompleteFragment, int i) {
                this.h = placeAutocompleteFragment;
                this.i = i;
            }

            @Override // com.mappls.sdk.plugins.places.autocomplete.ui.PlaceSelectionListener
            public final void onCancel() {
                if (l.this.getChildFragmentManager() != null) {
                    l.this.getChildFragmentManager().popBackStack();
                }
            }

            @Override // com.mappls.sdk.plugins.places.autocomplete.ui.PlaceSelectionListener
            public final void onFavoritePlaceSelected(MapplsFavoritePlace mapplsFavoritePlace) {
                if (l.this.getChildFragmentManager() != null) {
                    l.this.getChildFragmentManager().popBackStack(this.h.getClass().getName(), 1);
                }
                if (l.this.j.getStopModels().size() == 2) {
                    String mapplsPin = (this.i == 0 ? l.this.j.getStopModels().get(1) : l.this.j.getStopModels().get(0)).getMapplsPin();
                    if (mapplsPin != null && mapplsFavoritePlace.getMapplsPin().equalsIgnoreCase(mapplsPin)) {
                        Toast.makeText(l.this.u, "You might select same source and destination", 0).show();
                        return;
                    }
                }
                StopModel stopModel = l.this.j.getStopModels().get(this.i);
                if (mapplsFavoritePlace.getLongitude() == null || mapplsFavoritePlace.getLatitude() == null) {
                    stopModel.setLocation(null);
                } else {
                    stopModel.setLocation(Point.fromLngLat(mapplsFavoritePlace.getLongitude().doubleValue(), mapplsFavoritePlace.getLatitude().doubleValue()));
                }
                stopModel.setEntryLocation(null);
                stopModel.setLocationType(StopModel.TYPE_STOP);
                stopModel.setPlaceName(mapplsFavoritePlace.getPlaceName());
                stopModel.setPlaceAddress(mapplsFavoritePlace.getPlaceAddress());
                stopModel.setMapplsPin(mapplsFavoritePlace.getMapplsPin());
                if (l.this.j.getStopModels().size() < 5 && l.this.j.getStopModels().size() > 2 && l.this.j.getStopModels().get(l.this.j.getStopModels().size() - 1).getLocationType() != StopModel.TYPE_BLANK) {
                    StopModel stopModel2 = new StopModel();
                    stopModel2.setLocationType(StopModel.TYPE_BLANK);
                    l.this.j.getStopModels().add(stopModel2);
                }
                l.I(l.this);
            }

            @Override // com.mappls.sdk.plugins.places.autocomplete.ui.PlaceSelectionListener
            public final void onPlaceSelected(ELocation eLocation) {
                if (l.this.getChildFragmentManager() != null) {
                    l.this.getChildFragmentManager().popBackStack(this.h.getClass().getName(), 1);
                }
                if (l.this.j.getStopModels().size() == 2) {
                    String mapplsPin = (this.i == 0 ? l.this.j.getStopModels().get(1) : l.this.j.getStopModels().get(0)).getMapplsPin();
                    if (mapplsPin != null && eLocation.getMapplsPin().equalsIgnoreCase(mapplsPin)) {
                        Toast.makeText(l.this.u, "You might select same source and destination", 0).show();
                        return;
                    }
                }
                StopModel stopModel = l.this.j.getStopModels().get(this.i);
                Double d = eLocation.longitude;
                if (d == null || eLocation.latitude == null) {
                    stopModel.setLocation(null);
                } else {
                    stopModel.setLocation(Point.fromLngLat(d.doubleValue(), eLocation.latitude.doubleValue()));
                }
                Double d2 = eLocation.entryLongitude;
                if (d2 == null || eLocation.entryLatitude == null) {
                    stopModel.setEntryLocation(null);
                } else {
                    stopModel.setEntryLocation(Point.fromLngLat(d2.doubleValue(), eLocation.entryLatitude.doubleValue()));
                }
                stopModel.setLocationType(StopModel.TYPE_STOP);
                stopModel.setPlaceName(eLocation.placeName);
                stopModel.setPlaceAddress(eLocation.placeAddress);
                stopModel.setMapplsPin(eLocation.getMapplsPin());
                if (l.this.j.getStopModels().size() < 5 && l.this.j.getStopModels().size() > 2 && l.this.j.getStopModels().get(l.this.j.getStopModels().size() - 1).getLocationType() != StopModel.TYPE_BLANK) {
                    StopModel stopModel2 = new StopModel();
                    stopModel2.setLocationType(StopModel.TYPE_BLANK);
                    l.this.j.getStopModels().add(stopModel2);
                }
                l.I(l.this);
            }

            @Override // com.mappls.sdk.plugins.places.autocomplete.ui.PlaceSelectionListener
            public final void requestForCurrentLocation() {
                if (l.this.getChildFragmentManager() != null) {
                    l.this.getChildFragmentManager().popBackStack(this.h.getClass().getName(), 1);
                }
                l lVar = l.this;
                if (lVar.h != null) {
                    if (lVar.j.getStopModels().size() == 2) {
                        Point location = (this.i == 0 ? l.this.j.getStopModels().get(1) : l.this.j.getStopModels().get(0)).getLocation();
                        if (location != null && TurfMeasurement.distance(Point.fromLngLat(l.this.h.getLongitude(), l.this.h.getLatitude()), location) < 10.0d) {
                            Toast.makeText(l.this.u, "You might select same source and destination", 0).show();
                            return;
                        }
                    }
                    StopModel stopModel = l.this.j.getStopModels().get(this.i);
                    stopModel.setLocation(Point.fromLngLat(l.this.h.getLongitude(), l.this.h.getLatitude()));
                    stopModel.setEntryLocation(null);
                    stopModel.setLocationType(StopModel.TYPE_CURRENT_LOCATION);
                    stopModel.setPlaceName(null);
                    stopModel.setPlaceAddress(null);
                    stopModel.setMapplsPin(null);
                    if (l.this.j.getStopModels().size() < 5 && l.this.j.getStopModels().size() > 2 && l.this.j.getStopModels().get(l.this.j.getStopModels().size() - 1).getLocationType() != StopModel.TYPE_BLANK) {
                        StopModel stopModel2 = new StopModel();
                        stopModel2.setLocationType(StopModel.TYPE_BLANK);
                        l.this.j.getStopModels().add(stopModel2);
                    }
                    l.I(l.this);
                }
            }
        }

        public c() {
        }

        @Override // com.mappls.sdk.direction.ui.adapters.j.a
        public final void a() {
            Collections.reverse(l.this.j.getStopModels());
            l.this.k.a(l.this.j.getStopModels());
            l.this.a();
        }

        @Override // com.mappls.sdk.direction.ui.adapters.j.a
        public final void a(int i) {
            if (!(l.this.j.getStopModels().get(i).getLocation() == null && l.this.j.getStopModels().get(i).getMapplsPin() == null) && l.this.k.getItemCount() <= 4) {
                StopModel stopModel = new StopModel();
                stopModel.setLocationType(StopModel.TYPE_BLANK);
                l.this.j.getStopModels().add(stopModel);
                l.this.k.a(l.this.j.getStopModels());
                l.this.i.bottomSheetDetails.setVisibility(8);
                l.this.i.errorLayout.setVisibility(8);
                l.this.i.notificationLayout.setVisibility(8);
                if (l.this.n != null && l.this.n.getUiSettings() != null) {
                    l.this.n.getUiSettings().setLogoMargins(0, 0, 0, 0);
                }
                l.this.i.viewGetRoute.setVisibility(0);
            }
        }

        @Override // com.mappls.sdk.direction.ui.adapters.j.a
        public final void a(StopModel stopModel) {
            l.this.j.getStopModels().remove(stopModel);
            l.this.k.a(l.this.j.getStopModels());
            if (l.this.j.getStopModels().size() < 3) {
                if (l.this.s != null) {
                    l.this.s.clearRoute();
                }
                l.this.i.searchCategoryFab.setVisibility(8);
                l.this.i.viewGetRoute.setVisibility(8);
            }
        }

        @Override // com.mappls.sdk.direction.ui.adapters.j.a
        public final void b(int i) {
            PlaceAutocompleteFragment newInstance = PlaceAutocompleteFragment.newInstance(l.this.j.options.searchPlaceOption());
            newInstance.setOnPlaceSelectedListener(new a(newInstance, i));
            l lVar = l.this;
            if ((lVar.getChildFragmentManager() != null ? lVar.getChildFragmentManager().findFragmentByTag(newInstance.getClass().getName()) : null) == null) {
                FragmentTransaction beginTransaction = lVar.getChildFragmentManager().beginTransaction();
                beginTransaction.add(R.id.direction_container, newInstance, newInstance.getClass().getName());
                beginTransaction.addToBackStack(newInstance.getClass().getName());
                try {
                    beginTransaction.commit();
                } catch (IllegalStateException unused) {
                    beginTransaction.commitAllowingStateLoss();
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Style.OnStyleLoaded {
        public d() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            Point location;
            Point location2;
            l.p(l.this, style);
            if (l.this.j.getDirectionsResponse() != null) {
                DirectionsRoute directionsRoute = l.this.j.getDirectionsResponse().routes().get(0);
                LatLng latLng = null;
                List<Point> decode = directionsRoute.geometry() != null ? PolylineUtils.decode(directionsRoute.geometry(), 6) : null;
                if (decode == null) {
                    decode = new ArrayList<>();
                }
                ArrayList arrayList = new ArrayList();
                for (Point point : decode) {
                    arrayList.add(new LatLng(point.latitude(), point.longitude()));
                }
                LatLng latLng2 = (l.this.j.getStopModels().get(0).getLocationType() != StopModel.TYPE_STOP || l.this.j.getDirectionsResponse().waypoints() == null || l.this.j.getDirectionsResponse().waypoints().size() <= 0 || (location2 = l.this.j.getDirectionsResponse().waypoints().get(0).location()) == null) ? null : new LatLng(location2.latitude(), location2.longitude());
                if (l.this.j.getStopModels().get(l.this.j.getStopModels().size() - 1).getLocationType() == StopModel.TYPE_STOP && l.this.j.getDirectionsResponse().waypoints() != null && l.this.j.getDirectionsResponse().waypoints().size() > 0 && (location = l.this.j.getDirectionsResponse().waypoints().get(l.this.j.getDirectionsResponse().waypoints().size() - 1).location()) != null) {
                    latLng = new LatLng(location.latitude(), location.longitude());
                }
                LatLng latLng3 = latLng;
                ArrayList arrayList2 = new ArrayList();
                if (l.this.j.getStopModels().size() > 2) {
                    for (int i = 1; i < l.this.j.getStopModels().size() - 1; i++) {
                        if (l.this.j.getDirectionsResponse().waypoints() != null && l.this.j.getDirectionsResponse().waypoints().size() >= i && l.this.j.getStopModels().get(i).getLocationType() == StopModel.TYPE_STOP) {
                            DirectionsWaypoint directionsWaypoint = l.this.j.getDirectionsResponse().waypoints().get(i);
                            if (directionsWaypoint.location() != null) {
                                arrayList2.add(new LatLng(directionsWaypoint.location().latitude(), directionsWaypoint.location().longitude()));
                            }
                        }
                    }
                }
                if (l.this.s != null) {
                    l.this.s.onUpdateRoute(latLng2, latLng3, arrayList2, l.this.j.getDirectionsResponse().routes(), l.this.j.getSelectedIndex());
                }
                l.q(l.this, arrayList);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Style.OnStyleLoaded {
        public e() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            l.p(l.this, style);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(View view) {
        if (view != null) {
            m(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D(View view) {
        ImageView imageView;
        Context context;
        int i;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.j.getStopModels().size(); i2++) {
            if (this.j.getStopModels().get(i2).getLocationType() == StopModel.TYPE_BLANK || (i2 > 0 && com.mappls.sdk.direction.ui.common.d.a(this.j.getStopModels().get(i2 - 1), this.j.getStopModels().get(i2)))) {
                arrayList.add(this.j.getStopModels().get(i2));
            }
        }
        this.j.getStopModels().removeAll(arrayList);
        if (this.j.getStopModels().size() < 2) {
            StopModel stopModel = new StopModel();
            stopModel.setLocationType(StopModel.TYPE_BLANK);
            this.j.getStopModels().add(stopModel);
        }
        com.mappls.sdk.direction.ui.adapters.j jVar = this.k;
        if (jVar != null) {
            jVar.a(this.j.getStopModels());
        }
        if (this.j.getStopModels().size() > 2) {
            this.i.cdSelectLocation.setVisibility(8);
            this.i.collapsedRouteTimeline.collapsedRouteTimeline.setVisibility(0);
            this.i.errorLayout.setVisibility(8);
            this.i.notificationLayout.setVisibility(8);
            this.i.bottomSheetDetails.setVisibility(0);
            this.i.containerRouteDetails.setVisibility(0);
            this.i.recyclerDirectionStep.setVisibility(8);
            this.i.progressBar.setVisibility(0);
            this.i.bottomSheetShadow.setVisibility(8);
            this.i.bottomSheetDetails.setBackgroundColor(-1);
            this.i.layoutTimeDetails.setVisibility(8);
            this.i.collapsedRouteTimeline.setSourceRouteLocation(this.j.getSourceLocation());
            MapplsMap mapplsMap = this.n;
            if (mapplsMap != null && mapplsMap.getUiSettings() != null) {
                this.n.getUiSettings().setLogoMargins(0, 0, 0, 300);
            }
            if (this.j.getSourceLocation().equals("Your Current Location")) {
                imageView = this.i.collapsedRouteTimeline.imgSourceLocation;
                context = this.u;
                i = R.drawable.mappls_direction_current_location_icon;
            } else {
                imageView = this.i.collapsedRouteTimeline.imgSourceLocation;
                context = this.u;
                i = R.drawable.mappls_direction_start_loc;
            }
            imageView.setImageDrawable(ContextCompat.getDrawable(context, i));
            this.i.collapsedRouteTimeline.setDestinationRouteLocation(this.j.getDestinationLocation());
            this.i.collapsedRouteTimeline.setWayPoints((this.j.getStopModels().size() - 2) + " waypoints");
        } else {
            this.i.viewGetRoute.setVisibility(8);
        }
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F(View view) {
        if (this.j.getStopModels().size() > 2 && this.j.getStopModels().size() < 5) {
            StopModel stopModel = new StopModel();
            stopModel.setLocationType(StopModel.TYPE_BLANK);
            this.j.addStop(stopModel);
            this.k.a(this.j.getStopModels());
        }
        this.i.cdSelectLocation.setVisibility(0);
        this.i.collapsedRouteTimeline.collapsedRouteTimeline.setVisibility(8);
        this.i.errorLayout.setVisibility(8);
        this.i.notificationLayout.setVisibility(8);
        this.i.bottomSheetDetails.setVisibility(8);
        this.i.viewGetRoute.setVisibility(0);
        MapplsMap mapplsMap = this.n;
        if (mapplsMap == null || mapplsMap.getUiSettings() == null) {
            return;
        }
        this.n.getUiSettings().setLogoMargins(0, 0, 0, 0);
    }

    public static void I(l lVar) {
        com.mappls.sdk.direction.ui.adapters.j jVar = lVar.k;
        if (jVar != null) {
            jVar.a(lVar.j.getStopModels());
        }
        if (lVar.j.getStopModels().size() == 2) {
            List<StopModel> stopModels = lVar.j.getStopModels();
            if (stopModels.get(0).getLocationType() == StopModel.TYPE_BLANK || stopModels.get(1).getLocationType() == StopModel.TYPE_BLANK) {
                return;
            }
            lVar.i.bottomSheetDetails.setVisibility(0);
            lVar.i.errorLayout.setVisibility(8);
            lVar.i.notificationLayout.setVisibility(8);
            lVar.i.containerRouteDetails.setVisibility(0);
            lVar.i.progressBar.setVisibility(0);
            lVar.i.bottomSheetShadow.setVisibility(8);
            lVar.i.bottomSheetDetails.setBackgroundColor(-1);
            lVar.i.layoutTimeDetails.setVisibility(8);
            lVar.i.recyclerDirectionStep.setVisibility(8);
            MapplsMap mapplsMap = lVar.n;
            if (mapplsMap != null && mapplsMap.getUiSettings() != null) {
                lVar.n.getUiSettings().setLogoMargins(0, 0, 0, 300);
            }
            lVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(View view) {
        h hVar;
        if (this.j.getRouteReportSummaryResponse() == null || (hVar = this.s) == null) {
            return;
        }
        hVar.onRouteReportSummaryClick(this.j.getRouteReportSummaryResponse(), Integer.valueOf(this.j.getSelectedIndex()), this.j.getDirectionsResponse());
    }

    public static void p(l lVar, Style style) {
        Context context = lVar.u;
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            LocationComponentOptions build = LocationComponentOptions.builder(lVar.u).trackingGesturesManagement(true).foregroundDrawable(R.drawable.mappls_direction_current_location_marker).foregroundDrawableStale(R.drawable.mappls_direction_current_location_stale).bearingDrawable(R.drawable.mappls_direction_bearing_icon).accuracyColor(ContextCompat.getColor(lVar.u, R.color.mappls_direction_colorStart)).build();
            LocationComponent locationComponent = lVar.n.getLocationComponent();
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(lVar.u, style).locationComponentOptions(build).build());
            locationComponent.setLocationComponentEnabled(true);
            if (locationComponent.getLastKnownLocation() != null) {
                lVar.a(locationComponent.getLastKnownLocation());
            }
            lVar.o = locationComponent.getLocationEngine();
            lVar.o.requestLocationUpdates(new LocationEngineRequest.Builder(1000L).setPriority(0).build(), lVar, Looper.getMainLooper());
            locationComponent.setCameraMode(8);
            locationComponent.setRenderMode(4);
        } else if (lVar.shouldShowRequestPermissionRationale("android.permission.ACCESS_COARSE_LOCATION") || lVar.shouldShowRequestPermissionRationale("android.permission.ACCESS_FINE_LOCATION")) {
            lVar.requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 102);
        } else {
            lVar.requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 102);
        }
    }

    public static void q(l lVar, ArrayList arrayList) {
        if (lVar.n == null || arrayList.size() < 1) {
            return;
        }
        lVar.n.animateCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds.Builder().includes(arrayList).build(), 20, 600, 20, 350));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(View view) {
        if (this.j.getStopModels().get(0).getLocationType() == StopModel.TYPE_STOP || !this.j.options.showStartNavigation().booleanValue()) {
            m(0);
        } else if (this.j.getStopModels().get(0).getLocationType() == StopModel.TYPE_CURRENT_LOCATION) {
            StopModel stopModel = this.j.getStopModels().get(0);
            DirectionPoint direction = stopModel.getLocation() != null ? DirectionPoint.setDirection(stopModel.getLocation(), stopModel.getPlaceName(), stopModel.getPlaceAddress()) : DirectionPoint.setDirection(stopModel.getMapplsPin(), stopModel.getPlaceName(), stopModel.getPlaceAddress());
            StopModel stopModel2 = this.j.getStopModels().get(this.j.getStopModels().size() - 1);
            if (stopModel2.getLocationType() == StopModel.TYPE_BLANK) {
                stopModel2 = this.j.getStopModels().get(this.j.getStopModels().size() - 2);
                this.j.getStopModels().remove(this.j.getStopModels().size() - 1);
            }
            DirectionPoint direction2 = stopModel2.getLocation() != null ? DirectionPoint.setDirection(stopModel2.getLocation(), stopModel2.getPlaceName(), stopModel2.getPlaceAddress()) : DirectionPoint.setDirection(stopModel2.getMapplsPin(), stopModel2.getPlaceName(), stopModel2.getPlaceAddress());
            ArrayList arrayList = new ArrayList();
            if (this.j.getStopModels().size() > 2) {
                for (int i = 1; i < this.j.getStopModels().size() - 1; i++) {
                    StopModel stopModel3 = this.j.getStopModels().get(i);
                    arrayList.add(stopModel3.getLocation() != null ? DirectionPoint.setDirection(stopModel3.getLocation(), stopModel3.getPlaceName(), stopModel3.getPlaceAddress()) : DirectionPoint.setDirection(stopModel3.getMapplsPin(), stopModel3.getPlaceName(), stopModel3.getPlaceAddress()));
                }
            }
            this.s.onStartNavigation(direction, direction2, arrayList, this.j.getDirectionsResponse(), this.j.getSelectedIndex());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(com.mappls.sdk.direction.ui.common.a aVar) {
        if (aVar != null) {
            int i = aVar.f12583a;
            if (i != 1) {
                if (i == 2) {
                    this.i.nearbyReport.setVisibility(8);
                    this.i.tripCostSummary.setVisibility(8);
                    this.j.setRouteReportSummaryResponse(null);
                    return;
                }
                return;
            }
            this.i.nearbyReport.setVisibility(0);
            this.j.setRouteReportSummaryResponse((RouteReportSummaryResponse) aVar.c);
            h hVar = this.s;
            if (hVar != null) {
                hVar.onUpdateRouteReport(((RouteReportSummaryResponse) aVar.c).getRoutes(), this.j.getSelectedIndex());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(View view) {
        h hVar = this.s;
        if (hVar != null) {
            hVar.onCancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(com.mappls.sdk.direction.ui.common.a aVar) {
        T t;
        if (aVar == null || aVar.f12583a != 1 || (t = aVar.c) == 0) {
            return;
        }
        Pair pair = (Pair) t;
        this.j.tollHashMap.put((Integer) pair.first, (CostEstimationResponse) pair.second);
    }

    public final void H(View view) {
        if (this.j.getDirectionsResponse() == null) {
            Toast.makeText(this.u, "Route is not available.", 0).show();
        } else {
            this.s.searchCategory(this.j.getDirectionsResponse().routes().get(this.j.getSelectedIndex()).geometry(), this.j.options);
        }
    }

    public final void a() {
        String mapplsPin;
        List<StopModel> stopModels = this.j.getStopModels();
        if (stopModels.get(0).getLocationType() == StopModel.TYPE_BLANK || stopModels.get(1).getLocationType() == StopModel.TYPE_BLANK) {
            return;
        }
        StopModel stopModel = stopModels.get(0);
        Point location = stopModel.getLocation();
        Point entryLocation = stopModel.getEntryLocation();
        String format = entryLocation != null ? String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(entryLocation.longitude()), MapplsUtils.formatCoordinate(entryLocation.latitude())) : location != null ? String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(location.longitude()), MapplsUtils.formatCoordinate(location.latitude())) : stopModel.getMapplsPin();
        this.i.setOnStartClick(this.j);
        ArrayList arrayList = new ArrayList();
        if (this.j.getStopModels().size() > 2) {
            for (int i = 1; i < this.j.getStopModels().size() - 1; i++) {
                if (this.j.getStopModels().get(i).getEntryLocation() != null) {
                    Point entryLocation2 = this.j.getStopModels().get(i).getEntryLocation();
                    mapplsPin = String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(entryLocation2.longitude()), MapplsUtils.formatCoordinate(entryLocation2.latitude()));
                } else if (this.j.getStopModels().get(i).getLocation() != null) {
                    Point location2 = this.j.getStopModels().get(i).getLocation();
                    mapplsPin = String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(location2.longitude()), MapplsUtils.formatCoordinate(location2.latitude()));
                } else if (this.j.getStopModels().get(i).getMapplsPin() != null) {
                    mapplsPin = this.j.getStopModels().get(i).getMapplsPin();
                }
                arrayList.add(mapplsPin);
            }
        }
        if (stopModels.size() >= 3 || stopModels.get(1).getLocationType() != StopModel.TYPE_BLANK) {
            if (stopModels.get(stopModels.size() - 1).getLocationType() == StopModel.TYPE_BLANK) {
                stopModels.remove(stopModels.size() - 1);
                this.j.setStopModels(stopModels);
                com.mappls.sdk.direction.ui.adapters.j jVar = this.k;
                if (jVar != null) {
                    jVar.a(this.j.getStopModels());
                }
            }
            Point location3 = stopModels.get(stopModels.size() - 1).getLocation();
            Point entryLocation3 = stopModels.get(stopModels.size() - 1).getEntryLocation();
            String format2 = entryLocation3 != null ? String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(entryLocation3.longitude()), MapplsUtils.formatCoordinate(entryLocation3.latitude())) : location3 != null ? String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(location3.longitude()), MapplsUtils.formatCoordinate(location3.latitude())) : stopModels.get(stopModels.size() - 1).getMapplsPin();
            if (format == null || format2 == null) {
                return;
            }
            this.i.errorLayout.setVisibility(8);
            this.i.notificationLayout.setVisibility(8);
            this.i.bottomSheetDetails.setVisibility(0);
            this.i.containerRouteDetails.setVisibility(0);
            this.i.progressBar.setVisibility(0);
            MapplsMap mapplsMap = this.n;
            if (mapplsMap != null && mapplsMap.getUiSettings() != null) {
                this.n.getUiSettings().setLogoMargins(0, 0, 0, 300);
            }
            this.i.nearbyReport.setVisibility(8);
            this.i.tripCostSummary.setVisibility(8);
            this.j.setRouteReportSummaryResponse(null);
            this.j.getRouteLiveData(requireContext(), format, format2, arrayList);
        }
    }

    public final void a(int i, DirectionsRoute directionsRoute) {
        h hVar;
        this.j.setSelectedIndex(i);
        if (this.j.getRouteReportSummaryResponse() != null && (hVar = this.s) != null) {
            hVar.onUpdateRouteReport(this.j.getRouteReportSummaryResponse().getRoutes(), i);
        }
        s(directionsRoute);
    }

    public final void a(Location location) {
        DirectionViewModel directionViewModel = this.j;
        if (directionViewModel == null || directionViewModel.getStopModels().size() != 2) {
            return;
        }
        StopModel stopModel = this.j.getStopModels().get(0);
        if (stopModel.getLocationType() == StopModel.TYPE_BLANK) {
            int locationType = this.j.getStopModels().get(1).getLocationType();
            int i = StopModel.TYPE_CURRENT_LOCATION;
            if (locationType != i) {
                stopModel.setLocationType(i);
                stopModel.setLocation(Point.fromLngLat(location.getLongitude(), location.getLatitude()));
                this.n.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16.0d));
                a();
            }
        }
        com.mappls.sdk.direction.ui.adapters.j jVar = this.k;
        if (jVar != null) {
            jVar.a(this.j.getStopModels());
        }
    }

    public final void a(PoiResult poiResult) {
        boolean z;
        if (this.j.getStopModels().size() < 2 || this.j.getStopModels().size() >= 5) {
            return;
        }
        StopModel stopModel = new StopModel();
        if (poiResult.getLatitude() != null && poiResult.getLongitude() != null) {
            stopModel.setLocation(Point.fromLngLat(poiResult.getLongitude().doubleValue(), poiResult.getLatitude().doubleValue()));
        }
        stopModel.setMapplsPin(poiResult.getMapplsPin());
        stopModel.setPlaceName(poiResult.getPlaceName());
        stopModel.setPlaceAddress(poiResult.getPlaceAddress());
        stopModel.setLocationType(StopModel.TYPE_STOP);
        int i = 0;
        while (true) {
            if (i >= this.j.getStopModels().size()) {
                z = false;
                break;
            } else if (com.mappls.sdk.direction.ui.common.d.a(stopModel, this.j.getStopModels().get(i))) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            Toast.makeText(this.u, "Already added", 0).show();
            return;
        }
        this.j.addWayPoint(stopModel);
        a();
    }

    public final void a(h hVar) {
        this.s = hVar;
    }

    public final void a(MapView mapView) {
        this.p = mapView;
        if (this.u == null || this.f12594a) {
            return;
        }
        mapView.getMapAsync(this);
    }

    public final void a(CostEstimationResponse costEstimationResponse) {
        DirectionViewModel directionViewModel = this.j;
        directionViewModel.tollHashMap.put(Integer.valueOf(directionViewModel.getSelectedIndex()), costEstimationResponse);
    }

    public final void m(int i) {
        ArrayList arrayList = this.q;
        if (arrayList == null || arrayList.size() <= 0) {
            Toast.makeText(this.u, "No steps available to show Preview", 0).show();
            return;
        }
        h hVar = this.s;
        if (hVar != null) {
            hVar.onPreviewClick(this.q, i, this.t);
        }
    }

    public final void o(com.mappls.sdk.direction.ui.common.a aVar) {
        DirectionsResponse directionsResponse;
        Point location;
        Point location2;
        if (aVar != null) {
            int i = aVar.f12583a;
            if (i != 1) {
                if (i == 2) {
                    this.i.errorLayout.setVisibility(0);
                    this.i.notificationLayout.setVisibility(8);
                    this.i.bottomSheetDetails.setVisibility(8);
                    this.i.searchCategoryFab.setVisibility(8);
                    MapplsMap mapplsMap = this.n;
                    if (mapplsMap != null && mapplsMap.getUiSettings() != null) {
                        this.n.getUiSettings().setLogoMargins(10, 0, 0, 10);
                    }
                    h hVar = this.s;
                    if (hVar != null) {
                        hVar.clearRoute();
                    }
                    Toast.makeText(this.u, aVar.b, 0).show();
                    return;
                }
                return;
            }
            this.i.errorLayout.setVisibility(8);
            this.i.notificationLayout.setVisibility(8);
            this.j.tollHashMap.clear();
            T t = aVar.c;
            if (t == 0 || ((DirectionsResponse) t).routes().size() <= 0) {
                return;
            }
            r(this.j.getDirectionsResponse());
            LatLng latLng = null;
            LatLng latLng2 = (this.j.getStopModels().get(0).getLocationType() != StopModel.TYPE_STOP || ((DirectionsResponse) aVar.c).waypoints() == null || ((DirectionsResponse) aVar.c).waypoints().size() <= 0 || (location2 = ((DirectionsResponse) aVar.c).waypoints().get(0).location()) == null) ? null : new LatLng(location2.latitude(), location2.longitude());
            if (this.j.getStopModels().get(this.j.getStopModels().size() - 1).getLocationType() == StopModel.TYPE_STOP && ((DirectionsResponse) aVar.c).waypoints() != null && ((DirectionsResponse) aVar.c).waypoints().size() > 0 && (location = ((DirectionsResponse) aVar.c).waypoints().get(((DirectionsResponse) aVar.c).waypoints().size() - 1).location()) != null) {
                latLng = new LatLng(location.latitude(), location.longitude());
            }
            LatLng latLng3 = latLng;
            ArrayList arrayList = new ArrayList();
            if (this.j.getStopModels().size() > 2) {
                for (int i2 = 1; i2 < this.j.getStopModels().size() - 1; i2++) {
                    if (((DirectionsResponse) aVar.c).waypoints() != null && ((DirectionsResponse) aVar.c).waypoints().size() >= i2 && this.j.getStopModels().get(i2).getLocationType() == StopModel.TYPE_STOP) {
                        DirectionsWaypoint directionsWaypoint = ((DirectionsResponse) aVar.c).waypoints().get(i2);
                        if (directionsWaypoint.location() != null) {
                            arrayList.add(new LatLng(directionsWaypoint.location().latitude(), directionsWaypoint.location().longitude()));
                        }
                    }
                }
            }
            h hVar2 = this.s;
            if (hVar2 != null) {
                hVar2.onUpdateRoute(latLng2, latLng3, arrayList, ((DirectionsResponse) aVar.c).routes(), this.j.getSelectedIndex());
            }
            if (this.j.options.searchAlongRoute().booleanValue()) {
                this.i.searchCategoryFab.setVisibility(0);
            }
            if (this.j.options.resource().equalsIgnoreCase(DirectionsCriteria.RESOURCE_ROUTE_ETA) && this.j.options.showRouteReportSummary().booleanValue() && (directionsResponse = (DirectionsResponse) aVar.c) != null && directionsResponse.sessionId() != null) {
                this.j.getRouteSummaryLiveData(directionsResponse.sessionId());
            }
            if (((DirectionsResponse) aVar.c).sessionId() == null || !this.j.options.showTripCostSummary().booleanValue()) {
                this.i.tripCostSummary.setVisibility(8);
                return;
            }
            this.i.tripCostSummary.setVisibility(0);
            for (int i3 = 0; i3 < ((DirectionsResponse) aVar.c).routes().size(); i3++) {
                this.j.getTollCostLiveData(((DirectionsResponse) aVar.c).sessionId(), i3, null, null, null);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.u = context;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x00b4, code lost:
        if (r7 != 32) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00e5, code lost:
        if (r6.j.options.theme().intValue() == 2) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00e7, code lost:
        r7 = r6.j.options.directionDarkTheme();
     */
    @Override // androidx.fragment.app.Fragment
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View onCreateView(@androidx.annotation.NonNull android.view.LayoutInflater r7, @androidx.annotation.Nullable android.view.ViewGroup r8, @androidx.annotation.Nullable android.os.Bundle r9) {
        /*
            Method dump skipped, instructions count: 1283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.direction.ui.fragment.l.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroyView() {
        super.onDestroyView();
        RecyclerViewDragDropManager recyclerViewDragDropManager = this.r;
        if (recyclerViewDragDropManager != null) {
            recyclerViewDragDropManager.release();
        }
        this.j.directionResponseResourceLiveData.removeObservers(this);
        LocationEngine locationEngine = this.o;
        if (locationEngine != null) {
            locationEngine.removeLocationUpdates(this);
        }
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
    public final void onFailure(@NonNull Exception exc) {
        exc.printStackTrace();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public final void onLowMemory() {
        super.onLowMemory();
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public final void onMapError(int i, String str) {
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public final void onMapReady(MapplsMap mapplsMap) {
        this.n = mapplsMap;
        this.f12594a = true;
        mapplsMap.clear();
        if (mapplsMap.getUiSettings() != null) {
            this.n.getUiSettings().setLogoMargins(0, 0, 0, 0);
        }
        mapplsMap.getStyle(new d());
    }

    @Override // androidx.fragment.app.Fragment
    public final void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        MapplsMap mapplsMap;
        if (i != 102 || iArr.length <= 0 || iArr[0] != 0 || (mapplsMap = this.n) == null) {
            return;
        }
        mapplsMap.getStyle(new e());
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
    public final void onSuccess(LocationEngineResult locationEngineResult) {
        LocationEngineResult locationEngineResult2 = locationEngineResult;
        if (locationEngineResult2 != null) {
            Location lastLocation = locationEngineResult2.getLastLocation();
            this.h = lastLocation;
            a(lastLocation);
        }
    }

    public final void r(DirectionsResponse directionsResponse) {
        this.i.errorLayout.setVisibility(8);
        this.i.notificationLayout.setVisibility(8);
        this.i.recyclerDirectionStep.setLayoutManager(new LinearLayoutManager(this.u));
        this.i.recyclerDirectionStep.setAdapter(this.l);
        this.i.bottomSheetShadow.setVisibility(8);
        this.i.progressBar.setVisibility(8);
        this.i.bottomSheetShadow.setVisibility(0);
        this.i.bottomSheetDetails.setBackgroundColor(0);
        this.i.bottomSheetDetails.setVisibility(0);
        this.i.recyclerDirectionStep.setVisibility(0);
        this.i.layoutTimeDetails.setVisibility(0);
        MapplsMap mapplsMap = this.n;
        if (mapplsMap != null && mapplsMap.getUiSettings() != null) {
            this.n.getUiSettings().setLogoMargins(0, 0, 0, 300);
        }
        DirectionsRoute directionsRoute = directionsResponse.routes().get(this.j.getSelectedIndex());
        List<DirectionsRoute> routes = directionsResponse.routes();
        List<Point> decode = directionsRoute.geometry() != null ? PolylineUtils.decode(directionsRoute.geometry(), 6) : null;
        if (decode == null) {
            decode = new ArrayList<>();
        }
        ArrayList arrayList = new ArrayList();
        for (Point point : decode) {
            arrayList.add(new LatLng(point.latitude(), point.longitude()));
        }
        List<RouteLeg> legs = routes.get(this.j.getSelectedIndex()).legs();
        this.q = new ArrayList();
        if (legs != null) {
            for (RouteLeg routeLeg : legs) {
                if (routeLeg.steps() != null) {
                    this.q.addAll(routeLeg.steps());
                }
            }
        }
        ArrayList arrayList2 = this.q;
        if (arrayList2 != null && arrayList2.size() > 0) {
            if (this.l == null) {
                com.mappls.sdk.direction.ui.adapters.b bVar = new com.mappls.sdk.direction.ui.adapters.b(this.u, this.t);
                this.l = bVar;
                this.i.recyclerDirectionStep.setAdapter(bVar);
            }
            this.l.a(this.q, this.j.getStopModels());
        }
        if (this.n != null && arrayList.size() >= 1) {
            this.n.animateCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds.Builder().includes(arrayList).build(), 20, 600, 20, 350));
        }
        s(directionsRoute);
    }

    public final void s(@NonNull DirectionsRoute directionsRoute) {
        String str;
        LegAnnotation annotation;
        if (directionsRoute.distance() == null || directionsRoute.distance() == null || directionsRoute.duration() == null) {
            return;
        }
        BottomSheetBehavior<RelativeLayout> from = BottomSheetBehavior.from(this.i.bottomSheetDetails);
        this.m = from;
        from.setHideable(false);
        this.m.setState(4);
        if (directionsRoute.legs() == null || directionsRoute.legs().size() <= 0 || (annotation = directionsRoute.legs().get(0).annotation()) == null) {
            this.i.textRoute.setTextColor(ContextCompat.getColor(this.u, R.color.mappls_direction_eta_text_color_with_out_traffic));
        } else {
            this.i.textRoute.setTextColor(ContextCompat.getColor(this.u, com.mappls.sdk.direction.ui.common.d.a(annotation.congestion())));
        }
        RouteClasses routeClasses = directionsRoute.routeClasses();
        ArrayList arrayList = new ArrayList();
        if (routeClasses != null) {
            if (routeClasses.toll() != null && routeClasses.toll().intValue() == 1) {
                arrayList.add("Toll");
            }
            if (routeClasses.ferry() != null && routeClasses.ferry().intValue() == 1) {
                arrayList.add("Ferry");
            }
            if (routeClasses.ferry() != null && routeClasses.ferry().intValue() == 1) {
                arrayList.add("Tunnel");
            }
            if (routeClasses.motorway() != null && routeClasses.motorway().intValue() == 1) {
                arrayList.add("Motorway");
            }
            if (arrayList.size() > 0) {
                StringBuilder sb = new StringBuilder("This route includes ");
                if (arrayList.size() == 1) {
                    sb.append((String) arrayList.get(0));
                } else {
                    int i = 0;
                    while (i < arrayList.size()) {
                        if (i == arrayList.size() - 1) {
                            str = " & ";
                        } else {
                            str = i != 0 ? ", " : ", ";
                            sb.append((String) arrayList.get(i));
                            i++;
                        }
                        sb.append(str);
                        sb.append((String) arrayList.get(i));
                        i++;
                    }
                }
                this.i.notificationLayout.setVisibility(0);
                this.i.tvClassNotification.setText(sb.toString());
                this.i.setRouteTime("" + com.mappls.sdk.direction.ui.common.d.c(directionsRoute.duration().doubleValue()));
                this.i.setArrival(com.mappls.sdk.direction.ui.common.d.a(directionsRoute.duration().doubleValue()));
                this.i.setDistance("" + com.mappls.sdk.direction.ui.common.d.b(directionsRoute.distance().doubleValue()));
                this.i.executePendingBindings();
                this.i.invalidateAll();
            }
        }
        this.i.notificationLayout.setVisibility(8);
        this.i.setRouteTime("" + com.mappls.sdk.direction.ui.common.d.c(directionsRoute.duration().doubleValue()));
        this.i.setArrival(com.mappls.sdk.direction.ui.common.d.a(directionsRoute.duration().doubleValue()));
        this.i.setDistance("" + com.mappls.sdk.direction.ui.common.d.b(directionsRoute.distance().doubleValue()));
        this.i.executePendingBindings();
        this.i.invalidateAll();
    }

    public final void u() {
        this.j.directionResponseResourceLiveData.observe(getViewLifecycleOwner(), new Observer() { // from class: com.mappls.sdk.direction.ui.fragment.b0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                l.this.o((com.mappls.sdk.direction.ui.common.a) obj);
            }
        });
        this.j.routeReportSummaryResourceLiveData.observe(getViewLifecycleOwner(), new Observer() { // from class: com.mappls.sdk.direction.ui.fragment.a0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                l.this.w((com.mappls.sdk.direction.ui.common.a) obj);
            }
        });
        this.j.costEstimationResponseLiveData.observe(getViewLifecycleOwner(), new Observer() { // from class: com.mappls.sdk.direction.ui.fragment.c0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                l.this.y((com.mappls.sdk.direction.ui.common.a) obj);
            }
        });
    }
}
