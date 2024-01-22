package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes11.dex */
public class ScheduleReminderV3 implements Serializable {
    private static final int REMIND_STATE_DELETE = 1;
    private static final int REMIND_STATE_ENABLE = 2;
    private static final int REMIND_STATE_INVALID = 0;
    private static final int REMIND_SWITCH_OFF = 0;
    private static final int REMIND_SWITCH_ON = 1;
    private static final long serialVersionUID = 1;
    private int day;
    private int hour;
    private int id;
    private int min;
    private int mon;
    private String note;
    private int remind_on_off;
    private int repeat_type;
    private int sec;
    private int state;
    private String title;
    private boolean[] weekRepeat = new boolean[7];
    private int year;

    private void byteToWeekRepeat() {
        this.weekRepeat = new boolean[7];
        int i = 0;
        while (i < 7) {
            int i2 = i + 1;
            if ((this.repeat_type & (1 << i2)) != 0) {
                this.weekRepeat[i] = true;
            } else {
                this.weekRepeat[i] = false;
            }
            i = i2;
        }
    }

    private int weekRepeatToByte(boolean[] zArr) {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            if (zArr[i2]) {
                i |= 1 << (i2 + 1);
            }
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ScheduleReminderV3) && this.id == ((ScheduleReminderV3) obj).id;
    }

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }

    public int getId() {
        return this.id;
    }

    public int getMin() {
        return this.min;
    }

    public int getMon() {
        return this.mon;
    }

    public String getNote() {
        return this.note;
    }

    public int getRemind_on_off() {
        return this.remind_on_off;
    }

    public int getRepeat_type() {
        return this.repeat_type;
    }

    public int getSec() {
        return this.sec;
    }

    public int getState() {
        return this.state;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean[] getWeekRepeat() {
        byteToWeekRepeat();
        return this.weekRepeat;
    }

    public int getYear() {
        return this.year;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.id));
    }

    public void setDay(int i) {
        this.day = i;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setMin(int i) {
        this.min = i;
    }

    public void setMon(int i) {
        this.mon = i;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public void setRemind_on_off(int i) {
        this.remind_on_off = i;
    }

    public void setRepeat_type(int i) {
        this.repeat_type = i;
    }

    public void setSec(int i) {
        this.sec = i;
    }

    public void setState(int i) {
        this.state = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setWeekRepeat(boolean[] zArr) {
        this.weekRepeat = zArr;
        this.repeat_type = weekRepeatToByte(zArr);
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "ScheduleReminderV3{id=" + this.id + ", year=" + this.year + ", mon=" + this.mon + ", day=" + this.day + ", hour=" + this.hour + ", min=" + this.min + ", sec=" + this.sec + ", repeat_type=" + this.repeat_type + ", weekRepeat=" + Arrays.toString(this.weekRepeat) + ", remind_on_off=" + this.remind_on_off + ", state=" + this.state + ", title='" + this.title + "', note='" + this.note + "'}";
    }
}
