package com.mappls.sdk.maps.annotations;

import androidx.annotation.Keep;
import androidx.core.view.ViewCompat;
import com.mappls.sdk.maps.MapplsMap;
@Deprecated
/* loaded from: classes11.dex */
public final class Polyline extends BasePointCollection {
    @Keep
    private int color = ViewCompat.MEASURED_STATE_MASK;
    @Keep
    private float width = 10.0f;

    @Override // com.mappls.sdk.maps.annotations.BasePointCollection
    public void a() {
        MapplsMap mapplsMap = getMapplsMap();
        if (mapplsMap != null) {
            mapplsMap.updatePolyline(this);
        }
    }

    public int getColor() {
        return this.color;
    }

    public float getWidth() {
        return this.width;
    }

    public void setColor(int i) {
        this.color = i;
        a();
    }

    public void setWidth(float f) {
        this.width = f;
        a();
    }
}
