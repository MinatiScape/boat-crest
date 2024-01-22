package com.mappls.sdk.navigation.ui.map.route;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.StyleRes;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.navigation.camera.INavigation;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes11.dex */
public class NavigationMapRoute implements LifecycleObserver, MapView.OnDidFinishLoadingStyleListener {
    @StyleRes
    public final int h;
    public final MapplsMap i;
    public final MapView j;
    public h k;
    public q l;
    public boolean m = false;
    public boolean n = false;
    public o o;
    public f p;
    public j q;
    public INavigation r;

    public NavigationMapRoute(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @Nullable String str, @Nullable INavigation iNavigation, @StyleRes int i) {
        this.h = i;
        this.j = mapView;
        this.i = mapplsMap;
        this.r = iNavigation;
        o a2 = a(mapView, mapplsMap, str);
        this.o = a2;
        f fVar = new f(mapView, mapplsMap, a2.r(), i);
        this.p = fVar;
        this.q = new j(mapView, mapplsMap, fVar.c(this.o.r()));
        o oVar = this.o;
        this.k = new h(oVar);
        this.l = new q(oVar, this.p);
        d();
    }

    public final o a(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @Nullable String str) {
        Context context = mapView.getContext();
        return new o(context, mapplsMap, this.h, str, new i(context), new r(), new n(), FeatureCollection.fromFeatures(new Feature[0]), FeatureCollection.fromFeatures(new Feature[0]), new ArrayList(), new ArrayList(), new HashMap(), 0, true, true, new Handler(context.getMainLooper()));
    }

    public void a(@Nullable v vVar) {
        this.k.a(vVar);
    }

    public void a(List<ReportDetails> list) {
        j jVar = this.q;
        if (jVar != null) {
            jVar.g(list);
        }
    }

    public void a(@NonNull @Size(min = 1) List<DirectionsRoute> list, int i) {
        this.o.l(list, i);
    }

    public void b() {
        o oVar = this.o;
        if (oVar != null) {
            oVar.d();
        }
        f fVar = this.p;
        if (fVar != null) {
            fVar.d();
        }
        j jVar = this.q;
        if (jVar != null) {
            jVar.d();
        }
    }

    public void c() {
        this.o.v();
        this.p.l(this.o.r());
        this.q.f(this.o.r());
        INavigation iNavigation = this.r;
        if (iNavigation != null) {
            iNavigation.removeProgressChangeListener(this.l);
        }
        q qVar = new q(this.o, this.p);
        this.l = qVar;
        INavigation iNavigation2 = this.r;
        if (iNavigation2 != null) {
            iNavigation2.setProgressChangeListener(qVar);
        }
        this.i.removeOnMapClickListener(this.k);
        h hVar = new h(this.o);
        this.k = hVar;
        this.i.addOnMapClickListener(hVar);
    }

    public final void d() {
        if (!this.m) {
            this.i.addOnMapClickListener(this.k);
            this.m = true;
        }
        INavigation iNavigation = this.r;
        if (iNavigation != null) {
            iNavigation.setProgressChangeListener(this.l);
        }
        if (this.n) {
            return;
        }
        this.j.addOnDidFinishLoadingStyleListener(this);
        this.n = true;
    }

    @Override // com.mappls.sdk.maps.MapView.OnDidFinishLoadingStyleListener
    public void onDidFinishLoadingStyle() {
        c();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        d();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        if (this.m) {
            this.i.removeOnMapClickListener(this.k);
            this.m = false;
        }
        INavigation iNavigation = this.r;
        if (iNavigation != null) {
            iNavigation.removeProgressChangeListener(this.l);
        }
        if (this.n) {
            this.j.removeOnDidFinishLoadingStyleListener(this);
            this.n = false;
        }
    }
}
