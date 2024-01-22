package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.mappls.sdk.services.api.directions.models.AutoValue_DirectionsError;
import com.mappls.sdk.services.api.directions.models.d;
import java.io.Serializable;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class DirectionsError implements Serializable {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract DirectionsError build();

        public abstract Builder code(String str);

        public abstract Builder message(String str);
    }

    public static Builder builder() {
        return new d.b();
    }

    public static TypeAdapter<DirectionsError> typeAdapter(Gson gson) {
        return new AutoValue_DirectionsError.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract String code();

    @Nullable
    public abstract String message();

    public abstract Builder toBuilder();
}
