package com.coveiot.sdk.ble.api.model;

import java.util.List;
/* loaded from: classes9.dex */
public class BleGetSensAISummaryDetailData {
    private int activityType;
    private List<Integer> armSpeedList;
    private List<Integer> caloriesList;
    private int detailsDataNum;
    private List<Integer> distanceList;
    private List<Integer> handSpeedList;
    private List<Integer> hitOrMissList;
    private List<Integer> hrList;
    private long sessionId;
    private List<Integer> stepsList;
    private long timeStamp;

    public BleGetSensAISummaryDetailData(long j, int i, int i2, long j2, List<Integer> list, List<Integer> list2, List<Integer> list3, List<Integer> list4, List<Integer> list5, List<Integer> list6, List<Integer> list7) {
        this.sessionId = j;
        this.activityType = i;
        this.detailsDataNum = i2;
        this.timeStamp = j2;
        this.handSpeedList = list;
        this.hrList = list2;
        this.stepsList = list3;
        this.distanceList = list4;
        this.caloriesList = list5;
        this.hitOrMissList = list6;
        this.armSpeedList = list7;
    }

    public int getActivityType() {
        return this.activityType;
    }

    public List<Integer> getArmSpeedList() {
        return this.armSpeedList;
    }

    public List<Integer> getCaloriesList() {
        return this.caloriesList;
    }

    public int getDetailsDataNum() {
        return this.detailsDataNum;
    }

    public List<Integer> getDistanceList() {
        return this.distanceList;
    }

    public List<Integer> getHandSpeedList() {
        return this.handSpeedList;
    }

    public List<Integer> getHitOrMissList() {
        return this.hitOrMissList;
    }

    public List<Integer> getHrList() {
        return this.hrList;
    }

    public long getSessionId() {
        return this.sessionId;
    }

    public List<Integer> getStepsList() {
        return this.stepsList;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setActivityType(int i) {
        this.activityType = i;
    }

    public void setArmSpeedList(List<Integer> list) {
        this.armSpeedList = list;
    }

    public void setCaloriesList(List<Integer> list) {
        this.caloriesList = list;
    }

    public void setDetailsDataNum(int i) {
        this.detailsDataNum = i;
    }

    public void setDistanceList(List<Integer> list) {
        this.distanceList = list;
    }

    public void setHandSpeedList(List<Integer> list) {
        this.handSpeedList = list;
    }

    public void setHitOrMissList(List<Integer> list) {
        this.hitOrMissList = list;
    }

    public void setHrList(List<Integer> list) {
        this.hrList = list;
    }

    public void setSessionId(long j) {
        this.sessionId = j;
    }

    public void setStepsList(List<Integer> list) {
        this.stepsList = list;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public String toString() {
        return "BleGetSensAISummaryDetailData{sessionId=" + this.sessionId + ", activityType=" + this.activityType + ", detailsDataNum=" + this.detailsDataNum + ", timeStamp=" + this.timeStamp + ", handSpeedList=" + this.handSpeedList + ", hrList=" + this.hrList + ", stepsList=" + this.stepsList + ", distanceList=" + this.distanceList + ", caloriesList=" + this.caloriesList + ", hitOrMissList=" + this.hitOrMissList + ", armSpeedList=" + this.armSpeedList + '}';
    }
}
