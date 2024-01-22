package com.mappls.sdk.navigation.ui.map.route;

import android.os.Handler;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class c extends Thread {
    public List<ReportDetails> h;
    public WeakReference<s> i;
    public FeatureCollection j = FeatureCollection.fromFeatures(new ArrayList());
    public AtomicBoolean k = new AtomicBoolean(false);
    public Handler l;

    public c(List<ReportDetails> list, s sVar, Handler handler) {
        this.h = list;
        this.i = new WeakReference<>(sVar);
        this.l = handler;
    }

    public void b() {
        this.k.set(true);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        s sVar;
        List<ReportDetails> list = this.h;
        if (list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (ReportDetails reportDetails : this.h) {
                if (this.k.get()) {
                    return;
                }
                Feature fromGeometry = Feature.fromGeometry(Point.fromLngLat(reportDetails.getLongitude().doubleValue(), reportDetails.getLatitude().doubleValue()));
                fromGeometry.addStringProperty("mappls-navigation-route-event-marker", (reportDetails.getChildCategory() == null || reportDetails.getParentCategory() == null) ? reportDetails.getId() : reportDetails.getParentCategory() + "_" + reportDetails.getChildCategory());
                arrayList.add(fromGeometry);
            }
            this.j = FeatureCollection.fromFeatures(arrayList);
        }
        if (this.k.get() || (sVar = this.i.get()) == null) {
            return;
        }
        this.l.post(new b(this, sVar));
    }
}
