package com.mappls.sdk.navigation;

import android.app.Service;
import android.content.res.Resources;
import android.location.Location;
import android.os.Handler;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.google.android.gms.common.Scopes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.BaseMapplsHelper;
import com.mappls.sdk.maps.CoordinateCallback;
import com.mappls.sdk.maps.CoordinateResult;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.data.WayPoint;
import com.mappls.sdk.navigation.gpx.GPXDataModel;
import com.mappls.sdk.navigation.iface.INavigationListener;
import com.mappls.sdk.navigation.iface.INavigationLoggingListener;
import com.mappls.sdk.navigation.iface.ISaveTrackListener;
import com.mappls.sdk.navigation.iface.ITrackRecordingListener;
import com.mappls.sdk.navigation.iface.JunctionInfoChangedListener;
import com.mappls.sdk.navigation.iface.JunctionViewsLoadedListener;
import com.mappls.sdk.navigation.iface.LocationChangedListener;
import com.mappls.sdk.navigation.iface.NavigationEventListener;
import com.mappls.sdk.navigation.iface.NavigationEventLoadedListener;
import com.mappls.sdk.navigation.iface.OnAuthentication;
import com.mappls.sdk.navigation.iface.OnSpeedLimitListener;
import com.mappls.sdk.navigation.iface.POIAlongTheRouteChangedListener;
import com.mappls.sdk.navigation.iface.TollEntryExitListener;
import com.mappls.sdk.navigation.iface.VoiceCommandListener;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.navigation.model.Junction;
import com.mappls.sdk.navigation.model.NavigationResponse;
import com.mappls.sdk.navigation.model.NavigationSummary;
import com.mappls.sdk.navigation.routing.IRecalculatedDirection;
import com.mappls.sdk.navigation.routing.NavigationRoute;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.navigation.routing.c;
import com.mappls.sdk.navigation.t;
import com.mappls.sdk.navigation.textinstructions.TextInstructionHelper;
import com.mappls.sdk.navigation.u;
import com.mappls.sdk.navigation.util.ErrorType;
import com.mappls.sdk.navigation.util.ManeuverInfo;
import com.mappls.sdk.navigation.util.MapplsNavigationMode;
import com.mappls.sdk.navigation.util.NavigationSummaryHelper;
import com.mappls.sdk.navigation.x;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.MapplsDirectionManager;
import com.mappls.sdk.services.api.directions.MapplsDirections;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.DirectionsWaypoint;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import com.mappls.sdk.services.api.event.route.model.RouteReportSummaryResponse;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;
/* loaded from: classes11.dex */
public class f {
    public static boolean E = false;

    /* renamed from: a  reason: collision with root package name */
    public VoiceCommandListener f12898a;
    public NavigationApplication b;
    public String e;
    public Class<?> f;
    public Class<Service> g;
    public DirectionsResponse i;
    public JunctionViewsLoadedListener k;
    public RouteReportSummaryResponse n;
    public v o;
    public IRecalculatedDirection r;
    public ITrackRecordingListener s;
    public Handler t;
    public String u;
    public String v;
    public int w;
    public String x;
    public OnSpeedLimitListener z;
    public boolean c = true;
    public boolean d = true;
    public boolean h = true;
    public NavigationEventLoadedListener j = null;
    public String l = "280X200";
    public String m = WeatherCriteria.UNIT_TYPE_DAY;
    public int p = 100;
    public com.mappls.sdk.navigation.routing.d q = null;
    public boolean y = true;
    public boolean A = false;
    public boolean B = false;
    public boolean C = true;
    public List<AlternateRoute> D = new ArrayList();

    /* loaded from: classes11.dex */
    public class a implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f12899a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;

