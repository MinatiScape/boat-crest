package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.LegStep;
import java.io.IOException;
import java.util.List;
/* loaded from: classes11.dex */
public final class AutoValue_LegStep extends j {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<LegStep> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<Double> f13178a;
        public volatile TypeAdapter<String> b;
        public volatile TypeAdapter<StepManeuver> c;
        public volatile TypeAdapter<List<BannerInstructions>> d;
        public volatile TypeAdapter<List<StepIntersection>> e;
        public final Gson f;

        public GsonTypeAdapter(Gson gson) {
            this.f = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public LegStep read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            LegStep.Builder builder = LegStep.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    char c = 65535;
                    switch (nextName.hashCode()) {
                        case -2075945000:
                            if (nextName.equals("banner_instructions")) {
                                c = 0;
                                break;
                            }
                            break;
                        case -463249713:
                            if (nextName.equals("driving_side")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 661843161:
                            if (nextName.equals("rotary_name")) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            TypeAdapter<List<BannerInstructions>> typeAdapter = this.d;
                            if (typeAdapter == null) {
                                typeAdapter = this.f.getAdapter(TypeToken.getParameterized(List.class, BannerInstructions.class));
                                this.d = typeAdapter;
                            }
                            builder.bannerInstructions(typeAdapter.read(jsonReader));
                            continue;
                        case 1:
                            TypeAdapter<String> typeAdapter2 = this.b;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.f.getAdapter(String.class);
                                this.b = typeAdapter2;
                            }
                            builder.drivingSide(typeAdapter2.read(jsonReader));
                            continue;
                        case 2:
                            TypeAdapter<String> typeAdapter3 = this.b;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.f.getAdapter(String.class);
                                this.b = typeAdapter3;
                            }
                            builder.rotaryName(typeAdapter3.read(jsonReader));
                            continue;
                        default:
                            if ("distance".equals(nextName)) {
                                TypeAdapter<Double> typeAdapter4 = this.f13178a;
                                if (typeAdapter4 == null) {
                                    typeAdapter4 = this.f.getAdapter(Double.class);
                                    this.f13178a = typeAdapter4;
                                }
                                builder.distance(typeAdapter4.read(jsonReader).doubleValue());
                                break;
                            } else if ("duration".equals(nextName)) {
                                TypeAdapter<Double> typeAdapter5 = this.f13178a;
                                if (typeAdapter5 == null) {
                                    typeAdapter5 = this.f.getAdapter(Double.class);
                                    this.f13178a = typeAdapter5;
                                }
                                builder.duration(typeAdapter5.read(jsonReader).doubleValue());
                                break;
                            } else if ("geometry".equals(nextName)) {
                                TypeAdapter<String> typeAdapter6 = this.b;
                                if (typeAdapter6 == null) {
                                    typeAdapter6 = this.f.getAdapter(String.class);
                                    this.b = typeAdapter6;
                                }
                                builder.geometry(typeAdapter6.read(jsonReader));
                                break;
                            } else if (AppMeasurementSdk.ConditionalUserProperty.NAME.equals(nextName)) {
                                TypeAdapter<String> typeAdapter7 = this.b;
                                if (typeAdapter7 == null) {
                                    typeAdapter7 = this.f.getAdapter(String.class);
                                    this.b = typeAdapter7;
                                }
                                builder.name(typeAdapter7.read(jsonReader));
                                break;
                            } else if ("destinations".equals(nextName)) {
                                TypeAdapter<String> typeAdapter8 = this.b;
                                if (typeAdapter8 == null) {
                                    typeAdapter8 = this.f.getAdapter(String.class);
                                    this.b = typeAdapter8;
                                }
                                builder.destinations(typeAdapter8.read(jsonReader));
                                break;
                            } else if ("mode".equals(nextName)) {
                                TypeAdapter<String> typeAdapter9 = this.b;
                                if (typeAdapter9 == null) {
                                    typeAdapter9 = this.f.getAdapter(String.class);
                                    this.b = typeAdapter9;
                                }
                                builder.mode(typeAdapter9.read(jsonReader));
                                break;
                            } else if ("maneuver".equals(nextName)) {
                                TypeAdapter<StepManeuver> typeAdapter10 = this.c;
                                if (typeAdapter10 == null) {
                                    typeAdapter10 = this.f.getAdapter(StepManeuver.class);
                                    this.c = typeAdapter10;
                                }
                                builder.maneuver(typeAdapter10.read(jsonReader));
                                break;
                            } else if ("weight".equals(nextName)) {
                                TypeAdapter<Double> typeAdapter11 = this.f13178a;
                                if (typeAdapter11 == null) {
                                    typeAdapter11 = this.f.getAdapter(Double.class);
                                    this.f13178a = typeAdapter11;
                                }
                                builder.weight(typeAdapter11.read(jsonReader).doubleValue());
                                break;
                            } else if ("intersections".equals(nextName)) {
                                TypeAdapter<List<StepIntersection>> typeAdapter12 = this.e;
                                if (typeAdapter12 == null) {
                                    typeAdapter12 = this.f.getAdapter(TypeToken.getParameterized(List.class, StepIntersection.class));
                                    this.e = typeAdapter12;
                                }
                                builder.intersections(typeAdapter12.read(jsonReader));
                                break;
                            } else if ("ref".equals(nextName)) {
                                TypeAdapter<String> typeAdapter13 = this.b;
                                if (typeAdapter13 == null) {
                                    typeAdapter13 = this.f.getAdapter(String.class);
                                    this.b = typeAdapter13;
                                }
                                builder.ref(typeAdapter13.read(jsonReader));
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
        public void write(JsonWriter jsonWriter, LegStep legStep) throws IOException {
            if (legStep == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("distance");
            TypeAdapter<Double> typeAdapter = this.f13178a;
            if (typeAdapter == null) {
                typeAdapter = this.f.getAdapter(Double.class);
                this.f13178a = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Double.valueOf(legStep.distance()));
            jsonWriter.name("duration");
            TypeAdapter<Double> typeAdapter2 = this.f13178a;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.f.getAdapter(Double.class);
                this.f13178a = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Double.valueOf(legStep.duration()));
            jsonWriter.name("geometry");
            if (legStep.geometry() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.b;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.f.getAdapter(String.class);
                    this.b = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, legStep.geometry());
            }
            jsonWriter.name(AppMeasurementSdk.ConditionalUserProperty.NAME);
            if (legStep.name() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.b;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.f.getAdapter(String.class);
                    this.b = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, legStep.name());
            }
            jsonWriter.name("destinations");
            if (legStep.destinations() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.b;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.f.getAdapter(String.class);
                    this.b = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, legStep.destinations());
            }
            jsonWriter.name("mode");
            if (legStep.mode() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter6 = this.b;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.f.getAdapter(String.class);
                    this.b = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, legStep.mode());
            }
            jsonWriter.name("rotary_name");
            if (legStep.rotaryName() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter7 = this.b;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.f.getAdapter(String.class);
                    this.b = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, legStep.rotaryName());
            }
            jsonWriter.name("maneuver");
            if (legStep.maneuver() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<StepManeuver> typeAdapter8 = this.c;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.f.getAdapter(StepManeuver.class);
                    this.c = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, legStep.maneuver());
            }
            jsonWriter.name("banner_instructions");
            if (legStep.bannerInstructions() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<BannerInstructions>> typeAdapter9 = this.d;
                if (typeAdapter9 == null) {
                    typeAdapter9 = this.f.getAdapter(TypeToken.getParameterized(List.class, BannerInstructions.class));
                    this.d = typeAdapter9;
                }
                typeAdapter9.write(jsonWriter, legStep.bannerInstructions());
            }
            jsonWriter.name("driving_side");
            if (legStep.drivingSide() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter10 = this.b;
                if (typeAdapter10 == null) {
                    typeAdapter10 = this.f.getAdapter(String.class);
                    this.b = typeAdapter10;
                }
                typeAdapter10.write(jsonWriter, legStep.drivingSide());
            }
            jsonWriter.name("weight");
            TypeAdapter<Double> typeAdapter11 = this.f13178a;
            if (typeAdapter11 == null) {
                typeAdapter11 = this.f.getAdapter(Double.class);
                this.f13178a = typeAdapter11;
            }
            typeAdapter11.write(jsonWriter, Double.valueOf(legStep.weight()));
            jsonWriter.name("intersections");
            if (legStep.intersections() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<StepIntersection>> typeAdapter12 = this.e;
                if (typeAdapter12 == null) {
                    typeAdapter12 = this.f.getAdapter(TypeToken.getParameterized(List.class, StepIntersection.class));
                    this.e = typeAdapter12;
                }
                typeAdapter12.write(jsonWriter, legStep.intersections());
            }
            jsonWriter.name("ref");
            if (legStep.ref() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter13 = this.b;
                if (typeAdapter13 == null) {
                    typeAdapter13 = this.f.getAdapter(String.class);
                    this.b = typeAdapter13;
                }
                typeAdapter13.write(jsonWriter, legStep.ref());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(LegStep)";
        }
    }

    public AutoValue_LegStep(double d, double d2, @Nullable String str, @Nullable String str2, @Nullable String str3, String str4, @Nullable String str5, StepManeuver stepManeuver, @Nullable List<BannerInstructions> list, @Nullable String str6, double d3, @Nullable List<StepIntersection> list2, @Nullable String str7) {
        super(d, d2, str, str2, str3, str4, str5, stepManeuver, list, str6, d3, list2, str7);
    }
}
