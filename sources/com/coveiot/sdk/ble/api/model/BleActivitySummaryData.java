package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class BleActivitySummaryData {
    private int activityId;
    private int activityNum;
    private int averagePace;
    private long averageSpeed;
    private int categoryId;
    private int duration;
    private int isFromHAR;
    private int maxHr;
    private int minHr;
    private long sessionId;
    private long timeStamp;
    private long totalCalories;
    private long totalDistance;
    private int totalSteps;

    public BleActivitySummaryData(long j, int i, long j2, int i2, int i3, int i4, int i5, int i6, long j3, long j4, long j5, int i7, int i8, int i9) {
        this.timeStamp = j;
        this.activityNum = i;
        this.sessionId = j2;
        this.categoryId = i2;
        this.activityId = i3;
        this.minHr = i4;
        this.maxHr = i5;
        this.averagePace = i6;
        this.averageSpeed = j3;
        this.totalCalories = j4;
        this.totalDistance = j5;
        this.totalSteps = i7;
        this.duration = i8;
        this.isFromHAR = i9;
    }

    public int getActivityId() {
        return this.activityId;
    }

    public int getActivityNum() {
        return this.activityNum;
    }

    public int getAveragePace() {
        return this.averagePace;
    }

    public long getAverageSpeed() {
        return this.averageSpeed;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getIsFromHAR() {
        return this.isFromHAR;
    }

    public int getMaxHr() {
        return this.maxHr;
    }

    public int getMinHr() {
        return this.minHr;
    }

    public long getSessionId() {
        return this.sessionId;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public long getTotalCalories() {
        return this.totalCalories;
    }

    public long getTotalDistance() {
        return this.totalDistance;
    }

    public int getTotalSteps() {
        return this.totalSteps;
    }
}
