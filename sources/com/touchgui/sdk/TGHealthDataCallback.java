package com.touchgui.sdk;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public interface TGHealthDataCallback {
    void onCompleted();

    void onError(int i, @NonNull String str);

    void onHealthData(@NonNull Object obj);

    void onProgress(int i);

    void onStart();
}
