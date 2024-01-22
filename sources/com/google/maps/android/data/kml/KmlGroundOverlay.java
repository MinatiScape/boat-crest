package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class KmlGroundOverlay {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, String> f11554a;
    public final GroundOverlayOptions b;
    public String c;
    public LatLngBounds d;

    public KmlGroundOverlay(String str, LatLngBounds latLngBounds, float f, int i, HashMap<String, String> hashMap, float f2) {
        GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions();
        this.b = groundOverlayOptions;
        this.c = str;
        this.f11554a = hashMap;
        if (latLngBounds != null) {
            this.d = latLngBounds;
            groundOverlayOptions.positionFromBounds(latLngBounds);
            groundOverlayOptions.bearing(f2);
            groundOverlayOptions.zIndex(f);
            groundOverlayOptions.visible(i != 0);
            return;
        }
        throw new IllegalArgumentException("No LatLonBox given");
    }

    public GroundOverlayOptions a() {
        return this.b;
    }

    public String getImageUrl() {
        return this.c;
    }

    public LatLngBounds getLatLngBox() {
        return this.d;
    }

    public Iterable<String> getProperties() {
        return this.f11554a.keySet();
    }

    public String getProperty(String str) {
        return this.f11554a.get(str);
    }

    public boolean hasProperty(String str) {
        return this.f11554a.get(str) != null;
    }

    public String toString() {
        return "GroundOverlay{\n properties=" + this.f11554a + ",\n image url=" + this.c + ",\n LatLngBox=" + this.d + "\n}\n";
    }
}
