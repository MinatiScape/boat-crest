package com.mappls.sdk.services.api.geocoding;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes6.dex */
public class GeoCode {
    @SerializedName(GeoCodingCriteria.POD_CITY)
    @Expose
    public String city;
    @SerializedName("confidenceScore")
    @Expose
    public Float confidenceScore;
    @SerializedName("district")
    @Expose
    public String district;
    @SerializedName("formattedAddress")
    @Expose
    public String formattedAddress;
    @SerializedName("geocodeLevel")
    @Expose
    public String geocodeLevel;
    @SerializedName("houseName")
    @Expose
    public String houseName;
    @SerializedName("houseNumber")
    @Expose
    public String houseNumber;
    @SerializedName("latitude")
    @Expose
    public double latitude;
    @SerializedName("locality")
    @Expose
    public String locality;
    @SerializedName("longitude")
    @Expose
    public double longitude;
    @SerializedName(alternate = {"eLoc"}, value = "mapplsPin")
    @Expose
    public String mapplsPin;
    @SerializedName(GeoCodingCriteria.POD_PINCODE)
    @Expose
    public String pincode;
    @SerializedName(GeoCodingCriteria.POD_POINT_OF_INTEREST)
    @Expose
    public String poi;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName(GeoCodingCriteria.POD_STREET)
    @Expose
    public String street;
    @SerializedName("subDistrict")
    @Expose
    public String subDistrict;
    @SerializedName("subLocality")
    @Expose
    public String subLocality;
    @SerializedName("subSubLocality")
    @Expose
    public String subSubLocality;
    @SerializedName(GeoCodingCriteria.POD_VILLAGE)
    @Expose
    public String village;
}
