package com.mappls.sdk.maps.style.layers;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.google.gson.JsonElement;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.utils.ColorUtils;
import com.mappls.sdk.plugin.annotation.LineOptions;
@UiThread
/* loaded from: classes11.dex */
public class LineLayer extends Layer {
    @Keep
    public LineLayer(long j) {
        super(j);
    }

    @NonNull
    @Keep
    private native Object nativeGetLineBlur();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetLineBlurTransition();

    @NonNull
    @Keep
    private native Object nativeGetLineCap();

    @NonNull
    @Keep
    private native Object nativeGetLineColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetLineColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetLineDasharray();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetLineDasharrayTransition();

    @NonNull
    @Keep
    private native Object nativeGetLineGapWidth();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetLineGapWidthTransition();

    @NonNull
    @Keep
    private native Object nativeGetLineGradient();

    @NonNull
    @Keep
    private native Object nativeGetLineJoin();

    @NonNull
    @Keep
    private native Object nativeGetLineMiterLimit();

    @NonNull
    @Keep
    private native Object nativeGetLineOffset();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetLineOffsetTransition();

    @NonNull
    @Keep
    private native Object nativeGetLineOpacity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetLineOpacityTransition();

    @NonNull
    @Keep
    private native Object nativeGetLinePattern();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetLinePatternTransition();

    @NonNull
    @Keep
    private native Object nativeGetLineRoundLimit();

    @NonNull
    @Keep
    private native Object nativeGetLineSortKey();

    @NonNull
    @Keep
    private native Object nativeGetLineTranslate();

    @NonNull
    @Keep
    private native Object nativeGetLineTranslateAnchor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetLineTranslateTransition();

    @NonNull
    @Keep
    private native Object nativeGetLineWidth();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetLineWidthTransition();

    @Keep
    private native void nativeSetLineBlurTransition(long j, long j2);

    @Keep
    private native void nativeSetLineColorTransition(long j, long j2);

    @Keep
    private native void nativeSetLineDasharrayTransition(long j, long j2);

    @Keep
    private native void nativeSetLineGapWidthTransition(long j, long j2);

    @Keep
    private native void nativeSetLineOffsetTransition(long j, long j2);

    @Keep
    private native void nativeSetLineOpacityTransition(long j, long j2);

    @Keep
    private native void nativeSetLinePatternTransition(long j, long j2);

    @Keep
    private native void nativeSetLineTranslateTransition(long j, long j2);

    @Keep
    private native void nativeSetLineWidthTransition(long j, long j2);

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
    public PropertyValue<Float> getLineBlur() {
        checkThread();
        return new PropertyValue<>(LineOptions.PROPERTY_LINE_BLUR, nativeGetLineBlur());
    }

    @NonNull
    public TransitionOptions getLineBlurTransition() {
        checkThread();
        return nativeGetLineBlurTransition();
    }

    @NonNull
    public PropertyValue<String> getLineCap() {
        checkThread();
        return new PropertyValue<>("line-cap", nativeGetLineCap());
    }

    @NonNull
    public PropertyValue<String> getLineColor() {
        checkThread();
        return new PropertyValue<>(LineOptions.PROPERTY_LINE_COLOR, nativeGetLineColor());
    }

