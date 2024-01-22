package com.mappls.sdk.maps.covid;

import androidx.annotation.Keep;
@Keep
/* loaded from: classes11.dex */
class ZoneInfo {
    private String containmentZoneName;
    private int distanceToNearestZone;
    private String districtName;
    private boolean insideContainmentZone;
    private String mapLink;
    private String zoneType;

    public String getContainmentZoneName() {
        return this.containmentZoneName;
    }

    public int getDistanceToNearestZone() {
        return this.distanceToNearestZone;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public String getMapLink() {
        return this.mapLink;
    }

    public String getZoneType() {
        return this.zoneType;
    }

    public boolean isInsideContainmentZone() {
        return this.insideContainmentZone;
    }

    public void setContainmentZoneName(String str) {
        this.containmentZoneName = str;
    }

    public void setDistanceToNearestZone(int i) {
        this.distanceToNearestZone = i;
    }

    public void setDistrictName(String str) {
        this.districtName = str;
    }

    public void setInsideContainmentZone(boolean z) {
        this.insideContainmentZone = z;
    }

    public void setMapLink(String str) {
        this.mapLink = str;
    }

    public void setZoneType(String str) {
        this.zoneType = str;
    }
}
