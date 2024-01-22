package com.mappls.sdk.direction.ui.plugin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.model.DirectionOptions;
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
import com.mappls.sdk.services.utils.CongestionDelayInfo;
import com.mappls.sdk.services.utils.MapplsUtils;
import com.mappls.sdk.turf.TurfMisc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;
/* loaded from: classes11.dex */
public final class d implements MapView.OnDidFinishLoadingStyleListener, MapplsMap.OnMapClickListener {
    public MapplsMap j;
    public MapView k;
    public ArrayList l;
    public ArrayList m;
    public List<LatLng> p;
    public k s;
    public DirectionOptions t;
    public Handler h = new Handler(Looper.getMainLooper());
    public boolean i = false;
    public LatLng n = null;
    public LatLng o = null;
    public List<DirectionsRoute> q = null;
    public int r = 0;
    public boolean u = true;
    public Runnable v = new a();

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            d.c(d.this);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Style.OnStyleLoaded {
        public b() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            if (style.getSourceAs("com.mappls.sdk.directions.DIRECTIONS_SOURCE_ID") == null) {
                d.e(d.this, style);
                return;
            }
            d dVar = d.this;
            dVar.getClass();
            d.g(dVar, false, style);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Style.OnStyleLoaded {
        public c() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            d.g(d.this, true, style);
        }
    }

    /* renamed from: com.mappls.sdk.direction.ui.plugin.d$d  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0618d extends e {

        /* renamed from: a  reason: collision with root package name */
        public static final Expression f12610a;
        public static final Expression b;
        public static final Expression c;
        public static final Expression d;

