package com.mappls.sdk.services.api.directions;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.mappls.sdk.services.api.directions.models.BannerComponents;
import com.mappls.sdk.services.api.directions.models.BannerInstructions;
import com.mappls.sdk.services.api.directions.models.BannerText;
import com.mappls.sdk.services.api.directions.models.DirectionsError;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.DirectionsWaypoint;
import com.mappls.sdk.services.api.directions.models.IntersectionLanes;
import com.mappls.sdk.services.api.directions.models.LegAnnotation;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.services.api.directions.models.MaxSpeed;
import com.mappls.sdk.services.api.directions.models.RouteClasses;
import com.mappls.sdk.services.api.directions.models.RouteLeg;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import com.mappls.sdk.services.api.directions.models.StepIntersection;
import com.mappls.sdk.services.api.directions.models.StepManeuver;
import com.mappls.sdk.services.api.directions.models.VoiceInstructions;
import com.mappls.sdk.services.api.directionsrefresh.models.DirectionsRefreshResponse;
import com.mappls.sdk.services.api.distance.models.DistanceResponse;
import com.mappls.sdk.services.api.distance.models.DistanceResults;
import com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse;
import com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class AutoValueGson_DirectionsAdapterFactory extends DirectionsAdapterFactory {
    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (BannerComponents.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) BannerComponents.typeAdapter(gson);
        }
        if (BannerInstructions.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) BannerInstructions.typeAdapter(gson);
        }
        if (BannerText.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) BannerText.typeAdapter(gson);
        }
        if (DirectionsError.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) DirectionsError.typeAdapter(gson);
        }
        if (DirectionsRefreshResponse.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) DirectionsRefreshResponse.typeAdapter(gson);
        }
        if (DirectionsResponse.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) DirectionsResponse.typeAdapter(gson);
        }
        if (DirectionsRoute.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) DirectionsRoute.typeAdapter(gson);
        }
        if (DirectionsWaypoint.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) DirectionsWaypoint.typeAdapter(gson);
        }
        if (DistanceResponse.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) DistanceResponse.typeAdapter(gson);
        }
        if (DistanceResults.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) DistanceResults.typeAdapter(gson);
        }
        if (IntersectionLanes.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) IntersectionLanes.typeAdapter(gson);
        }
        if (LegAnnotation.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) LegAnnotation.typeAdapter(gson);
        }
        if (LegStep.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) LegStep.typeAdapter(gson);
        }
        if (MaxSpeed.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) MaxSpeed.typeAdapter(gson);
        }
        if (RouteClasses.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) RouteClasses.typeAdapter(gson);
        }
        if (RouteLeg.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) RouteLeg.typeAdapter(gson);
        }
        if (RouteOptions.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) RouteOptions.typeAdapter(gson);
        }
        if (StepIntersection.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) StepIntersection.typeAdapter(gson);
        }
        if (StepManeuver.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) StepManeuver.typeAdapter(gson);
        }
        if (TripOptimisationResponse.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) TripOptimisationResponse.typeAdapter(gson);
        }
        if (TripsWaypoint.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) TripsWaypoint.typeAdapter(gson);
        }
        if (VoiceInstructions.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) VoiceInstructions.typeAdapter(gson);
        }
        if (WalkingOptions.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) WalkingOptions.typeAdapter(gson);
        }
        return null;
    }
}
