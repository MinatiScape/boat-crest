package com.mappls.sdk.navigation.ui.map.route;

import android.os.Handler;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class x extends Thread {
    public final int h;
    public final List<FeatureCollection> i;
    public final WeakReference<t> j;
    public AtomicBoolean k = new AtomicBoolean(false);
    public Handler l;

    public x(int i, List<FeatureCollection> list, t tVar, Handler handler) {
        this.h = i;
        this.i = list;
        this.j = new WeakReference<>(tVar);
        this.l = handler;
    }

    public void b() {
        this.k.set(true);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        FeatureCollection featureCollection;
        List<Feature> features;
        t tVar;
        ArrayList arrayList = new ArrayList(this.i);
        if (arrayList.isEmpty() || this.k.get() || (features = (featureCollection = (FeatureCollection) arrayList.remove(this.h)).features()) == null || features.isEmpty()) {
            return;
        }
        for (Feature feature : features) {
            if (this.k.get()) {
                return;
            }
            feature.addBooleanProperty("primary-route", Boolean.TRUE);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            FeatureCollection featureCollection2 = (FeatureCollection) it.next();
            if (this.k.get()) {
                return;
            }
            List<Feature> features2 = featureCollection2.features();
            if (features2 != null && !features2.isEmpty()) {
                for (Feature feature2 : features2) {
                    if (this.k.get()) {
                        return;
                    }
                    feature2.addBooleanProperty("primary-route", Boolean.FALSE);
                }
                continue;
            }
        }
        if (this.k.get()) {
            return;
        }
        arrayList.add(0, featureCollection);
        if (this.k.get() || (tVar = this.j.get()) == null) {
            return;
        }
        this.l.post(new w(this, tVar, arrayList));
    }
}