        public a(int i, String str, String str2) {
            this.f12899a = i;
            this.b = str;
            this.c = str2;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public final void coordinateResultSuccess(List<CoordinateResult> list) {
            if (list == null || list.size() <= 0) {
                return;
            }
            f.this.addWayPoint(new LatLng(list.get(0).getLatitude().doubleValue(), list.get(0).getLongitude().doubleValue()), this.f12899a, this.b, this.c);
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public final void onFailure() {
        }
    }

    /* loaded from: classes11.dex */
    public class b extends u.b<Void, Object, NavigationResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ DirectionsResponse f12900a;
        public final /* synthetic */ int b;
        public final /* synthetic */ LatLng c;
        public final /* synthetic */ WayPoint d;
        public final /* synthetic */ List e;
        public final /* synthetic */ String f;
        public final /* synthetic */ boolean g;
        public final /* synthetic */ List h;
        public final /* synthetic */ OnAuthentication i;

        public b(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List list, String str, boolean z, List list2, OnAuthentication onAuthentication) {
            this.f12900a = directionsResponse;
            this.b = i;
            this.c = latLng;
            this.d = wayPoint;
            this.e = list;
            this.f = str;
            this.g = z;
            this.h = list2;
            this.i = onAuthentication;
        }

        @Override // com.mappls.sdk.navigation.u.b
        public final NavigationResponse a(Void[] voidArr) {
            return f.this.startNavigation(this.f12900a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }

        @Override // com.mappls.sdk.navigation.u.b
        public final void a(NavigationResponse navigationResponse) {
            NavigationResponse navigationResponse2 = navigationResponse;
            if (this.i != null) {
                if (navigationResponse2 == null || navigationResponse2.getError() == null) {
                    this.i.onSuccess();
                } else {
                    this.i.onFailure(navigationResponse2.getError().errorCode, navigationResponse2.getError().errorMessage, navigationResponse2.getException());
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        public final /* synthetic */ NavLocation h;

        public c(NavLocation navLocation) {
            this.h = navLocation;
        }

        @Override // java.lang.Runnable
        public final void run() {
            f.this.setCurrentLocation(this.h);
        }
    }

    public static boolean isNavigationRequestIsInProgress() {
        return E;
    }

    public final NavigationRoute a(@NonNull LatLng latLng, @NonNull String str, @NonNull DirectionsRoute directionsRoute, @Nullable List<WayPoint> list, @Nullable List<ReportDetails> list2) {
        ArrayList arrayList = new ArrayList();
        NavLocation navLocation = new NavLocation("router");
        navLocation.setLatitude(latLng.getLatitude());
        navLocation.setLongitude(latLng.getLongitude());
        try {
            List<Point> coordinates = LineString.fromPolyline(directionsRoute.geometry(), 6).coordinates();
            NavigationLogger.d("Points = %d", Integer.valueOf(coordinates.size()));
            for (int i = 0; i < coordinates.size(); i++) {
                NavLocation navLocation2 = new NavLocation("router");
                navLocation2.setLatitude(coordinates.get(i).latitude());
                navLocation2.setLongitude(coordinates.get(i).longitude());
                arrayList.add(navLocation2);
            }
        } catch (Exception e) {
            NavigationLogger.e(e);
        }
        ArrayList arrayList2 = null;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        if (list != null && list.size() > 0) {
            arrayList2 = new ArrayList();
            for (WayPoint wayPoint : list) {
                arrayList2.add(new LatLng(wayPoint.getEntryLatitude().doubleValue(), wayPoint.getEntryLongitude().doubleValue()));
                com.mappls.sdk.navigation.data.a aVar = new com.mappls.sdk.navigation.data.a(wayPoint.getLatitude().doubleValue(), wayPoint.getLongitude().doubleValue(), wayPoint.getEntryLatitude().doubleValue(), wayPoint.getEntryLongitude().doubleValue());
                aVar.a(wayPoint.getVisualName());
                aVar.b(wayPoint.getSpokenName());
                arrayList4.add(new x.a(new LatLng(wayPoint.getEntryLatitude().doubleValue(), wayPoint.getEntryLongitude().doubleValue()), aVar));
            }
        }
        com.mappls.sdk.navigation.routing.b bVar = new com.mappls.sdk.navigation.routing.b();
        bVar.f12947a = navLocation;
        bVar.b = str;
        bVar.c = arrayList2;
        d a2 = this.b.h().a();
        bVar.g = ((t.l) this.b.k().x0.get()).f12958a;
        ((Boolean) this.b.k().y.a(a2)).booleanValue();
        bVar.f = (c.a) this.b.k().r.get();
        bVar.e = a2;
        bVar.d = this.b;
        try {
            List<DirectionsWaypoint> subList = this.i.waypoints().subList(1, this.i.waypoints().size() - 1);
            if (subList != null) {
                for (DirectionsWaypoint directionsWaypoint : subList) {
                    arrayList3.add(new LatLng(directionsWaypoint.location().latitude(), directionsWaypoint.location().longitude()));
                }
            }
            bVar.c = arrayList3;
        } catch (Exception e2) {
            NavigationLogger.e(e2);
        }
        return new NavigationRoute(directionsRoute, arrayList, null, bVar, arrayList4, true, null, list2);
    }

    public void addJunctionInfoChangedListener(JunctionInfoChangedListener junctionInfoChangedListener) {
        this.b.w.a(junctionInfoChangedListener);
    }

    public void addLocationChangeListener(LocationChangedListener locationChangedListener) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.getLocationProvider().addLocationChangeListener(locationChangedListener);
    }

    public void addNavigationEventListener(NavigationEventListener navigationEventListener) {
        this.b.w.a(navigationEventListener);
    }

    public void addNavigationListener(INavigationListener iNavigationListener) {
        if (this.b == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        com.mappls.sdk.navigation.routing.d dVar = this.q;
        if (dVar != null) {
            dVar.a(iNavigationListener);
        }
    }

    public void addWayPoint(LatLng latLng, int i, String str) {
        addWayPoint(latLng, i, str, str);
    }

    public void addWayPoint(LatLng latLng, int i, String str, String str2) {
        if (this.b == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        com.mappls.sdk.navigation.data.a aVar = new com.mappls.sdk.navigation.data.a(GeoCodingCriteria.POD_POINT_OF_INTEREST, str);
        aVar.a(latLng.getLatitude());
        aVar.b(latLng.getLongitude());
        aVar.b(str2);
        this.b.m().a(new LatLng(latLng.getLatitude(), latLng.getLongitude()), true, i, aVar);
        NavigationApplication navigationApplication = this.b;
        navigationApplication.q.a(NavigationLocationProvider.convertLocation(navigationApplication.c, navigationApplication));
        recalculateRoute();
    }

    public void addWayPoint(String str, int i, String str2) {
        addWayPoint(str, i, str2, str2);
    }

    public void addWayPoint(String str, int i, String str2, String str3) {
        if (this.b == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        try {
            Object newInstance = BaseMapplsHelper.class.newInstance();
            Method declaredMethod = BaseMapplsHelper.class.getDeclaredMethod("getAnnotation", List.class, CoordinateCallback.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(newInstance, str, new a(i, str2, str3));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            NavigationLogger.d(e);
        }
    }

    public void addWayPoint(List<WayPoint> list, int i) {
        if (this.b == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        ArrayList arrayList = new ArrayList();
        for (WayPoint wayPoint : list) {
            com.mappls.sdk.navigation.data.a aVar = new com.mappls.sdk.navigation.data.a(GeoCodingCriteria.POD_POINT_OF_INTEREST, wayPoint.getVisualName());
            aVar.a(wayPoint.getEntryLatitude().doubleValue());
            aVar.b(wayPoint.getEntryLongitude().doubleValue());
            aVar.b(wayPoint.getSpokenName());
            arrayList.add(aVar);
        }
        this.b.u.a(arrayList, i);
        NavigationApplication navigationApplication = this.b;
        navigationApplication.q.a(NavigationLocationProvider.convertLocation(navigationApplication.c, navigationApplication));
        recalculateRoute();
    }

    public void announceCurrentDirection() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        try {
            this.b.q.o().a(navigationApplication.getLocationProvider().getLastKnownLocation());
        } catch (Exception e) {
            NavigationLogger.d(e);
        }
    }

    public final NavigationRoute b(@NonNull DirectionsRoute directionsRoute) {
        ArrayList arrayList = new ArrayList();
        try {
            List<Point> coordinates = LineString.fromPolyline(directionsRoute.geometry(), 6).coordinates();
            NavigationLogger.d("Points = %d", Integer.valueOf(coordinates.size()));
            for (int i = 0; i < coordinates.size(); i++) {
                NavLocation navLocation = new NavLocation("router");
                navLocation.setLatitude(coordinates.get(i).latitude());
                navLocation.setLongitude(coordinates.get(i).longitude());
                arrayList.add(navLocation);
            }
        } catch (Exception e) {
            NavigationLogger.e(e);
        }
        return new NavigationRoute(directionsRoute, arrayList, null, this.b.h().k().getParams(), this.b.h().k().getLocationPoints(), true, null, null);
    }

    public final synchronized void c(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list, boolean z, List<ReportDetails> list2) {
        if (this.b == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        if (list != null && directionsResponse.waypoints().size() - 2 == list.size()) {
            for (int i2 = 1; i2 <= directionsResponse.waypoints().size() - 2; i2++) {
                int i3 = i2 - 1;
                list.get(i3).setEntryLatitude(directionsResponse.waypoints().get(i2).location().latitude());
                list.get(i3).setEntryLongitude(directionsResponse.waypoints().get(i2).location().longitude());
                list.get(i3).setLatitude(Double.valueOf(directionsResponse.waypoints().get(i2).location().latitude()));
                list.get(i3).setLongitude(Double.valueOf(directionsResponse.waypoints().get(i2).location().longitude()));
            }
        }
        this.b.h().a(d.i);
        this.q.a(false);
        com.mappls.sdk.navigation.routing.d h = this.b.h();
        com.mappls.sdk.navigation.routing.h o = h.o();
        boolean booleanValue = ((Boolean) this.b.k().l0.get()).booleanValue();
        o.getClass();
        com.mappls.sdk.navigation.routing.h.a(booleanValue);
        if (!h.q()) {
            ((t.g) this.b.k().E0).set(h.a());
            this.b.k().o0.set(Boolean.TRUE);
            h.a(true);
            h.b(false);
            this.b.h().getClass();
            NavLocation navLocation = null;
            if (directionsResponse == null) {
                h.a(this.b.getLocationProvider().getLastKnownLocation(), false);
            } else {
                setDirectionsResponse(directionsResponse);
                setSessionId(directionsResponse.sessionId());
                setUuid(directionsResponse.uuid());
                setRouteIndex(i);
                DirectionsRoute selectedTrip = getSelectedTrip(directionsResponse, i);
                if (selectedTrip.routeOptions() != null) {
                    this.b.k().f12956a.set(selectedTrip.routeOptions().toJson());
                }
                NavLocation lastKnownLocation = this.b.getLocationProvider().getLastKnownLocation();
                LatLng latLng2 = latLng != null ? latLng : lastKnownLocation != null ? new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()) : null;
                NavLocation navLocation2 = new NavLocation("");
                if (latLng2 != null) {
                    navLocation2.setLatitude(latLng2.getLatitude());
                    navLocation2.setLongitude(latLng2.getLongitude());
                }
                h.a(navLocation2, a(latLng2, wayPoint.isValidCoordinates() ? wayPoint.getEntryLongitude() + Constants.SEPARATOR_COMMA + wayPoint.getEntryLatitude() : wayPoint.getMapplsPin(), selectedTrip, list, list2), false);
                navLocation = navLocation2;
            }
            this.b.m().b();
            if (wayPoint.isValidCoordinates()) {
                com.mappls.sdk.navigation.data.a aVar = new com.mappls.sdk.navigation.data.a(wayPoint.getEntryLatitude().doubleValue(), wayPoint.getEntryLongitude().doubleValue());
                aVar.b(wayPoint.getSpokenName());
                aVar.a(wayPoint.getVisualName());
                this.b.m().a(new LatLng(wayPoint.getEntryLatitude().doubleValue(), wayPoint.getEntryLongitude().doubleValue()), false, -1, aVar);
            } else {
                com.mappls.sdk.navigation.data.a aVar2 = new com.mappls.sdk.navigation.data.a(GeoCodingCriteria.POD_POINT_OF_INTEREST, wayPoint.getVisualName());
                aVar2.b(wayPoint.getSpokenName());
                aVar2.a(wayPoint.getVisualName());
                this.b.m().a(wayPoint.getMapplsPin(), false, aVar2);
            }
            if (list != null && list.size() > 0) {
                for (int i4 = 0; i4 < list.size(); i4++) {
                    if (list.get(i4).isValidCoordinates()) {
                        com.mappls.sdk.navigation.data.a aVar3 = new com.mappls.sdk.navigation.data.a(FirebaseAnalytics.Param.DESTINATION, list.get(i4).getVisualName());
                        aVar3.b(list.get(i4).getSpokenName());
                        this.b.k().a(list.get(i4).getEntryLatitude().doubleValue(), list.get(i4).getEntryLongitude().doubleValue(), aVar3, i4);
                    } else {
                        com.mappls.sdk.navigation.data.a aVar4 = new com.mappls.sdk.navigation.data.a(FirebaseAnalytics.Param.DESTINATION, list.get(i4).getVisualName());
                        aVar4.b(list.get(i4).getSpokenName());
                        this.b.k().a(list.get(i4).getMapplsPin(), aVar4, i4);
                    }
                }
            }
            this.b.m().b(true);
            this.b.a(new c(navLocation));
        } else if (((t.g) this.b.k().E0).get() != h.a()) {
            ((t.g) this.b.k().E0).set(h.a());
        }
    }

    public void enableFasterRouteAvailable(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().z0.set(Integer.valueOf(z ? 1 : 0));
    }

    public boolean enableFasterRouteAvailable() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Integer) navigationApplication.k().z0.get()).intValue() != 0;
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public void enableNearbyPoiAlongRoute(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().D.set(Boolean.valueOf(z));
    }

    public void extendNotification(NotificationCompat.Builder builder) {
        throw null;
    }

    public AdviseInfo getAdviseInfo() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            NavigationRoute.a aVar = new NavigationRoute.a();
            int i = 0;
            navigationApplication.q.a(aVar, false);
            String format = DateFormat.getTimeInstance(3).format(new Date(System.currentTimeMillis() + (this.b.q.j() * 1000)));
            try {
                if (aVar.f12943a == null) {
                    return null;
                }
                AdviseInfo adviseInfo = new AdviseInfo();
                adviseInfo.setText(aVar.f12943a.getDescriptionRoutePart());
                adviseInfo.setDistanceToNextAdvise(aVar.b);
                adviseInfo.setLeftDistance(this.b.q.h());
                adviseInfo.setEta(format);
                adviseInfo.setManeuverID(aVar.f12943a.getManeuverID());
                adviseInfo.setShortText(aVar.f12943a.getShortInstruction());
                adviseInfo.setRouteBeingRecalculated(this.b.q.s());
                adviseInfo.setLeftTime(this.b.q.j());
                if (aVar.b > 0 && aVar.f12943a.getAverageSpeed() > 0.0f) {
                    i = (int) (aVar.b / aVar.f12943a.getAverageSpeed());
                }
                adviseInfo.setLeftTimeStep(i);
                adviseInfo.setInfo(aVar.f12943a.getExtraInfo());
                adviseInfo.setNextInstructionInfo(aVar.f12943a.getNextExtraInfo());
                NavigationStep navigationStep = aVar.f12943a;
                adviseInfo.setNextInstructionText((navigationStep == null || navigationStep.getNextExtraInfo() == null) ? null : TextInstructionHelper.getInstance().getInstruction((LegStep) aVar.f12943a.getNextExtraInfo()));
                adviseInfo.setManeuverID(aVar.f12943a.getManeuverID());
                adviseInfo.setShortText(aVar.f12943a.getShortInstruction());
                NavLocation convertLocation = this.b.getCurrentLocation() != null ? NavigationLocationProvider.convertLocation(this.b.getCurrentLocation(), this.b) : null;
                if (convertLocation == null) {
                    convertLocation = this.b.getLocationProvider().getLastKnownLocation();
                }
                if (convertLocation == null) {
                    convertLocation = this.b.q.f();
                }
                adviseInfo.setLocation(convertLocation);
                return adviseInfo;
            } catch (Exception e) {
                NavigationLogger.e(e);
                return null;
            }
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public List<NavLocation> getAllRouteLocations() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.q.k().getImmutableAllLocations();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public List<AlternateRoute> getAlternateRoutes() {
        return this.D;
    }

    public List<String> getAvoidanceSettings() {
        if (this.b.k().f12956a.get() == null) {
            return null;
        }
        RouteOptions fromJson = RouteOptions.fromJson((String) this.b.k().f12956a.get());
        if (fromJson.exclude() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        String exclude = fromJson.exclude();
        if (exclude.contains(Constants.SEPARATOR_COMMA)) {
            for (String str : exclude.split(Constants.SEPARATOR_COMMA)) {
                arrayList.add(str);
            }
        } else {
            arrayList.add(exclude);
        }
        return arrayList;
    }

    public String getBaseRes() {
        return this.e;
    }

    public Long getCurrentNodeId() {
        if (this.b != null) {
            try {
                DirectionsRoute currentRoute = MapplsNavigationHelper.getInstance().getCurrentRoute();
                if (currentRoute != null && currentRoute.legs() != null && !currentRoute.legs().isEmpty() && currentRoute.legs().get(0).annotation() != null && currentRoute.legs().get(0).annotation().nodes() != null && !currentRoute.legs().get(0).annotation().nodes().isEmpty() && currentRoute.legs().get(0).annotation().nodes().size() > this.b.h().k().getCurrentRoute()) {
                    return currentRoute.legs().get(0).annotation().nodes().get(this.b.h().k().getCurrentRoute());
                }
                return -1L;
            } catch (Exception unused) {
                return -1L;
            }
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public DirectionsRoute getCurrentRoute() {
        return this.q.k().directionsRoute;
    }

    public com.mappls.sdk.navigation.routing.b getDefaultParams() {
        com.mappls.sdk.navigation.routing.b bVar = new com.mappls.sdk.navigation.routing.b();
        d dVar = d.i;
        bVar.g = ((t.l) this.b.k().x0.get()).f12958a;
        ((Boolean) this.b.k().y.a(dVar)).booleanValue();
        bVar.f = (c.a) this.b.k().r.get();
        bVar.e = dVar;
        bVar.d = this.b;
        return bVar;
    }

    public String getDeviceId() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            if (this.x == null) {
                this.x = Settings.Secure.getString(navigationApplication.getContentResolver(), "android_id");
            }
            return this.x;
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public DirectionsResponse getDirectionsResponse() {
        return this.i;
    }

    public List<ReportDetails> getEvents() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            if (navigationApplication.q.k() != null) {
                return this.b.q.k().getEvents();
            }
            return null;
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public NavLocation getFirstLocation() {
        if (this.b != null) {
            if (this.q.k().getCurrentRoute() < this.q.d().size()) {
                return this.q.d().get(this.q.k().getCurrentRoute());
            }
            return null;
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public String getJunctionViewImageSize() {
        return this.l;
    }

    public String getJunctionViewMode() {
        return this.m;
    }

    public String getJunctionViewURL(String str, boolean z) {
        String str2 = z ? WeatherCriteria.UNIT_TYPE_DAY : "night";
        return "https://cdn.mappls.com/jvimage/280X200/" + str2 + MqttTopic.TOPIC_LEVEL_SEPARATOR + str;
    }

    public List<Junction> getJunctionViews() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.q.k().getJunctionViews();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public JunctionViewsLoadedListener getJunctionViewsLoadedListener() {
        return this.k;
    }

    public int getLeftDistance() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.q.h();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public int getLeftTime() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.q.j();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public ManeuverInfo getManeuverInfo(NavigationApplication navigationApplication, int i) {
        ManeuverInfo maneuverInfo = new ManeuverInfo();
        if (i >= 0) {
            Resources resources = navigationApplication.getResources();
            int identifier = resources.getIdentifier("step_" + i, "drawable", navigationApplication.getPackageName());
            Resources resources2 = navigationApplication.getResources();
            int identifier2 = resources2.getIdentifier("step_" + i, "string", navigationApplication.getPackageName());
            maneuverInfo.setIcon(AppCompatResources.getDrawable(navigationApplication, identifier));
            maneuverInfo.setInfoText(navigationApplication.getResources().getString(identifier2));
        }
        return maneuverInfo;
    }

    public Class<?> getNavigationActivityClass() {
        return this.f;
    }

    public Class<Service> getNavigationCarAppServiceClass() {
        return this.g;
    }

    public NavigationEventLoadedListener getNavigationEventLoadedListener() {
        return this.j;
    }

    public MapplsNavigationMode getNavigationMode() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.k().q.get();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public List<NavigationStep> getNavigationSteps() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.q.k().getImmutableAllDirections() == null ? new ArrayList() : this.b.q.k().getImmutableAllDirections();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public NavigationSummary getNavigationSummary() {
        NavigationSummaryHelper.Companion companion = NavigationSummaryHelper.Companion;
        return new NavigationSummary(companion.getInstance().getAverageSpeed(), companion.getInstance().getTotalDistance(), companion.getInstance().getTotalTimeTaken());
    }

    public int getNodeIndex() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.q.k().getCurrentRoute();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public int getOffRouteThreshold() {
        return this.p;
    }

    public OnSpeedLimitListener getOnSpeedLimitListener() {
        return this.z;
    }

    public int getRouteIndex() {
        return this.w;
    }

    public List<NavLocation> getRouteLocations() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.q.k().getRouteLocations();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public RouteReportSummaryResponse getRouteReportSummaryResponse() {
        return this.n;
    }

    public IRecalculatedDirection getRouteService() {
        return this.r;
    }

    public ArrayList<GPXDataModel> getSavedTracks() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.m.getGPXTracks();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public NavLocation getSecondLocation() {
        if (this.b != null) {
            return this.q.d().get(this.q.k().getCurrentRoute() + 1);
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public DirectionsRoute getSelectedTrip(DirectionsResponse directionsResponse, int i) {
        DirectionsRoute directionsRoute;
        DirectionsRoute directionsRoute2 = null;
        try {
            for (DirectionsRoute directionsRoute3 : directionsResponse.routes()) {
                if (directionsRoute3 != null && directionsRoute3.routeIndex() != null && directionsRoute3.routeIndex().intValue() == i) {
                    return directionsRoute3;
                }
                if (directionsRoute2 == null) {
                    if (directionsResponse.routes().size() > i) {
                        directionsRoute = directionsResponse.routes().get(i);
                    } else if (directionsResponse.routes().size() > 0) {
                        directionsRoute = directionsResponse.routes().get(0);
                    }
                    directionsRoute2 = directionsRoute;
                }
            }
            return directionsRoute2;
        } catch (Exception e) {
            NavigationLogger.d(e);
            return directionsRoute2;
        }
    }

    public String getSessionId() {
        return this.v;
    }

    public TollEntryExitListener getTollEntryExitListener() {
        return this.b.q.n();
    }

    public String getUuid() {
        return this.u;
    }

    public VoiceCommandListener getVoiceCommandListener() {
        return this.f12898a;
    }

    public List<WayPoint> getWayPoints() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.b.u.e().iterator();
        while (it.hasNext()) {
            x.a aVar = (x.a) it.next();
            WayPoint wayPoint = new WayPoint(aVar.b(), aVar.c(), aVar.getLatitude(), aVar.getLongitude(), aVar.a(), aVar.d());
            wayPoint.setIndexOnPath(aVar.c);
            arrayList.add(wayPoint);
        }
        Timber.d("Waypoint Size = %d", Integer.valueOf(arrayList.size()));
        for (int i = 0; i < arrayList.size(); i++) {
            Timber.d("index = %d", Integer.valueOf(i));
            ((WayPoint) arrayList.get(i)).setIndex(this.b.q.k().getIndexOfIntermediatePoint(((WayPoint) arrayList.get(i)).getIndexOnPath()));
        }
        return arrayList;
    }

    public int getWholeDistance() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.q.k().getWholeDistance();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public void init(NavigationApplication navigationApplication) {
        if (this.b == null) {
            this.b = navigationApplication;
        }
        this.o = v.a().c().g().a().f().d().e().b(Settings.Secure.getString(navigationApplication.getContentResolver(), "android_id")).a(MapplsUtils.join(Constants.SEPARATOR_COMMA, new String[]{DirectionsCriteria.ANNOTATION_CONGESTION, DirectionsCriteria.ANNOTATION_NODES, "duration"})).b();
    }

    public boolean isCallAlternativeDuringNavigation() {
        return this.B;
    }

    public boolean isCloseServiceOnRemovingTask() {
        return this.y;
    }

    public boolean isCurrentMetricSystemKm() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((t.s) navigationApplication.k().y0.get()).name().equalsIgnoreCase("KILOMETERS_AND_METERS");
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public boolean isEnableInstructionsFromAPI() {
        return this.A;
    }

    public boolean isEnableNearbyPoiAlongRoute() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().D.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public boolean isFollowingMode() {
        return this.q.q();
    }

    public boolean isGPSCheckEnableForLocationChange() {
        return this.C;
    }

    public boolean isInterruptMusicForNavigationInstructions() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.k().t.get().booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public boolean isJunctionViewEnabled() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().G.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public boolean isLoggingEnabled() {
        return ((Boolean) this.b.k().f.get()).booleanValue();
    }

    public boolean isMute() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().l0.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public boolean isNavigating() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.q.q() || this.b.q.u() || this.b.q.s();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public boolean isNavigationEventAudioPromptEnabled() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().I.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public boolean isNavigationEventEnabled() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().H.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public boolean isNightMode() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return navigationApplication.p.b();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public boolean isPlayAsVoiceCall() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Integer) navigationApplication.k().h0.get()).intValue() == 0;
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public boolean isPlayDuringPhoneCallEnabled() {
        return this.d;
    }

    public boolean isRecording() {
        if (((com.mappls.sdk.navigation.tracks.a) s.a()) == null) {
            return false;
        }
        return this.b.k().u.get().booleanValue();
    }

    public boolean isRoutePlanningMode() {
        return this.q.u();
    }

    public boolean isSetFasterRouteImmidiately() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Integer) navigationApplication.k().z0.get()).intValue() == 2;
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public boolean isShortestRoute() {
        if (this.b.k().f12956a.get() == null) {
            return false;
        }
        RouteOptions fromJson = RouteOptions.fromJson((String) this.b.k().f12956a.get());
        return fromJson.routeType() != null && fromJson.routeType().intValue() == 1;
    }

    public boolean isShouldPlayNavigationInstructions() {
        return this.h;
    }

    public boolean isTrafficProbeEnabled() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().P.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public void navigateTo(LatLng latLng, String str) {
        navigateTo(latLng, str, str);
    }

    public void navigateTo(LatLng latLng, String str, String str2) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.m().a(true);
        com.mappls.sdk.navigation.data.a aVar = new com.mappls.sdk.navigation.data.a(GeoCodingCriteria.POD_POINT_OF_INTEREST, str);
        aVar.a(latLng.getLatitude());
        aVar.b(latLng.getLongitude());
        aVar.b(str2);
        this.b.m().a(new LatLng(latLng.getLatitude(), latLng.getLongitude()), true, -1, aVar);
        NavigationApplication navigationApplication2 = this.b;
        navigationApplication2.q.a(NavigationLocationProvider.convertLocation(navigationApplication2.c, navigationApplication2));
        recalculateRoute();
    }

    public void navigateTo(String str, String str2) {
        navigateTo(str, str2, str2);
    }

    public void navigateTo(String str, String str2, String str3) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.u.a(true);
        com.mappls.sdk.navigation.data.a aVar = new com.mappls.sdk.navigation.data.a(GeoCodingCriteria.POD_POINT_OF_INTEREST, str2);
        aVar.b(str3);
        this.b.u.a(str, true, aVar);
        NavigationApplication navigationApplication2 = this.b;
        navigationApplication2.q.a(NavigationLocationProvider.convertLocation(navigationApplication2.c, navigationApplication2));
        recalculateRoute();
    }

    public void recalculateRoute() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.q.v();
    }

    public void removeJunctionInfoChangedListener(JunctionInfoChangedListener junctionInfoChangedListener) {
        this.b.w.b(junctionInfoChangedListener);
    }

    public void removeLocationChangeListener(LocationChangedListener locationChangedListener) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.getLocationProvider().removeLocationChangeListener(locationChangedListener);
    }

    public void removeNavigationEventListener(NavigationEventListener navigationEventListener) {
        this.b.w.b(navigationEventListener);
    }

    public void removeNavigationListener(INavigationListener iNavigationListener) {
        if (this.b == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        com.mappls.sdk.navigation.routing.d dVar = this.q;
        if (dVar != null) {
            dVar.b(iNavigationListener);
        }
    }

    public void removeWayPoint(int i) {
        this.b.u.a(i, true);
        recalculateRoute();
    }

    public void replaceRoute(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list) {
        c(directionsResponse, i, latLng, wayPoint, list, true, null);
    }

    public void replaceWayPoints(List<WayPoint> list) {
        if (this.b == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        ArrayList arrayList = new ArrayList();
        for (WayPoint wayPoint : list) {
            com.mappls.sdk.navigation.data.a aVar = new com.mappls.sdk.navigation.data.a(GeoCodingCriteria.POD_POINT_OF_INTEREST, wayPoint.getVisualName());
            aVar.a(wayPoint.getEntryLatitude().doubleValue());
            aVar.b(wayPoint.getEntryLongitude().doubleValue());
            aVar.b(wayPoint.getSpokenName());
            arrayList.add(aVar);
        }
        this.b.u.a(arrayList);
        NavigationApplication navigationApplication = this.b;
        navigationApplication.q.a(NavigationLocationProvider.convertLocation(navigationApplication.c, navigationApplication));
        recalculateRoute();
    }

    public void saveCurrentTrack() {
        saveCurrentTrack(null, null);
    }

    public void saveCurrentTrack(String str, ISaveTrackListener iSaveTrackListener) {
        com.mappls.sdk.navigation.tracks.a aVar = (com.mappls.sdk.navigation.tracks.a) s.a();
        if (aVar == null) {
            return;
        }
        aVar.a(str, iSaveTrackListener);
    }

    public void setAlternateRoutes(List<AlternateRoute> list) {
        this.D = list;
    }

    public void setAvailableBetterRoute(List<DirectionsRoute> list) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.q.a(b(list.get(0)), list.get(0));
    }

    public void setAvoidanceSetting(List<String> list) {
        if (this.b.k().f12956a.get() == null) {
            return;
        }
        if (list == null || list.size() <= 0) {
            this.b.k().f12956a.set(RouteOptions.fromJson((String) this.b.k().f12956a.get()).toBuilder().exclude(null).build().toJson());
        } else {
            this.b.k().f12956a.set(RouteOptions.fromJson((String) this.b.k().f12956a.get()).toBuilder().exclude(MapplsUtils.join(Constants.SEPARATOR_COMMA, list.toArray())).build().toJson());
        }
        recalculateRoute();
    }

    public void setBaseUrl(String str) {
        this.e = str;
    }

    public void setCallAlternativeDuringNavigation(boolean z) {
        this.B = z;
    }

    public void setCloseServiceOnRemovingTask(boolean z) {
        this.y = z;
    }

    public void setCurrentLocation(Location location) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        if (location == null) {
            return;
        }
        navigationApplication.setCurrentLocation(location);
        this.b.getLocationProvider().u(location, NavigationLocationProvider.convertLocation(location, this.b), true);
    }

    public void setCurrentLocation(NavLocation navLocation) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        if (navLocation == null) {
            return;
        }
        navigationApplication.getLocationProvider().u(NavigationLocationProvider.revertLocation(navLocation, this.b), navLocation, true);
    }

    public void setDirectionsResponse(DirectionsResponse directionsResponse) {
        this.i = directionsResponse;
    }

    public void setEnableInstructionsFromAPI(boolean z) {
        this.A = z;
    }

    public void setEventAudioPromptBefore(int i) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        if (i >= 50) {
            navigationApplication.k().e.set(Integer.valueOf(i));
            return;
        }
        Timber.d("distance can not be less than 50; setting audio prompt distance to 50", new Object[0]);
        this.b.k().e.set(50);
    }

    public void setEventVisualPromptBefore(int i) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        if (i >= 50) {
            navigationApplication.k().d.set(Integer.valueOf(i));
            return;
        }
        Timber.d("distance can not be less than 50; setting event visual prompt distance to 50", new Object[0]);
        this.b.k().d.set(50);
    }