        static {
            Float valueOf = Float.valueOf(1.5f);
            Expression.Interpolator exponential = Expression.exponential(valueOf);
            Expression zoom = Expression.zoom();
            Float valueOf2 = Float.valueOf(6.5f);
            f12610a = Expression.interpolate(exponential, zoom, Expression.stop(14, valueOf2), Expression.stop(20, valueOf2));
            Expression.Interpolator exponential2 = Expression.exponential(valueOf);
            Expression zoom2 = Expression.zoom();
            Float valueOf3 = Float.valueOf(8.0f);
            b = Expression.interpolate(exponential2, zoom2, Expression.stop(14, valueOf3), Expression.stop(20, valueOf3));
            Expression.Interpolator exponential3 = Expression.exponential(valueOf);
            Expression zoom3 = Expression.zoom();
            Float valueOf4 = Float.valueOf(0.0f);
            c = Expression.interpolate(exponential3, zoom3, Expression.stop(7, valueOf4), Expression.stop(9, valueOf4), Expression.stop(11, valueOf4), Expression.stop(18, valueOf4), Expression.stop(20, valueOf4));
            Float valueOf5 = Float.valueOf(1.0f);
            Expression.interpolate(Expression.exponential(valueOf5), Expression.zoom(), Expression.stop(15, valueOf5), Expression.stop(16, valueOf5));
            d = Expression.match(Expression.get("mappls_direction_direction_type"), Expression.literal(false), Expression.stop("alternate", Boolean.TRUE));
        }
    }

    /* loaded from: classes11.dex */
    public static class e {
        static {
            g.a(f.f12611a, f.c);
            g.a(f.b, f.d);
        }
    }

    /* loaded from: classes11.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        public static final int f12611a = Color.parseColor("#07b9fc");
        public static final int b = Color.parseColor(Constants.BLACK);
        public static final int c = Color.parseColor("#a1bbd2");
        public static final int d = Color.parseColor(Constants.BLACK);
    }

    /* loaded from: classes11.dex */
    public static class g {
        public static void a(@ColorInt int i, @ColorInt int i2) {
            Expression.match(Expression.get("mappls_direction_direction_type"), Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(0))), Expression.stop("selected", Expression.match(Expression.get(DirectionsCriteria.ANNOTATION_CONGESTION), Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(i))), Expression.stop("moderate", Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#ff8c1a"))))), Expression.stop("heavy", Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#981b25"))))), Expression.stop("severe", Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#8b0000"))))))), Expression.stop("alternate", Expression.match(Expression.get(DirectionsCriteria.ANNOTATION_CONGESTION), Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(i2))), Expression.stop("moderate", Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#BEA087")))), Expression.stop("heavy", Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#B58281")))), Expression.stop("severe", Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#EFA7A7")))))));
        }
    }

    /* loaded from: classes11.dex */
    public static class h {
        public static LineLayer a(String str, Expression expression, Expression expression2, Expression expression3, Expression expression4, Expression expression5) {
            LineLayer lineLayer = new LineLayer(str, "com.mappls.sdk.directions.DIRECTIONS_SOURCE_ID");
            lineLayer.setSourceLayer("directions");
            lineLayer.setProperties(PropertyFactory.lineCap("round"), PropertyFactory.lineJoin("round"), PropertyFactory.lineColor(expression2), PropertyFactory.lineWidth(expression3), PropertyFactory.lineOffset(expression4));
            if (expression5 != null) {
                lineLayer.setProperties(PropertyFactory.lineOpacity(expression5));
            }
            if (expression != null) {
                lineLayer.setFilter(expression);
            }
            return lineLayer;
        }
    }

    /* loaded from: classes11.dex */
    public static class i extends e {

        /* renamed from: a  reason: collision with root package name */
        public static final Expression f12612a = Expression.match(Expression.get("mappls_direction_direction_type"), Expression.literal(false), Expression.stop("segment", Boolean.TRUE));
    }

    /* loaded from: classes11.dex */
    public static class j extends e {

        /* renamed from: a  reason: collision with root package name */
        public static final Expression f12613a;

        static {
            Expression expression = Expression.get("mappls_direction_direction_type");
            Expression literal = Expression.literal(false);
            Boolean bool = Boolean.TRUE;
            f12613a = Expression.match(expression, literal, Expression.stop("marker", bool));
            Expression.match(Expression.get("mappls_direction_direction_type"), Expression.literal(false), Expression.stop("advices", bool));
        }
    }

    /* loaded from: classes11.dex */
    public interface k {
        void a(int i, DirectionsRoute directionsRoute);
    }

    /* loaded from: classes11.dex */
    public static class l extends e {

        /* renamed from: a  reason: collision with root package name */
        public static final Expression f12614a;
        public static final Expression b;
        public static final Expression c;
        public static final Expression d;
        public static final Expression e;

        static {
            Float valueOf = Float.valueOf(1.5f);
            Expression.Interpolator exponential = Expression.exponential(valueOf);
            Expression zoom = Expression.zoom();
            Float valueOf2 = Float.valueOf(6.5f);
            f12614a = Expression.interpolate(exponential, zoom, Expression.stop(14, valueOf2), Expression.stop(20, valueOf2));
            Expression.Interpolator exponential2 = Expression.exponential(valueOf);
            Expression zoom2 = Expression.zoom();
            Float valueOf3 = Float.valueOf(8.0f);
            b = Expression.interpolate(exponential2, zoom2, Expression.stop(14, valueOf3), Expression.stop(20, valueOf3));
            Expression.Interpolator exponential3 = Expression.exponential(valueOf);
            Expression zoom3 = Expression.zoom();
            Float valueOf4 = Float.valueOf(0.0f);
            c = Expression.interpolate(exponential3, zoom3, Expression.stop(14, valueOf4), Expression.stop(20, valueOf4));
            Float valueOf5 = Float.valueOf(1.0f);
            d = Expression.interpolate(Expression.exponential(valueOf5), Expression.zoom(), Expression.stop(15, valueOf5), Expression.stop(16, valueOf5));
            e = Expression.match(Expression.get("mappls_direction_direction_type"), Expression.literal(false), Expression.stop("selected", Boolean.TRUE));
        }
    }

    /* loaded from: classes11.dex */
    public static class m {
        public static Bitmap a(@NonNull LinearLayout linearLayout) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            linearLayout.measure(makeMeasureSpec, makeMeasureSpec);
            int measuredWidth = linearLayout.getMeasuredWidth();
            int measuredHeight = linearLayout.getMeasuredHeight();
            linearLayout.layout(0, 0, measuredWidth, measuredHeight);
            Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            options.inBitmap = createBitmap;
            int i2 = options.outHeight;
            int i3 = options.outWidth;
            if (i2 > measuredHeight || i3 > measuredWidth) {
                int i4 = i2 / 2;
                int i5 = i3 / 2;
                while (i4 / i >= measuredHeight && i5 / i >= measuredWidth) {
                    i *= 2;
                }
            }
            options.inSampleSize = i;
            Bitmap bitmap = options.inBitmap;
            bitmap.eraseColor(0);
            linearLayout.draw(new Canvas(bitmap));
            return bitmap;
        }
    }

    public d(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, DirectionOptions directionOptions) {
        this.j = mapplsMap;
        this.k = mapView;
        this.t = directionOptions;
        l();
        mapView.addOnDidFinishLoadingStyleListener(this);
        mapplsMap.addOnMapClickListener(this);
    }

    public static Feature a(d dVar, Feature feature, String str) {
        dVar.getClass();
        try {
            JSONObject jSONObject = new JSONObject(feature.toJson());
            jSONObject.put("id", str);
            return Feature.fromJson(jSONObject.toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Expression b(@ColorInt int i2, @ColorInt int i3) {
        return Expression.match(Expression.get("mappls_direction_direction_type"), Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(0))), Expression.stop("selected", Expression.match(Expression.get(DirectionsCriteria.ANNOTATION_CONGESTION), Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(i2))), Expression.stop("moderate", Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#ff8c1a"))))), Expression.stop("heavy", Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#981b25"))))), Expression.stop("severe", Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#8b0000"))))), Expression.stop("low", Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(i2)))), Expression.stop("unknown", Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(i2)))))), Expression.stop("alternate", Expression.match(Expression.get(DirectionsCriteria.ANNOTATION_CONGESTION), Expression.toColor(Expression.literal(ColorUtils.colorToRgbaString(i3))), Expression.stop("moderate", Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#BEA087")))), Expression.stop("heavy", Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#B58281")))), Expression.stop("severe", Expression.literal(ColorUtils.colorToRgbaString(Color.parseColor("#EFA7A7")))))));
    }

    public static void c(d dVar) {
        synchronized (dVar) {
            if (dVar.m != null) {
                Executors.newSingleThreadExecutor().execute(new com.mappls.sdk.direction.ui.plugin.f(dVar));
            }
        }
    }

    public static void e(d dVar, Style style) {
        dVar.getClass();
        dVar.l = new ArrayList();
        style.addSource(new GeoJsonSource("com.mappls.sdk.directions.DIRECTIONS_SOURCE_ID"));
        try {
            dVar.m(style);
            dVar.h(style);
            dVar.o(style);
            dVar.j(style);
        } catch (Exception unused) {
            Timber.e("Unable to attach directions Layers to current style.", new Object[0]);
        }
        try {
            Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(dVar.k.getContext().getResources().getDrawable(dVar.t.sourceMarker().intValue()));
            Bitmap bitmapFromDrawable2 = BitmapUtils.getBitmapFromDrawable(dVar.k.getContext().getResources().getDrawable(dVar.t.destinationMarker().intValue()));
            Bitmap bitmapFromDrawable3 = BitmapUtils.getBitmapFromDrawable(dVar.k.getContext().getResources().getDrawable(dVar.t.firstWayPointMarker().intValue()));
            Bitmap bitmapFromDrawable4 = BitmapUtils.getBitmapFromDrawable(dVar.k.getContext().getResources().getDrawable(dVar.t.secondWayPointMarker().intValue()));
            Bitmap bitmapFromDrawable5 = BitmapUtils.getBitmapFromDrawable(dVar.k.getContext().getResources().getDrawable(dVar.t.thirdWayPointMarker().intValue()));
            Bitmap bitmapFromDrawable6 = BitmapUtils.getBitmapFromDrawable(dVar.k.getContext().getResources().getDrawable(R.drawable.mappls_direction_location_on_black_24dp));
            style.addImage("com.mappls.sdk.directions.mappls_direction_start_marker", bitmapFromDrawable);
            style.addImage("com.mappls.sdk.directions.mappls_direction_end_marker", bitmapFromDrawable2);
            style.addImage("com.mappls.sdk.directions.mappls_direction_via_points_1", bitmapFromDrawable3);
            style.addImage("com.mappls.sdk.directions.mappls_direction_via_points_2", bitmapFromDrawable4);
            style.addImage("com.mappls.sdk.directions.mappls_direction_via_points_3", bitmapFromDrawable5);
            style.addImage("com.mappls.sdk.directions.mappls_direction_track_points", bitmapFromDrawable6);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void f(d dVar, ArrayList arrayList) {
        Feature feature;
        RouteLeg routeLeg;
        if (!dVar.u || dVar.t.annotation() == null || !dVar.t.annotation().contains(DirectionsCriteria.ANNOTATION_CONGESTION) || dVar.q == null || dVar.m == null) {
            return;
        }
        int i2 = 0;
        while (i2 < dVar.m.size()) {
            LineString lineString = (LineString) dVar.m.get(i2);
            DirectionsRoute directionsRoute = dVar.q.get(i2);
            boolean z = dVar.r == i2;
            ArrayList arrayList2 = new ArrayList();
            if (directionsRoute.legs() != null && (routeLeg = directionsRoute.legs().get(0)) != null && routeLeg.annotation() != null && routeLeg.annotation().congestion() != null) {
                List<String> congestion = routeLeg.annotation().congestion();
                for (int i3 = 0; i3 < congestion.size(); i3++) {
                    if (congestion.size() + 1 <= lineString.coordinates().size()) {
                        String str = congestion.get(i3);
                        ArrayList arrayList3 = new ArrayList();
                        arrayList3.add(lineString.coordinates().get(i3));
                        arrayList3.add(lineString.coordinates().get(i3 + 1));
                        Feature fromGeometry = Feature.fromGeometry(LineString.fromLngLats(arrayList3));
                        fromGeometry.addStringProperty("mappls_direction_direction_type", z ? "selected" : "alternate");
                        fromGeometry.addStringProperty(DirectionsCriteria.ANNOTATION_CONGESTION, str);
                        arrayList2.add(fromGeometry);
                    }
                }
            }
            arrayList.addAll(arrayList2);
            i2++;
        }
        dVar.k.post(new com.mappls.sdk.direction.ui.plugin.g(dVar, arrayList));
        if (dVar.t.annotation() != null && dVar.t.annotation().contains(DirectionsCriteria.ANNOTATION_BASE_DURATION) && dVar.t.annotation().contains("duration")) {
            List<CongestionDelayInfo> congestionDelayInfoFromRoute = MapplsUtils.getCongestionDelayInfoFromRoute(dVar.q.get(dVar.r), LineString.fromPolyline(dVar.q.get(dVar.r).geometry(), 6).coordinates().get(0), 50.0d, 1);
            HashMap hashMap = new HashMap();
            for (int i4 = 0; i4 < congestionDelayInfoFromRoute.size(); i4++) {
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(dVar.k.getContext()).inflate(R.layout.mappls_direction_segment_delay_layout, (ViewGroup) null);
                ((TextView) linearLayout.findViewById(R.id.mappls_direction_delay_text)).setText("+" + congestionDelayInfoFromRoute.get(i4).getDelayDuration() + " min");
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                linearLayout.measure(makeMeasureSpec, makeMeasureSpec);
                hashMap.put("com.mappls.sdk.directions.mappls_direction_segment-delay_marker" + i4, m.a(linearLayout));
            }
            dVar.k.post(new com.mappls.sdk.direction.ui.plugin.i(dVar, hashMap));
            int i5 = 0;
            for (CongestionDelayInfo congestionDelayInfo : congestionDelayInfoFromRoute) {
                Feature fromGeometry2 = Feature.fromGeometry(congestionDelayInfo.getPoint());
                fromGeometry2.addStringProperty(Constants.KEY_ICON, "com.mappls.sdk.directions.mappls_direction_segment-delay_marker" + i5);
                fromGeometry2.addStringProperty("mappls_direction_direction_type", "segment");
                fromGeometry2.addNumberProperty("com.mappls.sdk.directions.mappls_direction_segment-delay_marker-sort-key", Integer.valueOf(0 - congestionDelayInfo.getDelayDuration()));
                String str2 = "segment-delay-" + i5;
                try {
                    JSONObject jSONObject = new JSONObject(fromGeometry2.toJson());
                    jSONObject.put("id", str2);
                    feature = Feature.fromJson(jSONObject.toString());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    feature = null;
                }
                arrayList.add(feature);
                i5++;
                arrayList.add(fromGeometry2);
            }
            dVar.k.post(new com.mappls.sdk.direction.ui.plugin.h(dVar, arrayList));
        }
    }

    public static void g(d dVar, boolean z, Style style) {
        ArrayList arrayList;
        List<Layer> layers;
        if (dVar.j == null || (arrayList = dVar.l) == null || arrayList.size() <= 0 || (layers = style.getLayers()) == null || layers.size() <= 0) {
            return;
        }
        for (Layer layer : layers) {
            if (dVar.l.contains(layer.getId())) {
                PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
                propertyValueArr[0] = PropertyFactory.visibility(z ? Property.VISIBLE : "none");
                layer.setProperties(propertyValueArr);
            }
        }
        boolean z2 = dVar.i;
        dVar.j.getStyle(new com.mappls.sdk.direction.ui.plugin.k(z2));
        dVar.i = z2;
    }

    public static void t(d dVar) {
        dVar.h.removeCallbacks(dVar.v);
        dVar.h.postDelayed(dVar.v, 100L);
    }

    public final void a() {
        if (this.j.getStyle() == null || !this.j.getStyle().isFullyLoaded()) {
            return;
        }
        this.j.getStyle().removeLayer("com.mappls.sdk.directions.route_alternate_case");
        this.j.getStyle().removeLayer("com.mappls.sdk.directions.route_alternate");
        this.j.getStyle().removeLayer("com.mappls.sdk.directions.route_selected_case");
        this.j.getStyle().removeLayer("com.mappls.sdk.directions.route_selected");
        this.j.getStyle().removeLayer("com.mappls.sdk.directions.directions-marker-layer");
        this.j.getStyle().removeLayer("com.mappls.sdk.directions.directions-marker-advices-layer");
        this.j.getStyle().removeLayer("com.mappls.sdk.directions.directions-marker-bearing-layer");
        this.j.getStyle().removeLayer("com.mappls.sdk.directions.directions-congestion-segment-marker-layer");
        this.j.getStyle().removeSource("com.mappls.sdk.directions.DIRECTIONS_SOURCE_ID");
    }

    public final void a(k kVar) {
        this.s = kVar;
    }

    public final void b() {
        GeoJsonSource geoJsonSource;
        if (this.j.getStyle() == null || !this.j.getStyle().isFullyLoaded() || (geoJsonSource = (GeoJsonSource) this.j.getStyle().getSourceAs("com.mappls.sdk.directions.DIRECTIONS_SOURCE_ID")) == null) {
            return;
        }
        geoJsonSource.setGeoJson(FeatureCollection.fromFeatures(new ArrayList()));
    }

    public final void h(Style style) {
        Expression expression = C0618d.d;
        Expression b2 = b(this.t.selectedRouteColor().intValue(), this.t.alternateRouteColor().intValue());
        Expression expression2 = C0618d.f12610a;
        Expression expression3 = C0618d.c;
        LineLayer a2 = h.a("com.mappls.sdk.directions.route_alternate", expression, b2, expression2, expression3, null);
        LineLayer a3 = h.a("com.mappls.sdk.directions.route_alternate_case", C0618d.d, b(this.t.selectedCasingRouteColor().intValue(), this.t.alternateCasingRouteColor().intValue()), C0618d.b, expression3, null);
        ArrayList arrayList = this.l;
        String str = (String) arrayList.get(arrayList.size() - 1);
        if (style.getLayer(str) != null) {
            style.addLayerBelow(a3, str);
        } else {
            style.addLayer(a3);
        }
        style.addLayerAbove(a2, a3.getId());
        this.l.add(a3.getId());
        this.l.add(a2.getId());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void j(Style style) {
        SymbolLayer withProperties = new SymbolLayer("com.mappls.sdk.directions.directions-congestion-segment-marker-layer", "com.mappls.sdk.directions.DIRECTIONS_SOURCE_ID").withProperties(PropertyFactory.iconImage("{icon}"), PropertyFactory.iconAnchor("bottom-left"), PropertyFactory.symbolSortKey(Expression.get("com.mappls.sdk.directions.mappls_direction_segment-delay_marker-sort-key")), PropertyFactory.iconOffset(new Float[]{Float.valueOf(0.0f), Float.valueOf(2.0f)}));
        withProperties.withFilter(i.f12612a);
        withProperties.setSourceLayer("directions");
        style.addLayer(withProperties);
        this.l.add(withProperties.getId());
    }

    public final void l() {
        this.j.getStyle(new b());
    }

    public final void m(Style style) {
        Expression expression = l.e;
        Expression b2 = b(this.t.selectedRouteColor().intValue(), this.t.alternateRouteColor().intValue());
        Expression expression2 = l.f12614a;
        Expression expression3 = l.c;
        LineLayer a2 = h.a("com.mappls.sdk.directions.route_selected", expression, b2, expression2, expression3, null);
        LineLayer a3 = h.a("com.mappls.sdk.directions.route_selected_case", l.e, b(this.t.selectedCasingRouteColor().intValue(), this.t.alternateCasingRouteColor().intValue()), l.b, expression3, l.d);
        if (style.getLayer("traffic-local") != null) {
            style.addLayerAbove(a3, "traffic-local");
        } else {
            style.addLayer(a3);
        }
        style.addLayerAbove(a2, a3.getId());
        this.l.add(a3.getId());
        this.l.add(a2.getId());
    }

    public final void o(Style style) {
        SymbolLayer withProperties = new SymbolLayer("com.mappls.sdk.directions.directions-marker-layer", "com.mappls.sdk.directions.DIRECTIONS_SOURCE_ID").withProperties(PropertyFactory.iconImage("{icon}"), PropertyFactory.iconAnchor("bottom"));
        withProperties.withFilter(j.f12613a);
        withProperties.setSourceLayer("directions");
        style.addLayer(withProperties);
        this.l.add(withProperties.getId());
    }

    @Override // com.mappls.sdk.maps.MapView.OnDidFinishLoadingStyleListener
    public final void onDidFinishLoadingStyle() {
        l();
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnMapClickListener
    public final boolean onMapClick(@NonNull LatLng latLng) {
        if (this.q != null) {
            HashMap hashMap = new HashMap();
            for (DirectionsRoute directionsRoute : this.q) {
                if (directionsRoute.geometry() != null) {
                    hashMap.put(LineString.fromPolyline(directionsRoute.geometry(), 6), directionsRoute);
                }
            }
            MapplsMap mapplsMap = this.j;
            List<DirectionsRoute> list = this.q;
            HashMap hashMap2 = new HashMap();
            Point fromLngLat = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
            PointF screenLocation = mapplsMap.getProjection().toScreenLocation(latLng);
            for (LineString lineString : hashMap.keySet()) {
                Point point = (Point) TurfMisc.nearestPointOnLine(fromLngLat, lineString.coordinates()).geometry();
                if (point == null) {
                    break;
                }
                PointF screenLocation2 = mapplsMap.getProjection().toScreenLocation(new LatLng(point.latitude(), point.longitude()));
                double hypot = Math.hypot(screenLocation.x - screenLocation2.x, screenLocation.y - screenLocation2.y);
                Timber.d("Distance is equal 2  --->  " + hypot, new Object[0]);
                hashMap2.put(Double.valueOf(hypot), (DirectionsRoute) hashMap.get(lineString));
                mapplsMap = mapplsMap;
            }
            Executors.newSingleThreadExecutor().execute(new com.mappls.sdk.direction.ui.plugin.e(this, hashMap2, list));
            return false;
        }
        return false;
    }

    public final void a(int i2) {
        this.r = i2;
        this.j.getStyle(new c());
        this.h.removeCallbacks(this.v);
        this.h.postDelayed(this.v, 100L);
    }

    public final void a(LatLng latLng, LatLng latLng2, List<LatLng> list, List<DirectionsRoute> list2, int i2) {
        this.n = latLng;
        this.o = latLng2;
        this.p = list;
        this.m = new ArrayList();
        for (DirectionsRoute directionsRoute : list2) {
            if (directionsRoute.geometry() != null) {
                this.m.add(LineString.fromPolyline(directionsRoute.geometry(), 6));
            }
        }
        this.q = list2;
        this.r = i2;
        this.h.removeCallbacks(this.v);
        this.h.postDelayed(this.v, 100L);
    }
}
