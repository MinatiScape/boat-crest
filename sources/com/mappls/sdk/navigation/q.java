package com.mappls.sdk.navigation;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class q {

    /* renamed from: a  reason: collision with root package name */
    public Thread f12928a;
    public NavigationLocationProvider b;
    public NavigationApplication c;

    public q(NavigationApplication navigationApplication, NavigationLocationProvider navigationLocationProvider) {
        this.c = navigationApplication;
        this.b = navigationLocationProvider;
    }

    public static NavLocation a(NavLocation navLocation, NavLocation navLocation2, float f) {
        double latitude = (navLocation.getLatitude() * 3.141592653589793d) / 180.0d;
        float bearingTo = (float) ((navLocation.bearingTo(navLocation2) * 3.141592653589793d) / 180.0d);
        double d = (f / 1000.0f) / 6371.0d;
        double d2 = bearingTo;
        double asin = Math.asin((Math.cos(d2) * Math.sin(d) * Math.cos(latitude)) + (Math.cos(d) * Math.sin(latitude)));
        double atan2 = Math.atan2(Math.cos(latitude) * Math.sin(d) * Math.sin(d2), Math.cos(d) - (Math.sin(asin) * Math.sin(latitude))) + ((navLocation.getLongitude() * 3.141592653589793d) / 180.0d);
        NavLocation navLocation3 = new NavLocation(navLocation);
        navLocation3.setLatitude((asin * 180.0d) / 3.141592653589793d);
        navLocation3.setLongitude((atan2 * 180.0d) / 3.141592653589793d);
        navLocation3.setBearing(bearingTo);
        return navLocation3;
    }

    public final boolean a() {
        return this.f12928a != null;
    }

    public final void b() {
        if (!a()) {
            List<NavLocation> d = this.c.q.d();
            if (d.isEmpty()) {
                return;
            }
            a0 a0Var = new a0(this, new ArrayList(d), this.c);
            this.f12928a = a0Var;
            a0Var.start();
            return;
        }
        this.f12928a = null;
    }

    public final void c() {
        if (!a()) {
            this.f12928a = null;
        }
        List<NavLocation> d = this.c.q.d();
        if (d.isEmpty()) {
            return;
        }
        a0 a0Var = new a0(this, new ArrayList(d), this.c);
        this.f12928a = a0Var;
        a0Var.start();
    }

    public final void d() {
        this.f12928a = null;
    }
}
