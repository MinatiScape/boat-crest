package com.coveiot.coveaccess.userappsetting;
/* loaded from: classes8.dex */
public class SaveDrinkReminderSettingsReq {

    /* renamed from: a  reason: collision with root package name */
    public boolean f6840a;
    public String b;
    public String c;
    public String d;

    public String getEndTime() {
        return this.c;
    }

    public String getInterval() {
        return this.d;
    }

    public String getStartTime() {
        return this.b;
    }

    public boolean isActive() {
        return this.f6840a;
    }

    public void setActive(boolean z) {
        this.f6840a = z;
    }

    public void setEndTime(String str) {
        this.c = str;
    }

    public void setInterval(String str) {
        this.d = str;
    }

    public void setStartTime(String str) {
        this.b = str;
    }
}
