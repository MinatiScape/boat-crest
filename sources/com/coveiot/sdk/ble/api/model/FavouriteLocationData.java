package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class FavouriteLocationData implements Serializable {
    private int locationIndex;
    private String placeAddress;
    private int placeId;
    private String placeTagName;

    public FavouriteLocationData(int i, int i2, String str, String str2) {
        this.locationIndex = i;
        this.placeId = i2;
        this.placeTagName = str;
        this.placeAddress = str2;
    }

    public int getLocationIndex() {
        return this.locationIndex;
    }

    public String getPlaceAddress() {
        return this.placeAddress;
    }

    public int getPlaceId() {
        return this.placeId;
    }

    public String getPlaceTagName() {
        return this.placeTagName;
    }

    public void setLocationIndex(int i) {
        this.locationIndex = i;
    }

    public void setPlaceAddress(String str) {
        this.placeAddress = str;
    }

    public void setPlaceId(int i) {
        this.placeId = i;
    }

    public void setPlaceTagName(String str) {
        this.placeTagName = str;
    }

    public FavouriteLocationData() {
    }
}
