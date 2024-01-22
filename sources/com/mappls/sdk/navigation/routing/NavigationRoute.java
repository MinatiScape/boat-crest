package com.mappls.sdk.navigation.routing;

import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.data.LocationPoint;
import com.mappls.sdk.navigation.events.NavEvent;
import com.mappls.sdk.navigation.model.Junction;
import com.mappls.sdk.navigation.model.JunctionApiResponse;
import com.mappls.sdk.navigation.textinstructions.TextInstructionHelper;
import com.mappls.sdk.navigation.util.NavigationUtils;
import com.mappls.sdk.services.api.directions.MapplsDirections;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.DirectionsWaypoint;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import timber.log.Timber;
/* loaded from: classes11.dex */
public class NavigationRoute {
    public static float routeSpeed = 13.0f;
    public static double t = 3000.0d;
    public static double u = 25.0d;

    /* renamed from: a  reason: collision with root package name */
    public final List<NavLocation> f12942a;
    public com.mappls.sdk.navigation.d appMode;
    public final List<NavigationStep> b;
    public final List<com.mappls.sdk.navigation.router.b> c;
    public List<NavigationStep> cacheAgreggatedDirections;
    public int cacheCurrentTextDirectionInfo;
    public int currentDirectionInfo;
    public int currentRoute;
    public int currentWaypointGPX;
    public final List<com.mappls.sdk.navigation.routing.a> d;
    public DirectionsRoute directionsRoute;
    public final List<ReportDetails> e;
    public final List<Junction> f;
    public final List<Double> g;
    public final List<String> h;
    public final List<Long> i;
    public final List<Double> j;
    public final String k;
    public final int[] l;
    public int lastWaypointGPX;
    public List<LocationPoint> locationPoints;
    public final int[] m;
    public final float n;
    public int nextIntermediate;
    public List<NavEvent> o;
    public NavLocation p;
    public b params;
    public String q;
    public List<LatLng> r;
    public HashMap<String, String> s;

