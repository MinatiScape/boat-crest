package com.coveiot.coveaccess.userappsetting;
/* loaded from: classes8.dex */
public class SaveLiftWristToViewSettingsReq {

    /* renamed from: a  reason: collision with root package name */
    public boolean f6844a;
    public String b;
    public String c;

    public String getEndTime() {
        return this.c;
    }

    public String getStartTime() {
        return this.b;
    }

    public boolean isActive() {
        return this.f6844a;
    }

    public void setActive(boolean z) {
        this.f6844a = z;
    }

    public void setEndTime(String str) {
        this.c = str;
    }

    public void setStartTime(String str) {
        this.b = str;
    }
}
