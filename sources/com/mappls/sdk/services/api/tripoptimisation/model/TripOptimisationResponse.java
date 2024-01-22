package com.mappls.sdk.services.api.tripoptimisation.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.PointAsCoordinatesTypeAdapter;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.DirectionsJsonObject;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.tripoptimisation.model.AutoValue_TripOptimisationResponse;
import com.mappls.sdk.services.api.tripoptimisation.model.a;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes8.dex */
public abstract class TripOptimisationResponse extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract TripOptimisationResponse build();

        public abstract Builder code(@NonNull String str);

        public abstract Builder trips(@NonNull List<DirectionsRoute> list);

        public abstract Builder waypoints(@Nullable List<TripsWaypoint> list);
    }

    public static Builder builder() {
        return new a.b();
    }

    public static TripOptimisationResponse fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        gsonBuilder.registerTypeAdapter(Point.class, new PointAsCoordinatesTypeAdapter());
        return (TripOptimisationResponse) gsonBuilder.create().fromJson(str, (Class<Object>) TripOptimisationResponse.class);
    }

    public static TypeAdapter<TripOptimisationResponse> typeAdapter(Gson gson) {
        return new AutoValue_TripOptimisationResponse.GsonTypeAdapter(gson);
    }

    public abstract String code();

    public abstract Builder toBuilder();

    public abstract List<DirectionsRoute> trips();

    public abstract List<TripsWaypoint> waypoints();
}
