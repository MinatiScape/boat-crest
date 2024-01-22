package com.mappls.sdk.navigation.routing;

import android.provider.Settings;
import com.clevertap.android.sdk.Constants;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.MapplsDirectionManager;
import com.mappls.sdk.services.api.directions.MapplsDirections;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public final class c {

    /* loaded from: classes11.dex */
    public enum a {
        /* JADX INFO: Fake field, exist only in values array */
        MAPMYINDIA("MAPMYINDIA"),
        OSRM("OSRM"),
        OSRM_MIREO("OSRM MIREO");
        

        /* renamed from: a  reason: collision with root package name */
        private final String f12948a;

        a(String str) {
            this.f12948a = str;
        }

        public final String a() {
            return this.f12948a;
        }
    }

    public static NavigationRoute a(b bVar) {
        NavigationRoute c;
        long currentTimeMillis = System.currentTimeMillis();
        if (bVar.f12947a != null && bVar.b != null) {
            StringBuilder a2 = com.mappls.sdk.navigation.h.a("Start finding route from ");
            a2.append(bVar.f12947a);
            a2.append(" to ");
            a2.append(bVar.b);
            a2.append(" using ");
            a2.append(bVar.f.a());
            NavigationLogger.i(a2.toString(), new Object[0]);
            try {
                a aVar = bVar.f;
                if (aVar != a.OSRM && aVar != a.OSRM_MIREO) {
                    c = new NavigationRoute("Selected route SERVICE is not available");
                    NavigationLogger.i("Finding route contained " + c.getImmutableAllLocations().size() + " points for " + (System.currentTimeMillis() - currentTimeMillis) + " ms", new Object[0]);
                    return c;
                }
                c = c(bVar);
                NavigationLogger.i("Finding route contained " + c.getImmutableAllLocations().size() + " points for " + (System.currentTimeMillis() - currentTimeMillis) + " ms", new Object[0]);
                return c;
            } catch (Exception e) {
                if (MapplsLMSManager.isInitialised()) {
                    MapplsLMSManager.getInstance().handledExceptions("calculateRouteImpl", "navigation-sdk", "0.13.6", e);
                }
                NavigationLogger.e(e, "Failed to find route ", new Object[0]);
            }
        }
        return new NavigationRoute(null);
    }

    public static DirectionsResponse b(NavigationApplication navigationApplication, Point point, String str, double d, List list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(point.longitude() + Constants.SEPARATOR_COMMA + point.longitude());
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(((LatLng) list.get(i)).getLongitude() + Constants.SEPARATOR_COMMA + ((LatLng) list.get(i)).getLatitude());
        }
        arrayList.add(str);
        double[][] dArr = (double[][]) Array.newInstance(double.class, arrayList.size(), 2);
        double[] dArr2 = new double[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (d > 0.0d) {
                double[] dArr3 = {0.0d, 180.0d};
                if (i2 == 0) {
                    dArr3[0] = (int) d;
                    dArr3[1] = 90.0d;
                    dArr[i2] = dArr3;
                } else {
                    dArr[i2] = dArr3;
                }
            }
            dArr2[i2] = 350.0d;
        }
        RouteOptions fromJson = RouteOptions.fromJson((String) navigationApplication.k().f12956a.get());
        MapplsDirections.Builder radiuses = MapplsDirections.builder().baseUrl(fromJson.baseUrl()).origin(point).destination(str).profile(fromJson.profile()).resource(fromJson.resource()).steps(fromJson.steps()).annotations(fromJson.annotations()).alternatives(fromJson.alternatives()).deviceId(Settings.Secure.getString(navigationApplication.getContentResolver(), "android_id")).overview("full").lessVerbose(fromJson.lessVerbose()).routeType(fromJson.routeType()).bannerInstructions(fromJson.bannerInstructions()).geometries(fromJson.geometries()).continueStraight(fromJson.continueStraight()).skipWaypoints(fromJson.skipWaypoints()).isSort(fromJson.isSort()).radiuses(dArr2);
        if (MapplsNavigationHelper.getInstance().getSessionId() != null) {
            radiuses.sessionId(MapplsNavigationHelper.getInstance().getSessionId());
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            LatLng latLng = (LatLng) it.next();
            radiuses.addWaypoint(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()));
        }
        if (fromJson.resource().equalsIgnoreCase(DirectionsCriteria.RESOURCE_ROUTE_ETA)) {
            radiuses.routeRefresh(Boolean.TRUE);
        }
        if (fromJson.roundaboutExits() != null) {
            radiuses.roundaboutExits(fromJson.roundaboutExits());
        }
        if (fromJson.exclude() != null) {
            radiuses.excludes(fromJson.exclude());
        }
        if (d > 0.0d) {
            for (int i3 = 0; i3 < dArr.length; i3++) {
                if (i3 == 0) {
                    radiuses.addBearing(Double.valueOf(dArr[i3][0]), Double.valueOf(dArr[i3][1]));
                } else {
                    radiuses.addBearing(null, null);
                }
            }
        }
        try {
            DirectionsResponse execute = MapplsDirectionManager.newInstance(radiuses.build()).execute();
            MapplsNavigationHelper.getInstance().setRouteIndex(0);
            return execute;
        } catch (Exception e) {
            NavigationLogger.e(e);
            if (MapplsLMSManager.isInitialised()) {
                MapplsLMSManager.getInstance().handledExceptions("getRouteCalculationResultOSRM", "navigation-sdk", "0.13.6", e);
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00b4 A[Catch: Exception -> 0x0310, TRY_LEAVE, TryCatch #0 {Exception -> 0x0310, blocks: (B:3:0x000d, B:5:0x001e, B:7:0x002e, B:14:0x0042, B:23:0x00b4, B:91:0x0302, B:15:0x0064, B:17:0x006e, B:19:0x0076), top: B:101:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0302 A[Catch: Exception -> 0x0310, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x0310, blocks: (B:3:0x000d, B:5:0x001e, B:7:0x002e, B:14:0x0042, B:23:0x00b4, B:91:0x0302, B:15:0x0064, B:17:0x006e, B:19:0x0076), top: B:101:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.mappls.sdk.navigation.routing.NavigationRoute c(com.mappls.sdk.navigation.routing.b r20) {
        /*
            Method dump skipped, instructions count: 826
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.routing.c.c(com.mappls.sdk.navigation.routing.b):com.mappls.sdk.navigation.routing.NavigationRoute");
    }
}
