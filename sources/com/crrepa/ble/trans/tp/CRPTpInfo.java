package com.crrepa.ble.trans.tp;

import java.io.File;
/* loaded from: classes9.dex */
public class CRPTpInfo {

    /* renamed from: a  reason: collision with root package name */
    public File f7683a;
    public int b;
    public int c;
    public int d;
    public String e;

    public int getDeviceStartIndex() {
        return this.d;
    }

    public File getFile() {
        return this.f7683a;
    }

    public String getFirmwareVersion() {
        return this.e;
    }

    public int getLength() {
        return this.c;
    }

    public int getStartIndex() {
        return this.b;
    }

    public void setDeviceStartIndex(int i) {
        this.d = i;
    }

    public void setFile(File file) {
        this.f7683a = file;
    }

    public void setFirmwareVersion(String str) {
        this.e = str;
    }

    public void setLength(int i) {
        this.c = i;
    }

    public void setStartIndex(int i) {
        this.b = i;
    }
}
