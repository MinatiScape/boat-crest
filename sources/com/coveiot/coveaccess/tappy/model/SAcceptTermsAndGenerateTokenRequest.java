package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SAcceptTermsAndGenerateTokenRequest implements Serializable {
    @SerializedName("DeviceId")
    @Expose
    private long deviceId;
    @SerializedName("EncryptedRiskData")
    @Expose
    private String encryptedRiskData;
    private transient long endUserId;
    private transient long id;
    @SerializedName("LocationLatitude")
    @Expose
    private double locationLatitude;
    @SerializedName("LocationLongitude")
    @Expose
    private double locationLongitude;
    @SerializedName("LocationSource")
    @Expose
    private String locationSource;
    @SerializedName("TermsAndConditionsID")
    @Expose
    private String termsAndConditionsID;

    public long getDeviceId() {
        return this.deviceId;
    }

    public String getEncryptedRiskData() {
        return this.encryptedRiskData;
    }

    public long getEndUserId() {
        return this.endUserId;
    }

    public long getId() {
        return this.id;
    }

    public double getLocationLatitude() {
        return this.locationLatitude;
    }

    public double getLocationLongitude() {
        return this.locationLongitude;
    }

    public String getLocationSource() {
        return this.locationSource;
    }

    public String getTermsAndConditionsID() {
        return this.termsAndConditionsID;
    }

    public void setDeviceId(long j) {
        this.deviceId = j;
    }

    public void setEncryptedRiskData(String str) {
        this.encryptedRiskData = str;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setLocationLatitude(double d) {
        this.locationLatitude = d;
    }

    public void setLocationLongitude(double d) {
        this.locationLongitude = d;
    }

    public void setLocationSource(String str) {
        this.locationSource = str;
    }

    public void setTermsAndConditionsID(String str) {
        this.termsAndConditionsID = str;
    }
}
