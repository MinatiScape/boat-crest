package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.PointAsCoordinatesTypeAdapter;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.AutoValue_DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.e;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class DirectionsResponse extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract DirectionsResponse autoBuild();

        public DirectionsResponse build() {
            return autoBuild();
        }

        public abstract Builder code(@NonNull String str);

        public abstract Builder message(@Nullable String str);

        public abstract Builder routes(@NonNull List<DirectionsRoute> list);

        public abstract List<DirectionsRoute> routes();

        public abstract Builder sessionId(@Nullable String str);

        public abstract Builder uuid(@Nullable String str);

        public abstract Builder waypoints(@Nullable List<DirectionsWaypoint> list);
    }

    @NonNull
    public static Builder builder() {
        return new e.b();
    }

    public static DirectionsResponse fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        gsonBuilder.registerTypeAdapter(Point.class, new PointAsCoordinatesTypeAdapter());
        return (DirectionsResponse) gsonBuilder.create().fromJson(str, (Class<Object>) DirectionsResponse.class);
    }

    public static TypeAdapter<DirectionsResponse> typeAdapter(Gson gson) {
        return new AutoValue_DirectionsResponse.GsonTypeAdapter(gson);
    }

    @NonNull
    public abstract String code();

    @Nullable
    public abstract String message();

    @NonNull
    public abstract List<DirectionsRoute> routes();

    @Nullable
    @SerializedName("sessionId")
    public abstract String sessionId();

    public abstract Builder toBuilder();

    @Nullable
    @SerializedName("routeId")
    public abstract String uuid();

    @Nullable
    public abstract List<DirectionsWaypoint> waypoints();
}
