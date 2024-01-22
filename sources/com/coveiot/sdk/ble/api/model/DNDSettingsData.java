package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class DNDSettingsData {
    public int endHour;
    public int endMin;
    public boolean isDNDEnabled;
    public boolean isLiftWristEnabled;
    public boolean isNotificationEnabled;
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

    public boolean isDNDEnabled() {
        return this.isDNDEnabled;
    }

    public boolean isLiftWristEnabled() {
        return this.isLiftWristEnabled;
    }

    public boolean isNotificationEnabled() {
        return this.isNotificationEnabled;
    }

    public void setDNDEnabled(boolean z) {
        this.isDNDEnabled = z;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public void setEndMin(int i) {
        this.endMin = i;
    }

    public void setLiftWristEnabled(boolean z) {
        this.isLiftWristEnabled = z;
    }

    public void setNotificationEnabled(boolean z) {
        this.isNotificationEnabled = z;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public void setStartMin(int i) {
        this.startMin = i;
    }

    public String toString() {
        return "DNDSettingsData{isDNDEnabled=" + this.isDNDEnabled + ", isNotificationEnabled=" + this.isNotificationEnabled + ", isLiftWristEnabled=" + this.isLiftWristEnabled + ", startHour=" + this.startHour + ", startMin=" + this.startMin + ", endHour=" + this.endHour + ", endMin=" + this.endMin + '}';
    }
}
