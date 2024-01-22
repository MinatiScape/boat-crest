package com.mappls.sdk.services.api.tripoptimisation.model;

import androidx.annotation.Nullable;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.tripoptimisation.model.TripsWaypoint;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class AutoValue_TripsWaypoint extends b {

    /* loaded from: classes8.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<TripsWaypoint> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<String> f13297a;
        public volatile TypeAdapter<double[]> b;
        public volatile TypeAdapter<Double> c;
        public volatile TypeAdapter<Integer> d;
        public final Gson e;

        public GsonTypeAdapter(Gson gson) {
            this.e = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public TripsWaypoint read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            TripsWaypoint.Builder builder = TripsWaypoint.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    char c = 65535;
                    switch (nextName.hashCode()) {
                        case -294735295:
                            if (nextName.equals("trips_index")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 601411348:
                            if (nextName.equals("waypoint_index")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 1901043637:
                            if (nextName.equals(FirebaseAnalytics.Param.LOCATION)) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            TypeAdapter<Integer> typeAdapter = this.d;
                            if (typeAdapter == null) {
                                typeAdapter = this.e.getAdapter(Integer.class);
                                this.d = typeAdapter;
                            }
                            builder.tripsIndex(typeAdapter.read(jsonReader));
                            continue;
                        case 1:
                            TypeAdapter<Integer> typeAdapter2 = this.d;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.e.getAdapter(Integer.class);
                                this.d = typeAdapter2;
                            }
                            builder.waypointIndex(typeAdapter2.read(jsonReader));
                            continue;
                        case 2:
                            TypeAdapter<double[]> typeAdapter3 = this.b;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.e.getAdapter(double[].class);
                                this.b = typeAdapter3;
                            }
                            builder.rawLocation(typeAdapter3.read(jsonReader));
                            continue;
                        default:
                            if (AppMeasurementSdk.ConditionalUserProperty.NAME.equals(nextName)) {
                                TypeAdapter<String> typeAdapter4 = this.f13297a;
                                if (typeAdapter4 == null) {
                                    typeAdapter4 = this.e.getAdapter(String.class);
                                    this.f13297a = typeAdapter4;
                                }
                                builder.name(typeAdapter4.read(jsonReader));
                                break;
                            } else if ("distance".equals(nextName)) {
                                TypeAdapter<Double> typeAdapter5 = this.c;
                                if (typeAdapter5 == null) {
                                    typeAdapter5 = this.e.getAdapter(Double.class);
                                    this.c = typeAdapter5;
                                }
                                builder.distance(typeAdapter5.read(jsonReader));
                                break;
                            } else if ("hint".equals(nextName)) {
                                TypeAdapter<String> typeAdapter6 = this.f13297a;
                                if (typeAdapter6 == null) {
                                    typeAdapter6 = this.e.getAdapter(String.class);
                                    this.f13297a = typeAdapter6;
                                }
                                builder.hint(typeAdapter6.read(jsonReader));
                                break;
                            } else {
                                jsonReader.skipValue();
                                continue;
                            }
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, TripsWaypoint tripsWaypoint) throws IOException {
            if (tripsWaypoint == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(AppMeasurementSdk.ConditionalUserProperty.NAME);
            if (tripsWaypoint.name() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.f13297a;
                if (typeAdapter == null) {
                    typeAdapter = this.e.getAdapter(String.class);
                    this.f13297a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, tripsWaypoint.name());
            }
            jsonWriter.name(FirebaseAnalytics.Param.LOCATION);
            if (tripsWaypoint.rawLocation() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<double[]> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.e.getAdapter(double[].class);
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, tripsWaypoint.rawLocation());
            }
            jsonWriter.name("distance");
            if (tripsWaypoint.distance() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter3 = this.c;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.e.getAdapter(Double.class);
                    this.c = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, tripsWaypoint.distance());
            }
            jsonWriter.name("hint");
            if (tripsWaypoint.hint() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.f13297a;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.e.getAdapter(String.class);
                    this.f13297a = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, tripsWaypoint.hint());
            }
            jsonWriter.name("waypoint_index");
            if (tripsWaypoint.waypointIndex() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter5 = this.d;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.e.getAdapter(Integer.class);
                    this.d = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, tripsWaypoint.waypointIndex());
            }
            jsonWriter.name("trips_index");
            if (tripsWaypoint.tripsIndex() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter6 = this.d;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.e.getAdapter(Integer.class);
                    this.d = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, tripsWaypoint.tripsIndex());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(TripsWaypoint)";
        }
    }

    public AutoValue_TripsWaypoint(@Nullable String str, @Nullable double[] dArr, Double d, String str2, Integer num, Integer num2) {
        super(str, dArr, d, str2, num, num2);
    }
}
