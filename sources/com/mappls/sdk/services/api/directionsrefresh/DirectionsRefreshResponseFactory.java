package com.mappls.sdk.services.api.directionsrefresh;

import androidx.annotation.Keep;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Response;
import retrofit2.Response;
@Keep
/* loaded from: classes11.dex */
class DirectionsRefreshResponseFactory {
    private final MapplsDirectionsRefresh mapplsDirectionsRefresh;

    public DirectionsRefreshResponseFactory(MapplsDirectionsRefresh mapplsDirectionsRefresh) {
        this.mapplsDirectionsRefresh = mapplsDirectionsRefresh;
    }

    private List<DirectionsRoute> generateAlternatives(Response<DirectionsRoute> response) {
        List<DirectionsRoute> alternatives = response.body().alternatives();
        if (alternatives == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (DirectionsRoute directionsRoute : alternatives) {
            arrayList.add(directionsRoute.toBuilder().routeId(response.body().betterRouteId()).build());
        }
        return arrayList;
    }

    private boolean isNotSuccessful(Response<DirectionsRoute> response) {
        return !response.isSuccessful() || response.body() == null;
    }

    public Response<DirectionsRoute> generate(Response<DirectionsRoute> response) {
        return isNotSuccessful(response) ? response : Response.success(response.body().toBuilder().routeId(this.mapplsDirectionsRefresh.requestId()).alternatives(generateAlternatives(response)).build(), new Response.Builder().code(200).message(CoveApiConstants.RESPONSE_STATUS_VALUE_OK).protocol(response.raw().protocol()).headers(response.headers()).request(response.raw().request()).build());
    }
}
