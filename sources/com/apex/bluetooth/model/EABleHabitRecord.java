package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.HabitIcon;
import com.apex.bluetooth.enumeration.HabitState;
/* loaded from: classes.dex */
public class EABleHabitRecord {
    public int begin_hour;
    public int begin_minute;
    public int blueColor;
    public int end_hour;
    public int end_minute;
    public int greenColor;
    public HabitIcon habitIcon;
    public HabitState habitState;
    public String mContent;
    public int redColor;
    public long time_stamp;

    public int getBegin_hour() {
        return this.begin_hour;
    }

    public int getBegin_minute() {
        return this.begin_minute;
    }

    public int getBlueColor() {
        return this.blueColor;
    }

    public int getEnd_hour() {
        return this.end_hour;
    }

    public int getEnd_minute() {
        return this.end_minute;
    }

    public int getGreenColor() {
        return this.greenColor;
    }

    public HabitIcon getHabitIcon() {
        return this.habitIcon;
    }

    public HabitState getHabitState() {
        return this.habitState;
    }

    public int getRedColor() {
        return this.redColor;
    }

    public long getTime_stamp() {
        return this.time_stamp;
    }

    public String getmContent() {
        return this.mContent;
    }

    public void setBegin_hour(int i) {
        this.begin_hour = i;
    }

    public void setBegin_minute(int i) {
        this.begin_minute = i;
    }

    public void setBlueColor(int i) {
        this.blueColor = i;
    }

    public void setEnd_hour(int i) {
        this.end_hour = i;
    }

    public void setEnd_minute(int i) {
        this.end_minute = i;
    }

    public void setGreenColor(int i) {
        this.greenColor = i;
    }

    public void setHabitIcon(HabitIcon habitIcon) {
        this.habitIcon = habitIcon;
    }

    public void setHabitState(HabitState habitState) {
        this.habitState = habitState;
    }

    public void setRedColor(int i) {
        this.redColor = i;
    }

    public void setTime_stamp(long j) {
        this.time_stamp = j;
    }

    public void setmContent(String str) {
        this.mContent = str;
    }

    public String toString() {
        return "EABleHabitRecord{habitIcon=" + this.habitIcon + ", habitState=" + this.habitState + ", time_stamp=" + this.time_stamp + ", begin_hour=" + this.begin_hour + ", begin_minute=" + this.begin_minute + ", end_hour=" + this.end_hour + ", end_minute=" + this.end_minute + ", redColor=" + this.redColor + ", greenColor=" + this.greenColor + ", blueColor=" + this.blueColor + ", mContent='" + this.mContent + "'}";
    }
}
