package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.FloatRange;
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
import com.mappls.sdk.services.api.directions.models.AutoValue_StepManeuver;
import com.mappls.sdk.services.api.directions.models.p;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class StepManeuver extends DirectionsJsonObject {
    public static final String ARRIVE = "arrive";
    public static final String CONTINUE = "continue";
    public static final String DEPART = "depart";
    public static final String END_OF_ROAD = "end of road";
    public static final String EXIT_ROTARY = "exit rotary";
    public static final String EXIT_ROUNDABOUT = "exit roundabout";
    public static final String FORK = "fork";
    public static final String MERGE = "merge";
    public static final String NEW_NAME = "new name";
    public static final String NOTIFICATION = "notification";
    public static final String OFF_RAMP = "off ramp";
    public static final String ON_RAMP = "on ramp";
    public static final String ROTARY = "rotary";
    public static final String ROUNDABOUT = "roundabout";
    public static final String ROUNDABOUT_TURN = "roundabout turn";
    public static final String TURN = "turn";

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder bearingAfter(@Nullable @FloatRange(from = 0.0d, to = 360.0d) Double d);

        public abstract Builder bearingBefore(@Nullable @FloatRange(from = 0.0d, to = 360.0d) Double d);

        public abstract StepManeuver build();

        public abstract Builder degree(@Nullable Double d);

        public abstract Builder exit(@Nullable Integer num);

        public abstract Builder instruction(@Nullable String str);

        public abstract Builder maneuverId(@Nullable Integer num);

        public abstract Builder modifier(@Nullable String str);

        public abstract Builder rawLocation(@NonNull double[] dArr);

        public abstract Builder shortInstruction(@Nullable String str);

        public abstract Builder type(@Nullable String str);
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface StepManeuverType {
    }

    public static Builder builder() {
        return new p.b();
    }

    public static StepManeuver fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (StepManeuver) gsonBuilder.create().fromJson(str, (Class<Object>) StepManeuver.class);
    }

    public static TypeAdapter<StepManeuver> typeAdapter(Gson gson) {
        return new AutoValue_StepManeuver.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("bearing_after")
    public abstract Double bearingAfter();

    @Nullable
    @SerializedName("bearing_before")
    public abstract Double bearingBefore();

    @Nullable
    @SerializedName("degree")
    public abstract Double degree();

    @Nullable
    public abstract Integer exit();

    @Nullable
    public abstract String instruction();

    @NonNull
    public Point location() {
        return Point.fromLngLat(rawLocation()[0], rawLocation()[1]);
    }

    @Nullable
    @SerializedName("maneuver_id")
    public abstract Integer maneuverId();

    @Nullable
    public abstract String modifier();

    @NonNull
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    public abstract double[] rawLocation();

    @Nullable
    @SerializedName("short_instruction")
    public abstract String shortInstruction();

    public abstract Builder toBuilder();

    @Nullable
    public abstract String type();
}
