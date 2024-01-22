package com.mappls.sdk.navigation;

import androidx.annotation.Keep;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
@Keep
/* loaded from: classes11.dex */
public class AlternateRoute {
    private DirectionsRoute directionsRoute;
    private Integer fasterDuration;

    public AlternateRoute(Integer num, DirectionsRoute directionsRoute) {
        this.fasterDuration = num;
        this.directionsRoute = directionsRoute;
    }

    public DirectionsRoute getDirectionsRoute() {
        return this.directionsRoute;
    }

    public Integer getFasterDuration() {
        return this.fasterDuration;
    }

    public void setDirectionsRoute(DirectionsRoute directionsRoute) {
        this.directionsRoute = directionsRoute;
    }

    public void setFasterDuration(Integer num) {
        this.fasterDuration = num;
    }
}
