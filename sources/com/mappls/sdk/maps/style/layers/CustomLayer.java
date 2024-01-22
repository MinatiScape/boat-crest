package com.mappls.sdk.maps.style.layers;

import androidx.annotation.Keep;
/* loaded from: classes11.dex */
public class CustomLayer extends Layer {
    public CustomLayer(String str, long j) {
        initialize(str, j);
    }

    @Override // com.mappls.sdk.maps.style.layers.Layer
    @Keep
    public native void finalize() throws Throwable;

    @Keep
    public native void initialize(String str, long j);

    @Keep
    @Deprecated
    public void update() {
    }

    @Keep
    public CustomLayer(long j) {
        super(j);
    }
}
