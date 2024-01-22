package com.coveiot.coveaccess.timeline.model;
/* loaded from: classes8.dex */
public class TimeLineStepsData extends TimeLineData {
    public int calories;
    public int distance;
    public int stepsValue;
    public int target;

    public TimeLineStepsData(TimelineCardType timelineCardType, String str, String str2) {
        super(timelineCardType, str, str2);
    }

    public int getCalories() {
        return this.calories;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getStepsValue() {
        return this.stepsValue;
    }

    public int getTarget() {
        return this.target;
    }

    public void setCalories(int i) {
        this.calories = i;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setStepsValue(int i) {
        this.stepsValue = i;
    }

    public void setTarget(int i) {
        this.target = i;
    }

    @Override // com.coveiot.coveaccess.timeline.model.TimeLineData
    public String toString() {
        return "TimeLineStepsData{stepsValue=" + this.stepsValue + ", target=" + this.target + ", calories=" + this.calories + ", distance=" + this.distance + ", timelineCardType=" + this.timelineCardType + ", timeStamp='" + this.timeStamp + "', date='" + this.date + "', logId='" + this.logId + "'}";
    }
}
