package com.mappls.sdk.maps.style.layers;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.google.gson.JsonElement;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.types.Formatted;
import com.mappls.sdk.maps.utils.ColorUtils;
import com.mappls.sdk.plugin.annotation.SymbolOptions;
@UiThread
/* loaded from: classes11.dex */
public class SymbolLayer extends Layer {
    @Keep
    public SymbolLayer(long j) {
        super(j);
    }

    @NonNull
    @Keep
    private native Object nativeGetIconAllowOverlap();

    @NonNull
    @Keep
    private native Object nativeGetIconAnchor();

    @NonNull
    @Keep
    private native Object nativeGetIconColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetIconColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetIconHaloBlur();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetIconHaloBlurTransition();

    @NonNull
    @Keep
    private native Object nativeGetIconHaloColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetIconHaloColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetIconHaloWidth();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetIconHaloWidthTransition();

    @NonNull
    @Keep
    private native Object nativeGetIconIgnorePlacement();

    @NonNull
    @Keep
    private native Object nativeGetIconImage();

    @NonNull
    @Keep
    private native Object nativeGetIconKeepUpright();

    @NonNull
    @Keep
    private native Object nativeGetIconOffset();

    @NonNull
    @Keep
    private native Object nativeGetIconOpacity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetIconOpacityTransition();

    @NonNull
    @Keep
    private native Object nativeGetIconOptional();

    @NonNull
    @Keep
    private native Object nativeGetIconPadding();

    @NonNull
    @Keep
    private native Object nativeGetIconPitchAlignment();

    @NonNull
    @Keep
    private native Object nativeGetIconRotate();

    @NonNull
    @Keep
    private native Object nativeGetIconRotationAlignment();

    @NonNull
    @Keep
    private native Object nativeGetIconSize();

    @NonNull
    @Keep
    private native Object nativeGetIconTextFit();

    @NonNull
    @Keep
    private native Object nativeGetIconTextFitPadding();

    @NonNull
    @Keep
    private native Object nativeGetIconTranslate();

    @NonNull
    @Keep
    private native Object nativeGetIconTranslateAnchor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetIconTranslateTransition();

    @NonNull
    @Keep
    private native Object nativeGetSymbolAvoidEdges();

    @NonNull
    @Keep
    private native Object nativeGetSymbolPlacement();

    @NonNull
    @Keep
    private native Object nativeGetSymbolSortKey();

    @NonNull
    @Keep
    private native Object nativeGetSymbolSpacing();

    @NonNull
    @Keep
    private native Object nativeGetSymbolZOrder();

    @NonNull
    @Keep
    private native Object nativeGetTextAllowOverlap();

    @NonNull
    @Keep
    private native Object nativeGetTextAnchor();

    @NonNull
    @Keep
    private native Object nativeGetTextColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetTextColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetTextField();

    @NonNull
    @Keep
    private native Object nativeGetTextFont();

    @NonNull
    @Keep
    private native Object nativeGetTextHaloBlur();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetTextHaloBlurTransition();

    @NonNull
    @Keep
    private native Object nativeGetTextHaloColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetTextHaloColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetTextHaloWidth();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetTextHaloWidthTransition();

    @NonNull
    @Keep
    private native Object nativeGetTextIgnorePlacement();

    @NonNull
    @Keep
    private native Object nativeGetTextJustify();

    @NonNull
    @Keep
    private native Object nativeGetTextKeepUpright();

    @NonNull
    @Keep
    private native Object nativeGetTextLetterSpacing();

    @NonNull
    @Keep
    private native Object nativeGetTextLineHeight();

    @NonNull
    @Keep
    private native Object nativeGetTextMaxAngle();

    @NonNull
    @Keep
    private native Object nativeGetTextMaxWidth();

    @NonNull
    @Keep
    private native Object nativeGetTextOffset();

    @NonNull
    @Keep
    private native Object nativeGetTextOpacity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetTextOpacityTransition();

