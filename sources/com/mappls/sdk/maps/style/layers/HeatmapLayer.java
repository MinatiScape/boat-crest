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
public class HeatmapLayer extends Layer {
    @Keep
    public HeatmapLayer(long j) {
        super(j);
    }

    @NonNull
    @Keep
    private native Object nativeGetHeatmapColor();

    @NonNull
    @Keep
    private native Object nativeGetHeatmapIntensity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetHeatmapIntensityTransition();

    @NonNull
    @Keep
    private native Object nativeGetHeatmapOpacity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetHeatmapOpacityTransition();

    @NonNull
    @Keep
    private native Object nativeGetHeatmapRadius();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetHeatmapRadiusTransition();

    @NonNull
    @Keep
    private native Object nativeGetHeatmapWeight();

    @Keep
    private native void nativeSetHeatmapIntensityTransition(long j, long j2);

    @Keep
    private native void nativeSetHeatmapOpacityTransition(long j, long j2);

    @Keep
    private native void nativeSetHeatmapRadiusTransition(long j, long j2);

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
    public PropertyValue<String> getHeatmapColor() {
        checkThread();
        return new PropertyValue<>("heatmap-color", nativeGetHeatmapColor());
    }

    @ColorInt
    public int getHeatmapColorAsInt() {
        checkThread();
        PropertyValue<String> heatmapColor = getHeatmapColor();
        if (heatmapColor.isValue()) {
            return ColorUtils.rgbaToColor(heatmapColor.getValue());
        }
        throw new RuntimeException("heatmap-color was set as a Function");
    }

    @NonNull
    public PropertyValue<Float> getHeatmapIntensity() {
        checkThread();
        return new PropertyValue<>("heatmap-intensity", nativeGetHeatmapIntensity());
    }

    @NonNull
    public TransitionOptions getHeatmapIntensityTransition() {
        checkThread();
        return nativeGetHeatmapIntensityTransition();
    }

    @NonNull
    public PropertyValue<Float> getHeatmapOpacity() {
        checkThread();
        return new PropertyValue<>("heatmap-opacity", nativeGetHeatmapOpacity());
    }

    @NonNull
    public TransitionOptions getHeatmapOpacityTransition() {
        checkThread();
        return nativeGetHeatmapOpacityTransition();
    }

    @NonNull
    public PropertyValue<Float> getHeatmapRadius() {
        checkThread();
        return new PropertyValue<>("heatmap-radius", nativeGetHeatmapRadius());
    }

    @NonNull
    public TransitionOptions getHeatmapRadiusTransition() {
        checkThread();
        return nativeGetHeatmapRadiusTransition();
    }

    @NonNull
    public PropertyValue<Float> getHeatmapWeight() {
        checkThread();
        return new PropertyValue<>("heatmap-weight", nativeGetHeatmapWeight());
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

    public void setHeatmapIntensityTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetHeatmapIntensityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setHeatmapOpacityTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetHeatmapOpacityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setHeatmapRadiusTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetHeatmapRadiusTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setSourceLayer(String str) {
        checkThread();
        nativeSetSourceLayer(str);
    }

    @NonNull
    public HeatmapLayer withFilter(@NonNull Expression expression) {
        setFilter(expression);
        return this;
    }

    @NonNull
    public HeatmapLayer withProperties(@NonNull PropertyValue<?>... propertyValueArr) {
        setProperties(propertyValueArr);
        return this;
    }

    @NonNull
    public HeatmapLayer withSourceLayer(String str) {
        setSourceLayer(str);
        return this;
    }

    public HeatmapLayer(String str, String str2) {
        initialize(str, str2);
    }
}
