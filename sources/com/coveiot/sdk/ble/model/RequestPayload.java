package com.coveiot.sdk.ble.model;
/* loaded from: classes9.dex */
public class RequestPayload {
    public int day;
    public int endHour;
    public int endMin;
    public int runStrideLength;
    public int startHour;
    public int startMin;
    public int strideLength;
    public int timeInterval;

    public RequestPayload(int i, int i2, int i3, int i4, int i5) {
        this.startHour = i;
        this.startMin = i2;
        this.endHour = i3;
        this.endMin = i4;
        this.day = i5;
    }

    public int getDay() {
        return this.day;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public int getEndMin() {
        return this.endMin;
    }

    public int getRunStrideLength() {
        return this.runStrideLength;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getStartMin() {
        return this.startMin;
    }

    public int getStrideLength() {
        return this.strideLength;
    }

    public int getTimeInterval() {
        return this.timeInterval;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public void setEndMin(int i) {
        this.endMin = i;
    }

    public void setRunStrideLength(int i) {
        this.runStrideLength = i;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public void setStartMin(int i) {
        this.startMin = i;
    }

    public void setStrideLength(int i) {
        this.strideLength = i;
    }

    public void setTimeInterval(int i) {
        this.timeInterval = i;
    }
}
