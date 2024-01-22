package com.mappls.sdk.services.api.directionsrefresh.models;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.DirectionsJsonObject;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directionsrefresh.models.AutoValue_DirectionsRefreshResponse;
import com.mappls.sdk.services.api.directionsrefresh.models.a;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class DirectionsRefreshResponse extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract DirectionsRefreshResponse build();

        public abstract Builder code(String str);

        public abstract Builder message(String str);

        public abstract Builder route(DirectionsRoute directionsRoute);
    }

    @NonNull
    public static Builder builder() {
        return new a.C0673a();
    }

    public static DirectionsRefreshResponse fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsRefreshAdapterFactory.create()).registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (DirectionsRefreshResponse) gsonBuilder.create().fromJson(str, (Class<Object>) DirectionsRefreshResponse.class);
    }

    public static TypeAdapter<DirectionsRefreshResponse> typeAdapter(Gson gson) {
        return new AutoValue_DirectionsRefreshResponse.GsonTypeAdapter(gson);
    }

    @NonNull
    public abstract String code();

    @Nullable
    public abstract String message();

    @Nullable
    public abstract DirectionsRoute route();
}
