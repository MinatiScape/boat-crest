package com.mappls.sdk.maps.style.layers;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.google.gson.JsonElement;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.utils.ColorUtils;
import com.mappls.sdk.plugin.annotation.CircleOptions;
@UiThread
/* loaded from: classes11.dex */
public class CircleLayer extends Layer {
    @Keep
    public CircleLayer(long j) {
        super(j);
    }

    @NonNull
    @Keep
    private native Object nativeGetCircleBlur();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetCircleBlurTransition();

    @NonNull
    @Keep
    private native Object nativeGetCircleColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetCircleColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetCircleOpacity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetCircleOpacityTransition();

    @NonNull
    @Keep
    private native Object nativeGetCirclePitchAlignment();

    @NonNull
    @Keep
    private native Object nativeGetCirclePitchScale();

    @NonNull
    @Keep
    private native Object nativeGetCircleRadius();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetCircleRadiusTransition();

    @NonNull
    @Keep
    private native Object nativeGetCircleSortKey();

    @NonNull
    @Keep
    private native Object nativeGetCircleStrokeColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetCircleStrokeColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetCircleStrokeOpacity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetCircleStrokeOpacityTransition();

    @NonNull
    @Keep
    private native Object nativeGetCircleStrokeWidth();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetCircleStrokeWidthTransition();

    @NonNull
    @Keep
    private native Object nativeGetCircleTranslate();

    @NonNull
    @Keep
    private native Object nativeGetCircleTranslateAnchor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetCircleTranslateTransition();

    @Keep
    private native void nativeSetCircleBlurTransition(long j, long j2);

    @Keep
    private native void nativeSetCircleColorTransition(long j, long j2);

    @Keep
    private native void nativeSetCircleOpacityTransition(long j, long j2);

    @Keep
    private native void nativeSetCircleRadiusTransition(long j, long j2);

    @Keep
    private native void nativeSetCircleStrokeColorTransition(long j, long j2);

    @Keep
    private native void nativeSetCircleStrokeOpacityTransition(long j, long j2);

    @Keep
    private native void nativeSetCircleStrokeWidthTransition(long j, long j2);

    @Keep
    private native void nativeSetCircleTranslateTransition(long j, long j2);

    @Override // com.mappls.sdk.maps.style.layers.Layer
    @Keep
    public native void finalize() throws Throwable;

    @NonNull
    public PropertyValue<Float> getCircleBlur() {
        checkThread();
        return new PropertyValue<>(CircleOptions.PROPERTY_CIRCLE_BLUR, nativeGetCircleBlur());
    }

    @NonNull
    public TransitionOptions getCircleBlurTransition() {
        checkThread();
        return nativeGetCircleBlurTransition();
    }

    @NonNull
    public PropertyValue<String> getCircleColor() {
        checkThread();
        return new PropertyValue<>(CircleOptions.PROPERTY_CIRCLE_COLOR, nativeGetCircleColor());
    }

