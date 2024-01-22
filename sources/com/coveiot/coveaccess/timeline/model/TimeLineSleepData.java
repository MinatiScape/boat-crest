package com.coveiot.coveaccess.timeline.model;
/* loaded from: classes8.dex */
public class TimeLineSleepData extends TimeLineData {
    public int awake;
    public int deepSleep;
    public int lightSleep;
    public int sleepValue;
    public int target;

    public TimeLineSleepData(TimelineCardType timelineCardType, String str, String str2) {
        super(timelineCardType, str, str2);
    }

    public int getAwake() {
        return this.awake;
    }

    public int getDeepSleep() {
        return this.deepSleep;
    }

    public int getLightSleep() {
        return this.lightSleep;
    }

    public int getSleepValue() {
        return this.sleepValue;
    }

    public int getTarget() {
        return this.target;
    }

    public void setAwake(int i) {
        this.awake = i;
    }

    public void setDeepSleep(int i) {
        this.deepSleep = i;
    }

    public void setLightSleep(int i) {
        this.lightSleep = i;
    }

    public void setSleepValue(int i) {
        this.sleepValue = i;
    }

    public void setTarget(int i) {
        this.target = i;
    }

    @Override // com.coveiot.coveaccess.timeline.model.TimeLineData
    public String toString() {
        return "TimeLineSleepData{sleepValue=" + this.sleepValue + ", deepSleep=" + this.deepSleep + ", lightSleep=" + this.lightSleep + ", awake=" + this.awake + ", target=" + this.target + ", timelineCardType=" + this.timelineCardType + ", timeStamp='" + this.timeStamp + "', date='" + this.date + "', logId='" + this.logId + "'}";
    }
}
