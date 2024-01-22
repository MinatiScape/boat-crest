package com.abupdate.iot_libs.inter;

import com.abupdate.iot_download_libs.DownEntity;
import com.abupdate.iot_download_libs.IOnDownListener;
import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.iot_libs.utils.c;
import java.io.File;
import java.util.List;
/* loaded from: classes.dex */
public abstract class IDownSimpleListener implements IOnDownListener {
    public void onCancel() {
    }

    public void onCompleted(File file) {
    }

    public void onDownloadProgress(long j, long j2, int i) {
    }

    public void onFailed(int i) {
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_all_progress(int i, long j, long j2) {
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_failed(DownEntity downEntity) {
        onFailed(downEntity.download_status);
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_finished(List<DownEntity> list, List<DownEntity> list2) {
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_manual_cancel() {
        onCancel();
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_progress(DownEntity downEntity, int i, long j, long j2) {
        onDownloadProgress(j, j2, i);
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_start() {
    }

    @Override // com.abupdate.iot_download_libs.IOnDownListener
    public void on_success(DownEntity downEntity) {
        if (new File(downEntity.file_path).exists()) {
            if (new File(OtaAgentPolicy.getConfig().updatePath).exists()) {
                new File(OtaAgentPolicy.getConfig().updatePath).delete();
            }
            c.b(downEntity.file_path, OtaAgentPolicy.config.updatePath);
        }
        onCompleted(new File(OtaAgentPolicy.getConfig().updatePath));
    }
}
