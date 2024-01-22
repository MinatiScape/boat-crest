package com.mappls.sdk.navigation.ui.map.route;

import androidx.annotation.NonNull;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.turf.TurfConstants;
import com.mappls.sdk.turf.TurfMeasurement;
import com.mappls.sdk.turf.TurfMisc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes11.dex */
public class h implements MapplsMap.OnMapClickListener {
    public final o h;
    public v i;

    public h(o oVar) {
        this.h = oVar;
    }

    public void a(v vVar) {
        this.i = vVar;
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnMapClickListener
    public boolean onMapClick(@NonNull LatLng latLng) {
        v vVar;
        if (this.h.C()) {
            HashMap<LineString, DirectionsRoute> A = this.h.A();
            if (A == null || A.isEmpty()) {
                return false;
            }
            List<DirectionsRoute> y = this.h.y();
            HashMap hashMap = new HashMap();
            Point fromLngLat = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
            for (LineString lineString : A.keySet()) {
                Point point = (Point) TurfMisc.nearestPointOnLine(fromLngLat, lineString.coordinates()).geometry();
                if (point == null) {
                    break;
                }
                hashMap.put(Double.valueOf(TurfMeasurement.distance(fromLngLat, point, TurfConstants.UNIT_METERS)), A.get(lineString));
            }
            ArrayList arrayList = new ArrayList(hashMap.keySet());
            Collections.sort(arrayList);
            int indexOf = y.indexOf((DirectionsRoute) hashMap.get(arrayList.get(0)));
            if (this.h.n(indexOf) && (vVar = this.i) != null) {
                vVar.a(indexOf);
            }
            return false;
        }
        return false;
    }
}
