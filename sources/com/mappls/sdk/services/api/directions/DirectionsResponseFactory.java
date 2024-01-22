package com.mappls.sdk.services.api.directions;

import androidx.annotation.Keep;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Response;
import retrofit2.Response;
@Keep
/* loaded from: classes11.dex */
class DirectionsResponseFactory {
    private final MapplsDirections mapplsDirections;

    public DirectionsResponseFactory(MapplsDirections mapplsDirections) {
        this.mapplsDirections = mapplsDirections;
    }

    private List<DirectionsRoute> generateRouteOptions(Response<DirectionsResponse> response) {
        List<DirectionsRoute> routes = response.body().routes();
        ArrayList arrayList = new ArrayList();
        for (DirectionsRoute directionsRoute : routes) {
            arrayList.add(directionsRoute.toBuilder().routeOptions(RouteOptions.builder().profile(this.mapplsDirections.profile()).resource(this.mapplsDirections.resource()).baseUrl(this.mapplsDirections.baseUrl()).coordinates(this.mapplsDirections.coordinates()).waypointIndices(this.mapplsDirections.waypointIndices()).waypointNames(this.mapplsDirections.waypointNames()).waypointTargets(this.mapplsDirections.waypointTargets()).skipWaypoints(this.mapplsDirections.skipWaypoints()).lessVerbose(this.mapplsDirections.lessVerbose()).annotations(this.mapplsDirections.annotation()).approaches(this.mapplsDirections.approaches()).bearings(this.mapplsDirections.bearing()).alternatives(this.mapplsDirections.alternatives()).language(this.mapplsDirections.language()).radiuses(this.mapplsDirections.radius()).user(this.mapplsDirections.user()).continueStraight(this.mapplsDirections.continueStraight()).bannerInstructions(this.mapplsDirections.bannerInstructions()).instructions(this.mapplsDirections.instructions()).roundaboutExits(this.mapplsDirections.roundaboutExits()).geometries(this.mapplsDirections.geometries()).overview(this.mapplsDirections.overview()).steps(this.mapplsDirections.steps()).exclude(this.mapplsDirections.exclude()).walkingOptions(this.mapplsDirections.walkingOptions()).routeRefresh(this.mapplsDirections.routeRefresh()).deviceID(this.mapplsDirections.deviceId()).requestUuid(response.body().uuid()).sessionId(response.body().sessionId()).isSort(this.mapplsDirections.isSort()).routeType(this.mapplsDirections.routeType()).build()).routeId(response.body().uuid()).build());
        }
        return arrayList;
    }

    private boolean isNotSuccessful(Response<DirectionsResponse> response) {
        return !response.isSuccessful() || response.body() == null || response.body().routes().isEmpty();
    }

    public Response<DirectionsResponse> generate(Response<DirectionsResponse> response) {
        return isNotSuccessful(response) ? response : Response.success(response.body().toBuilder().routes(generateRouteOptions(response)).build(), new Response.Builder().code(200).message(CoveApiConstants.RESPONSE_STATUS_VALUE_OK).protocol(response.raw().protocol()).headers(response.headers()).request(response.raw().request()).build());
    }
}
