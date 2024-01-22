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
import com.mappls.sdk.services.api.directions.models.AutoValue_BannerText;
import com.mappls.sdk.services.api.directions.models.c;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class BannerText extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract BannerText build();

        @Nullable
        public abstract Builder components(List<BannerComponents> list);

        public abstract Builder degrees(Double d);

        public abstract Builder drivingSide(@Nullable String str);

        public abstract Builder modifier(@Nullable String str);

        @Nullable
        public abstract Builder text(@NonNull String str);

        public abstract Builder type(@Nullable String str);
    }

    public static Builder builder() {
        return new c.b();
    }

    public static BannerText fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (BannerText) gsonBuilder.create().fromJson(str, (Class<Object>) BannerText.class);
    }

    public static TypeAdapter<BannerText> typeAdapter(Gson gson) {
        return new AutoValue_BannerText.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract List<BannerComponents> components();

    @Nullable
    public abstract Double degrees();

    @Nullable
    @SerializedName("driving_side")
    public abstract String drivingSide();

    @Nullable
    public abstract String modifier();

    @NonNull
    public abstract String text();

    public abstract Builder toBuilder();

    @Nullable
    public abstract String type();
}
