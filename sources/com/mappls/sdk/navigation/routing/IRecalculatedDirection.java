package com.mappls.sdk.navigation.routing;

import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import java.util.List;
/* loaded from: classes11.dex */
public interface IRecalculatedDirection {
    DirectionsResponse calculateRouteImpl(NavLocation navLocation, LatLng latLng, List<LatLng> list, RouteOptions routeOptions);
}
