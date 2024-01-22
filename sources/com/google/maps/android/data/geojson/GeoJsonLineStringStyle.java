package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Style;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes10.dex */
public class GeoJsonLineStringStyle extends Style implements a {
    public static final String[] h = {"LineString", "MultiLineString", "GeometryCollection"};

    public GeoJsonLineStringStyle() {
        PolylineOptions polylineOptions = new PolylineOptions();
        this.mPolylineOptions = polylineOptions;
        polylineOptions.clickable(true);
    }

    public final void a() {
        setChanged();
        notifyObservers();
    }

    public int getColor() {
        return this.mPolylineOptions.getColor();
    }

    @Override // com.google.maps.android.data.geojson.a
    public String[] getGeometryType() {
        return h;
    }

    public List<PatternItem> getPattern() {
        return this.mPolylineOptions.getPattern();
    }

    public float getWidth() {
        return this.mPolylineOptions.getWidth();
    }

    public float getZIndex() {
        return this.mPolylineOptions.getZIndex();
    }

    public boolean isClickable() {
        return this.mPolylineOptions.isClickable();
    }

    public boolean isGeodesic() {
        return this.mPolylineOptions.isGeodesic();
    }

    public boolean isVisible() {
        return this.mPolylineOptions.isVisible();
    }

    public void setClickable(boolean z) {
        this.mPolylineOptions.clickable(z);
        a();
    }

    public void setColor(int i) {
        this.mPolylineOptions.color(i);
        a();
    }

    public void setGeodesic(boolean z) {
        this.mPolylineOptions.geodesic(z);
        a();
    }

    public void setPattern(List<PatternItem> list) {
        this.mPolylineOptions.pattern(list);
        a();
    }

    public void setVisible(boolean z) {
        this.mPolylineOptions.visible(z);
        a();
    }

    public void setWidth(float f) {
        setLineStringWidth(f);
        a();
    }

    public void setZIndex(float f) {
        this.mPolylineOptions.zIndex(f);
        a();
    }

    public PolylineOptions toPolylineOptions() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(this.mPolylineOptions.getColor());
        polylineOptions.clickable(this.mPolylineOptions.isClickable());
        polylineOptions.geodesic(this.mPolylineOptions.isGeodesic());
        polylineOptions.visible(this.mPolylineOptions.isVisible());
        polylineOptions.width(this.mPolylineOptions.getWidth());
        polylineOptions.zIndex(this.mPolylineOptions.getZIndex());
        polylineOptions.pattern(getPattern());
        return polylineOptions;
    }

    public String toString() {
        return "LineStringStyle{\n geometry type=" + Arrays.toString(h) + ",\n color=" + getColor() + ",\n clickable=" + isClickable() + ",\n geodesic=" + isGeodesic() + ",\n visible=" + isVisible() + ",\n width=" + getWidth() + ",\n z index=" + getZIndex() + ",\n pattern=" + getPattern() + "\n}\n";
    }
}
