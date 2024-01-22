package com.mappls.sdk.maps.covid;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.InteractiveLayer;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.style.layers.RasterLayer;
import com.mappls.sdk.maps.style.sources.RasterSource;
import com.mappls.sdk.maps.style.sources.TileSet;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsApiConfiguration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.FuncN;
/* loaded from: classes11.dex */
public class RasterPlugin implements MapView.OnDidFinishLoadingStyleListener {
    public MapView i;
    public MapplsMap j;
    public e l;
    public g m;
    public MapplsMap.OnInteractiveLayerClickListener o;
    public HashMap<String, InteractiveLayer> h = new HashMap<>();
    public List<Raster> k = new ArrayList();
    public boolean n = true;

    /* loaded from: classes11.dex */
    public class a implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Raster f12699a;

        public a(Raster raster) {
            this.f12699a = raster;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            String type = this.f12699a.getType();
            if (style.getLayer("raster_layer" + type) == null) {
                RasterLayer rasterLayer = new RasterLayer("raster_layer" + type, "WMS_SOURCE" + type);
                if (this.f12699a.b() != null) {
                    style.addLayerBelow(rasterLayer, this.f12699a.b());
                } else {
                    style.addLayer(rasterLayer);
                }
            }
            RasterPlugin.this.q(this.f12699a);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Raster f12700a;

        public b(Raster raster) {
            this.f12700a = raster;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            String type = this.f12700a.getType();
            if (style.getSource("WMS_SOURCE" + type) == null) {
                style.addSource(new RasterSource("WMS_SOURCE" + type, new TileSet("tileset", RasterPlugin.this.m(type)), 512));
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Raster f12701a;

        public c(Raster raster) {
            this.f12701a = raster;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            String type = this.f12701a.getType();
            if (style.getSource("WMS_SOURCE" + type) != null) {
                return;
            }
            RasterPlugin.this.n(this.f12701a);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Raster f12702a;

        public d(RasterPlugin rasterPlugin, Raster raster) {
            this.f12702a = raster;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            RasterLayer rasterLayer = (RasterLayer) style.getLayer("raster_layer" + this.f12702a.getType());
            JsonObject a2 = this.f12702a.a();
            if (rasterLayer != null) {
                if (!(a2.get("raster-brightness-max") instanceof JsonNull)) {
                    rasterLayer.setProperties(PropertyFactory.rasterBrightnessMax(Float.valueOf(a2.get("raster-brightness-max").getAsFloat())));
                }
                if (!(a2.get("raster-brightness-min") instanceof JsonNull)) {
                    rasterLayer.setProperties(PropertyFactory.rasterBrightnessMin(Float.valueOf(a2.get("raster-brightness-min").getAsFloat())));
                }
                if (!(a2.get("raster-contrast") instanceof JsonNull)) {
                    rasterLayer.setProperties(PropertyFactory.rasterContrast(Float.valueOf(a2.get("raster-contrast").getAsFloat())));
                }
                if (!(a2.get("visibility") instanceof JsonNull)) {
                    PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
                    propertyValueArr[0] = PropertyFactory.visibility(a2.get("visibility").getAsBoolean() ? Property.VISIBLE : "none");
                    rasterLayer.setProperties(propertyValueArr);
                }
                if (!(a2.get("raster-fade-duration") instanceof JsonNull)) {
                    rasterLayer.setProperties(PropertyFactory.rasterFadeDuration(Float.valueOf(a2.get("raster-fade-duration").getAsFloat())));
                }
                if (!(a2.get("raster-opacity") instanceof JsonNull)) {
                    rasterLayer.setProperties(PropertyFactory.rasterOpacity(Float.valueOf(a2.get("raster-opacity").getAsFloat())));
                }
                if (!(a2.get("raster-hue-rotate") instanceof JsonNull)) {
                    rasterLayer.setProperties(PropertyFactory.rasterHueRotate(Float.valueOf(a2.get("raster-hue-rotate").getAsFloat())));
                }
                if (!(a2.get("raster-resampling") instanceof JsonNull)) {
                    rasterLayer.setProperties(PropertyFactory.rasterResampling(a2.get("raster-resampling").getAsString()));
                }
                if (a2.get("raster-saturation") instanceof JsonNull) {
                    return;
                }
                rasterLayer.setProperties(PropertyFactory.rasterSaturation(Float.valueOf(a2.get("raster-saturation").getAsFloat())));
            }
        }
    }

    /* loaded from: classes11.dex */
    public class e implements MapplsMap.OnMapClickListener {
        public FeatureCollection h;

        /* loaded from: classes11.dex */
        public class a implements Style.OnStyleLoaded {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ LatLng f12703a;

            public a(LatLng latLng) {
                this.f12703a = latLng;
            }

            @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
            public void onStyleLoaded(@NonNull Style style) {
                e.this.f(this.f12703a, style);
            }
        }

        /* loaded from: classes11.dex */
        public class b implements FuncN<CombinedResponse> {
            public b(e eVar) {
            }

            @Override // rx.functions.FuncN
            /* renamed from: a */
            public CombinedResponse call(Object... objArr) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : objArr) {
                    if (obj instanceof ResponseBody) {
                        arrayList.add((ResponseBody) obj);
                    }
                }
                return new CombinedResponse(arrayList);
            }
        }

        /* loaded from: classes11.dex */
        public class c extends Subscriber<CombinedResponse> {
            public final /* synthetic */ LatLng l;

            /* loaded from: classes11.dex */
            public class a implements Runnable {
                public a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    RasterPlugin.this.m.g(e.this.h, c.this.l, CovidAnnotationOption.a().opacity(Float.valueOf(0.1f)).build());
                }
            }

            public c(LatLng latLng) {
                this.l = latLng;
            }

            @Override // rx.Observer
            /* renamed from: b */
            public void onNext(CombinedResponse combinedResponse) {
                FeatureCollection featureCollection;
                if (combinedResponse != null) {
                    List<ResponseBody> responseBodies = combinedResponse.getResponseBodies();
                    if (responseBodies != null) {
                        ArrayList arrayList = new ArrayList();
                        for (ResponseBody responseBody : responseBodies) {
                            try {
                                featureCollection = FeatureCollection.fromJson(responseBody.string());
                            } catch (IOException e) {
                                FeatureCollection fromFeatures = FeatureCollection.fromFeatures(new ArrayList());
                                e.printStackTrace();
                                featureCollection = fromFeatures;
                            }
                            if (featureCollection.features() != null) {
                                arrayList.addAll(featureCollection.features());
                            }
                        }
                        if (arrayList.size() > 0 && RasterPlugin.this.n) {
                            int size = arrayList.size();
                            Feature fromGeometry = Feature.fromGeometry(Point.fromLngLat(this.l.getLongitude(), this.l.getLatitude()));
                            int i = size - 1;
                            fromGeometry.addStringProperty("id", ((Feature) arrayList.get(i)).id());
                            fromGeometry.addProperty("properties", ((Feature) arrayList.get(i)).properties());
                            fromGeometry.addStringProperty(AppMeasurementSdk.ConditionalUserProperty.NAME, ((Feature) arrayList.get(i)).id());
                            arrayList.add(fromGeometry);
                        }
                        e.this.h = FeatureCollection.fromFeatures(arrayList);
                    }
                    Log.e("TAG", combinedResponse.getResponseBodies().size() + "");
                }
            }

            @Override // rx.Observer
            public void onCompleted() {
                List<Feature> features;
                RasterPlugin.this.i.post(new a());
                if (RasterPlugin.this.o != null) {
                    ArrayList arrayList = new ArrayList();
                    if (e.this.h != null && (features = e.this.h.features()) != null) {
                        for (int size = (RasterPlugin.this.n ? features.size() - 1 : features.size()) - 1; size >= 0; size--) {
                            if (features.get(size).properties() != null) {
                                InteractiveItemDetails interactiveItemDetails = (InteractiveItemDetails) new Gson().fromJson((JsonElement) features.get(size).properties(), (Class<Object>) InteractiveItemDetails.class);
                                interactiveItemDetails.setType(e.this.e(features.get(size).id()));
                                arrayList.add(interactiveItemDetails);
                            }
                        }
                    }
                    if (arrayList.size() > 0) {
                        RasterPlugin.this.o.onInteractiveLayerClicked((InteractiveItemDetails) arrayList.get(0));
                    }
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                th.printStackTrace();
            }
        }

        public e() {
        }

        public final String e(String str) {
            if (str != null) {
                String[] split = str.split("\\.");
                return split.length > 0 ? split[0] : str;
            }
            return null;
        }

        public final void f(LatLng latLng, Style style) {
            List<Feature> queryRenderedFeatures;
            if (style.getLayer("annotation_layer_info_window") != null && (queryRenderedFeatures = RasterPlugin.this.j.queryRenderedFeatures(RasterPlugin.this.j.getProjection().toScreenLocation(latLng), "annotation_layer_info_window")) != null && queryRenderedFeatures.size() > 0 && RasterPlugin.this.m != null) {
                RasterPlugin.this.m.f();
            }
            if (RasterPlugin.this.m != null) {
                RasterPlugin.this.m.f();
            }
            ArrayList arrayList = new ArrayList();
            for (Raster raster : RasterPlugin.this.k) {
                if (raster.getVisibility() == null || raster.getVisibility().booleanValue()) {
                    raster.getType();
                    arrayList.add(MapplsLayerDetail.a().height(Integer.valueOf(RasterPlugin.this.i.getHeight())).width(Integer.valueOf(RasterPlugin.this.i.getWidth())).isStyle(Boolean.valueOf(raster.isStyles())).clickedPoint(RasterPlugin.this.j.getProjection().toScreenLocation(latLng)).visibleRegion(RasterPlugin.this.j.getProjection().getVisibleRegion().latLngBounds).layerType(raster.getType()).build().d());
                }
            }
            if (arrayList.size() == 0) {
                this.h = FeatureCollection.fromFeatures(new ArrayList());
            } else {
                Observable.zip(arrayList, new b(this)).subscribe((Subscriber) new c(latLng));
            }
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnMapClickListener
        public boolean onMapClick(@NonNull LatLng latLng) {
            RasterPlugin.this.j.getStyle(new a(latLng));
            return false;
        }
    }

    public RasterPlugin(MapView mapView, MapplsMap mapplsMap) {
        this.i = mapView;
        this.j = mapplsMap;
        mapView.addOnDidFinishLoadingStyleListener(this);
        this.m = new g(mapplsMap, mapView);
        e eVar = new e();
        this.l = eVar;
        mapplsMap.addOnMapClickListener(eVar);
    }

    public void addLayers(List<InteractiveLayer> list) {
        for (InteractiveLayer interactiveLayer : list) {
            boolean z = false;
            if (interactiveLayer.getId().equalsIgnoreCase("containment_zone_gradient") || interactiveLayer.getId().equalsIgnoreCase("corona_isolation_ward") || interactiveLayer.getId().equalsIgnoreCase("corona_sample_collection_centre") || interactiveLayer.getId().equalsIgnoreCase("corona_testing_centre") || interactiveLayer.getId().equalsIgnoreCase("corona_treatment_centre") || interactiveLayer.getId().equalsIgnoreCase("government_ration_distribution") || interactiveLayer.getId().equalsIgnoreCase("hunger_and_night_shelter") || interactiveLayer.getId().equalsIgnoreCase("hunger_relief_centre") || interactiveLayer.getId().equalsIgnoreCase("mygov_food_and_night_shelter") || interactiveLayer.getId().equalsIgnoreCase("relief_centers_ndma")) {
                z = true;
            }
            l(RasterOptions.d().type(interactiveLayer.getId()).visibility(Boolean.FALSE).styles(Boolean.valueOf(z)).build());
        }
    }

    public List<String> getLayers() {
        ArrayList arrayList = new ArrayList();
        for (Raster raster : this.k) {
            arrayList.add(raster.getType());
        }
        return arrayList;
    }

    public List<InteractiveLayer> getVisibleInteractiveLayer() {
        return new ArrayList(this.h.values());
    }

    public boolean hideLayer(InteractiveLayer interactiveLayer) {
        for (Raster raster : this.k) {
            if (raster.getType().equalsIgnoreCase(interactiveLayer.getId())) {
                this.h.remove(interactiveLayer.getId());
                raster.setVisibility(false);
                p(raster);
                return true;
            }
        }
        return false;
    }

    public void isShowInfoWindow(boolean z) {
        this.n = z;
    }

    public final void j(Raster raster) {
        this.j.getStyle(new a(raster));
    }

    public final void k(Raster raster) {
        this.j.getStyle(new b(raster));
    }

    public final Raster l(RasterOptions rasterOptions) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("raster-brightness-max", rasterOptions.b());
        jsonObject.addProperty("raster-brightness-min", rasterOptions.c());
        jsonObject.addProperty("raster-contrast", rasterOptions.e());
        jsonObject.addProperty("visibility", rasterOptions.m());
        jsonObject.addProperty("raster-fade-duration", rasterOptions.f());
        jsonObject.addProperty("raster-opacity", rasterOptions.h());
        jsonObject.addProperty("raster-hue-rotate", rasterOptions.g());
        jsonObject.addProperty("raster-resampling", rasterOptions.i());
        jsonObject.addProperty("raster-saturation", rasterOptions.j());
        jsonObject.addProperty("raster-saturation", rasterOptions.j());
        Raster raster = new Raster(rasterOptions.l(), jsonObject);
        raster.c(rasterOptions.a());
        raster.setStyles(rasterOptions.k() != null ? rasterOptions.k().booleanValue() : false);
        this.k.add(raster);
        n(raster);
        g gVar = this.m;
        if (gVar != null) {
            gVar.f();
        }
        return raster;
    }

    public final String m(String str) {
        if (MapplsApiConfiguration.getInstance().getUrlData() != null) {
            return MapplsApiConfiguration.getInstance().getUrlData().getMgisUrl() + "api/covid/wms?service=WMS&bbox={bbox-epsg-3857}&format=image/png&version=1.1.1&request=GetMap&srs=EPSG:3857&transparent=true&width=512&height=512&layers=covid:" + str + "&access_token=" + MapplsAccountManager.getInstance().getRestAPIKey();
        }
        return "https://mgis.mappls.com/api/covid/wms?service=WMS&bbox={bbox-epsg-3857}&format=image/png&version=1.1.1&request=GetMap&srs=EPSG:3857&transparent=true&width=512&height=512&layers=covid:" + str + "&access_token=" + MapplsAccountManager.getInstance().getRestAPIKey();
    }

    public final void n(Raster raster) {
        k(raster);
        j(raster);
    }

    public void o() {
        if (this.k != null) {
            r();
            List<InteractiveLayer> visibleInteractiveLayer = getVisibleInteractiveLayer();
            if (visibleInteractiveLayer.size() > 0) {
                for (InteractiveLayer interactiveLayer : visibleInteractiveLayer) {
                    showLayer(interactiveLayer);
                }
            }
        }
        g gVar = this.m;
        if (gVar != null) {
            gVar.i();
        }
    }

    @Override // com.mappls.sdk.maps.MapView.OnDidFinishLoadingStyleListener
    public void onDidFinishLoadingStyle() {
        onFinishLoadingStyle();
    }

    public void onFinishLoadingStyle() {
        o();
    }

    public final void p(Raster raster) {
        g gVar = this.m;
        if (gVar != null) {
            gVar.f();
        }
        q(raster);
    }

    public final void q(Raster raster) {
        this.j.getStyle(new d(this, raster));
    }

    public final void r() {
        for (Raster raster : this.k) {
            s(raster);
        }
    }

    public final void s(Raster raster) {
        this.j.getStyle(new c(raster));
    }

    public void setOnInteractiveLayerClickListener(MapplsMap.OnInteractiveLayerClickListener onInteractiveLayerClickListener) {
        this.o = onInteractiveLayerClickListener;
    }

    public boolean showLayer(InteractiveLayer interactiveLayer) {
        for (Raster raster : this.k) {
            if (raster.getType().equalsIgnoreCase(interactiveLayer.getId())) {
                this.h.put(interactiveLayer.getId(), interactiveLayer);
                raster.setVisibility(true);
                p(raster);
                return true;
            }
        }
        return false;
    }
}
