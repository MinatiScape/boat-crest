package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.MaxSpeed;
import java.io.IOException;
/* loaded from: classes11.dex */
public final class AutoValue_MaxSpeed extends k {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<MaxSpeed> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<Integer> f13179a;
        public volatile TypeAdapter<String> b;
        public volatile TypeAdapter<Boolean> c;
        public final Gson d;

        public GsonTypeAdapter(Gson gson) {
            this.d = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public MaxSpeed read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            MaxSpeed.Builder builder = MaxSpeed.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if ("speed".equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter = this.f13179a;
                        if (typeAdapter == null) {
                            typeAdapter = this.d.getAdapter(Integer.class);
                            this.f13179a = typeAdapter;
                        }
                        builder.speed(typeAdapter.read(jsonReader));
                    } else if ("unit".equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.b;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.d.getAdapter(String.class);
                            this.b = typeAdapter2;
                        }
                        builder.unit(typeAdapter2.read(jsonReader));
                    } else if ("unknown".equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter3 = this.c;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.d.getAdapter(Boolean.class);
                            this.c = typeAdapter3;
                        }
                        builder.unknown(typeAdapter3.read(jsonReader));
                    } else if ("none".equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter4 = this.c;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.d.getAdapter(Boolean.class);
                            this.c = typeAdapter4;
                        }
                        builder.none(typeAdapter4.read(jsonReader));
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
        public void write(JsonWriter jsonWriter, MaxSpeed maxSpeed) throws IOException {
            if (maxSpeed == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("speed");
            if (maxSpeed.speed() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter = this.f13179a;
                if (typeAdapter == null) {
                    typeAdapter = this.d.getAdapter(Integer.class);
                    this.f13179a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, maxSpeed.speed());
            }
            jsonWriter.name("unit");
            if (maxSpeed.unit() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.d.getAdapter(String.class);
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, maxSpeed.unit());
            }
            jsonWriter.name("unknown");
            if (maxSpeed.unknown() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter3 = this.c;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.d.getAdapter(Boolean.class);
                    this.c = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, maxSpeed.unknown());
            }
            jsonWriter.name("none");
            if (maxSpeed.none() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter4 = this.c;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.d.getAdapter(Boolean.class);
                    this.c = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, maxSpeed.none());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(MaxSpeed)";
        }
    }

    public AutoValue_MaxSpeed(@Nullable Integer num, @Nullable String str, @Nullable Boolean bool, @Nullable Boolean bool2) {
        super(num, str, bool, bool2);
    }
}
