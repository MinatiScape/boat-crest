package com.coveiot.coveaccess.leaderboard.model;

import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
/* loaded from: classes8.dex */
public enum FilterType {
    LOCALITY("locality"),
    CITY(GeoCodingCriteria.POD_CITY),
    STATE("state"),
    COUNTRY("country");
    
    private String filterType;

    FilterType(String str) {
        this.filterType = str;
    }

    public String getFilterType() {
        return this.filterType;
    }
}
