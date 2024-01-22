package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.RouteLeg;
import java.io.IOException;
import java.util.List;
import org.bouncycastle.i18n.ErrorBundle;
/* loaded from: classes11.dex */
public final class AutoValue_RouteLeg extends m {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<RouteLeg> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<Double> f13181a;
        public volatile TypeAdapter<String> b;
        public volatile TypeAdapter<List<LegStep>> c;
        public volatile TypeAdapter<LegAnnotation> d;
        public final Gson e;

        public GsonTypeAdapter(Gson gson) {
            this.e = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public RouteLeg read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            RouteLeg.Builder builder = RouteLeg.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if ("distance".equals(nextName)) {
                        TypeAdapter<Double> typeAdapter = this.f13181a;
                        if (typeAdapter == null) {
                            typeAdapter = this.e.getAdapter(Double.class);
                            this.f13181a = typeAdapter;
                        }
                        builder.distance(typeAdapter.read(jsonReader));
                    } else if ("duration".equals(nextName)) {
                        TypeAdapter<Double> typeAdapter2 = this.f13181a;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.e.getAdapter(Double.class);
                            this.f13181a = typeAdapter2;
                        }
                        builder.duration(typeAdapter2.read(jsonReader));
                    } else if (ErrorBundle.SUMMARY_ENTRY.equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.b;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.e.getAdapter(String.class);
                            this.b = typeAdapter3;
                        }
                        builder.summary(typeAdapter3.read(jsonReader));
                    } else if ("steps".equals(nextName)) {
                        TypeAdapter<List<LegStep>> typeAdapter4 = this.c;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.e.getAdapter(TypeToken.getParameterized(List.class, LegStep.class));
                            this.c = typeAdapter4;
                        }
                        builder.steps(typeAdapter4.read(jsonReader));
                    } else if ("annotation".equals(nextName)) {
                        TypeAdapter<LegAnnotation> typeAdapter5 = this.d;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.e.getAdapter(LegAnnotation.class);
                            this.d = typeAdapter5;
                        }
                        builder.annotation(typeAdapter5.read(jsonReader));
                    } else if ("weight".equals(nextName)) {
                        TypeAdapter<Double> typeAdapter6 = this.f13181a;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.e.getAdapter(Double.class);
                            this.f13181a = typeAdapter6;
                        }
                        builder.weight(typeAdapter6.read(jsonReader));
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
        public void write(JsonWriter jsonWriter, RouteLeg routeLeg) throws IOException {
            if (routeLeg == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("distance");
            if (routeLeg.distance() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter = this.f13181a;
                if (typeAdapter == null) {
                    typeAdapter = this.e.getAdapter(Double.class);
                    this.f13181a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, routeLeg.distance());
            }
            jsonWriter.name("duration");
            if (routeLeg.duration() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter2 = this.f13181a;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.e.getAdapter(Double.class);
                    this.f13181a = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, routeLeg.duration());
            }
            jsonWriter.name(ErrorBundle.SUMMARY_ENTRY);
            if (routeLeg.summary() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.b;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.e.getAdapter(String.class);
                    this.b = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, routeLeg.summary());
            }
            jsonWriter.name("steps");
            if (routeLeg.steps() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<LegStep>> typeAdapter4 = this.c;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.e.getAdapter(TypeToken.getParameterized(List.class, LegStep.class));
                    this.c = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, routeLeg.steps());
            }
            jsonWriter.name("annotation");
            if (routeLeg.annotation() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<LegAnnotation> typeAdapter5 = this.d;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.e.getAdapter(LegAnnotation.class);
                    this.d = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, routeLeg.annotation());
            }
            jsonWriter.name("weight");
            if (routeLeg.weight() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter6 = this.f13181a;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.e.getAdapter(Double.class);
                    this.f13181a = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, routeLeg.weight());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(RouteLeg)";
        }
    }

    public AutoValue_RouteLeg(@Nullable Double d, @Nullable Double d2, @Nullable String str, @Nullable List<LegStep> list, @Nullable LegAnnotation legAnnotation, @Nullable Double d3) {
        super(d, d2, str, list, legAnnotation, d3);
    }
}
