package com.coveiot.coveaccess.model.server;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SUpdateEgSession {
    @SerializedName(RemoteConfigConstants.RequestFieldKey.APP_ID)
    private String appId;
    @SerializedName(RemoteConfigConstants.RequestFieldKey.APP_VERSION)
    private String appVersion;
    @SerializedName("fcmRegistrationToken")
    private String fcmRegistrationToken;
    @SerializedName("projectId")
    private String projectId;

    public String getAppId() {
        return this.appId;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getFcmRegistrationToken() {
        return this.fcmRegistrationToken;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setFcmRegistrationToken(String str) {
        this.fcmRegistrationToken = str;
    }

    public void setProjectId(String str) {
        this.projectId = str;
    }
}
