package com.jstyle.blesdk1860.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class Clock extends SendData implements Serializable {
    public String content;
    public boolean enable;
    public int hour;
    public int minute;
    public int number;
    public int type;
    public byte week;

    public String getContent() {
        return this.content;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getNumber() {
        return this.number;
    }

    public int getType() {
        return this.type;
    }

    public int getWeek() {
        return this.week;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public void setNumber(int i) {
        this.number = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setWeek(byte b) {
        this.week = b;
    }

    public String toString() {
        return "Clock{number=" + this.number + ", type=" + this.type + ", hour=" + this.hour + ", minute=" + this.minute + ", week=" + ((int) this.week) + ", content='" + this.content + "', enable=" + this.enable + '}';
    }
}