    @ColorInt
    public int getCircleColorAsInt() {
        checkThread();
        PropertyValue<String> circleColor = getCircleColor();
        if (circleColor.isValue()) {
            return ColorUtils.rgbaToColor(circleColor.getValue());
        }
        throw new RuntimeException("circle-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getCircleColorTransition() {
        checkThread();
        return nativeGetCircleColorTransition();
    }

    @NonNull
    public PropertyValue<Float> getCircleOpacity() {
        checkThread();
        return new PropertyValue<>(CircleOptions.PROPERTY_CIRCLE_OPACITY, nativeGetCircleOpacity());
    }

    @NonNull
    public TransitionOptions getCircleOpacityTransition() {
        checkThread();
        return nativeGetCircleOpacityTransition();
    }

    @NonNull
    public PropertyValue<String> getCirclePitchAlignment() {
        checkThread();
        return new PropertyValue<>("circle-pitch-alignment", nativeGetCirclePitchAlignment());
    }

    @NonNull
    public PropertyValue<String> getCirclePitchScale() {
        checkThread();
        return new PropertyValue<>("circle-pitch-scale", nativeGetCirclePitchScale());
    }

    @NonNull
    public PropertyValue<Float> getCircleRadius() {
        checkThread();
        return new PropertyValue<>(CircleOptions.PROPERTY_CIRCLE_RADIUS, nativeGetCircleRadius());
    }

    @NonNull
    public TransitionOptions getCircleRadiusTransition() {
        checkThread();
        return nativeGetCircleRadiusTransition();
    }

    @NonNull
    public PropertyValue<Float> getCircleSortKey() {
        checkThread();
        return new PropertyValue<>("circle-sort-key", nativeGetCircleSortKey());
    }

    @NonNull
    public PropertyValue<String> getCircleStrokeColor() {
        checkThread();
        return new PropertyValue<>(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR, nativeGetCircleStrokeColor());
    }

    @ColorInt
    public int getCircleStrokeColorAsInt() {
        checkThread();
        PropertyValue<String> circleStrokeColor = getCircleStrokeColor();
        if (circleStrokeColor.isValue()) {
            return ColorUtils.rgbaToColor(circleStrokeColor.getValue());
        }
        throw new RuntimeException("circle-stroke-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getCircleStrokeColorTransition() {
        checkThread();
        return nativeGetCircleStrokeColorTransition();
    }

    @NonNull
    public PropertyValue<Float> getCircleStrokeOpacity() {
        checkThread();
        return new PropertyValue<>(CircleOptions.PROPERTY_CIRCLE_STROKE_OPACITY, nativeGetCircleStrokeOpacity());
    }

    @NonNull
    public TransitionOptions getCircleStrokeOpacityTransition() {
        checkThread();
        return nativeGetCircleStrokeOpacityTransition();
    }

    @NonNull
    public PropertyValue<Float> getCircleStrokeWidth() {
        checkThread();
        return new PropertyValue<>(CircleOptions.PROPERTY_CIRCLE_STROKE_WIDTH, nativeGetCircleStrokeWidth());
    }

    @NonNull
    public TransitionOptions getCircleStrokeWidthTransition() {
        checkThread();
        return nativeGetCircleStrokeWidthTransition();
    }

    @NonNull
    public PropertyValue<Float[]> getCircleTranslate() {
        checkThread();
        return new PropertyValue<>("circle-translate", nativeGetCircleTranslate());
    }

    @NonNull
    public PropertyValue<String> getCircleTranslateAnchor() {
        checkThread();
        return new PropertyValue<>("circle-translate-anchor", nativeGetCircleTranslateAnchor());
    }

    @NonNull
    public TransitionOptions getCircleTranslateTransition() {
        checkThread();
        return nativeGetCircleTranslateTransition();
    }

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

    public void setCircleBlurTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetCircleBlurTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setCircleColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetCircleColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setCircleOpacityTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetCircleOpacityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setCircleRadiusTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetCircleRadiusTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setCircleStrokeColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetCircleStrokeColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setCircleStrokeOpacityTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetCircleStrokeOpacityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setCircleStrokeWidthTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetCircleStrokeWidthTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setCircleTranslateTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetCircleTranslateTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setFilter(@NonNull Expression expression) {
        checkThread();
        nativeSetFilter(expression.toArray());
    }

    public void setSourceLayer(String str) {
        checkThread();
        nativeSetSourceLayer(str);
    }

    @NonNull
    public CircleLayer withFilter(@NonNull Expression expression) {
        setFilter(expression);
        return this;
    }

    @NonNull
    public CircleLayer withProperties(@NonNull PropertyValue<?>... propertyValueArr) {
        setProperties(propertyValueArr);
        return this;
    }

    @NonNull
    public CircleLayer withSourceLayer(String str) {
        setSourceLayer(str);
        return this;
    }

    public CircleLayer(String str, String str2) {
        initialize(str, str2);
    }
}
