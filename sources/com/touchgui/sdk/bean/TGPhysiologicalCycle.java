package com.touchgui.sdk.bean;

import java.util.Date;
/* loaded from: classes12.dex */
public class TGPhysiologicalCycle {
    private boolean enable;
    private Date lastDate;
    private int menstrualCycleDays;
    private int menstrualDuration;
    private int remindHour;
    private int remindMenstrual;
    private int remindMinute;
    private int remindOvulation;

    public Date getLastDate() {
        return this.lastDate;
    }

    public int getMenstrualCycleDays() {
        return this.menstrualCycleDays;
    }

    public int getMenstrualDuration() {
        return this.menstrualDuration;
    }

    public int getRemindHour() {
        return this.remindHour;
    }

    public int getRemindMenstrual() {
        return this.remindMenstrual;
    }

    public int getRemindMinute() {
        return this.remindMinute;
    }

    public int getRemindOvulation() {
        return this.remindOvulation;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public void setLastDate(Date date) {
        this.lastDate = date;
    }

    public void setMenstrualCycleDays(int i) {
        this.menstrualCycleDays = i;
    }

    public void setMenstrualDuration(int i) {
        this.menstrualDuration = i;
    }

    public void setRemindHour(int i) {
        this.remindHour = i;
    }

    public void setRemindMenstrual(int i) {
        this.remindMenstrual = i;
    }

    public void setRemindMinute(int i) {
        this.remindMinute = i;
    }

    public void setRemindOvulation(int i) {
        this.remindOvulation = i;
    }
}
