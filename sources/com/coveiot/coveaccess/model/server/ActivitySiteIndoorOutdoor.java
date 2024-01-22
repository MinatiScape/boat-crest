package com.coveiot.coveaccess.model.server;
/* loaded from: classes8.dex */
public enum ActivitySiteIndoorOutdoor {
    INDOOR("INDOOR"),
    OUTDOOR("OUTDOOR");
    
    private String activitySite;

    ActivitySiteIndoorOutdoor(String str) {
        this.activitySite = str;
    }

    public String getActivitySite() {
        return this.activitySite;
    }
}
