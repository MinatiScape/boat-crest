package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.Date;
/* loaded from: classes12.dex */
public class TGGpsInfo {
    private int agpsErrorCode;
    private int agpsInfo;
    @Nullable
    private Date date;
    private int errCode;
    private int fwVersion;
    private int gns;
    private int startMode;

    public int getAgpsErrorCode() {
        return this.agpsErrorCode;
    }

    public int getAgpsInfo() {
        return this.agpsInfo;
    }

    @Nullable
    public Date getDate() {
        return this.date;
    }

    public int getErrCode() {
        return this.errCode;
    }

    public int getFwVersion() {
        return this.fwVersion;
    }

    public int getGns() {
        return this.gns;
    }

    public int getStartMode() {
        return this.startMode;
    }

    public void setAgpsErrorCode(int i) {
        this.agpsErrorCode = i;
    }

    public void setAgpsInfo(int i) {
        this.agpsInfo = i;
    }

    public void setDate(@Nullable Date date) {
        this.date = date;
    }

    public void setErrCode(int i) {
        this.errCode = i;
    }

    public void setFwVersion(int i) {
        this.fwVersion = i;
    }

    public void setGns(int i) {
        this.gns = i;
    }

    public void setStartMode(int i) {
        this.startMode = i;
    }

    public String toString() {
        return "GpsInfo{errCode=" + this.errCode + ", fwVersion=" + this.fwVersion + ", agpsInfo=" + this.agpsInfo + ", agpsErrorCode=" + this.agpsErrorCode + ", date=" + this.date + ", startMode=" + this.startMode + ", gns=" + this.gns + '}';
    }
}
