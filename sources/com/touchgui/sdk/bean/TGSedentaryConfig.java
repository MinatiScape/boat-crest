package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGSedentaryConfig {
    public static final int EVERYDAY_OFF = 254;
    public static final int EVERYDAY_ON = 255;
    private int interval;
    private boolean noontimeOnOff;
    private int noontimeStartHour;
    private int noontimeStartMinute;
    private int noontimeStopHour;
    private int noontimeStopMinute;
    private int repeat;
    private int startHour;
    private int startMinute;
    private int stopHour;
    private int stopMinute;

    public int getInterval() {
        return this.interval;
    }

    public int getNoontimeStartHour() {
        return this.noontimeStartHour;
    }

    public int getNoontimeStartMinute() {
        return this.noontimeStartMinute;
    }

    public int getNoontimeStopHour() {
        return this.noontimeStopHour;
    }

    public int getNoontimeStopMinute() {
        return this.noontimeStopMinute;
    }

    public int getRepeat() {
        return this.repeat;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public int getStopHour() {
        return this.stopHour;
    }

    public int getStopMinute() {
        return this.stopMinute;
    }

    public boolean[] getWeekRepeat() {
        boolean[] zArr = new boolean[7];
        for (int i = 0; i < 7; i++) {
            zArr[i] = ((this.repeat >> i) & 2) == 2;
        }
        return zArr;
    }

    public boolean isNoontimeOnOff() {
        return this.noontimeOnOff;
    }

    public boolean isOn() {
        return (this.repeat & 1) == 1;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setNoontimeOnOff(boolean z) {
        this.noontimeOnOff = z;
    }

    public void setNoontimeStartHour(int i) {
        this.noontimeStartHour = i;
    }

    public void setNoontimeStartMinute(int i) {
        this.noontimeStartMinute = i;
    }

    public void setNoontimeStopHour(int i) {
        this.noontimeStopHour = i;
    }

    public void setNoontimeStopMinute(int i) {
        this.noontimeStopMinute = i;
    }

    public void setOn(boolean z) {
        int i = this.repeat & (-2);
        this.repeat = i;
        if (z) {
            this.repeat = i | 1;
        }
    }

    public void setRepeat(int i) {
        this.repeat = i;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public void setStopHour(int i) {
        this.stopHour = i;
    }

    public void setStopMinute(int i) {
        this.stopMinute = i;
    }

    public void setWeekRepeat(boolean[] zArr) {
        this.repeat &= 1;
        if (zArr != null) {
            for (int i = 0; i < Math.min(zArr.length, 7); i++) {
                if (zArr[i]) {
                    this.repeat |= 2 << i;
                }
            }
        }
    }
}
