package com.mappls.sdk.maps.renderer;

import androidx.annotation.Keep;
/* loaded from: classes11.dex */
public interface MapRendererScheduler {
    @Keep
    void queueEvent(Runnable runnable);

    @Keep
    void requestRender();
}
