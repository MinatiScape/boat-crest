package com.mappls.sdk.services.api;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
@Keep
/* loaded from: classes11.dex */
public class Place {
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("areaCode")
    @Expose
    private String areaCode;
    @SerializedName(GeoCodingCriteria.POD_CITY)
    @Expose
    private String city;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;
    @SerializedName("houseName")
    @Expose
    private String houseName;
    @SerializedName("houseNumber")
    @Expose
    private String houseNumber;
    @SerializedName(alternate = {"latitude"}, value = "lat")
    @Expose
    private Double lat;
    @SerializedName(alternate = {"longitude"}, value = "lng")
    @Expose
    private Double lng;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName(alternate = {"eLoc", "place_id"}, value = "mapplsPin")
    @Expose
    private String mapplsPin;
    @SerializedName(GeoCodingCriteria.POD_PINCODE)
    @Expose
    private String pincode;
    @SerializedName(GeoCodingCriteria.POD_POINT_OF_INTEREST)
    @Expose
    private String poi;
    @SerializedName("poi_dist")
    @Expose
    private String poiDist;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName(GeoCodingCriteria.POD_STREET)
    @Expose
    private String street;
    @SerializedName("street_dist")
    @Expose
    private String streetDist;
    @SerializedName("subDistrict")
    @Expose
    private String subDistrict;
    @SerializedName("subLocality")
    @Expose
    private String subLocality;
    @SerializedName("subSubLocality")
    @Expose
    private String subSubLocality;
    @SerializedName(GeoCodingCriteria.POD_VILLAGE)
    @Expose
    private String village;

    public String getArea() {
        return this.area;
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public String getCity() {
        return this.city;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getFormattedAddress() {
        return this.formattedAddress;
    }

    public String getHouseName() {
        return this.houseName;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public Double getLat() {
        return this.lat;
    }

    public Double getLng() {
        return this.lng;
    }

    public String getLocality() {
        return this.locality;
    }

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    public String getPincode() {
        return this.pincode;
    }

    public String getPoi() {
        return this.poi;
    }

    public String getPoiDist() {
        return this.poiDist;
    }

    public String getState() {
        return this.state;
    }

    public String getStreet() {
        return this.street;
    }

    public String getStreetDist() {
        return this.streetDist;
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

    public String getVillage() {
        return this.village;
    }

    public void setArea(String str) {
        this.area = str;
    }

    public void setAreaCode(String str) {
        this.areaCode = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public void setFormattedAddress(String str) {
        this.formattedAddress = str;
    }

    public void setHouseName(String str) {
        this.houseName = str;
    }

    public void setHouseNumber(String str) {
        this.houseNumber = str;
    }

    public void setLat(Double d) {
        this.lat = d;
    }

    public void setLng(Double d) {
        this.lng = d;
    }

    public void setLocality(String str) {
        this.locality = str;
    }

    public void setMapplsPin(String str) {
        this.mapplsPin = str;
    }

    public void setPincode(String str) {
        this.pincode = str;
    }

    public void setPoi(String str) {
        this.poi = str;
    }

    public void setPoiDist(String str) {
        this.poiDist = str;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void setStreet(String str) {
        this.street = str;
    }

    public void setStreetDist(String str) {
        this.streetDist = str;
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

    public void setVillage(String str) {
        this.village = str;
    }
}
