package com.mappls.sdk.services.api.distance.models;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.models.DirectionsWaypoint;
import com.mappls.sdk.services.api.distance.models.AutoValue_DistanceResults;
import com.mappls.sdk.services.api.distance.models.C$AutoValue_DistanceResults;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class DistanceResults extends DistanceJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract DistanceResults build();

        public abstract Builder code(@NonNull String str);

        public abstract Builder destinations(@Nullable List<DirectionsWaypoint> list);

        @SerializedName("distances")
        public abstract Builder distances(@Nullable List<Double[]> list);

        @SerializedName("durations")
        public abstract Builder durations(@Nullable List<Double[]> list);

        @SerializedName("fallback_speed_cells")
        public abstract Builder fallbackSpeedCells(@Nullable List<Double[]> list);

        public abstract Builder sources(@Nullable List<DirectionsWaypoint> list);
    }

    public static Builder builder() {
        return new C$AutoValue_DistanceResults.b();
    }

    public static TypeAdapter<DistanceResults> typeAdapter(Gson gson) {
        return new AutoValue_DistanceResults.GsonTypeAdapter(gson);
    }

    @NonNull
    public abstract String code();

    @Nullable
    public abstract List<DirectionsWaypoint> destinations();

    @Nullable
    @SerializedName("distances")
    public abstract List<Double[]> distances();

    @Nullable
    @SerializedName("durations")
    public abstract List<Double[]> durations();

    @Nullable
    @SerializedName("fallback_speed_cells")
    public abstract List<Double[]> fallbackSpeedCells();

    @Nullable
    public abstract List<DirectionsWaypoint> sources();

    public abstract Builder toBuilder();
}
