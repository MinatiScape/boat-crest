package com.coveiot.coveaccess.timeline.model;
/* loaded from: classes8.dex */
public class TimeLineBadgeData extends TimeLineData {
    public String badgeDesc;
    public String badgeTitle;
    public String badgeType;
    public String badgeUrl;

    public TimeLineBadgeData(TimelineCardType timelineCardType, String str, String str2) {
        super(timelineCardType, str, str2);
    }

    public String getBadgeDesc() {
        return this.badgeDesc;
    }

    public String getBadgeTitle() {
        return this.badgeTitle;
    }

    public String getBadgeType() {
        return this.badgeType;
    }

    public String getBadgeUrl() {
        return this.badgeUrl;
    }

    public void setBadgeDesc(String str) {
        this.badgeDesc = str;
    }

    public void setBadgeTitle(String str) {
        this.badgeTitle = str;
    }

    public void setBadgeType(String str) {
        this.badgeType = str;
    }

    public void setBadgeUrl(String str) {
        this.badgeUrl = str;
    }

    @Override // com.coveiot.coveaccess.timeline.model.TimeLineData
    public String toString() {
        return "TimeLineBadgeData{badgeType='" + this.badgeType + "', badgeTitle='" + this.badgeTitle + "', badgeDesc='" + this.badgeDesc + "', badgeUrl='" + this.badgeUrl + "', timelineCardType=" + this.timelineCardType + ", timeStamp='" + this.timeStamp + "', date='" + this.date + "', logId='" + this.logId + "'}";
    }
}
