package com.abupdate.iot_libs.inter;
/* loaded from: classes.dex */
public interface IDownloadListener {
    void onCancel();

    void onCompleted();

    void onDownloadProgress(long j, long j2);

    void onFailed(int i);

    void onPrepare();
}
