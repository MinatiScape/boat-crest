package com.mappls.sdk.navigation.routing;

import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.routing.c;
import java.util.List;
/* loaded from: classes11.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public NavLocation f12947a;
    public String b;
    public List<LatLng> c;
    public NavigationApplication d;
    public com.mappls.sdk.navigation.d e;
    public c.a f;
    public boolean g;
    public com.mappls.sdk.navigation.router.a h;

    public final String toString() {
        try {
            return "RouteOptionsParams{start=" + this.f12947a.toString() + ", end=" + this.b + ", intermediates=" + this.c.toString() + '}';
        } catch (Exception e) {
            NavigationLogger.d(e);
            return "";
        }
    }
}
