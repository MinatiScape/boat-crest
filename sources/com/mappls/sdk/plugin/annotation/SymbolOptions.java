package com.mappls.sdk.plugin.annotation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import java.util.concurrent.atomic.AtomicLong;
@Keep
/* loaded from: classes11.dex */
public class SymbolOptions extends g<Symbol> {
    private static final String ICON_IMAGE_NAME = "icon_image_name_";
    private static final AtomicLong IMAGE_ID_GENERATOR = new AtomicLong(0);
    public static final String PROPERTY_ICON_ANCHOR = "icon-anchor";
    public static final String PROPERTY_ICON_COLOR = "icon-color";
    public static final String PROPERTY_ICON_HALO_BLUR = "icon-halo-blur";
    public static final String PROPERTY_ICON_HALO_COLOR = "icon-halo-color";
    public static final String PROPERTY_ICON_HALO_WIDTH = "icon-halo-width";
    public static final String PROPERTY_ICON_IMAGE = "icon-image";
    public static final String PROPERTY_ICON_OFFSET = "icon-offset";
    public static final String PROPERTY_ICON_OPACITY = "icon-opacity";
    public static final String PROPERTY_ICON_ROTATE = "icon-rotate";
    public static final String PROPERTY_ICON_SIZE = "icon-size";
    private static final String PROPERTY_IS_DRAGGABLE = "is-draggable";
    public static final String PROPERTY_SYMBOL_SORT_KEY = "symbol-sort-key";
    public static final String PROPERTY_TEXT_ANCHOR = "text-anchor";
    public static final String PROPERTY_TEXT_COLOR = "text-color";
    public static final String PROPERTY_TEXT_FIELD = "text-field";
    public static final String PROPERTY_TEXT_FONT = "text-font";
    public static final String PROPERTY_TEXT_HALO_BLUR = "text-halo-blur";
    public static final String PROPERTY_TEXT_HALO_COLOR = "text-halo-color";
    public static final String PROPERTY_TEXT_HALO_WIDTH = "text-halo-width";
    public static final String PROPERTY_TEXT_JUSTIFY = "text-justify";
    public static final String PROPERTY_TEXT_LETTER_SPACING = "text-letter-spacing";
    public static final String PROPERTY_TEXT_MAX_WIDTH = "text-max-width";
    public static final String PROPERTY_TEXT_OFFSET = "text-offset";
    public static final String PROPERTY_TEXT_OPACITY = "text-opacity";
    public static final String PROPERTY_TEXT_RADIAL_OFFSET = "text-radial-offset";
    public static final String PROPERTY_TEXT_ROTATE = "text-rotate";
    public static final String PROPERTY_TEXT_SIZE = "text-size";
    public static final String PROPERTY_TEXT_TRANSFORM = "text-transform";
    private JsonElement data;
    private Point geometry;
    private Bitmap icon;
    private String iconAnchor;
    private String iconColor;
    private Float iconHaloBlur;
    private String iconHaloColor;
    private Float iconHaloWidth;
    private String iconImage;
    private Float[] iconOffset;
    private Float iconOpacity;
    private Float iconRotate;
    private Float iconSize;
    private boolean isDraggable;
    private String mapplsPin;
    private Float symbolSortKey;
    private String textAnchor;
    private String textColor;
    private String textField;
    private String[] textFont = {"Open Sans Medium"};
    private Float textHaloBlur;
    private String textHaloColor;
    private Float textHaloWidth;
    private String textJustify;
    private Float textLetterSpacing;
    private Float textMaxWidth;
    private Float[] textOffset;
    private Float textOpacity;
    private Float textRadialOffset;
    private Float textRotate;
    private Float textSize;
    private String textTransform;

