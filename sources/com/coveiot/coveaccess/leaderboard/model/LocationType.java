package com.coveiot.coveaccess.leaderboard.model;
/* loaded from: classes8.dex */
public enum LocationType {
    HOME("Home"),
    OFFICE("Office");
    
    private String locationType;

    LocationType(String str) {
        this.locationType = str;
    }

    public String getLocationType() {
        return this.locationType;
    }
}
