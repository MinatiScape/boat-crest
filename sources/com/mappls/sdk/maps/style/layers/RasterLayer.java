package com.mappls.sdk.maps.style.layers;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
@UiThread
/* loaded from: classes11.dex */
public class RasterLayer extends Layer {
    @Keep
    public RasterLayer(long j) {
        super(j);
    }

    @NonNull
    @Keep
    private native Object nativeGetRasterBrightnessMax();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetRasterBrightnessMaxTransition();

    @NonNull
    @Keep
    private native Object nativeGetRasterBrightnessMin();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetRasterBrightnessMinTransition();

    @NonNull
    @Keep
    private native Object nativeGetRasterContrast();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetRasterContrastTransition();

    @NonNull
    @Keep
    private native Object nativeGetRasterFadeDuration();

    @NonNull
    @Keep
    private native Object nativeGetRasterHueRotate();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetRasterHueRotateTransition();

    @NonNull
    @Keep
    private native Object nativeGetRasterOpacity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetRasterOpacityTransition();

    @NonNull
    @Keep
    private native Object nativeGetRasterResampling();

    @NonNull
    @Keep
    private native Object nativeGetRasterSaturation();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetRasterSaturationTransition();

    @Keep
    private native void nativeSetRasterBrightnessMaxTransition(long j, long j2);

    @Keep
    private native void nativeSetRasterBrightnessMinTransition(long j, long j2);

    @Keep
    private native void nativeSetRasterContrastTransition(long j, long j2);

    @Keep
    private native void nativeSetRasterHueRotateTransition(long j, long j2);

    @Keep
    private native void nativeSetRasterOpacityTransition(long j, long j2);

    @Keep
    private native void nativeSetRasterSaturationTransition(long j, long j2);

    @Override // com.mappls.sdk.maps.style.layers.Layer
    @Keep
    public native void finalize() throws Throwable;

    @NonNull
    public PropertyValue<Float> getRasterBrightnessMax() {
        checkThread();
        return new PropertyValue<>("raster-brightness-max", nativeGetRasterBrightnessMax());
    }

    @NonNull
    public TransitionOptions getRasterBrightnessMaxTransition() {
        checkThread();
        return nativeGetRasterBrightnessMaxTransition();
    }

    @NonNull
    public PropertyValue<Float> getRasterBrightnessMin() {
        checkThread();
        return new PropertyValue<>("raster-brightness-min", nativeGetRasterBrightnessMin());
    }

    @NonNull
    public TransitionOptions getRasterBrightnessMinTransition() {
        checkThread();
        return nativeGetRasterBrightnessMinTransition();
    }

    @NonNull
    public PropertyValue<Float> getRasterContrast() {
        checkThread();
        return new PropertyValue<>("raster-contrast", nativeGetRasterContrast());
    }

    @NonNull
    public TransitionOptions getRasterContrastTransition() {
        checkThread();
        return nativeGetRasterContrastTransition();
    }

    @NonNull
    public PropertyValue<Float> getRasterFadeDuration() {
        checkThread();
        return new PropertyValue<>("raster-fade-duration", nativeGetRasterFadeDuration());
    }

    @NonNull
    public PropertyValue<Float> getRasterHueRotate() {
        checkThread();
        return new PropertyValue<>("raster-hue-rotate", nativeGetRasterHueRotate());
    }

    @NonNull
    public TransitionOptions getRasterHueRotateTransition() {
        checkThread();
        return nativeGetRasterHueRotateTransition();
    }

    @NonNull
    public PropertyValue<Float> getRasterOpacity() {
        checkThread();
        return new PropertyValue<>("raster-opacity", nativeGetRasterOpacity());
    }

    @NonNull
    public TransitionOptions getRasterOpacityTransition() {
        checkThread();
        return nativeGetRasterOpacityTransition();
    }

    @NonNull
    public PropertyValue<String> getRasterResampling() {
        checkThread();
        return new PropertyValue<>("raster-resampling", nativeGetRasterResampling());
    }

    @NonNull
    public PropertyValue<Float> getRasterSaturation() {
        checkThread();
        return new PropertyValue<>("raster-saturation", nativeGetRasterSaturation());
    }

    @NonNull
    public TransitionOptions getRasterSaturationTransition() {
        checkThread();
        return nativeGetRasterSaturationTransition();
    }

    @NonNull
    public String getSourceId() {
        checkThread();
        return nativeGetSourceId();
    }

    @Keep
    public native void initialize(String str, String str2);

    public void setRasterBrightnessMaxTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetRasterBrightnessMaxTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setRasterBrightnessMinTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetRasterBrightnessMinTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setRasterContrastTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetRasterContrastTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setRasterHueRotateTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetRasterHueRotateTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setRasterOpacityTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetRasterOpacityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setRasterSaturationTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetRasterSaturationTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setSourceLayer(String str) {
        checkThread();
        nativeSetSourceLayer(str);
    }

    @NonNull
    public RasterLayer withProperties(@NonNull PropertyValue<?>... propertyValueArr) {
        setProperties(propertyValueArr);
        return this;
    }

    @NonNull
    public RasterLayer withSourceLayer(String str) {
        setSourceLayer(str);
        return this;
    }

    public RasterLayer(String str, String str2) {
        initialize(str, str2);
    }
}
