package com.ido.ble.dfu.nodic.firmware;

import com.ido.ble.dfu.nodic.firmware.CheckNewVersionResponse;
/* loaded from: classes11.dex */
public class FirmwareListener {

    /* loaded from: classes11.dex */
    public interface ICheckNewVersionListener {
        void onCheckFailed();

        void onHasNewVersion(CheckNewVersionResponse.NewVersionInfo newVersionInfo);

        void onNoNewVersion();
    }

    /* loaded from: classes11.dex */
    public interface IDownloadListener {
        void onFailed();

        void onProgress(int i);

        void onStart();

        void onSuccess();
    }
}
