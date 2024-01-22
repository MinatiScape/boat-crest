package com.mappls.sdk.navigation;

import com.google.android.gms.common.ConnectionResult;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class a0 extends Thread {
    public final /* synthetic */ List h;
    public final /* synthetic */ boolean i = false;
    public final /* synthetic */ float j = 1.0f;
    public final /* synthetic */ NavigationApplication k;
    public final /* synthetic */ q l;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ NavLocation h;

        public a(NavLocation navLocation) {
            this.h = navLocation;
        }

        @Override // java.lang.Runnable
        public final void run() {
            NavigationLocationProvider navigationLocationProvider;
            navigationLocationProvider = a0.this.l.b;
            navigationLocationProvider.setLocationFromSimulation(this.h);
        }
    }

    public a0(q qVar, ArrayList arrayList, NavigationApplication navigationApplication) {
        this.l = qVar;
        this.h = arrayList;
        this.k = navigationApplication;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Thread thread;
        NavLocation navLocation;
        float max;
        NavLocation navLocation2 = this.h.isEmpty() ? null : new NavLocation((NavLocation) this.h.remove(0));
        long time = navLocation2 == null ? 0L : navLocation2.getTime();
        q qVar = this.l;
        List list = this.h;
        qVar.getClass();
        float max2 = list.isEmpty() ? 20.0f : Math.max(20.0f, navLocation2.distanceTo((NavLocation) list.get(0)) / 2.0f);
        while (!this.h.isEmpty()) {
            thread = this.l.f12928a;
            if (thread == null) {
                break;
            }
            int i = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            float f = 1.5f;
            if (this.i) {
                navLocation = (NavLocation) this.h.remove(0);
                max = navLocation.distanceTo(navLocation2);
                if (!this.h.isEmpty()) {
                    i = (int) (((NavLocation) this.h.get(0)).getTime() - navLocation.getTime());
                    f = ((float) (navLocation.getTime() - time)) / 1000.0f;
                    time = navLocation.getTime();
                }
            } else if (navLocation2.distanceTo((NavLocation) this.h.get(0)) > max2) {
                max = max2;
                navLocation = q.a(navLocation2, (NavLocation) this.h.get(0), max2);
            } else {
                navLocation = new NavLocation((NavLocation) this.h.remove(0));
                q qVar2 = this.l;
                List list2 = this.h;
                qVar2.getClass();
                max = list2.isEmpty() ? 20.0f : Math.max(20.0f, navLocation.distanceTo((NavLocation) list2.get(0)) / 2.0f);
            }
            if (f != 0.0f) {
                navLocation.setSpeed((max / f) * this.j);
            }
            navLocation.setTime(System.currentTimeMillis());
            if (!navLocation.hasAccuracy() || Double.isNaN(navLocation.getAccuracy())) {
                navLocation.setAccuracy(5.0f);
            }
            if (navLocation2 != null && navLocation2.distanceTo(navLocation) > 3.0f) {
                navLocation.setBearing(navLocation2.bearingTo(navLocation));
            }
            this.k.l.post(new a(navLocation));
            try {
                Thread.sleep(i / this.j);
            } catch (InterruptedException e) {
                NavigationLogger.d(e);
            }
            navLocation2 = navLocation;
            max2 = max;
        }
        this.l.d();
    }
}
