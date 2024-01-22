package com.mappls.sdk.plugin.annotation;

import android.graphics.PointF;
import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.Polygon;
import com.mappls.sdk.gestures.MoveDistancesObject;
import com.mappls.sdk.maps.Projection;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.utils.ColorUtils;
import java.util.ArrayList;
import java.util.List;
@Keep
@UiThread
/* loaded from: classes11.dex */
public class Fill extends Annotation<Polygon> {
    private final AnnotationManager<?, Fill, ?, ?, ?, ?> annotationManager;

    public Fill(long j, AnnotationManager<?, Fill, ?, ?, ?, ?> annotationManager, JsonObject jsonObject, Polygon polygon) {
        super(j, jsonObject, polygon);
        this.annotationManager = annotationManager;
    }

    public String getFillColor() {
        if (this.jsonObject.get(FillOptions.PROPERTY_FILL_COLOR).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(FillOptions.PROPERTY_FILL_COLOR).getAsString();
    }

    @ColorInt
    public Integer getFillColorAsInt() {
        if (this.jsonObject.get(FillOptions.PROPERTY_FILL_COLOR).isJsonNull()) {
            return null;
        }
        return Integer.valueOf(ColorUtils.rgbaToColor(this.jsonObject.get(FillOptions.PROPERTY_FILL_COLOR).getAsString()));
    }

    public Float getFillOpacity() {
        if (this.jsonObject.get(FillOptions.PROPERTY_FILL_OPACITY).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(FillOptions.PROPERTY_FILL_OPACITY).getAsFloat());
    }

    public String getFillOutlineColor() {
        if (this.jsonObject.get(FillOptions.PROPERTY_FILL_OUTLINE_COLOR).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(FillOptions.PROPERTY_FILL_OUTLINE_COLOR).getAsString();
    }

    @ColorInt
    public Integer getFillOutlineColorAsInt() {
        if (this.jsonObject.get(FillOptions.PROPERTY_FILL_OUTLINE_COLOR).isJsonNull()) {
            return null;
        }
        return Integer.valueOf(ColorUtils.rgbaToColor(this.jsonObject.get(FillOptions.PROPERTY_FILL_OUTLINE_COLOR).getAsString()));
    }

    public String getFillPattern() {
        if (this.jsonObject.get(FillOptions.PROPERTY_FILL_PATTERN).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(FillOptions.PROPERTY_FILL_PATTERN).getAsString();
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    public String getName() {
        return "Fill";
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    @Nullable
    public Geometry getOffsetGeometry(@NonNull Projection projection, @NonNull MoveDistancesObject moveDistancesObject, float f, float f2) {
        List<List<Point>> coordinates = ((Polygon) this.geometry).coordinates();
        if (coordinates != null) {
            ArrayList arrayList = new ArrayList(coordinates.size());
            for (List<Point> list : coordinates) {
                ArrayList arrayList2 = new ArrayList();
                for (Point point : list) {
                    PointF screenLocation = projection.toScreenLocation(new LatLng(point.latitude(), point.longitude()));
                    screenLocation.x -= moveDistancesObject.getDistanceXSinceLast();
                    screenLocation.y -= moveDistancesObject.getDistanceYSinceLast();
                    LatLng fromScreenLocation = projection.fromScreenLocation(screenLocation);
                    if (fromScreenLocation.getLatitude() > 85.05112877980659d || fromScreenLocation.getLatitude() < -85.05112877980659d) {
                        return null;
                    }
                    arrayList2.add(Point.fromLngLat(fromScreenLocation.getLongitude(), fromScreenLocation.getLatitude()));
                }
                arrayList.add(arrayList2);
            }
            return Polygon.fromLngLats(arrayList);
        }
        return null;
    }

    @NonNull
    public List<List<LatLng>> getPoints() {
        ArrayList arrayList = new ArrayList();
        List<List<Point>> coordinates = ((Polygon) this.geometry).coordinates();
        if (coordinates != null) {
            for (List<Point> list : coordinates) {
                ArrayList arrayList2 = new ArrayList();
                for (Point point : list) {
                    arrayList2.add(new LatLng(point.latitude(), point.longitude()));
                }
                arrayList.add(arrayList2);
            }
        }
        return arrayList;
    }

    public void setFillColor(@ColorInt int i) {
        this.jsonObject.addProperty(FillOptions.PROPERTY_FILL_COLOR, ColorUtils.colorToRgbaString(i));
    }

    public void setFillColor(@NonNull String str) {
        this.jsonObject.addProperty(FillOptions.PROPERTY_FILL_COLOR, str);
    }

    public void setFillOpacity(Float f) {
        this.jsonObject.addProperty(FillOptions.PROPERTY_FILL_OPACITY, f);
    }

    public void setFillOutlineColor(@ColorInt int i) {
        this.jsonObject.addProperty(FillOptions.PROPERTY_FILL_OUTLINE_COLOR, ColorUtils.colorToRgbaString(i));
    }

    public void setFillOutlineColor(@NonNull String str) {
        this.jsonObject.addProperty(FillOptions.PROPERTY_FILL_OUTLINE_COLOR, str);
    }

    public void setFillPattern(String str) {
        this.jsonObject.addProperty(FillOptions.PROPERTY_FILL_PATTERN, str);
    }

    public void setPoints(List<List<LatLng>> list) {
        ArrayList arrayList = new ArrayList();
        for (List<LatLng> list2 : list) {
            ArrayList arrayList2 = new ArrayList();
            for (LatLng latLng : list2) {
                arrayList2.add(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()));
            }
            arrayList.add(arrayList2);
        }
        this.geometry = Polygon.fromLngLats(arrayList);
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    public void setUsedDataDrivenProperties() {
        if (!(this.jsonObject.get(FillOptions.PROPERTY_FILL_OPACITY) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(FillOptions.PROPERTY_FILL_OPACITY);
        }
        if (!(this.jsonObject.get(FillOptions.PROPERTY_FILL_COLOR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(FillOptions.PROPERTY_FILL_COLOR);
        }
        if (!(this.jsonObject.get(FillOptions.PROPERTY_FILL_OUTLINE_COLOR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(FillOptions.PROPERTY_FILL_OUTLINE_COLOR);
        }
        if (this.jsonObject.get(FillOptions.PROPERTY_FILL_PATTERN) instanceof JsonNull) {
            return;
        }
        this.annotationManager.enableDataDrivenProperty(FillOptions.PROPERTY_FILL_PATTERN);
    }
}
