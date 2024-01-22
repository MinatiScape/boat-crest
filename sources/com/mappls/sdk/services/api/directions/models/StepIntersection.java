package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.AutoValue_StepIntersection;
import com.mappls.sdk.services.api.directions.models.o;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class StepIntersection extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder bearings(@Nullable List<Integer> list);

        public abstract StepIntersection build();

        public abstract Builder classes(@Nullable List<String> list);

        public abstract Builder entry(@Nullable List<Boolean> list);

        public abstract Builder in(@Nullable Integer num);

        public abstract Builder lanes(@Nullable List<IntersectionLanes> list);

        public abstract Builder out(@Nullable Integer num);

        public abstract Builder rawLocation(@NonNull double[] dArr);
    }

    public static Builder builder() {
        return new o.b();
    }

    public static StepIntersection fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (StepIntersection) gsonBuilder.create().fromJson(str, (Class<Object>) StepIntersection.class);
    }

    public static TypeAdapter<StepIntersection> typeAdapter(Gson gson) {
        return new AutoValue_StepIntersection.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract List<Integer> bearings();

    @Nullable
    public abstract List<String> classes();

    @Nullable
    public abstract List<Boolean> entry();

    @Nullable
    public abstract Integer in();

    @Nullable
    public abstract List<IntersectionLanes> lanes();

    @NonNull
    public Point location() {
        return Point.fromLngLat(rawLocation()[0], rawLocation()[1]);
    }

    @Nullable
    public abstract Integer out();

    @NonNull
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    public abstract double[] rawLocation();

    public abstract Builder toBuilder();
}
