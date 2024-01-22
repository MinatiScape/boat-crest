package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class LongSit implements Serializable {
    private static final long serialVersionUID = 1;
    private int endHour;
    private int endMinute;
    private int interval;
    private boolean onOff;
    private int repetitions;
    private int startHour;
    private int startMinute;
    private boolean[] weeks = new boolean[7];

    private int toByte(boolean[] zArr, boolean z) {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            if (zArr[i2]) {
                i |= 1 << (i2 + 1);
            }
        }
        return z ? i | 1 : i;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public int getEndMinute() {
        return this.endMinute;
    }

    public int getInterval() {
        return this.interval;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public boolean[] getWeeks() {
        return this.weeks;
    }

    public boolean isOnOff() {
        return this.onOff;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public void setEndMinute(int i) {
        this.endMinute = i;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setOnOff(boolean z) {
        this.repetitions = toByte(this.weeks, z);
        this.onOff = z;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public void setWeeks(boolean[] zArr) {
        this.weeks = zArr;
        this.repetitions = toByte(zArr, this.onOff);
    }

    public String toString() {
        return "LongSit{startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", interval=" + this.interval + ", repetitions=" + this.repetitions + ", onOff=" + this.onOff + ", weeks=" + Arrays.toString(this.weeks) + '}';
    }
}
