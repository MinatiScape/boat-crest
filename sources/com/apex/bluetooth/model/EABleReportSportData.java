package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleReportSportData {
    public int altitude;
    public int calorie;
    public int count;
    public int distance;
    public int hr;
    public int pace;
    public int steps;
    public int stride_frequency;
    public long timestamp;

    public int getAltitude() {
        return this.altitude;
    }

    public int getCalorie() {
        return this.calorie;
    }

    public int getCount() {
        return this.count;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getHr() {
        return this.hr;
    }

    public int getPace() {
        return this.pace;
    }

    public int getSteps() {
        return this.steps;
    }

    public int getStride_frequency() {
        return this.stride_frequency;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setAltitude(int i) {
        this.altitude = i;
    }

    public void setCalorie(int i) {
        this.calorie = i;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setHr(int i) {
        this.hr = i;
    }

    public void setPace(int i) {
        this.pace = i;
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public void setStride_frequency(int i) {
        this.stride_frequency = i;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public String toString() {
        return "EABleReportSportData{steps=" + this.steps + ", calorie=" + this.calorie + ", hr=" + this.hr + ", timestamp=" + this.timestamp + ", distance=" + this.distance + ", count=" + this.count + ", altitude=" + this.altitude + ", pace=" + this.pace + ", stride_frequency=" + this.stride_frequency + '}';
    }
}
