package com.mappls.sdk.maps.style.layers;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.mappls.sdk.maps.utils.ColorUtils;
@UiThread
/* loaded from: classes11.dex */
public class HillshadeLayer extends Layer {
    @Keep
    public HillshadeLayer(long j) {
        super(j);
    }

    @NonNull
    @Keep
    private native Object nativeGetHillshadeAccentColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetHillshadeAccentColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetHillshadeExaggeration();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetHillshadeExaggerationTransition();

    @NonNull
    @Keep
    private native Object nativeGetHillshadeHighlightColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetHillshadeHighlightColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetHillshadeIlluminationAnchor();

    @NonNull
    @Keep
    private native Object nativeGetHillshadeIlluminationDirection();

    @NonNull
    @Keep
    private native Object nativeGetHillshadeShadowColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetHillshadeShadowColorTransition();

    @Keep
    private native void nativeSetHillshadeAccentColorTransition(long j, long j2);

    @Keep
    private native void nativeSetHillshadeExaggerationTransition(long j, long j2);

    @Keep
    private native void nativeSetHillshadeHighlightColorTransition(long j, long j2);

    @Keep
    private native void nativeSetHillshadeShadowColorTransition(long j, long j2);

    @Override // com.mappls.sdk.maps.style.layers.Layer
    @Keep
    public native void finalize() throws Throwable;

    @NonNull
    public PropertyValue<String> getHillshadeAccentColor() {
        checkThread();
        return new PropertyValue<>("hillshade-accent-color", nativeGetHillshadeAccentColor());
    }

    @ColorInt
    public int getHillshadeAccentColorAsInt() {
        checkThread();
        PropertyValue<String> hillshadeAccentColor = getHillshadeAccentColor();
        if (hillshadeAccentColor.isValue()) {
            return ColorUtils.rgbaToColor(hillshadeAccentColor.getValue());
        }
        throw new RuntimeException("hillshade-accent-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getHillshadeAccentColorTransition() {
        checkThread();
        return nativeGetHillshadeAccentColorTransition();
    }

    @NonNull
    public PropertyValue<Float> getHillshadeExaggeration() {
        checkThread();
        return new PropertyValue<>("hillshade-exaggeration", nativeGetHillshadeExaggeration());
    }

    @NonNull
    public TransitionOptions getHillshadeExaggerationTransition() {
        checkThread();
        return nativeGetHillshadeExaggerationTransition();
    }

    @NonNull
    public PropertyValue<String> getHillshadeHighlightColor() {
        checkThread();
        return new PropertyValue<>("hillshade-highlight-color", nativeGetHillshadeHighlightColor());
    }

    @ColorInt
    public int getHillshadeHighlightColorAsInt() {
        checkThread();
        PropertyValue<String> hillshadeHighlightColor = getHillshadeHighlightColor();
        if (hillshadeHighlightColor.isValue()) {
            return ColorUtils.rgbaToColor(hillshadeHighlightColor.getValue());
        }
        throw new RuntimeException("hillshade-highlight-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getHillshadeHighlightColorTransition() {
        checkThread();
        return nativeGetHillshadeHighlightColorTransition();
    }

    @NonNull
    public PropertyValue<String> getHillshadeIlluminationAnchor() {
        checkThread();
        return new PropertyValue<>("hillshade-illumination-anchor", nativeGetHillshadeIlluminationAnchor());
    }

    @NonNull
    public PropertyValue<Float> getHillshadeIlluminationDirection() {
        checkThread();
        return new PropertyValue<>("hillshade-illumination-direction", nativeGetHillshadeIlluminationDirection());
    }

    @NonNull
    public PropertyValue<String> getHillshadeShadowColor() {
        checkThread();
        return new PropertyValue<>("hillshade-shadow-color", nativeGetHillshadeShadowColor());
    }

    @ColorInt
    public int getHillshadeShadowColorAsInt() {
        checkThread();
        PropertyValue<String> hillshadeShadowColor = getHillshadeShadowColor();
        if (hillshadeShadowColor.isValue()) {
            return ColorUtils.rgbaToColor(hillshadeShadowColor.getValue());
        }
        throw new RuntimeException("hillshade-shadow-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getHillshadeShadowColorTransition() {
        checkThread();
        return nativeGetHillshadeShadowColorTransition();
    }

    @NonNull
    public String getSourceId() {
        checkThread();
        return nativeGetSourceId();
    }

    @Keep
    public native void initialize(String str, String str2);

    public void setHillshadeAccentColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetHillshadeAccentColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setHillshadeExaggerationTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetHillshadeExaggerationTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setHillshadeHighlightColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetHillshadeHighlightColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setHillshadeShadowColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetHillshadeShadowColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setSourceLayer(String str) {
        checkThread();
        nativeSetSourceLayer(str);
    }

    @NonNull
    public HillshadeLayer withProperties(@NonNull PropertyValue<?>... propertyValueArr) {
        setProperties(propertyValueArr);
        return this;
    }

    @NonNull
    public HillshadeLayer withSourceLayer(String str) {
        setSourceLayer(str);
        return this;
    }

    public HillshadeLayer(String str, String str2) {
        initialize(str, str2);
    }
}
