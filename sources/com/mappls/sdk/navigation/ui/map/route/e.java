package com.mappls.sdk.navigation.ui.map.route;

import android.os.Handler;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.RouteLeg;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class e extends Thread {
    public final List<DirectionsRoute> h;
    public final WeakReference<u> j;
    public Handler m;
    public final int n;
    public final List<FeatureCollection> i = new ArrayList();
    public final HashMap<LineString, DirectionsRoute> k = new HashMap<>();
    public AtomicBoolean l = new AtomicBoolean(false);

    public e(List<DirectionsRoute> list, int i, u uVar, Handler handler) {
        this.h = list;
        this.j = new WeakReference<>(uVar);
        this.m = handler;
        this.n = i;
    }

    public void b() {
        this.l.set(true);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        u uVar;
        int i = 0;
        while (i < this.h.size()) {
            if (this.l.get()) {
                return;
            }
            DirectionsRoute directionsRoute = this.h.get(i);
            boolean z = i == this.n;
            ArrayList arrayList = new ArrayList();
            if (directionsRoute != null) {
                LineString fromPolyline = LineString.fromPolyline(directionsRoute.geometry(), 6);
                Feature fromGeometry = Feature.fromGeometry(fromPolyline);
                fromGeometry.addBooleanProperty("primary-route", Boolean.valueOf(z));
                arrayList.add(fromGeometry);
                this.k.put(fromPolyline, directionsRoute);
                ArrayList arrayList2 = new ArrayList();
                for (RouteLeg routeLeg : directionsRoute.legs()) {
                    if (routeLeg.annotation() == null || routeLeg.annotation().congestion() == null) {
                        arrayList2.add(Feature.fromGeometry(fromPolyline));
                    } else {
                        for (int i2 = 0; i2 < routeLeg.annotation().congestion().size(); i2++) {
                            if (routeLeg.annotation().congestion().size() + 1 <= fromPolyline.coordinates().size()) {
                                ArrayList arrayList3 = new ArrayList();
                                arrayList3.add(fromPolyline.coordinates().get(i2));
                                arrayList3.add(fromPolyline.coordinates().get(i2 + 1));
                                Feature fromGeometry2 = Feature.fromGeometry(LineString.fromLngLats(arrayList3));
                                fromGeometry2.addStringProperty(DirectionsCriteria.ANNOTATION_CONGESTION, routeLeg.annotation().congestion().get(i2));
                                fromGeometry2.addBooleanProperty("primary-route", Boolean.valueOf(z));
                                arrayList2.add(fromGeometry2);
                            }
                        }
                    }
                }
                arrayList.addAll(arrayList2);
            }
            this.i.add(FeatureCollection.fromFeatures(arrayList));
            i++;
        }
        if (this.l.get() || (uVar = this.j.get()) == null) {
            return;
        }
        this.m.post(new d(this, uVar));
    }
}
