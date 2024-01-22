package com.mappls.sdk.geojson;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.geojson.gson.GeoJsonAdapterFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Keep
/* loaded from: classes11.dex */
public final class MultiLineString implements CoordinateContainer<List<List<Point>>> {
    private static final String TYPE = "MultiLineString";
    private final BoundingBox bbox;
    private final List<List<Point>> coordinates;
    private final String type;

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends BaseGeometryTypeAdapter<MultiLineString, List<List<Point>>> {
        public GsonTypeAdapter(Gson gson) {
            super(gson, new ListOfListOfPointCoordinatesTypeAdapter());
        }

        @Override // com.mappls.sdk.geojson.BaseGeometryTypeAdapter
        public CoordinateContainer<List<List<Point>>> createCoordinateContainer(String str, BoundingBox boundingBox, List<List<Point>> list) {
            if (str == null) {
                str = MultiLineString.TYPE;
            }
            return new MultiLineString(str, boundingBox, list);
        }

        @Override // com.google.gson.TypeAdapter
        public MultiLineString read(JsonReader jsonReader) throws IOException {
            return (MultiLineString) readCoordinateContainer(jsonReader);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, MultiLineString multiLineString) throws IOException {
            writeCoordinateContainer(jsonWriter, multiLineString);
        }
    }

    public MultiLineString(String str, @Nullable BoundingBox boundingBox, List<List<Point>> list) {
        Objects.requireNonNull(str, "Null type");
        this.type = str;
        this.bbox = boundingBox;
        Objects.requireNonNull(list, "Null coordinates");
        this.coordinates = list;
    }

    public static MultiLineString fromJson(@NonNull String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        return (MultiLineString) gsonBuilder.create().fromJson(str, (Class<Object>) MultiLineString.class);
    }

    public static MultiLineString fromLineString(@NonNull LineString lineString) {
        return new MultiLineString(TYPE, null, Arrays.asList(lineString.coordinates()));
    }

    public static MultiLineString fromLineStrings(@NonNull List<LineString> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (LineString lineString : list) {
            arrayList.add(lineString.coordinates());
        }
        return new MultiLineString(TYPE, null, arrayList);
    }

    public static MultiLineString fromLngLats(@NonNull List<List<Point>> list) {
        return new MultiLineString(TYPE, null, list);
    }

    public static TypeAdapter<MultiLineString> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    @Override // com.mappls.sdk.geojson.GeoJson
    @Nullable
    public BoundingBox bbox() {
        return this.bbox;
    }

    public boolean equals(Object obj) {
        BoundingBox boundingBox;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MultiLineString) {
            MultiLineString multiLineString = (MultiLineString) obj;
            return this.type.equals(multiLineString.type()) && ((boundingBox = this.bbox) != null ? boundingBox.equals(multiLineString.bbox()) : multiLineString.bbox() == null) && this.coordinates.equals(multiLineString.coordinates());
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.type.hashCode() ^ 1000003) * 1000003;
        BoundingBox boundingBox = this.bbox;
        return ((hashCode ^ (boundingBox == null ? 0 : boundingBox.hashCode())) * 1000003) ^ this.coordinates.hashCode();
    }

    public List<LineString> lineStrings() {
        List<List<Point>> coordinates = coordinates();
        ArrayList arrayList = new ArrayList(coordinates.size());
        for (List<Point> list : coordinates) {
            arrayList.add(LineString.fromLngLats(list));
        }
        return arrayList;
    }

    @Override // com.mappls.sdk.geojson.GeoJson
    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        return gsonBuilder.create().toJson(this);
    }

    public String toString() {
        return "MultiLineString{type=" + this.type + ", bbox=" + this.bbox + ", coordinates=" + this.coordinates + "}";
    }

    @Override // com.mappls.sdk.geojson.GeoJson
    @NonNull
    public String type() {
        return this.type;
    }

    public static MultiLineString fromLngLats(@NonNull List<List<Point>> list, @Nullable BoundingBox boundingBox) {
        return new MultiLineString(TYPE, boundingBox, list);
    }

    @Override // com.mappls.sdk.geojson.CoordinateContainer
    @NonNull
    public List<List<Point>> coordinates() {
        return this.coordinates;
    }

    public static MultiLineString fromLineString(@NonNull LineString lineString, @Nullable BoundingBox boundingBox) {
        return new MultiLineString(TYPE, boundingBox, Arrays.asList(lineString.coordinates()));
    }

    public static MultiLineString fromLngLats(double[][][] dArr) {
        ArrayList arrayList = new ArrayList(dArr.length);
        for (int i = 0; i < dArr.length; i++) {
            ArrayList arrayList2 = new ArrayList(dArr[i].length);
            for (int i2 = 0; i2 < dArr[i].length; i2++) {
                arrayList2.add(Point.fromLngLat(dArr[i][i2]));
            }
            arrayList.add(arrayList2);
        }
        return new MultiLineString(TYPE, null, arrayList);
    }

    public static MultiLineString fromLineStrings(@NonNull List<LineString> list, @Nullable BoundingBox boundingBox) {
        ArrayList arrayList = new ArrayList(list.size());
        for (LineString lineString : list) {
            arrayList.add(lineString.coordinates());
        }
        return new MultiLineString(TYPE, boundingBox, arrayList);
    }
}
