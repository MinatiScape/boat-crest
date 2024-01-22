package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.StepManeuver;
import java.io.IOException;
/* loaded from: classes11.dex */
public final class AutoValue_StepManeuver extends p {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<StepManeuver> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<Double> f13184a;
        public volatile TypeAdapter<double[]> b;
        public volatile TypeAdapter<String> c;
        public volatile TypeAdapter<Integer> d;
        public final Gson e;

        public GsonTypeAdapter(Gson gson) {
            this.e = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public StepManeuver read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            StepManeuver.Builder builder = StepManeuver.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    char c = 65535;
                    switch (nextName.hashCode()) {
                        case -1563699391:
                            if (nextName.equals("maneuver_id")) {
                                c = 0;
                                break;
                            }
                            break;
                        case -1335595316:
                            if (nextName.equals("degree")) {
                                c = 1;
                                break;
                            }
                            break;
                        case -901094096:
                            if (nextName.equals("bearing_before")) {
                                c = 2;
                                break;
                            }
                            break;
                        case -307042805:
                            if (nextName.equals("bearing_after")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 1790702923:
                            if (nextName.equals("short_instruction")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 1901043637:
                            if (nextName.equals(FirebaseAnalytics.Param.LOCATION)) {
                                c = 5;
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
                            builder.maneuverId(typeAdapter.read(jsonReader));
                            continue;
                        case 1:
                            TypeAdapter<Double> typeAdapter2 = this.f13184a;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.e.getAdapter(Double.class);
                                this.f13184a = typeAdapter2;
                            }
                            builder.degree(typeAdapter2.read(jsonReader));
                            continue;
                        case 2:
                            TypeAdapter<Double> typeAdapter3 = this.f13184a;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.e.getAdapter(Double.class);
                                this.f13184a = typeAdapter3;
                            }
                            builder.bearingBefore(typeAdapter3.read(jsonReader));
                            continue;
                        case 3:
                            TypeAdapter<Double> typeAdapter4 = this.f13184a;
                            if (typeAdapter4 == null) {
                                typeAdapter4 = this.e.getAdapter(Double.class);
                                this.f13184a = typeAdapter4;
                            }
                            builder.bearingAfter(typeAdapter4.read(jsonReader));
                            continue;
                        case 4:
                            TypeAdapter<String> typeAdapter5 = this.c;
                            if (typeAdapter5 == null) {
                                typeAdapter5 = this.e.getAdapter(String.class);
                                this.c = typeAdapter5;
                            }
                            builder.shortInstruction(typeAdapter5.read(jsonReader));
                            continue;
                        case 5:
                            TypeAdapter<double[]> typeAdapter6 = this.b;
                            if (typeAdapter6 == null) {
                                typeAdapter6 = this.e.getAdapter(double[].class);
                                this.b = typeAdapter6;
                            }
                            builder.rawLocation(typeAdapter6.read(jsonReader));
                            continue;
                        default:
                            if ("instruction".equals(nextName)) {
                                TypeAdapter<String> typeAdapter7 = this.c;
                                if (typeAdapter7 == null) {
                                    typeAdapter7 = this.e.getAdapter(String.class);
                                    this.c = typeAdapter7;
                                }
                                builder.instruction(typeAdapter7.read(jsonReader));
                                break;
                            } else if ("type".equals(nextName)) {
                                TypeAdapter<String> typeAdapter8 = this.c;
                                if (typeAdapter8 == null) {
                                    typeAdapter8 = this.e.getAdapter(String.class);
                                    this.c = typeAdapter8;
                                }
                                builder.type(typeAdapter8.read(jsonReader));
                                break;
                            } else if ("modifier".equals(nextName)) {
                                TypeAdapter<String> typeAdapter9 = this.c;
                                if (typeAdapter9 == null) {
                                    typeAdapter9 = this.e.getAdapter(String.class);
                                    this.c = typeAdapter9;
                                }
                                builder.modifier(typeAdapter9.read(jsonReader));
                                break;
                            } else if ("exit".equals(nextName)) {
                                TypeAdapter<Integer> typeAdapter10 = this.d;
                                if (typeAdapter10 == null) {
                                    typeAdapter10 = this.e.getAdapter(Integer.class);
                                    this.d = typeAdapter10;
                                }
                                builder.exit(typeAdapter10.read(jsonReader));
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
        public void write(JsonWriter jsonWriter, StepManeuver stepManeuver) throws IOException {
            if (stepManeuver == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("degree");
            if (stepManeuver.degree() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter = this.f13184a;
                if (typeAdapter == null) {
                    typeAdapter = this.e.getAdapter(Double.class);
                    this.f13184a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, stepManeuver.degree());
            }
            jsonWriter.name(FirebaseAnalytics.Param.LOCATION);
            if (stepManeuver.rawLocation() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<double[]> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.e.getAdapter(double[].class);
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, stepManeuver.rawLocation());
            }
            jsonWriter.name("bearing_before");
            if (stepManeuver.bearingBefore() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter3 = this.f13184a;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.e.getAdapter(Double.class);
                    this.f13184a = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, stepManeuver.bearingBefore());
            }
            jsonWriter.name("bearing_after");
            if (stepManeuver.bearingAfter() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter4 = this.f13184a;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.e.getAdapter(Double.class);
                    this.f13184a = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, stepManeuver.bearingAfter());
            }
            jsonWriter.name("instruction");
            if (stepManeuver.instruction() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.c;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.e.getAdapter(String.class);
                    this.c = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, stepManeuver.instruction());
            }
            jsonWriter.name("short_instruction");
            if (stepManeuver.shortInstruction() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter6 = this.c;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.e.getAdapter(String.class);
                    this.c = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, stepManeuver.shortInstruction());
            }
            jsonWriter.name("maneuver_id");
            if (stepManeuver.maneuverId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter7 = this.d;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.e.getAdapter(Integer.class);
                    this.d = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, stepManeuver.maneuverId());
            }
            jsonWriter.name("type");
            if (stepManeuver.type() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter8 = this.c;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.e.getAdapter(String.class);
                    this.c = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, stepManeuver.type());
            }
            jsonWriter.name("modifier");
            if (stepManeuver.modifier() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter9 = this.c;
                if (typeAdapter9 == null) {
                    typeAdapter9 = this.e.getAdapter(String.class);
                    this.c = typeAdapter9;
                }
                typeAdapter9.write(jsonWriter, stepManeuver.modifier());
            }
            jsonWriter.name("exit");
            if (stepManeuver.exit() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter10 = this.d;
                if (typeAdapter10 == null) {
                    typeAdapter10 = this.e.getAdapter(Integer.class);
                    this.d = typeAdapter10;
                }
                typeAdapter10.write(jsonWriter, stepManeuver.exit());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(StepManeuver)";
        }
    }

    public AutoValue_StepManeuver(@Nullable Double d, double[] dArr, @Nullable Double d2, @Nullable Double d3, @Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable String str4, @Nullable Integer num2) {
        super(d, dArr, d2, d3, str, str2, num, str3, str4, num2);
    }
}
