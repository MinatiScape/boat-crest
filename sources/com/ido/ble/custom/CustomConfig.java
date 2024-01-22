package com.ido.ble.custom;

import com.ido.ble.custom.connect.CustomGattCallBack;
import com.ido.ble.custom.connect.CustomGattCallBackImp;
/* loaded from: classes11.dex */
public class CustomConfig {
    private static CustomConfig customConfig;
    private String databaseName;
    private CustomGattCallBack.IEnableNotifyCallback iEnableNotifyCallback;
    private boolean isEnableLog;
    private boolean isSaveDeviceDataToDB;
    private int logSaveDays;
    private String logSavePath;
    private boolean autoConnectIfBreak = true;
    private boolean isCustomManageConnection = false;
    private boolean isNeedRemoveBondBeforeConnect = true;
    private boolean isEncryptedDBData = false;
    private boolean isEncryptedSPData = false;

    private CustomConfig() {
    }

    public static CustomConfig getConfig() {
        if (customConfig == null) {
            customConfig = new CustomConfig();
        }
        return customConfig;
    }

    public CustomGattCallBack.ICallBack getCustomGattCallBack() {
        return new CustomGattCallBackImp();
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public CustomGattCallBack.IEnableNotifyCallback getIEnableNotifyCallback() {
        return this.iEnableNotifyCallback;
    }

    public int getLogSaveDays() {
        return this.logSaveDays;
    }

    public String getLogSavePath() {
        return this.logSavePath;
    }

    public boolean isAutoConnectIfBreak() {
        return this.autoConnectIfBreak;
    }

    public boolean isCustomManageConnection() {
        return this.isCustomManageConnection;
    }

    public boolean isEnableLog() {
        return this.isEnableLog;
    }

    public boolean isEncryptedDBData() {
        return this.isEncryptedDBData;
    }

    public boolean isEncryptedSPData() {
        return this.isEncryptedSPData;
    }

    public boolean isNeedRemoveBondBeforeConnect() {
        return this.isNeedRemoveBondBeforeConnect;
    }

    public boolean isSaveDeviceDataToDB() {
        return this.isSaveDeviceDataToDB;
    }

    public CustomConfig setAutoConnectIfBreak(boolean z) {
        this.autoConnectIfBreak = z;
        return this;
    }

    public CustomConfig setDatabaseName(String str) {
        this.databaseName = str;
        return this;
    }

    public CustomConfig setEnableLog(boolean z) {
        this.isEnableLog = z;
        return this;
    }

    public CustomConfig setEnableNotifyCallBack(CustomGattCallBack.IEnableNotifyCallback iEnableNotifyCallback) {
        this.iEnableNotifyCallback = iEnableNotifyCallback;
        return this;
    }

    public CustomConfig setEncryptedDBData(boolean z) {
        this.isEncryptedDBData = z;
        return this;
    }

    public CustomConfig setEncryptedSPData(boolean z) {
        this.isEncryptedSPData = z;
        return this;
    }

    public CustomConfig setIsCustomManageConnection(boolean z) {
        this.isCustomManageConnection = z;
        return this;
    }

    public CustomConfig setIsSaveDeviceDataToDB(boolean z) {
        this.isSaveDeviceDataToDB = z;
        return this;
    }

    public CustomConfig setLogSaveDays(int i) {
        this.logSaveDays = i;
        return this;
    }

    public CustomConfig setLogSavePath(String str) {
        this.logSavePath = str;
        return this;
    }

    public CustomConfig setNeedRemoveBondBeforeConnect(boolean z) {
        this.isNeedRemoveBondBeforeConnect = z;
        return this;
    }
}
