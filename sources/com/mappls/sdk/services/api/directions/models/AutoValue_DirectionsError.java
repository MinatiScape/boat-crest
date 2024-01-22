package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.DirectionsError;
import java.io.IOException;
/* loaded from: classes11.dex */
public final class AutoValue_DirectionsError extends d {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DirectionsError> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<String> f13172a;
        public final Gson b;

        public GsonTypeAdapter(Gson gson) {
            this.b = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public DirectionsError read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            DirectionsError.Builder builder = DirectionsError.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if ("code".equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.f13172a;
                        if (typeAdapter == null) {
                            typeAdapter = this.b.getAdapter(String.class);
                            this.f13172a = typeAdapter;
                        }
                        builder.code(typeAdapter.read(jsonReader));
                    } else if (Constants.KEY_MESSAGE.equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.f13172a;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.b.getAdapter(String.class);
                            this.f13172a = typeAdapter2;
                        }
                        builder.message(typeAdapter2.read(jsonReader));
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
        public void write(JsonWriter jsonWriter, DirectionsError directionsError) throws IOException {
            if (directionsError == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("code");
            if (directionsError.code() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.f13172a;
                if (typeAdapter == null) {
                    typeAdapter = this.b.getAdapter(String.class);
                    this.f13172a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, directionsError.code());
            }
            jsonWriter.name(Constants.KEY_MESSAGE);
            if (directionsError.message() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.f13172a;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.b.getAdapter(String.class);
                    this.f13172a = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, directionsError.message());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(DirectionsError)";
        }
    }

    public AutoValue_DirectionsError(@Nullable String str, @Nullable String str2) {
        super(str, str2);
    }
}
