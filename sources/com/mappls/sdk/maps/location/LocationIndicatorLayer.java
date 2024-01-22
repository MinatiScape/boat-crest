package com.mappls.sdk.maps.location;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.TransitionOptions;
@UiThread
/* loaded from: classes11.dex */
class LocationIndicatorLayer extends Layer {
    @Keep
    public LocationIndicatorLayer(long j) {
        super(j);
    }

    @NonNull
    @Keep
    private native Object nativeGetAccuracyRadius();

    @NonNull
    @Keep
    private native Object nativeGetAccuracyRadiusBorderColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetAccuracyRadiusBorderColorTransition();

    @NonNull
    @Keep
    private native Object nativeGetAccuracyRadiusColor();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetAccuracyRadiusColorTransition();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetAccuracyRadiusTransition();

    @NonNull
    @Keep
    private native Object nativeGetBearing();

    @NonNull
    @Keep
    private native Object nativeGetBearingImage();

    @NonNull
    @Keep
    private native Object nativeGetBearingImageSize();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetBearingImageSizeTransition();

    @NonNull
    @Keep
    private native Object nativeGetImageTiltDisplacement();

    @NonNull
    @Keep
    private native Object nativeGetLocation();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetLocationTransition();

    @NonNull
    @Keep
    private native Object nativeGetPerspectiveCompensation();

    @NonNull
    @Keep
    private native Object nativeGetShadowImage();

    @NonNull
    @Keep
    private native Object nativeGetShadowImageSize();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetShadowImageSizeTransition();

    @NonNull
    @Keep
    private native Object nativeGetTopImage();

    @NonNull
    @Keep
    private native Object nativeGetTopImageSize();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetTopImageSizeTransition();

    @Keep
    private native void nativeSetAccuracyRadiusBorderColorTransition(long j, long j2);

    @Keep
    private native void nativeSetAccuracyRadiusColorTransition(long j, long j2);

    @Keep
    private native void nativeSetAccuracyRadiusTransition(long j, long j2);

    @Keep
    private native void nativeSetBearingImageSizeTransition(long j, long j2);

    @Keep
    private native void nativeSetLocationTransition(long j, long j2);

    @Keep
    private native void nativeSetShadowImageSizeTransition(long j, long j2);

    @Keep
    private native void nativeSetTopImageSizeTransition(long j, long j2);

    public void b(@NonNull TransitionOptions transitionOptions) {
        checkThread();
        nativeSetLocationTransition(transitionOptions.getDuration(), transitionOptions.getDelay());
    }

    @Override // com.mappls.sdk.maps.style.layers.Layer
    @Keep
    public native void finalize() throws Throwable;

    @Keep
    public native void initialize(String str);

    public LocationIndicatorLayer(String str) {
        initialize(str);
    }
}