    @Nullable
    public static SymbolOptions fromFeature(@NonNull Feature feature, Style style) {
        if (feature.geometry() != null) {
            if (feature.geometry() instanceof Point) {
                SymbolOptions symbolOptions = new SymbolOptions();
                symbolOptions.geometry = (Point) feature.geometry();
                if (feature.hasProperty(PROPERTY_SYMBOL_SORT_KEY)) {
                    symbolOptions.symbolSortKey = Float.valueOf(feature.getProperty(PROPERTY_SYMBOL_SORT_KEY).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_ICON_SIZE)) {
                    symbolOptions.iconSize = Float.valueOf(feature.getProperty(PROPERTY_ICON_SIZE).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_ICON_IMAGE)) {
                    symbolOptions.icon = style.getImage(feature.getProperty(PROPERTY_ICON_IMAGE).getAsString());
                    symbolOptions.iconImage = feature.getProperty(PROPERTY_ICON_IMAGE).getAsString();
                }
                if (feature.hasProperty(PROPERTY_ICON_ROTATE)) {
                    symbolOptions.iconRotate = Float.valueOf(feature.getProperty(PROPERTY_ICON_ROTATE).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_ICON_OFFSET)) {
                    symbolOptions.iconOffset = b.c(feature.getProperty(PROPERTY_ICON_OFFSET).getAsJsonArray());
                }
                if (feature.hasProperty(PROPERTY_ICON_ANCHOR)) {
                    symbolOptions.iconAnchor = feature.getProperty(PROPERTY_ICON_ANCHOR).getAsString();
                }
                if (feature.hasProperty(PROPERTY_TEXT_FIELD)) {
                    symbolOptions.textField = feature.getProperty(PROPERTY_TEXT_FIELD).getAsString();
                }
                if (feature.hasProperty(PROPERTY_TEXT_FONT)) {
                    symbolOptions.textFont = b.d(feature.getProperty(PROPERTY_TEXT_FONT).getAsJsonArray());
                }
                if (feature.hasProperty(PROPERTY_TEXT_SIZE)) {
                    symbolOptions.textSize = Float.valueOf(feature.getProperty(PROPERTY_TEXT_SIZE).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_TEXT_MAX_WIDTH)) {
                    symbolOptions.textMaxWidth = Float.valueOf(feature.getProperty(PROPERTY_TEXT_MAX_WIDTH).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_TEXT_LETTER_SPACING)) {
                    symbolOptions.textLetterSpacing = Float.valueOf(feature.getProperty(PROPERTY_TEXT_LETTER_SPACING).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_TEXT_JUSTIFY)) {
                    symbolOptions.textJustify = feature.getProperty(PROPERTY_TEXT_JUSTIFY).getAsString();
                }
                if (feature.hasProperty(PROPERTY_TEXT_RADIAL_OFFSET)) {
                    symbolOptions.textRadialOffset = Float.valueOf(feature.getProperty(PROPERTY_TEXT_RADIAL_OFFSET).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_TEXT_ANCHOR)) {
                    symbolOptions.textAnchor = feature.getProperty(PROPERTY_TEXT_ANCHOR).getAsString();
                }
                if (feature.hasProperty(PROPERTY_TEXT_ROTATE)) {
                    symbolOptions.textRotate = Float.valueOf(feature.getProperty(PROPERTY_TEXT_ROTATE).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_TEXT_TRANSFORM)) {
                    symbolOptions.textTransform = feature.getProperty(PROPERTY_TEXT_TRANSFORM).getAsString();
                }
                if (feature.hasProperty(PROPERTY_TEXT_OFFSET)) {
                    symbolOptions.textOffset = b.c(feature.getProperty(PROPERTY_TEXT_OFFSET).getAsJsonArray());
                }
                if (feature.hasProperty(PROPERTY_ICON_OPACITY)) {
                    symbolOptions.iconOpacity = Float.valueOf(feature.getProperty(PROPERTY_ICON_OPACITY).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_ICON_COLOR)) {
                    symbolOptions.iconColor = feature.getProperty(PROPERTY_ICON_COLOR).getAsString();
                }
                if (feature.hasProperty(PROPERTY_ICON_HALO_COLOR)) {
                    symbolOptions.iconHaloColor = feature.getProperty(PROPERTY_ICON_HALO_COLOR).getAsString();
                }
                if (feature.hasProperty(PROPERTY_ICON_HALO_WIDTH)) {
                    symbolOptions.iconHaloWidth = Float.valueOf(feature.getProperty(PROPERTY_ICON_HALO_WIDTH).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_ICON_HALO_BLUR)) {
                    symbolOptions.iconHaloBlur = Float.valueOf(feature.getProperty(PROPERTY_ICON_HALO_BLUR).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_TEXT_OPACITY)) {
                    symbolOptions.textOpacity = Float.valueOf(feature.getProperty(PROPERTY_TEXT_OPACITY).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_TEXT_COLOR)) {
                    symbolOptions.textColor = feature.getProperty(PROPERTY_TEXT_COLOR).getAsString();
                }
                if (feature.hasProperty(PROPERTY_TEXT_HALO_COLOR)) {
                    symbolOptions.textHaloColor = feature.getProperty(PROPERTY_TEXT_HALO_COLOR).getAsString();
                }
                if (feature.hasProperty(PROPERTY_TEXT_HALO_WIDTH)) {
                    symbolOptions.textHaloWidth = Float.valueOf(feature.getProperty(PROPERTY_TEXT_HALO_WIDTH).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_TEXT_HALO_BLUR)) {
                    symbolOptions.textHaloBlur = Float.valueOf(feature.getProperty(PROPERTY_TEXT_HALO_BLUR).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_IS_DRAGGABLE)) {
                    symbolOptions.isDraggable = feature.getProperty(PROPERTY_IS_DRAGGABLE).getAsBoolean();
                }
                return symbolOptions;
            }
            return null;
        }
        throw new RuntimeException("geometry field is required");
    }

