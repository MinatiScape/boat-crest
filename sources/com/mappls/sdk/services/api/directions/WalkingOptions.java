package com.mappls.sdk.services.api.directions;

import androidx.annotation.FloatRange;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.AutoValue_WalkingOptions;
import com.mappls.sdk.services.api.directions.a;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class WalkingOptions {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder alleyBias(@Nullable @FloatRange(from = -1.0d, to = 1.0d) Double d);

        public abstract WalkingOptions build();

        public abstract Builder walkingSpeed(@Nullable @FloatRange(from = 0.14d, to = 6.94d) Double d);

        public abstract Builder walkwayBias(@Nullable @FloatRange(from = -1.0d, to = 1.0d) Double d);
    }

    public static Builder builder() {
        return new a.C0668a();
    }

    public static WalkingOptions fromJson(String str) {
        return (WalkingOptions) new GsonBuilder().registerTypeAdapterFactory(WalkingOptionsAdapterFactory.create()).create().fromJson(str, (Class<Object>) WalkingOptions.class);
    }

    public static TypeAdapter<WalkingOptions> typeAdapter(Gson gson) {
        return new AutoValue_WalkingOptions.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("alley_bias")
    public abstract Double alleyBias();

    public final String toJson() {
        return new GsonBuilder().registerTypeAdapterFactory(WalkingOptionsAdapterFactory.create()).create().toJson(this, WalkingOptions.class);
    }

    @Nullable
    @SerializedName("walking_speed")
    public abstract Double walkingSpeed();

    @Nullable
    @SerializedName("walkway_bias")
    public abstract Double walkwayBias();
}
