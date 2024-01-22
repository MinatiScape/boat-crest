package com.jstyle.blesdk1860.model;
/* loaded from: classes11.dex */
public class MyDeviceInfo extends SendData {

    /* renamed from: a  reason: collision with root package name */
    public boolean f12526a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public int h = 60;
    public int i = -1;
    public int j;

    public int getBaseheart() {
        return this.h;
    }

    public int getDialinterface() {
        return this.j;
    }

    public int getScreenBrightness() {
        return this.i;
    }

    public boolean isAlarm_clock_master_switch() {
        return this.d;
    }

    public boolean isChinese() {
        return this.g;
    }

    public boolean isDistanceUnit() {
        return this.f12526a;
    }

    public boolean isDo_not_disturb_mode_switch() {
        return this.e;
    }

    public boolean isHand_up_light_screen_switch() {
        return this.c;
    }

    public boolean isIs12Hour() {
        return this.b;
    }

    public boolean isNight_mode() {
        return this.f;
    }

    public void setAlarm_clock_master_switch(boolean z) {
        this.d = z;
    }

    public void setBaseheart(int i) {
        this.h = i;
    }

    public void setChinese(boolean z) {
        this.g = z;
    }

    public void setDialinterface(int i) {
        this.j = i;
    }

    public void setDistanceUnit(boolean z) {
        this.f12526a = z;
    }

    public void setDo_not_disturb_mode_switch(boolean z) {
        this.e = z;
    }

    public void setHand_up_light_screen_switch(boolean z) {
        this.c = z;
    }

    public void setIs12Hour(boolean z) {
        this.b = z;
    }

    public void setNight_mode(boolean z) {
        this.f = z;
    }

    public void setScreenBrightness(int i) {
        this.i = i;
    }
}
