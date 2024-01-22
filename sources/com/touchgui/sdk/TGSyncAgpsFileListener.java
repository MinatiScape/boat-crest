package com.touchgui.sdk;
/* loaded from: classes12.dex */
public interface TGSyncAgpsFileListener {
    void onCompleted();

    void onError(Throwable th);

    void onProgress(int i);
}
