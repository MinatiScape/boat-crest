package com.coveiot.android.leonardo.firebaseservices.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class FitnessCMMessage implements Serializable {
    @SerializedName("buddyUserId")
    @Expose
    public String buddyUserId;
    @SerializedName("buddyUserName")
    @Expose
    public String buddyUserName;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName("requestStatus")
    @Expose
    public String requestStatus;

    public String getBuddyUserId() {
        return this.buddyUserId;
    }

    public String getBuddyUserName() {
        return this.buddyUserName;
    }

    public String getMessage() {
        return this.message;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }

    public void setBuddyUserId(String str) {
        this.buddyUserId = str;
    }

    public void setBuddyUserName(String str) {
        this.buddyUserName = str;
    }

    public void setRequestStatus(String str) {
        this.requestStatus = str;
    }
}
