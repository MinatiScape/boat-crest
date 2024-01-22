package com.abupdate.iot_download_libs;

import java.util.List;
/* loaded from: classes.dex */
public interface IOnDownListener {
    void on_all_progress(int i, long j, long j2);

    void on_failed(DownEntity downEntity);

    void on_finished(List<DownEntity> list, List<DownEntity> list2);

    void on_manual_cancel();

    void on_progress(DownEntity downEntity, int i, long j, long j2);

    void on_start();

    void on_success(DownEntity downEntity);
}
