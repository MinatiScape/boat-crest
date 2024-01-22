package com.ido.ble;
/* loaded from: classes11.dex */
public class InitParam {
    public String databaseName;
    public int log_save_days;
    public String log_save_path;
    public String soJinLogSavePath = "";
    public boolean isNeedSoLibAutoSyncConfigIfReboot = true;
    public boolean isEnableLog = true;
    public boolean isSaveDeviceDataToDB = true;
    public boolean isEncryptedDBData = false;
    public boolean isEncryptedSPData = false;

    public String toString() {
        return "InitParam{log_save_path='" + this.log_save_path + "', log_save_days=" + this.log_save_days + ", soJinLogSavePath='" + this.soJinLogSavePath + "', isNeedSoLibAutoSyncConfigIfReboot=" + this.isNeedSoLibAutoSyncConfigIfReboot + ", isEnableLog=" + this.isEnableLog + ", isSaveDeviceDataToDB=" + this.isSaveDeviceDataToDB + ", isEncryptedDBData=" + this.isEncryptedDBData + ", isEncryptedSPData=" + this.isEncryptedSPData + ", databaseName='" + this.databaseName + "'}";
    }
}
