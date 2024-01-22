package com.google.maps.android.data;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.geojson.GeoJsonRenderer;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlRenderer;
/* loaded from: classes10.dex */
public abstract class Layer {

    /* renamed from: a  reason: collision with root package name */
    public Renderer f11545a;

    /* loaded from: classes10.dex */
    public interface OnFeatureClickListener {
        void onFeatureClick(Feature feature);
    }

    public void addFeature(Feature feature) {
        this.f11545a.addFeature(feature);
    }

    public void addGeoJsonToMap() {
        Renderer renderer = this.f11545a;
        if (renderer instanceof GeoJsonRenderer) {
            ((GeoJsonRenderer) renderer).addLayerToMap();
            return;
        }
        throw new UnsupportedOperationException("Stored renderer is not a GeoJsonRenderer");
    }

    public void addKMLToMap() {
        Renderer renderer = this.f11545a;
        if (renderer instanceof KmlRenderer) {
            ((KmlRenderer) renderer).addLayerToMap();
            return;
        }
        throw new UnsupportedOperationException("Stored renderer is not a KmlRenderer");
    }

    public abstract void addLayerToMap();

    public Feature getContainerFeature(Object obj) {
        return this.f11545a.m(obj);
    }

    public Iterable<KmlContainer> getContainers() {
        Renderer renderer = this.f11545a;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).getNestedContainers();
        }
        return null;
    }

    public GeoJsonLineStringStyle getDefaultLineStringStyle() {
        return this.f11545a.n();
    }

    public GeoJsonPointStyle getDefaultPointStyle() {
        return this.f11545a.o();
    }

    public GeoJsonPolygonStyle getDefaultPolygonStyle() {
        return this.f11545a.p();
    }

    public Feature getFeature(Object obj) {
        return this.f11545a.q(obj);
    }

    public Iterable<? extends Feature> getFeatures() {
        return this.f11545a.getFeatures();
    }

    public Iterable<KmlGroundOverlay> getGroundOverlays() {
        Renderer renderer = this.f11545a;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).getGroundOverlays();
        }
        return null;
    }

    public GoogleMap getMap() {
        return this.f11545a.getMap();
    }

    public boolean hasContainers() {
        Renderer renderer = this.f11545a;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).hasNestedContainers();
        }
        return false;
    }

    public boolean hasFeatures() {
        return this.f11545a.hasFeatures();
    }

    public boolean isLayerOnMap() {
        return this.f11545a.isLayerOnMap();
    }

    public void removeFeature(Feature feature) {
        this.f11545a.removeFeature(feature);
    }

    public void removeLayerFromMap() {
        Renderer renderer = this.f11545a;
        if (renderer instanceof GeoJsonRenderer) {
            ((GeoJsonRenderer) renderer).removeLayerFromMap();
        } else if (renderer instanceof KmlRenderer) {
            ((KmlRenderer) renderer).removeLayerFromMap();
        }
    }

    public void setMap(GoogleMap googleMap) {
        this.f11545a.setMap(googleMap);
    }

    public void setOnFeatureClickListener(OnFeatureClickListener onFeatureClickListener) {
        this.f11545a.A(onFeatureClickListener);
    }

    public void storeRenderer(Renderer renderer) {
        this.f11545a = renderer;
    }
}
