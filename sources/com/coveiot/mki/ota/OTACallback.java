package com.coveiot.mki.ota;
/* loaded from: classes9.dex */
public interface OTACallback {
    void onAborted(OTAManager oTAManager);

    void onCompleted(OTAManager oTAManager);

    void onError(OTAManager oTAManager, String str);

    void onProgress(OTAManager oTAManager, float f);

    void onStarted(OTAManager oTAManager);
}
