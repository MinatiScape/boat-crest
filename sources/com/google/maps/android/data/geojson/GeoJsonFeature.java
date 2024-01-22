package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
/* loaded from: classes10.dex */
public class GeoJsonFeature extends Feature implements Observer {
    public final LatLngBounds j;
    public GeoJsonPointStyle k;
    public GeoJsonLineStringStyle l;
    public GeoJsonPolygonStyle m;

    public GeoJsonFeature(Geometry geometry, String str, HashMap<String, String> hashMap, LatLngBounds latLngBounds) {
        super(geometry, str, hashMap);
        this.mId = str;
        this.j = latLngBounds;
    }

    public final void a(a aVar) {
        if (hasGeometry() && Arrays.asList(aVar.getGeometryType()).contains(getGeometry().getGeometryType())) {
            setChanged();
            notifyObservers();
        }
    }

    public LatLngBounds getBoundingBox() {
        return this.j;
    }

    public GeoJsonLineStringStyle getLineStringStyle() {
        return this.l;
    }

    public MarkerOptions getMarkerOptions() {
        return this.k.toMarkerOptions();
    }

    public GeoJsonPointStyle getPointStyle() {
        return this.k;
    }

    public PolygonOptions getPolygonOptions() {
        return this.m.toPolygonOptions();
    }

    public GeoJsonPolygonStyle getPolygonStyle() {
        return this.m;
    }

    public PolylineOptions getPolylineOptions() {
        return this.l.toPolylineOptions();
    }

    @Override // com.google.maps.android.data.Feature
    public String removeProperty(String str) {
        return super.removeProperty(str);
    }

    @Override // com.google.maps.android.data.Feature
    public void setGeometry(Geometry geometry) {
        super.setGeometry(geometry);
        setChanged();
        notifyObservers();
    }

    public void setLineStringStyle(GeoJsonLineStringStyle geoJsonLineStringStyle) {
        if (geoJsonLineStringStyle != null) {
            GeoJsonLineStringStyle geoJsonLineStringStyle2 = this.l;
            if (geoJsonLineStringStyle2 != null) {
                geoJsonLineStringStyle2.deleteObserver(this);
            }
            this.l = geoJsonLineStringStyle;
            geoJsonLineStringStyle.addObserver(this);
            a(this.l);
            return;
        }
        throw new IllegalArgumentException("Line string style cannot be null");
    }

    public void setPointStyle(GeoJsonPointStyle geoJsonPointStyle) {
        if (geoJsonPointStyle != null) {
            GeoJsonPointStyle geoJsonPointStyle2 = this.k;
            if (geoJsonPointStyle2 != null) {
                geoJsonPointStyle2.deleteObserver(this);
            }
            this.k = geoJsonPointStyle;
            geoJsonPointStyle.addObserver(this);
            a(this.k);
            return;
        }
        throw new IllegalArgumentException("Point style cannot be null");
    }

    public void setPolygonStyle(GeoJsonPolygonStyle geoJsonPolygonStyle) {
        if (geoJsonPolygonStyle != null) {
            GeoJsonPolygonStyle geoJsonPolygonStyle2 = this.m;
            if (geoJsonPolygonStyle2 != null) {
                geoJsonPolygonStyle2.deleteObserver(this);
            }
            this.m = geoJsonPolygonStyle;
            geoJsonPolygonStyle.addObserver(this);
            a(this.m);
            return;
        }
        throw new IllegalArgumentException("Polygon style cannot be null");
    }

    @Override // com.google.maps.android.data.Feature
    public String setProperty(String str, String str2) {
        return super.setProperty(str, str2);
    }

    public String toString() {
        return "Feature{\n bounding box=" + this.j + ",\n geometry=" + getGeometry() + ",\n point style=" + this.k + ",\n line string style=" + this.l + ",\n polygon style=" + this.m + ",\n id=" + this.mId + ",\n properties=" + getProperties() + "\n}\n";
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        if (observable instanceof a) {
            a((a) observable);
        }
    }
}
