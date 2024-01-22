package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.AutoValue_BannerComponents;
import com.mappls.sdk.services.api.directions.models.a;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class BannerComponents extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract BannerComponents build();

        public abstract Builder text(@NonNull String str);

        public abstract Builder type(@NonNull String str);
    }

    public static Builder builder() {
        return new a.b();
    }

    public static BannerComponents fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (BannerComponents) gsonBuilder.create().fromJson(str, (Class<Object>) BannerComponents.class);
    }

    public static TypeAdapter<BannerComponents> typeAdapter(Gson gson) {
        return new AutoValue_BannerComponents.GsonTypeAdapter(gson);
    }

    @NonNull
    public abstract String text();

    public abstract Builder toBuilder();

    @NonNull
    public abstract String type();
}