    @Override // com.mappls.sdk.plugin.annotation.g
    public Symbol build(long j, AnnotationManager<?, Symbol, ?, ?, ?, ?> annotationManager) {
        String str;
        if (this.geometry == null && this.mapplsPin == null) {
            throw new RuntimeException("geometry field is required");
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(PROPERTY_SYMBOL_SORT_KEY, this.symbolSortKey);
        jsonObject.addProperty(PROPERTY_ICON_SIZE, this.iconSize);
        String str2 = this.iconImage;
        if (str2 == null) {
            if (this.icon == null) {
                this.icon = BitmapFactory.decodeResource(annotationManager.mapView.getResources(), R.drawable.mappls_maps_marker_icon_default);
            }
            long incrementAndGet = IMAGE_ID_GENERATOR.incrementAndGet();
            annotationManager.style.addImage(ICON_IMAGE_NAME + incrementAndGet, this.icon);
            str = ICON_IMAGE_NAME + incrementAndGet;
        } else {
            this.icon = annotationManager.style.getImage(str2);
            str = this.iconImage;
        }
        jsonObject.addProperty(PROPERTY_ICON_IMAGE, str);
        jsonObject.addProperty(PROPERTY_ICON_ROTATE, this.iconRotate);
        jsonObject.add(PROPERTY_ICON_OFFSET, b.a(this.iconOffset));
        jsonObject.addProperty(PROPERTY_ICON_ANCHOR, this.iconAnchor);
        jsonObject.addProperty(PROPERTY_TEXT_FIELD, this.textField);
        jsonObject.add(PROPERTY_TEXT_FONT, b.b(this.textFont));
        jsonObject.addProperty(PROPERTY_TEXT_SIZE, this.textSize);
        jsonObject.addProperty(PROPERTY_TEXT_MAX_WIDTH, this.textMaxWidth);
        jsonObject.addProperty(PROPERTY_TEXT_LETTER_SPACING, this.textLetterSpacing);
        jsonObject.addProperty(PROPERTY_TEXT_JUSTIFY, this.textJustify);
        jsonObject.addProperty(PROPERTY_TEXT_RADIAL_OFFSET, this.textRadialOffset);
        jsonObject.addProperty(PROPERTY_TEXT_ANCHOR, this.textAnchor);
        jsonObject.addProperty(PROPERTY_TEXT_ROTATE, this.textRotate);
        jsonObject.addProperty(PROPERTY_TEXT_TRANSFORM, this.textTransform);
        jsonObject.add(PROPERTY_TEXT_OFFSET, b.a(this.textOffset));
        jsonObject.addProperty(PROPERTY_ICON_OPACITY, this.iconOpacity);
        jsonObject.addProperty(PROPERTY_ICON_COLOR, this.iconColor);
        jsonObject.addProperty(PROPERTY_ICON_HALO_COLOR, this.iconHaloColor);
        jsonObject.addProperty(PROPERTY_ICON_HALO_WIDTH, this.iconHaloWidth);
        jsonObject.addProperty(PROPERTY_ICON_HALO_BLUR, this.iconHaloBlur);
        jsonObject.addProperty(PROPERTY_TEXT_OPACITY, this.textOpacity);
        jsonObject.addProperty(PROPERTY_TEXT_COLOR, this.textColor);
        jsonObject.addProperty(PROPERTY_TEXT_HALO_COLOR, this.textHaloColor);
        jsonObject.addProperty(PROPERTY_TEXT_HALO_WIDTH, this.textHaloWidth);
        jsonObject.addProperty(PROPERTY_TEXT_HALO_BLUR, this.textHaloBlur);
        Symbol symbol = new Symbol(j, annotationManager, jsonObject, this.geometry, this.mapplsPin, this.icon);
        symbol.setDraggable(this.isDraggable);
        symbol.setData(this.data);
        return symbol;
    }

    public SymbolOptions data(@Nullable JsonElement jsonElement) {
        this.data = jsonElement;
        return this;
    }

    public SymbolOptions draggable(boolean z) {
        this.isDraggable = z;
        return this;
    }

    public SymbolOptions geometry(Point point) {
        this.geometry = point;
        return this;
    }

    @Nullable
    public JsonElement getData() {
        return this.data;
    }

    public Point getGeometry() {
        return this.geometry;
    }

    public Bitmap getIcon() {
        return this.icon;
    }

    public String getIconAnchor() {
        return this.iconAnchor;
    }

    public String getIconColor() {
        return this.iconColor;
    }

    public Float getIconHaloBlur() {
        return this.iconHaloBlur;
    }

    public String getIconHaloColor() {
        return this.iconHaloColor;
    }

    public Float getIconHaloWidth() {
        return this.iconHaloWidth;
    }

    public String getIconImage() {
        return this.iconImage;
    }

    public Float[] getIconOffset() {
        return this.iconOffset;
    }

    public Float getIconOpacity() {
        return this.iconOpacity;
    }

    public Float getIconRotate() {
        return this.iconRotate;
    }

    public Float getIconSize() {
        return this.iconSize;
    }

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    public LatLng getPosition() {
        if (this.geometry == null) {
            return null;
        }
        return new LatLng(this.geometry.latitude(), this.geometry.longitude());
    }

    public Float getSymbolSortKey() {
        return this.symbolSortKey;
    }

    public String getTextAnchor() {
        return this.textAnchor;
    }

    public String getTextColor() {
        return this.textColor;
    }

    public String getTextField() {
        return this.textField;
    }

    public String[] getTextFont() {
        return this.textFont;
    }

    public Float getTextHaloBlur() {
        return this.textHaloBlur;
    }

    public String getTextHaloColor() {
        return this.textHaloColor;
    }

    public Float getTextHaloWidth() {
        return this.textHaloWidth;
    }

    public String getTextJustify() {
        return this.textJustify;
    }

    public Float getTextLetterSpacing() {
        return this.textLetterSpacing;
    }

    public Float getTextMaxWidth() {
        return this.textMaxWidth;
    }

    public Float[] getTextOffset() {
        return this.textOffset;
    }

    public Float getTextOpacity() {
        return this.textOpacity;
    }

    public Float getTextRadialOffset() {
        return this.textRadialOffset;
    }

    public Float getTextRotate() {
        return this.textRotate;
    }

    public Float getTextSize() {
        return this.textSize;
    }

    public String getTextTransform() {
        return this.textTransform;
    }

    public SymbolOptions icon(Bitmap bitmap) {
        this.icon = bitmap;
        return this;
    }

    public SymbolOptions icon(String str) {
        this.iconImage = str;
        return this;
    }

    public SymbolOptions iconAnchor(String str) {
        this.iconAnchor = str;
        return this;
    }

    public SymbolOptions iconColor(String str) {
        this.iconColor = str;
        return this;
    }

    public SymbolOptions iconHaloBlur(Float f) {
        this.iconHaloBlur = f;
        return this;
    }

    public SymbolOptions iconHaloColor(String str) {
        this.iconHaloColor = str;
        return this;
    }

    public SymbolOptions iconHaloWidth(Float f) {
        this.iconHaloWidth = f;
        return this;
    }

    public SymbolOptions iconOffset(Float[] fArr) {
        this.iconOffset = fArr;
        return this;
    }

    public SymbolOptions iconOpacity(Float f) {
        this.iconOpacity = f;
        return this;
    }

    public SymbolOptions iconRotate(Float f) {
        this.iconRotate = f;
        return this;
    }

    public SymbolOptions iconSize(Float f) {
        this.iconSize = f;
        return this;
    }

    public boolean isDraggable() {
        return this.isDraggable;
    }

    public SymbolOptions mapplsPin(String str) {
        this.mapplsPin = str;
        return this;
    }

    public SymbolOptions position(LatLng latLng) {
        this.geometry = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
        return this;
    }

    public SymbolOptions symbolSortKey(Float f) {
        this.symbolSortKey = f;
        return this;
    }

    public SymbolOptions textAnchor(String str) {
        this.textAnchor = str;
        return this;
    }

    public SymbolOptions textColor(String str) {
        this.textColor = str;
        return this;
    }

    public SymbolOptions textField(String str) {
        this.textField = str;
        return this;
    }

    public SymbolOptions textFont(String[] strArr) {
        this.textFont = strArr;
        return this;
    }

    public SymbolOptions textHaloBlur(Float f) {
        this.textHaloBlur = f;
        return this;
    }

    public SymbolOptions textHaloColor(String str) {
        this.textHaloColor = str;
        return this;
    }

    public SymbolOptions textHaloWidth(Float f) {
        this.textHaloWidth = f;
        return this;
    }

    public SymbolOptions textJustify(String str) {
        this.textJustify = str;
        return this;
    }

    public SymbolOptions textLetterSpacing(Float f) {
        this.textLetterSpacing = f;
        return this;
    }

    public SymbolOptions textMaxWidth(Float f) {
        this.textMaxWidth = f;
        return this;
    }

    public SymbolOptions textOffset(Float[] fArr) {
        this.textOffset = fArr;
        return this;
    }

    public SymbolOptions textOpacity(Float f) {
        this.textOpacity = f;
        return this;
    }

    public SymbolOptions textRadialOffset(Float f) {
        this.textRadialOffset = f;
        return this;
    }

    public SymbolOptions textRotate(Float f) {
        this.textRotate = f;
        return this;
    }

    public SymbolOptions textSize(Float f) {
        this.textSize = f;
        return this;
    }

    public SymbolOptions textTransform(String str) {
        this.textTransform = str;
        return this;
    }
}
