package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SEGLoginRequest {
    @SerializedName("googleAdId")
    private String googleAdId;
    @SerializedName("grant_type")
    private String grantType = "password";
    @SerializedName("password")
    private String password;
    @SerializedName("username")
    private String username;

    public SEGLoginRequest(String str, String str2, String str3) {
        this.username = str;
        this.password = str2;
        this.googleAdId = str3;
    }

    public String getGoogleAdId() {
        return this.googleAdId;
    }

    public String getGrantType() {
        return this.grantType;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setGoogleAdId(String str) {
        this.googleAdId = str;
    }

    public void setGrantType(String str) {
        this.grantType = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }
}
