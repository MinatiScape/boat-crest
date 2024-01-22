package com.ido.ble.protocol.model;

import java.util.Arrays;
/* loaded from: classes11.dex */
public class PressureParam {
    public static final int STATE_ALL_DAY = 221;
    public static final int STATE_OFF = 85;
    public static final int STATE_ON = 170;
    public int endHour;
    public int endMinute;
    public int highThreshold;
    public int interval;
    public int notifyFlag;
    public int onOff;
    public int remindOnOff;
    private int repeat;
    public int startHour;
    public int startMinute;
    public int stress_threshold;
    private boolean[] weekRepeat = new boolean[7];

    private int toByte(boolean[] zArr) {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            if (zArr[i2]) {
                i |= 1 << (i2 + 1);
            }
        }
        return i;
    }

    public boolean[] getWeekRepeat() {
        return this.weekRepeat;
    }

    public void setWeekRepeat(boolean[] zArr) {
        this.weekRepeat = zArr;
        this.repeat = toByte(zArr);
    }

    public String toString() {
        return "PressureParam{onOff=" + this.onOff + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", remindOnOff=" + this.remindOnOff + ", interval=" + this.interval + ", highThreshold=" + this.highThreshold + ", notifyFlag=" + this.notifyFlag + ", stress_threshold=" + this.stress_threshold + ", repeat=" + this.repeat + ", weekRepeat=" + Arrays.toString(this.weekRepeat) + '}';
    }
}
