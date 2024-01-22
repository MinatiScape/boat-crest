package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.AutoValue_MaxSpeed;
import com.mappls.sdk.services.api.directions.models.k;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class MaxSpeed extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract MaxSpeed build();

        public abstract Builder none(@Nullable Boolean bool);

        public abstract Builder speed(@Nullable Integer num);

        public abstract Builder unit(@Nullable String str);

        public abstract Builder unknown(@Nullable Boolean bool);
    }

    public static Builder builder() {
        return new k.b();
    }

    public static MaxSpeed fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (MaxSpeed) gsonBuilder.create().fromJson(str, (Class<Object>) MaxSpeed.class);
    }

    public static TypeAdapter<MaxSpeed> typeAdapter(Gson gson) {
        return new AutoValue_MaxSpeed.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract Boolean none();

    @Nullable
    public abstract Integer speed();

    public abstract Builder toBuilder();

    @Nullable
    public abstract String unit();

    @Nullable
    public abstract Boolean unknown();
}
