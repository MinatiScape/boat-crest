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
import com.mappls.sdk.services.api.directions.WalkingOptions;
import com.mappls.sdk.services.api.directions.WalkingOptionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.AutoValue_RouteOptions;
import com.mappls.sdk.services.api.directions.models.n;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class RouteOptions extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder accessToken(@Nullable String str);

        public abstract Builder alternatives(@Nullable Boolean bool);

        public abstract Builder annotations(@Nullable String str);

        public abstract Builder approaches(String str);

        public abstract Builder bannerInstructions(Boolean bool);

        public abstract Builder baseUrl(@NonNull String str);

        public abstract Builder bearings(String str);

        public abstract RouteOptions build();

        public abstract Builder continueStraight(Boolean bool);

        public abstract Builder coordinates(@NonNull List<String> list);

        public abstract Builder deviceID(@Nullable String str);

        public abstract Builder exclude(@Nullable String str);

        public abstract Builder geometries(String str);

        public abstract Builder instructions(Boolean bool);

        public abstract Builder isSort(@Nullable Boolean bool);

        public abstract Builder language(String str);

        public abstract Builder lessVerbose(Boolean bool);

        public abstract Builder overview(@Nullable String str);

        public abstract Builder profile(@NonNull String str);

        public abstract Builder radiuses(String str);

        public abstract Builder requestUuid(@Nullable String str);

        public abstract Builder resource(@NonNull String str);

        public abstract Builder roundaboutExits(@Nullable Boolean bool);

        public abstract Builder routeRefresh(@Nullable Boolean bool);

        public abstract Builder routeType(@Nullable Integer num);

        public abstract Builder sessionId(@Nullable String str);

        public abstract Builder skipWaypoints(@Nullable Boolean bool);

        public abstract Builder steps(@Nullable Boolean bool);

        public abstract Builder user(@NonNull String str);

        public abstract Builder walkingOptions(@Nullable WalkingOptions walkingOptions);

        public abstract Builder waypointIndices(@Nullable String str);

        public abstract Builder waypointNames(@Nullable String str);

        public abstract Builder waypointTargets(@Nullable String str);
    }

    public static Builder builder() {
        return new n.b();
    }

    public static RouteOptions fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        gsonBuilder.registerTypeAdapter(Point.class, new PointAsCoordinatesTypeAdapter());
        gsonBuilder.registerTypeAdapterFactory(WalkingOptionsAdapterFactory.create());
        return (RouteOptions) gsonBuilder.create().fromJson(str, (Class<Object>) RouteOptions.class);
    }

    public static TypeAdapter<RouteOptions> typeAdapter(Gson gson) {
        return new AutoValue_RouteOptions.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("access_token")
    public abstract String accessToken();

    @Nullable
    public abstract Boolean alternatives();

    @Nullable
    public abstract String annotations();

    @Nullable
    public abstract String approaches();

    @Nullable
    @SerializedName("banner_instructions")
    public abstract Boolean bannerInstructions();

    @NonNull
    public abstract String baseUrl();

    @Nullable
    public abstract String bearings();

    @Nullable
    @SerializedName("continueStraight")
    public abstract Boolean continueStraight();

    @NonNull
    public abstract List<String> coordinates();

    @Nullable
    public abstract String deviceID();

    @Nullable
    public abstract String exclude();

    public abstract String geometries();

    @Nullable
    @SerializedName("instructions")
    public abstract Boolean instructions();

    @Nullable
    public abstract Boolean isSort();

    @Nullable
    public abstract String language();

    @Nullable
    @SerializedName("lessverbose")
    public abstract Boolean lessVerbose();

    @Nullable
    public abstract String overview();

    @NonNull
    public abstract String profile();

    @Nullable
    public abstract String radiuses();

    @Nullable
    @SerializedName("uuid")
    public abstract String requestUuid();

    @NonNull
    public abstract String resource();

    @Nullable
    public abstract Boolean roundaboutExits();

    @Nullable
    public abstract Boolean routeRefresh();

    @Nullable
    public abstract Integer routeType();

    @Nullable
    @SerializedName("sessionId")
    public abstract String sessionId();

    @Nullable
    @SerializedName("skip_waypoints")
    public abstract Boolean skipWaypoints();

    @Nullable
    public abstract Boolean steps();

    public abstract Builder toBuilder();

    @NonNull
    public abstract String user();

    @Nullable
    public abstract WalkingOptions walkingOptions();

    @Nullable
    @SerializedName("waypoints")
    public abstract String waypointIndices();

    @Nullable
    @SerializedName("waypoint_names")
    public abstract String waypointNames();

    @Nullable
    @SerializedName("waypoint_targets")
    public abstract String waypointTargets();
}
