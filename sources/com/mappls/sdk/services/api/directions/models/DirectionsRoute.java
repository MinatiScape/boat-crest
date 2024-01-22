package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.utils.PolylineUtils;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.AutoValue_DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.f;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class DirectionsRoute extends DirectionsJsonObject {
    private List<Point> points = null;

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder alternatives(@Nullable List<DirectionsRoute> list);

        public abstract Builder betterRouteId(@Nullable String str);

        public abstract DirectionsRoute build();

        public abstract Builder distance(@Nullable Double d);

        public abstract Builder duration(@Nullable Double d);

        public abstract Builder geometry(@Nullable String str);

        public abstract Builder legs(@Nullable List<RouteLeg> list);

        public abstract Builder routeClasses(@Nullable RouteClasses routeClasses);

        public abstract Builder routeId(@Nullable String str);

        public abstract Builder routeIndex(Integer num);

        public abstract Builder routeOptions(@Nullable RouteOptions routeOptions);

        public abstract Builder summary(@Nullable String str);

        public abstract Builder weight(@Nullable Double d);

        public abstract Builder weightName(@Nullable String str);
    }

    public static Builder builder() {
        return new f.b();
    }

    public static DirectionsRoute fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (DirectionsRoute) gsonBuilder.create().fromJson(str, (Class<Object>) DirectionsRoute.class);
    }

    public static TypeAdapter<DirectionsRoute> typeAdapter(Gson gson) {
        return new AutoValue_DirectionsRoute.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract List<DirectionsRoute> alternatives();

    @Nullable
    public abstract String betterRouteId();

    @Nullable
    public abstract Double distance();

    @Nullable
    public abstract Double duration();

    @Nullable
    public abstract String geometry();

    public List<Point> getCoordinates() {
        if (this.points == null) {
            this.points = PolylineUtils.decode(geometry(), 6);
        }
        return this.points;
    }

    @Nullable
    public abstract List<RouteLeg> legs();

    @Nullable
    @SerializedName("contains_classes")
    public abstract RouteClasses routeClasses();

    @Nullable
    public abstract String routeId();

    @Nullable
    public abstract Integer routeIndex();

    @Nullable
    public abstract RouteOptions routeOptions();

    @Nullable
    public abstract String summary();

    public abstract Builder toBuilder();

    @Nullable
    public abstract Double weight();

    @Nullable
    @SerializedName("weight_name")
    public abstract String weightName();
}
