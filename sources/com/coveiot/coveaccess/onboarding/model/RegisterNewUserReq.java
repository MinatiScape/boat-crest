package com.coveiot.coveaccess.onboarding.model;

import com.coveiot.coveaccess.model.CoveApiRequestBaseModel;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.maps.style.layers.Property;
import java.io.File;
/* loaded from: classes8.dex */
public final class RegisterNewUserReq extends CoveApiRequestBaseModel {
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("bleId")
    @Expose
    private String bleId;
    @SerializedName("cloveDeviceBleId")
    @Expose
    private String cloveDeviceBleId;
    @SerializedName("deviceID")
    @Expose
    private String deviceID;
    @SerializedName("dpFile")
    @Expose(deserialize = false, serialize = false)
    private File dpFile;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("gcmRegKey")
    @Expose
    private String gcmRegKey;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("guestUserSessionId")
    @Expose
    private String guestUserSessionId;
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    @Expose
    private String height;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;
    @SerializedName("socialMediaId")
    @Expose
    private String socialMediaId;
    @SerializedName("verificationCode")
    @Expose
    private String verificationCode;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("role")
    @Expose
    private final String role = "User";
    @SerializedName("isCloveUser")
    @Expose
    private final String isCloveUser = BleConst.GetDeviceTime;

    public String getBirthDate() {
        return this.birthDate;
    }

    public String getBleId() {
        return this.bleId;
    }

    public String getCloveDeviceBleId() {
        return this.cloveDeviceBleId;
    }

    public String getDeviceID() {
        return this.deviceID;
    }

    public File getDpFile() {
        return this.dpFile;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getGcmRegKey() {
        return this.gcmRegKey;
    }

    public String getGender() {
        return this.gender;
    }

    public String getGuestUserSessionId() {
        return this.guestUserSessionId;
    }

    public String getHeight() {
        return this.height;
    }

    public String getIsCloveUser() {
        return BleConst.GetDeviceTime;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getSocialMediaId() {
        return this.socialMediaId;
    }

    public String getVerificationCode() {
        return this.verificationCode;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setBirthDate(String str) {
        this.birthDate = str;
    }

    public void setBleId(String str) {
        this.bleId = str;
    }

    public void setCloveDeviceBleId(String str) {
        this.cloveDeviceBleId = str;
    }

    public void setDeviceID(String str) {
        this.deviceID = str;
    }

    public void setDpFile(File file) {
        this.dpFile = file;
    }

    public void setEmailId(String str) {
        this.emailId = str;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setGcmRegKey(String str) {
        this.gcmRegKey = str;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public void setGuestUserSessionId(String str) {
        this.guestUserSessionId = str;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setMobileNumber(String str) {
        this.mobileNumber = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSocialMediaId(String str) {
        this.socialMediaId = str;
    }

    public void setVerificationCode(String str) {
        this.verificationCode = str;
    }

    public void setWeight(String str) {
        this.weight = str;
    }
}
