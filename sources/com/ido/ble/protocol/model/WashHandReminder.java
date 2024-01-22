package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class WashHandReminder implements Serializable {
    public static final int STATE_OFF = 0;
    public static final int STATE_ON = 1;
    private static final long serialVersionUID = 1;
    public int endHour;
    public int endMinute;
    public int interval;
    public int repeat;
    public int startHour;
    public int startMinute;
    public int onOff = 0;
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

    public boolean[] getWeekRepeat() {
        byteToWeekRepeat();
        return this.weeks;
    }

    public boolean[] getWeeks() {
        return this.weeks;
    }

    public void setWeeks(boolean[] zArr) {
        this.weeks = zArr;
        this.repeat = toByte(zArr, this.onOff == 1);
    }

    public String toString() {
        return "WashHandReminder{onOff=" + this.onOff + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", repeat=" + this.repeat + ", interval=" + this.interval + ", weeks=" + Arrays.toString(this.weeks) + '}';
    }
}
