package com.ido.ble.dfu;
/* loaded from: classes11.dex */
public class BleDFUConfig {
    public static final int OTA_MODE_AUTOMATIC = 255;
    public static final int OTA_MODE_NORMAL_FUNCTION = 0;
    public static final int OTA_MODE_SILENT_EXTEND_FLASH = 17;
    public static final int OTA_MODE_SILENT_FUNCTION = 16;
    public static final int OTA_MODE_SILENT_NO_TEMP = 18;
    public static final int PLATE_FORM_AUTO = -1;
    public static final int PLATFORM_APOLLO3 = 30;
    public static final int PLATFORM_CYPRESS = 20;
    public static final int PLATFORM_NORDIC = 0;
    public static final int PLATFORM_REALTEK = 10;
    private int PRN;
    private String deviceId;
    private String filePath;
    private String macAddress;
    private int platform = -1;
    private boolean isNeedReOpenBluetoothSwitchIfFailed = true;
    private int maxRetryTime = 6;
    private boolean isNeedAuth = false;
    private int otaWorkMode = 0;

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public int getMaxRetryTime() {
        return this.maxRetryTime;
    }

    public int getOtaWorkMode() {
        return this.otaWorkMode;
    }

    public int getPRN() {
        return this.PRN;
    }

    public int getPlatform() {
        return this.platform;
    }

    public boolean isNeedAuth() {
        return this.isNeedAuth;
    }

    public boolean isNeedReOpenBluetoothSwitchIfFailed() {
        return this.isNeedReOpenBluetoothSwitchIfFailed;
    }

    public BleDFUConfig setDeviceId(String str) {
        this.deviceId = str;
        return this;
    }

    public BleDFUConfig setFilePath(String str) {
        this.filePath = str;
        return this;
    }

    public BleDFUConfig setMacAddress(String str) {
        this.macAddress = str;
        return this;
    }

    public BleDFUConfig setMaxRetryTime(int i) {
        this.maxRetryTime = i;
        return this;
    }

    public BleDFUConfig setNeedAuth(boolean z) {
        this.isNeedAuth = z;
        return this;
    }

    public BleDFUConfig setNeedReOpenBluetoothSwitchIfFailed(boolean z) {
        this.isNeedReOpenBluetoothSwitchIfFailed = z;
        return this;
    }

    public BleDFUConfig setOtaWorkMode(int i) {
        this.otaWorkMode = i;
        return this;
    }

    public BleDFUConfig setPRN(int i) {
        this.PRN = i;
        return this;
    }

    public BleDFUConfig setPlatform(int i) {
        this.platform = i;
        return this;
    }

    public String toString() {
        return "BleDFUConfig{filePath='" + this.filePath + "', macAddress='" + this.macAddress + "', deviceId='" + this.deviceId + "', PRN=" + this.PRN + ", platform=" + this.platform + ", isNeedReOpenBluetoothSwitchIfFailed=" + this.isNeedReOpenBluetoothSwitchIfFailed + ", maxRetryTime=" + this.maxRetryTime + ", isNeedAuth=" + this.isNeedAuth + '}';
    }
}
