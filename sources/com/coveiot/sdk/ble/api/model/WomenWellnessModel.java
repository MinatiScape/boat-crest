package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class WomenWellnessModel {
    private boolean enabled;
    private long lastMenstrualTimestamp;
    private short menstrualCycleLength;
    private short menstrualPeriodLength;
    private short menstruationNotificationDay;
    private int menstruationNotificationMinute;
    private short ovulationNotificationDay;
    private int ovulationNotificationMinute;
    private short reminderType;
    private boolean vibrationEnabled;

    public long getLastMenstrualTimestamp() {
        return this.lastMenstrualTimestamp;
    }

    public short getMenstrualCycleLength() {
        return this.menstrualCycleLength;
    }

    public short getMenstrualPeriodLength() {
        return this.menstrualPeriodLength;
    }

    public short getMenstruationNotificationDay() {
        return this.menstruationNotificationDay;
    }

    public int getMenstruationNotificationMinute() {
        return this.menstruationNotificationMinute;
    }

    public short getOvulationNotificationDay() {
        return this.ovulationNotificationDay;
    }

    public int getOvulationNotificationMinute() {
        return this.ovulationNotificationMinute;
    }

    public short getReminderType() {
        return this.reminderType;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isVibrationEnabled() {
        return this.vibrationEnabled;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public void setLastMenstrualTimestamp(long j) {
        this.lastMenstrualTimestamp = j;
    }

    public void setMenstrualCycleLength(short s) {
        this.menstrualCycleLength = s;
    }

    public void setMenstrualPeriodLength(short s) {
        this.menstrualPeriodLength = s;
    }

    public void setMenstruationNotificationDay(short s) {
        this.menstruationNotificationDay = s;
    }

    public void setMenstruationNotificationMinute(int i) {
        this.menstruationNotificationMinute = i;
    }

    public void setOvulationNotificationDay(short s) {
        this.ovulationNotificationDay = s;
    }

    public void setOvulationNotificationMinute(int i) {
        this.ovulationNotificationMinute = i;
    }

    public void setReminderType(short s) {
        this.reminderType = s;
    }

    public void setVibrationEnabled(boolean z) {
        this.vibrationEnabled = z;
    }
}
