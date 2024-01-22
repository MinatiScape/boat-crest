package com.htsmart.wristband2.bean;
/* loaded from: classes11.dex */
public class SportPush {

    /* renamed from: a  reason: collision with root package name */
    public int f11966a;
    public boolean b;
    public boolean c;
    public byte d;

    public byte getBinFlag() {
        return this.d;
    }

    public int getSportType() {
        return this.f11966a;
    }

    public boolean isExist() {
        return this.b;
    }

    public boolean isPushEnabled() {
        return this.c;
    }

    public void setBinFlag(byte b) {
        this.d = b;
    }

    public void setExist(boolean z) {
        this.b = z;
    }

    public void setPushEnabled(boolean z) {
        this.c = z;
    }

    public void setSportType(int i) {
        this.f11966a = i;
    }
}
