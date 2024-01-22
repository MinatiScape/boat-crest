package com.mappls.sdk.geojson;

import androidx.annotation.Keep;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.geojson.exception.GeoJsonException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
@Keep
/* loaded from: classes11.dex */
public class ListOfPointCoordinatesTypeAdapter extends BaseCoordinatesTypeAdapter<List<Point>> {
    @Override // com.google.gson.TypeAdapter
    public List<Point> read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.NULL) {
            if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
                ArrayList arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
                    arrayList.add(readPoint(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            }
            throw new GeoJsonException("coordinates should be non-null array of array of double");
        }
        throw null;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, List<Point> list) throws IOException {
        if (list == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        for (Point point : list) {
            writePoint(jsonWriter, point);
        }
        jsonWriter.endArray();
    }
}
