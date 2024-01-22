package com.mappls.sdk.services.api.tripoptimisation.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse;
import java.io.IOException;
import java.util.List;
/* loaded from: classes8.dex */
public final class AutoValue_TripOptimisationResponse extends a {

    /* loaded from: classes8.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<TripOptimisationResponse> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<String> f13296a;
        public volatile TypeAdapter<List<DirectionsRoute>> b;
        public volatile TypeAdapter<List<TripsWaypoint>> c;
        public final Gson d;

        public GsonTypeAdapter(Gson gson) {
            this.d = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public TripOptimisationResponse read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            TripOptimisationResponse.Builder builder = TripOptimisationResponse.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if ("code".equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.f13296a;
                        if (typeAdapter == null) {
                            typeAdapter = this.d.getAdapter(String.class);
                            this.f13296a = typeAdapter;
                        }
                        builder.code(typeAdapter.read(jsonReader));
                    } else if ("trips".equals(nextName)) {
                        TypeAdapter<List<DirectionsRoute>> typeAdapter2 = this.b;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.d.getAdapter(TypeToken.getParameterized(List.class, DirectionsRoute.class));
                            this.b = typeAdapter2;
                        }
                        builder.trips(typeAdapter2.read(jsonReader));
                    } else if ("waypoints".equals(nextName)) {
                        TypeAdapter<List<TripsWaypoint>> typeAdapter3 = this.c;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.d.getAdapter(TypeToken.getParameterized(List.class, TripsWaypoint.class));
                            this.c = typeAdapter3;
                        }
                        builder.waypoints(typeAdapter3.read(jsonReader));
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
        public void write(JsonWriter jsonWriter, TripOptimisationResponse tripOptimisationResponse) throws IOException {
            if (tripOptimisationResponse == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("code");
            if (tripOptimisationResponse.code() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.f13296a;
                if (typeAdapter == null) {
                    typeAdapter = this.d.getAdapter(String.class);
                    this.f13296a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, tripOptimisationResponse.code());
            }
            jsonWriter.name("trips");
            if (tripOptimisationResponse.trips() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<DirectionsRoute>> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.d.getAdapter(TypeToken.getParameterized(List.class, DirectionsRoute.class));
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, tripOptimisationResponse.trips());
            }
            jsonWriter.name("waypoints");
            if (tripOptimisationResponse.waypoints() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<TripsWaypoint>> typeAdapter3 = this.c;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.d.getAdapter(TypeToken.getParameterized(List.class, TripsWaypoint.class));
                    this.c = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, tripOptimisationResponse.waypoints());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(TripOptimisationResponse)";
        }
    }

    public AutoValue_TripOptimisationResponse(String str, List<DirectionsRoute> list, List<TripsWaypoint> list2) {
        super(str, list, list2);
    }
}
