package com.mappls.sdk.plugins.places.autocomplete.model;

import androidx.annotation.Keep;
import java.util.Map;
@Keep
/* loaded from: classes10.dex */
public class MapplsFavoritePlace {
    private Double latitude;
    private Double longitude;
    private String mapplsPin;
    private Map otherInfo;
    private final String placeAddress;
    private final String placeName;

    public MapplsFavoritePlace(String str, String str2, Double d, Double d2) {
        if (str == null) {
            throw new IllegalArgumentException("Place Name should not be Null");
        }
        this.placeName = str;
        this.placeAddress = str2;
        this.latitude = d;
        this.longitude = d2;
    }

    public MapplsFavoritePlace(String str, String str2, String str3) {
        if (str == null) {
            throw new IllegalArgumentException("Place Name should not be Null");
        }
        this.placeName = str;
        this.placeAddress = str2;
        this.mapplsPin = str3;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    public Map getOtherInfo() {
        return this.otherInfo;
    }

    public String getPlaceAddress() {
        return this.placeAddress;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public void setMapplsPin(String str) {
        this.mapplsPin = str;
    }

    public void setOtherInfo(Map map) {
        this.otherInfo = map;
    }
}
