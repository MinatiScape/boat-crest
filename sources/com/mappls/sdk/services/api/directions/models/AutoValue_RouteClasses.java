package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.models.RouteClasses;
import java.io.IOException;
/* loaded from: classes11.dex */
public final class AutoValue_RouteClasses extends l {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<RouteClasses> {

        /* renamed from: a  reason: collision with root package name */
        public volatile TypeAdapter<Integer> f13180a;
        public final Gson b;

        public GsonTypeAdapter(Gson gson) {
            this.b = gson;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: a */
        public RouteClasses read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            RouteClasses.Builder builder = RouteClasses.builder();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (DirectionsCriteria.EXCLUDE_MOTORWAY.equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter = this.f13180a;
                        if (typeAdapter == null) {
                            typeAdapter = this.b.getAdapter(Integer.class);
                            this.f13180a = typeAdapter;
                        }
                        builder.motorway(typeAdapter.read(jsonReader));
                    } else if (DirectionsCriteria.EXCLUDE_TUNNEL.equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter2 = this.f13180a;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.b.getAdapter(Integer.class);
                            this.f13180a = typeAdapter2;
                        }
                        builder.tunnel(typeAdapter2.read(jsonReader));
                    } else if (DirectionsCriteria.EXCLUDE_TOLL.equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter3 = this.f13180a;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.b.getAdapter(Integer.class);
                            this.f13180a = typeAdapter3;
                        }
                        builder.toll(typeAdapter3.read(jsonReader));
                    } else if (DirectionsCriteria.EXCLUDE_FERRY.equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter4 = this.f13180a;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.b.getAdapter(Integer.class);
                            this.f13180a = typeAdapter4;
                        }
                        builder.ferry(typeAdapter4.read(jsonReader));
                    } else if (DirectionsCriteria.EXCLUDE_RESTRICTED.equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter5 = this.f13180a;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.b.getAdapter(Integer.class);
                            this.f13180a = typeAdapter5;
                        }
                        builder.restricted(typeAdapter5.read(jsonReader));
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
        public void write(JsonWriter jsonWriter, RouteClasses routeClasses) throws IOException {
            if (routeClasses == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(DirectionsCriteria.EXCLUDE_MOTORWAY);
            if (routeClasses.motorway() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter = this.f13180a;
                if (typeAdapter == null) {
                    typeAdapter = this.b.getAdapter(Integer.class);
                    this.f13180a = typeAdapter;
                }
                typeAdapter.write(jsonWriter, routeClasses.motorway());
            }
            jsonWriter.name(DirectionsCriteria.EXCLUDE_TUNNEL);
            if (routeClasses.tunnel() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter2 = this.f13180a;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.b.getAdapter(Integer.class);
                    this.f13180a = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, routeClasses.tunnel());
            }
            jsonWriter.name(DirectionsCriteria.EXCLUDE_TOLL);
            if (routeClasses.toll() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter3 = this.f13180a;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.b.getAdapter(Integer.class);
                    this.f13180a = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, routeClasses.toll());
            }
            jsonWriter.name(DirectionsCriteria.EXCLUDE_FERRY);
            if (routeClasses.ferry() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter4 = this.f13180a;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.b.getAdapter(Integer.class);
                    this.f13180a = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, routeClasses.ferry());
            }
            jsonWriter.name(DirectionsCriteria.EXCLUDE_RESTRICTED);
            if (routeClasses.restricted() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter5 = this.f13180a;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.b.getAdapter(Integer.class);
                    this.f13180a = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, routeClasses.restricted());
            }
            jsonWriter.endObject();
        }

        public String toString() {
            return "TypeAdapter(RouteClasses)";
        }
    }

    public AutoValue_RouteClasses(@Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Integer num5) {
        super(num, num2, num3, num4, num5);
    }
}
