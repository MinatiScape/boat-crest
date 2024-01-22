package com.ido.ble.business.sync;
/* loaded from: classes11.dex */
public interface ISyncProgressListener {
    void onFailed();

    void onProgress(int i);

    void onStart();

    void onSuccess();
}
