package com.mappls.sdk.direction.ui.plugin;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.style.layers.SymbolLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import java.util.ArrayList;
import java.util.List;
import timber.log.Timber;
/* loaded from: classes11.dex */
public final class a implements MapView.OnDidFinishLoadingStyleListener {
    public LatLng k;
    public MapplsMap l;
    public MapView m;
    public ArrayList n;
    public Handler h = new Handler();
    public boolean i = false;
    public float j = 0.0f;
    public Runnable o = new Runnable() { // from class: com.mappls.sdk.direction.ui.plugin.b
        @Override // java.lang.Runnable
        public final void run() {
            a.this.g();
        }
    };

    /* renamed from: com.mappls.sdk.direction.ui.plugin.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0617a implements Style.OnStyleLoaded {
        public C0617a() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            style.removeLayer("directions-marker-bearing-layer");
            style.removeSource("directions_bearing");
            a.d(a.this, style);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Style.OnStyleLoaded {
        public b() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            a.this.a(style);
            style.removeLayer("directions-marker-bearing-layer");
            style.removeSource("directions_bearing");
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Style.OnStyleLoaded {
        public c() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            a.e(a.this, true, style);
            ArrayList arrayList = new ArrayList();
            if (a.this.k != null) {
                Feature fromGeometry = Feature.fromGeometry(Point.fromLngLat(a.this.k.getLongitude(), a.this.k.getLatitude()));
                fromGeometry.addStringProperty(Constants.KEY_ICON, "directions-marker-bearing-image");
                fromGeometry.addStringProperty("direction_type", "bearing");
                arrayList.add(fromGeometry);
            }
            Layer layer = style.getLayer("directions-marker-bearing-layer");
            if (layer != null) {
                layer.setProperties(PropertyFactory.iconRotate(Float.valueOf(a.this.j)));
            }
            FeatureCollection fromFeatures = FeatureCollection.fromFeatures(arrayList);
            GeoJsonSource geoJsonSource = (GeoJsonSource) style.getSource("directions_bearing");
            if (geoJsonSource != null) {
                geoJsonSource.setGeoJson(fromFeatures);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f12606a;

        public d(boolean z) {
            this.f12606a = z;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            Layer layer = style.getLayer("directions-marker-bearing-layer");
            if (layer != null) {
                PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
                propertyValueArr[0] = PropertyFactory.visibility(this.f12606a ? Property.VISIBLE : "none");
                layer.setProperties(propertyValueArr);
            }
        }
    }

    public a(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap) {
        this.l = mapplsMap;
        this.m = mapView;
        mapplsMap.getStyle(new C0617a());
        mapView.addOnDidFinishLoadingStyleListener(this);
    }

    public static void d(a aVar, Style style) {
        aVar.getClass();
        aVar.n = new ArrayList();
        style.addSource(new GeoJsonSource("directions_bearing"));
        try {
            aVar.c(ContextCompat.getDrawable(aVar.m.getContext(), R.drawable.mappls_direction_user_puck_icon_demo), style);
        } catch (Exception unused) {
            Timber.e("Unable to attach Traffic Layers to current style.", new Object[0]);
        }
    }

    public static void e(a aVar, boolean z, Style style) {
        if (aVar.n == null) {
            return;
        }
        List<Layer> layers = style.getLayers();
        if (layers != null && layers.size() > 0) {
            for (Layer layer : layers) {
                if (aVar.n.contains(layer.getId())) {
                    PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
                    propertyValueArr[0] = PropertyFactory.visibility(z ? Property.VISIBLE : "none");
                    layer.setProperties(propertyValueArr);
                }
            }
        }
        aVar.a(aVar.i);
    }

    public final void a() {
        this.l.getStyle(new b());
    }

    public final void a(float f, LatLng latLng) {
        this.j = f;
        this.k = latLng;
        this.h.removeCallbacksAndMessages(null);
        this.h.postDelayed(this.o, 100L);
    }

    public final void a(Style style) {
        GeoJsonSource geoJsonSource;
        if (this.l != null && (geoJsonSource = (GeoJsonSource) style.getSource("directions_bearing")) != null) {
            geoJsonSource.setGeoJson(FeatureCollection.fromFeatures(new ArrayList()));
        }
        this.i = false;
        this.j = 0.0f;
    }

    public final void a(boolean z) {
        this.l.getStyle(new d(z));
        this.i = z;
    }

    public final void c(Drawable drawable, Style style) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            bitmap = createBitmap;
        }
        style.addImage("directions-marker-bearing-image", bitmap);
        SymbolLayer symbolLayer = new SymbolLayer("directions-marker-bearing-layer", "directions_bearing");
        Boolean bool = Boolean.TRUE;
        SymbolLayer withFilter = symbolLayer.withProperties(PropertyFactory.iconImage("directions-marker-bearing-image"), PropertyFactory.iconAllowOverlap(bool), PropertyFactory.iconIgnorePlacement(bool), PropertyFactory.iconRotationAlignment("map")).withFilter(Expression.match(Expression.get("direction_type"), Expression.literal(false), Expression.stop("bearing", bool)));
        style.addLayer(withFilter);
        this.n.add(withFilter.getId());
    }

    public final synchronized void g() {
        this.l.getStyle(new c());
    }

    @Override // com.mappls.sdk.maps.MapView.OnDidFinishLoadingStyleListener
    public final void onDidFinishLoadingStyle() {
        this.l.getStyle(new com.mappls.sdk.direction.ui.plugin.c(this));
    }
}
