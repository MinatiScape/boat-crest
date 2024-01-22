package com.mappls.sdk.maps.annotations;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.geometry.LatLng;
import java.util.ArrayList;
import java.util.List;
@Deprecated
/* loaded from: classes11.dex */
public final class Polygon extends BasePointCollection {
    @Keep
    private int fillColor = ViewCompat.MEASURED_STATE_MASK;
    @Keep
    private int strokeColor = ViewCompat.MEASURED_STATE_MASK;
    @Keep
    private List<List<LatLng>> holes = new ArrayList();

    @Override // com.mappls.sdk.maps.annotations.BasePointCollection
    public void a() {
        MapplsMap mapplsMap = getMapplsMap();
        if (mapplsMap != null) {
            mapplsMap.updatePolygon(this);
        }
    }

    public void b(List<LatLng> list) {
        this.holes.add(list);
        a();
    }

    public int getFillColor() {
        return this.fillColor;
    }

    public List<List<LatLng>> getHoles() {
        return new ArrayList(this.holes);
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public void setFillColor(int i) {
        this.fillColor = i;
        a();
    }

    public void setHoles(@NonNull List<? extends List<LatLng>> list) {
        this.holes = new ArrayList(list);
        a();
    }

    public void setStrokeColor(int i) {
        this.strokeColor = i;
        a();
    }
}
