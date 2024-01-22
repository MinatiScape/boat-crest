package com.mappls.sdk.services.api.distance.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.DirectionsWaypoint;
import com.mappls.sdk.services.api.distance.models.DistanceResults;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
final class AutoValue_DistanceResults extends C$AutoValue_DistanceResults {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DistanceResults> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<String> f13215a;
        public volatile TypeAdapter<List<DirectionsWaypoint>> b;
        public volatile TypeAdapter<List<Double[]>> c;
        public final Gson d;

        public GsonTypeAdapter(Gson gson) {
            this.d = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public DistanceResults read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            DistanceResults.Builder builder = DistanceResults.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    char c = 65535;
                    switch (nextName.hashCode()) {
                        case -1622842017:
                            if (nextName.equals("durations")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 118568828:
                            if (nextName.equals("fallback_speed_cells")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 352318238:
                            if (nextName.equals("distances")) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            TypeAdapter<List<Double[]>> typeAdapter = this.c;
                            if (typeAdapter == null) {
                                typeAdapter = this.d.getAdapter(TypeToken.getParameterized(List.class, Double[].class));
                                this.c = typeAdapter;
                            }
                            builder.durations(typeAdapter.read(jsonReader));
                            continue;
                        case 1:
                            TypeAdapter<List<Double[]>> typeAdapter2 = this.c;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.d.getAdapter(TypeToken.getParameterized(List.class, Double[].class));
                                this.c = typeAdapter2;
                            }
                            builder.fallbackSpeedCells(typeAdapter2.read(jsonReader));
                            continue;
                        case 2:
                            TypeAdapter<List<Double[]>> typeAdapter3 = this.c;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.d.getAdapter(TypeToken.getParameterized(List.class, Double[].class));
                                this.c = typeAdapter3;
                            }
                            builder.distances(typeAdapter3.read(jsonReader));
                            continue;
                        default:
                            if ("code".equals(nextName)) {
                                TypeAdapter<String> typeAdapter4 = this.f13215a;
                                if (typeAdapter4 == null) {
                                    typeAdapter4 = this.d.getAdapter(String.class);
                                    this.f13215a = typeAdapter4;
                                }
                                builder.code(typeAdapter4.read(jsonReader));
                                break;
                            } else if ("destinations".equals(nextName)) {
                                TypeAdapter<List<DirectionsWaypoint>> typeAdapter5 = this.b;
                                if (typeAdapter5 == null) {
                                    typeAdapter5 = this.d.getAdapter(TypeToken.getParameterized(List.class, DirectionsWaypoint.class));
                                    this.b = typeAdapter5;
                                }
                                builder.destinations(typeAdapter5.read(jsonReader));
                                break;
                            } else if ("sources".equals(nextName)) {
                                TypeAdapter<List<DirectionsWaypoint>> typeAdapter6 = this.b;
                                if (typeAdapter6 == null) {
                                    typeAdapter6 = this.d.getAdapter(TypeToken.getParameterized(List.class, DirectionsWaypoint.class));
                                    this.b = typeAdapter6;
                                }
                                builder.sources(typeAdapter6.read(jsonReader));
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
        public void write(JsonWriter jsonWriter, DistanceResults distanceResults) throws IOException {
            if (distanceResults == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("code");
            if (distanceResults.code() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.f13215a;
                if (typeAdapter == null) {
                    typeAdapter = this.d.getAdapter(String.class);
                    this.f13215a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, distanceResults.code());
            }
            jsonWriter.name("destinations");
            if (distanceResults.destinations() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<DirectionsWaypoint>> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.d.getAdapter(TypeToken.getParameterized(List.class, DirectionsWaypoint.class));
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, distanceResults.destinations());
            }
            jsonWriter.name("sources");
            if (distanceResults.sources() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<DirectionsWaypoint>> typeAdapter3 = this.b;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.d.getAdapter(TypeToken.getParameterized(List.class, DirectionsWaypoint.class));
                    this.b = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, distanceResults.sources());
            }
            jsonWriter.name("durations");
            if (distanceResults.durations() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Double[]>> typeAdapter4 = this.c;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.d.getAdapter(TypeToken.getParameterized(List.class, Double[].class));
                    this.c = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, distanceResults.durations());
            }
            jsonWriter.name("distances");
            if (distanceResults.distances() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Double[]>> typeAdapter5 = this.c;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.d.getAdapter(TypeToken.getParameterized(List.class, Double[].class));
                    this.c = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, distanceResults.distances());
            }
            jsonWriter.name("fallback_speed_cells");
            if (distanceResults.fallbackSpeedCells() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Double[]>> typeAdapter6 = this.c;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.d.getAdapter(TypeToken.getParameterized(List.class, Double[].class));
                    this.c = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, distanceResults.fallbackSpeedCells());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(DistanceResults)";
        }
    }

    public AutoValue_DistanceResults(final String str, @Nullable final List<DirectionsWaypoint> list, @Nullable final List<DirectionsWaypoint> list2, @Nullable final List<Double[]> list3, @Nullable final List<Double[]> list4, @Nullable final List<Double[]> list5) {
        new DistanceResults(str, list, list2, list3, list4, list5) { // from class: com.mappls.sdk.services.api.distance.models.$AutoValue_DistanceResults
            private final String code;
            private final List<DirectionsWaypoint> destinations;
            private final List<Double[]> distances;
            private final List<Double[]> durations;
            private final List<Double[]> fallbackSpeedCells;
            private final List<DirectionsWaypoint> sources;

            /* renamed from: com.mappls.sdk.services.api.distance.models.$AutoValue_DistanceResults$b */
            /* loaded from: classes11.dex */
            public static class b extends DistanceResults.Builder {

                /* renamed from: a  reason: collision with root package name */
                public String f13213a;
                public List<DirectionsWaypoint> b;
                public List<DirectionsWaypoint> c;
                public List<Double[]> d;
                public List<Double[]> e;
                public List<Double[]> f;

                @Override // com.mappls.sdk.services.api.distance.models.DistanceResults.Builder
                public DistanceResults build() {
                    String str = "";
                    if (this.f13213a == null) {
                        str = " code";
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_DistanceResults(this.f13213a, this.b, this.c, this.d, this.e, this.f);
                    }
                    throw new IllegalStateException("Missing required properties:" + str);
                }

                @Override // com.mappls.sdk.services.api.distance.models.DistanceResults.Builder
                public DistanceResults.Builder code(String str) {
                    Objects.requireNonNull(str, "Null code");
                    this.f13213a = str;
                    return this;
                }

                @Override // com.mappls.sdk.services.api.distance.models.DistanceResults.Builder
                public DistanceResults.Builder destinations(@Nullable List<DirectionsWaypoint> list) {
                    this.b = list;
                    return this;
                }

                @Override // com.mappls.sdk.services.api.distance.models.DistanceResults.Builder
                public DistanceResults.Builder distances(@Nullable List<Double[]> list) {
                    this.e = list;
                    return this;
                }

                @Override // com.mappls.sdk.services.api.distance.models.DistanceResults.Builder
                public DistanceResults.Builder durations(@Nullable List<Double[]> list) {
                    this.d = list;
                    return this;
                }

                @Override // com.mappls.sdk.services.api.distance.models.DistanceResults.Builder
                public DistanceResults.Builder fallbackSpeedCells(@Nullable List<Double[]> list) {
                    this.f = list;
                    return this;
                }

                @Override // com.mappls.sdk.services.api.distance.models.DistanceResults.Builder
                public DistanceResults.Builder sources(@Nullable List<DirectionsWaypoint> list) {
                    this.c = list;
                    return this;
                }

                public b() {
                }

                public b(DistanceResults distanceResults) {
                    this.f13213a = distanceResults.code();
                    this.b = distanceResults.destinations();
                    this.c = distanceResults.sources();
                    this.d = distanceResults.durations();
                    this.e = distanceResults.distances();
                    this.f = distanceResults.fallbackSpeedCells();
                }
            }

            {
                Objects.requireNonNull(str, "Null code");
                this.code = str;
                this.destinations = list;
                this.sources = list2;
                this.durations = list3;
                this.distances = list4;
                this.fallbackSpeedCells = list5;
            }

            @Override // com.mappls.sdk.services.api.distance.models.DistanceResults
            @NonNull
            public String code() {
                return this.code;
            }

            @Override // com.mappls.sdk.services.api.distance.models.DistanceResults
            @Nullable
            public List<DirectionsWaypoint> destinations() {
                return this.destinations;
            }

            @Override // com.mappls.sdk.services.api.distance.models.DistanceResults
            @Nullable
            @SerializedName("distances")
            public List<Double[]> distances() {
                return this.distances;
            }

            @Override // com.mappls.sdk.services.api.distance.models.DistanceResults
            @Nullable
            @SerializedName("durations")
            public List<Double[]> durations() {
                return this.durations;
            }

            public boolean equals(Object obj) {
                List<DirectionsWaypoint> list6;
                List<DirectionsWaypoint> list7;
                List<Double[]> list8;
                List<Double[]> list9;
                if (obj == this) {
                    return true;
                }
                if (obj instanceof DistanceResults) {
                    DistanceResults distanceResults = (DistanceResults) obj;
                    if (this.code.equals(distanceResults.code()) && ((list6 = this.destinations) != null ? list6.equals(distanceResults.destinations()) : distanceResults.destinations() == null) && ((list7 = this.sources) != null ? list7.equals(distanceResults.sources()) : distanceResults.sources() == null) && ((list8 = this.durations) != null ? list8.equals(distanceResults.durations()) : distanceResults.durations() == null) && ((list9 = this.distances) != null ? list9.equals(distanceResults.distances()) : distanceResults.distances() == null)) {
                        List<Double[]> list10 = this.fallbackSpeedCells;
                        if (list10 == null) {
                            if (distanceResults.fallbackSpeedCells() == null) {
                                return true;
                            }
                        } else if (list10.equals(distanceResults.fallbackSpeedCells())) {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }

            @Override // com.mappls.sdk.services.api.distance.models.DistanceResults
            @Nullable
            @SerializedName("fallback_speed_cells")
            public List<Double[]> fallbackSpeedCells() {
                return this.fallbackSpeedCells;
            }

            public int hashCode() {
                int hashCode = (this.code.hashCode() ^ 1000003) * 1000003;
                List<DirectionsWaypoint> list6 = this.destinations;
                int hashCode2 = (hashCode ^ (list6 == null ? 0 : list6.hashCode())) * 1000003;
                List<DirectionsWaypoint> list7 = this.sources;
                int hashCode3 = (hashCode2 ^ (list7 == null ? 0 : list7.hashCode())) * 1000003;
                List<Double[]> list8 = this.durations;
                int hashCode4 = (hashCode3 ^ (list8 == null ? 0 : list8.hashCode())) * 1000003;
                List<Double[]> list9 = this.distances;
                int hashCode5 = (hashCode4 ^ (list9 == null ? 0 : list9.hashCode())) * 1000003;
                List<Double[]> list10 = this.fallbackSpeedCells;
                return hashCode5 ^ (list10 != null ? list10.hashCode() : 0);
            }

            @Override // com.mappls.sdk.services.api.distance.models.DistanceResults
            @Nullable
            public List<DirectionsWaypoint> sources() {
                return this.sources;
            }

            @Override // com.mappls.sdk.services.api.distance.models.DistanceResults
            public DistanceResults.Builder toBuilder() {
                return new b(this);
            }

            public String toString() {
                return "DistanceResults{code=" + this.code + ", destinations=" + this.destinations + ", sources=" + this.sources + ", durations=" + this.durations + ", distances=" + this.distances + ", fallbackSpeedCells=" + this.fallbackSpeedCells + "}";
            }
        };
    }
}
