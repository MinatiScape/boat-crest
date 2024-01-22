package com.coveiot.android.navigation.mapplsUtils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.theme.R;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.LineLayer;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.style.layers.SymbolLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import com.mappls.sdk.maps.utils.BitmapUtils;
import com.mappls.sdk.maps.utils.ColorUtils;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.RouteLeg;
import com.mappls.sdk.turf.TurfMisc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;
/* loaded from: classes5.dex */
public final class DirectionPolylinePlugin implements MapView.OnDidFinishLoadingStyleListener, MapplsMap.OnMapClickListener {
    public LatLng k;
    public MapplsMap l;
    public MapView m;
    public List<String> n;
    public LineString p;
    public List<LineString> q;
    public List<LatLng> s;
    public OnNewRouteSelectedListener v;
    public Handler h = new Handler(Looper.getMainLooper());
    public Handler i = new Handler(Looper.getMainLooper());
    public boolean j = false;
    public boolean o = false;
    public LatLng r = null;
    public List<DirectionsRoute> t = null;
    public int u = 0;
    public boolean w = false;
    public String x = "driving";
    public Runnable y = new b();
    public Runnable z = new c();

    /* loaded from: classes5.dex */
    public interface OnNewRouteSelectedListener {
        void onClickedMapFarFromRoute();

        void onNewRouteSelected(int i, DirectionsRoute directionsRoute);
    }

    /* loaded from: classes5.dex */
    public class a implements Runnable {
        public final /* synthetic */ HashMap h;
        public final /* synthetic */ List i;

        /* renamed from: com.coveiot.android.navigation.mapplsUtils.DirectionPolylinePlugin$a$a  reason: collision with other inner class name */
        /* loaded from: classes5.dex */
        public class RunnableC0295a implements Runnable {
            public final /* synthetic */ int h;
            public final /* synthetic */ DirectionsRoute i;

            public RunnableC0295a(int i, DirectionsRoute directionsRoute) {
                this.h = i;
                this.i = directionsRoute;
            }

            @Override // java.lang.Runnable
            public void run() {
                DirectionPolylinePlugin.this.O();
                if (DirectionPolylinePlugin.this.v != null) {
                    DirectionPolylinePlugin.this.v.onNewRouteSelected(this.h, this.i);
                }
            }
        }

        public a(HashMap hashMap, List list) {
            this.h = hashMap;
            this.i = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList arrayList = new ArrayList(this.h.keySet());
            Collections.sort(arrayList);
            if (arrayList.size() <= 0) {
                return;
            }
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            if (doubleValue >= 100.0d) {
                if (DirectionPolylinePlugin.this.v != null) {
                    DirectionPolylinePlugin.this.v.onClickedMapFarFromRoute();
                    return;
                }
                return;
            }
            DirectionsRoute directionsRoute = (DirectionsRoute) this.h.get(Double.valueOf(doubleValue));
            int indexOf = this.i.indexOf(directionsRoute);
            DirectionPolylinePlugin.this.u = indexOf;
            DirectionPolylinePlugin.this.m.post(new RunnableC0295a(indexOf, directionsRoute));
        }
    }

    /* loaded from: classes5.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DirectionPolylinePlugin.this.P();
        }
    }

    /* loaded from: classes5.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DirectionPolylinePlugin.this.R();
        }
    }

    /* loaded from: classes5.dex */
    public class d implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f5512a;

        public d(boolean z) {
            this.f5512a = z;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            for (String str : DirectionPolylinePlugin.this.n) {
                Layer layer = style.getLayer(str);
                if (layer != null && (layer.getId().equalsIgnoreCase("route_alternate") || layer.getId().equalsIgnoreCase("route_alternate_case"))) {
                    PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
                    propertyValueArr[0] = PropertyFactory.visibility(this.f5512a ? "none" : Property.VISIBLE);
                    layer.setProperties(propertyValueArr);
                    PropertyValue<?>[] propertyValueArr2 = new PropertyValue[1];
                    propertyValueArr2[0] = PropertyFactory.lineOpacity(Float.valueOf(this.f5512a ? 0.0f : 1.0f));
                    layer.setProperties(propertyValueArr2);
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    public class e implements Style.OnStyleLoaded {
        public e() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            if (style.getSource("traversed_directions") == null) {
                DirectionPolylinePlugin.this.M(style);
            }
        }
    }

    /* loaded from: classes5.dex */
    public class f implements Style.OnStyleLoaded {
        public f() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            if (style.getSourceAs("directions") == null) {
                DirectionPolylinePlugin.this.L(style);
                return;
            }
            DirectionPolylinePlugin directionPolylinePlugin = DirectionPolylinePlugin.this;
            directionPolylinePlugin.N(directionPolylinePlugin.o, style);
        }
    }

    /* loaded from: classes5.dex */
    public class g implements Style.OnStyleLoaded {
        public g() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            DirectionPolylinePlugin.this.N(true, style);
        }
    }