    @ColorInt
    public int getLineColorAsInt() {
        checkThread();
        PropertyValue<String> lineColor = getLineColor();
        if (lineColor.isValue()) {
            return ColorUtils.rgbaToColor(lineColor.getValue());
        }
        throw new RuntimeException("line-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getLineColorTransition() {
        checkThread();
        return nativeGetLineColorTransition();
    }

    @NonNull
    public PropertyValue<Float[]> getLineDasharray() {
        checkThread();
        return new PropertyValue<>("line-dasharray", nativeGetLineDasharray());
    }

    @NonNull
    public TransitionOptions getLineDasharrayTransition() {
        checkThread();
        return nativeGetLineDasharrayTransition();
    }

    @NonNull
    public PropertyValue<Float> getLineGapWidth() {
        checkThread();
        return new PropertyValue<>(LineOptions.PROPERTY_LINE_GAP_WIDTH, nativeGetLineGapWidth());
    }

    @NonNull
    public TransitionOptions getLineGapWidthTransition() {
        checkThread();
        return nativeGetLineGapWidthTransition();
    }

    @NonNull
    public PropertyValue<String> getLineGradient() {
        checkThread();
        return new PropertyValue<>("line-gradient", nativeGetLineGradient());
    }

    @ColorInt
    public int getLineGradientAsInt() {
        checkThread();
        PropertyValue<String> lineGradient = getLineGradient();
        if (lineGradient.isValue()) {
            return ColorUtils.rgbaToColor(lineGradient.getValue());
        }
        throw new RuntimeException("line-gradient was set as a Function");
    }

    @NonNull
    public PropertyValue<String> getLineJoin() {
        checkThread();
        return new PropertyValue<>(LineOptions.PROPERTY_LINE_JOIN, nativeGetLineJoin());
    }

    @NonNull
    public PropertyValue<Float> getLineMiterLimit() {
        checkThread();
        return new PropertyValue<>("line-miter-limit", nativeGetLineMiterLimit());
    }

    @NonNull
    public PropertyValue<Float> getLineOffset() {
        checkThread();
        return new PropertyValue<>(LineOptions.PROPERTY_LINE_OFFSET, nativeGetLineOffset());
    }

    @NonNull
    public TransitionOptions getLineOffsetTransition() {
        checkThread();
        return nativeGetLineOffsetTransition();
    }

    @NonNull
    public PropertyValue<Float> getLineOpacity() {
        checkThread();
        return new PropertyValue<>(LineOptions.PROPERTY_LINE_OPACITY, nativeGetLineOpacity());
    }

    @NonNull
    public TransitionOptions getLineOpacityTransition() {
        checkThread();
        return nativeGetLineOpacityTransition();
    }

    @NonNull
    public PropertyValue<String> getLinePattern() {
        checkThread();
        return new PropertyValue<>(LineOptions.PROPERTY_LINE_PATTERN, nativeGetLinePattern());
    }

    @NonNull
    public TransitionOptions getLinePatternTransition() {
        checkThread();
        return nativeGetLinePatternTransition();
    }

    @NonNull
    public PropertyValue<Float> getLineRoundLimit() {
        checkThread();
        return new PropertyValue<>("line-round-limit", nativeGetLineRoundLimit());
    }

    @NonNull
    public PropertyValue<Float> getLineSortKey() {
        checkThread();
        return new PropertyValue<>("line-sort-key", nativeGetLineSortKey());
    }

    @NonNull
    public PropertyValue<Float[]> getLineTranslate() {
        checkThread();
        return new PropertyValue<>("line-translate", nativeGetLineTranslate());
    }

    @NonNull
    public PropertyValue<String> getLineTranslateAnchor() {
        checkThread();
        return new PropertyValue<>("line-translate-anchor", nativeGetLineTranslateAnchor());
    }

    @NonNull
    public TransitionOptions getLineTranslateTransition() {
        checkThread();
        return nativeGetLineTranslateTransition();
    }

    @NonNull
    public PropertyValue<Float> getLineWidth() {
        checkThread();
        return new PropertyValue<>(LineOptions.PROPERTY_LINE_WIDTH, nativeGetLineWidth());
    }

    @NonNull
    public TransitionOptions getLineWidthTransition() {
        checkThread();
        return nativeGetLineWidthTransition();
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

    @Keep
    public native void initialize(String str, String str2);

    public void setFilter(@NonNull Expression expression) {
        checkThread();
        nativeSetFilter(expression.toArray());
    }

    public void setLineBlurTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetLineBlurTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setLineColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetLineColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setLineDasharrayTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetLineDasharrayTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setLineGapWidthTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetLineGapWidthTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setLineOffsetTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetLineOffsetTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setLineOpacityTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetLineOpacityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setLinePatternTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetLinePatternTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setLineTranslateTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetLineTranslateTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setLineWidthTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetLineWidthTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setSourceLayer(String str) {
        checkThread();
        nativeSetSourceLayer(str);
    }

    @NonNull
    public LineLayer withFilter(@NonNull Expression expression) {
        setFilter(expression);
        return this;
    }

    @NonNull
    public LineLayer withProperties(@NonNull PropertyValue<?>... propertyValueArr) {
        setProperties(propertyValueArr);
        return this;
    }

    @NonNull
    public LineLayer withSourceLayer(String str) {
        setSourceLayer(str);
        return this;
    }

    public LineLayer(String str, String str2) {
        initialize(str, str2);
    }
}
