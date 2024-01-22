package com.mappls.sdk.services.api.distance.models;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.distance.models.AutoValue_DistanceResponse;
import com.mappls.sdk.services.api.distance.models.C$AutoValue_DistanceResponse;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class DistanceResponse extends DistanceJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract DistanceResponse build();

        public abstract Builder responseCode(long j);

        @SerializedName("results")
        public abstract Builder results(@Nullable DistanceResults distanceResults);

        public abstract Builder version(@Nullable String str);
    }

    public static Builder builder() {
        return new C$AutoValue_DistanceResponse.b();
    }

    public static TypeAdapter<DistanceResponse> typeAdapter(Gson gson) {
        return new AutoValue_DistanceResponse.GsonTypeAdapter(gson);
    }

    public abstract long responseCode();

    @Nullable
    @SerializedName("results")
    public abstract DistanceResults results();

    public abstract Builder toBuilder();

    @Nullable
    public abstract String version();
}
