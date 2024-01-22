package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.AutoValue_IntersectionLanes;
import com.mappls.sdk.services.api.directions.models.h;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class IntersectionLanes extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract IntersectionLanes build();

        public abstract Builder indications(@Nullable List<String> list);

        public abstract Builder valid(@Nullable Boolean bool);
    }

    public static Builder builder() {
        return new h.b();
    }

    public static IntersectionLanes fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (IntersectionLanes) gsonBuilder.create().fromJson(str, (Class<Object>) IntersectionLanes.class);
    }

    public static TypeAdapter<IntersectionLanes> typeAdapter(Gson gson) {
        return new AutoValue_IntersectionLanes.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract List<String> indications();

    public abstract Builder toBuilder();

    @Nullable
    public abstract Boolean valid();
}
