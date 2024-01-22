package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.models.BannerText;
import com.mappls.sdk.turf.TurfConstants;
import java.io.IOException;
import java.util.List;
/* loaded from: classes11.dex */
public final class AutoValue_BannerText extends c {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<BannerText> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<List<BannerComponents>> f13171a;
        public volatile TypeAdapter<String> b;
        public volatile TypeAdapter<Double> c;
        public final Gson d;

        public GsonTypeAdapter(Gson gson) {
            this.d = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public BannerText read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            BannerText.Builder builder = BannerText.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (!nextName.equals("driving_side")) {
                        if ("components".equals(nextName)) {
                            TypeAdapter<List<BannerComponents>> typeAdapter = this.f13171a;
                            if (typeAdapter == null) {
                                typeAdapter = this.d.getAdapter(TypeToken.getParameterized(List.class, BannerComponents.class));
                                this.f13171a = typeAdapter;
                            }
                            builder.components(typeAdapter.read(jsonReader));
                        } else if ("text".equals(nextName)) {
                            TypeAdapter<String> typeAdapter2 = this.b;
                            if (typeAdapter2 == null) {
                                typeAdapter2 = this.d.getAdapter(String.class);
                                this.b = typeAdapter2;
                            }
                            builder.text(typeAdapter2.read(jsonReader));
                        } else if ("modifier".equals(nextName)) {
                            TypeAdapter<String> typeAdapter3 = this.b;
                            if (typeAdapter3 == null) {
                                typeAdapter3 = this.d.getAdapter(String.class);
                                this.b = typeAdapter3;
                            }
                            builder.modifier(typeAdapter3.read(jsonReader));
                        } else if (TurfConstants.UNIT_DEGREES.equals(nextName)) {
                            TypeAdapter<Double> typeAdapter4 = this.c;
                            if (typeAdapter4 == null) {
                                typeAdapter4 = this.d.getAdapter(Double.class);
                                this.c = typeAdapter4;
                            }
                            builder.degrees(typeAdapter4.read(jsonReader));
                        } else if ("type".equals(nextName)) {
                            TypeAdapter<String> typeAdapter5 = this.b;
                            if (typeAdapter5 == null) {
                                typeAdapter5 = this.d.getAdapter(String.class);
                                this.b = typeAdapter5;
                            }
                            builder.type(typeAdapter5.read(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    } else {
                        TypeAdapter<String> typeAdapter6 = this.b;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.d.getAdapter(String.class);
                            this.b = typeAdapter6;
                        }
                        builder.drivingSide(typeAdapter6.read(jsonReader));
                    }
                }
            }
            jsonReader.endObject();
            return builder.build();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: b */
        public void write(JsonWriter jsonWriter, BannerText bannerText) throws IOException {
            if (bannerText == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("components");
            if (bannerText.components() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<BannerComponents>> typeAdapter = this.f13171a;
                if (typeAdapter == null) {
                    typeAdapter = this.d.getAdapter(TypeToken.getParameterized(List.class, BannerComponents.class));
                    this.f13171a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, bannerText.components());
            }
            jsonWriter.name("text");
            if (bannerText.text() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.b;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.d.getAdapter(String.class);
                    this.b = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, bannerText.text());
            }
            jsonWriter.name("modifier");
            if (bannerText.modifier() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.b;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.d.getAdapter(String.class);
                    this.b = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, bannerText.modifier());
            }
            jsonWriter.name(TurfConstants.UNIT_DEGREES);
            if (bannerText.degrees() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter4 = this.c;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.d.getAdapter(Double.class);
                    this.c = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, bannerText.degrees());
            }
            jsonWriter.name("driving_side");
            if (bannerText.drivingSide() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.b;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.d.getAdapter(String.class);
                    this.b = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, bannerText.drivingSide());
            }
            jsonWriter.name("type");
            if (bannerText.type() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter6 = this.b;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.d.getAdapter(String.class);
                    this.b = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, bannerText.type());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(BannerText)";
        }
    }

    public AutoValue_BannerText(@Nullable List<BannerComponents> list, String str, @Nullable String str2, @Nullable Double d, @Nullable String str3, @Nullable String str4) {
        super(list, str, str2, d, str3, str4);
    }
}
