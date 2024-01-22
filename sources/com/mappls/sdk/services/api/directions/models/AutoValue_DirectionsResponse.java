package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import java.io.IOException;
import java.util.List;
/* loaded from: classes11.dex */
public final class AutoValue_DirectionsResponse extends e {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DirectionsResponse> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<String> f13173a;
        public volatile TypeAdapter<List<DirectionsWaypoint>> b;
        public volatile TypeAdapter<List<DirectionsRoute>> c;
        public final Gson d;

        public GsonTypeAdapter(Gson gson) {
            this.d = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public DirectionsResponse read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            DirectionsResponse.Builder builder = DirectionsResponse.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (nextName.equals("sessionId")) {
                        TypeAdapter<String> typeAdapter = this.f13173a;
                        if (typeAdapter == null) {
                            typeAdapter = this.d.getAdapter(String.class);
                            this.f13173a = typeAdapter;
                        }
                        builder.sessionId(typeAdapter.read(jsonReader));
                    } else if (!nextName.equals("routeId")) {
                        if ("code".equals(nextName)) {
                            TypeAdapter<String> typeAdapter2 = this.f13173a;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.d.getAdapter(String.class);
                                this.f13173a = typeAdapter2;
                            }
                            builder.code(typeAdapter2.read(jsonReader));
                        } else if (Constants.KEY_MESSAGE.equals(nextName)) {
                            TypeAdapter<String> typeAdapter3 = this.f13173a;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.d.getAdapter(String.class);
                                this.f13173a = typeAdapter3;
                            }
                            builder.message(typeAdapter3.read(jsonReader));
                        } else if ("waypoints".equals(nextName)) {
                            TypeAdapter<List<DirectionsWaypoint>> typeAdapter4 = this.b;
                            if (typeAdapter4 == null) {
                                typeAdapter4 = this.d.getAdapter(TypeToken.getParameterized(List.class, DirectionsWaypoint.class));
                                this.b = typeAdapter4;
                            }
                            builder.waypoints(typeAdapter4.read(jsonReader));
                        } else if ("routes".equals(nextName)) {
                            TypeAdapter<List<DirectionsRoute>> typeAdapter5 = this.c;
                            if (typeAdapter5 == null) {
                                typeAdapter5 = this.d.getAdapter(TypeToken.getParameterized(List.class, DirectionsRoute.class));
                                this.c = typeAdapter5;
                            }
                            builder.routes(typeAdapter5.read(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    } else {
                        TypeAdapter<String> typeAdapter6 = this.f13173a;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.d.getAdapter(String.class);
                            this.f13173a = typeAdapter6;
                        }
                        builder.uuid(typeAdapter6.read(jsonReader));
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, DirectionsResponse directionsResponse) throws IOException {
            if (directionsResponse == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("code");
            if (directionsResponse.code() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.f13173a;
                if (typeAdapter == null) {
                    typeAdapter = this.d.getAdapter(String.class);
                    this.f13173a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, directionsResponse.code());
            }
            jsonWriter.name(Constants.KEY_MESSAGE);
            if (directionsResponse.message() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.f13173a;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.d.getAdapter(String.class);
                    this.f13173a = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, directionsResponse.message());
            }
            jsonWriter.name("waypoints");
            if (directionsResponse.waypoints() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<DirectionsWaypoint>> typeAdapter3 = this.b;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.d.getAdapter(TypeToken.getParameterized(List.class, DirectionsWaypoint.class));
                    this.b = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, directionsResponse.waypoints());
            }
            jsonWriter.name("routes");
            if (directionsResponse.routes() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<DirectionsRoute>> typeAdapter4 = this.c;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.d.getAdapter(TypeToken.getParameterized(List.class, DirectionsRoute.class));
                    this.c = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, directionsResponse.routes());
            }
            jsonWriter.name("routeId");
            if (directionsResponse.uuid() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.f13173a;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.d.getAdapter(String.class);
                    this.f13173a = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, directionsResponse.uuid());
            }
            jsonWriter.name("sessionId");
            if (directionsResponse.sessionId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter6 = this.f13173a;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.d.getAdapter(String.class);
                    this.f13173a = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, directionsResponse.sessionId());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(DirectionsResponse)";
        }
    }

    public AutoValue_DirectionsResponse(String str, @Nullable String str2, @Nullable List<DirectionsWaypoint> list, List<DirectionsRoute> list2, @Nullable String str3, @Nullable String str4) {
        super(str, str2, list, list2, str3, str4);
    }
}
