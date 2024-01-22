package com.mappls.sdk.maps.annotations;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.geometry.LatLng;
import java.util.ArrayList;
import java.util.List;
@Deprecated
/* loaded from: classes11.dex */
public abstract class BasePointCollection extends Annotation {
    @Keep
    private float alpha = 1.0f;
    @Keep
    private List<LatLng> points = new ArrayList();

    public abstract void a();

    public void addPoint(LatLng latLng) {
        this.points.add(latLng);
        a();
    }

    public float getAlpha() {
        return this.alpha;
    }

    @NonNull
    public List<LatLng> getPoints() {
        return new ArrayList(this.points);
    }

    public void setAlpha(float f) {
        this.alpha = f;
        a();
    }

    public void setPoints(@NonNull List<LatLng> list) {
        this.points = new ArrayList(list);
        a();
    }
}
