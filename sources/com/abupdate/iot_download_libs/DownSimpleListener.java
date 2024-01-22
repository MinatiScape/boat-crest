package com.abupdate.iot_download_libs;

import java.util.List;
/* loaded from: classes.dex */
public abstract class DownSimpleListener implements IOnDownListener {
    public static DownSimpleListener INSTANCE = new a();

    /* loaded from: classes.dex */
    public static class a extends DownSimpleListener {
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_all_progress(int i, long j, long j2) {
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_failed(DownEntity downEntity) {
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_finished(List<DownEntity> list, List<DownEntity> list2) {
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_manual_cancel() {
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_progress(DownEntity downEntity, int i, long j, long j2) {
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_start() {
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_success(DownEntity downEntity) {
    }
}
