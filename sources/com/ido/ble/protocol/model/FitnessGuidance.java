package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class FitnessGuidance implements Serializable {
    public static final int STATUS_OFF = 85;
    public static final int STATUS_ON = 170;
    public int end_hour;
    public int end_minute;
    public int go_mode;
    public int mode;
    public int notify_flag;
    private int repeat;
    public int start_hour;
    public int start_minute;
    public int target_steps;
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
    }

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
        byteToWeekRepeat();
        return this.weeks;
    }

    public boolean[] getWeeks() {
        return this.weeks;
    }

    public void setWeeks(boolean[] zArr) {
        this.weeks = zArr;
        this.repeat = toByte(zArr);
    }

    public String toString() {
        return "FitnessGuidance{mode=" + this.mode + ", go_mode=" + this.go_mode + ", start_hour=" + this.start_hour + ", start_minute=" + this.start_minute + ", end_hour=" + this.end_hour + ", end_minute=" + this.end_minute + ", notify_flag=" + this.notify_flag + ", repeat=" + this.repeat + ", target_steps=" + this.target_steps + ", weeks=" + Arrays.toString(this.weeks) + '}';
    }
}
