package com.mappls.sdk.navigation.ui.map.route;

import com.mappls.sdk.geojson.FeatureCollection;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class b implements Runnable {
    public final /* synthetic */ s h;
    public final /* synthetic */ c i;

    public b(c cVar, s sVar) {
        this.i = cVar;
        this.h = sVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        AtomicBoolean atomicBoolean;
        FeatureCollection featureCollection;
        atomicBoolean = this.i.k;
        if (atomicBoolean.get()) {
            return;
        }
        s sVar = this.h;
        featureCollection = this.i.j;
        sVar.a(featureCollection);
    }
}
