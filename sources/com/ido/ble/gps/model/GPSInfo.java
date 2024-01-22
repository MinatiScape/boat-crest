package com.ido.ble.gps.model;
/* loaded from: classes11.dex */
public class GPSInfo {
    public int agpsErrCode;
    public int agpsInfo;
    public int errCode;
    public int fwVersion;

    public String toString() {
        return "GPSInfo{errCode=" + this.errCode + ", fwVersion=" + this.fwVersion + ", agpsInfo=" + this.agpsInfo + ", agpsErrCode=" + this.agpsErrCode + '}';
    }
}
