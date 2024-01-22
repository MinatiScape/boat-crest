package com.mappls.sdk.geojson;

import androidx.annotation.Keep;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mappls.sdk.geojson.exception.GeoJsonException;
import com.mappls.sdk.geojson.shifter.CoordinateShifterManager;
import com.mappls.sdk.geojson.utils.GeoJsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
@Keep
/* loaded from: classes11.dex */
public abstract class BaseCoordinatesTypeAdapter<T> extends TypeAdapter<T> {
    public Point readPoint(JsonReader jsonReader) throws IOException {
        List<Double> readPointList = readPointList(jsonReader);
        if (readPointList != null && readPointList.size() > 1) {
            return new Point("Point", null, readPointList);
        }
        throw new GeoJsonException(" Point coordinates should be non-null double array");
    }

    public List<Double> readPointList(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.NULL) {
            ArrayList arrayList = new ArrayList();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                arrayList.add(Double.valueOf(jsonReader.nextDouble()));
            }
            jsonReader.endArray();
            if (arrayList.size() > 2) {
                return CoordinateShifterManager.getCoordinateShifter().shiftLonLatAlt(((Double) arrayList.get(0)).doubleValue(), ((Double) arrayList.get(1)).doubleValue(), ((Double) arrayList.get(2)).doubleValue());
            }
            return CoordinateShifterManager.getCoordinateShifter().shiftLonLat(((Double) arrayList.get(0)).doubleValue(), ((Double) arrayList.get(1)).doubleValue());
        }
        throw null;
    }

    public void writePoint(JsonWriter jsonWriter, Point point) throws IOException {
        if (point == null) {
            return;
        }
        writePointList(jsonWriter, point.coordinates());
    }

    public void writePointList(JsonWriter jsonWriter, List<Double> list) throws IOException {
        if (list == null) {
            return;
        }
        jsonWriter.beginArray();
        List<Double> unshiftPoint = CoordinateShifterManager.getCoordinateShifter().unshiftPoint(list);
        jsonWriter.value(GeoJsonUtils.trim(unshiftPoint.get(0).doubleValue()));
        jsonWriter.value(GeoJsonUtils.trim(unshiftPoint.get(1).doubleValue()));
        if (list.size() > 2) {
            jsonWriter.value(unshiftPoint.get(2));
        }
        jsonWriter.endArray();
    }
}
