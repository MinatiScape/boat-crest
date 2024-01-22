package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.AutoValue_RouteLeg;
import com.mappls.sdk.services.api.directions.models.m;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class RouteLeg extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder annotation(@Nullable LegAnnotation legAnnotation);

        public abstract RouteLeg build();

        public abstract Builder distance(@Nullable Double d);

        public abstract Builder duration(@Nullable Double d);

        public abstract Builder steps(@Nullable List<LegStep> list);

        public abstract Builder summary(@Nullable String str);

        public abstract Builder weight(@Nullable Double d);
    }

    public static Builder builder() {
        return new m.b();
    }

    public static RouteLeg fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (RouteLeg) gsonBuilder.create().fromJson(str, (Class<Object>) RouteLeg.class);
    }

    public static TypeAdapter<RouteLeg> typeAdapter(Gson gson) {
        return new AutoValue_RouteLeg.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract LegAnnotation annotation();

    @Nullable
    public abstract Double distance();

    @Nullable
    public abstract Double duration();

    @Nullable
    public abstract List<LegStep> steps();

    @Nullable
    public abstract String summary();

    public abstract Builder toBuilder();

    @Nullable
    public abstract Double weight();
}
