package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class FCMTokenDataReq implements Serializable {
    @SerializedName("fcmRegistrationToken")
    @Expose
    private String fcmRegistrationToken;

    public String getFcmRegistrationToken() {
        return this.fcmRegistrationToken;
    }

    public void setFcmRegistrationToken(String str) {
        this.fcmRegistrationToken = str;
    }
}
