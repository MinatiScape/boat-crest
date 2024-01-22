package com.mappls.sdk.services.api.directions;

import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.WalkingOptions;
import java.io.IOException;
/* loaded from: classes11.dex */
public final class AutoValue_WalkingOptions extends a {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<WalkingOptions> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<Double> f13162a;
        public final Gson b;

        public GsonTypeAdapter(Gson gson) {
            this.b = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public WalkingOptions read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            WalkingOptions.Builder builder = WalkingOptions.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    char c = 65535;
                    switch (nextName.hashCode()) {
                        case -1570095453:
                            if (nextName.equals("alley_bias")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 411003393:
                            if (nextName.equals("walking_speed")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 782059218:
                            if (nextName.equals("walkway_bias")) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            TypeAdapter<Double> typeAdapter = this.f13162a;
                            if (typeAdapter == null) {
                                typeAdapter = this.b.getAdapter(Double.class);
                                this.f13162a = typeAdapter;
                            }
                            builder.alleyBias(typeAdapter.read(jsonReader));
                            continue;
                        case 1:
                            TypeAdapter<Double> typeAdapter2 = this.f13162a;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.b.getAdapter(Double.class);
                                this.f13162a = typeAdapter2;
                            }
                            builder.walkingSpeed(typeAdapter2.read(jsonReader));
                            continue;
                        case 2:
                            TypeAdapter<Double> typeAdapter3 = this.f13162a;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.b.getAdapter(Double.class);
                                this.f13162a = typeAdapter3;
                            }
                            builder.walkwayBias(typeAdapter3.read(jsonReader));
                            continue;
                        default:
                            jsonReader.skipValue();
                            continue;
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, WalkingOptions walkingOptions) throws IOException {
            if (walkingOptions == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("walking_speed");
            if (walkingOptions.walkingSpeed() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter = this.f13162a;
                if (typeAdapter == null) {
                    typeAdapter = this.b.getAdapter(Double.class);
                    this.f13162a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, walkingOptions.walkingSpeed());
            }
            jsonWriter.name("walkway_bias");
            if (walkingOptions.walkwayBias() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter2 = this.f13162a;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.b.getAdapter(Double.class);
                    this.f13162a = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, walkingOptions.walkwayBias());
            }
            jsonWriter.name("alley_bias");
            if (walkingOptions.alleyBias() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter3 = this.f13162a;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.b.getAdapter(Double.class);
                    this.f13162a = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, walkingOptions.alleyBias());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(WalkingOptions)";
        }
    }

    public AutoValue_WalkingOptions(@Nullable Double d, @Nullable Double d2, @Nullable Double d3) {
        super(d, d2, d3);
    }
}