    /* loaded from: classes11.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public NavigationStep f12943a;
        public int b;
        public boolean c;
        public int d;
    }

    public NavigationRoute(NavLocation navLocation, String str, List<LatLng> list, Context context, boolean z, List<LocationPoint> list2) {
        this.cacheCurrentTextDirectionInfo = -1;
        this.locationPoints = new ArrayList();
        this.currentDirectionInfo = 0;
        this.currentRoute = 0;
        this.nextIntermediate = 0;
        this.currentWaypointGPX = 0;
        this.lastWaypointGPX = 0;
        this.o = new ArrayList();
        this.s = new HashMap<>();
        this.n = 0.0f;
        b defaultParams = MapplsNavigationHelper.getInstance().getDefaultParams();
        this.params = defaultParams;
        defaultParams.f12947a = navLocation;
        defaultParams.b = str;
        if (list2 != null) {
            this.locationPoints.addAll(list2);
        }
        this.p = navLocation;
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng : list) {
            arrayList.add(new LatLng(latLng.getLatitude(), latLng.getLongitude()));
        }
        this.r = arrayList;
        this.params.c = arrayList;
        this.q = str;
        this.o = new ArrayList();
        this.f = new ArrayList();
        this.e = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        this.k = null;
        int[] iArr = new int[arrayList.size()];
        this.m = iArr;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        this.c = new ArrayList();
        List<NavLocation> unmodifiableList = Collections.unmodifiableList(arrayList3);
        this.f12942a = unmodifiableList;
        int[] iArr2 = new int[arrayList3.size()];
        this.l = iArr2;
        b(context, unmodifiableList, arrayList, arrayList2, iArr);
        l(iArr2, unmodifiableList);
        this.appMode = com.mappls.sdk.navigation.d.i;
        ((NavigationStep) arrayList2.get(0)).getTurnType().a(navLocation.bearingTo((NavLocation) arrayList3.get(0)));
        List<NavigationStep> unmodifiableList2 = Collections.unmodifiableList(arrayList2);
        this.b = unmodifiableList2;
        this.g = new ArrayList();
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.h = new ArrayList();
        k(unmodifiableList2, iArr2);
        h(arrayList2);
        m(unmodifiableList2);
        this.d = Collections.unmodifiableList(arrayList4);
    }

    public NavigationRoute(DirectionsRoute directionsRoute, List<NavLocation> list, List<NavigationStep> list2, b bVar, List<LocationPoint> list3, boolean z) {
        this(directionsRoute, list, list2, bVar, list3, z, null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List, boolean] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v43, types: [java.util.List, java.util.List<com.mappls.sdk.navigation.data.LocationPoint>] */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.mappls.sdk.navigation.routing.NavigationRoute] */
    public NavigationRoute(DirectionsRoute directionsRoute, List<NavLocation> list, List<NavigationStep> list2, b bVar, List<LocationPoint> list3, boolean z, List<Junction> list4, List<ReportDetails> list5) {
        List<String> arrayList;
        this.cacheCurrentTextDirectionInfo = -1;
        this.locationPoints = new ArrayList();
        this.currentDirectionInfo = 0;
        this.currentRoute = 0;
        this.nextIntermediate = 0;
        this.currentWaypointGPX = 0;
        this.lastWaypointGPX = 0;
        this.o = new ArrayList();
        this.s = new HashMap<>();
        DirectionsRoute.Builder builder = directionsRoute.toBuilder();
        builder.legs(NavigationUtils.mergeRouteLegs(directionsRoute));
        this.directionsRoute = builder.build();
        this.n = 0.0f;
        this.k = null;
        this.params = bVar;
        this.p = bVar.f12947a;
        List<LatLng> list6 = bVar.c;
        this.r = list6;
        this.q = bVar.b;
        int[] iArr = new int[list6 == null ? 0 : list6.size()];
        this.m = iArr;
        ArrayList arrayList2 = list == null ? new ArrayList() : new ArrayList(list);
        if (list2 == null) {
            new ArrayList();
        } else {
            new ArrayList(list2);
        }
        if (directionsRoute.distance() != null && directionsRoute.duration() != null) {
            routeSpeed = (float) (directionsRoute.distance().doubleValue() / directionsRoute.duration().doubleValue());
        }
        ?? isEmpty = arrayList2.isEmpty();
        if (isEmpty == 0) {
            checkForDuplicatePoints(arrayList2, isEmpty);
        }
        if (list3 != null) {
            isEmpty = this.locationPoints;
            isEmpty.addAll(list3);
        }
        if (z) {
            addMissingTurnsToRouteOSRM(arrayList2, isEmpty, bVar.e, bVar.d, bVar.g);
        }
        this.appMode = bVar.e;
        List<NavLocation> unmodifiableList = Collections.unmodifiableList(arrayList2);
        this.f12942a = unmodifiableList;
        this.c = new ArrayList();
        int[] iArr2 = new int[arrayList2.size()];
        this.l = iArr2;
        l(iArr2, unmodifiableList);
        this.d = new ArrayList();
        if (list5 != null) {
            this.e = list5;
        } else {
            this.e = new ArrayList();
        }
        this.f = (list4 == null || list4.size() <= 0) ? new ArrayList<>() : list4;
        b(bVar.d, unmodifiableList, bVar.c, unmodifiableList, iArr);
        List<NavigationStep> unmodifiableList2 = Collections.unmodifiableList(unmodifiableList);
        this.b = unmodifiableList2;
        k(unmodifiableList2, iArr2);
        Timber.tag("Navigation:events").d("NavigationRoute %d", Integer.valueOf(getEvents().size()));
        if (directionsRoute.legs() == null || directionsRoute.legs().get(0) == null || directionsRoute.legs().get(0).annotation() == null) {
            this.g = new ArrayList();
            this.i = new ArrayList();
            this.j = new ArrayList();
            arrayList = new ArrayList<>();
        } else {
            this.g = directionsRoute.legs().get(0).annotation().speedLimit();
            this.i = directionsRoute.legs().get(0).annotation().nodes();
            this.j = directionsRoute.legs().get(0).annotation().duration();
            arrayList = directionsRoute.legs().get(0).annotation().tollRoad();
        }
        this.h = arrayList;
        setInternalEvents(this.e);
    }

    public NavigationRoute(String str) {
        this.cacheCurrentTextDirectionInfo = -1;
        this.locationPoints = new ArrayList();
        this.currentDirectionInfo = 0;
        this.currentRoute = 0;
        this.nextIntermediate = 0;
        this.currentWaypointGPX = 0;
        this.lastWaypointGPX = 0;
        this.o = new ArrayList();
        this.s = new HashMap<>();
        this.k = str;
        this.n = 0.0f;
        this.m = new int[0];
        this.f12942a = new ArrayList();
        this.c = new ArrayList();
        this.l = new int[0];
        this.b = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.i = new ArrayList();
        this.j = new ArrayList();
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x013f, code lost:
        if (r27 != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0158, code lost:
        if (r27 != false) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void addMissingTurnsToRoute(java.util.List<com.mappls.sdk.navigation.NavLocation> r21, java.util.List<com.mappls.sdk.navigation.routing.NavigationStep> r22, com.mappls.sdk.navigation.NavLocation r23, com.mappls.sdk.maps.geometry.LatLng r24, com.mappls.sdk.navigation.d r25, android.content.Context r26, boolean r27) {
        /*
            Method dump skipped, instructions count: 618
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.routing.NavigationRoute.addMissingTurnsToRoute(java.util.List, java.util.List, com.mappls.sdk.navigation.NavLocation, com.mappls.sdk.maps.geometry.LatLng, com.mappls.sdk.navigation.d, android.content.Context, boolean):void");
    }

    public static void b(Context context, List<NavLocation> list, List<LatLng> list2, List<NavigationStep> list3, int[] iArr) {
        if (list2 == null || list3 == null) {
            return;
        }
        int[] iArr2 = new int[list2.size()];
        int i = 0;
        int i2 = 0;
        while (i2 < list2.size()) {
            double d = t;
            LatLng latLng = list2.get(i2);
            for (int i3 = i2 == 0 ? 0 : iArr2[i2 - 1]; i3 < list.size(); i3++) {
                double e = e(list, latLng, i3);
                if (((int) e) != 0) {
                    double d2 = u;
                    if (e > d2 && d < d2) {
                        break;
                    }
                } else {
                    iArr2[i2] = i3;
                    d = e;
                }
            }
            if (d == t) {
                return;
            }
            i2++;
        }
        for (int i4 = 0; i < list2.size() && i4 < list3.size(); i4++) {
            int i5 = list3.get(i4).routePointOffset;
            int i6 = iArr2[i];
            if (i5 >= i6) {
                if (i5 > i6 && e(list, list2.get(i), i5) > 50.0d) {
                    NavigationStep navigationStep = list3.get(i4);
                    NavigationStep navigationStep2 = new NavigationStep(list3.get(i4).getAverageSpeed(), com.mappls.sdk.navigation.router.c.h());
                    navigationStep2.setRef(navigationStep.getRef());
                    navigationStep2.setStreetName(navigationStep.getStreetName());
                    navigationStep2.setDestinationName(navigationStep.getDestinationName());
                    navigationStep2.routePointOffset = iArr2[i];
                    navigationStep2.setDescriptionRoute(context.getString(R.string.mappls_navigation_route_head));
                    list3.add(i4, navigationStep2);
                }
                iArr[i] = i4;
                i++;
            }
        }
    }

    public static void checkForDuplicatePoints(List<NavLocation> list, List<NavigationStep> list2) {
        int i = 0;
        while (i < list.size() - 1) {
            int i2 = i + 1;
            if (list.get(i).distanceTo(list.get(i2)) == 0.0f) {
                list.remove(i);
                if (list2 != null) {
                    for (NavigationStep navigationStep : list2) {
                        int i3 = navigationStep.routePointOffset;
                        if (i3 > i) {
                            navigationStep.routePointOffset = i3 - 1;
                        }
                    }
                }
            } else {
                i = i2;
            }
        }
    }

    public static double e(List<NavLocation> list, LatLng latLng, int i) {
        return com.mappls.sdk.navigation.util.d.a(latLng, list.get(i).getLatitude(), list.get(i).getLongitude());
    }

    public static /* synthetic */ void g(List list) {
        if (MapplsNavigationHelper.getInstance().getNavigationEventLoadedListener() != null) {
            MapplsNavigationHelper.getInstance().getNavigationEventLoadedListener().onNavigationEventsLoaded(list);
        }
    }

    public static int getTurnType(String str, String str2) {
        if (str.equalsIgnoreCase("arrive") || str.equalsIgnoreCase("depart")) {
            return str.equalsIgnoreCase("arrive") ? 8 : 7;
        }
        str2.getClass();
        char c = 65535;
        switch (str2.hashCode()) {
            case -2016367553:
                if (str2.equals("slight right")) {
                    c = 0;
                    break;
                }
                break;
            case -1531469187:
                if (str2.equals("sharp left")) {
                    c = 1;
                    break;
                }
                break;
            case -757963388:
                if (str2.equals("slight left")) {
                    c = 2;
                    break;
                }
                break;
            case -225243546:
                if (str2.equals("sharp right")) {
                    c = 3;
                    break;
                }
                break;
            case 3317767:
                if (str2.equals("left")) {
                    c = 4;
                    break;
                }
                break;
            case 108511772:
                if (str2.equals("right")) {
                    c = 5;
                    break;
                }
                break;
            case 111623794:
                if (str2.equals("uturn")) {
                    c = 6;
                    break;
                }
                break;
            case 1787472634:
                if (str2.equals("straight")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 5;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
            default:
                return 0;
            case 5:
                return 3;
            case 6:
                return 6;
            case 7:
                return 7;
        }
    }

    public static void k(List<NavigationStep> list, int[] iArr) {
        int i = 0;
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).afterLeftTime = i;
            list.get(size).distance = iArr[list.get(size).routePointOffset];
            if (size < list.size() - 1) {
                list.get(size).distance -= iArr[list.get(size + 1).routePointOffset];
            }
            i += list.get(size).getExpectedTime();
        }
    }

    public static void l(int[] iArr, List<NavLocation> list) {
        if (iArr.length > 0) {
            iArr[list.size() - 1] = 0;
            for (int size = list.size() - 1; size > 0; size--) {
                int i = size - 1;
                int round = Math.round(list.get(i).distanceTo(list.get(size)));
                iArr[i] = round;
                iArr[i] = round + iArr[size];
            }
        }
    }

    public static String toString(com.mappls.sdk.navigation.router.c cVar, Context context, boolean z) {
        if (cVar.e()) {
            return z ? context.getString(R.string.mappls_route_roundabout_short, Integer.valueOf(cVar.a())) : context.getString(R.string.mappls_route_roundabout, Integer.valueOf(cVar.a()));
        } else if (cVar.c() == 7) {
            return context.getString(R.string.mappls_navigation_route_head);
        } else {
            if (cVar.c() == 2) {
                return context.getString(R.string.mappls_route_tsll);
            }
            if (cVar.c() == 11) {
                return context.getString(R.string.mappls_route_tl);
            }
            if (cVar.c() == 1) {
                return context.getString(R.string.mappls_route_tshl);
            }
            if (cVar.c() == 5) {
                return context.getString(R.string.mappls_route_tslr);
            }
            if (cVar.c() == 3) {
                return context.getString(R.string.mappls_route_tr);
            }
            if (cVar.c() == 4) {
                return context.getString(R.string.mappls_route_tshr);
            }
            if (cVar.c() != 6 && cVar.c() != 41) {
                return cVar.c() == 9 ? context.getString(R.string.mappls_route_kl) : cVar.c() == 10 ? context.getString(R.string.mappls_route_kr) : "";
            }
            return context.getString(R.string.mappls_route_tu);
        }
    }

    public void addMissingTurnsToRouteOSRM(List<NavLocation> list, List<NavigationStep> list2, com.mappls.sdk.navigation.d dVar, Context context, boolean z) {
        ArrayList arrayList;
        List<LegStep> list3;
        if (list.isEmpty()) {
            return;
        }
        int i = 0;
        List<LegStep> steps = this.directionsRoute.legs().get(0).steps();
        if (routeSpeed <= 0.0f) {
            dVar.getClass();
        }
        ArrayList arrayList2 = new ArrayList();
        int[] iArr = new int[list.size()];
        int i2 = 1;
        iArr[list.size() - 1] = 0;
        for (int size = list.size() - 1; size > 0; size--) {
            int i3 = size - 1;
            int round = Math.round(list.get(i3).distanceTo(list.get(size)));
            iArr[i3] = round;
            iArr[i3] = round + iArr[size];
        }
        list.clear();
        NavigationStep navigationStep = new NavigationStep((float) (steps.get(0).distance() / steps.get(0).duration()), com.mappls.sdk.navigation.router.c.h());
        navigationStep.routePointOffset = 0;
        int i4 = 6;
        if (steps.size() - 1 > 0) {
            List<Point> coordinates = LineString.fromPolyline(steps.get(0).geometry(), 6).coordinates();
            int i5 = 0;
            while (i5 < coordinates.size()) {
                NavLocation navLocation = new NavLocation("router");
                navLocation.setLatitude(coordinates.get(i5).latitude());
                navLocation.setLongitude(coordinates.get(i5).longitude());
                list.add(navLocation);
                i5++;
                arrayList2 = arrayList2;
            }
            arrayList = arrayList2;
            NavigationStep navigationStep2 = new NavigationStep((float) (steps.get(0).distance() / steps.get(0).duration()), com.mappls.sdk.navigation.router.c.h());
            navigationStep2.setNavLocation(list.get(0));
            navigationStep2.setDescriptionRoute(TextInstructionHelper.getInstance().getInstruction(steps.get(0)));
            navigationStep2.setStreetName(steps.get(0).name());
            navigationStep2.routePointOffset = 0;
            navigationStep2.setPosition(0);
            navigationStep2.setExtraInfo(steps.get(0));
            navigationStep2.setNextExtraInfo(steps.get(1));
            try {
                if (steps.get(0) != null && steps.get(0).maneuver().type() != null) {
                    LegStep legStep = steps.get(0);
                    if (!legStep.maneuver().type().equalsIgnoreCase("roundabout")) {
                        navigationStep2.setShortInstruction(TextInstructionHelper.getInstance().getShortInstruction(legStep));
                    }
                    navigationStep2.setManeuverID(TextInstructionHelper.getInstance().getManeuverId(legStep).intValue());
                }
            } catch (Exception e) {
                NavigationLogger.d(e);
            }
            navigationStep = navigationStep2;
        } else {
            arrayList = arrayList2;
            navigationStep.setDescriptionRoute(context.getString(R.string.mappls_navigation_route_head));
            navigationStep.setManeuverID(-1);
            navigationStep.setPosition(0);
        }
        ArrayList arrayList3 = arrayList;
        arrayList3.add(navigationStep);
        List<LegStep> subList = steps.subList(1, steps.size());
        int i6 = 0;
        while (i6 < subList.size()) {
            try {
                LegStep legStep2 = subList.get(i6);
                LineString fromPolyline = LineString.fromPolyline(legStep2.geometry(), i4);
                List<Point> subList2 = fromPolyline.coordinates().subList(i2, fromPolyline.coordinates().size());
                int size2 = list.size() - i2;
                if (subList2 == null || subList2.size() <= 0) {
                    list3 = subList;
                    List<Point> coordinates2 = fromPolyline.coordinates();
                    if (coordinates2 != null && coordinates2.size() > 0) {
                        NavLocation navLocation2 = new NavLocation("router");
                        navLocation2.setLatitude(coordinates2.get(0).latitude());
                        navLocation2.setLongitude(coordinates2.get(0).longitude());
                        list.add(navLocation2);
                    }
                } else {
                    int i7 = i;
                    while (i7 < subList2.size()) {
                        NavLocation navLocation3 = new NavLocation("router");
                        navLocation3.setLatitude(subList2.get(i7).latitude());
                        navLocation3.setLongitude(subList2.get(i7).longitude());
                        list.add(navLocation3);
                        i7++;
                        subList = subList;
                    }
                    list3 = subList;
                }
                if (list.size() <= size2) {
                    subList = list3;
                } else {
                    NavLocation navLocation4 = list.get(size2);
                    String instruction = TextInstructionHelper.getInstance().getInstruction(legStep2);
                    navigationStep.distance = (int) legStep2.distance();
                    com.mappls.sdk.navigation.router.c a2 = com.mappls.sdk.navigation.router.c.a(0, z);
                    try {
                        a2 = legStep2.maneuver().type().equalsIgnoreCase("roundabout") ? com.mappls.sdk.navigation.router.c.a(legStep2.maneuver().exit() != null ? legStep2.maneuver().exit().intValue() : 0, legStep2.maneuver().degree() != null ? legStep2.maneuver().degree().intValue() : 0.0f, z) : com.mappls.sdk.navigation.router.c.a(getTurnType(legStep2.maneuver().type(), legStep2.maneuver().modifier()), z);
                    } catch (Exception e2) {
                        NavigationLogger.e(e2);
                    }
                    String name = legStep2.name();
                    NavigationStep navigationStep3 = new NavigationStep((float) (legStep2.distance() / legStep2.duration()), a2);
                    navigationStep3.setNavLocation(navLocation4);
                    navigationStep3.setDescriptionRoute(instruction);
                    navigationStep3.setStreetName(name);
                    navigationStep3.routePointOffset = size2;
                    int i8 = i6 + 1;
                    navigationStep3.setPosition(i8);
                    if (i6 == list3.size() - 1) {
                        navigationStep3.setDestination(true);
                    }
                    try {
                        if (!legStep2.maneuver().type().equalsIgnoreCase("roundabout")) {
                            navigationStep3.setShortInstruction(TextInstructionHelper.getInstance().getShortInstruction(legStep2));
                        }
                        navigationStep3.setManeuverID(TextInstructionHelper.getInstance().getManeuverId(legStep2).intValue());
                        if (!navigationStep3.isDestination() && navigationStep3.getManeuverID() == 8) {
                            navigationStep3.setDescriptionRoute(navigationStep3.getDescriptionRoutePart().replaceAll(FirebaseAnalytics.Param.DESTINATION, "Intermediate destination"));
                        }
                    } catch (Exception e3) {
                        NavigationLogger.d(e3);
                    }
                    navigationStep3.setExtraInfo(legStep2);
                    if (i8 < list3.size()) {
                        subList = list3;
                        navigationStep3.setNextExtraInfo(subList.get(i8));
                    } else {
                        subList = list3;
                    }
                    arrayList3.add(navigationStep3);
                    navigationStep = navigationStep3;
                }
                i6++;
                i = 0;
                i2 = 1;
                i4 = 6;
            } catch (Exception e4) {
                NavigationLogger.e(e4);
            }
        }
        if (list2.isEmpty()) {
            list2.addAll(arrayList3);
        }
        int i9 = 0;
        for (int size3 = list2.size() - 1; size3 >= 0; size3--) {
            list2.get(size3).afterLeftTime = i9;
            i9 += list2.get(size3).getExpectedTime();
        }
    }

    public final List<DirectionsRoute> c(DirectionsResponse directionsResponse, MapplsDirections mapplsDirections) {
        List<DirectionsRoute> routes = directionsResponse.routes();
        ArrayList arrayList = new ArrayList();
        for (DirectionsRoute directionsRoute : routes) {
            arrayList.add(directionsRoute.toBuilder().routeOptions(RouteOptions.builder().profile(mapplsDirections.profile()).resource(mapplsDirections.resource()).baseUrl(mapplsDirections.baseUrl()).coordinates(mapplsDirections.coordinates()).waypointIndices(mapplsDirections.waypointIndices()).waypointNames(mapplsDirections.waypointNames()).waypointTargets(mapplsDirections.waypointTargets()).lessVerbose(mapplsDirections.lessVerbose()).annotations(mapplsDirections.annotation()).approaches(mapplsDirections.approaches()).bearings(mapplsDirections.bearing()).alternatives(mapplsDirections.alternatives()).language(mapplsDirections.language()).radiuses(mapplsDirections.radius()).user(mapplsDirections.user()).bannerInstructions(mapplsDirections.bannerInstructions()).geometries(mapplsDirections.geometries()).overview(mapplsDirections.overview()).steps(mapplsDirections.steps()).exclude(mapplsDirections.exclude()).walkingOptions(mapplsDirections.walkingOptions()).routeRefresh(mapplsDirections.routeRefresh()).deviceID(mapplsDirections.deviceId()).requestUuid(directionsResponse.uuid()).sessionId(directionsResponse.sessionId()).isSort(mapplsDirections.isSort()).routeType(mapplsDirections.routeType()).roundaboutExits(mapplsDirections.roundaboutExits()).build()).build());
        }
        return arrayList;
    }

    public double currentDuration() {
        List<Double> list = this.j;
        if (list != null) {
            int size = list.size();
            int i = this.currentRoute;
            if (size > i) {
                return this.j.get(i).doubleValue();
            }
        }
        return 0.0d;
    }

    public double currentNode() {
        List<Long> list = this.i;
        if (list != null) {
            int size = list.size();
            int i = this.currentRoute;
            if (size > i) {
                return this.i.get(i).longValue();
            }
        }
        return 0.0d;
    }

    public double currentRoadSpeed() {
        List<Double> list = this.g;
        if (list != null) {
            int size = list.size();
            int i = this.currentRoute;
            if (size > i) {
                return this.g.get(i).doubleValue();
            }
        }
        return 0.0d;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0174  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.mappls.sdk.services.api.directions.models.DirectionsRoute d(java.lang.String r22, java.lang.String r23, java.lang.Boolean r24) {
        /*
            Method dump skipped, instructions count: 494
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.routing.NavigationRoute.d(java.lang.String, java.lang.String, java.lang.Boolean):com.mappls.sdk.services.api.directions.models.DirectionsRoute");
    }

    public boolean directionsAvailable() {
        return this.currentDirectionInfo < this.b.size();
    }

    public final int f(int i) {
        int[] iArr = this.l;
        if (iArr.length > i) {
            return iArr[i];
        }
        return 0;
    }

    public List<com.mappls.sdk.navigation.routing.a> getAlarmInfo() {
        return this.d;
    }

    public com.mappls.sdk.navigation.d getAppMode() {
        return this.appMode;
    }

    public float getCurrentMaxSpeed() {
        getCurrentSegmentResult();
        return 0.0f;
    }

    public int getCurrentRoute() {
        return this.currentRoute;
    }

    public com.mappls.sdk.navigation.router.b getCurrentSegmentResult() {
        int i = this.currentRoute;
        int i2 = i > 0 ? i - 1 : 0;
        if (i2 < this.c.size()) {
            return this.c.get(i2);
        }
        return null;
    }

    public DirectionsResponse getDirectionsResponse(MapplsDirections mapplsDirections) {
        LatLng latLng = null;
        if (this.k != null) {
            return null;
        }
        DirectionsResponse.Builder builder = DirectionsResponse.builder();
        ArrayList arrayList = new ArrayList();
        arrayList.add(d(mapplsDirections.profile(), mapplsDirections.geometries(), mapplsDirections.steps()));
        builder.routes(arrayList);
        builder.code(CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
        ArrayList arrayList2 = new ArrayList();
        NavLocation navLocation = this.p;
        arrayList2.add(DirectionsWaypoint.builder().rawLocation(new double[]{navLocation.getLongitude(), navLocation.getLatitude()}).name("").build());
        List<LatLng> list = this.r;
        if (list != null) {
            for (LatLng latLng2 : list) {
                arrayList2.add(DirectionsWaypoint.builder().rawLocation(new double[]{latLng2.getLongitude(), latLng2.getLatitude()}).name("").build());
            }
        }
        if (this.q.contains(Constants.SEPARATOR_COMMA)) {
            String[] split = this.q.split(Constants.SEPARATOR_COMMA);
            latLng = new LatLng(Double.parseDouble(split[1]), Double.parseDouble(split[0]));
        }
        if (latLng != null) {
            arrayList2.add(DirectionsWaypoint.builder().rawLocation(new double[]{latLng.getLongitude(), latLng.getLatitude()}).name("").build());
        }
        builder.waypoints(arrayList2);
        DirectionsResponse build = builder.build();
        return build.toBuilder().routes(c(build, mapplsDirections)).build();
    }

    public int getDistanceToFinish(NavLocation navLocation) {
        int i;
        int[] iArr = this.l;
        if (iArr == null || (i = this.currentRoute) >= iArr.length) {
            return 0;
        }
        int i2 = iArr[i];
        if (i < this.f12942a.size()) {
            return navLocation != null ? (int) (navLocation.distanceTo(this.f12942a.get(this.currentRoute)) + i2) : i2;
        }
        return i2;
    }

    public int getDistanceToNextIntermediate(NavLocation navLocation) {
        int i;
        int[] iArr = this.l;
        if (iArr == null || (i = this.currentRoute) >= iArr.length) {
            return 0;
        }
        int i2 = iArr[i];
        NavLocation navLocation2 = this.f12942a.get(i);
        if (navLocation != null) {
            i2 = (int) (navLocation.distanceTo(navLocation2) + i2);
        }
        int i3 = this.nextIntermediate;
        int[] iArr2 = this.m;
        if (i3 >= iArr2.length) {
            return 0;
        }
        return i2 - f(this.b.get(iArr2[i3]).routePointOffset);
    }

    public int getDistanceToPoint(int i) {
        int i2;
        int[] iArr = this.l;
        if (iArr == null || (i2 = this.currentRoute) >= iArr.length || i >= iArr.length || i <= i2) {
            return 0;
        }
        return iArr[i2] - iArr[i];
    }

    public String getErrorMessage() {
        return this.k;
    }

    public List<ReportDetails> getEvents() {
        return this.e;
    }

    public List<NavigationStep> getImmutableAllDirections() {
        return this.b;
    }

    public List<NavLocation> getImmutableAllLocations() {
        return this.f12942a;
    }

    public int getIndexOfIntermediate(int i) {
        int[] iArr = this.m;
        int length = (iArr.length - i) - 1;
        if (length >= iArr.length || length < 0) {
            return -1;
        }
        return this.b.get(iArr[length]).routePointOffset;
    }

    public int getIndexOfIntermediatePoint(int i) {
        int[] iArr = this.m;
        if (i >= iArr.length || i < 0) {
            return -1;
        }
        return this.b.get(iArr[i]).routePointOffset;
    }

    public int getIntermediatePointsToPass() {
        int i = this.nextIntermediate;
        int[] iArr = this.m;
        if (i >= iArr.length) {
            return 0;
        }
        return iArr.length - i;
    }

    public List<NavEvent> getInternalEvents() {
        return this.o;
    }

    public String getJunctionImage(String str) {
        if (this.s.containsKey(str)) {
            return this.s.get(str);
        }
        return null;
    }

    public List<Junction> getJunctionViews() {
        return this.f;
    }

    public int getLeftTime(NavLocation navLocation) {
        if (this.currentDirectionInfo < this.b.size()) {
            NavigationStep navigationStep = this.b.get(this.currentDirectionInfo);
            int i = navigationStep.afterLeftTime;
            int f = f(this.currentRoute);
            if (this.currentDirectionInfo + 1 < this.b.size()) {
                f -= f(this.b.get(this.currentDirectionInfo + 1).routePointOffset);
            }
            NavLocation navLocation2 = this.f12942a.get(this.currentRoute);
            if (navLocation != null) {
                f = (int) (navLocation.distanceTo(navLocation2) + f);
            }
            return (int) ((f / navigationStep.getAverageSpeed()) + i);
        }
        return 0;
    }

    public int getLeftTimeToNextIntermediate(NavLocation navLocation) {
        if (this.nextIntermediate >= this.m.length) {
            return 0;
        }
        return getLeftTime(navLocation) - this.b.get(this.m[this.nextIntermediate]).afterLeftTime;
    }

    public NavLocation getLocationFromRouteDirection(NavigationStep navigationStep) {
        List<NavLocation> list;
        if (navigationStep == null || (list = this.f12942a) == null || navigationStep.routePointOffset >= list.size()) {
            return null;
        }
        return this.f12942a.get(navigationStep.routePointOffset);
    }

    public List<LocationPoint> getLocationPoints() {
        return this.locationPoints;
    }

    public int getNextIntermediate() {
        return this.nextIntermediate;
    }

    public a getNextRouteDirectionInfo(a aVar, NavLocation navLocation, boolean z) {
        int i;
        try {
            int i2 = this.currentDirectionInfo;
            if (i2 >= this.b.size()) {
                aVar.d = -1;
                aVar.b = -1;
                aVar.f12943a = null;
                return null;
            }
            boolean z2 = true;
            int i3 = i2 + 1;
            if (z) {
                while (true) {
                    if (i3 >= this.b.size()) {
                        break;
                    }
                    NavigationStep navigationStep = this.b.get(i3);
                    if (navigationStep.getTurnType() != null) {
                        navigationStep.getTurnType().getClass();
                        break;
                    }
                    i3++;
                }
            }
            int[] iArr = this.l;
            int i4 = this.currentRoute;
            int i5 = iArr[i4];
            if (navLocation != null) {
                i5 = (int) (i5 + navLocation.distanceTo(this.f12942a.get(i4)));
            }
            if (i3 < this.b.size()) {
                aVar.f12943a = this.b.get(i3);
                int i6 = this.b.get(i3).routePointOffset;
                int i7 = this.currentRoute;
                i5 -= (i6 > i7 || i7 > this.b.get(i3).routeEndPointOffset) ? this.l[this.b.get(i3).routePointOffset] : this.l[this.b.get(i3).routeEndPointOffset];
            }
            int[] iArr2 = this.m;
            if (iArr2 != null && (i = this.nextIntermediate) < iArr2.length) {
                if (iArr2[i] != i3) {
                    z2 = false;
                }
                aVar.c = z2;
            }
            aVar.d = i3;
            aVar.b = i5;
            return aVar;
        } catch (Exception e) {
            NavigationLogger.e(e);
            aVar.d = -1;
            aVar.b = -1;
            aVar.f12943a = null;
            return null;
        }
    }

    public a getNextRouteDirectionInfoAfter(a aVar, a aVar2, boolean z) {
        NavigationStep navigationStep;
        int i;
        int i2 = aVar.d;
        if (i2 >= this.b.size() || (navigationStep = aVar.f12943a) == null) {
            aVar2.d = -1;
            aVar2.b = -1;
            aVar2.f12943a = null;
            return null;
        }
        int i3 = this.l[navigationStep.routePointOffset];
        int i4 = i2 + 1;
        if (z) {
            while (true) {
                if (i4 >= this.b.size()) {
                    break;
                }
                NavigationStep navigationStep2 = this.b.get(i4);
                if (navigationStep2.getTurnType() != null) {
                    navigationStep2.getTurnType().getClass();
                    break;
                }
                i4++;
            }
        }
        if (i4 < this.b.size()) {
            aVar2.f12943a = this.b.get(i4);
            i3 -= this.l[this.b.get(i4).routePointOffset];
        }
        int[] iArr = this.m;
        if (iArr != null && (i = this.nextIntermediate) < iArr.length) {
            aVar2.c = iArr[i] == i4;
        }
        aVar2.b = i3;
        aVar2.d = i4;
        return aVar2;
    }

    public NavLocation getNextRouteLocation() {
        if (this.currentRoute < this.f12942a.size()) {
            return this.f12942a.get(this.currentRoute);
        }
        return null;
    }

    public NavLocation getNextRouteLocation(int i) {
        if (this.currentRoute + i < this.f12942a.size()) {
            return this.f12942a.get(this.currentRoute + i);
        }
        return null;
    }

    public List<com.mappls.sdk.navigation.router.b> getOriginalRoute() {
        if (this.c.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.c.get(0));
        for (int i = 1; i < this.c.size(); i++) {
            if (this.c.get(i - 1) != this.c.get(i)) {
                arrayList.add(this.c.get(i));
            }
        }
        return arrayList;
    }

    public b getParams() {
        return this.params;
    }

    public List<NavigationStep> getRouteDirections() {
        if (this.currentDirectionInfo < this.b.size() - 1) {
            int i = this.cacheCurrentTextDirectionInfo;
            int i2 = this.currentDirectionInfo;
            if (i != i2) {
                this.cacheCurrentTextDirectionInfo = i2;
                List<NavigationStep> list = this.b;
                if (i2 != 0) {
                    list = list.subList(i2 + 1, list.size());
                }
                this.cacheAgreggatedDirections = new ArrayList();
                NavigationStep navigationStep = null;
                for (NavigationStep navigationStep2 : list) {
                    if (navigationStep != null) {
                        if (navigationStep2.getTurnType() != null) {
                            navigationStep2.getTurnType().getClass();
                        } else {
                            float expectedTime = navigationStep.getExpectedTime() + navigationStep2.getExpectedTime();
                            int i3 = navigationStep.distance + navigationStep2.distance;
                            navigationStep.distance = i3;
                            navigationStep.setAverageSpeed(i3 / expectedTime);
                            navigationStep.afterLeftTime = navigationStep2.afterLeftTime;
                        }
                    }
                    navigationStep = new NavigationStep(navigationStep2.getAverageSpeed(), navigationStep2.getTurnType());
                    navigationStep.routePointOffset = navigationStep2.routePointOffset;
                    navigationStep.routeEndPointOffset = navigationStep2.routeEndPointOffset;
                    navigationStep.setDestinationName(navigationStep2.getDestinationName());
                    navigationStep.setRef(navigationStep2.getRef());
                    navigationStep.setStreetName(navigationStep2.getStreetName());
                    navigationStep.setDescriptionRoute(navigationStep2.getDescriptionRoutePart());
                    this.cacheAgreggatedDirections.add(navigationStep);
                    float expectedTime2 = navigationStep.getExpectedTime() + navigationStep2.getExpectedTime();
                    int i32 = navigationStep.distance + navigationStep2.distance;
                    navigationStep.distance = i32;
                    navigationStep.setAverageSpeed(i32 / expectedTime2);
                    navigationStep.afterLeftTime = navigationStep2.afterLeftTime;
                }
            }
            return this.cacheAgreggatedDirections;
        }
        return Collections.emptyList();
    }

    public int getRouteDistanceToFinish(int i) {
        int i2;
        int[] iArr = this.l;
        if (iArr == null || (i2 = this.currentRoute + i) >= iArr.length) {
            return 0;
        }
        return iArr[i2];
    }

    public List<NavLocation> getRouteLocations() {
        if (this.currentRoute < this.f12942a.size()) {
            List<NavLocation> list = this.f12942a;
            return list.subList(this.currentRoute, list.size());
        }
        return Collections.emptyList();
    }

    public float getRoutingTime() {
        return this.n;
    }

    public int getWholeDistance() {
        int[] iArr = this.l;
        if (iArr.length > 0) {
            return iArr[0];
        }
        return 0;
    }

    public final void h(List<NavigationStep> list) {
        if (list != null) {
            int i = 1;
            if (list.size() > 1) {
                while (i < list.size()) {
                    NavigationStep navigationStep = list.get(i);
                    if (navigationStep.getTurnType().c() == 7) {
                        NavigationStep navigationStep2 = list.get(i - 1);
                        int i2 = navigationStep2.distance;
                        navigationStep2.setAverageSpeed((navigationStep.distance + i2) / ((navigationStep.distance / navigationStep.getAverageSpeed()) + (i2 / navigationStep2.getAverageSpeed())));
                        navigationStep2.setDistance(navigationStep2.distance + navigationStep.distance);
                        list.remove(i);
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    public boolean i(ReportDetails reportDetails) {
        NavigationApplication navigationApplication;
        b bVar = this.params;
        if (bVar == null || (navigationApplication = bVar.d) == null) {
            return true;
        }
        boolean booleanValue = ((Boolean) navigationApplication.k().M.get()).booleanValue();
        boolean booleanValue2 = ((Boolean) this.params.d.k().K.get()).booleanValue();
        boolean booleanValue3 = ((Boolean) this.params.d.k().O.get()).booleanValue();
        if (reportDetails.getParentCategoryId() != null) {
            if (reportDetails.getParentCategoryId().intValue() == 5) {
                return booleanValue2;
            }
            if (reportDetails.getParentCategoryId().intValue() == 2) {
                return booleanValue;
            }
            if (reportDetails.getParentCategoryId().intValue() == 6) {
                return booleanValue3;
            }
            return true;
        }
        return true;
    }

    public boolean isCalculated() {
        return !this.f12942a.isEmpty();
    }

    public boolean isEmpty() {
        return this.f12942a.isEmpty() || this.currentRoute >= this.f12942a.size();
    }

    public boolean isToll() {
        List<String> list = this.h;
        if (list != null) {
            int size = list.size();
            int i = this.currentRoute;
            if (size > i && Integer.parseInt(this.h.get(i)) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean j(ReportDetails reportDetails) {
        NavigationApplication navigationApplication;
        b bVar = this.params;
        if (bVar == null || (navigationApplication = bVar.d) == null) {
            return true;
        }
        boolean booleanValue = ((Boolean) navigationApplication.k().L.get()).booleanValue();
        boolean booleanValue2 = ((Boolean) this.params.d.k().J.get()).booleanValue();
        boolean booleanValue3 = ((Boolean) this.params.d.k().N.get()).booleanValue();
        if (reportDetails.getParentCategoryId() != null) {
            if (reportDetails.getParentCategoryId().intValue() == 5) {
                return booleanValue2;
            }
            if (reportDetails.getParentCategoryId().intValue() == 2) {
                return booleanValue;
            }
            if (reportDetails.getParentCategoryId().intValue() == 6) {
                return booleanValue3;
            }
            return true;
        }
        return true;
    }

    public final void m(List<NavigationStep> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            NavigationStep navigationStep = list.get(i);
            if (i > 0) {
                navigationStep.setPosition(i - 1);
            }
        }
    }

    public void passIntermediatePoint() {
        this.nextIntermediate++;
    }

    public void setEvents(List<ReportDetails> list) {
        this.e.clear();
        this.e.addAll(list);
        setInternalEvents(list);
    }

    public void setInternalEvents(final List<ReportDetails> list) {
        NavigationApplication navigationApplication;
        this.o.clear();
        if (list != null && list.size() > 0) {
            NavigationLogger.d("Total events from API = %d", Integer.valueOf(list.size()));
            for (ReportDetails reportDetails : list) {
                NavEvent navEvent = new NavEvent(reportDetails.getChildCategory(), reportDetails.getLatitude().doubleValue(), reportDetails.getLongitude().doubleValue(), j(reportDetails), i(reportDetails));
                navEvent.setIndex(reportDetails.getNodeIdx());
                navEvent.setReportDetails(reportDetails);
                this.o.add(navEvent);
            }
        }
        b bVar = this.params;
        if (bVar == null || (navigationApplication = bVar.d) == null) {
            return;
        }
        navigationApplication.o().a(6);
        this.params.d.a(new Runnable() { // from class: com.mappls.sdk.navigation.routing.e
            @Override // java.lang.Runnable
            public final void run() {
                NavigationRoute.g(list);
            }
        });
    }

    public void setJunctionViews(JunctionApiResponse junctionApiResponse) {
        List<Junction> list;
        this.f.clear();
        if (junctionApiResponse != null && (list = junctionApiResponse.data) != null && list.size() > 0) {
            this.f.addAll(junctionApiResponse.data);
            for (Junction junction : this.f) {
                this.s.put(junction.id, junction.image);
            }
        }
        if (MapplsNavigationHelper.getInstance().getJunctionViewsLoadedListener() != null) {
            MapplsNavigationHelper.getInstance().getJunctionViewsLoadedListener().onJunctionViewsLoaded(this.f);
        }
    }

    public void settingChanged() {
        NavigationApplication navigationApplication;
        this.o.clear();
        List<ReportDetails> list = this.e;
        if (list != null && list.size() > 0) {
            for (ReportDetails reportDetails : this.e) {
                NavEvent navEvent = new NavEvent(reportDetails.getChildCategory(), reportDetails.getLatitude().doubleValue(), reportDetails.getLongitude().doubleValue(), j(reportDetails), i(reportDetails));
                navEvent.setIndex(reportDetails.getNodeIdx());
                navEvent.setReportDetails(reportDetails);
                this.o.add(navEvent);
            }
        }
        b bVar = this.params;
        if (bVar == null || (navigationApplication = bVar.d) == null) {
            return;
        }
        navigationApplication.o().a(6);
    }

    public void updateCurrentRoute(int i) {
        this.currentRoute = i;
        while (this.currentDirectionInfo < this.b.size() - 1 && this.b.get(this.currentDirectionInfo + 1).routePointOffset < i && this.b.get(this.currentDirectionInfo + 1).routeEndPointOffset < i) {
            this.currentDirectionInfo++;
        }
        while (true) {
            int i2 = this.nextIntermediate;
            int[] iArr = this.m;
            if (i2 >= iArr.length || this.b.get(iArr[i2]).routePointOffset >= i) {
                return;
            }
            this.nextIntermediate++;
        }
    }
}
