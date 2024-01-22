package com.ido.ble.gps.agps;
/* loaded from: classes11.dex */
public interface IAGpsTranslateStateListener {
    void onFailed(String str);

    void onProgress(int i);

    void onStart();

    void onSuccess();
}
