package com.crrepa.ble.conn.listener;
/* loaded from: classes9.dex */
public interface CRPBleFirmwareUpgradeListener {
    public static final int DEVICE_MACADDR_NULL = 18;
    public static final int DFU_PROCESS_FAILED = 23;
    public static final int FIRMWARE_DOWNLOAD_FAILED = 17;
    public static final int FIRMWARE_VERSION_NULL = 20;
    public static final int NO_FOUND_DEVICE = 19;
    public static final int NO_NEW_FIRMWARE_VERSION = 21;

    void onError(int i, String str);

    void onFirmwareDownloadComplete();

    void onFirmwareDownloadStarting();

    void onUpgradeAborted();

    void onUpgradeCompleted();

    void onUpgradeProgressChanged(int i, float f);

    void onUpgradeProgressStarting();
}
