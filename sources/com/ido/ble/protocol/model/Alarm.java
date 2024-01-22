package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class Alarm implements Serializable {
    public static final int STATUS_DISPLAY = 85;
    public static final int STATUS_NOT_DISPLAY = 170;
    public static final int TYPE_BATH = 12;
    public static final int TYPE_BRUSH_TEETH = 9;
    public static final int TYPE_COURSE = 11;
    public static final int TYPE_CUSTOMIZE = 7;
    public static final int TYPE_DINNER = 8;
    public static final int TYPE_ENGAGEMENT = 4;
    public static final int TYPE_EXERCISE = 2;
    public static final int TYPE_GATHERING = 5;
    public static final int TYPE_GETUP = 0;
    public static final int TYPE_LEARN = 13;
    public static final int TYPE_MEDICINE = 3;
    public static final int TYPE_MEETING = 6;
    public static final int TYPE_PLAY = 14;
    public static final int TYPE_REST = 10;
    public static final int TYPE_SLEEP = 1;
    private static final long serialVersionUID = 1;
    private int alarmHour;
    private int alarmId;
    private int alarmMinute;
    private int alarmRepetitions;
    private int alarmSnoozeDuration;
    private int alarmType;
    private boolean on_off;
    private int alarmStatus = 85;
    private boolean[] weekRepeat = new boolean[7];

    private int toByte(boolean[] zArr, boolean z) {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            if (zArr[i2]) {
                i |= 1 << (i2 + 1);
            }
        }
        return z ? i | 1 : i;
    }

    public int getAlarmHour() {
        return this.alarmHour;
    }

    public int getAlarmId() {
        return this.alarmId;
    }

    public int getAlarmMinute() {
        return this.alarmMinute;
    }

    public int getAlarmSnoozeDuration() {
        return this.alarmSnoozeDuration;
    }

    public int getAlarmStatus() {
        return this.alarmStatus;
    }

    public int getAlarmType() {
        return this.alarmType;
    }

    public boolean getOn_off() {
        return this.on_off;
    }

    public boolean[] getWeekRepeat() {
        return this.weekRepeat;
    }

    public void setAlarmHour(int i) {
        this.alarmHour = i;
    }

    public void setAlarmId(int i) {
        this.alarmId = i;
    }

    public void setAlarmMinute(int i) {
        this.alarmMinute = i;
    }

    public void setAlarmSnoozeDuration(int i) {
        this.alarmSnoozeDuration = i;
    }

    public void setAlarmStatus(int i) {
        this.alarmStatus = i;
    }

    public void setAlarmType(int i) {
        this.alarmType = i;
    }

    public void setOn_off(boolean z) {
        this.on_off = z;
        this.alarmRepetitions = toByte(this.weekRepeat, z);
    }

    public void setWeekRepeat(boolean[] zArr) {
        this.weekRepeat = zArr;
        this.alarmRepetitions = toByte(zArr, this.on_off);
    }

    public String toString() {
        return "Alarm{alarmId=" + this.alarmId + ", alarmStatus=" + this.alarmStatus + ", alarmType=" + this.alarmType + ", alarmHour=" + this.alarmHour + ", alarmMinute=" + this.alarmMinute + ", on_off=" + this.on_off + ", alarmRepetitions=" + this.alarmRepetitions + ", weekRepeat=" + Arrays.toString(this.weekRepeat) + ", alarmSnoozeDuration=" + this.alarmSnoozeDuration + '}';
    }
}
