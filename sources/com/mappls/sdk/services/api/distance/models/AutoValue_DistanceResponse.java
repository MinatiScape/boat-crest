package com.mappls.sdk.services.api.distance.models;

import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.distance.models.DistanceResponse;
import java.io.IOException;
/* loaded from: classes11.dex */
final class AutoValue_DistanceResponse extends C$AutoValue_DistanceResponse {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DistanceResponse> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<String> f13214a;
        public volatile TypeAdapter<DistanceResults> b;
        public volatile TypeAdapter<Long> c;
        public final Gson d;

        public GsonTypeAdapter(Gson gson) {
            this.d = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public DistanceResponse read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            DistanceResponse.Builder builder = DistanceResponse.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (!nextName.equals("results")) {
                        if ("version".equals(nextName)) {
                            TypeAdapter<String> typeAdapter = this.f13214a;
                            if (typeAdapter == null) {
                                typeAdapter = this.d.getAdapter(String.class);
                                this.f13214a = typeAdapter;
                            }
                            builder.version(typeAdapter.read(jsonReader));
                        } else if ("responseCode".equals(nextName)) {
                            TypeAdapter<Long> typeAdapter2 = this.c;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.d.getAdapter(Long.class);
                                this.c = typeAdapter2;
                            }
                            builder.responseCode(typeAdapter2.read(jsonReader).longValue());
                        } else {
                            jsonReader.skipValue();
                        }
                    } else {
                        TypeAdapter<DistanceResults> typeAdapter3 = this.b;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.d.getAdapter(DistanceResults.class);
                            this.b = typeAdapter3;
                        }
                        builder.results(typeAdapter3.read(jsonReader));
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, DistanceResponse distanceResponse) throws IOException {
            if (distanceResponse == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("version");
            if (distanceResponse.version() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.f13214a;
                if (typeAdapter == null) {
                    typeAdapter = this.d.getAdapter(String.class);
                    this.f13214a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, distanceResponse.version());
            }
            jsonWriter.name("results");
            if (distanceResponse.results() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<DistanceResults> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.d.getAdapter(DistanceResults.class);
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, distanceResponse.results());
            }
            jsonWriter.name("responseCode");
            TypeAdapter<Long> typeAdapter3 = this.c;
            if (typeAdapter3 == null) {
                typeAdapter3 = this.d.getAdapter(Long.class);
                this.c = typeAdapter3;
            }
            typeAdapter3.write(jsonWriter, Long.valueOf(distanceResponse.responseCode()));
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(DistanceResponse)";
        }
    }

    public AutoValue_DistanceResponse(@Nullable final String str, @Nullable final DistanceResults distanceResults, final long j) {
        new DistanceResponse(str, distanceResults, j) { // from class: com.mappls.sdk.services.api.distance.models.$AutoValue_DistanceResponse
            private final long responseCode;
            private final DistanceResults results;
            private final String version;

            /* renamed from: com.mappls.sdk.services.api.distance.models.$AutoValue_DistanceResponse$b */
            /* loaded from: classes11.dex */
            public static class b extends DistanceResponse.Builder {

                /* renamed from: a  reason: collision with root package name */
                public String f13212a;
                public DistanceResults b;
                public Long c;

                @Override // com.mappls.sdk.services.api.distance.models.DistanceResponse.Builder
                public DistanceResponse build() {
                    String str = "";
                    if (this.c == null) {
                        str = " responseCode";
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_DistanceResponse(this.f13212a, this.b, this.c.longValue());
                    }
                    throw new IllegalStateException("Missing required properties:" + str);
                }

                @Override // com.mappls.sdk.services.api.distance.models.DistanceResponse.Builder
                public DistanceResponse.Builder responseCode(long j) {
                    this.c = Long.valueOf(j);
                    return this;
                }

                @Override // com.mappls.sdk.services.api.distance.models.DistanceResponse.Builder
                public DistanceResponse.Builder results(@Nullable DistanceResults distanceResults) {
                    this.b = distanceResults;
                    return this;
                }

                @Override // com.mappls.sdk.services.api.distance.models.DistanceResponse.Builder
                public DistanceResponse.Builder version(@Nullable String str) {
                    this.f13212a = str;
                    return this;
                }

                public b() {
                }

                public b(DistanceResponse distanceResponse) {
                    this.f13212a = distanceResponse.version();
                    this.b = distanceResponse.results();
                    this.c = Long.valueOf(distanceResponse.responseCode());
                }
            }

            {
                this.version = str;
                this.results = distanceResults;
                this.responseCode = j;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof DistanceResponse) {
                    DistanceResponse distanceResponse = (DistanceResponse) obj;
                    String str2 = this.version;
                    if (str2 != null ? str2.equals(distanceResponse.version()) : distanceResponse.version() == null) {
                        DistanceResults distanceResults2 = this.results;
                        if (distanceResults2 != null ? distanceResults2.equals(distanceResponse.results()) : distanceResponse.results() == null) {
                            if (this.responseCode == distanceResponse.responseCode()) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
                return false;
            }

            public int hashCode() {
                String str2 = this.version;
                int hashCode = ((str2 == null ? 0 : str2.hashCode()) ^ 1000003) * 1000003;
                DistanceResults distanceResults2 = this.results;
                int hashCode2 = distanceResults2 != null ? distanceResults2.hashCode() : 0;
                long j2 = this.responseCode;
                return ((hashCode ^ hashCode2) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
            }

            @Override // com.mappls.sdk.services.api.distance.models.DistanceResponse
            public long responseCode() {
                return this.responseCode;
            }

            @Override // com.mappls.sdk.services.api.distance.models.DistanceResponse
            @Nullable
            @SerializedName("results")
            public DistanceResults results() {
                return this.results;
            }

            @Override // com.mappls.sdk.services.api.distance.models.DistanceResponse
            public DistanceResponse.Builder toBuilder() {
                return new b(this);
            }

            public String toString() {
                return "DistanceResponse{version=" + this.version + ", results=" + this.results + ", responseCode=" + this.responseCode + "}";
            }

            @Override // com.mappls.sdk.services.api.distance.models.DistanceResponse
            @Nullable
            public String version() {
                return this.version;
            }
        };
    }
}
