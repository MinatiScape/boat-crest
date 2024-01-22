package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class LiftWristViewData {
    public int endHour;
    public int endMin;
    public boolean isEnabled;
    public int startHour;
    public int startMin;

    public int getEndHour() {
        return this.endHour;
    }

    public int getEndMin() {
        return this.endMin;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getStartMin() {
        return this.startMin;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public void setEndMin(int i) {
        this.endMin = i;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public void setStartMin(int i) {
        this.startMin = i;
    }

    public String toString() {
        return "LiftWristViewData{isDNDEnabled=" + this.isEnabled + ", startHour=" + this.startHour + ", startMin=" + this.startMin + ", endHour=" + this.endHour + ", endMin=" + this.endMin + '}';
    }
}
