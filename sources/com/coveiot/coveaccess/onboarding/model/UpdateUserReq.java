package com.coveiot.coveaccess.onboarding.model;

import com.coveiot.coveaccess.model.CoveApiRequestBaseModel;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public final class UpdateUserReq extends CoveApiRequestBaseModel {
    @SerializedName("_unset")
    @Expose
    private List<String> _unset;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("cloveDeviceBleId")
    @Expose
    private String cloveDeviceBleId;
    @SerializedName("deviceID")
    @Expose
    private String deviceID;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    private final String role = "User";
    private transient String userId;

    public String getBirthDate() {
        return this.birthDate;
    }

    public String getCloveDeviceBleId() {
        return this.cloveDeviceBleId;
    }

    public String getDeviceID() {
        return this.deviceID;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getGender() {
        return this.gender;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return this.name;
    }

    public String getUserId() {
        return this.userId;
    }

    public List<String> get_unset() {
        return this._unset;
    }

    public void setBirthDate(String str) {
        this.birthDate = str;
    }

    public void setCloveDeviceBleId(String str) {
        this.cloveDeviceBleId = str;
    }

    public void setDeviceID(String str) {
        this.deviceID = str;
    }

    public void setEmailId(String str) {
        this.emailId = str;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void set_unset(List<String> list) {
        this._unset = list;
    }
}
