package com.mappls.sdk.maps.style.layers;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.google.gson.JsonElement;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.utils.ColorUtils;
@UiThread
/* loaded from: classes11.dex */
public class FillExtrusionLayer extends Layer {
    @Keep
    public FillExtrusionLayer(long j) {
        super(j);
    }

    @NonNull
    @Keep
    private native Object nativeGetFillExtrusionBase();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetFillExtrusionBaseTransition();

    @NonNull
    @Keep
    private native Object nativeGetFillExtrusionColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetFillExtrusionColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetFillExtrusionHeight();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetFillExtrusionHeightTransition();

    @NonNull
    @Keep
    private native Object nativeGetFillExtrusionOpacity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetFillExtrusionOpacityTransition();

    @NonNull
    @Keep
    private native Object nativeGetFillExtrusionPattern();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetFillExtrusionPatternTransition();

    @NonNull
    @Keep
    private native Object nativeGetFillExtrusionTranslate();

    @NonNull
    @Keep
    private native Object nativeGetFillExtrusionTranslateAnchor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetFillExtrusionTranslateTransition();

    @NonNull
    @Keep
    private native Object nativeGetFillExtrusionVerticalGradient();

    @Keep
    private native void nativeSetFillExtrusionBaseTransition(long j, long j2);

    @Keep
    private native void nativeSetFillExtrusionColorTransition(long j, long j2);

    @Keep
    private native void nativeSetFillExtrusionHeightTransition(long j, long j2);

    @Keep
    private native void nativeSetFillExtrusionOpacityTransition(long j, long j2);

    @Keep
    private native void nativeSetFillExtrusionPatternTransition(long j, long j2);

    @Keep
    private native void nativeSetFillExtrusionTranslateTransition(long j, long j2);

    @Override // com.mappls.sdk.maps.style.layers.Layer
    @Keep
    public native void finalize() throws Throwable;

    @NonNull
    public PropertyValue<Float> getFillExtrusionBase() {
        checkThread();
        return new PropertyValue<>("fill-extrusion-base", nativeGetFillExtrusionBase());
    }

    @NonNull
    public TransitionOptions getFillExtrusionBaseTransition() {
        checkThread();
        return nativeGetFillExtrusionBaseTransition();
    }

    @NonNull
    public PropertyValue<String> getFillExtrusionColor() {
        checkThread();
        return new PropertyValue<>("fill-extrusion-color", nativeGetFillExtrusionColor());
    }

    @ColorInt
    public int getFillExtrusionColorAsInt() {
        checkThread();
        PropertyValue<String> fillExtrusionColor = getFillExtrusionColor();
        if (fillExtrusionColor.isValue()) {
            return ColorUtils.rgbaToColor(fillExtrusionColor.getValue());
        }
        throw new RuntimeException("fill-extrusion-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getFillExtrusionColorTransition() {
        checkThread();
        return nativeGetFillExtrusionColorTransition();
    }

    @NonNull
    public PropertyValue<Float> getFillExtrusionHeight() {
        checkThread();
        return new PropertyValue<>("fill-extrusion-height", nativeGetFillExtrusionHeight());
    }

    @NonNull
    public TransitionOptions getFillExtrusionHeightTransition() {
        checkThread();
        return nativeGetFillExtrusionHeightTransition();
    }

    @NonNull
    public PropertyValue<Float> getFillExtrusionOpacity() {
        checkThread();
        return new PropertyValue<>("fill-extrusion-opacity", nativeGetFillExtrusionOpacity());
    }

    @NonNull
    public TransitionOptions getFillExtrusionOpacityTransition() {
        checkThread();
        return nativeGetFillExtrusionOpacityTransition();
    }

    @NonNull
    public PropertyValue<String> getFillExtrusionPattern() {
        checkThread();
        return new PropertyValue<>("fill-extrusion-pattern", nativeGetFillExtrusionPattern());
    }

    @NonNull
    public TransitionOptions getFillExtrusionPatternTransition() {
        checkThread();
        return nativeGetFillExtrusionPatternTransition();
    }

    @NonNull
    public PropertyValue<Float[]> getFillExtrusionTranslate() {
        checkThread();
        return new PropertyValue<>("fill-extrusion-translate", nativeGetFillExtrusionTranslate());
    }

    @NonNull
    public PropertyValue<String> getFillExtrusionTranslateAnchor() {
        checkThread();
        return new PropertyValue<>("fill-extrusion-translate-anchor", nativeGetFillExtrusionTranslateAnchor());
    }

    @NonNull
    public TransitionOptions getFillExtrusionTranslateTransition() {
        checkThread();
        return nativeGetFillExtrusionTranslateTransition();
    }

    @NonNull
    public PropertyValue<Boolean> getFillExtrusionVerticalGradient() {
        checkThread();
        return new PropertyValue<>("fill-extrusion-vertical-gradient", nativeGetFillExtrusionVerticalGradient());
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

    public void setFillExtrusionBaseTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetFillExtrusionBaseTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setFillExtrusionColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetFillExtrusionColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setFillExtrusionHeightTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetFillExtrusionHeightTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setFillExtrusionOpacityTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetFillExtrusionOpacityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setFillExtrusionPatternTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetFillExtrusionPatternTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setFillExtrusionTranslateTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetFillExtrusionTranslateTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
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
    public FillExtrusionLayer withFilter(@NonNull Expression expression) {
        setFilter(expression);
        return this;
    }

    @NonNull
    public FillExtrusionLayer withProperties(@NonNull PropertyValue<?>... propertyValueArr) {
        setProperties(propertyValueArr);
        return this;
    }

    @NonNull
    public FillExtrusionLayer withSourceLayer(String str) {
        setSourceLayer(str);
        return this;
    }

    public FillExtrusionLayer(String str, String str2) {
        initialize(str, str2);
    }
}
