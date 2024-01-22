package com.coveiot.coveaccess.leaderboard.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public final class AddressReq implements Serializable {
    private String administrativeArea;
    private String city;
    private String country;
    private String latitude;
    private String locality;
    private String locationType;
    private String longitude;

    public String getAdministrativeArea() {
        return this.administrativeArea;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public String getLocality() {
        return this.locality;
    }

    public String getLocationType() {
        return this.locationType;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setAdministrativeArea(String str) {
        this.administrativeArea = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public void setLocality(String str) {
        this.locality = str;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType.getLocationType();
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }
}
