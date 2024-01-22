package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.IntersectionLanes;
import java.io.IOException;
import java.util.List;
/* loaded from: classes11.dex */
public final class AutoValue_IntersectionLanes extends h {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<IntersectionLanes> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<Boolean> f13176a;
        public volatile TypeAdapter<List<String>> b;
        public final Gson c;

        public GsonTypeAdapter(Gson gson) {
            this.c = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public IntersectionLanes read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            IntersectionLanes.Builder builder = IntersectionLanes.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if ("valid".equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter = this.f13176a;
                        if (typeAdapter == null) {
                            typeAdapter = this.c.getAdapter(Boolean.class);
                            this.f13176a = typeAdapter;
                        }
                        builder.valid(typeAdapter.read(jsonReader));
                    } else if ("indications".equals(nextName)) {
                        TypeAdapter<List<String>> typeAdapter2 = this.b;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.c.getAdapter(TypeToken.getParameterized(List.class, String.class));
                            this.b = typeAdapter2;
                        }
                        builder.indications(typeAdapter2.read(jsonReader));
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
        public void write(JsonWriter jsonWriter, IntersectionLanes intersectionLanes) throws IOException {
            if (intersectionLanes == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("valid");
            if (intersectionLanes.valid() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter = this.f13176a;
                if (typeAdapter == null) {
                    typeAdapter = this.c.getAdapter(Boolean.class);
                    this.f13176a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, intersectionLanes.valid());
            }
            jsonWriter.name("indications");
            if (intersectionLanes.indications() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<String>> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.c.getAdapter(TypeToken.getParameterized(List.class, String.class));
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, intersectionLanes.indications());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(IntersectionLanes)";
        }
    }

    public AutoValue_IntersectionLanes(@Nullable Boolean bool, @Nullable List<String> list) {
        super(bool, list);
    }
}
