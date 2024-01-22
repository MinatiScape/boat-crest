package com.mappls.sdk.category.model;

import androidx.annotation.Keep;
import com.mappls.sdk.services.api.autosuggest.model.AddressTokens;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public class PoiResult {
    private AddressTokens addressToken;
    private String brandCode;
    private Long distance;
    private List<String> keywords;
    private Double latitude;
    private Double longitude;
    private String mapplsPin;
    private String placeAddress;
    private String placeName;
    private String poiType;

    public AddressTokens getAddressToken() {
        return this.addressToken;
    }

    public String getBrandCode() {
        return this.brandCode;
    }

    public Long getDistance() {
        return this.distance;
    }

    public List<String> getKeywords() {
        return this.keywords;
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

    public String getPlaceAddress() {
        return this.placeAddress;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public String getPoiType() {
        return this.poiType;
    }

    public void setAddressToken(AddressTokens addressTokens) {
        this.addressToken = addressTokens;
    }

    public void setBrandCode(String str) {
        this.brandCode = str;
    }

    public void setDistance(Long l) {
        this.distance = l;
    }

    public void setKeywords(List<String> list) {
        this.keywords = list;
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

    public void setPlaceAddress(String str) {
        this.placeAddress = str;
    }

    public void setPlaceName(String str) {
        this.placeName = str;
    }

    public void setPoiType(String str) {
        this.poiType = str;
    }
}
