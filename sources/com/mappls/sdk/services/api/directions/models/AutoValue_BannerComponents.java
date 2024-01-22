package com.mappls.sdk.services.api.directions.models;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.BannerComponents;
import java.io.IOException;
/* loaded from: classes11.dex */
public final class AutoValue_BannerComponents extends a {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<BannerComponents> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<String> f13169a;
        public final Gson b;

        public GsonTypeAdapter(Gson gson) {
            this.b = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public BannerComponents read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            BannerComponents.Builder builder = BannerComponents.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if ("text".equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.f13169a;
                        if (typeAdapter == null) {
                            typeAdapter = this.b.getAdapter(String.class);
                            this.f13169a = typeAdapter;
                        }
                        builder.text(typeAdapter.read(jsonReader));
                    } else if ("type".equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.f13169a;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.b.getAdapter(String.class);
                            this.f13169a = typeAdapter2;
                        }
                        builder.type(typeAdapter2.read(jsonReader));
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
        public void write(JsonWriter jsonWriter, BannerComponents bannerComponents) throws IOException {
            if (bannerComponents == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("text");
            if (bannerComponents.text() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.f13169a;
                if (typeAdapter == null) {
                    typeAdapter = this.b.getAdapter(String.class);
                    this.f13169a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, bannerComponents.text());
            }
            jsonWriter.name("type");
            if (bannerComponents.type() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.f13169a;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.b.getAdapter(String.class);
                    this.f13169a = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, bannerComponents.type());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(BannerComponents)";
        }
    }

    public AutoValue_BannerComponents(String str, String str2) {
        super(str, str2);
    }
}
