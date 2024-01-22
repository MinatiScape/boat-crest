package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.AutoValue_VoiceInstructions;
import com.mappls.sdk.services.api.directions.models.q;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class VoiceInstructions extends DirectionsJsonObject {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder announcement(String str);

        public abstract VoiceInstructions build();

        public abstract Builder distanceAlongGeometry(Double d);

        public abstract Builder ssmlAnnouncement(String str);
    }

    public static Builder builder() {
        return new q.b();
    }

    public static VoiceInstructions fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return (VoiceInstructions) gsonBuilder.create().fromJson(str, (Class<Object>) VoiceInstructions.class);
    }

    public static TypeAdapter<VoiceInstructions> typeAdapter(Gson gson) {
        return new AutoValue_VoiceInstructions.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract String announcement();

    @Nullable
    public abstract Double distanceAlongGeometry();

    @Nullable
    public abstract String ssmlAnnouncement();

    public abstract Builder toBuilder();
}
