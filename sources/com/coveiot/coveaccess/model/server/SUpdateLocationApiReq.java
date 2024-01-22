package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SUpdateLocationApiReq {
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("panicCode")
    private String panicCode;
    @SerializedName("userID")
    private String userID;

    public String getLatitude() {
        return this.latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public String getPanicCode() {
        return this.panicCode;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public void setPanicCode(String str) {
        this.panicCode = str;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public String toString() {
        return "UpdateLocationApiReq{userID='" + this.userID + "', panicCode='" + this.panicCode + "', latitude='" + this.latitude + "', longitude='" + this.longitude + "'}";
    }
}
