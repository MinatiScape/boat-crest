package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import java.io.IOException;
import java.util.List;
import org.bouncycastle.i18n.ErrorBundle;
/* loaded from: classes11.dex */
public final class AutoValue_DirectionsRoute extends f {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DirectionsRoute> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<Double> f13174a;
        public volatile TypeAdapter<String> b;
        public volatile TypeAdapter<List<RouteLeg>> c;
        public volatile TypeAdapter<RouteOptions> d;
        public volatile TypeAdapter<Integer> e;
        public volatile TypeAdapter<RouteClasses> f;
        public volatile TypeAdapter<List<DirectionsRoute>> g;
        public final Gson h;

        public GsonTypeAdapter(Gson gson) {
            this.h = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public DirectionsRoute read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            DirectionsRoute.Builder builder = DirectionsRoute.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (nextName.equals("contains_classes")) {
                        TypeAdapter<RouteClasses> typeAdapter = this.f;
                        if (typeAdapter == null) {
                            typeAdapter = this.h.getAdapter(RouteClasses.class);
                            this.f = typeAdapter;
                        }
                        builder.routeClasses(typeAdapter.read(jsonReader));
                    } else if (!nextName.equals("weight_name")) {
                        if ("distance".equals(nextName)) {
                            TypeAdapter<Double> typeAdapter2 = this.f13174a;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.h.getAdapter(Double.class);
                                this.f13174a = typeAdapter2;
                            }
                            builder.distance(typeAdapter2.read(jsonReader));
                        } else if ("duration".equals(nextName)) {
                            TypeAdapter<Double> typeAdapter3 = this.f13174a;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.h.getAdapter(Double.class);
                                this.f13174a = typeAdapter3;
                            }
                            builder.duration(typeAdapter3.read(jsonReader));
                        } else if ("geometry".equals(nextName)) {
                            TypeAdapter<String> typeAdapter4 = this.b;
                            if (typeAdapter4 == null) {
                                typeAdapter4 = this.h.getAdapter(String.class);
                                this.b = typeAdapter4;
                            }
                            builder.geometry(typeAdapter4.read(jsonReader));
                        } else if ("weight".equals(nextName)) {
                            TypeAdapter<Double> typeAdapter5 = this.f13174a;
                            if (typeAdapter5 == null) {
                                typeAdapter5 = this.h.getAdapter(Double.class);
                                this.f13174a = typeAdapter5;
                            }
                            builder.weight(typeAdapter5.read(jsonReader));
                        } else if ("legs".equals(nextName)) {
                            TypeAdapter<List<RouteLeg>> typeAdapter6 = this.c;
                            if (typeAdapter6 == null) {
                                typeAdapter6 = this.h.getAdapter(TypeToken.getParameterized(List.class, RouteLeg.class));
                                this.c = typeAdapter6;
                            }
                            builder.legs(typeAdapter6.read(jsonReader));
                        } else if ("routeOptions".equals(nextName)) {
                            TypeAdapter<RouteOptions> typeAdapter7 = this.d;
                            if (typeAdapter7 == null) {
                                typeAdapter7 = this.h.getAdapter(RouteOptions.class);
                                this.d = typeAdapter7;
                            }
                            builder.routeOptions(typeAdapter7.read(jsonReader));
                        } else if ("routeIndex".equals(nextName)) {
                            TypeAdapter<Integer> typeAdapter8 = this.e;
                            if (typeAdapter8 == null) {
                                typeAdapter8 = this.h.getAdapter(Integer.class);
                                this.e = typeAdapter8;
                            }
                            builder.routeIndex(typeAdapter8.read(jsonReader));
                        } else if (ErrorBundle.SUMMARY_ENTRY.equals(nextName)) {
                            TypeAdapter<String> typeAdapter9 = this.b;
                            if (typeAdapter9 == null) {
                                typeAdapter9 = this.h.getAdapter(String.class);
                                this.b = typeAdapter9;
                            }
                            builder.summary(typeAdapter9.read(jsonReader));
                        } else if ("alternatives".equals(nextName)) {
                            TypeAdapter<List<DirectionsRoute>> typeAdapter10 = this.g;
                            if (typeAdapter10 == null) {
                                typeAdapter10 = this.h.getAdapter(TypeToken.getParameterized(List.class, DirectionsRoute.class));
                                this.g = typeAdapter10;
                            }
                            builder.alternatives(typeAdapter10.read(jsonReader));
                        } else if ("betterRouteId".equals(nextName)) {
                            TypeAdapter<String> typeAdapter11 = this.b;
                            if (typeAdapter11 == null) {
                                typeAdapter11 = this.h.getAdapter(String.class);
                                this.b = typeAdapter11;
                            }
                            builder.betterRouteId(typeAdapter11.read(jsonReader));
                        } else if ("routeId".equals(nextName)) {
                            TypeAdapter<String> typeAdapter12 = this.b;
                            if (typeAdapter12 == null) {
                                typeAdapter12 = this.h.getAdapter(String.class);
                                this.b = typeAdapter12;
                            }
                            builder.routeId(typeAdapter12.read(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    } else {
                        TypeAdapter<String> typeAdapter13 = this.b;
                        if (typeAdapter13 == null) {
                            typeAdapter13 = this.h.getAdapter(String.class);
                            this.b = typeAdapter13;
                        }
                        builder.weightName(typeAdapter13.read(jsonReader));
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, DirectionsRoute directionsRoute) throws IOException {
            if (directionsRoute == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("distance");
            if (directionsRoute.distance() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter = this.f13174a;
                if (typeAdapter == null) {
                    typeAdapter = this.h.getAdapter(Double.class);
                    this.f13174a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, directionsRoute.distance());
            }
            jsonWriter.name("duration");
            if (directionsRoute.duration() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter2 = this.f13174a;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.h.getAdapter(Double.class);
                    this.f13174a = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, directionsRoute.duration());
            }
            jsonWriter.name("geometry");
            if (directionsRoute.geometry() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.b;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.h.getAdapter(String.class);
                    this.b = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, directionsRoute.geometry());
            }
            jsonWriter.name("weight");
            if (directionsRoute.weight() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter4 = this.f13174a;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.h.getAdapter(Double.class);
                    this.f13174a = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, directionsRoute.weight());
            }
            jsonWriter.name("weight_name");
            if (directionsRoute.weightName() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.b;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.h.getAdapter(String.class);
                    this.b = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, directionsRoute.weightName());
            }
            jsonWriter.name("legs");
            if (directionsRoute.legs() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<RouteLeg>> typeAdapter6 = this.c;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.h.getAdapter(TypeToken.getParameterized(List.class, RouteLeg.class));
                    this.c = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, directionsRoute.legs());
            }
            jsonWriter.name("routeOptions");
            if (directionsRoute.routeOptions() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<RouteOptions> typeAdapter7 = this.d;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.h.getAdapter(RouteOptions.class);
                    this.d = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, directionsRoute.routeOptions());
            }
            jsonWriter.name("routeIndex");
            if (directionsRoute.routeIndex() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter8 = this.e;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.h.getAdapter(Integer.class);
                    this.e = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, directionsRoute.routeIndex());
            }
            jsonWriter.name("contains_classes");
            if (directionsRoute.routeClasses() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<RouteClasses> typeAdapter9 = this.f;
                if (typeAdapter9 == null) {
                    typeAdapter9 = this.h.getAdapter(RouteClasses.class);
                    this.f = typeAdapter9;
                }
                typeAdapter9.write(jsonWriter, directionsRoute.routeClasses());
            }
            jsonWriter.name(ErrorBundle.SUMMARY_ENTRY);
            if (directionsRoute.summary() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter10 = this.b;
                if (typeAdapter10 == null) {
                    typeAdapter10 = this.h.getAdapter(String.class);
                    this.b = typeAdapter10;
                }
                typeAdapter10.write(jsonWriter, directionsRoute.summary());
            }
            jsonWriter.name("alternatives");
            if (directionsRoute.alternatives() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<DirectionsRoute>> typeAdapter11 = this.g;
                if (typeAdapter11 == null) {
                    typeAdapter11 = this.h.getAdapter(TypeToken.getParameterized(List.class, DirectionsRoute.class));
                    this.g = typeAdapter11;
                }
                typeAdapter11.write(jsonWriter, directionsRoute.alternatives());
            }
            jsonWriter.name("betterRouteId");
            if (directionsRoute.betterRouteId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter12 = this.b;
                if (typeAdapter12 == null) {
                    typeAdapter12 = this.h.getAdapter(String.class);
                    this.b = typeAdapter12;
                }
                typeAdapter12.write(jsonWriter, directionsRoute.betterRouteId());
            }
            jsonWriter.name("routeId");
            if (directionsRoute.routeId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter13 = this.b;
                if (typeAdapter13 == null) {
                    typeAdapter13 = this.h.getAdapter(String.class);
                    this.b = typeAdapter13;
                }
                typeAdapter13.write(jsonWriter, directionsRoute.routeId());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(DirectionsRoute)";
        }
    }

    public AutoValue_DirectionsRoute(@Nullable Double d, @Nullable Double d2, @Nullable String str, @Nullable Double d3, @Nullable String str2, @Nullable List<RouteLeg> list, @Nullable RouteOptions routeOptions, @Nullable Integer num, @Nullable RouteClasses routeClasses, @Nullable String str3, @Nullable List<DirectionsRoute> list2, @Nullable String str4, @Nullable String str5) {
        super(d, d2, str, d3, str2, list, routeOptions, num, routeClasses, str3, list2, str4, str5);
    }
}
