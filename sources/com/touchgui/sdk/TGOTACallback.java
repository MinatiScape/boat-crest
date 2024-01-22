package com.touchgui.sdk;
/* loaded from: classes12.dex */
public interface TGOTACallback {
    void onCompleted();

    void onError(Throwable th);

    default void onFileProgress(int i, int i2) {
    }

    void onProgress(int i);
}
