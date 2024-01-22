package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.AutoValue_RouteClasses;
import com.mappls.sdk.services.api.directions.models.l;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class RouteClasses extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract RouteClasses build();

        public abstract Builder ferry(@Nullable Integer num);

        public abstract Builder motorway(@Nullable Integer num);

        public abstract Builder restricted(@Nullable Integer num);

        public abstract Builder toll(@Nullable Integer num);

        public abstract Builder tunnel(@Nullable Integer num);
    }

    public static Builder builder() {
        return new l.a();
    }

    public static RouteClasses fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (RouteClasses) gsonBuilder.create().fromJson(str, (Class<Object>) RouteClasses.class);
    }

    public static TypeAdapter<RouteClasses> typeAdapter(Gson gson) {
        return new AutoValue_RouteClasses.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract Integer ferry();

    @Nullable
    public abstract Integer motorway();

    @Nullable
    public abstract Integer restricted();

    @Nullable
    public abstract Integer toll();

    @Nullable
    public abstract Integer tunnel();
}
