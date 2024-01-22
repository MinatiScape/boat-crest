package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.Style;
import java.util.Arrays;
/* loaded from: classes10.dex */
public class GeoJsonPointStyle extends Style implements a {
    public static final String[] h = {"Point", "MultiPoint", "GeometryCollection"};

    public GeoJsonPointStyle() {
        this.mMarkerOptions = new MarkerOptions();
    }

    public final void a() {
        setChanged();
        notifyObservers();
    }

    public float getAlpha() {
        return this.mMarkerOptions.getAlpha();
    }

    public float getAnchorU() {
        return this.mMarkerOptions.getAnchorU();
    }

    public float getAnchorV() {
        return this.mMarkerOptions.getAnchorV();
    }

    @Override // com.google.maps.android.data.geojson.a
    public String[] getGeometryType() {
        return h;
    }

    public BitmapDescriptor getIcon() {
        return this.mMarkerOptions.getIcon();
    }

    public float getInfoWindowAnchorU() {
        return this.mMarkerOptions.getInfoWindowAnchorU();
    }

    public float getInfoWindowAnchorV() {
        return this.mMarkerOptions.getInfoWindowAnchorV();
    }

    @Override // com.google.maps.android.data.Style
    public float getRotation() {
        return this.mMarkerOptions.getRotation();
    }

    public String getSnippet() {
        return this.mMarkerOptions.getSnippet();
    }

    public String getTitle() {
        return this.mMarkerOptions.getTitle();
    }

    public float getZIndex() {
        return this.mMarkerOptions.getZIndex();
    }

    public boolean isDraggable() {
        return this.mMarkerOptions.isDraggable();
    }

    public boolean isFlat() {
        return this.mMarkerOptions.isFlat();
    }

    public boolean isVisible() {
        return this.mMarkerOptions.isVisible();
    }

    public void setAlpha(float f) {
        this.mMarkerOptions.alpha(f);
        a();
    }

    public void setAnchor(float f, float f2) {
        setMarkerHotSpot(f, f2, "fraction", "fraction");
        a();
    }

    public void setDraggable(boolean z) {
        this.mMarkerOptions.draggable(z);
        a();
    }

    public void setFlat(boolean z) {
        this.mMarkerOptions.flat(z);
        a();
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        this.mMarkerOptions.icon(bitmapDescriptor);
        a();
    }

    public void setInfoWindowAnchor(float f, float f2) {
        this.mMarkerOptions.infoWindowAnchor(f, f2);
        a();
    }

    public void setRotation(float f) {
        setMarkerRotation(f);
        a();
    }

    public void setSnippet(String str) {
        this.mMarkerOptions.snippet(str);
        a();
    }

    public void setTitle(String str) {
        this.mMarkerOptions.title(str);
        a();
    }

    public void setVisible(boolean z) {
        this.mMarkerOptions.visible(z);
        a();
    }

    public void setZIndex(float f) {
        this.mMarkerOptions.zIndex(f);
        a();
    }

    public MarkerOptions toMarkerOptions() {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.alpha(this.mMarkerOptions.getAlpha());
        markerOptions.anchor(this.mMarkerOptions.getAnchorU(), this.mMarkerOptions.getAnchorV());
        markerOptions.draggable(this.mMarkerOptions.isDraggable());
        markerOptions.flat(this.mMarkerOptions.isFlat());
        markerOptions.icon(this.mMarkerOptions.getIcon());
        markerOptions.infoWindowAnchor(this.mMarkerOptions.getInfoWindowAnchorU(), this.mMarkerOptions.getInfoWindowAnchorV());
        markerOptions.rotation(this.mMarkerOptions.getRotation());
        markerOptions.snippet(this.mMarkerOptions.getSnippet());
        markerOptions.title(this.mMarkerOptions.getTitle());
        markerOptions.visible(this.mMarkerOptions.isVisible());
        markerOptions.zIndex(this.mMarkerOptions.getZIndex());
        return markerOptions;
    }

    public String toString() {
        return "PointStyle{\n geometry type=" + Arrays.toString(h) + ",\n alpha=" + getAlpha() + ",\n anchor U=" + getAnchorU() + ",\n anchor V=" + getAnchorV() + ",\n draggable=" + isDraggable() + ",\n flat=" + isFlat() + ",\n info window anchor U=" + getInfoWindowAnchorU() + ",\n info window anchor V=" + getInfoWindowAnchorV() + ",\n rotation=" + getRotation() + ",\n snippet=" + getSnippet() + ",\n title=" + getTitle() + ",\n visible=" + isVisible() + ",\n z index=" + getZIndex() + "\n}\n";
    }
}
