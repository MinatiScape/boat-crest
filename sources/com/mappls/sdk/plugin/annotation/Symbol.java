package com.mappls.sdk.plugin.annotation;

import android.graphics.Bitmap;
import android.graphics.PointF;
import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.gestures.MoveDistancesObject;
import com.mappls.sdk.maps.Projection;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.utils.ColorUtils;
@Keep
@UiThread
/* loaded from: classes11.dex */
public class Symbol extends Annotation<Point> {
    private final AnnotationManager<?, Symbol, ?, ?, ?, ?> annotationManager;
    public Bitmap icon;
    public String mapplsPin;

    public Symbol(long j, AnnotationManager<?, Symbol, ?, ?, ?, ?> annotationManager, JsonObject jsonObject, Point point, String str, Bitmap bitmap) {
        super(j, jsonObject, point);
        this.annotationManager = annotationManager;
        this.mapplsPin = str;
        this.icon = bitmap;
    }

    public String getIconAnchor() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_ANCHOR).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(SymbolOptions.PROPERTY_ICON_ANCHOR).getAsString();
    }

    public String getIconColor() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_COLOR).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(SymbolOptions.PROPERTY_ICON_COLOR).getAsString();
    }

    @ColorInt
    public Integer getIconColorAsInt() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_COLOR).isJsonNull()) {
            return null;
        }
        return Integer.valueOf(ColorUtils.rgbaToColor(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_COLOR).getAsString()));
    }

    public Float getIconHaloBlur() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_HALO_BLUR).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_HALO_BLUR).getAsFloat());
    }

    public String getIconHaloColor() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_HALO_COLOR).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(SymbolOptions.PROPERTY_ICON_HALO_COLOR).getAsString();
    }

    @ColorInt
    public Integer getIconHaloColorAsInt() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_HALO_COLOR).isJsonNull()) {
            return null;
        }
        return Integer.valueOf(ColorUtils.rgbaToColor(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_HALO_COLOR).getAsString()));
    }

    public Float getIconHaloWidth() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_HALO_WIDTH).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_HALO_WIDTH).getAsFloat());
    }

    public String getIconImage() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_IMAGE).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(SymbolOptions.PROPERTY_ICON_IMAGE).getAsString();
    }

    public PointF getIconOffset() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_OFFSET).isJsonNull()) {
            return null;
        }
        JsonArray asJsonArray = this.jsonObject.getAsJsonArray(SymbolOptions.PROPERTY_ICON_OFFSET);
        return new PointF(asJsonArray.get(0).getAsFloat(), asJsonArray.get(1).getAsFloat());
    }

    public Float getIconOpacity() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_OPACITY).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_OPACITY).getAsFloat());
    }

    public Float getIconRotate() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_ROTATE).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_ROTATE).getAsFloat());
    }

    public Float getIconSize() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_ICON_SIZE).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_SIZE).getAsFloat());
    }

    @Nullable
    public String getMapplsPin() {
        return this.mapplsPin;
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    public String getName() {
        return "Symbol";
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    @Nullable
    public Geometry getOffsetGeometry(@NonNull Projection projection, @NonNull MoveDistancesObject moveDistancesObject, float f, float f2) {
        LatLng fromScreenLocation = projection.fromScreenLocation(new PointF(moveDistancesObject.getCurrentX() - f, moveDistancesObject.getCurrentY() - f2));
        if (fromScreenLocation.getLatitude() > 85.05112877980659d || fromScreenLocation.getLatitude() < -85.05112877980659d) {
            return null;
        }
        return Point.fromLngLat(fromScreenLocation.getLongitude(), fromScreenLocation.getLatitude());
    }

    @Nullable
    public LatLng getPosition() {
        if (this.geometry == 0) {
            return null;
        }
        return new LatLng(((Point) this.geometry).latitude(), ((Point) this.geometry).longitude());
    }

    public Float getSymbolSortKey() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_SYMBOL_SORT_KEY).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_SYMBOL_SORT_KEY).getAsFloat());
    }

    public String getTextAnchor() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_ANCHOR).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_ANCHOR).getAsString();
    }

    public String getTextColor() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_COLOR).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_COLOR).getAsString();
    }

    @ColorInt
    public Integer getTextColorAsInt() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_COLOR).isJsonNull()) {
            return null;
        }
        return Integer.valueOf(ColorUtils.rgbaToColor(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_COLOR).getAsString()));
    }

    public String getTextField() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_FIELD).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_FIELD).getAsString();
    }

    public String[] getTextFont() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_FONT).isJsonNull()) {
            return null;
        }
        JsonArray asJsonArray = this.jsonObject.getAsJsonArray(SymbolOptions.PROPERTY_TEXT_FONT);
        String[] strArr = new String[asJsonArray.size()];
        for (int i = 0; i < asJsonArray.size(); i++) {
            strArr[i] = asJsonArray.get(i).getAsString();
        }
        return strArr;
    }

    public Float getTextHaloBlur() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_HALO_BLUR).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_HALO_BLUR).getAsFloat());
    }

    public String getTextHaloColor() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_HALO_COLOR).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_HALO_COLOR).getAsString();
    }

    @ColorInt
    public Integer getTextHaloColorAsInt() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_HALO_COLOR).isJsonNull()) {
            return null;
        }
        return Integer.valueOf(ColorUtils.rgbaToColor(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_HALO_COLOR).getAsString()));
    }

    public Float getTextHaloWidth() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_HALO_WIDTH).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_HALO_WIDTH).getAsFloat());
    }

    public String getTextJustify() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_JUSTIFY).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_JUSTIFY).getAsString();
    }

    public Float getTextLetterSpacing() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_LETTER_SPACING).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_LETTER_SPACING).getAsFloat());
    }

    public Float getTextMaxWidth() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_MAX_WIDTH).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_MAX_WIDTH).getAsFloat());
    }

    public PointF getTextOffset() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_OFFSET).isJsonNull()) {
            return null;
        }
        JsonArray asJsonArray = this.jsonObject.getAsJsonArray(SymbolOptions.PROPERTY_TEXT_OFFSET);
        return new PointF(asJsonArray.get(0).getAsFloat(), asJsonArray.get(1).getAsFloat());
    }

    public Float getTextOpacity() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_OPACITY).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_OPACITY).getAsFloat());
    }

    public Float getTextRadialOffset() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_RADIAL_OFFSET).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_RADIAL_OFFSET).getAsFloat());
    }

    public Float getTextRotate() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_ROTATE).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_ROTATE).getAsFloat());
    }

    public Float getTextSize() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_SIZE).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_SIZE).getAsFloat());
    }

    public String getTextTransform() {
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_TRANSFORM).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_TRANSFORM).getAsString();
    }

    public void setIconAnchor(String str) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_ICON_ANCHOR, str);
    }

    public void setIconColor(@ColorInt int i) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_ICON_COLOR, ColorUtils.colorToRgbaString(i));
    }

    public void setIconColor(@NonNull String str) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_ICON_COLOR, str);
    }

    public void setIconHaloBlur(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_ICON_HALO_BLUR, f);
    }

    public void setIconHaloColor(@ColorInt int i) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_ICON_HALO_COLOR, ColorUtils.colorToRgbaString(i));
    }

    public void setIconHaloColor(@NonNull String str) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_ICON_HALO_COLOR, str);
    }

    public void setIconHaloWidth(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_ICON_HALO_WIDTH, f);
    }

    public void setIconImage(String str) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_ICON_IMAGE, str);
    }

    public void setIconOffset(PointF pointF) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(Float.valueOf(pointF.x));
        jsonArray.add(Float.valueOf(pointF.y));
        this.jsonObject.add(SymbolOptions.PROPERTY_ICON_OFFSET, jsonArray);
    }

    public void setIconOpacity(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_ICON_OPACITY, f);
    }

    public void setIconRotate(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_ICON_ROTATE, f);
    }

    public void setIconSize(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_ICON_SIZE, f);
    }

    public void setMapplsPin(String str) {
        this.geometry = null;
        this.mapplsPin = str;
    }

    public void setPosition(LatLng latLng) {
        this.mapplsPin = null;
        this.geometry = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
    }

    public void setSymbolSortKey(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_SYMBOL_SORT_KEY, f);
    }

    public void setTextAnchor(String str) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_ANCHOR, str);
    }

    public void setTextColor(@ColorInt int i) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_COLOR, ColorUtils.colorToRgbaString(i));
    }

    public void setTextColor(@NonNull String str) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_COLOR, str);
    }

    public void setTextField(String str) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_FIELD, str);
    }

    public void setTextFont(String[] strArr) {
        JsonArray jsonArray = new JsonArray();
        for (String str : strArr) {
            jsonArray.add(str);
        }
        this.jsonObject.add(SymbolOptions.PROPERTY_TEXT_FONT, jsonArray);
    }

    public void setTextHaloBlur(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_HALO_BLUR, f);
    }

    public void setTextHaloColor(@ColorInt int i) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_HALO_COLOR, ColorUtils.colorToRgbaString(i));
    }

    public void setTextHaloColor(@NonNull String str) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_HALO_COLOR, str);
    }

    public void setTextHaloWidth(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_HALO_WIDTH, f);
    }

    public void setTextJustify(String str) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_JUSTIFY, str);
    }

    public void setTextLetterSpacing(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_LETTER_SPACING, f);
    }

    public void setTextMaxWidth(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_MAX_WIDTH, f);
    }

    public void setTextOffset(PointF pointF) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(Float.valueOf(pointF.x));
        jsonArray.add(Float.valueOf(pointF.y));
        this.jsonObject.add(SymbolOptions.PROPERTY_TEXT_OFFSET, jsonArray);
    }

    public void setTextOpacity(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_OPACITY, f);
    }

    public void setTextRadialOffset(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_RADIAL_OFFSET, f);
    }

    public void setTextRotate(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_ROTATE, f);
    }

    public void setTextSize(Float f) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_SIZE, f);
    }

    public void setTextTransform(String str) {
        this.jsonObject.addProperty(SymbolOptions.PROPERTY_TEXT_TRANSFORM, str);
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    public void setUsedDataDrivenProperties() {
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_SYMBOL_SORT_KEY) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_SYMBOL_SORT_KEY);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_SIZE) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_ICON_SIZE);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_IMAGE) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_ICON_IMAGE);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_ROTATE) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_ICON_ROTATE);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_OFFSET) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_ICON_OFFSET);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_ANCHOR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_ICON_ANCHOR);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_FIELD) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_FIELD);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_FONT) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_FONT);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_SIZE) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_SIZE);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_MAX_WIDTH) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_MAX_WIDTH);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_LETTER_SPACING) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_LETTER_SPACING);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_JUSTIFY) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_JUSTIFY);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_RADIAL_OFFSET) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_RADIAL_OFFSET);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_ANCHOR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_ANCHOR);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_ROTATE) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_ROTATE);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_TRANSFORM) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_TRANSFORM);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_OFFSET) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_OFFSET);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_OPACITY) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_ICON_OPACITY);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_COLOR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_ICON_COLOR);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_HALO_COLOR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_ICON_HALO_COLOR);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_HALO_WIDTH) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_ICON_HALO_WIDTH);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_ICON_HALO_BLUR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_ICON_HALO_BLUR);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_OPACITY) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_OPACITY);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_COLOR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_COLOR);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_HALO_COLOR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_HALO_COLOR);
        }
        if (!(this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_HALO_WIDTH) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_HALO_WIDTH);
        }
        if (this.jsonObject.get(SymbolOptions.PROPERTY_TEXT_HALO_BLUR) instanceof JsonNull) {
            return;
        }
        this.annotationManager.enableDataDrivenProperty(SymbolOptions.PROPERTY_TEXT_HALO_BLUR);
    }
}
