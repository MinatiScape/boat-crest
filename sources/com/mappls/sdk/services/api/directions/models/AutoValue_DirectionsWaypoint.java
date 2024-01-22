package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.DirectionsWaypoint;
import java.io.IOException;
/* loaded from: classes11.dex */
public final class AutoValue_DirectionsWaypoint extends g {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DirectionsWaypoint> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<String> f13175a;
        public volatile TypeAdapter<double[]> b;
        public volatile TypeAdapter<Double> c;
        public final Gson d;

        public GsonTypeAdapter(Gson gson) {
            this.d = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public DirectionsWaypoint read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            DirectionsWaypoint.Builder builder = DirectionsWaypoint.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (!nextName.equals(FirebaseAnalytics.Param.LOCATION)) {
                        if (AppMeasurementSdk.ConditionalUserProperty.NAME.equals(nextName)) {
                            TypeAdapter<String> typeAdapter = this.f13175a;
                            if (typeAdapter == null) {
                                typeAdapter = this.d.getAdapter(String.class);
                                this.f13175a = typeAdapter;
                            }
                            builder.name(typeAdapter.read(jsonReader));
                        } else if ("distance".equals(nextName)) {
                            TypeAdapter<Double> typeAdapter2 = this.c;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.d.getAdapter(Double.class);
                                this.c = typeAdapter2;
                            }
                            builder.distance(typeAdapter2.read(jsonReader));
                        } else if ("hint".equals(nextName)) {
                            TypeAdapter<String> typeAdapter3 = this.f13175a;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.d.getAdapter(String.class);
                                this.f13175a = typeAdapter3;
                            }
                            builder.hint(typeAdapter3.read(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    } else {
                        TypeAdapter<double[]> typeAdapter4 = this.b;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.d.getAdapter(double[].class);
                            this.b = typeAdapter4;
                        }
                        builder.rawLocation(typeAdapter4.read(jsonReader));
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, DirectionsWaypoint directionsWaypoint) throws IOException {
            if (directionsWaypoint == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(AppMeasurementSdk.ConditionalUserProperty.NAME);
            if (directionsWaypoint.name() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.f13175a;
                if (typeAdapter == null) {
                    typeAdapter = this.d.getAdapter(String.class);
                    this.f13175a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, directionsWaypoint.name());
            }
            jsonWriter.name(FirebaseAnalytics.Param.LOCATION);
            if (directionsWaypoint.rawLocation() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<double[]> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.d.getAdapter(double[].class);
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, directionsWaypoint.rawLocation());
            }
            jsonWriter.name("distance");
            if (directionsWaypoint.distance() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter3 = this.c;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.d.getAdapter(Double.class);
                    this.c = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, directionsWaypoint.distance());
            }
            jsonWriter.name("hint");
            if (directionsWaypoint.hint() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.f13175a;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.d.getAdapter(String.class);
                    this.f13175a = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, directionsWaypoint.hint());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(DirectionsWaypoint)";
        }
    }

    public AutoValue_DirectionsWaypoint(@Nullable String str, @Nullable double[] dArr, Double d, String str2) {
        super(str, dArr, d, str2);
    }
}
