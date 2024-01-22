package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.BannerInstructions;
import java.io.IOException;
import org.jose4j.jwt.ReservedClaimNames;
/* loaded from: classes11.dex */
public final class AutoValue_BannerInstructions extends b {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<BannerInstructions> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<Double> f13170a;
        public volatile TypeAdapter<Integer> b;
        public volatile TypeAdapter<BannerText> c;
        public final Gson d;

        public GsonTypeAdapter(Gson gson) {
            this.d = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public BannerInstructions read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            BannerInstructions.Builder builder = BannerInstructions.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (!nextName.equals("distance_along_geometry")) {
                        if ("secondary".equals(nextName)) {
                            TypeAdapter<Integer> typeAdapter = this.b;
                            if (typeAdapter == null) {
                                typeAdapter = this.d.getAdapter(Integer.class);
                                this.b = typeAdapter;
                            }
                            builder.secondary(typeAdapter.read(jsonReader));
                        } else if ("primary".equals(nextName)) {
                            TypeAdapter<BannerText> typeAdapter2 = this.c;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.d.getAdapter(BannerText.class);
                                this.c = typeAdapter2;
                            }
                            builder.primary(typeAdapter2.read(jsonReader));
                        } else if (ReservedClaimNames.SUBJECT.equals(nextName)) {
                            TypeAdapter<BannerText> typeAdapter3 = this.c;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.d.getAdapter(BannerText.class);
                                this.c = typeAdapter3;
                            }
                            builder.sub(typeAdapter3.read(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    } else {
                        TypeAdapter<Double> typeAdapter4 = this.f13170a;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.d.getAdapter(Double.class);
                            this.f13170a = typeAdapter4;
                        }
                        builder.distanceAlongGeometry(typeAdapter4.read(jsonReader).doubleValue());
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, BannerInstructions bannerInstructions) throws IOException {
            if (bannerInstructions == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("distance_along_geometry");
            TypeAdapter<Double> typeAdapter = this.f13170a;
            if (typeAdapter == null) {
                typeAdapter = this.d.getAdapter(Double.class);
                this.f13170a = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Double.valueOf(bannerInstructions.distanceAlongGeometry()));
            jsonWriter.name("secondary");
            if (bannerInstructions.secondary() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.d.getAdapter(Integer.class);
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, bannerInstructions.secondary());
            }
            jsonWriter.name("primary");
            if (bannerInstructions.primary() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<BannerText> typeAdapter3 = this.c;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.d.getAdapter(BannerText.class);
                    this.c = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, bannerInstructions.primary());
            }
            jsonWriter.name(ReservedClaimNames.SUBJECT);
            if (bannerInstructions.sub() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<BannerText> typeAdapter4 = this.c;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.d.getAdapter(BannerText.class);
                    this.c = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, bannerInstructions.sub());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(BannerInstructions)";
        }
    }

    public AutoValue_BannerInstructions(double d, @Nullable Integer num, BannerText bannerText, @Nullable BannerText bannerText2) {
        super(d, num, bannerText, bannerText2);
    }
}
