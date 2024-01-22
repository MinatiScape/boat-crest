package com.coveiot.sdk.ble.api.model;

import java.util.List;
/* loaded from: classes9.dex */
public class AppAutoActivityDetectionModel {
    private byte[] activities;
    private Integer countDownValue;
    private Boolean isFridayEnabled;
    private Boolean isMondayEnabled;
    private Boolean isSaturdayEnabled;
    private Boolean isSundayEnabled;
    private Boolean isThursdayEnabled;
    private Boolean isTuesdayEnabled;
    private Boolean isVibrationEnabled;
    private Boolean isWednesdayEnabled;
    private List<Period> periods;

    /* loaded from: classes9.dex */
    public static class Period {
        private Integer endTime;
        private Integer startTime;

        public Period(Integer num, Integer num2) {
            this.startTime = 0;
            this.endTime = 0;
            this.startTime = num;
            this.endTime = num2;
        }

        public Integer getEndTime() {
            return this.endTime;
        }

        public Integer getStartTime() {
            return this.startTime;
        }
    }

    public AppAutoActivityDetectionModel(byte[] bArr, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Integer num, Boolean bool8, List<Period> list) {
        this.activities = new byte[32];
        Boolean bool9 = Boolean.TRUE;
        this.isSundayEnabled = bool9;
        this.isMondayEnabled = bool9;
        this.isTuesdayEnabled = bool9;
        this.isWednesdayEnabled = bool9;
        this.isThursdayEnabled = bool9;
        this.isFridayEnabled = bool9;
        this.isSaturdayEnabled = bool9;
        this.countDownValue = 0;
        this.isVibrationEnabled = Boolean.FALSE;
        this.periods = null;
        this.activities = bArr;
        this.isSundayEnabled = bool;
        this.isMondayEnabled = bool2;
        this.isTuesdayEnabled = bool3;
        this.isWednesdayEnabled = bool4;
        this.isThursdayEnabled = bool5;
        this.isFridayEnabled = bool6;
        this.isSaturdayEnabled = bool7;
        this.countDownValue = num;
        this.isVibrationEnabled = bool8;
        this.periods = list;
    }

    public byte[] getActivities() {
        return this.activities;
    }

    public Integer getCountDownValue() {
        return this.countDownValue;
    }

    public Boolean getFridayEnabled() {
        return this.isFridayEnabled;
    }

    public Boolean getMondayEnabled() {
        return this.isMondayEnabled;
    }

    public List<Period> getPeriods() {
        return this.periods;
    }

    public Boolean getSaturdayEnabled() {
        return this.isSaturdayEnabled;
    }

    public Boolean getSundayEnabled() {
        return this.isSundayEnabled;
    }

    public Boolean getThursdayEnabled() {
        return this.isThursdayEnabled;
    }

    public Boolean getTuesdayEnabled() {
        return this.isTuesdayEnabled;
    }

    public Boolean getVibrationEnabled() {
        return this.isVibrationEnabled;
    }

    public Boolean getWednesdayEnabled() {
        return this.isWednesdayEnabled;
    }

    public void setActivities(byte[] bArr) {
        this.activities = bArr;
    }

    public void setCountDownValue(Integer num) {
        this.countDownValue = num;
    }

    public void setFridayEnabled(Boolean bool) {
        this.isFridayEnabled = bool;
    }

    public void setMondayEnabled(Boolean bool) {
        this.isMondayEnabled = bool;
    }

    public void setPeriods(List<Period> list) {
        this.periods = list;
    }

    public void setSaturdayEnabled(Boolean bool) {
        this.isSaturdayEnabled = bool;
    }

    public void setSundayEnabled(Boolean bool) {
        this.isSundayEnabled = bool;
    }

    public void setThursdayEnabled(Boolean bool) {
        this.isThursdayEnabled = bool;
    }

    public void setTuesdayEnabled(Boolean bool) {
        this.isTuesdayEnabled = bool;
    }

    public void setVibrationEnabled(Boolean bool) {
        this.isVibrationEnabled = bool;
    }

    public void setWednesdayEnabled(Boolean bool) {
        this.isWednesdayEnabled = bool;
    }
}
