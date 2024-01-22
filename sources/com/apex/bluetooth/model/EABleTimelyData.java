package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleTimelyData {
    public int bat_lev;
    public int blood_oxygen;
    public int calorie;
    public int distance;
    public int duration;
    public int hr;
    public int pressure;
    public int steps;

    public int getBat_lev() {
        return this.bat_lev;
    }

    public int getBlood_oxygen() {
        return this.blood_oxygen;
    }

    public int getCalorie() {
        return this.calorie;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getHr() {
        return this.hr;
    }

    public int getPressure() {
        return this.pressure;
    }

    public int getSteps() {
        return this.steps;
    }

    public void setBat_lev(int i) {
        this.bat_lev = i;
    }

    public void setBlood_oxygen(int i) {
        this.blood_oxygen = i;
    }

    public void setCalorie(int i) {
        this.calorie = i;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setHr(int i) {
        this.hr = i;
    }

    public void setPressure(int i) {
        this.pressure = i;
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public String toString() {
        return "EABleTimelyData{steps=" + this.steps + ", calorie=" + this.calorie + ", distance=" + this.distance + ", duration=" + this.duration + ", hr=" + this.hr + ", pressure=" + this.pressure + ", blood_oxygen=" + this.blood_oxygen + '}';
    }
}
