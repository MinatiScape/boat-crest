package com.mappls.sdk.services.api.nearby.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.autosuggest.model.AddressTokens;
import java.util.List;
import java.util.Map;
@Keep
/* loaded from: classes6.dex */
public class NearbyAtlasResult {
    @SerializedName("addressTokens")
    @Expose
    public AddressTokens addressTokens;
    @SerializedName("distance")
    @Expose
    public Long distance;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("entryLatitude")
    @Expose
    public Double entryLatitude;
    @SerializedName("entryLongitude")
    @Expose
    public Double entryLongitude;
    @SerializedName("hourOfOperation")
    @Expose
    public String hourOfOperation;
    @SerializedName("keywords")
    @Expose
    public List<String> keywords = null;
    @SerializedName("landlineNo")
    @Expose
    public String landlineNo;
    @SerializedName("latitude")
    @Expose
    public Double latitude;
    @SerializedName("longitude")
    @Expose
    public Double longitude;
    @SerializedName(alternate = {"eLoc"}, value = "mapplsPin")
    @Expose
    public String mapplsPin;
    @SerializedName("mobileNo")
    @Expose
    public String mobileNo;
    @SerializedName("orderIndex")
    @Expose
    public long orderIndex;
    @SerializedName("placeAddress")
    @Expose
    public String placeAddress;
    @SerializedName("placeName")
    @Expose
    public String placeName;
    @SerializedName("richInfo")
    @Expose
    private Map richInfo;
    @SerializedName("type")
    @Expose
    public String type;

    public AddressTokens getAddressTokens() {
        return this.addressTokens;
    }

    public Long getDistance() {
        return this.distance;
    }

    public String getEmail() {
        return this.email;
    }

    public Double getEntryLatitude() {
        return this.entryLatitude;
    }

    public Double getEntryLongitude() {
        return this.entryLongitude;
    }

    public String getHourOfOperation() {
        return this.hourOfOperation;
    }

    public List<String> getKeywords() {
        return this.keywords;
    }

    public String getLandlineNo() {
        return this.landlineNo;
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

    public String getMobileNo() {
        return this.mobileNo;
    }

    public long getOrderIndex() {
        return this.orderIndex;
    }

    public String getPlaceAddress() {
        return this.placeAddress;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public Map getRichInfo() {
        return this.richInfo;
    }

    public String getType() {
        return this.type;
    }

    public void setAddressTokens(AddressTokens addressTokens) {
        this.addressTokens = addressTokens;
    }

    public void setDistance(Long l) {
        this.distance = l;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setEntryLatitude(Double d) {
        this.entryLatitude = d;
    }

    public void setEntryLongitude(Double d) {
        this.entryLongitude = d;
    }

    public void setHourOfOperation(String str) {
        this.hourOfOperation = str;
    }

    public void setKeywords(List<String> list) {
        this.keywords = list;
    }

    public void setLandlineNo(String str) {
        this.landlineNo = str;
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

    public void setMobileNo(String str) {
        this.mobileNo = str;
    }

    public void setOrderIndex(long j) {
        this.orderIndex = j;
    }

    public void setPlaceAddress(String str) {
        this.placeAddress = str;
    }

    public void setPlaceName(String str) {
        this.placeName = str;
    }

    public void setRichInfo(Map map) {
        this.richInfo = map;
    }

    public void setType(String str) {
        this.type = str;
    }
}
