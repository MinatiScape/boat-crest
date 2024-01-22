package com.mappls.sdk.navigation.ui.map.route;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.BaseMapplsHelper;
import com.mappls.sdk.maps.CoordinateCallback;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.location.LocationComponentConstants;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.LineLayer;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes11.dex */
public class o {
    public AtomicReference<x> A;
    public Handler B;
    public n C;
    public r D;
    public Drawable E;
    public Drawable F;
    public String G;
    public t H;
    public u I;

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<LineString, DirectionsRoute> f12987a;
    public final List<FeatureCollection> b;
    public final List<DirectionsRoute> c;
    public final List<String> d;
    @ColorInt
    public int e;
    @ColorInt
    public int f;
    @ColorInt
    public int g;
    @ColorInt
    public int h;
    @ColorInt
    public int i;
    @ColorInt
    public int j;
    @ColorInt
    public int k;
    @ColorInt
    public int l;
    @ColorInt
    public int m;
    @ColorInt
    public int n;
    public float o;
    public float p;
    public boolean q;
    public MapplsMap r;
    public GeoJsonSource s;
    public GeoJsonSource t;
    public int u;
    public boolean v;
    public boolean w;
    public FeatureCollection x;
    public FeatureCollection y;
    public AtomicReference<com.mappls.sdk.navigation.ui.map.route.e> z;

    /* loaded from: classes11.dex */
    public class a implements t {
        public a() {
        }

