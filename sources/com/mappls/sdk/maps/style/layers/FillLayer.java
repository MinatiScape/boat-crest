package com.mappls.sdk.maps.style.layers;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.google.gson.JsonElement;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.utils.ColorUtils;
import com.mappls.sdk.plugin.annotation.FillOptions;
@UiThread
/* loaded from: classes11.dex */
public class FillLayer extends Layer {
    @Keep
    public FillLayer(long j) {
        super(j);
    }

    @NonNull
    @Keep
    private native Object nativeGetFillAntialias();

    @NonNull
    @Keep
    private native Object nativeGetFillColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetFillColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetFillOpacity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetFillOpacityTransition();

    @NonNull
    @Keep
    private native Object nativeGetFillOutlineColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetFillOutlineColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetFillPattern();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetFillPatternTransition();

    @NonNull
    @Keep
    private native Object nativeGetFillSortKey();

    @NonNull
    @Keep
    private native Object nativeGetFillTranslate();

    @NonNull
    @Keep
    private native Object nativeGetFillTranslateAnchor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetFillTranslateTransition();

    @Keep
    private native void nativeSetFillColorTransition(long j, long j2);

    @Keep
    private native void nativeSetFillOpacityTransition(long j, long j2);

    @Keep
    private native void nativeSetFillOutlineColorTransition(long j, long j2);

    @Keep
    private native void nativeSetFillPatternTransition(long j, long j2);

    @Keep
    private native void nativeSetFillTranslateTransition(long j, long j2);

    @Override // com.mappls.sdk.maps.style.layers.Layer
    @Keep
    public native void finalize() throws Throwable;

    @NonNull
    public PropertyValue<Boolean> getFillAntialias() {
        checkThread();
        return new PropertyValue<>("fill-antialias", nativeGetFillAntialias());
    }

    @NonNull
    public PropertyValue<String> getFillColor() {
        checkThread();
        return new PropertyValue<>(FillOptions.PROPERTY_FILL_COLOR, nativeGetFillColor());
    }

    @ColorInt
    public int getFillColorAsInt() {
        checkThread();
        PropertyValue<String> fillColor = getFillColor();
        if (fillColor.isValue()) {
            return ColorUtils.rgbaToColor(fillColor.getValue());
        }
        throw new RuntimeException("fill-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getFillColorTransition() {
        checkThread();
        return nativeGetFillColorTransition();
    }

    @NonNull
    public PropertyValue<Float> getFillOpacity() {
        checkThread();
        return new PropertyValue<>(FillOptions.PROPERTY_FILL_OPACITY, nativeGetFillOpacity());
    }

    @NonNull
    public TransitionOptions getFillOpacityTransition() {
        checkThread();
        return nativeGetFillOpacityTransition();
    }

    @NonNull
    public PropertyValue<String> getFillOutlineColor() {
        checkThread();
        return new PropertyValue<>(FillOptions.PROPERTY_FILL_OUTLINE_COLOR, nativeGetFillOutlineColor());
    }

    @ColorInt
    public int getFillOutlineColorAsInt() {
        checkThread();
        PropertyValue<String> fillOutlineColor = getFillOutlineColor();
        if (fillOutlineColor.isValue()) {
            return ColorUtils.rgbaToColor(fillOutlineColor.getValue());
        }
        throw new RuntimeException("fill-outline-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getFillOutlineColorTransition() {
        checkThread();
        return nativeGetFillOutlineColorTransition();
    }

    @NonNull
    public PropertyValue<String> getFillPattern() {
        checkThread();
        return new PropertyValue<>(FillOptions.PROPERTY_FILL_PATTERN, nativeGetFillPattern());
    }

    @NonNull
    public TransitionOptions getFillPatternTransition() {
        checkThread();
        return nativeGetFillPatternTransition();
    }

    @NonNull
    public PropertyValue<Float> getFillSortKey() {
        checkThread();
        return new PropertyValue<>("fill-sort-key", nativeGetFillSortKey());
    }

    @NonNull
    public PropertyValue<Float[]> getFillTranslate() {
        checkThread();
        return new PropertyValue<>("fill-translate", nativeGetFillTranslate());
    }

    @NonNull
    public PropertyValue<String> getFillTranslateAnchor() {
        checkThread();
        return new PropertyValue<>("fill-translate-anchor", nativeGetFillTranslateAnchor());
    }

    @NonNull
    public TransitionOptions getFillTranslateTransition() {
        checkThread();
        return nativeGetFillTranslateTransition();
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

    public void setFillColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetFillColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setFillOpacityTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetFillOpacityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setFillOutlineColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetFillOutlineColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setFillPatternTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetFillPatternTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setFillTranslateTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetFillTranslateTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
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
    public FillLayer withFilter(@NonNull Expression expression) {
        setFilter(expression);
        return this;
    }

    @NonNull
    public FillLayer withProperties(@NonNull PropertyValue<?>... propertyValueArr) {
        setProperties(propertyValueArr);
        return this;
    }

    @NonNull
    public FillLayer withSourceLayer(String str) {
        setSourceLayer(str);
        return this;
    }

    public FillLayer(String str, String str2) {
        initialize(str, str2);
    }
}
