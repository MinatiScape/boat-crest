package com.coveiot.coveaccess.timeline.model;
/* loaded from: classes8.dex */
public class TimeLineCheerData extends TimeLineData {
    public String buddyMessage;
    public String cheerSenderName;
    public String cheertype;
    public String fitnessMessageId;

    public TimeLineCheerData(TimelineCardType timelineCardType, String str, String str2) {
        super(timelineCardType, str, str2);
    }

    public String getBuddyMessage() {
        return this.buddyMessage;
    }

    public String getCheerSenderName() {
        return this.cheerSenderName;
    }

    public String getCheertype() {
        return this.cheertype;
    }

    public String getFitnessMessageId() {
        return this.fitnessMessageId;
    }

    public void setBuddyMessage(String str) {
        this.buddyMessage = str;
    }

    public void setCheerSenderName(String str) {
        this.cheerSenderName = str;
    }

    public void setCheertype(String str) {
        this.cheertype = str;
    }

    public void setFitnessMessageId(String str) {
        this.fitnessMessageId = str;
    }

    @Override // com.coveiot.coveaccess.timeline.model.TimeLineData
    public String toString() {
        return "TimeLineCheerData{cheerSenderName='" + this.cheerSenderName + "', cheertype='" + this.cheertype + "', fitnessMessageId='" + this.fitnessMessageId + "', buddyMessage='" + this.buddyMessage + "', timelineCardType=" + this.timelineCardType + ", timeStamp='" + this.timeStamp + "', date='" + this.date + "', logId='" + this.logId + "'}";
    }
}
