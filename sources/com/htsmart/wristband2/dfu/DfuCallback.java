package com.htsmart.wristband2.dfu;
/* loaded from: classes11.dex */
public interface DfuCallback {
    void onError(int i, int i2);

    void onProgressChanged(int i);

    void onStateChanged(int i, boolean z);

    void onSuccess();
}
