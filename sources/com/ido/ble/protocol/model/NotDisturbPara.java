package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class NotDisturbPara implements Serializable {
    public static final int STATE_OFF = 85;
    public static final int STATE_ON = 170;
    public static final int TIME_RANGE_OFF = 1;
    public static final int TIME_RANGE_ON = 2;
    private static final long serialVersionUID = 1;
    public int allDayOnOff;
    public int endHour;
    public int endMinute;
    public int haveTimeRange;
    public int intelligentOnOff;
    public int noontimeRestEndHour;
    public int noontimeRestEndMinute;
    public int noontimeRestOnOff;
    public int noontimeRestStartHour;
    public int noontimeRestStartMinute;
    public int onOFf;
    public int startHour;
    public int startMinute;
    private int weekRepeat;
    private boolean[] weeks = new boolean[7];

    private int toByte(boolean[] zArr) {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            if (zArr[i2]) {
                i |= 1 << (i2 + 1);
            }
        }
        return i;
    }

    public boolean[] getWeeks() {
        return this.weeks;
    }

    public void setWeeks(boolean[] zArr) {
        this.weeks = zArr;
        this.weekRepeat = toByte(zArr);
    }

    public String toString() {
        return "NotDisturbPara{onOFf=" + this.onOFf + ", allDayOnOff=" + this.allDayOnOff + ", intelligentOnOff=" + this.intelligentOnOff + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", haveTimeRange=" + this.haveTimeRange + ", weeks=" + Arrays.toString(this.weeks) + ", weekRepeat=" + this.weekRepeat + ", noontimeRestOnOff=" + this.noontimeRestOnOff + ", noontimeRestStartHour=" + this.noontimeRestStartHour + ", noontimeRestStartMinute=" + this.noontimeRestStartMinute + ", noontimeRestEndHour=" + this.noontimeRestEndHour + ", noontimeRestEndMinute=" + this.noontimeRestEndMinute + '}';
    }
}
