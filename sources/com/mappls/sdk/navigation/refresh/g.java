package com.mappls.sdk.navigation.refresh;

import android.os.Handler;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import com.mappls.sdk.services.api.event.route.MapplsRouteSummary;
import com.mappls.sdk.services.api.event.route.MapplsRouteSummaryManager;
import java.util.ArrayList;
import timber.log.Timber;
/* loaded from: classes11.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    public final NavigationApplication f12936a;
    public boolean b;
    public final Handler c = new Handler();
    public final Runnable d = new Runnable() { // from class: com.mappls.sdk.navigation.refresh.k
        @Override // java.lang.Runnable
        public final void run() {
            g.this.b();
        }
    };

    public g(NavigationApplication navigationApplication) {
        this.f12936a = navigationApplication;
        this.b = ((Boolean) navigationApplication.k().H.get()).booleanValue();
    }

    public static void c(g gVar) {
        gVar.c.removeCallbacksAndMessages(null);
        if (gVar.f12936a.h().q()) {
            gVar.c.postDelayed(gVar.d, ((Integer) gVar.f12936a.k().c.get()).intValue() > 30000 ? ((Integer) gVar.f12936a.k().c.get()).intValue() : 30000);
        }
    }

    public final void a() {
        Timber.e("endTrip", new Object[0]);
        this.c.removeCallbacksAndMessages(null);
    }

    public final void a(Boolean bool) {
        if (this.b == bool.booleanValue()) {
            return;
        }
        this.b = bool.booleanValue();
        b();
    }

    public final void b() {
        RouteOptions fromJson;
        String str;
        Timber.e("routeRecalculated", new Object[0]);
        if (this.b && MapplsNavigationHelper.getInstance().isNavigating()) {
            if (this.f12936a.h().q() && (fromJson = RouteOptions.fromJson((String) this.f12936a.k().f12956a.get())) != null && fromJson.annotations() != null && fromJson.annotations().contains(DirectionsCriteria.ANNOTATION_NODES) && fromJson.requestUuid() != null && this.b) {
                int routeIndex = MapplsNavigationHelper.getInstance().getRouteIndex();
                if (MapplsNavigationHelper.getInstance().getCurrentRoute().routeIndex() != null) {
                    routeIndex = MapplsNavigationHelper.getInstance().getCurrentRoute().routeIndex().intValue();
                }
                MapplsRouteSummary.Builder builder = MapplsRouteSummary.builder();
                if (MapplsNavigationHelper.getInstance().getBaseRes() != null) {
                    builder.baseUrl(MapplsNavigationHelper.getInstance().getBaseRes() + "apis/O2O/");
                }
                MapplsRouteSummary.Builder routeIdx = builder.routeId(fromJson.requestUuid()).routeIdx(Integer.valueOf(routeIndex));
                if (MapplsNavigationHelper.getInstance().getCurrentNodeId().longValue() > 0) {
                    str = MapplsNavigationHelper.getInstance().getCurrentNodeId() + "";
                } else {
                    str = null;
                }
                MapplsRouteSummaryManager.newInstance(routeIdx.currentNode(str).build()).call(new j(this));
                return;
            }
            return;
        }
        this.f12936a.h().k().setEvents(new ArrayList());
        this.f12936a.o().a(6);
    }

    public final void c() {
        this.c.removeCallbacksAndMessages(null);
        if (this.f12936a.h().q()) {
            this.c.postDelayed(this.d, 0L);
        }
    }
}
