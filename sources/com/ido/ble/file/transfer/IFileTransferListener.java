package com.ido.ble.file.transfer;
/* loaded from: classes11.dex */
public interface IFileTransferListener {
    void onFailed(String str);

    void onProgress(int i);

    void onStart();

    void onSuccess();
}
