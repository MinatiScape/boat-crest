package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SRefreshTokenRequest {
    @SerializedName("grant_type")
    private String grantType;
    @SerializedName("refresh_token")
    private String refreshToken;

    public String getGrantType() {
        return this.grantType;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setGrantType(String str) {
        this.grantType = str;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }
}