    /* loaded from: classes5.dex */
    public class h implements Style.OnStyleLoaded {

        /* loaded from: classes5.dex */
        public class a implements Runnable {
            public final /* synthetic */ Style h;

            /* renamed from: com.coveiot.android.navigation.mapplsUtils.DirectionPolylinePlugin$h$a$a  reason: collision with other inner class name */
            /* loaded from: classes5.dex */
            public class RunnableC0296a implements Runnable {
                public final /* synthetic */ FeatureCollection h;

                public RunnableC0296a(FeatureCollection featureCollection) {
                    this.h = featureCollection;
                }

                @Override // java.lang.Runnable
                public void run() {
                    GeoJsonSource geoJsonSource = (GeoJsonSource) a.this.h.getSourceAs("directions");
                    if (geoJsonSource != null) {
                        geoJsonSource.setGeoJson(this.h);
                    }
                }
            }

            public a(Style style) {
                this.h = style;
            }

            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList(DirectionPolylinePlugin.this.q.size());
                int i = 0;
                int i2 = 0;
                while (i2 < DirectionPolylinePlugin.this.q.size()) {
                    Feature fromGeometry = Feature.fromGeometry((LineString) DirectionPolylinePlugin.this.q.get(i2));
                    if (DirectionPolylinePlugin.this.x.equalsIgnoreCase("walking")) {
                        fromGeometry.addStringProperty("direction_type", DirectionPolylinePlugin.this.u == i2 ? "selected-walking" : "alternate-walking");
                    } else {
                        fromGeometry.addStringProperty("direction_type", DirectionPolylinePlugin.this.u == i2 ? "selected" : "alternate");
                    }
                    fromGeometry.addNumberProperty("position_text", Integer.valueOf(i2));
                    arrayList.add(fromGeometry);
                    i2++;
                }
                LatLng latLng = DirectionPolylinePlugin.this.k;
                if (latLng != null) {
                    Feature fromGeometry2 = Feature.fromGeometry(Point.fromLngLat(latLng.getLongitude(), DirectionPolylinePlugin.this.k.getLatitude()));
                    fromGeometry2.addStringProperty(Constants.KEY_ICON, "directions-marker-bearing-image");
                    fromGeometry2.addStringProperty("direction_type", "bearing");
                    arrayList.add(fromGeometry2);
                }
                if (DirectionPolylinePlugin.this.r != null) {
                    Feature fromGeometry3 = Feature.fromGeometry(Point.fromLngLat(DirectionPolylinePlugin.this.r.getLongitude(), DirectionPolylinePlugin.this.r.getLatitude()));
                    fromGeometry3.addStringProperty(Constants.KEY_ICON, "end_marker");
                    fromGeometry3.addStringProperty("direction_type", "marker");
                    arrayList.add(DirectionPolylinePlugin.this.F(fromGeometry3, "end-marker"));
                }
                if (DirectionPolylinePlugin.this.s != null) {
                    for (LatLng latLng2 : DirectionPolylinePlugin.this.s) {
                        Feature fromGeometry4 = Feature.fromGeometry(Point.fromLngLat(latLng2.getLongitude(), latLng2.getLatitude()));
                        fromGeometry4.addStringProperty(Constants.KEY_ICON, "via_points");
                        fromGeometry4.addStringProperty("direction_type", "marker");
                        arrayList.add(DirectionPolylinePlugin.this.F(fromGeometry4, "via-marker-" + i));
                        i++;
                    }
                }
                DirectionPolylinePlugin.this.m.post(new RunnableC0296a(FeatureCollection.fromFeatures(arrayList)));
                DirectionPolylinePlugin.this.v(arrayList, this.h);
            }
        }

