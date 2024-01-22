package com.mappls.sdk.direction.ui.plugin;

import com.mappls.sdk.direction.ui.plugin.d;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes11.dex */
public final class e implements Runnable {
    public final /* synthetic */ HashMap h;
    public final /* synthetic */ List i;
    public final /* synthetic */ d j;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ int h;
        public final /* synthetic */ DirectionsRoute i;

        public a(int i, DirectionsRoute directionsRoute) {
            this.h = i;
            this.i = directionsRoute;
        }

        @Override // java.lang.Runnable
        public final void run() {
            d.k kVar;
            d.k kVar2;
            kVar = e.this.j.s;
            if (kVar != null) {
                kVar2 = e.this.j.s;
                kVar2.a(this.h, this.i);
            }
        }
    }

    public e(d dVar, HashMap hashMap, List list) {
        this.j = dVar;
        this.h = hashMap;
        this.i = list;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MapView mapView;
        ArrayList arrayList = new ArrayList(this.h.keySet());
        Collections.sort(arrayList);
        double doubleValue = ((Double) arrayList.get(0)).doubleValue();
        if (doubleValue < 20.0d) {
            DirectionsRoute directionsRoute = (DirectionsRoute) this.h.get(Double.valueOf(doubleValue));
            int indexOf = this.i.indexOf(directionsRoute);
            this.j.r = indexOf;
            d.t(this.j);
            mapView = this.j.k;
            mapView.post(new a(indexOf, directionsRoute));
        }
    }
}
