package com.mappls.sdk.plugin.annotation;

import android.graphics.PointF;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LongSparseArray;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.BaseMapplsHelper;
import com.mappls.sdk.maps.CoordinateCallback;
import com.mappls.sdk.maps.CoordinateResult;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.log.Logger;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import com.mappls.sdk.plugin.annotation.Annotation;
import com.mappls.sdk.plugin.annotation.OnAnnotationClickListener;
import com.mappls.sdk.plugin.annotation.OnAnnotationDragListener;
import com.mappls.sdk.plugin.annotation.OnAnnotationLongClickListener;
import com.mappls.sdk.plugin.annotation.g;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public abstract class AnnotationManager<L extends Layer, T extends Annotation, S extends g<T>, D extends OnAnnotationDragListener<T>, U extends OnAnnotationClickListener<T>, V extends OnAnnotationLongClickListener<T>> {
    private static final String TAG = "AnnotationManager";
    private String belowLayerId;
    private com.mappls.sdk.plugin.annotation.c<L> coreElementProvider;
    private long currentId;
    private d draggableAnnotationController;
    private GeoJsonOptions geoJsonOptions;
    private GeoJsonSource geoJsonSource;
    public L layer;
    public Expression layerFilter;
    private final AnnotationManager<L, T, S, D, U, V>.c mapClickResolver;
    public MapView mapView;
    public final MapplsMap mapplsMap;
    public Style style;
    public final LongSparseArray<T> annotations = new LongSparseArray<>();
    public final Map<String, Boolean> dataDrivenPropertyUsageMap = new HashMap();
    public final Map<String, PropertyValue> constantPropertyUsageMap = new HashMap();
    private final List<D> dragListeners = new ArrayList();
    private final List<U> clickListeners = new ArrayList();
    private final List<V> longClickListeners = new ArrayList();

    /* loaded from: classes11.dex */
    public class a implements MapView.OnDidFinishLoadingStyleListener {
        public final /* synthetic */ MapplsMap h;
        public final /* synthetic */ GeoJsonOptions i;

        /* renamed from: com.mappls.sdk.plugin.annotation.AnnotationManager$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0655a implements Style.OnStyleLoaded {
            public C0655a() {
            }

            @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
            public void onStyleLoaded(@NonNull Style style) {
                AnnotationManager.this.style = style;
                for (int i = 0; i < AnnotationManager.this.annotations.size(); i++) {
                    T valueAt = AnnotationManager.this.annotations.valueAt(i);
                    if ((valueAt instanceof Symbol) && style.isFullyLoaded()) {
                        Symbol symbol = (Symbol) valueAt;
                        style.addImage(symbol.getIconImage(), symbol.icon);
                    }
                }
                a aVar = a.this;
                AnnotationManager.this.initializeSourcesAndLayers(aVar.i);
            }
        }

        public a(MapplsMap mapplsMap, GeoJsonOptions geoJsonOptions) {
            this.h = mapplsMap;
            this.i = geoJsonOptions;
        }

        @Override // com.mappls.sdk.maps.MapView.OnDidFinishLoadingStyleListener
        public void onDidFinishLoadingStyle() {
            this.h.getStyle(new C0655a());
        }
    }

    /* loaded from: classes11.dex */
    public class b implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ List f13082a;
        public final /* synthetic */ List b;

        public b(List list, List list2) {
            this.f13082a = list;
            this.b = list2;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void coordinateResultSuccess(List<CoordinateResult> list) {
            if (list.size() > 0) {
                for (CoordinateResult coordinateResult : list) {
                    for (Symbol symbol : this.f13082a) {
                        if (symbol.mapplsPin.equalsIgnoreCase(coordinateResult.getMapplsPin())) {
                            this.b.add(Feature.fromGeometry(Point.fromLngLat(coordinateResult.getLongitude().doubleValue(), coordinateResult.getLatitude().doubleValue()), symbol.getFeature()));
                            symbol.setUsedDataDrivenProperties();
                        }
                    }
                }
            }
            AnnotationManager.this.geoJsonSource.setGeoJson(FeatureCollection.fromFeatures(this.b));
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void onFailure() {
            AnnotationManager.this.geoJsonSource.setGeoJson(FeatureCollection.fromFeatures(this.b));
        }
    }

    /* loaded from: classes11.dex */
    public class c implements MapplsMap.OnMapClickListener, MapplsMap.OnMapLongClickListener {
        public c() {
        }

        public /* synthetic */ c(AnnotationManager annotationManager, a aVar) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.mappls.sdk.maps.MapplsMap.OnMapClickListener
        public boolean onMapClick(@NonNull LatLng latLng) {
            Annotation queryMapForFeatures;
            if (!AnnotationManager.this.clickListeners.isEmpty() && (queryMapForFeatures = AnnotationManager.this.queryMapForFeatures(latLng)) != null) {
                for (OnAnnotationClickListener onAnnotationClickListener : AnnotationManager.this.clickListeners) {
                    if (onAnnotationClickListener.onAnnotationClick(queryMapForFeatures)) {
                        return true;
                    }
                }
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.mappls.sdk.maps.MapplsMap.OnMapLongClickListener
        public boolean onMapLongClick(@NonNull LatLng latLng) {
            Annotation queryMapForFeatures;
            if (!AnnotationManager.this.longClickListeners.isEmpty() && (queryMapForFeatures = AnnotationManager.this.queryMapForFeatures(latLng)) != null) {
                for (OnAnnotationLongClickListener onAnnotationLongClickListener : AnnotationManager.this.longClickListeners) {
                    if (onAnnotationLongClickListener.onAnnotationLongClick(queryMapForFeatures)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @UiThread
    public AnnotationManager(MapView mapView, MapplsMap mapplsMap, Style style, com.mappls.sdk.plugin.annotation.c<L> cVar, d dVar, String str, GeoJsonOptions geoJsonOptions) {
        this.mapplsMap = mapplsMap;
        this.mapView = mapView;
        this.style = style;
        this.belowLayerId = str;
        this.coreElementProvider = cVar;
        this.geoJsonOptions = geoJsonOptions;
        this.draggableAnnotationController = dVar;
        if (!style.isFullyLoaded()) {
            throw new RuntimeException("The style has to be non-null and fully loaded.");
        }
        AnnotationManager<L, T, S, D, U, V>.c cVar2 = new c(this, null);
        this.mapClickResolver = cVar2;
        mapplsMap.addOnMapClickListener(cVar2);
        mapplsMap.addOnMapLongClickListener(cVar2);
        dVar.d(this);
        initializeSourcesAndLayers(geoJsonOptions);
        mapView.addOnDidFinishLoadingStyleListener(new a(mapplsMap, geoJsonOptions));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeSourcesAndLayers(GeoJsonOptions geoJsonOptions) {
        this.geoJsonSource = this.coreElementProvider.a(geoJsonOptions);
        this.layer = this.coreElementProvider.c();
        this.style.addSource(this.geoJsonSource);
        String str = this.belowLayerId;
        if (str == null) {
            this.style.addLayer(this.layer);
        } else {
            this.style.addLayerBelow(this.layer, str);
        }
        initializeDataDrivenPropertyMap();
        this.layer.setProperties((PropertyValue[]) this.constantPropertyUsageMap.values().toArray(new PropertyValue[0]));
        Expression expression = this.layerFilter;
        if (expression != null) {
            setFilter(expression);
        }
        updateSource();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public T queryMapForFeatures(@NonNull LatLng latLng) {
        return queryMapForFeatures(this.mapplsMap.getProjection().toScreenLocation(latLng));
    }

    @Keep
    @UiThread
    public void addClickListener(@NonNull U u) {
        this.clickListeners.add(u);
    }

    @Keep
    @UiThread
    public void addDragListener(@NonNull D d) {
        this.dragListeners.add(d);
    }

    @Keep
    @UiThread
    public void addLongClickListener(@NonNull V v) {
        this.longClickListeners.add(v);
    }

    @Keep
    @UiThread
    public void clear(T t) {
        this.annotations.remove(t.getId());
        updateSource();
    }

    @Keep
    @UiThread
    public void clear(List<T> list) {
        for (T t : list) {
            this.annotations.remove(t.getId());
        }
        updateSource();
    }

    @Keep
    @UiThread
    public void clearAll() {
        this.annotations.clear();
        updateSource();
    }

    @Keep
    @UiThread
    public T create(S s) {
        T t = (T) s.build(this.currentId, this);
        this.annotations.put(t.getId(), t);
        this.currentId++;
        updateSource();
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Keep
    @UiThread
    public List<T> create(List<S> list) {
        ArrayList arrayList = new ArrayList();
        for (S s : list) {
            Annotation build = s.build(this.currentId, this);
            arrayList.add(build);
            this.annotations.put(build.getId(), build);
            this.currentId++;
        }
        updateSource();
        return arrayList;
    }

    public void enableDataDrivenProperty(@NonNull String str) {
        if (this.dataDrivenPropertyUsageMap.get(str).equals(Boolean.FALSE)) {
            this.dataDrivenPropertyUsageMap.put(str, Boolean.TRUE);
            setDataDrivenPropertyIsUsed(str);
        }
    }

    public abstract String getAnnotationIdKey();

    @Keep
    @UiThread
    public LongSparseArray<T> getAnnotations() {
        return this.annotations;
    }

    @VisibleForTesting
    public List<U> getClickListeners() {
        return this.clickListeners;
    }

    public List<D> getDragListeners() {
        return this.dragListeners;
    }

    @Keep
    public String getLayerId() {
        return this.layer.getId();
    }

    @VisibleForTesting
    public List<V> getLongClickListeners() {
        return this.longClickListeners;
    }

    public abstract void initializeDataDrivenPropertyMap();

    public void internalUpdateSource() {
        if (this.style.isFullyLoaded()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (int i = 0; i < this.annotations.size(); i++) {
                T valueAt = this.annotations.valueAt(i);
                if ((valueAt instanceof Symbol) && valueAt.geometry == 0) {
                    Symbol symbol = (Symbol) valueAt;
                    arrayList3.add(symbol.mapplsPin);
                    arrayList2.add(symbol);
                } else {
                    arrayList.add(Feature.fromGeometry(valueAt.getGeometry(), valueAt.getFeature()));
                    valueAt.setUsedDataDrivenProperties();
                }
            }
            if (arrayList3.size() == 0) {
                this.geoJsonSource.setGeoJson(FeatureCollection.fromFeatures(arrayList));
                return;
            }
            try {
                Object newInstance = BaseMapplsHelper.class.newInstance();
                Method declaredMethod = BaseMapplsHelper.class.getDeclaredMethod("getAnnotation", List.class, CoordinateCallback.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(newInstance, arrayList3, new b(arrayList2, arrayList));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
            }
        }
    }

    @Keep
    @UiThread
    public void onDestroy() {
        this.mapplsMap.removeOnMapClickListener(this.mapClickResolver);
        this.mapplsMap.removeOnMapLongClickListener(this.mapClickResolver);
        if (this.style.isFullyLoaded()) {
            this.style.removeLayer(getLayerId());
            this.style.removeSource(this.coreElementProvider.a());
        }
        this.draggableAnnotationController.i(this);
        this.dragListeners.clear();
        this.clickListeners.clear();
        this.longClickListeners.clear();
    }

    @Nullable
    public T queryMapForFeatures(@NonNull PointF pointF) {
        List<Feature> queryRenderedFeatures = this.mapplsMap.queryRenderedFeatures(pointF, this.coreElementProvider.b());
        if (queryRenderedFeatures.isEmpty()) {
            return null;
        }
        return this.annotations.get(queryRenderedFeatures.get(0).getProperty(getAnnotationIdKey()).getAsLong());
    }

    @Keep
    @UiThread
    public void removeClickListener(@NonNull U u) {
        this.clickListeners.remove(u);
    }

    @Keep
    @UiThread
    public void removeDragListener(@NonNull D d) {
        this.dragListeners.remove(d);
    }

    @Keep
    @UiThread
    public void removeLongClickListener(@NonNull V v) {
        this.longClickListeners.remove(v);
    }

    public abstract void setDataDrivenPropertyIsUsed(@NonNull String str);

    public abstract void setFilter(@NonNull Expression expression);

    @Keep
    @UiThread
    public void update(T t) {
        if (this.annotations.containsValue(t)) {
            this.annotations.put(t.getId(), t);
            updateSource();
            return;
        }
        Logger.e(TAG, "Can't update annotation: " + t.toString() + ", the annotation isn't active annotation.");
    }

    @Keep
    @UiThread
    public void update(List<T> list) {
        for (T t : list) {
            this.annotations.put(t.getId(), t);
        }
        updateSource();
    }

    public void updateSource() {
        this.draggableAnnotationController.k();
        internalUpdateSource();
    }
}
