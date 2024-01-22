package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.AutoValue_LegStep;
import com.mappls.sdk.services.api.directions.models.j;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class LegStep extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder bannerInstructions(@NonNull List<BannerInstructions> list);

        public abstract LegStep build();

        public abstract Builder destinations(@Nullable String str);

        public abstract Builder distance(double d);

        public abstract Builder drivingSide(@Nullable String str);

        public abstract Builder duration(double d);

        public abstract Builder geometry(@Nullable String str);

        public abstract Builder intersections(@NonNull List<StepIntersection> list);

        public abstract Builder maneuver(@NonNull StepManeuver stepManeuver);

        public abstract Builder mode(@NonNull String str);

        public abstract Builder name(@Nullable String str);

        public abstract Builder ref(@Nullable String str);

        public abstract Builder rotaryName(@Nullable String str);

        public abstract Builder weight(double d);
    }

    public static Builder builder() {
        return new j.b();
    }

    public static LegStep fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (LegStep) gsonBuilder.create().fromJson(str, (Class<Object>) LegStep.class);
    }

    public static TypeAdapter<LegStep> typeAdapter(Gson gson) {
        return new AutoValue_LegStep.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("banner_instructions")
    public abstract List<BannerInstructions> bannerInstructions();

    @Nullable
    public abstract String destinations();

    public abstract double distance();

    @Nullable
    @SerializedName("driving_side")
    public abstract String drivingSide();

    public abstract double duration();

    @Nullable
    public abstract String geometry();

    @Nullable
    public abstract List<StepIntersection> intersections();

    @NonNull
    public abstract StepManeuver maneuver();

    @NonNull
    public abstract String mode();

    @Nullable
    public abstract String name();

    @Nullable
    public abstract String ref();

    @Nullable
    @SerializedName("rotary_name")
    public abstract String rotaryName();

    public abstract Builder toBuilder();

    public abstract double weight();
}
