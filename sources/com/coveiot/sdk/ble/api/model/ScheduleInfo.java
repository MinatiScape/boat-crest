package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class ScheduleInfo implements Serializable {
    public int advance;
    public String content;
    public int day;
    public int hour;
    public int minute;
    public int month;
    public int scheduleId;
    public String title;
    public int year;

    public int getAdvance() {
        return this.advance;
    }

    public String getContent() {
        return this.content;
    }

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getMonth() {
        return this.month;
    }

    public int getScheduleId() {
        return this.scheduleId;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public void setAdvance(int i) {
        this.advance = i;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setScheduleId(int i) {
        this.scheduleId = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "ScheduleInfo{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", advance=" + this.advance + ", title='" + this.title + "', content='" + this.content + "'}";
    }
}
