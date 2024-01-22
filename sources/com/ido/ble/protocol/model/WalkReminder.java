package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class WalkReminder implements Serializable {
    public static final int OFF = 0;
    public static final int ON = 1;
    private static final long serialVersionUID = 1;
    private int endHour;
    private int endMinute;
    private int goalStep;
    private int goalTime;
    private int notifyFlag;
    private int repeat;
    private int startHour;
    private int startMinute;
    private int onOff = 0;
    private boolean[] weeks = new boolean[7];

    private void byteToWeekRepeat() {
        this.weeks = new boolean[7];
        int i = 0;
        while (i < 7) {
            int i2 = i + 1;
            if ((this.repeat & (1 << i2)) != 0) {
                this.weeks[i] = true;
            } else {
                this.weeks[i] = false;
            }
            i = i2;
        }
        if ((this.repeat & 1) == 1) {
            this.onOff = 1;
        } else {
            this.onOff = 0;
        }
    }

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

    public int getGoalStep() {
        return this.goalStep;
    }

    public int getGoalTime() {
        return this.goalTime;
    }

    public int getNotifyFlag() {
        return this.notifyFlag;
    }

    public int getOnOff() {
        return this.onOff;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public boolean[] getWeekRepeat() {
        byteToWeekRepeat();
        return this.weeks;
    }

    public boolean[] getWeeks() {
        return this.weeks;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public void setEndMinute(int i) {
        this.endMinute = i;
    }

    public void setGoalStep(int i) {
        this.goalStep = i;
    }

    public void setGoalTime(int i) {
        this.goalTime = i;
    }

    public void setNotifyFlag(int i) {
        this.notifyFlag = i;
    }

    public void setOnOff(int i) {
        this.onOff = i;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public void setWeeks(boolean[] zArr) {
        this.weeks = zArr;
        this.repeat = toByte(zArr, this.onOff == 1);
    }

    public String toString() {
        return "WalkReminder{onOff=" + this.onOff + ", goalStep=" + this.goalStep + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", goalTime=" + this.goalTime + ", notifyFlag=" + this.notifyFlag + ", repeat=" + this.repeat + ", weeks=" + Arrays.toString(this.weeks) + '}';
    }
}
