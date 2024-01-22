package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleMonitorReminder {
    public int begin_hour;
    public int begin_minute;
    public int cup;
    public EABleMonitorType eaBleMonitorType;
    public int end_hour;
    public int end_minute;
    public int interval;
    public int reminderSwitch;
    public int step_threshold;
    public int week_cycle_bit;

    /* loaded from: classes.dex */
    public enum EABleMonitorType {
        drink(0),
        washHands(1),
        takeMedicine(2);
        
        public int value;

        EABleMonitorType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getBegin_hour() {
        return this.begin_hour;
    }

    public int getBegin_minute() {
        return this.begin_minute;
    }

    public int getCup() {
        return this.cup;
    }

    public EABleMonitorType getEaBleMonitorType() {
        return this.eaBleMonitorType;
    }

    public int getEnd_hour() {
        return this.end_hour;
    }

    public int getEnd_minute() {
        return this.end_minute;
    }

    public int getInterval() {
        return this.interval;
    }

    public int getReminderSwitch() {
        return this.reminderSwitch;
    }

    public int getStep_threshold() {
        return this.step_threshold;
    }

    public int getWeek_cycle_bit() {
        return this.week_cycle_bit;
    }

    public void setBegin_hour(int i) {
        this.begin_hour = i;
    }

    public void setBegin_minute(int i) {
        this.begin_minute = i;
    }

    public void setCup(int i) {
        this.cup = i;
    }

    public void setEaBleMonitorType(EABleMonitorType eABleMonitorType) {
        this.eaBleMonitorType = eABleMonitorType;
    }

    public void setEnd_hour(int i) {
        this.end_hour = i;
    }

    public void setEnd_minute(int i) {
        this.end_minute = i;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setReminderSwitch(int i) {
        this.reminderSwitch = i;
    }

    public void setStep_threshold(int i) {
        this.step_threshold = i;
    }

    public void setWeek_cycle_bit(int i) {
        this.week_cycle_bit = i;
    }
}
