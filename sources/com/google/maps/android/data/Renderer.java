package com.google.maps.android.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.maps.android.R;
import com.google.maps.android.collections.GroundOverlayManager;
import com.google.maps.android.collections.MarkerManager;
import com.google.maps.android.collections.PolygonManager;
import com.google.maps.android.collections.PolylineManager;
import com.google.maps.android.data.Layer;
import com.google.maps.android.data.geojson.BiMultiMap;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonGeometryCollection;
import com.google.maps.android.data.geojson.GeoJsonLineString;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonMultiLineString;
import com.google.maps.android.data.geojson.GeoJsonMultiPoint;
import com.google.maps.android.data.geojson.GeoJsonMultiPolygon;
import com.google.maps.android.data.geojson.GeoJsonPoint;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygon;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlMultiGeometry;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlPolygon;
import com.google.maps.android.data.kml.KmlStyle;
import com.google.maps.android.data.kml.KmlUtil;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public class Renderer {
    public static final Object B = null;
    public static final DecimalFormat C = new DecimalFormat("#.####");
    public final GroundOverlayManager.Collection A;
    public GoogleMap h;
    public final BiMultiMap<Feature> i;
    public HashMap<String, KmlStyle> j;
    public HashMap<String, KmlStyle> k;
    public HashMap<String, String> l;
    public final BiMultiMap<Feature> m;
    public HashMap<KmlGroundOverlay, GroundOverlay> n;
    public final Set<String> o;
    public ImagesCache p;
    public int q;
    public boolean r;
    public Context s;
    public ArrayList<KmlContainer> t;
    public final GeoJsonPointStyle u;
    public final GeoJsonLineStringStyle v;
    public final GeoJsonPolygonStyle w;
    public final MarkerManager.Collection x;
    public final PolygonManager.Collection y;
    public final PolylineManager.Collection z;

    /* loaded from: classes10.dex */
    public static final class ImagesCache {

        /* renamed from: a  reason: collision with root package name */
        public final Map<String, Map<String, BitmapDescriptor>> f11549a = new HashMap();
        public final Map<String, BitmapDescriptor> b = new HashMap();
        public final Map<String, Bitmap> c = new HashMap();
    }

    /* loaded from: classes10.dex */
    public class a implements GoogleMap.InfoWindowAdapter {
        public a() {
        }

        @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
        public View getInfoContents(Marker marker) {
            View inflate = LayoutInflater.from(Renderer.this.s).inflate(R.layout.amu_info_window, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.window);
            if (marker.getSnippet() != null) {
                textView.setText(Html.fromHtml(marker.getTitle() + "<br>" + marker.getSnippet()));
            } else {
                textView.setText(Html.fromHtml(marker.getTitle()));
            }
            return inflate;
        }

        @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
        public View getInfoWindow(Marker marker) {
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public class b implements GoogleMap.OnPolygonClickListener {
        public final /* synthetic */ Layer.OnFeatureClickListener h;

        public b(Layer.OnFeatureClickListener onFeatureClickListener) {
            this.h = onFeatureClickListener;
        }

        @Override // com.google.android.gms.maps.GoogleMap.OnPolygonClickListener
        public void onPolygonClick(Polygon polygon) {
            if (Renderer.this.q(polygon) != null) {
                this.h.onFeatureClick(Renderer.this.q(polygon));
            } else if (Renderer.this.m(polygon) != null) {
                this.h.onFeatureClick(Renderer.this.m(polygon));
            } else {
                Layer.OnFeatureClickListener onFeatureClickListener = this.h;
                Renderer renderer = Renderer.this;
                onFeatureClickListener.onFeatureClick(renderer.q(renderer.r(polygon)));
            }
        }
    }

    /* loaded from: classes10.dex */
    public class c implements GoogleMap.OnMarkerClickListener {
        public final /* synthetic */ Layer.OnFeatureClickListener h;

        public c(Layer.OnFeatureClickListener onFeatureClickListener) {
            this.h = onFeatureClickListener;
        }

        @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
        public boolean onMarkerClick(Marker marker) {
            if (Renderer.this.q(marker) != null) {
                this.h.onFeatureClick(Renderer.this.q(marker));
                return false;
            } else if (Renderer.this.m(marker) != null) {
                this.h.onFeatureClick(Renderer.this.m(marker));
                return false;
            } else {
                Layer.OnFeatureClickListener onFeatureClickListener = this.h;
                Renderer renderer = Renderer.this;
                onFeatureClickListener.onFeatureClick(renderer.q(renderer.r(marker)));
                return false;
            }
        }
    }

    /* loaded from: classes10.dex */
    public class d implements GoogleMap.OnPolylineClickListener {
        public final /* synthetic */ Layer.OnFeatureClickListener h;

        public d(Layer.OnFeatureClickListener onFeatureClickListener) {
            this.h = onFeatureClickListener;
        }

        @Override // com.google.android.gms.maps.GoogleMap.OnPolylineClickListener
        public void onPolylineClick(Polyline polyline) {
            if (Renderer.this.q(polyline) != null) {
                this.h.onFeatureClick(Renderer.this.q(polyline));
            } else if (Renderer.this.m(polyline) != null) {
                this.h.onFeatureClick(Renderer.this.m(polyline));
            } else {
                Layer.OnFeatureClickListener onFeatureClickListener = this.h;
                Renderer renderer = Renderer.this;
                onFeatureClickListener.onFeatureClick(renderer.q(renderer.r(polyline)));
            }
        }
    }

    public Renderer(GoogleMap googleMap, Context context, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager, @Nullable ImagesCache imagesCache) {
        this(googleMap, new HashSet(), null, null, null, new BiMultiMap(), markerManager, polygonManager, polylineManager, groundOverlayManager);
        this.s = context;
        this.k = new HashMap<>();
        this.p = imagesCache == null ? new ImagesCache() : imagesCache;
    }

    public static boolean getPlacemarkVisibility(Feature feature) {
        return (feature.hasProperty("visibility") && Integer.parseInt(feature.getProperty("visibility")) == 0) ? false : true;
    }

    public void A(Layer.OnFeatureClickListener onFeatureClickListener) {
        this.y.setOnPolygonClickListener(new b(onFeatureClickListener));
        this.x.setOnMarkerClickListener(new c(onFeatureClickListener));
        this.z.setOnPolylineClickListener(new d(onFeatureClickListener));
    }

    public void addFeature(Feature feature) {
        Object obj = B;
        if (feature instanceof GeoJsonFeature) {
            v((GeoJsonFeature) feature);
        }
        if (this.r) {
            if (this.i.containsKey(feature)) {
                removeFromMap(this.i.get(feature));
            }
            if (feature.hasGeometry()) {
                if (feature instanceof KmlPlacemark) {
                    KmlPlacemark kmlPlacemark = (KmlPlacemark) feature;
                    obj = addKmlPlacemarkToMap(kmlPlacemark, feature.getGeometry(), getPlacemarkStyle(feature.getId()), kmlPlacemark.getInlineStyle(), getPlacemarkVisibility(feature));
                } else {
                    obj = addGeoJsonFeatureToMap(feature, feature.getGeometry());
                }
            }
        }
        this.i.put((BiMultiMap<Feature>) feature, obj);
    }

    public Object addGeoJsonFeatureToMap(Feature feature, Geometry geometry) {
        String geometryType = geometry.getGeometryType();
        geometryType.hashCode();
        char c2 = 65535;
        switch (geometryType.hashCode()) {
            case -2116761119:
                if (geometryType.equals("MultiPolygon")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1065891849:
                if (geometryType.equals("MultiPoint")) {
                    c2 = 1;
                    break;
                }
                break;
            case -627102946:
                if (geometryType.equals("MultiLineString")) {
                    c2 = 2;
                    break;
                }
                break;
            case 77292912:
                if (geometryType.equals("Point")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1267133722:
                if (geometryType.equals(KmlPolygon.GEOMETRY_TYPE)) {
                    c2 = 4;
                    break;
                }
                break;
            case 1806700869:
                if (geometryType.equals("LineString")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1950410960:
                if (geometryType.equals("GeometryCollection")) {
                    c2 = 6;
                    break;
                }
                break;
        }
        MarkerOptions markerOptions = null;
        PolylineOptions polylineOptions = null;
        PolygonOptions polygonOptions = null;
        switch (c2) {
            case 0:
                return i(((GeoJsonFeature) feature).getPolygonStyle(), (GeoJsonMultiPolygon) geometry);
            case 1:
                return h(((GeoJsonFeature) feature).getPointStyle(), (GeoJsonMultiPoint) geometry);
            case 2:
                return g(((GeoJsonFeature) feature).getLineStringStyle(), (GeoJsonMultiLineString) geometry);
            case 3:
                if (feature instanceof GeoJsonFeature) {
                    markerOptions = ((GeoJsonFeature) feature).getMarkerOptions();
                } else if (feature instanceof KmlPlacemark) {
                    markerOptions = ((KmlPlacemark) feature).getMarkerOptions();
                }
                return j(markerOptions, (GeoJsonPoint) geometry);
            case 4:
                if (feature instanceof GeoJsonFeature) {
                    polygonOptions = ((GeoJsonFeature) feature).getPolygonOptions();
                } else if (feature instanceof KmlPlacemark) {
                    polygonOptions = ((KmlPlacemark) feature).getPolygonOptions();
                }
                return k(polygonOptions, (DataPolygon) geometry);
            case 5:
                if (feature instanceof GeoJsonFeature) {
                    polylineOptions = ((GeoJsonFeature) feature).getPolylineOptions();
                } else if (feature instanceof KmlPlacemark) {
                    polylineOptions = ((KmlPlacemark) feature).getPolylineOptions();
                }
                return d(polylineOptions, (GeoJsonLineString) geometry);
            case 6:
                return c((GeoJsonFeature) feature, ((GeoJsonGeometryCollection) geometry).getGeometries());
            default:
                return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0053, code lost:
        if (r0.equals("Point") == false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object addKmlPlacemarkToMap(com.google.maps.android.data.kml.KmlPlacemark r13, com.google.maps.android.data.Geometry r14, com.google.maps.android.data.kml.KmlStyle r15, com.google.maps.android.data.kml.KmlStyle r16, boolean r17) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.Renderer.addKmlPlacemarkToMap(com.google.maps.android.data.kml.KmlPlacemark, com.google.maps.android.data.Geometry, com.google.maps.android.data.kml.KmlStyle, com.google.maps.android.data.kml.KmlStyle, boolean):java.lang.Object");
    }

    public void assignStyleMap(HashMap<String, String> hashMap, HashMap<String, KmlStyle> hashMap2) {
        for (String str : hashMap.keySet()) {
            String str2 = hashMap.get(str);
            if (hashMap2.containsKey(str2)) {
                hashMap2.put(str, hashMap2.get(str2));
            }
        }
    }

    public GroundOverlay attachGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        return this.A.addGroundOverlay(groundOverlayOptions);
    }

    public final ArrayList<Object> c(GeoJsonFeature geoJsonFeature, List<Geometry> list) {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (Geometry geometry : list) {
            arrayList.add(addGeoJsonFeatureToMap(geoJsonFeature, geometry));
        }
        return arrayList;
    }

    public void cacheBitmap(String str, Bitmap bitmap) {
        this.p.c.put(str, bitmap);
    }

    public void checkClearBitmapCache() {
        ImagesCache imagesCache;
        if (this.q != 0 || (imagesCache = this.p) == null || imagesCache.c.isEmpty()) {
            return;
        }
        this.p.c.clear();
    }

    public void clearStylesRenderer() {
        this.k.clear();
    }

    public final Polyline d(PolylineOptions polylineOptions, LineString lineString) {
        polylineOptions.addAll(lineString.getGeometryObject());
        Polyline addPolyline = this.z.addPolyline(polylineOptions);
        addPolyline.setClickable(polylineOptions.isClickable());
        return addPolyline;
    }

    public void downloadFinished() {
        this.q--;
        checkClearBitmapCache();
    }

    public void downloadStarted() {
        this.q++;
    }

    public final void e(String str, double d2, MarkerOptions markerOptions) {
        BitmapDescriptor cachedMarkerImage = getCachedMarkerImage(str, d2);
        if (cachedMarkerImage != null) {
            markerOptions.icon(cachedMarkerImage);
        } else {
            this.o.add(str);
        }
    }

    public final ArrayList<Object> f(KmlPlacemark kmlPlacemark, KmlMultiGeometry kmlMultiGeometry, KmlStyle kmlStyle, KmlStyle kmlStyle2, boolean z) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator<Geometry> it = kmlMultiGeometry.getGeometryObject().iterator();
        while (it.hasNext()) {
            arrayList.add(addKmlPlacemarkToMap(kmlPlacemark, it.next(), kmlStyle, kmlStyle2, z));
        }
        return arrayList;
    }

    public final ArrayList<Polyline> g(GeoJsonLineStringStyle geoJsonLineStringStyle, GeoJsonMultiLineString geoJsonMultiLineString) {
        ArrayList<Polyline> arrayList = new ArrayList<>();
        for (GeoJsonLineString geoJsonLineString : geoJsonMultiLineString.getLineStrings()) {
            arrayList.add(d(geoJsonLineStringStyle.toPolylineOptions(), geoJsonLineString));
        }
        return arrayList;
    }

    public HashMap<? extends Feature, Object> getAllFeatures() {
        return this.i;
    }

    public BitmapDescriptor getCachedGroundOverlayImage(String str) {
        Bitmap bitmap;
        BitmapDescriptor bitmapDescriptor = this.p.b.get(str);
        if (bitmapDescriptor != null || (bitmap = this.p.c.get(str)) == null) {
            return bitmapDescriptor;
        }
        BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap(bitmap);
        this.p.b.put(str, fromBitmap);
        return fromBitmap;
    }

    public BitmapDescriptor getCachedMarkerImage(String str, double d2) {
        Bitmap bitmap;
        String format = C.format(d2);
        Map<String, BitmapDescriptor> map = this.p.f11549a.get(str);
        BitmapDescriptor bitmapDescriptor = map != null ? map.get(format) : null;
        if (bitmapDescriptor != null || (bitmap = this.p.c.get(str)) == null) {
            return bitmapDescriptor;
        }
        BitmapDescriptor u = u(bitmap, d2);
        s(str, format, u);
        return u;
    }

    public ArrayList<KmlContainer> getContainerList() {
        return this.t;
    }

    public Set<Feature> getFeatures() {
        return this.i.keySet();
    }

    public HashMap<KmlGroundOverlay, GroundOverlay> getGroundOverlayMap() {
        return this.n;
    }

    public GoogleMap getMap() {
        return this.h;
    }

    public Set<String> getMarkerIconUrls() {
        return this.o;
    }

    public KmlStyle getPlacemarkStyle(String str) {
        return this.k.get(str) != null ? this.k.get(str) : this.k.get(null);
    }

    public HashMap<String, String> getStyleMaps() {
        return this.l;
    }

    public HashMap<String, KmlStyle> getStylesRenderer() {
        return this.k;
    }

    public Collection<Object> getValues() {
        return this.i.values();
    }

    public final ArrayList<Marker> h(GeoJsonPointStyle geoJsonPointStyle, GeoJsonMultiPoint geoJsonMultiPoint) {
        ArrayList<Marker> arrayList = new ArrayList<>();
        for (GeoJsonPoint geoJsonPoint : geoJsonMultiPoint.getPoints()) {
            arrayList.add(j(geoJsonPointStyle.toMarkerOptions(), geoJsonPoint));
        }
        return arrayList;
    }

    public boolean hasFeatures() {
        return this.i.size() > 0;
    }

    public final ArrayList<Polygon> i(GeoJsonPolygonStyle geoJsonPolygonStyle, GeoJsonMultiPolygon geoJsonMultiPolygon) {
        ArrayList<Polygon> arrayList = new ArrayList<>();
        for (GeoJsonPolygon geoJsonPolygon : geoJsonMultiPolygon.getPolygons()) {
            arrayList.add(k(geoJsonPolygonStyle.toPolygonOptions(), geoJsonPolygon));
        }
        return arrayList;
    }

    public boolean isLayerOnMap() {
        return this.r;
    }

    public final Marker j(MarkerOptions markerOptions, Point point) {
        markerOptions.position(point.getGeometryObject());
        return this.x.addMarker(markerOptions);
    }

    public final Polygon k(PolygonOptions polygonOptions, DataPolygon dataPolygon) {
        polygonOptions.addAll(dataPolygon.getOuterBoundaryCoordinates());
        for (List<LatLng> list : dataPolygon.getInnerBoundaryCoordinates()) {
            polygonOptions.addHole(list);
        }
        Polygon addPolygon = this.y.addPolygon(polygonOptions);
        addPolygon.setClickable(polygonOptions.isClickable());
        return addPolygon;
    }

    public final void l() {
        this.x.setInfoWindowAdapter(new a());
    }

    public Feature m(Object obj) {
        BiMultiMap<Feature> biMultiMap = this.m;
        if (biMultiMap != null) {
            return biMultiMap.getKey(obj);
        }
        return null;
    }

    public GeoJsonLineStringStyle n() {
        return this.v;
    }

    public GeoJsonPointStyle o() {
        return this.u;
    }

    public GeoJsonPolygonStyle p() {
        return this.w;
    }

    public void putContainerFeature(Object obj, Feature feature) {
        this.m.put((BiMultiMap<Feature>) feature, obj);
    }

    public void putFeatures(Feature feature, Object obj) {
        this.i.put((BiMultiMap<Feature>) feature, obj);
    }

    public void putStyles() {
        this.k.putAll(this.j);
    }

    public Feature q(Object obj) {
        return this.i.getKey(obj);
    }

    public final ArrayList<?> r(Object obj) {
        for (Object obj2 : getValues()) {
            if (obj2.getClass().getSimpleName().equals("ArrayList")) {
                ArrayList<?> arrayList = (ArrayList) obj2;
                if (arrayList.contains(obj)) {
                    return arrayList;
                }
            }
        }
        return null;
    }

    public void removeFeature(Feature feature) {
        if (this.i.containsKey(feature)) {
            removeFromMap(this.i.remove(feature));
        }
    }

    public void removeFeatures(HashMap<? extends Feature, Object> hashMap) {
        t(hashMap.values());
    }

    public void removeFromMap(Object obj) {
        if (obj instanceof Marker) {
            this.x.remove((Marker) obj);
        } else if (obj instanceof Polyline) {
            this.z.remove((Polyline) obj);
        } else if (obj instanceof Polygon) {
            this.y.remove((Polygon) obj);
        } else if (obj instanceof GroundOverlay) {
            this.A.remove((GroundOverlay) obj);
        } else if (obj instanceof ArrayList) {
            Iterator it = ((ArrayList) obj).iterator();
            while (it.hasNext()) {
                removeFromMap(it.next());
            }
        }
    }

    public void removeGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> hashMap) {
        for (GroundOverlay groundOverlay : hashMap.values()) {
            if (groundOverlay != null) {
                this.A.remove(groundOverlay);
            }
        }
    }

    public final void s(String str, String str2, BitmapDescriptor bitmapDescriptor) {
        Map<String, BitmapDescriptor> map = this.p.f11549a.get(str);
        if (map == null) {
            map = new HashMap<>();
            this.p.f11549a.put(str, map);
        }
        map.put(str2, bitmapDescriptor);
    }

    public void setLayerVisibility(boolean z) {
        this.r = z;
    }

    public void setMap(GoogleMap googleMap) {
        this.h = googleMap;
    }

    public void storeData(HashMap<String, KmlStyle> hashMap, HashMap<String, String> hashMap2, HashMap<KmlPlacemark, Object> hashMap3, ArrayList<KmlContainer> arrayList, HashMap<KmlGroundOverlay, GroundOverlay> hashMap4) {
        this.j = hashMap;
        this.l = hashMap2;
        this.i.putAll(hashMap3);
        this.t = arrayList;
        this.n = hashMap4;
    }

    public final void t(Collection collection) {
        for (Object obj : collection) {
            if (obj instanceof Collection) {
                t((Collection) obj);
            } else if (obj instanceof Marker) {
                this.x.remove((Marker) obj);
            } else if (obj instanceof Polyline) {
                this.z.remove((Polyline) obj);
            } else if (obj instanceof Polygon) {
                this.y.remove((Polygon) obj);
            }
        }
    }

    public final BitmapDescriptor u(Bitmap bitmap, double d2) {
        int i;
        int i2 = (int) (this.s.getResources().getDisplayMetrics().density * 32.0f * d2);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width < height) {
            i = (int) ((height * i2) / width);
        } else if (width > height) {
            int i3 = (int) ((width * i2) / height);
            i = i2;
            i2 = i3;
        } else {
            i = i2;
        }
        return BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(bitmap, i2, i, false));
    }

    public final void v(GeoJsonFeature geoJsonFeature) {
        if (geoJsonFeature.getPointStyle() == null) {
            geoJsonFeature.setPointStyle(this.u);
        }
        if (geoJsonFeature.getLineStringStyle() == null) {
            geoJsonFeature.setLineStringStyle(this.v);
        }
        if (geoJsonFeature.getPolygonStyle() == null) {
            geoJsonFeature.setPolygonStyle(this.w);
        }
    }

    public final void w(PolylineOptions polylineOptions, KmlStyle kmlStyle) {
        PolylineOptions polylineOptions2 = kmlStyle.getPolylineOptions();
        if (kmlStyle.isStyleSet("outlineColor")) {
            polylineOptions.color(polylineOptions2.getColor());
        }
        if (kmlStyle.isStyleSet(Property.ICON_TEXT_FIT_WIDTH)) {
            polylineOptions.width(polylineOptions2.getWidth());
        }
        if (kmlStyle.isLineRandomColorMode()) {
            polylineOptions.color(KmlStyle.computeRandomColor(polylineOptions2.getColor()));
        }
    }

    public final void x(MarkerOptions markerOptions, KmlStyle kmlStyle, KmlStyle kmlStyle2) {
        double iconScale;
        MarkerOptions markerOptions2 = kmlStyle.getMarkerOptions();
        if (kmlStyle.isStyleSet("heading")) {
            markerOptions.rotation(markerOptions2.getRotation());
        }
        if (kmlStyle.isStyleSet("hotSpot")) {
            markerOptions.anchor(markerOptions2.getAnchorU(), markerOptions2.getAnchorV());
        }
        if (kmlStyle.isStyleSet("markerColor")) {
            markerOptions.icon(markerOptions2.getIcon());
        }
        if (kmlStyle.isStyleSet("iconScale")) {
            iconScale = kmlStyle.getIconScale();
        } else {
            iconScale = kmlStyle2.isStyleSet("iconScale") ? kmlStyle2.getIconScale() : 1.0d;
        }
        if (kmlStyle.isStyleSet("iconUrl")) {
            e(kmlStyle.getIconUrl(), iconScale, markerOptions);
        } else if (kmlStyle2.getIconUrl() != null) {
            e(kmlStyle2.getIconUrl(), iconScale, markerOptions);
        }
    }

    public final void y(PolygonOptions polygonOptions, KmlStyle kmlStyle) {
        PolygonOptions polygonOptions2 = kmlStyle.getPolygonOptions();
        if (kmlStyle.hasFill() && kmlStyle.isStyleSet("fillColor")) {
            polygonOptions.fillColor(polygonOptions2.getFillColor());
        }
        if (kmlStyle.hasOutline()) {
            if (kmlStyle.isStyleSet("outlineColor")) {
                polygonOptions.strokeColor(polygonOptions2.getStrokeColor());
            }
            if (kmlStyle.isStyleSet(Property.ICON_TEXT_FIT_WIDTH)) {
                polygonOptions.strokeWidth(polygonOptions2.getStrokeWidth());
            }
        }
        if (kmlStyle.isPolyRandomColorMode()) {
            polygonOptions.fillColor(KmlStyle.computeRandomColor(polygonOptions2.getFillColor()));
        }
    }

    public final void z(KmlStyle kmlStyle, Marker marker, KmlPlacemark kmlPlacemark) {
        boolean hasProperty = kmlPlacemark.hasProperty(AppMeasurementSdk.ConditionalUserProperty.NAME);
        boolean hasProperty2 = kmlPlacemark.hasProperty(SavingTrackHelper.POINT_COL_DESCRIPTION);
        boolean hasBalloonStyle = kmlStyle.hasBalloonStyle();
        boolean containsKey = kmlStyle.getBalloonOptions().containsKey("text");
        if (hasBalloonStyle && containsKey) {
            marker.setTitle(KmlUtil.substituteProperties(kmlStyle.getBalloonOptions().get("text"), kmlPlacemark));
            l();
        } else if (hasBalloonStyle && hasProperty) {
            marker.setTitle(kmlPlacemark.getProperty(AppMeasurementSdk.ConditionalUserProperty.NAME));
            l();
        } else if (hasProperty && hasProperty2) {
            marker.setTitle(kmlPlacemark.getProperty(AppMeasurementSdk.ConditionalUserProperty.NAME));
            marker.setSnippet(kmlPlacemark.getProperty(SavingTrackHelper.POINT_COL_DESCRIPTION));
            l();
        } else if (hasProperty2) {
            marker.setTitle(kmlPlacemark.getProperty(SavingTrackHelper.POINT_COL_DESCRIPTION));
            l();
        } else if (hasProperty) {
            marker.setTitle(kmlPlacemark.getProperty(AppMeasurementSdk.ConditionalUserProperty.NAME));
            l();
        }
    }

    public void putStyles(HashMap<String, KmlStyle> hashMap) {
        this.k.putAll(hashMap);
    }

    public Renderer(GoogleMap googleMap, HashMap<? extends Feature, Object> hashMap, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager) {
        this(googleMap, null, new GeoJsonPointStyle(), new GeoJsonLineStringStyle(), new GeoJsonPolygonStyle(), null, markerManager, polygonManager, polylineManager, groundOverlayManager);
        this.i.putAll(hashMap);
        this.p = null;
    }

    public Renderer(GoogleMap googleMap, Set<String> set, GeoJsonPointStyle geoJsonPointStyle, GeoJsonLineStringStyle geoJsonLineStringStyle, GeoJsonPolygonStyle geoJsonPolygonStyle, BiMultiMap<Feature> biMultiMap, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager) {
        this.i = new BiMultiMap<>();
        this.q = 0;
        this.h = googleMap;
        this.r = false;
        this.o = set;
        this.u = geoJsonPointStyle;
        this.v = geoJsonLineStringStyle;
        this.w = geoJsonPolygonStyle;
        this.m = biMultiMap;
        if (googleMap != null) {
            this.x = (markerManager == null ? new MarkerManager(googleMap) : markerManager).newCollection();
            this.y = (polygonManager == null ? new PolygonManager(googleMap) : polygonManager).newCollection();
            this.z = (polylineManager == null ? new PolylineManager(googleMap) : polylineManager).newCollection();
            this.A = (groundOverlayManager == null ? new GroundOverlayManager(googleMap) : groundOverlayManager).newCollection();
            return;
        }
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
    }
}
