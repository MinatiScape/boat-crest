package com.mappls.sdk.navigation.ui.map.route;

import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.CoordinateCallback;
import com.mappls.sdk.maps.CoordinateResult;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class p implements CoordinateCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Map f12994a;
    public final /* synthetic */ List b;
    public final /* synthetic */ o c;

    public p(o oVar, Map map, List list) {
        this.c = oVar;
        this.f12994a = map;
        this.b = list;
    }

    @Override // com.mappls.sdk.maps.CoordinateCallback
    public void coordinateResultSuccess(List<CoordinateResult> list) {
        if (list != null && list.size() > 0) {
            for (CoordinateResult coordinateResult : list) {
                Feature fromGeometry = Feature.fromGeometry(Point.fromLngLat(coordinateResult.getLongitude().doubleValue(), coordinateResult.getLatitude().doubleValue()));
                fromGeometry.addStringProperty("wayPoint", (String) this.f12994a.get(coordinateResult.getMapplsPin()));
                this.b.add(fromGeometry);
            }
        }
        o.w(this.c, FeatureCollection.fromFeatures(this.b));
    }

    @Override // com.mappls.sdk.maps.CoordinateCallback
    public void onFailure() {
        o.w(this.c, FeatureCollection.fromFeatures(this.b));
    }
}
