package com.mappls.sdk.maps.style.light;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.mappls.sdk.maps.style.layers.TransitionOptions;
import com.mappls.sdk.maps.utils.ColorUtils;
import com.mappls.sdk.maps.utils.ThreadUtils;
@UiThread
/* loaded from: classes11.dex */
public class Light {
    @Keep
    private long nativePtr;

    @Keep
    public Light(long j) {
        a();
        this.nativePtr = j;
    }

    @NonNull
    @Keep
    private native String nativeGetAnchor();

    @NonNull
    @Keep
    private native String nativeGetColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetColorTransition();

    @NonNull
    @Keep
    private native float nativeGetIntensity();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetIntensityTransition();

    @NonNull
    @Keep
    private native Position nativeGetPosition();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetPositionTransition();

    @Keep
    private native void nativeSetAnchor(String str);

    @Keep
    private native void nativeSetColor(String str);

    @Keep
    private native void nativeSetColorTransition(long j, long j2);

    @Keep
    private native void nativeSetIntensity(float f);

    @Keep
    private native void nativeSetIntensityTransition(long j, long j2);

    @Keep
    private native void nativeSetPosition(Position position);

    @Keep
    private native void nativeSetPositionTransition(long j, long j2);

    public final void a() {
        ThreadUtils.checkThread("Mbgl-Light");
    }

    @NonNull
    public String getAnchor() {
        a();
        return nativeGetAnchor();
    }

    @NonNull
    public String getColor() {
        a();
        return nativeGetColor();
    }

    @NonNull
    public TransitionOptions getColorTransition() {
        a();
        return nativeGetColorTransition();
    }

    @NonNull
    public float getIntensity() {
        a();
        return nativeGetIntensity();
    }

    @NonNull
    public TransitionOptions getIntensityTransition() {
        a();
        return nativeGetIntensityTransition();
    }

    @NonNull
    public Position getPosition() {
        a();
        return nativeGetPosition();
    }

    @NonNull
    public TransitionOptions getPositionTransition() {
        a();
        return nativeGetPositionTransition();
    }

    public void setAnchor(String str) {
        a();
        nativeSetAnchor(str);
    }

    public void setColor(@ColorInt int i) {
        a();
        nativeSetColor(ColorUtils.colorToRgbaString(i));
    }

    public void setColorTransition(@NonNull TransitionOptions transitionOptions) {
        a();
        nativeSetColorTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setIntensity(float f) {
        a();
        nativeSetIntensity(f);
    }

    public void setIntensityTransition(@NonNull TransitionOptions transitionOptions) {
        a();
        nativeSetIntensityTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setPosition(@NonNull Position position) {
        a();
        nativeSetPosition(position);
    }

    public void setPositionTransition(@NonNull TransitionOptions transitionOptions) {
        a();
        nativeSetPositionTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    public void setColor(String str) {
        a();
        nativeSetColor(str);
    }
}
