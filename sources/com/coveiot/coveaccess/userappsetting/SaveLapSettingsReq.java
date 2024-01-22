package com.coveiot.coveaccess.userappsetting;
/* loaded from: classes8.dex */
public class SaveLapSettingsReq {

    /* renamed from: a  reason: collision with root package name */
    public int f6843a;
    public int b;
    public int c;

    public int getCycleDistance() {
        return this.c;
    }

    public int getRunDistance() {
        return this.b;
    }

    public int getWalkDistance() {
        return this.f6843a;
    }

    public void setCycleDistance(int i) {
        this.c = i;
    }

    public void setRunDistance(int i) {
        this.b = i;
    }

    public void setWalkDistance(int i) {
        this.f6843a = i;
    }
}
