package com.crrepa.ble.trans.upgrade.bean;
/* loaded from: classes9.dex */
public class HSFirmwareInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f7687a;
    public String b;
    public String c;
    public String d;
    public String e;

    public String getAppFilePath() {
        return this.f7687a;
    }

    public String getConfigFilePath() {
        return this.b;
    }

    public String getPatchFilePath() {
        return this.c;
    }

    public String getUserFilePath() {
        return this.d;
    }

    public String getUserStartAddress() {
        return this.e;
    }

    public void setAppFilePath(String str) {
        this.f7687a = str;
    }

    public void setConfigFilePath(String str) {
        this.b = str;
    }

    public void setPatchFilePath(String str) {
        this.c = str;
    }

    public void setUserFilePath(String str) {
        this.d = str;
    }

    public void setUserStartAddress(String str) {
        this.e = str;
    }
}
