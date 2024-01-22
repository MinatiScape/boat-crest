package com.mappls.sdk.navigation.routing;

import android.location.Location;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.NavigationLocationProvider;
import com.mappls.sdk.navigation.NavigationService;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.iface.INavigationListener;
import com.mappls.sdk.navigation.iface.INavigationLoggingListener;
import com.mappls.sdk.navigation.iface.TollEntryExitListener;
import com.mappls.sdk.navigation.notifications.a;
import com.mappls.sdk.navigation.routing.NavigationRoute;
import com.mappls.sdk.navigation.routing.c;
import com.mappls.sdk.navigation.t;
import com.mappls.sdk.navigation.util.NavigationSummaryHelper;
import com.mappls.sdk.navigation.y;
import com.mappls.sdk.services.api.MapplsApiConfiguration;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class d {
    public static float F = 30.0f;
    public static boolean G = false;
    public TollEntryExitListener C;

    /* renamed from: a  reason: collision with root package name */
    public INavigationLoggingListener f12949a;
    public NavigationApplication g;
    public String m;
    public List<LatLng> n;
    public NavLocation o;
    public NavLocation p;
    public Thread q;
    public String s;
    public String t;
    public com.mappls.sdk.navigation.d w;
    public t x;
    public com.mappls.sdk.navigation.routing.c y;
    public h z;
    public long b = 0;
    public long c = 0;
    public double d = 0.0d;
    public boolean e = false;
    public LinkedList f = new LinkedList();
    public LinkedList h = new LinkedList();
    public boolean i = false;
    public boolean j = false;
    public boolean k = false;
    public NavigationRoute l = new NavigationRoute("");
    public long r = 0;
    public long u = 0;
    public int v = 0;
    public long A = 0;
    public int B = 0;
    public boolean D = false;
    public String E = null;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (d.this.h != null) {
                Iterator it = d.this.h.iterator();
                while (it.hasNext()) {
                    INavigationListener iNavigationListener = (INavigationListener) ((WeakReference) it.next()).get();
                    if (iNavigationListener == null) {
                        it.remove();
                    } else {
                        iNavigationListener.onNavigationCancelled();
                    }
                }
            }
            Iterator it2 = d.this.f.iterator();
            while (it2.hasNext()) {
                c cVar = (c) ((WeakReference) it2.next()).get();
                if (cVar == null) {
                    it2.remove();
                } else {
                    cVar.b();
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {
        public final /* synthetic */ boolean h;
        public final /* synthetic */ NavLocation i;
        public final /* synthetic */ boolean j;
        public final /* synthetic */ NavigationRoute k;

        public b(boolean z, NavLocation navLocation, boolean z2, NavigationRoute navigationRoute) {
            this.h = z;
            this.i = navLocation;
            this.j = z2;
            this.k = navigationRoute;
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [T, java.lang.Boolean] */
        @Override // java.lang.Runnable
        public final void run() {
            y yVar = new y();
            yVar.f13052a = Boolean.TRUE;
            if (this.h) {
                NavigationSummaryHelper.Companion.getInstance().navigationStarted(this.i);
            }
            if (d.this.h != null) {
                Iterator it = d.this.h.iterator();
                while (it.hasNext()) {
                    INavigationListener iNavigationListener = (INavigationListener) ((WeakReference) it.next()).get();
                    if (iNavigationListener == null) {
                        it.remove();
                    } else {
                        if (this.h) {
                            iNavigationListener.onNavigationStarted();
                        }
                        if (d.this.l.directionsRoute != null && d.this.l.directionsRoute.geometry() != null) {
                            if (this.j) {
                                iNavigationListener.onETARefreshed(d.this.l.directionsRoute.geometry());
                            } else {
                                iNavigationListener.onNewRoute(d.this.l.directionsRoute.geometry());
                            }
                        }
                        if (d.this.g.getCurrentLocation() != null) {
                            d dVar = d.this;
                            dVar.b(NavigationLocationProvider.convertLocation(dVar.g.getCurrentLocation(), d.this.g));
                        }
                    }
                }
            }
            Iterator it2 = d.this.f.iterator();
            while (it2.hasNext()) {
                c cVar = (c) ((WeakReference) it2.next()).get();
                if (cVar == null) {
                    it2.remove();
                } else {
                    cVar.a();
                }
            }
            if (((Boolean) yVar.f13052a).booleanValue()) {
                d.this.g.getString(R.string.mappls_new_route_calculated_dist);
                NavigationFormatter.getFormattedDistance(this.k.getWholeDistance(), d.this.g);
                if (this.k.getRoutingTime() != 0.0f) {
                    com.mappls.sdk.navigation.util.a.a((int) this.k.getRoutingTime(), d.this.g.a());
                }
                d.this.g.q();
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        void a();

        void b();

        void c();
    }

    /* renamed from: com.mappls.sdk.navigation.routing.d$d  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0644d extends Thread {
        public final com.mappls.sdk.navigation.routing.b h;
        public boolean i;
        public Thread j;

        public C0644d(com.mappls.sdk.navigation.routing.b bVar, boolean z) {
            super("Calculating route");
            this.h = bVar;
            this.i = z;
            if (bVar.h == null) {
                bVar.h = new com.mappls.sdk.navigation.router.a();
            }
        }

        public final void a(Thread thread) {
            this.j = thread;
        }

        public final boolean b() {
            return this.i;
        }

        public final void c() {
            this.h.h.f12940a = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            d dVar;
            String string;
            synchronized (d.this) {
                d.this.q = this;
            }
            if (this.j != null) {
                while (this.j.isAlive()) {
                    try {
                        Thread.sleep(50L);
                    } catch (InterruptedException e) {
                        NavigationLogger.d(e);
                    }
                }
                synchronized (d.this) {
                    d.this.q = this;
                }
            }
            d.this.s = null;
            d.this.t = null;
            com.mappls.sdk.navigation.routing.c cVar = d.this.y;
            com.mappls.sdk.navigation.routing.b bVar = this.h;
            cVar.getClass();
            NavigationRoute a2 = com.mappls.sdk.navigation.routing.c.a(bVar);
            if (this.h.h.f12940a) {
                synchronized (d.this) {
                    d.this.q = null;
                }
                return;
            }
            if (!a2.isCalculated()) {
                this.h.f.getClass();
            }
            NavigationRoute navigationRoute = d.this.l;
            synchronized (d.this) {
                if (a2.isCalculated()) {
                    d.this.l = a2;
                } else {
                    d dVar2 = d.this;
                    dVar2.v = (dVar2.v * 3) / 2;
                    d dVar3 = d.this;
                    dVar3.v = Math.min(dVar3.v, 30000);
                }
                d.this.q = null;
            }
            if (a2.isCalculated()) {
                d.this.e(navigationRoute, a2, this.h.f12947a, false);
            } else {
                if (a2.getErrorMessage() != null) {
                    d dVar4 = d.this;
                    StringBuilder sb = new StringBuilder();
                    NavigationApplication navigationApplication = d.this.g;
                    int i = R.string.mappls_error_calculating_route;
                    sb.append(navigationApplication.getString(i));
                    sb.append(":\n");
                    sb.append(a2.getErrorMessage());
                    dVar4.s = sb.toString();
                    dVar = d.this;
                    string = dVar.g.getString(i);
                } else {
                    d dVar5 = d.this;
                    NavigationApplication navigationApplication2 = dVar5.g;
                    int i2 = R.string.mappls_empty_route_calculated;
                    dVar5.s = navigationApplication2.getString(i2);
                    dVar = d.this;
                    string = dVar.g.getString(i2);
                }
                dVar.t = string;
                d dVar6 = d.this;
                d.j(dVar6, dVar6.s);
            }
            d.this.g.getNotificationHelper().refreshNotification(a.EnumC0640a.NAVIGATION);
            d.this.r = System.currentTimeMillis();
        }
    }

    public d(NavigationApplication navigationApplication) {
        this.g = navigationApplication;
        t k = navigationApplication.k();
        this.x = k;
        this.z = new h(this, k);
        this.y = new com.mappls.sdk.navigation.routing.c();
        a(this.x.E0.get());
        a(this.x.e().c());
        MapplsNavigationHelper.getInstance().setRoutingHelper(this);
    }

    public static double a(NavLocation navLocation, NavLocation navLocation2, NavLocation navLocation3) {
        if (navLocation == null || navLocation2 == null || navLocation3 == null) {
            return 0.0d;
        }
        return com.mappls.sdk.navigation.util.d.a(navLocation.getLatitude(), navLocation.getLongitude(), navLocation2.getLatitude(), navLocation2.getLongitude(), navLocation3.getLatitude(), navLocation3.getLongitude());
    }

    public static void a(long j) {
        if (j >= 10) {
            F = (float) j;
        }
    }

    public static void j(d dVar, String str) {
        dVar.g.a(new i(dVar, str));
    }

    public static boolean p() {
        return G;
    }

    public final NavLocation a(NavLocation navLocation, boolean z) {
        return b(navLocation, z, this.l, false);
    }

    public final com.mappls.sdk.navigation.d a() {
        return this.w;
    }

    public final void a(int i) {
        this.B = i;
    }

    public final void a(NavLocation navLocation) {
        this.p = navLocation;
    }

    public final void a(com.mappls.sdk.navigation.d dVar) {
        this.w = dVar;
        this.z.j();
    }

    public final void a(INavigationListener iNavigationListener) {
        NavigationApplication navigationApplication;
        Location location;
        this.h.add(new WeakReference(iNavigationListener));
        if (iNavigationListener != null) {
            if (this.i) {
                iNavigationListener.onNavigationStarted();
            }
            if (this.q instanceof C0644d) {
                iNavigationListener.onReRoutingRequested();
            }
            DirectionsRoute directionsRoute = this.l.directionsRoute;
            if (directionsRoute != null && directionsRoute.geometry() != null) {
                iNavigationListener.onNewRoute(this.l.directionsRoute.geometry());
            }
            NavLocation lastKnownLocation = this.g.getLocationProvider().getLastKnownLocation();
            if (lastKnownLocation == null && (location = (navigationApplication = this.g).c) != null) {
                lastKnownLocation = NavigationLocationProvider.convertLocation(location, navigationApplication);
            }
            b(lastKnownLocation);
        }
    }

    public final void a(INavigationLoggingListener iNavigationLoggingListener) {
        this.f12949a = iNavigationLoggingListener;
    }

    public final void a(TollEntryExitListener tollEntryExitListener) {
        this.C = tollEntryExitListener;
    }

    public final void a(NavigationRoute navigationRoute) {
        a(this.o, navigationRoute, true);
    }

    public final synchronized void a(String str, ArrayList arrayList, NavLocation navLocation) {
        NavigationRoute navigationRoute = this.l;
        a(str, (List<LatLng>) arrayList, false);
        this.l = navigationRoute;
        b(navLocation, false, navigationRoute, true);
        this.g.o().a(this.l);
    }

    public final synchronized void a(String str, List<LatLng> list, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_name", "Cancel");
            if (MapplsLMSManager.isInitialised()) {
                MapplsLMSManager.getInstance().add("navigation-stop", "navigation-sdk", "0.13.6", jSONObject);
            }
        } catch (JSONException unused) {
        }
        this.l = new NavigationRoute("");
        this.E = null;
        G = false;
        this.v = 0;
        this.g.o().a(this.l);
        if (z) {
            this.g.i().a();
            this.g.a(new a());
        }
        this.m = str;
        this.n = list;
        Thread thread = this.q;
        if (thread instanceof C0644d) {
            ((C0644d) thread).c();
        }
        if (str == null) {
            this.x.o0.set(Boolean.FALSE);
            this.x.p0.set(null);
            this.o = null;
            a(false);
        }
    }

    public final void a(ArrayList arrayList) {
        LinkedList linkedList = this.h;
        if (linkedList != null) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                INavigationListener iNavigationListener = (INavigationListener) ((WeakReference) it.next()).get();
                if (iNavigationListener == null) {
                    it.remove();
                } else {
                    iNavigationListener.onAlternateRoutesUpdate(arrayList);
                }
            }
        }
    }

    public final void a(List<DirectionsRoute> list) {
        LinkedList linkedList = this.h;
        if (linkedList != null) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                INavigationListener iNavigationListener = (INavigationListener) ((WeakReference) it.next()).get();
                if (iNavigationListener == null) {
                    it.remove();
                } else {
                    iNavigationListener.onBetterRouteAvailable(list);
                }
            }
        }
    }

    public final void a(boolean z) {
        this.i = z;
        MapplsApiConfiguration.getInstance().setNavigating(z);
        this.k = false;
        if (z) {
            NavigationApplication navigationApplication = this.g;
            int i = NavigationService.p;
            navigationApplication.a(1, 0);
        } else if (this.g.f() != null) {
            MapplsApiConfiguration.getInstance().setLocation(null);
            if (MapplsLMSManager.isInitialised()) {
                MapplsLMSManager.getInstance().setCurrentLocation(null);
            }
            NavigationService f = this.g.f();
            NavigationApplication navigationApplication2 = this.g;
            int i2 = NavigationService.p;
            f.a(navigationApplication2, 1);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(29:8|9|(1:11)(2:210|(2:212|213)(12:214|(1:274)(2:218|(1:273)(1:222))|223|(1:272)(1:228)|229|(1:240)|241|(1:243)(1:271)|244|(4:(3:250|251|(3:(2:259|(1:263))(1:255)|256|(1:258)))|264|251|(0))|(1:270)(1:268)|269))|12|13|(3:15|(1:17)(1:198)|18)(2:199|(24:201|(1:203)|204|(1:208)|209|20|(1:197)|24|(1:196)|28|(2:30|(4:32|(6:35|(3:37|(2:39|(4:41|(1:43)(2:47|(1:49))|44|45))(1:50)|46)|51|52|46|33)|53|(5:55|(3:(2:59|60)(1:62)|61|56)|63|64|(3:66|(4:69|(3:75|76|77)(3:71|72|73)|74|67)|78))))|79|80|81|(3:83|(4:86|(3:92|93|94)(3:88|89|90)|91|84)|95)|96|(2:98|(1:100))(2:189|(2:191|(1:193)))|101|102|(3:118|(1:126)(1:124)|125)|127|(1:187)(2:131|(2:153|(2:156|(13:158|159|160|(1:162)|163|(1:165)|166|(1:168)|170|(1:172)(1:181)|173|(1:175)|(3:177|(1:179)|180)))))|184|185))|19|20|(1:22)|197|24|(1:26)|196|28|(0)|79|80|81|(0)|96|(0)(0)|101|102|(11:104|106|108|110|112|114|116|118|(1:120)|126|125)|127|(1:129)|187|184|185) */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x03ea, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x03eb, code lost:
        com.mappls.sdk.navigation.apis.NavigationLogger.e(r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0357 A[Catch: Exception -> 0x03ea, TryCatch #2 {Exception -> 0x03ea, blocks: (B:150:0x0353, B:152:0x0357, B:153:0x0384, B:155:0x038a, B:157:0x0398, B:158:0x039c, B:159:0x03a0, B:161:0x03b3, B:163:0x03be, B:169:0x03d4, B:166:0x03c5, B:168:0x03d0), top: B:265:0x0353 }] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x03b3 A[Catch: Exception -> 0x03ea, TryCatch #2 {Exception -> 0x03ea, blocks: (B:150:0x0353, B:152:0x0357, B:153:0x0384, B:155:0x038a, B:157:0x0398, B:158:0x039c, B:159:0x03a0, B:161:0x03b3, B:163:0x03be, B:169:0x03d4, B:166:0x03c5, B:168:0x03d0), top: B:265:0x0353 }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0149  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.mappls.sdk.navigation.NavLocation b(com.mappls.sdk.navigation.NavLocation r33, boolean r34, com.mappls.sdk.navigation.routing.NavigationRoute r35, boolean r36) {
        /*
            Method dump skipped, instructions count: 1912
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.routing.d.b(com.mappls.sdk.navigation.NavLocation, boolean, com.mappls.sdk.navigation.routing.NavigationRoute, boolean):com.mappls.sdk.navigation.NavLocation");
    }

    public final NavigationApplication b() {
        return this.g;
    }

    public final void b(INavigationListener iNavigationListener) {
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            INavigationListener iNavigationListener2 = (INavigationListener) ((WeakReference) it.next()).get();
            if (iNavigationListener2 != null) {
                if (iNavigationListener == iNavigationListener2) {
                }
            }
            it.remove();
            return;
        }
    }

    public final void b(NavigationRoute navigationRoute) {
        a(this.o, navigationRoute, false);
    }

    public final void b(boolean z) {
        this.j = z;
    }

    public final List<NavLocation> d() {
        return this.l.getImmutableAllLocations();
    }

    public final void d(NavLocation navLocation, String str, List list, NavigationRoute navigationRoute, boolean z, boolean z2) {
        String[] split;
        if (navLocation == null || str == null) {
            return;
        }
        if ((this.q != null || System.currentTimeMillis() - this.r <= this.v) && !z && z2) {
            return;
        }
        if (System.currentTimeMillis() - this.r < 30000) {
            this.u++;
        }
        com.mappls.sdk.navigation.routing.b bVar = new com.mappls.sdk.navigation.routing.b();
        bVar.f12947a = navLocation;
        bVar.b = str;
        bVar.c = list;
        if (this.u >= 3) {
            this.u = 0L;
        }
        bVar.g = ((t.l) this.x.x0.get()).f12958a;
        ((Boolean) this.x.y.a(this.w)).booleanValue();
        bVar.f = (c.a) this.x.r.get();
        bVar.e = this.w;
        bVar.d = this.g;
        synchronized (this) {
            LinkedList linkedList = this.h;
            if (linkedList != null) {
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    INavigationListener iNavigationListener = (INavigationListener) ((WeakReference) it.next()).get();
                    if (iNavigationListener == null) {
                        it.remove();
                    } else {
                        iNavigationListener.onReRoutingRequested();
                    }
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("source", navLocation.getLatitude() + Constants.SEPARATOR_COMMA + navLocation.getLongitude());
                if (str.contains(Constants.SEPARATOR_COMMA)) {
                    if (str.split(Constants.SEPARATOR_COMMA).length > 1) {
                        jSONObject.put(FirebaseAnalytics.Param.DESTINATION, split[1] + Constants.SEPARATOR_COMMA + split[0]);
                    }
                } else {
                    jSONObject.put(FirebaseAnalytics.Param.DESTINATION, str);
                }
                if (list != null && list.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    Iterator it2 = list.iterator();
                    while (it2.hasNext()) {
                        LatLng latLng = (LatLng) it2.next();
                        arrayList.add(latLng.getLatitude() + Constants.SEPARATOR_COMMA + latLng.getLongitude());
                    }
                    jSONObject.put("via_points", MapplsUtils.join("|", arrayList.toArray()));
                }
                jSONObject.put("start_point_change", z2);
                jSONObject.put("param_change", z);
                if (MapplsLMSManager.isInitialised()) {
                    MapplsLMSManager.getInstance().add("navigation-reroute-requested", "navigation-sdk", "0.13.6", jSONObject);
                }
            } catch (JSONException unused) {
            }
            NavigationLogger.d("Route is being recalculated", new Object[0]);
            Thread thread = this.q;
            C0644d c0644d = new C0644d(bVar, z);
            this.q = c0644d;
            if (thread != null) {
                c0644d.a(thread);
            }
            this.q.start();
        }
    }

    public final com.mappls.sdk.navigation.router.b e() {
        return this.l.getCurrentSegmentResult();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e(com.mappls.sdk.navigation.routing.NavigationRoute r17, com.mappls.sdk.navigation.routing.NavigationRoute r18, com.mappls.sdk.navigation.NavLocation r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.routing.d.e(com.mappls.sdk.navigation.routing.NavigationRoute, com.mappls.sdk.navigation.routing.NavigationRoute, com.mappls.sdk.navigation.NavLocation, boolean):void");
    }

    public final NavLocation f() {
        return this.o;
    }

    public final String g() {
        return this.t;
    }

    public final int h() {
        return this.l.getDistanceToFinish(this.p);
    }

    public final int i() {
        return this.l.getDistanceToNextIntermediate(this.p);
    }

    public final int j() {
        return this.l.getLeftTime(this.p);
    }

    public final NavigationRoute k() {
        return this.l;
    }

    public final double l() {
        NavigationRoute navigationRoute = this.l;
        if (navigationRoute == null || navigationRoute.getImmutableAllDirections().size() < 2) {
            return 0.0d;
        }
        NavigationRoute navigationRoute2 = this.l;
        if (navigationRoute2.currentRoute == 0) {
            return 0.0d;
        }
        List<NavLocation> immutableAllLocations = navigationRoute2.getImmutableAllLocations();
        return a(this.p, immutableAllLocations.get(this.l.currentRoute - 1), immutableAllLocations.get(this.l.currentRoute));
    }

    public final boolean l(float f, NavLocation navLocation) {
        boolean z = false;
        if (this.m != null && navLocation != null && this.l.isCalculated()) {
            boolean z2 = this.e;
            if (navLocation.hasBearing()) {
                float bearing = navLocation.getBearing();
                NavLocation nextRouteLocation = this.l.getNextRouteLocation();
                if (Math.abs(com.mappls.sdk.navigation.util.d.a(bearing, navLocation.bearingTo(nextRouteLocation))) <= 135.0d) {
                    this.A = 0L;
                } else if (navLocation.distanceTo(nextRouteLocation) > f) {
                    if (this.A == 0) {
                        this.A = System.currentTimeMillis();
                    } else if (System.currentTimeMillis() - this.A > 5000) {
                        NavigationLogger.i("bearingMotion is opposite to bearingRoute", new Object[0]);
                        z = true;
                    }
                }
            } else {
                z = z2;
            }
            this.e = z;
        }
        return z;
    }

    public final t m() {
        return this.x;
    }

    public final TollEntryExitListener n() {
        return this.C;
    }

    public final h o() {
        return this.z;
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x0162 A[EDGE_INSN: B:126:0x0162->B:49:0x0162 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x014f A[LOOP:0: B:3:0x000e->B:48:0x014f, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean o(float r29, com.mappls.sdk.navigation.NavLocation r30) {
        /*
            Method dump skipped, instructions count: 881
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.routing.d.o(float, com.mappls.sdk.navigation.NavLocation):boolean");
    }

    public final boolean q() {
        return this.i;
    }

    public final boolean r() {
        return this.k;
    }

    public final boolean s() {
        return this.q instanceof C0644d;
    }

    public final boolean t() {
        return this.l.isCalculated();
    }

    public final boolean u() {
        return this.j;
    }

    public final void v() {
        a(this.m, this.n, false);
        d(this.p, this.m, this.n, this.l, true, false);
    }

    public final void w() {
        this.k = true;
        if (this.g.f() != null) {
            NavigationService f = this.g.f();
            NavigationApplication navigationApplication = this.g;
            int i = NavigationService.p;
            f.a(navigationApplication, 1);
        }
    }

    public final boolean x() {
        return System.currentTimeMillis() - this.r > Constants.ONE_MIN_IN_MILLIS;
    }

    public final float y() {
        int i = this.B;
        if (i <= 0) {
            i = this.x.e().c();
        }
        return ((Float) this.x.j.get()).floatValue() * i;
    }

    public final synchronized NavigationRoute.a a(NavigationRoute.a aVar, boolean z) {
        NavigationRoute.a nextRouteDirectionInfo;
        nextRouteDirectionInfo = this.l.getNextRouteDirectionInfo(aVar, this.o, z);
        if (nextRouteDirectionInfo != null) {
            h hVar = this.z;
            float f = nextRouteDirectionInfo.b;
            NavLocation navLocation = this.o;
            hVar.a((navLocation == null || !navLocation.hasSpeed()) ? hVar.c : navLocation.getSpeed(), f, hVar.k, 0.0f);
        }
        return nextRouteDirectionInfo;
    }

    public final synchronized NavigationRoute.a a(NavigationRoute.a aVar, NavigationRoute.a aVar2) {
        NavigationRoute.a nextRouteDirectionInfoAfter;
        nextRouteDirectionInfoAfter = this.l.getNextRouteDirectionInfoAfter(aVar, aVar2, true);
        if (nextRouteDirectionInfoAfter != null) {
            h hVar = this.z;
            hVar.a(hVar.c, nextRouteDirectionInfoAfter.b, hVar.k, 0.0f);
        }
        return nextRouteDirectionInfoAfter;
    }

    public final void a(NavLocation navLocation, NavigationRoute navigationRoute, boolean z) {
        DirectionsRoute directionsRoute;
        NavigationLogger.d("fromRouteRefresh %s", Boolean.valueOf(z));
        try {
            this.E = RouteOptions.fromJson((String) this.g.k().f12956a.get()).profile();
        } catch (Exception unused) {
        }
        NavigationRoute navigationRoute2 = this.l;
        this.l = navigationRoute;
        if (navigationRoute != null && (directionsRoute = navigationRoute.directionsRoute) != null && directionsRoute.geometry() != null) {
            String geometry = navigationRoute.directionsRoute.geometry();
            Objects.requireNonNull(geometry);
            LineString.fromPolyline(geometry, 6);
        }
        this.p = navLocation;
        e(navigationRoute2, navigationRoute, navLocation, z);
    }

    public final void a(NavigationRoute navigationRoute, DirectionsRoute directionsRoute) {
        MapplsNavigationHelper.getInstance().setDirectionsResponse(MapplsNavigationHelper.getInstance().getDirectionsResponse().toBuilder().uuid(directionsRoute.routeId()).build());
        MapplsNavigationHelper.getInstance().setUuid(directionsRoute.routeId());
        MapplsNavigationHelper.getInstance().setRouteIndex(1);
        this.g.k().f12956a.set(RouteOptions.fromJson((String) this.g.k().f12956a.get()).toBuilder().requestUuid(directionsRoute.routeId()).build().toJson());
        if (navigationRoute != null) {
            a(this.o, navigationRoute, false);
        }
    }

    public final void b(NavLocation navLocation) {
        if (this.i || ((this.x.m() == null && this.j) || this.g.getLocationProvider().getLocationSimulation().a())) {
            a(navLocation, false);
        }
    }
}
