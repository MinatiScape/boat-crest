package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.models.AutoValue_LegAnnotation;
import com.mappls.sdk.services.api.directions.models.i;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class LegAnnotation extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder baseDuration(@Nullable List<Double> list);

        public abstract LegAnnotation build();

        public abstract Builder congestion(@Nullable List<String> list);

        public abstract Builder distance(@Nullable List<Double> list);

        public abstract Builder duration(@Nullable List<Double> list);

        public abstract Builder maxspeed(@Nullable List<MaxSpeed> list);

        public abstract Builder nodes(@Nullable List<Long> list);

        public abstract Builder speed(@Nullable List<Double> list);

        public abstract Builder speedLimit(@Nullable List<Double> list);

        public abstract Builder tollRoad(@Nullable List<String> list);
    }

    public static Builder builder() {
        return new i.b();
    }

    public static LegAnnotation fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (LegAnnotation) gsonBuilder.create().fromJson(str, (Class<Object>) LegAnnotation.class);
    }

    public static TypeAdapter<LegAnnotation> typeAdapter(Gson gson) {
        return new AutoValue_LegAnnotation.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract List<Double> baseDuration();

    @Nullable
    public abstract List<String> congestion();

    @Nullable
    public abstract List<Double> distance();

    @Nullable
    public abstract List<Double> duration();

    @Nullable
    public abstract List<MaxSpeed> maxspeed();

    @Nullable
    public abstract List<Long> nodes();

    @Nullable
    public abstract List<Double> speed();

    @Nullable
    @SerializedName(DirectionsCriteria.ANNOTATION_SPEED_LIMIT)
    public abstract List<Double> speedLimit();

    public abstract Builder toBuilder();

    @Nullable
    @SerializedName(DirectionsCriteria.ANNOTATION_TOLL_ROAD)
    public abstract List<String> tollRoad();
}
