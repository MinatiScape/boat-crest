package com.mappls.sdk.direction.ui.fragment;

import com.mappls.sdk.direction.ui.model.DirectionOptions;
import com.mappls.sdk.direction.ui.model.DirectionPoint;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.services.api.event.route.model.RouteReport;
import com.mappls.sdk.services.api.event.route.model.RouteReportSummaryResponse;
import java.util.List;
/* loaded from: classes11.dex */
public interface h {
    void clearRoute();

    void onCancel();

    void onPreviewClick(List<LegStep> list, int i, int i2);

    void onRouteReportSummaryClick(RouteReportSummaryResponse routeReportSummaryResponse, Integer num, DirectionsResponse directionsResponse);

    void onStartNavigation(DirectionPoint directionPoint, DirectionPoint directionPoint2, List<DirectionPoint> list, DirectionsResponse directionsResponse, int i);

    void onTripEstimation(CostEstimationResponse costEstimationResponse, DirectionsResponse directionsResponse, int i);

    void onUpdateRoute(LatLng latLng, LatLng latLng2, List<LatLng> list, List<DirectionsRoute> list2, int i);

    void onUpdateRouteReport(List<RouteReport> list, int i);

    void searchCategory(String str, DirectionOptions directionOptions);
}
