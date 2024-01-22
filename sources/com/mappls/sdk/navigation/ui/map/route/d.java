package com.mappls.sdk.navigation.ui.map.route;

import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class d implements Runnable {
    public final /* synthetic */ u h;
    public final /* synthetic */ e i;

    public d(e eVar, u uVar) {
        this.i = eVar;
        this.h = uVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        AtomicBoolean atomicBoolean;
        List<FeatureCollection> list;
        HashMap<LineString, DirectionsRoute> hashMap;
        atomicBoolean = this.i.l;
        if (atomicBoolean.get()) {
            return;
        }
        u uVar = this.h;
        list = this.i.i;
        hashMap = this.i.k;
        uVar.a(list, hashMap);
    }
}
