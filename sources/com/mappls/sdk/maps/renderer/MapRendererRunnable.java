package com.mappls.sdk.maps.renderer;

import androidx.annotation.Keep;
@Keep
/* loaded from: classes11.dex */
class MapRendererRunnable implements Runnable {
    private final long nativePtr;

    public MapRendererRunnable(long j) {
        this.nativePtr = j;
    }

    private native void nativeInitialize();

    public native void finalize() throws Throwable;

    @Override // java.lang.Runnable
    public native void run();
}