        @Override // com.mappls.sdk.navigation.ui.map.route.t
        public void a(List<FeatureCollection> list) {
            o.i(o.this, list);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements u {

        /* loaded from: classes11.dex */
        public class a implements Style.OnStyleLoaded {
            public a() {
            }

            @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
            public void onStyleLoaded(@NonNull Style style) {
                o oVar = o.this;
                o.k(oVar, oVar.w, style);
                o oVar2 = o.this;
                oVar2.s(oVar2.u);
                o oVar3 = o.this;
                oVar3.m(oVar3.v, style);
            }
        }

        public b() {
        }

        @Override // com.mappls.sdk.navigation.ui.map.route.u
        public void a(List<FeatureCollection> list, HashMap<LineString, DirectionsRoute> hashMap) {
            o.this.b.addAll(list);
            o.this.f12987a.putAll(hashMap);
            o.i(o.this, list);
            o.H(o.this);
            o.this.v();
            o.this.r.getStyle(new a());
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ FeatureCollection f12991a;
        public final /* synthetic */ r b;
        public final /* synthetic */ FeatureCollection c;
        public final /* synthetic */ n d;
        public final /* synthetic */ boolean e;
        public final /* synthetic */ int f;
        public final /* synthetic */ boolean g;
        public final /* synthetic */ List h;
        public final /* synthetic */ List i;

        public c(FeatureCollection featureCollection, r rVar, FeatureCollection featureCollection2, n nVar, boolean z, int i, boolean z2, List list, List list2) {
            this.f12991a = featureCollection;
            this.b = rVar;
            this.c = featureCollection2;
            this.d = nVar;
            this.e = z;
            this.f = i;
            this.g = z2;
            this.h = list;
            this.i = list2;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            GeoJsonOptions withMaxZoom = new GeoJsonOptions().withMaxZoom(16);
            o.this.y = this.f12991a;
            if (style.getSource("mappls-navigation-waypoint-source") == null) {
                o oVar = o.this;
                r rVar = this.b;
                FeatureCollection featureCollection = oVar.y;
                rVar.getClass();
                oVar.s = new GeoJsonSource("mappls-navigation-waypoint-source", featureCollection, withMaxZoom);
                style.addSource(o.this.s);
            } else {
                o.this.s = (GeoJsonSource) style.getSource("mappls-navigation-waypoint-source");
                if (o.this.s != null) {
                    o.this.s.setGeoJson(o.this.y);
                }
            }
            GeoJsonOptions withMaxZoom2 = new GeoJsonOptions().withMaxZoom(16);
            o.this.x = this.c;
            if (style.getSource("mappls-navigation-route-source") == null) {
                o oVar2 = o.this;
                r rVar2 = this.b;
                FeatureCollection featureCollection2 = oVar2.x;
                rVar2.getClass();
                oVar2.t = new GeoJsonSource("mappls-navigation-route-source", featureCollection2, withMaxZoom2);
                style.addSource(o.this.t);
            } else {
                o.this.t = (GeoJsonSource) style.getSource("mappls-navigation-route-source");
                if (o.this.t != null) {
                    o.this.t.setGeoJson(o.this.x);
                }
            }
            o oVar3 = o.this;
            o.h(oVar3, style, this.d, oVar3.E, o.this.F, o.this.G);
            o.k(o.this, this.e, style);
            o.this.s(this.f);
            o.this.m(this.g, style);
            if (this.h.isEmpty() || !this.i.isEmpty()) {
                return;
            }
            o.this.t(this.h, this.f);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Style.OnStyleLoaded {
        public d() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            if (style.getSource("mappls-navigation-route-source") == null || style.getSource("mappls-navigation-waypoint-source") == null) {
                o.g(o.this, style);
                o oVar = o.this;
                o.h(oVar, style, oVar.C, o.this.E, o.this.F, o.this.G);
                return;
            }
            o.this.s = (GeoJsonSource) style.getSource("mappls-navigation-waypoint-source");
            if (o.this.s != null) {
                o.this.s.setGeoJson(o.this.y);
            }
            o.this.t = (GeoJsonSource) style.getSource("mappls-navigation-route-source");
            if (o.this.t != null) {
                o.this.t.setGeoJson(o.this.x);
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public String f12993a;
        public String b;

        public e() {
        }

        public /* synthetic */ e(a aVar) {
            this();
        }
    }

    public o(Context context, MapplsMap mapplsMap, @StyleRes int i, String str, i iVar, r rVar, n nVar, FeatureCollection featureCollection, FeatureCollection featureCollection2, List<DirectionsRoute> list, List<FeatureCollection> list2, HashMap<LineString, DirectionsRoute> hashMap, int i2, boolean z, boolean z2, Handler handler) {
        HashMap<LineString, DirectionsRoute> hashMap2 = new HashMap<>();
        this.f12987a = hashMap2;
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.c = arrayList2;
        this.v = true;
        this.w = true;
        this.z = new AtomicReference<>(null);
        this.A = new AtomicReference<>(null);
        this.H = new a();
        this.I = new b();
        this.d = new ArrayList();
        this.B = handler;
        this.r = mapplsMap;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.NavigationMapRoute);
        this.e = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_routeColor, ContextCompat.getColor(context, R.color.mappls_route_default_color));
        this.f = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_routeModerateCongestionColor, ContextCompat.getColor(context, R.color.mappls_route_congestion_moderate_color));
        this.h = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_routeHeavyCongestionColor, ContextCompat.getColor(context, R.color.mappls_route_congestion_heavy_color));
        this.g = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_routeSevereCongestionColor, ContextCompat.getColor(context, R.color.mappls_route_congestion_severe_color));
        this.n = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_routeShieldColor, ContextCompat.getColor(context, R.color.mappls_route_sheild_color));
        this.o = obtainStyledAttributes.getFloat(R.styleable.NavigationMapRoute_routeScale, 1.0f);
        this.q = obtainStyledAttributes.getBoolean(R.styleable.NavigationMapRoute_roundedLineCap, true);
        this.i = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_alternativeRouteColor, ContextCompat.getColor(context, R.color.mappls_alternate_route_default_color));
        this.j = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_alternativeRouteModerateCongestionColor, ContextCompat.getColor(context, R.color.mappls_alternate_route_congestion_moderate_color));
        this.l = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_alternativeRouteSevereCongestionColor, ContextCompat.getColor(context, R.color.mappls_alternate_route_congestion_severe_color));
        this.k = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_alternativeRouteHeavyCongestionColor, ContextCompat.getColor(context, R.color.mappls_alternate_route_congestion_heavy_color));
        this.m = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_alternativeRouteShieldColor, ContextCompat.getColor(context, R.color.mappls_alternate_route_sheild_color));
        this.p = obtainStyledAttributes.getFloat(R.styleable.NavigationMapRoute_alternativeRouteScale, 1.0f);
        this.D = rVar;
        this.C = nVar;
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.NavigationMapRoute_waypointIcon, R.drawable.navigation_ui_marker_via_1);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.NavigationMapRoute_destinationIcon, R.drawable.destination_marker);
        obtainStyledAttributes.recycle();
        this.E = iVar.a(resourceId);
        this.F = iVar.a(resourceId2);
        String str2 = str == null ? LocationComponentConstants.SHADOW_LAYER : str;
        this.C = nVar;
        this.G = str2;
        arrayList2.addAll(list);
        arrayList.addAll(list2);
        hashMap2.putAll(hashMap);
        mapplsMap.getStyle(new c(featureCollection2, rVar, featureCollection, nVar, z2, i2, z, list, list2));
    }

    public static void H(o oVar) {
        e eVar;
        DirectionsRoute directionsRoute = oVar.c.get(oVar.u);
        ArrayList arrayList = new ArrayList();
        if (directionsRoute != null && directionsRoute.routeOptions() != null && directionsRoute.routeOptions().coordinates() != null) {
            ArrayList arrayList2 = new ArrayList();
            for (int i = 1; i < directionsRoute.routeOptions().coordinates().size(); i++) {
                int size = directionsRoute.routeOptions().coordinates().size() - 1;
                String str = FirebaseAnalytics.Param.DESTINATION;
                if (i == size) {
                    String str2 = directionsRoute.routeOptions().coordinates().get(i);
                    if (str2.contains(Constants.SEPARATOR_COMMA)) {
                        String[] split = str2.split(Constants.SEPARATOR_COMMA);
                        Feature fromGeometry = Feature.fromGeometry(Point.fromLngLat(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
                        fromGeometry.addStringProperty("wayPoint", FirebaseAnalytics.Param.DESTINATION);
                        arrayList.add(fromGeometry);
                    } else {
                        eVar = new e(null);
                        eVar.b = str2;
                        eVar.f12993a = str;
                        arrayList2.add(eVar);
                    }
                } else {
                    String str3 = directionsRoute.routeOptions().coordinates().get(i);
                    if (str3.contains(Constants.SEPARATOR_COMMA)) {
                        String[] split2 = str3.split(Constants.SEPARATOR_COMMA);
                        Feature fromGeometry2 = Feature.fromGeometry(Point.fromLngLat(Double.parseDouble(split2[0]), Double.parseDouble(split2[1])));
                        fromGeometry2.addStringProperty("wayPoint", "origin");
                        arrayList.add(fromGeometry2);
                    } else {
                        eVar = new e(null);
                        eVar.b = str3;
                        str = "origin";
                        eVar.f12993a = str;
                        arrayList2.add(eVar);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                ArrayList arrayList3 = new ArrayList();
                HashMap hashMap = new HashMap();
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    e eVar2 = (e) it.next();
                    arrayList3.add(eVar2.b);
                    hashMap.put(eVar2.b, eVar2.f12993a);
                }
                p pVar = new p(oVar, hashMap, arrayList);
                try {
                    Object newInstance = BaseMapplsHelper.class.newInstance();
                    Method declaredMethod = BaseMapplsHelper.class.getDeclaredMethod("getAnnotation", List.class, CoordinateCallback.class);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(newInstance, arrayList3, pVar);
                    return;
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                    pVar.onFailure();
                    return;
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                    pVar.onFailure();
                    return;
                } catch (InstantiationException e4) {
                    e4.printStackTrace();
                    pVar.onFailure();
                    return;
                } catch (NoSuchMethodException e5) {
                    e5.printStackTrace();
                    pVar.onFailure();
                    return;
                } catch (InvocationTargetException e6) {
                    e6.printStackTrace();
                    pVar.onFailure();
                    return;
                }
            }
        }
        oVar.e(FeatureCollection.fromFeatures(arrayList));
    }

    public static void g(o oVar, Style style) {
        oVar.getClass();
        GeoJsonOptions withMaxZoom = new GeoJsonOptions().withMaxZoom(16);
        if (style.getSource("mappls-navigation-waypoint-source") == null) {
            r rVar = oVar.D;
            FeatureCollection featureCollection = oVar.y;
            rVar.getClass();
            GeoJsonSource geoJsonSource = new GeoJsonSource("mappls-navigation-waypoint-source", featureCollection, withMaxZoom);
            oVar.s = geoJsonSource;
            style.addSource(geoJsonSource);
        } else {
            GeoJsonSource geoJsonSource2 = (GeoJsonSource) style.getSource("mappls-navigation-waypoint-source");
            oVar.s = geoJsonSource2;
            if (geoJsonSource2 != null) {
                geoJsonSource2.setGeoJson(oVar.y);
            }
        }
        GeoJsonOptions withMaxZoom2 = new GeoJsonOptions().withMaxZoom(16);
        if (style.getSource("mappls-navigation-route-source") != null) {
            GeoJsonSource geoJsonSource3 = (GeoJsonSource) style.getSource("mappls-navigation-route-source");
            oVar.t = geoJsonSource3;
            if (geoJsonSource3 != null) {
                geoJsonSource3.setGeoJson(oVar.x);
                return;
            }
            return;
        }
        r rVar2 = oVar.D;
        FeatureCollection featureCollection2 = oVar.x;
        rVar2.getClass();
        GeoJsonSource geoJsonSource4 = new GeoJsonSource("mappls-navigation-route-source", featureCollection2, withMaxZoom2);
        oVar.t = geoJsonSource4;
        style.addSource(geoJsonSource4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0138, code lost:
        if (r2.getLayer(r0.getId()) != null) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void h(com.mappls.sdk.navigation.ui.map.route.o r31, com.mappls.sdk.maps.Style r32, com.mappls.sdk.navigation.ui.map.route.n r33, android.graphics.drawable.Drawable r34, android.graphics.drawable.Drawable r35, java.lang.String r36) {
        /*
            Method dump skipped, instructions count: 1117
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.ui.map.route.o.h(com.mappls.sdk.navigation.ui.map.route.o, com.mappls.sdk.maps.Style, com.mappls.sdk.navigation.ui.map.route.n, android.graphics.drawable.Drawable, android.graphics.drawable.Drawable, java.lang.String):void");
    }

    public static void i(o oVar, List list) {
        oVar.getClass();
        ArrayList arrayList = new ArrayList();
        for (int size = list.size() - 1; size >= 0; size--) {
            arrayList.addAll(((FeatureCollection) list.get(size)).features());
        }
        FeatureCollection fromFeatures = FeatureCollection.fromFeatures(arrayList);
        oVar.x = fromFeatures;
        oVar.t.setGeoJson(fromFeatures);
    }

    public static void k(o oVar, boolean z, Style style) {
        oVar.w = z;
        if (oVar.r != null) {
            for (String str : oVar.d) {
                if (str.equals("mappls-navigation-route-layer") || str.equals("mappls-navigation-route-shield-layer")) {
                    Layer layer = style.getLayer(str);
                    if (layer != null) {
                        ((LineLayer) layer).setFilter(z ? Expression.literal(true) : Expression.eq(Expression.get("primary-route"), true));
                    }
                }
            }
        }
    }

    public static void w(o oVar, FeatureCollection featureCollection) {
        oVar.y = featureCollection;
        oVar.s.setGeoJson(featureCollection);
    }

    public HashMap<LineString, DirectionsRoute> A() {
        return this.f12987a;
    }

    public boolean C() {
        return this.v;
    }

    public void d() {
        if (this.r.getStyle() == null || !this.r.getStyle().isFullyLoaded()) {
            return;
        }
        this.r.getStyle().removeLayer("mappls-navigation-waypoint-layer");
        this.r.getStyle().removeSource("mappls-navigation-waypoint-source");
        this.r.getStyle().removeLayer("mappls-navigation-route-layer");
        this.r.getStyle().removeLayer("mappls-navigation-route-shield-layer");
        this.r.getStyle().removeSource("mappls-navigation-route-source");
    }

    public final void e(FeatureCollection featureCollection) {
        this.y = featureCollection;
        this.s.setGeoJson(featureCollection);
    }

    public void l(List<DirectionsRoute> list, int i) {
        if (list.isEmpty()) {
            return;
        }
        if (!this.c.isEmpty()) {
            this.c.clear();
        }
        if (!this.f12987a.isEmpty()) {
            this.f12987a.clear();
        }
        if (!this.b.isEmpty()) {
            this.b.clear();
        }
        FeatureCollection fromFeatures = FeatureCollection.fromFeatures(new Feature[0]);
        this.x = fromFeatures;
        this.t.setGeoJson(fromFeatures);
        e(FeatureCollection.fromFeatures(new Feature[0]));
        this.c.addAll(list);
        this.u = i;
        this.w = list.size() > 1;
        this.v = true;
        t(list, this.u);
    }

    public void m(boolean z, Style style) {
        this.v = z;
        if (this.r != null) {
            for (String str : this.d) {
                Layer layer = style.getLayer(str);
                if (layer != null) {
                    PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
                    propertyValueArr[0] = PropertyFactory.visibility(z ? Property.VISIBLE : "none");
                    layer.setProperties(propertyValueArr);
                }
            }
        }
    }

    public boolean n(int i) {
        boolean z = this.u != i && i < this.c.size() && i >= 0;
        if (z) {
            this.u = i;
            s(i);
        }
        return z;
    }

    public String r() {
        if (this.d.isEmpty()) {
            return "mapbox-location-shadow-layer";
        }
        List<String> list = this.d;
        return list.get(list.size() - 1);
    }

    public final void s(int i) {
        this.u = i;
        if (i < 0 || i > this.b.size() - 1) {
            return;
        }
        x andSet = this.A.getAndSet(new x(i, this.b, this.H, this.B));
        if (andSet != null) {
            andSet.b();
        }
        x xVar = this.A.get();
        if (xVar != null) {
            xVar.start();
        }
    }

    public final void t(List<DirectionsRoute> list, int i) {
        com.mappls.sdk.navigation.ui.map.route.e andSet = this.z.getAndSet(new com.mappls.sdk.navigation.ui.map.route.e(list, i, this.I, this.B));
        if (andSet != null) {
            andSet.b();
        }
        com.mappls.sdk.navigation.ui.map.route.e eVar = this.z.get();
        if (eVar != null) {
            eVar.start();
        }
    }

    public void v() {
        this.r.getStyle(new d());
    }

    public List<DirectionsRoute> y() {
        return this.c;
    }
}
