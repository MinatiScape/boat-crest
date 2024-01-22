package com.mappls.sdk.navigation.refresh;

import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.event.route.model.RouteReportSummaryResponse;
/* loaded from: classes11.dex */
public final class j implements OnResponseCallback<RouteReportSummaryResponse> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ g f12939a;

    public j(g gVar) {
        this.f12939a = gVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x002e, code lost:
        if (r0.h().x() == false) goto L7;
     */
    @Override // com.mappls.sdk.services.api.OnResponseCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onError(int r3, java.lang.String r4) {
        /*
            r2 = this;
            com.mappls.sdk.navigation.refresh.g r0 = r2.f12939a
            com.mappls.sdk.navigation.NavigationApplication r0 = com.mappls.sdk.navigation.refresh.g.a(r0)
            com.mappls.sdk.navigation.routing.d r0 = r0.h()
            boolean r0 = r0.q()
            if (r0 == 0) goto L35
            com.mappls.sdk.navigation.refresh.g r0 = r2.f12939a
            com.mappls.sdk.navigation.NavigationApplication r0 = com.mappls.sdk.navigation.refresh.g.a(r0)
            com.mappls.sdk.navigation.routing.d r0 = r0.h()
            boolean r0 = r0.s()
            if (r0 != 0) goto L30
            com.mappls.sdk.navigation.refresh.g r0 = r2.f12939a
            com.mappls.sdk.navigation.NavigationApplication r0 = com.mappls.sdk.navigation.refresh.g.a(r0)
            com.mappls.sdk.navigation.routing.d r0 = r0.h()
            boolean r0 = r0.x()
            if (r0 != 0) goto L35
        L30:
            com.mappls.sdk.navigation.refresh.g r0 = r2.f12939a
            com.mappls.sdk.navigation.refresh.g.c(r0)
        L35:
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1 = 0
            r0[r1] = r3
            r3 = 1
            r0[r3] = r4
            java.lang.String r3 = "Request failed with code = %d and message = %s"
            timber.log.Timber.e(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.refresh.j.onError(int, java.lang.String):void");
    }

    @Override // com.mappls.sdk.services.api.OnResponseCallback
    public final void onSuccess(RouteReportSummaryResponse routeReportSummaryResponse) {
        NavigationApplication navigationApplication;
        NavigationApplication navigationApplication2;
        NavigationApplication navigationApplication3;
        NavigationApplication navigationApplication4;
        RouteReportSummaryResponse routeReportSummaryResponse2 = routeReportSummaryResponse;
        if (routeReportSummaryResponse2 != null && routeReportSummaryResponse2.getRoutes() != null && routeReportSummaryResponse2.getRoutes().size() > 0) {
            navigationApplication4 = this.f12939a.f12936a;
            navigationApplication4.h().k().setEvents(routeReportSummaryResponse2.getRoutes().get(0).getReports());
        }
        navigationApplication = this.f12939a.f12936a;
        if (navigationApplication.h().q()) {
            MapplsNavigationHelper.getInstance().setRouteReportSummaryResponse(routeReportSummaryResponse2);
            navigationApplication2 = this.f12939a.f12936a;
            if (!navigationApplication2.h().s()) {
                navigationApplication3 = this.f12939a.f12936a;
                if (navigationApplication3.h().x()) {
                    return;
                }
            }
            g.c(this.f12939a);
        }
    }
}
