package com.mappls.sdk.navigation.refresh;

import android.os.Handler;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.navigation.routing.NavigationRoute;
import com.mappls.sdk.navigation.util.NavigationUtils;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh;
import com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefreshManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import okhttp3.HttpUrl;
/* loaded from: classes11.dex */
public final class e {
    public String b;
    public NavigationApplication d;
    public MapplsDirectionsRefreshManager e;
    public Integer f;

    /* renamed from: a  reason: collision with root package name */
    public final Handler f12935a = new Handler();
    public int c = 0;
    public final Runnable g = new a();

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            RouteOptions fromJson = RouteOptions.fromJson((String) e.this.d.k().f12956a.get());
            if (fromJson == null || !fromJson.resource().equalsIgnoreCase(DirectionsCriteria.RESOURCE_ROUTE_ETA)) {
                return;
            }
            e.h(e.this);
        }
    }

    public e(NavigationApplication navigationApplication) {
        this.d = navigationApplication;
    }

    public static NavigationRoute b(e eVar, DirectionsRoute directionsRoute) {
        DirectionsRoute directionsRoute2;
        eVar.getClass();
        ArrayList arrayList = new ArrayList();
        if (directionsRoute != null) {
            try {
            } catch (Exception e) {
                NavigationLogger.d(e);
                directionsRoute2 = null;
            }
            if (directionsRoute.geometry() == null) {
                return null;
            }
            String geometry = directionsRoute.geometry();
            Objects.requireNonNull(geometry);
            List<Point> coordinates = LineString.fromPolyline(geometry, 6).coordinates();
            for (int i = 0; i < coordinates.size(); i++) {
                NavLocation navLocation = new NavLocation("router");
                navLocation.setLatitude(coordinates.get(i).latitude());
                navLocation.setLongitude(coordinates.get(i).longitude());
                arrayList.add(navLocation);
            }
            DirectionsRoute.Builder builder = directionsRoute.toBuilder();
            builder.legs(NavigationUtils.mergeRouteLegs(directionsRoute));
            directionsRoute2 = builder.build();
            if (directionsRoute2 != null) {
                NavigationRoute navigationRoute = new NavigationRoute(directionsRoute2, arrayList, null, eVar.d.h().k().getParams(), null, true, eVar.d.h().k().getJunctionViews(), eVar.d.h().k().getEvents());
                navigationRoute.updateCurrentRoute(eVar.d.h().k().getCurrentRoute());
                return navigationRoute;
            }
            return null;
        }
        return null;
    }

    public static void h(e eVar) {
        int i;
        if (eVar.d.getLocationProvider().getLocationSimulation().a()) {
            return;
        }
        if (eVar.c != 2) {
            eVar.b = MapplsNavigationHelper.getInstance().getUuid();
            if ((eVar.d.h().s() || MapplsNavigationHelper.getInstance().getUuid() == null) && MapplsNavigationHelper.getInstance().getUuid() == null && (i = eVar.c) != 2 && i != 3) {
                return;
            }
        }
        if (eVar.d.k().f12956a.get() == null) {
            return;
        }
        RouteOptions fromJson = RouteOptions.fromJson((String) eVar.d.k().f12956a.get());
        try {
            if (eVar.b != null && fromJson != null && fromJson.baseUrl() != null) {
                if (HttpUrl.parse(fromJson.baseUrl()) == null) {
                    return;
                }
                if (!eVar.d.h().x()) {
                    eVar.g();
                    return;
                }
                MapplsDirectionsRefreshManager mapplsDirectionsRefreshManager = eVar.e;
                if (mapplsDirectionsRefreshManager != null) {
                    mapplsDirectionsRefreshManager.cancel();
                }
                AdviseInfo adviseInfo = MapplsNavigationHelper.getInstance().getAdviseInfo();
                int routeIndex = MapplsNavigationHelper.getInstance().getRouteIndex();
                if (MapplsNavigationHelper.getInstance().getCurrentRoute() != null && MapplsNavigationHelper.getInstance().getCurrentRoute().routeIndex() != null) {
                    routeIndex = MapplsNavigationHelper.getInstance().getCurrentRoute().routeIndex().intValue();
                }
                MapplsDirectionsRefresh.Builder tripType = MapplsDirectionsRefresh.builder().baseUrl(fromJson.baseUrl()).isRefresh(Boolean.TRUE).requestId(eVar.b).profile(fromJson.profile()).nodeIndex(Long.valueOf(MapplsNavigationHelper.getInstance().getNodeIndex())).routeIndex(Integer.valueOf(routeIndex)).sessionId(MapplsNavigationHelper.getInstance().getSessionId()).tripType(Integer.valueOf(eVar.c));
                if (adviseInfo != null && MapplsNavigationHelper.getInstance().isCallAlternativeDuringNavigation()) {
                    NavLocation navLocation = adviseInfo.getNavLocation();
                    if (navLocation != null) {
                        tripType.source(Point.fromLngLat(navLocation.getLongitude(), navLocation.getLatitude()));
                    }
                    eVar.d.p();
                }
                MapplsDirectionsRefreshManager newInstance = MapplsDirectionsRefreshManager.newInstance(tripType.build());
                eVar.e = newInstance;
                newInstance.call(new i(eVar, adviseInfo));
            }
        } catch (Exception e) {
            NavigationLogger.e(e);
        }
    }

    public final void a() {
        NavigationLogger.d("cancelTrip", new Object[0]);
        this.c = 3;
        this.f12935a.removeCallbacksAndMessages(null);
        this.f12935a.postDelayed(this.g, 0L);
    }

    public final void b() {
        NavigationLogger.d("endTrip", new Object[0]);
        this.c = 2;
        this.f12935a.removeCallbacksAndMessages(null);
        this.f12935a.postDelayed(this.g, 0L);
    }

    public final void c() {
        NavigationLogger.d("recalculated", new Object[0]);
        this.f12935a.removeCallbacksAndMessages(null);
        this.c = 0;
        if (this.d.h().q()) {
            this.f12935a.postDelayed(this.g, 0L);
        }
    }

    public final void e() {
        NavigationLogger.d("startTrip", new Object[0]);
        this.f12935a.removeCallbacksAndMessages(null);
        this.c = 1;
        if (this.d.h().q()) {
            this.f12935a.postDelayed(this.g, 0L);
        }
    }

    public final void g() {
        int i = this.c;
        if (i == 2 || i == 3) {
            this.f12935a.removeCallbacksAndMessages(null);
            MapplsDirectionsRefreshManager mapplsDirectionsRefreshManager = this.e;
            if (mapplsDirectionsRefreshManager != null) {
                mapplsDirectionsRefreshManager.cancel();
                return;
            }
            return;
        }
        this.c = 0;
        this.f12935a.removeCallbacksAndMessages(null);
        if (this.d.h().q()) {
            this.f12935a.postDelayed(this.g, ((Integer) this.d.k().b.get()).intValue() > 30000 ? ((Integer) this.d.k().b.get()).intValue() : 30000);
        }
    }
}
