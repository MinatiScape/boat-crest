package com.coveiot.coveaccess.model.server;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
/* loaded from: classes8.dex */
public class SPostFeedbackToServer {
    @SerializedName("contactPreference")
    private String contactPreference;
    @SerializedName("emailId")
    private String emailId;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName("mobileNumber")
    private String mobileNumber;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;
    @SerializedName(SavingTrackHelper.POINT_COL_CATEGORY)
    private String subject;

    public String getContactPreference() {
        return this.contactPreference;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setContactPreference(String str) {
        this.contactPreference = str;
    }

    public void setEmailId(String str) {
        this.emailId = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setMobileNumber(String str) {
        this.mobileNumber = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSubject(String str) {
        this.subject = str;
    }
}
