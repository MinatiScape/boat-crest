package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class BleAlarmInfo implements Serializable {
    public int alarmId;
    public String eventName;
    public boolean friday;
    public int hour;
    public boolean isAlarmOn;
    public int minute;
    public boolean monday;
    public boolean saturday;
    public boolean sunday;
    public boolean thursday;
    public boolean tuesday;
    public boolean wednesday;

    public BleAlarmInfo(boolean z, int i, String str, int i2, int i3, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        this.isAlarmOn = z;
        this.alarmId = i;
        this.eventName = str;
        this.hour = i2;
        this.minute = i3;
        this.sunday = z2;
        this.monday = z3;
        this.tuesday = z4;
        this.wednesday = z5;
        this.thursday = z6;
        this.friday = z7;
        this.saturday = z8;
    }

    public int getAlarmId() {
        return this.alarmId;
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

    public boolean isAlarmOn() {
        return this.isAlarmOn;
    }

    public boolean isFriday() {
        return this.friday;
    }

    public boolean isMonday() {
        return this.monday;
    }

    public boolean isSaturday() {
        return this.saturday;
    }

    public boolean isSunday() {
        return this.sunday;
    }

    public boolean isThursday() {
        return this.thursday;
    }

    public boolean isTuesday() {
        return this.tuesday;
    }

    public boolean isWednesday() {
        return this.wednesday;
    }

    public void setAlarmId(int i) {
        this.alarmId = i;
    }

    public void setAlarmOn(boolean z) {
        this.isAlarmOn = z;
    }

    public void setEventName(String str) {
        this.eventName = str;
    }

    public void setFriday(boolean z) {
        this.friday = z;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public void setMonday(boolean z) {
        this.monday = z;
    }

    public void setSaturday(boolean z) {
        this.saturday = z;
    }

    public void setSunday(boolean z) {
        this.sunday = z;
    }

    public void setThursday(boolean z) {
        this.thursday = z;
    }

    public void setTuesday(boolean z) {
        this.tuesday = z;
    }

    public void setWednesday(boolean z) {
        this.wednesday = z;
    }
}
