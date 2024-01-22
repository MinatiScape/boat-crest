package com.coveiot.coveaccess.fitnessbuddies.model.buddydetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class BuddyRecentActivityDetails implements Serializable {
    @SerializedName("activityCode")
    @Expose
    public String activityCode;
    @SerializedName("activityData")
    @Expose
    public BuddyRecentActivityData activityData;
    @SerializedName("descInImperial")
    @Expose
    public String descInImperial;
    @SerializedName("descInMetric")
    @Expose
    public String descInMetric;
    @SerializedName("sessionType")
    @Expose
    public String sessionType;
    @SerializedName("totalActivityDuration")
    @Expose
    public Integer totalActivityDuration;

    public String getActivityCode() {
        return this.activityCode;
    }

    public BuddyRecentActivityData getActivityData() {
        return this.activityData;
    }

    public String getDescInImperial() {
        return this.descInImperial;
    }

    public String getDescInMetric() {
        return this.descInMetric;
    }

    public String getSessionType() {
        return this.sessionType;
    }

    public Integer getTotalActivityDuration() {
        return this.totalActivityDuration;
    }

    public void setActivityCode(String str) {
        this.activityCode = str;
    }

    public void setActivityData(BuddyRecentActivityData buddyRecentActivityData) {
        this.activityData = buddyRecentActivityData;
    }

    public void setDescInImperial(String str) {
        this.descInImperial = str;
    }

    public void setDescInMetric(String str) {
        this.descInMetric = str;
    }

    public void setSessionType(String str) {
        this.sessionType = str;
    }

    public void setTotalActivityDuration(Integer num) {
        this.totalActivityDuration = num;
    }
}
