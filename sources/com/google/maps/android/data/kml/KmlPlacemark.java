package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import java.util.Map;
/* loaded from: classes10.dex */
public class KmlPlacemark extends Feature {
    public final String j;
    public final KmlStyle k;

    public KmlPlacemark(Geometry geometry, String str, KmlStyle kmlStyle, Map<String, String> map) {
        super(geometry, str, map);
        this.j = str;
        this.k = kmlStyle;
    }

    public KmlStyle getInlineStyle() {
        return this.k;
    }

    public MarkerOptions getMarkerOptions() {
        KmlStyle kmlStyle = this.k;
        if (kmlStyle == null) {
            return null;
        }
        return kmlStyle.getMarkerOptions();
    }

    public PolygonOptions getPolygonOptions() {
        KmlStyle kmlStyle = this.k;
        if (kmlStyle == null) {
            return null;
        }
        return kmlStyle.getPolygonOptions();
    }

    public PolylineOptions getPolylineOptions() {
        KmlStyle kmlStyle = this.k;
        if (kmlStyle == null) {
            return null;
        }
        return kmlStyle.getPolylineOptions();
    }

    public String getStyleId() {
        return super.getId();
    }

    public String toString() {
        return "Placemark{\n style id=" + this.j + ",\n inline style=" + this.k + "\n}\n";
    }
}
