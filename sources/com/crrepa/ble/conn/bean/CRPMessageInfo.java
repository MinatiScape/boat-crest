package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPMessageInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f7657a;
    public int b;
    public int c;
    public boolean d;
    public boolean e;

    public CRPMessageInfo() {
    }

    public CRPMessageInfo(String str, int i, int i2, boolean z, boolean z2) {
        this.f7657a = str;
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = z2;
    }

    public String getMessage() {
        return this.f7657a;
    }

    public int getType() {
        return this.b;
    }

    public int getVersionCode() {
        return this.c;
    }

    public boolean isHs() {
        return this.d;
    }

    public boolean isSmallScreen() {
        return this.e;
    }

    public void setHs(boolean z) {
        this.d = z;
    }

    public void setMessage(String str) {
        this.f7657a = str;
    }

    public void setSmallScreen(boolean z) {
        this.e = z;
    }

    public void setType(int i) {
        this.b = i;
    }

    public void setVersionCode(int i) {
        this.c = i;
    }
}
