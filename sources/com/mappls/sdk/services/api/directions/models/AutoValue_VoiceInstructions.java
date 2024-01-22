package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.VoiceInstructions;
import java.io.IOException;
/* loaded from: classes11.dex */
public final class AutoValue_VoiceInstructions extends q {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<VoiceInstructions> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<Double> f13185a;
        public volatile TypeAdapter<String> b;
        public final Gson c;

        public GsonTypeAdapter(Gson gson) {
            this.c = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public VoiceInstructions read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            VoiceInstructions.Builder builder = VoiceInstructions.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if ("distanceAlongGeometry".equals(nextName)) {
                        TypeAdapter<Double> typeAdapter = this.f13185a;
                        if (typeAdapter == null) {
                            typeAdapter = this.c.getAdapter(Double.class);
                            this.f13185a = typeAdapter;
                        }
                        builder.distanceAlongGeometry(typeAdapter.read(jsonReader));
                    } else if ("announcement".equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.b;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.c.getAdapter(String.class);
                            this.b = typeAdapter2;
                        }
                        builder.announcement(typeAdapter2.read(jsonReader));
                    } else if ("ssmlAnnouncement".equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.b;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.c.getAdapter(String.class);
                            this.b = typeAdapter3;
                        }
                        builder.ssmlAnnouncement(typeAdapter3.read(jsonReader));
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, VoiceInstructions voiceInstructions) throws IOException {
            if (voiceInstructions == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("distanceAlongGeometry");
            if (voiceInstructions.distanceAlongGeometry() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter = this.f13185a;
                if (typeAdapter == null) {
                    typeAdapter = this.c.getAdapter(Double.class);
                    this.f13185a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, voiceInstructions.distanceAlongGeometry());
            }
            jsonWriter.name("announcement");
            if (voiceInstructions.announcement() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.c.getAdapter(String.class);
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, voiceInstructions.announcement());
            }
            jsonWriter.name("ssmlAnnouncement");
            if (voiceInstructions.ssmlAnnouncement() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.b;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.c.getAdapter(String.class);
                    this.b = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, voiceInstructions.ssmlAnnouncement());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(VoiceInstructions)";
        }
    }

    public AutoValue_VoiceInstructions(@Nullable Double d, @Nullable String str, @Nullable String str2) {
        super(d, str, str2);
    }
}
