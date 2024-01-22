package com.mappls.sdk.services.api.directionsrefresh.models;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directionsrefresh.models.DirectionsRefreshResponse;
import java.io.IOException;
/* loaded from: classes11.dex */
public final class AutoValue_DirectionsRefreshResponse extends a {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DirectionsRefreshResponse> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<String> f13207a;
        public volatile TypeAdapter<DirectionsRoute> b;
        public final Gson c;

        public GsonTypeAdapter(Gson gson) {
            this.c = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public DirectionsRefreshResponse read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            DirectionsRefreshResponse.Builder builder = DirectionsRefreshResponse.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if ("code".equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.f13207a;
                        if (typeAdapter == null) {
                            typeAdapter = this.c.getAdapter(String.class);
                            this.f13207a = typeAdapter;
                        }
                        builder.code(typeAdapter.read(jsonReader));
                    } else if (Constants.KEY_MESSAGE.equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.f13207a;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.c.getAdapter(String.class);
                            this.f13207a = typeAdapter2;
                        }
                        builder.message(typeAdapter2.read(jsonReader));
                    } else if ("route".equals(nextName)) {
                        TypeAdapter<DirectionsRoute> typeAdapter3 = this.b;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.c.getAdapter(DirectionsRoute.class);
                            this.b = typeAdapter3;
                        }
                        builder.route(typeAdapter3.read(jsonReader));
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
        public void write(JsonWriter jsonWriter, DirectionsRefreshResponse directionsRefreshResponse) throws IOException {
            if (directionsRefreshResponse == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("code");
            if (directionsRefreshResponse.code() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.f13207a;
                if (typeAdapter == null) {
                    typeAdapter = this.c.getAdapter(String.class);
                    this.f13207a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, directionsRefreshResponse.code());
            }
            jsonWriter.name(Constants.KEY_MESSAGE);
            if (directionsRefreshResponse.message() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.f13207a;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.c.getAdapter(String.class);
                    this.f13207a = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, directionsRefreshResponse.message());
            }
            jsonWriter.name("route");
            if (directionsRefreshResponse.route() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<DirectionsRoute> typeAdapter3 = this.b;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.c.getAdapter(DirectionsRoute.class);
                    this.b = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, directionsRefreshResponse.route());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(DirectionsRefreshResponse)";
        }
    }

    public AutoValue_DirectionsRefreshResponse(String str, @Nullable String str2, @Nullable DirectionsRoute directionsRoute) {
        super(str, str2, directionsRoute);
    }
}
