package com.mappls.sdk.navigation.refresh;

import android.os.Handler;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.utils.PolylineUtils;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.refresh.a;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute;
import com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRouteManager;
import com.mappls.sdk.services.api.alongroute.models.POIAlongRouteResponse;
import com.mappls.sdk.services.api.alongroute.models.SuggestedPOI;
import com.mappls.sdk.turf.TurfConstants;
import com.mappls.sdk.turf.TurfMisc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import timber.log.Timber;
/* loaded from: classes11.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final NavigationApplication f12929a;
    public int b;
    public List<c> c;
    public boolean d;

    /* renamed from: com.mappls.sdk.navigation.refresh.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0641a implements OnResponseCallback<POIAlongRouteResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f12930a;

        public C0641a(int i) {
            this.f12930a = i;
        }

        public static int b(c cVar, c cVar2) {
            return cVar.b - cVar2.b;
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        /* renamed from: c */
        public final void onSuccess(POIAlongRouteResponse pOIAlongRouteResponse) {
            Timber.d("onSuccess", new Object[0]);
            if (!a.this.f12929a.h().q() || pOIAlongRouteResponse == null || pOIAlongRouteResponse.getSuggestedPOIs() == null || pOIAlongRouteResponse.getSuggestedPOIs().size() <= 0 || a.this.f12929a.h().k().directionsRoute == null) {
                a.this.f12929a.o().a(new ArrayList());
                a.this.f12929a.o().a(2);
            } else {
                List<Point> decode = PolylineUtils.decode(a.this.f12929a.h().k().directionsRoute.geometry(), 6);
                a.this.c = new ArrayList();
                for (SuggestedPOI suggestedPOI : pOIAlongRouteResponse.getSuggestedPOIs()) {
                    Point fromLngLat = Point.fromLngLat(suggestedPOI.getLongitude().doubleValue(), suggestedPOI.getLatitude().doubleValue());
                    Timber.d("suggestedPOI = %s", fromLngLat);
                    Feature nearestPointOnLine = TurfMisc.nearestPointOnLine(fromLngLat, decode);
                    int intValue = ((Integer) nearestPointOnLine.getNumberProperty(FirebaseAnalytics.Param.INDEX)).intValue();
                    Timber.d("Index = %d", Integer.valueOf(intValue));
                    Timber.d("nearestPoint = %s", nearestPointOnLine.toJson());
                    a.this.c.add(new c(suggestedPOI, intValue));
                }
                Collections.sort(a.this.c, new Comparator() { // from class: com.mappls.sdk.navigation.refresh.f
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int b;
                        b = a.C0641a.b((c) obj, (c) obj2);
                        return b;
                    }
                });
                a.this.f12929a.o().a(a.this.c);
                a.this.f12929a.o().a(2);
                Iterator it = a.this.c.iterator();
                while (it.hasNext()) {
                    Timber.d("Index = %d", Integer.valueOf(((c) it.next()).a()));
                }
            }
            a.this.b = this.f12930a;
            a.this.getClass();
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onError(int i, String str) {
            Timber.d("onError", new Object[0]);
            Timber.e("Request failed with code = %d and message = %s", Integer.valueOf(i), str);
            a.this.getClass();
        }
    }

    public a(NavigationApplication navigationApplication) {
        new Handler();
        this.b = 0;
        new Runnable() { // from class: com.mappls.sdk.navigation.refresh.d
            @Override // java.lang.Runnable
            public final void run() {
                a.this.d();
            }
        };
        this.f12929a = navigationApplication;
        this.d = ((Boolean) navigationApplication.k().D.get()).booleanValue();
    }

    public static void a() {
        Timber.e("endTrip", new Object[0]);
    }

    public final void c() {
        int wholeDistance = (this.f12929a.h().k().getWholeDistance() - this.f12929a.h().h()) / 5000;
        Timber.d("current segment = %d", Integer.valueOf(wholeDistance));
        if (wholeDistance > this.b) {
            try {
                f();
            } catch (Exception e) {
                NavigationLogger.e(e);
            }
        }
    }

    public final void d() {
        Timber.d("routeRecalculated", new Object[0]);
        if (!this.d || !MapplsNavigationHelper.getInstance().isNavigating()) {
            this.f12929a.o().a(2);
            return;
        }
        try {
            f();
        } catch (Exception e) {
            NavigationLogger.e(e);
        }
    }

    public final void e() {
        Timber.e("startTrip", new Object[0]);
        try {
            f();
        } catch (Exception e) {
            NavigationLogger.e(e);
        }
    }

    public final void f() {
        if (this.f12929a.h().q() && this.d) {
            if (this.f12929a.h().k() != null && this.f12929a.h().k().getWholeDistance() > 0) {
                int wholeDistance = this.f12929a.h().k().getWholeDistance() / 5000;
            }
            Timber.d("getAlongTheRoutePOIs", new Object[0]);
            int wholeDistance2 = (this.f12929a.h().k().getWholeDistance() - this.f12929a.h().h()) / 5000;
            Timber.d("Current segment before call = %d", Integer.valueOf(wholeDistance2));
            long j = wholeDistance2 * 5000;
            Timber.d("Segment distance = %d", Long.valueOf(j));
            if (this.f12929a.h().k() == null || this.f12929a.h().k().directionsRoute == null || this.f12929a.h().k().directionsRoute.geometry() == null) {
                return;
            }
            MapplsPOIAlongRouteManager.newInstance(MapplsPOIAlongRoute.builder().buffer(Integer.valueOf(((Long) this.f12929a.k().E.get()).intValue())).path(TurfMisc.lineSliceAlong(LineString.fromPolyline(this.f12929a.h().k().directionsRoute.geometry(), 6), j, j + 5000, TurfConstants.UNIT_METERS).toPolyline(6)).category((String) this.f12929a.k().F.get()).build()).call(new C0641a(wholeDistance2));
        }
    }
}