    @NonNull
    @Keep
    private native Object nativeGetTextOptional();

    @NonNull
    @Keep
    private native Object nativeGetTextPadding();

    @NonNull
    @Keep
    private native Object nativeGetTextPitchAlignment();

    @NonNull
    @Keep
    private native Object nativeGetTextRadialOffset();

    @NonNull
    @Keep
    private native Object nativeGetTextRotate();

    @NonNull
    @Keep
    private native Object nativeGetTextRotationAlignment();

    @NonNull
    @Keep
    private native Object nativeGetTextSize();

    @NonNull
    @Keep
    private native Object nativeGetTextTransform();

    @NonNull
    @Keep
    private native Object nativeGetTextTranslate();

    @NonNull
    @Keep
    private native Object nativeGetTextTranslateAnchor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetTextTranslateTransition();

    @NonNull
    @Keep
    private native Object nativeGetTextVariableAnchor();

    @NonNull
    @Keep
    private native Object nativeGetTextWritingMode();

    @Keep
    private native void nativeSetIconColorTransition(long j, long j2);

    @Keep
    private native void nativeSetIconHaloBlurTransition(long j, long j2);

    @Keep
    private native void nativeSetIconHaloColorTransition(long j, long j2);

    @Keep
    private native void nativeSetIconHaloWidthTransition(long j, long j2);

    @Keep
    private native void nativeSetIconOpacityTransition(long j, long j2);

    @Keep
    private native void nativeSetIconTranslateTransition(long j, long j2);

    @Keep
    private native void nativeSetTextColorTransition(long j, long j2);

    @Keep
    private native void nativeSetTextHaloBlurTransition(long j, long j2);

    @Keep
    private native void nativeSetTextHaloColorTransition(long j, long j2);

    @Keep
    private native void nativeSetTextHaloWidthTransition(long j, long j2);

    @Keep
    private native void nativeSetTextOpacityTransition(long j, long j2);

    @Keep
    private native void nativeSetTextTranslateTransition(long j, long j2);

    @Override // com.mappls.sdk.maps.style.layers.Layer
    @Keep
    public native void finalize() throws Throwable;

    @Nullable
    public Expression getFilter() {
        checkThread();
        JsonElement nativeGetFilter = nativeGetFilter();
        if (nativeGetFilter != null) {
            return Expression.Converter.convert(nativeGetFilter);
        }
        return null;
    }

    @NonNull
    public PropertyValue<Boolean> getIconAllowOverlap() {
        checkThread();
        return new PropertyValue<>("icon-allow-overlap", nativeGetIconAllowOverlap());
    }