        public h() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            DirectionPolylinePlugin.this.N(true, style);
            Executors.newSingleThreadExecutor().execute(new a(style));
        }
    }

    /* loaded from: classes5.dex */
    public class i implements Runnable {
        public final /* synthetic */ Style h;
        public final /* synthetic */ FeatureCollection i;

        public i(DirectionPolylinePlugin directionPolylinePlugin, Style style, FeatureCollection featureCollection) {
            this.h = style;
            this.i = featureCollection;
        }

        @Override // java.lang.Runnable
        public void run() {
            GeoJsonSource geoJsonSource = (GeoJsonSource) this.h.getSourceAs("directions");
            if (geoJsonSource != null) {
                geoJsonSource.setGeoJson(this.i);
            }
        }
    }

    /* loaded from: classes5.dex */
    public class j implements Style.OnStyleLoaded {
        public j() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            if (DirectionPolylinePlugin.this.p != null) {
                Feature fromGeometry = Feature.fromGeometry(DirectionPolylinePlugin.this.p);
                fromGeometry.addStringProperty("direction_type", "traversed");
                GeoJsonSource geoJsonSource = (GeoJsonSource) style.getSourceAs("traversed_directions");
                if (geoJsonSource != null) {
                    geoJsonSource.setGeoJson(fromGeometry);
                    return;
                }
                return;
            }
            FeatureCollection fromFeatures = FeatureCollection.fromFeatures(new ArrayList());
            GeoJsonSource geoJsonSource2 = (GeoJsonSource) style.getSourceAs("traversed_directions");
            if (geoJsonSource2 != null) {
                geoJsonSource2.setGeoJson(fromFeatures);
            }
        }
    }

    /* loaded from: classes5.dex */
    public static class k extends m {
        public static final Expression c = Expression.match(Expression.get("direction_type"), Expression.literal(false), Expression.stop("alternate-walking", Boolean.TRUE));
    }

    /* loaded from: classes5.dex */
    public static class l extends m {
        public static final Expression c;
        public static final Expression d;
        public static final Expression e;
        public static final Expression f;

        static {
            Float valueOf = Float.valueOf(1.5f);
            Expression.Interpolator exponential = Expression.exponential(valueOf);
            Expression zoom = Expression.zoom();
            Float valueOf2 = Float.valueOf(6.5f);
            c = Expression.interpolate(exponential, zoom, Expression.stop(14, valueOf2), Expression.stop(20, valueOf2));
            Expression.Interpolator exponential2 = Expression.exponential(valueOf);
            Expression zoom2 = Expression.zoom();
            Float valueOf3 = Float.valueOf(8.0f);
            d = Expression.interpolate(exponential2, zoom2, Expression.stop(14, valueOf3), Expression.stop(20, valueOf3));
            Expression.Interpolator exponential3 = Expression.exponential(valueOf);
            Expression zoom3 = Expression.zoom();
            Float valueOf4 = Float.valueOf(0.0f);
            e = Expression.interpolate(exponential3, zoom3, Expression.stop(7, valueOf4), Expression.stop(9, valueOf4), Expression.stop(11, valueOf4), Expression.stop(18, valueOf4), Expression.stop(20, valueOf4));
            Float valueOf5 = Float.valueOf(1.0f);
            Expression.interpolate(Expression.exponential(valueOf5), Expression.zoom(), Expression.stop(15, valueOf5), Expression.stop(16, valueOf5));
            f = Expression.match(Expression.get("direction_type"), Expression.literal(false), Expression.stop("alternate", Boolean.TRUE));
        }
    }

    /* loaded from: classes5.dex */
    public static class m {

        /* renamed from: a  reason: collision with root package name */
        public static final Expression f5518a = o.a(n.f5519a, n.c);
        public static final Expression b = o.a(n.b, n.d);
    }

    /* loaded from: classes5.dex */
    public static class n {

        /* renamed from: a  reason: collision with root package name */
        public static final int f5519a = Color.parseColor("#07b9fc");
        public static final int b = Color.parseColor(Constants.BLACK);
        public static final int c = Color.parseColor("#a1bbd2");
        public static final int d = Color.parseColor(Constants.BLACK);
    }

    /* loaded from: classes5.dex */
    public static class o {
        public static Expression a(@ColorInt int i, @ColorInt int i2) {
            return Expression.match(Expression.get("direction_type"), Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(0))), Expression.stop("selected", Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(i)))), Expression.stop("alternate", Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(i2)))), Expression.stop("traversed", Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(i2)))));
        }
    }

    /* loaded from: classes5.dex */
    public static class p {
        public static LineLayer a(String str, float f, Expression expression, Expression expression2, Expression expression3, Expression expression4) {
            return b(str, f, expression, expression2, expression3, expression4, null);
        }

        public static LineLayer b(String str, float f, Expression expression, Expression expression2, Expression expression3, Expression expression4, Expression expression5) {
            LineLayer lineLayer = new LineLayer(str, "directions");
            lineLayer.setSourceLayer("directions");
            lineLayer.setProperties(PropertyFactory.lineCap("round"), PropertyFactory.lineJoin("round"), PropertyFactory.lineColor(expression2), PropertyFactory.lineWidth(expression3), PropertyFactory.lineOffset(expression4));
            if (expression5 != null) {
                lineLayer.setProperties(PropertyFactory.lineOpacity(expression5));
            }
            lineLayer.setFilter(expression);
            lineLayer.setMinZoom(f);
            return lineLayer;
        }

        public static LineLayer c(String str, float f, Expression expression, Expression expression2, Expression expression3, Expression expression4) {
            return d(str, f, expression, expression2, expression3, expression4, null);
        }

        public static LineLayer d(String str, float f, Expression expression, Expression expression2, Expression expression3, Expression expression4, Expression expression5) {
            LineLayer lineLayer = new LineLayer(str, "traversed_directions");
            lineLayer.setSourceLayer("directions");
            lineLayer.setProperties(PropertyFactory.lineCap("round"), PropertyFactory.lineJoin("round"), PropertyFactory.lineColor(expression2), PropertyFactory.lineWidth(expression3), PropertyFactory.lineOffset(expression4));
            if (expression5 != null) {
                lineLayer.setProperties(PropertyFactory.lineOpacity(expression5));
            }
            lineLayer.setFilter(expression);
            lineLayer.setMinZoom(f);
            return lineLayer;
        }
    }

    /* loaded from: classes5.dex */
    public static class q extends m {
        public static final Expression c;

        static {
            Expression expression = Expression.get("direction_type");
            Expression literal = Expression.literal(false);
            Boolean bool = Boolean.TRUE;
            c = Expression.match(expression, literal, Expression.stop("marker", bool));
            Expression.match(Expression.get("direction_type"), Expression.literal(false), Expression.stop("advices", bool));
        }
    }

    /* loaded from: classes5.dex */
    public static class r extends m {
        public static final Expression c = Expression.match(Expression.get("direction_type"), Expression.literal(false), Expression.stop("selected-walking", Boolean.TRUE));
    }

    /* loaded from: classes5.dex */
    public static class s extends m {
        public static final Expression c;
        public static final Expression d;
        public static final Expression e;
        public static final Expression f;
        public static final Expression g;

        static {
            Float valueOf = Float.valueOf(1.5f);
            Expression.Interpolator exponential = Expression.exponential(valueOf);
            Expression zoom = Expression.zoom();
            Float valueOf2 = Float.valueOf(6.5f);
            c = Expression.interpolate(exponential, zoom, Expression.stop(14, valueOf2), Expression.stop(20, valueOf2));
            Expression.Interpolator exponential2 = Expression.exponential(valueOf);
            Expression zoom2 = Expression.zoom();
            Float valueOf3 = Float.valueOf(8.0f);
            d = Expression.interpolate(exponential2, zoom2, Expression.stop(14, valueOf3), Expression.stop(20, valueOf3));
            Expression.Interpolator exponential3 = Expression.exponential(valueOf);
            Expression zoom3 = Expression.zoom();
            Float valueOf4 = Float.valueOf(0.0f);
            e = Expression.interpolate(exponential3, zoom3, Expression.stop(14, valueOf4), Expression.stop(20, valueOf4));
            Float valueOf5 = Float.valueOf(1.0f);
            f = Expression.interpolate(Expression.exponential(valueOf5), Expression.zoom(), Expression.stop(15, valueOf5), Expression.stop(16, valueOf5));
            g = Expression.match(Expression.get("direction_type"), Expression.literal(false), Expression.stop("selected", Boolean.TRUE));
        }
    }

    /* loaded from: classes5.dex */
    public static class t extends m {
        public static final Expression c;
        public static final Expression d;
        public static final Expression e;
        public static final Expression f;
        public static final Expression g;

        static {
            Float valueOf = Float.valueOf(1.5f);
            Expression.Interpolator exponential = Expression.exponential(valueOf);
            Expression zoom = Expression.zoom();
            Float valueOf2 = Float.valueOf(6.5f);
            c = Expression.interpolate(exponential, zoom, Expression.stop(14, valueOf2), Expression.stop(20, valueOf2));
            Expression.Interpolator exponential2 = Expression.exponential(valueOf);
            Expression zoom2 = Expression.zoom();
            Float valueOf3 = Float.valueOf(8.0f);
            d = Expression.interpolate(exponential2, zoom2, Expression.stop(14, valueOf3), Expression.stop(20, valueOf3));
            Expression.Interpolator exponential3 = Expression.exponential(valueOf);
            Expression zoom3 = Expression.zoom();
            Float valueOf4 = Float.valueOf(0.0f);
            e = Expression.interpolate(exponential3, zoom3, Expression.stop(7, valueOf4), Expression.stop(9, valueOf4), Expression.stop(11, valueOf4), Expression.stop(18, valueOf4), Expression.stop(20, valueOf4));
            Float valueOf5 = Float.valueOf(1.0f);
            f = Expression.interpolate(Expression.exponential(valueOf5), Expression.zoom(), Expression.stop(15, valueOf5), Expression.stop(16, valueOf5));
            g = Expression.match(Expression.get("direction_type"), Expression.literal(false), Expression.stop("traversed", Boolean.TRUE));
        }
    }

    public DirectionPolylinePlugin(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap) {
        this.l = mapplsMap;
        this.m = mapView;
        Q();
        T();
        mapView.addOnDidFinishLoadingStyleListener(this);
        mapplsMap.addOnMapClickListener(this);
    }

    public final void A(Style style) {
        try {
            Resources resources = this.m.getContext().getResources();
            int i2 = R.drawable.destination_marker_icon;
            Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(resources.getDrawable(i2));
            Bitmap bitmapFromDrawable2 = BitmapUtils.getBitmapFromDrawable(this.m.getContext().getResources().getDrawable(i2));
            Bitmap bitmapFromDrawable3 = BitmapUtils.getBitmapFromDrawable(this.m.getContext().getResources().getDrawable(i2));
            Bitmap bitmapFromDrawable4 = BitmapUtils.getBitmapFromDrawable(this.m.getContext().getResources().getDrawable(i2));
            Bitmap bitmapFromDrawable5 = BitmapUtils.getBitmapFromDrawable(this.m.getContext().getResources().getDrawable(R.drawable.ic_walking_selected_dot));
            Bitmap bitmapFromDrawable6 = BitmapUtils.getBitmapFromDrawable(this.m.getContext().getResources().getDrawable(R.drawable.ic_walking_alternate));
            style.addImage("start_marker", bitmapFromDrawable);
            style.addImage("end_marker", bitmapFromDrawable2);
            style.addImage("via_points", bitmapFromDrawable3);
            style.addImage("track_points", bitmapFromDrawable4);
            style.addImage("selected_dotted_route_image", bitmapFromDrawable5);
            style.addImage("alternate_dotted_route_image", bitmapFromDrawable6);
        } catch (Exception unused) {
        }
    }

    public final void B(Style style) {
        Expression expression = s.g;
        Expression expression2 = m.f5518a;
        Expression expression3 = s.c;
        Expression expression4 = s.e;
        D(p.b("route_selected_case", 0.0f, s.g, m.b, s.d, expression4, s.f), p.a("route_selected", 0.0f, expression, expression2, expression3, expression4), "highway_name", style);
    }

    public final void C(Style style) {
        SymbolLayer withProperties = new SymbolLayer("directions-marker-layer", "directions").withProperties(PropertyFactory.iconImage(Expression.get(Constants.KEY_ICON)), PropertyFactory.iconAnchor("bottom"));
        withProperties.withFilter(q.c);
        withProperties.setSourceLayer("directions");
        style.addLayer(withProperties);
        this.n.add(withProperties.getId());
    }

    public final void D(Layer layer, Layer layer2, String str, Style style) {
        if (style.getLayer(str) != null) {
            style.addLayerBelow(layer, str);
        } else {
            style.addLayer(layer);
        }
        style.addLayerAbove(layer2, layer.getId());
        this.n.add(layer.getId());
        this.n.add(layer2.getId());
    }

    public final void E(Style style) {
        Expression expression = t.g;
        Expression expression2 = m.f5518a;
        Expression expression3 = t.c;
        Expression expression4 = t.e;
        D(p.d("route_traversed_case", 0.0f, t.g, m.b, t.d, expression4, t.f), p.c("route_traversed", 0.0f, expression, expression2, expression3, expression4), "highway_name", style);
    }

    public final Feature F(Feature feature, String str) {
        try {
            JSONObject jSONObject = new JSONObject(feature.toJson());
            jSONObject.put("id", str);
            return Feature.fromJson(jSONObject.toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public final synchronized void G(MapplsMap mapplsMap, HashMap<Double, DirectionsRoute> hashMap, Point point, PointF pointF, HashMap<LineString, DirectionsRoute> hashMap2) {
        for (LineString lineString : hashMap2.keySet()) {
            Point J = J(point, lineString);
            if (J == null) {
                return;
            }
            PointF screenLocation = mapplsMap.getProjection().toScreenLocation(new LatLng(J.latitude(), J.longitude()));
            hashMap.put(Double.valueOf(Math.hypot(pointF.x - screenLocation.x, pointF.y - screenLocation.y)), hashMap2.get(lineString));
        }
    }

    public final List<Feature> H(DirectionsRoute directionsRoute, LineString lineString) {
        ArrayList arrayList = new ArrayList();
        if (directionsRoute.legs() != null) {
            RouteLeg routeLeg = directionsRoute.legs().get(0);
            if (routeLeg != null && routeLeg.annotation() != null && routeLeg.annotation().congestion() != null) {
                List<String> congestion = routeLeg.annotation().congestion();
                for (int i2 = 0; i2 < congestion.size(); i2++) {
                    if (congestion.size() + 1 <= lineString.coordinates().size()) {
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(lineString.coordinates().get(i2));
                        arrayList2.add(lineString.coordinates().get(i2 + 1));
                        Feature fromGeometry = Feature.fromGeometry(LineString.fromLngLats(arrayList2));
                        fromGeometry.addStringProperty(DirectionsCriteria.ANNOTATION_CONGESTION, congestion.get(i2));
                        arrayList.add(fromGeometry);
                    }
                }
            }
        }
        return arrayList;
    }

    public final synchronized void I(MapplsMap mapplsMap, @NonNull LatLng latLng, HashMap<LineString, DirectionsRoute> hashMap, List<DirectionsRoute> list) {
        HashMap<Double, DirectionsRoute> hashMap2 = new HashMap<>();
        G(mapplsMap, hashMap2, Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()), mapplsMap.getProjection().toScreenLocation(latLng), hashMap);
        Executors.newSingleThreadExecutor().execute(new a(hashMap2, list));
    }

    public final Point J(Point point, LineString lineString) {
        return (Point) TurfMisc.nearestPointOnLine(point, lineString.coordinates()).geometry();
    }

    public final String K() {
        List<String> list = this.n;
        return list.get(list.size() - 1);
    }

    public final void L(Style style) {
        this.n = new ArrayList();
        x(style);
        y(style);
        A(style);
    }

    public final void M(Style style) {
        if (style.getSource("traversed_directions") == null) {
            style.addSource(new GeoJsonSource("traversed_directions"));
        }
        E(style);
    }

    public final void N(boolean z, Style style) {
        List<String> list;
        List<Layer> layers;
        if (this.l == null || (list = this.n) == null || list.size() <= 0 || (layers = style.getLayers()) == null || layers.size() <= 0) {
            return;
        }
        for (Layer layer : layers) {
            if (this.n.contains(layer.getId())) {
                PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
                propertyValueArr[0] = PropertyFactory.visibility(z ? Property.VISIBLE : "none");
                layer.setProperties(propertyValueArr);
            }
        }
        setBearingLayerVisibility(this.j, style);
    }

    public final void O() {
        this.h.removeCallbacksAndMessages(null);
        this.h.postDelayed(this.y, 100L);
    }

    public final synchronized void P() {
        if (this.q == null) {
            return;
        }
        this.l.getStyle(new h());
    }

    public final void Q() {
        this.l.getStyle(new f());
    }

    public final synchronized void R() {
        this.l.getStyle(new j());
    }

    public final void S() {
        this.i.removeCallbacksAndMessages(null);
        this.i.postDelayed(this.z, 10L);
    }

    public final void T() {
        this.l.getStyle(new e());
    }

    public int getSelected() {
        return this.u;
    }

    public boolean isEnableCongestion() {
        return this.w;
    }

    public boolean isEnabled() {
        return this.o;
    }

    @Override // com.mappls.sdk.maps.MapView.OnDidFinishLoadingStyleListener
    public void onDidFinishLoadingStyle() {
        Q();
        T();
        if (isEnabled()) {
            O();
        }
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnMapClickListener
    public boolean onMapClick(@NonNull LatLng latLng) {
        if (this.t != null) {
            HashMap<LineString, DirectionsRoute> hashMap = new HashMap<>();
            for (DirectionsRoute directionsRoute : this.t) {
                if (directionsRoute.geometry() != null) {
                    hashMap.put(LineString.fromPolyline(directionsRoute.geometry(), 6), directionsRoute);
                }
            }
            I(this.l, latLng, hashMap, this.t);
            return false;
        }
        OnNewRouteSelectedListener onNewRouteSelectedListener = this.v;
        if (onNewRouteSelectedListener != null) {
            onNewRouteSelectedListener.onClickedMapFarFromRoute();
            return false;
        }
        return false;
    }

    public void removeAllData() {
        if (this.l.getStyle() == null || !this.l.getStyle().isFullyLoaded()) {
            return;
        }
        GeoJsonSource geoJsonSource = (GeoJsonSource) this.l.getStyle().getSourceAs("directions");
        if (geoJsonSource != null) {
            geoJsonSource.setGeoJson(FeatureCollection.fromFeatures(new ArrayList()));
        }
        GeoJsonSource geoJsonSource2 = (GeoJsonSource) this.l.getStyle().getSourceAs("traversed_directions");
        if (geoJsonSource2 != null) {
            geoJsonSource2.setGeoJson(FeatureCollection.fromFeatures(new ArrayList()));
        }
    }

    public void removePolylineClickListener() {
        this.v = null;
    }

    public void setBearingIcon(float f2, LatLng latLng) {
        this.k = latLng;
        O();
    }

    public void setBearingLayerVisibility(boolean z, Style style) {
        Layer layer = style.getLayer("directions-marker-bearing-layer");
        if (layer != null) {
            PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
            propertyValueArr[0] = PropertyFactory.visibility(z ? Property.VISIBLE : "none");
            layer.setProperties(propertyValueArr);
        }
        this.j = z;
    }

    public void setCurrentLocation(Location location) {
        List<LineString> list = this.q;
        if (list == null || list.size() <= 0 || this.q.get(0) == null) {
            return;
        }
        Point point = this.q.get(0).coordinates().get(0);
        if (point.latitude() == location.getLatitude() && point.longitude() == location.getLongitude()) {
            return;
        }
        this.p = TurfMisc.lineSlice(point, Point.fromLngLat(location.getLongitude(), location.getLatitude()), this.q.get(0));
        S();
    }

    public void setEnableCongestion(boolean z) {
        this.w = z;
    }

    public void setEnabled(boolean z) {
        if (z != this.o) {
            this.o = z;
            Q();
        }
    }

    public void setOnNewRouteSelectedListener(OnNewRouteSelectedListener onNewRouteSelectedListener) {
        this.v = onNewRouteSelectedListener;
    }

    public void setProfile(String str) {
        this.x = str;
    }

    public void setSelected(int i2) {
        this.u = i2;
        this.l.getStyle(new g());
        O();
    }

    public void setTrips(List<LineString> list, LatLng latLng, LatLng latLng2, List<LatLng> list2, List<DirectionsRoute> list3) {
        this.r = latLng2;
        this.s = list2;
        this.q = list;
        this.t = list3;
        this.u = 0;
        O();
        S();
    }

    public void showSelectedOnly(boolean z) {
        this.l.getStyle(new d(z));
    }

    public final void t(Style style) {
        Float valueOf = Float.valueOf(1.0f);
        Expression interpolate = Expression.interpolate(Expression.exponential(valueOf), Expression.zoom(), Expression.stop(1, valueOf), Expression.stop(2, valueOf), Expression.stop(3, valueOf), Expression.stop(4, valueOf), Expression.stop(5, valueOf), Expression.stop(6, Float.valueOf(2.0f)), Expression.stop(7, Float.valueOf(3.0f)), Expression.stop(8, Float.valueOf(4.0f)), Expression.stop(9, Float.valueOf(5.0f)), Expression.stop(10, Float.valueOf(6.0f)), Expression.stop(22, Float.valueOf(30.0f)));
        SymbolLayer symbolLayer = new SymbolLayer("route_alternate_dotted", "directions");
        Boolean bool = Boolean.TRUE;
        SymbolLayer withFilter = symbolLayer.withProperties(PropertyFactory.iconImage("alternate_dotted_route_image"), PropertyFactory.iconIgnorePlacement(bool), PropertyFactory.symbolPlacement("line"), PropertyFactory.symbolSpacing(interpolate), PropertyFactory.iconAllowOverlap(bool)).withFilter(k.c);
        List<String> list = this.n;
        String str = list.get(list.size() - 1);
        if (str != null && style.getLayer(str) != null) {
            style.addLayerBelow(withFilter, str);
        } else {
            style.addLayer(withFilter);
        }
        this.n.add(withFilter.getId());
    }

    public void toggle() {
        this.o = !this.o;
        Q();
    }

    public final void u(Style style) {
        Expression expression = l.f;
        Expression expression2 = m.f5518a;
        Expression expression3 = l.c;
        Expression expression4 = l.e;
        D(p.a("route_alternate_case", 0.0f, l.f, m.b, l.d, expression4), p.a("route_alternate", 0.0f, expression, expression2, expression3, expression4), K(), style);
    }

    public final void v(List<Feature> list, Style style) {
        List<DirectionsRoute> list2;
        if (!this.w || (list2 = this.t) == null || this.q == null) {
            return;
        }
        list.addAll(H(list2.get(this.u), this.q.get(this.u)));
        this.m.post(new i(this, style, FeatureCollection.fromFeatures(list)));
    }

    public final void w(Style style) {
        Expression.Interpolator exponential = Expression.exponential(Float.valueOf(1.5f));
        Expression zoom = Expression.zoom();
        Float valueOf = Float.valueOf(6.5f);
        LineLayer withProperties = new LineLayer("congestion_layer", "directions").withProperties(PropertyFactory.lineCap("round"), PropertyFactory.lineJoin("round"), PropertyFactory.lineWidth(Expression.interpolate(exponential, zoom, Expression.stop(14, valueOf), Expression.stop(20, valueOf))), PropertyFactory.lineColor(Expression.match(Expression.get(DirectionsCriteria.ANNOTATION_CONGESTION), Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(0))), Expression.stop("moderate", Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#ff8c1a")))), Expression.stop("heavy", Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#981b25")))), Expression.stop("severe", Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#8b0000")))))));
        if (style.getSource("congestion_layer") == null) {
            style.addLayerAbove(withProperties, "route_selected");
        }
    }

    public final void x(Style style) {
        style.addSource(new GeoJsonSource("directions"));
    }

    public final void y(Style style) {
        try {
            B(style);
            u(style);
            z(style);
            t(style);
            w(style);
            C(style);
        } catch (Exception unused) {
            Timber.e("Unable to attach directions Layers to current style.", new Object[0]);
        }
    }

    public final void z(Style style) {
        Float valueOf = Float.valueOf(1.0f);
        Expression interpolate = Expression.interpolate(Expression.exponential(valueOf), Expression.zoom(), Expression.stop(1, valueOf), Expression.stop(2, valueOf), Expression.stop(3, valueOf), Expression.stop(4, valueOf), Expression.stop(5, valueOf), Expression.stop(6, Float.valueOf(2.0f)), Expression.stop(7, Float.valueOf(3.0f)), Expression.stop(8, Float.valueOf(4.0f)), Expression.stop(9, Float.valueOf(5.0f)), Expression.stop(10, Float.valueOf(6.0f)), Expression.stop(22, Float.valueOf(30.0f)));
        SymbolLayer symbolLayer = new SymbolLayer("route_selected_dotted", "directions");
        Boolean bool = Boolean.TRUE;
        SymbolLayer withFilter = symbolLayer.withProperties(PropertyFactory.iconImage("selected_dotted_route_image"), PropertyFactory.iconIgnorePlacement(bool), PropertyFactory.symbolPlacement("line"), PropertyFactory.symbolSpacing(interpolate), PropertyFactory.iconAllowOverlap(bool)).withFilter(r.c);
        List<String> list = this.n;
        String str = list.get(list.size() - 1);
        if (str != null && style.getLayer(str) != null) {
            style.addLayerBelow(withFilter, str);
        } else {
            style.addLayer(withFilter);
        }
        this.n.add(withFilter.getId());
    }

    public void setTrips(List<LineString> list, LatLng latLng, LatLng latLng2, List<LatLng> list2, List<DirectionsRoute> list3, int i2) {
        this.r = latLng2;
        this.s = list2;
        this.q = list;
        this.t = list3;
        this.u = i2;
        O();
        S();
    }
}
