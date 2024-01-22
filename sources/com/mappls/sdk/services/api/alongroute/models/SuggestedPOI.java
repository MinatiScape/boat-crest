package com.mappls.sdk.services.api.alongroute.models;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import com.szabh.smable3.entity.BleNotificationSettings;
@Keep
/* loaded from: classes11.dex */
public class SuggestedPOI {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("brand_code")
    @Expose
    private String brandCode;
    @SerializedName(SavingTrackHelper.POINT_COL_CATEGORY)
    @Expose
    private String category;
    @SerializedName(GeoCodingCriteria.POD_CITY)
    @Expose
    private String city;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("e_lat")
    @Expose
    private Double eLat;
    @SerializedName("e_lng")
    @Expose
    private Double eLng;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName(alternate = {"place_id"}, value = "mapplsPin")
    @Expose
    private String mapplsPin;
    @SerializedName("orderIndex")
    @Expose
    private Integer orderIndex;
    @SerializedName(GeoCodingCriteria.POD_POINT_OF_INTEREST)
    @Expose
    private String poi;
    @SerializedName("poplrName")
    @Expose
    private String popularName;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("subDistrict")
    @Expose
    private String subDistrict;
    @SerializedName("subLocality")
    @Expose
    private String subLocality;
    @SerializedName("subSubLocality")
    @Expose
    private String subSubLocality;
    @SerializedName(BleNotificationSettings.CALL)
    @Expose
    private String telNo;
    @SerializedName("website")
    @Expose
    private String website;

    public String getAddress() {
        return this.address;
    }

    public String getBrandCode() {
        return this.brandCode;
    }

    public String getCategory() {
        return this.category;
    }

    public String getCity() {
        return this.city;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public String getDistrict() {
        return this.district;
    }

    public Double getELat() {
        return this.eLat;
    }

    public Double getELng() {
        return this.eLng;
    }

    public String getEmail() {
        return this.email;
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

    public Integer getOrderIndex() {
        return this.orderIndex;
    }

    public String getPoi() {
        return this.poi;
    }

    public String getPopularName() {
        return this.popularName;
    }

    public String getState() {
        return this.state;
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

    public String getTelNo() {
        return this.telNo;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setBrandCode(String str) {
        this.brandCode = str;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setDistance(Integer num) {
        this.distance = num;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public void setELat(Double d) {
        this.eLat = d;
    }

    public void setELng(Double d) {
        this.eLng = d;
    }

    public void setEmail(String str) {
        this.email = str;
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

    public void setOrderIndex(Integer num) {
        this.orderIndex = num;
    }

    public void setPoi(String str) {
        this.poi = str;
    }

    public void setPopularName(String str) {
        this.popularName = str;
    }

    public void setState(String str) {
        this.state = str;
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

    public void setTelNo(String str) {
        this.telNo = str;
    }

    public void setWebsite(String str) {
        this.website = str;
    }
}
