package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.models.LegAnnotation;
import java.io.IOException;
import java.util.List;
/* loaded from: classes11.dex */
public final class AutoValue_LegAnnotation extends i {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<LegAnnotation> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<List<Double>> f13177a;
        public volatile TypeAdapter<List<MaxSpeed>> b;
        public volatile TypeAdapter<List<String>> c;
        public volatile TypeAdapter<List<Long>> d;
        public final Gson e;

        public GsonTypeAdapter(Gson gson) {
            this.e = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public LegAnnotation read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            LegAnnotation.Builder builder = LegAnnotation.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (nextName.equals(DirectionsCriteria.ANNOTATION_SPEED_LIMIT)) {
                        TypeAdapter<List<Double>> typeAdapter = this.f13177a;
                        if (typeAdapter == null) {
                            typeAdapter = this.e.getAdapter(TypeToken.getParameterized(List.class, Double.class));
                            this.f13177a = typeAdapter;
                        }
                        builder.speedLimit(typeAdapter.read(jsonReader));
                    } else if (!nextName.equals(DirectionsCriteria.ANNOTATION_TOLL_ROAD)) {
                        if ("distance".equals(nextName)) {
                            TypeAdapter<List<Double>> typeAdapter2 = this.f13177a;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.e.getAdapter(TypeToken.getParameterized(List.class, Double.class));
                                this.f13177a = typeAdapter2;
                            }
                            builder.distance(typeAdapter2.read(jsonReader));
                        } else if ("duration".equals(nextName)) {
                            TypeAdapter<List<Double>> typeAdapter3 = this.f13177a;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.e.getAdapter(TypeToken.getParameterized(List.class, Double.class));
                                this.f13177a = typeAdapter3;
                            }
                            builder.duration(typeAdapter3.read(jsonReader));
                        } else if ("speed".equals(nextName)) {
                            TypeAdapter<List<Double>> typeAdapter4 = this.f13177a;
                            if (typeAdapter4 == null) {
                                typeAdapter4 = this.e.getAdapter(TypeToken.getParameterized(List.class, Double.class));
                                this.f13177a = typeAdapter4;
                            }
                            builder.speed(typeAdapter4.read(jsonReader));
                        } else if (DirectionsCriteria.ANNOTATION_MAXSPEED.equals(nextName)) {
                            TypeAdapter<List<MaxSpeed>> typeAdapter5 = this.b;
                            if (typeAdapter5 == null) {
                                typeAdapter5 = this.e.getAdapter(TypeToken.getParameterized(List.class, MaxSpeed.class));
                                this.b = typeAdapter5;
                            }
                            builder.maxspeed(typeAdapter5.read(jsonReader));
                        } else if (DirectionsCriteria.ANNOTATION_CONGESTION.equals(nextName)) {
                            TypeAdapter<List<String>> typeAdapter6 = this.c;
                            if (typeAdapter6 == null) {
                                typeAdapter6 = this.e.getAdapter(TypeToken.getParameterized(List.class, String.class));
                                this.c = typeAdapter6;
                            }
                            builder.congestion(typeAdapter6.read(jsonReader));
                        } else if (DirectionsCriteria.ANNOTATION_NODES.equals(nextName)) {
                            TypeAdapter<List<Long>> typeAdapter7 = this.d;
                            if (typeAdapter7 == null) {
                                typeAdapter7 = this.e.getAdapter(TypeToken.getParameterized(List.class, Long.class));
                                this.d = typeAdapter7;
                            }
                            builder.nodes(typeAdapter7.read(jsonReader));
                        } else if (DirectionsCriteria.ANNOTATION_BASE_DURATION.equals(nextName)) {
                            TypeAdapter<List<Double>> typeAdapter8 = this.f13177a;
                            if (typeAdapter8 == null) {
                                typeAdapter8 = this.e.getAdapter(TypeToken.getParameterized(List.class, Double.class));
                                this.f13177a = typeAdapter8;
                            }
                            builder.baseDuration(typeAdapter8.read(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    } else {
                        TypeAdapter<List<String>> typeAdapter9 = this.c;
                        if (typeAdapter9 == null) {
                            typeAdapter9 = this.e.getAdapter(TypeToken.getParameterized(List.class, String.class));
                            this.c = typeAdapter9;
                        }
                        builder.tollRoad(typeAdapter9.read(jsonReader));
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, LegAnnotation legAnnotation) throws IOException {
            if (legAnnotation == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("distance");
            if (legAnnotation.distance() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Double>> typeAdapter = this.f13177a;
                if (typeAdapter == null) {
                    typeAdapter = this.e.getAdapter(TypeToken.getParameterized(List.class, Double.class));
                    this.f13177a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, legAnnotation.distance());
            }
            jsonWriter.name("duration");
            if (legAnnotation.duration() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Double>> typeAdapter2 = this.f13177a;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.e.getAdapter(TypeToken.getParameterized(List.class, Double.class));
                    this.f13177a = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, legAnnotation.duration());
            }
            jsonWriter.name("speed");
            if (legAnnotation.speed() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Double>> typeAdapter3 = this.f13177a;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.e.getAdapter(TypeToken.getParameterized(List.class, Double.class));
                    this.f13177a = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, legAnnotation.speed());
            }
            jsonWriter.name(DirectionsCriteria.ANNOTATION_MAXSPEED);
            if (legAnnotation.maxspeed() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<MaxSpeed>> typeAdapter4 = this.b;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.e.getAdapter(TypeToken.getParameterized(List.class, MaxSpeed.class));
                    this.b = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, legAnnotation.maxspeed());
            }
            jsonWriter.name(DirectionsCriteria.ANNOTATION_CONGESTION);
            if (legAnnotation.congestion() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<String>> typeAdapter5 = this.c;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.e.getAdapter(TypeToken.getParameterized(List.class, String.class));
                    this.c = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, legAnnotation.congestion());
            }
            jsonWriter.name(DirectionsCriteria.ANNOTATION_TOLL_ROAD);
            if (legAnnotation.tollRoad() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<String>> typeAdapter6 = this.c;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.e.getAdapter(TypeToken.getParameterized(List.class, String.class));
                    this.c = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, legAnnotation.tollRoad());
            }
            jsonWriter.name(DirectionsCriteria.ANNOTATION_NODES);
            if (legAnnotation.nodes() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Long>> typeAdapter7 = this.d;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.e.getAdapter(TypeToken.getParameterized(List.class, Long.class));
                    this.d = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, legAnnotation.nodes());
            }
            jsonWriter.name(DirectionsCriteria.ANNOTATION_BASE_DURATION);
            if (legAnnotation.baseDuration() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Double>> typeAdapter8 = this.f13177a;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.e.getAdapter(TypeToken.getParameterized(List.class, Double.class));
                    this.f13177a = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, legAnnotation.baseDuration());
            }
            jsonWriter.name(DirectionsCriteria.ANNOTATION_SPEED_LIMIT);
            if (legAnnotation.speedLimit() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Double>> typeAdapter9 = this.f13177a;
                if (typeAdapter9 == null) {
                    typeAdapter9 = this.e.getAdapter(TypeToken.getParameterized(List.class, Double.class));
                    this.f13177a = typeAdapter9;
                }
                typeAdapter9.write(jsonWriter, legAnnotation.speedLimit());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(LegAnnotation)";
        }
    }

    public AutoValue_LegAnnotation(@Nullable List<Double> list, @Nullable List<Double> list2, @Nullable List<Double> list3, @Nullable List<MaxSpeed> list4, @Nullable List<String> list5, @Nullable List<String> list6, @Nullable List<Long> list7, @Nullable List<Double> list8, @Nullable List<Double> list9) {
        super(list, list2, list3, list4, list5, list6, list7, list8, list9);
    }
}
