package com.mappls.sdk.services.api.directions.models;

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
import com.mappls.sdk.services.api.directions.models.AutoValue_DirectionsWaypoint;
import com.mappls.sdk.services.api.directions.models.g;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class DirectionsWaypoint extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract DirectionsWaypoint build();

        public abstract Builder distance(@Nullable Double d);

        public abstract Builder hint(@Nullable String str);

        public abstract Builder name(@Nullable String str);

        public abstract Builder rawLocation(@Nullable double[] dArr);
    }

    public static Builder builder() {
        return new g.b();
    }

    public static DirectionsWaypoint fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (DirectionsWaypoint) gsonBuilder.create().fromJson(str, (Class<Object>) DirectionsWaypoint.class);
    }

    public static TypeAdapter<DirectionsWaypoint> typeAdapter(Gson gson) {
        return new AutoValue_DirectionsWaypoint.GsonTypeAdapter(gson);
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
}
