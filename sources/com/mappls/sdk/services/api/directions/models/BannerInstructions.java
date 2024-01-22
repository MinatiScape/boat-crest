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
import com.mappls.sdk.services.api.directions.models.AutoValue_BannerInstructions;
import com.mappls.sdk.services.api.directions.models.b;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class BannerInstructions extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract BannerInstructions build();

        public abstract Builder distanceAlongGeometry(double d);

        public abstract Builder primary(@NonNull BannerText bannerText);

        public abstract Builder secondary(@Nullable Integer num);

        public abstract Builder sub(@Nullable BannerText bannerText);
    }

    public static Builder builder() {
        return new b.C0671b();
    }

    public static BannerInstructions fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (BannerInstructions) gsonBuilder.create().fromJson(str, (Class<Object>) BannerInstructions.class);
    }

    public static TypeAdapter<BannerInstructions> typeAdapter(Gson gson) {
        return new AutoValue_BannerInstructions.GsonTypeAdapter(gson);
    }

    @SerializedName("distance_along_geometry")
    public abstract double distanceAlongGeometry();

    @NonNull
    public abstract BannerText primary();

    @Nullable
    public abstract Integer secondary();

    @Nullable
    public abstract BannerText sub();

    public abstract Builder toBuilder();
}
