package com.coveiot.coveaccess.onboarding.model;

import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class AngelNetworkLocation implements Serializable {
    @SerializedName("address")
    public String address;
    @SerializedName(GeoCodingCriteria.POD_CITY)
    public String city;
    @SerializedName("id")
    public long id;
    @SerializedName("mainOrgDetails")
    public AngelNetwork mainOrgDetails;
    @SerializedName("noOfVolunteers")
    public long noOfVolunteers;
    @SerializedName("orgId")
    public long orgId;
    @SerializedName("phone")
    public String phone;

    public AngelNetworkLocation(long j, long j2, String str, String str2, String str3, long j3, AngelNetwork angelNetwork) {
        this.id = j;
        this.orgId = j2;
        this.city = str;
        this.address = str2;
        this.phone = str3;
        this.noOfVolunteers = j3;
        this.mainOrgDetails = angelNetwork;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public long getId() {
        return this.id;
    }

    public AngelNetwork getMainOrgDetails() {
        return this.mainOrgDetails;
    }

    public long getNoOfVolunteers() {
        return this.noOfVolunteers;
    }

    public long getOrgId() {
        return this.orgId;
    }

    public String getPhone() {
        return this.phone;
    }
}
