package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.EABleSportStatus;
/* loaded from: classes.dex */
public class EABleStartAppSports {
    public AppSportType appSportType;
    public int checkSport;
    public int reportInterval;
    public EABleSportStatus sportStatus;

    /* loaded from: classes.dex */
    public enum AppSportType {
        ourdoor_default(0),
        ourdoor_walking(1),
        ourdoor_running(2),
        ourdoor_on_foot(3),
        ourdoor_on_mountaineering(4),
        ourdoor_trail_running(5),
        ourdoor_cycling(6);
        
        public int value;

        AppSportType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public AppSportType getAppSportType() {
        return this.appSportType;
    }

    public int getCheckSport() {
        return this.checkSport;
    }

    public int getReportInterval() {
        return this.reportInterval;
    }

    public EABleSportStatus getSportStatus() {
        return this.sportStatus;
    }

    public void setAppSportType(AppSportType appSportType) {
        this.appSportType = appSportType;
    }

    public void setCheckSport(int i) {
        this.checkSport = i;
    }

    public void setReportInterval(int i) {
        this.reportInterval = i;
    }

    public void setSportStatus(EABleSportStatus eABleSportStatus) {
        this.sportStatus = eABleSportStatus;
    }
}