    @NonNull
    public PropertyValue<String> getIconAnchor() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_ICON_ANCHOR, nativeGetIconAnchor());
    }

    @NonNull
    public PropertyValue<String> getIconColor() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_ICON_COLOR, nativeGetIconColor());
    }

    @ColorInt
    public int getIconColorAsInt() {
        checkThread();
        PropertyValue<String> iconColor = getIconColor();
        if (iconColor.isValue()) {
            return ColorUtils.rgbaToColor(iconColor.getValue());
        }
        throw new RuntimeException("icon-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getIconColorTransition() {
        checkThread();
        return nativeGetIconColorTransition();
    }

    @NonNull
    public PropertyValue<Float> getIconHaloBlur() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_ICON_HALO_BLUR, nativeGetIconHaloBlur());
    }

    @NonNull
    public TransitionOptions getIconHaloBlurTransition() {
        checkThread();
        return nativeGetIconHaloBlurTransition();
    }

    @NonNull
    public PropertyValue<String> getIconHaloColor() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_ICON_HALO_COLOR, nativeGetIconHaloColor());
    }

    @ColorInt
    public int getIconHaloColorAsInt() {
        checkThread();
        PropertyValue<String> iconHaloColor = getIconHaloColor();
        if (iconHaloColor.isValue()) {
            return ColorUtils.rgbaToColor(iconHaloColor.getValue());
        }
        throw new RuntimeException("icon-halo-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getIconHaloColorTransition() {
        checkThread();
        return nativeGetIconHaloColorTransition();
    }

    @NonNull
    public PropertyValue<Float> getIconHaloWidth() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_ICON_HALO_WIDTH, nativeGetIconHaloWidth());
    }

    @NonNull
    public TransitionOptions getIconHaloWidthTransition() {
        checkThread();
        return nativeGetIconHaloWidthTransition();
    }

    @NonNull
    public PropertyValue<Boolean> getIconIgnorePlacement() {
        checkThread();
        return new PropertyValue<>("icon-ignore-placement", nativeGetIconIgnorePlacement());
    }

    @NonNull
    public PropertyValue<String> getIconImage() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_ICON_IMAGE, nativeGetIconImage());
    }

    @NonNull
    public PropertyValue<Boolean> getIconKeepUpright() {
        checkThread();
        return new PropertyValue<>("icon-keep-upright", nativeGetIconKeepUpright());
    }

    @NonNull
    public PropertyValue<Float[]> getIconOffset() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_ICON_OFFSET, nativeGetIconOffset());
    }

    @NonNull
    public PropertyValue<Float> getIconOpacity() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_ICON_OPACITY, nativeGetIconOpacity());
    }

    @NonNull
    public TransitionOptions getIconOpacityTransition() {
        checkThread();
        return nativeGetIconOpacityTransition();
    }

    @NonNull
    public PropertyValue<Boolean> getIconOptional() {
        checkThread();
        return new PropertyValue<>("icon-optional", nativeGetIconOptional());
    }

    @NonNull
    public PropertyValue<Float> getIconPadding() {
        checkThread();
        return new PropertyValue<>("icon-padding", nativeGetIconPadding());
    }

    @NonNull
    public PropertyValue<String> getIconPitchAlignment() {
        checkThread();
        return new PropertyValue<>("icon-pitch-alignment", nativeGetIconPitchAlignment());
    }

    @NonNull
    public PropertyValue<Float> getIconRotate() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_ICON_ROTATE, nativeGetIconRotate());
    }

    @NonNull
    public PropertyValue<String> getIconRotationAlignment() {
        checkThread();
        return new PropertyValue<>("icon-rotation-alignment", nativeGetIconRotationAlignment());
    }

    @NonNull
    public PropertyValue<Float> getIconSize() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_ICON_SIZE, nativeGetIconSize());
    }

    @NonNull
    public PropertyValue<String> getIconTextFit() {
        checkThread();
        return new PropertyValue<>("icon-text-fit", nativeGetIconTextFit());
    }

    @NonNull
    public PropertyValue<Float[]> getIconTextFitPadding() {
        checkThread();
        return new PropertyValue<>("icon-text-fit-padding", nativeGetIconTextFitPadding());
    }

    @NonNull
    public PropertyValue<Float[]> getIconTranslate() {
        checkThread();
        return new PropertyValue<>("icon-translate", nativeGetIconTranslate());
    }

    @NonNull
    public PropertyValue<String> getIconTranslateAnchor() {
        checkThread();
        return new PropertyValue<>("icon-translate-anchor", nativeGetIconTranslateAnchor());
    }

    @NonNull
    public TransitionOptions getIconTranslateTransition() {
        checkThread();
        return nativeGetIconTranslateTransition();
    }

    @NonNull
    public String getSourceId() {
        checkThread();
        return nativeGetSourceId();
    }

    @NonNull
    public String getSourceLayer() {
        checkThread();
        return nativeGetSourceLayer();
    }

    @NonNull
    public PropertyValue<Boolean> getSymbolAvoidEdges() {
        checkThread();
        return new PropertyValue<>("symbol-avoid-edges", nativeGetSymbolAvoidEdges());
    }

    @NonNull
    public PropertyValue<String> getSymbolPlacement() {
        checkThread();
        return new PropertyValue<>("symbol-placement", nativeGetSymbolPlacement());
    }

    @NonNull
    public PropertyValue<Float> getSymbolSortKey() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_SYMBOL_SORT_KEY, nativeGetSymbolSortKey());
    }

    @NonNull
    public PropertyValue<Float> getSymbolSpacing() {
        checkThread();
        return new PropertyValue<>("symbol-spacing", nativeGetSymbolSpacing());
    }

    @NonNull
    public PropertyValue<String> getSymbolZOrder() {
        checkThread();
        return new PropertyValue<>("symbol-z-order", nativeGetSymbolZOrder());
    }

    @NonNull
    public PropertyValue<Boolean> getTextAllowOverlap() {
        checkThread();
        return new PropertyValue<>("text-allow-overlap", nativeGetTextAllowOverlap());
    }

    @NonNull
    public PropertyValue<String> getTextAnchor() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_ANCHOR, nativeGetTextAnchor());
    }

    @NonNull
    public PropertyValue<String> getTextColor() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_COLOR, nativeGetTextColor());
    }

    @ColorInt
    public int getTextColorAsInt() {
        checkThread();
        PropertyValue<String> textColor = getTextColor();
        if (textColor.isValue()) {
            return ColorUtils.rgbaToColor(textColor.getValue());
        }
        throw new RuntimeException("text-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getTextColorTransition() {
        checkThread();
        return nativeGetTextColorTransition();
    }

    @NonNull
    public PropertyValue<Formatted> getTextField() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_FIELD, nativeGetTextField());
    }

    @NonNull
    public PropertyValue<String[]> getTextFont() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_FONT, nativeGetTextFont());
    }

    @NonNull
    public PropertyValue<Float> getTextHaloBlur() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_HALO_BLUR, nativeGetTextHaloBlur());
    }

    @NonNull
    public TransitionOptions getTextHaloBlurTransition() {
        checkThread();
        return nativeGetTextHaloBlurTransition();
    }

    @NonNull
    public PropertyValue<String> getTextHaloColor() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_HALO_COLOR, nativeGetTextHaloColor());
    }

    @ColorInt
    public int getTextHaloColorAsInt() {
        checkThread();
        PropertyValue<String> textHaloColor = getTextHaloColor();
        if (textHaloColor.isValue()) {
            return ColorUtils.rgbaToColor(textHaloColor.getValue());
        }
        throw new RuntimeException("text-halo-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getTextHaloColorTransition() {
        checkThread();
        return nativeGetTextHaloColorTransition();
    }

    @NonNull
    public PropertyValue<Float> getTextHaloWidth() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_HALO_WIDTH, nativeGetTextHaloWidth());
    }

    @NonNull
    public TransitionOptions getTextHaloWidthTransition() {
        checkThread();
        return nativeGetTextHaloWidthTransition();
    }

    @NonNull
    public PropertyValue<Boolean> getTextIgnorePlacement() {
        checkThread();
        return new PropertyValue<>("text-ignore-placement", nativeGetTextIgnorePlacement());
    }

    @NonNull
    public PropertyValue<String> getTextJustify() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_JUSTIFY, nativeGetTextJustify());
    }

    @NonNull
    public PropertyValue<Boolean> getTextKeepUpright() {
        checkThread();
        return new PropertyValue<>("text-keep-upright", nativeGetTextKeepUpright());
    }

    @NonNull
    public PropertyValue<Float> getTextLetterSpacing() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_LETTER_SPACING, nativeGetTextLetterSpacing());
    }

    @NonNull
    public PropertyValue<Float> getTextLineHeight() {
        checkThread();
        return new PropertyValue<>("text-line-height", nativeGetTextLineHeight());
    }

    @NonNull
    public PropertyValue<Float> getTextMaxAngle() {
        checkThread();
        return new PropertyValue<>("text-max-angle", nativeGetTextMaxAngle());
    }

    @NonNull
    public PropertyValue<Float> getTextMaxWidth() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_MAX_WIDTH, nativeGetTextMaxWidth());
    }

    @NonNull
    public PropertyValue<Float[]> getTextOffset() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_OFFSET, nativeGetTextOffset());
    }

    @NonNull
    public PropertyValue<Float> getTextOpacity() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_OPACITY, nativeGetTextOpacity());
    }

    @NonNull
    public TransitionOptions getTextOpacityTransition() {
        checkThread();
        return nativeGetTextOpacityTransition();
    }

    @NonNull
    public PropertyValue<Boolean> getTextOptional() {
        checkThread();
        return new PropertyValue<>("text-optional", nativeGetTextOptional());
    }

    @NonNull
    public PropertyValue<Float> getTextPadding() {
        checkThread();
        return new PropertyValue<>("text-padding", nativeGetTextPadding());
    }

    @NonNull
    public PropertyValue<String> getTextPitchAlignment() {
        checkThread();
        return new PropertyValue<>("text-pitch-alignment", nativeGetTextPitchAlignment());
    }

    @NonNull
    public PropertyValue<Float> getTextRadialOffset() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_RADIAL_OFFSET, nativeGetTextRadialOffset());
    }

    @NonNull
    public PropertyValue<Float> getTextRotate() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_ROTATE, nativeGetTextRotate());
    }

    @NonNull
    public PropertyValue<String> getTextRotationAlignment() {
        checkThread();
        return new PropertyValue<>("text-rotation-alignment", nativeGetTextRotationAlignment());
    }

    @NonNull
    public PropertyValue<Float> getTextSize() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_SIZE, nativeGetTextSize());
    }

    @NonNull
    public PropertyValue<String> getTextTransform() {
        checkThread();
        return new PropertyValue<>(SymbolOptions.PROPERTY_TEXT_TRANSFORM, nativeGetTextTransform());
    }

    @NonNull
    public PropertyValue<Float[]> getTextTranslate() {
        checkThread();
        return new PropertyValue<>("text-translate", nativeGetTextTranslate());
    }

    @NonNull
    public PropertyValue<String> getTextTranslateAnchor() {
        checkThread();
        return new PropertyValue<>("text-translate-anchor", nativeGetTextTranslateAnchor());
    }

    @NonNull
    public TransitionOptions getTextTranslateTransition() {
        checkThread();
        return nativeGetTextTranslateTransition();
    }

    @NonNull
    public PropertyValue<String[]> getTextVariableAnchor() {
        checkThread();
        return new PropertyValue<>("text-variable-anchor", nativeGetTextVariableAnchor());
    }

    @NonNull
    public PropertyValue<String[]> getTextWritingMode() {
        checkThread();
        return new PropertyValue<>("text-writing-mode", nativeGetTextWritingMode());
    }

    @Keep
    public native void initialize(String str, String str2);

    public void setFilter(@NonNull Expression expression) {
        checkThread();
        nativeSetFilter(expression.toArray());
    }

    public void setIconColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetIconColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setIconHaloBlurTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetIconHaloBlurTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setIconHaloColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetIconHaloColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setIconHaloWidthTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetIconHaloWidthTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setIconOpacityTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetIconOpacityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setIconTranslateTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetIconTranslateTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setSourceLayer(String str) {
        checkThread();
        nativeSetSourceLayer(str);
    }

    public void setTextColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetTextColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setTextHaloBlurTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetTextHaloBlurTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setTextHaloColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetTextHaloColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setTextHaloWidthTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetTextHaloWidthTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setTextOpacityTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetTextOpacityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setTextTranslateTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetTextTranslateTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    @NonNull
    public SymbolLayer withFilter(@NonNull Expression expression) {
        setFilter(expression);
        return this;
    }

    @NonNull
    public SymbolLayer withProperties(@NonNull PropertyValue<?>... propertyValueArr) {
        setProperties(propertyValueArr);
        return this;
    }

    @NonNull
    public SymbolLayer withSourceLayer(String str) {
        setSourceLayer(str);
        return this;
    }

    public SymbolLayer(String str, String str2) {
        initialize(str, str2);
    }
}
