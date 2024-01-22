package com.mappls.sdk.maps.annotations;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
@Deprecated
/* loaded from: classes11.dex */
public abstract class Annotation implements Comparable<Annotation> {
    private long id = -1;
    public MapView mapView;
    public MapplsMap mapplsMap;

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && (obj instanceof Annotation) && this.id == ((Annotation) obj).getId();
    }

    public long getId() {
        return this.id;
    }

    public MapView getMapView() {
        return this.mapView;
    }

    public MapplsMap getMapplsMap() {
        return this.mapplsMap;
    }

    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    public void remove() {
        MapplsMap mapplsMap = this.mapplsMap;
        if (mapplsMap == null) {
            return;
        }
        mapplsMap.removeAnnotation(this);
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    public void setMapplsMap(MapplsMap mapplsMap) {
        this.mapplsMap = mapplsMap;
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull Annotation annotation) {
        if (this.id < annotation.getId()) {
            return 1;
        }
        return this.id > annotation.getId() ? -1 : 0;
    }
}
