package com.mappls.sdk.services.api.placedetail.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.Map;
@Keep
/* loaded from: classes6.dex */
public class PlaceDetailResponse {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("avgRating")
    @Expose
    private Integer avgRating;
    @SerializedName(GeoCodingCriteria.POD_CITY)
    @Expose
    private String city;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("houseName")
    @Expose
    private String houseName;
    @SerializedName("houseNumber")
    @Expose
    private String houseNumber;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName(alternate = {"eloc"}, value = "mapplsPin")
    @Expose
    private String mapplsPin;
    @SerializedName(GeoCodingCriteria.POD_PINCODE)
    @Expose
    private String pincode;
    @SerializedName("placeName")
    @Expose
    private String placeName;
    @SerializedName(GeoCodingCriteria.POD_POINT_OF_INTEREST)
    @Expose
    private String poi;
    @SerializedName("richInfo")
    @Expose
    private Map richInfo;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName(GeoCodingCriteria.POD_STREET)
    @Expose
    private String street;
    @SerializedName("subDistrict")
    @Expose
    private String subDistrict;
    @SerializedName("subLocality")
    @Expose
    private String subLocality;
    @SerializedName("subSubLocality")
    @Expose
    private String subSubLocality;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName(GeoCodingCriteria.POD_VILLAGE)
    @Expose
    private String village;

    public String getAddress() {
        return this.address;
    }

    public Integer getAvgRating() {
        return this.avgRating;
    }

    public String getCity() {
        return this.city;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getHouseName() {
        return this.houseName;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public String getLocality() {
        return this.locality;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    public String getPincode() {
        return this.pincode;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public String getPoi() {
        return this.poi;
    }

    public Map getRichInfo() {
        return this.richInfo;
    }

    public String getState() {
        return this.state;
    }

    public String getStreet() {
        return this.street;
    }

    public String getSubDistrict() {
        return this.subDistrict;
    }

    public String getSubLocality() {
        return this.subLocality;
    }

    public String getSubSubLocality() {
        return this.subSubLocality;
    }

    public String getType() {
        return this.type;
    }

    public String getVillage() {
        return this.village;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAvgRating(Integer num) {
        this.avgRating = num;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public void setHouseName(String str) {
        this.houseName = str;
    }

    public void setHouseNumber(String str) {
        this.houseNumber = str;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public void setLocality(String str) {
        this.locality = str;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public void setMapplsPin(String str) {
        this.mapplsPin = str;
    }

    public void setPincode(String str) {
        this.pincode = str;
    }

    public void setPlaceName(String str) {
        this.placeName = str;
    }

    public void setPoi(String str) {
        this.poi = str;
    }

    public void setRichInfo(Map map) {
        this.richInfo = map;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void setStreet(String str) {
        this.street = str;
    }

    public void setSubDistrict(String str) {
        this.subDistrict = str;
    }

    public void setSubLocality(String str) {
        this.subLocality = str;
    }

    public void setSubSubLocality(String str) {
        this.subSubLocality = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setVillage(String str) {
        this.village = str;
    }
}
