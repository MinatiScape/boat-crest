package com.coveiot.coveaccess.timeline.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class TimeLineData implements Serializable {
    public String date;
    public String logId;
    public String timeStamp;
    public TimelineCardType timelineCardType;

    public TimeLineData(TimelineCardType timelineCardType, String str, String str2) {
        this.timelineCardType = timelineCardType;
        this.timeStamp = str;
        this.date = str2;
    }

    public String getDate() {
        return this.date;
    }

    public String getLogId() {
        return this.logId;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public TimelineCardType getTimelineCardType() {
        return this.timelineCardType;
    }

    public void setLogId(String str) {
        this.logId = str;
    }

    public String toString() {
        return "TimeLineData{timelineCardType=" + this.timelineCardType.toString() + ", timeStamp='" + this.timeStamp + "', date='" + this.date + "', logId='" + this.logId + "'}";
    }
}
