package com.coveiot.coveaccess.model.server;
/* loaded from: classes8.dex */
public enum AppConnectivityCodeEnum {
    CONNECTED("CONNECTED"),
    DISCONNECTED("DISCONNECTED"),
    ERR_CONNECTION_FAILED("ERR_CONNECTION_FAILED");
    
    private String appConnectivityCode;

    AppConnectivityCodeEnum(String str) {
        this.appConnectivityCode = str;
    }

    public String getAppConnectivityCode() {
        return this.appConnectivityCode;
    }
}
