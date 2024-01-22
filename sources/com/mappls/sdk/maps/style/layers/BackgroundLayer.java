package com.mappls.sdk.maps.style.layers;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.mappls.sdk.maps.utils.ColorUtils;
@UiThread
/* loaded from: classes11.dex */
public class BackgroundLayer extends Layer {
    @Keep
    public BackgroundLayer(long j) {
        super(j);
    }

    @NonNull
    @Keep
    private native Object nativeGetBackgroundColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetBackgroundColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetBackgroundOpacity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetBackgroundOpacityTransition();

    @NonNull
    @Keep
    private native Object nativeGetBackgroundPattern();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetBackgroundPatternTransition();

    @Keep
    private native void nativeSetBackgroundColorTransition(long j, long j2);

    @Keep
    private native void nativeSetBackgroundOpacityTransition(long j, long j2);

    @Keep
    private native void nativeSetBackgroundPatternTransition(long j, long j2);

    @Override // com.mappls.sdk.maps.style.layers.Layer
    @Keep
    public native void finalize() throws Throwable;

    @NonNull
    public PropertyValue<String> getBackgroundColor() {
        checkThread();
        return new PropertyValue<>("background-color", nativeGetBackgroundColor());
    }

    @ColorInt
    public int getBackgroundColorAsInt() {
        checkThread();
        PropertyValue<String> backgroundColor = getBackgroundColor();
        if (backgroundColor.isValue()) {
            return ColorUtils.rgbaToColor(backgroundColor.getValue());
        }
        throw new RuntimeException("background-color was set as a Function");
    }

    @NonNull
    public TransitionOptions getBackgroundColorTransition() {
        checkThread();
        return nativeGetBackgroundColorTransition();
    }

    @NonNull
    public PropertyValue<Float> getBackgroundOpacity() {
        checkThread();
        return new PropertyValue<>("background-opacity", nativeGetBackgroundOpacity());
    }

    @NonNull
    public TransitionOptions getBackgroundOpacityTransition() {
        checkThread();
        return nativeGetBackgroundOpacityTransition();
    }

    @NonNull
    public PropertyValue<String> getBackgroundPattern() {
        checkThread();
        return new PropertyValue<>("background-pattern", nativeGetBackgroundPattern());
    }

    @NonNull
    public TransitionOptions getBackgroundPatternTransition() {
        checkThread();
        return nativeGetBackgroundPatternTransition();
    }

    @Keep
    public native void initialize(String str);

    public void setBackgroundColorTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetBackgroundColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setBackgroundOpacityTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetBackgroundOpacityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setBackgroundPatternTransition(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetBackgroundPatternTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    @NonNull
    public BackgroundLayer withProperties(@NonNull PropertyValue<?>... propertyValueArr) {
        setProperties(propertyValueArr);
        return this;
    }

    public BackgroundLayer(String str) {
        initialize(str);
    }
}
