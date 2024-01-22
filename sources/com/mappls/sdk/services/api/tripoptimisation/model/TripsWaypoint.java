package com.mappls.sdk.services.api.tripoptimisation.model;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.DirectionsJsonObject;
import com.mappls.sdk.services.api.tripoptimisation.model.AutoValue_TripsWaypoint;
import com.mappls.sdk.services.api.tripoptimisation.model.b;
@AutoValue
@Keep
/* loaded from: classes8.dex */
public abstract class TripsWaypoint extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract TripsWaypoint build();

        public abstract Builder distance(@Nullable Double d);

        public abstract Builder hint(@Nullable String str);

        public abstract Builder name(@Nullable String str);

        public abstract Builder rawLocation(@Nullable double[] dArr);

        public abstract Builder tripsIndex(@Nullable Integer num);

        public abstract Builder waypointIndex(@Nullable Integer num);
    }

    public static Builder builder() {
        return new b.C0703b();
    }

    public static TripsWaypoint fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (TripsWaypoint) gsonBuilder.create().fromJson(str, (Class<Object>) TripsWaypoint.class);
    }

    public static TypeAdapter<TripsWaypoint> typeAdapter(Gson gson) {
        return new AutoValue_TripsWaypoint.GsonTypeAdapter(gson);
    }

    public abstract Double distance();

    public abstract String hint();

    @Nullable
    public Point location() {
        return Point.fromLngLat(rawLocation()[0], rawLocation()[1]);
    }

    @Nullable
    public abstract String name();

    @Nullable
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    public abstract double[] rawLocation();

    public abstract Builder toBuilder();

    @SerializedName("trips_index")
    public abstract Integer tripsIndex();

    @SerializedName("waypoint_index")
    public abstract Integer waypointIndex();
}
