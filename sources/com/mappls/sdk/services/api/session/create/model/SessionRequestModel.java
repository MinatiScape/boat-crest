package com.mappls.sdk.services.api.session.create.model;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class SessionRequestModel {
    @SerializedName("associationId")
    private String associationId;
    @SerializedName("deviceAlias")
    @Expose
    private String deviceAlias;
    @SerializedName("deviceFingerprint")
    @Expose
    private String deviceFingerprint;
    @SerializedName("endPoint")
    @Expose
    private String endPoint;
    @SerializedName("nst")
    @Expose
    private Long nst;
    @SerializedName("osName")
    @Expose
    private String osName;
    @SerializedName("osVersion")
    @Expose
    private String osVersion;
    @SerializedName("requestedTTL")
    @Expose
    private Integer requestedTTL;
    @SerializedName(RemoteConfigConstants.RequestFieldKey.SDK_VERSION)
    @Expose
    private String sdkVersion;
    @SerializedName("startPoint")
    @Expose
    private String startPoint;
    @SerializedName("tripDistance")
    @Expose
    private Long tripDistance;
    @SerializedName("tripDuration")
    @Expose
    private Long tripDuration;

    public String getAssociationId() {
        return this.associationId;
    }

    public String getDeviceAlias() {
        return this.deviceAlias;
    }

    public String getDeviceFingerprint() {
        return this.deviceFingerprint;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

    public Long getNst() {
        return this.nst;
    }

    public String getOsName() {
        return this.osName;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public Integer getRequestedTTL() {
        return this.requestedTTL;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public String getStartPoint() {
        return this.startPoint;
    }

    public Long getTripDistance() {
        return this.tripDistance;
    }

    public Long getTripDuration() {
        return this.tripDuration;
    }

    public void setAssociationId(String str) {
        this.associationId = str;
    }

    public void setDeviceAlias(String str) {
        this.deviceAlias = str;
    }

    public void setDeviceFingerprint(String str) {
        this.deviceFingerprint = str;
    }

    public void setEndPoint(@Nullable String str) {
        this.endPoint = str;
    }

    public void setNst(Long l) {
        this.nst = l;
    }

    public void setOsName(String str) {
        this.osName = str;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setRequestedTTL(Integer num) {
        this.requestedTTL = num;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public void setStartPoint(@Nullable String str) {
        this.startPoint = str;
    }

    public void setTripDistance(Long l) {
        this.tripDistance = l;
    }

    public void setTripDuration(Long l) {
        this.tripDuration = l;
    }
}
