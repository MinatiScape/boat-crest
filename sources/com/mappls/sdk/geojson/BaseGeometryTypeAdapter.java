package com.mappls.sdk.geojson;

import androidx.annotation.Keep;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.geojson.exception.GeoJsonException;
import com.mappls.sdk.geojson.gson.BoundingBoxTypeAdapter;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
@Keep
/* loaded from: classes11.dex */
public abstract class BaseGeometryTypeAdapter<G, T> extends TypeAdapter<G> {
    private volatile TypeAdapter<BoundingBox> boundingBoxAdapter = new BoundingBoxTypeAdapter();
    private volatile TypeAdapter<T> coordinatesAdapter;
    private final Gson gson;
    private volatile TypeAdapter<String> stringAdapter;

    public BaseGeometryTypeAdapter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.coordinatesAdapter = typeAdapter;
    }

    public abstract CoordinateContainer<T> createCoordinateContainer(String str, BoundingBox boundingBox, T t);

    public CoordinateContainer<T> readCoordinateContainer(JsonReader jsonReader) throws IOException {
        String str = null;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        jsonReader.beginObject();
        BoundingBox boundingBox = null;
        T t = null;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
            } else {
                nextName.hashCode();
                char c = 65535;
                switch (nextName.hashCode()) {
                    case 3017257:
                        if (nextName.equals("bbox")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 3575610:
                        if (nextName.equals("type")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1871919611:
                        if (nextName.equals("coordinates")) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        TypeAdapter<BoundingBox> typeAdapter = this.boundingBoxAdapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(BoundingBox.class);
                            this.boundingBoxAdapter = typeAdapter;
                        }
                        boundingBox = typeAdapter.read(jsonReader);
                        continue;
                    case 1:
                        TypeAdapter<String> typeAdapter2 = this.stringAdapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.stringAdapter = typeAdapter2;
                        }
                        str = typeAdapter2.read(jsonReader);
                        continue;
                    case 2:
                        TypeAdapter<T> typeAdapter3 = this.coordinatesAdapter;
                        if (typeAdapter3 != null) {
                            t = typeAdapter3.read(jsonReader);
                            continue;
                        } else {
                            throw new GeoJsonException("Coordinates type adapter is null");
                        }
                    default:
                        jsonReader.skipValue();
                        continue;
                }
            }
        }
        jsonReader.endObject();
        return createCoordinateContainer(str, boundingBox, t);
    }

    public void writeCoordinateContainer(JsonWriter jsonWriter, CoordinateContainer<T> coordinateContainer) throws IOException {
        if (coordinateContainer == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        jsonWriter.name("type");
        if (coordinateContainer.type() == null) {
            jsonWriter.nullValue();
        } else {
            TypeAdapter<String> typeAdapter = this.stringAdapter;
            if (typeAdapter == null) {
                typeAdapter = this.gson.getAdapter(String.class);
                this.stringAdapter = typeAdapter;
            }
            typeAdapter.write(jsonWriter, coordinateContainer.type());
        }
        jsonWriter.name("bbox");
        if (coordinateContainer.bbox() == null) {
            jsonWriter.nullValue();
        } else {
            TypeAdapter<BoundingBox> typeAdapter2 = this.boundingBoxAdapter;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.gson.getAdapter(BoundingBox.class);
                this.boundingBoxAdapter = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, coordinateContainer.bbox());
        }
        jsonWriter.name("coordinates");
        if (coordinateContainer.coordinates() == null) {
            jsonWriter.nullValue();
        } else {
            TypeAdapter<T> typeAdapter3 = this.coordinatesAdapter;
            if (typeAdapter3 != null) {
                typeAdapter3.write(jsonWriter, coordinateContainer.coordinates());
            } else {
                throw new GeoJsonException("Coordinates type adapter is null");
            }
        }
        jsonWriter.endObject();
    }
}
