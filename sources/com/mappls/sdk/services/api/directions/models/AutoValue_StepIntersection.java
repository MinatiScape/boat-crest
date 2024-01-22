package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.StepIntersection;
import java.io.IOException;
import java.util.List;
/* loaded from: classes11.dex */
public final class AutoValue_StepIntersection extends o {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<StepIntersection> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<double[]> f13183a;
        public volatile TypeAdapter<List<Integer>> b;
        public volatile TypeAdapter<List<String>> c;
        public volatile TypeAdapter<List<Boolean>> d;
        public volatile TypeAdapter<Integer> e;
        public volatile TypeAdapter<List<IntersectionLanes>> f;
        public final Gson g;

        public GsonTypeAdapter(Gson gson) {
            this.g = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public StepIntersection read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            StepIntersection.Builder builder = StepIntersection.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (!nextName.equals(FirebaseAnalytics.Param.LOCATION)) {
                        if ("bearings".equals(nextName)) {
                            TypeAdapter<List<Integer>> typeAdapter = this.b;
                            if (typeAdapter == null) {
                                typeAdapter = this.g.getAdapter(TypeToken.getParameterized(List.class, Integer.class));
                                this.b = typeAdapter;
                            }
                            builder.bearings(typeAdapter.read(jsonReader));
                        } else if ("classes".equals(nextName)) {
                            TypeAdapter<List<String>> typeAdapter2 = this.c;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.g.getAdapter(TypeToken.getParameterized(List.class, String.class));
                                this.c = typeAdapter2;
                            }
                            builder.classes(typeAdapter2.read(jsonReader));
                        } else if ("entry".equals(nextName)) {
                            TypeAdapter<List<Boolean>> typeAdapter3 = this.d;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.g.getAdapter(TypeToken.getParameterized(List.class, Boolean.class));
                                this.d = typeAdapter3;
                            }
                            builder.entry(typeAdapter3.read(jsonReader));
                        } else if ("in".equals(nextName)) {
                            TypeAdapter<Integer> typeAdapter4 = this.e;
                            if (typeAdapter4 == null) {
                                typeAdapter4 = this.g.getAdapter(Integer.class);
                                this.e = typeAdapter4;
                            }
                            builder.in(typeAdapter4.read(jsonReader));
                        } else if ("out".equals(nextName)) {
                            TypeAdapter<Integer> typeAdapter5 = this.e;
                            if (typeAdapter5 == null) {
                                typeAdapter5 = this.g.getAdapter(Integer.class);
                                this.e = typeAdapter5;
                            }
                            builder.out(typeAdapter5.read(jsonReader));
                        } else if ("lanes".equals(nextName)) {
                            TypeAdapter<List<IntersectionLanes>> typeAdapter6 = this.f;
                            if (typeAdapter6 == null) {
                                typeAdapter6 = this.g.getAdapter(TypeToken.getParameterized(List.class, IntersectionLanes.class));
                                this.f = typeAdapter6;
                            }
                            builder.lanes(typeAdapter6.read(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    } else {
                        TypeAdapter<double[]> typeAdapter7 = this.f13183a;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.g.getAdapter(double[].class);
                            this.f13183a = typeAdapter7;
                        }
                        builder.rawLocation(typeAdapter7.read(jsonReader));
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, StepIntersection stepIntersection) throws IOException {
            if (stepIntersection == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(FirebaseAnalytics.Param.LOCATION);
            if (stepIntersection.rawLocation() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<double[]> typeAdapter = this.f13183a;
                if (typeAdapter == null) {
                    typeAdapter = this.g.getAdapter(double[].class);
                    this.f13183a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, stepIntersection.rawLocation());
            }
            jsonWriter.name("bearings");
            if (stepIntersection.bearings() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Integer>> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.g.getAdapter(TypeToken.getParameterized(List.class, Integer.class));
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, stepIntersection.bearings());
            }
            jsonWriter.name("classes");
            if (stepIntersection.classes() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<String>> typeAdapter3 = this.c;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.g.getAdapter(TypeToken.getParameterized(List.class, String.class));
                    this.c = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, stepIntersection.classes());
            }
            jsonWriter.name("entry");
            if (stepIntersection.entry() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Boolean>> typeAdapter4 = this.d;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.g.getAdapter(TypeToken.getParameterized(List.class, Boolean.class));
                    this.d = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, stepIntersection.entry());
            }
            jsonWriter.name("in");
            if (stepIntersection.in() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter5 = this.e;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.g.getAdapter(Integer.class);
                    this.e = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, stepIntersection.in());
            }
            jsonWriter.name("out");
            if (stepIntersection.out() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter6 = this.e;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.g.getAdapter(Integer.class);
                    this.e = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, stepIntersection.out());
            }
            jsonWriter.name("lanes");
            if (stepIntersection.lanes() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<IntersectionLanes>> typeAdapter7 = this.f;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.g.getAdapter(TypeToken.getParameterized(List.class, IntersectionLanes.class));
                    this.f = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, stepIntersection.lanes());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(StepIntersection)";
        }
    }

    public AutoValue_StepIntersection(double[] dArr, @Nullable List<Integer> list, @Nullable List<String> list2, @Nullable List<Boolean> list3, @Nullable Integer num, @Nullable Integer num2, @Nullable List<IntersectionLanes> list4) {
        super(dArr, list, list2, list3, num, num2, list4);
    }
}
