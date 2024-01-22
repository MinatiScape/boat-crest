package com.coveiot.coveaccess.model.server;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SPostHeartRateAndBP {
    @SerializedName("currentBpDiastolic")
    private int currentBpDiastolic;
    @SerializedName("currentBpSystolic")
    private int currentBpSystolic;
    @SerializedName("currentHr")
    private int currentHr;
    @SerializedName("fatigueAlarmDate")
    private String fatigueAlarmDate;
    @SerializedName("fatigueLevel")
    private double fatigueLevel;
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    private LocationBean location;

    /* loaded from: classes8.dex */
    public static class LocationBean {
        @SerializedName("latitude")
        private double latitude;
        @SerializedName("longitude")
        private double longitude;

        public double getLatitude() {
            return this.latitude;
        }

        public double getLongitude() {
            return this.longitude;
        }

        public void setLatitude(double d) {
            this.latitude = d;
        }

        public void setLongitude(double d) {
            this.longitude = d;
        }
    }

    public int getCurrentBpDiastolic() {
        return this.currentBpDiastolic;
    }

    public int getCurrentBpSystolic() {
        return this.currentBpSystolic;
    }

    public int getCurrentHr() {
        return this.currentHr;
    }

    public String getFatigueAlarmDate() {
        return this.fatigueAlarmDate;
    }

    public double getFatigueLevel() {
        return this.fatigueLevel;
    }

    public LocationBean getLocation() {
        return this.location;
    }

    public void setCurrentBpDiastolic(int i) {
        this.currentBpDiastolic = i;
    }

    public void setCurrentBpSystolic(int i) {
        this.currentBpSystolic = i;
    }

    public void setCurrentHr(int i) {
        this.currentHr = i;
    }

    public void setFatigueAlarmDate(String str) {
        this.fatigueAlarmDate = str;
    }

    public void setFatigueLevel(double d) {
        this.fatigueLevel = d;
    }

    public void setLocation(LocationBean locationBean) {
        this.location = locationBean;
    }
}
