package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes9.dex */
public class AlarmInfo implements Serializable {
    public int alarmId;
    public int alarmType = 1;
    public Days daysSelected;
    public String eventName;
    public int hour;
    public boolean isAlarmOn;
    public int minute;
    public int snoozeDuration;
    public int snoozeRepeatCount;

    /* loaded from: classes9.dex */
    public static class Days {
        private boolean[] isDaySelected;

        public Days(boolean... zArr) {
            this.isDaySelected = new boolean[7];
            this.isDaySelected = zArr;
        }

        public boolean[] getIsDaySelected() {
            return this.isDaySelected;
        }

        public String toString() {
            return "Days{isDaySelected=" + Arrays.toString(this.isDaySelected) + '}';
        }
    }

    public int getAlarmId() {
        return this.alarmId;
    }

    public int getAlarmType() {
        return this.alarmType;
    }

    public Days getDaysSelected() {
        return this.daysSelected;
    }

    public String getEventName() {
        return this.eventName;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getSnoozeDuration() {
        return this.snoozeDuration;
    }

    public int getSnoozeRepeatCount() {
        return this.snoozeRepeatCount;
    }

    public boolean isAlarmOn() {
        return this.isAlarmOn;
    }

    public void setAlarmId(int i) {
        this.alarmId = i;
    }

    public void setAlarmOn(boolean z) {
        this.isAlarmOn = z;
    }

    public void setAlarmType(int i) {
        this.alarmType = i;
    }

    public void setDaysSelected(Days days) {
        this.daysSelected = days;
    }

    public void setEventName(String str) {
        this.eventName = str;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public void setSnoozeDuration(int i) {
        this.snoozeDuration = i;
    }

    public void setSnoozeRepeatCount(int i) {
        this.snoozeRepeatCount = i;
    }

    public String toString() {
        return "AlarmInfo{isAlarmOn=" + this.isAlarmOn + ", getAlarmId=" + this.alarmId + ", eventName='" + this.eventName + "', hour=" + this.hour + ", minute=" + this.minute + ", daysSelected=" + this.daysSelected + '}';
    }
}
