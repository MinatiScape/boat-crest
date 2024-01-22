package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class EnabledTypeSports {
    @SerializedName("activitySite")
    private String activitySite;
    @SerializedName("sessionType")
    private String sessionType;

    public String getActivitySite() {
        return this.activitySite;
    }

    public String getSessionType() {
        return this.sessionType;
    }

    public void setActivitySite(String str) {
        this.activitySite = str;
    }

    public void setSessionType(String str) {
        this.sessionType = str;
    }
}