    public void setEvents(List<ReportDetails> list) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.q.k().setEvents(list);
    }

    public void setFasterRouteImmidiately(boolean z) {
        if (this.b == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        if (!enableFasterRouteAvailable()) {
            throw new IllegalStateException("Please enable Faster Route Available Setting");
        }
        this.b.k().z0.set(Integer.valueOf(z ? 2 : 1));
    }

    public void setGPSCheckEnableForLocationChange(boolean z) {
        this.C = z;
    }

    public void setInterruptMusicForNavigationInstructions(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().t.a(d.i, (d) Boolean.valueOf(z));
    }

    @Deprecated
    public void setJunctionInfoChangedListener(JunctionInfoChangedListener junctionInfoChangedListener) {
        this.b.w.c(junctionInfoChangedListener);
    }

    public void setJunctionViewEnabled(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().G.set(Boolean.valueOf(z));
        this.b.v.a(z);
    }

    public void setJunctionViewImageSize(int i, int i2) {
        String str = i + "X" + i2;
        if (this.l.equalsIgnoreCase(str)) {
            return;
        }
        this.l = str;
        if (isNavigating()) {
            recalculateRoute();
        }
    }

    public void setJunctionViewMode(String str) {
        if (this.m.equalsIgnoreCase(str)) {
            return;
        }
        this.m = str;
        if (isNavigating()) {
            recalculateRoute();
        }
    }

    public void setJunctionViewsLoadedListener(JunctionViewsLoadedListener junctionViewsLoadedListener) {
        this.k = junctionViewsLoadedListener;
    }

    public void setJunctionVisualPromptBefore(int i) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        if (i >= 50) {
            navigationApplication.k().g.set(Integer.valueOf(i));
            return;
        }
        Timber.d("distance can not be less than 50; setting junction visual prompt distance to 50", new Object[0]);
        this.b.k().g.set(50);
    }

    public void setLoggingEnable(boolean z) {
        this.b.k().f.set(Boolean.valueOf(z));
    }

    public void setMetricSystemToKM(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().y0.set(z ? t.s.KILOMETERS_AND_METERS : t.s.MILES_AND_FEET);
    }

    public void setMute(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.q.o().getClass();
        com.mappls.sdk.navigation.routing.h.a(z);
        this.b.k().l0.set(Boolean.valueOf(z));
        if (z) {
            this.b.s.j();
        }
    }

    public void setNavigationActivityClass(Class<?> cls) {
        this.f = cls;
    }

    public void setNavigationCarAppServiceClass(Class<Service> cls) {
        this.g = cls;
    }

    public void setNavigationEventAudioPromptEnabled(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().I.set(Boolean.valueOf(z));
    }

    public void setNavigationEventEnabled(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().H.set(Boolean.valueOf(z));
        this.b.v.b(z);
    }

    @Deprecated
    public void setNavigationEventListener(NavigationEventListener navigationEventListener) {
        this.b.w.c(navigationEventListener);
    }

    public void setNavigationEventLoadedListener(NavigationEventLoadedListener navigationEventLoadedListener) {
        this.j = navigationEventLoadedListener;
    }

    public void setNavigationLoggingListener(INavigationLoggingListener iNavigationLoggingListener) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.q.a(iNavigationLoggingListener);
    }

    public void setNavigationMode(MapplsNavigationMode mapplsNavigationMode) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().q.set(mapplsNavigationMode);
    }

    public void setOffRouteThreshold(int i) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        if (i <= 0) {
            this.p = -1;
            navigationApplication.q.getClass();
            com.mappls.sdk.navigation.routing.d.a(-1L);
        } else if (i < 10) {
            throw new IllegalArgumentException("off-route distance can't be less than 10 meters");
        } else {
            if (i > 100) {
                throw new IllegalArgumentException("off-route distance can't be more than 100 meters");
            }
            this.p = i;
            navigationApplication.q.getClass();
            com.mappls.sdk.navigation.routing.d.a(i);
        }
    }

    public void setOnSpeedLimitListener(OnSpeedLimitListener onSpeedLimitListener) {
        this.z = onSpeedLimitListener;
    }

    public void setPlaceOnRouteListener(POIAlongTheRouteChangedListener pOIAlongTheRouteChangedListener) {
        this.b.w.a(pOIAlongTheRouteChangedListener);
    }

    public void setPlayAsVoiceCall(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().h0.a(d.i, Integer.valueOf(z ? 0 : 3));
        this.b.s.a(z ? 0 : 3);
    }

    public void setPlayDuringPhoneCallEnabled(boolean z) {
        this.d = z;
    }

    public void setRouteIndex(int i) {
        this.w = i;
    }

    public void setRouteReportSummaryResponse(RouteReportSummaryResponse routeReportSummaryResponse) {
        this.n = routeReportSummaryResponse;
    }

    public void setRouteService(IRecalculatedDirection iRecalculatedDirection) {
        this.r = iRecalculatedDirection;
    }

    public void setRouteSettingOptions(v vVar) {
        this.o = vVar;
    }

    public void setRoutingHelper(com.mappls.sdk.navigation.routing.d dVar) {
        this.q = dVar;
    }

    public void setSelectedIndex(int i) {
        DirectionsResponse directionsResponse = this.i;
        if (directionsResponse == null || i >= directionsResponse.routes().size()) {
            return;
        }
        DirectionsResponse build = this.i.toBuilder().uuid(this.i.routes().get(i).routeId()).build();
        this.i = build;
        this.u = build.routes().get(i).routeId();
        this.w = i;
        this.q.b(b(this.i.routes().get(i)));
    }

    public void setSessionId(String str) {
        this.v = str;
    }

    public void setShortestRoute(boolean z) {
        t.j jVar;
        RouteOptions.Builder builder;
        Integer num;
        if (this.b.k().f12956a.get() == null) {
            return;
        }
        RouteOptions fromJson = RouteOptions.fromJson((String) this.b.k().f12956a.get());
        if (z) {
            jVar = this.b.k().f12956a;
            builder = fromJson.toBuilder();
            num = 1;
        } else {
            jVar = this.b.k().f12956a;
            builder = fromJson.toBuilder();
            num = null;
        }
        jVar.set(builder.routeType(num).build().toJson());
        recalculateRoute();
    }

    public void setShouldPlayNavigationInstructions(boolean z) {
        this.h = z;
    }

    public void setTollEntryExitListener(TollEntryExitListener tollEntryExitListener) {
        this.b.q.a(tollEntryExitListener);
    }

    public void setTrackRecordingListener(ITrackRecordingListener iTrackRecordingListener) {
        this.s = iTrackRecordingListener;
    }

    public void setTrafficProbeEnabled(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().P.set(Boolean.valueOf(z));
    }

    public void setUuid(String str) {
        this.u = str;
    }

    public void setVoiceCommandListener(VoiceCommandListener voiceCommandListener) {
        this.f12898a = voiceCommandListener;
    }

    public void showRoadConditionsEvents(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().O.set(Boolean.valueOf(z));
        if (!this.b.q.q() || this.b.q.k() == null) {
            return;
        }
        this.b.q.k().settingChanged();
    }

    public boolean showRoadConditionsEvents() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().O.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public void showSafetyEvents(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().K.set(Boolean.valueOf(z));
        if (!this.b.q.q() || this.b.q.k() == null) {
            return;
        }
        this.b.q.k().settingChanged();
    }

    public boolean showSafetyEvents() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().K.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public void showTrafficEvents(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().M.set(Boolean.valueOf(z));
        if (!this.b.q.q() || this.b.q.k() == null) {
            return;
        }
        this.b.q.k().settingChanged();
    }

    public boolean showTrafficEvents() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().M.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public void speakNavigationPrompt(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().m0.set(Boolean.valueOf(z));
        if (z) {
            return;
        }
        this.b.s.j();
    }

    public boolean speakNavigationPrompt() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().m0.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public void speakRoadConditionsEvents(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().N.set(Boolean.valueOf(z));
        if (!this.b.q.q() || this.b.q.k() == null) {
            return;
        }
        this.b.q.k().settingChanged();
    }

    public boolean speakRoadConditionsEvents() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().N.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public void speakSafetyEvents(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().J.set(Boolean.valueOf(z));
        if (!this.b.q.q() || this.b.q.k() == null) {
            return;
        }
        this.b.q.k().settingChanged();
    }

    public boolean speakSafetyEvents() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().J.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    public void speakTrafficEvents(boolean z) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.k().L.set(Boolean.valueOf(z));
        if (!this.b.q.q() || this.b.q.k() == null) {
            return;
        }
        this.b.q.k().settingChanged();
    }

    public boolean speakTrafficEvents() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            return ((Boolean) navigationApplication.k().L.get()).booleanValue();
        }
        throw new IllegalStateException("Navigation Helper is not initialized.");
    }

    @WorkerThread
    public NavigationResponse startNavigation(LatLng latLng, WayPoint wayPoint, List<WayPoint> list, @Nullable String str, List<ReportDetails> list2) {
        return startNavigation(latLng, wayPoint, list, str, false);
    }

    @WorkerThread
    public NavigationResponse startNavigation(LatLng latLng, WayPoint wayPoint, List<WayPoint> list, @Nullable String str, boolean z) {
        E = true;
        MapplsDirections.Builder overview = MapplsDirections.builder().origin(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude())).destination(Point.fromLngLat(wayPoint.getEntryLongitude().doubleValue(), wayPoint.getEntryLatitude().doubleValue())).profile(this.o.c).resource(this.o.d).steps(Boolean.valueOf(this.o.f13044a)).annotations(this.o.f).alternatives(Boolean.valueOf(this.o.b)).deviceId(this.o.e).overview("full");
        this.o.getClass();
        MapplsDirections.Builder lessVerbose = overview.lessVerbose(Boolean.FALSE);
        this.o.getClass();
        MapplsDirections.Builder routeType = lessVerbose.routeType(0);
        if (this.e != null) {
            routeType.baseUrl(this.e + "advancedmaps/v1/");
        }
        if (this.o.d.equalsIgnoreCase(DirectionsCriteria.RESOURCE_ROUTE_ETA)) {
            routeType.routeRefresh(Boolean.TRUE);
        }
        this.o.getClass();
        if (list != null) {
            for (int i = 0; i < list.size() - 1; i++) {
                routeType.addWaypoint(Point.fromLngLat(list.get(i).getEntryLongitude().doubleValue(), list.get(i).getEntryLatitude().doubleValue()));
            }
        }
        try {
            DirectionsResponse execute = MapplsDirectionManager.newInstance(routeType.build()).execute();
            if (execute != null) {
                NavigationResponse a2 = this.b.r.a(execute, 0, latLng, wayPoint, str);
                if (a2.getError() == null) {
                    try {
                        c(execute, 0, latLng, wayPoint, list, true, null);
                    } catch (Exception e) {
                        NavigationLogger.d(e);
                        a2 = new NavigationResponse(ErrorType.UNKNOWN_ERROR, e);
                    }
                }
                E = false;
                if (z) {
                    this.b.getLocationProvider().getLocationSimulation().b();
                    return a2;
                }
                return a2;
            }
            return new NavigationResponse(ErrorType.UNKNOWN_ERROR, null);
        } catch (Exception e2) {
            NavigationLogger.d(e2);
            return new NavigationResponse(ErrorType.UNKNOWN_ERROR, e2);
        }
    }

    @WorkerThread
    public NavigationResponse startNavigation(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list, @Nullable String str, List<ReportDetails> list2) {
        return startNavigation(directionsResponse, i, latLng, wayPoint, list, str, false, list2);
    }

    @WorkerThread
    public NavigationResponse startNavigation(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list, @Nullable String str, boolean z, List<ReportDetails> list2) {
        RouteOptions routeOptions;
        NavigationResponse navigationResponse = null;
        if (E) {
            return new NavigationResponse(ErrorType.NAVIGATION_ALREADY_REQUESTED, null);
        }
        if (isNavigating()) {
            return new NavigationResponse(ErrorType.NAVIGATION_ALREADY_RUNNING, null);
        }
        boolean z2 = true;
        E = true;
        if (this.b.k().q.get() != MapplsNavigationMode.ONLINE && (this.b.k().q.get() != MapplsNavigationMode.AUTOMATIC || !this.b.k().a(true))) {
            z2 = false;
        }
        if (!z2 || !this.c) {
            navigationResponse = new NavigationResponse(null, null);
        }
        if (z2 && this.c) {
            NavigationLogger.e("!isInternal && isOnline", new Object[0]);
            navigationResponse = this.b.r.a(directionsResponse, i, latLng, wayPoint, str);
        }
        NavigationResponse navigationResponse2 = navigationResponse;
        if (navigationResponse2.getError() == null) {
            try {
                c(directionsResponse, i, latLng, wayPoint, list, true, list2);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("source_location", latLng.getLatitude() + Constants.SEPARATOR_COMMA + latLng.getLongitude());
                if (wayPoint != null) {
                    jSONObject.put(FirebaseAnalytics.Param.DESTINATION, wayPoint.getVisualValue());
                }
                if (list != null && list.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (WayPoint wayPoint2 : list) {
                        arrayList.add(wayPoint2.getVisualValue());
                    }
                    jSONObject.put("via_points", MapplsUtils.join("|", arrayList.toArray()));
                }
                if (directionsResponse.sessionId() != null) {
                    jSONObject.put(WorkoutConstants.SESSION_ID, directionsResponse.sessionId());
                }
                if (directionsResponse.uuid() != null) {
                    jSONObject.put("request_id", directionsResponse.uuid());
                }
                jSONObject.put("selected_index", i);
                if (directionsResponse.routes().size() > 0 && (routeOptions = directionsResponse.routes().get(0).routeOptions()) != null) {
                    jSONObject.put("resource", routeOptions.resource());
                    jSONObject.put(Scopes.PROFILE, routeOptions.profile());
                    jSONObject.put("alternatives", routeOptions.alternatives());
                }
                if (MapplsLMSManager.isInitialised()) {
                    MapplsLMSManager.getInstance().add("navigation-started", "navigation-sdk", "0.13.6", jSONObject);
                }
            } catch (JSONException unused) {
            } catch (Exception e) {
                NavigationLogger.d(e);
                if (MapplsLMSManager.isInitialised()) {
                    MapplsLMSManager.getInstance().handledExceptions("startNavigation", "navigation-sdk", "0.13.6", e);
                }
                navigationResponse2 = new NavigationResponse(ErrorType.UNKNOWN_ERROR, e);
            }
        }
        E = false;
        if (z) {
            this.b.getLocationProvider().getLocationSimulation().b();
        }
        return navigationResponse2;
    }

    @UiThread
    public void startNavigation(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list, @Nullable String str, List<ReportDetails> list2, @Nullable OnAuthentication onAuthentication) {
        startNavigation(directionsResponse, i, latLng, wayPoint, list, str, false, list2, onAuthentication);
    }

    @UiThread
    public void startNavigation(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list, @Nullable String str, boolean z, List<ReportDetails> list2, @Nullable OnAuthentication onAuthentication) {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        navigationApplication.n().getClass();
        u.a(new b(directionsResponse, i, latLng, wayPoint, list, str, z, list2, onAuthentication), new Void[0]);
    }

    public void stopNavigation() {
        NavigationApplication navigationApplication = this.b;
        if (navigationApplication != null) {
            navigationApplication.stopNavigation();
        }
    }

    public void stopRecording() {
        com.mappls.sdk.navigation.tracks.a aVar = (com.mappls.sdk.navigation.tracks.a) s.a();
        if (aVar == null) {
            return;
        }
        if (isRecording()) {
            aVar.e();
        }
        stopTrackRecordingHandler();
        ITrackRecordingListener iTrackRecordingListener = this.s;
        if (iTrackRecordingListener != null) {
            iTrackRecordingListener.onRecordingEnded();
        }
    }

    public void stopTrackRecordingHandler() {
        Handler handler = this.t;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void updateArrivalDistance(int i) {
        this.b.q.a(i);
    }

    public void updateWayPoint(WayPoint wayPoint, int i) {
        if (this.b == null) {
            throw new IllegalStateException("Navigation Helper is not initialized.");
        }
        com.mappls.sdk.navigation.data.a aVar = new com.mappls.sdk.navigation.data.a(GeoCodingCriteria.POD_POINT_OF_INTEREST, wayPoint.getVisualName());
        aVar.a(wayPoint.getEntryLatitude().doubleValue());
        aVar.b(wayPoint.getEntryLongitude().doubleValue());
        aVar.b(wayPoint.getSpokenName());
        this.b.m().a(new LatLng(wayPoint.getEntryLatitude().doubleValue(), wayPoint.getEntryLongitude().doubleValue()), i, aVar);
        NavigationApplication navigationApplication = this.b;
        navigationApplication.q.a(NavigationLocationProvider.convertLocation(navigationApplication.c, navigationApplication));
        recalculateRoute();
    }
}
