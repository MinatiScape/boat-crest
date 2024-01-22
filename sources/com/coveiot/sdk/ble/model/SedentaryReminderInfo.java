package com.coveiot.sdk.ble.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class SedentaryReminderInfo implements Serializable {
    public int endHour1;
    public int endHour2;
    public int endMin1;
    public int endMin2;
    public boolean isOn;
    public int reminder;
    public int startHour1;
    public int startHour2;
    public int startMin1;
    public int startMin2;

    public int getEndHour1() {
        return this.endHour1;
    }

    public int getEndHour2() {
        return this.endHour2;
    }

    public int getEndMin1() {
        return this.endMin1;
    }

    public int getEndMin2() {
        return this.endMin2;
    }

    public int getReminder() {
        return this.reminder;
    }

    public int getStartHour1() {
        return this.startHour1;
    }

    public int getStartHour2() {
        return this.startHour2;
    }

    public int getStartMin1() {
        return this.startMin1;
    }

    public int getStartMin2() {
        return this.startMin2;
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void setEndHour1(int i) {
        this.endHour1 = i;
    }

    public void setEndHour2(int i) {
        this.endHour2 = i;
    }

    public void setEndMin1(int i) {
        this.endMin1 = i;
    }

    public void setEndMin2(int i) {
        this.endMin2 = i;
    }

    public void setOn(boolean z) {
        this.isOn = z;
    }

    public void setReminder(int i) {
        this.reminder = i;
    }

    public void setStartHour1(int i) {
        this.startHour1 = i;
    }

    public void setStartHour2(int i) {
        this.startHour2 = i;
    }

    public void setStartMin1(int i) {
        this.startMin1 = i;
    }

    public void setStartMin2(int i) {
        this.startMin2 = i;
    }
}
